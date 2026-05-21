using System.IO;
using System.Windows;
using System.Windows.Threading;
using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using EDC.Core.Config;
using EDC.Core.Execution;
using EDC.Core.Logging;
using EDC.Core.Models;
using EDC.Core.Reports;
using EDC.Core.Tests;

namespace EDC.Wpf.ViewModels;

public partial class MainViewModel : ObservableObject
{
    private readonly EdcConfigStore _store;
    private readonly JavaProcessRunner _runner = new();
    private readonly LogStreamParser _parser = new();
    private readonly LogFileTailer _tailer = new();
    private readonly Dispatcher _ui;
    private readonly string _baseDir;

    public EdcConfig Config { get; }
    public TestSelectionViewModel TestSelection { get; }
    public ResultsLogViewModel ResultsLog { get; } = new();
    public ToastHostViewModel Toasts { get; } = new();
    public ReportsExplorerViewModel Reports { get; }

    /// <summary>
    /// Subset of <see cref="EdcConfig.Servers"/> currently shown in the UI.
    /// The underlying collection always has <see cref="EdcConfig.MaxServers"/> slots for
    /// on-disk compatibility with the Java engine, but the UI only renders rows the user
    /// has explicitly added.
    /// </summary>
    public System.Collections.ObjectModel.ObservableCollection<ServerSlot> VisibleServers { get; } = new();

    [ObservableProperty] private string javaPath = "(not found)";
    [ObservableProperty] private string jarPath = "(not found)";
    // Default follows the Windows app-mode setting (Settings > Personalization >
    // Colors > Choose your mode). Detected once at startup via the registry;
    // the user can still flip it with Backstage > Toggle Theme. Falls back to
    // dark if the registry key is missing or unreadable.
    [ObservableProperty] private bool isDarkTheme = IsSystemDarkTheme();

    public MainViewModel()
    {
        _ui = Application.Current?.Dispatcher ?? Dispatcher.CurrentDispatcher;
        // IMPORTANT: AppContext.BaseDirectory points to the BUNDLE EXTRACTION temp
        // folder when published as single-file with IncludeNativeLibrariesForSelfExtract=true
        // (e.g. %TEMP%\.net\Enterprise Deployment Check\<hash>). The engine, runtime
        // JRE, Input and PythonAPI folders sit next to the .exe in the install dir,
        // so we must resolve _baseDir from Environment.ProcessPath instead.
        //
        // Fallback: when launched via `dotnet exec <app>.dll` (some VS Code launch
        // configurations) Environment.ProcessPath returns dotnet.exe instead of the
        // apphost, so engine\edc.jar would not be found next to it. Detect that and
        // fall back to the directory holding the loaded WPF assembly, which always
        // matches the bin\Debug or install layout.
        _baseDir = Path.GetDirectoryName(Environment.ProcessPath) ?? AppContext.BaseDirectory;
        if (!File.Exists(Path.Combine(_baseDir, "engine", "edc.jar")))
        {
            var asmDir = Path.GetDirectoryName(typeof(MainViewModel).Assembly.Location);
            if (!string.IsNullOrEmpty(asmDir)
                && File.Exists(Path.Combine(asmDir, "engine", "edc.jar")))
            {
                _baseDir = asmDir;
            }
        }

        // Config lives next to the exe in `Input\config.properties` (matches Java app convention).
        // Start with a blank EdcConfig so portal credentials / server slots / test
        // selections are empty by default — the user explicitly clicks Backstage > Open
        // (ReloadConfig) to pull in a previously-saved file, or Save to persist edits.
        // This prevents stale credentials from a prior session showing up unexpectedly,
        // especially during development where bin\Debug\...\Input\config.properties
        // accumulates state.
        var inputDir = Path.Combine(_baseDir, "Input");
        Directory.CreateDirectory(inputDir);
        _store = new EdcConfigStore(Path.Combine(inputDir, "config.properties"));
        Config = new EdcConfig();
        // Refresh the Save button's enabled state whenever any of the three
        // required portal fields change. SaveConfig requires Portal URL,
        // Admin Username and Admin Password to all be non-blank.
        Config.PropertyChanged += (_, e) =>
        {
            if (e.PropertyName is nameof(EdcConfig.PortalUrl)
                              or nameof(EdcConfig.PortalUserName)
                              or nameof(EdcConfig.PortalPassword))
            {
                SaveConfigCommand.NotifyCanExecuteChanged();
            }
        };
        TestSelection = new TestSelectionViewModel(Config);
        Reports = new ReportsExplorerViewModel(_baseDir);

        SyncVisibleServersFromConfig();

        JavaPath = JreLocator.Locate(_baseDir) ?? "(not found — install JRE or set JAVA_HOME)";
        var jar = Path.Combine(_baseDir, "engine", "edc.jar");
        JarPath = File.Exists(jar) ? jar : "(missing — engine\\edc.jar)";

        // Tail the engine's curated log file — it contains only test-case messages
        // (no Maven / TestNG / SLF4J noise). The stdout/stderr streams from the Java
        // process are dropped here, *except* for the EDCTestListener result line which
        // is the only signal that updates the Passed / Failed / Skipped counters.
        _tailer.LineRead += (_, line) => _ui.BeginInvoke(() => HandleLine(line));

        _runner.OutputReceived += (_, line) => _ui.BeginInvoke(() => HandleCounterOnly(line));
        // JVM stderr: mostly SLF4J / TestNG noise, but it's also where the engine
        // writes startup validation failures ("Please provide data for at least
        // one server url", etc.). Keep the last N lines so we can surface them
        // to the user when the process exits non-zero.
        _runner.ErrorReceived += (_, line) => _ui.BeginInvoke(() => OnStderrLine(line));
        _runner.Exited += (_, exitCode) => _ui.BeginInvoke(() =>
        {
            _tailer.Stop();
            if (exitCode != 0)
            {
                SurfaceStderrOnFailure(exitCode);
            }
            ResultsLog.OnRunStopped(exitCode, _selectedTestNamesForRun);
            Reports.Refresh();
            // Re-evaluate the ribbon buttons: Run becomes enabled, Stop becomes disabled.
            OnPropertyChanged(nameof(CanRun));
            OnPropertyChanged(nameof(CanStop));
        });

        // App.xaml merges the dark surface palette + Fluent Dark.Cobalt by default.
        // If the system is set to light mode, swap to the light dictionaries now so
        // the very first frame matches the OS theme.
        if (!IsDarkTheme)
            ApplyTheme(false);
    }

    // Ring buffer of the most recent JVM stderr lines. On non-zero exit we promote
    // the meaningful lines (validation failures, exceptions) into the Results Log
    // and a toast so the user actually sees the reason the engine bailed.
    private readonly System.Collections.Generic.Queue<string> _stderrBuffer = new();
    private const int StderrBufferMax = 80;

    // Set when the user clicks Stop. The JVM will exit with a non-zero code as a
    // result of being killed, but that's expected — suppress the failure toast
    // and don't promote any of the stderr chatter (e.g. Selenium CDP WARNINGs)
    // into the user-facing Results Log.
    private bool _stopRequested;

    // Snapshot of the user's selected-test names at the moment Run was clicked.
    // Used by the Exited \u2192 OnRunStopped path to synthesize Skipped entries in
    // the Results Log for any selected test the engine never reached (covers
    // both user-initiated Stop and an unexpected JVM crash).
    private System.Collections.Generic.IReadOnlyList<string> _selectedTestNamesForRun =
        System.Array.Empty<string>();

    private void OnStderrLine(string line)
    {
        if (string.IsNullOrWhiteSpace(line)) return;
        _stderrBuffer.Enqueue(line);
        while (_stderrBuffer.Count > StderrBufferMax) _stderrBuffer.Dequeue();

        // Promote obvious errors live, so the user sees them as they happen
        // instead of waiting for the process to exit. Selenium / WebDriver /
        // SLF4J / java.util.logging routinely write WARNING and INFO lines to
        // stderr (DevTools session noise, CDP version mismatches, deprecation
        // notices, etc.) — those are kept in the ring buffer for diagnostics
        // but must NOT bubble into the user-facing Results Log as errors.
        // Java exception / stack-trace lines are also suppressed here — they
        // remain in the buffer for a crash dump on failure but are hidden from
        // the live log so the user does not see raw e.printStackTrace() output.
        if (JavaExceptionDetail.IsMatch(line)) return;
        if (LooksLikeEngineError(line) && !IsBenignWarning(line))
        {
            ResultsLog.Append(LogEntry.Error(line));
        }
    }

    private static bool LooksLikeEngineError(string line) =>
        line.Contains("Validation failed", StringComparison.OrdinalIgnoreCase) ||
        line.Contains("Exception", StringComparison.Ordinal) ||
        line.StartsWith("Caused by", StringComparison.Ordinal) ||
        line.Contains(" FATAL ", StringComparison.Ordinal) ||
        line.StartsWith("Error:", StringComparison.OrdinalIgnoreCase);

    // Java exception / stack-trace text that should NEVER reach the user-facing
    // Results Log, regardless of which stream produced it. The lines are still
    // kept in the diagnostic stderr ring buffer for crash reports.
    private static readonly System.Text.RegularExpressions.Regex JavaExceptionDetail =
        new(@"(?:^|\s)(?:Exception in thread\b|Caused by:\s|Suppressed:\s|\bat\s+[\w$.<>]+\([^)]*\)|\.{3}\s*\d+\s+more\b|(?:[a-z][\w$]*\.){2,}[A-Z][\w$]*(?:Exception|Error|Throwable)\b|\b(?:Build info|Session info|Driver info|System info):\s)",
            System.Text.RegularExpressions.RegexOptions.Compiled);

    /// <summary>
    /// Returns true for stderr lines that look like routine Selenium / WebDriver /
    /// logging-framework chatter rather than a real engine failure. These are
    /// suppressed from the user-facing Results Log (but still buffered for the
    /// on-exit diagnostic dump).
    /// </summary>
    private static bool IsBenignWarning(string line)
    {
        // java.util.logging default formatter: "WARNING: ..." / "INFO: ..."
        if (line.StartsWith("WARNING:", StringComparison.Ordinal)) return true;
        if (line.StartsWith("INFO:",    StringComparison.Ordinal)) return true;
        // SLF4J bootstrap noise ("SLF4J: Class path contains multiple SLF4J bindings" etc.)
        if (line.StartsWith("SLF4J:",   StringComparison.Ordinal)) return true;
        // Common Log4j / SLF4J severity markers embedded mid-line.
        if (line.Contains(" WARN ",  StringComparison.Ordinal)) return true;
        if (line.Contains(" WARN: ", StringComparison.Ordinal)) return true;
        if (line.Contains(" INFO ",  StringComparison.Ordinal)) return true;
        // Selenium DevTools / CDP chatter that's emitted as WARNING/INFO but
        // mentions a fully-qualified org.openqa class and so trips the
        // "Exception"-substring check above via "DevToolsException" /
        // "WebDriverException" in the class name without an actual stack frame.
        if (line.Contains("org.openqa.selenium.devtools", StringComparison.Ordinal)) return true;
        if (line.Contains("CDP implementation",          StringComparison.Ordinal)) return true;
        if (line.Contains("Unable to find an exact match for CDP", StringComparison.Ordinal)) return true;
        // java.util.logging timestamp prefix ("May 18, 2026 12:08:47 AM org.openqa...")
        // — the *next* line is the WARNING text; that line itself is just a header.
        if (System.Text.RegularExpressions.Regex.IsMatch(
                line,
                @"^[A-Z][a-z]{2} \d{1,2}, \d{4} \d{1,2}:\d{2}:\d{2} (AM|PM) \S"))
            return true;
        return false;
    }

    private void SurfaceStderrOnFailure(int exitCode)
    {
        if (_stderrBuffer.Count == 0) return;

        // If the user clicked Stop, the JVM was killed deliberately. The exit
        // code will be non-zero and stderr likely contains Selenium teardown
        // chatter (UnreachableBrowserException, CDP version mismatch WARNINGs,
        // etc.) — none of which the user needs to see as a popup.
        if (_stopRequested)
        {
            _stderrBuffer.Clear();
            return;
        }

        // Show the most actionable line (a Validation failed / Exception line if
        // we have one, otherwise the last non-benign stderr line) in a toast so
        // the user is not left staring at "stopped exit(1)".
        string? best = null;
        foreach (var l in _stderrBuffer)
        {
            if (JavaExceptionDetail.IsMatch(l)) continue;
            if (LooksLikeEngineError(l) && !IsBenignWarning(l)) { best = l; break; }
        }
        if (best is null)
        {
            // Fallback: walk the buffer from newest to oldest and pick the most
            // recent line that ISN'T benign noise OR exception text. If
            // everything is benign / exception detail, skip the toast entirely.
            var arr = _stderrBuffer.ToArray();
            for (int i = arr.Length - 1; i >= 0; i--)
            {
                if (string.IsNullOrWhiteSpace(arr[i])) continue;
                if (IsBenignWarning(arr[i])) continue;
                if (JavaExceptionDetail.IsMatch(arr[i])) continue;
                best = arr[i];
                break;
            }
        }

        if (best is null)
        {
            // All buffered stderr was benign or exception detail — don't bother
            // the user with a popup that just repeats stack trace text.
            _stderrBuffer.Clear();
            return;
        }

        ResultsLog.Append(LogEntry.Error($"Engine exited with code {exitCode}: {best}"));
        Toasts.Show($"Engine failed (exit {exitCode})", best, ToastSeverity.Error, autoDismissMs: 12000);

        _stderrBuffer.Clear();
    }

    /// <summary>Show row 0 plus every additional row that already has a URL persisted.</summary>
    private void SyncVisibleServersFromConfig()
    {
        // Build the target list (slot 0 always; plus any later slot that has a URL).
        var target = new List<ServerSlot> { Config.Servers[0] };
        for (int i = 1; i < EdcConfig.MaxServers; i++)
        {
            if (!string.IsNullOrWhiteSpace(Config.Servers[i].Url))
                target.Add(Config.Servers[i]);
        }

        // Incremental diff: avoid a Clear()+Add() cycle on Reload — that
        // teardown/rebuild of the ItemsControl's containers races with the
        // PasswordBox.Loaded handler and leaves rows visibly blank even though
        // the underlying ServerSlot is populated. CopyInto has already fired
        // PropertyChanged on every existing slot, so rows that stay visible
        // refresh through their bindings without needing a remount.

        // 1. Remove any visible slot that is no longer in the target set.
        for (int i = VisibleServers.Count - 1; i >= 0; i--)
        {
            if (!target.Contains(VisibleServers[i]))
                VisibleServers.RemoveAt(i);
        }
        // 2. Append any slot in the target set that isn't yet visible, preserving target order.
        foreach (var slot in target)
        {
            if (!VisibleServers.Contains(slot))
                VisibleServers.Add(slot);
        }
    }

    public bool CanAddServer    => VisibleServers.Count < EdcConfig.MaxServers;
    public bool CanRemoveServer => VisibleServers.Count > 1;

    [RelayCommand]
    private void AddServer()
    {
        // Find the first underlying slot not currently shown and append it.
        foreach (var slot in Config.Servers)
        {
            if (!VisibleServers.Contains(slot))
            {
                VisibleServers.Add(slot);
                OnPropertyChanged(nameof(CanAddServer));
                OnPropertyChanged(nameof(CanRemoveServer));
                return;
            }
        }
    }

    [RelayCommand]
    private void RemoveServer(ServerSlot? slot)
    {
        if (slot is null) return;
        if (VisibleServers.Count <= 1) return; // keep at least one row visible
        // Clear the slot so it isn't persisted, then hide it.
        slot.Url = string.Empty;
        slot.AdminUsername = string.Empty;
        slot.AdminPassword = string.Empty;
        slot.Federated = true;
        slot.Role = "Hosting Server";
        VisibleServers.Remove(slot);
        OnPropertyChanged(nameof(CanAddServer));
        OnPropertyChanged(nameof(CanRemoveServer));
    }

    // Run is gated only on "not currently running". We deliberately do NOT
    // require java.exe / edc.jar to exist here, because in dev/Debug those
    // payload files live in the published tree and would silently disable
    // the Run button with no on-screen explanation. RunTestsAsync re-checks
    // both paths and surfaces an actionable toast ("Java not found" /
    // "Engine missing") if either is missing.
    public bool CanRun => !ResultsLog.IsRunning;
    public bool CanStop => ResultsLog.IsRunning;

    [RelayCommand]
    private async Task RunTestsAsync()
    {
        if (string.IsNullOrWhiteSpace(Config.PortalUrl))
        {
            Toasts.Show("Missing input", "Portal URL is required before running tests.", ToastSeverity.Warning);
            return;
        }
        if (string.IsNullOrWhiteSpace(Config.PortalUserName))
        {
            Toasts.Show("Missing input", "Portal Admin Username is required before running tests.", ToastSeverity.Warning);
            return;
        }
        if (string.IsNullOrWhiteSpace(Config.PortalPassword))
        {
            Toasts.Show("Missing input", "Portal Admin Password is required before running tests.", ToastSeverity.Warning);
            return;
        }
        if (TestSelectionRules.CountSelectedTests(Config) == 0)
        {
            Toasts.Show("No tests selected", "Select at least one test case before running.", ToastSeverity.Warning);
            return;
        }
        // Any of these tests will, inside the Java engine, require at least one
        // populated server slot. We do NOT include RunAllTest here:
        //  * The WPF cascade keeps the individual server flags in sync with
        //    RunAllTest (ApplyRunAll honours Kubernetes, the reverse cascade
        //    in TestSelectionViewModel clears RunAllTest on partial deselect,
        //    and EdcConfigStore.Load() normalises inconsistent files).
        //  * Under Kubernetes, RunAllTest legitimately means "every test
        //    EXCEPT the server-admin ones" — requiring a server slot there
        //    would block valid K8s runs.
        bool needsServer = Config.LoginManager || Config.LoginServer
                        || Config.ValidateServerRole || Config.ValidateDataStores;
        if (needsServer)
        {
            bool anyServerComplete = false;
            for (int i = 0; i < EdcConfig.MaxServers; i++)
            {
                var s = Config.Servers[i];
                if (!string.IsNullOrWhiteSpace(s.Url) &&
                    !string.IsNullOrWhiteSpace(s.AdminUsername) &&
                    !string.IsNullOrWhiteSpace(s.AdminPassword))
                {
                    anyServerComplete = true;
                    break;
                }
            }
            if (!anyServerComplete)
            {
                Toasts.Show("Missing server credentials",
                    "At least one server slot (URL + Admin Username + Admin Password) is required when 'Login to Server Admin', 'Login to Server Manager', 'Validate Server Roles', or 'Validate Data Stores' is selected.",
                    ToastSeverity.Warning, autoDismissMs: 10000);
                return;
            }
        }
        if (!File.Exists(JavaPath))
        {
            Toasts.Show("Java not found",
                $"Expected: {Path.Combine(_baseDir, "runtime", "bin", "java.exe")}. Install JRE 17 or set JAVA_HOME.",
                ToastSeverity.Error, autoDismissMs: 8000);
            return;
        }
        if (!File.Exists(JarPath))
        {
            Toasts.Show("Engine missing",
                $"Expected: {Path.Combine(_baseDir, "engine", "edc.jar")}.",
                ToastSeverity.Error, autoDismissMs: 8000);
            return;
        }

        _store.Save(Config);
        _stopRequested = false;
        // Snapshot the selected-test list at run start. The Stop / Exited path
        // uses it to synthesize Skipped entries in the Results Log for any
        // selected test the engine never reached (so the user sees the full set
        // accounted for, not just the few that ran before they hit Stop).
        _selectedTestNamesForRun = EDC.Core.Tests.TestSelectionRules
            .EnumerateSelectedTestNames(Config).ToList();
        ResultsLog.OnRunStarted();
        OnPropertyChanged(nameof(CanRun));
        OnPropertyChanged(nameof(CanStop));

        // Start tailing today's engine log directory so curated test output streams
        // into the Results Log as soon as the JVM creates the file.
        var logsDir = Path.Combine(_baseDir, "EnterpriseDeploymentCheckResults",
            DateTime.Now.ToString("yyyy-MM-dd"), "Logs");
        _tailer.Start(logsDir);

        try
        {
            await _runner.StartAsync(new JavaProcessRunner.Options(
                JavaExe: JavaPath,
                JarPath: JarPath,
                WorkingDirectory: _baseDir));
        }
        catch (Exception ex)
        {
            ResultsLog.Append(LogEntry.Error("Failed to start engine: " + ex.Message));
            ResultsLog.OnRunStopped(-1, _selectedTestNamesForRun);
        }
        OnPropertyChanged(nameof(CanRun));
        OnPropertyChanged(nameof(CanStop));
    }

    [RelayCommand]
    private async Task StopTestsAsync()
    {
        _stopRequested = true;
        // Snapshot the Skipped counter and the total selected tests so we can
        // tell the user exactly how many were marked Skipped by this Stop.
        var skippedBefore = ResultsLog.Skipped;
        var selectedTotal = EDC.Core.Tests.TestSelectionRules.CountSelectedTests(Config);
        var completedBefore = ResultsLog.Total;
        // Reflect the user's intent in the Results Log immediately, not after
        // the JVM finishes tearing down (which can take a few seconds while
        // Selenium closes browsers and the @AfterSuite cleanup runs). This:
        //   * sets the status badge to "Stopped"
        //   * marks the in-flight test-case header as "Skipped" and increments
        //     the Skipped counter so the user sees the cancellation reflected.
        ResultsLog.OnStopRequested();
        // NOTE: We intentionally do NOT stop the log tailer here. The Java engine's
        // shutdown hook runs DeleteAllFolder / cleanup during the graceful window
        // below and writes those messages to the engine log file; we want the user
        // to see them stream into the Results Log. The tailer is stopped in the
        // Exited handler once the JVM is actually gone.
        // Cooperative stop sends "STOP" on stdin. The Java engine sets its global
        // `stop` flag, so every remaining @Test method short-circuits via
        // skipTest(...) — TestNG records each as SKIPPED in the Extent report
        // and emits a Skip line that streams into the Results Log via the tailer.
        // The graceful window must cover three things in sequence:
        //   1. The in-flight test finishes its current step (can't be interrupted
        //      safely — would leak Selenium / browser state).
        //   2. Every remaining @Test method enters and self-skips (fast).
        //   3. @AfterSuite (DeleteCreatedTestData → PythonHelp.DeleteAllFolder)
        //      walks the portal and deletes every artifact created during the
        //      run. That EXE call can legitimately take 2–4 minutes.
        // 5 minutes is a generous safety net; the JVM exits as soon as cleanup
        // finishes, so this isn't extra wait time in the common case.
        _runner.GracefulStopTimeout = TimeSpan.FromMinutes(5);
        await _runner.StopAsync();
        // Best-effort skipped count for the toast. The Exited → OnRunStopped path
        // may still be queued on the UI dispatcher at this point, so prefer the
        // planned count (selected - already-completed-before-stop) which is what
        // the user actually expects to see.
        var plannedSkip = System.Math.Max(0, selectedTotal - completedBefore);
        var msg = plannedSkip > 0
            ? $"{plannedSkip} test{(plannedSkip == 1 ? string.Empty : "s")} marked as skipped. The in-flight test finished its current step; @AfterSuite cleanup (DeleteAllFolder) ran to completion."
            : "Engine terminated. Results log preserved.";
        Toasts.Show("Tests stopped", msg, ToastSeverity.Info, autoDismissMs: 10000);
        OnPropertyChanged(nameof(CanRun));
        OnPropertyChanged(nameof(CanStop));
    }

    // Save is gated on Portal URL + Admin Username + Admin Password all being
    // non-blank — saving an empty / partial config would just produce an
    // unusable config.properties that the Java engine would reject on the next
    // run with a validation error.
    private bool CanSaveConfig =>
        !string.IsNullOrWhiteSpace(Config.PortalUrl)
        && !string.IsNullOrWhiteSpace(Config.PortalUserName)
        && !string.IsNullOrWhiteSpace(Config.PortalPassword);

    [RelayCommand(CanExecute = nameof(CanSaveConfig))]
    private void SaveConfig()
    {
        _store.Save(Config);
        // The on-disk file now has a real PortalUrl, so Reload becomes meaningful.
        ReloadConfigCommand.NotifyCanExecuteChanged();
        Toasts.Show("Configuration saved", "Input\\config.properties has been updated.", ToastSeverity.Success);
    }

    [RelayCommand(CanExecute = nameof(CanReloadConfig))]
    private void ReloadConfig()
    {
        var fresh = _store.Load();
        CopyInto(fresh, Config);
        SyncVisibleServersFromConfig();
        OnPropertyChanged(nameof(CanAddServer));
        Toasts.Show("Configuration reloaded", "Re-read from Input\\config.properties.", ToastSeverity.Info);
    }

    // Reload reads from disk. There is nothing useful to reload until the user
    // has saved a real configuration (PortalUrl populated). On a fresh install
    // / freshly-published build the disk file is the blank shippable template,
    // so the button stays disabled to avoid wiping unsaved edits with blanks.
    private bool CanReloadConfig
    {
        get
        {
            try
            {
                if (!System.IO.File.Exists(_store.ConfigPath)) return false;
                var disk = _store.Load();
                return !string.IsNullOrWhiteSpace(disk.PortalUrl);
            }
            catch { return false; }
        }
    }

    [RelayCommand]
    private void NewConfig()
    {
        var r = MessageBox.Show(
            "Reset all portal credentials, server slots and test selections to defaults?\n\nUnsaved edits will be lost.",
            "New configuration",
            MessageBoxButton.OKCancel,
            MessageBoxImage.Question);
        if (r != MessageBoxResult.OK) return;
        CopyInto(new EdcConfig(), Config);
        SyncVisibleServersFromConfig();
        OnPropertyChanged(nameof(CanAddServer));
    }

    [RelayCommand]
    private void OpenReport()
    {
        var report = ReportLocator.FindLatestHtmlReport(_baseDir)
                  ?? ReportLocator.FindLatestLog(_baseDir);
        if (report is null)
        {
            Toasts.Show("No report", "No reports found yet. Run a test first.", ToastSeverity.Info);
            return;
        }
        System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo(report) { UseShellExecute = true });
    }

    [RelayCommand]
    private void OpenResultsFolder()
    {
        var root = ReportLocator.ResultsRoot(_baseDir);
        Directory.CreateDirectory(root);
        System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo(root) { UseShellExecute = true });
    }

    [RelayCommand]
    private void OpenHelpDoc()
    {
        // Help PDF lives in the Input folder next to the executable.
        const string fileName = "Enterprise Deployment Check - Help Doc.pdf";
        var pdf = Path.Combine(_baseDir, "Input", fileName);
        if (!File.Exists(pdf))
        {
            // Back-compat: also accept the legacy location (next to the exe).
            var legacy = Path.Combine(_baseDir, fileName);
            if (File.Exists(legacy)) pdf = legacy;
        }
        if (!File.Exists(pdf))
        {
            Toasts.Show("Help doc not found",
                $"Place \"{fileName}\" in the Input folder next to the executable.",
                ToastSeverity.Warning);
            return;
        }
        System.Diagnostics.Process.Start(new System.Diagnostics.ProcessStartInfo(pdf) { UseShellExecute = true });
    }

    [RelayCommand]
    private void ToggleTheme()
    {
        IsDarkTheme = !IsDarkTheme;
        ApplyTheme(IsDarkTheme);
    }

    /// <summary>
    /// Reads the Windows app-mode preference (Settings > Personalization > Colors).
    /// Returns true when the user has chosen dark mode for apps.
    /// </summary>
    private static bool IsSystemDarkTheme()
    {
        try
        {
            using var key = Microsoft.Win32.Registry.CurrentUser.OpenSubKey(
                @"Software\Microsoft\Windows\CurrentVersion\Themes\Personalize");
            // AppsUseLightTheme: 0 = dark, 1 = light. Default to dark when absent.
            var value = key?.GetValue("AppsUseLightTheme");
            if (value is int i) return i == 0;
        }
        catch
        {
            // Registry access failed (locked-down policy, sandboxed env, etc.) —
            // fall back to dark so the app still renders consistently.
        }
        return true;
    }

    private static void ApplyTheme(bool dark)
    {
        var app = Application.Current;
        if (app is null) return;
        var dicts = app.Resources.MergedDictionaries;

        // Swap the Fluent.Ribbon colour variant (Dark.Cobalt ↔ Light.Cobalt).
        var fluentVariant = $"pack://application:,,,/Fluent;component/Themes/Themes/{(dark ? "Dark.Cobalt" : "Light.Cobalt")}.xaml";
        var fluentIndex = -1;
        for (int i = 0; i < dicts.Count; i++)
        {
            var src = dicts[i].Source?.ToString() ?? string.Empty;
            if (src.Contains("Fluent;component/Themes/Themes/", StringComparison.OrdinalIgnoreCase))
            {
                fluentIndex = i;
                break;
            }
        }
        if (fluentIndex >= 0)
            dicts[fluentIndex] = new ResourceDictionary { Source = new Uri(fluentVariant) };

        // Swap our own surface palette (Dark.xaml ↔ Light.xaml).
        var surfaceUri = $"Themes/{(dark ? "Dark" : "Light")}.xaml";
        var surfaceIndex = -1;
        for (int i = 0; i < dicts.Count; i++)
        {
            var src = dicts[i].Source?.ToString() ?? string.Empty;
            if (src.EndsWith("Themes/Dark.xaml", StringComparison.OrdinalIgnoreCase) ||
                src.EndsWith("Themes/Light.xaml", StringComparison.OrdinalIgnoreCase))
            {
                surfaceIndex = i;
                break;
            }
        }
        if (surfaceIndex >= 0)
            dicts[surfaceIndex] = new ResourceDictionary { Source = new Uri(surfaceUri, UriKind.Relative) };
    }

    private void HandleLine(string raw)
    {
        var r = _parser.Parse(raw);
        if (r.Drop) return;
        if (r.ResultUpdate is not null)
            ResultsLog.RecordOutcome(r.ResultUpdate.Outcome);

        // The Results Log pane mirrors the Extent report: it only shows the
        // curated status events (PASS / FAIL / SKIP / WARN / ERROR) and the
        // "<name> Started" test-case banners. Everything else (per-step
        // setText* progress chatter, framework noise) stays in the archival
        // log file on disk but is hidden from the user-facing pane.
        bool isStatusEvent = r.Entry.Level is LogLevel.Pass
                                            or LogLevel.Fail
                                            or LogLevel.Skip
                                            or LogLevel.Warn
                                            or LogLevel.Error;
        if (!isStatusEvent && !IsTestCaseBanner(raw)) return;

        ResultsLog.Append(r.Entry);
    }

    // A banner line is the unprefixed "<TestName> ... started" message the
    // Java engine emits via setTextGreen() at the top of each test case.
    // We allow it through the pane filter so ResultsLog.Append can promote
    // it to a new test-case group header.
    private static bool IsTestCaseBanner(string raw)
    {
        if (string.IsNullOrWhiteSpace(raw)) return false;
        var t = raw.Trim();
        if (t.Contains(':')) return false;
        return t.IndexOf(" started", StringComparison.OrdinalIgnoreCase) > 0;
    }

    // Stdout sidecar: scan for the listener's result line only. The line never reaches
    // the Results Log pane; it just bumps the Passed / Failed / Skipped counters.
    private void HandleCounterOnly(string raw)
    {
        var r = _parser.Parse(raw);
        if (r.ResultUpdate is not null)
            ResultsLog.RecordOutcome(r.ResultUpdate.Outcome);
    }

    private static void CopyInto(EdcConfig from, EdcConfig to)
    {
        to.PortalUrl = from.PortalUrl;
        to.PortalUserName = from.PortalUserName;
        to.PortalPassword = from.PortalPassword;
        to.Kubernetes = from.Kubernetes;
        to.Browser = from.Browser;
        to.RunAllTest = from.RunAllTest;
        to.LoginPortal = from.LoginPortal;
        to.LoginManager = from.LoginManager;
        to.LoginServer = from.LoginServer;
        to.ValidateServerRole = from.ValidateServerRole;
        to.ValidateDataStores = from.ValidateDataStores;
        to.ValidateOrganization = from.ValidateOrganization;
        to.CreateFeatureLayer = from.CreateFeatureLayer;
        to.CreateTileLayer = from.CreateTileLayer;
        to.CreateSceneLayer = from.CreateSceneLayer;
        to.CreateMap = from.CreateMap;
        to.CreateWebAppBuilder = from.CreateWebAppBuilder;
        to.CreateExperienceBuilderApp = from.CreateExperienceBuilderApp;
        to.CreateMember = from.CreateMember;
        to.CreateDashboard = from.CreateDashboard;
        to.CreateStoryMap = from.CreateStoryMap;
        for (int i = 0; i < EdcConfig.MaxServers; i++)
        {
            to.Servers[i].Url = from.Servers[i].Url;
            to.Servers[i].AdminUsername = from.Servers[i].AdminUsername;
            to.Servers[i].AdminPassword = from.Servers[i].AdminPassword;
            to.Servers[i].Role = from.Servers[i].Role;
            to.Servers[i].Federated = from.Servers[i].Federated;
        }
    }
}

using System.Collections.ObjectModel;
using System.Linq;
using System.Windows.Threading;
using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using EDC.Core.Logging;

namespace EDC.Wpf.ViewModels;

/// <summary>
/// Backs the Results Log pane: summary counters, progress, elapsed timer, log rows
/// (grouped by test case as collapsible sections).
/// </summary>
public partial class ResultsLogViewModel : ObservableObject
{
    private const int MaxRows = 5000;

    // Flat list kept for the Copy / Select-all / clipboard plumbing.
    public ObservableCollection<LogEntry> Entries { get; } = new();

    // Grouped view: each test case is a collapsible row in the pane.
    public ObservableCollection<TestCaseLogGroup> Groups { get; } = new();

    /// <summary>Active filter for the Groups view (All / Passed / Failed / Skipped).</summary>
    [ObservableProperty] private LogFilter activeLogFilter = LogFilter.All;

    partial void OnActiveLogFilterChanged(LogFilter value) => OnPropertyChanged(nameof(FilteredGroups));

    /// <summary>Returns Groups filtered by <see cref="ActiveLogFilter"/>.</summary>
    public System.Collections.Generic.IEnumerable<TestCaseLogGroup> FilteredGroups => ActiveLogFilter switch
    {
        LogFilter.Passed  => Groups.Where(g => g.Outcome == LogLevel.Pass),
        LogFilter.Failed  => Groups.Where(g => g.Outcome is LogLevel.Fail or LogLevel.Error),
        LogFilter.Skipped => Groups.Where(g => g.Outcome == LogLevel.Skip),
        _                 => Groups,
    };

    private TestCaseLogGroup? _currentGroup;

    // Set true the moment the Stop button is clicked. Any test-case outcome
    // (typically a Skip) the Java engine emits while it's tearing down is then
    // ignored for the in-flight group so its header stays "Stopped" instead of
    // flipping to "Skipped" and incrementing the Skipped counter.
    private bool _stopRequested;

    // Snapshot of the test-case group that was in-flight when Stop was pressed.
    // After the cleanup banner ("Deleting created test data started") creates a
    // new group, any stray log lines that look like leftover Selenium steps from
    // the aborted test are routed back to this group instead of polluting the
    // cleanup section.
    private TestCaseLogGroup? _stoppedGroup;

    [ObservableProperty] private int passed;
    [ObservableProperty] private int failed;
    [ObservableProperty] private int skipped;

    /// <summary>Total number of test cases recorded so far (passed + failed + skipped).</summary>
    public int Total => Passed + Failed + Skipped;

    partial void OnPassedChanged(int value) => OnPropertyChanged(nameof(Total));
    partial void OnFailedChanged(int value) => OnPropertyChanged(nameof(Total));
    partial void OnSkippedChanged(int value) => OnPropertyChanged(nameof(Total));

    [ObservableProperty] private bool isRunning;
    // Default false so the status-bar / summary progress bars don't animate before any run starts.
    [ObservableProperty] private bool isIndeterminate;
    [ObservableProperty] private double progressValue;
    [ObservableProperty] private string statusText = "Idle";
    [ObservableProperty] private string elapsedText = "00:00";

    private DateTime _startedUtc;
    private readonly DispatcherTimer _timer;

    public ResultsLogViewModel()
    {
        _timer = new DispatcherTimer(DispatcherPriority.Normal) { Interval = TimeSpan.FromSeconds(1) };
        _timer.Tick += (_, _) =>
        {
            var span = DateTime.UtcNow - _startedUtc;
            ElapsedText = span.TotalHours >= 1
                ? span.ToString(@"hh\:mm\:ss")
                : span.ToString(@"mm\:ss");
        };
        Groups.CollectionChanged += (_, _) => OnPropertyChanged(nameof(FilteredGroups));
    }

    public void OnRunStarted()
    {
        Passed = Failed = Skipped = 0;
        Entries.Clear();
        Groups.Clear();
        _currentGroup = null;
        _stopRequested = false;
        _stoppedGroup = null;
        IsRunning = true;
        IsIndeterminate = true;
        ProgressValue = 0;
        StatusText = "Running…";
        _startedUtc = DateTime.UtcNow;
        ElapsedText = "00:00";
        _timer.Start();
    }

    /// <summary>
    /// Called synchronously from the Stop command (before the JVM has actually
    /// exited). Marks the in-flight test case as Skipped (user-cancelled) so its
    /// header reads "Skipped" instead of flipping later, and increments the
    /// Skipped counter immediately. Late SKIP/PASS/FAIL outcomes the Java engine
    /// may emit for the same test while it's tearing down are filtered in
    /// <see cref="RecordOutcome"/> so the counter doesn't double-count.
    /// </summary>
    public void OnStopRequested()
    {
        _stopRequested = true;
        // Show progress activity while the JVM is still doing cooperative
        // shutdown (in-flight test finishing + remaining tests self-skipping +
        // @AfterSuite DeleteAllFolder cleanup walking the portal). OnRunStopped
        // switches the badge to a final "Stopped" once the JVM actually exits.
        StatusText = "Stopping… (cleanup running)";
        IsIndeterminate = true;
        if (_currentGroup is { Outcome: null } g)
        {
            if (IsCleanupGroup(g))
            {
                // The post-suite "Deleting created test data" section isn't a
                // @Test — it's the cleanup phase. If it's in flight when Stop
                // is pressed, treat it as Passed (its steps run to completion
                // during the graceful window) and don't touch the counters.
                g.Outcome = LogLevel.Pass;
            }
            else
            {
                g.Outcome = LogLevel.Skip;
                Skipped++;
                _stoppedGroup = g;
            }
            // Keep IsExpanded = true so the user can still see the steps that ran.
        }
    }

    public void OnRunStopped(int exitCode, System.Collections.Generic.IEnumerable<string>? selectedTestNames = null)
    {
        IsRunning = false;
        IsIndeterminate = false;
        _timer.Stop();
        // If the status was already set to "Stopping…" by the Stop button
        // handler (user-initiated termination), finalize it to "Stopped" now
        // that the JVM has actually exited (cleanup either ran to completion
        // or was force-killed at the graceful-timeout boundary).
        bool userStop = _stopRequested;
        if (userStop)
            StatusText = "Stopped";
        else
            StatusText = exitCode == 0 ? "Completed" : $"Stopped (exit {exitCode})";

        // Any test case that was in flight when the run ended without a PASS /
        // FAIL / SKIP line of its own:
        //   * user-initiated stop → mark Skipped (consistent with new Stop semantics)
        //   * crash / non-zero exit  → mark Stopped (engine died unexpectedly)
        if (_currentGroup is { Outcome: null, IsStopped: false } cur)
        {
            if (IsCleanupGroup(cur))
            {
                // Cleanup phase reached the end without an explicit outcome line;
                // its steps all ran — mark Passed (no counter change: not a @Test).
                cur.Outcome = LogLevel.Pass;
            }
            else if (_stopRequested)
            {
                cur.Outcome = LogLevel.Skip;
                Skipped++;
            }
            else
            {
                cur.IsStopped = true;
            }
        }
        else if (_currentGroup is not null)
        {
            _currentGroup.IsExpanded = false;
        }

        // Synthesize Skipped entries for every selected test the engine never
        // reached — covers both the cooperative-stop case (parent set stop=true
        // but JVM was killed before emitting skip lines for all remaining tests)
        // and any premature crash. The Extent report side is handled by the Java
        // engine itself (skipTest(...) in each @Test method when stop=true).
        //
        // Insertion position matters: when stop=true the remaining @Test methods
        // call skipTest() *without* printing the "X started" banner, so no real
        // group is created from the log stream for them. The cleanup section
        // ("Deleting created test data") DOES print its banner and therefore
        // appears in Groups before this synthesis runs. We must insert the
        // synthesized skipped groups BEFORE the first cleanup group so the final
        // order is: passed, in-flight skip, remaining skipped..., delete passed.
        if (_stopRequested && selectedTestNames is not null)
        {
            var existing = new System.Collections.Generic.HashSet<string>(
                Groups.Select(g => g.Name),
                StringComparer.OrdinalIgnoreCase);
            // Find the index of the first cleanup group, if any. Synthesized
            // skip entries are inserted just before it; otherwise they append.
            int insertAt = Groups.Count;
            for (int i = 0; i < Groups.Count; i++)
            {
                if (IsCleanupGroup(Groups[i])) { insertAt = i; break; }
            }
            foreach (var name in selectedTestNames)
            {
                if (string.IsNullOrWhiteSpace(name) || existing.Contains(name))
                    continue;
                var g = new TestCaseLogGroup(name, expanded: false) { Outcome = LogLevel.Skip };
                Groups.Insert(insertAt, g);
                insertAt++;
                Skipped++;
                existing.Add(name);
            }
        }
    }

    public void Append(LogEntry entry)
    {
        // 1. Detect a new test-case banner ("X functionality started", "X Creation started", ...).
        if (TryExtractTestCaseName(entry.Message, out var name))
        {
            StartGroup(name);
            return; // The banner itself is redundant once it becomes the section title.
        }

        // 2. Drop anything that arrives before the first real test case starts
        //    (avoids the "Run startup" / framework-banner clutter the user asked to remove).
        if (_currentGroup is null) return;

        // 3. After the user stops, the aborted test's Selenium thread keeps emitting
        //    a few stale step lines ("Click on Create button", etc.) while the
        //    cleanup group ("Delete created test data") is already current. Route
        //    those stragglers back into the stopped group so they don't pollute
        //    the cleanup section. Cleanup-shaped messages stay in the new group.
        var target = _currentGroup;
        if (_stopRequested && _stoppedGroup is not null && !ReferenceEquals(_currentGroup, _stoppedGroup)
            && !LooksLikeCleanupMessage(entry.Message))
        {
            target = _stoppedGroup;
        }
        target.Entries.Add(entry);

        Entries.Add(entry);
        if (Entries.Count > MaxRows) Entries.RemoveAt(0);
    }

    private static bool LooksLikeCleanupMessage(string message)
    {
        if (string.IsNullOrWhiteSpace(message)) return false;
        return message.Contains("folder",   StringComparison.OrdinalIgnoreCase)
            || message.Contains("deletion", StringComparison.OrdinalIgnoreCase)
            || message.Contains("deleting", StringComparison.OrdinalIgnoreCase)
            || message.Contains("cleanup",  StringComparison.OrdinalIgnoreCase)
            || message.Contains("delete",   StringComparison.OrdinalIgnoreCase);
    }

    // The Java engine emits a single post-suite banner — "Deleting created test
    // data started" — that opens a Results Log section for the @AfterSuite
    // cleanup phase. It isn't a @Test, so it has no Pass/Fail/Skip outcome line.
    // Treat it as a passing section when its steps complete (or when the user
    // stops during cleanup), without touching the Passed/Failed/Skipped counters.
    private static bool IsCleanupGroup(TestCaseLogGroup g)
    {
        if (g is null || string.IsNullOrWhiteSpace(g.Name)) return false;
        var n = g.Name;
        return n.Contains("deleting", StringComparison.OrdinalIgnoreCase)
            || n.Contains("deletion", StringComparison.OrdinalIgnoreCase)
            || n.Contains("cleanup",  StringComparison.OrdinalIgnoreCase)
            || n.Contains("test data", StringComparison.OrdinalIgnoreCase);
    }

    public void RecordOutcome(LogLevel outcome)
    {
        // After the user clicks Stop the in-flight test was already finalized as
        // Skipped in OnStopRequested(); the Java engine (TestNG) often emits a
        // late SKIP line for that same test as it tears down. Ignore any outcome
        // for a group that already has one so we never double-count or flip the
        // header.
        if (_currentGroup is { Outcome: not null })
            return;

        switch (outcome)
        {
            case LogLevel.Pass: Passed++; break;
            case LogLevel.Fail: Failed++; break;
            case LogLevel.Skip: Skipped++; break;
        }
        // Tag the current test case with its outcome so the expander header shows it.
        if (_currentGroup is not null) _currentGroup.Outcome = outcome;
        IsIndeterminate = false;
        // Approximate progress against the known catalogue (TestSelectionRules.TotalTestCount).
        ProgressValue = Math.Min(100.0, Total * 100.0 / EDC.Core.Tests.TestSelectionRules.TotalTestCount);
    }

    public void Clear()
    {
        Entries.Clear();
        Groups.Clear();
        _currentGroup = null;
        _stopRequested = false;
        _stoppedGroup = null;
        Passed = Failed = Skipped = 0;
        ProgressValue = 0;
        ActiveLogFilter = LogFilter.All;
        StatusText = "Idle";
        ElapsedText = "00:00";
    }

    private void StartGroup(string name, bool expanded = true)
    {
        // Only the freshly opened group stays expanded — collapse the previous one.
        if (_currentGroup is not null) _currentGroup.IsExpanded = false;
        var group = new TestCaseLogGroup(name, expanded);
        Groups.Add(group);
        _currentGroup = group;
    }

    private static bool TryExtractTestCaseName(string message, out string name)
    {
        name = string.Empty;
        if (string.IsNullOrWhiteSpace(message)) return false;
        var trimmed = message.Trim();
        // Match the "<name> ... started" banners the Java TestSuite emits via setTextGreen.
        var idx = trimmed.IndexOf(" started", StringComparison.OrdinalIgnoreCase);
        if (idx <= 0) return false;
        // Skip lines that already contain a colon — those are progress/status notes, not banners.
        if (trimmed.Contains(':')) return false;
        var head = trimmed[..idx].Trim();
        foreach (var suffix in new[] { " functionality", " Test", " Creation", " test" })
        {
            if (head.EndsWith(suffix, StringComparison.OrdinalIgnoreCase))
                head = head[..^suffix.Length].TrimEnd();
        }
        if (head.Length == 0) return false;
        name = head;
        return true;
    }

    [RelayCommand(CanExecute = nameof(CanClearLog))]
    private void ClearLog() => Clear();

    private bool CanClearLog() => !IsRunning;

    partial void OnIsRunningChanged(bool value) => ClearLogCommand.NotifyCanExecuteChanged();

    [RelayCommand]
    private void FilterAll() => ActiveLogFilter = LogFilter.All;
    [RelayCommand]
    private void FilterPassed() => ActiveLogFilter = LogFilter.Passed;
    [RelayCommand]
    private void FilterFailed() => ActiveLogFilter = LogFilter.Failed;
    [RelayCommand]
    private void FilterSkipped() => ActiveLogFilter = LogFilter.Skipped;

    /// <summary>Build a tab-separated plain-text dump of the supplied entries (or all entries if null).</summary>
    public string FormatEntries(System.Collections.IEnumerable? entries = null)
    {
        var src = entries?.Cast<LogEntry>() ?? Entries;
        var sb = new System.Text.StringBuilder();
        foreach (var e in src)
        {
            sb.Append(e.Timestamp.ToString("HH:mm:ss")).Append('\t')
              .Append(e.Level.ToString().ToUpperInvariant()).Append('\t')
              .AppendLine(e.Message);
        }
        return sb.ToString();
    }

    [RelayCommand]
    private void CopyAll()
    {
        if (Entries.Count == 0) return;
        try { System.Windows.Clipboard.SetText(FormatEntries()); } catch { /* clipboard occasionally locked */ }
    }
}

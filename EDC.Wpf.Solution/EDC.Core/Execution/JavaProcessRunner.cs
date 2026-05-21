using System.Diagnostics;
using System.Text;

namespace EDC.Core.Execution;

/// <summary>
/// Spawns the Java engine (`java -jar engine\edc.jar`) and streams its output.
/// Cooperative stop is not reachable across JVMs, so <see cref="StopAsync"/> kills the tree.
/// </summary>
public sealed class JavaProcessRunner : IDisposable
{
    public sealed record Options(
        string JavaExe,
        string JarPath,
        string WorkingDirectory,
        IReadOnlyList<string>? ExtraArgs = null);

    public event EventHandler<string>? OutputReceived;
    public event EventHandler<string>? ErrorReceived;
    public event EventHandler<int>? Exited;

    private Process? _proc;
    private readonly object _gate = new();

    public bool IsRunning
    {
        get
        {
            lock (_gate) return _proc is { HasExited: false };
        }
    }

    public Task StartAsync(Options opt, CancellationToken ct = default)
    {
        lock (_gate)
        {
            if (_proc is { HasExited: false })
                throw new InvalidOperationException("Java engine already running.");

            if (!File.Exists(opt.JavaExe))
                throw new FileNotFoundException("java.exe not found.", opt.JavaExe);
            if (!File.Exists(opt.JarPath))
                throw new FileNotFoundException("Engine JAR not found.", opt.JarPath);

            // `true` puts the engine into headless/CLI mode (see EDCMainFrame.main).
            // Without it the JAR opens the legacy Swing window because the jar
            // manifest's Main-Class is EDCMainFrame.
            var args = new List<string> { "-jar", opt.JarPath, "true" };
            if (opt.ExtraArgs is { Count: > 0 }) args.AddRange(opt.ExtraArgs);

            var psi = new ProcessStartInfo
            {
                FileName = opt.JavaExe,
                WorkingDirectory = opt.WorkingDirectory,
                UseShellExecute = false,
                CreateNoWindow = true,
                RedirectStandardOutput = true,
                RedirectStandardError = true,
                // Stdin is redirected so the WPF Stop button can send a cooperative
                // "STOP" line into the JVM. The Java side has a watcher thread that
                // reads stdin and calls System.exit(0) on receipt of STOP, which fires
                // the JVM shutdown hook that runs DeleteAllFolder cleanup. Without
                // this, Stop would TerminateProcess the JVM and leak the artifacts
                // created mid-suite.
                RedirectStandardInput = true,
                // Java writes UTF-8 bytes (the EDCTestListener line contains a U+2192 arrow).
                // Without this, .NET decodes as the OEM/ANSI codepage and the arrow turns into
                // mojibake, so LogStreamParser's listener regex never matches and Passed /
                // Failed / Skipped counters stay at 0.
                StandardOutputEncoding = Encoding.UTF8,
                StandardErrorEncoding = Encoding.UTF8,
            };
            // Ask the JVM itself to emit UTF-8 for stdout / stderr regardless of system locale.
            psi.Environment["JAVA_TOOL_OPTIONS"] = "-Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8";
            foreach (var a in args) psi.ArgumentList.Add(a);

            _proc = new Process { StartInfo = psi, EnableRaisingEvents = true };
            _proc.OutputDataReceived += (_, e) => { if (e.Data is not null) OutputReceived?.Invoke(this, e.Data); };
            _proc.ErrorDataReceived += (_, e) => { if (e.Data is not null) ErrorReceived?.Invoke(this, e.Data); };
            _proc.Exited += (_, _) => Exited?.Invoke(this, _proc?.ExitCode ?? -1);

            _proc.Start();
            _proc.BeginOutputReadLine();
            _proc.BeginErrorReadLine();
        }

        ct.Register(() => _ = StopAsync());
        return Task.CompletedTask;
    }

    /// <summary>
    /// Maximum time to wait for the Java engine's shutdown hook (the @AfterSuite
    /// cleanup that deletes folders / items created during the run) before falling
    /// back to a forced TerminateProcess.
    /// </summary>
    public TimeSpan GracefulStopTimeout { get; set; } = TimeSpan.FromSeconds(90);

    public async Task StopAsync()
    {
        Process? local;
        lock (_gate)
        {
            local = _proc;
            _proc = null;
        }
        if (local is null || local.HasExited) return;

        // Cooperative shutdown: ask the JVM to exit cleanly so its shutdown hook
        // runs DeleteAllFolder for whatever artefacts were created mid-suite.
        try
        {
            await local.StandardInput.WriteLineAsync("STOP").ConfigureAwait(false);
            await local.StandardInput.FlushAsync().ConfigureAwait(false);
        }
        catch { /* pipe already closed or process already exiting — fall through to wait */ }

        try
        {
            using var cts = new CancellationTokenSource(GracefulStopTimeout);
            await local.WaitForExitAsync(cts.Token).ConfigureAwait(false);
        }
        catch (OperationCanceledException) { /* timeout — forced kill below */ }
        catch { /* best effort */ }

        if (!local.HasExited)
        {
            try { local.Kill(entireProcessTree: true); } catch { /* best effort */ }
        }
    }

    public void Dispose()
    {
        _ = StopAsync();
        _proc?.Dispose();
    }
}

namespace EDC.Core.Execution;

/// <summary>
/// Tails the Java engine's plain-text log file (the one
/// <c>EDCMainFrame.writeStatus()</c> writes via its <c>printwrite</c> stream).
///
/// Watches a directory for <c>EnterpriseDeploymentCheckLogs_*.txt</c> files;
/// when the engine creates the file it opens it with shared read-access and
/// raises <see cref="LineRead"/> for each new line. Stops cleanly on dispose.
///
/// We tail the log file (not stdout) so the Results Log pane shows only the
/// engine's curated messages — Maven, TestNG and SLF4J noise stay out.
/// </summary>
public sealed class LogFileTailer : IDisposable
{
    public event EventHandler<string>? LineRead;

    private CancellationTokenSource? _cts;
    private Task? _loop;
    private string? _watchDir;
    private DateTime _startedUtc;

    public void Start(string watchDir)
    {
        Stop();
        _watchDir = watchDir;
        // Subtract a small skew so a file created the same instant still qualifies.
        _startedUtc = DateTime.UtcNow.AddSeconds(-2);
        Directory.CreateDirectory(watchDir);
        _cts = new CancellationTokenSource();
        _loop = Task.Run(() => RunAsync(_cts.Token));
    }

    public void Stop()
    {
        try { _cts?.Cancel(); } catch { }
        try { _loop?.Wait(1000); } catch { }
        _cts?.Dispose();
        _cts = null;
        _loop = null;
    }

    public void Dispose() => Stop();

    private async Task RunAsync(CancellationToken ct)
    {
        string? currentFile = null;
        FileStream? fs = null;
        StreamReader? sr = null;
        try
        {
            while (!ct.IsCancellationRequested)
            {
                var latest = FindLatest(_watchDir!, _startedUtc);
                if (latest is not null && !string.Equals(latest, currentFile, StringComparison.OrdinalIgnoreCase))
                {
                    // Engine rolled to a new log file (e.g. another run inside the same session) — reopen.
                    sr?.Dispose();
                    fs?.Dispose();
                    currentFile = latest;
                    try
                    {
                        fs = new FileStream(currentFile, FileMode.Open, FileAccess.Read, FileShare.ReadWrite | FileShare.Delete);
                        sr = new StreamReader(fs);
                    }
                    catch
                    {
                        fs = null;
                        sr = null;
                    }
                }

                if (sr is not null)
                {
                    string? line;
                    while ((line = await sr.ReadLineAsync().ConfigureAwait(false)) is not null)
                    {
                        if (ct.IsCancellationRequested) return;
                        LineRead?.Invoke(this, line);
                    }
                }

                try { await Task.Delay(400, ct).ConfigureAwait(false); }
                catch (OperationCanceledException) { return; }
            }
        }
        finally
        {
            sr?.Dispose();
            fs?.Dispose();
        }
    }

    private static string? FindLatest(string dir, DateTime minCreatedUtc)
    {
        try
        {
            if (!Directory.Exists(dir)) return null;
            var files = Directory.EnumerateFiles(dir, "EnterpriseDeploymentCheckLogs_*.txt");
            string? best = null;
            DateTime bestTime = DateTime.MinValue;
            foreach (var f in files)
            {
                // Only consider files created since the tailer was started — otherwise
                // a stale log from a previous run would be replayed from the top.
                DateTime created;
                try { created = File.GetCreationTimeUtc(f); }
                catch { continue; }
                if (created < minCreatedUtc) continue;

                var t = File.GetLastWriteTimeUtc(f);
                if (t > bestTime) { bestTime = t; best = f; }
            }
            return best;
        }
        catch
        {
            return null;
        }
    }
}

namespace EDC.Core.Execution;

/// <summary>
/// Resolves the path to `java.exe`:
///   1. bundled `runtime\bin\java.exe` next to the WPF exe (preferred — zip-ship layout)
///   2. JAVA_HOME environment variable
///   3. system PATH
/// </summary>
public static class JreLocator
{
    public static string? Locate(string? appBaseDir = null)
    {
        appBaseDir ??= AppContext.BaseDirectory;

        var bundled = Path.Combine(appBaseDir, "runtime", "bin", "java.exe");
        if (File.Exists(bundled)) return bundled;

        var javaHome = Environment.GetEnvironmentVariable("JAVA_HOME");
        if (!string.IsNullOrWhiteSpace(javaHome))
        {
            var fromHome = Path.Combine(javaHome, "bin", "java.exe");
            if (File.Exists(fromHome)) return fromHome;
        }

        var path = Environment.GetEnvironmentVariable("PATH");
        if (!string.IsNullOrWhiteSpace(path))
        {
            foreach (var dir in path.Split(Path.PathSeparator, StringSplitOptions.RemoveEmptyEntries))
            {
                try
                {
                    var candidate = Path.Combine(dir, "java.exe");
                    if (File.Exists(candidate)) return candidate;
                }
                catch { /* ignore malformed PATH entries */ }
            }
        }

        return null;
    }
}

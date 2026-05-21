using System.Text.RegularExpressions;

namespace EDC.Core.Logging;

/// <summary>
/// Converts raw stdout/stderr lines from the Java engine into typed <see cref="LogEntry"/>.
/// Recognises:
///   • `INFO:` / `STEP:` / `PASS:` / `FAIL:` / `WARN:` prefixes
///   • `[EDCTestListener] Updating result for test: X → PASSED|FAILED|SKIPPED`
///   • Selenium / TestNG noise (down-graded to Info)
/// </summary>
public sealed partial class LogStreamParser
{
    public sealed record Result(LogEntry Entry, TestResultUpdate? ResultUpdate, bool Drop = false);

    public sealed record TestResultUpdate(string TestName, LogLevel Outcome);

    [GeneratedRegex(@"\[EDCTestListener\]\s+Updating result for test:\s+(?<name>.+?)\s+(?:→|->)\s+(?<status>PASSED|FAILED|SKIPPED)", RegexOptions.IgnoreCase)]
    private static partial Regex ListenerRegex();

    // Java exception / stack-trace detection — matches lines that *look* like
    // exception output anywhere in the line (so a "FAIL: java.lang.X..." line
    // produced by setTextRed(exp.getLocalizedMessage()) is also caught).
    [GeneratedRegex(@"(?:^|\s)(?:Exception in thread\b|Caused by:\s|Suppressed:\s|\bat\s+[\w$.<>]+\([^)]*\)|\.{3}\s*\d+\s+more\b|(?:[a-z][\w$]*\.){2,}[A-Z][\w$]*(?:Exception|Error|Throwable)\b|\b(?:Build info|Session info|Driver info|System info):\s)")]
    private static partial Regex JavaExceptionRegex();

    // Lines we never want in the user-facing Results Log (mirrors the Swing app's filter).
    private static readonly string[] NoisePrefixes =
    {
        "[INFO]", "[WARNING]", "[DEBUG]",
        "[surefire]", "[testng]",
        "Downloading from", "Downloaded from", "Progress ",
        "Picked up JAVA_TOOL_OPTIONS", "Picked up _JAVA_OPTIONS",
        "SLF4J:", "WARNING: An illegal reflective",
        "--- maven-", "--- surefire-",
        "T E S T S",
        "BUILD SUCCESS", "BUILD FAILURE",
        "Apache Maven",
        "Scanning for projects",
        "Building Enterprise"
    };

    private static readonly string[] NoiseContains =
    {
        "org.apache.maven",
        "maven-surefire-plugin",
        "Tests run: 0",
        "INFO: Detected dialect"
    };

    private static bool IsNoise(string raw)
    {
        var t = raw.TrimStart();
        if (t.Length == 0) return true;
        for (int i = 0; i < NoisePrefixes.Length; i++)
            if (t.StartsWith(NoisePrefixes[i], StringComparison.OrdinalIgnoreCase)) return true;
        for (int i = 0; i < NoiseContains.Length; i++)
            if (t.Contains(NoiseContains[i], StringComparison.OrdinalIgnoreCase)) return true;
        if (JavaExceptionRegex().IsMatch(raw)) return true;
        return false;
    }

    public Result Parse(string raw)
    {
        if (string.IsNullOrWhiteSpace(raw))
            return new Result(new LogEntry(DateTime.Now, LogLevel.Info, ""), null, Drop: true);

        if (IsNoise(raw))
            return new Result(new LogEntry(DateTime.Now, LogLevel.Info, raw), null, Drop: true);

        TestResultUpdate? update = null;
        var m = ListenerRegex().Match(raw);
        if (m.Success)
        {
            var outcome = m.Groups["status"].Value.ToUpperInvariant() switch
            {
                "PASSED" => LogLevel.Pass,
                "FAILED" => LogLevel.Fail,
                "SKIPPED" => LogLevel.Skip,
                _ => LogLevel.Info
            };
            update = new TestResultUpdate(m.Groups["name"].Value.Trim(), outcome);
        }

        var level = DetectLevel(raw);
        var display = StripLevelPrefix(raw, level);
        return new Result(new LogEntry(DateTime.Now, level, display), update);
    }

    private static LogLevel DetectLevel(string raw)
    {
        var trimmed = raw.TrimStart();
        if (trimmed.StartsWith("PASS", StringComparison.OrdinalIgnoreCase)) return LogLevel.Pass;
        if (trimmed.StartsWith("FAIL", StringComparison.OrdinalIgnoreCase)) return LogLevel.Fail;
        if (trimmed.StartsWith("SKIP", StringComparison.OrdinalIgnoreCase)) return LogLevel.Skip;
        if (trimmed.StartsWith("WARN", StringComparison.OrdinalIgnoreCase)) return LogLevel.Warn;
        if (trimmed.StartsWith("ERROR", StringComparison.OrdinalIgnoreCase)) return LogLevel.Error;
        if (trimmed.StartsWith("STEP", StringComparison.OrdinalIgnoreCase)) return LogLevel.Step;
        if (trimmed.Contains("===") || trimmed.Contains("---")) return LogLevel.Heading;
        return LogLevel.Info;
    }

    // Strip the leading "PASS: " / "FAIL: " / "WARN: " / "SKIP: " marker so the
    // UI shows clean text (the icon + color already convey the level).
    private static string StripLevelPrefix(string raw, LogLevel level)
    {
        string? marker = level switch
        {
            LogLevel.Pass  => "PASS:",
            LogLevel.Fail  => "FAIL:",
            LogLevel.Skip  => "SKIP:",
            LogLevel.Warn  => "WARN:",
            LogLevel.Error => "ERROR:",
            LogLevel.Step  => "STEP:",
            _ => null,
        };
        if (marker is null) return raw;

        var trimmed = raw.TrimStart();
        if (!trimmed.StartsWith(marker, StringComparison.OrdinalIgnoreCase)) return raw;
        var rest = trimmed.Substring(marker.Length).TrimStart();
        return rest.Length == 0 ? raw : rest;
    }
}

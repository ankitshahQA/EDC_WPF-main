namespace EDC.Core.Reports;

/// <summary>
/// Finds the most recent EnterpriseDeploymentCheckResults\&lt;date&gt;\Logs file or HTML report.
/// </summary>
public static class ReportLocator
{
    public static string ResultsRoot(string baseDir) =>
        Path.Combine(baseDir, "EnterpriseDeploymentCheckResults");

    public static string? FindLatestLog(string baseDir)
    {
        var root = ResultsRoot(baseDir);
        if (!Directory.Exists(root)) return null;

        return Directory.EnumerateFiles(root, "*.txt", SearchOption.AllDirectories)
            .OrderByDescending(File.GetLastWriteTimeUtc)
            .FirstOrDefault();
    }

    public static string? FindLatestHtmlReport(string baseDir)
    {
        var root = ResultsRoot(baseDir);
        if (!Directory.Exists(root)) return null;

        return Directory.EnumerateFiles(root, "*.html", SearchOption.AllDirectories)
            .OrderByDescending(File.GetLastWriteTimeUtc)
            .FirstOrDefault();
    }
}

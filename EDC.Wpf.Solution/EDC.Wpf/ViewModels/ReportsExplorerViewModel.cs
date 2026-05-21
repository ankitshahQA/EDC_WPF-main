using System.Collections.ObjectModel;
using System.Diagnostics;
using System.IO;
using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;
using EDC.Core.Reports;

namespace EDC.Wpf.ViewModels;

public partial class ReportFileItem : ObservableObject
{
    [ObservableProperty] private string name = string.Empty;
    [ObservableProperty] private string folder = string.Empty;
    [ObservableProperty] private string fullPath = string.Empty;
    [ObservableProperty] private DateTime modified;
    [ObservableProperty] private long sizeBytes;
    [ObservableProperty] private string kind = string.Empty;

    public string SizeDisplay => SizeBytes switch
    {
        < 1024            => $"{SizeBytes} B",
        < 1024L * 1024     => $"{SizeBytes / 1024.0:F1} KB",
        < 1024L * 1024 * 1024 => $"{SizeBytes / 1024.0 / 1024.0:F1} MB",
        _                  => $"{SizeBytes / 1024.0 / 1024.0 / 1024.0:F2} GB"
    };
}

public partial class ReportsExplorerViewModel : ObservableObject
{
    private readonly string _baseDir;

    public ObservableCollection<ReportFileItem> Items { get; } = new();

    [ObservableProperty] private string status = string.Empty;
    [ObservableProperty] private ReportFileItem? selected;

    public string RootPath => ReportLocator.ResultsRoot(_baseDir);

    public ReportsExplorerViewModel(string baseDir)
    {
        _baseDir = baseDir;
        Refresh();
    }

    [RelayCommand]
    public void Refresh()
    {
        Items.Clear();
        var root = RootPath;
        if (!Directory.Exists(root))
        {
            Status = "No reports yet. Run a test to generate one.";
            return;
        }

        var files = new DirectoryInfo(root)
            .EnumerateFiles("*.*", SearchOption.AllDirectories)
            .Where(f => f.Extension.Equals(".html", StringComparison.OrdinalIgnoreCase)
                     || f.Extension.Equals(".htm",  StringComparison.OrdinalIgnoreCase))
            .OrderByDescending(f => f.LastWriteTimeUtc)
            .Take(500);

        int count = 0;
        foreach (var f in files)
        {
            var rel = Path.GetRelativePath(root, f.DirectoryName ?? root);
            Items.Add(new ReportFileItem
            {
                Name = f.Name,
                Folder = rel == "." ? "" : rel,
                FullPath = f.FullName,
                Modified = f.LastWriteTime,
                SizeBytes = f.Length,
                Kind = "HTML report"
            });
            count++;
        }
        Status = count == 0
            ? $"No HTML reports found in {root}."
            : $"{count} report(s) — newest first.";
    }

    [RelayCommand]
    public void OpenItem(ReportFileItem? item)
    {
        var target = item ?? Selected;
        if (target is null || !File.Exists(target.FullPath)) return;
        try
        {
            Process.Start(new ProcessStartInfo(target.FullPath) { UseShellExecute = true });
        }
        catch { /* shell open failure surfaces in Explorer fallback */ }
    }

    [RelayCommand]
    public void RevealInExplorer(ReportFileItem? item)
    {
        var target = item ?? Selected;
        if (target is null || !File.Exists(target.FullPath)) return;
        try
        {
            Process.Start(new ProcessStartInfo("explorer.exe", $"/select,\"{target.FullPath}\"") { UseShellExecute = true });
        }
        catch { }
    }

    [RelayCommand]
    public void OpenRoot()
    {
        var root = RootPath;
        Directory.CreateDirectory(root);
        Process.Start(new ProcessStartInfo(root) { UseShellExecute = true });
    }
}

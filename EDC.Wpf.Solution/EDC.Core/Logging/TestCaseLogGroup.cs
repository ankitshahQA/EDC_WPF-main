using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Runtime.CompilerServices;

namespace EDC.Core.Logging;

/// <summary>
/// A single test-case section in the Results Log. Holds the log lines emitted
/// between the "X started" banner and the listener's PASS / FAIL / SKIP update.
/// </summary>
public sealed class TestCaseLogGroup : INotifyPropertyChanged
{
    public string Name { get; }
    public DateTime StartedAt { get; } = DateTime.Now;
    public ObservableCollection<LogEntry> Entries { get; } = new();

    private LogLevel? _outcome;
    public LogLevel? Outcome
    {
        get => _outcome;
        set
        {
            if (_outcome != value)
            {
                _outcome = value;
                // Snapshot the runtime the moment an outcome arrives so the row's duration
                // stops climbing once the test finishes.
                if (value is not null && _finishedAt is null) _finishedAt = DateTime.Now;
                Raise();
                Raise(nameof(OutcomeText));
                Raise(nameof(OutcomeGlyph));
                Raise(nameof(DurationText));
            }
        }
    }

    private bool _isStopped;
    /// <summary>
    /// Set when the run was terminated by the user before this test produced a
    /// PASS / FAIL / SKIP outcome. Overrides <see cref="OutcomeText"/> /
    /// <see cref="OutcomeGlyph"/> so the header reads "Stopped" instead of the
    /// in-progress "Running…" placeholder. Distinct from Skip so it doesn't get
    /// rolled into the Skipped counter.
    /// </summary>
    public bool IsStopped
    {
        get => _isStopped;
        set
        {
            if (_isStopped != value)
            {
                _isStopped = value;
                if (value && _finishedAt is null) _finishedAt = DateTime.Now;
                Raise();
                Raise(nameof(OutcomeText));
                Raise(nameof(OutcomeGlyph));
                Raise(nameof(DurationText));
            }
        }
    }

    private DateTime? _finishedAt;
    /// <summary>Compact mm:ss / s display of the test-case runtime, used by the modern Results Log row.</summary>
    public string DurationText
    {
        get
        {
            var end = _finishedAt ?? DateTime.Now;
            var d = end - StartedAt;
            if (d.TotalSeconds < 1) return "<1s";
            if (d.TotalMinutes < 1) return $"{(int)d.TotalSeconds}s";
            return $"{(int)d.TotalMinutes}m {d.Seconds:00}s";
        }
    }

    public string OutcomeText => _isStopped ? "Stopped" : _outcome switch
    {
        LogLevel.Pass => "Passed",
        LogLevel.Fail => "Failed",
        LogLevel.Skip => "Skipped",
        _ => "Running…"
    };

    public string OutcomeGlyph => _isStopped ? "■" : _outcome switch
    {
        LogLevel.Pass => "✔",
        LogLevel.Fail => "✘",
        LogLevel.Skip => "↷",
        _ => "•"
    };

    private bool _isExpanded;
    public bool IsExpanded
    {
        get => _isExpanded;
        set { if (_isExpanded != value) { _isExpanded = value; Raise(); } }
    }

    public TestCaseLogGroup(string name, bool expanded = false)
    {
        Name = name;
        _isExpanded = expanded;
    }

    public event PropertyChangedEventHandler? PropertyChanged;
    private void Raise([CallerMemberName] string? name = null)
        => PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(name));
}

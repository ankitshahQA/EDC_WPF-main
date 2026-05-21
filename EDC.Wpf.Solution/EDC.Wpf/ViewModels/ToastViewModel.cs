using System.Collections.ObjectModel;
using System.Windows.Threading;
using CommunityToolkit.Mvvm.ComponentModel;
using CommunityToolkit.Mvvm.Input;

namespace EDC.Wpf.ViewModels;

public enum ToastSeverity { Info, Success, Warning, Error }

public partial class ToastItem : ObservableObject
{
    [ObservableProperty] private string title = string.Empty;
    [ObservableProperty] private string message = string.Empty;
    [ObservableProperty] private ToastSeverity severity = ToastSeverity.Info;

    public string Glyph => Severity switch
    {
        ToastSeverity.Success => "\uE73E",   // CheckMark
        ToastSeverity.Warning => "\uE7BA",   // Warning
        ToastSeverity.Error   => "\uEA39",   // ErrorBadge
        _                     => "\uE946",   // Info
    };
}

public partial class ToastHostViewModel : ObservableObject
{
    public ObservableCollection<ToastItem> Toasts { get; } = new();

    [RelayCommand]
    private void Dismiss(ToastItem? toast)
    {
        if (toast is null) return;
        Toasts.Remove(toast);
    }

    public void Show(string title, string message, ToastSeverity severity = ToastSeverity.Info, int autoDismissMs = 4500)
    {
        var toast = new ToastItem { Title = title, Message = message, Severity = severity };
        var dispatcher = System.Windows.Application.Current?.Dispatcher;
        if (dispatcher is null) return;
        dispatcher.BeginInvoke(() =>
        {
            Toasts.Insert(0, toast);
            if (autoDismissMs > 0)
            {
                var timer = new DispatcherTimer { Interval = System.TimeSpan.FromMilliseconds(autoDismissMs) };
                timer.Tick += (_, _) => { timer.Stop(); Toasts.Remove(toast); };
                timer.Start();
            }
        });
    }
}

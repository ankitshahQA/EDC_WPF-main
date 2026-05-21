using System.Windows;
using System.Windows.Controls;
using EDC.Wpf.ViewModels;

namespace EDC.Wpf.Views;

public partial class ResultsLogPane : UserControl
{
    public ResultsLogPane() { InitializeComponent(); }

    private ResultsLogViewModel? Vm => (DataContext as MainViewModel)?.ResultsLog;

    private void CopyAll_OnClick(object sender, RoutedEventArgs e) => Vm?.CopyAllCommand.Execute(null);
}

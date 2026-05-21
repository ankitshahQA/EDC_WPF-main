using System.ComponentModel;
using System.Windows;
using Fluent;

namespace EDC.Wpf.Views;

public partial class MainWindow : RibbonWindow
{
    public MainWindow()
    {
        InitializeComponent();
    }

    protected override void OnClosing(CancelEventArgs e)
    {
        var result = MessageBox.Show(this,
            "Are you sure you want to exit Enterprise Deployment Check?",
            "Confirm Exit",
            MessageBoxButton.YesNo,
            MessageBoxImage.Question);
        if (result != MessageBoxResult.Yes) e.Cancel = true;
        base.OnClosing(e);
    }

    private void ExitButton_OnClick(object sender, RoutedEventArgs e) => Close();

    private void ReportItem_OnDoubleClick(object sender, System.Windows.Input.MouseButtonEventArgs e)
    {
        if (sender is System.Windows.Controls.ListViewItem { DataContext: ViewModels.ReportFileItem item }
            && DataContext is ViewModels.MainViewModel vm)
        {
            vm.Reports.OpenItemCommand.Execute(item);
        }
    }

    private void AboutButton_OnClick(object sender, RoutedEventArgs e)
    {
        MessageBox.Show(this,
            "Enterprise Deployment Check\r\n" +
            "Version: 2.0\r\n" +
            "Run confidence tests against ARCGIS Enterprise\r\n" +
            "Contact Information: mnelson@esri.com\r\n" +
            "Copyright \u00A92026 Esri Inc. All rights reserved.",
            "About",
            MessageBoxButton.OK,
            MessageBoxImage.Information);
    }

}

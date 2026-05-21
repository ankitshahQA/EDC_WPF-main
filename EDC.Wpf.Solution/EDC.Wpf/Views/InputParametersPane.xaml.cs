using System.ComponentModel;
using System.Windows;
using System.Windows.Controls;
using EDC.Core.Models;
using EDC.Wpf.ViewModels;

namespace EDC.Wpf.Views;

public partial class InputParametersPane : UserControl
{
    private MainViewModel? _vm;

    public InputParametersPane()
    {
        InitializeComponent();
        DataContextChanged += (_, _) =>
        {
            if (_vm is not null) _vm.Config.PropertyChanged -= OnConfigPropertyChanged;
            _vm = DataContext as MainViewModel;
            if (_vm is not null)
            {
                PortalPasswordBox.Password = _vm.Config.PortalPassword;
                _vm.Config.PropertyChanged += OnConfigPropertyChanged;
            }
        };

        // Keep the masked PasswordBox in sync when the user types into the revealed TextBox.
        RevealPortalPasswordToggle.Unchecked += (_, _) =>
        {
            if (_vm is not null) PortalPasswordBox.Password = _vm.Config.PortalPassword;
        };
    }

    private void OnConfigPropertyChanged(object? sender, PropertyChangedEventArgs e)
    {
        if (e.PropertyName == nameof(_vm.Config.PortalPassword) && _vm is not null
            && PortalPasswordBox.Password != _vm.Config.PortalPassword
            && RevealPortalPasswordToggle.IsChecked != true)
        {
            PortalPasswordBox.Password = _vm.Config.PortalPassword;
        }
    }

    private void PortalPasswordBox_OnPasswordChanged(object sender, RoutedEventArgs e)
    {
        if (_vm is not null)
            _vm.Config.PortalPassword = PortalPasswordBox.Password;
    }

    // ── Per-server-row password reveal ──────────────────────────────────────
    private void ServerPasswordBox_OnLoaded(object sender, RoutedEventArgs e)
    {
        if (sender is PasswordBox pb && pb.DataContext is ServerSlot slot && pb.Password != slot.AdminPassword)
            pb.Password = slot.AdminPassword;
    }

    private void ServerPasswordBox_OnPasswordChanged(object sender, RoutedEventArgs e)
    {
        if (sender is PasswordBox pb && pb.DataContext is ServerSlot slot && slot.AdminPassword != pb.Password)
            slot.AdminPassword = pb.Password;
    }
}

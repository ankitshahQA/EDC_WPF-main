using System;
using System.Globalization;
using System.Windows.Data;

namespace EDC.Wpf.Converters;

/// <summary>
/// Binds a RadioButton's IsChecked to a string property: ConverterParameter is the value
/// this radio represents ("Chrome" or "Edge").
/// </summary>
public sealed class BrowserToBoolConverter : IValueConverter
{
    public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
    {
        var current = value as string ?? "";
        var match = parameter as string ?? "";
        return string.Equals(current, match, StringComparison.OrdinalIgnoreCase);
    }

    public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
    {
        bool checkedNow = value is bool b && b;
        return checkedNow ? (parameter as string ?? "Chrome") : Binding.DoNothing;
    }
}

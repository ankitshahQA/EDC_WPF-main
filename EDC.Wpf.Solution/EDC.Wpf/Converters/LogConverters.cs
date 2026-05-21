using System;
using System.Globalization;
using System.Windows;
using System.Windows.Data;
using System.Windows.Media;
using EDC.Core.Logging;

namespace EDC.Wpf.Converters;

/// <summary>
/// Maps a <see cref="LogLevel"/> to a Calcite icon <see cref="Geometry"/>.
/// Used by the Results Log to render small SVG-style status glyphs (pass / fail / skip)
/// instead of Unicode symbols.
/// </summary>
public sealed class LogLevelToCalciteGeometryConverter : IValueConverter
{
    public object? Convert(object value, Type targetType, object parameter, CultureInfo culture)
    {
        var key = value switch
        {
            LogLevel.Pass => "Calcite.Geom.Check",
            LogLevel.Fail or LogLevel.Error => "Calcite.Geom.X",
            LogLevel.Skip or LogLevel.Warn => "Calcite.Geom.Reset",
            LogLevel.Heading => "Calcite.Geom.Play",
            _ => "Calcite.Geom.Play",
        };
        return Application.Current?.Resources[key] as Geometry;
    }

    public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        => throw new NotSupportedException();
}

/// <summary>
/// Maps a <see cref="LogLevel"/> to a foreground brush for the Results Log rows.
/// </summary>
public sealed class LogLevelToBrushConverter : IValueConverter
{
    public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
    {
        if (value is not LogLevel lvl) return Brushes.Gainsboro;
        return lvl switch
        {
            LogLevel.Pass => Application.Current.Resources["SuccessBrush"] ?? Brushes.LimeGreen,
            LogLevel.Fail or LogLevel.Error => Application.Current.Resources["DangerBrush"] ?? Brushes.OrangeRed,
            LogLevel.Skip or LogLevel.Warn => Application.Current.Resources["WarningBrush"] ?? Brushes.Gold,
            LogLevel.Heading => Application.Current.Resources["BrandBlueBrush"] ?? Brushes.DeepSkyBlue,
            LogLevel.Step => Application.Current.Resources["TextSecondaryBrush"] ?? Brushes.LightGray,
            _ => Application.Current.Resources["TextPrimaryBrush"] ?? Brushes.WhiteSmoke
        };
    }

    public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        => throw new NotSupportedException();
}

public sealed class LogLevelToGlyphConverter : IValueConverter
{
    public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
    {
        if (value is not LogLevel lvl) return "•";
        return lvl switch
        {
            LogLevel.Pass => "✔",
            LogLevel.Fail or LogLevel.Error => "✘",
            LogLevel.Skip => "↷",
            LogLevel.Warn => "⚠",
            LogLevel.Heading => "▸",
            LogLevel.Step => "›",
            _ => "•"
        };
    }

    public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        => throw new NotSupportedException();
}

public sealed class BoolToVisibilityConverter : IValueConverter
{
    public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
    {
        bool b = value is bool x && x;
        if (parameter is string s && s.Equals("invert", StringComparison.OrdinalIgnoreCase)) b = !b;
        return b ? Visibility.Visible : Visibility.Collapsed;
    }
    public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        => throw new NotSupportedException();
}

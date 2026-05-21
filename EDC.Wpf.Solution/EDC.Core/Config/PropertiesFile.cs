using System.Globalization;
using System.Text;

namespace EDC.Core.Config;

/// <summary>
/// Minimal reader/writer for java.util.Properties text format.
/// Supports `key=value`, `#`/`!` comment lines, blank lines.
/// Preserves insertion/file order so round-trips don't reshuffle keys.
/// </summary>
public sealed class PropertiesFile
{
    private readonly List<string> _orderedKeys = new();
    private readonly Dictionary<string, string> _values = new(StringComparer.Ordinal);

    public IReadOnlyList<string> Keys => _orderedKeys;

    public string? Get(string key) => _values.TryGetValue(key, out var v) ? v : null;

    public string GetOrDefault(string key, string fallback) => Get(key) ?? fallback;

    public void Set(string key, string value)
    {
        if (!_values.ContainsKey(key))
            _orderedKeys.Add(key);
        _values[key] = value ?? string.Empty;
    }

    public static PropertiesFile Load(string path)
    {
        var p = new PropertiesFile();
        if (!File.Exists(path)) return p;

        foreach (var raw in File.ReadAllLines(path, Encoding.UTF8))
        {
            var line = raw.TrimStart();
            if (line.Length == 0) continue;
            if (line[0] == '#' || line[0] == '!') continue;

            int eq = line.IndexOf('=');
            if (eq < 0) eq = line.IndexOf(':');
            if (eq < 0) continue;

            var key = line[..eq].Trim();
            var value = line[(eq + 1)..].Trim();
            p.Set(key, value);
        }
        return p;
    }

    /// <summary>
    /// Writes the file in the same style as Java's Properties.store:
    /// `#EDC Configuration` header, sorted keys, `key=value`.
    /// (Java's stable-sorted variant — we sort to match what the legacy app produced.)
    /// </summary>
    public void Save(string path, string headerComment = "EDC Configuration")
    {
        var dir = Path.GetDirectoryName(path);
        if (!string.IsNullOrEmpty(dir)) Directory.CreateDirectory(dir);

        using var w = new StreamWriter(path, false, new UTF8Encoding(false));
        w.WriteLine($"#{headerComment}");
        w.WriteLine($"#{DateTime.Now.ToString("ddd MMM dd HH:mm:ss zzz yyyy", CultureInfo.InvariantCulture)}");

        foreach (var key in _orderedKeys.OrderBy(k => k, StringComparer.Ordinal))
            w.WriteLine($"{key}={_values[key]}");
    }
}

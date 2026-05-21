using EDC.Core.Logging;

namespace EDC.Wpf.Tests;

public class LogStreamParserTests
{
    private readonly LogStreamParser _p = new();

    [Theory]
    [InlineData("[EDCTestListener] Updating result for test: Login_To_Portal → PASSED", "Login_To_Portal", LogLevel.Pass)]
    [InlineData("[EDCTestListener] Updating result for test: Create_Map -> FAILED", "Create_Map", LogLevel.Fail)]
    [InlineData("[EDCTestListener] Updating result for test: Dashboard → SKIPPED", "Dashboard", LogLevel.Skip)]
    public void Parses_listener_lines(string raw, string expectedName, LogLevel expectedOutcome)
    {
        var r = _p.Parse(raw);
        Assert.NotNull(r.ResultUpdate);
        Assert.Equal(expectedName, r.ResultUpdate!.TestName);
        Assert.Equal(expectedOutcome, r.ResultUpdate.Outcome);
    }

    [Theory]
    [InlineData("INFO: Starting test", LogLevel.Info)]
    [InlineData("FAIL: assertion broke", LogLevel.Fail)]
    [InlineData("PASS: ok", LogLevel.Pass)]
    [InlineData("WARN: slow response", LogLevel.Warn)]
    [InlineData("STEP: click button", LogLevel.Step)]
    [InlineData("=== Section ===", LogLevel.Heading)]
    public void Detects_level_from_prefix(string raw, LogLevel expected)
    {
        var r = _p.Parse(raw);
        Assert.Equal(expected, r.Entry.Level);
        Assert.Null(r.ResultUpdate);
    }

    [Fact]
    public void Empty_input_returns_info_entry()
    {
        var r = _p.Parse("   ");
        Assert.Equal(LogLevel.Info, r.Entry.Level);
        Assert.Null(r.ResultUpdate);
    }
}

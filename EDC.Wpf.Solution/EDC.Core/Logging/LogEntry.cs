namespace EDC.Core.Logging;

public enum LogLevel { Info, Step, Pass, Fail, Skip, Warn, Error, Heading }

public sealed record LogEntry(DateTime Timestamp, LogLevel Level, string Message)
{
    public static LogEntry Info(string msg) => new(DateTime.Now, LogLevel.Info, msg);
    public static LogEntry Error(string msg) => new(DateTime.Now, LogLevel.Error, msg);
}

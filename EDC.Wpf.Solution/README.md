# Enterprise Deployment Check — WPF Edition

ArcGIS Pro–styled WPF front-end for the EDC test suite. The Java/Selenium/TestNG
engine from the original project (`Enterprise Deployment Check/`) is unchanged
and runs as a child process; the .NET UI simply shells out to it and parses
the stdout stream into a live Results Log.

> .NET 8 · WPF · Fluent.Ribbon 11 · AvalonDock 4.74 · CommunityToolkit.Mvvm · Serilog

---

## Solution layout

```
EDC.Wpf.Solution/
├── EDC.sln
├── EDC.Core/                      Pure .NET 8 library — no UI dependencies
│   ├── Config/                    Properties-file reader, EdcConfigStore
│   ├── Execution/                 JavaProcessRunner, JreLocator
│   ├── Logging/                   LogEntry, LogStreamParser, TestCaseLogGroup
│   ├── Models/                    EdcConfig, ServerSlot (ObservableObject)
│   ├── Reports/                   ReportLocator
│   ├── Tests/                     TestSelectionRules (cascade logic)
│   └── Theming/                   AppTheme enum
│
├── EDC.Wpf/                       WPF host (net8.0-windows)
│   ├── App.xaml                   Merges Fluent variant + ArcProPalette + Dark/Light
│   ├── Themes/
│   │   ├── ArcProPalette.xaml     Calcite tokens, font-size scale, control styles
│   │   ├── Dark.xaml              Dark surfaces, text brushes, caption-button brushes
│   │   └── Light.xaml             Light surfaces, text brushes, caption-button brushes
│   ├── ViewModels/                MainViewModel, ResultsLogViewModel, TestSelectionViewModel
│   ├── Views/
│   │   ├── MainWindow.xaml        RibbonWindow + Backstage + 3-pane workspace
│   │   ├── InputParametersPane.xaml
│   │   ├── TestsToRunPane.xaml
│   │   └── ResultsLogPane.xaml
│   ├── Converters/                LogLevelToGlyph/Brush, BoolToVisibility, …
│   └── app.manifest               requireAdministrator (mirrors the legacy edc.manifest)
│
└── EDC.Wpf.Tests/                 xUnit tests for EDC.Core (config parsing,
                                   log parsing, test-selection cascade, etc.)
```

---

## Build, test, run

```pwsh
cd EDC.Wpf.Solution
dotnet build EDC.sln -c Debug
dotnet test  EDC.Wpf.Tests
dotnet run   --project EDC.Wpf
```

`EDC.Core` has zero UI dependencies and is fully unit-tested. The Java engine
is treated as an opaque executable — its source code is untouched.

---

## Visual language

The shell follows ArcGIS Pro 3.x conventions and supports a runtime
**Dark / Light** theme toggle (View tab → Theme).

### Title bar & caption buttons

- `fluent:RibbonWindow` with `TitleBarHeight="32"` and the Esri logo as the
  window icon.
- Standard Windows-style **Min / Max / Close** caption buttons:
  - Transparent background, theme-aware glyph color.
  - Subtle alpha-overlay hover on Min/Max (white on Dark, black on Light).
  - Windows red (`#E81123`) hover on Close with white glyph on both themes.
- Caption-button brushes live in `Themes/Dark.xaml` and `Themes/Light.xaml`
  so they re-resolve correctly on theme swap.

### Ribbon (Fluent.Ribbon)

- **Backstage "Project"** menu: *New*, *Open* (reload from disk), *Save*,
  *Reports*, *About*.
- Tabs: **Home** (Execution / Configuration / Reports groups),
  **Tests**, **View** (theme toggle, pane visibility), **Help**.

### Workspace layout

The center area is a 50/50 split (`*` / `6px` GridSplitter / `*`,
`MinWidth=380` on each side):

| Left column                       | Right column (AvalonDock)         |
|-----------------------------------|-----------------------------------|
| **Input Parameters** + **Tests to Run** as tabbed anchorables | **Results Log** |

All panes are draggable / floatable via AvalonDock.

### Results Log

- Pill-style summary counters at the top — **Total / ✔ Passed / ✘ Failed
  / ↷ Skipped** + elapsed timer + *Copy log* button.
- Per-test-case **collapsible Expander groups** (`TestCaseLogGroup`) with
  outcome glyph + outcome text in the header.
- Each row: 3-column grid (timestamp · glyph · message) using Consolas /
  Segoe UI Symbol.
- Foreground colors are **theme-aware via `DynamicResource` + `DataTrigger`
  on `Level`** so log lines remain readable after switching themes:
  - Pass → `StatusPassTextBrush`
  - Fail / Error → `StatusFailTextBrush`
  - Skip / Warn → `StatusSkipTextBrush`
  - Heading → `BrandBlueBrush`
  - Step → `TextSecondaryBrush`
  - default (Info) → `TextPrimaryBrush`
- Right-click context menu: *Copy all* / *Clear log*.

### Section headers & typography

Section headers (`SectionHeaderText`) use `FontSize.Body` (13) + `Medium`
weight + `TextPrimaryBrush` for a calmer, less "ribbon-shouty" look. Font
sizes come from `ArcProPalette.xaml`:

```
FontSize.Body      = 13
FontSize.SubTitle  = 15
FontSize.Title     = 18
```

### Theming internals

`MainViewModel.SetTheme(bool dark)` (in `ViewModels/MainViewModel.cs`) does
**two** dictionary swaps inside `Application.Current.Resources.MergedDictionaries`:

1. Replaces the **Fluent.Ribbon theme variant** (Office2013 Dark ↔ Light).
2. Replaces our own **surface palette** (`Themes/Dark.xaml` ↔
   `Themes/Light.xaml`).

> ⚠ Anything declared at the top level of `App.xaml` (outside the merged
> dictionaries) does **not** re-resolve on theme swap. Always put
> theme-sensitive brushes inside the per-theme dictionaries, and consume
> them via `{DynamicResource …}` — never `{StaticResource}` and never
> hard-coded `#RRGGBB` in view code.

---

## Architecture (UI ↔ engine)

```
        ┌────────────────── EDC.Wpf (WPF UI) ──────────────────┐
        │                                                       │
        │  MainViewModel ── Config (EdcConfig) ─┐               │
        │       │                                ├─ writes ──►  │ Input\config.properties
        │       │                                │               │
        │       ├─ RunTestsCommand               │               │
        │       │      │                                         │
        │       │      ▼                                         │
        │       │  JavaProcessRunner ─ spawns ──► runtime\bin\java.exe -jar engine\edc.jar
        │       │      │                                         │
        │       │      └─ stdout/stderr stream ─►  LogStreamParser
        │       │                                       │        │
        │       ▼                                       ▼        │
        │  ResultsLogViewModel  ◄────── LogEntry rows + result counters
        │                                                       │
        └───────────────────────────────────────────────────────┘
```

---

## Publish a single EXE

> The full end-to-end recipe for producing the shippable
> `Enterprise Deployment Check.zip` (WPF exe + fat jar + bundled Temurin 17
> JRE + `Input\` assets) lives in [BUILD.md](BUILD.md). The summary below
> covers just the WPF publish step.

```pwsh
dotnet publish EDC.Wpf\EDC.Wpf.csproj `
  -c Release -r win-x64 --self-contained true `
  -p:PublishSingleFile=true `
  -p:IncludeNativeLibrariesForSelfExtract=true `
  -p:EnableCompressionInSingleFile=true `
  --nologo
```

After publish, the publish directory holds:

```
publish\
├── EDC.exe              ← single self-contained executable (~70 MB)
├── Input\               ← copied from ..\..\Enterprise Deployment Check\Input
├── PythonAPI\           ← copied PyInstaller helpers (*.exe)
├── engine\              ← drop the fat JAR here as `edc.jar`
└── runtime\             ← drop an Adoptium Temurin JRE 17 (with bin\java.exe) here
```

The `EdcZipPublish` MSBuild target also packs the publish folder into
`bin\Release\EDC-v1.0.0-win-x64.zip` for distribution.

### To finish a redistributable build

1. Build the Java fat JAR from `Enterprise Deployment Check\` (`mvn package`)
   and copy/rename it to `publish\engine\edc.jar`.
2. Download Adoptium Temurin 17 (Windows x64 JRE) and extract so that
   `publish\runtime\bin\java.exe` exists.
3. Re-zip `publish\` (or rerun `dotnet publish` to regenerate the zip).
4. Ship the zip; users extract and double-click `EDC.exe` — Windows requests
   elevation (matches the original launcher's manifest).

### Deploy to local install location

The recurring inner-loop command used during development:

```pwsh
Get-Process EDC -ErrorAction SilentlyContinue | Stop-Process -Force
Start-Sleep -Seconds 2
dotnet publish EDC.Wpf\EDC.Wpf.csproj -c Release -r win-x64 --self-contained true `
  -p:PublishSingleFile=true -p:IncludeNativeLibrariesForSelfExtract=true `
  -p:EnableCompressionInSingleFile=true --nologo
Copy-Item EDC.Wpf\bin\Release\net8.0-windows\win-x64\publish\EDC.exe `
  C:\EDCBUilds\EDC-v1.0.0-win-x64_2\EDC.exe -Force
Start-Process C:\EDCBUilds\EDC-v1.0.0-win-x64_2\EDC.exe
```

---

## Distribution size

Approximate breakdown of the shipping zip:

| Component         | Size     | Notes                                                |
|-------------------|----------|------------------------------------------------------|
| `engine/edc.jar`  | ~1.04 GB | Java fat JAR (Selenium + ArcGIS Runtime + …)         |
| `Input/`          | ~731 MB  | 7× PyInstaller `.exe` (~91 MB ea) + `LosAngeles.vtpk` + SLPKs |
| `runtime/`        | ~125 MB  | Bundled Adoptium JRE 17                              |
| `EDC.exe`         | ~70 MB   | .NET 8 self-contained single-file (compressed)       |
| **Zip total**     | ~1.87 GB | DEFLATE via MSBuild `ZipDirectory`                   |

Levers if you need a slimmer distribution (ranked by ROI):

1. **PyInstaller `--onedir`** for the Python helpers (shares one Python
   runtime across all 7 scripts) — potential ~500 MB saving.
2. **Shrink/thin the JAR** (ProGuard / R8 / thin-jar + `lib/`) — potential
   100–600 MB depending on what's bundled.
3. **`jlink`-stripped JRE** with only the modules `edc.jar` uses — ~70 MB
   saving.
4. **`EDC.exe` tweaks** in `EDC.Wpf.csproj` — ~10–15 MB saving:
   ```xml
   <InvariantGlobalization>true</InvariantGlobalization>
   <SatelliteResourceLanguages>en</SatelliteResourceLanguages>
   <DebuggerSupport>false</DebuggerSupport>
   <EventSourceSupport>false</EventSourceSupport>
   <HttpActivityPropagationSupport>false</HttpActivityPropagationSupport>
   <MetadataUpdaterSupport>false</MetadataUpdaterSupport>
   <UseSystemResourceKeys>true</UseSystemResourceKeys>
   <DebugType>none</DebugType>
   <DebugSymbols>false</DebugSymbols>
   ```
   Do **not** set `PublishTrimmed=true` — WPF is not trim-safe and XAML
   `{x:Type …}` lookups will silently break.
5. **7-Zip LZMA2** instead of `ZipDirectory` for the final archive (~5–10 %
   smaller zip; most payload is already compressed).

---

## Conventions for contributors

- **Never** hard-code colors in views. Use
  `{DynamicResource TextPrimaryBrush}`, `…SecondaryBrush`,
  `BorderSubtleBrush`, `WindowBackgroundBrush`, `PanelBackgroundBrush`,
  `CardBackgroundBrush`, `BrandBlueBrush`, `StatusPass/Fail/SkipTextBrush`,
  etc. Add new theme tokens to **both** `Dark.xaml` and `Light.xaml`.
- **Never** rely on `IValueConverter` to return a brush for theme-sensitive
  text. Converters cache the returned brush at convert time and don't refresh
  on theme swap. Use a `Style` + `DataTrigger` + `DynamicResource` instead
  (see `LogMessageText` in `Views/ResultsLogPane.xaml`).
- **Never** put theme-sensitive brushes in `App.xaml` outside the merged
  dictionaries — the runtime theme swap only re-merges the
  `Themes/Dark.xaml` / `Themes/Light.xaml` entries.
- Keep `EDC.Core` UI-free and unit-tested. View-model code goes in
  `EDC.Wpf/ViewModels/`.
- Run `dotnet test EDC.Wpf.Tests` before opening a PR.

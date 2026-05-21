# EDC Architecture

> Companion document to [README.md](README.md). Describes the runtime
> components, layering rules, data flow, threading model, on-disk contract,
> and extension points of **Enterprise Deployment Check**.

---

## Table of Contents

1. [Component map](#component-map)
2. [Runtime data flow](#runtime-data-flow)
3. [Layering rules](#layering-rules)
4. [Process model](#process-model)
5. [On-disk contract](#on-disk-contract)
6. [Threading & cancellation](#threading--cancellation)
7. [Theming pipeline](#theming-pipeline)
8. [Extension points](#extension-points)
9. [Sequence: a single test run](#sequence-a-single-test-run)
10. [Glossary](#glossary)

---

## Component map

```
+--------------------------------------------------------------+
|  Enterprise Deployment Check.exe   (.NET 8, WPF, single-file)|
|                                                              |
|  +---- EDC.Wpf  (UI host, net8.0-windows) ----------------+  |
|  |                                                        |  |
|  |  MainWindow (Fluent RibbonWindow)                      |  |
|  |    Backstage:   New / Open / Save / Reports / About    |  |
|  |    Ribbon:      Home . Tests . View . Help             |  |
|  |    Workspace:   [Input Params | Tests to Run] |  |  Log|  |
|  |                                                        |  |
|  |  ViewModels (MVVM, CommunityToolkit.Mvvm)              |  |
|  |    MainViewModel                                       |  |
|  |    TestSelectionViewModel                              |  |
|  |    ResultsLogViewModel                                 |  |
|  |    ReportsExplorerViewModel                            |  |
|  |    ToastViewModel                                      |  |
|  |                                                        |  |
|  +--------------------+-----------------------------------+  |
|                       |                                      |
|                       v   (project reference only)           |
|  +---- EDC.Core  (headless library, net8.0) -------------+   |
|  |                                                        |  |
|  |  Config/   EdcConfigStore  (Java .properties I/O)      |  |
|  |  Models/   EdcConfig, ServerSlot                       |  |
|  |  Execution/ JavaProcessRunner, JreLocator              |  |
|  |  Logging/  LogStreamParser, LogEntry, TestCaseLogGroup |  |
|  |  Reports/  ReportLocator                               |  |
|  |  Tests/    TestSelectionRules (cascade)                |  |
|  |  Theming/  AppTheme enum                               |  |
|  |                                                        |  |
|  +--------------------+-----------------------------------+  |
+-----------------------+--------------------------------------+
                        |
                        | spawns (java.exe -jar engine\edc.jar)
                        v
+-----------------------+--------------------------------------+
|  edc.jar  (Java 21, Maven jar-with-dependencies)             |
|                                                              |
|  com.automation.testcases                                    |
|    EDCMainFrame       (Legacy Swing UI entry point)          |
|    TestRunner         (Programmatic TestNG launcher)         |
|    EDCTestListener    (PASS/FAIL/SKIP stdout emitter)        |
|    *_TestScenario     (Login, Org, Groups, Content, Map,     |
|                        Scene, Dashboard, StoryMap, EXB, WAB) |
|                                                              |
|  com.automation.pages   (Selenium Page Objects)              |
|  com.automation.library (TestBase, CommonFunction, ImageUtil)|
|                                                              |
|  Selenium 4.27 ----> Chrome (local)                          |
|  REST/Admin API ---> ArcGIS Portal / Server                  |
|  Runtime.exec    ---> PythonAPI\*.exe                        |
+-----------------------+--------------------------------------+
                        |
                        | Runtime.exec  (one-shot helpers)
                        v
+-----------------------+--------------------------------------+
|  PythonAPI\*.exe      (PyInstaller --onefile, ArcGIS API)    |
|                                                              |
|  Folder.exe                 servicesCreatefolder.py          |
|  DeleteAllFolder.exe        DeleteFoldersAndItems.py         |
|  DeleteItemsAll.exe         MoveItem.exe                     |
|  DeleteTileLayers.exe       DeleteSceneLayers.py             |
|  SceneLayerCreation.exe     SceneLayerFromRoot.py            |
|  ValidateDataStore.exe      ValidateObjectStore.exe          |
|  ValidateObjectStorek8s.exe Projectversion.exe               |
+--------------------------------------------------------------+
```

---

## Runtime data flow

```
 user input (ribbon + tabs)
        |
        v
 MainViewModel (mutates EdcConfig)
        |
        |  Save  --> EdcConfigStore.Save(...) --> Input\config.properties
        |  Run   --> JavaProcessRunner.Start(...)
        v
 java.exe -jar engine\edc.jar  ----> Selenium / REST / Python helpers
        |
        |  stdout / stderr (single combined stream)
        v
 LogStreamParser (per-line regex classifier)
        |
        |  LogEntry { Timestamp, Level, Message, TestCase? }
        v
 ResultsLogViewModel (groups into TestCaseLogGroup, updates counters)
        |
        v
 ResultsLogPane.xaml  (DataTemplate per LogEntry, status filters)
```

Three observable side-channels also exist:

- **Disk log tail** (`LogFileTailer`) — watches the date-stamped
  `EnterpriseDeploymentCheckResults\YYYY-MM-DD\Logs\` directory for the
  TestNG `EnterpriseDeploymentCheckLogs_*.txt` so the WPF UI can still
  reconstruct state even if the parent process restarts.
- **ExtentReports HTML** — emitted by the Java engine on completion;
  surfaced via `ReportLocator.FindLatestHtmlReport(baseDir)`.
- **Failure screenshots** — written by Selenium under
  `EnterpriseDeploymentCheckResults\YYYY-MM-DD\Screenshots\`.

---

## Layering rules

Strict, enforced by project references and code review:

| Layer        | May reference                                  | Must NOT reference                |
|--------------|------------------------------------------------|-----------------------------------|
| `EDC.Core`   | BCL, Microsoft.Extensions.* (optional)         | WPF, PresentationFramework, XAML, AvalonDock, Fluent.Ribbon |
| `EDC.Wpf`    | `EDC.Core`, WPF, Fluent.Ribbon, MVVM toolkit   | ArcGIS REST APIs, Selenium, JDBC  |
| `edc.jar`    | Selenium, TestNG, Apache POI, ArcGIS REST       | The .NET assemblies               |
| Python `.py` | ArcGIS API for Python, stdlib                  | .NET, Java                        |

**Why it matters:**

- `EDC.Core` stays fully unit-testable (`EDC.Wpf.Tests` uses xUnit and never
  spins up a WPF Dispatcher).
- `EDC.Wpf` can be replaced (CLI / Avalonia / MAUI) without touching
  business logic.
- The Java engine is the single integration boundary with ArcGIS — every
  REST/Admin/browser call lives there.

---

## Process model

```
 (Windows session, UAC-elevated)
   |
   +--> Enterprise Deployment Check.exe   (foreground GUI; .NET 8 host)
          |
          +--> java.exe -jar engine\edc.jar  (child; redirected stdout+stderr)
                 |
                 +--> chrome.exe        (Selenium-managed)
                 +--> chromedriver.exe  (Selenium Manager auto-fetches)
                 +--> Folder.exe        (PyInstaller helper, transient)
                 +--> SceneLayerCreation.exe ...
```

- `JavaProcessRunner` uses `ProcessStartInfo` with
  `RedirectStandardOutput = RedirectStandardError = true`,
  `UseShellExecute = false`, and a `CancellationToken` that ends the child
  process tree on Stop.
- `JreLocator.Locate(baseDir)` resolves the JRE in this order:
  1. `<baseDir>\runtime\bin\java.exe` (shipped JRE — preferred).
  2. `JAVA_HOME\bin\java.exe`.
  3. First `java.exe` found on `PATH`.
- The Java engine exits with code 0 on success, non-zero on suite failure.
  `JavaProcessRunner.OnExited` surfaces the exit code; the WPF UI flips
  `IsRunning` to false and shows a toast.

---

## On-disk contract

Everything is relative to the directory containing
`Enterprise Deployment Check.exe`:

```
<install>\
  Enterprise Deployment Check.exe
  engine\
    edc.jar                                  (Java fat JAR)
  runtime\                                   (optional, preferred)
    bin\java.exe
    ...
  Input\
    config.properties                        (mutable, single source of truth)
    TestNG.xml                               (suite definition)
    ChateauFare.slpk, *.slpk, *.vtpk         (sample geodata)
    Folder.exe, *.exe                        (PyInstaller helpers)
    Enterprise Deployment Check - Help Doc.pdf
  EnterpriseDeploymentCheckResults\          (created at runtime)
    YYYY-MM-DD\
      Logs\
        EnterpriseDeploymentCheckLogs_<ts>.txt
      Reports\
        ExtentReport.html
      Screenshots\
        <test>_<ts>.png
```

- `Input\config.properties` uses **Java `.properties`** format
  (ISO-8859-1, `key=value`, `#` comments). `EdcConfigStore` reads/writes
  while preserving order to minimise VCS noise.
- `TestNG.xml` is the *suite* definition; per-test enablement is driven by
  the `Yes/No` flags in `config.properties` via
  `com.automation.library.AnnotationTransformer`.
- `EnterpriseDeploymentCheckResults\…\Logs\<ts>.txt` is the authoritative
  audit trail. The WPF Results Log pane only mirrors a subset (status +
  banner lines) for ergonomic display.

---

## Threading & cancellation

WPF side:

- All view-model mutations happen on the **UI dispatcher**. View-models use
  `[ObservableProperty]` + `[RelayCommand]` from CommunityToolkit.Mvvm.
- `JavaProcessRunner` raises `OutputReceived` / `ErrorReceived` on
  **thread-pool threads**; the consumer (`MainViewModel.HandleLine`)
  marshals back to the UI thread via `Dispatcher.BeginInvoke` before
  touching `ResultsLogViewModel`.
- Long-running file I/O (e.g. opening an ExtentReport) uses `Task.Run` so
  the ribbon stays responsive.

Cancellation:

- `MainViewModel.IsRunning` is bound to ribbon button `CanExecute`s.
  Pressing **Stop** signals a `CancellationTokenSource`; `JavaProcessRunner`
  kills the child process tree (`Process.Kill(entireProcessTree: true)`).
- `ResultsLogViewModel.ClearLogCommand.CanExecute` is `!IsRunning`, so the
  button auto-disables while a run is in progress.

Java side:

- Each `_TestScenario` runs synchronously on TestNG's worker thread.
- `EDCTestListener` is invoked synchronously after each `@Test` and writes
  the parseable `PASS / FAIL / SKIP <name>` line **after** any user-level
  logging — guaranteeing the .NET parser sees status events in order.

---

## Theming pipeline

```
 App.xaml
   MergedDictionaries:
     [0] Fluent.Ribbon variant (Office2013 Dark/Light)
     [1] Themes\ArcProPalette.xaml   (font scale, brand blue, sizing tokens)
     [2] Themes\Dark.xaml or Light.xaml  (surface palette, swappable)

 MainViewModel.SetTheme(dark)
   1. Replace [0] with the matching Fluent variant.
   2. Replace [2] with Dark.xaml or Light.xaml.
   3. Persisted theme choice is in-memory only (no file).

 Consumers
   - XAML  : {DynamicResource BrandBlueBrush}        (re-resolves on swap)
   - Code  : Application.Current.FindResource("...") (re-resolves on swap)
   - NEVER : converters returning a Brush (cached — won't update on swap)
   - NEVER : brushes outside the MergedDictionaries (won't swap)
```

Tokens that *every* view should consume instead of hard-coded colors:

```
TextPrimaryBrush         TextSecondaryBrush       TextDisabledBrush
WindowBackgroundBrush    PanelBackgroundBrush     CardBackgroundBrush
BorderSubtleBrush        HoverOverlayBrush
BrandBlueBrush
StatusPassTextBrush      StatusFailTextBrush      StatusSkipTextBrush
StatusWarnTextBrush      StatusInfoTextBrush
```

Implicit styles (`ProButton`, `ProTextBox`, `ProComboBox`, `WatermarkTextBox`)
live in `Themes\Dark.xaml` and `Themes\Light.xaml` so they re-style on swap.

---

## Extension points

### Add a new test scenario

1. Java side (`Enterprise Deployment Check\src\com\automation\testcases\`):
   - Create `MyNew_TestScenario.java` with TestNG `@Test` methods.
   - Wire it into `Input\TestNG.xml`.
   - Add the `@Test` flag to the listener-emitted output naming convention.
2. Config side (`EDC.Core\Models\EdcConfig.cs`):
   - Add a property + key to `EdcConfigStore` round-trip.
3. UI side:
   - Add a `CheckBox` in `Views\TestsToRunPane.xaml` bound via
     `TestSelectionViewModel`.
   - Extend `TestSelectionRules` if "Run all tests" should cascade-tick it.
4. Tests:
   - Add an `EdcConfigStoreTests` round-trip case for the new key.

### Add a new Python helper

1. Drop `MyHelper.py` under
   `Enterprise Deployment Check\src\com\automation\PythonAPI\`.
2. Add a sibling `MyHelper.spec` (prefer `--onedir` for new helpers — see
   the *Distribution size* section of the README).
3. `pyinstaller MyHelper.spec` → resulting `dist\MyHelper\…` goes into the
   staging `Input\` (or a new `PythonAPI\` subfolder; the Java engine
   shells out via `Runtime.exec` with the resolved path).
4. Update the test scenario that consumes it.

### Add a new ribbon command

1. Add a `[RelayCommand]` to `MainViewModel`.
2. Add a `<fluent:Button>` in `Views\MainWindow.xaml` bound to it.
3. If it touches files, use the existing `Toasts` view-model for feedback.

---

## Sequence: a single test run

```
 User: clicks Run (Home > Execution > Run Tests Large)
   |
   v
 MainViewModel.RunTestsCommand
   - persists EdcConfig -> Input\config.properties (EdcConfigStore.Save)
   - clears ResultsLogViewModel buffers (only if not blocked by CanExecute)
   - resolves jre   = JreLocator.Locate(baseDir)
   - resolves jar   = <baseDir>\engine\edc.jar
   - resolves work  = <baseDir>
   - starts LogFileTailer on <baseDir>\EnterpriseDeploymentCheckResults\<today>\Logs
   - launches JavaProcessRunner.Start(jre, jar, workDir)
   |
   v
 java.exe -jar engine\edc.jar
   - boots TestNG with Input\TestNG.xml
   - AnnotationTransformer disables @Tests whose config flag is "No"
   - EDCTestListener emits "PASS Login_To_Portal" / "FAIL ..." / "SKIP ..."
     after each test, plus heading / step / info / warn / error lines
   |
   v   (stdout, line by line)
 LogStreamParser
   - classifies into LogEntry { Level, Message, TestCase? }
   - returns to MainViewModel.HandleLine
   |
   v
 MainViewModel.HandleLine
   - filters to status-prefixed lines + banner lines
   - marshals to UI thread
   - appends into ResultsLogViewModel (grouped by TestCase)
   - updates Pass/Fail/Skip/Total counters
   |
   v
 ResultsLogPane.xaml
   - DataTemplate renders one row per LogEntry
   - filter chips (Total / Passed / Failed / Skipped) toggle visibility
   - ContextMenu: Copy all, Clear log (disabled while IsRunning)
   |
   ... (eventually) ...
 java.exe exits with code 0/N
   - JavaProcessRunner.OnExited fires
   - MainViewModel sets IsRunning = false
   - Toasts.Show("Run complete", ...) or ("Run failed", ...)
   - ReportLocator.FindLatestHtmlReport(baseDir) feeds the Reports view
```

---

## Glossary

| Term                       | Meaning                                                                 |
|----------------------------|-------------------------------------------------------------------------|
| **Engine** / `edc.jar`     | The Java fat-JAR (`maven-assembly-plugin` `jar-with-dependencies`) that owns all ArcGIS integration. |
| **Listener**               | `com.automation.testcases.EDCTestListener` — emits parseable PASS/FAIL/SKIP. |
| **Page Object**            | Selenium pattern: one class per portal page (`LoginPage`, `MapPage`, …). |
| **Scenario**               | A TestNG class under `com.automation.testcases.*_TestScenario`.        |
| **Config**                 | `Input\config.properties` — Java `.properties` format, single source of truth. |
| **Slot**                   | One of the 10 server slots (`Federated{N}`, `ServerUrl{N}`, …).         |
| **Status line**            | A stdout line beginning with `PASS / FAIL / SKIP / WARN / ERROR`, emitted by `EDCTestListener`. |
| **Banner line**            | A stdout line marking the start of a test/section (contains " started", no colon). |
| **Results folder**         | `EnterpriseDeploymentCheckResults\YYYY-MM-DD\` — Logs, Reports, Screenshots. |
| **Shippable**              | The pristine `Input\config.properties.shippable` template that replaces the dev `config.properties` during zip staging. Ships with every test flag set to `No` and all server slots blank, so a fresh install starts from a clean slate. |

---

See [README.md](README.md) for the user-facing quick start, VS Code dev
workflow, and configuration reference.

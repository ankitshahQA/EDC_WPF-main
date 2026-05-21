# Enterprise Deployment Check (EDC)

[![Java](https://img.shields.io/badge/Java-21-007396?logo=openjdk&logoColor=white)](#java-engine)
[![.NET](https://img.shields.io/badge/.NET-8.0-512BD4?logo=dotnet&logoColor=white)](#net-wpf-ui)
[![Selenium](https://img.shields.io/badge/Selenium-4.27-43B02A?logo=selenium&logoColor=white)](#test-execution)
[![TestNG](https://img.shields.io/badge/TestNG-7.10-DE2C2C)](#test-execution)
[![Python](https://img.shields.io/badge/Python-3.x-3776AB?logo=python&logoColor=white)](#python-helpers)
[![Windows](https://img.shields.io/badge/Windows-x64-0078D6?logo=windows&logoColor=white)](#system-requirements)

> **Validates portal and server functionality for ArcGIS Enterprise deployments
> (v11.0+).** A single tool to onboard new deployments or verify the integrity
> of an existing setup — reproducible, configurable, and reportable.

---

## Table of Contents

1. [What EDC does](#what-edc-does)
2. [Project layout](#project-layout)
3. [System requirements](#system-requirements)
4. [Quick start (end users)](#quick-start-end-users)
5. [Quickstart (VS Code)](#quickstart-vs-code)
6. [Architecture](#architecture)
7. [.NET WPF UI](#net-wpf-ui)
7. [Java engine](#java-engine)
8. [Python helpers](#python-helpers)
9. [Configuration reference (`config.properties`)](#configuration-reference-configproperties)
10. [Test catalogue](#test-catalogue)
11. [Building from source](#building-from-source)
12. [Packaging & distribution](#packaging--distribution)
13. [Distribution size](#distribution-size)
14. [Running and reading results](#running-and-reading-results)
15. [Theming & UI conventions](#theming--ui-conventions)
16. [Logging](#logging)
17. [Troubleshooting](#troubleshooting)
18. [Contributing](#contributing)
19. [Repository links](#repository-links)

---

## What EDC does

EDC drives **Selenium + TestNG** browser automation and direct REST/Admin API
calls against an ArcGIS Enterprise deployment to confirm that:

- Portal login works (admin + named user).
- Server Manager and Server Admin login work for each federated server.
- Server roles are configured as expected (Hosting / GIS / Image / etc.).
- Data stores and (on Kubernetes) object stores are healthy.
- Organization metadata, members, groups and content are reachable.
- A user can create representative content end-to-end: feature layer, tile
  layer, scene layer, map, dashboard, story map, web app, experience builder
  app, web app builder app.

The user picks which checks to run, points the tool at a portal, optionally
lists up to 10 federated servers, and gets:

- A live, colorised log of every step taken.
- Pass / Fail / Skip counters with elapsed time.
- An on-disk **ExtentReports** HTML report under `EnterpriseDeploymentCheckResults\`.

There are **two front-ends to the same engine**:

| Front-end          | Tech                                  | Status                                  |
|--------------------|---------------------------------------|-----------------------------------------|
| Modern WPF UI      | .NET 8 · WPF · Fluent.Ribbon · AvalonDock | Active — `EDC.Wpf.Solution\`         |
| Legacy Swing UI    | Java Swing (`EDCMainFrame`)           | Maintained — `Enterprise Deployment Check\` |

The Swing UI ships as the original `EDC.exe` (built with launch4j). The new
WPF UI ships as `Enterprise Deployment Check.exe` and re-uses the same Java
fat-JAR engine, the same `config.properties` schema, the same Python helpers,
and the same on-disk report folder.

---

## Project layout

```
EDC-main/
├── README.md                              ← you are here
├── .github/                               Issue templates
│
├── Enterprise Deployment Check/           ── Java engine + legacy Swing UI
│   ├── pom.xml                            Maven, builds jar-with-dependencies
│   ├── edc.manifest                       requireAdministrator (UAC)
│   ├── launch4j_edc.xml                   wraps the fat JAR as EDC.exe
│   ├── Input/
│   │   ├── config.properties              Single source of truth at runtime
│   │   ├── TestNG.xml                     Suite definition
│   │   ├── ChateauFare.slpk               Scene layer sample (small)
│   │   ├── ChateauFare_Large.slpk         Scene layer sample (large)
│   │   ├── SimpleMultiPatch.slpk          Multipatch sample
│   │   └── LosAngeles.vtpk                Vector tile package sample
│   └── src/com/automation/
│       ├── library/                       AnnotationTransformer, CommonFunction,
│       │                                  ImageUtil, TestBase
│       ├── pages/                         Selenium Page Objects (Login, Home,
│       │                                  Dashboard, Map, Scene, Server, Groups,
│       │                                  Content, Organization, StoryMap,
│       │                                  ExperienceBuilder, WebAppBuilder)
│       ├── testcases/                     ── TestNG @Test classes + Swing UI
│       │   ├── EDCMainFrame.java          Swing JFrame entry point (main)
│       │   ├── EDCTestListener.java       TestNG listener — produces the
│       │   │                              parseable PASS/FAIL/SKIP stream
│       │   ├── TestRunner.java            Programmatic TestNG launcher
│       │   ├── ConfigControl*.java        Swing panels — properties editor
│       │   ├── InputParametersPanel.java  Swing panel — Input Parameters tab
│       │   ├── TestsToRunPanel.java       Swing panel — Tests checklist
│       │   ├── ResultsLogPanel.java       Swing panel — log viewer
│       │   ├── DialogControl.java         Modal dialogs
│       │   ├── Login_To_Portal_TestScenario.java
│       │   ├── Login_To_ServerAdmin_TestScenario.java
│       │   ├── Login_To_ServerManager_TestScenario.java
│       │   ├── Organization_TestScenario.java
│       │   ├── Groups_TestScenario.java
│       │   ├── Content_Functionality_TestScenario.java
│       │   ├── CreateScene_TestScenario.java
│       │   ├── CreateDashboard_Functionality.java
│       │   ├── Map_Functionality_TestScenario.java
│       │   ├── StoryMap_Functionality.java
│       │   ├── ExperienceBuilder_Functionality_TestScenario.java
│       │   └── WebAppBuilder_Functionality_TestScenario.java
│       └── PythonAPI/                     PyInstaller helpers (see below)
│
├── EDC.Wpf.Solution/                      ── Modern .NET 8 WPF front-end
│   ├── EDC.sln
│   ├── EDC.Core/                          Pure library — no UI deps
│   │   ├── Config/                        Properties-file reader, EdcConfigStore
│   │   ├── Execution/                     JavaProcessRunner, JreLocator
│   │   ├── Logging/                       LogEntry, LogStreamParser,
│   │   │                                  TestCaseLogGroup
│   │   ├── Models/                        EdcConfig, ServerSlot
│   │   ├── Reports/                       ReportLocator
│   │   ├── Tests/                         TestSelectionRules (cascade logic)
│   │   └── Theming/                       AppTheme enum
│   ├── EDC.Wpf/                           WPF host (net8.0-windows)
│   │   ├── App.xaml(.cs)                  Merges Fluent + ArcProPalette + Dark/Light
│   │   ├── app.manifest                   requireAdministrator
│   │   ├── Assets/                        Esri logo (.png + .ico)
│   │   ├── Themes/                        ArcProPalette · Dark · Light
│   │   ├── ViewModels/                    MainViewModel, ResultsLogViewModel,
│   │   │                                  TestSelectionViewModel,
│   │   │                                  ReportsExplorerViewModel, ToastViewModel
│   │   ├── Views/                         MainWindow + 3 panes
│   │   └── Converters/                    Log/Visibility converters
│   └── EDC.Wpf.Tests/                     xUnit — config parse, log parse,
│                                          test-selection cascade
│
└── EnterpriseDeploymentCheckResults/      Output directory created at run time
    └── YYYY-MM-DD/
        ├── Logs/                          EnterpriseDeploymentCheckLogs_*.txt
        ├── Reports/                       ExtentReports HTML
        └── Screenshots/                   PNGs of failure points
```

---

## System requirements

| Item               | Minimum                                              |
|--------------------|------------------------------------------------------|
| OS                 | Windows 10 / 11 / Server 2019+ (x64)                 |
| Privileges         | Local Administrator (UAC elevation requested)        |
| .NET runtime       | None — the WPF EXE is self-contained                 |
| Java runtime       | None — shipped JRE 17/21 lives in `runtime\`         |
| Browser            | Google Chrome (current); WebDriver auto-managed      |
| Python             | None — PyInstaller exes are self-contained           |
| Disk               | ~2 GB extracted (engine + JRE + sample data)         |
| Network            | HTTPS access to the portal / server URLs under test  |

---

## Quick start (end users)

1. Grab the latest `Enterprise Deployment Check.zip` from the internal share:
   `\\esri.com\psdata\PSProductsQA\EDC`
2. Extract anywhere (e.g. `C:\EDC\`). Keep the folder structure intact —
   the top-level folder inside the zip is `Enterprise Deployment Check\`
   containing `Enterprise Deployment Check.exe`, `engine\edc.jar`, and `Input\`.
3. Double-click `Enterprise Deployment Check.exe` → accept the UAC prompt.
4. Fill in:
   - **Portal URL** (e.g. `https://gis.example.com/portal`)
   - **Portal admin user / password**
   - One or more **Federated server URLs + admin credentials**
   - Tick **Kubernetes** if the deployment is ArcGIS Enterprise on K8s.
5. On the *Tests to Run* tab, tick the boxes you want — or tick **Run all
   tests**.
6. Click **Run** in the ribbon's *Execution* group.
7. Watch the *Results Log* on the right. When finished, click **Open report
   folder** (Reports group) to browse the HTML report.

> No installer is required. The first run creates
> `EnterpriseDeploymentCheckResults\YYYY-MM-DD\` next to the EXE.
> `Input\config.properties` ships pristine (all toggles `No`, all credentials
> blank) so a fresh extraction shows no records from a previous machine.

### Headless / CI-CD mode (no UI)

The Java engine ships a headless CLI suitable for Azure DevOps, Jenkins,
GitHub Actions, or any agent that can run a Java process and read an exit
code. The shippable zip already bundles **Adoptium Temurin 17 JRE** under
`runtime\` and the **PyInstaller-compiled Python helpers** under `PythonAPI\`,
so the end-user/CI agent does **not** need Java or Python installed.

```pwsh
# From the unzipped folder (uses the bundled JRE):
.\runtime\bin\java.exe -jar .\engine\edc.jar `
    --headless `
    --config  .\Input\config.properties `
    --results .\EnterpriseDeploymentCheckResults `
    --tests   LoginPortal,CreateFeatureLayer,CreateMap `
    [--fail-on-skip]
```

| Exit code | Meaning |
|-----------|---------|
| `0` | All selected tests passed |
| `1` | At least one test failed |
| `2` | At least one test was skipped (only when `--fail-on-skip` is set) |
| `3` | Config validation error |
| `4` | Uncaught exception |

Full reference and sample pipeline files (`azure-pipelines.yml`,
`Jenkinsfile`): [Enterprise Deployment Check/ci/README.md](Enterprise%20Deployment%20Check/ci/README.md).

---

## Quickstart (VS Code)

For developers who want to open, build, debug, and ship EDC entirely from
Visual Studio Code.

### 1. Recommended extensions

Install once:

| Extension                                          | Why                                  |
|----------------------------------------------------|--------------------------------------|
| **C# Dev Kit** (`ms-dotnettools.csdevkit`)         | Build / debug `EDC.Wpf.Solution`.    |
| **C#** (`ms-dotnettools.csharp`)                   | Roslyn language server (auto-pulled).|
| **Extension Pack for Java** (`vscjava.vscode-java-pack`) | Build / debug the Java engine. |
| **Maven for Java** (in the pack)                   | Run `mvn package` from the side bar. |
| **Python** (`ms-python.python`)                    | Edit / debug the PyInstaller helpers.|
| **XAML Styler** (`Josefpihrt-vscode.roslynator` or `ms-dotnettools.xaml`) | XAML formatting. |

### 2. Open the workspace

```pwsh
code c:\EDC-main
```

On first open VS Code will prompt to:

- Trust the workspace — **yes**.
- Restore .NET dependencies — **yes** (or run `dotnet restore` in the
  `EDC.Wpf.Solution` terminal).
- Configure the Java runtime — point it at JDK 21 (e.g.
  `C:\Users\<you>\.vscode\extensions\redhat.java-*\jre\21.*`).

### 3. Run / debug the WPF UI (F5)

Drop the following into `.vscode\launch.json` (create the file if missing):

```jsonc
{
  "version": "0.2.0",
  "configurations": [
    {
      "name": "Run EDC.Wpf (Debug)",
      "type": "coreclr",
      "request": "launch",
      "preLaunchTask": "build-edc-wpf",
      "program": "${workspaceFolder}/EDC-main/EDC.Wpf.Solution/EDC.Wpf/bin/Debug/net8.0-windows/Enterprise Deployment Check.dll",
      "args": [],
      "cwd": "${workspaceFolder}/EDC-main/EDC.Wpf.Solution/EDC.Wpf/bin/Debug/net8.0-windows",
      "console": "internalConsole",
      "stopAtEntry": false,
      "requireExactSource": false
    },
    {
      "name": "Run EDCMainFrame (Java Swing, Legacy)",
      "type": "java",
      "request": "launch",
      "mainClass": "com.automation.testcases.EDCMainFrame",
      "projectName": "Enterprise_Deployment_Check",
      "cwd": "${workspaceFolder}/EDC-main"
    }
  ]
}
```

And into `.vscode\tasks.json`:

```jsonc
{
  "version": "2.0.0",
  "tasks": [
    {
      "label": "build-edc-wpf",
      "type": "process",
      "command": "dotnet",
      "args": [
        "build",
        "${workspaceFolder}/EDC-main/EDC.Wpf.Solution/EDC.sln",
        "-c", "Debug",
        "/property:GenerateFullPaths=true",
        "/consoleloggerparameters:NoSummary"
      ],
      "problemMatcher": "$msCompile",
      "group": { "kind": "build", "isDefault": true }
    },
    {
      "label": "publish-edc-wpf",
      "type": "shell",
      "command": "dotnet",
      "args": [
        "publish",
        "${workspaceFolder}/EDC-main/EDC.Wpf.Solution/EDC.Wpf/EDC.Wpf.csproj",
        "-c", "Release", "-r", "win-x64",
        "--self-contained", "true",
        "-p:PublishSingleFile=true",
        "-p:IncludeNativeLibrariesForSelfExtract=true"
      ],
      "problemMatcher": []
    },
    {
      "label": "package-edc-jar",
      "type": "shell",
      "command": "mvn",
      "args": ["-q", "-DskipTests", "package"],
      "options": { "cwd": "${workspaceFolder}/EDC-main/Enterprise Deployment Check" },
      "problemMatcher": []
    },
    {
      "label": "test-edc-wpf",
      "type": "process",
      "command": "dotnet",
      "args": ["test", "${workspaceFolder}/EDC-main/EDC.Wpf.Solution/EDC.Wpf.Tests/EDC.Wpf.Tests.csproj"],
      "problemMatcher": "$msCompile",
      "group": "test"
    }
  ]
}
```

Then:

| Action               | Keystroke / command                                   |
|----------------------|-------------------------------------------------------|
| Build WPF            | <kbd>Ctrl</kbd>+<kbd>Shift</kbd>+<kbd>B</kbd>         |
| Run WPF (F5)         | Pick **Run EDC.Wpf (Debug)** in the Run & Debug panel |
| Run legacy Swing UI  | Pick **Run EDCMainFrame (Java Swing, Legacy)**        |
| Run xUnit tests      | Command Palette → *Tasks: Run Task* → `test-edc-wpf`  |
| Package Java JAR     | Command Palette → *Tasks: Run Task* → `package-edc-jar` |
| Publish single-file  | Command Palette → *Tasks: Run Task* → `publish-edc-wpf`|

### 4. Per-pane workflows

- **XAML iteration:** edit `EDC.Wpf\Views\*.xaml` and themes
  `EDC.Wpf\Themes\Dark.xaml` / `Light.xaml`. Hit <kbd>F5</kbd>; theme swap
  is on the *View* ribbon tab inside the running app.
- **ViewModel iteration:** edit `EDC.Wpf\ViewModels\*.cs`. Hot Reload
  (`dotnet watch`) is **not** supported for WPF self-contained single-file
  publishes — use plain F5 against the Debug build.
- **Java engine iteration:** edit any class under `Enterprise Deployment Check\src\…`,
  run the `package-edc-jar` task, then either launch the Swing UI directly
  (F5 → *Run EDCMainFrame*) or copy the freshly built
  `target\Enterprise_Deployment_Check-0.0.1-SNAPSHOT-jar-with-dependencies.jar`
  to `…\engine\edc.jar` next to the WPF exe.
- **Tests:** the `test-edc-wpf` task runs the xUnit suite (`EDC.Wpf.Tests`)
  which covers `EdcConfigStore`, `LogStreamParser`, and the
  `TestSelectionRules` cascade.

### 5. End-to-end smoke from inside VS Code

```pwsh
# Terminal pane (PowerShell), from c:\EDC-main
cd EDC-main\EDC.Wpf.Solution
dotnet publish .\EDC.Wpf\EDC.Wpf.csproj -c Release -r win-x64 `
  --self-contained true -p:PublishSingleFile=true `
  -p:IncludeNativeLibrariesForSelfExtract=true
Start-Process ".\EDC.Wpf\bin\Release\net8.0-windows\win-x64\publish\Enterprise Deployment Check.exe"
```

The exe self-extracts on first launch into `%TEMP%\.net\…` and then runs.

---

## Architecture

```
        ┌────────────────────── EDC.Wpf (WPF UI) ───────────────────────┐
        │                                                                │
        │  Backstage ──► New / Open / Save / Reports / About             │
        │                                                                │
        │  Ribbon ─ Home · Tests · View · Help                           │
        │                                                                │
        │  MainViewModel ── Config (EdcConfig) ─┐                        │
        │       │                                ├─ writes ──►  Input\config.properties
        │       │                                │                       │
        │       ├─ RunTestsCommand               │                       │
        │       │      │                                                 │
        │       │      ▼                                                 │
        │       │   JavaProcessRunner ─ spawns ──► runtime\bin\java.exe  │
        │       │      │                            -jar engine\edc.jar  │
        │       │      └─ stdout/stderr ──► LogStreamParser              │
        │       │                                   │                    │
        │       ▼                                   ▼                    │
        │   ResultsLogViewModel ◄── LogEntry rows + result counters      │
        │                                                                │
        └────────────────────────────────────────────────────────────────┘
                                       │
                                       │   spawns
                                       ▼
        ┌──────────────────── edc.jar (Java engine) ────────────────────┐
        │                                                                │
        │  TestRunner ──► TestNG suite (TestNG.xml)                      │
        │      │                                                         │
        │      ├─► EDCTestListener  ── emits "PASS/FAIL/SKIP <name>"     │
        │      │                       lines on stdout (parsed upstream) │
        │      │                                                         │
        │      ├─► Login_To_Portal_TestScenario                          │
        │      ├─► Login_To_ServerAdmin_TestScenario                     │
        │      ├─► Login_To_ServerManager_TestScenario                   │
        │      ├─► Organization / Groups / Content TestScenarios         │
        │      ├─► CreateScene / Map / Dashboard / StoryMap / …          │
        │      │                                                         │
        │      └─► ExtentReports ──► EnterpriseDeploymentCheckResults\   │
        │                                                                │
        │  Page Objects (Selenium) drive Chrome.                         │
        │  Python helpers shell out for ArcGIS Python API tasks.         │
        │                                                                │
        └────────────────────────────────────────────────────────────────┘
                                       │
                                       │  shells out for specific tasks
                                       ▼
        ┌──────────────────── PythonAPI\*.exe ─────────────────────────┐
        │  Folder.exe              servicesCreatefolder.py             │
        │  DeleteAllFolder.exe     bulk cleanup helper                 │
        │  MoveItem.exe            move items between folders          │
        │  DeleteTileLayers.exe    cleanup helper                      │
        │  SceneLayerCreation.exe  publishes the SLPK samples          │
        │  ValidateDataStore.exe   datastoreutils against the engine   │
        │  ValidateObjectStore.exe k8s ObjectStore probe               │
        │  Projectversion.exe      reports portal version              │
        └───────────────────────────────────────────────────────────────┘
```

- **EDC.Core** has zero UI dependencies and is fully unit-tested.
- **EDC.Wpf** only orchestrates and renders — never talks to ArcGIS directly.
- The **Java engine** is the only component that talks to ArcGIS REST/Admin
  APIs and drives the browser.

---

## .NET WPF UI

> .NET 8 · WPF · Fluent.Ribbon 11 · AvalonDock 4.74 · CommunityToolkit.Mvvm · Serilog

### Solution projects

| Project          | Purpose                                                |
|------------------|--------------------------------------------------------|
| `EDC.Core`       | Headless library: config, log parsing, process runner. |
| `EDC.Wpf`        | WPF host (RibbonWindow + AvalonDock + panes + VMs).    |
| `EDC.Wpf.Tests`  | xUnit tests against `EDC.Core`.                        |

### Visual language

- **ArcGIS Pro 3.x** cues throughout.
- **Title bar** — `fluent:RibbonWindow`, `TitleBarHeight=32`, Esri logo as
  window icon. Windows-style Min/Max/Close caption buttons with theme-aware
  glyph color, alpha-overlay hover, and Windows red (`#E81123`) close hover.
- **Ribbon** — Backstage "Project" page (New / Open / Save / Reports /
  About) plus tabs Home · Tests · View · Help.
- **Workspace** — 50/50 split (`*` / `6px` GridSplitter / `*`,
  `MinWidth=380` each side):
  - Left: **Input Parameters** + **Tests to Run** as a tabbed `TabControl`.
  - Right: **Results Log** as a single-tab `TabControl` sharing the same
    styled tab header.
- **Status bar** — live status text + elapsed timer.
- **Theme toggle** (View tab) — runtime Dark ↔ Light swap.
- **Section headers** use `FontSize.Body` (13) + `Medium` weight + `TextPrimaryBrush`.

### Key view-models

| ViewModel                  | Responsibility                                         |
|----------------------------|--------------------------------------------------------|
| `MainViewModel`            | Owns `EdcConfig`, run/stop commands, theme swap.       |
| `TestSelectionViewModel`   | Implements the *Run all tests* cascade rules.          |
| `ResultsLogViewModel`      | Streams `LogEntry` rows into `TestCaseLogGroup`s.      |
| `ReportsExplorerViewModel` | Lists past reports, opens HTML in the default browser. |
| `ToastViewModel`           | Transient banners (saved / errors).                    |

### Theming internals

`MainViewModel.SetTheme(bool dark)` does **two** dictionary swaps inside
`Application.Current.Resources.MergedDictionaries`:

1. Replaces the **Fluent.Ribbon variant** (Office2013 Dark ↔ Light).
2. Replaces our own **surface palette** (`Themes/Dark.xaml` ↔
   `Themes/Light.xaml`).

> ⚠ Brushes declared at the top of `App.xaml` (outside merged dictionaries)
> **do not** re-resolve on theme swap. Always put theme-sensitive brushes
> inside the per-theme dictionaries and consume them via `{DynamicResource}`.

---

## Java engine

> Java 21 · Selenium 4.27 · TestNG 7.10 · ExtentReports 5.0 · Apache POI 5.2 ·
> Guava 33 · Netty · commons-io · commons-lang3 · Gson · Lombok

### Entry points

| Class                                              | Role                                  |
|----------------------------------------------------|---------------------------------------|
| `com.automation.testcases.EDCMainFrame`            | Swing JFrame (legacy UI `main`).      |
| `com.automation.testcases.TestRunner`              | Programmatic TestNG launcher.         |
| `com.automation.testcases.EDCTestListener`         | TestNG listener; emits parseable PASS/FAIL/SKIP lines on stdout for the WPF UI. |

`pom.xml` builds with `maven-assembly-plugin` (`jar-with-dependencies`) and
the `Main-Class` manifest entry points at `EDCMainFrame` (legacy) — the WPF
launcher invokes the same JAR but lets TestNG drive `TestRunner` via the
suite XML.

### Test execution

- The suite is defined in `Input\TestNG.xml`.
- Per-test behaviour is gated by `Input\config.properties` (`Yes` / `No`
  flags + the *Run all tests* override).
- `EDCTestListener` writes a deterministic, one-line-per-event stream that
  `EDC.Core.Logging.LogStreamParser` understands:
  - `PASS <test name>` · `FAIL <test name>` · `SKIP <test name>`
  - Heading / Step / Info / Warn / Error lines for everything else.
- ExtentReports HTML is written under
  `EnterpriseDeploymentCheckResults\YYYY-MM-DD\Reports\`.
- Failure screenshots land in `…\Screenshots\`.

### Page Objects (Selenium)

Under `src\com\automation\pages\`:

`LoginPage`, `HomePage`, `OrganizationPage`, `GroupsPage`, `ContentPage`,
`MapPage`, `SceneLayer`, `DashboardPage`, `StoryMapPage`,
`ExperienceBuilderApp`, `WebAppBuilder`, `ServerPage`.

### Library (`src\com\automation\library\`)

- `TestBase` — driver lifecycle, ExtentReports plumbing.
- `CommonFunction` — waits, asserts, JS helpers.
- `ImageUtil` — screenshot capture.
- `AnnotationTransformer` — applies the `Yes/No` flags to TestNG `@Test`s.

---

## Python helpers

Each helper is a small **ArcGIS API for Python** script bundled as a
self-contained PyInstaller `.exe` (currently `--onefile`, ~91 MB each — see
[Distribution size](#distribution-size) for a `--onedir` proposal). The
Java engine `Runtime.exec`s these at the right moments.

| Helper                                 | Purpose                                       |
|----------------------------------------|-----------------------------------------------|
| `Folder.exe`                           | Create org folder.                            |
| `DeleteAllFolder.exe`                  | Bulk-delete folders.                          |
| `DeleteFoldersAndItems.py`             | Delete folders + items (used by tests).       |
| `DeleteItemsAll.exe`                   | Bulk-delete items.                            |
| `MoveItem.exe`                         | Move items between folders.                   |
| `DeleteTileLayers.exe`                 | Cleanup tile layers.                          |
| `DeleteSceneLayers.py`                 | Cleanup scene layers.                         |
| `SceneLayerCreation.exe`               | Publish the SLPK samples as scene services.   |
| `SceneLayerFromRoot.py`                | Scene-layer publishing variant.               |
| `ValidateDataStore.exe`                | Datastoreutils-style health probe.            |
| `ValidateObjectStore.exe`              | Object store health probe (Enterprise).       |
| `ValidateObjectStorek8s.exe`           | Object store health probe (Kubernetes).       |
| `Projectversion.exe`                   | Reports portal/server version.                |
| `servicesCreatefolder.py`              | Service-folder creation utility.              |

Source `.py` files and `*.spec` files live next to the built exes under
`Enterprise Deployment Check\src\com\automation\PythonAPI\`.

---

## Configuration reference (`config.properties`)

Single source of truth. Located at `Input\config.properties` next to the
executable. Generated/edited by both the legacy Swing UI and the new WPF UI.
Keys are case-sensitive.

### Portal

| Key                | Values                  | Meaning                                |
|--------------------|-------------------------|----------------------------------------|
| `PortalUrl`        | URL                     | Full portal URL (with `/portal`).      |
| `PortalUserName`   | string                  | Portal admin user.                     |
| `PortalPassword`   | string                  | Portal admin password (stored plain).  |
| `Browser`          | `Chrome`                | Browser to drive (Chrome only today).  |
| `Kubernetes`       | `Yes` / `No`            | Targets ArcGIS Enterprise on K8s.      |

### Servers (1–10)

For each slot N in `1..10`:

| Key                       | Values                                              |
|---------------------------|-----------------------------------------------------|
| `ServerUrl{N}`            | URL of the federated server (or blank).             |
| `ServerAdminUsername{N}`  | Server admin user.                                  |
| `ServerAdminPassword{N}`  | Server admin password.                              |
| `ServerRole{N}`           | `Hosting Server` · `GIS Server` · `Image Server` …  |
| `Federated{N}`            | `Yes` / `No` — is this slot a federated server?     |

### Test toggles (all `Yes` / `No`)

| Group             | Keys                                                                     |
|-------------------|--------------------------------------------------------------------------|
| Global            | `RunAllTest`                                                             |
| Login             | `LoginPortal` · `LoginManager` · `LoginServer`                           |
| Validation        | `ValidateServerRole` · `ValidateDataStores` · `ValidateOrganization`     |
| Create content    | `CreateFeatureLayer` · `CreateTileLayer` · `CreateSceneLayer` · `CreateMap` · `CreateDashboard` · `CreateStoryMap` · `CreateWebAppBuilder` · `CreateExperienceBuilderApp` · `CreateMember`                                              |

When `RunAllTest=Yes`, the `TestSelectionRules` cascade in `EDC.Core.Tests`
ticks every individual flag.

#### First-launch defaults

The shipped `config.properties.shippable` ships with every test flag set
to `No` (nothing pre-selected) and all server slots blank, so the first
run on a fresh machine starts from a clean slate. The user enables the
tests they want via the WPF *Tests to run* pane — either by ticking
individual checkboxes or by ticking *Run All Tests*, which cascades and
ticks every individual flag for them.

---

## Test catalogue

| Scenario class                              | What it does                                        |
|---------------------------------------------|-----------------------------------------------------|
| `Login_To_Portal_TestScenario`              | Sign in to the Portal UI; assert landing page.      |
| `Login_To_ServerManager_TestScenario`       | Sign in to Server Manager for each federated server.|
| `Login_To_ServerAdmin_TestScenario`         | Sign in to Server Admin Directory for each server.  |
| `Organization_TestScenario`                 | Reads org settings, members count, federation.      |
| `Groups_TestScenario`                       | Create / list / delete a group.                     |
| `Content_Functionality_TestScenario`        | Item create / move / delete via Python helpers.     |
| `CreateScene_TestScenario`                  | Publish the bundled SLPK as a scene layer.          |
| `Map_Functionality_TestScenario`            | Create a web map and add operational layers.        |
| `CreateDashboard_Functionality`             | Create a dashboard from a web map.                  |
| `StoryMap_Functionality`                    | Create a basic story map.                           |
| `ExperienceBuilder_Functionality_TestScenario` | Create an Experience Builder app.                |
| `WebAppBuilder_Functionality_TestScenario`  | Create a Web AppBuilder app.                        |

---

## Building from source

### .NET WPF UI

```pwsh
cd EDC.Wpf.Solution
dotnet build EDC.sln -c Debug
dotnet test  EDC.Wpf.Tests
dotnet run   --project EDC.Wpf
```

### Java engine

```pwsh
cd "Enterprise Deployment Check"
mvn -DskipTests package
# Output: target\Enterprise_Deployment_Check-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

### Python helpers

Each `*.py` has a sibling `*.spec` for PyInstaller:

```pwsh
cd "Enterprise Deployment Check\src\com\automation\PythonAPI"
pip install pyinstaller arcgis
pyinstaller Folder.spec      # → dist\Folder.exe
# Repeat for each .spec, or switch to --onedir mode (see "Distribution size").
```

---

## Packaging & distribution

### Publish the WPF EXE

```pwsh
cd EDC.Wpf.Solution
dotnet publish EDC.Wpf\EDC.Wpf.csproj `
  -c Release -r win-x64 --self-contained true `
  -p:PublishSingleFile=true `
  -p:IncludeNativeLibrariesForSelfExtract=true `
  -p:EnableCompressionInSingleFile=true `
  --nologo
```

After publish, `bin\Release\net8.0-windows\win-x64\publish\` holds:

```
publish\
├── Enterprise Deployment Check.exe  ← self-contained single-file (~158 MB)
├── Input\                           ← copied from ..\..\Enterprise Deployment Check\Input
├── PythonAPI\                       ← copied PyInstaller helpers (*.exe)
├── engine\                          ← drop the fat JAR here as `edc.jar`
└── runtime\                         ← optional: Adoptium Temurin JRE 17/21 with bin\java.exe
```

The `EdcZipPublish` MSBuild target then packs the whole folder into
`bin\Release\Enterprise Deployment Check.zip`.

### Finish a redistributable build

1. `mvn package` the Java fat JAR; copy/rename it to `publish\engine\edc.jar`.
2. (Optional) Extract an Adoptium Temurin JRE so that
   `publish\runtime\bin\java.exe` exists. If absent, `JreLocator` falls back
   to `JAVA_HOME` / `PATH`.
3. Stage a clean shippable zip:
   ```pwsh
   $stage = "$env:TEMP\EDC-Ship\Enterprise Deployment Check"
   Remove-Item "$env:TEMP\EDC-Ship" -Recurse -Force -ErrorAction SilentlyContinue
   New-Item -ItemType Directory -Path "$stage\engine","$stage\Input" -Force | Out-Null
   Copy-Item .\EDC.Wpf\bin\Release\net8.0-windows\win-x64\publish\"Enterprise Deployment Check.exe" $stage -Force
   Copy-Item ..\"Enterprise Deployment Check"\target\Enterprise_Deployment_Check-0.0.1-SNAPSHOT-jar-with-dependencies.jar "$stage\engine\edc.jar" -Force
   Get-ChildItem ..\"Enterprise Deployment Check"\Input -File | Where-Object Name -ne 'config.properties' | Copy-Item -Destination "$stage\Input\" -Force
   Copy-Item ..\"Enterprise Deployment Check"\Input\config.properties.shippable "$stage\Input\config.properties" -Force
   Add-Type -AssemblyName System.IO.Compression.FileSystem
   [System.IO.Compression.ZipFile]::CreateFromDirectory("$env:TEMP\EDC-Ship", "C:\EDCBUilds\Enterprise Deployment Check.zip", [System.IO.Compression.CompressionLevel]::Optimal, $false)
   ```
4. **Publish the zip** to the shared release location:
   `\\esri.com\psdata\PSProductsQA\EDC\Enterprise Deployment Check.zip`
5. Users grab it from that share, extract, and double-click
   `Enterprise Deployment Check.exe` (UAC elevates).

> Note the `config.properties.shippable` template ships pristine values
> (all test flags `No`, all `Federated*=No`, no credentials, no
> `ServerRole`) so the first run on a fresh machine starts blank — no
> carry-over from the dev box.

> The zip is **not** committed to git — it's distributed via the network
> share above. See `.gitignore` for the exact exclusion patterns.

### Inner-loop redeploy (development)

```pwsh
$exe = "C:\EDCBUilds\EDC-v1.0.0-win-x64_2\Enterprise Deployment Check.exe"
Get-Process | Where-Object Path -eq $exe | Stop-Process -Force -ErrorAction SilentlyContinue
Start-Sleep -Seconds 1
dotnet publish EDC.Wpf\EDC.Wpf.csproj -c Release -r win-x64 --self-contained true `
  -p:PublishSingleFile=true -p:IncludeNativeLibrariesForSelfExtract=true --nologo
Copy-Item "EDC.Wpf\bin\Release\net8.0-windows\win-x64\publish\Enterprise Deployment Check.exe" `
  $exe -Force
Start-Process $exe
```

---

## Distribution size

Approximate breakdown of the shipping zip (current build):

| Component                          | Size      | Notes                                                |
|------------------------------------|-----------|------------------------------------------------------|
| `engine\edc.jar`                   | ~1.07 GB  | Java fat JAR (Selenium + driver mgmt + …)            |
| `Input\` (helpers + sample data)   | ~830 MB   | 7× PyInstaller `.exe` (~91 MB ea) + `LosAngeles.vtpk` + SLPKs |
| `Enterprise Deployment Check.exe`  | ~158 MB   | .NET 8 self-contained single-file, natives embedded  |
| **Zip total**                      | ~1.87 GB  | DEFLATE via `System.IO.Compression.ZipFile`          |

Levers if you need a slimmer distribution (ranked by ROI):

1. **PyInstaller `--onedir`** for the Python helpers (one shared Python
   runtime across all 7 scripts) — potential ~500 MB saving.
2. **Shrink/thin the JAR** (ProGuard / R8 / thin-jar + `lib/`; drop unused
   browser drivers and natives) — potential 100–600 MB.
3. **`jlink`-stripped JRE** with only the modules `edc.jar` uses — ~70 MB.
4. **`Enterprise Deployment Check.exe` tweaks** in `EDC.Wpf.csproj` — ~10–15 MB. Drop
   `IncludeNativeLibrariesForSelfExtract` to slim by ~85 MB at the cost of a
   one-time native-lib extraction to `%TEMP%\.net\…` on first launch.
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
   smaller; most payload is already compressed).

---

## Running and reading results

### Logs

- Live in-app log — *Results Log* pane, grouped per test case, colorised
  by level (Pass / Fail / Skip / Warn / Heading / Step / Info).
- Right-click → *Copy all* / *Clear log*. Toolbar → *Copy log*.

### Reports

`EnterpriseDeploymentCheckResults\YYYY-MM-DD\`:

```
YYYY-MM-DD\
├── Logs\          EnterpriseDeploymentCheckLogs_<timestamp>.txt   (raw stdout)
├── Reports\       ExtentReport.html                               (rich, browsable)
└── Screenshots\   <test>_<timestamp>.png                          (failure points)
```

The *Reports* group on the Home tab and the *Reports* backstage page both
let you jump straight into today's report folder.

---

## Theming & UI conventions

For anyone touching the WPF code:

- **Never** hard-code colors in views. Use
  `{DynamicResource TextPrimaryBrush}`, `…SecondaryBrush`,
  `BorderSubtleBrush`, `WindowBackgroundBrush`, `PanelBackgroundBrush`,
  `CardBackgroundBrush`, `BrandBlueBrush`, `StatusPass/Fail/SkipTextBrush`,
  etc. Add new tokens to **both** `Dark.xaml` and `Light.xaml`.
- **Never** rely on `IValueConverter` to return a brush for theme-sensitive
  text. Converters cache the returned brush at convert time and don't refresh
  on theme swap. Use a `Style` + `DataTrigger` + `DynamicResource` instead
  (see `LogMessageText` in `Views/ResultsLogPane.xaml`).
- **Never** put theme-sensitive brushes at the top of `App.xaml` (outside
  the merged dictionaries) — the theme swap only re-merges
  `Themes/Dark.xaml` / `Themes/Light.xaml`.

Font scale (in `Themes/ArcProPalette.xaml`):

```
FontSize.Body      = 13
FontSize.SubTitle  = 15
FontSize.Title     = 18
```

---

## Logging

- The Java engine writes a single raw stream to stdout. The WPF parser
  (`EDC.Core.Logging.LogStreamParser`) classifies each line into a
  `LogEntry { Timestamp, Level, Message }` and groups consecutive entries
  by test case (`TestCaseLogGroup`).
- A parallel **Serilog** file sink in the WPF host writes a structured log
  under `%LOCALAPPDATA%\EDC\logs\`.
- The TestNG listener also persists a raw txt log under the date-stamped
  results folder for audit.

---

## Troubleshooting

| Symptom                                        | Fix                                                            |
|------------------------------------------------|----------------------------------------------------------------|
| EDC.exe doesn't start                          | Accept the UAC prompt; check Event Viewer → Application logs.  |
| `java.exe not found`                           | `runtime\bin\java.exe` is missing. Extract a Temurin JRE there.|
| `edc.jar not found`                            | `engine\edc.jar` is missing. Run `mvn package` and copy it.    |
| Chrome version mismatch                        | Selenium 4.27 uses Selenium Manager — clear `~\.cache\selenium`. |
| Caption / log text invisible after theme swap  | A brush is hard-coded or returned via a converter; see *Theming*. |
| HTTPS handshake failures against portal        | Trust the cert in the Windows store or use a CA-signed cert.    |
| Tests stop mid-run                             | Inspect `Logs\…txt` next to the report; usually a portal timeout.|
| Python helper exits 1                          | `Input\config.properties` mismatch; re-save from the UI.        |

---

## Contributing

1. Open an issue using the **New issue template** in `.github\ISSUE_TEMPLATE\`.
2. Follow the wiki:
   - [Creating a New EDC Test Issue](https://github.com/EsriPS/EDC/wiki/Creating-a-New-EDC-Test-Issue)
   - [GitHub Projects Columns & Views](https://github.com/EsriPS/EDC/wiki/GitHub-Projects-Columns-&-Views)
   - [Communication and Workflow](https://github.com/EsriPS/EDC/wiki/Communication-and-Workflow)
3. For WPF changes:
   - Keep `EDC.Core` UI-free.
   - Run `dotnet test EDC.Wpf.Tests` before opening a PR.
   - Honour the [theming conventions](#theming--ui-conventions).
4. For Java changes:
   - Run `mvn -DskipTests package`; smoke-test against a real portal.
   - Add a new Page Object if the test reaches into a new portal page.
   - Wire the new scenario into `Input\TestNG.xml` and add a config flag in
     `EdcConfig` + `TestSelectionRules`.
5. For Python helpers:
   - Update the `.spec`. Prefer `--onedir` for new helpers (smaller zip).

---

## Repository links

- **Wiki:** https://github.com/EsriPS/EDC/wiki
- **New-issue template:** [.github/ISSUE_TEMPLATE/new-issue-template.md](.github/ISSUE_TEMPLATE/new-issue-template.md)
- **WPF UI readme:** [EDC.Wpf.Solution/README.md](EDC.Wpf.Solution/README.md)
- **Java engine source:** [Enterprise Deployment Check/src](Enterprise%20Deployment%20Check/src)
- **Python helpers:** [Enterprise Deployment Check/src/com/automation/PythonAPI](Enterprise%20Deployment%20Check/src/com/automation/PythonAPI)

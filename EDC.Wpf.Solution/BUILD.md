# Building the shippable `Enterprise Deployment Check.zip`

This document is the **end-to-end recipe** used to produce
`C:\EDCBUilds\Enterprise Deployment Check.zip` — the single distributable
archive a customer can extract and run with **no prerequisites**:

* **no .NET install** — the WPF EXE is self-contained
* **no Java install** — Adoptium Temurin 17 JRE is bundled under `runtime\`
* **no Python install** — the helpers under `PythonAPI\` are PyInstaller
  one-file EXEs

> Target machine assumption: Windows 10/11 x64. The archive is fully
> self-contained: bundled Adoptium Temurin 17 JRE, .NET 8 self-contained
> WPF exe, fat Java engine jar, PyInstaller helper EXEs, and all `Input/`
> assets.

> **TL;DR — want it automated?** Run [`tools\Build-Shippable.ps1`](../tools/Build-Shippable.ps1)
> from the repo root. It does steps 3–6 below and emits
> `C:\EDCBUilds\Enterprise Deployment Check.zip`. The manual recipe below is
> kept so you can run individual steps when debugging a packaging issue.

---

## 1. What ends up in the zip

```
Enterprise Deployment Check.zip
└── Enterprise Deployment Check\
    ├── Enterprise Deployment Check.exe   ← .NET 8 WPF, single-file, self-contained (~70 MB)
    ├── README.txt                         ← End-user guide (GUI + headless CLI)
    ├── engine\
    │   └── edc.jar                       ← Maven fat jar, TestNG + Selenium 4.35 + ExtentReports
    ├── runtime\                          ← Eclipse Temurin 17 JRE (Windows x64)
    │   ├── bin\java.exe
    │   ├── lib\
    │   └── …
    ├── PythonAPI\                        ← PyInstaller-compiled helpers (no Python needed)
    │   ├── DeleteAllFolder.exe
    │   ├── … (8 helper EXEs total)
    └── Input\
        ├── config.properties             ← sourced from config.properties.shippable (blank creds)
        ├── ChateauFare.slpk
        ├── ChateauFare_Large.slpk
        ├── LosAngeles.vtpk
        ├── SimpleMultiPatch.slpk
        ├── TestNG.xml
        └── Enterprise Deployment Check - Help Doc.pdf
```

`JreLocator` resolves `java.exe` in this order, so the bundled JRE is
always picked up first:

1. `runtime\bin\java.exe` next to the exe (the layout above) ← **preferred**
2. `JAVA_HOME` environment variable
3. system `PATH`

The Java engine resolves Python helper EXEs from `PythonAPI\` next to the
exe (and falls back to `Input\` for legacy layouts), so a customer with no
Python installed can still run every test — the helpers are self-contained
binaries produced by PyInstaller.

---

## 2. Source-tree layout

| Source path                                                          | Role                                                |
|----------------------------------------------------------------------|-----------------------------------------------------|
| `C:\EDC-main\EDC-main\EDC.Wpf.Solution\`                             | .NET 8 WPF solution (the new UI)                    |
| `C:\EDC-main\EDC-main\Enterprise Deployment Check\`                  | Legacy Java engine + `Input\` assets                |
| `C:\Enterprise Deployment Check\Enterprise Deployment Check\`        | "Deployed" copy used as the staging source for the zip |
| `C:\EDCBUilds\Enterprise Deployment Check.zip`                       | Final shippable archive                             |

---

## 3. Build the Java engine (`engine\edc.jar`)

The Maven build emits a fat jar with all transitive deps inlined.

```pwsh
cd "C:\EDC-main\EDC-main\Enterprise Deployment Check"
mvn -B -q clean package -DskipTests
```

- `-DskipTests` is intentional. The Surefire config references
  `${File}.xml` (a TestNG suite chosen at runtime), so unit-test
  invocation through Maven fails with *"Suite file …${File}.xml is not a
  valid file"*. The jar itself builds cleanly.
- Output: `target\Enterprise_Deployment_Check-0.0.1-SNAPSHOT-jar-with-dependencies.jar`
  (~1.04 GB — large because Selenium + ArcGIS Runtime + Apache POI + …).

Copy to the deploy location, renaming to `edc.jar`:

```pwsh
Copy-Item `
  "C:\EDC-main\EDC-main\Enterprise Deployment Check\target\Enterprise_Deployment_Check-0.0.1-SNAPSHOT-jar-with-dependencies.jar" `
  "C:\Enterprise Deployment Check\Enterprise Deployment Check\engine\edc.jar" -Force
```

---

## 4. Build the WPF exe

Single-file, self-contained, compressed:

```pwsh
cd C:\EDC-main\EDC-main\EDC.Wpf.Solution

Stop-Process -Name 'Enterprise Deployment Check' -Force -ErrorAction SilentlyContinue

dotnet publish .\EDC.Wpf\EDC.Wpf.csproj `
  -c Release -r win-x64 --self-contained true `
  -p:PublishSingleFile=true `
  -p:IncludeNativeLibrariesForSelfExtract=true `
  -p:EnableCompressionInSingleFile=true `
  --nologo
```

Publish output: `EDC.Wpf\bin\Release\net8.0-windows\win-x64\publish\Enterprise Deployment Check.exe`
(~70 MB). Copy it over the deployed copy (retry once after killing the
running process if it's locked):

```pwsh
$src = 'C:\EDC-main\EDC-main\EDC.Wpf.Solution\EDC.Wpf\bin\Release\net8.0-windows\win-x64\publish\Enterprise Deployment Check.exe'
$dst = 'C:\Enterprise Deployment Check\Enterprise Deployment Check\Enterprise Deployment Check.exe'
try { Copy-Item $src $dst -Force }
catch {
    Stop-Process -Name 'Enterprise Deployment Check' -Force -ErrorAction SilentlyContinue
    Start-Sleep -Milliseconds 500
    Copy-Item $src $dst -Force
}
```

> `PublishTrimmed` is **not** enabled — WPF + XAML reflection is not
> trim-safe and the app silently breaks when trimmed.

---

## 5. Bundle the JRE (`runtime\`)

Eclipse Temurin 17 JRE for Windows x64 (Eclipse Public License 2.0,
redistributable). This is a one-time setup; reuse the existing `runtime\`
folder on subsequent rebuilds.

```pwsh
$ErrorActionPreference = 'Stop'
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12

# Temurin 17 GA JRE, Windows x64, HotSpot
$url = 'https://api.adoptium.net/v3/binary/latest/17/ga/windows/x64/jre/hotspot/normal/eclipse?project=jdk'
$tmp = 'C:\EDCBUilds\jre-temp'
Remove-Item $tmp -Recurse -Force -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Path $tmp | Out-Null

Invoke-WebRequest -Uri $url -OutFile "$tmp\temurin17-jre.zip" -UseBasicParsing
Expand-Archive "$tmp\temurin17-jre.zip" -DestinationPath "$tmp\extracted" -Force

# Archive contains a single top-level folder like "jdk-17.0.19+10-jre".
$jreRoot = (Get-ChildItem "$tmp\extracted" -Directory | Select-Object -First 1).FullName

# Confirm it runs
& "$jreRoot\bin\java.exe" -version 2>&1

# Replace the deployed runtime folder with the JRE contents (children, not the parent).
$deployRuntime = 'C:\Enterprise Deployment Check\Enterprise Deployment Check\runtime'
Remove-Item $deployRuntime -Recurse -Force -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Path $deployRuntime -Force | Out-Null
Copy-Item "$jreRoot\*" $deployRuntime -Recurse -Force

# Verify
Test-Path "$deployRuntime\bin\java.exe"   # → True

Remove-Item $tmp -Recurse -Force
```

After this step, `C:\Enterprise Deployment Check\Enterprise Deployment Check\runtime\bin\java.exe`
exists and the WPF exe finds it automatically.

---

## 6. Stage and pack the shippable zip

Use a temp stage so we get a clean tree (no `Logs\`,
`EnterpriseDeploymentCheckResults\`, or developer `config.properties`):

```pwsh
$ErrorActionPreference = 'Stop'

$stage = "$env:TEMP\EDC-Ship-JRE"
Remove-Item $stage -Recurse -Force -ErrorAction SilentlyContinue
New-Item -ItemType Directory -Path "$stage\Enterprise Deployment Check\engine" -Force | Out-Null
New-Item -ItemType Directory -Path "$stage\Enterprise Deployment Check\Input"  -Force | Out-Null

# exe + jar + runtime (recurse-copy the bundled JRE)
Copy-Item 'C:\Enterprise Deployment Check\Enterprise Deployment Check\Enterprise Deployment Check.exe' `
          "$stage\Enterprise Deployment Check\" -Force
Copy-Item 'C:\Enterprise Deployment Check\Enterprise Deployment Check\engine\edc.jar' `
          "$stage\Enterprise Deployment Check\engine\edc.jar" -Force
Copy-Item 'C:\Enterprise Deployment Check\Enterprise Deployment Check\runtime' `
          "$stage\Enterprise Deployment Check\runtime" -Recurse -Force

# Input assets — skip the developer config files
Get-ChildItem 'C:\EDC-main\EDC-main\Enterprise Deployment Check\Input' -File `
  | Where-Object { $_.Name -ne 'config.properties' -and $_.Name -ne 'config.properties.shippable' } `
  | ForEach-Object { Copy-Item $_.FullName "$stage\Enterprise Deployment Check\Input\" -Force }

# Promote the shippable template to config.properties for the customer
Copy-Item 'C:\EDC-main\EDC-main\Enterprise Deployment Check\Input\config.properties.shippable' `
          "$stage\Enterprise Deployment Check\Input\config.properties" -Force

# Compress
$zipOut = 'C:\EDCBUilds\Enterprise Deployment Check.zip'
Remove-Item $zipOut -Force -ErrorAction SilentlyContinue
Add-Type -AssemblyName System.IO.Compression.FileSystem
[System.IO.Compression.ZipFile]::CreateFromDirectory(
    $stage,
    $zipOut,
    [System.IO.Compression.CompressionLevel]::Optimal,
    $false)   # do NOT include the stage folder itself as a top-level entry

Remove-Item $stage -Recurse -Force
```

Expected output (last known good build):

| Artifact                                                                | Size      | Notes                              |
|-------------------------------------------------------------------------|-----------|------------------------------------|
| `Enterprise Deployment Check.exe`                                       | ~70 MB    | .NET 8 self-contained single-file  |
| `engine\edc.jar`                                                        | ~1.04 GB  | Maven fat jar                      |
| `runtime\` (Temurin 17.0.19+10)                                         | ~125 MB   | bundled JRE                        |
| `Input\`                                                                | ~731 MB   | SLPK / VTPK / Help PDF / TestNG.xml |
| **`Enterprise Deployment Check.zip`**                                   | **~1.92 GB** | DEFLATE, ~335 entries            |

---

## 7. Customer install (what end users do)

### GUI mode

1. Unzip `Enterprise Deployment Check.zip` anywhere with write access.
2. Double-click `Enterprise Deployment Check.exe`. Windows requests
   elevation (the WPF manifest specifies `requireAdministrator`, matching
   the legacy launcher).
3. Fill in **Portal URL**, **Portal Admin Username** / **Password**, pick
   test cases on the **Tests** tab, click **Run**.
4. HTML reports land in `EnterpriseDeploymentCheckResults\<yyyy-MM-dd>\`
   next to the exe.

No Java install, no Python install, no .NET install, no extra setup.

### Headless / CI-CD mode

The shippable zip is also a complete CI/CD payload — the bundled JRE and
PythonAPI helpers mean a build agent needs nothing pre-installed. Customers
run the engine directly with the bundled `java.exe`:

```pwsh
# From the unzipped folder:
.\runtime\bin\java.exe -jar .\engine\edc.jar `
    --headless `
    --config  .\Input\config.properties `
    --results .\EnterpriseDeploymentCheckResults `
    --tests   LoginPortal,CreateFeatureLayer,CreateMap `
    [--fail-on-skip]
```

Exit codes: `0` pass, `1` failure, `2` skipped + `--fail-on-skip`, `3` config
error, `4` uncaught error. Full reference (test keys, sample
`azure-pipelines.yml`, `Jenkinsfile`) lives in
[`Enterprise Deployment Check/ci/README.md`](../Enterprise%20Deployment%20Check/ci/README.md).

The same instructions are reproduced in `README.txt` inside the shippable
zip so an end user with the zip but no repo access can still discover the
CLI.

---

## 8. Troubleshooting the build

| Symptom                                                                  | Cause / fix |
|--------------------------------------------------------------------------|-------------|
| `Copy-Item` fails on `Enterprise Deployment Check.exe` with "in use"     | The app is still running. The recipe `Stop-Process -Name 'Enterprise Deployment Check'` and retries; if it persists, kill via Task Manager. |
| `mvn package` fails with `Suite file ...${File}.xml is not a valid file` | Surefire test phase tries to run TestNG with an unresolved placeholder. Use `-DskipTests`; the jar still builds correctly. |
| `pageLoadTimeout(int, TimeUnit)` / `implicitlyWait(int, TimeUnit)` compile errors | Selenium 4.x removed the int/TimeUnit overloads. Use `Duration.ofSeconds(...)`. |
| `java -version` prints to stderr and `$ErrorActionPreference='Stop'` aborts the script | PowerShell treats native-tool stderr as a terminating error. Wrap the call with `$ErrorActionPreference='Continue'` or pipe with `2>&1 | Out-String`. |
| Zip ends up at ~3 GB (double-sized)                                       | The "update existing zip" path was used instead of `CreateFromDirectory` on a clean stage. Always delete `$zipOut` first and re-pack from a clean `$stage`. |
| Customer reports *"Java not found"* on first launch                       | The `runtime\` folder didn't ship inside the zip. Re-verify step 5 produced `runtime\bin\java.exe` in the stage tree before step 6. |

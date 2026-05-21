<#
.SYNOPSIS
    Builds the shippable Enterprise Deployment Check.zip end-to-end.

.DESCRIPTION
    Implements the recipe documented in EDC.Wpf.Solution/BUILD.md:

      1. Builds the Java fat jar with Maven (skips tests).
      2. Publishes the WPF EXE as .NET 8 self-contained single-file (win-x64).
      3. Optionally downloads and bundles an Eclipse Temurin 17 JRE
         (skipped if -RuntimeSource is supplied or a previously bundled
         runtime\ folder already exists at the staging target).
      4. Stages a clean tree containing:
              Enterprise Deployment Check.exe
              README.txt                          (from tools\shippable-README.txt)
              engine\edc.jar
              runtime\                            (bundled JRE)
              Input\*.exe                         (PyInstaller helpers)
              Input\config.properties             (from config.properties.shippable)
              Input\TestNG.xml + *.slpk/.vtpk + Help PDF
      5. Compresses the stage into a single .zip with DEFLATE.

    End users who unzip the archive need no Java, no Python, and no .NET
    install.

.PARAMETER OutputZip
    Final path of the .zip to produce.
    Default: C:\EDCBUilds\Enterprise Deployment Check.zip

.PARAMETER RuntimeSource
    Path to an already-extracted Temurin 17 JRE directory (one that
    contains bin\java.exe). If omitted, the script downloads the
    current GA Temurin 17 JRE from Adoptium.

.PARAMETER ExternalInput
    Optional canonical Input folder containing the SLPK/VTPK assets and
    extra Python helper EXEs (DeleteAllFolder.exe, etc.) that aren't
    committed to the repo. Defaults to
    C:\NET_PY_JA\Enterprise Deployment Check\Enterprise Deployment Check\Input
    (matches the in-tree EDC.Wpf.csproj default). Pass an empty string
    to skip the overlay copy.

.PARAMETER SkipJavaBuild
    Reuse an existing target\*-jar-with-dependencies.jar instead of
    rebuilding it. Useful when iterating on packaging.

.PARAMETER SkipDotnetBuild
    Reuse an existing dotnet publish output instead of republishing.

.EXAMPLE
    pwsh .\tools\Build-Shippable.ps1

.EXAMPLE
    # Reuse a previously downloaded JRE and skip the slow Maven step
    pwsh .\tools\Build-Shippable.ps1 `
        -RuntimeSource 'C:\Tools\jdk-17.0.19+10-jre' `
        -SkipJavaBuild
#>

[CmdletBinding()]
param(
    [string] $OutputZip      = 'C:\EDCBUilds\Enterprise Deployment Check.zip',
    [string] $RuntimeSource  = '',
    [string] $ExternalInput  = 'C:\NET_PY_JA\Enterprise Deployment Check\Enterprise Deployment Check\Input',
    [switch] $SkipJavaBuild,
    [switch] $SkipDotnetBuild
)

$ErrorActionPreference = 'Stop'
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12

# --------------------------------------------------------------------
# Resolve repo-relative paths (script lives in <repo>\tools\)
# --------------------------------------------------------------------
$RepoRoot   = Resolve-Path (Join-Path $PSScriptRoot '..')
$EngineDir  = Join-Path $RepoRoot 'Enterprise Deployment Check'
$WpfSlnDir  = Join-Path $RepoRoot 'EDC.Wpf.Solution'
$WpfCsproj  = Join-Path $WpfSlnDir 'EDC.Wpf\EDC.Wpf.csproj'
$JarSrc     = Join-Path $EngineDir 'target\Enterprise_Deployment_Check-0.0.1-SNAPSHOT-jar-with-dependencies.jar'
$PublishDir = Join-Path $WpfSlnDir 'EDC.Wpf\bin\Release\net8.0-windows\win-x64\publish'
$ExeSrc     = Join-Path $PublishDir 'Enterprise Deployment Check.exe'
$ReadmeSrc  = Join-Path $PSScriptRoot 'shippable-README.txt'
$ConfigTmpl = Join-Path $EngineDir 'Input\config.properties.shippable'
$PyApiDir   = Join-Path $EngineDir 'src\com\automation\PythonAPI'

function Step([string]$msg) {
    Write-Host ''
    Write-Host "==> $msg" -ForegroundColor Cyan
}

# --------------------------------------------------------------------
# 1. Build engine jar
# --------------------------------------------------------------------
if (-not $SkipJavaBuild) {
    Step "Building Java fat jar (Maven)..."
    Push-Location $EngineDir
    try {
        & mvn -B -DskipTests clean package
        if ($LASTEXITCODE -ne 0) { throw "Maven build failed (exit $LASTEXITCODE)." }
    } finally { Pop-Location }
}
if (-not (Test-Path $JarSrc)) {
    throw "Fat jar not found at $JarSrc. Run without -SkipJavaBuild."
}
Write-Host "    JAR : $JarSrc"

# --------------------------------------------------------------------
# 2. Publish WPF EXE (.NET 8 self-contained single-file)
# --------------------------------------------------------------------
if (-not $SkipDotnetBuild) {
    Step "Publishing WPF EXE (.NET 8, self-contained, single-file)..."
    # Best-effort: stop a running instance so the EXE isn't locked
    Get-Process | Where-Object { $_.Path -eq $ExeSrc } |
        Stop-Process -Force -ErrorAction SilentlyContinue
    Start-Sleep -Milliseconds 300

    Push-Location $WpfSlnDir
    try {
        & dotnet publish $WpfCsproj `
            -c Release -r win-x64 --self-contained true `
            -p:PublishSingleFile=true `
            -p:IncludeNativeLibrariesForSelfExtract=true `
            -p:EnableCompressionInSingleFile=true `
            --nologo
        if ($LASTEXITCODE -ne 0) { throw "dotnet publish failed (exit $LASTEXITCODE)." }
    } finally { Pop-Location }
}
if (-not (Test-Path $ExeSrc)) {
    throw "Published EXE not found at $ExeSrc. Run without -SkipDotnetBuild."
}
Write-Host "    EXE : $ExeSrc"

# --------------------------------------------------------------------
# 3. Resolve / download the bundled JRE
# --------------------------------------------------------------------
if (-not $RuntimeSource -or -not (Test-Path (Join-Path $RuntimeSource 'bin\java.exe'))) {
    Step "Downloading Eclipse Temurin 17 GA JRE (Windows x64)..."
    $tmpJre = Join-Path $env:TEMP 'EDC-jre-temp'
    Remove-Item $tmpJre -Recurse -Force -ErrorAction SilentlyContinue
    New-Item -ItemType Directory -Path $tmpJre | Out-Null

    $url = 'https://api.adoptium.net/v3/binary/latest/17/ga/windows/x64/jre/hotspot/normal/eclipse?project=jdk'
    $zipPath = Join-Path $tmpJre 'temurin17-jre.zip'
    Invoke-WebRequest -Uri $url -OutFile $zipPath -UseBasicParsing
    Expand-Archive $zipPath -DestinationPath (Join-Path $tmpJre 'extracted') -Force
    $RuntimeSource = (Get-ChildItem (Join-Path $tmpJre 'extracted') -Directory | Select-Object -First 1).FullName

    # Sanity check. `java -version` writes to stderr, which PowerShell under
    # $ErrorActionPreference='Stop' treats as a terminating error. Temporarily
    # downgrade and check $LASTEXITCODE explicitly.
    $prevPref = $ErrorActionPreference
    $ErrorActionPreference = 'Continue'
    try {
        & (Join-Path $RuntimeSource 'bin\java.exe') -version 2>&1 | Out-Null
    } finally { $ErrorActionPreference = $prevPref }
    if ($LASTEXITCODE -ne 0) { throw "Downloaded JRE failed -version sanity check." }
}
Write-Host "    JRE : $RuntimeSource"

# --------------------------------------------------------------------
# 4. Stage a clean tree
# --------------------------------------------------------------------
Step "Staging shippable tree..."
$Stage = Join-Path $env:TEMP 'EDC-Ship'
Remove-Item $Stage -Recurse -Force -ErrorAction SilentlyContinue
$Root = Join-Path $Stage 'Enterprise Deployment Check'
New-Item -ItemType Directory -Path "$Root\engine"   -Force | Out-Null
New-Item -ItemType Directory -Path "$Root\Input"    -Force | Out-Null
New-Item -ItemType Directory -Path "$Root\runtime"  -Force | Out-Null

# 4a. EXE
Copy-Item $ExeSrc $Root -Force

# 4b. README.txt (end-user guide, including the headless CLI)
if (Test-Path $ReadmeSrc) {
    Copy-Item $ReadmeSrc (Join-Path $Root 'README.txt') -Force
    Write-Host "    + README.txt"
} else {
    Write-Warning "  shippable-README.txt not found at $ReadmeSrc -- zip will ship without it."
}

# 4c. Engine jar
Copy-Item $JarSrc (Join-Path $Root 'engine\edc.jar') -Force

# 4d. JRE  (copy children only -> runtime\bin\java.exe, runtime\lib\, ...)
Copy-Item (Join-Path $RuntimeSource '*') (Join-Path $Root 'runtime') -Recurse -Force

# 4e. Python helpers (in-repo). PythonHelp.java looks for these in Input\,
# so stage them there (NOT under a separate PythonAPI\ folder).
if (Test-Path $PyApiDir) {
    $repoPy = Get-ChildItem $PyApiDir -Filter '*.exe' -File -ErrorAction SilentlyContinue
    if ($repoPy) {
        $repoPy | ForEach-Object {
            Copy-Item $_.FullName (Join-Path $Root 'Input') -Force
        }
        Write-Host "    + Input\*.exe helpers (repo): $($repoPy.Count)"
    }
}

# 4f. Input assets, minus developer config files
Get-ChildItem (Join-Path $EngineDir 'Input') -File `
    | Where-Object { $_.Name -notin @('config.properties', 'config.properties.shippable') } `
    | ForEach-Object { Copy-Item $_.FullName (Join-Path $Root 'Input') -Force }

# Promote the shippable template to config.properties for the customer
Copy-Item $ConfigTmpl (Join-Path $Root 'Input\config.properties') -Force
Write-Host "    + Input\config.properties  (from config.properties.shippable)"

# 4g. Overlay external Input (extra Python EXEs + SLPK/VTPK assets) if present
if ($ExternalInput -and (Test-Path $ExternalInput)) {
    Write-Host "    + Overlaying external Input from $ExternalInput"
    $extras = Get-ChildItem $ExternalInput -Recurse -File
    foreach ($f in $extras) {
        $rel = $f.FullName.Substring($ExternalInput.Length).TrimStart('\','/')
        $dest = Join-Path (Join-Path $Root 'Input') $rel
        $destDir = Split-Path $dest -Parent
        if (-not (Test-Path $destDir)) { New-Item -ItemType Directory -Path $destDir -Force | Out-Null }
        Copy-Item $f.FullName $dest -Force
    }
} elseif ($ExternalInput) {
    Write-Warning "  External Input folder not found: $ExternalInput  (skipping overlay)."
}

# --------------------------------------------------------------------
# 5. Zip
# --------------------------------------------------------------------
Step "Compressing -> $OutputZip"
$OutDir = Split-Path $OutputZip -Parent
if ($OutDir -and -not (Test-Path $OutDir)) { New-Item -ItemType Directory -Path $OutDir -Force | Out-Null }
Remove-Item $OutputZip -Force -ErrorAction SilentlyContinue
Add-Type -AssemblyName System.IO.Compression.FileSystem
[System.IO.Compression.ZipFile]::CreateFromDirectory(
    $Stage,
    $OutputZip,
    [System.IO.Compression.CompressionLevel]::Optimal,
    $false)   # do NOT include the stage folder itself as a top-level entry

# Cleanup
Remove-Item $Stage -Recurse -Force -ErrorAction SilentlyContinue

# --------------------------------------------------------------------
# Done
# --------------------------------------------------------------------
$zip = Get-Item $OutputZip
$mb  = [math]::Round($zip.Length / 1MB, 1)
Write-Host ''
Write-Host "Done." -ForegroundColor Green
Write-Host "  $OutputZip"
Write-Host "  Size : $mb MB"
Write-Host ''
Write-Host "End users:"
Write-Host "  - Unzip anywhere with write access."
Write-Host "  - GUI mode      : double-click 'Enterprise Deployment Check.exe'"
Write-Host "  - Headless mode : .\runtime\bin\java.exe -jar .\engine\edc.jar --help"

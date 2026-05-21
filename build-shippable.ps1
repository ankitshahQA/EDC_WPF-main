$ErrorActionPreference = 'Stop'
$root = "C:\Automation\Git\EDC_WPF-main\EDC_WPF-main"
Set-Location $root

function Step($n, $msg) { Write-Host "`n==[ $n ] $msg ==" -ForegroundColor Cyan }

# --- 1. Maven: build Java fat JAR ---
Step '1/5' 'Maven clean package (Java fat JAR)'
Push-Location "Enterprise Deployment Check"
mvn -B -DskipTests clean package
if ($LASTEXITCODE -ne 0) { Pop-Location; throw "Maven failed (exit $LASTEXITCODE)" }
Pop-Location
$jarSrc = Get-ChildItem "Enterprise Deployment Check\target\*jar-with-dependencies.jar" |
          Select-Object -First 1
if (-not $jarSrc) { throw "Fat JAR not produced." }

# --- 2. dotnet: publish single-file Release exe ---
Step '2/5' 'dotnet publish (Release, win-x64, single-file, self-contained)'
$publishOut = Join-Path $root "EDC.Wpf.Solution\EDC.Wpf\bin\Release\net8.0-windows\win-x64\publish"
if (Test-Path $publishOut) { Remove-Item $publishOut -Recurse -Force }
dotnet publish "EDC.Wpf.Solution\EDC.Wpf\EDC.Wpf.csproj" `
    -c Release -r win-x64 --self-contained true `
    -p:PublishSingleFile=true `
    -p:IncludeNativeLibrariesForSelfExtract=true `
    -p:EnableCompressionInSingleFile=true `
    --nologo
if ($LASTEXITCODE -ne 0) { throw "dotnet publish failed (exit $LASTEXITCODE)" }
$exeSrc = Get-ChildItem "$publishOut\*.exe" | Select-Object -First 1
if (-not $exeSrc) { throw "Published exe not found in $publishOut" }

# --- 3. Stage shippable tree in temp ---
Step '3/5' 'Stage shippable tree'
$stage = Join-Path $env:TEMP "EDC-Ship"
if (Test-Path $stage) { Remove-Item $stage -Recurse -Force }
$app = Join-Path $stage "Enterprise Deployment Check"
New-Item -ItemType Directory -Path "$app\engine"  -Force | Out-Null
New-Item -ItemType Directory -Path "$app\Input"   -Force | Out-Null
New-Item -ItemType Directory -Path "$app\runtime" -Force | Out-Null

Copy-Item $exeSrc.FullName "$app\Enterprise Deployment Check.exe" -Force
Copy-Item $jarSrc.FullName "$app\engine\edc.jar" -Force

# Input assets — sourced from the canonical shipping payload (contains the
# Python helper EXEs, SLPK/VTPK data, Help PDF, TestNG.xml, etc.). Falls back
# to the repo's slim Input\ folder if the canonical source is missing.
$canonicalInput = 'C:\NET_PY_JA\Enterprise Deployment Check\Enterprise Deployment Check\Input'
if (Test-Path $canonicalInput) {
    Write-Host "  using Input source: $canonicalInput"
    Get-ChildItem $canonicalInput -File |
        Where-Object { $_.Name -ne 'config.properties.shippable' } |
        ForEach-Object { Copy-Item $_.FullName "$app\Input\" -Force }
} else {
    Write-Host "  WARNING: canonical Input not found, falling back to repo Input\" -ForegroundColor Yellow
    $inputSrc = "Enterprise Deployment Check\Input"
    Get-ChildItem $inputSrc -File |
        Where-Object { $_.Name -notin @('config.properties','config.properties.shippable') } |
        ForEach-Object { Copy-Item $_.FullName "$app\Input\" -Force }
    $shippableCfg = Join-Path $inputSrc 'config.properties.shippable'
    if (Test-Path $shippableCfg) {
        Copy-Item $shippableCfg "$app\Input\config.properties" -Force
    } elseif (Test-Path (Join-Path $inputSrc 'config.properties')) {
        Copy-Item (Join-Path $inputSrc 'config.properties') "$app\Input\config.properties" -Force
    }
}

# --- 4. Bundle JRE (reuse installed JDK at JAVA_HOME / discovered path) ---
Step '4/5' 'Bundle runtime\ (JRE)'
$jreSrc = $null
if ($env:JAVA_HOME -and (Test-Path (Join-Path $env:JAVA_HOME 'bin\java.exe'))) {
    $jreSrc = $env:JAVA_HOME
} else {
    $candidate = (Get-Command java -ErrorAction SilentlyContinue).Source
    if ($candidate) { $jreSrc = Split-Path (Split-Path $candidate -Parent) -Parent }
}
if (-not $jreSrc) { throw "No JDK/JRE found to bundle (set JAVA_HOME)." }
Write-Host "  using JRE: $jreSrc"
Copy-Item "$jreSrc\*" "$app\runtime\" -Recurse -Force
if (-not (Test-Path "$app\runtime\bin\java.exe")) { throw "runtime\bin\java.exe missing after copy." }

# --- 5. Zip it ---
Step '5/5' 'Compress to shippable zip'
$zipOut = Join-Path $root "Enterprise Deployment Check.zip"
if (Test-Path $zipOut) { Remove-Item $zipOut -Force }
Add-Type -AssemblyName System.IO.Compression.FileSystem
[System.IO.Compression.ZipFile]::CreateFromDirectory(
    $stage, $zipOut,
    [System.IO.Compression.CompressionLevel]::Optimal, $false)

$zipMB = [math]::Round((Get-Item $zipOut).Length / 1MB, 1)
Write-Host "`nDONE." -ForegroundColor Green
Write-Host ("  Output : {0}" -f $zipOut) -ForegroundColor Green
Write-Host ("  Size   : {0} MB" -f $zipMB) -ForegroundColor Green
Write-Host ("  Stage  : {0} (kept for inspection - delete manually if not needed)" -f $stage)

===============================================================================
 Enterprise Deployment Check (EDC)
 Validates ArcGIS Enterprise portal and server deployments
===============================================================================

This archive is fully self-contained. You do NOT need to install Java,
Python, or .NET to use it.

  - .NET 8 runtime          ... bundled inside Enterprise Deployment Check.exe
  - Java 17 (Temurin JRE)   ... bundled under runtime\
  - Python helpers          ... bundled under PythonAPI\ as compiled EXEs

System requirements
-------------------
  - Windows 10 / 11 (x64)
  - Local administrator rights (the EXE elevates via UAC)
  - Network reachability to the ArcGIS Enterprise portal and federated
    servers you want to validate
  - One of: Google Chrome OR Microsoft Edge installed (for the Selenium
    browser tests)

What's in this folder
---------------------
  Enterprise Deployment Check.exe   The WPF user interface
  README.txt                         This file
  engine\edc.jar                     Java test engine
  runtime\bin\java.exe               Bundled JRE (auto-discovered)
  PythonAPI\*.exe                    Bundled Python helpers
  Input\config.properties            Editable test configuration
  Input\TestNG.xml                   TestNG suite definition
  Input\*.slpk / *.vtpk              Sample upload assets used by tests
  Input\Enterprise Deployment Check - Help Doc.pdf    User guide

After the first run, an EnterpriseDeploymentCheckResults\<yyyy-MM-dd>\
folder appears next to the EXE with the Extent HTML report and step logs.

===============================================================================
 OPTION A - Graphical mode (most users)
===============================================================================

  1. Extract this zip anywhere you have write access (avoid Program Files;
     pick e.g. C:\EDC\).
  2. Double-click "Enterprise Deployment Check.exe" and accept the UAC
     prompt.
  3. On the Input Parameters tab, fill in:
        - Portal URL                  https://gis.example.com/portal
        - Portal admin username + password
        - Federated server URL(s)     and Server Admin credentials
        - Tick "Kubernetes" if the deployment is ArcGIS Enterprise on K8s
  4. On the Tests to Run tab, tick the tests you want, or tick
     "Run all tests".
  5. Click the Run button in the ribbon.
  6. Watch the Results Log on the right. To stop early, click Stop --
     the current step finishes, remaining tests are skipped, and the
     "Delete created test data" cleanup runs.
  7. When the run ends, click "Open report folder" (Reports group) to
     browse the HTML report.

===============================================================================
 OPTION B - Headless / CI-CD mode (Azure DevOps, Jenkins, scripts)
===============================================================================

You can run the engine without the UI, using the bundled JRE. Open
PowerShell in the unzipped folder and run:

  .\runtime\bin\java.exe -jar .\engine\edc.jar `
      --headless `
      --config  .\Input\config.properties `
      --results .\EnterpriseDeploymentCheckResults `
      --tests   LoginPortal,CreateFeatureLayer,CreateMap

  -- For a full help screen:
  .\runtime\bin\java.exe -jar .\engine\edc.jar --help

CLI options
-----------
  --headless, -h                 Run without UI (CI/CD mode)
  --config  <path>, -c <path>    Path to config.properties
                                 (default: .\Input\config.properties)
  --results <dir>,  -r <dir>     Root folder for reports/logs
                                 (default: .\EnterpriseDeploymentCheckResults)
  --tests   <list>, -t <list>    Comma-separated test keys, or "all".
                                 Overrides the selection in config.properties.
  --fail-on-skip                 Exit with code 2 if any test was skipped
  --help, -?                     Print this help

Test keys (case-insensitive)
----------------------------
  LoginServer, LoginManager, LoginPortal, ValidateDataStores,
  ValidateServerRole, ValidateOrganization, CreateFeatureLayer,
  CreateTileLayer, CreateMap, CreateWebAppBuilder,
  CreateExperienceBuilderApp, CreateSceneLayer, CreateMember,
  CreateDashboard, CreateStoryMap, or "all"

Exit codes (use these in your CI pipeline)
------------------------------------------
   0   All selected tests passed
   1   At least one test failed
   2   At least one test was skipped AND --fail-on-skip was passed
   3   Config validation error
   4   Uncaught exception during the run

Cooperative stop
----------------
Sending the literal line "STOP" (followed by a newline) to the engine's
standard input requests a clean shutdown: the current test finishes its
current step, remaining selected tests are marked SKIPPED, then the
@AfterSuite cleanup deletes every artifact created during the run. Allow
up to ~5 minutes after sending STOP before force-killing the process.

Sample CI configurations (azure-pipelines.yml and Jenkinsfile) are
available in the source repository under
"Enterprise Deployment Check\ci\".

===============================================================================
 CONFIGURATION (Input\config.properties)
===============================================================================

The file ships pristine: every test toggle = No, every credential blank.
Edit it with any text editor; restart the WPF UI to pick up changes
(or pass --config in headless mode).

Key sections:

  Browser=Chrome|Edge                  Which browser Selenium drives
  Kubernetes=Yes|No                    ArcGIS Enterprise on Kubernetes?

  PortalUrl=...                        Portal base URL
  PortalUserName / PortalPassword      Portal admin credentials

  ServerUrl1..10=                      Federated server base URLs
  ServerAdminUsername1..10=
  ServerAdminPassword1..10=
  Federated1..10=Yes|No                Which slots are active
  ServerRole1..10=                     Expected role per slot
                                       (GIS / Hosting / Image / etc.)

  Test toggles (Yes to enable):
    LoginServer, LoginManager, LoginPortal,
    ValidateDataStores, ValidateServerRole, ValidateOrganization,
    CreateFeatureLayer, CreateTileLayer, CreateMap,
    CreateWebAppBuilder, CreateExperienceBuilderApp,
    CreateSceneLayer, CreateMember, CreateDashboard, CreateStoryMap

  RunAllTest=Yes                       Shortcut: turn on everything

For the canonical field-by-field reference see
"Input\Enterprise Deployment Check - Help Doc.pdf".

===============================================================================
 TROUBLESHOOTING
===============================================================================

"Java not found"
    The bundled runtime\bin\java.exe is missing. Make sure you unzipped
    the entire archive (some Windows extractors silently skip nested
    archives on long paths). Re-extract to a short path like C:\EDC\.

"Cannot find PythonAPI helper ..."
    The PythonAPI\ folder is missing or only partially extracted. Same
    fix as above -- re-extract to a short path.

"Chrome driver / browser version mismatch"
    Selenium auto-downloads matching drivers but needs Internet on first
    run. Update Chrome/Edge to a recent stable version.

"Tests stop after one or two steps with a connection error"
    The WPF host couldn't reach the portal/server URL. Verify firewall,
    DNS, and certificate trust from this machine.

"@AfterSuite cleanup didn't delete created content"
    The graceful-stop window is 5 minutes. If the cleanup is taking
    longer than that (very large portals), invoke it manually:
        .\runtime\bin\java.exe -jar .\engine\edc.jar `
            --headless --tests LoginPortal,CreateMember `
            --config .\Input\config.properties
    then delete leftover items via the portal UI.

===============================================================================
 SUPPORT
===============================================================================

For issues, feature requests, or to file a bug, contact the EDC team
internally. Include:

  - Your config.properties (REDACT credentials before sharing)
  - The full contents of
    EnterpriseDeploymentCheckResults\<date>\Logs\
  - The Extent report HTML from
    EnterpriseDeploymentCheckResults\<date>\Reports\
  - Your ArcGIS Enterprise version + deployment shape (Windows / Linux / K8s)

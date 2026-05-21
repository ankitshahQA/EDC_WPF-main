# CI/CD usage

The Enterprise Deployment Check engine ships with a headless CLI mode that is
suitable for Azure DevOps, Jenkins, GitHub Actions, or any agent that can run
a Java process and read an exit code.

## CLI

```
java -jar Enterprise_Deployment_Check-0.0.1-SNAPSHOT-jar-with-dependencies.jar \
     --headless \
     [--config  <path-to-config.properties>] \
     [--results <output-root>] \
     [--tests   <comma-list>|all] \
     [--fail-on-skip]
```

`java -jar ... true` is the legacy form and is still honoured (equivalent to
`--headless`).

### Options

| Flag              | Alt | Description                                                      |
|-------------------|-----|------------------------------------------------------------------|
| `--headless`      | `-h`| Run without the Swing UI                                         |
| `--config <path>` | `-c`| Path to `config.properties` (default `./Input/config.properties`) |
| `--results <dir>` | `-r`| Root folder for reports/logs (default `./EnterpriseDeploymentCheckResults`) |
| `--tests <list>`  | `-t`| Comma-separated test keys, or `all`. Overrides config selection. |
| `--fail-on-skip`  |     | Exit `2` if any test was skipped                                  |
| `--help`          | `-?`| Print options and exit                                            |

You can also set `EDC_CONFIG` / `EDC_RESULTS_DIR` (env) or
`-Dedc.config=...` / `-Dedc.results.dir=...` (JVM system properties) instead
of the corresponding CLI flags.

### Test keys (for `--tests`)

`LoginServer`, `LoginManager`, `LoginPortal`, `ValidateDataStores`,
`ValidateServerRole`, `ValidateOrganization`, `CreateFeatureLayer`,
`CreateTileLayer`, `CreateMap`, `CreateWebAppBuilder`,
`CreateExperienceBuilderApp`, `CreateSceneLayer`, `CreateMember`,
`CreateDashboard`, `CreateStoryMap`, or `all`.

Keys are case-insensitive. Unknown keys log a warning and are ignored.

### Exit codes

| Code | Meaning                                                |
|------|--------------------------------------------------------|
| `0`  | All selected tests passed                              |
| `1`  | At least one test failed                               |
| `2`  | At least one test was skipped AND `--fail-on-skip` set |
| `3`  | Config validation error (`verifyConfigvalues`)         |
| `4`  | Uncaught exception during the run                      |

Skipped tests are treated as success unless `--fail-on-skip` is supplied, since
the cooperative-stop path also produces SKIPPED entries.

## Sample pipelines

* Azure DevOps — [azure-pipelines.yml](azure-pipelines.yml)
* Jenkins      — [Jenkinsfile](Jenkinsfile)

Both samples build the fat jar with Maven, run the engine against a
pipeline-supplied config, and archive the Extent report + step logs as build
artifacts.

## Per-environment config

Keep a `config.properties` per environment (dev / staging / prod) outside the
repo and inject it as a secure file in the pipeline (Azure DevOps secure
files; Jenkins `withCredentials([file(...)])`), then point the engine at it
with `--config`.

## Cooperative stop in CI

If the CI agent times out or the user cancels the job, send the JVM
`SIGTERM` (Unix) or close its stdin / send a `STOP\n` line (Windows wrapper)
to trigger the same cooperative shutdown the WPF Stop button uses: every
remaining `@Test` is marked SKIPPED and `@AfterSuite` runs
`PythonHelp.DeleteAllFolder()` so no portal artifacts are left behind.
Allow at least 5 minutes after sending stop before force-killing.

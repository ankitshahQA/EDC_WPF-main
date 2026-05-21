using CommunityToolkit.Mvvm.ComponentModel;
using EDC.Core.Models;
using EDC.Core.Tests;

namespace EDC.Wpf.ViewModels;

/// <summary>
/// Wraps the test-selection portion of <see cref="EdcConfig"/> and enforces cascade rules.
/// Listens to the parent config so toggling Kubernetes / RunAll cascades correctly.
/// </summary>
public partial class TestSelectionViewModel : ObservableObject
{
    public EdcConfig Config { get; }

    private bool _suppress;

    public TestSelectionViewModel(EdcConfig config)
    {
        Config = config;
        Config.PropertyChanged += OnConfigChanged;
    }

    private void OnConfigChanged(object? sender, System.ComponentModel.PropertyChangedEventArgs e)
    {
        if (_suppress) return;
        try
        {
            _suppress = true;
            switch (e.PropertyName)
            {
                case nameof(EdcConfig.RunAllTest):
                    if (Config.RunAllTest) TestSelectionRules.ApplyRunAll(Config);
                    else                   TestSelectionRules.ClearAll(Config);
                    break;
                case nameof(EdcConfig.Kubernetes):
                    if (Config.Kubernetes)
                    {
                        TestSelectionRules.ApplyKubernetesRules(Config);
                    }
                    else if (Config.RunAllTest)
                    {
                        // Re-tick the K8s-incompatible tests when the user backs out of
                        // Kubernetes while "Run all tests" is still on, otherwise they'd
                        // stay unchecked even though every other test is ticked.
                        TestSelectionRules.ApplyRunAll(Config);
                    }
                    break;
                case nameof(EdcConfig.ValidateServerRole):
                    TestSelectionRules.ApplyServerRoleDependency(Config);
                    break;
                // Mutually exclusive: ExperienceBuilder ↔ WebAppBuilder
                case nameof(EdcConfig.CreateExperienceBuilderApp):
                    if (Config.CreateExperienceBuilderApp) Config.CreateWebAppBuilder = false;
                    TestSelectionRules.ApplyPortalTestDependency(Config);
                    break;
                case nameof(EdcConfig.CreateWebAppBuilder):
                    if (Config.CreateWebAppBuilder) Config.CreateExperienceBuilderApp = false;
                    TestSelectionRules.ApplyPortalTestDependency(Config);
                    break;
                // Portal tests: auto-select LoginPortal as a prerequisite
                case nameof(EdcConfig.CreateFeatureLayer):
                case nameof(EdcConfig.CreateTileLayer):
                case nameof(EdcConfig.CreateSceneLayer):
                case nameof(EdcConfig.CreateMap):
                case nameof(EdcConfig.CreateMember):
                case nameof(EdcConfig.CreateDashboard):
                case nameof(EdcConfig.CreateStoryMap):
                case nameof(EdcConfig.ValidateOrganization):
                    TestSelectionRules.ApplyPortalTestDependency(Config);
                    break;
            }

            // Reverse cascade: keep "Run All Tests" in sync with the actual
            // selection state. The Java engine treats RunAllTest=Yes as a global
            // override (every test gets forced back on at JVM startup), so if the
            // user un-ticks any individual test we MUST clear RunAllTest too —
            // otherwise the saved config.properties is internally inconsistent
            // and Java fails fast on missing server credentials, etc.
            if (e.PropertyName != nameof(EdcConfig.RunAllTest) && Config.RunAllTest)
            {
                int expected = TestSelectionRules.TotalTestCount - (Config.Kubernetes ? 2 : 0);
                if (TestSelectionRules.CountSelectedTests(Config) < expected)
                    Config.RunAllTest = false;
            }
            OnPropertyChanged(nameof(SelectedTestCount));
            OnPropertyChanged(nameof(PortalTestCount));
            OnPropertyChanged(nameof(ServerTestCount));
            OnPropertyChanged(nameof(IsKubernetesIncompatibleEnabled));
            OnPropertyChanged(nameof(IsLoginPortalEnabled));
            OnPropertyChanged(nameof(IsExperienceBuilderEnabled));
            OnPropertyChanged(nameof(IsWebAppBuilderEnabled));
        }
        finally { _suppress = false; }
    }

    public int SelectedTestCount => TestSelectionRules.CountSelectedTests(Config);
    public int PortalTestCount   => TestSelectionRules.CountPortalTests(Config);
    public int ServerTestCount   => TestSelectionRules.CountServerTests(Config);

    /// <summary>Total number of individually selectable tests (mirrors <see cref="TestSelectionRules.TotalTestCount"/>).</summary>
    public int TotalTestCount => TestSelectionRules.TotalTestCount;

    /// <summary>True when K8s-incompatible tests (data store / server role) should be enabled.</summary>
    public bool IsKubernetesIncompatibleEnabled => !Config.Kubernetes;

    /// <summary>
    /// "Login to Portal" is force-on whenever any dependent portal test is selected.
    /// Bind to <c>IsEnabled</c> on the checkbox so the user can't uncheck a prerequisite.
    /// </summary>
    public bool IsLoginPortalEnabled => !TestSelectionRules.AnyDependentPortalTest(Config);

    /// <summary>
    /// ExperienceBuilder and WebAppBuilder are mutually exclusive when the user is
    /// individually toggling them, but "Run All Tests" force-enables BOTH at once.
    /// In that case neither checkbox should appear disabled — keep a checkbox enabled
    /// whenever it's already checked so the user can still uncheck it.
    /// </summary>
    public bool IsExperienceBuilderEnabled => Config.CreateExperienceBuilderApp || !Config.CreateWebAppBuilder;
    public bool IsWebAppBuilderEnabled    => Config.CreateWebAppBuilder        || !Config.CreateExperienceBuilderApp;
}

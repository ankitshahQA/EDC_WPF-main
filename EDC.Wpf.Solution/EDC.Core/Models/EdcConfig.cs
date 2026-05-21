using System.Collections.ObjectModel;
using CommunityToolkit.Mvvm.ComponentModel;

namespace EDC.Core.Models;

/// <summary>
/// In-memory representation of Input/config.properties.
/// Property names follow the Java field semantics in EDCMainFrame.
/// </summary>
public partial class EdcConfig : ObservableObject
{
    public const int MaxServers = 10;

    // ── Portal ─────────────────────────────────────────────────────────────
    [ObservableProperty] private string portalUrl = string.Empty;
    [ObservableProperty] private string portalUserName = string.Empty;
    [ObservableProperty] private string portalPassword = string.Empty;
    [ObservableProperty] private bool kubernetes;
    [ObservableProperty] private string browser = "Chrome"; // "Chrome" | "Edge"

    // ── Servers ────────────────────────────────────────────────────────────
    public ObservableCollection<ServerSlot> Servers { get; } = new();

    // ── Test selection ─────────────────────────────────────────────────────
    [ObservableProperty] private bool runAllTest;
    [ObservableProperty] private bool loginPortal;
    [ObservableProperty] private bool loginManager;
    [ObservableProperty] private bool loginServer;
    [ObservableProperty] private bool validateServerRole;
    [ObservableProperty] private bool validateDataStores;
    [ObservableProperty] private bool validateOrganization;
    [ObservableProperty] private bool createFeatureLayer;
    [ObservableProperty] private bool createTileLayer;
    [ObservableProperty] private bool createSceneLayer;
    [ObservableProperty] private bool createMap;
    [ObservableProperty] private bool createWebAppBuilder;
    [ObservableProperty] private bool createExperienceBuilderApp;
    [ObservableProperty] private bool createMember;
    [ObservableProperty] private bool createDashboard;
    [ObservableProperty] private bool createStoryMap;

    public EdcConfig()
    {
        for (int i = 0; i < MaxServers; i++)
            Servers.Add(new ServerSlot());
    }
}

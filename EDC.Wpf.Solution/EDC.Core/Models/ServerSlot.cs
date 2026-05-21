using CommunityToolkit.Mvvm.ComponentModel;

namespace EDC.Core.Models;

/// <summary>
/// One of up to 10 server entries the user configures.
/// Mirrors EDCMainFrame.Serverurl[] / ServerAdminUsername[] / ServerAdminPassword[] / ServerAdminRole[] / ServerFederated[].
/// </summary>
public partial class ServerSlot : ObservableObject
{
    public static readonly string[] Roles =
    {
        "Hosting Server",
        "Raster Analysis",
        "Notebook",
        "Workflow Manager"
    };

    [ObservableProperty] private string url = string.Empty;
    [ObservableProperty] private string adminUsername = string.Empty;
    [ObservableProperty] private string adminPassword = string.Empty;
    [ObservableProperty] private string role = "Hosting Server";
    [ObservableProperty] private bool federated = true;

    partial void OnRoleChanged(string value)
    {
        // Snap legacy / unknown role names ("Imagery", "Mission", "Geo Analytics", "Knowledge Server")
        // back to the Hosting default so the ComboBox always has a selection.
        if (!Array.Exists(Roles, r => string.Equals(r, value, StringComparison.OrdinalIgnoreCase)))
            role = "Hosting Server";
    }

    public bool IsEmpty =>
        string.IsNullOrWhiteSpace(Url) &&
        string.IsNullOrWhiteSpace(AdminUsername) &&
        string.IsNullOrWhiteSpace(AdminPassword);
}

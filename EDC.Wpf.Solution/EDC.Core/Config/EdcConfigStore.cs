using EDC.Core.Models;

namespace EDC.Core.Config;

/// <summary>
/// Loads / saves <see cref="EdcConfig"/> against Input/config.properties
/// using the exact key names the Java engine reads in EDCMainFrame.readfromconfigdisplay.
/// </summary>
public sealed class EdcConfigStore
{
    public string ConfigPath { get; }

    public EdcConfigStore(string configPath)
    {
        ConfigPath = configPath;
    }

    public EdcConfig Load()
    {
        var p = PropertiesFile.Load(ConfigPath);
        var c = new EdcConfig
        {
            PortalUrl = p.GetOrDefault("PortalUrl", ""),
            PortalUserName = p.GetOrDefault("PortalUserName", ""),
            PortalPassword = p.GetOrDefault("PortalPassword", ""),
            Kubernetes = IsYes(p.Get("Kubernetes")),
            Browser = NormalizeBrowser(p.Get("Browser")),

            RunAllTest = IsYes(p.Get("RunAllTest")),
            LoginPortal = IsYes(p.Get("LoginPortal")),
            LoginManager = IsYes(p.Get("LoginManager")),
            LoginServer = IsYes(p.Get("LoginServer")),
            ValidateServerRole = IsYes(p.Get("ValidateServerRole")),
            ValidateDataStores = IsYes(p.Get("ValidateDataStores")),
            ValidateOrganization = IsYes(p.Get("ValidateOrganization")),
            CreateFeatureLayer = IsYes(p.Get("CreateFeatureLayer")),
            CreateTileLayer = IsYes(p.Get("CreateTileLayer")),
            CreateSceneLayer = IsYes(p.Get("CreateSceneLayer")),
            CreateMap = IsYes(p.Get("CreateMap")),
            CreateWebAppBuilder = IsYes(p.Get("CreateWebAppBuilder")),
            CreateExperienceBuilderApp = IsYes(p.Get("CreateExperienceBuilderApp")),
            CreateMember = IsYes(p.Get("CreateMember")),
            CreateDashboard = IsYes(p.Get("CreateDashboard")),
            CreateStoryMap = IsYes(p.Get("CreateStoryMap")),
        };

        for (int i = 0; i < EdcConfig.MaxServers; i++)
        {
            int n = i + 1;
            var slot = c.Servers[i];
            slot.Url = p.GetOrDefault($"ServerUrl{n}", "");
            slot.AdminUsername = p.GetOrDefault($"ServerAdminUsername{n}", "");
            slot.AdminPassword = p.GetOrDefault($"ServerAdminPassword{n}", "");
            slot.Role = p.GetOrDefault($"ServerRole{n}", n == 1 ? "Hosting Server" : "");
            slot.Federated = IsYesOr(p.Get($"Federated{n}"), true);
        }

        // Normalize: if RunAllTest=Yes was persisted but the individual flags
        // do NOT all match (e.g. the user un-ticked a sub-test in a previous
        // session, or the file was hand-edited), clear RunAllTest. The Java
        // engine treats RunAllTest=Yes as a global override that re-enables
        // every test at JVM startup, which silently overrides the user's
        // partial deselection and can crash the run (e.g. server-login tests
        // with no Server URL configured).
        if (c.RunAllTest)
        {
            int expected = Tests.TestSelectionRules.TotalTestCount - (c.Kubernetes ? 2 : 0);
            if (Tests.TestSelectionRules.CountSelectedTests(c) < expected)
                c.RunAllTest = false;
        }

        return c;
    }

    public void Save(EdcConfig c)
    {
        var p = PropertiesFile.Load(ConfigPath); // preserve unknown keys if any

        p.Set("PortalUrl", c.PortalUrl);
        p.Set("PortalUserName", c.PortalUserName);
        p.Set("PortalPassword", c.PortalPassword);
        p.Set("Kubernetes", YesNo(c.Kubernetes));
        p.Set("Browser", c.Browser);

        p.Set("RunAllTest", YesNo(c.RunAllTest));
        p.Set("LoginPortal", YesNo(c.LoginPortal));
        p.Set("LoginManager", YesNo(c.LoginManager));
        p.Set("LoginServer", YesNo(c.LoginServer));
        p.Set("ValidateServerRole", YesNo(c.ValidateServerRole));
        p.Set("ValidateDataStores", YesNo(c.ValidateDataStores));
        p.Set("ValidateOrganization", YesNo(c.ValidateOrganization));
        p.Set("CreateFeatureLayer", YesNo(c.CreateFeatureLayer));
        p.Set("CreateTileLayer", YesNo(c.CreateTileLayer));
        p.Set("CreateSceneLayer", YesNo(c.CreateSceneLayer));
        p.Set("CreateMap", YesNo(c.CreateMap));
        p.Set("CreateWebAppBuilder", YesNo(c.CreateWebAppBuilder));
        p.Set("CreateExperienceBuilderApp", YesNo(c.CreateExperienceBuilderApp));
        p.Set("CreateMember", YesNo(c.CreateMember));
        p.Set("CreateDashboard", YesNo(c.CreateDashboard));
        p.Set("CreateStoryMap", YesNo(c.CreateStoryMap));

        for (int i = 0; i < EdcConfig.MaxServers; i++)
        {
            int n = i + 1;
            var slot = c.Servers[i];
            p.Set($"ServerUrl{n}", slot.Url);
            p.Set($"ServerAdminUsername{n}", slot.AdminUsername);
            p.Set($"ServerAdminPassword{n}", slot.AdminPassword);
            p.Set($"ServerRole{n}", slot.Role);
            p.Set($"Federated{n}", YesNo(slot.Federated));
        }

        p.Save(ConfigPath);
    }

    private static bool IsYes(string? s) => s is not null && s.Trim().Equals("Yes", StringComparison.OrdinalIgnoreCase);

    private static bool IsYesOr(string? s, bool fallback) =>
        string.IsNullOrWhiteSpace(s) ? fallback : IsYes(s);

    private static string YesNo(bool b) => b ? "Yes" : "No";

    private static string NormalizeBrowser(string? raw)
    {
        if (string.IsNullOrWhiteSpace(raw)) return "Chrome";
        return raw.Contains("edge", StringComparison.OrdinalIgnoreCase) ? "Edge" : "Chrome";
    }
}

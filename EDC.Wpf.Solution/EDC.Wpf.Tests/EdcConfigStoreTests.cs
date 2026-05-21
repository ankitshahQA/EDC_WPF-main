using EDC.Core.Config;
using EDC.Core.Models;
using EDC.Core.Tests;

namespace EDC.Wpf.Tests;

public class EdcConfigStoreTests
{
    [Fact]
    public void Save_then_Load_round_trips_values()
    {
        var path = Path.Combine(Path.GetTempPath(), $"edc-{Guid.NewGuid():N}.properties");
        try
        {
            var store = new EdcConfigStore(path);
            var c = new EdcConfig
            {
                PortalUrl = "https://portal.example.com/arcgis",
                PortalUserName = "admin",
                PortalPassword = "p@ss",
                Kubernetes = true,
                Browser = "Edge",
                RunAllTest = true,
                CreateDashboard = true,
            };
            // RunAllTest=true requires every individual flag to be set consistently;
            // otherwise EdcConfigStore.Load will normalize RunAllTest back to false
            // (guards against the Java engine's global-override behaviour).
            TestSelectionRules.ApplyRunAll(c);
            c.Servers[0].Url = "https://server1/arcgis";
            c.Servers[0].AdminUsername = "site1";
            c.Servers[0].Federated = false;
            c.Servers[0].Role = "Raster Analysis";

            store.Save(c);
            var c2 = store.Load();

            Assert.Equal(c.PortalUrl, c2.PortalUrl);
            Assert.Equal(c.PortalUserName, c2.PortalUserName);
            Assert.Equal(c.PortalPassword, c2.PortalPassword);
            Assert.True(c2.Kubernetes);
            Assert.Equal("Edge", c2.Browser);
            Assert.True(c2.RunAllTest);
            Assert.True(c2.CreateDashboard);
            Assert.Equal("https://server1/arcgis", c2.Servers[0].Url);
            Assert.Equal("site1", c2.Servers[0].AdminUsername);
            Assert.False(c2.Servers[0].Federated);
            Assert.Equal("Raster Analysis", c2.Servers[0].Role);
        }
        finally
        {
            if (File.Exists(path)) File.Delete(path);
        }
    }

    [Fact]
    public void Load_missing_file_returns_defaults()
    {
        var store = new EdcConfigStore(Path.Combine(Path.GetTempPath(), $"missing-{Guid.NewGuid():N}.properties"));
        var c = store.Load();
        Assert.Equal("Chrome", c.Browser);
        Assert.False(c.Kubernetes);
        Assert.Equal(EdcConfig.MaxServers, c.Servers.Count);
        Assert.True(c.Servers[0].Federated);
        Assert.Equal("Hosting Server", c.Servers[0].Role);
    }

    [Fact]
    public void Reads_yes_no_case_insensitively()
    {
        var path = Path.Combine(Path.GetTempPath(), $"edc-{Guid.NewGuid():N}.properties");
        // Mix of YES/yes/Yes/no/No exercises case-insensitive parsing.
        // RunAllTest=YES requires all individual K8s flags to also be Yes,
        // otherwise EdcConfigStore.Load normalises RunAllTest back to false.
        // ValidateServerRole/ValidateDataStores stay No because Kubernetes=YES.
        File.WriteAllText(path,
            "Kubernetes=YES\n" +
            "RunAllTest=yes\n" +
            "LoginPortal=Yes\n" +
            "LoginManager=YES\n" +
            "LoginServer=yes\n" +
            "ValidateOrganization=Yes\n" +
            "ValidateServerRole=No\n" +
            "ValidateDataStores=no\n" +
            "CreateMember=YES\n" +
            "CreateMap=yes\n" +
            "CreateFeatureLayer=Yes\n" +
            "CreateTileLayer=YES\n" +
            "CreateSceneLayer=yes\n" +
            "CreateDashboard=Yes\n" +
            "CreateStoryMap=YES\n" +
            "CreateWebAppBuilder=yes\n" +
            "CreateExperienceBuilderApp=Yes\n");
        try
        {
            var c = new EdcConfigStore(path).Load();
            Assert.True(c.Kubernetes);
            Assert.True(c.RunAllTest);
            Assert.True(c.LoginPortal);
            Assert.False(c.ValidateServerRole);
            Assert.False(c.ValidateDataStores);
        }
        finally { File.Delete(path); }
    }

    [Theory]
    [InlineData("Yes", true)]
    [InlineData("yes", true)]
    [InlineData("YES", true)]
    [InlineData("yEs", true)]
    [InlineData("No", false)]
    [InlineData("no", false)]
    [InlineData("NO", false)]
    [InlineData("", false)]
    [InlineData("   ", false)]
    [InlineData("maybe", false)]
    public void Parses_yes_no_values_case_insensitively(string raw, bool expected)
    {
        var path = Path.Combine(Path.GetTempPath(), $"edc-{Guid.NewGuid():N}.properties");
        // Use a standalone flag (LoginPortal with no other test flags and no
        // RunAllTest) so the Load normalisation step does not interfere.
        File.WriteAllText(path, $"LoginPortal={raw}\n");
        try
        {
            var c = new EdcConfigStore(path).Load();
            Assert.Equal(expected, c.LoginPortal);
        }
        finally { File.Delete(path); }
    }
}

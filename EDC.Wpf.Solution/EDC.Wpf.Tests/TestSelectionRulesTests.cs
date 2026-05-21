using EDC.Core.Models;
using EDC.Core.Tests;

namespace EDC.Wpf.Tests;

public class TestSelectionRulesTests
{
    [Fact]
    public void ApplyRunAll_enables_all_non_k8s_flags()
    {
        var c = new EdcConfig { RunAllTest = true, Kubernetes = false };
        TestSelectionRules.ApplyRunAll(c);

        Assert.True(c.LoginPortal); Assert.True(c.LoginManager); Assert.True(c.LoginServer);
        Assert.True(c.CreateFeatureLayer); Assert.True(c.CreateTileLayer); Assert.True(c.CreateMap);
        Assert.True(c.CreateSceneLayer); Assert.True(c.CreateWebAppBuilder); Assert.True(c.CreateExperienceBuilderApp);
        Assert.True(c.CreateMember); Assert.True(c.CreateDashboard); Assert.True(c.CreateStoryMap);
        Assert.True(c.ValidateServerRole); Assert.True(c.ValidateDataStores);
    }

    [Fact]
    public void ApplyRunAll_with_k8s_disables_server_role_and_datastores()
    {
        var c = new EdcConfig { RunAllTest = true, Kubernetes = true };
        TestSelectionRules.ApplyRunAll(c);

        Assert.False(c.ValidateServerRole);
        Assert.False(c.ValidateDataStores);
        Assert.True(c.LoginPortal); // others still enabled
    }

    [Fact]
    public void ApplyKubernetesRules_clears_incompatible_flags()
    {
        var c = new EdcConfig
        {
            Kubernetes = true,
            ValidateServerRole = true,
            ValidateDataStores = true,
        };
        TestSelectionRules.ApplyKubernetesRules(c);

        Assert.False(c.ValidateServerRole);
        Assert.False(c.ValidateDataStores);
    }

    [Fact]
    public void ApplyServerRoleDependency_forces_portal_login()
    {
        var c = new EdcConfig { ValidateServerRole = true, LoginPortal = false };
        TestSelectionRules.ApplyServerRoleDependency(c);
        Assert.True(c.LoginPortal);
    }

    [Fact]
    public void CountSelectedTests_counts_all_15_when_run_all()
    {
        var c = new EdcConfig { RunAllTest = true };
        TestSelectionRules.ApplyRunAll(c);
        Assert.Equal(15, TestSelectionRules.CountSelectedTests(c));
    }
}

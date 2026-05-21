using EDC.Core.Models;
using System.Collections.Generic;

namespace EDC.Core.Tests;

/// <summary>
/// Mirrors EDCMainFrame.applyTestFlags / TestsToRunPanel cascade rules.
/// Call after any user toggle to keep dependent flags consistent.
/// </summary>
public static class TestSelectionRules
{
    /// <summary>Total number of individually selectable tests (3 server + 12 portal).</summary>
    public const int TotalTestCount = 15;

    public static void ApplyRunAll(EdcConfig c)
    {
        if (!c.RunAllTest) return;
        c.LoginPortal = c.LoginManager = c.LoginServer = true;
        c.CreateFeatureLayer = c.CreateTileLayer = c.CreateMap = true;
        c.CreateSceneLayer = c.CreateWebAppBuilder = c.CreateExperienceBuilderApp = true;
        c.CreateMember = c.CreateDashboard = c.CreateStoryMap = true;
        c.ValidateOrganization = true;
        // K8s deployments do not expose data-store or server-role checks
        c.ValidateServerRole = !c.Kubernetes;
        c.ValidateDataStores = !c.Kubernetes;
    }

    /// <summary>
    /// Clears every individual test flag. Called when the user un-checks "Run all tests"
    /// so the cascade is symmetric (matches EDCMainFrame.applyTestFlags behaviour).
    /// </summary>
    public static void ClearAll(EdcConfig c)
    {
        c.LoginPortal = c.LoginManager = c.LoginServer = false;
        c.ValidateServerRole = c.ValidateDataStores = c.ValidateOrganization = false;
        c.CreateFeatureLayer = c.CreateTileLayer = c.CreateSceneLayer = false;
        c.CreateMap = c.CreateWebAppBuilder = c.CreateExperienceBuilderApp = false;
        c.CreateMember = c.CreateDashboard = c.CreateStoryMap = false;
    }

    public static void ApplyKubernetesRules(EdcConfig c)
    {
        if (!c.Kubernetes) return;
        c.ValidateServerRole = false;
        c.ValidateDataStores = false;
    }

    /// <summary>
    /// "Validate Server Role" implies portal login (same dependency in applyTestFlags).
    /// </summary>
    public static void ApplyServerRoleDependency(EdcConfig c)
    {
        if (c.ValidateServerRole && !c.Kubernetes)
            c.LoginPortal = true;
    }

    /// <summary>
    /// Any portal test (content creation, organization, member, etc.) requires portal login.
    /// Called when the user individually checks a portal test to auto-select LoginPortal.
    /// </summary>
    public static void ApplyPortalTestDependency(EdcConfig c)
    {
        if (AnyDependentPortalTest(c))
            c.LoginPortal = true;
    }

    /// <summary>
    /// True when at least one portal test that requires a portal login is selected.
    /// While this is true, the "Login to Portal" checkbox is forced on and must be
    /// disabled in the UI so the user can't uncheck a prerequisite.
    /// </summary>
    public static bool AnyDependentPortalTest(EdcConfig c)
    {
        return c.CreateFeatureLayer || c.CreateTileLayer || c.CreateSceneLayer
            || c.CreateMap || c.CreateWebAppBuilder || c.CreateExperienceBuilderApp
            || c.CreateMember || c.CreateDashboard || c.CreateStoryMap
            || c.ValidateOrganization || c.ValidateServerRole;
    }

    public static int CountSelectedTests(EdcConfig c)
    {
        int n = 0;
        if (c.LoginPortal) n++;
        if (c.LoginManager) n++;
        if (c.LoginServer) n++;
        if (c.ValidateServerRole) n++;
        if (c.ValidateDataStores) n++;
        if (c.ValidateOrganization) n++;
        if (c.CreateFeatureLayer) n++;
        if (c.CreateTileLayer) n++;
        if (c.CreateSceneLayer) n++;
        if (c.CreateMap) n++;
        if (c.CreateWebAppBuilder) n++;
        if (c.CreateExperienceBuilderApp) n++;
        if (c.CreateMember) n++;
        if (c.CreateDashboard) n++;
        if (c.CreateStoryMap) n++;
        return n;
    }

    public static int CountServerTests(EdcConfig c)
    {
        int n = 0;
        if (c.LoginServer) n++;
        if (c.LoginManager) n++;
        if (c.ValidateDataStores) n++;
        return n;
    }

    public static int CountPortalTests(EdcConfig c)
    {
        int n = 0;
        if (c.LoginPortal) n++;
        if (c.ValidateServerRole) n++;
        if (c.ValidateOrganization) n++;
        if (c.CreateFeatureLayer) n++;
        if (c.CreateTileLayer) n++;
        if (c.CreateSceneLayer) n++;
        if (c.CreateMap) n++;
        if (c.CreateWebAppBuilder) n++;
        if (c.CreateExperienceBuilderApp) n++;
        if (c.CreateMember) n++;
        if (c.CreateDashboard) n++;
        if (c.CreateStoryMap) n++;
        return n;
    }

    /// <summary>
    /// Returns the display names (matching the Results Log group titles produced
    /// by the Java engine's "X functionality started" banners after suffix
    /// stripping) of every test the user selected, in TestNG @Test priority order.
    /// Used by the Stop flow to synthesize Skipped entries for any selected test
    /// the engine didn't reach before the JVM exited.
    /// </summary>
    public static IEnumerable<string> EnumerateSelectedTestNames(EdcConfig c)
    {
        // Order mirrors TestSuite.java @Test(priority = N).
        //  1 ServerAdminLogin              2 ServerManagerLogin
        //  3 ValidateDataStores            4 PortalLogin
        //  6 CreateFeatureLayer            7 CreateTileLayer
        //  8 VerifySceneLayerFunctionality 9 CreateWebMap
        // 10 CreateDashboard              11 CreateStoryMap
        // 12 CreateExperienceBuilderApp   13 CreateWebAppBuilderApp
        // 14 CreateGroups                 15 VerifyOrganizationFunctionality
        // 16 ValidateServerRoles
        if (c.LoginServer)                 yield return "Login to Server Admin";
        if (c.LoginManager)                yield return "Login to Server Manager";
        if (c.ValidateDataStores)          yield return "Validating Data Stores";
        if (c.LoginPortal)                 yield return "Login to Portal";
        if (c.CreateFeatureLayer)          yield return "Create Feature Layer";
        if (c.CreateTileLayer)             yield return "Create Tile Layer";
        if (c.CreateSceneLayer)            yield return "Scene Layer";
        if (c.CreateMap)                   yield return "Create Web Map";
        if (c.CreateDashboard)             yield return "Create Dashboard";
        if (c.CreateStoryMap)              yield return "Create Story Map";
        if (c.CreateExperienceBuilderApp)  yield return "Create Experience Builder";
        if (c.CreateWebAppBuilder)         yield return "Create WebApp Builder";
        if (c.CreateMember)                yield return "Create Group";
        if (c.ValidateOrganization)        yield return "Verify Organization";
        if (c.ValidateServerRole)          yield return "Validating Server Roles";
    }
}

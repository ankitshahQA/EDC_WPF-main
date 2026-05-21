package com.automation.testcases;

import com.automation.PythonAPI.PythonHelp;
import com.automation.library.CommonFunction;
import com.automation.library.TestBase;
import com.automation.pages.ContentPage;
import com.automation.pages.DashboardPage;
import com.automation.pages.HomePage;

public class CreateDashboard_Functionality extends TestBase {

	CommonFunction cfunction = new CommonFunction();
	ContentPage cp = new ContentPage();
	DashboardPage Dash = new DashboardPage();
	HomePage hp = new HomePage();
	String msg = "";

	public void CreateDashboard_functionality() throws Exception {

		msg = "Select Dashboards from the ArcGIS App container";
		childnode = logger.createNode(msg);
		hp.selectDashboards();
		cfunction.switchTowindow(1);// switch to window(intermediate Step, as dashboard page is opening in new window)
		Dash.verifyDashboardPage();

		if (DashboardPageFlag) {
			msg = "Creating Dashboard App with name: " + DashboardName;
			childnode = logger.createNode(msg);

			Dash.createDashboard();
			Dash.EnterDashboardDetails(DashboardName);
			Dash.verifyDashboardCreated();

			if (DashboardCreatedFlag) {
				PythonHelp.MoveItemInfolder(DashboardName); // Created item move admin folder to automation folder
				Dash.NavigateContentPage();
				cp.VerifyDashboardCreated(DashboardName);
				driver.close();// close the dashboard page window tab
				cfunction.switchToMainWindow();// switch back to main window
				
				
			} else {
				CommonFunction.logStatus("FAIL", "Dashboard creation failed with name: " + DashboardName);
			}
		} else {
			CommonFunction.logStatus("FAIL", "Dashboard page is not displayed, hence dashboard creation failed");
		}
	}

}

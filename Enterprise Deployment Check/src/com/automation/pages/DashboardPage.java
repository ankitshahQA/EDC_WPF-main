package com.automation.pages;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;

public class DashboardPage extends TestBase {
	String msg = "";
	CommonFunction cfunction = new CommonFunction();
	HomePage hp = new HomePage();
	ContentPage cp = new ContentPage();
	final static String CONTENT_TITLE_XPATH1 = "//h1[contains(@id,'title')and text()='Content']";
	final static String CONTENT_MENU_ITEM = "//div[@id='esri-header-menus-desktop']//a[contains(@class,'esri-header-menus-link') and contains(@href,'content')]";
	final static String DASHBOARD_HOMEPAGE_XPATH = "//div//span[text()='ArcGIS Dashboards']";
	final static String CREATE_DASHBOARD_BTN_XPATH = "//calcite-button[contains(@href,'dashboards/new')]";
	final static String DASHBOARD_TITLE_JSPATH = "document.querySelector('calcite-input').shadowRoot.querySelector('div > div.element-wrapper > input[aria-label*=\"Title\"]')";
	final static String DASHBOARD_TITLE_11_2JSPATH = "document.querySelector('calcite-input-text').shadowRoot.querySelector(' input[aria-label*=\"Title\"]')";

	final static String DASHBOARD_TAGS_JSPATH = "document.querySelector('calcite-combobox').shadowRoot.querySelector('input[aria-label*=\"Tags\"]')";
	final static String SAVE_BTN_XPATH = "//span[text()='Create dashboard']";
	final static String DASHBOARD_CREATED_XPATH = "//*[contains(@class,'dashboard-title')]";
	final static String BURGER_MENU_XPATH = "//calcite-action[@icon='hamburger']";
	final static String CONTENT_MENU_XPATH = "//calcite-dropdown-item[contains(@href,'content')]";

	public boolean verifyDashboardPage() throws Exception {
		msg = "Verify Dashboard Page is displayed";
		if (projectVersion.contains("11.") || projectVersion.contains("12.")) {
			try {
				if (CommonFunction.elementIsDisplayed(DASHBOARD_HOMEPAGE_XPATH, 10)) {
					CommonFunction.logStatus("PASS", msg);
					DashboardPageFlag = true;
				} else {
					CommonFunction.logStatus("FAIL", msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}

		} else {

		}
		return DashboardPageFlag;
	}

	public void createDashboard() throws Exception {

		msg = "Click on Create Dashboard button";
		if (projectVersion.contains("11.") || projectVersion.contains("12.")) {
			try {
				cfunction.Commmon_Click("xpath", CREATE_DASHBOARD_BTN_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}

		} else {

		}
	}

	public void EnterDashboardDetails(String DashboardName) throws Exception {

		msg = "Send the Title details to the respective field";
		if (projectVersion.equalsIgnoreCase("11.1.0") || projectVersion.equalsIgnoreCase("11.3.0")
				|| projectVersion.equalsIgnoreCase("11.4.0") || projectVersion.equalsIgnoreCase("11.5.0")
				|| projectVersion.equalsIgnoreCase("Kubernetes11.5.0") || projectVersion.equalsIgnoreCase("12.0.0")
				|| projectVersion.equalsIgnoreCase("12.1.0")|| projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) {
			try {
				cfunction.Webelement_JSInput(DASHBOARD_TITLE_JSPATH, DashboardName);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}

		} else if (projectVersion.equalsIgnoreCase("11.2.0")) {
			try {
				cfunction.Webelement_JSInput(DASHBOARD_TITLE_11_2JSPATH, DashboardName);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}

		}

		msg = "Enter the Add Tags details to the respective field";
		if (projectVersion.contains("11.") || projectVersion.contains("12.")) {
			try {
				cfunction.Webelement_JSInput(DASHBOARD_TAGS_JSPATH, "DashboardTag");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}

		} else {

		}

		msg = "Click on Save button to save the created Dashboard";
		if (projectVersion.contains("11.") || projectVersion.contains("12.")) {
			try {
				cfunction.Commmon_Click("xpath", SAVE_BTN_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}

		} else {

		}
	}

	public boolean verifyDashboardCreated() throws Exception {

		msg = "Verify Dashborad is created sucessfully";
		String DASHBOARD_CREATED_XPATH = "//span[text()='" + DashboardName + "']";
		cfunction.sync(5);
		if (projectVersion.contains("11.") || projectVersion.contains("12.")) {
			try {
				if (CommonFunction.elementIsDisplayed(DASHBOARD_CREATED_XPATH, 20)) {
					CommonFunction.logStatus("PASS", msg);
					DashboardCreatedFlag = true;
				} else {
					CommonFunction.logStatus("FAIL", msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}

		} else {

		}
		return DashboardCreatedFlag;
	}

	public void NavigateContentPage() throws Exception {

		msg = "Click on the Burgur menu and navigate to content page";
		if (projectVersion.contains("11.") || projectVersion.contains("12.")) {
			try {
				cfunction.Commmon_Click("xpath", BURGER_MENU_XPATH);
				cfunction.Commmon_Click("xpath", CONTENT_MENU_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}

		} else {

		}

		msg = "Wait for content page to load";
		try {
			TestBase.waitforpagetoload();
			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| projectVersion.equalsIgnoreCase("Kubernetes12.1.0")
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.waitforelement("//h1[ text()='Content']");
			} else {
				cfunction.waitforelement(CONTENT_TITLE_XPATH1);

			}
		} catch (Exception e) {
			try {
				cfunction.Commmon_Click("xpath", BURGER_MENU_XPATH);
				cfunction.Commmon_Click("xpath", CONTENT_MENU_XPATH);
			} catch (Exception e1) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e1);

			}
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}
	}

}

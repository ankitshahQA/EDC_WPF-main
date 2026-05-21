package com.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;

public class ServerPage extends TestBase {
	CommonFunction cfunction = new CommonFunction();
	String msg = "";
	boolean flag = false;

	final String SERVER_XPATH = "//li[contains(@id,'dijit__TemplatedMixin_') and button[text()='Servers']]";
	final String SERVR_ROLE_XPATH = "(//div[@class='flex fed-server-card__row']//button[@class='btn btn-link'])";
	final String SERVICEURL_XPATH = "(//calcite-link[@class='fed-server__label'])";
	final String ADMINURL_XPATH = "//div[@class='fed-server__label']//calcite-link";
	final String SERVER_STATUS_XPATH = "(//span[@class='tooltip tooltip-multiline tooltip-wide'])";
	final String SIGN_OUT_XPATH = "//*[text()='Signout']";
	final String LoginHyperLInk_XPATH = "//a[text()='Login']";
	final String ARCGIS_XPATH = "//div[@id='loginTitle' and @aria-expanded='true']";
	final String ARCGIS_ARROW_XPATH = "//div[@id='loginTitle']";
	final String USERNAME_MANAGER_XPATH = "//input[@name='username']";
	final String PASSWORD_MANAGER_XPATH = "//input[@name='password']";
	final String LOGIN_MANAGER_XPATH = "//*[text()='Login' or @id='signIn']";
	final String SIGNINSUBMIT_XPATH = "//*[@id='signIn']";
	final String AFTER_LOGIN_XPATH = "//*[text()='Sign Out']";
	final String SITE_BTN_XPATH = "//div[@id='page']//li[@esri-tab-id='site']";
	final String DATASTORES_XPATH = "//div[@widgetid='dataStoresLabel']";
	final String VALIDATEALL_XPATH = "//span[text()='Validate All']";
	final String LISTOFALLVALIATE_XPATH = "//div[@class='dojoxGridContent']//table[@class='dojoxGridRowTable']";
	final String STATUS_GREEN_XPATH = "(//span[@class='dijitInline esriIconValidationValid'])";

	public void validateServer(String managerURL, String SeverRole) throws Exception {

		if (SeverRole.equalsIgnoreCase("Raster Analysis Server")) {
			msg = "Verify that the server role is " + SeverRole;
			flag = false;
			int i = 0;
			try {
				@SuppressWarnings("unchecked")
				List<WebElement> Elements = cfunction.getWebElementList(SERVR_ROLE_XPATH);
				for (i = 0; i < Elements.size(); i++) {
					cfunction.ScrollToElement(SERVR_ROLE_XPATH + "[" + (i + 1) + "]");
					String ActulServerRole = cfunction.getElementText(SERVR_ROLE_XPATH + "[" + (i + 1) + "]");
					if (ActulServerRole.contains(SeverRole)) {
						flag = true;
						break;
					}
				}
				if (flag) {
					CommonFunction.logStatus("PASS", msg);
				} else {
					CommonFunction.logStatus("FAIL", msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			if (flag) {
				msg = "Verify that the server Status is 'All systems operational' ";
				cfunction.sync(5);
				try {
					String a = SERVICEURL_XPATH + "[" + (i + 1) + "]";
					cfunction.ScrollToElement(SERVICEURL_XPATH + "[" + (i + 1) + "]");
					String b = SERVER_STATUS_XPATH + "[" + (i + 1) + "]";
					cfunction.waitforelement(SERVER_STATUS_XPATH, 600);
					String ActulServerStatus = cfunction.getElementText(SERVER_STATUS_XPATH + "[" + (i + 1) + "]");
					if (ActulServerStatus.equalsIgnoreCase("All systems operational")) {
						CommonFunction.logStatus("PASS", msg);
					} else {
						CommonFunction.logStatus("FAIL", msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);

				}
			}
		} else {
			msg = "Verify that the federated Server  URL is " + managerURL;
			flag = false;
			int i = 0;
			try {
				List<WebElement> Elements = cfunction.getWebElementList(SERVICEURL_XPATH);
				for (i = 0; i < Elements.size(); i++) {
					String ServerURL = Elements.get(i).getText();
					cfunction.ScrollToElement(SERVICEURL_XPATH + "[" + (i + 1) + "]");
					if (managerURL.contains(ServerURL)) {
						flag = true;
						break;
					}
				}
				if (flag) {
					CommonFunction.logStatus("PASS", msg);
				} else {
					CommonFunction.logStatus("FAIL", msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}

			if (flag) {
				msg = "Verify that the server role is " + SeverRole;
				cfunction.sync(10);
				try {
					cfunction.ScrollToElement(SERVICEURL_XPATH + "[" + (i + 1) + "]");
					cfunction.waitforelement(SERVER_STATUS_XPATH, 600);
					String ActulServerRole = cfunction.getElementText(SERVR_ROLE_XPATH + "[" + (i + 1) + "]");
					if (ActulServerRole.contains(SeverRole)) {
						CommonFunction.logStatus("PASS", msg);
					} else {
						CommonFunction.logStatus("FAIL", msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);

				}

				msg = "Verify that the server Status is 'All systems operational' ";
				cfunction.sync(5);
				try {
					String a = SERVICEURL_XPATH + "[" + (i + 1) + "]";
					cfunction.ScrollToElement(SERVICEURL_XPATH + "[" + (i + 1) + "]");
					String b = SERVER_STATUS_XPATH + "[" + (i + 1) + "]";
					cfunction.waitforelement(SERVER_STATUS_XPATH, 600);
					String ActulServerStatus = cfunction.getElementText(SERVER_STATUS_XPATH + "[" + (i + 1) + "]");
					if (ActulServerStatus.equalsIgnoreCase("All systems operational")) {
						CommonFunction.logStatus("PASS", msg);
					} else {
						CommonFunction.logStatus("FAIL", msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);

				}

			}
		}
	}

	public String getAdminURL(int i) throws InterruptedException {
		String str = "";
		try {
			if (!(Serverurl[i].isEmpty())) {
				String url = (Serverurl[i] + "/admin");
				String newStr = url.substring(0, url.length() - 13);
				str = newStr + ":6443/arcgis";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	public String getServerURL(int i) throws InterruptedException {
		String managerURL = Serverurl[i].trim();
		return managerURL;
	}

	public void validateDataStores(String managerURL, String usernametxt, String pwdtxt) throws Exception {
		flag = false;
		boolean check = true;
		msg = "Navigate to manager URL";
		try {
			driver.get(managerURL);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);

			if (driver.findElements(By.xpath(LoginHyperLInk_XPATH)).size() > 0) {
				msg = "Click On the Login HyperLink";
				try {
					cfunction.Commmon_Click("xpath", LoginHyperLInk_XPATH);
					cfunction.sync(2);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e) {

					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);
				}
			}

			if (driver.findElements(By.xpath(ARCGIS_XPATH)).size() == 0) {
				if (driver.findElements(By.xpath(ARCGIS_ARROW_XPATH)).size() > 0) {
					msg = "ArcGIS Login section expanded";
					cfunction.Commmon_Click("xpath", ARCGIS_ARROW_XPATH);
				}
			}

			msg = "Enter Username for server Manager";
			try {
				cfunction.CommonTextBox_Input(USERNAME_MANAGER_XPATH, usernametxt);
				cfunction.sync(2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				check = false;
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}

			msg = "Enter Password for server Manager";
			try {
				cfunction.CommonTextBox_Input(PASSWORD_MANAGER_XPATH, pwdtxt);
				CommonFunction.logStatus("PASS", msg);
				cfunction.sync(2);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}
			msg = "Click on Sign In";
			try {
				cfunction.Commmon_Click("xpath", LOGIN_MANAGER_XPATH);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}
			try {
				cfunction.waitForloadingToEnd();
			} catch (Exception e) {
				e.printStackTrace();
				// CommonFunction.logStatus("FAIL", msg+e);
			}

			msg = "Verify user is able to sign in";
			try {
				cfunction.waitforelement(AFTER_LOGIN_XPATH, 20);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
				check = false;
			}

			if (check) {
				msg = "Click on the Site Button";
				try {
					cfunction.Commmon_Click("xpath", SITE_BTN_XPATH);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e) {
					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);

				}

				msg = "Click on the 'Data Stores' Button";
				try {
					cfunction.sync(5);
					cfunction.Commmon_Click("xpath", DATASTORES_XPATH);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e) {
					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);

				}

				msg = "Click on the 'Validate All' Button";
				try {
					cfunction.sync(5);
					cfunction.Commmon_Click("xpath", VALIDATEALL_XPATH);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e) {
					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);

				}

				msg = "Verify all the data store status with Green Mark.";
				try {
					cfunction.sync(10);
					List<WebElement> Elements = cfunction.getWebElementList(LISTOFALLVALIATE_XPATH);
					for (int i = 0; i < 10; i++) {
						if (Elements.size() == 0) {
							cfunction.sync(2);
						} else
							break;
					}

					for (int i = 0; i < Elements.size(); i++) {
						String Validate_XPATH = STATUS_GREEN_XPATH + "[" + (i + 1) + "]";
						if (cfunction.elementexist(Validate_XPATH) == true) {

							flag = true;
						} else {

							flag = false;
							break;
						}
					}
					if (flag) {

						CommonFunction.logStatus("PASS", msg);
					} else {
						CommonFunction.logStatus("FAIL", msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);

				}

				msg = "Sign Out";
				try {
					cfunction.Commmon_Click("xpath", AFTER_LOGIN_XPATH);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e) {
					try {
						driver.navigate().refresh();
						cfunction.sync(5);
						cfunction.Commmon_Click("xpath", AFTER_LOGIN_XPATH);
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e1) {
						e.printStackTrace();

						CommonFunction.logStatusWithException("FAIL", msg, e1);
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}
	}

}

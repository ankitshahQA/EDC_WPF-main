package com.automation.pages;

import org.openqa.selenium.By;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;

public class LoginPage extends TestBase {

	// XPath code
	final String LOGINLINK_Kubernetes_ADMIN_XPATH = "(//a[contains(@href,'admin/login')])[2]";
	final String Kubernetes_USERNAME_ADMIN_XPATH = "//input[@id='user_username']";
	final String Kubernetes_PASSWORD_ADMIN_XPATH = "//input[@id='user_password']";
	final String Kubernetes_LOGIN_ADMIN_XPATH = "//button[@id='signIn']";
	final String Kubernetes_SIGN_OUT_XPATH = "//a[contains(@href,'/admin/logout')]";
	final String LOGIN_FRAME_XPATH = "//iframe[@id='oAuthFrame']";
	final String USERNAME_XPATH = "//input[@id='user_username']";
	final String PASSWORD_XPATH = "//input[@id='user_password']";
	final String USERNAME_ADMIN_XPATH = "//input[@id='usernameUi']";
	final String PASSWORD_ADMIN_XPATH = "//input[@id='password']";
	// final String USERNAME_MANAGER_XPATH = "//input[@id='user_username']";
	// final String PASSWORD_MANAGER_XPATH = "//input[@id='user_password']";
	final String USERNAME_MANAGER_XPATH = "//input[@name='username']";
	final String PASSWORD_MANAGER_XPATH = "//input[@name='password']";
	final String LOGIN_ADMIN_XPATH = "//input[@value='Login']";
	// final String LOGIN_MANAGER_XPATH = "//*[@id='signIn']";
	final String LOGIN_MANAGER_XPATH = "//*[text()='Login' or @id='signIn']";
	final String SIGNINSUBMIT_XPATH = "//*[@id='signIn']";
	final String AFTERSIGN_IN_XPATH = "(//div[contains(@class,'esri-header-account')]//img[@class='esri-header-account-image'])";
	final String AFTERSIGN_IN_XPATH_10_6_1 = "//a[@id='siteHeader-home']";
	final String SIGNIN_XPATH = "//*[text()='Sign In']";
	final String AUTHORIZATION_XPATH = "//*[@id='oauth']";
	final String AFTER_LOGIN_XPATH = "//*[text()='Sign Out']";
	final String Kubernetes_LANDING_PAGE_HEADER_XPATh="//span[text()=' ArcGIS Enterprise Manager ']";
	final String Kubernetes_AFTER_LOGIN_USERNAME_XPATH="//calcite-button//div[@class='user-manager']";
	final String Kubernetes_SIGNOUT_XPATH="//calcite-dropdown-item[@role='menuitemradio']";
	final String SIGN_OUT_XPATH = "//*[text()='Signout']";
	final String LoginHyperLInk_XPATH = "//a[text()='Login']";
	final String KUBERNTES_LoginHyperLInk_XPATH="//a[text()='Sign in']";
	final String LOGO_XPATH = "//div[@id='logoDisplay']/span[text()='ArcGIS Enterprise']";
	final String ENTERPRISE_XPATH = "//div[@id='enterpriseTitle'and @aria-expanded='true']";
	final String ENTERPRISE_SSO_XPATH = "//div[@id='enterprisePanel']//div[contains(@class,'js-entlogin')]";
	final String ENTERPRISE_ARROW_XPATH = "(//span[@class='accordion-icon'])[1]";
	final String ARCGIS_XPATH = "//div[@id='loginTitle' and @aria-expanded='true']";
	final String ARCGIS_ARROW_XPATH = "//div[@id='loginTitle']";
	final String PROFILE_XPATH="//button[contains(@id,'account')]";
	final String APPSWITCHER_JSPATH="document.querySelector('arcgis-app-switcher').shadowRoot.querySelector('calcite-button')";
	final String APPSWITCHER_XPATH="//button[@id='esri-header-apps-control']";
	String msg = "";
	CommonFunction cfunction = new CommonFunction();

	public void verifyPageLoadedand_sign_in() throws Exception {
		// CommonFunction.waitforpagetoload();

		msg = "Wait for sign In page to load";
		try {
			cfunction.waitforpagetoload();
			// if (cfunction.elementexist(LOGIN_FRAME_XPATH)){
			// driver.switchTo().frame("oAuthFrame");
			// }

			cfunction.waitforelement(SIGNIN_XPATH, 100);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				driver.navigate().refresh();
				cfunction.waitforelement(SIGNIN_XPATH, 100);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				e1.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e1);
			}
		}
		msg = "Click on sign in";
		try {
			cfunction.Commmon_Click("xpath", SIGNIN_XPATH);
			CommonFunction.logStatus("PASS", msg);
			// cfunction.switchToLoginWindow();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				cfunction.sync(20);
				cfunction.Commmon_Click("xpath", SIGNIN_XPATH);
				CommonFunction.logStatus("PASS", msg);
				// cfunction.switchToLoginWindow();

			} catch (Exception e1) {
				e1.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e1);
			}

		}

	}

	public void loginToApplication(String usernametxt, String pwdtxt) throws Exception {

		if (driver.findElements(By.xpath(ARCGIS_XPATH)).size() == 0) {
			msg = "ArcGIS Login section expanded";
			cfunction.Commmon_Click("xpath", ARCGIS_ARROW_XPATH);

		}
		msg = "Enter Username";
		try {
			cfunction.CommonTextBox_Input(USERNAME_XPATH, usernametxt);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}
		msg = "Enter Password";
		try {
			cfunction.CommonTextBox_Input(PASSWORD_XPATH, pwdtxt);
			CommonFunction.logStatus("PASS", msg);
			cfunction.sync(2);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}
		msg = "Click on Sign In";
		try {
			cfunction.Commmon_Click("xpath", SIGNINSUBMIT_XPATH);
			CommonFunction.logStatus("PASS", msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}
		
		msg = "Verify user is able to sign in";
		try {
			cfunction.sync(20);
			if(projectVersion.contains("10.")) {
				cfunction.waitforelement(APPSWITCHER_XPATH, 25);
				logindone = true;
				CommonFunction.logStatus("PASS", msg);
			}else {
				if(cfunction.getWebelement_JSpath(APPSWITCHER_JSPATH)!=null) {
					logindone = true;
					CommonFunction.logStatus("PASS", msg);
				}
		}} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
			logindone = false;
		}
		

	}

	public void loginToApplication_10_6_1(String usernametxt, String pwdtxt) throws Exception {

		
		msg = "Enter Username";
		try {
			// if (cfunction.elementexist(LOGIN_FRAME_XPATH)){
			driver.switchTo().frame("oAuthFrame");
			// }
			cfunction.waitforelement(USERNAME_XPATH);
			cfunction.CommonTextBox_Input(USERNAME_XPATH, usernametxt);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}
		msg = "Enter Password";
		try {

			cfunction.CommonTextBox_Input(PASSWORD_XPATH, pwdtxt);
			CommonFunction.logStatus("PASS", msg);
			cfunction.sync(2);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}
		msg = "Click on Sign In";
		try {

			cfunction.Commmon_Click("xpath", SIGNINSUBMIT_XPATH);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		} /*
			 * try { cfunction.waitForloadingToEnd(); } catch (Exception e) {
			 * e.printStackTrace(); CommonFunction.logStatus("FAIL", msg+e); }
			 */
		msg = "Verify user is able to login to application: ";
		try {
			driver.switchTo().defaultContent();
			cfunction.waitforelement(AFTERSIGN_IN_XPATH_10_6_1, 25);
			CommonFunction.logStatus("PASS", msg);
			logindone = true;
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}
		// System.out.println("Logged in as " + usernametxt);
		// }
	}

	public void loginToServerAdmin(String usernametxt, String pwdtxt) throws Exception {
		boolean check = true;
		boolean ADMINLOGINDONE = false;
		msg = "Enter Username for server Admin";
		try {
			cfunction.CommonTextBox_Input(USERNAME_ADMIN_XPATH, usernametxt);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
			check = false;
		}
		if (check) {
			msg = "Enter Password for server Admin ";
			try {
				cfunction.CommonTextBox_Input(PASSWORD_ADMIN_XPATH, pwdtxt);
				CommonFunction.logStatus("PASS", msg);
				cfunction.sync(2);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on Sign In ";
			try {
				cfunction.Commmon_Click("xpath", LOGIN_ADMIN_XPATH);
				CommonFunction.logStatus("PASS", msg);
				cfunction.sync(10);

			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}

			msg = "verify the page login successfully";
			try {

				cfunction.waitforelement(SIGN_OUT_XPATH, 25);
				CommonFunction.logStatus("PASS", msg);
				ADMINLOGINDONE = true;
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
		if (ADMINLOGINDONE) {
			msg = "click on the Sign out button";
			try {
				cfunction.sync(5);
				cfunction.Commmon_Click("xpath", SIGN_OUT_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
		// //System.out.println("Logged in as "+ usernametxt);

	}

	public void loginToServerAdminKubernetes(String usernametxt, String pwdtxt) throws Exception {
		boolean ADMINLOGINDONE = false;
		boolean KubernetesLOGINLINK = false;

		msg = "Click on the Login link";
		try {
			if (cfunction.elementexist(LOGINLINK_Kubernetes_ADMIN_XPATH)) {
				cfunction.Commmon_Click("Xpath", LOGINLINK_Kubernetes_ADMIN_XPATH);
				KubernetesLOGINLINK = true;
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		if (KubernetesLOGINLINK) {

			msg = "Enter Username for server Admin";
			try {
				cfunction.CommonTextBox_Input(Kubernetes_USERNAME_ADMIN_XPATH, usernametxt);
				cfunction.sync(2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}

			msg = "Enter Password for server Admin ";
			try {
				cfunction.CommonTextBox_Input(Kubernetes_PASSWORD_ADMIN_XPATH, pwdtxt);
				CommonFunction.logStatus("PASS", msg);
				cfunction.sync(2);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on Sign In ";
			try {
				cfunction.Commmon_Click("xpath", Kubernetes_LOGIN_ADMIN_XPATH);
				CommonFunction.logStatus("PASS", msg);
				cfunction.sync(10);

			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}

			msg = "verify the page login successfully";
			try {

				cfunction.waitforelement(Kubernetes_SIGN_OUT_XPATH, 25);
				CommonFunction.logStatus("PASS", msg);
				ADMINLOGINDONE = true;
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}

		}

		if (ADMINLOGINDONE) {
			msg = "click on the Sign out button";
			try {
				cfunction.sync(5);
				cfunction.Commmon_Click("xpath", Kubernetes_SIGN_OUT_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}

	}

	public void loginToServerManager(String managerurl, String usernametxt, String pwdtxt) throws Exception {
		boolean check = true;
		msg = "Navigate to manager URL";
		try {
			driver.get(managerurl);
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
				// cfunction.waitforelement(USERNAME_MANAGER_XPATH,30);
				cfunction.CommonTextBox_Input(USERNAME_MANAGER_XPATH, usernametxt);
				cfunction.sync(2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				check = false;
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}
			if (check) {
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
				msg = "Sign Out";
				try {
					cfunction.sync(5);
					cfunction.Commmon_Click("xpath", AFTER_LOGIN_XPATH);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e) {
					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);
					// throw e;
				}

				// System.out.println("Logged in to Server manager as " + usernametxt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}
	}
	
	public void loginToServerManager_Kubernetes(String managerurl, String usernametxt, String pwdtxt) throws Exception {
		boolean check = true;
		msg = "Navigate to manager URL";
		try {
			driver.get(managerurl);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);

			if (driver.findElements(By.xpath(KUBERNTES_LoginHyperLInk_XPATH)).size() > 0) {
				msg = "Click On the Login HyperLink";
				try {
					cfunction.Commmon_Click("xpath", KUBERNTES_LoginHyperLInk_XPATH);
					cfunction.sync(2);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e) {

					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);
				}
			}

			
			msg = "Enter Username for server Manager";
			try {
				// cfunction.waitforelement(USERNAME_MANAGER_XPATH,30);
				cfunction.CommonTextBox_Input(USERNAME_MANAGER_XPATH, usernametxt);
				cfunction.sync(2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				check = false;
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}
			if (check) {
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
				
			}
			
			msg="Verify the landing page header";
			try {
				cfunction.verifyElementText(Kubernetes_LANDING_PAGE_HEADER_XPATh, "ArcGIS Enterprise Manager");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
				
			}
			

			msg = "Verify user is able to sign in";
			try {
				//cfunction.waitforelement(Kubernetes11.5.0_AFTER_LOGIN_USERNAME_XPATH, 20);
				cfunction.Commmon_Click("xpath", Kubernetes_AFTER_LOGIN_USERNAME_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
				check = false;
			}
			if (check) {
				msg = "Sign Out";
				try {
					cfunction.sync(5);
					cfunction.Commmon_Click("xpath", Kubernetes_SIGNOUT_XPATH);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e) {
					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);
					// throw e;
				}

				// System.out.println("Logged in to Server manager as " + usernametxt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}
	}
}

package com.automation.testcases;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.pages.MapPage;

@SuppressWarnings("serial")
public class Login_To_ServerAdmin_TestScenario extends TestBase {
	public static String adminURLTest;
	public static String URL_Mamager = "";
	CommonFunction cfunction = new CommonFunction();
	LoginPage lp = new LoginPage();
	HomePage hp = new HomePage();
	MapPage mp = new MapPage();
	boolean find = true;
	String msg;

	public void login_Kubernetes_site_admin() {
		try {
			int j = no_of_site;
			boolean flag = false;
			for (int i = 0; i < j; i++) {
				if ((!Serverurl[i].isEmpty()) && (!ServerAdminUsername[i].isEmpty())
						&& (!ServerAdminPassword[i].isEmpty())) {
					if (ServerAdminRole[i].equalsIgnoreCase("Hosting Server")) {
						msg = "Login to Kubernetes Server Admin url with role 'Hosting' " + Serverurl[i];
						childnode = logger.createNode(msg);
						flag = true;
						driver.get(Serverurl[i] + "/admin");
						CommonFunction.waitforpagetoload();
						driver.navigate().refresh();
						CommonFunction.logStatus("PASS", msg);
						lp.loginToServerAdminKubernetes(ServerAdminUsername[i], ServerAdminPassword[i]);

					}
				}

			}
			if (!flag) {
				CommonFunction.logStatus("WARNING", "This Test can be perform only for Server Role 'Hosting Server' ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}
	}

	public void login_site_admin() {
		try {
			int j = no_of_site;
			boolean flag = false;
			for (int i = 0; i < j; i++) {
				if ((!Serverurl[i].isEmpty()) && (!ServerAdminUsername[i].isEmpty())
						&& (!ServerAdminPassword[i].isEmpty())) {
					if (ServerAdminRole[i].equalsIgnoreCase("Hosting Server")) {
						msg = "Login to Server Admin url with role 'Hosting' " + Serverurl[i];
						childnode = logger.createNode(msg);
						flag = true;
						driver.get(Serverurl[i] + "/admin");
						CommonFunction.waitforpagetoload();
						driver.navigate().refresh();
						CommonFunction.logStatus("PASS", msg);
						lp.loginToServerAdmin(ServerAdminUsername[i], ServerAdminPassword[i]);

					}
				}

			}
			if (!flag) {
				CommonFunction.logStatus("WARNING", "This Test can be perform only for Server Role 'Hosting Server' ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}
	}

	public void login_Serveradmin() {

		try {
			int j = no_of_site;
			boolean flag = false;

			for (int i = 0; i < j; i++) {
				String Server = Serverurl[i];
				msg = "Login to Server Admin url with role 'Hosting' " + Serverurl[i];
				childnode = logger.createNode(msg);
				if (ServerAdminRole[i].equalsIgnoreCase("Hosting Server")) {
				if ((!Serverurl[i].isEmpty()) && (!ServerAdminUsername[i].isEmpty())
						&& (!ServerAdminPassword[i].isEmpty())) {
					flag = true;
						if ((projectVersion.equalsIgnoreCase("Kubernetes11.5.0")||projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) && (flagKUB)) {
							flag = true;
							driver.get(Serverurl[i] + "/admin");
							CommonFunction.waitforpagetoload();
							driver.navigate().refresh();
							CommonFunction.logStatus("PASS", msg);
							lp.loginToServerAdminKubernetes(ServerAdminUsername[i], ServerAdminPassword[i]);

						} else if ((!flagKUB) && (!(projectVersion.equalsIgnoreCase("Kubernetes11.5.0")||projectVersion.equalsIgnoreCase("Kubernetes12.1.0")))) {
							flag = true;
							driver.get(Serverurl[i] + "/admin");
							CommonFunction.waitforpagetoload();
							driver.navigate().refresh();
							CommonFunction.logStatus("PASS", msg);
							lp.loginToServerAdmin(ServerAdminUsername[i], ServerAdminPassword[i]);

						} else if ((flagKUB) && (!(projectVersion.equalsIgnoreCase("Kubernetes11.5.0")||projectVersion.equalsIgnoreCase("Kubernetes12.1.0")))){
							CommonFunction.logStatus("WARNING",
									"<b>Action Required:</b> The Kubernetes checkbox has been selected, but the Kubernetes Server Admin URL provided is invalid. Kindly update it with a correctly formatted and valid URL.");
						} else if (!(flagKUB) && ((projectVersion.equalsIgnoreCase("Kubernetes11.5.0")||projectVersion.equalsIgnoreCase("Kubernetes12.1.0")))) {
							CommonFunction.logStatus("WARNING",
									"<b>Action Required:</b> Select the Kubernetes checkbox in the Enterprise Development Check Tool, as you have provided the Kubernetes Server Admin URL.");
							}
					
				} else {
					CommonFunction.logStatus("WARNING",
							"<b>Action Required:</b> Server Admin URL or Server Admin Username or Server Admin Password is not provided in the Enterprise Development Check Tool. Please provide the required details to perform this test.");
				}
			}
		}
			if (!flag) {
				CommonFunction.logStatus("WARNING",
						" <b>Action Required:</b> This Test can be perform only for Server Role 'Hosting Server' ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}
	}

}

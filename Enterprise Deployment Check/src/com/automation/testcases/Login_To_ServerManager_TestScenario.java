package com.automation.testcases;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;
import com.automation.pages.LoginPage;

public class Login_To_ServerManager_TestScenario extends TestBase {

	CommonFunction cfunction = new CommonFunction();
	LoginPage lp = new LoginPage();
	String msg = "";
	// String managerurl=null;
	boolean find = true;

	public void login_server_manager() throws InterruptedException {
		try {
			int j = no_of_site;
			boolean flag=false;
			for (int i = 0; i < j; i++) {

				if (!Serverurl[i].isEmpty()) {
					
					String managerURL = Serverurl[i].trim() + "/manager";
					if (ServerAdminRole[i].contains("Hosting")) {
						msg = "Login to Server Manager URL " + (i + 1);
						childnode = logger.createNode(msg);
						flag=true;
						if (ServerFederated[i]) {
							if ((!(PortalUserName.isEmpty())) && (!(PortalPassword.isEmpty()))) {
								lp.loginToServerManager(managerURL, PortalUserName, PortalPassword);

							} else {
								CommonFunction.logStatus("FAIL",
										"Please provide details for 'Portal Username' and 'Portal Password'");
							}
						} else {
							if ((!(ServerAdminUsername[i].isEmpty())) && (!(ServerAdminPassword[i].isEmpty()))) {

								lp.loginToServerManager(managerURL, ServerAdminUsername[i], ServerAdminPassword[i]);
							} else {
								CommonFunction.logStatus("FAIL", "Please provide details for 'Server Admin Username "
										+ (i + 1) + "'a nd 'Server Admin Password '" + (i + 1));
							}
						}
					} 
					}
			}
				if(!flag) {
						CommonFunction.logStatus("WARNING", "This Test can be perform only for Server Role 'Hosting Server' ");
					}
				
			

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}
	}
	
	public void login_server_manager_Kubernetes() throws InterruptedException {
		try {
			int j = no_of_site;
			boolean flag=false;
			for (int i = 0; i < j; i++) {

				if (!Serverurl[i].isEmpty()) {
					
					String managerURL = Serverurl[i].trim() + "/manager";
					if (ServerAdminRole[i].contains("Hosting")) {
						msg = "Login to Server Manager URL " + (i + 1);
						childnode = logger.createNode(msg);
						flag=true;
						if (ServerFederated[i]) {
							if ((!(PortalUserName.isEmpty())) && (!(PortalPassword.isEmpty()))) {
								lp.loginToServerManager_Kubernetes(managerURL, PortalUserName, PortalPassword);

							} else {
								CommonFunction.logStatus("FAIL",
										"Please provide details for 'Portal Username' and 'Portal Password'");
							}
						} else {
							if ((!(ServerAdminUsername[i].isEmpty())) && (!(ServerAdminPassword[i].isEmpty()))) {

								lp.loginToServerManager_Kubernetes(managerURL, ServerAdminUsername[i], ServerAdminPassword[i]);
							} else {
								CommonFunction.logStatus("FAIL", "Please provide details for 'Server Admin Username "
										+ (i + 1) + "'a nd 'Server Admin Password '" + (i + 1));
							}
						}
					} 
					}
			}
				if(!flag) {
						CommonFunction.logStatus("WARNING", "This Test can be perform only for Server Role 'Hosting Server' ");
					}
				
			

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}
	}
	
	public void login_ServerManager() throws InterruptedException {

		try {
			int j = no_of_site;
			boolean flag = false;
			for (int i = 0; i < j; i++) {
				flag = false;
				
				String managerURL = Serverurl[i].trim() + "/manager";
				if (ServerAdminRole[i].contains("Hosting")) {

					msg = "Login to Server Manager URL " + (i + 1);
					childnode = logger.createNode(msg);
					flag = true;
					if (ServerFederated[i]) {
						if ((!(PortalUserName.isEmpty())) && (!(PortalPassword.isEmpty()))) {
							if (((projectVersion.equalsIgnoreCase("Kubernetes11.5.0")||projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) && (flagKUB)) {
								flag = true;
								CommonFunction.waitforpagetoload();
								driver.navigate().refresh();
								CommonFunction.logStatus("PASS", msg);
								lp.loginToServerManager_Kubernetes(managerURL, PortalUserName, PortalPassword);

							} else if ((!flagKUB) && (!(projectVersion.equalsIgnoreCase("Kubernetes11.5.0")||projectVersion.equalsIgnoreCase("Kubernetes12.1.0")))) {
								flag = true;
								CommonFunction.waitforpagetoload();
								driver.navigate().refresh();
								CommonFunction.logStatus("PASS", msg);
								lp.loginToServerManager(managerURL, PortalUserName, PortalPassword);

							} else if ((flagKUB) && (!(projectVersion.equalsIgnoreCase("Kubernetes11.5.0")||projectVersion.equalsIgnoreCase("Kubernetes12.1.0")))) {
								CommonFunction.logStatus("WARNING",
										"<b>Action Required:</b> The Kubernetes checkbox has been selected, but the Kubernetes Server Manager URL provided is invalid. Kindly update it with a correctly formatted and valid URL.");
							} else if (!(flagKUB) && ((projectVersion.equalsIgnoreCase("Kubernetes11.5.0")||projectVersion.equalsIgnoreCase("Kubernetes12.1.0")))) {
								CommonFunction.logStatus("WARNING",
										"<b>Action Required:</b> Select the Kubernetes checkbox in the Enterprise Development Check Tool, as you have provided the Kubernetes Server ManagerL URL.");
							}

						} else {
							CommonFunction.logStatus("WARNING",
									"<b>Action Required:</b> Server ManagerL URL or Portal Username or  Portal Password is not provided in the Enterprise Development Check Tool. Please provide the required details to perform this test.");
						}
					} else {
						// Non-federated standalone server: authenticate with the per-slot
						// Server Admin credentials (Portal credentials are not valid here).
						if ((!(ServerAdminUsername[i].isEmpty())) && (!(ServerAdminPassword[i].isEmpty()))) {
							if (((projectVersion.equalsIgnoreCase("Kubernetes11.5.0")||projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) && (flagKUB)) {
								flag = true;
								CommonFunction.waitforpagetoload();
								driver.navigate().refresh();
								CommonFunction.logStatus("PASS", msg);
								lp.loginToServerManager_Kubernetes(managerURL, ServerAdminUsername[i], ServerAdminPassword[i]);

							} else if ((!flagKUB) && (!(projectVersion.equalsIgnoreCase("Kubernetes11.5.0")||projectVersion.equalsIgnoreCase("Kubernetes12.1.0")))) {
								flag = true;
								CommonFunction.waitforpagetoload();
								driver.navigate().refresh();
								CommonFunction.logStatus("PASS", msg);
								lp.loginToServerManager(managerURL, ServerAdminUsername[i], ServerAdminPassword[i]);

							} else if ((flagKUB) && (!(projectVersion.equalsIgnoreCase("Kubernetes11.5.0")||projectVersion.equalsIgnoreCase("Kubernetes12.1.0")))) {
								CommonFunction.logStatus("WARNING",
										"<b>Action Required:</b> The Kubernetes checkbox has been selected, but the Kubernetes Server Manager URL provided is invalid. Kindly update it with a correctly formatted and valid URL.");
							} else if (!(flagKUB) && ((projectVersion.equalsIgnoreCase("Kubernetes11.5.0")||projectVersion.equalsIgnoreCase("Kubernetes12.1.0")))) {
								CommonFunction.logStatus("WARNING",
										"<b>Action Required:</b> Select the Kubernetes checkbox in the Enterprise Development Check Tool, as you have provided the Kubernetes Server Manager URL.");
							}
						} else {
							CommonFunction.logStatus("FAIL",
									"Please provide details for 'Server Admin Username " + (i + 1)
											+ "' and 'Server Admin Password " + (i + 1) + "'");
						}
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


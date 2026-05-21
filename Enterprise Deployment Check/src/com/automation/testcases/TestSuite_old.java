package com.automation.testcases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;

@SuppressWarnings("serial")
public class TestSuite_old extends TestBase {

	@SuppressWarnings("unused")

	boolean flagdevtopia = false;
	int cnt = 0;
	String msg = "";

	@BeforeTest
	@Parameters({ "platform", "browser" })
	public void SetUpAutomation(String platform, String browser) throws Exception {
		// Fetch the test url from data source
		try {
			try {
				setTextGreen("Read Configuration Properties file");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			configSetup();
			System.out.println("config setup done");
			readfromconfigdisplay();
			System.out.println("config read done");
			System.out.println("Setup Extent Report");
			try {
				setTextGreen("Setting Reports");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			TestBase.extentReportSetup(platform, browser);
			try {
				setreport();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (!stop) {

				try {
					setTextGreen("Instantiating Browser.....");

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				TestBase.setup(platform, browser);
			}
			// setting submit defect to false
			submitdefect = false;

			// publish("Testing publish");
			try {
				setValue(10);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (stop) {
				if (driver != null) {
					driver.close();
					driver.quit();
				}
			}
			if (session && !stop) {
				extentReportbrowserversionSetup();
				setCounter();
			}
			// Extent Report setup function

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@BeforeMethod
	@Parameters({ "platform", "browser" })
	public void setUpMethod(String platform, String browser, Method m) throws Exception {
		testcaseName = m.getName();
		resetStepCount();
		// VDFailResult = 0;
	}

	@Test(priority = 1)
	@Parameters({ "browser" })
	public void ServerAdminLogin(String browser) throws InterruptedException, IOException {
		if (!stop) {
			if (flagloginadmin) {
				cnt = cnt + 1;
				if (session) {
					CommonFunction.setUpTest(flagdevtopia);
					try {
						setTextGreen("Login to Server Admin functionality started");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					Login_To_ServerAdmin_TestScenario ts1 = new Login_To_ServerAdmin_TestScenario();
					ts1.login_site_admin();

				}

				else {
					msg = "Not able to proceed with test steps as not able to create browser session";
					throw new SkipException(msg);
				}

				try {
					setValue((100 / counter) * cnt);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Test(priority = 2)
	@Parameters({ "browser" })
	public void ServerManagerLogin(String browser) throws InterruptedException, IOException {
		if (!stop) {
			if (flagloginmanager && (!flagdatastore)) {
				cnt = cnt + 1;
				if (session) {
					CommonFunction.setUpTest(flagdevtopia);
					try {
						setTextGreen("Login to Server Manager functionality started");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					Login_To_ServerManager_TestScenario ts2 = new Login_To_ServerManager_TestScenario();
					ts2.login_server_manager();

				} else {
					msg = "Not able to proceed with test steps as not able to create browser session";
					throw new SkipException(msg);
				}

				try {
					setValue((100 / counter) * cnt);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Test(priority = 3)
	@Parameters({ "browser" })
	public void ValidateDataStores(String browser) throws Exception {
		if (!stop) {
			if (flagloginmanager && flagdatastore) {

				cnt = cnt + 1;
				if (session) {
					CommonFunction.setUpTest(flagdevtopia);
					try {
						setTextGreen("Validating Data Stores functionality started");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					Content_Functionality_TestScenario ts4 = new Content_Functionality_TestScenario();
					ts4.Validating_Data_Stores_functionality();

				} else {
					msg = "Not able to proceed with test steps as not able to create browser session";
					throw new SkipException(msg);
				}

				try {
					setValue((100 / counter) * cnt);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	@Test(priority = 4)
	@Parameters({ "browser" })
	public void PortalLogin(String browser) throws Exception {
		if (!stop) {
			if (flagloginportal) {
				cnt = cnt + 1;
				if (session) {
					CommonFunction.setUpTest(flagdevtopia);
					try {
						setTextGreen("Login to Portal functionality started");
					} catch (Exception ex) {
						ex.printStackTrace();
					}

					Login_To_Portal_TestScenario ts3 = new Login_To_Portal_TestScenario();
					ts3.portal_login();
					if (logindone) {
						getappversion();
						extentReportappversionSetup();
						Content_Functionality_TestScenario ts4 = new Content_Functionality_TestScenario();
						ts4.Navigate_content();
						setTestVariables();

					} else {
						msg = "Not able to proceed with test steps as not able to Login to application";
						throw new SkipException(msg);
					}
				} else {
					msg = "Not able to proceed with test steps as not able to create browser session";
					throw new SkipException(msg);
				}

				try {
					setValue((100 / counter) * cnt);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}
	}



	@Test(priority = 5)
	@Parameters({ "browser" })
	public void CreateFolder(String browser) throws InterruptedException, IOException {
		if (!stop) {
			if (flagcontent) {
				cnt = cnt + 1;
				if (session) {

					if (logindone) {
						CommonFunction.setUpTest(flagdevtopia);
						try {
							setTextGreen("Create New Folder");
						} catch (Exception ex) {
							ex.printStackTrace();
						}

						Content_Functionality_TestScenario ts4 = new Content_Functionality_TestScenario();
						ts4.create_folder_functionality();

					} else {
						msg = "Not able to proceed with test steps as not able to Login to application";
						throw new SkipException(msg);
					}

				} else {
					msg = "Not able to proceed with test steps as not able to create browser session";
					throw new SkipException(msg);
				}

				try {
					setValue((100 / counter) * cnt);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Test(priority = 6)
	@Parameters({ "browser" })
	public void CreateLayer(String browser) throws Exception {
		if (!stop) {
			if (flagfeaturelayer) {
				cnt = cnt + 1;
				if (flagtilelayer) {
					cnt = cnt + 1;
				}
				if (session) {

					if (logindone) {
						CommonFunction.setUpTest(flagdevtopia);
						if (foldercreateddone) {
							try {
								setTextGreen("Create Feature/Tile Layer functionality started");
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							Content_Functionality_TestScenario ts4 = new Content_Functionality_TestScenario();
							ts4.feature_layer_functionality();
						} else {
							msg = "Not able to proceed with test steps as not able to Create Folder";
							throw new SkipException(msg);
						}
					} else {
						msg = "Not able to proceed with test steps as not able to Login to application";
						throw new SkipException(msg);
					}

				} else {
					msg = "Not able to proceed with test steps as not able to create browser session";
					throw new SkipException(msg);
				}
				try {
					setValue((100 / counter) * cnt);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	@Test(priority = 7)
	@Parameters({ "browser" })
	public void VerifySceneLayerFunctionality(String browser) throws Exception {

		if (flagscenelayer) {
			cnt = cnt + 1;
			if (session) {
				if (logindone) {
					CommonFunction.setUpTest(flagdevtopia);
					if (projectVersion.contains("11.")) {
						try {
							setTextGreen("Scene Layer Creation started");
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						CreateScene_TestScenario ts10 = new CreateScene_TestScenario();
						ts10.scenelayer_functionality();

					}

					else {
						msg = "Not able to proceed with test steps as it is specific to 11.1, 11.2 and 11.3 only";
						CommonFunction.logStatus("SKIP", msg);
						throw new SkipException(msg);
					}
				} else {
					msg = "Not able to proceed with test steps as not able to Login to application";
					throw new SkipException(msg);
				}

			} else {
				msg = "Not able to proceed with test steps as not able to create browser session";
				throw new SkipException(msg);
			}

			try {
				setValue((100 / counter) * cnt);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	@Test(priority = 8)
	@Parameters({ "browser" })
	public void CreateWebMap(String browser) throws Exception {
		if (!stop) {
			if (flagmap) {
				cnt = cnt + 1;
				if (session) {
					CommonFunction.setUpTest(flagdevtopia);
					if (logindone) {
						if (foldercreateddone) {
							try {
								setTextGreen("Create WebMap functionality started");
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							Map_Functionality_TestScenario ts7 = new Map_Functionality_TestScenario();

							ts7.map_functionality();
						} else {
							msg = "Not able to proceed with test steps as not able to Create Folder";
							throw new SkipException(msg);
						}
					} else {
						msg = "Not able to proceed with test steps as not able to Login to application";
						throw new SkipException(msg);
					}

				} else {
					msg = "Not able to proceed with test steps as not able to create browser session";
					throw new SkipException(msg);
				}

				try {
					setValue((100 / counter) * cnt);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Test(priority = 9)
	@Parameters({ "browser" })
	public void CreateWebAppBuilderApp(String browser) throws Exception {
		if (!stop) {
			if (flagwebappbuilder) {
				cnt = cnt + 1;
				if (session) {
					if (logindone) {
						CommonFunction.setUpTest(flagdevtopia);

						if (foldercreateddone) {
							try {
								setTextGreen("Create WebApp Builder functionality started");
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							WebAppBuilder_Functionality_TestScenario ts4 = new WebAppBuilder_Functionality_TestScenario();
							ts4.webappbuilder_functionality_11_3_0();

						} else {
							msg = "Not able to proceed with test steps as not able to Create Folder";
							throw new SkipException(msg);
						}
					} else {
						msg = "Not able to proceed with test steps as not able to Login to application";
						throw new SkipException(msg);
					}

				} else {
					msg = "Not able to proceed with test steps as not able to create browser session";
					throw new SkipException(msg);
				}
				try {
					setValue((100 / counter) * cnt);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

	}

	
	@Test(priority = 10)
	@Parameters({ "browser" })
	public void CreateGroups(String browser) throws Exception {
		if (!stop) {

			if (flaggroup) {
				cnt = cnt + 1;
				if (session) {
					CommonFunction.setUpTest(flagdevtopia);
					if (logindone) {
						try {
							setTextGreen("Create Group functionality started");
						} catch (Exception ex) {
							ex.printStackTrace();
						}

						Groups_TestScenario ts5 = new Groups_TestScenario();
						ts5.groups_functionality_10_8_0();

					} else {
						msg = "Not able to proceed with test steps as not able to Login to application";
						throw new SkipException(msg);
					}

				} else {
					msg = "Not able to proceed with test steps as not able to create browser session";
					throw new SkipException(msg);
				}

				try {
					setValue((100 / counter) * cnt);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Test(priority = 11)
	@Parameters({ "browser" })
	public void VerifyOrganizationFunctionality(String browser) throws Exception {
		if (!stop) {
			if (flagorganization) {
				cnt = cnt + 1;
				if (session) {
					CommonFunction.setUpTest(flagdevtopia);
					if (logindone) {

						try {
							setTextGreen("Verify Organization functionality started");
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						Organization_TestScenario ts6 = new Organization_TestScenario();
						ts6.organization_functionality();

					} else {
						msg = "Not able to proceed with test steps as not able to Login to application";
						throw new SkipException(msg);
					}

				} else {
					msg = "Not able to proceed with test steps as not able to create browser session";
					throw new SkipException(msg);
				}
				try {
					setValue((100 / counter) * cnt);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	@Test(priority = 12)
	@Parameters({ "browser" })
	public void ValidateServerRoles(String browser) throws Exception {
		if (!stop) {
			if (flagServerRole) {
				cnt = cnt + 1;
				if (session) {
					if (logindone) {
						CommonFunction.setUpTest(flagdevtopia);
						if (projectVersion.contains("11")) {
							try {
								setTextGreen("Validating Server Roles functionality started");
							} catch (Exception ex) {
								ex.printStackTrace();
							}
							Content_Functionality_TestScenario ts4 = new Content_Functionality_TestScenario();
							ts4.Validating_Server_functionality();

						} else {
							msg = "Not able to proceed with test steps as it is specific to version above 11 only";
							CommonFunction.logStatus("SKIP", msg);
							throw new SkipException(msg);
						}
					} else {
						msg = "Not able to proceed with test steps as not able to Login to application";
						throw new SkipException(msg);
					}

				} else {
					msg = "Not able to proceed with test steps as not able to create browser session";
					throw new SkipException(msg);
				}

				try {
					setValue((100 / counter) * cnt);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		if (session) {
			String msg = "";
			try {
				extent.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@AfterTest
	public void endReport() throws Exception {

		if (session) {
			System.out.println("After Test");
			try {
				// GeneratetxtResultFile.generatetxtResultFileforCITool(extentReportFilePath_BrowserNameOnly);
				extent.flush();
				softAssert.assertAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@AfterSuite
	@Parameters({ "browser" })
	public void DeleteCreatedTestData(String platform) throws Exception {
		System.out.println("After SUIT");
		flagreport = true;
		if (!session) {
			try {
				// JOptionPane.showMessageDialog(frame1, globalmsg, "Enterprise Installation
				// Sanity Test Error",
				// JOptionPane.ERROR_MESSAGE);
				try {
					CommonFunction.log_global_failStatus();
					setTextRed(globalmsg);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				System.out.println("quit");

			} catch (Exception e) {
				System.out.println("Dialog box not displayed");
				// CommonFunction.logStatus("PASS", "Suit Completed");
			}
		} else {


			if (flagfeaturelayer || flagtilelayer || flagwebappbuilder || flagmap || flagcontent
					|| (flagscenelayer && scenecreated)) {
				if ((projectVersion.equalsIgnoreCase("10.6.1") || projectVersion.equalsIgnoreCase("10.7.1")
						|| projectVersion.equalsIgnoreCase("10.8.0") || projectVersion.equalsIgnoreCase("10.8.1")
						|| projectVersion.equalsIgnoreCase("10.9.0") || projectVersion.equalsIgnoreCase("10.9.1")
						|| projectVersion.equalsIgnoreCase("11.0.0") || projectVersion.equalsIgnoreCase("11.1.0")
						|| projectVersion.equalsIgnoreCase("11.2.0") || projectVersion.equalsIgnoreCase("11.3.0")
						|| (projectVersion.equalsIgnoreCase("11.4.0"))
						|| (projectVersion.equalsIgnoreCase("11.5.0")))) {
					CommonFunction.setUpTest(flagdevtopia);
					Content_Functionality_TestScenario ts8 = new Content_Functionality_TestScenario();
					try {
						setTextGreen("Deleting created test data........");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					ts8.content_delete_functionality();

				} else {
					try {
						String str1 = null;
						// JavascriptExecutor js = (JavascriptExecutor) driver;
						if (projectVersion != "Not found") {
							str1 = "Test can only be run on portal version 10.6.1 or 10.7.1 or 10.8.0 or 10.8.1 or 10.9.0 or 10.9.1 or 11.0.0 or 11.1.0 or 11.2.0 or 11.3.0 or 11.4.0 or 11.5.0. It is not compatible with your portal application version: "
									+ projectVersion;

						} else {
							str1 = "Test can only be run on portal version 10.6.1 or 10.7.1 or 10.8.0 or 10.8.1 or 10.9.0 or 10.9.1or 11.0.0 or 11.1.0 or 11.2.0 or 11.3.0 or 11.4.0 or 11.5.0. Not able to find current Portal application version";
						}
						CommonFunction.logStatus("FAIL", str1);
						setTextRed(str1);

					} catch (Exception e) {
						System.out.println(e);

					}
				}
			}

			try {
				printwrite.close();
				setValue(100);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			System.out.println("quit");
			if (stop) {
				close1();
			} else {
				close();
			}

			System.out.println("close");
		} // end of session else
	}
} // end of stop if

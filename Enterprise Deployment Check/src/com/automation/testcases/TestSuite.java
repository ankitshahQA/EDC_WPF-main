package com.automation.testcases;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.PythonAPI.PythonHelp;
import com.automation.library.CommonFunction;
import com.automation.library.TestBase;

@SuppressWarnings("serial")
public class TestSuite extends TestBase {

	@SuppressWarnings("unused")
	boolean flagdevtopia = false;
	int cnt = 0;
	String msg = "";

	/**
	 * Set to {@code true} once the test-data cleanup ({@code DeleteAllFolder})
	 * has run, regardless of which path invoked it (TestNG {@code @AfterSuite}
	 * or the JVM shutdown hook). Prevents the cleanup from running twice on a
	 * normal headless exit, where {@code @AfterSuite} fires first and the
	 * shutdown hook fires immediately afterward as the JVM tears down.
	 */
	private static volatile boolean cleanupDone = false;

	@BeforeTest
	@Parameters({ "platform" })
	public void SetUpAutomation(String platform) throws Exception {
		// Fetch the test url from data source
		try {

			try {
				setTextGreen("Read Configuration Properties file");
			} catch (Exception ex) {

			}

			if (flagrun) {
				System.out.println("Setup Extent Report");
				try {
					setTextGreen("Setting Reports");
				} catch (Exception ex) {
					// ex.printStackTrace();
				}
				TestBase.extentReportSetup(platform, Browser);
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

					TestBase.setup(platform, Browser);
					System.out.println("testbase setup");
				}
				submitdefect = false;
				if (uirun) {

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
				}

				if (session && !stop) {
					extentReportbrowserversionSetup();
					System.out.println("Browser version setup");
					setCounter();
				}
				// Fetch the project version through python script
				PythonHelp.getProjectVersion();
				if (!DeploymentType.isEmpty() && !Appversion.isEmpty()) {
					if (DeploymentType.contains("Kubernetes")) {
						projectVersion = "Kubernetes" + Appversion;
					} else {
						projectVersion = Appversion;
					}
				} else {
					projectVersion = "Not found";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@BeforeMethod
	public void setUpMethod(Method m) throws Exception {
		testcaseName = m.getName();
		resetStepCount();
		VDFailResult = 0;
		extentNodeCreated = false;
		childnode = null;
	}

	@Test(priority = 1)
	public void ServerAdminLogin() throws InterruptedException, IOException {
		if (stop) {
			skipTest("Not able to proceed with 'Login to Server Admin' as previous step requested stop");
			return;
		}
		if (!stop) {
			if (flagloginadmin) {
				cnt = cnt + 1;
				if (session) {
					CommonFunction.setUpTest(flagdevtopia);
					try {
						setTextGreen("Login to Server Admin functionality started");
					} catch (Exception ex) {
					}
					Login_To_ServerAdmin_TestScenario ts1 = new Login_To_ServerAdmin_TestScenario();
					ts1.login_Serveradmin();

				}

				else {
					msg = "Not able to proceed with 'Login to Server Admin functionality' as not able to create browser session";
					skipTest(msg);
				}
				if (uirun) {
					try {
						setValue((100 / counter) * cnt);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}

	@Test(priority = 2)
	public void ServerManagerLogin() throws InterruptedException, IOException {
		if (stop) {
			skipTest("Not able to proceed with 'Login to Server Manager' as previous step requested stop");
			return;
		}
		if (!stop) {
			if (flagloginmanager) {
				cnt = cnt + 1;
				if (session) {
					CommonFunction.setUpTest(flagdevtopia);

					try {
						setTextGreen("Login to Server Manager functionality started");
					} catch (Exception ex) {
						// ex.printStackTrace();
					}
					Login_To_ServerManager_TestScenario ts2 = new Login_To_ServerManager_TestScenario();
					ts2.login_ServerManager();

				} else {
					msg = "Not able to proceed with 'Login to Server Manager' steps as not able to create browser session";
					skipTest(msg);
				}
				if (uirun) {
					try {
						setValue((100 / counter) * cnt);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}

	@Test(priority = 3)
	public void ValidateDataStores() throws Exception {
		if (stop) {
			skipTest("Not able to proceed with 'Validating Data Stores' as previous step requested stop");
			return;
		}
		if (!stop) {
			if (flagdatastore) {
				cnt = cnt + 1;

				CommonFunction.setUpTest(flagdevtopia);

				try {
					setTextGreen("Validating Data Stores functionality started");
				} catch (Exception ex) {
					// ex.printStackTrace();
				}
				if (flagKUB) { // projectVersion is Kubernetes{
					msg = "Not able to proceed with 'Validating Data Stores' steps as it is not supported the 'Kubernetes' Enterprise version";
					skipTest(msg);

				} else {
					PythonHelp.validateDataStoresPython();

				}

			}
			if (uirun) {
				try {
					setValue((100 / counter) * cnt);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	@Test(priority = 4)
	public void PortalLogin() throws Exception {
		if (stop) {
			skipTest("Not able to proceed with 'Login to Portal' as previous step requested stop");
			return;
		}
		if (!stop) {
			if (flagloginportal) {
				cnt = cnt + 1;
				if (session) {
					CommonFunction.setUpTest(flagdevtopia);

					try {
						setTextGreen("Login to Portal functionality started");
					} catch (Exception ex) {
						// ex.printStackTrace();
					}

					Login_To_Portal_TestScenario ts3 = new Login_To_Portal_TestScenario();
					ts3.portal_login();

					if (logindone) {
						if (projectVersion.contains("UNKNOWN")) {
							getappversion();
						}
						skipSuite = false;// reset the value skipsuite to false for each login
						foldercreateddone = false;// reset the value of folder created to false for each login
						ObjectStore = false;// reset the value of object store to false for each login
						if (projectVersion.equalsIgnoreCase("Not found")) {
							skipSuite = true;
							if (skipSuite) {
								skipTest("Skipping test execution");
							}

						}
						if (projectVersion.startsWith("Kubernetes")) {
							PythonHelp.ValidateObjectDataStore_K8S();
						} else {
							PythonHelp.ValidateObjectDataStore();
						}
						extentReportappversionSetup();
						Content_Functionality_TestScenario ts4 = new Content_Functionality_TestScenario();
						ts4.Navigate_content();
						setTestVariables();
					} else {
						msg = "Not able to proceed with 'Login to Portal' steps as not able to Login to application";
						skipTest(msg);
					}
				} else {
					msg = "Not able to proceed with 'Login to Portal' steps as not able to create browser session";
					skipTest(msg);
				}
				if (uirun) {
					try {
						setValue((100 / counter) * cnt);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			}
		}
	}

	// @Test(priority = 5)
	public void CreateFolder() throws InterruptedException, IOException {
		if (!stop) {
			if (flagcontent) {
				cnt = cnt + 1;
				if (session) {

					if (logindone) {
						CommonFunction.setUpTest(flagdevtopia);

						try {
							setTextGreen("Create New Folder");
						} catch (Exception ex) {
							// ex.printStackTrace();
						}

						Content_Functionality_TestScenario ts4 = new Content_Functionality_TestScenario();
						ts4.create_folder_functionality();

					} else {
						msg = "Not able to proceed with 'Create New Folder' steps as not able to Login to application";
						skipTest(msg);
					}

				} else {
					msg = "Not able to proceed with 'Create New Folder' steps as not able to create browser session";
					skipTest(msg);
				}
				if (uirun) {
					try {
						setValue((100 / counter) * cnt);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}

	@Test(priority = 6)
	public void CreateFeatureLayer() throws Exception {
		if (stop) {
			skipTest("Not able to proceed with 'Create Feature Layer' as previous step requested stop");
			return;
		}
		if (skipSuite) {
			skipTest(
					"Skipping 'Create Feature Layer' as previous critical step failed (Portal login / version not found)");
			return;
		}
		if (!stop) {
			if (!skipSuite) {
				if (flagfeaturelayer) {
					cnt = cnt + 1;
					if (session) {
						if (logindone) {
							CommonFunction.setUpTest(flagdevtopia);
							PythonHelp.CreateFolder();
							if (foldercreateddone) {

								try {
									setTextGreen("Create Feature Layer functionality started");
								} catch (Exception ex) {
								}
								Content_Functionality_TestScenario ts4 = new Content_Functionality_TestScenario();
								ts4.feature_layer_functionality();

							} else {
								msg = "Not able to proceed with 'Create Feature Layer' steps as not able to Create Folder";
								skipTest(msg);
							}
						} else {
							msg = "Not able to proceed with 'Create Feature Layer' steps as not able to Login to application";
							skipTest(msg);
						}

					} else {
						msg = "Not able to proceed with 'Create Feature Layer' steps as not able to create browser session";
						skipTest(msg);
					}
					if (uirun) {
						try {
							setValue((100 / counter) * cnt);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

	@Test(priority = 7)
	public void CreateTileLayer() throws Exception {
		if (stop) {
			skipTest("Not able to proceed with 'Create Tile Layer' as previous step requested stop");
			return;
		}
		if (skipSuite) {
			skipTest(
					"Skipping 'Create Tile Layer' as previous critical step failed (Portal login / version not found)");
			return;
		}
		if (!stop) {
			if (!skipSuite) {
				if (flagtilelayer) {
					cnt = cnt + 1;

					if (session) {

						if (logindone) {

							CommonFunction.setUpTest(flagdevtopia);
							if (!foldercreateddone) {
								PythonHelp.CreateFolder();
							}
							if (foldercreateddone) {
								try {
									setTextGreen("Create Tile Layer functionality started");
								} catch (Exception ex) {
									// ex.printStackTrace();
								}
								if (ObjectStore) {
									Content_Functionality_TestScenario ts4 = new Content_Functionality_TestScenario();
									ts4.tile_layer_functionality();

								} else {
									msg = "Not able to proceed with 'Create Tile Layer' steps as 'Object/Tile cache DataStore is not installed/configured in the environment'";
									skipTest(msg);
								}
							} else {
								msg = "Not able to proceed with 'Create Tile Layer' steps as not able to Create Folder";
								skipTest(msg);
							}

						} else {
							msg = "Not able to proceed with 'Create Tile Layer' steps as not able to Login to application";
							skipTest(msg);
						}

					} else {
						msg = "Not able to proceed with test 'Create Tile Layer' as not able to create browser session";
						skipTest(msg);
					}
					if (uirun) {
						try {
							setValue((100 / counter) * cnt);
						} catch (Exception ex) {

							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

	@Test(priority = 8)
	public void VerifySceneLayerFunctionality() throws Exception {
		if (stop) {
			skipTest("Not able to proceed with 'Scene Layer Creation' as previous step requested stop");
			return;
		}
		if (skipSuite) {
			skipTest(
					"Skipping 'Scene Layer Creation' as previous critical step failed (Portal login / version not found)");
			return;
		}
		if (!stop) {
			if (!skipSuite) {
				if (flagscenelayer) {
					cnt = cnt + 1;
					if (session) {

						if (logindone) {

							CommonFunction.setUpTest(flagdevtopia);
							if (projectVersion.startsWith("11.")
									|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
									|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
									|| (projectVersion.startsWith("12."))) {
								try {
									setTextGreen("Scene Layer Creation started");
								} catch (Exception ex) {

								}
								if (!foldercreateddone) {
									PythonHelp.CreateFolder();
								}
								if (ObjectStore) {
									PythonHelp.CreateSceneLayer();

								} else {
									msg = "Not able to proceed with 'Scene Layer Creation' steps as 'Object/Tile cache DataStore is not installed/configured in the environment'";
									skipTest(msg);
								}
							}
						} else {
							msg = "Not able to proceed with 'Scene Layer Creation' steps as not able to Login to application";
							skipTest(msg);
						}

					} else {
						msg = "Not able to proceed with 'Scene Layer Creation' steps as not able to create browser session";
						skipTest(msg);
					}
					if (uirun) {
						try {
							setValue((100 / counter) * cnt);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

	@Test(priority = 9)
	public void CreateWebMap() throws Exception {
		if (stop) {
			skipTest("Not able to proceed with 'Create Web Map' as previous step requested stop");
			return;
		}
		if (skipSuite) {
			skipTest("Skipping 'Create Web Map' as previous critical step failed (Portal login / version not found)");
			return;
		}
		if (!skipSuite) {
			if (!stop) {
				if (flagmap) {
					cnt = cnt + 1;
					if (session) {
						CommonFunction.setUpTest(flagdevtopia);
						if (logindone) {
							if (!foldercreateddone) {
								Content_Functionality_TestScenario ts1 = new Content_Functionality_TestScenario();
								// ts1.create_folder_functionality();
								PythonHelp.CreateFolder();
							}
							if (foldercreateddone) {
								try {
									setTextGreen("Create Web Map functionality started");
								} catch (Exception ex) {
									// ex.printStackTrace();
								}
								Map_Functionality_TestScenario ts7 = new Map_Functionality_TestScenario();

								ts7.map_functionality();

							} else {
								msg = "Not able to proceed with 'Create Web Map' steps as not able to Create Folder";
								skipTest(msg);
							}
						} else {
							msg = "Not able to proceed with 'Create Web Map' steps as not able to Login to application";
							skipTest(msg);
						}

					} else {
						msg = "Not able to proceed with 'Create WebMap' steps as not able to create browser session";
						skipTest(msg);
					}
					if (uirun) {
						try {
							setValue((100 / counter) * cnt);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

	@Test(priority = 10)
	public void CreateDashboard() throws Exception {
		if (stop) {
			skipTest("Not able to proceed with 'Create Dashboard Test' as previous step requested stop");
			return;
		}
		if (skipSuite) {
			skipTest(
					"Skipping 'Create Dashboard Test' as previous critical step failed (Portal login / version not found)");
			return;
		}
		if (!stop) {
			if (!skipSuite) {
				if (flagdashboard) {
					cnt = cnt + 1;
					if (session) {
						if (logindone) {
							CommonFunction.setUpTest(flagdevtopia);
							if (!foldercreateddone) {
								PythonHelp.CreateFolder();
							}
							if (foldercreateddone) {
								if (projectVersion.contains("11.") || (projectVersion.contains("12."))) {
									try {
										setTextGreen("Create Dashboard Test functionality started");
									} catch (Exception ex) {
									}
									CreateDashboard_Functionality Dashboard = new CreateDashboard_Functionality();
									Dashboard.CreateDashboard_functionality();

								} else { 
									msg = "Not able to proceed with 'Create Dashboard Test' steps as it is not supported the 11.1 below Enterprsie version";
									skipTest(msg);

								}

							} else {
								msg = "Not able to proceed with 'Create Dashboard Test' steps as not able to Create Folder";
								skipTest(msg);
							}
						} else {
							msg = "Not able to proceed with 'Create Dashboard Test' steps as not able to Login to application";
							skipTest(msg);
						}

					} else {
						msg = "Not able to proceed with 'Create Dashboard Test' steps as not able to create browser session";
						skipTest(msg);
					}
					if (uirun) {
						try {
							setValue((100 / counter) * cnt);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

	@Test(priority = 11)
	public void CreateStoryMap() throws Exception {
		if (stop) {
			skipTest("Not able to proceed with 'Create Story Map' as previous step requested stop");
			return;
		}
		if (skipSuite) {
			skipTest("Skipping 'Create Story Map' as previous critical step failed (Portal login / version not found)");
			return;
		}
		if (!stop) {
			if (!skipSuite) {
				if (flagstorymap) {
					cnt = cnt + 1;
					if (session) {
						if (logindone) {
							CommonFunction.setUpTest(flagdevtopia);
							if (!foldercreateddone) {
								PythonHelp.CreateFolder();
							}
							if (foldercreateddone) {
								if (projectVersion.contains("11.") || (projectVersion.contains("12."))) {
									try {
										setTextGreen("Create Story Map functionality started");
									} catch (Exception ex) {
									}
									Content_Functionality_TestScenario ts4 = new Content_Functionality_TestScenario();
									//ts4.Validating_Server_functionality();

								} else { 
									msg = "Not able to proceed with 'Create Story Map' steps as it is not supported the 11.1 below Enterprsie version";
									skipTest(msg);

								}
								StoryMap_Functionality ts16 = new StoryMap_Functionality();
								ts16.createandVerifyStoryMap();
								
							} else {
								msg = "Not able to proceed with 'Create Story Map' steps as not able to Create Folder";
								skipTest(msg);
							}
						} else {
							msg = "Not able to proceed with 'Create Story Map' steps as not able to Login to application";
							skipTest(msg);
						}

					} else {
						msg = "Not able to proceed with 'Create Story Map' steps as not able to create browser session";
						skipTest(msg);
					}
					if (uirun) {
						try {
							setValue((100 / counter) * cnt);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

	@Test(priority = 12)
	public void CreateExperienceBuilderApp() throws Exception {
		if (stop) {
			skipTest("Not able to proceed with 'Create Experience Builder' as previous step requested stop");
			return;
		}
		if (skipSuite) {
			skipTest(
					"Skipping 'Create Experience Builder' as previous critical step failed (Portal login / version not found)");
			return;
		}
		if (!stop) {
			if (!skipSuite) {
				if (flagexperiencebuilder) {
					cnt = cnt + 1;
					if (session) {
						if (logindone) {
							CommonFunction.setUpTest(flagdevtopia);
							if (projectVersion.startsWith("12.")
									|| projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) {

								if (!foldercreateddone) {
									PythonHelp.CreateFolder();
								}
								if (foldercreateddone) {

									try {
										setTextGreen("Create Experience Builder functionality started");
									} catch (Exception ex) {
										// ex.printStackTrace();
									}
									ExperienceBuilder_Functionality_TestScenario ts4 = new ExperienceBuilder_Functionality_TestScenario();
									ts4.experiencebuilder_functionality();

								}
							} else {
								msg = "Not able to proceed with 'Create Experience Builder' steps as it is specific to Enterprise version 12 and above";
								skipTest(msg);
							}
						} else {
							msg = "Not able to proceed with 'Create Experience Builder' steps as not able to Login to application";
							skipTest(msg);
						}

					} else {
						msg = "Not able to proceed with 'Create Experience Builder' steps as not able to create browser session";
						skipTest(msg);
					}
					if (uirun) {
						try {
							setValue((100 / counter) * cnt);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}

	}

	@Test(priority = 13)
	public void CreateWebAppBuilderApp() throws Exception {
		if (stop) {
			skipTest("Not able to proceed with 'Create WebApp Builder' as previous step requested stop");
			return;
		}
		if (skipSuite) {
			skipTest(
					"Skipping 'Create WebApp Builder' as previous critical step failed (Portal login / version not found)");
			return;
		}
		if (!skipSuite) {
			if (!stop) {
				if (flagwebappbuilder) {
					cnt = cnt + 1;
					if (session) {
						if (logindone) {
							CommonFunction.setUpTest(flagdevtopia);
							if (!foldercreateddone) {
								Content_Functionality_TestScenario ts1 = new Content_Functionality_TestScenario();
								// ts1.create_folder_functionality();
								PythonHelp.CreateFolder();
							}
							if (foldercreateddone) {

								try {
									setTextGreen("Create WebApp Builder functionality started");
								} catch (Exception ex) {
									// ex.printStackTrace();
								}
								if ((projectVersion.equalsIgnoreCase("12.0.0"))
										|| (projectVersion.equalsIgnoreCase("12.1.0"))
										|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
									msg = "Not able to proceed with 'WebApp Builder functionality' as it supports versions 11.5 and below only.";
									skipTest(msg);
								} else {
									WebAppBuilder_Functionality_TestScenario ts4 = new WebAppBuilder_Functionality_TestScenario();
									ts4.webappbuilder_functionality_11_3_0();

								}
							} else {
								msg = "Not able to proceed with 'Create WebApp Builder' steps as not able to Create Folder";
								skipTest(msg);
							}
						} else {
							msg = "Not able to proceed with 'Create WebApp Builder' steps as not able to Login to application";
							skipTest(msg);
						}

					} else {
						msg = "Not able to proceed with 'Create WebApp Builder' steps as not able to create browser session";
						skipTest(msg);
					}
					if (uirun) {
						try {
							setValue((100 / counter) * cnt);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}

	}

	@Test(priority = 14)
	public void CreateGroups() throws Exception {
		if (stop) {
			skipTest("Not able to proceed with 'Create Group' as previous step requested stop");
			return;
		}
		if (skipSuite) {
			skipTest("Skipping 'Create Group' as previous critical step failed (Portal login / version not found)");
			return;
		}
		if (!skipSuite) {
			if (!stop) {
				if (flaggroup) {
					cnt = cnt + 1;
					if (session) {
						CommonFunction.setUpTest(flagdevtopia);
						if (logindone) {
							if (!foldercreateddone) {
								Content_Functionality_TestScenario ts1 = new Content_Functionality_TestScenario();
								// ts1.create_folder_functionality();
								PythonHelp.CreateFolder();
							}
							if (foldercreateddone) {
								try {
									setTextGreen("Create Group functionality started");
								} catch (Exception ex) {
									// ex.printStackTrace();
								}
								Groups_TestScenario ts5 = new Groups_TestScenario();
								ts5.groups_functionality_10_8_0();

							} else {
								msg = "Not able to proceed with 'Create Group' steps as not able to Create Folder";
								skipTest(msg);
							}
						} else {
							msg = "Not able to proceed with 'Create Group' steps as not able to Login to application";
							skipTest(msg);
						}

					} else {
						msg = "Not able to proceed with 'Create Group' steps as not able to create browser session";
						skipTest(msg);
					}
					if (uirun) {
						try {
							setValue((100 / counter) * cnt);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

	@Test(priority = 15)
	public void VerifyOrganizationFunctionality() throws Exception {
		if (stop) {
			skipTest("Not able to proceed with 'Verify Organization' as previous step requested stop");
			return;
		}
		if (skipSuite) {
			skipTest(
					"Skipping 'Verify Organization' as previous critical step failed (Portal login / version not found)");
			return;
		}
		if (!skipSuite) {
			if (!stop) {
				if (flagorganization) {
					cnt = cnt + 1;
					if (session) {
						CommonFunction.setUpTest(flagdevtopia);
						if (logindone) {

							try {
								setTextGreen("Verify Organization functionality started");
							} catch (Exception ex) {
								// ex.printStackTrace();
							}

							Organization_TestScenario ts6 = new Organization_TestScenario();
							ts6.organization_functionality();

						} else {
							msg = "Not able to proceed with 'Verify Organization' steps as not able to Login to application";
							skipTest(msg);
						}

					} else {
						msg = "Not able to proceed with 'Verify Organization' steps as not able to create browser session";
						skipTest(msg);
					}
					if (uirun) {
						try {
							setValue((100 / counter) * cnt);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

	@Test(priority = 16)
	public void ValidateServerRoles() throws Exception {
		if (stop) {
			skipTest("Not able to proceed with 'Validating Server Roles' as previous step requested stop");
			return;
		}
		if (skipSuite) {
			skipTest(
					"Skipping 'Validating Server Roles' as previous critical step failed (Portal login / version not found)");
			return;
		}
		if (!skipSuite) {
			if (!stop) {
				if (flagServerRole) {
					cnt = cnt + 1;
					if (session) {
						if (logindone) {
							CommonFunction.setUpTest(flagdevtopia);
							if (projectVersion.startsWith("11") || (projectVersion.startsWith("12"))) {
								try {
									setTextGreen("Validating Server Roles functionality started");
								} catch (Exception ex) {
									// ex.printStackTrace();
								}
								Content_Functionality_TestScenario ts4 = new Content_Functionality_TestScenario();
								ts4.Validating_Server_functionality();

							} else if (flagKUB) { // projectVersion is Kubernetes_11.5.0{
								msg = "Not able to proceed with 'Validating Server Roles' steps as it is not supported the 'Kubernetes' Enterprise version";
								skipTest(msg);

							} else {
								msg = "Not able to proceed with 'Validating Server Roles' steps as it is specific to version above 11 only";
								skipTest(msg);
							}
						} else {
							msg = "Not able to proceed with 'Validating Server Roles' steps as not able to Login to application";
							skipTest(msg);
						}

					} else {
						msg = "Not able to proceed with 'Validating Server Roles' steps as not able to create browser session";
						skipTest(msg);
					}
					if (uirun) {
						try {
							setValue((100 / counter) * cnt);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		}
	}

	

	@AfterMethod
	public void getResult(ITestResult result) throws IOException {

		if (flagrun) {
			if (session) {
				try {
					extent.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}

				// Failure rule:
				// 1. logStatus("FAIL", ...) → VDFailResult > 0 → FAILURE
				// 2. Assert.* (TestNG/JUnit) throws AssertionError → FAILURE
				// 3. Any other Throwable (RuntimeException, IOException, NPE, …)
				// must NOT count as a TestNG failure.
				try {
					org.testng.ITestContext ctx = result.getTestContext();
					Throwable thrown = result.getThrowable();
					boolean assertFailed = (thrown instanceof AssertionError);

					if (VDFailResult > 0 || assertFailed) {
						// Force FAILURE
						result.setStatus(ITestResult.FAILURE);
						if (VDFailResult > 0 && !assertFailed) {
							result.setThrowable(
									new AssertionError(VDFailResult + " step(s) failed in " + result.getName()));
						}
						// (if assertFailed, keep the original AssertionError as-is)
						if (ctx != null) {
							ctx.getPassedTests().removeResult(result);
							ctx.getSkippedTests().removeResult(result);
							ctx.getFailedTests().addResult(result);
						}
					} else {
						// No logStatus FAIL and no AssertionError.
						// If TestNG marked it FAILURE because of some other thrown exception,
						// override it back to SUCCESS so the failure count stays accurate.
						if (result.getStatus() == ITestResult.FAILURE) {
							result.setStatus(ITestResult.SUCCESS);
							result.setThrowable(null);
							if (ctx != null) {
								ctx.getFailedTests().removeResult(result);
								ctx.getSkippedTests().removeResult(result);
								ctx.getPassedTests().addResult(result);
							}
						} else if (result.getStatus() != ITestResult.SKIP) {
							result.setStatus(ITestResult.SUCCESS);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}

		// Update UI count ONCE for this test case (guarded by isCounted flag inside)
		EDCTestListener.updateResultCount(result);
		VDFailResult = 0;
	}

	@AfterTest
	public void endReport() throws Exception {
		if (flagrun) {

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
	}

	/**
	 * Static cleanup entry point invoked from the JVM shutdown hook installed
	 * by {@code EDCMainFrame} in headless mode. Mirrors the test-data cleanup
	 * performed by the {@link #DeleteCreatedTestData()} {@code @AfterSuite}
	 * method, but is safe to call without a TestNG-managed instance and avoids
	 * any UI interactions. All errors are swallowed because shutdown hooks
	 * must never throw.
	 */
	public static void runShutdownCleanup() {
		try {
			if (cleanupDone) {
				System.out.println(
						"Shutdown cleanup skipped — @AfterSuite already deleted test data.");
				return;
			}
			if (!logindone) {
				System.out.println(
						"Shutdown cleanup skipped — Portal login was not completed (logindone=false).");
				return;
			}
			System.out.println("Shutdown cleanup: deleting created test data...");
			try {
				CommonFunction.setUpTest(false);
			} catch (Throwable t) {
				t.printStackTrace();
			}
			try {
				PythonHelp.DeleteAllFolder();
			} catch (Throwable t) {
				t.printStackTrace();
			}
			cleanupDone = true;
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@AfterSuite
	public void DeleteCreatedTestData() throws Exception {
		System.out.println("After SUIT");
		flagreport = true;
		if (!session) {
			try {
				if (uirun) {
					try {
						CommonFunction.log_global_failStatus();
						setTextRed(globalmsg);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				System.out.println("quit");

			} catch (Exception e) {
				System.out.println("Dialog box not displayed");
				// CommonFunction.logStatus("PASS", "Suit Completed");
			}
		} else {
			try {
				if (logindone && (flagfeaturelayer || flagtilelayer || flagwebappbuilder || flagdashboard||flagstorymap||flagmap || flagcontent
						|| flaggroup || (flagscenelayer && scenecreated))) {
					if ((projectVersion.equalsIgnoreCase("10.6.1") || projectVersion.equalsIgnoreCase("10.7.1")
							|| projectVersion.equalsIgnoreCase("10.8.0") || projectVersion.equalsIgnoreCase("10.8.1")
							|| projectVersion.equalsIgnoreCase("10.9.0") || projectVersion.equalsIgnoreCase("10.9.1")
							|| projectVersion.equalsIgnoreCase("11.0.0") || projectVersion.equalsIgnoreCase("11.1.0")
							|| projectVersion.equalsIgnoreCase("11.2.0") || projectVersion.equalsIgnoreCase("11.3.0")
							|| (projectVersion.equalsIgnoreCase("11.4.0"))
							|| (projectVersion.equalsIgnoreCase("11.5.0"))
							|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
							|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
							|| (projectVersion.equalsIgnoreCase("12.0.0"))
							|| (projectVersion.equalsIgnoreCase("12.1.0")))) {
						try {
							CommonFunction.setUpTest(flagdevtopia);
							@SuppressWarnings("unused")
							Content_Functionality_TestScenario ts8 = new Content_Functionality_TestScenario();

							try {
								setTextGreen("Deleting created test data started");
							} catch (Exception ex) {
								// ignore UI errors
							}

							try {
								PythonHelp.DeleteAllFolder();
								cleanupDone = true;
							} catch (Throwable e) {
								e.printStackTrace();
							}
						} catch (Throwable e) {
							e.printStackTrace();
						}
					} else {
						try {
							String str1 = null;
							if (projectVersion.equalsIgnoreCase("Home")) {
								str1 = "<b>Action Required:<b> Select the Kubernetes checkbox in the Enterprise Development Check Tool.";
							} else {
								str1 = "Tests can be run on portal version 10.8.1,10.9.1,11.1 or higher. Not able to find current Portal application version"
										+ projectVersion;
							}
							CommonFunction.logStatus("FAIL", str1);
						} catch (Exception e) {
							System.out.println(e);
						}
					}
				} else if (!logindone) {
					System.out.println(
							"Skipping DeleteAllFolder cleanup — Portal login was not completed (logindone=false).");
				}
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {

				try {
					if (uirun) {
						try {
							printwrite.close();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						try {
							setValue(100);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						System.out.println("quit");
						try {
							if (stop) {
								close1();
							} else {
								close();
							}
						} catch (Throwable e) {
							e.printStackTrace();
							// Hard fallback — make sure the driver is gone.
							try {
								if (driver != null) {
									driver.quit();
								}
							} catch (Throwable ignored) {
							}
						}
					} else {
						try {
							Thread.sleep(1000);
						} catch (Exception ignored) {
						}
						try {
							if (driver != null) {
								driver.quit();
							}
						} catch (Throwable t) {
							t.printStackTrace();
						}
					}
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			// Final completion banner in the Results Log panel (green style)
			try {
				ResultsLogPanel rp = EDCMainFrame.getLogPanel();
				if (rp != null) {
					javax.swing.SwingUtilities.invokeLater(() -> rp.appendLog(
							"All operations completed. Please click on report button to view results.", "completion"));
				} else {
					System.out.println("All operations completed. Please click on report button to view results.");
				}
			} catch (Exception ignored) {
			}
			System.out.println("close");
		} // end of session else
	}
} // end of stop if
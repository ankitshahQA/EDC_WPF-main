package com.automation.testcases;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;

import com.automation.PythonAPI.PythonHelp;
import com.automation.library.CommonFunction;
import com.automation.library.TestBase;
import com.automation.pages.ContentPage;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.pages.MapPage;
import com.automation.pages.ServerPage;

public class Content_Functionality_TestScenario extends TestBase {

	CommonFunction cfunction = new CommonFunction();
	LoginPage lp = new LoginPage();
	HomePage hp = new HomePage();
	MapPage mp = new MapPage();
	ContentPage cp = new ContentPage();
	ServerPage SP = new ServerPage();
	String msg = "";

	boolean find = true;

	public void content_functionality() throws InterruptedException {

		// Step message
		msg = "Navigate to content ";
		childnode = logger.createNode(msg);
		cfunction.sync(5);

		try {
			hp.click_on_content_home();

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		msg = "Verify Content Page";

		try {
			cp.verifycontentpage();

			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		msg = "Create New Folder";
		childnode = logger.createNode(msg);

		try {
			cp.createnewfolder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		msg = "Create New feature layer";
		childnode = logger.createNode(msg);

		try {
			cp.verifyfeaturelayerdialog();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			cp.createfeaturelayer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			cp.SaveFeatureLayer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cfunction.waitforpagetoload();

		msg = "Creating Tile layer ";
		childnode = logger.createNode(msg);

		try {
			cp.verifyTilelayerdialog();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			cp.createtilelayer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			cp.SaveTileLayer();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		cfunction.waitforpagetoload();

		msg = "My organization table/grid/list view";
		childnode = logger.createNode(msg);

		try {
			cp.Verifymyorganization();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * msg = "Add from Web:Sample SmartCities"; childnode = logger.createNode(msg);
		 * cp.Addfromweb();
		 * 
		 */
		/*
		 * msg = "Navigate to content page";
		 * 
		 * try { hp.navigateToContentPage(); cp.verifycontentpage();
		 * CommonFunction.logStatus("PASS", msg);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); CommonFunction.logStatus("FAIL",
		 * msg);
		 * 
		 * }
		 */

	}

	public void Navigate_content() throws Exception {

		if (projectVersion.equalsIgnoreCase("10.6.1")) {
			Navigate_content_10_6_1();
		} else if (projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
				|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0")) 	|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))|| (projectVersion.equalsIgnoreCase("11.5.0"))) {
			Navigate_content_11_3_0();

		} else if ((projectVersion.equalsIgnoreCase("12.0.0"))) {
			cfunction.switchToWidgetTemplateFrame();
			Navigate_content_12_0_0();
		} else if ((projectVersion.equalsIgnoreCase("12.1.0"))) {
			cfunction.switchtoqueryframe1();
			hp.click_on_content_home_12_0_0();
			cfunction.switchtodefaultContent();
		}

		else if (projectVersion.equalsIgnoreCase("10.7.1") || projectVersion.equalsIgnoreCase("10.8.0")
				|| projectVersion.equalsIgnoreCase("10.8.1") || projectVersion.equalsIgnoreCase("10.9.0")
				|| projectVersion.equalsIgnoreCase("10.9.1") || projectVersion.equalsIgnoreCase("11.2.0")
				|| projectVersion.equalsIgnoreCase("11.0.0") || projectVersion.equalsIgnoreCase("11.1.0")) {
			// Step message
			msg = "Navigate to content ";
			childnode = logger.createNode(msg);
			//cfunction.sync(5);

			try {
				hp.click_on_content_home();

			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg);

			}

			msg = "Verify Content Page";
			try {
				cp.verifycontentpage();
				CommonFunction.logStatus("PASS", msg);

			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg);

			}
		}
		/*
		 * if (projectVersion.equalsIgnoreCase("11.1.0") ||
		 * projectVersion.equalsIgnoreCase("11.2.0") ||
		 * (projectVersion.equalsIgnoreCase("11.3.0")) ||
		 * (projectVersion.equalsIgnoreCase("11.4.0")) ||
		 * (projectVersion.equalsIgnoreCase("Kubernetes11.5.0")) ||
		 * (projectVersion.equalsIgnoreCase("11.5.0")) ||
		 * (projectVersion.equalsIgnoreCase("12.0.0")) ||
		 * (projectVersion.equalsIgnoreCase("12.1.0"))) {
		 * //cp.deleteEsitingSceneLayer(); }
		 */
	}

	public void create_folder_functionality() throws InterruptedException {

		if (projectVersion.equalsIgnoreCase("10.7.1") || projectVersion.equalsIgnoreCase("10.8.0")
				|| projectVersion.equalsIgnoreCase("10.8.1") || projectVersion.equalsIgnoreCase("10.9.0")
				|| projectVersion.equalsIgnoreCase("10.9.1") || projectVersion.equalsIgnoreCase("11.2.0")
				|| projectVersion.equalsIgnoreCase("11.0.0") || projectVersion.equalsIgnoreCase("11.1.0")) {

			// msg = "Navigate to content ";
			msg = "Create New Folder";
			childnode = logger.createNode(msg);
			cfunction.sync(10);

			msg = "Click on content ";
			try {
				hp.click_on_content_home();
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg);
			}

			msg = "Verify Content Page";
			try {
				cp.verifycontentpage();
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg);
			}

			msg = "Create New Folder";

			try {
				cp.createnewfolder();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			msg = "Navigate to content ";
			// childnode = logger.createNode(msg);
			try {
				hp.click_on_content_home();

			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg);

			}
			msg = "Verify Content Page";

			try {
				cp.verifycontentpage();
				CommonFunction.logStatus("PASS", msg);
				foldercreateddone = true;
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg);

			}
		} else if (projectVersion.equalsIgnoreCase("10.6.1")) {
			create_folder_functionality_10_6_1();
		}

		else if (projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
				|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0")) || (projectVersion.equalsIgnoreCase("11.5.0"))
				|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
			create_folder_functionality_11_3_0();
		}

		// hp.navigateToHometPage();
	}

	public void create_folder_functionality_11_3_0() throws InterruptedException {

		// Step message
		msg = "Create New Folder";
		childnode = logger.createNode(msg);
		cfunction.sync(10);

		msg = "Click on content ";
		try {
			hp.click_on_content_home();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		msg = "Verify Content Page";

		try {
			if (projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0")) || (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cp.verifycontentpage_11_3_0();
				CommonFunction.logStatus("PASS", msg);
			} else {
				cp.verifycontentpage();
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		try {
			cp.createnewfolder_11_3_0();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		msg = "Navigate to content ";
		try {
			hp.click_on_content_home();
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}
		msg = "Verify Content Page";

		try {
			if (projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0")) || (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cp.verifycontentpage_11_3_0();
				foldercreateddone = true;
				CommonFunction.logStatus("PASS", msg);
			} else {
				cp.verifycontentpage();
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}
		// hp.navigateToHometPage();
	}

	public void feature_layer_functionality() throws Exception {
        cfunction.sync(2);
		if (projectVersion.equalsIgnoreCase("10.7.1") || projectVersion.equalsIgnoreCase("10.8.0")
				|| projectVersion.equalsIgnoreCase("10.8.1") || projectVersion.equalsIgnoreCase("10.9.0")
				|| projectVersion.equalsIgnoreCase("10.9.1") || projectVersion.equalsIgnoreCase("11.2.0")
				|| projectVersion.equalsIgnoreCase("11.0.0") || projectVersion.equalsIgnoreCase("11.1.0")) {

			msg = "Create Feature layer";
			childnode = logger.createNode(msg);

			try {
				cp.verifyfeaturelayerdialog();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (featurecreated) {
				try {
					cp.createfeaturelayer();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (featurecreated) {
				try {
					// cp.SaveFeatureLayer();
					cp.SaveLayer(FeatureName, "TestTag", FeatureName);
					
					PythonHelp.MoveItemInfolder(FeatureName);
					
					msg = "Verify Feature created";
					childnode = logger.createNode(msg);
					cp.verifyFeatureLayerCreated(FeatureName);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (projectVersion.equalsIgnoreCase("10.6.1")) {
				Navigate_content_10_6_1();
			} else if (projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0")) || (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))|| (projectVersion.equalsIgnoreCase("11.5.0"))) {
				Navigate_content_11_3_0();
			} else if ((projectVersion.equalsIgnoreCase("12.0.0"))) {
				try {
					cfunction.switchToWidgetTemplateFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Navigate_content_12_0_0();
				cfunction.switchtodefaultContent();
			}

			else if ((projectVersion.equalsIgnoreCase("12.1.0"))) {
				try {
					cfunction.switchtoqueryframe1();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Navigate_content_12_0_0();
				cfunction.switchtodefaultContent();
			}

			else if (projectVersion.equalsIgnoreCase("10.7.1") || projectVersion.equalsIgnoreCase("10.8.0")
					|| projectVersion.equalsIgnoreCase("10.8.1") || projectVersion.equalsIgnoreCase("10.9.0")
					|| projectVersion.equalsIgnoreCase("10.9.1") || projectVersion.equalsIgnoreCase("11.2.0")
					|| projectVersion.equalsIgnoreCase("11.0.0") || projectVersion.equalsIgnoreCase("11.1.0")) {
				// Step message
				msg = "Navigate to content ";
				try {
					hp.click_on_content_home();

				} catch (Exception e) {
					e.printStackTrace();
					CommonFunction.logStatus("FAIL", msg);

				}

				msg = "Verify Content Page";
				try {
					cp.verifycontentpage();
					} catch (Exception e) {
					e.printStackTrace();
					}

			}
		} else if (projectVersion.equalsIgnoreCase("10.6.1")) {

			feature_layer_functionality_10_6_1();
		}

		else
			feature_layer_functionality_11_3_0();
	}

	public void feature_layer_functionality_11_3_0() throws InterruptedException {

		msg = "Create Feature layer";
		childnode = logger.createNode(msg);

		try {
			cp.verifyfeaturelayerdialog11_3();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (featurecreated) {
			try {
				cp.createfeaturelayer();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (featurecreated) {
			try {
				cp.SaveLayer(FeatureName, "TestTag", FeatureName);
				
				PythonHelp.MoveItemInfolder(FeatureName);
				
				msg = "Verify Feature created";
				childnode = logger.createNode(msg);
				
				cp.verifyFeatureLayerCreated(FeatureName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cfunction.waitforpagetoload();

	}

	public void tile_layer_functionality() throws Exception {
           cfunction.refreshpage();
           cfunction.waitforpagetoload();
		
           
           if (flagtilelayer) {
			if (projectVersion.equalsIgnoreCase("10.8.1")) {
				msg = "Create Tile layer ";
				childnode = logger.createNode(msg);

				cp.createTile_10_8_1(TileName, "SampleCities");
				
				PythonHelp.MoveItemInfolder(TileName);
				
				msg = "Verify Tile created";
				childnode = logger.createNode(msg);
				cp.verifyFeatureLayerCreated(TileName);

				msg = "Navigate to content page";

				try {
					hp.navigateToContentPage();
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e) {
					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);
				}

				cfunction.sync(2);
			}

			else {
				if (projectVersion.equalsIgnoreCase("10.6.1")) {
					Navigate_content_10_6_1();
				} else if (projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
						|| (projectVersion.equalsIgnoreCase("11.5.0"))) {
					Navigate_content_11_3_0();
				} else if ((projectVersion.equalsIgnoreCase("12.0.0"))) {
					try {
						cfunction.switchToWidgetTemplateFrame();
					} catch (Exception e) {
						e.printStackTrace();
					}
					Navigate_content_12_0_0();
					cfunction.switchtodefaultContent();
				}

				else if ((projectVersion.equalsIgnoreCase("12.1.0"))) {
					try {
						cfunction.switchtoqueryframe1();
					} catch (Exception e) {
						e.printStackTrace();
					}
					Navigate_content_12_0_0();
					cfunction.switchtodefaultContent();
				}

				else if (projectVersion.equalsIgnoreCase("10.7.1") || projectVersion.equalsIgnoreCase("10.8.0")
						||  projectVersion.equalsIgnoreCase("10.9.0")
						|| projectVersion.equalsIgnoreCase("10.9.1") || projectVersion.equalsIgnoreCase("11.2.0")
						|| projectVersion.equalsIgnoreCase("11.0.0") || projectVersion.equalsIgnoreCase("11.1.0")) {
					// Step message
					msg = "Navigate to content ";
					try {
						hp.click_on_content_home();

					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatus("FAIL", msg);

					}

					msg = "Verify Content Page";
					try {
						cp.verifycontentpage();
						CommonFunction.logStatus("PASS", msg);

					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatus("FAIL", msg);

					}
				}

				msg = "Create Tile layer ";
				childnode = logger.createNode(msg);
				try {
					if (projectVersion.equalsIgnoreCase("10.9.1") )
						cp.verifyTilelayerdialog_10_9_1();
					else
						cp.verifyTilelayerdialog();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (tilecreated) {
					try {
						cp.createtilelayer();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (tilecreated) {
					try {

						cp.SaveTileLayer(TileName, "SampleCities", TileName);
						
						
						PythonHelp.MoveItemInfolder(TileName);
					
						msg = "Verify Tile created";
						childnode = logger.createNode(msg);
						cp.verifyFeatureLayerCreated( TileName);
						// cp.SaveTileLayer();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				cfunction.waitforpagetoload();

				if (projectVersion.equalsIgnoreCase("10.6.1")) {
					Navigate_content_10_6_1();
				} else if (projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
						|| (projectVersion.equalsIgnoreCase("11.5.0"))) {
					Navigate_content_11_3_0();
				} else if ((projectVersion.equalsIgnoreCase("12.0.0"))) {
					try {
						cfunction.switchToWidgetTemplateFrame();
					} catch (Exception e) {
						e.printStackTrace();
					}
					Navigate_content_12_0_0();
					cfunction.switchtodefaultContent();
				}

				else if ((projectVersion.equalsIgnoreCase("12.1.0"))) {
					try {
						cfunction.switchtoqueryframe1();
					} catch (Exception e) {
						e.printStackTrace();
					}
					Navigate_content_12_0_0();
					cfunction.switchtodefaultContent();
				}

				else if (projectVersion.equalsIgnoreCase("10.7.1") || projectVersion.equalsIgnoreCase("10.8.0")
						|| projectVersion.equalsIgnoreCase("10.8.1") || projectVersion.equalsIgnoreCase("10.9.0")
						|| projectVersion.equalsIgnoreCase("10.9.1") || projectVersion.equalsIgnoreCase("11.2.0")
						|| projectVersion.equalsIgnoreCase("11.0.0") || projectVersion.equalsIgnoreCase("11.1.0")) {
					// Step message
					msg = "Navigate to content ";
					try {
						hp.click_on_content_home();

					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatus("FAIL", msg);

					}

					msg = "Verify Content Page";
					try {
						cp.verifycontentpage();
						CommonFunction.logStatus("PASS", msg);

					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatus("FAIL", msg);

					}
				}

			}
		}
	}

	public void Navigate_content_10_6_1() throws InterruptedException {

		msg = "Navigate to content ";
		childnode = logger.createNode(msg);
		cfunction.sync(5);

		try {
			hp.click_on_content_home_10_6_1();

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		msg = "Verify Content Page";

		try {
			cp.verifycontentpage_10_6_1();

			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}
	}

	public void Navigate_content_10_9_1() throws InterruptedException {

		msg = "Navigate to content ";

		cfunction.sync(5);

		try {
			hp.click_on_content_home_10_9_1();

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		msg = "Verify Content Page";

		try {
			cp.verifycontentpage_10_9_1();

			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}
	}

	public void Navigate_content_11_3_0() throws Exception {

		msg = "Navigate to content ";
		cfunction.sync(5);

		try {
			hp.click_on_content_home();
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		msg = "Verify Content Page";

		try {
			cp.verifycontentpage_11_3_0();
			//CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			//CommonFunction.logStatus("FAIL", msg);

		}

		// cp.deleteEsitingSceneLayer11_3();

	}

	public void Navigate_content_12_0_0() throws Exception {

		msg = "Navigate to content ";
		cfunction.sync(5);
		try {
			hp.click_on_content_home_12_0_0();
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		msg = "Verify Content Page";

		try {
			cp.verifycontentpage_11_3_0();

			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		// cp.deleteEsitingSceneLayer11_3();

	}

	public void content_functionality_10_6_1() throws InterruptedException {

		msg = "Navigate to content ";
		cfunction.sync(5);

		try {
			hp.click_on_content_home_10_6_1();

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		msg = "Verify Content Page";

		try {
			cp.verifycontentpage_10_6_1();

			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		msg = "Create New Folder";
		childnode = logger.createNode(msg);
		try {
			cp.createnewfolder();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		msg = "Create Feature layer";
		childnode = logger.createNode(msg);

		try {
			cp.verifyfeaturelayerdialog();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		try {
			cp.createfeaturelayer();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			cp.SaveFeatureLayer();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		cfunction.waitforpagetoload();

		msg = "Create Tile layer ";
		childnode = logger.createNode(msg);

		try {
			cp.verifyTilelayerdialog();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			cp.createtilelayer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			cp.SaveTileLayer();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		cfunction.waitforpagetoload();

		msg = "My organization table/grid/list view";
		childnode = logger.createNode(msg);

		try {
			cp.Verifymyorganization_10_6_1();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		msg = "Navigate to content page";

		/*
		 * msg = "Add from Web:Sample SmartCities"; childnode = logger.createNode(msg);
		 * 
		 * cp.Addfromweb_10_6_1();
		 * 
		 */
	}

	public void create_folder_functionality_10_6_1() throws InterruptedException {

		msg = "Navigate to content ";
		childnode = logger.createNode(msg);
		cfunction.sync(5);

		try {
			hp.click_on_content_home_10_6_1();

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		msg = "Verify Content Page";

		try {
			cp.verifycontentpage_10_6_1();

			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		msg = "Create New Folder";
		childnode = logger.createNode(msg);
		try {
			cp.createnewfolder();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		msg = "Navigate to content ";
		childnode = logger.createNode(msg);
		cfunction.sync(5);

		try {
			hp.click_on_content_home_10_6_1();

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		msg = "Verify Content Page";

		try {
			cp.verifycontentpage_10_6_1();

			CommonFunction.logStatus("PASS", msg);
			foldercreateddone = true;

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}
	}

	public void feature_layer_functionality_10_6_1() throws Exception {

		msg = "Create Feature layer";
		childnode = logger.createNode(msg);

		cp.verifyfeaturelayerdialog();
		if (featurecreated) {
			cp.createfeaturelayer();
		}
		if (featurecreated) {
			cp.SaveFeatureLayer();
		}
		cfunction.waitforpagetoload();

	}

	public void content_delete_functionality() throws Exception {

		// Step message

		cfunction.sync(5);
		msg = "Delete Content/Folder ";
		childnode = logger.createNode(msg);
		if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
				|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0")) || (projectVersion.equalsIgnoreCase("11.5.0"))
				|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
			cp.deleteCreated11_3();
		} else {
			cp.deleteCreated();
		}
	}

	public void Validating_Server_functionality() throws Exception {
		cfunction.refreshpage();
		cfunction.waitforpagetoload();

		childnode = logger.createNode("Navigate to Server Page");
		msg = "Navigate to organization";
		try {
			cfunction.sync(5);
			driver.switchTo().defaultContent();
			hp.navigateToOrganizationPage();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Click on setting";
		try {
			hp.click_on_Setting();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on Server";
		try {
			hp.click_on_Server();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(10);
		int j = no_of_site;
		for (int i = 0; i < j; i++) {
			// String adURL = SP.getAdminURL(i);
			String managerURL = SP.getServerURL(i);

			if (ServerFederated[i]) {
				childnode = logger.createNode("Validating Server Site for Server URL " + (i + 1) + " : " + managerURL);
				SP.validateServer(managerURL, ServerAdminRole[i]);

			} else {
				msg = "Not able to proceed furthur Server URL " + (i + 1) + " is Non-federated";
				CommonFunction.logStatus("SKIP", msg);
				// throw new SkipException(msg);
			}
		}

	}

	public void Validating_Data_Stores_functionality() throws Exception {

		try {
			int j = no_of_site;
			boolean verifystore = false;
			for (int i = 0; i < j; i++) {
				if (!Serverurl[i].isEmpty()) {

					String managerURL = Serverurl[i].trim() + "/manager";
					if (ServerAdminRole[i].equalsIgnoreCase("Hosting Server")) {
						msg = "Validate Data Store for Server Page " + (i + 1);
						childnode = logger.createNode(msg);
						if (ServerFederated[i]) {
							if ((!(PortalUserName.isEmpty())) && (!(PortalPassword.isEmpty()))) {
								SP.validateDataStores(managerURL, PortalUserName, PortalPassword);
								verifystore = true;// this will be true only when we have any of server url with hosting
													// server
							} else {
								CommonFunction.logStatus("FAIL",
										"Please provide details for 'Portal Username' and 'Portal Password' as server is federated");
							}
						} else {
							if ((!(ServerAdminUsername[i].isEmpty())) && (!(ServerAdminPassword[i].isEmpty()))) {
								verifystore = true;
								SP.validateDataStores(managerURL, ServerAdminUsername[i], ServerAdminPassword[i]);
							} else {
								CommonFunction.logStatus("FAIL",
										"Please provide details for 'Server Admin Username " + (i + 1)
												+ "'and 'Server Admin Password '" + (i + 1)
												+ "as Server is non federated");
							}
						}
					}
				}
			}
			if (!verifystore) {

				msg = "Validate Data Store for Server";
				childnode = logger.createNode(msg);
				CommonFunction.logStatus("WARNING",
						"<Strong>Not able to 'login the server manager'. Please provide Server url with Server Role 'Hosting Server'</Strong>");
			}

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}

		/*
		 * msg = "Navigate to organization"; try { hp.navigateToOrganizationPage();
		 * CommonFunction.logStatus("PASS", msg); } catch (Exception e) {
		 * e.printStackTrace(); CommonFunction.logStatusWithException("FAIL", msg, e);
		 * 
		 * }
		 * 
		 * msg = "Click on setting"; try { hp.click_on_Setting();
		 * CommonFunction.logStatus("PASS", msg); } catch (Exception e) {
		 * e.printStackTrace(); CommonFunction.logStatusWithException("FAIL", msg, e);
		 * 
		 * } msg = "Click on Server"; try { hp.click_on_Server();
		 * CommonFunction.logStatus("PASS", msg); } catch (Exception e) {
		 * e.printStackTrace(); CommonFunction.logStatusWithException("FAIL", msg, e);
		 * 
		 * }
		 * 
		 * int j = no_of_site; for (int i = 0; i < j; i++) { // String adURL =
		 * SP.getAdminURL(i); String managerURL = SP.getServerURL(i);
		 * 
		 * if (ServerFederated[i]) { childnode =
		 * logger.createNode("Validating Server Site for Server URL " + (i + 1) + " : "
		 * + managerURL); SP.validateServer(managerURL, ServerAdminRole[i]);
		 * 
		 * } else { msg = "Not able to proceed furthur Server URL " + (i + 1) +
		 * " is Non-federated"; CommonFunction.logStatus("SKIP", msg); // throw new
		 * SkipException(msg); } }
		 */

	}
}
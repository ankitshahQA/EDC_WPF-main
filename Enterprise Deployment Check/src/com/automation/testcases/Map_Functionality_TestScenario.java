package com.automation.testcases;

import com.automation.PythonAPI.PythonHelp;
import com.automation.library.CommonFunction;
import com.automation.library.TestBase;
import com.automation.pages.ContentPage;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.pages.MapPage;



public class Map_Functionality_TestScenario extends TestBase{

	CommonFunction cfunction = new CommonFunction();
	LoginPage lp=new LoginPage();
	HomePage hp=new HomePage();
	MapPage mp=new MapPage();
	ContentPage cp= new ContentPage();
	String msg = "";
	boolean find = true;
	
	public void Navigate_content_12_0_0() throws Exception {

		msg = "Navigate to content ";
		childnode = logger.createNode(msg);
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
	
	public void Navigate_content_11_3_0() throws Exception {

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
			cp.verifycontentpage_11_3_0();

			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		// cp.deleteEsitingSceneLayer11_3();

	}

	
	public void map_functionality() throws Exception {
		
              cfunction.refreshpage();
              cfunction.waitforpagetoload();
              
              if (projectVersion.equalsIgnoreCase("10.6.1")) {
		map_functionality_10_6_1();
		}
		
		else if (projectVersion.equalsIgnoreCase("10.7.1") || projectVersion.equalsIgnoreCase("10.8.0")
				|| projectVersion.equalsIgnoreCase("10.8.1")
				|| projectVersion.equalsIgnoreCase("10.9.0")
				|| projectVersion.equalsIgnoreCase("10.9.1")
				|| projectVersion.equalsIgnoreCase("11.2.0")
				|| projectVersion.equalsIgnoreCase("11.0.0")
				|| projectVersion.equalsIgnoreCase("11.1.0")
				|| (projectVersion.equalsIgnoreCase("11.3.0"))
				|| (projectVersion.equalsIgnoreCase("11.4.0"))) {
		//Step message
		
		msg = "Navigate to Map";
		childnode = logger.createNode(msg);

		hp.navigateToMapPage();

		cfunction.sync(10);
		msg = "Verify Detail Panel";	
		childnode = logger.createNode(msg);

		mp.verifydetailpanel();
		cfunction.waitforpagetoload();

		cfunction.sync(10);
		msg = "Verify BaseMap";
		childnode = logger.createNode(msg);

		mp.verifyBaseMapRendering();

		cfunction.sync(3);
		msg = "Add Layers";
		childnode = logger.createNode(msg);

		mp.SearchForLayers();

		cfunction.sync(2);
		msg = "Browse by living Atlas";
		childnode = logger.createNode(msg);

		mp.SearchForLivingAtlas();

		cfunction.sync(2);
		msg = "Verify Legends and Table";
		childnode = logger.createNode(msg);

		mp.VerifyLegendTableDisplays();

		cfunction.sync(2);
		msg = "Save Map";
		childnode = logger.createNode(msg);

		mp.SaveMap();
		
		PythonHelp.MoveItemInfolder(MapName);
		
		msg = "Verify Map created";
		childnode = logger.createNode(msg);
		try {

			mp.navigateToContentPage();
			// CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();

		}

		mp.VerfiyItemCreatedInFolder(MapName);

		}else if((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
				|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))
				|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
			cfunction.sync(5);
			msg = "Navigate to Map";
			childnode = logger.createNode(msg);
			hp.navigateToMapPage();
			cfunction.sync(5);
			cfunction.refreshpage();

			cfunction.sync(5);
			msg = "Add Layers";
			childnode = logger.createNode(msg);
			mp.SearchForLayers_11_5();
			
			cfunction.sync(2);
			msg = "Save Map";
			childnode = logger.createNode(msg);
			mp.SaveMap_11_5();
			
			PythonHelp.MoveItemInfolder(MapName);
			
			
			
			if ((projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
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

			else if ((projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				try {
					cfunction.switchtoqueryframe1();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Navigate_content_12_0_0();
				cfunction.switchtodefaultContent();
			}
			
			msg = "Verify Map created";
			childnode = logger.createNode(msg);
			mp.VerfiyItemCreatedInFolder(MapName);
			
			
		}
	}
	public void map_functionality_10_6_1() throws Exception {

		//Step message

		cfunction.sync(5);
		msg = "Navigate to Map";
		childnode = logger.createNode(msg);

		hp.navigateToMapPage_10_6_1();
		mp.verifymappage();

		cfunction.sync(2);
		msg = "Verify Detail Panel";	
		childnode = logger.createNode(msg);

		mp.verifydetailpanel();
		cfunction.waitforpagetoload();

		cfunction.sync(2);
		msg = "Verify BaseMap";
		childnode = logger.createNode(msg);

		mp.verifyBaseMapRendering();

		cfunction.sync(2);
		msg = "Add Layers";
		childnode = logger.createNode(msg);

		mp.SearchForLayers();

		cfunction.sync(2);
		msg = "Browse by living Atlas";
		childnode = logger.createNode(msg);

		mp.SearchForLivingAtlas();

		cfunction.sync(2);
		msg = "Verify Legends and Table";
		childnode = logger.createNode(msg);

		mp.VerifyLegendTableDisplays();

		cfunction.sync(2);
		msg = "Save Map";
		childnode = logger.createNode(msg);

		mp.SaveMap();
		
		PythonHelp.MoveItemInfolder(MapName);
		
		mp.VerfiyItemCreatedInFolder(MapName);


	}

}





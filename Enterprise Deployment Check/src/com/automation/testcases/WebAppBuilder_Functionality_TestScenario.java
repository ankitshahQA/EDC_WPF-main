package com.automation.testcases;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;
import com.automation.pages.ContentPage;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.pages.MapPage;
import com.automation.pages.WebAppBuilder;

public class WebAppBuilder_Functionality_TestScenario extends TestBase {

	CommonFunction cfunction = new CommonFunction();
	LoginPage lp = new LoginPage();
	HomePage hp = new HomePage();
	MapPage mp = new MapPage();
	ContentPage cp = new ContentPage();
	WebAppBuilder wb = new WebAppBuilder();
	String msg = "";
	boolean find = true;

	public void webappbuilder_functionality() throws Exception {
		msg = "Add from Web:Sample SmartCities";
		childnode = logger.createNode(msg);
		if (projectVersion.equalsIgnoreCase("10.6.1")) {
			// https://sampleserver6.arcgisonline.com/arcgis/rest/services/Census/MapServer
			cp.Addfromweb_10_6_1();
		} else {
			cp.Addfromweb();
		}

		msg = "Create webapp builder ";
		childnode = logger.createNode(msg);

		cp.clickCreateWebAppBuilder();

		wb.Enterdetailsforwebapp();

		msg = "Create query widget";
		childnode = logger.createNode(msg);

		wb.createQuery();

		msg = "Verify query created";

		wb.VerifyQuery();

		msg = "Create Legend";
		childnode = logger.createNode(msg);

		wb.createLegend();

		msg = "Create Layer";
		childnode = logger.createNode(msg);

		wb.createLayer();

		msg = "Create Basemap";
		childnode = logger.createNode(msg);

		wb.createBasemap();

		msg = "Create Bookmark";
		childnode = logger.createNode(msg);

		wb.createbookmark();

		msg = "Save/verify Webapp builder ";
		childnode = logger.createNode(msg);

		wb.saveWebapp();
		CommonFunction.logStatus("PASS", msg);

		wb.navigateToContentPage();

		wb.verifywebappbuilder();

	}

	public void webappbuilder_functionality_11_3_0() throws Exception {
		cfunction.refreshpage();
		cfunction.waitforpagetoload();
		
		if (projectVersion.startsWith("10.") || projectVersion.equalsIgnoreCase("11.2.0")
				|| projectVersion.equalsIgnoreCase("11.0.0")
				|| projectVersion.equalsIgnoreCase("11.1.0")) {
               webappbuilder_functionality();
		}
		
		else if (projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
				|| (projectVersion.equalsIgnoreCase("11.5.0")) ||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))) {		
		
		msg = "Add from Web:Sample SmartCities";
		childnode = logger.createNode(msg);
		cp.Addfromweb_11_3_0();

		msg = "Create webapp builder ";
		childnode = logger.createNode(msg);

		cp.clickCreateWebAppBuilder();

		wb.Enterdetailsforwebapp11_3();

		msg = "Create query widget";
		childnode = logger.createNode(msg);

		wb.createQuery();

		msg = "Verify query created";

		wb.VerifyQuery();

		msg = "Create Legend";
		childnode = logger.createNode(msg);

		wb.createLegend();

		msg = "Create Layer";
		childnode = logger.createNode(msg);

		wb.createLayer();

		msg = "Create Basemap";
		childnode = logger.createNode(msg);

		wb.createBasemap();

		msg = "Create Bookmark";
		childnode = logger.createNode(msg);

		wb.createbookmark();

		msg = "Save/verify Webapp builder ";
		childnode = logger.createNode(msg);

		wb.saveWebapp();
		CommonFunction.logStatus("PASS", msg);

		wb.navigateToContentPage();

		wb.verifywebappbuilder();

	}}

}

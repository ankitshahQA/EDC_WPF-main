package com.automation.testcases;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;
import com.automation.pages.ContentPage;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.pages.SceneLayer;

@SuppressWarnings("serial")
public class CreateScene_TestScenario extends TestBase {

	CommonFunction cfunction = new CommonFunction();
	LoginPage lp = new LoginPage();
	HomePage hp = new HomePage();
	ContentPage cp = new ContentPage();
	SceneLayer scnl = new SceneLayer();
	String msg = "";

	public void scenelayer_functionality() throws Exception {
		cfunction.refreshpage();
		cfunction.waitforpagetoload();

		if (projectVersion.equalsIgnoreCase("11.1.0") || projectVersion.equalsIgnoreCase("11.2.0")
				|| projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
				|| (projectVersion.equalsIgnoreCase("11.5.0")) || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
				|| projectVersion.equalsIgnoreCase("12.0.0")||(projectVersion.equalsIgnoreCase("12.1.0"))) {
			msg = "Create Scene Layer";
			childnode = logger.createNode(msg);
			cp.clickCreateScenelayer();

			msg = "Verify Scene Layer Created";
			childnode = logger.createNode(msg);
			scnl.verifySceneLayerCreated();

			msg = "Create and Save Scene";
			childnode = logger.createNode(msg);
			scnl.createScene();

		}

	}
}

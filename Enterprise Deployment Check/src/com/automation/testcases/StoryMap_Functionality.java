package com.automation.testcases;

import com.automation.PythonAPI.PythonHelp;
import com.automation.library.CommonFunction;
import com.automation.library.TestBase;
import com.automation.pages.ContentPage;
import com.automation.pages.HomePage;
import com.automation.pages.MapPage;
import com.automation.pages.StoryMapPage;

public class StoryMap_Functionality extends TestBase {

	CommonFunction cfunction = new CommonFunction();
	ContentPage cp = new ContentPage();
	StoryMapPage smp = new StoryMapPage();
	HomePage hp = new HomePage();
	MapPage mp = new MapPage();
	String msg = "";

	boolean find = true;

	public void createandVerifyStoryMap() throws Exception {

		msg = "Select StoryMaps from the ArcGIS App container";
		childnode = logger.createNode(msg);
		hp.selectStoryMaps();
		smp.verifyStoryMapPage();

		if (storymapPage) {
			msg = "Creating StoryMap App with name: " + StoryMapName;
			childnode = logger.createNode(msg);
			smp.createStoryMap();
			PythonHelp.MoveItemInfolder(StoryMapName);
			cfunction.refreshpage();
			cfunction.sync(5);
			mp.VerfiyItemCreatedInFolder(StoryMapName);
		} else
			CommonFunction.logStatus("FAIL", "Cannot procee with StoryMap creation as StoryMap page is not displayed");

	}

}
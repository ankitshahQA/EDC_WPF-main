package com.automation.testcases;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;
import com.automation.pages.ContentPage;
import com.automation.pages.ExperienceBuilderApp;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.pages.MapPage;

public class ExperienceBuilder_Functionality_TestScenario  extends TestBase{
	
	String msg="";
	CommonFunction cfunction = new CommonFunction();
	HomePage hp = new HomePage();
	ContentPage cp = new ContentPage();
	ExperienceBuilderApp exb= new ExperienceBuilderApp();
	
	
	
	
	
	
	public void experiencebuilder_functionality() throws Exception {
	

	msg="Select Experience Builder App template";
	childnode = logger.createNode(msg);
	cp.clickExperienceBuilderTemplate();
		
	msg="Creating Experience Builder App with name: "+ExperienceBuilderName;
	childnode = logger.createNode(msg);
	exb.createExperienceBuilderApp();
	
	msg="Verify the created Experience Builder App is displayed in folder";
	childnode = logger.createNode(msg);
	cp.verifyCreatedContentInFolder(ExperienceBuilderName);
	
	msg="Verify the created Experience Builder App ";
	cp.selectContentFromFolder(ExperienceBuilderName);
	
	
	
	
	
	
   }
	
	}
	

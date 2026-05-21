package com.automation.library;

/*****
 *
 * Class: AnnotationTransformer
 * This class implements IAnnotationTransformer and it lets
 * control the @test annotation at runtime.
 *
 * it overrides the transformer method which checks the excel and
 * based on Y/N values in Runmode, sets 'enabled' to true or false
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.automation.testcases.EDCMainFrame;

/**
 *
 *
 *
 */
public class AnnotationTransformer implements IAnnotationTransformer {
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		
		//System.out.println(testMethod.getName());
		String msg="";
		try {
			if(!EDCMainFrame.configread)	{
		EDCMainFrame.configSetup();
		EDCMainFrame.InitializeFolder();
		
		//InitializeFolder();
		System.out.println("config setup done");
		EDCMainFrame.readfromconfigdisplay();
		EDCMainFrame.configread=true;
		if(!EDCMainFrame.uirun) {
			msg=EDCMainFrame.verifyConfigvalues();
			
			if(!(msg=="")) {
				EDCMainFrame.flagrun=false;
				EDCMainFrame.flagloginadmin=false;
				EDCMainFrame.flagloginmanager=false;
				EDCMainFrame.flagdatastore=false;
				EDCMainFrame.flagloginportal=false;
				EDCMainFrame.flagfeaturelayer=false;
				EDCMainFrame.flagscenelayer=false;
				EDCMainFrame.flagmap=false;
				EDCMainFrame.flagexperiencebuilder=false;
				EDCMainFrame.flagwebappbuilder=false;
				EDCMainFrame.flaggroup=false;
				EDCMainFrame.flagorganization=false;
				EDCMainFrame.flagServerRole=false;
				EDCMainFrame.flagtilelayer=false;
				EDCMainFrame.flagcontent=false;
				EDCMainFrame.flagdashboard=false;
				EDCMainFrame.flagstorymap=false;
				
				System.out.println(msg);
				EDCMainFrame.setTextGreen(msg);
			}
		}
		
			}
			
			if(testMethod.getName().contains("ServerAdminLogin")){
				annotation.setEnabled(EDCMainFrame.flagloginadmin);
			}
			if(testMethod.getName().contains("ServerManagerLogin")){
				annotation.setEnabled(EDCMainFrame.flagloginmanager);
			}
			if(testMethod.getName().contains("ValidateDataStores")){
				annotation.setEnabled(EDCMainFrame.flagdatastore);
			}
			if(testMethod.getName().contains("PortalLogin")){
				annotation.setEnabled(EDCMainFrame.flagloginportal);
			}
			//if(testMethod.getName().contains("CreateFolder")){
				//annotation.setEnabled(EDCMainFrame.flagcontent);
			//}
			if(testMethod.getName().contains("CreateFeatureLayer")){
				annotation.setEnabled(EDCMainFrame.flagfeaturelayer);
			}
			if(testMethod.getName().contains("CreateTileLayer")){
				annotation.setEnabled(EDCMainFrame.flagtilelayer);
			}
			if(testMethod.getName().contains("VerifySceneLayerFunctionality")){
				annotation.setEnabled(EDCMainFrame.flagscenelayer);
			}

			if(testMethod.getName().contains("CreateWebMap")){
				annotation.setEnabled(EDCMainFrame.flagmap);
			}
			 if(testMethod.getName().contains("CreateExperienceBuilderApp")){
				annotation.setEnabled(EDCMainFrame.flagexperiencebuilder);
			}
			if(testMethod.getName().contains("CreateWebAppBuilderApp")){
				annotation.setEnabled(EDCMainFrame.flagwebappbuilder);
			}
			if(testMethod.getName().contains("CreateGroups")){
				annotation.setEnabled(EDCMainFrame.flaggroup);
			}
			if(testMethod.getName().contains("VerifyOrganizationFunctionality")){
				annotation.setEnabled(EDCMainFrame.flagorganization);
			}
			if(testMethod.getName().contains("ValidateServerRoles")){
				annotation.setEnabled(EDCMainFrame.flagServerRole);
			}
			if(testMethod.getName().contains("CreateDashboard")){
				annotation.setEnabled(EDCMainFrame.flagdashboard);
			}
			if(testMethod.getName().contains("CreateStoryMap")){
				annotation.setEnabled(EDCMainFrame.flagstorymap);
			}
			//else {
				
			//}
			

		
		}catch(Exception ex) {
			ex.printStackTrace();
		}
}
}

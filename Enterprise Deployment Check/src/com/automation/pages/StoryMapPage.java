package com.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.automation.PythonAPI.PythonHelp;
import com.automation.library.CommonFunction;
import com.automation.library.TestBase;

public class StoryMapPage extends TestBase {

	CommonFunction cfunction = new CommonFunction();
	HomePage hp = new HomePage();
	ContentPage cp = new ContentPage();

	final static String NEWSTORY_11_1XPATH = "//div[contains(@class,'add-item') or contains(@class,'story-action-buttons')]//button";
	final static String CREATESTOERYMAP_12_1XPATH = "//button[@data-testid='create']";
	final static String NEWSTORY_12_1XPATH = "//a[@data-testid='create-menu-story']";
	final static String STARTFROMSRATCH_11_1XPATH = "//div[@data-testid='dropdown-container']//button[@content='Start from scratch']";
	final static String STORYMAPTITLE_11_1XPATH = "//textarea[@placeholder='Title your story' or @placeholder='Story title'] ";
	final static String STORYMAPTITLEHEADER_11_1XPATH = "//button[contains(@aria-label,'Return to top')]//*[contains(@class,'story-title')]";
	final static String STORYMAPTITLE_11_5_XPATH = "//*[contains(@class,'card-title')]";
	final static String STORYMAPPUBLISH_11_1XPATH = "(//button[text()='Publish'])";
	final static String STORYMAPPUBLISHOPTIONSPAGE_11_1XPATH = "//*[contains(@class,'header-title')]";
	final static String STORYMAPPROFILE_11_1XPATH = "//div[@class='profile-container']//button";
	final static String VIEWPUBLISHEDSTORY_11_5_XPATH = "//div[contains(@class,'finish-stage')]//following-sibling::div//a";
	final static String STORYMYPROJECTS_11_1XPATH = "//a[contains(@href,'stories')]";
	final static String GOTOMYPROJECT_12_XPATH = "//a[contains(@class,'secondary') and contains(@class,'sharing-stage-button')]";
	final static String GOTOSTORYMAP_12_1XPATH = "//div[contains(@class,'finish-stage')]//following-sibling::div//a[contains(@href,'content')]";
	final static String PUBLISHEDSTORYMAP_11_1XPATH = "//a[contains(@content,'StoryMap')]//*[contains(@class,'card-title')]";
	final static String PUBLISHEDSTORYMAP_12_1_XPATH = "//div[contains(@class,'card')]//*[contains(@class,'card-title')]";
	String msg = "";

	public void verifyStoryMapPage() throws Exception {

		msg = "Verify StoryMaps page is displayed";
		try {
			if (projectVersion.equals("12.1.0")|| projectVersion.equals("Kubernetes12.1.0")) {
				String actualtitle = cfunction.getElementText(CREATESTOERYMAP_12_1XPATH);
				if (actualtitle.equalsIgnoreCase("Create")) {
					CommonFunction.logStatus("PASS", msg);
					storymapPage = true;
				}else
					CommonFunction.logStatus("FAIL", msg);
			} else {
				if (cfunction.elementIsDisplayed(NEWSTORY_11_1XPATH, 10)) {
					CommonFunction.logStatus("PASS", msg);
					storymapPage = true;
				} else
					CommonFunction.logStatus("FAIL", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}

	}

	public void createStoryMap() throws Exception {

		msg = "Click on Create Story button";
		try {
			if (projectVersion.equals("12.1.0")|| projectVersion.equals("Kubernetes12.1.0")) {
				cfunction.Commmon_Click("xpath", CREATESTOERYMAP_12_1XPATH);
			} else
				cfunction.Commmon_Click("xpath", NEWSTORY_11_1XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}

		cfunction.sync(2);
		msg = "Click on New Story button";
		try {
			if (projectVersion.equals("12.1.0")|| projectVersion.equals("Kubernetes12.1.0")) {
				cfunction.Commmon_Click("xpath", NEWSTORY_12_1XPATH);
			} else
				cfunction.Commmon_Click("xpath", STARTFROMSRATCH_11_1XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}

		cfunction.sync(2);
		msg = "Enter Story Title as: " + StoryMapName;
		try {
			cfunction.CommonTextBox_Input(STORYMAPTITLE_11_1XPATH, StoryMapName);
			// CommonTextBox_InputJS
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}

		cfunction.sync(2);
		// in the header top right
		msg = "Verify the Story title is displayed on the header also as: " + StoryMapName;
		try {
			String actualtitle = cfunction.getElementText(STORYMAPTITLEHEADER_11_1XPATH);
			if (actualtitle.equalsIgnoreCase(StoryMapName))
				CommonFunction.logStatus("PASS", msg);
			else
				CommonFunction.logStatus("FAIL", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}

		cfunction.sync(2);
		msg = "Click on Publish button to publish the StoryMap";
		try {
			cfunction.Commmon_Click("xpath", STORYMAPPUBLISH_11_1XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}

		cfunction.sync(5);//
		msg = "Validate Publish options page is displayed";
		try {
			String actualtitle = cfunction.getElementText(STORYMAPPUBLISHOPTIONSPAGE_11_1XPATH);
			if (actualtitle.equalsIgnoreCase("Publish options"))
				CommonFunction.logStatus("PASS", msg);
			else
				CommonFunction.logStatus("FAIL", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}

		msg = "Again click on Publish button";
		try {
			cfunction.JSclickOnListOfElementBasedOnIndex(STORYMAPPUBLISH_11_1XPATH, 1);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}

		cfunction.sync(2);
		msg = "Wait for the StoryMap to get published";
		try {
			if (cfunction.elementIsDisplayed(STORYMAPTITLEHEADER_11_1XPATH, 10))
				CommonFunction.logStatus("PASS", msg);
			else
				CommonFunction.logStatus("FAIL", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}

		cfunction.sync(2);
		msg = "Verify the StoryMap is published successfully";
		if (projectVersion.equals("11.1.0") || projectVersion.equals("11.2.0") || projectVersion.equals("11.3.0")
				|| projectVersion.equals("11.4.0")) {
			try {
				String actualtitle = cfunction.getElementText(STORYMAPTITLEHEADER_11_1XPATH);
				if (actualtitle.equalsIgnoreCase(StoryMapName))
					CommonFunction.logStatus("PASS", msg);
				else
					CommonFunction.logStatus("FAIL", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg);
			}
		} else if (projectVersion.equals("11.5.0") || projectVersion.equals("12.0.0")
				|| projectVersion.equals("12.1.0")||projectVersion.equals("Kubernetes11.5.0")
				|| projectVersion.equals("Kubernetes12.1.0")) {
			try {
				if (cfunction.elementIsDisplayed(STORYMAPTITLE_11_5_XPATH, 10))
					CommonFunction.logStatus("PASS", msg);
				else
					CommonFunction.logStatus("FAIL", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg);
			}
		}

		msg = "Click on View published Story link";
		if (projectVersion.equals("11.5.0") || projectVersion.equals("Kubernetes11.5.0")) {
			try {
				cfunction.Commmon_Click("xpath", VIEWPUBLISHEDSTORY_11_5_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", "Failed to" + msg);
			}
		} else if (projectVersion.equals("12.0.0")) {
			try {
				cfunction.Commmon_Click("xpath", GOTOMYPROJECT_12_XPATH);
				CommonFunction.logStatus("PASS", "Go to My Projects page");
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", "Failed to" + msg);
			}

		}

		msg = "Click on Go to Story Contents";
		if (projectVersion.equals("12.1.0") || projectVersion.equals("Kubernetes12.1.0")) {
			try {
				cfunction.Commmon_Click("xpath", GOTOSTORYMAP_12_1XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", "Failed to" + msg);
			}

		}

		msg = "Click on profile and click My projects";
		if (projectVersion.equals("11.1.0") || projectVersion.equals("11.2.0") || projectVersion.equals("11.3.0")
				|| projectVersion.equals("11.4.0") || projectVersion.equals("11.5.0") 
				|| projectVersion.equals("Kubernetes11.5.0")) {

			try {
				cfunction.Commmon_Click("xpath", STORYMAPPROFILE_11_1XPATH);
				cfunction.sync(2);
				cfunction.Commmon_Click("xpath", STORYMYPROJECTS_11_1XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg);
			}
		}

		cfunction.sync(2);
		msg = "Verify Story Content page is opened";
		try {
			if (cfunction.elementIsDisplayed("// *[text()='Stories']", 5))
				CommonFunction.logStatus("PASS", "Verify Story Content page is opened");
			else
				CommonFunction.logStatus("FAIL", "Story Content page is not opened");
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}

		cfunction.sync(2);
		msg = "Verify the created StoryMap is displayed in the selected section";
		try {
			boolean found = false;
			if (projectVersion.equalsIgnoreCase("12.1.0") || projectVersion.equals("Kubernetes12.1.0")) {
				List<WebElement> storyList12 = cfunction.getListOfWebElements(PUBLISHEDSTORYMAP_12_1_XPATH);
				for (WebElement story : storyList12) {
					if (story.getText().equalsIgnoreCase(StoryMapName)) {
						found = true;
						break;

					}
				}
			} else {
			List<WebElement> storyList = cfunction.getListOfWebElements(PUBLISHEDSTORYMAP_11_1XPATH);
			for (WebElement story : storyList) {
				if (story.getText().equalsIgnoreCase(StoryMapName)) {
					found = true;
					break;
				}
			}}
			if (found) {
				CommonFunction.logStatus("PASS", msg);
			} else {
				CommonFunction.logStatus("FAIL", "No matching StoryMap found in the list");
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);

		}

		msg = "Switch back to Home page";
		try {
			cfunction.switchToMainWindow();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}

	}

}

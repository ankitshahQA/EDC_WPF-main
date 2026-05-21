package com.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.automation.PythonAPI.PythonHelp;
import com.automation.library.CommonFunction;
import com.automation.library.TestBase;

public class ExperienceBuilderApp extends TestBase {

	CommonFunction cfunction = new CommonFunction();
	HomePage hp = new HomePage();
	ContentPage cp = new ContentPage();
	String msg = "";

	final public String PUBLISH_BUTTON_XPATH = "//button[contains(@class,'tool-list-publish')]";
	final public String PENCIL_ICON_XPATH = "//div[contains(@class,'header-logo')]//button[contains(@class,'tooltip-button')]";
	final public String APP_NAMEINPUT_XPATH = "//input[@aria-label='App Title']";
	final public String PUBLISHMESSGAE_XPATH = "//div[@class='jimu-message-description']";
	final public String HOME_BUTTON_XPATH = "//div[contains(@class,'header-logo-item')]";
	final public String MENU_XPATH = "//span[@data-testid='test-context']";
	final public String CONTENT_MENU_XPATH = "//a[contains(@href,'content')]";
	final public String SKIP_XPATH = "//button[@data-action='skip']";

	public void createExperienceBuilderApp() throws Exception {

		cfunction.sync(5);

		// verify if popup appears
		msg = "Verify  if Getting started tour poup appears...and click Skip";
		try {
			if (cfunction.elementIsDisplayed(SKIP_XPATH, 5)) {
				cfunction.Commmon_Click("xpath", SKIP_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		msg = "Verify Experinece Builder template is opened successfully";
		try {
			if (cfunction.elementIsDisplayed(PUBLISH_BUTTON_XPATH))
				CommonFunction.logStatus("PASS", msg);
			else
				CommonFunction.logStatus("FAIL", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		cfunction.sync(2);
		msg = "Click on pencil icon to edit the App name";
		try {
			cfunction.Commmon_Click("xpath", PENCIL_ICON_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		cfunction.sync(2);
		msg = "Rename the App with name: " + ExperienceBuilderName;
		try {
			cfunction.CommonTextBox_InputWithClear(APP_NAMEINPUT_XPATH, ExperienceBuilderName);
			cfunction.PressEnterByAction();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		cfunction.sync(2);
		msg = "Click on Publish button to publish the App";
		try {
			cfunction.Commmon_Click("xpath", PUBLISH_BUTTON_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		msg = "Verify messge after publishing the App: Published successfully!";
		try {
			if (cfunction.elementIsDisplayed(APP_NAMEINPUT_XPATH)) {
				String toastmsg = cfunction.getElementText(PUBLISHMESSGAE_XPATH);
				if (toastmsg.equals("Published successfully!"))
					CommonFunction.logStatus("PASS", msg);
				else
					CommonFunction.logStatus("FAIL", msg);
			} else
				CommonFunction.logStatus("FAIL", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		cfunction.sync(2);
		if(projectVersion.equals("12.1")) {
		cfunction.sync(2);
		msg = "Click on Home button on top left corner to navigate back to home page";
		try {
			cfunction.Commmon_Click("xpath", HOME_BUTTON_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		msg = "Click on Menu icon on top left corner to open the dropdown menu";
		try {
			cfunction.Commmon_Click("xpath", MENU_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		msg = "Click Content option from the dropdown menu to navigate to content page";
		try {
			cfunction.Commmon_Click("xpath", CONTENT_MENU_XPATH);
			cfunction.switchTowindow(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}
		}
		
		cfunction.sync(2);
		msg = "Switch back to Main window and refresh the page";
		try {
			cfunction.switchToMainWindow();
			cfunction.refreshpage();
			CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
		}
		
		PythonHelp.MoveItemInfolder(ExperienceBuilderName);

	}

}

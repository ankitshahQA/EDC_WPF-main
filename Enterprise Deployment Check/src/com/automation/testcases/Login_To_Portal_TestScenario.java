package com.automation.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.pages.MapPage;

public class Login_To_Portal_TestScenario extends TestBase {

	CommonFunction cfunction = new CommonFunction();
	LoginPage lp = new LoginPage();
	HomePage hp = new HomePage();
	MapPage mp = new MapPage();
	String msg = "";
	boolean find = true;

	public void portal_login() throws Exception {

		// Step message
		msg = "Login to Portal ";
		childnode = logger.createNode(msg);
		CommonFunction.OpenURL(Portalurl);

		if (flagKUB) {
			lp.verifyPageLoadedand_sign_in();
			lp.loginToApplication(PortalUserName, PortalPassword);

		} else {
			List<WebElement> list1 = driver.findElements(By.xpath("//*[@id='oauth']"));
			if (list1.size() == 0) {
				lp.verifyPageLoadedand_sign_in();
			}
			List<WebElement> list2 = driver.findElements(By.xpath("//iframe[@id='oAuthFrame']"));
			if (list2.size() == 0) {
				lp.loginToApplication(PortalUserName, PortalPassword);

			} else {
				lp.loginToApplication_10_6_1(PortalUserName, PortalPassword);
			}
		}
	}

	public void portal_login_10_6_1() throws InterruptedException {

		// Step message
		msg = "Navigating to Portal URL";
		try {
			// driver.get(URL);
			CommonFunction.waitforpagetoload();
			lp.verifyPageLoadedand_sign_in();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}
		msg = "Login to Portal";
		try {
			// CommonFunction.waitforpagetoload();

			lp.loginToApplication_10_6_1(PortalUserName, PortalPassword);
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}
	}
}

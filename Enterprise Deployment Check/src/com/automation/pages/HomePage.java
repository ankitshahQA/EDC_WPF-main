package com.automation.pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;

public class HomePage extends TestBase {
	
	// XPath code
	// final String MAP_XPATH = "//a[contains(@class,'top-nav-link') and
	// contains(@href,'webmap')]";
	
	final String SETTINGS_12_0_0_XPATH="//calcite-menu-item[@data-id='settings']";
	final String SETTINGS_12_0_0_JSPATH="document.querySelector('calcite-menu > calcite-menu-item:nth-child(5)').shadowRoot.querySelector('li > div > a')";
	final String SETTINGS_12_1_0_JSPATH="document.querySelector('calcite-menu > calcite-menu-item:nth-child(6)').shadowRoot.querySelector('li > div > a')";
	
	
	
	
	
	final String MAP_XPATH = "//a[contains(@id,'esri-header-menus-link-desktop') and contains(@href,'webmap')]";
	final String CONTENT_XPATH = "//a[contains(@id,'esri-header-menus-link-desktop') and contains(@href,'content')]";
	final String CONTENT12_0__XPATH="//div[contains(@class,'esri-header-menus -desktop -no')]//a[contains(@class,'esri-header-menus-link') and contains(@href,'content')]";
	final String CONTENT_10_6_1_XPATH = "//a[contains(@id,'siteHeader-myContent') and contains(@href,'content')]";
	final String CONTENT_10_9_1_XPATH="(//a[contains(@href,'content.html')])[2]";
	final String CONTENT_TITLE_XPATH1 = "//h1[contains(@id,'title')and text()='Content']";
	final String HOME_XPATH = "//a[contains(@class,'top-nav-link') and contains(@href,'index')]";
	final String LOGO_XPATH = "//div[@id='logoDisplay']/span[text()='ArcGIS Enterprise']";
	final String TEST_XPATH = "//a[text()='Add Members']";
	final String CONTENT_MENU_ITEM = "//div[@id='esri-header-menus-desktop']//a[contains(@class,'esri-header-menus-link') and contains(@href,'content')]";
	String msg = "";
	final String MAP_MENU_ITEM = "//div[@id='esri-header-menus-desktop']//a[contains(@class,'esri-header-menus-link') and contains(@href,'map')]";
	// final String MAP_MENU_ITEM_10_6_1="//a[contains(@id,'pNavMap')]";
	final String MAP_MENU_ITEM_10_6_1 = "//a[contains(@id,'siteHeader-map') and contains(@href,'webmap')]";
	final String GROUPS_MENU_ITEM = "//div[@id='esri-header-menus-desktop']//a[contains(@class,'esri-header-menus-link') and contains(@href,'groups')]";
	final String GROUPS_MENU_ITEM_10_6_1 = "//a[contains(@id,'siteHeader-groups') and contains(@href,'groups')]";
	final String ORGANIZATION_MENU_ITEM = "//div[@id='esri-header-menus-desktop']//a[contains(@class,'esri-header-menus-link') and contains(@href,'organization')]";
	final String ORGANIZATION_MENU_ITEM_10_6_1 = "//a[contains(@id,'siteHeader-myOrganization') and contains(@href,'organization')]";
	final String SETTINGBUTTON_XPATH="//*[contains(@class,'subnav-links')]//a[contains(@data-tab,'setting')]";
	final String SERVER_XPATH="//li[contains(@id,'dijit__TemplatedMixin_') and button[text()='Servers']]";
	final String ServerRole_XPATH = "//div[@class='flex fed-server-card__row']//button[@class='btn btn-link']";
	final String SERVICEURL_XPATH="//calcite-link[@class='fed-server__label']";
	final String ADMINURL_XPATH="//div[@class='fed-server__label']//calcite-link";
	final String SERVER_STATUS_XPATH="//span[@class='tooltip tooltip-multiline tooltip-wide']";
	final String APPSWITCHER_JSPATH="document.querySelector('arcgis-app-switcher').shadowRoot.querySelector('calcite-button')";
	final String STORYMAPS_JSPATH="document.querySelector('arcgis-app-switcher').shadowRoot.querySelector('li[data-id=\"ArcGIS StoryMaps\"]')";
	final String DASHBOARDS_JSPATH="[...document.querySelector('arcgis-app-switcher').shadowRoot.querySelectorAll('arcgis-app-switcher-link')].find(link=>link.shadowRoot.querySelector('a')?.href.includes('dashboards'))";
	
	
	
	CommonFunction cfunction = new CommonFunction();

	public void navigateToHometPage() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Navigate to Home";
		try {

			cfunction.CommmonJS_Click(HOME_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void navigateToContentPage() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Navigate to content";
		try {
			cfunction.CommmonJS_Click(CONTENT_MENU_ITEM);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Wait for content page to load";
		try {
			cfunction.waitforpagetoload();
			if(projectVersion.equals("11.3.0")||(projectVersion.equalsIgnoreCase("11.4.0"))||(projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0")) ||projectVersion.equalsIgnoreCase("Kubernetes12.1.0")|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.waitforelement("//h1[ text()='Content']");
			}else {
			cfunction.waitforelement(CONTENT_TITLE_XPATH1);

		}} catch (Exception e) {
			try {

				cfunction.CommmonJS_Click(CONTENT_MENU_ITEM);
			} catch (Exception e1) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e1);

			}
			e.printStackTrace();

		}
	}

	public void navigateToContentPage_10_6_1() throws InterruptedException {
		// CommonFunction.waitforpagetoload();
		msg = "Navigate to content page";
		try {
			cfunction.CommmonJS_Click(CONTENT_10_6_1_XPATH);
			CommonFunction.logStatus("PASS", msg);
			//

		} catch (Exception e) {
			try {
				cfunction.CommmonJS_Click("//a[contains(@id,'pNavContent')]");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e1);
			}
		}
		msg = "Verify Content page displays";
		try {
			cfunction.waitforpagetoload();
			cfunction.waitforelement(CONTENT_TITLE_XPATH1);
			//CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			try {
				cfunction.CommmonJS_Click(CONTENT_10_6_1_XPATH);
				cfunction.sync(3);
				cfunction.waitforelement(CONTENT_TITLE_XPATH1);
				//CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				e.printStackTrace();
				//CommonFunction.logStatus("FAIL", msg);

			}

		}
	}

	public void click_on_map_home() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Click on MAP link";
		try {

			cfunction.CommmonJS_Click(MAP_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}
		// Map_landing_chrome

	}

	public void click_on_content_home() throws InterruptedException {
		
		cfunction.CommmonJS_Click(CONTENT_XPATH);

	}
public void click_on_content_home_12_0_0() throws InterruptedException {
		
		cfunction.CommmonJS_Click(CONTENT12_0__XPATH);

	}
	

	public void click_on_content_home_10_6_1() throws InterruptedException {
		// CommonFunction.waitforpagetoload();

		cfunction.CommmonJS_Click(CONTENT_10_6_1_XPATH);

	}
	
	public void click_on_content_home_10_9_1() throws InterruptedException {
		// CommonFunction.waitforpagetoload();

		cfunction.CommmonJS_Click(CONTENT_10_9_1_XPATH);

	}

	public void navigateToMapPage() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Navigate to Map page";
		try {
			cfunction.CommmonJS_Click(MAP_MENU_ITEM);
			CommonFunction.waitforpagetoload();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void navigateToMapPage_10_6_1() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Navigate to Map page";
		try {

			cfunction.CommmonJS_Click(MAP_MENU_ITEM_10_6_1);
			CommonFunction.waitforpagetoload();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void navigateToGroupsPage() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Navigate to groups";
		try {
			cfunction.ScrollToElement(GROUPS_MENU_ITEM);
			cfunction.CommmonJS_Click(GROUPS_MENU_ITEM);
			//cfunction.sync(10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void navigateToGroupsPage_10_6_1() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Navigate to groups";
		try {

			cfunction.CommmonJS_Click(GROUPS_MENU_ITEM_10_6_1);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e1) {
			e1.printStackTrace();
			try {

				cfunction.CommmonJS_Click(GROUPS_MENU_ITEM_10_6_1);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}

		}
	}

	public void navigateToOrganizationPage() throws Exception {
		// CommonFunction.waitforpagetoload();
		
		msg= "Navigate to organization";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(ORGANIZATION_MENU_ITEM);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void navigateToOrganizationPage_10_6_1() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Navigate to organization";
		try {

			cfunction.CommmonJS_Click(ORGANIZATION_MENU_ITEM_10_6_1);
			CommonFunction.waitforpagetoload();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}
	
	public void click_on_Setting() throws InterruptedException {
		try {
			
				if ((projectVersion.equalsIgnoreCase("12.0.0"))) {
					try {
						cfunction.sync(5);
						cfunction.switchToWidgetTemplateFrame();
						cfunction.hoverByAction(SETTINGS_12_0_0_XPATH);
						cfunction.clickusingActions(SETTINGS_12_0_0_JSPATH);
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatus("FAIL", msg);

					}
				} else if ((projectVersion.equalsIgnoreCase("12.1.0"))) {
					try {
						cfunction.sync(5);
						cfunction.switchtoqueryframe1();
						cfunction.hoverByAction(SETTINGS_12_0_0_XPATH);
						cfunction.clickusingActions(SETTINGS_12_1_0_JSPATH);
					} catch (Exception e) {
						e.printStackTrace();
					}
					// cfunction.switchToWidgetTemplateFrame();

				}else {
				cfunction.Commmon_Click("XPATH",SETTINGBUTTON_XPATH);
			}
			
			} catch (Exception e) {
			e.printStackTrace();
		}
		}
	
	public void click_on_Server() throws InterruptedException {
		if(projectVersion.equalsIgnoreCase("12.0.0")||(projectVersion.equalsIgnoreCase("12.1.0"))) {
			driver.switchTo().defaultContent();
			 cfunction.ScrollToElement(SERVER_XPATH);
			 cfunction.Commmon_Click("xpath", SERVER_XPATH);
		}else {
		 cfunction.ScrollToElement(SERVER_XPATH);
		 cfunction.Commmon_Click("xpath", SERVER_XPATH);
		}
	}
	
	public boolean verifyServerRole(String role) throws InterruptedException {
		boolean bool =false;
		cfunction.sync(10);
		cfunction.ScrollToElement(ServerRole_XPATH);
		cfunction.waitforelement(SERVER_STATUS_XPATH, 600);
		String serverRole  =cfunction.getElementText(ServerRole_XPATH);
		if(serverRole.equalsIgnoreCase(role))
		{
			bool = true;
		}
		return bool;
	}
	
	public boolean verifyServerStatus (String Status) throws InterruptedException {
		boolean bool =false;
		cfunction.sync(10);
		cfunction.ScrollToElement(SERVER_STATUS_XPATH);
		cfunction.waitforelement(SERVER_STATUS_XPATH, 600);
		String serverStatus  =cfunction.getElementText(SERVER_STATUS_XPATH);
		if(serverStatus.equalsIgnoreCase(Status))
		{
			bool = true;
		}
		return bool;
	}
	
	
	public boolean verifyServerLink(String role) throws InterruptedException {
		boolean bool =false;
		cfunction.sync(10);
		
		List <WebElement> Elements= cfunction.getWebElementList(SERVICEURL_XPATH);
		for(int i=0; i<Elements.size();i++) {
		String ServerURL=	Elements.get(i).getText();
		if(ServerURL.equalsIgnoreCase(role)){
			
			bool =true;
				}
	}
		return bool;
		}
		
	
	public boolean verifyAdminLink(String role) throws InterruptedException {
		boolean bool =false;
		CommonFunction.waitforpagetoload();
		String Expected_AdminURL=cfunction.getElementText(ADMINURL_XPATH);
		if(Expected_AdminURL.equalsIgnoreCase(role)){
				
			bool =true;
				}
		return bool;
	}
	
	public void selectStoryMaps() throws Exception {


		cfunction.refreshpage();
		cfunction.waitforpagetoload();

		navigateToContentPage();
		clickOnAppSwitcher();		
		selectStoryMapsFromAppSwitcher();
		cfunction.switchTowindow(1);
	}
	
	
	public  void clickOnAppSwitcher() throws InterruptedException {
		cfunction.Webelement_JSClick(APPSWITCHER_JSPATH);
	}
	public void selectStoryMapsFromAppSwitcher() throws InterruptedException {
		cfunction.sync(3);
		cfunction.Webelement_JSClick(STORYMAPS_JSPATH);
		cfunction.sync(3);
	}
	
	public void selectDashboards() throws Exception {
		cfunction.refreshpage();
		cfunction.waitforpagetoload();
		navigateToContentPage();
		selectDashboardFromAppSwitcher();
	}
	
	public  void selectDashboardFromAppSwitcher() throws InterruptedException {
		cfunction.Webelement_JSClick(APPSWITCHER_JSPATH);
		cfunction.sync(5);
		cfunction.clickusingActions(DASHBOARDS_JSPATH);
	}
		
}

package com.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;

public class OrganizationPage extends TestBase {

	// XPath code
	final String PAGE_X_OF_MEMBERSLIST12_0_XPATH = "//arcgis-member-browser-content//arcgis-member-browser-table-row";
	final String PAGE_X_OF_MEMBERSLIST_XPATH_10_6_1 = "//div[contains(@class,'page')]//following-sibling::a";
	final String PAGE_X_OF_MEMBERSLIST_XPATH = "//span[contains(@class,'pagination-links')]/a";
	final String MAP_XPATH = "//a[contains(@class,'top-nav-link') and contains(@href,'webmap')]";
	final String CONTENT_XPATH = "//a[contains(@class,'top-nav-link') and contains(@href,'content')]";
	final String DELETE_USER1_ACTION11_3_XPATH = "(//button[@data-action='deleteMember'])";
	final String CONTENT_MENU_ITEM = "//div[@id='esri-header-menus-desktop']//a[contains(@class,'esri-header-menus-link') and contains(@href,'content')]";
	String msg = "";
	final String GROUPS_MENU_ITEM = "//div[@id='esri-header-menus-desktop']//a[contains(@class,'esri-header-menus-link') and contains(@href,'groups')]";
	final String GROUPS_TITLE_XPATH = "//h1[@id='pagetitle'and text()='Groups']";
	final String PAGE_X_OF_MEMBERSLIST11_3_XPATH = "//*[contains(@id,'uniqName_10_')]//div[@class=\"org-member-full-name avenir-demi\"]//a";
	final String ORGANIZATION_TITLE_XPATH = "//div[@id='esri-header-menus-desktop']//a[contains(@class,'esri-header-menus-link') and contains(@class,'active')and contains(@href,'organization')]";
	final String ORGANIZATION_12_0_0_TITLE_XPATH = "//div[contains(@class,'esri-header-menus -desktop -no')]//a[contains(@class,'esri-header-menus-link') and contains(@href,'organization')]";
	final String ORGANIZATION_TITLE_XPATH_10_6_1 = "//a[contains(@class,'top-nav-link') and contains(@class,'active')and contains(@href,'organization')]";
	final String CREATE_GROUP_XPATH = "//a[@href='./group.html']";
	final String MEMBERS_TAB_XPATH = "//a[@data-tab='members']";
	final String Add_MEMBERS_12_0_XPATh = "//div[contains(@class,'org-members__top-bar-actions')]//calcite-button[text()='Add members']";
	final String ADD_MEMBERS_XPATH = "//button[@data-action='inviteMembers' and text()='Add Members']";
	final String ADD_MEMBERS_XPATH_10_6_1 = "//span[@id='organization-adduser-btn_label']";
	final String NEXT_XPATH = "//button[@class='btn js-overlay-primary']";
	final String NEXT_12_0_XPATH = "//calcite-button[@slot='footer-end' and text()='Next']";
	final String NEXT_XPATH_10_6_1 = "//div[@data-dojo-attach-point='_createTypeContainer']//span[contains(@class,'dijitButtonText' )and text()='Next']";
	final String NEW_MEMBER_XPATH = "//button[@class='btn org-members-invite-btn js-add-member']";
	final String NEW_MEMBER_12_0_XPATH = "//calcite-button[@title='New member']";
	final String NEXT_MEMBER_XPATH_10_6_1 = "//td[@class='esriAlignTrailing']//span[contains(@class,'dijitButtonText' )and text()='Next']";
	final String FIRST_NAME_XPATH = "//input[@name='firstname']";
	final String FIRST_NAME_12_0_JSPATH = "document.querySelector(\"div > calcite-input-text\").shadowRoot.querySelector(\"div.element-wrapper > input[aria-label='First name']\")";
	final String LEVEL_MEMBER_XPATH_10_6_1 = "//input[@id='createSingleLevel1Radio']//..";
	final String LAST_NAME_XPATH = "//input[@name='lastname']";
	final String LAST_NAME_12_0_JSPATH = "document.querySelector('calcite-label:nth-child(2) > div > calcite-input-text').shadowRoot.querySelector('div.element-wrapper > input')";
	final String EMAIL_XPATH = "//input[@name='email']";
	final String EMAIL_12_0_JSPATH = "document.querySelector(' div:nth-child(3) div > calcite-input-text').shadowRoot.querySelector('input')";
	final String FIRST_NAME_XPATH_10_6_1 = "//input[@id='createSingleFirstName']";
	final String LAST_NAME_XPATH_10_6_1 = "//input[@id='createSingleLastName']";
	final String EMAIL_XPATH_10_6_1 = "//input[@id='createSingleEmail']";
	final String USERNAME_XPATH_10_6_1 = "//input[@id='createSingleUsername']";
	final String PASSWORD_XPATH_10_6_1 = "//input[@id='createSinglePassword']";
	final String USERNAME_XPATH = "//input[@name='username']";
	final String USERNAME_12_0_JSPATH = "document.querySelector('div:nth-child(3) > calcite-label:nth-child(2) > div > calcite-input-text').shadowRoot.querySelector('input')";
	final String PASSWORD_XPATH = "//input[@name='password']";
	final String PASSWROD_12_0_JSPATH = "document.querySelector('calcite-label:nth-child(2) > calcite-input').shadowRoot.querySelector('input')";
	final String NEXT_MEMBER_XPATH = "//button[contains(@class,'add invite-members-form--add-btn')]";
	final String NEXT_TO_SAVE_MEMEBR_12_0_JSPATH = "//calcite-button[text()='Next']";
	final String NEXT_MEMBER_12_0_XPATH = "//calcite-button[text()='Save']";
	final String RETURN_ORGANIZATION_XPATH = "//div[@data-dojo-attach-point='_completed']//span[contains(@class,'dijitButtonText' )and contains(text(),'Organization')]";
	final String SECOND_MEMBER_XPATH = "//button[@class='btn-link margin-right-2 js-add-member']";
	final String SECOND_MEMBER_12_0_XPATH = "//calcite-button[@icon-start='plus-circle']";
	final String SECOND_MEMBER_XPATH_10_6_1 = "//div[@data-dojo-attach-point='_step3']//span[contains(@class,'dijitButtonText' )and contains(text(),'Members')]";
	final String NEW_MEMBER_XPATH_10_6_1 = "//div[@data-dojo-attach-point='_completed']//span[contains(@class,'dijitButtonText' )and contains(text(),'member')]";
	final String NEXT_LICENSE_XPATH = "//button[@class='btn js-overlay-primary']";
	final String FOOTER_ADD_MEMBERS_12_0_XPATH = "//calcite-button[@slot='footer-end' and text()='Add members']";
	final String USER1_XPATH = "//a[@title='Test " + Testuser1 + "']";
	final String USER2_XPATH = "//a[@title='Test " + Testuser2 + "']";
	final String AllCREATEDUSERS_JSPATH = "document.querySelector('arcgis-member-browser-table > arcgis-member-browser-table-row:nth-child(%s)').shadowRoot.querySelector('span > arcgis-user-popup').shadowRoot.querySelector(' arcgis-user-avatar').shadowRoot.querySelector('span > span.title.has-description.text--clamp')";
	final String USER1_XPATH_10_6_1 = "//a[text()='Test " + Testuser1 + "']";
	final String USER2_XPATH_10_6_1 = "//a[text()='Test " + Testuser2 + "']";
	final String USER1_11_3ACTION_XPATH = "//*[contains(@id,'uniqName_10_')]//div[@class=\"org-member-full-name avenir-demi\"]//a[text()='+Testuser1+']";
	final String USER2_11_3ACTION_XPATH = "//*[contains(@id,'uniqName_10_')]//div[@class=\"org-member-full-name avenir-demi\"]//a[text()='+Testuser2+']";

	final String DELETE_USER1_ACTION_XPATH = "//a[@title='Test " + Testuser1
			+ "']/../../../../../span[contains(@class,'action-column')]//button";
	final String DELETE_USER2_ACTION_XPATH = "//a[@title='Test " + Testuser2
			+ "']/../../../../../span[contains(@class,'action-column')]//button";
	final String DELETE_USER1_ACTION_XPATH_10_6_1 = "//a[text()='Test " + Testuser1
			+ "']/../../..//img[contains(@class,'actionsArrow')]";
	final String DELETE_USER2_ACTION_XPATH_10_6_1 = "//a[text()='Test " + Testuser2
			+ "']/../../..//img[contains(@class,'actionsArrow')]";
	final String DELETE_ACTION_12_0_JSPATH = "document.querySelector(\" arcgis-member-browser-content\").shadowRoot.querySelector('calcite-dropdown-item[icon-start=\"trash\"]')";
	final String DELETE_ACTION_XPATH = "//div[contains(@class,'js-dropdown is-active')]//button[contains(@data-action,'deleteMember')]";
	final String DELETE_ACTION_XPATH_10_6_1 = "//td[contains(@id,'userActionMenu-delete_text')]";
	final String STATUS_XPATH = "//a[@href='#status']";
	final String STATUS_XPATH_10_6_1 = "//span[@id='organization-view-status-button_label']";
	final String MEMBER_XPATH = "//a[@href='#members']";
	final String MEMBER_12_0_0_XPATH = "//calcite-menu-item[@data-id='members']";
	final String MEMBER_12_0_0_JSPATH = "document.querySelector('calcite-menu > calcite-menu-item:nth-child(2)').shadowRoot.querySelector('li > div > a')";
	final String STATUS_CONTENT_TAB_XPATH = "//span[@role='tab' and text()='Content']";
	final String STATUS_MEMBERS_TAB_XPATH = "//span[@role='tab' and text()='Members']";
	final String STATUS_GROUPS_TAB_XPATH = "//span[@role='tab' and text()='Groups']";
	final String DELETE_OK_XPATH = "//span[@id='button_delete-warning-submit_label']";
	final String DELETE_OK_11_1_XPATH = "//calcite-button[@data-test='ok']";
	final String ITEM_REPORTS_XPATH = "//div[@id='itemAccessReport']";
	final String ITEM_COUNT_XPATH = "//div[@id='itemsCountReport']";
	final String ITEM_CHART_XPATH = "//div[@id='itemsSumChartReport']";
	// members
	final String USER_PROFILE_XPATH = "//p[@id='uProfile']";
	final String USER_DETAIL_XPATH = "//div[@id='uItemsDetailsDiv']";
	final String ACTIVE_MEMBER_XPATH = "//p[@id='uAccountsTitle']";
	final String ACCOUNT_TYPE_XPATH = "//p[@id='uAccountsTypesTitle']";
	// groups
	final String GROUPS_COUNT_XPATH = "//p[@id='groupsTotalCount']";
	final String GROUPS_OWNERS_XPATH = "//p[@id='gTopOwners']";
	final String GROUPS_REPORT_XPATH = "//div[@id='groupsBySharingChart']";
	final String FEATURED_GROUP_XPATH = "//p[@id='gFeaturedTitle']";
	// add-on Lincene
	final String MANAGE_ADDON_LICENSE_12_0_JSPATH = "document.querySelector('arcgis-member-browser > arcgis-member-browser-content').shadowRoot.querySelector('calcite-dropdown-group:nth-child(2) > calcite-dropdown-item:nth-child(1)')";
	final String MANAGE_ADDON_LICENSE_XPATH = "//div[contains(@class,'js-dropdown is-active')]//button[contains(@data-action,'manageLicenses')]";
	final String LICENSE_DESELECT_XPATH = "//div[contains(@class,'licenses__heading')]//button[contains(text(),'Deselect')]";
	final String LICENSE_SAVE_XPATH = "//div[contains(@class,'overlay-action-bar ')]//button[text()='Save']";
	final String LICENSE_CANCEL_XPATH = "(//div[contains(@class,'overlay-action-bar ')]//nav//button)[2]";
	final String LICENSE_CANCEL12_0_XPATH = "//calcite-dialog[3]/calcite-button[1]";
	final String NEXT_PAGE_XPATH_10_6_1 = "//img[@data-dojo-attach-point='_nextButton']";
	final String TOTAL_PAGES_10_6_1 = "//span[@data-dojo-attach-point='_userPages']";

	final String STATUS_12_0_0_XPATH = "//calcite-menu-item[@data-id='status']";
	final String STATUS_12_0_0_JSPATH = "document.querySelector('calcite-menu > calcite-menu-item:nth-child(4)').shadowRoot.querySelector('li > div > a')";
    final String MOREBUTTON_XPATH="//button[@data-dojo-attach-point='moreBtn']";
	final String DELETEALLMEMBERS_XPATH="//button[@data-dojo-attach-point='deleteMembers']";
    
    
    
    
    
    CommonFunction cfunction = new CommonFunction();

	public void navigateToContentPage() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Navigate to content";
		try {

			cfunction.CommmonJS_Click(CONTENT_MENU_ITEM);
			CommonFunction.waitforpagetoload();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void verifyorganizationpage() throws InterruptedException {
		// CommonFunction.waitforpagetoload();
		msg = "Verify organization page";
		try {
			cfunction.waitforelement(ORGANIZATION_12_0_0_TITLE_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void verifyorganizationpage_12_0_0() throws InterruptedException {
		// CommonFunction.waitforpagetoload();

		try {
			cfunction.waitforelement(ORGANIZATION_12_0_0_TITLE_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void verifyorganizationpage_10_6_1() throws InterruptedException {
		// CommonFunction.waitforpagetoload();

		try {
			cfunction.waitforelement(ORGANIZATION_TITLE_XPATH_10_6_1);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void verifymembersdeleted() throws Exception {
		msg = "Verify user1 deleted";
		try {
			cfunction.sync(2);
			cfunction.waitforinvisibilityofelement(USER1_XPATH, 5);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify user2 deleted";
		try {

			cfunction.waitforinvisibilityofelement(USER2_XPATH, 5);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void deletemember() throws Exception {
		// CommonFunction.waitforpagetoload();
		boolean flag1 = true;
		msg = "Click on member tab";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(MEMBERS_TAB_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Check for user license of memeber 1";
		try {

			cfunction.sync(2);
			List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST_XPATH));
			int size = itemListString.size();
			for (int i = 0; i <= size; i++) {
				if (cfunction.elementexist(DELETE_USER1_ACTION_XPATH,10)) {
					break;
				} else
					cfunction.JSclickOnListOfElementBasedOnIndex(PAGE_X_OF_MEMBERSLIST_XPATH, i + 1);
			}
			cfunction.CommmonJS_Click(DELETE_USER1_ACTION_XPATH);
			cfunction.sync(2);
			cfunction.CommmonJS_Click(MANAGE_ADDON_LICENSE_XPATH);
			cfunction.sync(2);
			if (cfunction.elementIsDisplayed(LICENSE_DESELECT_XPATH,10)) {
				cfunction.CommmonJS_Click(LICENSE_DESELECT_XPATH);
				cfunction.sync(2);
				cfunction.CommmonJS_Click(LICENSE_SAVE_XPATH);
				CommonFunction.logStatus("PASS", "License deselected for User");
			} else {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(LICENSE_CANCEL_XPATH);
			}
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		msg = "Click on action button to delete member 1";
		try {
			/*
			 * cfunction.sync(2); cfunction.CommmonJS_Click(DELETE_USER1_ACTION_XPATH);
			 * CommonFunction.logStatus("PASS", msg);
			 */

			cfunction.sync(2);
			List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST_XPATH));
			int size = itemListString.size();
			for (int i = 0; i <= size; i++) {
				if (cfunction.elementexist(DELETE_USER1_ACTION_XPATH,5)) {
					break;
				} else
					cfunction.JSclickOnListOfElementBasedOnIndex(PAGE_X_OF_MEMBERSLIST_XPATH, i + 1);
			}
			cfunction.CommmonJS_Click(DELETE_USER1_ACTION_XPATH);
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				cfunction.CommmonJS_Click(DELETE_USER1_ACTION_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				e1.printStackTrace();
				flag1 = false;
				CommonFunction.logStatusWithException("FAIL", msg, e1);
			}

		}

		if (flag1) {
			msg = "Click on delete";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(DELETE_ACTION_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					cfunction.CommmonJS_Click(DELETE_ACTION_XPATH);
				} catch (Exception e1) {
					e1.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);
				}
			}

			msg = "Click on delete confirmation dialog box";
			try {
				cfunction.sync(2);
				if (projectVersion.equalsIgnoreCase("11.1.0")) {
					cfunction.Commmon_Click("xpath", DELETE_OK_11_1_XPATH);
				} else if (projectVersion.equalsIgnoreCase("11.2.0")) {
					cfunction.confirmdelete11_2_0();
				} else
					cfunction.Commmon_Click("xpath", DELETE_OK_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			try {
				cfunction.waitforinvisibilityofelement(DELETE_OK_XPATH, 20);
				// CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				// CommonFunction.logStatus("FAIL", msg+e);

			}
			msg = "Verify user1 deleted";
			try {
				cfunction.waitforpagetoload();
				cfunction.sync(2);
				cfunction.waitforinvisibilityofelement(USER1_XPATH, 5);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					driver.navigate().refresh();
					cfunction.waitforpagetoload();
					cfunction.sync(2);
					cfunction.waitforinvisibilityofelement(USER1_XPATH, 5);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e1) {
					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e1);

				}

			}
		}

		flag1 = true;

		msg = "Check for user license of member2";
		try {

			cfunction.sync(2);
			List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST_XPATH));
			int size = itemListString.size();
			for (int i = 0; i <= size; i++) {
				if (cfunction.elementexist(DELETE_USER2_ACTION_XPATH,10)) {
					break;
				} else
					cfunction.JSclickOnListOfElementBasedOnIndex(PAGE_X_OF_MEMBERSLIST_XPATH, i + 1);
			}
			cfunction.CommmonJS_Click(DELETE_USER2_ACTION_XPATH);
			cfunction.sync(2);
			cfunction.CommmonJS_Click(MANAGE_ADDON_LICENSE_XPATH);
			cfunction.sync(2);
			if (cfunction.elementIsDisplayed(LICENSE_DESELECT_XPATH,10)) {
				cfunction.CommmonJS_Click(LICENSE_DESELECT_XPATH);
				cfunction.sync(2);
				cfunction.CommmonJS_Click(LICENSE_SAVE_XPATH);
				CommonFunction.logStatus("PASS", "License deselected for User");
			} else {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(LICENSE_CANCEL_XPATH);
			}
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		msg = "Click on action button to delete member 2";
		try {
			/*
			 * cfunction.sync(2); cfunction.CommmonJS_Click(DELETE_USER1_ACTION_XPATH);
			 * CommonFunction.logStatus("PASS", msg);
			 */

			cfunction.sync(2);
			List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST_XPATH));
			int size = itemListString.size();
			for (int i = 0; i <= size; i++) {
				if (cfunction.elementexist(DELETE_USER2_ACTION_XPATH,5)) {
					break;
				} else
					cfunction.JSclickOnListOfElementBasedOnIndex(PAGE_X_OF_MEMBERSLIST_XPATH, i + 1);
			}
			cfunction.CommmonJS_Click(DELETE_USER2_ACTION_XPATH);
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				cfunction.CommmonJS_Click(DELETE_USER1_ACTION_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				e1.printStackTrace();
				flag1 = false;
				CommonFunction.logStatusWithException("FAIL", msg, e1);
			}

		}
		if (flag1) {
			msg = "Click on delete";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(DELETE_ACTION_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					cfunction.CommmonJS_Click(DELETE_USER2_ACTION_XPATH);
					cfunction.sync(2);
					cfunction.CommmonJS_Click(DELETE_ACTION_XPATH);
				} catch (Exception e1) {
					e1.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e1);
				}
			}
			msg = "Click on delete confirmation dialog box";
			try {
				cfunction.sync(2);
				if (projectVersion.equalsIgnoreCase("11.1.0")) {
					cfunction.Commmon_Click("xpath", DELETE_OK_11_1_XPATH);
				} else if (projectVersion.equalsIgnoreCase("11.2.0") || projectVersion.equalsIgnoreCase("11.3.0")
						|| (projectVersion.equalsIgnoreCase("11.4.0")) || (projectVersion.equalsIgnoreCase("11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
						|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
					cfunction.confirmdelete11_2_0();
				} else
					cfunction.Commmon_Click("xpath", DELETE_OK_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			try {
				cfunction.waitforinvisibilityofelement(DELETE_OK_XPATH, 20);
				// CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				// CommonFunction.logStatus("FAIL", msg+e);

			}
			msg = "Verify user2 deleted";
			try {

				cfunction.waitforpagetoload();
				cfunction.waitforinvisibilityofelement(USER2_XPATH, 15);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					driver.navigate().refresh();
					cfunction.waitforpagetoload();
					cfunction.sync(2);
					cfunction.waitforinvisibilityofelement(USER2_XPATH, 15);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e1) {
					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e1);

				}
			}
		}
	}

	public void deletemember11_3() throws Exception {
		// CommonFunction.waitforpagetoload();
		boolean flag1 = true;
		msg = "Click on member tab";
		try {
			if (projectVersion.equals("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				cfunction.sync(2);
				cfunction.hoverByAction(MEMBER_12_0_0_XPATH);
				cfunction.clickusingActions(MEMBER_12_0_0_JSPATH);
				CommonFunction.logStatus("PASS", msg);
			} else {
				cfunction.CommmonJS_Click(MEMBERS_TAB_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Check for user license of memeber 1";
		try {
			cfunction.sync(2);
			if (projectVersion.equalsIgnoreCase("11.1.0")||projectVersion.equalsIgnoreCase("11.5.0") || projectVersion.equalsIgnoreCase("11.3.0")) {
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST11_3_XPATH));//number of members in the page
				int size = itemListString.size();
				for (int i =size; i > 0 ; i--) {
					String MEMEBR11_5_XPATH ="(//a[@data-action='orgProfilePopup'])";
					String VIEWDETISL_11_5_XPATH = "(//button[@data-dojo-attach-point='_actionsBtn'])";
					String text = driver.findElement(By.xpath(MEMEBR11_5_XPATH + "[" + (i) + "]")).getText();
					if (text.contains("Test " + Testuser1)) {
						cfunction.ScrollToElement(VIEWDETISL_11_5_XPATH + "[" + (i) + "]");
						//cfunction.clickusingActions(VIEWDETISL_11_5_XPATH + "[" + (i) + "]");
						cfunction.clickxpathusingActions(VIEWDETISL_11_5_XPATH + "[" + (i) + "]");
						break;
					}
				}

			} else if (projectVersion.equalsIgnoreCase("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST12_0_XPATH));
				int size = itemListString.size();
				// System.out.println(size);
				for (int i = size; i > 0; i--) {
					String MEMEBR12_0_JSPATH = "(document.querySelector('arcgis-member-browser > arcgis-member-browser-content > arcgis-member-browser-table > arcgis-member-browser-table-row:nth-child(%s)').shadowRoot.querySelector('arcgis-user-popup').shadowRoot.querySelector('button > slot > arcgis-user-avatar').shadowRoot.querySelector('span>span'))";
					String VIEWDETISL_12_0_JSPATH = "document.querySelector('arcgis-member-browser > arcgis-member-browser-content > arcgis-member-browser-table > arcgis-member-browser-table-row:nth-child(%s)').shadowRoot.querySelector('span > span > button')";

					WebElement element = cfunction.Webelement_JSPath(String.format(MEMEBR12_0_JSPATH, (i)));
					// WebElement element = cfunction.Webelement_JSPath(MEMEBR12_0_JSPATH + "[" +
					// (i) + "]");
					if (element.getText().contains("Test " + Testuser1)) {
						cfunction.ScrollToWebElement(String.format(VIEWDETISL_12_0_JSPATH, (i)));
						cfunction.clickusingActions(String.format(VIEWDETISL_12_0_JSPATH, (i)));
						break;
					}
				}
			} else {
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST11_3_XPATH));
				int size = itemListString.size();
				for (int i = size; i > 0; i--) {
					String MEMEBR11_3_JSPATH = "document.querySelector('div.table-select-row-avatar:nth-child(%s) > span.table-select-cell.org-member-user-column > div > div.flex-1.org-member__name-col > div.org-member-full-name.avenir-demi > a')";
					String VIEWDETISL_11_3_JSPATH = "document.querySelector('div.table-select-row-avatar:nth-child(%s)> span.table-select-cell.org-members__action-column.org-members__action-column-body.table-select-cell-overflow > div > button')";
					WebElement element = cfunction.Webelement_JSPath(String.format(MEMEBR11_3_JSPATH, (i)));
					if (element.getText().contains("Test " + Testuser1)) {
						cfunction.ScrollToWebElement(String.format(VIEWDETISL_11_3_JSPATH, (i)));
						cfunction.clickusingActions(String.format(VIEWDETISL_11_3_JSPATH, (i)));
						break;
					}
				}
			}
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}
		
		msg = "Check on Manage Addon";
		if (projectVersion.equals("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0"))
				|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
			cfunction.Webelement_JSClick(MANAGE_ADDON_LICENSE_12_0_JSPATH);
			cfunction.sync(2);
			cfunction.CommmonJS_Click(LICENSE_CANCEL12_0_XPATH);

		} else {
			cfunction.CommmonJS_Click(MANAGE_ADDON_LICENSE_XPATH);
			cfunction.sync(2);
			if (cfunction.elementIsDisplayed(LICENSE_DESELECT_XPATH,5)) {
				cfunction.CommmonJS_Click(LICENSE_DESELECT_XPATH);
				cfunction.CommmonJS_Click(LICENSE_SAVE_XPATH);
				CommonFunction.logStatus("PASS", "License deselected for User");
			} else {
				cfunction.CommmonJS_Click(LICENSE_CANCEL_XPATH);
			}
			CommonFunction.logStatus("PASS", msg);
		}

		msg = "Click on action button to delete member 1";
		try {

			if (projectVersion.equalsIgnoreCase("11.1.0")||projectVersion.equalsIgnoreCase("11.5.0") || projectVersion.equalsIgnoreCase("11.3.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))) {
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST11_3_XPATH));
				int size = itemListString.size();
				for (int i = size; i > 0; i--) {
					String MEMEBR11_5_XPATH ="(//a[@data-action='orgProfilePopup'])";
					String VIEWDETISL_11_5_XPATH = "(//button[@data-dojo-attach-point='_actionsBtn'])";
					String text=driver.findElement(By.xpath(MEMEBR11_5_XPATH + "[" + (i) + "]")).getText();
					if (text.contains("Test " + Testuser1)) {
						cfunction.ScrollToElement(VIEWDETISL_11_5_XPATH);
						cfunction.clickxpathusingActions(VIEWDETISL_11_5_XPATH + "[" + (i) + "]");
						break;
					}
				}
			} else if (projectVersion.equalsIgnoreCase("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST12_0_XPATH));
				int size = itemListString.size();
				for (int i = size; i > 0; i--) {
					String MEMEBR12_0_JSPATH = "(document.querySelector('arcgis-member-browser > arcgis-member-browser-content > arcgis-member-browser-table > arcgis-member-browser-table-row:nth-child(%s)').shadowRoot.querySelector('arcgis-user-popup').shadowRoot.querySelector('button > slot > arcgis-user-avatar').shadowRoot.querySelector('span>span'))";
					String VIEWDETISL_12_0_JSPATH = "document.querySelector('arcgis-member-browser > arcgis-member-browser-content > arcgis-member-browser-table > arcgis-member-browser-table-row:nth-child(%s)').shadowRoot.querySelector('span > span > button')";

					WebElement element = cfunction.Webelement_JSPath(String.format(MEMEBR12_0_JSPATH, (i)));
					// WebElement element = cfunction.Webelement_JSPath(MEMEBR12_0_JSPATH + "[" +
					// (i) + "]");
					if (element.getText().contains("Test " + Testuser1)) {
						cfunction.ScrollToWebElement(String.format(VIEWDETISL_12_0_JSPATH, (i)));
						cfunction.clickusingActions(String.format(VIEWDETISL_12_0_JSPATH, (i)));
						break;
					}
				}
			} else {
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST11_3_XPATH));
				int size = itemListString.size();
				for (int i = size; i > 0; i--) {
					String MEMEBR11_3_JSPATH = "document.querySelector('div.table-select-row-avatar:nth-child(%s) > span.table-select-cell.org-member-user-column > div > div.flex-1.org-member__name-col > div.org-member-full-name.avenir-demi > a')";
					String VIEWDETISL_11_3_JSPATH = "document.querySelector('div.table-select-row-avatar:nth-child(%s) > span.table-select-cell.org-members__action-column.org-members__action-column-body.table-select-cell-overflow > div > button')";
					WebElement element = cfunction.Webelement_JSPath(String.format(MEMEBR11_3_JSPATH, (i)));
					if (element.getText().contains("Test " + Testuser1)) {
						cfunction.ScrollToWebElement((String.format(VIEWDETISL_11_3_JSPATH, (i))));
						cfunction.clickusingActions(String.format(VIEWDETISL_11_3_JSPATH, (i)));
						// cfunction.clickusingActions(String.format(DELETE_USER1_ACTION11_3_XPATH,(i+1)));

						break;
					}
				}
			}
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				cfunction.CommmonJS_Click(DELETE_USER1_ACTION_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				e1.printStackTrace();
				flag1 = false;
				CommonFunction.logStatusWithException("FAIL", msg, e1);
			}

		}

		if (flag1) {
			msg = "Click on delete";
			try {
				if (projectVersion.equalsIgnoreCase("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
					cfunction.Webelement_JSClick(DELETE_ACTION_12_0_JSPATH);
					CommonFunction.logStatus("PASS", msg);
				} else {
					cfunction.CommmonJS_Click(DELETE_ACTION_XPATH);
					CommonFunction.logStatus("PASS", msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					cfunction.CommmonJS_Click(DELETE_ACTION_XPATH);
				} catch (Exception e1) {
					e1.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);
				}
			}

			msg = "Click on delete confirmation dialog box";
			try {
				cfunction.sync(2);
				if (projectVersion.equalsIgnoreCase("11.1.0")) {
					cfunction.Commmon_Click("xpath", DELETE_OK_11_1_XPATH);
				} else if (projectVersion.equalsIgnoreCase("11.2.0") || projectVersion.equalsIgnoreCase("11.3.0")
						|| (projectVersion.equalsIgnoreCase("11.4.0"))) {
					cfunction.confirmdelete11_2_0();
				} else if ((projectVersion.equalsIgnoreCase("11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
					cfunction.confirmDialogdelete11_5_0();
				} else
					cfunction.Commmon_Click("xpath", DELETE_OK_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}

		}

		flag1 = true;

		msg = "Check for user license of member2";
		try {
			cfunction.sync(2);
			if (projectVersion.equalsIgnoreCase("11.1.0")||projectVersion.equalsIgnoreCase("11.5.0") || projectVersion.equalsIgnoreCase("11.3.0")
					|| projectVersion.equalsIgnoreCase("11.4.0")) {
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST11_3_XPATH));
				int size = itemListString.size();
				for (int i = size; i > 0; i--) {
					String MEMEBR11_5_XPATH ="(//a[@data-action='orgProfilePopup'])";
					String VIEWDETISL_11_5_XPATH = "(//button[@data-dojo-attach-point='_actionsBtn'])";
					String text = driver.findElement(By.xpath(MEMEBR11_5_XPATH + "[" + (i) + "]")).getText();
					cfunction.ScrollToElement(VIEWDETISL_11_5_XPATH + "[" + (i) + "]");
					cfunction.sync(2);
					if (text.contains("Test " + Testuser2)) {
						//cfunction.clickusingActions(VIEWDETISL_11_5_XPATH + "[" + (i) + "]");
						cfunction.clickxpathusingActions(VIEWDETISL_11_5_XPATH + "[" + (i) + "]");
						break;
					}
				}
			} else if (projectVersion.equalsIgnoreCase("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST12_0_XPATH));
				int size = itemListString.size();
				for (int i = size; i > 0; i--) {
					String MEMEBR12_0_JSPATH = "(document.querySelector('arcgis-member-browser > arcgis-member-browser-content > arcgis-member-browser-table > arcgis-member-browser-table-row:nth-child(%s)').shadowRoot.querySelector('arcgis-user-popup').shadowRoot.querySelector('button > slot > arcgis-user-avatar').shadowRoot.querySelector('span>span'))";
					String VIEWDETISL_12_0_JSPATH = "document.querySelector('arcgis-member-browser > arcgis-member-browser-content > arcgis-member-browser-table > arcgis-member-browser-table-row:nth-child(%s)').shadowRoot.querySelector('span > span > button')";

					WebElement element = cfunction.Webelement_JSPath(String.format(MEMEBR12_0_JSPATH, (i)));
					// WebElement element = cfunction.Webelement_JSPath(MEMEBR12_0_JSPATH + "[" +
					// (i) + "]");
					if (element.getText().contains("Test " + Testuser2)) {
						cfunction.ScrollToWebElement(String.format(VIEWDETISL_12_0_JSPATH, (i)));
						cfunction.clickusingActions(String.format(VIEWDETISL_12_0_JSPATH, (i)));
						break;
					}
				}
			} else {
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST11_3_XPATH));
				int size = itemListString.size();
				for (int i = size; i > 0; i--) {
					cfunction.sync(2);
					String MEMEBR11_3_JSPATH = "document.querySelector('div.table-select-row-avatar:nth-child(%s) > span.table-select-cell.org-member-user-column > div > div.flex-1.org-member__name-col > div.org-member-full-name.avenir-demi > a')";
					String VIEWDETISL_11_3_JSPATH = "document.querySelector('div.table-select-row-avatar:nth-child(%s)> span.table-select-cell.org-members__action-column.org-members__action-column-body.table-select-cell-overflow > div > button')";
					WebElement element = cfunction.Webelement_JSPath(String.format(MEMEBR11_3_JSPATH, (i)));
					if (element.getText().contains("Test " + Testuser2)) {
						cfunction.ScrollToWebElement(String.format(VIEWDETISL_11_3_JSPATH, (i)));
						cfunction.clickusingActions(String.format(VIEWDETISL_11_3_JSPATH, (i)));
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		
		if (projectVersion.equals("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0"))
				|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
			cfunction.Webelement_JSClick(MANAGE_ADDON_LICENSE_12_0_JSPATH);
			cfunction.sync(2);
			cfunction.CommmonJS_Click(LICENSE_CANCEL12_0_XPATH);
			
			CommonFunction.logStatus("PASS", msg);
		} else {
			//cfunction.CommmonJS_Click(MANAGE_ADDON_LICENSE_XPATH);
			cfunction.Commmon_Click("xpath", MANAGE_ADDON_LICENSE_XPATH);
			cfunction.sync(2);
			if (cfunction.elementIsDisplayed(LICENSE_DESELECT_XPATH)) {
				cfunction.CommmonJS_Click(LICENSE_DESELECT_XPATH);
				cfunction.CommmonJS_Click(LICENSE_SAVE_XPATH);
				CommonFunction.logStatus("PASS", "License deselected for User");
			} else {
				cfunction.CommmonJS_Click(LICENSE_CANCEL_XPATH);
			}
			CommonFunction.logStatus("PASS", msg);
		}

		msg = "Click on action button to delete member 2";
		try {

			if (projectVersion.equalsIgnoreCase("11.1.0")||projectVersion.equalsIgnoreCase("11.5.0") || projectVersion.equalsIgnoreCase("11.3.0")
					|| projectVersion.equalsIgnoreCase("11.4.0")) {
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST11_3_XPATH));
				int size = itemListString.size();
				for (int i = size; i > 0; i--) {
					String MEMEBR11_5_XPATH ="(//a[@data-action='orgProfilePopup'])";
					String VIEWDETISL_11_5_XPATH = "(//button[@data-dojo-attach-point='_actionsBtn'])";
					String text=driver.findElement(By.xpath(MEMEBR11_5_XPATH + "[" + (i) + "]")).getText();
					if (text.contains("Test " + Testuser2)) {
						//cfunction.ScrollToElement(VIEWDETISL_11_5_XPATH);
						cfunction.clickxpathusingActions(VIEWDETISL_11_5_XPATH + "[" + (i) + "]");
						break;
					}
				}
			} else if (projectVersion.equalsIgnoreCase("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST12_0_XPATH));
				int size = itemListString.size();
				for (int i = size; i > 0; i--) {
					String MEMEBR12_0_JSPATH = "(document.querySelector('arcgis-member-browser > arcgis-member-browser-content > arcgis-member-browser-table > arcgis-member-browser-table-row:nth-child(%s)').shadowRoot.querySelector('arcgis-user-popup').shadowRoot.querySelector('button > slot > arcgis-user-avatar').shadowRoot.querySelector('span>span'))";
					String VIEWDETISL_12_0_JSPATH = "document.querySelector('arcgis-member-browser > arcgis-member-browser-content > arcgis-member-browser-table > arcgis-member-browser-table-row:nth-child(%s)').shadowRoot.querySelector('span > span > button')";

					WebElement element = cfunction.Webelement_JSPath(String.format(MEMEBR12_0_JSPATH, (i)));
					// WebElement element = cfunction.Webelement_JSPath(MEMEBR12_0_JSPATH + "[" +
					// (i) + "]");
					if (element.getText().contains("Test " + Testuser2)) {
						//cfunction.ScrollToWebElement(String.format(VIEWDETISL_12_0_JSPATH, (i)));
						cfunction.clickusingActions(String.format(VIEWDETISL_12_0_JSPATH, (i)));
						break;
					}
				}
			} else {
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST11_3_XPATH));
				int size = itemListString.size();
				for (int i = size; i > 0; i--) {
					String MEMEBR11_3_JSPATH = "document.querySelector('div.table-select-row-avatar:nth-child(%s)> span.table-select-cell.org-member-user-column > div > div.flex-1.org-member__name-col > div.org-member-full-name.avenir-demi > a')";
					String VIEWDETISL_11_3_JSPATH = "document.querySelector('div.table-select-row-avatar:nth-child(%s) > span.table-select-cell.org-members__action-column.org-members__action-column-body.table-select-cell-overflow > div > button')";
					WebElement element = cfunction.Webelement_JSPath(String.format(MEMEBR11_3_JSPATH, (i)));
					if (element.getText().contains("Test " + Testuser2)) {
						cfunction.ScrollToWebElement((String.format(VIEWDETISL_11_3_JSPATH, (i))));
						cfunction.clickusingActions(String.format(VIEWDETISL_11_3_JSPATH, (i)));
						// cfunction.clickusingActions(String.format(DELETE_USER1_ACTION11_3_XPATH,(i+1)));

						break;
					}
				}
			}
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			try {
				cfunction.CommmonJS_Click(DELETE_USER1_ACTION_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				e1.printStackTrace();
				flag1 = false;
				CommonFunction.logStatusWithException("FAIL", msg, e1);
			}

		}

		if (flag1) {
			msg = "Click on delete";
			try {
				if (projectVersion.equalsIgnoreCase("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
					cfunction.Webelement_JSClick(DELETE_ACTION_12_0_JSPATH);
					CommonFunction.logStatus("PASS", msg);
				} else {
					cfunction.CommmonJS_Click(DELETE_ACTION_XPATH);
					CommonFunction.logStatus("PASS", msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
				try {
					cfunction.CommmonJS_Click(DELETE_USER2_ACTION_XPATH);
					cfunction.CommmonJS_Click(DELETE_ACTION_XPATH);
				} catch (Exception e1) {
					e1.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e1);
				}
			}
			msg = "Click on delete confirmation dialog box";
			try {
				cfunction.sync(2);
				if (projectVersion.equalsIgnoreCase("11.1.0")) {
					cfunction.Commmon_Click("xpath", DELETE_OK_11_1_XPATH);
				} else if (projectVersion.equalsIgnoreCase("11.2.0") || projectVersion.equalsIgnoreCase("11.3.0")
						|| (projectVersion.equalsIgnoreCase("11.4.0"))) {
					cfunction.confirmdelete11_2_0();
				} else if ((projectVersion.equalsIgnoreCase("11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
						|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
					cfunction.confirmDialogdelete11_5_0();
				} else
					cfunction.Commmon_Click("xpath", DELETE_OK_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
		
		
		//delete rest of the automation testmembers
		try {
			cfunction.refreshpage();
			cfunction.sync(10);

			if (projectVersion.equalsIgnoreCase("11.1.0")||projectVersion.equalsIgnoreCase("11.5.0") || projectVersion.equalsIgnoreCase("11.3.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))) {
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST11_3_XPATH));
				int size = itemListString.size();
				for (int i = size; i > 0; i--) {
					String MEMEBR11_5_XPATH ="(//a[@data-action='orgProfilePopup'])";
					String CHECKOBX_XPATH = "(//input[contains(@class,'select-checkbox')])";
					String text=driver.findElement(By.xpath(MEMEBR11_5_XPATH + "[" + (i) + "]")).getText();
					if (text.contains("Test AutomationUser")) {
						cfunction.ScrollToElement(CHECKOBX_XPATH);
						cfunction.clickxpathusingActions(CHECKOBX_XPATH + "[" + (i) + "]");
					}}
				cfunction.Commmon_Click("xpath", MOREBUTTON_XPATH);
				cfunction.Commmon_Click("xpath", DELETEALLMEMBERS_XPATH);
				
				
					
			
          }else if (projectVersion.equalsIgnoreCase("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
        	  cfunction.switchtoqueryframe1();
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST12_0_XPATH));
				int size = itemListString.size();
				for (int i = size; i > 0; i--) {
					String MEMEBR12_0_JSPATH = "(document.querySelector('arcgis-member-browser > arcgis-member-browser-content > arcgis-member-browser-table > arcgis-member-browser-table-row:nth-child(%s)').shadowRoot.querySelector('arcgis-user-popup').shadowRoot.querySelector('button > slot > arcgis-user-avatar').shadowRoot.querySelector('span>span'))";
					String CHEKCBOX_11_3_JSPATH = "document.querySelector(\"arcgis-member-browser-table-row:nth-child(%s)\").shadowRoot.querySelector(\" calcite-checkbox\")";
					WebElement element = cfunction.Webelement_JSPath(String.format(MEMEBR12_0_JSPATH, (i)));
					// WebElement element = cfunction.Webelement_JSPath(MEMEBR12_0_JSPATH + "[" +
					// (i) + "]");
					if (element.getText().contains("Test AutomationUser")) {
						cfunction.ScrollToWebElement(String.format(CHEKCBOX_11_3_JSPATH, (i)));
						cfunction.clickusingActions(String.format(CHEKCBOX_11_3_JSPATH, (i)));
						
					}
				}
				
				String MOREBUTTON_JSPATH ="document.querySelector(\"arcgis-member-browser-actions\").shadowRoot.querySelector(\"#arcgis-member-browser-actions\").shadowRoot.querySelector(\"calcite-dropdown > calcite-button\")";
				cfunction.Webelement_JSClick(MOREBUTTON_JSPATH);
			    String DELETEALLMEMBERS_JSPATH="document.querySelector(\" arcgis-member-browser-actions\").shadowRoot.querySelector(\"#arcgis-member-browser-actions\").shadowRoot.querySelector(\"calcite-dropdown-item[icon-start='trash']\")";
			    cfunction.clickusingActions(DELETEALLMEMBERS_JSPATH);
			     
          } else {
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST11_3_XPATH));
				int size = itemListString.size();
				for (int i = size; i > 0; i--) {
					String MEMEBR11_3_JSPATH = "document.querySelector('div.table-select-row-avatar:nth-child(%s) > span.table-select-cell.org-member-user-column > div > div.flex-1.org-member__name-col > div.org-member-full-name.avenir-demi > a')";
					String CHEKCBOX_11_3_JSPATH = "document.querySelector(\"arcgis-member-browser-table-row:nth-child(%s)\").shadowRoot.querySelector(\" calcite-checkbox\")";
					WebElement element = cfunction.Webelement_JSPath(String.format(MEMEBR11_3_JSPATH, (i)));
					if (element.getText().contains("Test AutomationUser")) {
						cfunction.ScrollToWebElement((String.format(CHEKCBOX_11_3_JSPATH, (i))));
						cfunction.clickusingActions(String.format(CHEKCBOX_11_3_JSPATH, (i)));
						// cfunction.clickusingActions(String.format(DELETE_USER1_ACTION11_3_XPATH,(i+1)));

						break;
					}
				}
			}
			
			}catch(Exception e) {
			e.printStackTrace();
		}
		
		if ((projectVersion.equalsIgnoreCase("11.5.0"))
				|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
				|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
			cfunction.Commmon_Click("xpath", "(//calcite-dialog[@role='dialog']//calcite-button)[2]");
		}else if(projectVersion.equalsIgnoreCase("12.1.0") || (projectVersion.equalsIgnoreCase("12.0.0"))) {
			cfunction.Commmon_Click("xpath", "(//calcite-dialog[@class='dialog-bg-white']//calcite-button[@kind='danger'])");
		}
		
		cfunction.sync(5);//wait for 5 secs for process to complete
	}
	

	public void deletemember_10_6_1() throws Exception {
		// CommonFunction.waitforpagetoload();
		boolean flag1 = true;

		msg = "Click on action button to delete member 1";
		try {

			cfunction.sync(2);
			List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST_XPATH_10_6_1));
			int size = itemListString.size();
			for (int i = 0; i <= size; i++) {
				if (cfunction.elementexist(DELETE_USER1_ACTION_XPATH_10_6_1,10)) {
					break;
				} else
					cfunction.JSclickOnListOfElementBasedOnIndex(PAGE_X_OF_MEMBERSLIST_XPATH_10_6_1, i + 1);
			}
			cfunction.CommmonJS_Click(DELETE_USER1_ACTION_XPATH_10_6_1);
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			try {

				// cfunction.CommmonJS_Click(NEXT_PAGE);
				cfunction.waitforpagetoload();
				cfunction.elementexist(DELETE_USER1_ACTION_XPATH_10_6_1,5);
				cfunction.CommmonJS_Click(DELETE_USER1_ACTION_XPATH_10_6_1);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				e1.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e1);
				flag1 = false;
			}

		}
		if (flag1) {
			msg = "Click on delete";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(DELETE_ACTION_XPATH_10_6_1);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					cfunction.CommmonJS_Click(DELETE_ACTION_XPATH_10_6_1);
				} catch (Exception e1) {
					e1.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);
				}
			}

			msg = "Click on delete confirmation dialog box";
			try {
				cfunction.Commmon_Click("xpath", DELETE_OK_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			try {
				cfunction.waitforinvisibilityofelement(DELETE_OK_XPATH, 20);
				// CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				// CommonFunction.logStatus("FAIL", msg+e);

			}
			msg = "Verify user1 deleted";
			try {
				cfunction.waitforpagetoload();
				cfunction.sync(2);
				cfunction.waitforinvisibilityofelement(USER1_XPATH_10_6_1, 15);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					driver.navigate().refresh();
					cfunction.waitforpagetoload();
					cfunction.sync(2);
					cfunction.waitforinvisibilityofelement(USER1_XPATH_10_6_1, 15);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e1) {
					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e1);

				}

			}
		}
		flag1 = true;

		msg = "Click on action button for member 2 to delete";
		try {
			cfunction.sync(5);

			String count = cfunction.getElementText(TOTAL_PAGES_10_6_1);
			int size = Integer.parseInt(count);
			for (int i = 0; i <= size; i++) {
				if (cfunction.elementexist(DELETE_USER2_ACTION_XPATH_10_6_1,10)) {
					break;
				} else
					cfunction.Commmon_Click("xpath", NEXT_PAGE_XPATH_10_6_1);
			}
			cfunction.CommmonJS_Click(DELETE_USER2_ACTION_XPATH_10_6_1);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			try {
				// cfunction.CommmonJS_Click(NEXT_PAGE);
				cfunction.waitforpagetoload();
				cfunction.elementexist(DELETE_USER2_ACTION_XPATH_10_6_1,10);
				cfunction.CommmonJS_Click(DELETE_USER2_ACTION_XPATH_10_6_1);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e2) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
				flag1 = false;
			}
		}

		if (flag1) {
			msg = "Click on delete";
			try {
				cfunction.CommmonJS_Click(DELETE_ACTION_XPATH_10_6_1);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					cfunction.CommmonJS_Click(DELETE_USER2_ACTION_XPATH_10_6_1);
					cfunction.CommmonJS_Click(DELETE_ACTION_XPATH_10_6_1);
				} catch (Exception e1) {
					e1.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e1);
				}
			}
			msg = "Click on delete confirmation dialog box";
			try {
				cfunction.Commmon_Click("xpath", DELETE_OK_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			try {
				cfunction.waitforinvisibilityofelement(DELETE_OK_XPATH, 30);
				// CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				// CommonFunction.logStatus("FAIL", msg+e);

			}
			msg = "Verify user2 deleted";
			try {

				cfunction.waitforpagetoload();
				cfunction.waitforinvisibilityofelement(USER2_XPATH_10_6_1, 15);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					driver.navigate().refresh();
					cfunction.waitforpagetoload();
					cfunction.sync(2);
					cfunction.waitforinvisibilityofelement(USER2_XPATH_10_6_1, 15);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e1) {
					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e1);

				}
			}
		}

	}

	public void createmember1() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Click on member tab";
		try {

			cfunction.CommmonJS_Click(MEMBERS_TAB_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Wait for members add button";
		try {

			cfunction.waitforelement(ADD_MEMBERS_XPATH, 20);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on member tab";
		try {

			cfunction.CommmonJS_Click(ADD_MEMBERS_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on Next";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(NEXT_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on New member";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(NEW_MEMBER_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Wait for element to enter first name";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(FIRST_NAME_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
			member1created = false;
		}
		if (member1created) {
			msg = "Enter first name";
			try {
				cfunction.sync(2);
				cfunction.CommonTextBox_Input(FIRST_NAME_XPATH, "Test");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter last name";
			try {
				cfunction.sync(2);
				cfunction.CommonTextBox_Input(LAST_NAME_XPATH, Testuser1);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter email";
			try {
				cfunction.sync(2);
				cfunction.CommonTextBox_Input(EMAIL_XPATH, "Testuser1@esri.com");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter username";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(USERNAME_XPATH);
				// cfunction.CommonTextBox_Input(EMAIL_XPATH,"Testuser1");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter password";
			try {
				cfunction.sync(2);
				cfunction.CommonTextBox_Input(PASSWORD_XPATH, "Testuser123");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on Next";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(NEXT_MEMBER_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on second member";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(SECOND_MEMBER_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
	}

	public void createmember1_Version12() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Click on members";
		try {
			cfunction.sync(2);
			cfunction.hoverByAction(MEMBER_12_0_0_XPATH);
			cfunction.clickusingActions(MEMBER_12_0_0_JSPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Wait for members add button";
		try {

			cfunction.waitforelement(Add_MEMBERS_12_0_XPATh, 20);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on member tab";
		try {

			cfunction.CommmonJS_Click(Add_MEMBERS_12_0_XPATh);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(2);

		msg = "Click on Next";
		try {
			cfunction.CommmonJS_Click(NEXT_12_0_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on New member";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(NEW_MEMBER_12_0_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Wait for element to enter first name";
		try {
			cfunction.sync(2);
			cfunction.waitforwebelement(FIRST_NAME_12_0_JSPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
			member1created = false;
		}
		if (member1created) {
			msg = "Enter first name";
			try {
				cfunction.sync(2);
				cfunction.Webelement_JSInput(FIRST_NAME_12_0_JSPATH, "Test");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter last name";
			try {
				cfunction.sync(2);
				cfunction.Webelement_JSInput(LAST_NAME_12_0_JSPATH, Testuser1);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter email";
			try {
				cfunction.sync(2);
				cfunction.Webelement_JSInput(EMAIL_12_0_JSPATH, "Testuser1@esri.com");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter username";
			try {
				cfunction.sync(2);
				cfunction.Webelement_JSClick(USERNAME_12_0_JSPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}

			msg = "Enter password";
			try {
				cfunction.sync(2);
				cfunction.Webelement_JSInput(PASSWROD_12_0_JSPATH, "Testuser123");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on Next";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(NEXT_MEMBER_12_0_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on second member";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(SECOND_MEMBER_12_0_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
	}

	public void createmember2_Version12_0() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Wait for element to enter first name";
		try {
			cfunction.sync(2);
			cfunction.waitforwebelement(FIRST_NAME_12_0_JSPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
			member2created = false;
		}

		if (member2created) {
			msg = "Enter first name";
			try {
				cfunction.sync(2);
				cfunction.Webelement_JSInput(FIRST_NAME_12_0_JSPATH, "Test");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter last name";
			try {
				cfunction.sync(2);
				cfunction.Webelement_JSInput(LAST_NAME_12_0_JSPATH, Testuser2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter email";
			try {
				cfunction.sync(2);
				cfunction.Webelement_JSInput(EMAIL_12_0_JSPATH, "Testuser2@esri.com");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}

			msg = "Enter username";
			try {
				cfunction.sync(2);
				cfunction.Webelement_JSClick(USERNAME_12_0_JSPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}

			msg = "Enter password";
			try {
				cfunction.sync(2);
				cfunction.Webelement_JSInput(PASSWROD_12_0_JSPATH, "Testuser123");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on Next";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(NEXT_MEMBER_12_0_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}

			msg = "Click on Next";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(NEXT_TO_SAVE_MEMEBR_12_0_JSPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on Next";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(NEXT_TO_SAVE_MEMEBR_12_0_JSPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}

			msg = "Click on Add Member";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(FOOTER_ADD_MEMBERS_12_0_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
	}

	public void createmember1_10_6_1() throws Exception {
		// CommonFunction.waitforpagetoload();

		msg = "Wait for members add button";
		try {

			cfunction.waitforelement(ADD_MEMBERS_XPATH_10_6_1, 20);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on member tab";
		try {

			cfunction.CommmonJS_Click(ADD_MEMBERS_XPATH_10_6_1);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on Next";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(NEXT_XPATH_10_6_1);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Enter first name";
		try {
			cfunction.sync(2);
			cfunction.CommonTextBox_Input(FIRST_NAME_XPATH_10_6_1, "Test");
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
			member1created = false;
		}
		if (member1created) {
			msg = "Enter last name";
			try {
				cfunction.sync(2);
				cfunction.CommonTextBox_Input(LAST_NAME_XPATH_10_6_1, Testuser1);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter email";
			try {
				cfunction.sync(2);
				cfunction.CommonTextBox_Input(EMAIL_XPATH_10_6_1, "Testuser1@esri.com");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter username";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(USERNAME_XPATH_10_6_1);

				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter password";
			try {
				cfunction.sync(2);
				cfunction.CommonTextBox_Input(PASSWORD_XPATH_10_6_1, "Testuser123");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on Next";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(NEXT_MEMBER_XPATH_10_6_1);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on second member";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(SECOND_MEMBER_XPATH_10_6_1);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);// div[@data-dojo-attach-point='_completed']//span[contains(@class,'dijitButtonText'
				// )and contains(text(),'member')]

			}
			msg = "Click on Invite member";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(NEW_MEMBER_XPATH_10_6_1);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
	}

	public void createmember2_10_6_1() throws Exception {
		msg = "Click on Next";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(NEXT_XPATH_10_6_1);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		// CommonFunction.waitforpagetoload();

		msg = "Enter first name";
		try {
			cfunction.sync(2);
			cfunction.CommonTextBox_Input(FIRST_NAME_XPATH_10_6_1, "Test");
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Enter last name";
		try {
			cfunction.sync(2);
			cfunction.CommonTextBox_Input(LAST_NAME_XPATH_10_6_1, Testuser2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Enter email";
		try {
			cfunction.sync(2);
			cfunction.CommonTextBox_Input(EMAIL_XPATH_10_6_1, "Testuser2@esri.com");
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Enter username";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(USERNAME_XPATH_10_6_1);
			// cfunction.CommonTextBox_Input(USERNAME_XPATH_10_6_1,"Test"+Testuser2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Enter password";
		try {
			cfunction.sync(2);
			cfunction.CommonTextBox_Input(PASSWORD_XPATH_10_6_1, "Testuser123");
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Select Level";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(LEVEL_MEMBER_XPATH_10_6_1);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on Next";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(NEXT_MEMBER_XPATH_10_6_1);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on second member";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(SECOND_MEMBER_XPATH_10_6_1);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);// div[@data-dojo-attach-point='_completed']//span[contains(@class,'dijitButtonText'
			// )and contains(text(),'member')]
			// input[@id='createSingleLevel1Radio']
		}

		msg = "Click on Return Organization";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(RETURN_ORGANIZATION_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void createmember2() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Wait for element to enter first name";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(FIRST_NAME_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			member2created = false;
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		if (member2created) {
			msg = "Enter first name";
			try {
				cfunction.sync(2);
				cfunction.CommonTextBox_Input(FIRST_NAME_XPATH, "Test");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter last name";
			try {
				cfunction.sync(2);
				cfunction.CommonTextBox_Input(LAST_NAME_XPATH, Testuser2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter email";
			try {
				cfunction.sync(2);
				cfunction.CommonTextBox_Input(EMAIL_XPATH, "Testuser2@esri.com");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter username";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(USERNAME_XPATH);
				// cfunction.CommonTextBox_Input(USERNAME_XPATH,"Testuser2");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Enter password";
			try {
				cfunction.sync(2);
				cfunction.CommonTextBox_Input(PASSWORD_XPATH, "Testuser123");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on Next";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(NEXT_MEMBER_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on Next";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(NEXT_LICENSE_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on Next";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(NEXT_LICENSE_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on Add Member";
			try {
				cfunction.sync(2);
				cfunction.CommmonJS_Click(NEXT_LICENSE_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
	}

	public void verifymembers() throws Exception {
		boolean isFound = false;
		msg = "Verify user1 created";
		try {
			cfunction.sync(2);
			// driver.navigate().refresh();
			try {
				if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
						|| (projectVersion.equalsIgnoreCase("11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))) {

					while (true) {

					    List<WebElement> itemListString = driver
					            .findElements(By.xpath("//div[@class='flex-1 org-member__name-col']"));
					    int size = itemListString.size();
					    // Traverse from last element
					    for (int i = size - 1; i >= 0; i--) {
					        WebElement element = itemListString.get(i);
					        cfunction.ScrollToWebElement(element);
					        if (cfunction.elementexist(USER1_XPATH)) {
								CommonFunction.logStatus("PASS", msg);
								isFound = true;
								break;
							}
					    }
					    if (isFound) {
					        break;
					    }
					    // Go to next page
					    if (cfunction.elementexist(PAGE_X_OF_MEMBERSLIST_XPATH)) {
					        cfunction.JSclickOnListOfElementBasedOnIndex(PAGE_X_OF_MEMBERSLIST_XPATH, 1);
					    } else {
					        break; // No more pages
					    }
					}	}
			} catch (Exception e) {
				e.printStackTrace();
				driver.navigate().refresh();
				cfunction.waitforpagetoload();
				cfunction.waitforelement(USER1_XPATH, 20);
			}
		}catch(Exception e)
	   { 		e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

	msg="Verify user2 created";
	try{
		cfunction.sync(2);
		cfunction.ScrollToElement(USER2_XPATH);
		cfunction.waitforelement(USER2_XPATH, 20);
		CommonFunction.logStatus("PASS", msg);
	}catch(
	Exception e)
	{	e.printStackTrace();
		CommonFunction.logStatusWithException("FAIL", msg, e);
	}}

	public void verifCreatedymembers12_0() throws Exception {
		cfunction.sync(5);
		msg = "Verify user1 created";

		try {
			cfunction.clickusingActions(MEMBER_12_0_0_JSPATH);
		}catch(Exception e) {
			e.printStackTrace();
	}
		try {
			if (projectVersion.equalsIgnoreCase("12.0.0")||(projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				cfunction.waitforpagetoload();
				List<WebElement> itemListString = driver
						.findElements(By.xpath("//arcgis-member-browser-content//arcgis-member-browser-table-row"));
				int size = itemListString.size();
				for (int i = size ; i > 0; i--) {
					cfunction.ScrollToWebElement(String.format(AllCREATEDUSERS_JSPATH, (i)));
					
					String UserName = cfunction.Webelement_JSPath(String.format(AllCREATEDUSERS_JSPATH,(i))).getText();
					if (UserName.equalsIgnoreCase("Test "+Testuser1)) {
						CommonFunction.logStatus("PASS", msg);
						break;
				}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		msg = "Verify user2 created";
		try {
			if (projectVersion.equalsIgnoreCase("12.0.0")||(projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				cfunction.waitforpagetoload();
				List<WebElement> itemListString = driver
						.findElements(By.xpath("//arcgis-member-browser-content//arcgis-member-browser-table-row"));
				int size = itemListString.size();
				for (int i = size ; i > 0; i--) {
					cfunction.ScrollToWebElement(String.format(AllCREATEDUSERS_JSPATH, (i)));
					String UserName = cfunction.Webelement_JSPath(String.format(AllCREATEDUSERS_JSPATH,(i))).getText();
					if (UserName.equalsIgnoreCase("Test "+Testuser2)) {
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void verifymembers_10_6_1() throws Exception {

		msg = "Verify user1 created";
		try {
			cfunction.sync(2);
			// driver.navigate().refresh();
			try {

				cfunction.waitforpagetoload();
				List<WebElement> itemListString = driver.findElements(By.xpath(PAGE_X_OF_MEMBERSLIST_XPATH_10_6_1));
				int size = itemListString.size();
				for (int i = 0; i <= size; i++) {
					if (cfunction.elementexist(USER1_XPATH_10_6_1)) {
						CommonFunction.logStatus("PASS", msg);
						break;
					} else
						cfunction.JSclickOnListOfElementBasedOnIndex(PAGE_X_OF_MEMBERSLIST_XPATH_10_6_1, i + 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
				driver.navigate().refresh();
				cfunction.waitforpagetoload();
				cfunction.waitforelement(USER1_XPATH_10_6_1, 20);

			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}
		msg = "Verify user2 created";
		try {

			if (cfunction.elementexist(USER2_XPATH_10_6_1)) {
				CommonFunction.logStatus("PASS", msg);
			} else {
				// cfunction.CommmonJS_Click(NEXT_PAGE);
				cfunction.waitforpagetoload();
				cfunction.elementexist(USER2_XPATH_10_6_1);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

	}

	public void verifystatus() throws Exception {

		msg = "Click on status";
		try {
			if (projectVersion.equalsIgnoreCase("12.0.0")||(projectVersion.equalsIgnoreCase("12.1.0"))|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				cfunction.hoverByAction(STATUS_12_0_0_XPATH);
				cfunction.clickusingActions(STATUS_12_0_0_JSPATH);
				CommonFunction.logStatus("PASS", msg);
			} else {
				cfunction.sync(5);
				cfunction.CommmonJS_Click(STATUS_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Switch to Iframe";
		try {
			cfunction.sync(5);
			driver.switchTo().frame("statusIframe");
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Click on Status-> content";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(STATUS_CONTENT_TAB_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify content reports available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(ITEM_REPORTS_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify content count available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(ITEM_COUNT_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify content charts available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(ITEM_CHART_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on Status-> members";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(STATUS_MEMBERS_TAB_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify user profile available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(USER_PROFILE_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Verify user detail available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(USER_DETAIL_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify active member details available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(ACTIVE_MEMBER_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify accounts type detail available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(ACTIVE_MEMBER_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on Status-> groups";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(STATUS_GROUPS_TAB_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Verify groups count available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(GROUPS_COUNT_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify group owners details available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(GROUPS_OWNERS_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify group reports available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(GROUPS_REPORT_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify featured details available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(FEATURED_GROUP_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void verifystatus_12_0_0() throws Exception {

		msg = "Click on status";
		try {
			cfunction.sync(5);
			cfunction.CommmonJS_Click(STATUS_12_0_0_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void verifymembertab() throws Exception {

		msg = "Click on members";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(MEMBER_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Verify members rows avaliable";
		try {
			cfunction.sync(2);
			List<WebElement> list1 = driver.findElements(
					By.xpath("//div[contains(@class,'table-select-rows')]//div[contains(@class,'table-select-row')]"));
			int cnt1 = list1.size();
			if (cnt1 > 0) {
				CommonFunction.logStatus("PASS", msg);
			} else {
				CommonFunction.logStatus("FAIL", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Verify last login info available";
		try {
			cfunction.sync(2);
			List<WebElement> list1 = driver.findElements(By.xpath(
					"//div[contains(@class,'select-row-avatar ')]//span[contains(@class,'members__lastlogin-column') ]/span[string-length(text()) > 0]"));
			int cnt1 = list1.size();
			if (cnt1 > 0) {
				CommonFunction.logStatus("PASS", msg);
			} else {
				CommonFunction.logStatus("FAIL", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void verifymembertab_10_6_1() throws Exception {

		/*
		 * msg = "Click on members"; try { cfunction.sync(2);
		 * cfunction.CommmonJS_Click(MEMBER_XPATH); CommonFunction.logStatus("PASS",
		 * msg); } catch (Exception e) { e.printStackTrace();
		 * CommonFunction.logStatus("FAIL", msg+e);
		 * 
		 * }
		 */

		msg = "Verify members rows avaliable";
		try {
			cfunction.sync(2);
			List<WebElement> list1 = driver.findElements(By.xpath("//div[contains(@class,'dojoxGridRow')]"));
			int cnt1 = list1.size();
			if (cnt1 > 0) {
				CommonFunction.logStatus("PASS", msg);
			} else {
				CommonFunction.logStatus("FAIL", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Verify last login info available";
		try {
			cfunction.sync(2);
			List<WebElement> list1 = driver.findElements(By.xpath(
					"//div[contains(@class,'dojoxGridRow')]//table[contains(@class,'dojoxGridRowTable') ]//td[3]/span[string-length(text()) > 0]"));
			int cnt1 = list1.size();
			if (cnt1 > 0) {
				CommonFunction.logStatus("PASS", msg);
			} else {
				CommonFunction.logStatus("FAIL", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void verifystatus_10_6_1() throws Exception {

		msg = "Click on status";
		try {
			cfunction.refreshpage();
			cfunction.waitforelement(STATUS_XPATH_10_6_1, 100);
			cfunction.CommmonJS_Click(STATUS_XPATH_10_6_1);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Click on Status-> content";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(STATUS_CONTENT_TAB_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify content reports available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(ITEM_REPORTS_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify content count available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(ITEM_COUNT_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify content charts available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(ITEM_CHART_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on Status-> members";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(STATUS_MEMBERS_TAB_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify user profile available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(USER_PROFILE_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Verify user detail available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(USER_DETAIL_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify active member details available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(ACTIVE_MEMBER_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify accounts type detail available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(ACTIVE_MEMBER_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on Status-> groups";
		try {
			cfunction.sync(2);
			cfunction.CommmonJS_Click(STATUS_GROUPS_TAB_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Verify groups count available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(GROUPS_COUNT_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify group owners details available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(GROUPS_OWNERS_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify group reports available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(GROUPS_REPORT_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify featured details available";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(FEATURED_GROUP_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void verifymembertab_12_0_0() throws Exception {

		msg = "Click on members";
		try {

			cfunction.sync(2);
			cfunction.hoverByAction(MEMBER_12_0_0_XPATH);
			cfunction.clickusingActions(MEMBER_12_0_0_JSPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Verify members rows avaliable";
		try {
			cfunction.sync(2);
			List<WebElement> list1 = driver
					.findElements(By.xpath("//arcgis-member-browser-content//arcgis-member-browser-table-row"));
			int cnt1 = list1.size();
			if (cnt1 > 0) {
				CommonFunction.logStatus("PASS", msg);
			} else {
				CommonFunction.logStatus("FAIL", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

}

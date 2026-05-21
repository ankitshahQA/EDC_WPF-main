package com.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;
import com.automation.testcases.Content_Functionality_TestScenario;

public class GroupsPage extends TestBase {
	final String CHECKTHEDILOAGBOX11_3_JSPATH = "document.querySelector(\" div > arcgis-tunnel > arcgis-item-share\").shadowRoot.querySelector(\"arcgis-dependency-check\").shadowRoot.querySelector(\"arcgis-item-browser > arcgis-item-browser-content > arcgis-item-card:nth-child(2)\").shadowRoot.querySelector(\"section > div.item-browser-card__upper\")";
	final String DONTCLICKUPDATE_11_3_JSPATH = "document.querySelector(\"div > arcgis-tunnel > arcgis-item-share\").shadowRoot.querySelector(\"arcgis-dependency-check\").shadowRoot.querySelector(\"calcite-modal > calcite-button:nth-child(3)\").shadowRoot.querySelector(\"div > button\")";
	final String DONTCLICKUPDATE_11_5_JSPATH = "document.querySelector('arcgis-tunnel > arcgis-item-share').shadowRoot.querySelector('arcgis-dependency-check').shadowRoot.querySelector('calcite-dialog:nth-child(2)').shadowRoot.querySelector('calcite-panel').shadowRoot.querySelector('#close')";
	final String DONTCLICKCANCLEUPDATE_11_5_JSPATH="document.querySelector(\"arcgis-tunnel > arcgis-item-share\").shadowRoot.querySelector(\"arcgis-dependency-check\").shadowRoot.querySelector(\"calcite-dialog.rls-dialog\").shadowRoot.querySelector(\"calcite-panel\").shadowRoot.querySelector(\"#close\")";
	final String DILAOGBOX11_3_JSPATH = "document.querySelector('div > arcgis-tunnel > arcgis-item-share').shadowRoot.querySelector('arcgis-dependency-check').shadowRoot.querySelector('div')";
	final String SHAREBUTTONONMAP_11_3_JSPATH = "document.querySelector(\"arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row:nth-child(1)\").shadowRoot.querySelector(\"arcgis-item-share-summary-editable\").shadowRoot.querySelector(\"button\")";
	final String EDITSHARINGBUTTON_11_3_JSPATH = "document.querySelector(' arcgis-tunnel > arcgis-item-share').shadowRoot.querySelector('div> arcgis-item-share-group').shadowRoot.querySelector('div > calcite-button').shadowRoot.querySelector('div > button')";
	final String GROUPSHARINGNAME11_3_JSPATH = "document.querySelector('arcgis-tunnel > arcgis-item-share').shadowRoot.querySelector(' arcgis-group-browser > arcgis-group-browser-content > arcgis-item-share-group-card:nth-child(%s)').shadowRoot.querySelector('arcgis-group-card').shadowRoot.querySelector('section div.group-card-flex-1 > h3 > span')";
	final String GROUPSHARINGLIST11_3_JSPATH = "document.querySelector('div > arcgis-tunnel > arcgis-item-share').shadowRoot.querySelectorAll('arcgis-group-browser > arcgis-group-browser-content > arcgis-item-share-group-card')";
// XPath code
	final String APPLYBUTTON_11_5_JSPATH = "document.querySelector(\"arcgis-item-share\").shadowRoot.querySelector(\"calcite-dialog > calcite-button:nth-child(3)\").shadowRoot.querySelector(\"div > button\")";
	final String APPLYBUTTON11_3_JSPATH = "document.querySelector('body > div > arcgis-tunnel > arcgis-item-share').shadowRoot.querySelector('calcite-modal > calcite-button:nth-child(4)').shadowRoot.querySelector('div > button')";
	final String GROUP_SAVE_XPATH_VS_10_9_1 = "//button[contains(@id,'create-group-save-btn')]";
	final String GROUP_TAGS_XPATH_VS_10_9_1 = "//div[contains(@id,'widget_filter-')]//input";
	final String GROUPS_TAGS_JSPATH_11_2_0 = "document.querySelector(\"section:nth-child(1) > div > div.basis-full > calcite-label:nth-child(3) > arcgis-tags-picker\").shadowRoot.querySelector(\"calcite-label > calcite-combobox\").shadowRoot.querySelector(\"input\")";
	final String GROUP_SUMMARY_XPATH_VS_10_9_1 = "//textarea[@id='create-group-summary-input']";
	final String GROUP_NAME_XPATH_VS_10_9_1 = "(//input[@class='js-text-input'])[1]";
	final String GROUP_NAME_XPATH_VS_11_2_0 = "document.querySelector(\"section > div > div.basis-full > calcite-label:nth-child(1) > calcite-input-text\").shadowRoot.querySelector(\"input[type=text]\")";
	final String MAP_XPATH = "//a[contains(@class,'top-nav-link') and contains(@href,'webmap')]";
	final String CONTENT_XPATH = "//a[contains(@class,'top-nav-link') and contains(@href,'content')]";

	final String CONTENT_MENU_ITEM = "//div[@id='esri-header-menus-desktop']//a[contains(@class,'esri-header-menus-link') and contains(@href,'content')]";
	String msg = "";
	final String GROUPS_MENU_ITEM = "//div[@id='esri-header-menus-desktop']//a[contains(@class,'esri-header-menus-link') and contains(@href,'groups')]";
	final String GROUPS_TITLE_XPATH = "//h1[contains(@id,'title')and text()='Groups']";
	final String GROUPS_TITLE_11_2_XPATH = "//div[ text()='Groups']";
	final String GROUPS_TITLE_11_3_XPATH = "//h1[ text()='Groups']";
	final String GROUPS_CONTENT_XPATH = "(//div[contains(@class,'card-content-auto-top')])[1]";
	final String GROUPS_CREATED_TITLE_XPATH = "//span[@id='pagetitle'and text()='" + GroupName + "']";
	final String GROUPS_CREATED_TITLE_XPATH_10_6_1 = "//h1[contains(@id,'title')]//span[text()='" + GroupName + "']";
	final String GROUPS_CREATED_LINK_XPATH = "//a[@title='" + GroupName + "']";
	final String DELETE_CREATED_GROUP_XPATH = "//a[contains(@title,'" + GroupName
			+ "')]/../../button[contains(@class,'remove')]";
	final String DELETE_OK_XPATH = "//span[@id='button_delete-warning-submit_label']";
	final String DELETE_OK_11_1_XPATH = "//calcite-button[@data-test='ok']";
	final String CREATE_GROUP_XPATH = "//a[@href='./group.html']";
	final String CREATE_GROUP11_2_XPATH = "//div[contains(@class,'group-browser')]//calcite-button";
	final String GROUP_NAME_XPATH = "//input[@class='js-group-title']";
	final String GROUP_SUMMARY_XPATH = "//input[@name='snippet']";
	final String GROUP_SUMMARY_11_2_0_JSPATH = "document.querySelector(\"section> div > div.basis-full > calcite-label:nth-child(2) > calcite-input\").shadowRoot.querySelector(\"textarea\")";
	final String GROUP_TAGS_XPATH = "//div[@id='createGroupTags']//input[contains(@class,'dijitInputInner')]";
	final String GROUP_TAGS_XPATH_10_6_1 = "//div[@id='createGroupTags']//input[contains(@class,'dijitInputInner')]";
	final String GROUPS_CREATE_XPATH = "//button[contains(@data-dojo-attach-point,'createBtn')]";
	final String GROUPS_CREATE_XPATH_11_2_0 = "//*[text()='Save']";
	// final String
	// GROUPS_CREATE_XPATH="//button[@data-dojo-attach-point='_createBtn']";
	final String INVITE_UERS_11_4_XPATH = "//calcite-button[text()='Invite members']";
	final String INVITE_UERS_XPATH = "//button[@data-action='inviteUsers']";
	final String USER1_XPATH = "//div[@data-dojo-attach-point='_groupsPane']//a[@data-title='Test " + Testuser1 + "']";
	final String USER2_XPATH = "//div[@data-dojo-attach-point='_groupsPane']//a[@data-title='Test " + Testuser2 + "']";
	final String USER1_10_8_0_XPATH = "//h4[text()='Test " + Testuser1 + "']/../..";
	/// following-sibling::label/input
	final String USER2_10_8_0_XPATH = "//h4[text()='Test " + Testuser2 + "']/../..";
	final String USER1_11_2_0_JSPATH = "document.querySelector(\"[class*='members-list'] > [class*='flex'] >div> div>div>arcgis-user-avatar\").shadowRoot.querySelector(\"calcite-avatar[full-name='Test "
			+ Testuser1 + "']~span\")";
	final String USER2_11_2_0_JSPATH = "document.querySelector(\"[class*='members-list'] > [class*='flex'] >div> div>div>arcgis-user-avatar\").shadowRoot.querySelector(\"calcite-avatar[full-name='Test "
			+ Testuser2 + "']\")";

	final String SELECTED_USER1_XPATH = "//div[@data-dojo-attach-point='_selectedGroupsPane']//a[@data-title='Test "
			+ Testuser1 + "']";
	final String SELECTED_USER2_XPATH = "//div[@data-dojo-attach-point='_selectedGroupsPane']//a[@data-title='Test "
			+ Testuser2 + "']";
	final String SEND_INVITE_XPATH = "//span[@id='button_send-invite_label']";
	final String ADD_TO_GROUP_XPATH = "//button[text()='Add to group']";
	final String ADD_TO_GROUP_XPATH_VS_10_9_1 = "//button[text()='Add members to group']";
	final String ADD_TO_GROUP_XPATH_VS_11_4_0 = "//calcite-button[text()='Add members to group']";
	final String MEMBERS_LIST11_2_0_JSPATH = "document.querySelectorAll(\"[class*='members-list'] > [class*='flex'] >div> div>div>arcgis-user-avatar\")";
	final String MEMBERS_LIST11_5_0_JSPATH = "document.querySelectorAll(\"arcgis-member-browser > arcgis-member-browser-content > arcgis-member-card\")";
	final String MEMBERS_NAME_11_5_0_JSPATH = "document.querySelector('arcgis-member-browser > arcgis-member-browser-content > arcgis-member-card:nth-child(%s)').shadowRoot.querySelector('section > div.compact__name-section > div.compact__fullname')";

	CommonFunction cfunction = new CommonFunction();
	ContentPage cp = new ContentPage();

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
	}

	public void verifygrouppage() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Verify group page";
		try {
			cfunction.sync(5);
			if (projectVersion.equalsIgnoreCase("11.2.0")) {
				cfunction.waitforelement(GROUPS_TITLE_11_2_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} else if (projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					) {
				// cfunction.ScrollToWebElement(GROUPS_TITLE_11_3_XPATH);
				cfunction.waitforelement(GROUPS_TITLE_11_3_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}else if((projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))
				 ||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				driver.switchTo().defaultContent();
				//cfunction.switchToWidgetTemplateFrame();
				//driver.switchTo().frame("statusIframe");
				cfunction.waitforelement(GROUPS_TITLE_11_3_XPATH);
				CommonFunction.logStatus("PASS", msg);

			} else {
				cfunction.waitforelement(GROUPS_TITLE_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void verifygroupcreated() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Verify group created";
		try {
			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))) {
				String GROUPHEADER11_3_jspath = "document.querySelector(\" arcgis-group-browser\").shadowRoot.querySelector(\"arcgis-browser > arcgis-group-browser-preview\").shadowRoot.querySelector(\"calcite-flow > calcite-flow-item\").shadowRoot.querySelector(\"div > calcite-panel\").shadowRoot.querySelector(\"div > article > header > div.header-container > div.header-content\")";
				WebElement element = cfunction.Webelement_JSPath(GROUPHEADER11_3_jspath);
				cfunction.ScrollToElement(GROUPHEADER11_3_jspath);
				if (element.getText().contains(GroupName)) {
					CommonFunction.logStatus("PASS", msg);
					groupcreated = true;
				}
			} else if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))
					||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				String GROUPHEADER11_5_Xpath = "//h1[@data-harness='title']";
				String element = cfunction.getElementText(GROUPHEADER11_5_Xpath);
				cfunction.ScrollToElement(GROUPHEADER11_5_Xpath);
				if (element.contains(GroupName)) {
					CommonFunction.logStatus("PASS", msg);
					groupcreated = true;
				}
			} else {
				cfunction.waitforelement(GROUPS_CREATED_TITLE_XPATH);
				CommonFunction.logStatus("PASS", msg);
				groupcreated = true;
			}
		} catch (Exception e) {
			driver.navigate().refresh();
			cfunction.sync(3);
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
			groupcreated = false;
		}

	}

	public void verifygroupcreated_10_6_1() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Verify group created";
		try {
			cfunction.waitforelement(GROUPS_CREATED_TITLE_XPATH_10_6_1);
			CommonFunction.logStatus("PASS", msg);
			groupcreated = true;
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
			groupcreated = false;

		}

	}

	public void verifygroupdeleted() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Verify group deleted";
		try {
			cfunction.waitforinvisibilityofelement(GROUPS_CREATED_TITLE_XPATH, 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void verifygroupdeleted11_2_0() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Verify group deleted";
		try {
			if (projectVersion.equals("11.2.0")) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				List<WebElement> ele = (List<WebElement>) js
						.executeScript("return " + "document.querySelectorAll(\"arcgis-group-card\")");
				for (int i = ele.size() ; i >0; i--) {
					String jspath = "document.querySelector(\"arcgis-group-card:nth-child(" + (i )
							+ ")\").shadowRoot.querySelector(\"section > a h3\")";
					WebElement element = (WebElement) js.executeScript("return " + jspath);

					if (element.getText().contains(GroupName)) {
						cfunction.waitforinvisibilityofelement(element, 10);
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			} else if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				List<WebElement> ele = (List<WebElement>) js
						.executeScript("return " + "document.querySelectorAll(\"arcgis-group-card\")");
				for(int i = ele.size() ; i >0; i--) {
					String GROUP11_3_jspath = "document.querySelector(\"div > arcgis-group-browser > arcgis-group-browser-content > arcgis-group-card:nth-child(%s)\").shadowRoot.querySelector(\"section > div.group-card__upper >div div.group-card-flex-1 >div>div > h3 > span\")";
					cfunction.ScrollToWebElement(String.format(GROUP11_3_jspath, (i)));
					WebElement element = cfunction.Webelement_JSPath(String.format(GROUP11_3_jspath, (i)));
					// WebElement element = (WebElement) js.executeScript("return " +
					// GROUP11_3_jspath);

					if (element.getText().contains(GroupName)) {
						// cfunction.waitforinvisibilityofelement(element, 10);
						CommonFunction.logStatus("FAIL", msg);
						break;
					}
				}
			} else if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("12.0.0"))
					||(projectVersion.equalsIgnoreCase("12.1.0")) ||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				List<WebElement> ele = (List<WebElement>) js
						.executeScript("return " + "document.querySelectorAll(\"arcgis-group-card\")");
				for (int i = ele.size() ; i >0; i--) {
					String GROUP11_5_jspath = "document.querySelector('arcgis-group-browser > arcgis-group-browser-content > arcgis-group-card:nth-child(%s)').shadowRoot.querySelector('section > a > div > div.group-card-flex-1 > div > div > h3 > span')";
					cfunction.ScrollToWebElement((String.format(GROUP11_5_jspath, (i))));
					WebElement element = cfunction.Webelement_JSPath(String.format(GROUP11_5_jspath, (i)));
					if (element.getText().contains(GroupName)) {
						// cfunction.waitforinvisibilityofelement(element, 10);
						CommonFunction.logStatus("FAIL", msg);
						break;
					}
				}
			}
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void creategroup() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Click on create group";
		try {
			if (projectVersion.equalsIgnoreCase("11.2.0"))
				cfunction.CommmonJS_Click(CREATE_GROUP11_2_XPATH);
			else
				cfunction.CommmonJS_Click(CREATE_GROUP_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Enter group name";
		try {
			if (projectVersion.equals("10.9.1") || projectVersion.startsWith("11")) {
				cfunction.CommonTextBox_Input(GROUP_NAME_XPATH_VS_10_9_1, GroupName);
			} else
				cfunction.CommonTextBox_Input(GROUP_NAME_XPATH, GroupName);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Enter Summary";
		try {
			if (projectVersion.equals("10.9.1") || projectVersion.startsWith("11")) {
				cfunction.CommonTextBox_Input(GROUP_SUMMARY_XPATH_VS_10_9_1, GroupName);
			} else
				cfunction.CommonTextBox_Input(GROUP_SUMMARY_XPATH, GroupName);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Enter Tags";
		try {
			if (projectVersion.equals("10.9.1") || projectVersion.startsWith("11")) {
				cfunction.CommonTextBox_Input(GROUP_TAGS_XPATH_VS_10_9_1, "Test");
			} else {
				cfunction.CommonTextBox_Input(GROUP_TAGS_XPATH, "Test");
				WebElement element1 = driver.findElement(By.xpath(GROUP_TAGS_XPATH));
				element1.sendKeys(Keys.ENTER);
			}
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on create group";
		try {
			if (projectVersion.equals("10.9.1")) {
				cfunction.CommmonJS_Click(GROUP_SAVE_XPATH_VS_10_9_1);
			} else if (projectVersion.startsWith("11")) {
				cfunction.Commmon_Click("xpath", GROUP_SAVE_XPATH_VS_10_9_1);
			} else
				cfunction.CommmonJS_Click(GROUPS_CREATE_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify Group created";
		try {
			cfunction.waitforelement(INVITE_UERS_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e1) {
			try {
				// System.out.println("In verify group page");
				cfunction.CommmonJS_Click(GROUP_SAVE_XPATH_VS_10_9_1);
				cfunction.waitforelement(INVITE_UERS_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				groupcreated = false;
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
	}

	public void creategroup_11_2() throws Exception {

		// CommonFunction.waitforpagetoload();
		msg = "Click on create group";
		try {
			cfunction.CommmonJS_Click(CREATE_GROUP11_2_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			try {
				cfunction.CommmonJS_Click(CREATE_GROUP11_2_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
			}
		}
		
		msg = "Enter group name";
		try {
			cfunction.Webelement_JSInput(GROUP_NAME_XPATH_VS_11_2_0, GroupName);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Enter Summary";
		try {
			if (!(projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| 
					(projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))
					||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0")))) {
				cfunction.Webelement_JSInput(GROUP_SUMMARY_11_2_0_JSPATH, GroupName);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Enter Tags";
		try {
			cfunction.Webelement_JSInput(GROUPS_TAGS_JSPATH_11_2_0, "test");
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on create group";
		try {

			cfunction.Commmon_Click("xpath", GROUPS_CREATE_XPATH_11_2_0);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		
		cfunction.sync(5);
		msg = "Verify Group created";
		try {
			if (projectVersion.equalsIgnoreCase("11.4.0") || (projectVersion.equalsIgnoreCase("11.5.0"))||
					(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("12.0.0"))||
					(projectVersion.equalsIgnoreCase("12.1.0"))||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				cfunction.waitforelement(INVITE_UERS_11_4_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} else {
				cfunction.waitforelement(INVITE_UERS_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e1) {
			try {
				// System.out.println("In verify group page");
				cfunction.CommmonJS_Click(GROUP_SAVE_XPATH_VS_10_9_1);
				cfunction.waitforelement(INVITE_UERS_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				groupcreated = false;
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}

	}

	public void invitemembers() throws Exception {
		// CommonFunction.waitforpagetoload();

		msg = "Click on invite members";
		try {
			if (projectVersion.equalsIgnoreCase("11.4.0") || (projectVersion.equalsIgnoreCase("11.5.0"))||
					(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("12.0.0"))
					||(projectVersion.equalsIgnoreCase("12.1.0"))||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				cfunction.CommmonJS_Click(INVITE_UERS_11_4_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} else {
				cfunction.CommmonJS_Click(INVITE_UERS_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(2);
		msg = "Click on user1";
		try {
			cfunction.CommmonJS_Click(USER1_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(2);
		msg = "Verify user1 added";
		try {
			cfunction.waitforelement(SELECTED_USER1_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(2);
		msg = "Click on user2";
		try {
			cfunction.CommmonJS_Click(USER2_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(2);
		msg = "Verify user2 added";
		try {
			cfunction.waitforelement(SELECTED_USER2_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(2);
		msg = "Click on send invitation";
		try {
			cfunction.CommmonJS_Click(SEND_INVITE_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void invitemembers_10_8_0() throws Exception {
		// CommonFunction.waitforpagetoload();

		msg = "Click on invite members";
		try {
			cfunction.CommmonJS_Click(INVITE_UERS_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(2);
		msg = "Click on user1";
		try {
			cfunction.CommmonJS_Click(USER1_10_8_0_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		cfunction.sync(2);
		msg = "Click on user2";
		try {
			cfunction.CommmonJS_Click(USER2_10_8_0_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(2);
		msg = "Click on send invitation";
		try {
			if (projectVersion.equals("10.9.1") || projectVersion.startsWith("11")) {
				cfunction.CommmonJS_Click(ADD_TO_GROUP_XPATH_VS_10_9_1);
			} else
				cfunction.CommmonJS_Click(ADD_TO_GROUP_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void invitemembers11_2_0() throws Exception {

		// CommonFunction.waitforpagetoload();

		msg = "Click on invite members";
		try {
			if (projectVersion.equalsIgnoreCase("11.4.0") || (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0")
							||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0")))
					) {
				cfunction.CommmonJS_Click(INVITE_UERS_11_4_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} else {
				cfunction.CommmonJS_Click(INVITE_UERS_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(5);
		msg = "Click on user1";
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			if((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("12.0.0"))
					||(projectVersion.equalsIgnoreCase("12.1.0"))||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {

				List<WebElement> ele = (List<WebElement>) js.executeScript("return " + MEMBERS_LIST11_5_0_JSPATH);
				for (int i = ele.size(); i >= 0; i--) {
					cfunction.ScrollToWebElement(String.format(MEMBERS_NAME_11_5_0_JSPATH, (i)));
					String listName = cfunction.Webelement_JSPath(String.format(MEMBERS_NAME_11_5_0_JSPATH, (i)))
							.getText();
					if (listName.contains("Test " + Testuser1)) {
						cfunction.clickusingActions(String.format(MEMBERS_NAME_11_5_0_JSPATH, (i)));
						break;
					}
				}
			} else {
				List<WebElement> ele1 = (List<WebElement>) js.executeScript("return " + MEMBERS_LIST11_2_0_JSPATH);
				for (int j = ele1.size(); j > 0; j--) {
					String jspath1 = "document.querySelector(\"[class*='members-list'] >div:nth-child(" + (j)
							+ ")>[class*='flex'] >div> div>arcgis-user-avatar\").shadowRoot.querySelector(\"calcite-avatar~span\")";

					WebElement element1 = (WebElement) js.executeScript("return " + jspath1);
					if (element1.getText().contains("Test " + Testuser1)) {
						String jspathclick = "document.querySelector(\"[class*='members-list'] >div:nth-child("
								+ (j ) + ")>[class*='checkbox']>span\")";
						cfunction.Webelement_JSClick(jspathclick);
						break;
					}
				}
			}
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		cfunction.sync(3);
		msg = "Click on user2";
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;

			if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("12.0.0"))
					||(projectVersion.equalsIgnoreCase("12.1.0")) ||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {

				List<WebElement> ele = (List<WebElement>) js.executeScript("return " + MEMBERS_LIST11_5_0_JSPATH);
				for (int i = ele.size(); i >= 0; i--) {
					cfunction.ScrollToWebElement(String.format(MEMBERS_NAME_11_5_0_JSPATH, (i)));
					String listName = cfunction.Webelement_JSPath(String.format(MEMBERS_NAME_11_5_0_JSPATH, (i)))
							.getText();
					if (listName.contains("Test " + Testuser2)) {
						cfunction.clickusingActions(String.format(MEMBERS_NAME_11_5_0_JSPATH, (i)));
						break;
					}
				}
			} else {

				List<WebElement> ele1 = (List<WebElement>) js.executeScript("return " + MEMBERS_LIST11_2_0_JSPATH);
				for (int j = ele1.size() - 1; j >= 0; j--) {
					String jspath = "document.querySelector(\"[class*='members-list'] >div:nth-child(" + (j)
							+ ")>[class*='flex'] >div> div>arcgis-user-avatar\").shadowRoot.querySelector(\"calcite-avatar~span\")";
					WebElement element = (WebElement) js.executeScript("return " + jspath);
					if (element.getText().contains("Test " + Testuser2)) {
						String jspathclick = "document.querySelector(\"[class*='members-list'] >div:nth-child("
								+ (j) + ")>[class*='checkbox']>span\")";
						cfunction.Webelement_JSClick(jspathclick);
						break;
					}
				}
			}
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(2);
		msg = "Click on send invitation";
		try {
			if (projectVersion.equals("10.9.1") || projectVersion.equalsIgnoreCase("11.3.0")
					|| projectVersion.equalsIgnoreCase("11.1.0") || projectVersion.equalsIgnoreCase("11.2.0")) {
				cfunction.CommmonJS_Click(ADD_TO_GROUP_XPATH_VS_10_9_1);
			} else if (projectVersion.equals("11.4.0") || (projectVersion.equalsIgnoreCase("11.5.0"))||
					(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("12.0.0"))||
					(projectVersion.equalsIgnoreCase("12.1.0"))||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				cfunction.CommmonJS_Click(ADD_TO_GROUP_XPATH_VS_11_4_0);
			} else
				cfunction.CommmonJS_Click(ADD_TO_GROUP_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void sharecontents1() throws Exception {
		// CommonFunction.waitforpagetoload();

		msg = "Share contents";
		try {
			if (featurecreated) {
				// div[contains(@class,'table-select-rows')]//div[contains(@class,'table-select-row')]//span[contains(@class,'table-select-title')]/a

				String textname = "";
				List<WebElement> list1 = driver.findElements(By.xpath(
						"//div[contains(@class,'table-select-rows')]//div[contains(@class,'table-select-row')]"));
				if (list1.size() > 0) {
					for (int i = 1; i <= list1.size(); i++) {
						textname = driver.findElement(By.xpath(
								"(//div[contains(@class,'table-select-rows')]//div[contains(@class,'table-select-row')]//span[contains(@class,'table-select-title')]/a)["
										+ i + "]"))
								.getText();
						if (textname.equalsIgnoreCase(FeatureName)) {

							try {
								msg = "Click on share icon corersponding to feature layer";
								driver.findElement(By.xpath("(//span/button[@data-action='share'])[" + i + "]"))
										.click();
								CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								e.printStackTrace();

								CommonFunction.logStatusWithException("FAIL", msg, e);

							}

							cfunction.sync(3);
							
							try {
								msg = "Select Group";
								driver.findElement(By.xpath("//label[text()='" + GroupName + "']/../..//td/input"))
										.click();
								cfunction.sync(3);
							} catch (Exception e) {
								e.printStackTrace();

								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
							try {
								msg = "Click OK button";
								driver.findElement(By.xpath(
										"//span[contains(@id,'dijit_form_Button')]//span[contains(@id,'dijit_form_Button')]"))
										.click();
								cfunction.sync(5);
							} catch (Exception e) {
								e.printStackTrace();

								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
							// if(driver.findElements(By.xpath("//span[contains(@class,'jevent')and
							// contains(@class,'primary') and
							// contains(@class,'right')]/span/span").size()>0) {
							// driver.findElement(By.xpath("//span[contains(@class,'jevent')and
							// contains(@class,'primary') and contains(@class,'right')]/span/span").click();
							// }

							if (driver.findElements(By
									.xpath("//span[contains(@class,'jevent')and contains(@class,'primary')]/span/span"))
									.size() > 0) {
								driver.findElement(By.xpath(
										"//span[contains(@class,'jevent')and contains(@class,'primary')]/span/span"))
										.click();
								// driver.findElement(By.xpath("//span[contains(@id,'dijit_form_Button')]//span[contains(@id,'dijit_form_Button')]").click();
								cfunction.sync(5);
							}
							if (driver
									.findElements(By.xpath(
											"//span[contains(@class,'jevent')and contains(@class,'ok')]/span/span"))
									.size() > 0) {
								driver.findElement(By
										.xpath("//span[contains(@class,'jevent')and contains(@class,'ok')]/span/span"))
										.click();
								// driver.findElement(By.xpath("//span[contains(@id,'dijit_form_Button')]//span[contains(@id,'dijit_form_Button')]").click();
								cfunction.sync(5);
							}
							CommonFunction.logStatus("PASS", msg);
							break;
						}

					}
				} else {

				}
			} else {
				for (int i = 1; i <= 1; i++) {

					driver.findElement(By.xpath("(//span/button[@data-action='share'])[" + i + "]")).click();
					cfunction.sync(3);
					driver.findElement(By.xpath("//label[text()='" + GroupName + "']/../..//td/input")).click();
					cfunction.sync(3);
					driver.findElement(By.xpath(
							"//span[contains(@id,'dijit_form_Button')]//span[contains(@id,'dijit_form_Button')]"))
							.click();
					cfunction.sync(5);
					// if(driver.findElements(By.xpath("//span[contains(@class,'jevent')and
					// contains(@class,'primary') and
					// contains(@class,'right')]/span/span").size()>0) {
					// driver.findElement(By.xpath("//span[contains(@class,'jevent')and
					// contains(@class,'primary') and contains(@class,'right')]/span/span").click();
					// }
					if (driver
							.findElements(By
									.xpath("//span[contains(@class,'jevent')and contains(@class,'cancel')]/span/span"))
							.size() > 0) {
						driver.findElement(
								By.xpath("//span[contains(@class,'jevent')and contains(@class,'cancel')]/span/span"))
								.click();
						driver.findElement(By.xpath(
								"//span[contains(@id,'dijit_form_Button')]//span[contains(@id,'dijit_form_Button')]"))
								.click();
						cfunction.sync(5);
					}

				}
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();

			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void sharecontents() throws Exception {
		// CommonFunction.waitforpagetoload();
		boolean flagcontenttoshare = false;
		msg = "Share contents";
		try {
			if (flagcontent) {
				// div[contains(@class,'table-select-rows')]//div[contains(@class,'table-select-row')]//span[contains(@class,'table-select-title')]/a

				String textname = "";
				List<WebElement> list1 = driver.findElements(By.xpath(
						"//div[contains(@class,'table-select-rows')]//div[contains(@class,'table-select-row')]"));
				if (list1.size() > 0) {
					flagcontenttoshare = true;
					String str=list1.get(0).getText();
					String contentname=str.substring(0,str.indexOf(" "));
					
					try {
							msg = "Click on share icon corersponding to content:" + contentname;
							driver.findElement(By.xpath("(//span/button[@data-action='share'])[1]")).click();
							cfunction.sync(3);
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}

						try {
							msg = "Select Group: "	+ GroupName;
							driver.findElement(By.xpath("//label[text()='" + GroupName + "']/../..//td/input")).click();
							cfunction.sync(3);
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						try {
							msg = "Click OK button";
							driver.findElement(By.xpath(
									"//div[@widgetid='share-dialog']//span[contains(@id,'dijit_form_Button')]//span[contains(@id,'dijit_form_Button')]"))
									.click();
							cfunction.sync(5);
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}

						if (driver.findElements(By.xpath(
								"//div[contains(@class,'dijitFocused') and @id='shareCheck-dialog']//span[contains(@class,'jevent')and contains(@class,'cancel')]/span/span"))
								.size() > 0) {
							try {
								msg = "Click Cancel button on update sharing dialog";
								driver.findElement(By.xpath(
										"//div[contains(@class,'dijitFocused') and @id='shareCheck-dialog']//span[contains(@class,'jevent')and contains(@class,'cancel')]/span/span"))
										.click();
								cfunction.sync(2);
								CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								e.printStackTrace();

								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
						}

						try {
							msg = "Verify Share Icon appears";
							cfunction.waitforelement(
									"(//button[@data-action='share'  and  contains(@aria-label,'Shared') and  contains(@aria-label,'Group')])[1]");
							cfunction.sync(3);
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						/*
						 * if(driver. findElements(By.
						 * xpath("//div[contains(@class,'dijitFocused') and @id='shareCheck-dialog']//span[contains(@class,'jevent')and contains(@class,'ok')]/span/span"
						 * ).size()>0) { driver. findElement(By.
						 * xpath("//div[contains(@class,'dijitFocused') and @id='shareCheck-dialog']//span[contains(@class,'jevent')and contains(@class,'ok')]/span/span"
						 * ).click(); //driver.findElement(By.xpath(
						 * "//span[contains(@id,'dijit_form_Button')]//span[contains(@id,'dijit_form_Button')]"
						 * ).click(); cfunction.sync(5); }
						 */
						// CommonFunction.logStatus("PASS", msg);
						// break;
						// }

				} else {
					flagtilelayer=true;
				//cp.clickmycontentfolder();
				
				
			}
			}
			if (!flagcontenttoshare) {

				if (driver.findElements(By.xpath("//span/button[@data-action='share']")).size() > 0) {
					try {
						msg = "Click on share icon corersponding to item 1";
						driver.findElement(By.xpath("(//span/button[@data-action='share'])[1]")).click();
						cfunction.sync(3);
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();

						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
					try {
						msg = "Select Group name: " + GroupName;
						driver.findElement(By.xpath("//label[text()='" + GroupName + "']/../..//td/input")).click();
						cfunction.sync(3);
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();

						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
					try {
						msg = "Click on OK to share content";
						driver.findElement(By.xpath(
								"//div[@widgetid='share-dialog']//span[contains(@id,'dijit_form_Button')]//span[contains(@id,'dijit_form_Button')]"))
								.click();
						cfunction.sync(2);
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();

						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
					/*
					 * if(driver. findElements(By.
					 * xpath("//span[contains(@class,'jevent')and contains(@class,'cancel')]/span/span"
					 * ).size()>0) { driver. findElement(By.
					 * xpath("//span[contains(@class,'jevent')and contains(@class,'cancel')]/span/span"
					 * ).click(); //driver.findElement(By.xpath(
					 * "//span[contains(@id,'dijit_form_Button')]//span[contains(@id,'dijit_form_Button')]"
					 * ).click(); cfunction.sync(5); }
					 */

					if (driver.findElements(By.xpath(
							"//div[contains(@class,'dijitFocused') and @id='shareCheck-dialog']//span[contains(@class,'jevent')and contains(@class,'cancel')]/span/span"))
							.size() > 0) {
						try {
							msg = "Click Cancel button on update sharing dialog";
							driver.findElement(By.xpath(
									"//div[contains(@class,'dijitFocused') and @id='shareCheck-dialog']//span[contains(@class,'jevent')and contains(@class,'cancel')]/span/span"))
									.click();
							cfunction.sync(2);
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						try {
							msg = "Verify Share Icon appears";
							cfunction.waitforelement(
									"(//button[@data-action='share'  and  contains(@aria-label,'Shared') and  contains(@aria-label,'Group')])[1]");
							cfunction.sync(3);
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
					} /*
						 * if(driver. findElements(By.
						 * xpath("//div[contains(@class,'dijitFocused') and @id='shareCheck-dialog']//span[contains(@class,'jevent')and contains(@class,'ok')]/span/span"
						 * ).size()>0) { driver. findElement(By.
						 * xpath("//div[contains(@class,'dijitFocused') and @id='shareCheck-dialog']//span[contains(@class,'jevent')and contains(@class,'ok')]/span/span"
						 * ).click(); //driver.findElement(By.xpath(
						 * "//span[contains(@id,'dijit_form_Button')]//span[contains(@id,'dijit_form_Button')]"
						 * ).click(); cfunction.sync(2); }
						 */
				} else {
					CommonFunction.logStatus("FAIL", "There is no content available to share.");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void sharecontents_10_8_1() throws Exception {
		// CommonFunction.waitforpagetoload();
		boolean flagcontenttoshare = false;
		cfunction.sync(5);
		msg = "Share contents";
		try {
			if (flagcontent) {
				
				String textname = "";
				List<WebElement> list1 = driver.findElements(By.xpath(
						"//div[contains(@class,'table-select-rows')]//div[contains(@class,'table-select-row')]"));
				if (list1.size() > 0) {
					flagcontenttoshare = true;
					try {
						String str=list1.get(0).getText();
						String contentname = str.split("\\R")[0]; // \\R matches any line break
		                   
							cfunction.sync(5);
							msg = "Click on share icon corersponding to content: " + contentname;
							driver.findElement(By.xpath("(//button[contains(@class,'js-share-button')])[1]"))
									.click();
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						cfunction.sync(3);
						
						try {
							msg = "Click on edit group sharing button";
							cfunction.JSclickOnListOfElementIDBasedOnIndex("edit-group-sharing-button", 0);
							// driver.findElementById("edit-group-sharing-button").click();
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);
						}
						cfunction.sync(3);
						
						try {
							msg = "Select Group as Owner";
							cfunction.JSclickOnListOfElementBasedOnIndex("//input[@title='" + GroupName + "']", 0);
							// driver.findElement(By.xpath("//input[@title='"+GroupName+"']").click();
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						cfunction.sync(3);
						
						try {
							msg = "Set Sharing level to Owner by clicking ok button";
							cfunction.JSclickOnListOfElementBasedOnIndex(
									"//div[@class='share-dlg__container']//button[@id='share-modal-primary']", 0);
							// driver.findElement(By.xpath("//div[@class='share-dlg__container']//button[@id='share-modal-primary']").click();
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						cfunction.sync(5);
						
						try {
							msg = "Click Save button";
							cfunction.JSclickOnListOfElementBasedOnIndex(
									"//div[@class='share-dlg__container']//button[@id='share-modal-primary']", 0);
							// driver.findElement(By.xpath("//div[@class='share-dlg__container']//button[@id='share-modal-primary']").click();
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						cfunction.sync(5);
						
						if (driver
								.findElements(By.xpath(
										"//div[@class='share-dlg__container']//button[@id='share-modal-secondary']"))
								.size() > 0) {
							try {
								msg = "Click Cancel button on update sharing dialog";
								cfunction.JSclickOnListOfElementBasedOnIndex(
										"//div[@class='share-dlg__container']//button[@id='share-modal-secondary']", 0);
								// driver.findElement(By.xpath("//div[@class='share-dlg__container']//button[@id='share-modal-secondary']").click();
								CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								e.printStackTrace();

								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
						}
						cfunction.sync(2);

						
						try {
							msg = "Verify Share Icon appears";
							cfunction.waitforelement(
									"(//span[@class='js-sharing-plus-icon share-summary__plus'])[1]");
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						
				} else {
					flagcontenttoshare = false;
					String FolderName1 = driver
							.findElement(By.xpath("//h4[@class='folder-list-title']//following-sibling::ul/li[2]"))
							.getAttribute("data-title");
					msg = "Navigate to default user folder";
					try {
						cfunction.CommmonJS_Click("//h4[@class='folder-list-title']//following-sibling::ul//a[text()='"
								+ FolderName1 + "']");
						cfunction.waitforpagetoload();
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
				}
			}
			if (!flagcontenttoshare) {

				if (driver.findElements(By.xpath("//button[contains(@class,'js-share-button')]")).size() > 0) {
					try {
						msg = "Click on share icon corersponding to content1:";
						driver.findElement(By.xpath("(//button[contains(@class,'js-share-button')])[1]")).click();
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();

						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
					cfunction.sync(3);
					
					try {
						msg = "Click on edit group sharing button ";
						driver.findElement(By.id("edit-group-sharing-button")).click();
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();

						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
					cfunction.sync(3);
					
					try {
						msg = "Select Group";
						driver.findElement(By.xpath("//input[@title='" + GroupName + "']")).click();
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();

						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
					cfunction.sync(3);
					
					try {
						msg = "Click OK button";
						driver.findElement(
								By.xpath("//div[@class='share-dlg__container']//button[@id='share-modal-primary']"))
								.click();
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();

						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
					cfunction.sync(3);
					
					try {
						msg = "Click Save button";
						driver.findElement(
								By.xpath("//div[@class='share-dlg__container']//button[@id='share-modal-primary']"))
								.click();
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();

						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
					cfunction.sync(5);
					

					if (driver
							.findElements(By
									.xpath("//div[@class='share-dlg__container']//button[@id='share-modal-secondary']"))
							.size() > 0) {
						try {
							msg = "Click cancel button on Update share dialog";
							driver.findElement(By
									.xpath("//div[@class='share-dlg__container']//button[@id='share-modal-secondary']"))
									.click();
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
					}
					cfunction.sync(2);

					
					try {
						msg = "Verify Share Icon appears";
						cfunction.waitforelement("(//span[@class='js-sharing-plus-icon share-summary__plus'])[1]");
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);
					}
					cfunction.sync(3);
					
				} else {
					CommonFunction.logStatus("FAIL", "There is no content available to share.");

				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void sharecontents_11_3_0() throws Exception {
		// CommonFunction.waitforpagetoload();
		boolean flagcontenttoshare = false;
		msg = "Share contents";
		
		try {
			if (flagcontent) {
				String textname = "";
				List<WebElement> list1 = driver.findElements(
						By.xpath("//arcgis-item-browser-table[@class='hydrated']//arcgis-item-browser-table-row"));
				if (list1.size() > 0) {
					String str=list1.get(0).getText();
					String contentname = str.split("\\R")[0]; // \\R matches any line break
                    flagcontenttoshare = true;
					try {
							cfunction.sync(2);
							msg = "Click on share icon corersponding to content: "+contentname;
							cfunction.ScrollToWebElement(SHAREBUTTONONMAP_11_3_JSPATH);
							cfunction.clickusingActions(SHAREBUTTONONMAP_11_3_JSPATH);
							CommonFunction.logStatus("PASS", msg);
							cfunction.sync(3);
							
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						try {
							msg = "Click on edit group sharing button";
							cfunction.sync(2);
							cfunction.ScrollToWebElement(EDITSHARINGBUTTON_11_3_JSPATH);
							cfunction.clickusingActions(EDITSHARINGBUTTON_11_3_JSPATH);
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						cfunction.sync(2);
						
						try {
							msg = "Select Group: "	+ GroupName;
							List<WebElement> list = cfunction.WebelementList_JSPath(GROUPSHARINGLIST11_3_JSPATH);
							// System.out.println(list.size());
							for (int j = 0; j < list.size(); j++) {
								cfunction.ScrollToWebElement((String.format(GROUPSHARINGNAME11_3_JSPATH, (j + 1))));
								WebElement ele = cfunction
										.Webelement_JSPath(String.format(GROUPSHARINGNAME11_3_JSPATH, (j + 1)));
								// System.out.println(ele.getText());
								if (ele.getText().equalsIgnoreCase(GroupName)) {
									cfunction.ScrollToWebElement((String.format(GROUPSHARINGNAME11_3_JSPATH, (j + 1))));
									cfunction.clickusingActions(String.format(GROUPSHARINGNAME11_3_JSPATH, (j + 1)));
									CommonFunction.logStatus("PASS", msg);
									break;
								}
							}
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}

						cfunction.sync(2);
						
						msg = "Click Apply button to share content";
						try {
							if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
									||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))) {

								cfunction.ScrollToWebElement(APPLYBUTTON_11_5_JSPATH);
								cfunction.clickusingActions((APPLYBUTTON_11_5_JSPATH));
								CommonFunction.logStatus("PASS", msg);
							} else {
								cfunction.ScrollToWebElement(APPLYBUTTON11_3_JSPATH);
								cfunction.clickusingActions((APPLYBUTTON11_3_JSPATH));
								CommonFunction.logStatus("PASS", msg);
							}
						} catch (Exception e) {
							e.printStackTrace();
							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						cfunction.sync(5);
						
						msg = "Click Save button";
						try {
							if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| 
									(projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))
									||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))){
								cfunction.ScrollToWebElement(APPLYBUTTON_11_5_JSPATH);
								cfunction.clickusingActions((APPLYBUTTON_11_5_JSPATH));
								CommonFunction.logStatus("PASS", msg);
							} else {
								cfunction.ScrollToWebElement(APPLYBUTTON11_3_JSPATH);
								cfunction.clickusingActions((APPLYBUTTON11_3_JSPATH));
								CommonFunction.logStatus("PASS", msg);
							}
						} catch (Exception e) {
							e.printStackTrace();
							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						
						cfunction.sync(3);
						

						WebElement ex;
						WebElement ex1;
						try {
							if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| 
									(projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))
									||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
								ex1 = cfunction.Webelement_JSPath(
										"document.querySelector('arcgis-tunnel > arcgis-item-share').shadowRoot.querySelector('arcgis-dependency-check').shadowRoot.querySelector('calcite-dialog:nth-child(2) > calcite-button')");
								cfunction.clickusingActions((DONTCLICKUPDATE_11_5_JSPATH));
								if (!(ex1 == null)) {
									cfunction.clickusingActions((DONTCLICKCANCLEUPDATE_11_5_JSPATH));
								}
							} else {
								ex = cfunction.Webelement_JSPath(
										"document.querySelector('arcgis-tunnel > arcgis-item-share').shadowRoot.querySelector('arcgis-dependency-check').shadowRoot.querySelector('calcite-modal').shadowRoot.querySelector('div.header > header')");
								msg = "Click Cancel button on update sharing dialog";
								cfunction.clickusingActions((DONTCLICKUPDATE_11_3_JSPATH));
								if (!(ex == null)) {
									cfunction.clickusingActions((DONTCLICKUPDATE_11_3_JSPATH));
								}
							}

						} catch (Exception e) {
							ex = null;
							ex1 = null;
						}
				}

			} else {
				flagcontenttoshare = false;

				msg = "Navigate to default user folder";
				try {
					if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| 
							(projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))
							||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
						String ADMIN_FOLDER_JSPATH = "document.querySelector('calcite-list > calcite-list-item:nth-child(2)').shadowRoot.querySelector('div > div.wrapper >div')";
						cfunction.ScrollToWebElement(ADMIN_FOLDER_JSPATH);
						cfunction.clickusingActions(ADMIN_FOLDER_JSPATH);

						CommonFunction.logStatus("PASS", msg);
					} else {
						String ADMIN_FOLDER_JSPATH = "document.querySelector(\" div.js-root arcgis-item-browser > arcgis-item-browser-filters > arcgis-item-browser-filter-folder > arcgis-browser-filter > div > calcite-list > calcite-list-item:nth-child(2)\").shadowRoot.querySelector(\"div > tr > td.content-container.content-container--selectable.content-container--has-center-content \")";
						cfunction.ScrollToWebElement(ADMIN_FOLDER_JSPATH);
						cfunction.clickusingActions(ADMIN_FOLDER_JSPATH);

						CommonFunction.logStatus("PASS", msg);
					}
				} catch (Exception e) {
					e.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e);

				}

				cfunction.ScrollToElement("//h1[text()='Content']");

				if (!flagcontenttoshare) {
					List<WebElement> list1 = driver.findElements(
							By.xpath("//arcgis-item-browser-table[@class='hydrated']//arcgis-item-browser-table-row"));
					if (list1.size() > 0) {
						try {
							// cfunction.sync(7);
							msg = "Click on share icon corersponding to content " ;
							cfunction.ScrollToWebElement(String.format(SHAREBUTTONONMAP_11_3_JSPATH, (1)));
							cfunction.clickusingActions(String.format(SHAREBUTTONONMAP_11_3_JSPATH, (1)));
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						
						cfunction.sync(5);
						
						try {
							msg = "Click on edit group sharing button";
							cfunction.ScrollToWebElement(String.format(EDITSHARINGBUTTON_11_3_JSPATH));
							cfunction.clickusingActions(String.format(EDITSHARINGBUTTON_11_3_JSPATH));
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						
						cfunction.sync(2);
						
						try {
							msg = "Select Group";
							List<WebElement> list = cfunction.WebelementList_JSPath(GROUPSHARINGLIST11_3_JSPATH);
							// System.out.println(list.size());
							for (int j = 0; j < list.size(); j++) {
								cfunction.ScrollToWebElement((String.format(GROUPSHARINGNAME11_3_JSPATH, (j + 1))));
								WebElement ele = cfunction
										.Webelement_JSPath(String.format(GROUPSHARINGNAME11_3_JSPATH, (j + 1)));
								// System.out.println(ele.getText());
								if (ele.getText().equalsIgnoreCase(GroupName)) {
									cfunction.ScrollToWebElement((String.format(GROUPSHARINGNAME11_3_JSPATH, (j + 1))));
									cfunction.clickusingActions(String.format(GROUPSHARINGNAME11_3_JSPATH, (j + 1)));
									CommonFunction.logStatus("PASS", msg);
									break;
								}
							}
						} catch (Exception e) {
							e.printStackTrace();

							CommonFunction.logStatusWithException("FAIL", msg, e);

						}

						msg = "Click Apply button to share content";
						try {
							if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))||
									(projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))
									||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {

								cfunction.ScrollToWebElement(APPLYBUTTON_11_5_JSPATH);
								cfunction.clickusingActions((APPLYBUTTON_11_5_JSPATH));
								CommonFunction.logStatus("PASS", msg);
							} else {
								cfunction.ScrollToWebElement(APPLYBUTTON11_3_JSPATH);
								cfunction.clickusingActions((APPLYBUTTON11_3_JSPATH));
								CommonFunction.logStatus("PASS", msg);
							}
						} catch (Exception e) {
							e.printStackTrace();
							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						
						cfunction.sync(3);
						
						msg = "Click Save button";
						try {
							if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))||
									(projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))
									||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {

								cfunction.ScrollToWebElement(APPLYBUTTON_11_5_JSPATH);
								cfunction.clickusingActions((APPLYBUTTON_11_5_JSPATH));
								CommonFunction.logStatus("PASS", msg);
							} else {
								cfunction.ScrollToWebElement(APPLYBUTTON11_3_JSPATH);
								cfunction.clickusingActions((APPLYBUTTON11_3_JSPATH));
								CommonFunction.logStatus("PASS", msg);
							}
						} catch (Exception e) {
							e.printStackTrace();
							CommonFunction.logStatusWithException("FAIL", msg, e);

						}

						WebElement ex;
						try {
							ex = cfunction.Webelement_JSPath(
									"document.querySelector('arcgis-tunnel > arcgis-item-share').shadowRoot.querySelector('arcgis-dependency-check').shadowRoot.querySelector('calcite-modal').shadowRoot.querySelector('div.header > header')");
							msg = "Click Cancel button on update sharing dialog";
							cfunction.clickusingActions((DONTCLICKUPDATE_11_3_JSPATH));

						} catch (Exception e) {
							ex = null;

						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void sharecontents_10_8_1_1() throws Exception {
		// CommonFunction.waitforpagetoload();

		msg = "share contents";
		try {
			List<WebElement> list1 = driver.findElements(
					By.xpath("//div[contains(@class,'table-select-rows')]//div[contains(@class,'table-select-row')]"));
			// List<WebElement> list1 =
			// driver.findElements(By.xpath("//span/button[@data-action='share']");
			if (featurecreated) {
				// div[contains(@class,'table-select-rows')]//div[contains(@class,'table-select-row')]//span[contains(@class,'table-select-title')]/a

				String textname = "";
				for (int i = 1; i <= list1.size(); i++) {
					// list1.get(i).click();
					textname = driver.findElement(By.xpath(
							"(//div[contains(@class,'table-select-rows')]//div[contains(@class,'table-select-row')]//span[contains(@class,'table-select-title')]/a)["
									+ i + "]"))
							.getText();
					if (textname.equalsIgnoreCase(FeatureName)) {
						driver.findElement(By.xpath("(//button[contains(@class,'js-share-button')])[" + i + "]"))
								.click();
						cfunction.sync(3);
						driver.findElement(By.id("edit-group-sharing-button")).click();
						cfunction.sync(2);
						driver.findElement(By.xpath("//input[@title='" + GroupName + "']")).click();
						cfunction.sync(3);
						driver.findElement(By.xpath("//button[@id='share-modal-primary']")).click();
						cfunction.sync(3);
						driver.findElement(By.xpath("//button[@id='share-modal-primary']")).click();
						cfunction.sync(5);
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}

			} else {
				for (int i = 1; i <= 1; i++) {

					driver.findElement(By.xpath("(//button[contains(@class,'js-share-button')])[" + i + "]")).click();
					cfunction.sync(3);
					driver.findElement(By.id("edit-group-sharing-button")).click();
					cfunction.sync(2);
					driver.findElement(By.xpath("//input[@title='" + GroupName + "']")).click();
					cfunction.sync(3);
					driver.findElement(By.xpath("//button[@id='share-modal-primary']")).click();
					cfunction.sync(3);
					driver.findElement(By.xpath("//button[@id='share-modal-primary']")).click();
					cfunction.sync(5);
					CommonFunction.logStatus("PASS", msg);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void clickgroupcreated() throws Exception {
		// CommonFunction.waitforpagetoload();
		cfunction.sync(2);
		msg = "Click on group link";
		try {
			cfunction.sync(10);
			cfunction.CommmonJS_Click(GROUPS_CREATED_LINK_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void clickgroupcreated11_2_0() throws Exception {
		// CommonFunction.waitforpagetoload();
		cfunction.sync(8);
		msg = "Click on group link";
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			List<WebElement> ele = (List<WebElement>) js
					.executeScript("return " + "document.querySelectorAll(\"arcgis-group-card\")");

			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))) {
				for (int i = ele.size(); i>0 ; i--) {
					String GROUP11_3_jspath = "document.querySelector(\"div > arcgis-group-browser > arcgis-group-browser-content > arcgis-group-card:nth-child(%s)\").shadowRoot.querySelector(\"section > div.group-card__upper >div div.group-card-flex-1 >div>div > h3 > span\")";
					cfunction.ScrollToWebElement(String.format(GROUP11_3_jspath, (i )));
					WebElement element = cfunction.Webelement_JSPath(String.format(GROUP11_3_jspath, (i )));
					// WebElement element = (WebElement) js.executeScript("return " +
					// GROUP11_3_jspath);

					if (element.getText().contains(GroupName)) {
						Actions act = new Actions(driver);
						act.scrollToElement(element).build().perform();
						cfunction.sync(3);
						act.moveToElement(element).click().build().perform();
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			} else if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))
					||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				for (int i = ele.size(); i>0 ; i--) {
					String GROUP11_5_jspath = "document.querySelector('arcgis-group-browser > arcgis-group-browser-content > arcgis-group-card:nth-child(%s)').shadowRoot.querySelector('section > a > div > div.group-card-flex-1 > div > div > h3 > span')";
					cfunction.ScrollToWebElement(String.format(GROUP11_5_jspath, (i )));
					WebElement element = cfunction.Webelement_JSPath(String.format(GROUP11_5_jspath, (i)));
					if (element.getText().contains(GroupName)) {
						Actions act = new Actions(driver);
						act.scrollToElement(element).build().perform();
						cfunction.sync(3);
						act.moveToElement(element).click().build().perform();
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			} else {
				for (int i = ele.size(); i>0 ; i--) {
					String jspath = "document.querySelector(\"arcgis-group-card:nth-child(" + (i )
							+ ")\").shadowRoot.querySelector(\"section > a h3\")";
					WebElement element = (WebElement) js.executeScript("return " + jspath);

					if (element.getText().contains(GroupName)) {
						Actions act = new Actions(driver);
						act.scrollToElement(element).build().perform();
						cfunction.sync(3);
						act.moveToElement(element).click().build().perform();
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

	public void verifygroupcreated11_2_0() throws Exception {
		// CommonFunction.waitforpagetoload();
		cfunction.sync(2);
		msg = "Verify Group created";
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			List<WebElement> ele = (List<WebElement>) js
					.executeScript("return " + "document.querySelectorAll(\"arcgis-group-card\")");
			for (int i = ele.size(); i>0 ; i--) {
				String jspath = "document.querySelector(\"arcgis-group-card:nth-child(" + (i )
						+ ")\").shadowRoot.querySelector(\"section > a h3\")";
				WebElement element = (WebElement) js.executeScript("return " + jspath);

				if (element.getText().contains(GroupName)) {
					CommonFunction.logStatus("PASS", msg);
					break;
				}
			}

			CommonFunction.logStatus("FAIL", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void verifycontentsavailable() throws Exception {
		CommonFunction.waitforpagetoload();
		msg = "Verify contents available";
		try {
			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))) {
				String GORUPCONTENT11_3_JSPATH = "document.querySelector(\"arcgis-group-browser\").shadowRoot.querySelector(\"arcgis-browser > arcgis-group-browser-preview\").shadowRoot.querySelector(\"calcite-flow > calcite-flow-item\").shadowRoot.querySelector(\"div > calcite-panel\")";
				List<WebElement>list=cfunction.WebelementList_JSPath(GORUPCONTENT11_3_JSPATH);
				cfunction.waitforelement(GROUPS_CONTENT_XPATH);
				if(list.size()>0)
					CommonFunction.logStatus("PASS", msg);
				else
					CommonFunction.logStatus("FAIL", msg);
			} else {
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				driver.navigate().refresh();
				waitforpagetoload();
				cfunction.waitforelement(GROUPS_CONTENT_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				e1.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e1);
			}
		}
	}

	public void deletegroup() throws Exception {
		// CommonFunction.waitforpagetoload();
		boolean flag = true;
		msg = "Delete group";
		try {
			if (projectVersion.equalsIgnoreCase("11.2.0")) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				List<WebElement> ele = (List<WebElement>) js
						.executeScript("return " + "document.querySelectorAll(\"arcgis-group-card\")");

				for (int i = ele.size() ; i >0; i--) {
					String jspath = "document.querySelector(\"arcgis-group-card:nth-child(" + (i )
							+ ")\").shadowRoot.querySelector(\"section > a h3\")";
					WebElement element = cfunction.getWebelement_JSpath(jspath);
					if (element.getText().contains(GroupName)) {
						String jspathclick = "document.querySelector(\" arcgis-group-card:nth-child(" + (i )
								+ ") > calcite-dropdown > calcite-action\").shadowRoot.querySelector(\"button\")";
						Actions act = new Actions(driver);
						act.scrollToElement(element).build().perform();
						cfunction.sync(3);
						cfunction.clickusingActions(jspathclick);
						cfunction.sync(2);

						String delete = "document.querySelector(\" arcgis-group-card:nth-child(" + (i )
								+ ") > calcite-dropdown > calcite-dropdown-group > calcite-dropdown-item\")";
						cfunction.clickusingActions(delete);
						break;
					}
				}
			} else if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))) {

				JavascriptExecutor js = (JavascriptExecutor) driver;
				List<WebElement> ele = (List<WebElement>) js
						.executeScript("return " + "document.querySelectorAll(\"arcgis-group-card\")");
				for (int i = ele.size() ; i >0; i--) {
					String GROUP11_3_jspath = "document.querySelector(\"div > arcgis-group-browser > arcgis-group-browser-content > arcgis-group-card:nth-child(%s)\").shadowRoot.querySelector(\"section > div.group-card__upper >div div.group-card-flex-1 >div>div > h3 > span\")";
					cfunction.ScrollToWebElement((String.format(GROUP11_3_jspath, (i ))));
					WebElement element = cfunction.Webelement_JSPath(String.format(GROUP11_3_jspath, (i )));
					if (element.getText().contains(GroupName)) {
						Actions act = new Actions(driver);
						act.scrollToElement(element).build().perform();
						cfunction.sync(3);
						act.moveToElement(element).click().build().perform();
						String MoreDetails = "document.querySelector(\"div > arcgis-group-browser > arcgis-group-browser-content > arcgis-group-card:nth-child(%s) > div > calcite-dropdown > calcite-action\").shadowRoot.querySelector(\"button\")";
						cfunction.ScrollToWebElement(MoreDetails);
						cfunction.clickusingActions(String.format(MoreDetails, (i)));

						String delete = "document.querySelector(\" div > arcgis-group-browser > arcgis-group-browser-content > arcgis-group-card:nth-child(%s) > div > calcite-dropdown > calcite-dropdown-group > calcite-dropdown-item\")";
						cfunction.ScrollToWebElement((String.format(delete, (i))));
						cfunction.clickusingActions(String.format(delete, (i )));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			} else if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))
					||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {

				JavascriptExecutor js = (JavascriptExecutor) driver;
				List<WebElement> ele = (List<WebElement>) js
						.executeScript("return " + "document.querySelectorAll(\"arcgis-group-card\")");
				for (int i = ele.size() ; i >0; i--) {
					String GROUP11_5_jspath = "document.querySelector('arcgis-group-browser > arcgis-group-browser-content > arcgis-group-card:nth-child(%s)').shadowRoot.querySelector('section > a > div > div.group-card-flex-1 > div > div > h3 > span')";
					cfunction.ScrollToWebElement((String.format(GROUP11_5_jspath, (i))));
					WebElement element = cfunction.Webelement_JSPath(String.format(GROUP11_5_jspath, (i)));
					if (element.getText().contains(GroupName)) {
						/*
						 * Actions act = new Actions(driver);
						 * act.scrollToElement(element).build().perform(); cfunction.sync(3);
						 * act.moveToElement(element).click().build().perform();
						 */
						String MoreDetails = "document.querySelector(\"arcgis-group-browser > arcgis-group-browser-content > arcgis-group-card:nth-child(%s) > calcite-dropdown > calcite-action\").shadowRoot.querySelector(\"button\")";
						cfunction.ScrollToWebElement(String.format(MoreDetails, (i)));
						cfunction.clickusingActions(String.format(MoreDetails, (i)));
                         cfunction.sync(2);
						String delete = "document.querySelector('arcgis-group-browser > arcgis-group-browser-content > arcgis-group-card:nth-child(%s) > calcite-dropdown > calcite-dropdown-group > calcite-dropdown-item')";
						//cfunction.ScrollToWebElement((String.format(delete, (i + 1))));
						cfunction.clickusingActions(String.format(delete, (i)));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}

			} else {
				cfunction.CommmonJS_Click(DELETE_CREATED_GROUP_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
			flag = false;

		}
		if (flag) {
			cfunction.sync(2);
			msg = "click confirmation box";
			try {
				if (projectVersion.equalsIgnoreCase("11.1.0")) {
					cfunction.CommmonJS_Click(DELETE_OK_11_1_XPATH);
				}

				else if (projectVersion.equalsIgnoreCase("11.2.0") || (projectVersion.equalsIgnoreCase("11.3.0"))
						|| (projectVersion.equalsIgnoreCase("11.4.0"))) {
					cfunction.confirmdelete11_2_0();
				} else if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						) {
					cfunction.confirmdelete11_5_0();
				}else if (projectVersion.equalsIgnoreCase("12.0.0")||(projectVersion.equalsIgnoreCase("12.1.0"))
						||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))){
					cfunction.confirmdelete12_0_0();
				} else
					cfunction.CommmonJS_Click(DELETE_OK_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
	}
}

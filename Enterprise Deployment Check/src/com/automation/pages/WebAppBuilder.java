package com.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;

public class WebAppBuilder extends TestBase {
	ContentPage CP = new ContentPage();
	final String FOLDERITEM11_5_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelector('calcite-combobox > calcite-combobox-item:nth-child(%s)')";
	final String ALL_MY_CONTENT_11_3_JSPATH = "document.querySelector(\"calcite-list-item:nth-child(1)\").shadowRoot.querySelector(\"div > tr > td > div.content\")";
	final String ALL_MY_CONTENT_11_5_JSPATH = "document.querySelector(\"arcgis-item-browser-filter-folder > arcgis-browser-filter > div > calcite-list > calcite-list-item:nth-child(1)\").shadowRoot.querySelector(\"div > div.wrapper > div.row.container.container--hover.container--border.container--border-selected\")";

	final String FOLDERNAME11_3_JSPATH = "document.querySelector(\"calcite-list > calcite-list-item:nth-child(%s)\").shadowRoot.querySelector(\"div > tr\")";
	final String FOLDERLIST11_3_JSPATH = "document.querySelectorAll(\"arcgis-item-browser-filter-folder > arcgis-browser-filter > div > calcite-list > calcite-list-item\")";
	final String FOLDERNAMELIST11_5_JSPATH = "document.querySelector('calcite-list > calcite-list-item:nth-child(%s)').shadowRoot.querySelector('div > div.wrapper >div')";
	final String FEATURE_FOLDER_11_JSPATH = "document.querySelector(\"arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-folder-picker\").shadowRoot.querySelector(\"calcite-label > div > calcite-combobox\").shadowRoot.querySelector(\"div.wrapper.wrapper--single > div > span[class*='input-wrap']\")";
	final String FEATURE_FOLDER_11_1_JSPATH = "document.querySelector(\"arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-folder-picker\").shadowRoot.querySelector(\"calcite-label > div > div > calcite-combobox\").shadowRoot.querySelector(\"div.wrapper > div\")";
	String FEATURE_NAME_XPATH;
	final String SAVE_FEATURE_BUTTON11_3_JSPATH = "document.querySelector(\"body > calcite-modal:nth-child(16) > calcite-button\").shadowRoot.querySelector('div > button ')";
	final String DIALOG_TITLE11_3_XPATH = "//div[text()='Create a web app']";
	final String ENTERTAGE11_3_JSPATH = "document.querySelector('arcgis-item-properties > arcgis-tags-picker').shadowRoot.querySelector('calcite-label > calcite-combobox').shadowRoot.querySelector('input[type=text]')";
	final String TITTLE11_3_JSPATH = "document.querySelector('arcgis-title-input').shadowRoot.querySelector('#item-properties-title').shadowRoot.querySelector('div.element-wrapper > input[type=text]')";
	final String WEB_APP_BUILDER_XPATH = "//a[contains(@class,'top-nav-link') and contains(text(),'Web AppBuilder')]";
	// XPath code
	final String DIALOG_TITLE_XPATH = "//span[text()='Create a web app']";
	final String ENTER_TAGS_XPATH = "//div[contains(@id,'userTags')]//input[@type='text']";
	final String ENTER_TITLE_XPATH = "//input[@name='title']";
	final String FEATURE_FOLDER_XPATH = "//div[@class='esriItemPropertiesDlg']//table[contains(@id,'dialog_ItemPropertiesDlg') and contains(@id,'folder')]";
	final String SAVE_FEATURE_BUTTON_XPATH = "//span[contains(@class,'dijitButtonText') and text()='OK']/..";
	final String ENTER_USERNAME_XPATH = "//input[@name='serviceUsername']";
	final String ENTER_PASSWORD_XPATH = "//input[@name='servicePassword']";
	final String WIDGET_TAB_XPATH = "//div[@title='Widget']";
	final String WIDGET_QUERY_XPATH = "(//div[@class='removeable-onscreen-widgets-list']//div[contains(@widgetid,'uniqName_0')])[1]";
	final String WIDGET_BASEMAP_XPATH = "(//div[@class='removeable-onscreen-widgets-list']//div[contains(@widgetid,'uniqName_0')])[3]";
	final String WIDGET_BOOKMARK_XPATH = "(//div[@class='removeable-onscreen-widgets-list']//div[contains(@widgetid,'uniqName_0')])[4]";
	final String WIDGET_LEGEND_XPATH = "(//div[@class='removeable-onscreen-widgets-list']//div[contains(@widgetid,'uniqName_0')])[2]";
	final String LAYER_XPATH = "//div[@data-widget-name='LayerList' and contains(@class,'jimu-float-trailing')]/img";
	final String LAYER_CITY_XPATH = "//div[contains(@class,'checkbox-graphicsLayer') ]/div[1]";
	final String WIDGET_SEARCH_TEXT_XPATH = "//div[@data-dojo-attach-point='officialSection']//input[@class='jimu-input']";
	final String WIDGET_QUERY_ID_XPATH = "//div[@data-widget-name='Query']";
	final String WIDGET_BASEMAP_ID_XPATH = "//div[@data-widget-name='BasemapGallery']";
	final String WIDGET_BOOKMARK_ID_XPATH = "//div[@data-widget-name='Bookmark']";
	final String LEGEND_QUERY_ID_XPATH = "//div[@data-widget-name='Legend']";
	final String CLOSE_BOOKMARK_XPATH = "//h2[text()='Bookmark']/..//div[contains(@aria-label,'Close')]|//div[text()='Bookmark']/..//div[contains(@aria-label,'Close')]";
	final String CLOSE_BOOKMARK_XPATH_10_6_1 = "//h2[text()='Bookmark'] /..//div[contains(@aria-label,'Close')]|//div[text()='Bookmark'] /..//div[contains(@class,'close-btn')]";
	final String CLOSE_LAYER_XPATH = "//h2[contains(text(),'Layer')]/..//div[contains(@aria-label,'Close')]|//div[contains(text(),'Layer')]/..//div[contains(@aria-label,'Close')]";
	final String CLOSE_LAYER_XPATH_10_6_1 = "//h2[contains(text(),'Layer')]/..//div[contains(@aria-label,'Close')]|//div[contains(text(),'Layer')]/..//div[contains(@class,'close-btn')]";
	// final String CLOSE_QUERY_XPATH =
	// "//div[text()='Query']/..//div[@aria-label='Close window']";
	final String CREATE_NEW_FOLDER_11_2_XPATH = "//button[@id='new-folder-button']";
	final String CLOSE_QUERY_XPATH = "//h2[text()='Query'] /..//div[contains(@aria-label,'Close')]|//div[text()='Query'] /..//div[contains(@aria-label,'Close')]";
	final String CLOSE_QUERY_XPATH_10_6_1 = "//h2[text()='Query'] /..//div[contains(@aria-label,'Close')]|//div[text()='Query'] /..//div[contains(@class,'close-btn')]";
	final String WIDGET_SELECT_OK = "//div[contains(@class,'jimu-popup-action-btn') and @title='OK']";
	final String SHOW_BASEMAP_LEGEND_XPATH = "//td[text()='Show basemap legends']//preceding-sibling::td//div[contains(@class,'checkbox')]/div[1]";
	final String NEW_QUERY_XPATH = "//div[@class='tip' and text()='New query']";
	final String SELECT_PORTAL_RADIO_XPATH = "//table[@class='radio-table']//td[@class='portal-td']";
	final String SELECT_MAP_IMAGE_LAYER_XPATH = "(//div[@class='item-name' and contains(@title,'Census')]/../../div[contains(@class,'item-thumbnail')])[1]";
	final String NEXT_SELECT_XPATH = "//div[contains(@class,'jimu-float-trailing')and contains(@class,'next')]";
	// final String CITIES_LAYER_XPATH = "//span[@role='treeitem' and
	// text()='Cities']";
	final String CITIES_LAYER_XPATH = "//span[@role='treeitem' and text()='states']";
	final String CITIES_OK_XPATH = "//div[contains(@id,'jimu_dijit_QueryableServiceChooserFromPortal')]//div[contains(@class,'jimu-float-trailing')and contains(@class,'ok')]";
	final String QUERY_IMAGE_ICON = "//div[@data-widget-name='Query']/img";
	final String LEGEND_IMAGE_ICON = "//div[contains(@class,'jimu-widget-onscreen-icon') and @data-widget-name='Legend']/img";
	final String BASEMAP_IMAGE_ICON = "//div[contains(@class,'jimu-widget-onscreen-icon') and @data-widget-name='BasemapGallery']/img";
	final String BOOKMARK_IMAGE_ICON = "//div[contains(@class,'jimu-widget-onscreen-icon') and @data-widget-name='Bookmark']/img";
	// final String QUERY_RESULTS_XPATH = "(//td[@class='popup-td'])[1]";
	final String QUERY_RESULTS_XPATH = "(//td[contains(@class,'popup-td')])[1]";
	final String QUERY_POPUP_XPATH = "//div[@class='esriPopupMobile']";
	final String BASEMAP_GALLERY_XPATH = "(//img[@class='esriBasemapGalleryThumbnail'])[1]";
	final String ZOOM_IN_XPATH = "//div[contains(@class,'zoom-in')]";
	final String ZOOM_OUT_XPATH = "//div[contains(@class,'zoom-out')]";
	final String ADD_BOOKMARK_XPATH = "//div[@class='editable-btns']/div";
	final String BOOKMARK_NAME_XPATH = "//div[@class='drag-masker']";
	final String SAVE_WEBAPP_XPATH = "//div[contains(@class,'save-enable')]";
	String msg = "";
	String BUILDER_NAME_XPATH;
	String FOLDER_NAME_XPATH;
	final String MAP_MENU_ITEM = "//div[@id='esri-header-menus-desktop']//a[contains(@class,'esri-header-menus-link') and contains(@href,'webmap')]";
	final String HOME_NAVIGATION_XPATH = "//a[contains(@class,'dropdown-btn') and contains(@class,'js-home-dropdown')]";
	final String CONTENT_LINK_XPATH = "//div[@class='dropdown-menu']//a[text()='Content']";
	final String CREATED_FOLDER_XPATH = "//*[@class='folder-list-title']//following-sibling::ul//a[text()='"
			+ FolderName + "']";

	final String SELECT_MAP_IMAGE_LAYER10_9_XPATH = "(//div[@class='item-info']/..//div[contains(@class,'item-thumbnail')])[1]";
	final String CITIES_LAYERLIST_XPATH = "//span[@role='treeitem']";
	final String WIDGET_BASEMAP10_9_XPATH = "(//div[@class='widget-node'])[1]";
	final String SELECT_DATASOURCELAYER_XPATH = "(//div[contains(text(),'Cities')]//..//..//div[contains(@class,'thumbnail')])";
	final String CITIES_XPATH = "//span[@role='treeitem' and text()='Cities']";
	final String DATASOURCE_SEARCHINPUT_XPATH = "//div[contains(@id,'Search')]//input[contains(@class,'input')]";
	final String DATASOURCE_SEARCHICON_XPATH = "(//div[contains(@class,'search-btn')])[1]";
	final String TASKS_XPATH = "//div[text()='Tasks']";
	final String APPLY_TASKS_XPATH = "//div[contains(@class,'query')]//div[text()='Apply']";
	final String QUERY_WARNING_OK_XPATH = "//div[contains(@class,'message')]//div[text()='OK']";

	final String CENSUSMAP_LAYER_TEXT_XPATH = "(//*[contains(@class,'mycontent')]//table[contains(@class,'item')]//*[contains(@class,'item-thumbnail')]//following-sibling::div//div[1])";
	final String SELECT_FIRSTMAP_IMAGE_LAYER10_9_XPATH = "(//div[contains(@class,'mycontent')]//div[contains(@class,'item-thumbnail')])[1]";
	final String SELECT_CENSUSMAP_IMAGE_LAYER10_9_XPATH = "(//div[contains(@class,'mycontent')]//table[contains(@class,'item')]//div[contains(@class,'item-thumbnail')])";

	boolean query = false;
	CommonFunction cfunction = new CommonFunction();

	public void Enterdetailsforwebapp() throws Exception {
		// CommonFunction.waitforpagetoload();
		boolean flag = true;
		msg = "Verify web app dialog box";
		try {

			cfunction.elementexist(DIALOG_TITLE_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Enter builder Title";
		try {
			cfunction.CommonTextBox_Input(ENTER_TITLE_XPATH, WebAppBuilderName);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
			flag = false;
		}
		if (flag) {
			msg = "Enter builder tags";
			try {
				cfunction.CommonTextBox_Input(ENTER_TAGS_XPATH, "TestTag");
				WebElement element1 = driver.findElement(By.xpath(ENTER_TAGS_XPATH));
				element1.sendKeys(Keys.ENTER);
				cfunction.sync(2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			msg = "Click on folder dropdown";
			try {
				if (!projectVersion.equalsIgnoreCase("11.0.0")) {
					cfunction.Commmon_Click("xpath", FEATURE_FOLDER_XPATH);
					CommonFunction.logStatus("PASS", msg);
				}
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
			msg = "Select folder to save";
			try {
				if (!projectVersion.equalsIgnoreCase("11.0.0")) {
					FOLDER_NAME_XPATH = "//table[contains(@id,'dialog_ItemPropertiesDlg') and contains(@id,'folders_menu')]//td[text()='"
							+ FolderName + "']";
					cfunction.waitforelement(FOLDER_NAME_XPATH);
					cfunction.Commmon_Click("xpath", FOLDER_NAME_XPATH);
					cfunction.sync(2);
					CommonFunction.logStatus("PASS", msg);
				}
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
			msg = "Click on done button";
			try {

				cfunction.Commmon_Click("xpath", SAVE_FEATURE_BUTTON_XPATH);
				cfunction.sync(25);
				cfunction.waitforpagetoload();
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Verify webapp builder created";
			try {
				if (!projectVersion.equalsIgnoreCase("11.0.0")) {
					cfunction.waitforelement(WEB_APP_BUILDER_XPATH, 40);
					CommonFunction.logStatus("PASS", msg);
				}
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
		}
	}

	public void Enterdetailsforwebapp11_3() throws Exception {
		// CommonFunction.waitforpagetoload();
		boolean flag = true;
		msg = "Verify web app dialog box";
		try {

			cfunction.elementexist(DIALOG_TITLE11_3_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Enter builder Title";
		try {
			cfunction.Webelement_JSInput(TITTLE11_3_JSPATH, WebAppBuilderName);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
			flag = false;
		}
		if (flag) {
			msg = "Enter builder tags";
			try {

				cfunction.Webelement_JSInput(ENTERTAGE11_3_JSPATH, "TestTag");
				cfunction.sync(2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			msg = "Click on folder dropdown";
			try {
				if (projectVersion.equalsIgnoreCase("11.0.0")) {
					cfunction.Webelement_JSClick(FEATURE_FOLDER_11_JSPATH);
				} else if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))) {
					cfunction.sync(5);
					String FOLDER_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelector('calcite-label calcite-combobox').shadowRoot.querySelector('div > div.wrapper.wrapper--single > div > span.input-wrap.input-wrap--single')";
					cfunction.Webelement_JSClick(FOLDER_JSPATH);
				} else if (projectVersion.equals("11.1.0") || projectVersion.equals("11.2.0")
						|| (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))) {
					String FOLDER_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelector('calcite-label calcite-combobox').shadowRoot.querySelector('div > div.wrapper.wrapper--single > div > span.input-wrap.input-wrap--single')";
					cfunction.Webelement_JSClick(FOLDER_JSPATH);
				} else {
					cfunction.Commmon_Click("xpath", FEATURE_FOLDER_XPATH);
				}
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
			msg = "Select folder to save";
			try {

				if (!(projectVersion.equals("11.0.0") || projectVersion.equals("11.1.0")
						|| projectVersion.equals("11.2.0") || projectVersion.equals("11.3.0")
						|| (projectVersion.equalsIgnoreCase("11.4.0"))
						|| (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0")))) {
					FOLDER_NAME_XPATH = "//table[contains(@id,'dialog_ItemPropertiesDlg') and contains(@id,'folders_menu')]//td[text()='"
							+ FolderName + "']";
					cfunction.sync(10);
					cfunction.Commmon_Click("xpath", FOLDER_NAME_XPATH);
					cfunction.sync(2);

				} else if ((projectVersion.equals("11.3.0")) || (projectVersion.equalsIgnoreCase("11.4.0"))) {
					final String FOLDERITEMLIST11_3_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelector('calcite-combobox > calcite-combobox-item[text-label="
							+ FolderName + "]')";
					WebElement ele = cfunction.Webelement_JSPath(FOLDERITEMLIST11_3_JSPATH);
					if (ele == null) {
						cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH);
					} else if (ele.getText().equalsIgnoreCase(FolderName)) {
						// cfunction.clickJS(ele);
						cfunction.ScrollToWebElement(FOLDERITEMLIST11_3_JSPATH);
						cfunction.clickusingActions(FOLDERITEMLIST11_3_JSPATH);

					}

				} else if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))) {
					final String FOLDERITEMLIST11_5_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelectorAll('calcite-combobox > calcite-combobox-item')";

					List<WebElement> list1 = cfunction.WebelementList_JSPath(FOLDERITEMLIST11_5_JSPATH);
					if (list1.size() > 0) {
						System.out.println(list1.size());

						for (int i = list1.size(); i>0 ; i--) {
							cfunction.ScrollToWebElement(String.format(FOLDERITEM11_5_JSPATH, (i )));
							String listName = cfunction.Webelement_JSPath(String.format(FOLDERITEM11_5_JSPATH, (i )))
									.getText();

							if (listName.contains(FolderName)) {

								cfunction.clickusingActions(String.format(FOLDERITEM11_5_JSPATH, (i )));

								CommonFunction.logStatus("PASS", msg);
								break;
							}
						}
					}
				}

			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			msg = "Click on done button";
			try {

				cfunction.CommmonJS_Click1("//calcite-button[text()='Save']");
				cfunction.sync(25);
				cfunction.waitforpagetoload();
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Verify webapp builder created";
			try {

				cfunction.waitforelement(WEB_APP_BUILDER_XPATH, 40);
				CommonFunction.logStatus("PASS", msg);

			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
		}
	}

	public void createQuery() throws Exception {
		boolean flag = true;

		// cfunction.waitforinvisibilityofelement("div[@id='app-loading']",30);
		// cfunction.waitforinvisibilityofelement("div[@class='jimu-agol-loading']",30);
		cfunction.waitforinvisibilityofelement("div[contains(@class,'jimu-loading-shelter')]", 40);
		msg = "Click on widget tab";
		try {
			cfunction.waitforelement(WIDGET_TAB_XPATH, 20);
			cfunction.Commmon_Click("xpath", WIDGET_TAB_XPATH);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				cfunction.Commmon_Click("xpath", WIDGET_TAB_XPATH);
				cfunction.sync(2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e1);
			}
		}
		msg = "Click on widget icon";
		try {
			cfunction.ScrollToElement(WIDGET_QUERY_XPATH);
			cfunction.Commmon_Click("xpath", WIDGET_QUERY_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Wait for widget search page open";
		try {
			cfunction.sync(2);
			cfunction.waitforelement(WIDGET_SEARCH_TEXT_XPATH, 20);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Search for Query widget";
		try {
			cfunction.CommonTextBox_Input(WIDGET_SEARCH_TEXT_XPATH, "Query");
			cfunction.sync(2);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on query widget";
		try {

			cfunction.Commmon_Click("xpath", WIDGET_QUERY_ID_XPATH);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click OK";
		try {
			cfunction.Commmon_Click("xpath", WIDGET_SELECT_OK);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Wait for  New Query";
		try {
			cfunction.waitforelement(NEW_QUERY_XPATH, 20);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
			flag = false;
		}
		if (flag) {
			msg = "Click on New Query";
			try {
				cfunction.Commmon_Click("xpath", NEW_QUERY_XPATH);
				cfunction.sync(2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Select portal radion button";
			try {
				cfunction.Commmon_Click("xpath", SELECT_PORTAL_RADIO_XPATH);
				cfunction.sync(5);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Wait for map layer";
			try {
				cfunction.waitforelement(SELECT_MAP_IMAGE_LAYER_XPATH, 20);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				try {
					cfunction.waitforelement(SELECT_MAP_IMAGE_LAYER10_9_XPATH, 30);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e1) {
					e1.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e1);

				}
			}
			msg = "Select map layer";
			/*
			 * try {
			 * 
			 * if(projectVersion.equalsIgnoreCase("10.9.1")) {
			 * cfunction.CommonTextBox_Input(DATASOURCE_SEARCHINPUT_XPATH, "Sample");
			 * cfunction.sync(3); cfunction.Commmon_Click("xpath",
			 * DATASOURCE_SEARCHICON_XPATH); cfunction.sync(2);
			 * cfunction.ScrollToElement(SELECT_DATASOURCELAYER_XPATH+"[3]");
			 * cfunction.CommmonJS_Click(SELECT_DATASOURCELAYER_XPATH+"[3]"); } else {
			 * if(cfunction.elementexist(SELECT_DATASOURCELAYER_XPATH)){
			 * cfunction.Commmon_Click("xpath", SELECT_DATASOURCELAYER_XPATH); }else {
			 * cfunction.Commmon_Click("xpath", SELECT_MAP_IMAGE_LAYER_XPATH);
			 * }}cfunction.sync(2); CommonFunction.logStatus("PASS", msg); } catch
			 * (Exception e) {
			 * 
			 * e.printStackTrace(); CommonFunction.logStatusWithException("FAIL", msg, e);
			 * 
			 * 
			 * }
			 */
			try {
				cfunction.Commmon_Click("xpath", SELECT_MAP_IMAGE_LAYER_XPATH);
				cfunction.sync(5);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				try {
					cfunction.selectCensusmapfromList(CENSUSMAP_LAYER_TEXT_XPATH,
							SELECT_CENSUSMAP_IMAGE_LAYER10_9_XPATH);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e1) {
					try {
						// System.out.println("In 2nd catch block");
						cfunction.Commmon_Click("xpath", SELECT_FIRSTMAP_IMAGE_LAYER10_9_XPATH);
						CommonFunction.logStatus("WARNING",
								"Expected Map layer 'Census' not visible hence slected 1st map from list");
					} catch (Exception e2) {
						e2.printStackTrace();
						CommonFunction.logStatus("FAIL", msg + e2);

					}
				}
			}

			msg = "Click Next";
			try {
				cfunction.Commmon_Click("xpath", NEXT_SELECT_XPATH);
				cfunction.sync(2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Select Cities";
			try {
				/*
				 * if
				 * (projectVersion.equalsIgnoreCase("11.0.0")||projectVersion.equalsIgnoreCase(
				 * "11.1.0")) {
				 * 
				 * if(cfunction.elementIsDisplayed(CITIES_XPATH)) {
				 * cfunction.Commmon_Click("xpath", CITIES_XPATH); }else
				 * cfunction.selectlastCity(CITIES_LAYERLIST_XPATH); } else
				 * cfunction.Commmon_Click("xpath", CITIES_LAYER_XPATH); cfunction.sync(2);
				 * CommonFunction.logStatus("PASS", msg);
				 */
				if (projectVersion.equalsIgnoreCase("11.1.0")) {
					cfunction.selectlastCity(CITIES_LAYERLIST_XPATH);
				} else
					cfunction.Commmon_Click("xpath", CITIES_LAYER_XPATH);
				cfunction.sync(4);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				try {
					cfunction.selectlastCity(CITIES_LAYERLIST_XPATH);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e1) {
					e1.printStackTrace();
					CommonFunction.logStatusWithException("FAIL", msg, e1);

				}
			}
			msg = "Click Ok";
			try {
				cfunction.Commmon_Click("xpath", CITIES_OK_XPATH);
				cfunction.sync(2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}

			msg = "Click Ok";
			try {
				cfunction.Commmon_Click("xpath", WIDGET_SELECT_OK);
				cfunction.sync(2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
				driver.navigate().refresh();

			}
		}
	}

	public void VerifyQuery() throws Exception {

		// CommonFunction.waitforpagetoload();
		msg = "Wait for Query icon appeared";
		try {
			cfunction.switchtoqueryframe();
			cfunction.waitforelement(QUERY_IMAGE_ICON, 20);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on query icon";
		try {
			cfunction.switchtoqueryframe();
			cfunction.sync(5);
			cfunction.Commmon_Click("xpath", QUERY_IMAGE_ICON);
			cfunction.sync(5);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		// Intermediate Steps

		msg = "Check Query Failed warning";
		try {
			cfunction.sync(60);
			if (cfunction.elementexist(QUERY_WARNING_OK_XPATH)) {
				query = true;
				// System.out.println("In the query failed");
				cfunction.Commmon_Click("xpath", QUERY_WARNING_OK_XPATH);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		if (query) {
			msg = "Click on Tasks tab";
			try {
				cfunction.sync(2);
				cfunction.Commmon_Click("xpath", TASKS_XPATH);
				cfunction.sync(5);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}

			msg = "Click on APPLY button";
			try {
				cfunction.Commmon_Click("xpath", APPLY_TASKS_XPATH);

			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
		msg = "Wait for Query results appeared";
		try {
			cfunction.waitforelement(QUERY_RESULTS_XPATH, 20);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		/*
		 * msg = "Click on query results"; try { cfunction.Commmon_Click("xpath",
		 * QUERY_RESULTS_XPATH); CommonFunction.logStatus("PASS", msg); } catch
		 * (Exception e) { e.printStackTrace(); CommonFunction.logStatus("FAIL", msg+e);
		 * 
		 * } msg = "Wait for result popup appeared"; try {
		 * cfunction.waitforelement(QUERY_POPUP_XPATH, 20);
		 * CommonFunction.logStatus("PASS", msg); } catch (Exception e) {
		 * e.printStackTrace(); CommonFunction.logStatus("FAIL", msg+e);
		 * 
		 * }
		 */
		msg = "Close query";
		try {
			if (projectVersion.equalsIgnoreCase("10.6.1")) {
				cfunction.Commmon_Click("xpath", CLOSE_QUERY_XPATH_10_6_1);
			} else {

				cfunction.Commmon_Click("xpath", CLOSE_QUERY_XPATH);
			}
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void createLegend() throws Exception {
		boolean flag = true;
		// CommonFunction.waitforpagetoload();
		msg = "Click on Widget to create legend";
		try {
			cfunction.switchtodefaultContent();
			cfunction.Commmon_Click("xpath", WIDGET_LEGEND_XPATH);
			cfunction.sync(2);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			try {
				cfunction.Commmon_Click("xpath", WIDGET_TAB_XPATH);
				cfunction.sync(5);
				cfunction.switchtodefaultContent();
				cfunction.Commmon_Click("xpath", WIDGET_LEGEND_XPATH);
			} catch (Exception e1) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
		msg = "Wait for widget search page";
		try {
			cfunction.waitforelement(WIDGET_SEARCH_TEXT_XPATH, 20);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Search for Legend widget";
		try {
			cfunction.CommonTextBox_Input(WIDGET_SEARCH_TEXT_XPATH, "Legend");
			cfunction.sync(2);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on legend widget";
		try {
			cfunction.Commmon_Click("xpath", LEGEND_QUERY_ID_XPATH);
			cfunction.sync(2);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click Ok";
		try {
			cfunction.Commmon_Click("xpath", WIDGET_SELECT_OK);
			cfunction.sync(2);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		if (!projectVersion.equalsIgnoreCase("10.6.1")) {

			msg = "Wait for basemap radiobutton";
			try {
				cfunction.waitforelement(SHOW_BASEMAP_LEGEND_XPATH, 20);

				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Select basemap radio button";
			try {
				cfunction.Commmon_Click("xpath", SHOW_BASEMAP_LEGEND_XPATH);
				cfunction.sync(2);

				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
		msg = "Click ok";
		try {
			cfunction.Commmon_Click("xpath", WIDGET_SELECT_OK);
			cfunction.sync(2);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify legend created";
		try {
			cfunction.switchtoqueryframe();
			cfunction.waitforelement(LEGEND_IMAGE_ICON, 20);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void createLayer() throws Exception {

		// CommonFunction.waitforpagetoload();
		msg = "Click on layer";
		try {
			cfunction.switchtoqueryframe();
			cfunction.Commmon_Click("xpath", LAYER_XPATH);
			cfunction.sync(2);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Wait for layer checkbox";
		try {
			cfunction.waitforelement(LAYER_CITY_XPATH, 20);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Select layer checkbox";
		try {
			cfunction.Commmon_Click("xpath", LAYER_CITY_XPATH);
			cfunction.sync(2);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Close layer window";
		try {
			cfunction.switchtoqueryframe();
			if (projectVersion.equalsIgnoreCase("10.6.1")) {
				cfunction.Commmon_Click("xpath", CLOSE_LAYER_XPATH_10_6_1);
			} else {

				cfunction.Commmon_Click("xpath", CLOSE_LAYER_XPATH);
			}

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void createBasemap() throws Exception {
		boolean flag = true;
		// CommonFunction.waitforpagetoload();
		msg = "Click on basemap widget";
		try {
			cfunction.switchtodefaultContent();

			cfunction.Commmon_Click("xpath", WIDGET_BASEMAP_XPATH);
			cfunction.sync(2);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Wait for widget serach page";
		try {
			cfunction.waitforelement(WIDGET_SEARCH_TEXT_XPATH, 20);
			msg = "Click on widget tab";

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Search for basemap";
		try {
			cfunction.CommonTextBox_Input(WIDGET_SEARCH_TEXT_XPATH, "Basemap");
			cfunction.sync(2);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Select basemap widget";
		try {
			cfunction.Commmon_Click("xpath", WIDGET_BASEMAP_ID_XPATH);
			cfunction.sync(3);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			try {
				cfunction.Commmon_Click("xpath", WIDGET_BASEMAP10_9_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}

		msg = "Click Ok";
		try {
			cfunction.Commmon_Click("xpath", WIDGET_SELECT_OK);
			cfunction.sync(3);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click OK";
		try {
			cfunction.Commmon_Click("xpath", WIDGET_SELECT_OK);
			cfunction.sync(3);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Wait for basmap icon";
		try {
			cfunction.switchtoqueryframe();
			cfunction.waitforelement(BASEMAP_IMAGE_ICON, 20);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
			flag = false;
		}
		if (flag) {
			msg = "Click on basmap icon";
			try {
				cfunction.Commmon_Click("xpath", BASEMAP_IMAGE_ICON);
				cfunction.sync(3);

				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Wait for Basemap gallery";
			try {
				cfunction.waitforelement(BASEMAP_GALLERY_XPATH, 20);
				cfunction.sync(2);

				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Select any Basemap";
			try {
				cfunction.Commmon_Click("xpath", BASEMAP_GALLERY_XPATH);
				cfunction.sync(2);

				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
	}

	public void createbookmark() throws Exception {
		boolean flag = true;
		// CommonFunction.waitforpagetoload();
		msg = "Click on widget bookmark";
		try {
			cfunction.switchtodefaultContent();
			cfunction.Commmon_Click("xpath", WIDGET_BOOKMARK_XPATH);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Wait widget search page opens";
		try {
			cfunction.waitforelement(WIDGET_SEARCH_TEXT_XPATH, 20);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Search for bookamrk widget";
		try {
			cfunction.CommonTextBox_Input(WIDGET_SEARCH_TEXT_XPATH, "Bookmark");
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on bookmark widget";
		try {
			cfunction.Commmon_Click("xpath", WIDGET_BOOKMARK_ID_XPATH);
			cfunction.sync(3);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click ok";
		try {
			cfunction.Commmon_Click("xpath", WIDGET_SELECT_OK);
			Thread.sleep(3000);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click Ok";
		try {
			cfunction.Commmon_Click("xpath", WIDGET_SELECT_OK);
			Thread.sleep(3000);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on Zoom first time";
		try {
			cfunction.switchtoqueryframe();
			cfunction.Commmon_Click("xpath", ZOOM_IN_XPATH);
			Thread.sleep(3000);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		} /*
			 * msg = "Click on Zoom second time"; try { cfunction.Commmon_Click("xpath",
			 * ZOOM_IN_XPATH); Thread.sleep(3000); CommonFunction.logStatus("PASS", msg); }
			 * catch (Exception e) { e.printStackTrace();
			 * CommonFunction.logStatusWithException("FAIL", msg, e);
			 * 
			 * }
			 */
		msg = "Click on Bookmark image";
		try {
			cfunction.waitforelement(BOOKMARK_IMAGE_ICON, 20);
			cfunction.Commmon_Click("xpath", BOOKMARK_IMAGE_ICON);
			Thread.sleep(3000);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click on Add button";
		try {
			cfunction.Commmon_Click("xpath", ADD_BOOKMARK_XPATH);
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
			flag = false;
		}
		if (flag) {
			msg = "Add bookmark name";
			try {
				cfunction.CommonTextBox_Inputrobo();
				Thread.sleep(2000);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Close Bookmark window";
			try {
				cfunction.switchtoqueryframe();
				if (projectVersion.equalsIgnoreCase("10.6.1")) {
					cfunction.Commmon_Click("xpath", CLOSE_BOOKMARK_XPATH_10_6_1);
				} else {

					cfunction.Commmon_Click("xpath", CLOSE_BOOKMARK_XPATH);
				}

				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on zoom out";
			try {
				cfunction.Commmon_Click("xpath", ZOOM_OUT_XPATH);
				Thread.sleep(3);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			} /*
				 * msg = "Click on Zoom out"; try { cfunction.Commmon_Click("xpath",
				 * ZOOM_OUT_XPATH); cfunction.sync(3); CommonFunction.logStatus("PASS", msg); }
				 * catch (Exception e) { e.printStackTrace();
				 * CommonFunction.logStatusWithException("FAIL", msg, e);
				 * 
				 * }
				 */
			// cfunction.setElementAttribute(BOOKMARK_NAME_XPATH,"title","");
			msg = "Click on bookmark widget";
			try {
				cfunction.Commmon_Click("xpath", BOOKMARK_IMAGE_ICON);
				Thread.sleep(3);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Click on bookmark name";
			try {

				cfunction.Commmon_Click("xpath", BOOKMARK_NAME_XPATH);
				Thread.sleep(3);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Close bookmark window";

			try {
				cfunction.switchtoqueryframe();
				if (projectVersion.equalsIgnoreCase("10.6.1")) {
					cfunction.Commmon_Click("xpath", CLOSE_BOOKMARK_XPATH_10_6_1);
				} else {

					cfunction.Commmon_Click("xpath", CLOSE_BOOKMARK_XPATH);
				}

				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}
		}
	}

	public void saveWebapp() throws Exception {
		msg = "Save webapp builder";
		try {
			cfunction.switchtodefaultContent();
			cfunction.Commmon_Click("xpath", SAVE_WEBAPP_XPATH);
			cfunction.sync(2);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void navigateToContentPage() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Click on home page link";
		try {
			cfunction.CommmonJS_Click(HOME_NAVIGATION_XPATH);
			cfunction.sync(10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Navigate to content page";
		try {
			cfunction.CommmonJS_Click(CONTENT_LINK_XPATH);
			cfunction.sync(10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void verifywebappbuilder() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Click on the " +FolderName+ " folder";
		try {
			List<WebElement> list = cfunction.WebelementList_JSPath(FOLDERLIST11_3_JSPATH);
			if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0")|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0")))) {
				for (int i = list.size(); i>0 ; i--) {
					cfunction.ScrollToWebElement(String.format(FOLDERNAMELIST11_5_JSPATH, (i )));
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.clickusingActions(String.format(FOLDERNAMELIST11_5_JSPATH, (i )));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			} else if (projectVersion.equalsIgnoreCase("11.1.0") || projectVersion.equalsIgnoreCase("11.2.0")
					|| projectVersion.equalsIgnoreCase("11.3.0") || projectVersion.equalsIgnoreCase("11.4.0")) {
				for (int i = list.size(); i>0 ; i--) {
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAME11_3_JSPATH, (i)));
					// System.out.println(ele.getText());
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.ScrollToWebElement(String.format(FOLDERNAME11_3_JSPATH, (i )));
						cfunction.clickusingActions(String.format(FOLDERNAME11_3_JSPATH, (i)));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			} else {
				cfunction.Commmon_Click("xpath", CREATED_FOLDER_XPATH);
				cfunction.sync(5);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Verify " + WebAppBuilderName + " web app builder created";
		try {
			cfunction.sync(10);
			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))) {
				final String FOLDERFEATURE_JSPATH = "document.querySelector('div> arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row').shadowRoot.querySelector('div>a')";
				WebElement ele = cfunction.Webelement_JSPath(FOLDERFEATURE_JSPATH);
				// System.out.println(ele.getText());
				if (ele.getText().equalsIgnoreCase(WebAppBuilderName)) {
					CommonFunction.logStatus("PASS", msg);
					featurecreated = true;
				} else {
					CommonFunction.logStatus("FAIL", msg);
					featurecreated = false;
					flagfeaturelayer = false;
				}
			} else {
				BUILDER_NAME_XPATH = "//a[text()='" + WebAppBuilderName + "']";
				cfunction.waitforelement(BUILDER_NAME_XPATH, 60);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			flagwebappbuilder = false;
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

	}
}

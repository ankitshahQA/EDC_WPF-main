package com.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;

public class MapPage extends TestBase {
	HomePage hp = new HomePage();
	// XPath code
	String FOLDER_NAME_XPATH;
	String FEATURE_NAME_XPATH;
	final String BASE_MAP_XPATH = "//span[@id='webmap-basemap']";
	final String HOME_BUTTON_XPATH = "//div[@class='homeContainer']/div[@title()='Default extent']";
	final String DETAILS_XPATH = "//span[@id='webmap-details_label']";
	final String DETAILS_PANE_XPATH = "//span[@widgetid='webmap-details' and contains(@class,'dijitChecked')]";
	final String DETAILS_BUTTON_XPATH = "//span[@id='webmap-details']";
	final String CONTENT_BUTTON_XPATH = "//span[@id='webmap-details-about-content']";
	final String CONTENT_STREET_LAYER_NAME_XPATH = "//div[@id='toc-main']//div[contains(@class,'toc_baseLayer')]//span[contains(@class,'toc_layerName') and contains(text(),'Streets')]";
	final String OPEN_CLASSICMAP_XPATH = "(//*[text()='Open in Map Viewer Classic'])[2]";
	String CONTENT_WORLDSTREET_LAYER_NAME_XPATH;
	String LEGEND_TABLE_XPATH;
	String MAP_NAME_XPATH;
	String mapfeature = "Feature";
	String SHOW_LEGEND_XPATH;
	String SHOW_TABLE_XPATH;
	String HIDE_LEGEND_XPATH;
	String HIDE_TABLE_XPATH;
	String CONTENT_SAMPLESMARTCITIES_LAYER_NAME_XPATH;
	final String TABLE_XPATH = "//div[@class='esri-feature-table-grid']";
	final String SAVE_DROPDOWN_BUTTON_XPATH = "//span[@id='webmap-save']";
	final String SAVE_BUTTON_11_5_XPATH = "//calcite-action[@id='file']";
	final String SAVEAS_BUTTON_11_5_XPATH = "//calcite-action[@id='savemapas']";
	final String SAVE_OPTION_XPATH = "//td[@id='webmap-save-save_text']";
	final String MAP_TITLE_BUTTON_XPATH = "//input[@id='save-webmap-title']";
	final String MAP_TAG_XPATH = "//div[@id='save-webmap-tags']//input";
	final String MAP_SUMMARY_XPATH = "//input[@id='save-webmap-summary']";
	final String SAVE_MAP_BUTTON_XPATH = "//span[@id='save-webmap-ok']/..";
	final String ADD_BUTTON_XPATH = "//span[@id='webmap-add']";
	final String SEARCH_LAYER_BUTTON_XPATH = "//td[@id='webmap-search-layers_text']";
	final String BROWSE_BY_LIVING_ATLAS_BUTTON_XPATH = "//td[@id='webmap-add-esri-maplayers_text']";
	final String SEARCH_RESULTS_XPATH = "//div[@class='ib-results__item-list']";
	final String MY_CONTENT_DROPDOWN = "//h3[@id='section-dropdown-toggle' and contains(text(),'My')and contains(text(),'Content')]/button";
	final String MY_ORGANIZATION_DROPDOWN = "//button[@id='myOrganization-option']";
	final String MY_CONTENT11_3_DROPDOWN = "//h3[@id='section-dropdown-toggle' and contains(text(),'My')and contains(text(),'Content')]";
	final String FOLDERNAME11_3_JSPATH = "document.querySelector(\"calcite-list > calcite-list-item:nth-child(%s)\").shadowRoot.querySelector(\"div > tr\")";
	final String FOLDERLIST11_3_JSPATH = "document.querySelectorAll(\"arcgis-item-browser-filter-folder > arcgis-browser-filter > div > calcite-list > calcite-list-item\")";
	final String FEATURE_FOLDER_11_JSPATH = "document.querySelector(\"arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-folder-picker\").shadowRoot.querySelector(\"calcite-label > div > calcite-combobox\").shadowRoot.querySelector(\"div.wrapper.wrapper--single > div > span[class*='input-wrap']\")";
	final String FEATURE_FOLDER_11_1_JSPATH = "document.querySelector(\"arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-folder-picker\").shadowRoot.querySelector(\"calcite-label > div > div > calcite-combobox\").shadowRoot.querySelector(\"div.wrapper > div\")";
	final String ALL_MY_CONTENT_11_3_JSPATH = "document.querySelector(\"calcite-list-item:nth-child(1)\").shadowRoot.querySelector(\"div > tr > td > div.content\")";
	final String ALL_MY_CONTENT_11_5_JSPATH = "document.querySelector(\"arcgis-item-browser-filter-folder > arcgis-browser-filter > div > calcite-list > calcite-list-item:nth-child(1)\").shadowRoot.querySelector(\"div > div.wrapper > div.row.container.container--hover.container--border.container--border-selected\")";
	final String FOLDERITEM11_5_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelector('calcite-combobox > calcite-combobox-item:nth-child(%s)')";
	final String FEATURE_FOLDER_XPATH = "//div[@class='esriItemPropertiesDlg']//table[contains(@id,'dialog_ItemPropertiesDlg') and contains(@id,'folder')]";
	final String MAP_FOLDER_01_9_1_XPATH="//*[@id='save-webmap-folder']";
	final String TITLE_INPUT_JSPATH = "document.querySelector('arcgis-title-input').shadowRoot.querySelector('#item-properties-title').shadowRoot.querySelector('div.element-wrapper > input')";
	final String TITLE_TAGNAME_JSPATH = "document.querySelector(' arcgis-tags-picker').shadowRoot.querySelector(\"calcite-label > calcite-combobox\").shadowRoot.querySelector('input')";
	final String MAP_SUMMARY_11_5_JSPATH = "document.querySelector('arcgis-summary-input').shadowRoot.querySelector('#summary-input').shadowRoot.querySelector('textarea')";
	final String MAP_FOLDER_11_5_JSPATH = "document.querySelector(' arcgis-folder-picker').shadowRoot.querySelector('calcite-label > div> div> calcite-combobox')";
	final String FOLDERITEMLIST11_5_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelectorAll('calcite-combobox > calcite-combobox-item')";

	final String MAP_FOLDER_DROPDOWNBTN_XPATH = "//div[@id=\"widget_save-webmap-folder\"]//div[@data-dojo-attach-point=\"_buttonNode\"]";
	final String ADDBTN_11_5_XPATH = "//calcite-action[@id='adddata']";
	final String ADD_LAYER_URL_BUTTON_XPATH = "//calcite-action[@id='addfromurl']";
	final String URL_INPUTBOX_JSPATH = "document.querySelector('arcgis-new-item-pages-url-type').shadowRoot.querySelector('arcgis-new-item-url').shadowRoot.querySelector('#item-properties-url').shadowRoot.querySelector(' div.element-wrapper > input')";
	final String TYPE_DROPDOWN_JSPATH = "document.querySelector('arcgis-new-item-pages-url-type').shadowRoot.querySelector('arcgis-select').shadowRoot.querySelector('#AGS')";
	final String ADDMAP_XPATH = "//calcite-button[@data-id=\"nextButton\" and text()='Add to map']";

	// final String SAMPLE_WORLD_CITIES_XPATH =
	// "//div[@class='card-mc__title-container']//button[text()='SampleWorldCities']";
	final String SAMPLE_WORLD_CITIES_XPATH = "(//div[@class='card-mc__title-container'])[1]";
	String SAMPLE_WORLD_CITIES_ADD_BUTTON;
	// final String WORLD_STREET_MAP_XPATH =
	// "//div[@class='card-mc__title-container']//button[text()='World Street
	// Map']";
	final String WORLD_STREET_MAP_XPATH = "//div[@class='card-mc__title-container']";
	String WORLD_STREET_MAP_ADD_BUTTON;
	final String LIVING_ATLAS_LAYER_BUTTON_XPATH = "//td[@id='webmap-add-esri-maplayers_text']";
	final String BASE_MAP_GALLERY = "//div[@id='basemap-gallery_Gallery']";
	final String GALLERY_STREET_IMAGE = "//div[@id='basemap-gallery_Gallery']//div[@class='galleryLabelContainer']//span[text()='Streets']/../../a";
	final String HOME_NAVIGATION_XPATH = "//a[@id='home-navigation-dropdown-link']";
	final String HOME_LINK_XPATH = "//div[@class='dropdown-menu']//a[text()='Home']";
	final String CONTENT_LINK_XPATH = "//div[@class='dropdown-menu']//a[text()='Content']";
	final String CREATED_FOLDER_XPATH = "//*[@class='folder-list-title']//following-sibling::ul//a[text()='"
			+ FolderName + "']";

	String msg = "";
	CommonFunction cfunction = new CommonFunction();

	public void verifymappage() throws InterruptedException {
		// CommonFunction.waitforpagetoload();

		try {
			cfunction.waitforelement(BASE_MAP_XPATH);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void navigateToHomePage() throws InterruptedException {
		// CommonFunction.waitforpagetoload();
		msg = "Navigate to home page from map page";
		try {
			cfunction.CommmonJS_Click(HOME_NAVIGATION_XPATH);
			cfunction.CommmonJS_Click(HOME_LINK_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg + e);

		}
	}

	public void navigateToContentPage() throws InterruptedException {
		// CommonFunction.waitforpagetoload();
		msg = "Click on home on map page";
		try {
			driver.findElement(By.xpath("//div[@id='home-navigation-dropdown']")).click();
			// cfunction.CommmonJS_Click( HOME_NAVIGATION_XPATH);
			cfunction.sync(10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			// CommonFunction.logStatus("FAIL", msg+e);

		}
		msg = "Click on content";
		try {
			cfunction.CommmonJS_Click(CONTENT_LINK_XPATH);
			CommonFunction.logStatus("PASS", msg);
			cfunction.sync(15);
			cfunction.waitforpagetoload();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				cfunction.CommmonJS_Click(HOME_NAVIGATION_XPATH);
				cfunction.CommmonJS_Click(CONTENT_LINK_XPATH);
				cfunction.sync(2);
				cfunction.waitforpagetoload();
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				e1.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e1);

			}

		}
	}

	public void verifyBaseMapImage() {

		msg = "Verify BaseMap image";
		cfunction.waitforpagetoload();
		cfunction.sync(20);

		try {
			String filename = null;
			boolean find = true;
			filename = CommonFunction.findfilename("Map_landing");
			// find = AshortHelp.imagedifference("//div[@id='map']", filename);
			CommonFunction.logStatus("PASS", msg);
			// System.out.println("image found" + find);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg + e);

		}

	}

	public void verifydetailpanel() {

		// intermediate Step
		msg = "Click Open in Classic Map Viewer";
		try {
			cfunction.sync(10);
			if (driver.findElements(By.xpath(OPEN_CLASSICMAP_XPATH)).size() > 0) {
				cfunction.Commmon_Click("xpath", OPEN_CLASSICMAP_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		msg = "Verify Details panel displays";
		try {
			cfunction.waitforelement(DETAILS_PANE_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
		}
		msg = "Click on Detail button";
		try {
			cfunction.CommmonJS_Click(DETAILS_BUTTON_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
		}
		msg = "Verify Details Panel hide";
		try {
			cfunction.waitforinvisibilityofelement(DETAILS_PANE_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();

		}

		msg = "Click on Detail button";
		try {
			cfunction.CommmonJS_Click(DETAILS_BUTTON_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
		}

	}

	public void verifyBaseMapRendering() {
		msg = "Click on BaseMap";
		try {
			cfunction.CommmonJS_Click(BASE_MAP_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
		}
		cfunction.sync(2);
		
		msg = "Verify Basemap gallery displays";
		try {
			cfunction.waitforelement(BASE_MAP_GALLERY);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
		}
		
		msg = "Click on Street Basemap to render";
		try {
			cfunction.CommmonJS_Click(GALLERY_STREET_IMAGE);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();

		}
		/*
		 * msg = "Verify BaseMap street rendering image"; cfunction.waitforpagetoload();
		 * cfunction.sync(20);
		 * 
		 * try { String filename = null; boolean find = true; filename =
		 * CommonFunction.findfilename("Map_Street"); find =
		 * AshortHelp.imagedifference("//div[@id='map']", filename);
		 * CommonFunction.logStatus("PASS", msg);
		 * //System.out.println("BaseMap image after changing found" + find);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); CommonFunction.logStatus("FAIL",
		 * msg+e);
		 * 
		 * }
		 */

	}

	public void ClickAdd() {

		cfunction.Commmon_Click("xpath", ADD_BUTTON_XPATH);

	}

	public void SearchForLayers() {
		boolean flag = true;
		msg = "Click on Add button";
		try {
			ClickAdd();
			//cfunction.sync(3);
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg + e);

		}
		cfunction.sync(2);
		msg = "Click on Search for layers button";
		try {
			cfunction.Commmon_Click("xpath", SEARCH_LAYER_BUTTON_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
			flag = false;
		}
		
		cfunction.sync(2);
		
		if (flag) {
			if (driver.findElements(By.xpath(SAMPLE_WORLD_CITIES_XPATH)).isEmpty()) {
				msg = "Click on My content dropdown";
				try {
					if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
							|| (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))) {
						cfunction.CommmonJS_Click(MY_CONTENT11_3_DROPDOWN);
						CommonFunction.logStatus("PASS", msg);
					} else {
						cfunction.CommmonJS_Click(MY_CONTENT_DROPDOWN);
						CommonFunction.logStatus("PASS", msg);
					}

				} catch (Exception e) {
					CommonFunction.logStatus("FAIL", msg + e);
					e.printStackTrace();
				}
				
				cfunction.sync(2);
				
				msg = "Select My Organization";
				try {
					cfunction.CommmonJS_Click(MY_ORGANIZATION_DROPDOWN);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e) {
					CommonFunction.logStatus("FAIL", msg + e);
					e.printStackTrace();
				}
				
				cfunction.sync(3);
				

			}

			msg = "Add feature layer";
			if (flagfeaturelayer && featurecreated) {
				try {
					findlastsmartcity();
					cfunction.sync(5);
					if (!(driver.findElements(By.xpath(SAMPLE_WORLD_CITIES_XPATH)).isEmpty())) {
						cfunction.CommmonJS_Click(SAMPLE_WORLD_CITIES_ADD_BUTTON);
						CommonFunction.logStatus("PASS", msg);
					}
				} catch (Exception e) {
					CommonFunction.logStatus("FAIL", msg + e);
					e.printStackTrace();
				}
			} else {
				try {
					if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
							|| (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))) {
						findlastsmartcity();
						if (!(driver.findElements(By.xpath(SAMPLE_WORLD_CITIES_XPATH)).isEmpty())) {
							cfunction.CommmonJS_Click(SAMPLE_WORLD_CITIES_ADD_BUTTON);
							CommonFunction.logStatus("PASS", msg);
						}
					} else {
						findlastsmartcity1();
						cfunction.sync(1);
						if (!(driver.findElements(By.xpath(SAMPLE_WORLD_CITIES_XPATH)).isEmpty())) {
							cfunction.CommmonJS_Click(SAMPLE_WORLD_CITIES_ADD_BUTTON);
							CommonFunction.logStatus("PASS", msg);

						}
					}
				} catch (Exception e) {
					CommonFunction.logStatus("FAIL", msg + e);
					e.printStackTrace();
				}
			}
			cfunction.sync(3);
			
		}
		/*
		 * msg = "Verify Sample World Cities image renders";
		 * cfunction.waitforpagetoload(); cfunction.sync(20);
		 * 
		 * try { String filename = null; boolean find = true; filename =
		 * CommonFunction.findfilename("Map_World_Cities"); find =
		 * AshortHelp.imagedifference("//div[@id='map']", filename);
		 * CommonFunction.logStatus("PASS", msg);
		 * //System.out.println("Sample World Cities image" + find);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); CommonFunction.logStatus("FAIL",
		 * msg+e);
		 * 
		 * }
		 */
	}

	public void findlastsmartcity() {
		List<WebElement> list1 = driver.findElements(By.xpath(SAMPLE_WORLD_CITIES_XPATH));
		int cnt1 = list1.size();
		// SAMPLE_WORLD_CITIES_ADD_BUTTON =
		// "(//div[@class='card-mc__title-container']//button[text()='SampleWorldCities'])["
		// + cnt1 + "]/../../following::div//button[@title='Add']";
		SAMPLE_WORLD_CITIES_ADD_BUTTON = "(//div[@class='card-mc__title-container']//button[contains(text(),'')])["
				+ cnt1 + "]/../../following::div//button[@title='Add']";
		mapfeature = "Feature";
		CONTENT_SAMPLESMARTCITIES_LAYER_NAME_XPATH = "(//div[@id='toc-main']//div[contains(@class,'toc_layer')]//span[contains(@class,'toc_layerName') and contains(text(),'"
				+ mapfeature + "')])[1]";
		LEGEND_TABLE_XPATH = "(//div[@id='toc-main']//div[contains(@class,'toc_toggle_legend') and contains(@id,'"
				+ mapfeature + "')]//table[@class='esriLegendLayer'])[1]";
		SHOW_LEGEND_XPATH = "(//div[contains(@id,'" + mapfeature + "') and contains(@class,'iconShowLegend')])[1]";
		SHOW_TABLE_XPATH = "(//div[contains(@id,'" + mapfeature
				+ "') and contains(@class,'iconShowAttributeTable')])[1]";
		HIDE_LEGEND_XPATH = "//div[contains(@id,'" + mapfeature + "') and contains(@class,'iconHideLegend')]";
		HIDE_TABLE_XPATH = "//div[contains(@id,'" + mapfeature + "') and contains(@class,'iconHideAttributeTable')]";

	}

	public void findlastsmartcity1() {

		SAMPLE_WORLD_CITIES_ADD_BUTTON = "(//section[contains(@class,'card-mc')]//div[@class='card-mc__author-row'] //img[contains(@title,'Feature')])[1]//..//..//..//..//button[@title='Add']";
		mapfeature = driver.findElement(By.xpath(
				"(//section[contains(@class,'card-mc')]//div[@class='card-mc__author-row'] //img[contains(@title,'Feature')])[1]//..//../button"))
				.getText().split(" ")[0].split("_")[0];
		CONTENT_SAMPLESMARTCITIES_LAYER_NAME_XPATH = "(//div[@id='toc-main']//div[contains(@class,'toc_layer')]//span[contains(@class,'toc_layerName') and contains(text(),'"
				+ mapfeature + "')])[1]";
		LEGEND_TABLE_XPATH = "(//div[@id='toc-main']//div[contains(@class,'toc_toggle_legend') and contains(@id,'"
				+ mapfeature + "')]//table[@class='esriLegendLayer'])[1]";
		SHOW_LEGEND_XPATH = "(//div[contains(@id,'" + mapfeature + "') and contains(@class,'iconShowLegend')])[1]";
		SHOW_TABLE_XPATH = "(//div[contains(@id,'" + mapfeature
				+ "') and contains(@class,'iconShowAttributeTable')])[1]";
		HIDE_LEGEND_XPATH = "//div[contains(@id,'" + mapfeature + "') and contains(@class,'iconHideLegend')]";
		HIDE_TABLE_XPATH = "//div[contains(@id,'" + mapfeature + "') and contains(@class,'iconHideAttributeTable')]";
	}

	public void findlastworldstreetmap() {
		cfunction.CommonTextBox_Input("//input[@id='item-browser-search-input']", "children");
		cfunction.PressEnterByAction();
		// cfunction.hoverByAction( ADD_BUTTON_XPATH);
		if (driver
				.findElements(By.xpath("//div[@class='card-mc__title-container']//button[contains(text(),'Children')]"))
				.size() > 0) {

			WORLD_STREET_MAP_ADD_BUTTON = "(//div[@class='card-mc__title-container']//button[contains(text(),'Children')])[1]/../../following::div//button[@title='Add']";
			CONTENT_WORLDSTREET_LAYER_NAME_XPATH = "//div[@id='toc-main']//div[contains(@class,'toc_layer')]//span[contains(@class,'toc_layerName') and contains(text(),'Children')]";

		} else {
			WORLD_STREET_MAP_ADD_BUTTON = "(//div[@class='card-mc__title-container'])[1]//../../following::div//button[@title='Add']";
			String text = driver.findElement(By.xpath("(//div[@class='card-mc__title-container'])[1]//button"))
					.getText().split(" ")[0].split("_")[0];

			CONTENT_WORLDSTREET_LAYER_NAME_XPATH = "//div[@id='toc-main']//div[contains(@class,'toc_layer')]//span[contains(@class,'toc_layerName') and contains(text(),'"
					+ text + "')]";
		}

	}

	public void SearchForLivingAtlas() {
		msg = "Click on Add button ";
		try {
			ClickAdd();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg + e);

		}
		cfunction.sync(3);
		
		msg = "Click on Browse by Living Atlas ";
		try {
			cfunction.Commmon_Click("xpath", LIVING_ATLAS_LAYER_BUTTON_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
		}
		cfunction.sync(3);
		
		if (driver.findElements(By.xpath(WORLD_STREET_MAP_XPATH)).isEmpty()) {
			msg = "No Search results for browse by living Atlas found";
			CommonFunction.logStatus("FAIL", msg);
		} else {
			msg = "Add layer from living atlas ";
			try {
				cfunction.sync(5);
				findlastworldstreetmap();
				cfunction.CommmonJS_Click(WORLD_STREET_MAP_ADD_BUTTON);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatus("FAIL", msg + e);
				e.printStackTrace();
			}
			
			cfunction.sync(5);

			
			/*
			 * msg = "Verify World Street Map image renders"; cfunction.waitforpagetoload();
			 * cfunction.sync(20);
			 * 
			 * try { String filename = null; boolean find = true; filename =
			 * CommonFunction.findfilename("Map_World_Street"); find =
			 * AshortHelp.imagedifference("//div[@id='map']", filename);
			 * CommonFunction.logStatus("PASS", msg);
			 * //System.out.println("World Street Map image" + find);
			 * 
			 * } catch (Exception e) { e.printStackTrace(); CommonFunction.logStatus("FAIL",
			 * msg+e);
			 * 
			 * }
			 */

		}

	}

	public void VerifyLegendTableDisplays() {
		msg = "Click on Content button";
		try {
			cfunction.CommmonJS_Click(DETAILS_BUTTON_XPATH);
			cfunction.waitforpagetoload();
			cfunction.CommmonJS_Click(CONTENT_BUTTON_XPATH);
			cfunction.waitforpagetoload();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
		}
		cfunction.sync(2);
		msg = "Verify Street Layer Added";
		try {
			cfunction.waitforelement(CONTENT_STREET_LAYER_NAME_XPATH, 30);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
		}

		msg = "Verify World Street Layer Added";
		try {
			cfunction.waitforelement(CONTENT_WORLDSTREET_LAYER_NAME_XPATH, 30);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
		}

		cfunction.sync(2);
		if (!(driver.findElements(By.xpath(CONTENT_SAMPLESMARTCITIES_LAYER_NAME_XPATH)).isEmpty())) {
			msg = "Verify feature layer Added";
			try {
				cfunction.waitforelement(CONTENT_SAMPLESMARTCITIES_LAYER_NAME_XPATH, 30);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatus("FAIL", msg + e);
				e.printStackTrace();
			}

			msg = "Hover over feature layer";

			try {
				WebElement element1 = driver.findElement(By.xpath(CONTENT_SAMPLESMARTCITIES_LAYER_NAME_XPATH));
				cfunction.hoverOnElement(element1);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e);

			}

			msg = "Click on show Legend";
			try {

				cfunction.Commmon_Click("xpath", SHOW_LEGEND_XPATH);
				cfunction.sync(2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e);

			}
			msg = "Verify Legend will displays";
			try {

				cfunction.waitforelement(LEGEND_TABLE_XPATH);

			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e);

			}
			cfunction.sync(2);
			msg = "Click on Show Table";
			try {

				cfunction.Commmon_Click("xpath", SHOW_TABLE_XPATH);
				cfunction.sync(2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e);

			}
			msg = "Verify Table will displays";
			try {
				cfunction.waitforelement(TABLE_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e);

			}
			cfunction.sync(2);
			msg = "Click on Hide Legend";
			try {

				cfunction.Commmon_Click("xpath", HIDE_LEGEND_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e);

			}
			msg = "Verify Legend will hide";
			try {

				cfunction.waitforinvisibilityofelement(LEGEND_TABLE_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e);

			}
			cfunction.sync(2);
			msg = "Click on Hide Table";
			try {

				cfunction.Commmon_Click("xpath", HIDE_TABLE_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e);

			}
			msg = "Verify Table will hide";
			try {

				cfunction.waitforinvisibilityofelement(TABLE_XPATH);
				CommonFunction.logStatus("PASS", msg);

			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e);

			}
			cfunction.sync(2);
		}
	}

	public void SaveMap() throws Exception {
		boolean flag = true;
		msg = "Click on save button";
		try {
			cfunction.sync(5);
			cfunction.Commmon_Click("xpath", SAVE_DROPDOWN_BUTTON_XPATH);
			cfunction.Commmon_Click("xpath", SAVE_OPTION_XPATH);
			cfunction.sync(3);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
		}
		msg = "Enter Map Title";
		try {

			cfunction.CommonTextBox_Input(MAP_TITLE_BUTTON_XPATH, MapName);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
			flag = false;
		}
		if (flag) {
			msg = "Enter Map tags";
			try {
				cfunction.CommonTextBox_Input(MAP_TAG_XPATH, "TestTag");
				WebElement element1 = driver.findElement(By.xpath(MAP_TAG_XPATH));
				element1.sendKeys(Keys.ENTER);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatus("FAIL", msg + e);
				e.printStackTrace();
			}
			msg = "Enter Map Summary";
			try {
				cfunction.CommonTextBox_Input(MAP_SUMMARY_XPATH, MapName);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatus("FAIL", msg + e);
				e.printStackTrace();
			}
/*
			cfunction.sync(5);
			msg = "Click on folder dropdown";
			try {
				if (projectVersion.equalsIgnoreCase("11.0.0")) {
					cfunction.Webelement_JSClick(FEATURE_FOLDER_11_JSPATH);
				} else if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))) {
					//cfunction.sync(10);
					cfunction.Commmon_Click("xpath", MAP_FOLDER_DROPDOWNBTN_XPATH);
				} else if (projectVersion.equals("11.1.0") || projectVersion.equals("11.2.0")
						|| (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))||(projectVersion.equalsIgnoreCase("10.9.1"))|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))) {
					cfunction.Commmon_Click("xpath", MAP_FOLDER_DROPDOWNBTN_XPATH);
				} else if(projectVersion.equalsIgnoreCase("10.8.1")){ 
					cfunction.Commmon_Click("xpath", "//*[@id='widget_save-webmap-folder']/div");
				}else {
					cfunction.Commmon_Click("xpath", FEATURE_FOLDER_XPATH);
				}
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
			
			msg = "Select folder to save";
			try {

				if ((projectVersion.equals("11.0.0") || projectVersion.equals("11.1.0")
						|| projectVersion.equals("11.2.0") || projectVersion.equals("11.3.0")
						|| (projectVersion.equalsIgnoreCase("11.4.0"))||(projectVersion.equalsIgnoreCase("10.9.1")))) {
					FOLDER_NAME_XPATH = "//div[@id='save-webmap-folder_popup']//div[text()='" + FolderName + "']";
					// cfunction.sync(5);
					cfunction.ScrollToElement(FOLDER_NAME_XPATH);
					cfunction.Commmon_Click("xpath", FOLDER_NAME_XPATH);
					cfunction.sync(2);
				}
				
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
	*/		msg = "Click on done button";
			try {

				cfunction.CommmonJS_Click(SAVE_MAP_BUTTON_XPATH);
				cfunction.waitforpagetoload();
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e);

			}
			msg = "Verify Map will save";
			try {
				MAP_NAME_XPATH = "//div[@id='webmap-title-text' and text()='" + MapName + "']";
				cfunction.waitforelement(MAP_NAME_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				driver.navigate().refresh();
				cfunction.sync(10);
				CommonFunction.logStatus("FAIL", msg + e);

			}
			cfunction.sync(3);

			
		}
	}

	public void SearchForLayers_11_5() {
		boolean flag = true;
		String SERVERURL = "https://services3.arcgis.com/GVgbJbqm8hXASVYi/arcgis/rest/services/Trails/FeatureServer/0";
		msg = "Click on Add button";
		try {
			cfunction.Commmon_Click("xpath", ADDBTN_11_5_XPATH);
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg + e);

		}
		cfunction.sync(3);
		
		msg = "Click on Add Layers for URL button";
		try {
			cfunction.Commmon_Click("xpath", ADD_LAYER_URL_BUTTON_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
			flag = false;
		}
		cfunction.sync(2);
		
		if (flag) {

			msg = "Send the URL Input";
			try {
				cfunction.Webelement_JSActInput(URL_INPUTBOX_JSPATH, SERVERURL);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatus("FAIL", msg + e);
				e.printStackTrace();
			}
			cfunction.sync(2);
			

			msg = "Click on the 'Add Map'";
			try {
				cfunction.Commmon_Click("xpath", ADDMAP_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatus("FAIL", msg + e);
				e.printStackTrace();
			}
			cfunction.sync(3);

			
		}

		msg = "Verify the map added successfully";
		try {
			cfunction.elementexist("//calcite-list-item[@title='Trails 0']", 10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
		}
		cfunction.sync(5);
		
	}

	public void SaveMap_11_5() throws Exception {
		boolean flag = true;
		msg = "Click on save button";
		try {
			
			cfunction.Commmon_Click("xpath", SAVE_BUTTON_11_5_XPATH);
			cfunction.Commmon_Click("xpath", SAVEAS_BUTTON_11_5_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
		}
		cfunction.sync(3);
		

		msg = "Enter Map Title: "+MapName;
		try {
			cfunction.sync(10);
			cfunction.Webelement_JSClick(TITLE_INPUT_JSPATH);
			cfunction.Webelement_JSInput(TITLE_INPUT_JSPATH, MapName);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg + e);
			e.printStackTrace();
			flag = false;
		}
		if (flag) {
			msg = "Enter Map tags";
			try {
				cfunction.Webelement_JSInput(TITLE_TAGNAME_JSPATH, "TestTag");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatus("FAIL", msg + e);
				e.printStackTrace();
			}

			msg = "Enter Map Summary: "+MapName;
			try {
				cfunction.Webelement_JSInput(MAP_SUMMARY_11_5_JSPATH, MapName);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatus("FAIL", msg + e);
				e.printStackTrace();
			}

	/*		msg = "Click on folder dropdown";
			try {

				cfunction.Webelement_JSClick(MAP_FOLDER_11_5_JSPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			msg = "Select folder to save";
			try {
				List<WebElement> list1 = cfunction.WebelementList_JSPath(FOLDERITEMLIST11_5_JSPATH);
				if (list1.size() > 0) {
					for (int i = 0; i < list1.size(); i++) {
						cfunction.ScrollToWebElement(String.format(FOLDERITEM11_5_JSPATH, (i + 1)));
						String listName = cfunction.Webelement_JSPath(String.format(FOLDERITEM11_5_JSPATH, (i + 1)))
								.getText();
						if (listName.contains(FolderName)) {
							cfunction.clickusingActions(String.format(FOLDERITEM11_5_JSPATH, (i + 1)));
							CommonFunction.logStatus("PASS", msg);
							break;
						}
					}
				}

			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
	*/		msg = "Click on done button to Save the Map with name: "+MapName;
			try {
				cfunction.sync(1);
				cfunction.Commmon_Click("xpath", "//calcite-button[@class='js-save']");
				CommonFunction.logStatus("PASS", msg);
				cfunction.sync(30);
				cfunction.waitforpagetoload();
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
			msg = "Verify Map will save";
			try {
				MAP_NAME_XPATH = "//span[@class='esri-header-inline-title-text' and text()='" + MapName + "']";
				cfunction.waitforelement(MAP_NAME_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				driver.navigate().refresh();
				cfunction.sync(10);
				CommonFunction.logStatus("FAIL", msg + e);

			}

			msg = "Click on the header Menu button ";
			try {
				cfunction.Commmon_Click("xpath", "//button[@class='esri-header-menus-toggle -visible']");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e);

			}

			msg = "Click on the home page";
			try {
				cfunction.sync(3);
				cfunction.Commmon_Click("xpath", "(//a[contains(@id,'esri-header-menus-link')])[1]");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg + e);

			}
			
			

			
		}
	}

	public void VerfiyItemCreatedInFolder(String CreateItemName) {

		msg = "Click on Folder link";
		try {
			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))) {
				List<WebElement> list = cfunction.WebelementList_JSPath(FOLDERLIST11_3_JSPATH);
				for (int i = list.size() ; i >0; i--) {
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAME11_3_JSPATH, (i)));
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.ScrollToWebElement(String.format(FOLDERNAME11_3_JSPATH, (i)));
						cfunction.clickusingActions(String.format(FOLDERNAME11_3_JSPATH, (i )));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			} else if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				String FOLDERTEXT_11_5_JSPATH = "document.querySelector('arcgis-item-browser-filter-folder > arcgis-browser-filter > div > calcite-list > calcite-list-item:nth-child(%s)').shadowRoot.querySelector('div > div.wrapper > div')";
				List<WebElement> list = cfunction.WebelementList_JSPath(FOLDERLIST11_3_JSPATH);
				for (int i = list.size() ; i >0; i--) {
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERTEXT_11_5_JSPATH, (i)));
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.ScrollToWebElement(String.format(FOLDERTEXT_11_5_JSPATH, (i)));
						cfunction.clickusingActions(String.format(FOLDERTEXT_11_5_JSPATH, (i)));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			} else {
				cfunction.CommmonJS_Click(CREATED_FOLDER_XPATH);
				cfunction.waitforpagetoload();
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg + e);

		}
		msg = "Verify " + CreateItemName + " is saved in Folder";
		try {
			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
							|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))
							|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")))) {
				cfunction.sync(2);
				final String FOLDERFEATURE_JSPATH = "document.querySelector('div> arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row').shadowRoot.querySelector('div>a')";
				cfunction.ScrollToWebElement(FOLDERFEATURE_JSPATH);
				WebElement ele = cfunction.Webelement_JSPath(FOLDERFEATURE_JSPATH);
				if (ele.getText().equalsIgnoreCase(CreateItemName)) {
					CommonFunction.logStatus("PASS", msg);
					featurecreated = true;
				} else {
					CommonFunction.logStatus("FAIL", msg);
					featurecreated = false;
					flagfeaturelayer = false;
				}
			} else {
				MAP_NAME_XPATH = "//a[text()='" + CreateItemName + "']";
				cfunction.waitforelement(MAP_NAME_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			flagmap = false;
			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg + e);

		}
	}

}

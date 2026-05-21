
package com.automation.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.PythonAPI.PythonHelp;
import com.automation.library.CommonFunction;
import com.automation.library.TestBase;

@SuppressWarnings("serial")
public class ContentPage extends TestBase {
	int i;
	boolean VeriyYourDevice = true;
	static String ItemLIST_XPATH;
	// XPath code

	final String SCENCELAYERYOURDEVCIE_JSPATH = "document.querySelector('body > arcgis-new-item > calcite-dialog > div.content > arcgis-new-item-pages-home').shadowRoot.querySelector('arcgis-file-browser').shadowRoot.querySelector('arcgis-drag-and-drop > div > div.cta-container > button')";
	final String SCENCELAYERUPLOADINPUTFILE_JSPATH = "document.querySelector('arcgis-new-item > calcite-dialog > div.content > arcgis-new-item-pages-home').shadowRoot.querySelector(\"arcgis-file-browser\").shadowRoot.querySelector('arcgis-drag-and-drop > div > input')";
	final String SCENCELAYERUPLOADINPUTFILE_11_4_0_JSPATH = "document.querySelector('arcgis-new-item-pages-home').shadowRoot.querySelector('arcgis-file-browser').shadowRoot.querySelector('arcgis-drag-and-drop > div > input')";
	final String FOLDERITEM11_5_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelector('calcite-combobox > calcite-combobox-item:nth-child(%s)')";
	String ITEM11_4LISTSIZE_JSPATH = "document.querySelector('arcgis-new-item-pages-tile-layer-from-existing').shadowRoot.querySelector('arcgis-new-item-feature-layer-browser').shadowRoot.querySelectorAll('arcgis-item-browser > arcgis-item-browser-content > arcgis-item-browser-card')";
	String ITEM11_3_CONTENT_JSPATH = "document.querySelector(' div.js-root  arcgis-item-browser > div:nth-child(8) > arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row:nth-child(%s)').shadowRoot.querySelector('a> span')";
	String CHECKBOX_11_3_JSPATH = "document.querySelector(' div.js-root arcgis-item-browser > div:nth-child(8) > arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row:nth-child(%s)').shadowRoot.querySelector(' div.checkbox-wrap > div > calcite-checkbox')";
	String ITEM11_3JSPATH = "document.querySelectorAll(\"arcgis-item-browser > div:nth-child(8) > arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row\")";
	final String ITEM11_4JSPATH = "document.querySelectorAll(\"arcgis-item-browser > div:nth-child(9) > arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row\")";

	final String CONTENT_MENU_ITEM = "//div[@id='esri-header-menus-desktop']//a[contains(@class,'esri-header-menus-link') and contains(@href,'content')]";
	final String CREATE_TILE_11_4_JSPATH = "document.querySelector('arcgis-new-item-pages-tile-layer-from-existing').shadowRoot.querySelector('arcgis-new-item-feature-layer-browser').shadowRoot.querySelector('arcgis-item-browser > arcgis-item-browser-content > arcgis-item-browser-card:nth-child("
			+ i
			+ ")').shadowRoot.querySelector('arcgis-item-card').shadowRoot.querySelectorAll('section > div.item-browser-card__upper > div')";
	final String ALLMYCONTENTFOLDER_JSPATH = "document.querySelector(\" div.js-root arcgis-item-browser > arcgis-item-browser-filters > arcgis-item-browser-filter-folder > arcgis-browser-filter > div > calcite-list > calcite-list-item\").shadowRoot.querySelector(\"div > tr > td.content-container.content-container--selectable.content-container--has-center-content > div.content > div\")";
	final String DELETE_ALL11_3_CONTENT = "document.querySelector(\" div.js-root  arcgis-item-browser > arcgis-item-browser-actions\").shadowRoot.querySelector(\"arcgis-browser-actions\").shadowRoot.querySelector(\"#Delete-trash\").shadowRoot.querySelector(\"div > button\")";
	final String SELECT_ALL11_3_CONTENT = "document.querySelector(\"div.js-root  arcgis-item-browser\").shadowRoot.querySelector(\"arcgis-browser > arcgis-selected\").shadowRoot.querySelector(\" calcite-label\")";
	final String CREATE_ITEM11_3_XPATH = "//calcite-button[@aria-label='New item']";
	final String CREATE_NEW_FOLDER_TITLE_11_3_0_XPATH = "//h2[@class='header sc-arcgis-item-browser-filter-folder']";
	final String CREATE_NEW_FOLDER_NAMEINPUT_JSPATH_11_3_0 = "document.querySelector('calcite-label > calcite-input').shadowRoot.querySelector('div.element-wrapper > input[type=text]')";
	final String CREATE_NEW_FOLDER_SAVE_11_3_0__XPATH = "//calcite-button[@class='sc-arcgis-item-browser-filter-folder' and text()='Save']";
	final String CREATE_NEWFOLDER_SAVE_12_1_XPATH = "//arcgis-tunnel//calcite-button[@data-id='cancelButton']//following-sibling::calcite-button";
	final String CREATEAPPBUTTONIN_VS_10_9_1_XPATH = "//button[@id='build-dropdown']";
	final String CLICK_WEBAPPBULIDER_11_3_XPATH = "//button[contains(@class,'create-app__tile flex ')]";
	final String CLICK_WEBAPPBULIDER_11_4_XPATH = "//button[contains(@class,'dropdown-tile flex cursor')]";
	final String CREATEAPPBUTTONIN_VS_11_3_0_XPATH = "//calcite-button[@id='create-app' and text()='Create app']";
	final String CONTENT_INSIDE_FOLDER_XPATH = "//div[contains(@class,'table-select-rows js-table-rows')]/div";
	final String CONTENT_TITLE_XPATH = "//h1[@id='pagetitle'and text()='Content']";
	final String CONTENT_TITLE_10_6_1_XPATH = "//h1[@id='contenttitle']";
	final String CONTENT_TITLE_10_9_1_XPATH = "//a[@href='#content']";
	final String CONTENT_TITLE_11_3_0_XPATH = "//h1[ text()='Content']";
	final String MY_CONTENT_XPATH = "//a[contains(@class,'nav-link') and contains(@class,'sub') and contains(@href,'content')]";
	final String MY_CONTENT11_3_XPATH = "//calcite-menu-item[@data-id='my']";
	final String HOME_BUTTON_XPATH = "//div[@class='homeContainer']/div[@title()='Default extent']";
	final String CREATE_BUTTON_XPATH = "(//button[contains(@class,'create-items')])[2]";
	final String ADD_BUTTON_XPATH = "(//button[contains(@class,'create-items')])[1]";
	final String LAYER_OPTIONS_XPATH = "//arcgis-new-item-layer-preference[@class='hydrated']";
	final String UPLOAD_FILE_10_8_1_XPATH = "//button[@id='upload-file']";
	final String UPLOADINPUT_10_8_1_XPATH = "//div[@class='type file desktopapplication']//input";
	final String TITLETITLE_10_8_1_XPAH = "//div[@id='widget_title']//input";
	final String TAGS_10_8_1_XPATH = "//div[@id='tags']//input";
	final String ADD_FROM_WEB_XPATH = "//button[@data-action='url']";
	final String CREATE_WEB_APP_BUILDER_XPATH = "//button[@data-action='item-wab']";
	final String CREATE_WEB_APP_BUILDER11_3_XPATH = "//button[contains(@class,'create-app__tile flex ')]";
	final String ADD_FROM_WEB_WIDGET_XPATH = "//div[@widgetid='itemUrl']";
	final String ENTER_URL_XPATH = "//div[@widgetid='itemUrl']//input";
	final String ENTER_TITLE_XPATH = "//div[@id='widget_title']//input";
	final String ENTER_USERNAME_XPATH = "//input[@name='serviceUsername']";
	final String ENTER_PASSWORD_XPATH = "//input[@name='servicePassword']";
	final String SELECT_SAVE_AUTHENTICATION_XPATH = "//input[@id='storeauth']";
	final String ADD_ITEM_DIALOG_BUTTON_XPATH = "//span[@id='addItem-btn']//..";
	// final String ADD_ITEM_NAME_XPATH="//span[@id='pagetitle' and
	// text()='SampleWorldCities']";
	// final String
	// ADD_ITEM_NAME_XPATH_10_6_1="//h1[@id='itemtitle']/span[text()='SampleWorldCities']";
	final String ADD_ITEM_NAME_XPATH = "//span[@id='pagetitle' and contains(text(),'Census')]";
	final String ADD_ITEM_NAME_XPATH_10_6_1 = "//h1[@id='itemtitle']/span[contains(text(),'Census')]";
	// https://qaclient10.esri.com/server/rest/services/SampleWorldCities/MapServer
	final String CREATE_FEATURE_LAYER_XPATH = "//button[@data-action='publish-features']";
	final String CREATE_TILE_LAYER_XPATH = "//button[@data-action='publish-tiles']";
	final String VECTORTILE_JSPATH = "document.querySelector('arcgis-new-item-pages-tile-layer-select').shadowRoot.querySelector('[description*=\"vector\"]')";
	final String RASTOR_TILELAYER_11_3_JSPATH = "document.querySelector('arcgis-new-item-pages-tile-layer-select').shadowRoot.querySelector('div > calcite-tile-select-group:nth-child(2) > calcite-tile-select:nth-child(2)').shadowRoot.querySelector('div > div')";
	final String RASTOR_TILELAYER_11_JSPATH = "document.querySelector('arcgis-new-item-pages-tile-layer-select').shadowRoot.querySelector('div > calcite-tile-select-group:nth-child(2) > calcite-tile-select:nth-child(2)').shadowRoot.querySelector('div > calcite-tile')";
	final String TILEPACKAGE_JSPATH = "document.querySelector('arcgis-new-item-pages-tile-layer-select').shadowRoot.querySelector('[heading=\"Upload a tile package\"]')";
	final String EXISTINGFEATURE_LAYER_JSPATH = "document.querySelector('arcgis-new-item-pages-tile-layer-select').shadowRoot.querySelector('div > calcite-tile-select-group:nth-child(4) > calcite-tile-select:nth-child(3)').shadowRoot.querySelector('div > calcite-tile')";
	final String EXISTINGFEATURE_LAYER11_3_JSPATH = "document.querySelector('arcgis-new-item-pages-tile-layer-select').shadowRoot.querySelector('div > calcite-tile-select-group:nth-child(4) > calcite-tile-select:nth-child(3)').shadowRoot.querySelector('div > div')";
	final String CREATE_TILE11_LAYER_JSPATH = "document.querySelector(\"arcgis-new-item-pages-tile-layer-from-existing\").shadowRoot.querySelector(\"arcgis-new-item-feature-layer-browser\").shadowRoot.querySelectorAll(\"arcgis-item-browser > arcgis-item-browser-content > arcgis-item-browser-card> section h3\")";
	final String CREATE_WEBAPP_BUILDER_XPATH = "//button[@data-action='item-wab']";
	final String CREATE_FEATURE_DIALOG_XPATH = "//div[contains(@widgetid,'dijit_Dialog')]";
	final String CREATE_TILE_11_2_JSPATH = "document.querySelector(\"arcgis-new-item-pages-tile-layer-from-existing\").shadowRoot.querySelector(\"arcgis-new-item-feature-layer-browser\").shadowRoot.querySelector(\"arcgis-item-browser > arcgis-item-browser-content > arcgis-item-browser-card\").shadowRoot.querySelectorAll(\"section h3\")";
	final String ELECTRIC_UTIL_FEATURE_XPATH = "(//div[contains(@class,'gallery-left')]//a)[4]";
	final String ELECTRIC_UTIL_FEATURE_11_XPATH = "document.querySelector('arcgis-new-item-pages-feature-layer-from-template').shadowRoot.querySelector('arcgis-new-item-feature-layer-browser').shadowRoot.querySelector('arcgis-item-browser > arcgis-item-browser-filters > arcgis-item-browser-filter-industry').shadowRoot.querySelector('calcite-accordion-item > calcite-tree > calcite-tree-item:nth-child(2) > a')";
	final String FEATURE_UTIL_TEMPLATE11_2_JSPATH = "document.querySelector('arcgis-new-item-pages-feature-layer-from-template').shadowRoot.querySelector('arcgis-new-item-feature-layer-browser').shadowRoot.querySelector(' calcite-accordion-item > div:nth-child(2) > calcite-tree > calcite-tree-item:nth-child(2)>a')";
	final String FEATURE_LAYER_TEMPLATE_XPATH = "(//div[contains(@class,'gallery-view fadeIn')])[1]";
	final String FEATURE_LAYER_TEMPLATE11_JSPATH = "document.querySelector('arcgis-new-item-pages-feature-layer-from-template').shadowRoot.querySelector('arcgis-new-item-feature-layer-browser').shadowRoot.querySelector('arcgis-item-browser > arcgis-item-browser-content > arcgis-item-browser-card:nth-child(1) ')";
	final String FEATURE_LAYER_TEMPLATE11_1_JSPATH = "document.querySelector(\"arcgis-new-item-pages-feature-layer-from-template\").shadowRoot.querySelector(\"arcgis-new-item-feature-layer-browser\").shadowRoot.querySelector(\"arcgis-item-browser > arcgis-item-browser-content > arcgis-item-browser-card:nth-child(1) > section > div[class*=item-browser-card] > calcite-radio-button\")";
	final String FEATURE_TEMPLATE_CREATE_BUTTON_XPATH = "//div[@id='template-info-panel']//button[@id='on-next']";
	final String FEATURE_TEMPLATE_NEXT_BUTTON_XPATH = "//span[contains(@class,'dijitButtonContents')]//span[text()='Next']";
	final String FEATURE_11_NEXT_XPATH = "//calcite-button[@data-id='nextButton']";
	final String SAVE_BUTTON_11_JSPATH = "document.querySelector('body > div:nth-child(11) > arcgis-new-item > calcite-modal > calcite-button:nth-child(5)')";
	final String ONLINE_BASEMAP11_JSPATH = "document.querySelector('arcgis-new-item-pages-tile-layer-settings').shadowRoot.querySelector('#standard-input-option').shadowRoot.querySelector('div > calcite-tile')";
	final String FEATURE_ZOOM_BUTTON_XPATH = "//div[@class='esriSimpleSliderIncrementButton']//span[text()='+']";
	final String FEATURE_TITLE_BUTTON_XPATH = "//div[contains(@id,'ItemPropertiesDlg') and contains(@id,'title')]//input";
	final String FEATURE_TITLE_11_BUTTON_JSPATH = "document.querySelector('arcgis-item-properties > arcgis-title-input').shadowRoot.querySelector('#item-properties-title').shadowRoot.querySelector('div > div.element-wrapper > input[type=text]')";
	final String TILETITLE_10_9_1_JSPATH = "document.querySelector('arcgis-item-properties-title').shadowRoot.querySelector('div[class*=\"calcite-input-element\"]> input')";
	final String FEATURE_TAG_XPATH = "//div[contains(@id,'ItemPropertiesDlg') and contains(@id,'tags')]//input";
	final String TILE_10_9_1_TAGS_JSPATH = "document.querySelector('arcgis-item-properties-tags').shadowRoot.querySelector('calcite-combobox').shadowRoot.querySelector('input')";
	final String FEATURE_TAG_11_JSPATH = "document.querySelector('arcgis-item-properties > arcgis-tags-picker').shadowRoot.querySelector('calcite-combobox').shadowRoot.querySelector('input')";
	final String ADD_WEB_TAG_XPATH = "//div[@id='tags']//input";
	final String FEATURE_SUMMARY_XPATH = "//textarea[contains(@id,'dialog_ItemPropertiesDlg') and contains(@id,'summary')]";
	final String FEATURE_SUMMARY_11_JSPATH = "document.querySelector('arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-summary-input').shadowRoot.querySelector('#summary-input').shadowRoot.querySelector('textarea')";
	final String FEATURE_FOLDER_XPATH = "//div[@class='esriItemPropertiesDlg']//table[contains(@id,'dialog_ItemPropertiesDlg') and contains(@id,'folder')]";
	final String FEATURE_FOLDER_11_JSPATH = "document.querySelector(\"arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-folder-picker\").shadowRoot.querySelector(\"calcite-label > div > calcite-combobox\").shadowRoot.querySelector(\"div.wrapper.wrapper--single > div > span[class*='input-wrap']\")";
	final String FEATURE_FOLDER_11_1_JSPATH = "document.querySelector(\"arcgis-item-properties > arcgis-folder-picker\").shadowRoot.querySelector(\"calcite-label > div > div > calcite-combobox\").shadowRoot.querySelector(\"div.wrapper > div\")";
	final String TILE_FOLDER_10_9_1_JSPATH = "document.querySelector('arcgis-item-properties-folder').shadowRoot.querySelector('calcite-label > label > calcite-combobox')";
	final String SAVE_FEATURE_BUTTON_XPATH = "//span[contains(@class,'dijitButtonText') and text()='Done']/..";
	final String TITLE_LAYER_MANAGE_XPATH = "//div[contains(@class,'esriManageTiles')]//div[@class='dijitDialogTitleBar']";
	final String TILELAYER_MANAGE_CLOSE_BTN_XPATH = "//button[@data-dojo-attach-point='_closeButton']";
	String FOLDER_NAME_XPATH;
	String FEATURE_NAME_XPATH;
	String test = null;
	final String TILE_LAYER_CHECKBOX_XPATH = "//table[@id='scaleGrid-header']//input[@type='checkbox']";
	final String TILE_LAYER_CLOSE_XPATH = "//button[@data-dojo-attach-point='_closeButton']";

	final String HOME_NAVIGATION_XPATH = "//a[@id='home-navigation-dropdown-link']";
	final String HOME_LINK_XPATH = "//div[@class='dropdown-menu']//a[text()='Home']";
	final String CONTENT_LINK_XPATH = "//div[@class='dropdown-menu']//a[text()='Content']";
	// final String
	// CREATE_NEW_FOLDER_XPATH="//div[@class='js-filter-wrap']//button[@aria-label='Create
	// new folder']";
	final String CREATE_NEW_FOLDER_XPATH = "//h4[@class='folder-list-title']//button[@aria-label='Create new folder']";
	final String CREATE_NEW_FOLDER_11_2_XPATH = "//button[@id='new-folder-button']";
	final String CREATE_NEW_FOLDER_11_3_XPATH = "//calcite-button[@id='create-folder-button'] ";
	final String CREATE_NEW_FOLDER_TITLE_XPATH = "//div[@data-dojo-attach-point='titleBar']//span[text()='Create a Folder']";
	final String CREATE_NEW_FOLDER_NAME_XPATH = "//form[@data-dojo-attach-point='_createFolderForm']//input";
	final String CREATE_NEW_FOLDER_OK_XPATH = "//div[@data-dojo-attach-point='actionBarNode']//span[@id='dijit_form_Button_1']";
	// final String
	final String DELETE_BUTTON_11_5_JSPATH = "document.querySelector('arcgis-item-browser-actions').shadowRoot.querySelector('arcgis-browser-actions').shadowRoot.querySelector(\"#Delete-trash\")";
	final String SELECT_ALL_CONTENT = "//input[@type='checkbox'and @aria-label='Select all']";
	final String DELETE_ALL_CONTENT = "//button[contains(@class,'delete')]";
	final String DELETE_CONFIRMATION_XPATH = "//span[@id='button_delete-warning-submit_label']";
	final String DELETE_ALL_CONFIRMATION_XPATH = "//calcite-modal//calcite-button[text()='Delete']";
	final String DELETE_CONFIRMATION_11_1_XPATH = "//calcite-button[@data-test='ok']";
	final String DELETE_CONFIRMATION_11_3_XPATH = "document.querySelector(\" arcgis-tunnel > arcgis-delete-folder\").shadowRoot.querySelector(\"calcite-modal > calcite-button:nth-child(4)\").shadowRoot.querySelector(\"div > button\")";
	// final String DELETE_CONFIRMATION_11_4_XPATH =
	// "document.querySelector(\"arcgis-tunnel >
	// arcgis-delete-folder\").shadowRoot.querySelector(\"arcgis-delete-items\").shadowRoot.querySelector(\"calcite-modal
	// > calcite-button:nth-child(5)\")";
	final String DELETE_CONFIRMATION_11_4_XPATH = "document.querySelector(\"arcgis-tunnel > arcgis-delete-folder\").shadowRoot.querySelector(\"arcgis-delete-items\").shadowRoot.querySelector(\"calcite-button[data-id='nextButton']\")";
	final String DELETE_CONFIRMATION_11_4_1_XPATH = "document.querySelector(\"arcgis-tunnel > arcgis-delete-folder\").shadowRoot.querySelector(\"calcite-button[data-id='nextButton']\")";

	final String DELETE_CONFIRMATION_10_9_XPATH = "//calcite-modal[@intl-close='Close']//calcite-button[@slot='primary']";
	// 2 tablet-hide
	final String DELETE_FOLDER_11_3_XPATH = "(//calcite-action[contains(@id,'delete-button')])";
	final String DELETE_FOLDER_XPATH = "//section[contains(@class,'trailer-')]//button[@aria-label='Delete Folder' and @data-title='"
			+ FolderName + "']";

	final String CONTENTPAG_SEARCH_INPUT_11_5_JSPATH = "document.querySelector('arcgis-item-browser > arcgis-item-browser-top-bar > arcgis-item-browser-search').shadowRoot.querySelector('arcgis-browser-search').shadowRoot.querySelector('input')";
	final String SERACHLAYER_SIZE_11_5_JSPATH = "document.querySelector(\"arcgis-item-browser\").shadowRoot.querySelector(\"arcgis-browser\").shadowRoot.querySelector(\"div.content__top > span.range\")";
	final String SERACHLAYER_SIZE_10_9_JSPATH = "//span[@data-dojo-attach-point='_rangeText']";

	// final String CREATED_FOLDER_XPATH="//section[@class='trailer-2
	// tablet-hide']//a[@data-title='"+FolderName+"']";
	final String CREATED_FOLDER_XPATH1 = "//section[@class='trailer-2 tablet-hide']//li[@data-title='" + FolderName
			+ "']";
	final String CREATED_FOLDER_XPATH = "//*[@class='folder-list-title']//following-sibling::ul//a[text()='"
			+ FolderName + "']";
	final String ALL_MY_CONTENT_XPATH = "//a[@data-id='all']";
	final String ITEMCHECKBOX_JSPATH = "document.querySelector('arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row:nth-child(%s)').shadowRoot.querySelector('div > div.checkbox-wrap > div')";
	final String ALLITEMLIST11_5_JSPATH = "document.querySelectorAll('arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row')";
	final String ALLCONTENTITEMLIST11_5_JSPATH = "document.querySelector('arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row:nth-child(%s)').shadowRoot.querySelector('div> span')";
	final String ALL_MY_CONTENT_11_3_JSPATH = "document.querySelector(\"calcite-list-item:nth-child(1)\").shadowRoot.querySelector(\"div > tr > td > div.content\")";
	final String ALL_MY_CONTENT_11_5_JSPATH = "document.querySelector(\"arcgis-item-browser-filter-folder > arcgis-browser-filter > div > calcite-list > calcite-list-item:nth-child(1)\").shadowRoot.querySelector(\"div > div.wrapper > div.row.container.container--hover.container--border.container--border-selected\")";
	final String MY_ORGANIZATION_XPATH = "//a[@data-tab='organization']";
	final String SEARCH_ORGANIZATION_XPATH = "//input[@placeholder='Search My Organization']";
	final String SAMPLE_WORLD_CITIES_XPATH = "(//div[contains(@class,'card-select')]//span[@data-tooltip='Map Image Layer'])[last()]//../..//a[@class='card-title-link']";
	// final String SAMPLE_WORLD_CITIES_XPATH= "(//div[contains(@class,'js-card card
	// card-select')]//span[@data-tooltip='Map Image
	// Layer']//../span[contains(@class,'card-owner') and
	// text()='PortalAdmin'])[1]/../..//a[@class='card-title-link']";
	final String COPY_URL_XPATH = "//input[@id='copy-to-clipboard-url']";
	final String MY_ORGANIZATION_VIEW_XPATH = "//button[@aria-label='Select View']";
	final String VIEW_LIST_XPATH = "//button[@data-view='list']";
	final String VIEW_TABLE_XPATH = "//button[@data-view='table']";
	final String VIEW_GRID_XPATH = "//button[@data-view='grid']";
	final String INPUT_TITLE11_JSPATH = "document.querySelector('arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-title-input').shadowRoot.querySelector('#item-properties-title').shadowRoot.querySelector('div > div.element-wrapper > input[type=text]')";
	final String TITLE_TAG11_JSPATH = "document.querySelector('arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-tags-picker').shadowRoot.querySelector('calcite-label > calcite-combobox').shadowRoot.querySelector('span > input')";
	final String SAVE11_FF_XPATH = "document.querySelector('body calcite-modal:nth-child(1) > calcite-button:nth-child(5)')";
	final String SCENELAYER_11_1_JSPATH = "document.querySelector(\" arcgis-new-item-pages-home\").shadowRoot.querySelector(\"arcgis-new-item-create-layers-list\").shadowRoot.querySelector(\"nav > ul > li button > div.card__icon > calcite-icon[icon='layerGraphics']\")";
	final String SCENELAYER_XPATH = "//button[contains(@class,'scene')]";
	final String UPLOAD_SCENEPACKAGE_11_1_JSPATH = "document.querySelector(\"arcgis-new-item-pages-scene-layer-select\").shadowRoot.querySelector(\"div > calcite-tile-select-group > calcite-tile-select:nth-child(1)\")";
	final String SCENELAYER11_1_YOURDEVICE_JSPATH = "document.querySelector(\"arcgis-new-item-pages-layer-upload\").shadowRoot.querySelector(\"arcgis-file-browser\").shadowRoot.querySelector(\"arcgis-drag-and-drop > div > input\")";
	final String SCENE11_1_TITLE_XPATH = "document.querySelector(\"arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-title-input\").shadowRoot.querySelector(\"#item-properties-title\").shadowRoot.querySelector(\"div >input[type=text]\")";
	final String PUBLISHLAYER_AS_HOSTED_JSPATH = "document.querySelector('arcgis-new-item-pages-publish-select').shadowRoot.querySelector('#hosted-input-option')";
	final String FOLDER_DROPDOWN_JSPATH = "document.querySelector(\"arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-folder-picker\").shadowRoot.querySelector(\"calcite-label > div > div > calcite-combobox\").shadowRoot.querySelector(\"div.wrapper.wrapper--single > div > span.input-wrap.input-wrap--single\")";
	final String FOLDERLIST_JSPATH = "document.querySelector(\"arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-folder-picker\").shadowRoot.querySelectorAll(\"calcite-label > div > div > calcite-combobox > calcite-combobox-item\")";
	final String FOLDERLIST11_3_JSPATH = "document.querySelectorAll(\"arcgis-item-browser-filter-folder > arcgis-browser-filter > div > calcite-list > calcite-list-item\")";
	final String FOLDERLIST10_9_1_XPATH = "(//*[@class='folder-list-title']//following-sibling::ul//li)";
	final String FOLDERNAME11_3_JSPATH = "document.querySelector(\"calcite-list > calcite-list-item:nth-child(%s)\").shadowRoot.querySelector(\"div > tr>td>div.content\")";
	final String FOLDERNAME11_5_JSPATH = "document.querySelector('calcite-list > calcite-list-item:nth-child(%s)').shadowRoot.querySelector('div>div')";
	final String FINAL_FOLDERNAME_11_3_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelector(' calcite-combobox > calcite-combobox-item:nth-child(%s)').shadowRoot.querySelector('span.title')";
	final String FEATURELAYER_OPTION_JSPATH = "document.querySelector('body > arcgis-new-item > calcite-dialog > div.content > arcgis-new-item-pages-feature-layer-select').shadowRoot.querySelector('div > calcite-tile-group > calcite-tile:nth-child(3)').shadowRoot.querySelector('div > div > calcite-icon').shadowRoot.querySelector('svg')";
	final String FOLDERNAMELIST11_5_JSPATH = "document.querySelector('calcite-list > calcite-list-item:nth-child(%s)').shadowRoot.querySelector('div > div.wrapper >div')";
	final String NEXT_10_9_1_XPATH = "//calcite-button[@slot='primary']";
	final String FEATURE_CREATIONLOADER_JSPATH = "document.querySelector('arcgis-new-item-loader').shadowRoot.querySelector('[role=\"progressbar\"]')";
	final String EXPERIENCE_BUILDER_XPATH = "//a[contains(@href,'experience')]";
	final String EXB_TEMPLATE_XPATH = "//a[contains(@href,'templatelist')]";
	final String FOLDABLE_TEMPLATE_XPATH = "//div[@aria-label='Fullscreen fixed']//button[@title='Create']";
	final String CONTENTNAME_JSPATH = "document.querySelector('arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row:nth-child(%s)').shadowRoot.querySelector('a')";
	final String EXPRESSMODE_POPUP_XPATH = "//div[@role='alertdialog']//..//button";
	final String EXPERIENCEMENU_XPATH = "//li[@role='menuitem']//a[contains(@href,'experienceslist')]";
	final String CREATE_NEW_XPATH = "//div[@data-testid='app-list']//button";

	String msg = "";

	CommonFunction cfunction = new CommonFunction();
	HomePage hp = new HomePage();

	// String FolderName="Folder_"+dateformat.format(datef);
	public void verifycontentpage() {
		// CommonFunction.waitforpagetoload();
		try {
			cfunction.waitforpagetoload();
			if (projectVersion.equals("11.3.0") || (projectVersion.equals("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
							|| (projectVersion.equalsIgnoreCase("12.0.0")))
					|| (projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				cfunction.sync(10);
				cfunction.waitforelement("//h1[ text()='Content']");
			} else {
				cfunction.waitforelement(CONTENT_TITLE_XPATH);

			}
		} catch (Exception e) {
			e.printStackTrace();
			// CommonFunction.logStatus("FAIL", msg);

		}
	}

	public void verifycontentpage_10_6_1() throws InterruptedException {
		// CommonFunction.waitforpagetoload();

		try {
			cfunction.waitforpagetoload();
			cfunction.waitforelement(CONTENT_TITLE_10_6_1_XPATH);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifycontentpage_10_9_1() throws InterruptedException {
		// CommonFunction.waitforpagetoload();

		try {
			cfunction.waitforpagetoload();
			cfunction.waitforelement(CONTENT_TITLE_10_9_1_XPATH);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void verifycontentpage_11_3_0() throws InterruptedException {
		// CommonFunction.waitforpagetoload();

		try {
			cfunction.waitforpagetoload();
			cfunction.sync(10);
			cfunction.waitforelement(CONTENT_TITLE_11_3_0_XPATH);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void createnewfolder_11_3_0() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Click on create new folder";
		try {
			if (projectVersion.equalsIgnoreCase("11.2.0")) {
				cfunction.CommmonJS_Click(CREATE_NEW_FOLDER_11_2_XPATH);

			} else if (projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.CommmonJS_Click(CREATE_NEW_FOLDER_11_3_XPATH);

			} else {
				cfunction.CommmonJS_Click(CREATE_NEW_FOLDER_XPATH);
			}
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Wait for create folder dialog box";
		try {
			if (projectVersion.equalsIgnoreCase("12.1.0"))
				cfunction.waitforwebelement(CREATE_NEW_FOLDER_NAMEINPUT_JSPATH_11_3_0);
			else
				cfunction.waitforelement(CREATE_NEW_FOLDER_TITLE_11_3_0_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			try {
				cfunction.CommmonJS_Click(CREATE_NEW_FOLDER_11_3_XPATH);
				cfunction.waitforelement(CREATE_NEW_FOLDER_TITLE_11_3_0_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e1);

			}
			// e.printStackTrace();
			// CommonFunction.logStatus("FAIL", msg+e);

		}
		msg = "Enter folder name";
		try {
			cfunction.CommonTextBox_InputJS(CREATE_NEW_FOLDER_NAMEINPUT_JSPATH_11_3_0, FolderName);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click Ok";
		try {
			if (projectVersion.equalsIgnoreCase("12.1.0"))
				cfunction.CommmonJS_Click(CREATE_NEWFOLDER_SAVE_12_1_XPATH);
			else
				cfunction.CommmonJS_Click(CREATE_NEW_FOLDER_SAVE_11_3_0__XPATH);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify folder created";
		try {

			List<WebElement> list = cfunction.WebelementList_JSPath(FOLDERLIST11_3_JSPATH);
			// final String FOLDERNAME11_5_JSPATH = "document.querySelector('calcite-list >
			// calcite-list-item:nth-child(%s)').shadowRoot.querySelector('div')";

			// System.out.println(list.size());
			for (int i = list.size(); i > 0; i--) {

				if ((projectVersion.equalsIgnoreCase("11.5.0")) || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
					WebElement ele_11_5 = cfunction.Webelement_JSPath(String.format(FOLDERNAME11_5_JSPATH, (i)));
					if (ele_11_5.getText().equalsIgnoreCase(FolderName)) {
						CommonFunction.logStatus("PASS", msg);
						foldercreateddone = true;
						break;
					}
				} else {
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAME11_3_JSPATH, (i)));
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						CommonFunction.logStatus("PASS", msg);
						foldercreateddone = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			try {
				cfunction.refreshpage();
				cfunction.sync(5);
				cfunction.CommmonJS_Click(CREATE_NEW_FOLDER_XPATH);
				cfunction.sync(3);
				cfunction.CommonTextBox_Input(CREATE_NEW_FOLDER_NAME_XPATH, FolderName);
				cfunction.sync(2);
				cfunction.CommmonJS_Click(CREATE_NEW_FOLDER_OK_XPATH);
				cfunction.sync(2);
				cfunction.waitforelement(CREATED_FOLDER_XPATH);
				foldercreateddone = true;
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				e.printStackTrace();
				flagcontent = false;
				FolderName = driver
						.findElement(By.xpath("//h4[@class='folder-list-title']//following-sibling::ul/li[2]"))
						.getAttribute("data-title");
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
	}

	public void createnewfolder() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Click on create new folder";
		try {
			if (projectVersion.equalsIgnoreCase("11.2.0")) {
				cfunction.CommmonJS_Click(CREATE_NEW_FOLDER_11_2_XPATH);

			} else if (projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.CommmonJS_Click(CREATE_NEW_FOLDER_11_3_XPATH);

			} else {
				cfunction.CommmonJS_Click(CREATE_NEW_FOLDER_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Wait for create folder dialog box";
		try {
			cfunction.waitforelement(CREATE_NEW_FOLDER_TITLE_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			try {
				cfunction.CommmonJS_Click(CREATE_NEW_FOLDER_XPATH);
				cfunction.waitforelement(CREATE_NEW_FOLDER_TITLE_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e1);

			}
			// e.printStackTrace();
			// CommonFunction.logStatus("FAIL", msg+e);

		}
		msg = "Enter folder name";
		try {
			cfunction.CommonTextBox_Input(CREATE_NEW_FOLDER_NAME_XPATH, FolderName);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Click Ok";
		try {
			cfunction.CommmonJS_Click(CREATE_NEW_FOLDER_OK_XPATH);
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify folder created";
		try {

			cfunction.waitforelement(CREATED_FOLDER_XPATH);
			CommonFunction.logStatus("PASS", msg);
			foldercreateddone = true;
		} catch (Exception e) {
			try {
				cfunction.refreshpage();
				cfunction.sync(5);
				cfunction.CommmonJS_Click(CREATE_NEW_FOLDER_XPATH);
				cfunction.sync(3);
				cfunction.CommonTextBox_Input(CREATE_NEW_FOLDER_NAME_XPATH, FolderName);
				cfunction.sync(2);
				cfunction.CommmonJS_Click(CREATE_NEW_FOLDER_OK_XPATH);
				cfunction.sync(2);
				cfunction.waitforelement(CREATED_FOLDER_XPATH);
				CommonFunction.logStatus("PASS", msg);
				foldercreateddone = true;
			} catch (Exception e1) {
				e.printStackTrace();
				flagcontent = false;
				FolderName = driver
						.findElement(By.xpath("//h4[@class='folder-list-title']//following-sibling::ul/li[2]"))
						.getAttribute("data-title");
				CommonFunction.logStatusWithException("FAIL", msg, e);

			}
		}
	}

	public void navigateToHomePage() throws Exception {
		// CommonFunction.waitforpagetoload();
		msg = "Navigate to home page from map page";
		try {
			cfunction.CommmonJS_Click(HOME_NAVIGATION_XPATH);
			cfunction.sync(2);
			cfunction.CommmonJS_Click(HOME_LINK_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void clickmycontentfolder() throws Exception {
		FeatureLayerExist = false;
		boolean clickedfolder = false;
		WebElement folder = null;
		msg = "Click on My content";
		try {
			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.CommmonJS_Click(MY_CONTENT11_3_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} else {
				cfunction.CommmonJS_Click(MY_CONTENT_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}
		msg = "Click on folder link";
		try {
			List<WebElement> list = cfunction.WebelementList_JSPath(FOLDERLIST11_3_JSPATH);
			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))) {

				// System.out.println(list.size());
				for (int i = list.size(); i > 0; i--) {
					cfunction.ScrollToWebElement(String.format(FOLDERNAME11_3_JSPATH, (i)));
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAME11_3_JSPATH, (i)));
					if (ele.getText().equalsIgnoreCase("All My Content")) {
						folder = ele;

					}
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.ScrollToWebElement(String.format(FOLDERNAME11_3_JSPATH, (i)));
						cfunction.clickusingActions(String.format(FOLDERNAME11_3_JSPATH, (i)));
						CommonFunction.logStatus("PASS", msg);
						clickedfolder = true;
						break;
					}
				}
				if (clickedfolder == false) {
					cfunction.ScrollToJsElement(folder);
					folder.click();
					CommonFunction.logStatus("PASS", msg);

				}
			} else if ((projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				for (int i = list.size(); i > 0; i--) {
					cfunction.ScrollToWebElement(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
					if (ele.getText().equalsIgnoreCase("All My Content")) {
						folder = ele;
					}
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.clickusingActions(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
				if (clickedfolder == false) {
					folder.click();
					CommonFunction.logStatus("PASS", msg);

				}
			} else {
				int size = cfunction.getListOfWebElements(CREATED_FOLDER_XPATH).size();
				if (size > 0) {
					cfunction.CommmonJS_Click(CREATED_FOLDER_XPATH);
					cfunction.sync(2);
					CommonFunction.logStatus("PASS", msg);
				} else
					driver.findElement(
							By.xpath("//*[contains(text(),'All my content') or contains(text(),'All My Content')]"))
							.click();
				CommonFunction.logStatus("PASS", msg);

			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Verfiy the Feature layer should exist in the created folder " + FolderName;
		try {

			if (projectVersion.equalsIgnoreCase("11.5.0") || projectVersion.equalsIgnoreCase("11.4.0")
					|| projectVersion.equalsIgnoreCase("11.3.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.ScrollToWebElement(CONTENTPAG_SEARCH_INPUT_11_5_JSPATH);
				cfunction.sync(5);
				cfunction.Webelement_JSInput(CONTENTPAG_SEARCH_INPUT_11_5_JSPATH, FeatureName);
				String SizeText = cfunction.Webelement_JSPath(SERACHLAYER_SIZE_11_5_JSPATH).getText();
				if (SizeText.equalsIgnoreCase("1-1 of 1")) {
					CommonFunction.logStatus("PASS", msg);
					FeatureLayerExist = true;
				}
			} else if (projectVersion.equalsIgnoreCase("10.8.1") || projectVersion.equalsIgnoreCase("10.9.1")
					|| projectVersion.equalsIgnoreCase("11.1.0")) {
				String CONTENTPAGE_SEARCH_INPUT10_9_XPATH = "//input[@type='search']";
				// cfunction.ScrollToElement(CONTENTPAGE_SEARCH_INPUT10_9_XPATH);
				cfunction.CommonTextBox_Input(CONTENTPAGE_SEARCH_INPUT10_9_XPATH, FeatureName);
				String SizeText = cfunction.getElementText(SERACHLAYER_SIZE_10_9_JSPATH);
				String ActualText = "1 - 1 of 1 in " + FolderName + "";
				if (SizeText.equalsIgnoreCase(ActualText)) {
					CommonFunction.logStatus("PASS", msg);
					FeatureLayerExist = true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

	public void verifyfeaturelayerdialog() throws Exception {
		msg = "Click on Create button";
		try {
			cfunction.CommmonJS_Click(CREATE_BUTTON_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(5);
		msg = "Click on Feature layer button";
		try {
			if (projectVersion.equals("10.9.1") || projectVersion.startsWith("11")) {
				// cfunction.sync(20);
				String JSPATH = "document.querySelector('arcgis-new-item-pages-home').shadowRoot.querySelector('arcgis-new-item-create-layers-list').shadowRoot.querySelector('nav > ul > li:nth-child(1) > button > div.card__content')";
				cfunction.Webelement_JSClick(JSPATH);
			} else
				cfunction.CommmonJS_Click(CREATE_FEATURE_LAYER_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}
		cfunction.sync(5);

		msg = "Verify create feature layer dialogbox displays";
		try {
			if (projectVersion.startsWith("11")) {
				String JSPATH = "document.querySelector('arcgis-new-item-pages-feature-layer-select').shadowRoot.querySelector('div > calcite-tile-select-group > calcite-tile-select:nth-child(3)')";
				/*
				 * WebElement elm5 = getWebelement_JSpath(JSPATH); cfunction.clickJS(elm5);
				 */
				cfunction.Webelement_JSClick(JSPATH);
				cfunction.sync(2);
				cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
			} else
				cfunction.waitforelement(CREATE_FEATURE_DIALOG_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			featurecreated = false;
			e.printStackTrace();

		}

	}

	public void verifyfeaturelayerdialog11_3() throws Exception {
		cfunction.sync(5);
		msg = "Click on Create button";
		try {

			cfunction.CommmonJS_Click(CONTENT_MENU_ITEM);
			// cfunction.sync(5);
			cfunction.CommmonJS_Click(CREATE_ITEM11_3_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(10);

		msg = "Click on Feature layer button";
		try {

			// cfunction.sync(20);
			String JSPATH = "document.querySelector('arcgis-new-item-pages-home').shadowRoot.querySelector('arcgis-new-item-create-layers-list').shadowRoot.querySelector('button > div.card__content')";
			/*
			 * WebElement elm5 = getWebelement_JSpath(JSPATH); cfunction.clickJS(elm5);
			 */
			cfunction.Webelement_JSClick(JSPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);

		msg = "Verify create feature layer dialogbox displays";
		try {
			if (projectVersion.startsWith("11") || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| projectVersion.startsWith("12.0")) {
				String JSPATH = "document.querySelector('arcgis-new-item-pages-feature-layer-select').shadowRoot.querySelector('div > calcite-tile-select-group > calcite-tile-select:nth-child(3)')";
				cfunction.ScrollToWebElement(JSPATH);
				cfunction.clickusingActions(JSPATH);
				cfunction.sync(2);
				cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
			} else if (projectVersion.startsWith("12.1") || (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				cfunction.clickusingActions(FEATURELAYER_OPTION_JSPATH);
				cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
			} else
				cfunction.waitforelement(CREATE_FEATURE_DIALOG_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			featurecreated = false;
			e.printStackTrace();

		}
		cfunction.sync(5);

	}

	public void verifyTilelayerdialog() throws Exception {

		cfunction.sync(10);
		msg = "Click on Create button";
		try {
			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.CommmonJS_Click(CREATE_ITEM11_3_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} else {
				cfunction.CommmonJS_Click(CREATE_BUTTON_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(5);

		msg = "Click on Tile layer button";
		try {
			if (projectVersion.startsWith("11") || projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0")
					|| projectVersion.startsWith("12.1")) {
				// cfunction.sync(20);
				String JSPATH = "document.querySelector(\" arcgis-new-item-pages-home\").shadowRoot.querySelector(\"arcgis-new-item-create-layers-list\").shadowRoot.querySelector(\"nav > ul > li button > div.card__icon > calcite-icon[icon='tileLayer']\")";
				/* cfunction.Webelement_JSClick(JSPATH); */
				cfunction.Webelement_JSClick(JSPATH);
			} else
				cfunction.CommmonJS_Click(CREATE_TILE_LAYER_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);

		msg = "Verify create Tile layer dialogbox displays";
		try {
			if (!(projectVersion.startsWith("11") || projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0")
					|| projectVersion.startsWith("12.1"))) {
				cfunction.waitforelement(CREATE_FEATURE_DIALOG_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			tilecreated = false;
			e.printStackTrace();
		}

	}

	public void verifyTilelayerdialog_10_9_1() throws Exception {

		msg = "Click on Create button";
		try {
			cfunction.sync(10);
			cfunction.CommmonJS_Click(CREATE_BUTTON_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(5);
		msg = "Upload Tile Package 'LosAngeles.vtpk'";
		try {
			String tilePackage = System.getProperty("user.dir") + "/Input/" + "LosAngeles" + ".vtpk";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = (WebElement) js.executeScript("return " + SCENCELAYERUPLOADINPUTFILE_11_4_0_JSPATH);
			element.sendKeys(tilePackage);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}
		cfunction.sync(5);

	}

	public void createtilelayer() throws Exception {
		cfunction.sync(5);
		msg = "Select Vector Tile layer ";
		try {
			if (projectVersion.startsWith("11") || projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0")
					|| projectVersion.startsWith("12.1")) {
				cfunction.Webelement_JSClick(VECTORTILE_JSPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		msg = "Select Type of method to create Tile Layer: 'Upload A Tile Package' ";
		try {
			if (projectVersion.startsWith("11") || projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0")
					|| projectVersion.startsWith("12.1")) {
				cfunction.Webelement_JSClick(TILEPACKAGE_JSPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);

		msg = "Click Next";
		try {
			if (projectVersion.startsWith("11") || projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0")
					|| projectVersion.startsWith("12.1")) {
				cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);
		msg = "Upload 'LosAngeles.vtpk' package";
		try {
			if (projectVersion.startsWith("11") || projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0")
					|| projectVersion.startsWith("12.1")) {
				// cfunction.sync(10);
				String vectorPackage = System.getProperty("user.dir") + "/Input/" + "LosAngeles" + ".vtpk";
				String vectorPackageff = System.getProperty("user.dir") + "\\Input\\" + "LosAngeles" + ".vtpk";
				/*
				 * JavascriptExecutor js = (JavascriptExecutor) driver; WebElement element =
				 * (WebElement) js.executeScript("return " + SCENELAYER11_1_YOURDEVICE_JSPATH);
				 */
				WebElement element = cfunction.Webelement_JSPath(SCENELAYER11_1_YOURDEVICE_JSPATH);
				if (Browser.equalsIgnoreCase("Firefox"))
					element.sendKeys(vectorPackageff);
				else
					element.sendKeys(vectorPackage);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);

		msg = "Select 'Add vector tile package and created a Hosted Tile Layer'";
		try {
			cfunction.Webelement_JSClick(PUBLISHLAYER_AS_HOSTED_JSPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);
		msg = "Click Next";
		try {
			if (projectVersion.equalsIgnoreCase("10.9.1") || projectVersion.equalsIgnoreCase("10.8.1"))
				cfunction.Commmon_Click("xpath", NEXT_10_9_1_XPATH);
			else
				cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}
		cfunction.sync(3);

	}

	public void createTile_10_8_1(String title, String tag) throws Exception {

		msg = "Navigate to content page";

		try {
			hp.navigateToContentPage();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		cfunction.sync(2);

		msg = "Click on Add Item ";
		try {
			cfunction.CommmonJS_Click(ADD_BUTTON_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();

		}
		cfunction.sync(2);

		msg = "Click on 'From your Computer' option";
		try {
			cfunction.CommmonJS_Click(UPLOAD_FILE_10_8_1_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();

		}

		cfunction.sync(2);

		msg = "Upload the Vector Tile Package";
		try {
			String vectorPackage = System.getProperty("user.dir") + "/Input/" + "LosAngeles" + ".vtpk";
			cfunction.CommonTextBox_Input(UPLOADINPUT_10_8_1_XPATH, vectorPackage);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(5);

		msg = "Enter title Name: " + title;
		try {
			cfunction.CommonTextBox_Input(TITLETITLE_10_8_1_XPAH, title);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		msg = "Enter tag: " + tag;
		try {
			cfunction.CommonTextBox_Input(TAGS_10_8_1_XPATH, tag);
			cfunction.pressEnterKey();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		msg = "Click Add Item to publish the layer";
		try {
			cfunction.CommmonJS_Click("//span[@id='addItem-btn']");
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {

			e.printStackTrace();
			try {
				cfunction.CommmonJS_Click(ADD_ITEM_DIALOG_BUTTON_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				CommonFunction.logStatusWithException("FAIL", msg, e1);
				e.printStackTrace();
			}
		}

		cfunction.sync(3);

		msg = "Verify Tile layer created";

		try {
			FEATURE_NAME_XPATH = "//h1[@id='itemtitle']//span[text()='" + TileName + "']";
			// cfunction.waitforelement(FEATURE_NAME_XPATH, 60);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
			driver.navigate().refresh();
		}

	}

	public void SaveTileLayer() throws Exception {
		boolean flag = true;
		boolean tilelayeflag = false;
		msg = "Enter Layer Title";
		try {
			if (projectVersion.startsWith("11") || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.Webelement_JSInput(FEATURE_TITLE_11_BUTTON_JSPATH, TileName);
			} else
				cfunction.CommonTextBox_Input(FEATURE_TITLE_BUTTON_XPATH, TileName);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		cfunction.sync(3);

		msg = "Enter Layer tags";
		try {
			if (projectVersion.startsWith("11")) {
				cfunction.Webelement_JSInput(FEATURE_TAG_11_JSPATH, "SampleCities");
			} else {
				cfunction.CommonTextBox_Input(FEATURE_TAG_XPATH, "SampleCities");
				WebElement element1 = driver.findElement(By.xpath(FEATURE_TAG_XPATH));
				element1.click();
			}
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {

			e.printStackTrace();

		}

		msg = "Enter layer Summary";
		try {
			if (projectVersion.startsWith("11")) {
				cfunction.Webelement_JSInput(FEATURE_SUMMARY_11_JSPATH, TileName);
			} else
				cfunction.CommonTextBox_Input(FEATURE_SUMMARY_XPATH, TileName);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		msg = "Click on folder dropdown";
		try {
			if (projectVersion.equalsIgnoreCase("11.0.0")) {
				cfunction.Webelement_JSClick(FEATURE_FOLDER_11_JSPATH);
			} else if ((projectVersion.equals("11.3.0")) || (projectVersion.equalsIgnoreCase("11.1.0"))
					|| (projectVersion.equalsIgnoreCase("11.4.0")) || (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
							|| (projectVersion.equalsIgnoreCase("12.0.0"))
							|| (projectVersion.equalsIgnoreCase("12.1.0")))
					|| (projectVersion.equalsIgnoreCase("11.2.0"))) {
				cfunction.sync(10);
				// cfunction.clickusingActions(ALL_MY_CONTENT_11_3_JSPATH);
				cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH);
				// } else if (projectVersion.equals("11.1.0") || projectVersion.equals("11.2.0")
				// ||
				// (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes11.5.0")))
				// {
				// cfunction.clickusingActions(ALL_MY_CONTENT_11_3_JSPATH);
				// cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH);
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

			if (!(projectVersion.equals("11.0.0") || projectVersion.equals("11.1.0") || projectVersion.equals("11.2.0")
					|| projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equals("11.5.0")) || (projectVersion.equalsIgnoreCase("12.0.0"))
					|| (projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0")))) {
				FOLDER_NAME_XPATH = "//table[contains(@id,'dialog_ItemPropertiesDlg') and contains(@id,'folders_menu')]//td[text()='"
						+ FolderName + "']";
				cfunction.sync(5);
				cfunction.Commmon_Click("xpath", FOLDER_NAME_XPATH);
				CommonFunction.logStatus("PASS", msg);

			} else if ((projectVersion.equals("11.3.0")) || (projectVersion.equalsIgnoreCase("11.4.0"))) {
				final String FOLDERITEMLIST11_3_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelector('calcite-combobox > calcite-combobox-item[text-label="
						+ FolderName + "]')";
				WebElement ele = cfunction.Webelement_JSPath(FOLDERITEMLIST11_3_JSPATH);
				if (ele == null) {
					cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH);
					CommonFunction.logStatus("PASS", msg);
				} else if (ele.getText().equalsIgnoreCase(FolderName)) {
					// cfunction.clickJS(ele);
					cfunction.ScrollToWebElement(FOLDERITEMLIST11_3_JSPATH);
					cfunction.clickusingActions(FOLDERITEMLIST11_3_JSPATH);
					CommonFunction.logStatus("PASS", msg);
				}

			} else if ((projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				final String FOLDERITEMLIST11_5_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelectorAll('calcite-combobox > calcite-combobox-item')";

				List<WebElement> list1 = cfunction.WebelementList_JSPath(FOLDERITEMLIST11_5_JSPATH);
				if (list1.size() > 0) {
					System.out.println(list1.size());

					for (int i = list1.size(); i > 0; i--) {
						cfunction.ScrollToWebElement(String.format(FOLDERITEM11_5_JSPATH, (i)));
						String listName = cfunction.Webelement_JSPath(String.format(FOLDERITEM11_5_JSPATH, (i)))
								.getText();

						if (listName.contains(FolderName)) {

							cfunction.clickusingActions(String.format(FOLDERITEM11_5_JSPATH, (i)));

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

		cfunction.sync(2);

		msg = "Click on Save button";

		/*
		 * msg = "Click on folder dropdown"; try { if (projectVersion.equals("11.1.0")
		 * || projectVersion.equals("11.2.0") || projectVersion.equals("11.3.0") ||
		 * (projectVersion.equalsIgnoreCase("11.4.0")) ||
		 * (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase
		 * ("Kubernetes11.5.0"))) {
		 * cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH); } else
		 * cfunction.Commmon_Click("xpath", FEATURE_FOLDER_XPATH); cfunction.sync(2);
		 * CommonFunction.logStatus("PASS", msg); } catch (Exception e) {
		 * CommonFunction.logStatusWithException("FAIL", msg, e); e.printStackTrace(); }
		 * 
		 * msg = "Select folder to save"; try { if (!(projectVersion.contains("11"))) {
		 * FOLDER_NAME_XPATH =
		 * "//table[contains(@id,'dialog_ItemPropertiesDlg') and contains(@id,'folders_menu')]//td[text()='"
		 * + FolderName + "']"; cfunction.waitforelement(FOLDER_NAME_XPATH);
		 * cfunction.Commmon_Click("xpath", FOLDER_NAME_XPATH); } cfunction.sync(2);
		 * CommonFunction.logStatus("PASS", msg); } catch (Exception e) {
		 * CommonFunction.logStatusWithException("FAIL", msg, e); e.printStackTrace(); }
		 * msg = "Click on done button";
		 */
		try {
			if (projectVersion.startsWith("11")) {
				cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
			} else
				cfunction.CommmonJS_Click(SAVE_FEATURE_BUTTON_XPATH);
			cfunction.sync(60);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Verify Tile layer created";

		try {
			if (projectVersion.equalsIgnoreCase("10.6.1")) {
				FEATURE_NAME_XPATH = "//h1[@id='itemtitle']//span[text()='" + TileName + "']";
			} else if (projectVersion.startsWith("11")) {
				FEATURE_NAME_XPATH = "//header//h1//span[text()='" + TileName + "']";
			} else {
				FEATURE_NAME_XPATH = "//header[@title='" + TileName + "']";
			}

			cfunction.waitforelement(FEATURE_NAME_XPATH, 60);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
			flag = false;
			driver.navigate().refresh();
		}

		msg = "Close Tile dialog box";
		cfunction.sync(20);
		if (!(driver.findElements(By.xpath("//div[contains(@class,'esriManageTiles manage-tiles')]")).isEmpty())) {
			try {
				cfunction.CommmonJS_Click(TILE_LAYER_CLOSE_XPATH);
				cfunction.sync(2);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
		}
		msg = "Navigate to content page";
		try {
			if (projectVersion.equalsIgnoreCase("10.6.1")) {
				hp.navigateToContentPage_10_6_1();
				verifycontentpage_10_6_1();
				CommonFunction.logStatus("PASS", msg);
			} else {
				hp.navigateToContentPage();
				verifycontentpage();
				CommonFunction.logStatus("PASS", msg);

			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(2);

		msg = "Click on folder link";
		try {
			List<WebElement> list = cfunction.WebelementList_JSPath(FOLDERLIST11_3_JSPATH);

			if ((projectVersion.equalsIgnoreCase("11.5.0")) || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				for (int i = list.size(); i > 0; i--) {
					cfunction.ScrollToWebElement(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.clickusingActions(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
						CommonFunction.logStatus("PASS", msg);
						tilelayeflag = true;
						break;
					}
				}
			} else {
				for (int i = list.size(); i > 0; i--) {
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAME11_3_JSPATH, (i)));
					// System.out.println(ele.getText());
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.ScrollToWebElement(String.format(FOLDERNAME11_3_JSPATH, (i)));
						cfunction.clickusingActions(String.format(FOLDERNAME11_3_JSPATH, (i)));
						CommonFunction.logStatus("PASS", msg);
						tilelayeflag = true;
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		if (tilelayeflag) {
			msg = "Verify Tile created under folder";
			try {
				if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
						|| (projectVersion.equalsIgnoreCase("11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
					cfunction.ScrollToElement("//h1[ text()='Content']");
					cfunction.sync(10);
					final String FOLDERFEATURE_JSPATH = "document.querySelector('div> arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row').shadowRoot.querySelector('div>a')";
					WebElement ele = cfunction.Webelement_JSPath(FOLDERFEATURE_JSPATH);
					// cfunction.ScrollToWebElement(FOLDERFEATURE_JSPATH);
					// System.out.println(ele.getText());
					if (ele.getText().equalsIgnoreCase(TileName)) {
						CommonFunction.logStatus("PASS", msg);
						featurecreated = true;
					} else {
						CommonFunction.logStatus("FAIL", msg);
						featurecreated = false;
						flagfeaturelayer = false;
					}

				} else {
					if (!(driver.findElements(By.xpath("//a[text()='" + TileName + "']")).isEmpty())) {
						CommonFunction.logStatus("PASS", msg);
						featurecreated = true;
					} else {
						CommonFunction.logStatus("FAIL", msg);
						featurecreated = false;
						flagfeaturelayer = false;
					}
				}
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
				featurecreated = false;
				flagfeaturelayer = false;
			}

		}
	}

	public void createfeaturelayer() throws Exception {
		cfunction.sync(5);
		msg = "Select feature layer type";
		try {

			if (projectVersion.equals("11.0.0") || projectVersion.equals("11.1.0")) {
				// cfunction.sync(20);
				/*
				 * WebElement elm5 = getWebelement_JSpath(ELECTRIC_UTIL_FEATURE_11_XPATH);
				 * cfunction.clickJS(elm5);
				 */
				cfunction.Webelement_JSClick(ELECTRIC_UTIL_FEATURE_11_XPATH);
			} else if (projectVersion.equals("11.2.0") || projectVersion.equals("11.3.0")
					|| (projectVersion.equalsIgnoreCase("11.4.0")) || (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				// cfunction.sync(20);
				// WebElement elm5 = getWebelement_JSpath(FEATURE_UTIL_TEMPLATE11_2_JSPATH);
				cfunction.clickusingActions(FEATURE_UTIL_TEMPLATE11_2_JSPATH);
				// cfunction.clickJS(elm5);
			} else {
				// cfunction.sync(20);
				cfunction.CommmonJS_Click(ELECTRIC_UTIL_FEATURE_XPATH);
			}
			cfunction.waitforpagetoload();
			// cfunction.sync(5);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);
		msg = "Select feature layer template";
		try {
			if (projectVersion.startsWith("11") || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0.0")
					|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.Webelement_JSClick(FEATURE_LAYER_TEMPLATE11_JSPATH);
			} else
				cfunction.Commmon_Click("xpath", FEATURE_LAYER_TEMPLATE_XPATH);
			// cfunction.sync(10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			try {
				if (projectVersion.startsWith("11")) {
					cfunction.waitforelement(FEATURE_LAYER_TEMPLATE11_1_JSPATH, 20);
					cfunction.Webelement_JSClick(FEATURE_LAYER_TEMPLATE11_1_JSPATH);
				}
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				CommonFunction.logStatusWithException("FAIL", msg, e1);
				e1.printStackTrace();
			}
		}

		cfunction.sync(2);
		msg = "Click on create for feature layer template";
		try {
			if (projectVersion.startsWith("11") || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0.0")
					|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
			} else
				cfunction.Commmon_Click("xpath", FEATURE_TEMPLATE_CREATE_BUTTON_XPATH);
			// cfunction.sync(10);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(5);

		msg = "Click on next for feature layer template";
		try {
			if (projectVersion.startsWith("11") || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0.0")
					|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
			} else
				cfunction.Commmon_Click("xpath", FEATURE_TEMPLATE_NEXT_BUTTON_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			featurecreated = false;
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();

		}

		cfunction.sync(2);

		if (featurecreated) {
			msg = "Click on zoom button one time";
			try {
				if (!(projectVersion.startsWith("11") || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0.0")
						|| projectVersion.startsWith("12.1.0"))) {
					cfunction.CommmonJS_Click(FEATURE_ZOOM_BUTTON_XPATH);
					CommonFunction.logStatus("PASS", msg);
				}
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			cfunction.sync(2);

			msg = "Click on zoom button second time";
			try {
				if (!(projectVersion.startsWith("11") || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0.0")
						|| projectVersion.startsWith("12.1.0"))) {
					cfunction.CommmonJS_Click(FEATURE_ZOOM_BUTTON_XPATH);
					// cfunction.sync(2);
					CommonFunction.logStatus("PASS", msg);
				}
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();

			}
			msg = "Click on zoom button third time";
			try {
				if (!(projectVersion.startsWith("11") || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0.0")
						|| projectVersion.startsWith("12.1.0"))) {
					cfunction.CommmonJS_Click(FEATURE_ZOOM_BUTTON_XPATH);
					// cfunction.sync(2);
					CommonFunction.logStatus("PASS", msg);
				}
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();

			}

			msg = "Click on zoom button fourth time";
			try {
				if (!(projectVersion.startsWith("11") || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0.0")
						|| projectVersion.equalsIgnoreCase("12.1.0"))) {
					cfunction.CommmonJS_Click(FEATURE_ZOOM_BUTTON_XPATH);
					CommonFunction.logStatus("PASS", msg);
				}
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();

			}

			cfunction.sync(3);

			msg = "Click on next for feature layer template";
			try {
				if (projectVersion.startsWith("11") || projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
						|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0.0")
						|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
					// cfunction.waitforvisibilityofelement(LAYER_OPTIONS_XPATH, 20);
					cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
				} else
					cfunction.CommmonJS_Click(FEATURE_TEMPLATE_NEXT_BUTTON_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();

			}

			cfunction.sync(3);

		}
	}

	public void SaveFeatureLayer() throws Exception {

		msg = "Enter Layer Title";
		try {
			if (projectVersion.startsWith("11")) {
				cfunction.Webelement_JSInput(FEATURE_TITLE_11_BUTTON_JSPATH, FeatureName);
			} else
				cfunction.CommonTextBox_Input(FEATURE_TITLE_BUTTON_XPATH, FeatureName);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			try {
				cfunction.Webelement_JSActInput(FEATURE_TITLE_11_BUTTON_JSPATH, FeatureName);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				CommonFunction.logStatusWithException("FAIL", msg, e1);
				e.printStackTrace();
			}
		}
		cfunction.sync(2);

		msg = "Enter Layer tags";
		try {
			if (projectVersion.startsWith("11")) {
				cfunction.Webelement_JSInput(FEATURE_TAG_11_JSPATH, "TestTag");
			} else {
				cfunction.CommonTextBox_Input(FEATURE_TAG_XPATH, "TestTag");
				WebElement element1 = driver.findElement(By.xpath(FEATURE_TAG_XPATH));
				element1.sendKeys(Keys.ENTER);
			}
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {

			e.printStackTrace();
		}

		msg = "Enter layer Summary";
		try {
			if (projectVersion.startsWith("11")) {
				cfunction.Webelement_JSInput(FEATURE_SUMMARY_11_JSPATH, FeatureName);
			} else
				cfunction.CommonTextBox_Input(FEATURE_SUMMARY_XPATH, FeatureName);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();

		}
		cfunction.sync(2);

		msg = "Click on folder dropdown";
		try {
			if (projectVersion.equalsIgnoreCase("11.0.0")) {
				cfunction.Webelement_JSClick(FEATURE_FOLDER_11_JSPATH);
			} else if ((projectVersion.equals("11.3.0")) || (projectVersion.equalsIgnoreCase("11.1.0"))
					|| (projectVersion.equalsIgnoreCase("11.4.0")) || (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
							|| (projectVersion.equalsIgnoreCase("12.0.0"))
							|| (projectVersion.equalsIgnoreCase("12.1.0"))
							|| (projectVersion.equalsIgnoreCase("11.2.0")))) {
				cfunction.sync(10);
				// cfunction.clickusingActions(ALL_MY_CONTENT_11_3_JSPATH);
				cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH);

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

			if (!(projectVersion.equals("11.0.0") || projectVersion.equals("11.1.0") || projectVersion.equals("11.2.0")
					|| projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equals("11.5.0")) || (projectVersion.equalsIgnoreCase("12.0.0"))
					|| (projectVersion.equalsIgnoreCase("12.1.0")))) {
				FOLDER_NAME_XPATH = "//table[contains(@id,'dialog_ItemPropertiesDlg') and contains(@id,'folders_menu')]//td[text()='"
						+ FolderName + "']";
				cfunction.sync(5);
				cfunction.Commmon_Click("xpath", FOLDER_NAME_XPATH);
				CommonFunction.logStatus("PASS", msg);

			} else if ((projectVersion.equals("11.3.0")) || (projectVersion.equalsIgnoreCase("11.4.0"))) {
				final String FOLDERITEMLIST11_3_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelector('calcite-combobox > calcite-combobox-item[text-label="
						+ FolderName + "]')";
				WebElement ele = cfunction.Webelement_JSPath(FOLDERITEMLIST11_3_JSPATH);
				if (ele == null) {
					cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH);
					CommonFunction.logStatus("PASS", msg);
				} else if (ele.getText().equalsIgnoreCase(FolderName)) {
					// cfunction.clickJS(ele);
					cfunction.ScrollToWebElement(FOLDERITEMLIST11_3_JSPATH);
					cfunction.clickusingActions(FOLDERITEMLIST11_3_JSPATH);
					CommonFunction.logStatus("PASS", msg);
				}

			} else if ((projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				final String FOLDERITEMLIST11_5_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelectorAll('calcite-combobox > calcite-combobox-item')";

				List<WebElement> list1 = cfunction.WebelementList_JSPath(FOLDERITEMLIST11_5_JSPATH);
				if (list1.size() > 0) {
					System.out.println(list1.size());

					for (int i = list1.size(); i > 0; i--) {
						cfunction.ScrollToWebElement(String.format(FOLDERITEM11_5_JSPATH, (i)));
						String listName = cfunction.Webelement_JSPath(String.format(FOLDERITEM11_5_JSPATH, (i)))
								.getText();

						if (listName.contains(FolderName)) {

							cfunction.clickusingActions(String.format(FOLDERITEM11_5_JSPATH, (i)));

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

		cfunction.sync(2);

		msg = "Click on Save button";
		try {
			cfunction.sync(10);
			if (projectVersion.startsWith("11")) {
				cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
			} else
				cfunction.CommmonJS_Click(SAVE_FEATURE_BUTTON_XPATH);
			cfunction.waitforpagetoload();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		cfunction.sync(30);

		msg = "Verify feature layer created";

		try {
			if (projectVersion.equalsIgnoreCase("10.6.1")) {
				FEATURE_NAME_XPATH = "//h1[@id='itemtitle']//span[text()='" + FeatureName + "']";
			} else if (projectVersion.startsWith("11")) {

				FEATURE_NAME_XPATH = "//header//h1//span[text()='" + FeatureName + "']";
			} else {
				FEATURE_NAME_XPATH = "//header[@title='" + FeatureName + "']";
			}
			cfunction.waitforelement(FEATURE_NAME_XPATH, 60);
			featurecreated = true;

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			featurecreated = false;

			e.printStackTrace();
			driver.navigate().refresh();
			cfunction.sync(5);
		}
		msg = "Navigate to content page";
		// https://qaclient10.esri.com/portal/sharing/servers/b1aa68318b6b4d47983c2405eb146e65/rest/services/SampleWorldCities/MapServer
		// https://qaclient10.esri.com/portal/sharing/servers/47ec12ebabf541ef9785772c58cdaf51/rest/services/SampleWorldCities/MapServer
		try {
			if (projectVersion.equalsIgnoreCase("10.6.1")) {
				hp.navigateToContentPage_10_6_1();
				verifycontentpage_10_6_1();
				CommonFunction.logStatus("PASS", msg);

			} else {
				hp.navigateToContentPage();
				verifycontentpage();
				CommonFunction.logStatus("PASS", msg);

			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		cfunction.sync(2);

		msg = "Click on folder link";
		try {
			List<WebElement> list = cfunction.WebelementList_JSPath(FOLDERLIST11_3_JSPATH);

			if ((projectVersion.equalsIgnoreCase("11.5.0")) || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				for (int i = list.size(); i > 0; i--) {
					cfunction.ScrollToWebElement(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.clickusingActions(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			} else {
				for (int i = list.size(); i > 0; i--) {
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAME11_3_JSPATH, (i)));
					// System.out.println(ele.getText());
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.ScrollToWebElement(String.format(FOLDERNAME11_3_JSPATH, (i)));
						cfunction.clickusingActions(String.format(FOLDERNAME11_3_JSPATH, (i)));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify feature layer created under folder";
		try {
			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.ScrollToElement("//h1[ text()='Content']");
				cfunction.sync(10);
				final String FOLDERFEATURE_JSPATH = "document.querySelector('div> arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row').shadowRoot.querySelector('div>a')";
				WebElement ele = cfunction.Webelement_JSPath(FOLDERFEATURE_JSPATH);
				// cfunction.ScrollToWebElement(FOLDERFEATURE_JSPATH);
				// System.out.println(ele.getText());
				if (ele.getText().equalsIgnoreCase(FeatureName)) {
					CommonFunction.logStatus("PASS", msg);
					featurecreated = true;
				} else {
					CommonFunction.logStatus("FAIL", msg);
					featurecreated = false;
					flagfeaturelayer = false;
				}

			} else {
				if (!(driver.findElements(By.xpath("//a[text()='" + FeatureName + "']")).isEmpty())) {
					CommonFunction.logStatus("PASS", msg);
					featurecreated = true;
				} else {
					CommonFunction.logStatus("FAIL", msg);
					featurecreated = false;
					flagfeaturelayer = false;
				}
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
			featurecreated = false;
			flagfeaturelayer = false;
		}

	}

	public void verifyFeatureLayerCreated(String layername) throws Exception {
		boolean layercreated = true;
		boolean flaglayer = true;

		msg = "Verify  layer is created and Overview page is opened";
		try {
			if (projectVersion.equalsIgnoreCase("10.6.1")) {
				FEATURE_NAME_XPATH = "//h1[@id='itemtitle']//span[text()='" + layername + "']";
			} else if (projectVersion.startsWith("11") || projectVersion.equalsIgnoreCase("Kubernetes11.5.0")) {

				FEATURE_NAME_XPATH = "//header//h1//span[text()='" + layername + "']";
			} else if (projectVersion.startsWith("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0"))
					|| projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) {
				FEATURE_NAME_XPATH = "//h1[text()='" + layername + "']";
			} else {
				FEATURE_NAME_XPATH = "//header[@title='" + layername + "']";
			}
			cfunction.waitforelement(FEATURE_NAME_XPATH, 120);

			layercreated = true;

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			layercreated = false;

			e.printStackTrace();
			driver.navigate().refresh();
			cfunction.sync(5);
		}

		msg = "Navigate to content page";
		try {
			if (projectVersion.equalsIgnoreCase("10.6.1")) {
				hp.navigateToContentPage_10_6_1();
				CommonFunction.logStatus("PASS", msg);
			} else {
				hp.navigateToContentPage();
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		cfunction.sync(2);

		msg = "Click on folder link";
		try {
			List<WebElement> list = cfunction.WebelementList_JSPath(FOLDERLIST11_3_JSPATH);
			if ((projectVersion.equalsIgnoreCase("11.5.0")) || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))
					|| projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) {
				for (int i = list.size(); i > 0; i--) {
					cfunction.ScrollToWebElement(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.clickusingActions(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			} else if (projectVersion.equalsIgnoreCase("10.9.1") || projectVersion.equalsIgnoreCase("10.8.1")) {
				List<WebElement> folderlist = cfunction.getListOfWebElements(FOLDERLIST10_9_1_XPATH);

				for (int i = folderlist.size(); i > 0; i--) {
					WebElement ele = driver.findElement(By.xpath(FOLDERLIST10_9_1_XPATH + "[" + i + "]"));
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.ScrollToWebElement(ele);
						ele.click();
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}

			} else {
				for (int i = list.size(); i > 0; i--) {
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAME11_3_JSPATH, (i)));
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.ScrollToWebElement(String.format(FOLDERNAME11_3_JSPATH, (i)));
						cfunction.clickusingActions(String.format(FOLDERNAME11_3_JSPATH, (i)));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify layer is created under the folder: " + FolderName;
		try {
			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0"))
					|| projectVersion.equalsIgnoreCase("Kubernetes12.1.0")
					|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.ScrollToElement("//h1[ text()='Content']");
				cfunction.sync(2);
				final String FOLDERFEATURE_JSPATH = "document.querySelector('div> arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row').shadowRoot.querySelector('div>a')";
				WebElement ele = cfunction.Webelement_JSPath(FOLDERFEATURE_JSPATH);

				if (ele.getText().equalsIgnoreCase(layername)) {
					CommonFunction.logStatus("PASS", msg);
					layercreated = true;
				} else {
					CommonFunction.logStatus("FAIL", msg);
					layercreated = false;
					flaglayer = false;
				}

			} else {
				if (!(driver.findElements(By.xpath("//a[text()='" + layername + "']")).isEmpty())) {
					CommonFunction.logStatus("PASS", msg);
					layercreated = true;
				} else {

					List<WebElement> folderlist = cfunction.getListOfWebElements(FOLDERLIST10_9_1_XPATH);

					for (int i = 0; i < folderlist.size(); i++) {
						// System.out.println(ele.getText());
						if (folderlist.get(i).getText().equalsIgnoreCase(FolderName)) {
							WebElement folder = folderlist.get(i);
							((JavascriptExecutor) driver)
									.executeScript("arguments[0].scrollIntoView({block:'center'});", folder);
							folderlist.get(i).click();
							break;
						}
					}
					if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
							|| (projectVersion.equalsIgnoreCase("11.5.0"))
							|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
							|| projectVersion.equalsIgnoreCase("Kubernetes12.1.0")
							|| (projectVersion.equalsIgnoreCase("12.0.0"))
							|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
						cfunction.ScrollToElement("//h1[ text()='Content']");
						final String FOLDERFEATURE_JSPATH = "document.querySelector('div> arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row').shadowRoot.querySelector('div>a')";
						WebElement ele = cfunction.Webelement_JSPath(FOLDERFEATURE_JSPATH);

						if (ele.getText().equalsIgnoreCase(layername)) {
							layercreated = true;
							CommonFunction.logStatus("PASS", msg);
						} else {
							layercreated = false;
							flaglayer = false;
							CommonFunction.logStatus("FAIL", msg);
						}
					} else {

						if (!(driver.findElements(By.xpath("//a[text()='" + layername + "']")).isEmpty())) {
							CommonFunction.logStatus("PASS", msg);
							layercreated = true;
						} else {
							CommonFunction.logStatus("FAIL", msg);
							layercreated = false;
							flaglayer = false;
						}

					}

				}
			}
		} catch (Exception e) {

			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
			layercreated = false;
			flaglayer = false;
		}

		if (layername.toLowerCase().contains("feature")) {
			featurecreated = layercreated;
			flagfeaturelayer = flaglayer;
		} else {
			tilecreated = layercreated;
			flagtilelayer = flaglayer;
		}

	}

	public void deleteCreated() throws Exception {
		// CommonFunction.waitforpagetoload();
		if (projectVersion.equalsIgnoreCase("10.6.1")) {
			hp.navigateToContentPage_10_6_1();
		} else {
			hp.navigateToContentPage();
		}

		msg = "Click on folder link " + FolderName;
		try {

			if (driver
					.findElements(By.xpath(
							"//*[@class='folder-list-title']//following-sibling::ul//a[text()='" + FolderName + "']"))
					.size() > 0) {
				cfunction.CommmonJS_Click(CREATED_FOLDER_XPATH);

				if (driver.findElements(By
						.xpath("//div[contains(@class,'table-select-rows')]//div[contains(@class,'table-select-row')]"))
						.size() > 0) {

					if (flagcontent) {
						msg = "Select all elements";
						try {
							cfunction.CommmonJS_Click(SELECT_ALL_CONTENT);
							cfunction.sync(2);
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();
							CommonFunction.logStatusWithException("FAIL", msg, e);

						}
						msg = "Click delete";
						try {
							cfunction.Commmon_Click("xpath", DELETE_ALL_CONTENT);
							cfunction.sync(2);
							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();
							try {
								// cfunction.CommmonJS_Click(SELECT_ALL_CONTENT);
								// cfunction.sync(2);
								cfunction.Commmon_Click("xpath", DELETE_ALL_CONTENT);
								CommonFunction.logStatus("PASS", msg);
							} catch (Exception e1) {
								e.printStackTrace();
								CommonFunction.logStatusWithException("FAIL", msg, e1);

							}

						}
						if (projectVersion.equalsIgnoreCase("10.9.0")) {
							msg = "Confirm delete";
							try {
								cfunction.Commmon_Click("xpath", DELETE_CONFIRMATION_10_9_XPATH);
								// cfunction.sync(43);
								CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								e.printStackTrace();
								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
							try {
								cfunction.waitforinvisibilityofelement(DELETE_CONFIRMATION_10_9_XPATH, 180);
								// CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								e.printStackTrace();
								// CommonFunction.logStatus("FAIL", msg+e);

							}
						} else {

							msg = "Confirm delete";
							try {
								if (projectVersion.equalsIgnoreCase("10.9.1") || projectVersion.startsWith("11.1.0")) {

									JavascriptExecutor jse = (JavascriptExecutor) driver;
									String JSPATH = "document.querySelector('body > div:nth-child(13) > calcite-modal > calcite-button:nth-child(4)')";
									WebElement elm = getWebelement_JSpath(JSPATH);
									elm.click();
								} else if (projectVersion.equalsIgnoreCase("11.2.0"))
									cfunction.confirmdelete11_2_0();
								else
									cfunction.Commmon_Click("xpath", DELETE_CONFIRMATION_XPATH);
								CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								try {
									cfunction.Commmon_Click("xpath", DELETE_ALL_CONFIRMATION_XPATH);
									CommonFunction.logStatus("PASS", msg);
								} catch (Exception e1) {
									e1.printStackTrace();
									CommonFunction.logStatusWithException("FAIL", msg, e1);

								}
							}
							try {
								cfunction.waitforinvisibilityofelement("//*[text()='Delete']", 180);
								// CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								cfunction.waitforinvisibilityofelement("//*[text()='Delete']", 30);
								e.printStackTrace();
								// CommonFunction.logStatus("FAIL", msg+e);

							}
						}
						msg = "Verify Items deleted";
						try {
							if (driver.findElements(By.xpath(
									"//div[contains(@class,'table-select-rows')]//div[contains(@class,'table-select-row')]"))
									.size() == 0) {
								CommonFunction.logStatus("PASS", msg);
							} else {
								CommonFunction.logStatus("FAIL", msg);
							}
						} catch (Exception e) {
							CommonFunction.logStatusWithException("FAIL", msg, e);
							e.printStackTrace();
						}
					} else {
						if (flagfeaturelayer && featurecreated) {
							msg = "Select feature layer";
							try {
								cfunction.CommmonJS_Click(
										"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'Feature')]//..//..//..//..//input");

								CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								e.printStackTrace();
								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
						} else {
							CommonFunction.logStatus("INFO", "Feature Not Created, hence nothing to delete");
						}
						if (flagtilelayer && tilecreated) {
							msg = "Select tile layer";
							try {
								cfunction.CommmonJS_Click(
										"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'Tile')]//..//..//..//..//input");

								CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								e.printStackTrace();
								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
						} else {
							CommonFunction.logStatus("INFO", "Tile Layer Not Created, hence nothing to delete");
						}

						if (flagwebappbuilder && webappcreated) {
							msg = "Select WebAppBuilder layer";
							try {
								cfunction.CommmonJS_Click(
										"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'Builder')]//..//..//..//..//input");

								CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								e.printStackTrace();
								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
							msg = "Select Census layer";
							try {
								cfunction.CommmonJS_Click(
										"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'"
												+ AddFromWebLayerTitle + "')]//..//..//..//..//input");

								CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								e.printStackTrace();
								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
						}

						else {
							CommonFunction.logStatus("INFO", "WebApp Not Created, hence nothing to delete");
						}
						if (flagmap && mapcreated) {
							msg = "Select Map layer";
							try {
								cfunction.CommmonJS_Click(
										"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'MAP')]//..//..//..//..//input");

								CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								e.printStackTrace();
								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
						} else {
							CommonFunction.logStatus("INFO", "WebMap Not Created, hence nothing to delete");
						}

						if (flagscenelayer && projectVersion.equalsIgnoreCase("11.1.0")
								|| flagscenelayer && projectVersion.equalsIgnoreCase("11.2.0")) {
							msg = "Select Scene layer";
							try {
								List<WebElement> input = cfunction.getListOfWebElements(
										"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'Scene')]//..//..//..//..//input");
								for (WebElement ele : input) {
									((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
								}
								cfunction.sync(2);
								CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								e.printStackTrace();
								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
						}

						msg = "Click delete";
						try {
							cfunction.Commmon_Click("xpath", DELETE_ALL_CONTENT);

							CommonFunction.logStatus("PASS", msg);
						} catch (Exception e) {
							e.printStackTrace();
							try {
								// cfunction.CommmonJS_Click(SELECT_ALL_CONTENT);
								// cfunction.sync(2);
								cfunction.CommmonJS_Click(DELETE_ALL_CONTENT);
								CommonFunction.logStatus("PASS", msg);
							} catch (Exception e1) {
								e.printStackTrace();
								CommonFunction.logStatusWithException("FAIL", msg, e1);

							}

						}

						if (projectVersion.equalsIgnoreCase("10.9.1") || projectVersion.startsWith("11")) {
							msg = "Confirm delete";
							try {

								if (projectVersion.equalsIgnoreCase("11.2.0"))
									cfunction.confirmdelete11_2_0();
								else
									cfunction.Commmon_Click("xpath", DELETE_CONFIRMATION_10_9_XPATH);
								cfunction.sync(2);

								CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								try {
									JavascriptExecutor jse = (JavascriptExecutor) driver;
									String JSPATH = "document.querySelector('body > div:nth-child(13) > calcite-modal > calcite-button:nth-child(4)')";
									WebElement elm = getWebelement_JSpath(JSPATH);
									elm.click();

								} catch (Exception e1) {
									e1.printStackTrace();
									CommonFunction.logStatusWithException("FAIL", msg, e1);

								}
							}

							try {
								cfunction.waitforinvisibilityofelement("//*[text()='Delete']", 180);
								// CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								e.printStackTrace();
								// CommonFunction.logStatus("FAIL", msg+e);

							}
						} else {

							msg = "Confirm delete";
							try {
								cfunction.Commmon_Click("xpath", DELETE_CONFIRMATION_XPATH);
								CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								e.printStackTrace();
								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
							try {
								cfunction.waitforinvisibilityofelement("//*[text()='Delete']", 180);
								// CommonFunction.logStatus("PASS", msg);
							} catch (Exception e) {
								e.printStackTrace();
								// CommonFunction.logStatus("FAIL", msg+e);

							}
						}

						if (flagfeaturelayer) {
							msg = "Verify feature layer deleted";
							try {
								if (driver.findElements(By.xpath(
										"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'"
												+ FeatureName + "')]//..//..//..//..//input"))
										.size() == 0) {

									CommonFunction.logStatus("PASS", msg);
								} else {
									CommonFunction.logStatus("FAIL", msg);
								}
							} catch (Exception e) {
								e.printStackTrace();
								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
						}
						if (flagtilelayer) {
							msg = "Verify Tile layer deleted";
							try {
								if (driver.findElements(By.xpath(
										"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'"
												+ TileName + "')]//..//..//..//..//input"))
										.size() == 0) {
									CommonFunction.logStatus("PASS", msg);
								} else {
									CommonFunction.logStatus("FAIL", msg);
								}
							} catch (Exception e) {
								e.printStackTrace();
								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
						}
						if (flagwebappbuilder) {
							msg = "Verify WebApp builder deleted";
							try {
								if (driver.findElements(By.xpath(
										"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'"
												+ WebAppBuilderName + "')]//..//..//..//..//input"))
										.size() == 0) {

									CommonFunction.logStatus("PASS", msg);
								} else {
									CommonFunction.logStatus("FAIL", msg);
								}
							} catch (Exception e) {
								e.printStackTrace();
								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
							msg = "Verify Census layer deleted";
							try {
								if (driver.findElements(By.xpath(
										"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'"
												+ AddFromWebLayerTitle + "')]//..//..//..//..//input"))
										.size() == 0) {

									CommonFunction.logStatus("PASS", msg);
								} else {
									CommonFunction.logStatus("FAIL", msg);
								}
							} catch (Exception e) {
								e.printStackTrace();
								CommonFunction.logStatusWithException("FAIL", msg, e);

							}

						}
						if (flagmap) {
							msg = "Verify Map layer deleted";
							try {
								if (driver.findElements(By.xpath(
										"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'"
												+ MapName + "')]//..//..//..//..//input"))
										.size() == 0) {
									CommonFunction.logStatus("PASS", msg);
								} else {
									CommonFunction.logStatus("FAIL", msg);
								}
							} catch (Exception e) {
								e.printStackTrace();
								CommonFunction.logStatusWithException("FAIL", msg, e);

							}
						}

						if (flagscenelayer && projectVersion.equalsIgnoreCase("11.1.0")
								|| flagscenelayer && projectVersion.equalsIgnoreCase("11.2.0")) {
							msg = "Verify Scene layer deleted";
							try {
								if (driver.findElements(By.xpath(
										"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'Scene')]//..//..//..//..//input"))
										.size() == 0) {

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
				}
				if (flagcontent) {

					try {
						if (cfunction.elementexist(CONTENT_INSIDE_FOLDER_XPATH, 10)) {
							cfunction.CommmonJS_Click(SELECT_ALL_CONTENT);
							cfunction.CommmonJS_Click(DELETE_ALL_CONTENT);
							cfunction.CommmonJS_Click(DELETE_CONFIRMATION_XPATH);
							cfunction.sync(3);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					msg = "Hover over created Folder";
					try {
						driver.navigate().refresh();
						cfunction.sync(4);
						WebElement element1 = driver.findElement(By.xpath(CREATED_FOLDER_XPATH));
						cfunction.hoverOnElement(element1);
						cfunction.hoverByAction(CREATED_FOLDER_XPATH);
						cfunction.sync(2);

						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);
					}
					msg = "Delete Folder";
					try {
						cfunction.CommmonJS_Click1(DELETE_FOLDER_XPATH);
						cfunction.sync(2);
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}

					msg = "Confirm delete";
					try {
						if (projectVersion.equalsIgnoreCase("11.1.0") || projectVersion.equalsIgnoreCase("11.2.0")) {
							cfunction.Commmon_Click("xpath", DELETE_CONFIRMATION_11_1_XPATH);
						} else
							cfunction.Commmon_Click("xpath", DELETE_CONFIRMATION_XPATH);
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
					try {
						cfunction.waitforinvisibilityofelement("//*[text()='Delete']", 100);
					} catch (Exception e) {
						e.printStackTrace();

					}

					msg = "Verify folder deleted";
					try {
						if (driver.findElements(By.xpath(CREATED_FOLDER_XPATH)).size() == 0) {
							CommonFunction.logStatus("PASS", msg);
						} else {
							CommonFunction.logStatus("FAIL", msg);
						}
					} catch (Exception e) {
						CommonFunction.logStatusWithException("FAIL", msg, e);
						e.printStackTrace();
					}
				}
			} else if (!flagcontent) {

				if (flagfeaturelayer) {

					msg = "Select feature layer";
					try {
						cfunction.CommmonJS_Click(
								"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'Feature')]//..//..//..//..//input");
						cfunction.sync(2);
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
				}
				if (flagtilelayer) {
					msg = "Select tile layer";
					try {
						cfunction.CommmonJS_Click(
								"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'Tile')]//..//..//..//..//input");

						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
				}
				if (flagwebappbuilder) {
					msg = "Select WebAppBuilder layer";
					try {
						cfunction.CommmonJS_Click(
								"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'Builder')]//..//..//..//..//input");

						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
					msg = "Select Census layer";
					try {
						cfunction.CommmonJS_Click(
								"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'"
										+ AddFromWebLayerTitle + "')]//..//..//..//..//label/input");

						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
				}
				if (flagmap) {
					msg = "Select Map layer";
					try {
						cfunction.CommmonJS_Click(
								"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'MAP')]//..//..//..//..//input");
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
				}

				if (flagscenelayer && projectVersion.equalsIgnoreCase("11.1.0")
						|| flagscenelayer && projectVersion.equalsIgnoreCase("11.2.0")) {
					msg = "Select Scene layer";
					try {
						List<WebElement> input = cfunction.getListOfWebElements(
								"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'Scene')]//..//..//..//..//input");
						for (WebElement ele : input) {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
						}
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
				}

				msg = "Click delete";
				try {
					cfunction.Commmon_Click("xpath", DELETE_ALL_CONTENT);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e) {
					e.printStackTrace();
					try {
						// cfunction.CommmonJS_Click(SELECT_ALL_CONTENT);
						// cfunction.sync(2);
						cfunction.CommmonJS_Click(DELETE_ALL_CONTENT);
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e1) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e1);

					}

				}

				if (projectVersion.equalsIgnoreCase("10.9.1") || projectVersion.startsWith("11")) {
					msg = "Confirm delete";
					try {
						cfunction.Commmon_Click("xpath", DELETE_CONFIRMATION_10_9_XPATH);
						cfunction.sync(43);
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						try {
							JavascriptExecutor jse = (JavascriptExecutor) driver;
							String JSPATH = "document.querySelector('body > div:nth-child(13) > calcite-modal > calcite-button:nth-child(4)')";
							WebElement elm = getWebelement_JSpath(JSPATH);
							elm.click();

						} catch (Exception e1) {
							e1.printStackTrace();
							CommonFunction.logStatusWithException("FAIL", msg, e1);

						}
					}

					try {
						cfunction.waitforinvisibilityofelement(DELETE_CONFIRMATION_10_9_XPATH, 60);
						// CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();
						// CommonFunction.logStatus("FAIL", msg+e);

					}
				} else {

					msg = "Confirm delete";
					try {
						cfunction.Commmon_Click("xpath", DELETE_CONFIRMATION_XPATH);
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
					try {
						cfunction.waitforinvisibilityofelement(DELETE_CONFIRMATION_XPATH, 300);
						// CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();
						// CommonFunction.logStatus("FAIL", msg+e);

					}
				}

				if (flagfeaturelayer) {
					msg = "Verify feature layer deleted";
					try {
						if (driver.findElements(By.xpath(
								"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'"
										+ FeatureName + "')]//..//..//..//..//input"))
								.size() == 0) {
							CommonFunction.logStatus("PASS", msg);
						} else {
							CommonFunction.logStatus("FAIL", msg);
						}
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
				}
				if (flagtilelayer) {
					msg = "Verify Tile layer deleted";
					try {
						if (driver.findElements(By.xpath(
								"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'"
										+ TileName + "')]//..//..//..//..//input"))
								.size() == 0) {
							CommonFunction.logStatus("PASS", msg);
						} else {
							CommonFunction.logStatus("FAIL", msg);
						}
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
				}
				if (flagwebappbuilder) {
					msg = "Verify WebApp builder deleted";
					try {
						if (driver.findElements(By.xpath(
								"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'"
										+ WebAppBuilderName + "')]//..//..//..//..//input"))
								.size() == 0) {
							CommonFunction.logStatus("PASS", msg);
						} else {
							CommonFunction.logStatus("FAIL", msg);
						}
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
					msg = "Verify Census layer deleted";
					try {
						if (driver.findElements(By.xpath(
								"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'"
										+ AddFromWebLayerTitle + "')]//..//..//..//..//input"))
								.size() == 0) {
							CommonFunction.logStatus("PASS", msg);
						} else {
							CommonFunction.logStatus("FAIL", msg);
						}
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}

				}
				if (flagmap) {
					msg = "Verify Map layer deleted";
					try {
						if (driver.findElements(By.xpath(
								"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'"
										+ MapName + "')]//..//..//..//..//input"))
								.size() == 0) {
							CommonFunction.logStatus("PASS", msg);
						} else {
							CommonFunction.logStatus("FAIL", msg);
						}
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}
				}

				if (flagscenelayer && projectVersion.equalsIgnoreCase("11.1.0")
						|| flagscenelayer && projectVersion.equalsIgnoreCase("11.2.0")) {
					msg = "Verify Scene layer deleted";
					try {
						if (driver.findElements(By.xpath(
								"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'Scene')]//..//..//..//..//input"))
								.size() == 0) {
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

			else {
				CommonFunction.logStatus("FAIL", "Not able to select folder to delete content");
			}
			if (projectVersion.equalsIgnoreCase("11.1.0") || projectVersion.equalsIgnoreCase("11.2.0")) {
				deleteEsitingSceneLayer();
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
	}

	public void deleteCreated11_3() throws Exception {
		boolean flag = false;
		if (projectVersion.equalsIgnoreCase("10.6.1")) {
			hp.navigateToContentPage_10_6_1();
		} else {
			hp.navigateToContentPage();
		}

		msg = "Click on folder link " + FolderName;
		try {
			cfunction.sync(5);
			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))) {
				List<WebElement> list = cfunction.WebelementList_JSPath(FOLDERLIST11_3_JSPATH);
				for (int i = list.size(); i > 0; i--) {
					// cfunction.ScrollToWebElement(String.format(FOLDERNAME11_3_JSPATH, (i + 1)));
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAME11_3_JSPATH, (i)));

					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.Webelement_JSClick(String.format(FOLDERNAME11_3_JSPATH, (i)));
						cfunction.hoverByAction(ele);
						CommonFunction.logStatus("PASS", msg);
						msg = "Click on Delete button";
						String path = "(//calcite-action[contains(@id,'delete-button')])" + "[" + (i - 1) + "]";
						cfunction.hoverByAction(path);
						cfunction.CommmonJS_Click(path);
						// cfunction.ScrollToWebElement(String.format(FOLDERNAME11_3_JSPATH, (i + 1)));
						// cfunction.clickusingActions(String.format(FOLDERNAME11_3_JSPATH, (i + 1)));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			} else if ((projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				List<WebElement> list = cfunction.WebelementList_JSPath(FOLDERLIST11_3_JSPATH);
				for (int i = list.size(); i > 0; i--) {
					// cfunction.ScrollToWebElement(String.format(FOLDERNAMELIST11_5_JSPATH, (i +
					// 1)));
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.ScrollToWebElement(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
						cfunction.clickusingActions(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
						CommonFunction.logStatus("PASS", msg);
						msg = "Click on Delete button";
						String button = "document.querySelector(\"arcgis-item-browser > arcgis-item-browser-filters > arcgis-item-browser-filter-folder > arcgis-browser-filter > div > calcite-list > calcite-list-item:nth-child(%s) > calcite-dropdown > calcite-action\").shadowRoot.querySelector(\"div>button\")";
						// cfunction.ScrollToWebElement(String.format(FOLDERNAME11_3_JSPATH, (i + 1)));
						cfunction.clickusingActions(String.format(button, (i)));
						String path = "(//calcite-dropdown-item[@icon-start='trash'])" + "[" + (i - 1) + "]";
						// cfunction.hoverByAction(path);
						cfunction.CommmonJS_Click(path);

						CommonFunction.logStatus("PASS", msg);
						break;
					}

				}
			}

			msg = "Confirm delete";
			try {
				if (projectVersion.equalsIgnoreCase("11.1.0") || projectVersion.equalsIgnoreCase("11.2.0")) {
					cfunction.Commmon_Click("xpath", DELETE_CONFIRMATION_11_1_XPATH);
					CommonFunction.logStatus("PASS", msg);
				} else if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
						|| (projectVersion.equalsIgnoreCase("11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
					cfunction.sync(5);
					try {
						cfunction.clickusingActions(DELETE_CONFIRMATION_11_4_XPATH);
					} catch (Exception e) {
						try {
							cfunction.clickusingActions(DELETE_CONFIRMATION_11_4_1_XPATH);
						} catch (Exception e1) {
							CommonFunction.logStatus("PASS", "No Delete button found");
						}
					}

					CommonFunction.logStatus("PASS", msg);
				} else {
					cfunction.Commmon_Click("xpath", DELETE_CONFIRMATION_XPATH);
					CommonFunction.logStatus("PASS", msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}

			try {
				cfunction.waitforinvisibilityofelement("//*[text()='Delete']", 120);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void Addfromweb() throws Exception {
		msg = "Navigate to content page";

		try {
			hp.navigateToContentPage();
			verifycontentpage();
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		cfunction.sync(2);

		if (projectVersion.equals("10.9.1") || projectVersion.startsWith("11")) {

			msg = "Click on New Item button";
			try {
				cfunction.CommmonJS_Click(CREATE_BUTTON_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			cfunction.sync(2);

			msg = "Click on from web";
			try {
				cfunction.sync(5);
				String JSPATH = "document.querySelector('arcgis-new-item-pages-home').shadowRoot.querySelector('arcgis-new-item-create-layers-list').shadowRoot.querySelector('nav > ul > li:nth-child(2) > button > div.card__icon')";
				WebElement elm5 = getWebelement_JSpath(JSPATH);
				cfunction.clickJS(elm5);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();

			}

			msg = "Enter copied url";
			try {
				cfunction.sync(5);
				String JSPATH = "document.querySelector('arcgis-new-item-pages-url-type').shadowRoot.querySelector('arcgis-new-item-url').shadowRoot.querySelector('#item-properties-url')";
				WebElement elm5 = getWebelement_JSpath(JSPATH);
				Actions act = new Actions(driver);
				act.sendKeys(elm5, webserviceurl).build().perform();
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			cfunction.sync(5);

			msg = "Click Next";
			try {
				if ((projectVersion.startsWith("11"))) {
					cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
				} else {
					String JSPATH = "document.querySelector('body > div:nth-child(11) > arcgis-new-item > calcite-modal > calcite-button:nth-child(5)')";
					WebElement elm5 = getWebelement_JSpath(JSPATH);
					elm5.click();
					if (Browser.equalsIgnoreCase("firefox")) {
						cfunction.sync(2);
						elm5.click();
					} else
						cfunction.sync(5);
				}
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
			}

			msg = "Enter Title ";
			try {
				cfunction.sync(5);
				if (projectVersion.startsWith("11")) {
					cfunction.Webelement_JSActInput(INPUT_TITLE11_JSPATH, AddFromWebLayerTitle);
				} else {
					String JSPATH = "document.querySelector('arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-item-properties-title').shadowRoot.querySelector('#item-properties-title')";
					WebElement elm5 = getWebelement_JSpath(JSPATH);
					Actions act = new Actions(driver);
					act.sendKeys(elm5, AddFromWebLayerTitle).build().perform();
				}
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				try {
					// System.out.println("In catch block of Title");
					String JSPATH = "document.querySelector('arcgis-new-item-pages-url-type').shadowRoot.querySelector('arcgis-select').shadowRoot.querySelector('#no-selection')";
					WebElement elmselect = getWebelement_JSpath(JSPATH);
					Actions act = new Actions(driver);
					act.moveToElement(elmselect).click().build().perform();
					cfunction.sync(2);
					String eleJS = "document.querySelector('arcgis-new-item-pages-url-type').shadowRoot.querySelector('arcgis-select').shadowRoot.querySelector('#ags').shadowRoot.querySelector('div.text-container > p')";
					WebElement elmdrop = getWebelement_JSpath(eleJS);
					act.moveToElement(elmdrop).click().build().perform();
					if (projectVersion.equalsIgnoreCase("11.0.0") && Browser.equalsIgnoreCase("firefox")) {
						cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
					} else {
						cfunction.sync(2);
						String JSPATH1 = "document.querySelector('body > div:nth-child(11) > arcgis-new-item > calcite-modal > calcite-button:nth-child(5)')";
						WebElement elm5 = getWebelement_JSpath(JSPATH1);
						elm5.click();
						if (Browser.equalsIgnoreCase("firefox")) {
							cfunction.sync(2);
							elm5.click();
						} else
							cfunction.sync(5);
					}
					if (projectVersion.startsWith("11")) {
						cfunction.Webelement_JSInput(INPUT_TITLE11_JSPATH, AddFromWebLayerTitle);
					} else {
						String JSPATH2 = "document.querySelector('arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-item-properties-title').shadowRoot.querySelector('#item-properties-title')";
						WebElement elm6 = getWebelement_JSpath(JSPATH2);
						act.sendKeys(elm6, AddFromWebLayerTitle).build().perform();
					}
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e1) {
					CommonFunction.logStatusWithException("FAIL", msg, e1);
					e1.printStackTrace();
				}
			}

			msg = "Enter tags for service from web";
			try {
				if (projectVersion.startsWith("11")) {
					cfunction.Webelement_JSInput(TITLE_TAG11_JSPATH, "TestTag");
				} else {
					String JSPATH = "document.querySelector(\"arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-item-properties-tags\").shadowRoot.querySelector(\"calcite-label > label > calcite-combobox\").shadowRoot.querySelector(\"input\")";
					WebElement elm5 = getWebelement_JSpath(JSPATH);
					Actions act = new Actions(driver);
					act.sendKeys(elm5, "TestTag").build().perform();
				}
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				try {
					cfunction.Webelement_JSInput2(TITLE_TAG11_JSPATH, "TestTag");
				} catch (Exception e1) {
					CommonFunction.logStatusWithException("FAIL", msg, e1);
					e1.printStackTrace();
				}
			}

			cfunction.sync(10);

			msg = "Click on Save button ";
			try {
				if (projectVersion.startsWith("11")) {
					cfunction.Webelement_JSClick(SAVE_BUTTON_11_JSPATH);
				} else {
					String JSPATH = "document.querySelector('body > div:nth-child(11) > arcgis-new-item > calcite-modal > calcite-button:nth-child(5)')";
					WebElement elm5 = getWebelement_JSpath(JSPATH);
					elm5.click();
				}
				cfunction.sync(5);
			} catch (Exception e) {
				try {
					cfunction.Webelement_JSClick(SAVE11_FF_XPATH);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e1) {
					CommonFunction.logStatusWithException("FAIL", msg, e1);
					e1.printStackTrace();
				}
			}
		}

		else {
			msg = "Click on Add Item ";
			try {
				cfunction.CommmonJS_Click(ADD_BUTTON_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();

			}

			cfunction.sync(2);

			msg = "Click on from web";
			try {
				cfunction.CommmonJS_Click(ADD_FROM_WEB_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();

			}

			cfunction.sync(2);

			msg = "Wait for widget";
			try {

				cfunction.waitforelement(ADD_FROM_WEB_WIDGET_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			cfunction.sync(3);

			msg = "Enter copied url";
			try {
				cfunction.CommonTextBox_Input(ENTER_URL_XPATH, webserviceurl);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			cfunction.sync(3);

			msg = "Click on Title";
			try {
				// cfunction.CommmonJS_Click(ENTER_TITLE_XPATH);
				cfunction.Commmon_Click("xpath", ENTER_TITLE_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			cfunction.sync(2);

			msg = "Enter Title ";
			try {
				// cfunction.CommonTextBox_InputJS(ENTER_TITLE_XPATH, AddFromWebLayerTitle);
				cfunction.CommonTextBox_Input(ENTER_TITLE_XPATH, AddFromWebLayerTitle);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
			cfunction.sync(3);

			msg = "Enter  tags for service from web";
			try {
				cfunction.CommonTextBox_Input(ADD_WEB_TAG_XPATH, "TestTag");
				WebElement element1 = driver.findElement(By.xpath(ADD_WEB_TAG_XPATH));
				element1.click();
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			cfunction.sync(2);

			msg = "Click Done";
			try {
				cfunction.sync(1);
				cfunction.CommmonJS_Click("//span[@id='addItem-btn']");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {

				e.printStackTrace();
				try {
					cfunction.CommmonJS_Click(ADD_ITEM_DIALOG_BUTTON_XPATH);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e1) {
					CommonFunction.logStatusWithException("FAIL", msg, e1);
					e.printStackTrace();
				}
			}

			cfunction.sync(3);

		}
		msg = "Verify layer created";
		try {
			cfunction.waitforelement(ADD_ITEM_NAME_XPATH, 120);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {

			e.printStackTrace();
			try {
				if (cfunction.elementexist(ADD_ITEM_DIALOG_BUTTON_XPATH)) {
					cfunction.CommmonJS_Click(ADD_ITEM_DIALOG_BUTTON_XPATH);
				}
				cfunction.waitforelement(ADD_ITEM_NAME_XPATH, 60);
				cfunction.sync(3);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				CommonFunction.logStatusWithException("FAIL", msg, e1);
				driver.navigate().refresh();
				e1.printStackTrace();
			}
		}

		cfunction.sync(10);

		msg = "Navigate to content page";

		try {
			hp.navigateToContentPage();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}
		cfunction.sync(2);

	}

	public void Addfromweb_11_3_0() throws Exception {
		msg = "Navigate to content page";

		try {
			hp.navigateToContentPage();
			verifycontentpage();
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		cfunction.sync(2);

		if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
				|| (projectVersion.equalsIgnoreCase("11.5.0")) || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
				|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {

			msg = "Click on New Item button";
			try {
				cfunction.CommmonJS_Click(CREATE_ITEM11_3_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			cfunction.sync(5);

			msg = "Click on from web";
			try {
				String JSPATH = "document.querySelector('arcgis-new-item-pages-home').shadowRoot.querySelector('arcgis-new-item-create-layers-list').shadowRoot.querySelector('nav > ul > li:nth-child(2)')";
				WebElement elm5 = getWebelement_JSpath(JSPATH);
				cfunction.clickusingActions(JSPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();

			}
			cfunction.sync(5);

			msg = "Enter copied url";
			try {
				String JSPATH = "document.querySelector('arcgis-new-item-pages-url-type').shadowRoot.querySelector('arcgis-new-item-url').shadowRoot.querySelector('#item-properties-url')";
				WebElement elm5 = getWebelement_JSpath(JSPATH);
				Actions act = new Actions(driver);
				act.sendKeys(elm5, webserviceurl).build().perform();
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			cfunction.sync(5);

			msg = "Click Next";
			try {

				if ((projectVersion.startsWith("11")) || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))) {
					cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
				} else {
					cfunction.sync(5);
					String JSPATH = "document.querySelector('body > div:nth-child(11) > arcgis-new-item > calcite-modal > calcite-button:nth-child(5)')";
					WebElement elm5 = getWebelement_JSpath(JSPATH);
					elm5.click();
					if (Browser.equalsIgnoreCase("firefox")) {
						cfunction.sync(2);
						elm5.click();
					} else
						cfunction.sync(5);
				}
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
			}

			cfunction.sync(5);

			msg = "Enter Title ";
			try {
				if (projectVersion.startsWith("11") || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))) {
					cfunction.Webelement_JSActInput(INPUT_TITLE11_JSPATH, AddFromWebLayerTitle);
				} else {
					String JSPATH = "document.querySelector('arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-item-properties-title').shadowRoot.querySelector('#item-properties-title')";
					WebElement elm5 = getWebelement_JSpath(JSPATH);
					Actions act = new Actions(driver);
					act.sendKeys(elm5, AddFromWebLayerTitle).build().perform();
				}
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				try {
					//// System.out.println("In catch block of Title");
					String JSPATH = "document.querySelector('arcgis-new-item-pages-url-type').shadowRoot.querySelector('arcgis-select').shadowRoot.querySelector('#no-selection')";
					WebElement elmselect = getWebelement_JSpath(JSPATH);
					Actions act = new Actions(driver);
					act.moveToElement(elmselect).click().build().perform();
					cfunction.sync(2);
					String eleJS = "document.querySelector('arcgis-new-item-pages-url-type').shadowRoot.querySelector('arcgis-select').shadowRoot.querySelector('#ags').shadowRoot.querySelector('div.text-container > p')";
					WebElement elmdrop = getWebelement_JSpath(eleJS);
					act.moveToElement(elmdrop).click().build().perform();
					if (projectVersion.equalsIgnoreCase("11.0.0") && Browser.equalsIgnoreCase("firefox")) {
						cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
					} else {
						cfunction.sync(2);
						String JSPATH1 = "document.querySelector('body > div:nth-child(11) > arcgis-new-item > calcite-modal > calcite-button:nth-child(5)')";
						WebElement elm5 = getWebelement_JSpath(JSPATH1);
						elm5.click();
						if (Browser.equalsIgnoreCase("firefox")) {
							cfunction.sync(2);
							elm5.click();
						} else
							cfunction.sync(5);
					}
					if (projectVersion.startsWith("11") || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))) {
						cfunction.Webelement_JSInput(INPUT_TITLE11_JSPATH, AddFromWebLayerTitle);
					} else {
						String JSPATH2 = "document.querySelector('arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-item-properties-title').shadowRoot.querySelector('#item-properties-title')";
						WebElement elm6 = getWebelement_JSpath(JSPATH2);
						act.sendKeys(elm6, AddFromWebLayerTitle).build().perform();
					}
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e1) {
					CommonFunction.logStatusWithException("FAIL", msg, e1);
					e1.printStackTrace();
				}
			}

			cfunction.sync(3);

			msg = "Enter tags for service from web";
			try {
				if (projectVersion.startsWith("11") || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))) {
					cfunction.Webelement_JSInput(TITLE_TAG11_JSPATH, "TestTag");
				} else {
					String JSPATH = "document.querySelector(\"arcgis-new-item-pages-item-properties > arcgis-item-properties > arcgis-item-properties-tags\").shadowRoot.querySelector(\"calcite-label > label > calcite-combobox\").shadowRoot.querySelector(\"input\")";
					WebElement elm5 = getWebelement_JSpath(JSPATH);
					Actions act = new Actions(driver);
					act.sendKeys(elm5, "TestTag").build().perform();
				}
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				try {
					cfunction.Webelement_JSInput2(TITLE_TAG11_JSPATH, "TestTag");
				} catch (Exception e1) {
					CommonFunction.logStatusWithException("FAIL", msg, e1);
					e1.printStackTrace();
				}
			}

			cfunction.sync(5);

			SelectFolder(); // Select the created folder .............................................

			/*
			 * msg = "Click on folder dropdown"; try { if
			 * (projectVersion.equalsIgnoreCase("11.0.0")) {
			 * cfunction.Webelement_JSClick(FEATURE_FOLDER_11_JSPATH); } else if
			 * (projectVersion.equals("11.3.0") ||
			 * (projectVersion.equalsIgnoreCase("11.4.0"))) { cfunction.sync(10);
			 * cfunction.clickusingActions(ALL_MY_CONTENT_11_3_JSPATH);
			 * cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH); } else if
			 * (projectVersion.equals("11.1.0") || projectVersion.equals("11.2.0") ||
			 * (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase
			 * ("Kubernetes11.5.0"))) { //
			 * cfunction.clickusingActions(ALL_MY_CONTENT_11_3_JSPATH);
			 * cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH); } else {
			 * cfunction.Commmon_Click("xpath", FEATURE_FOLDER_XPATH); }
			 * CommonFunction.logStatus("PASS", msg); } catch (Exception e) {
			 * CommonFunction.logStatusWithException("FAIL", msg, e); e.printStackTrace(); }
			 * msg = "Select folder to save"; try {
			 * 
			 * if (!(projectVersion.equals("11.0.0") || projectVersion.equals("11.1.0") ||
			 * projectVersion.equals("11.2.0") || projectVersion.equals("11.3.0") ||
			 * (projectVersion.equalsIgnoreCase("11.4.0")) ||
			 * (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase
			 * ("Kubernetes11.5.0")))) { FOLDER_NAME_XPATH =
			 * "//table[contains(@id,'dialog_ItemPropertiesDlg') and contains(@id,'folders_menu')]//td[text()='"
			 * + FolderName + "']"; cfunction.sync(10); cfunction.Commmon_Click("xpath",
			 * FOLDER_NAME_XPATH); cfunction.sync(2);
			 * 
			 * } else if ((projectVersion.equals("11.3.0")) ||
			 * (projectVersion.equalsIgnoreCase("11.4.0"))) { final String
			 * FOLDERITEMLIST11_3_JSPATH =
			 * "document.querySelector('arcgis-folder-picker').shadowRoot.querySelector('calcite-combobox > calcite-combobox-item[text-label="
			 * + FolderName + "]')"; WebElement ele =
			 * cfunction.Webelement_JSPath(FOLDERITEMLIST11_3_JSPATH); if (ele == null) {
			 * cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH); } else if
			 * (ele.getText().equalsIgnoreCase(FolderName)) { // cfunction.clickJS(ele);
			 * cfunction.ScrollToWebElement(FOLDERITEMLIST11_3_JSPATH);
			 * cfunction.clickusingActions(FOLDERITEMLIST11_3_JSPATH);
			 * 
			 * }
			 * 
			 * } else if ((projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.
			 * equalsIgnoreCase("Kubernetes11.5.0"))) { final String
			 * FOLDERITEMLIST11_5_JSPATH =
			 * "document.querySelector('arcgis-folder-picker').shadowRoot.querySelectorAll('calcite-combobox > calcite-combobox-item')"
			 * ;
			 * 
			 * List<WebElement> list1 =
			 * cfunction.WebelementList_JSPath(FOLDERITEMLIST11_5_JSPATH); if (list1.size()
			 * > 0) { System.out.println(list1.size());
			 * 
			 * for (int i = 0; i < list1.size(); i++) {
			 * cfunction.ScrollToWebElement(String.format(FOLDERITEM11_5_JSPATH, (i + 1)));
			 * String listName =
			 * cfunction.Webelement_JSPath(String.format(FOLDERITEM11_5_JSPATH, (i + 1)))
			 * .getText();
			 * 
			 * if (listName.contains(FolderName)) {
			 * 
			 * cfunction.clickusingActions(String.format(FOLDERITEM11_5_JSPATH, (i + 1)));
			 * 
			 * CommonFunction.logStatus("PASS", msg); break; } } } }
			 * 
			 * } catch (Exception e) { CommonFunction.logStatusWithException("FAIL", msg,
			 * e); e.printStackTrace(); }
			 */
			msg = "Click on Save button ";
			try {
				if (projectVersion.equalsIgnoreCase("11.0.0") || projectVersion.equalsIgnoreCase("11.1.0")
						|| projectVersion.equalsIgnoreCase("11.2.0") || projectVersion.equalsIgnoreCase("11.3.0")
						|| (projectVersion.equalsIgnoreCase("11.4.0"))) {
					cfunction.Webelement_JSClick(SAVE_BUTTON_11_JSPATH);
					CommonFunction.logStatus("PASS", msg);
				} else if ((projectVersion.equalsIgnoreCase("11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
					cfunction.CommmonJS_Click("//calcite-dialog//calcite-button[@data-id='nextButton']");
					CommonFunction.logStatus("PASS", msg);
				} else {
					String JSPATH = "document.querySelector('body > div:nth-child(11) > arcgis-new-item > calcite-modal > calcite-button:nth-child(5)')";
					WebElement elm5 = getWebelement_JSpath(JSPATH);
					elm5.click();
					CommonFunction.logStatus("PASS", msg);
				}
				cfunction.sync(5);
			} catch (Exception e) {
				try {
					cfunction.Webelement_JSClick(SAVE11_FF_XPATH);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e1) {
					CommonFunction.logStatusWithException("FAIL", msg, e1);
					e1.printStackTrace();
				}
			}
		}

		else {
			msg = "Click on Add Item ";
			try {
				cfunction.CommmonJS_Click(ADD_BUTTON_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();

			}
			cfunction.sync(2);

			msg = "Click on from web";
			try {
				cfunction.CommmonJS_Click(ADD_FROM_WEB_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();

			}
			cfunction.sync(2);

			msg = "Wait for widget";
			try {

				cfunction.waitforelement(ADD_FROM_WEB_WIDGET_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
			cfunction.sync(3);

			msg = "Enter copied url";
			try {
				cfunction.CommonTextBox_Input(ENTER_URL_XPATH, webserviceurl);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
			cfunction.sync(3);

			msg = "Click on Title";
			try {
				// cfunction.CommmonJS_Click(ENTER_TITLE_XPATH);
				cfunction.Commmon_Click("xpath", ENTER_TITLE_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
			cfunction.sync(2);

			msg = "Enter Title ";
			try {
				// cfunction.CommonTextBox_InputJS(ENTER_TITLE_XPATH, AddFromWebLayerTitle);
				cfunction.CommonTextBox_Input(ENTER_TITLE_XPATH, AddFromWebLayerTitle);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
			cfunction.sync(3);

			msg = "Enter  tags for service from web";
			try {
				cfunction.CommonTextBox_Input(ADD_WEB_TAG_XPATH, "TestTag");
				WebElement element1 = driver.findElement(By.xpath(ADD_WEB_TAG_XPATH));
				element1.click();
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
					cfunction.sync(10);
					cfunction.clickusingActions(ALL_MY_CONTENT_11_3_JSPATH);
					cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH);
				} else if (projectVersion.equals("11.1.0") || projectVersion.equals("11.2.0")
						|| (projectVersion.equalsIgnoreCase("11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
					// cfunction.clickusingActions(ALL_MY_CONTENT_11_3_JSPATH);
					cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH);
				} else {
					cfunction.Commmon_Click("xpath", FEATURE_FOLDER_XPATH);
				}
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
			cfunction.sync(2);

			msg = "Select folder to save";
			try {

				if (!(projectVersion.equals("11.0.0") || projectVersion.equals("11.1.0")
						|| projectVersion.equals("11.2.0") || projectVersion.equals("11.3.0")
						|| (projectVersion.equalsIgnoreCase("11.4.0")) || (projectVersion.equalsIgnoreCase("11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("12.0.0"))
						|| (projectVersion.equalsIgnoreCase("12.1.0")))) {
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

				} else if ((projectVersion.equalsIgnoreCase("11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
					final String FOLDERITEMLIST11_5_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelectorAll('calcite-combobox > calcite-combobox-item')";

					List<WebElement> list1 = cfunction.WebelementList_JSPath(FOLDERITEMLIST11_5_JSPATH);
					if (list1.size() > 0) {
						System.out.println(list1.size());

						for (int i = list1.size(); i > 0; i--) {
							cfunction.ScrollToWebElement(String.format(FOLDERITEM11_5_JSPATH, (i)));
							String listName = cfunction.Webelement_JSPath(String.format(FOLDERITEM11_5_JSPATH, (i)))
									.getText();

							if (listName.contains(FolderName)) {

								cfunction.clickusingActions(String.format(FOLDERITEM11_5_JSPATH, (i)));

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

			msg = "Click Done";
			try {
				cfunction.sync(1);
				cfunction.CommmonJS_Click("//span[@id='addItem-btn']");
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {

				e.printStackTrace();
				try {
					cfunction.CommmonJS_Click(ADD_ITEM_DIALOG_BUTTON_XPATH);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e1) {
					CommonFunction.logStatusWithException("FAIL", msg, e1);
					e.printStackTrace();
				}
			}
			cfunction.sync(3);

		}
		msg = "Verify layer created";
		try {
			cfunction.waitforelement(ADD_ITEM_NAME_XPATH, 120);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {

			e.printStackTrace();
			try {
				if (cfunction.elementexist(ADD_ITEM_DIALOG_BUTTON_XPATH)) {
					cfunction.CommmonJS_Click(ADD_ITEM_DIALOG_BUTTON_XPATH);
				}
				cfunction.waitforelement(ADD_ITEM_NAME_XPATH, 60);
				cfunction.sync(3);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				CommonFunction.logStatusWithException("FAIL", msg, e1);
				driver.navigate().refresh();
				cfunction.sync(10);
				e1.printStackTrace();
			}
		}
		cfunction.sync(10);

		msg = "Navigate to content page";

		try {
			hp.navigateToContentPage();
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(2);

	}

	public void Addfromweb_10_6_1() throws Exception {

		msg = "Navigate to content page";

		try {
			hp.navigateToContentPage_10_6_1();
			verifycontentpage_10_6_1();
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(2);

		msg = "Click on Add Item ";
		try {
			cfunction.CommmonJS_Click(ADD_BUTTON_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();

		}
		cfunction.sync(2);

		msg = "Click on from web";
		try {
			cfunction.CommmonJS_Click(ADD_FROM_WEB_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();

		}
		cfunction.sync(2);

		msg = "Wait for widget";
		try {

			cfunction.waitforelement(ADD_FROM_WEB_WIDGET_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}
		cfunction.sync(5);

		msg = "Enter copied url";
		try {
			cfunction.CommonTextBox_Input(ENTER_URL_XPATH, webserviceurl);
			cfunction.sync(5);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}
		msg = "Click on Title";
		try {
			cfunction.Commmon_Click("xpath", ENTER_TITLE_XPATH);
			// cfunction.CommmonJS_Click(ENTER_TITLE_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}
		cfunction.sync(15);

		/*
		 * msg = "Enter username "; try {
		 * cfunction.CommonTextBox_Input(ENTER_USERNAME_XPATH,UserName);
		 * cfunction.sync(3); CommonFunction.logStatus("PASS", msg); } catch (Exception
		 * e) { CommonFunction.logStatus("FAIL", msg+e); e.printStackTrace(); } msg =
		 * "Enter password"; try {
		 * cfunction.CommonTextBox_Input(ENTER_PASSWORD_XPATH,Password);
		 * cfunction.sync(3); CommonFunction.logStatus("PASS", msg); } catch (Exception
		 * e) { CommonFunction.logStatus("FAIL", msg+e); e.printStackTrace(); }
		 */
		/*
		 * msg = "Select Store authentication"; try { cfunction.CommmonJS_Click(
		 * SELECT_SAVE_AUTHENTICATION_XPATH); cfunction.sync(3);
		 * CommonFunction.logStatus("PASS", msg); } catch (Exception e) {
		 * CommonFunction.logStatus("FAIL", msg+e); e.printStackTrace(); }
		 */
		msg = "Click Done";
		try {

			cfunction.CommmonJS_Click("//span[@id='addItem-btn']");

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {

			e.printStackTrace();
			try {
				cfunction.CommmonJS_Click(ADD_ITEM_DIALOG_BUTTON_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				CommonFunction.logStatusWithException("FAIL", msg, e1);
				e.printStackTrace();
			}
		}
		cfunction.sync(3);

		msg = "Verify layer created";
		try {

			cfunction.waitforelement(ADD_ITEM_NAME_XPATH_10_6_1, 20);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {

			e.printStackTrace();
			try {
				cfunction.CommmonJS_Click(ADD_ITEM_DIALOG_BUTTON_XPATH);
				cfunction.waitforelement(ADD_ITEM_NAME_XPATH_10_6_1, 30);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				CommonFunction.logStatusWithException("FAIL", msg, e1);
				driver.navigate().refresh();
				cfunction.sync(10);
				e1.printStackTrace();
			}
		}
		cfunction.sync(2);

		msg = "Navigate to content page";

		try {
			hp.navigateToContentPage_10_6_1();

			// verifycontentpage();
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(2);

	}

	public void Verifymyorganization() throws Exception {

		msg = "Navigate to my organization";
		try {

			cfunction.CommmonJS_Click(MY_ORGANIZATION_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}
		cfunction.sync(2);

		msg = "Click on view button";
		try {

			cfunction.CommmonJS_Click(MY_ORGANIZATION_VIEW_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(1);

		msg = "Verify list";

		try {
			cfunction.CommmonJS_Click(VIEW_LIST_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);

		msg = "Click on view button";
		try {

			cfunction.CommmonJS_Click(MY_ORGANIZATION_VIEW_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(1);

		msg = "Verify Table";

		try {
			cfunction.CommmonJS_Click(VIEW_TABLE_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);

		msg = "Click on view button";
		try {

			cfunction.CommmonJS_Click(MY_ORGANIZATION_VIEW_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(1);

		msg = "Verify Grid";

		try {
			cfunction.CommmonJS_Click(VIEW_GRID_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);

		msg = "Navigate to content page";

		try {
			hp.navigateToContentPage();

			// verifycontentpage();
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(2);

	}

	public void Verifymyorganization_10_6_1() throws Exception {

		msg = "Navigate to my organization";
		try {

			cfunction.CommmonJS_Click(MY_ORGANIZATION_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}
		cfunction.sync(2);

		msg = "Verify list";

		try {
			cfunction.CommmonJS_Click(VIEW_LIST_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);

		msg = "Verify Table";

		try {
			cfunction.CommmonJS_Click(VIEW_TABLE_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		msg = "Verify Grid";

		try {
			cfunction.CommmonJS_Click(VIEW_GRID_XPATH);

			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);

		try {
			hp.navigateToContentPage_10_6_1();
			verifycontentpage_10_6_1();
			CommonFunction.logStatus("PASS", msg);

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		cfunction.sync(2);

	}

	public void clickCreateWebAppBuilder() throws Exception {

		msg = "Click on Create button";
		try {
			if (projectVersion.equalsIgnoreCase("10.9.1") || projectVersion.equals("11.1.0")
					|| projectVersion.equals("11.0.0") || projectVersion.equals("11.2.0")) {
				cfunction.CommmonJS_Click(CREATEAPPBUTTONIN_VS_10_9_1_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} else if (projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.hoverByAction(CREATEAPPBUTTONIN_VS_11_3_0_XPATH);
				cfunction.clickusingActions("document.querySelector('#create-app')");
				CommonFunction.logStatus("PASS", msg);
			} else {
				cfunction.CommmonJS_Click(CREATE_BUTTON_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(3);

		msg = "Click on Create webapp builder";
		try {
			if ((projectVersion.equalsIgnoreCase("11.3.0"))) {
				cfunction.Commmon_Click("xpath", CLICK_WEBAPPBULIDER_11_3_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} else if (projectVersion.equalsIgnoreCase("11.4.0") || (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {

				cfunction.CommmonJS_Click1(CLICK_WEBAPPBULIDER_11_4_XPATH);

			} else {
				cfunction.CommmonJS_Click(CREATE_WEB_APP_BUILDER_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
			webappcreated = false;
		}
		cfunction.sync(2);

	}

	public void clickCreateScenelayer() throws Exception {

		try {
			hp.navigateToContentPage();
			verifycontentpage();
			cfunction.sync(2);

		} catch (Exception e) {
			e.printStackTrace();

		}

		msg = "Click on New Item button";
		try {
			if (projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0")))
				cfunction.CommmonJS_Click(CREATE_ITEM11_3_XPATH);
			else
				cfunction.CommmonJS_Click(CREATE_BUTTON_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);

		msg = "Click on Scene Layer";
		try {
			String slpkNew = System.getProperty("user.dir") + "/Input/" + "SimpleMultiPatch" + ".slpk";
			String slpk = System.getProperty("user.dir") + "/Input/" + "ChateauFare" + ".slpk";
			String slpkff = System.getProperty("user.dir") + "\\Input\\" + "ChateauFare" + ".slpk";
			if (projectVersion.equalsIgnoreCase("12.0.0") || (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				WebElement element = (WebElement) js.executeScript("return " + SCENCELAYERUPLOADINPUTFILE_JSPATH);
				if (Browser.equalsIgnoreCase("Firefox")) {
					element.sendKeys(slpkff);
					CommonFunction.logStatus("PASS", "Click on the Your Device icon and Upload slpk");
					VeriyYourDevice = false;
				} else {
					element.sendKeys(slpk);
					CommonFunction.logStatus("PASS", "Click on the Your Device icon and Upload slpk");
					VeriyYourDevice = false;
				}
			} else if ((projectVersion.equalsIgnoreCase("11.4.0"))) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				WebElement element = (WebElement) js
						.executeScript("return " + SCENCELAYERUPLOADINPUTFILE_11_4_0_JSPATH);
				element.sendKeys(slpkNew);
				CommonFunction.logStatus("PASS", "Click on the Your Device icon and Upload slpk");
				VeriyYourDevice = false;
			} else {
				cfunction.sync(5);
				WebElement elm5 = getWebelement_JSpath(SCENELAYER_11_1_JSPATH);
				cfunction.clickJS(elm5);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();

		}

		cfunction.sync(5);

		if (VeriyYourDevice) {
			msg = "Click Upload Scene Package";
			try {
				WebElement elm5 = getWebelement_JSpath(UPLOAD_SCENEPACKAGE_11_1_JSPATH);
				cfunction.clickJS(elm5);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			msg = "Click Next";
			try {
				cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
			cfunction.sync(3);

			msg = "Upload slpk";
			try {
				String slpk = System.getProperty("user.dir") + "/Input/" + "ChateauFare" + ".slpk";
				String slpkff = System.getProperty("user.dir") + "\\Input\\" + "ChateauFare" + ".slpk";
				JavascriptExecutor js = (JavascriptExecutor) driver;
				WebElement element = (WebElement) js.executeScript("return " + SCENELAYER11_1_YOURDEVICE_JSPATH);
				if (Browser.equalsIgnoreCase("Firefox"))
					element.sendKeys(slpkff);
				else
					element.sendKeys(slpk);
				CommonFunction.logStatus("PASS", msg);

			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatusWithException("FAIL", msg, e);
			}
			cfunction.sync(3);

		}
		msg = "Click Next";
		try {
			cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		msg = "Enter Title";
		try {
			cfunction.Webelement_JSInput(SCENE11_1_TITLE_XPATH, SceneLayerName);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		msg = "Enter tags";
		try {
			cfunction.Webelement_JSInput(TITLE_TAG11_JSPATH, "TestTag");
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		SelectFolder();// Select created folder........................

		/*
		 * msg = "Select folder to save"; try { if
		 * ((projectVersion.equalsIgnoreCase("11.0.0") ||
		 * projectVersion.equalsIgnoreCase("11.1.0") ||
		 * projectVersion.equalsIgnoreCase("11.2.0"))) {
		 * 
		 * cfunction.getWebelement_JSpath(FOLDER_DROPDOWN_JSPATH).click();
		 * 
		 * // List<WebElement> list=cfunction.WebelementList_JSPath(FOLDERLIST_JSPATH);
		 * List<WebElement> list = cfunction.WebelementList_JSPath(FOLDERLIST_JSPATH);
		 * for (WebElement ele : list) {
		 * 
		 * if (ele.getText().equalsIgnoreCase(FolderName)) { ele.click(); break; } } }
		 * else if (projectVersion.equals("11.3.0") ||
		 * (projectVersion.equalsIgnoreCase("11.4.0"))) {
		 * cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH); final String
		 * FOLDERITEMLIST11_3_JSPATH =
		 * "document.querySelector('arcgis-folder-picker').shadowRoot.querySelector('calcite-combobox > calcite-combobox-item[text-label="
		 * + FolderName + "]')"; WebElement ele =
		 * cfunction.Webelement_JSPath(FOLDERITEMLIST11_3_JSPATH); //
		 * System.out.println(ele.getText());
		 * 
		 * if (ele == null) { cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH);
		 * } else if (ele.getText().equalsIgnoreCase(FolderName)) {
		 * cfunction.clickusingActions(FOLDERITEMLIST11_3_JSPATH); } } else if
		 * (projectVersion.equalsIgnoreCase("11.5.0"))||(projectVersion.equalsIgnoreCase
		 * ("Kubernetes11.5.0")) {
		 * cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH); final String
		 * FOLDERITEMLIST11_5_JSPATH =
		 * "document.querySelector('arcgis-folder-picker').shadowRoot.querySelectorAll('calcite-combobox > calcite-combobox-item')"
		 * ;
		 * 
		 * List<WebElement> list1 =
		 * cfunction.WebelementList_JSPath(FOLDERITEMLIST11_5_JSPATH); if (list1.size()
		 * > 0) { System.out.println(list1.size());
		 * 
		 * for (int i = 0; i < list1.size(); i++) {
		 * cfunction.ScrollToWebElement(String.format(FOLDERITEM11_5_JSPATH, (i + 1)));
		 * String listName =
		 * cfunction.Webelement_JSPath(String.format(FOLDERITEM11_5_JSPATH, (i + 1)))
		 * .getText();
		 * 
		 * if (listName.contains(FolderName)) {
		 * 
		 * cfunction.clickusingActions(String.format(FOLDERITEM11_5_JSPATH, (i + 1)));
		 * 
		 * CommonFunction.logStatus("PASS", msg); break; } } }
		 * 
		 * } cfunction.sync(2); CommonFunction.logStatus("PASS", msg); } catch
		 * (Exception e) { CommonFunction.logStatusWithException("FAIL", msg, e);
		 * e.printStackTrace(); }
		 */
		msg = "Click Next";
		try {

			cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}
		cfunction.sync(5);

	}

	public void deleteEsitingSceneLayer() {
		boolean flag = false;
		msg = "Verify Scene Layer in the All My Content folder";
		try {

			if ((projectVersion.equalsIgnoreCase("11.4.0"))) {

				cfunction.Webelement_JSClick(ALL_MY_CONTENT_11_3_JSPATH);
				cfunction.sync(2);
				List<WebElement> input = cfunction.getListOfWebElements(
						"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'Scene')]//..//..//..//..//input");
				if (input.size() > 0) {
					for (WebElement ele : input) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
						flag = true;
					}

				}
			} else if (projectVersion.equalsIgnoreCase("11.5.0") || projectVersion.equalsIgnoreCase("11.3.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				if ((projectVersion.equalsIgnoreCase("11.5.0")) || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
					cfunction.Webelement_JSClick(ALL_MY_CONTENT_11_5_JSPATH);
				} else if (projectVersion.equalsIgnoreCase("11.3.0")) {
					cfunction.Webelement_JSClick(ALL_MY_CONTENT_11_3_JSPATH);
				}
				cfunction.sync(3);
				List<WebElement> list1 = cfunction.WebelementList_JSPath(ALLITEMLIST11_5_JSPATH);
				if (list1.size() > 0) {
					for (int i = list1.size(); i > 0; i--) {
						cfunction.ScrollToWebElement(String.format(ALLCONTENTITEMLIST11_5_JSPATH, (i)));
						String listName = cfunction.Webelement_JSPath(String.format(ALLCONTENTITEMLIST11_5_JSPATH, (i)))
								.getText();
						if (listName.contains("Scene") || listName.contains("scene")) {
							cfunction.clickusingActions(String.format(ITEMCHECKBOX_JSPATH, (i)));
							flag = true;
						}
					}
				}
			} else {
				cfunction.Commmon_Click("xpath", ALL_MY_CONTENT_XPATH);
				cfunction.sync(2);
				List<WebElement> input = cfunction.getListOfWebElements(
						"//div[contains(@class,'table-select-rows')]//span[contains(@class,'table-select-title')]//a[contains(text(),'Scene')]//..//..//..//..//input");

				if (input.size() > 0) {
					for (WebElement ele : input) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);

						flag = true;
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		if (flag) {
			msg = "Select Layer and delete it";
			try {

				cfunction.sync(5);
				if (projectVersion.equalsIgnoreCase("11.2.0")) {
					cfunction.Commmon_Click("xpath", DELETE_ALL_CONTENT);
					cfunction.confirmdelete11_2_0();
				} else if (projectVersion.equalsIgnoreCase("11.5.0") || projectVersion.equalsIgnoreCase("11.3.0")
						|| (projectVersion.equalsIgnoreCase("12.0.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
						|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
					cfunction.ScrollToWebElement(DELETE_BUTTON_11_5_JSPATH);
					cfunction.Webelement_JSClick(DELETE_BUTTON_11_5_JSPATH);
					if ((projectVersion.equalsIgnoreCase("11.5.0"))
							|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
							|| (projectVersion.equalsIgnoreCase("12.0.0"))
							|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
						cfunction.Webelement_JSClick(
								"document.querySelector(' arcgis-delete-items').shadowRoot.querySelector('calcite-dialog > calcite-button:nth-child(4)')");

					} else if (projectVersion.equalsIgnoreCase("11.3.0")) {
						cfunction.Webelement_JSClick(
								"document.querySelector('arcgis-delete-items').shadowRoot.querySelector('calcite-modal > calcite-button:nth-child(5)')");

					}
				} else {
					cfunction.Commmon_Click("xpath", DELETE_ALL_CONTENT);
					cfunction.Commmon_Click("xpath", DELETE_CONFIRMATION_10_9_XPATH);
					cfunction.waitforinvisibilityofelement(DELETE_CONFIRMATION_10_9_XPATH, 60);
					// CommonFunction.logStatus("PASS", msg);
				}
				cfunction.sync(10);
				cfunction.refreshpage();
				cfunction.sync(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteEsitingSceneLayer11_3() throws Exception {
		boolean flag = false;
		msg = "Verify Scene Layer in the All My Content folder";
		try {
			if (projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.ScrollToWebElement(ALLMYCONTENTFOLDER_JSPATH);
				cfunction.Webelement_JSClick(ALL_MY_CONTENT_11_3_JSPATH);
			} else {
				cfunction.Commmon_Click("xpath", ALL_MY_CONTENT_XPATH);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		msg = "Select the Scene Layer in the All My Content folder";
		try {
			String ITEM11_3JSPATH = "document.querySelectorAll(\"arcgis-item-browser > div:nth-child(8) > arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row\")";
			List<WebElement> list = cfunction.WebelementList_JSPath(ITEM11_3JSPATH);
			if (list.size() > 0) {
				for (int i = 0; i <= list.size(); i++) {
					cfunction.ScrollToWebElement(String.format(ITEM11_3_CONTENT_JSPATH, (i + 1)));
					String listName = cfunction.Webelement_JSPath(String.format(ITEM11_3_CONTENT_JSPATH, (i + 1)))
							.getText();
					if (listName.contains("Scene")) {
						cfunction.clickusingActions(String.format(CHECKBOX_11_3_JSPATH, (i + 1)));
						flag = true;
					}
				}
				CommonFunction.logStatus("PASS", msg);
				if (flag) {
					msg = "Click delete";
					try {
						cfunction.ScrollToWebElement(DELETE_ALL11_3_CONTENT);
						cfunction.clickusingActions(DELETE_ALL11_3_CONTENT);
						cfunction.sync(2);
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();

					}

					msg = "Confirm delete";
					try {

						JavascriptExecutor jse = (JavascriptExecutor) driver;
						String JSPATH = " document.querySelector(\"body > arcgis-delete-items\").shadowRoot.querySelector(\"calcite-modal > calcite-button:nth-child(5)\")";
						cfunction.clickusingActions(JSPATH);
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						e.printStackTrace();
						CommonFunction.logStatusWithException("FAIL", msg, e);

					}

					try {
						cfunction.waitforinvisibilityofelement("//*[text()='Delete']", 180);
						// CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						cfunction.waitforinvisibilityofelement("//*[text()='Delete']", 30);
						e.printStackTrace();
						// CommonFunction.logStatus("FAIL", msg+e);

					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void SelectFolder() throws Exception {
		//// removed arcgis-new-item-pages-item-properties >
		msg = "Click on folder dropdown";
		try {
			if (projectVersion.equalsIgnoreCase("11.0.0")) {
				cfunction.Webelement_JSClick(FEATURE_FOLDER_11_JSPATH);
			} else if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))) {
				cfunction.clickusingActions(ALL_MY_CONTENT_11_3_JSPATH);
				cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH);
			} else if (projectVersion.equals("11.1.0") || projectVersion.equals("11.2.0")
					|| (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH);
			} else
				cfunction.Commmon_Click("xpath", FEATURE_FOLDER_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			try {
				cfunction.Webelement_JSClick(TILE_FOLDER_10_9_1_JSPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
		}

		cfunction.sync(2);
		msg = "Select folder to save";
		try {
			if ((projectVersion.equals("11.1.0")) || (projectVersion.equals("11.3.0"))
					|| (projectVersion.equalsIgnoreCase("11.4.0"))) {
				String FOLDERITEMLIST11_3_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelector('calcite-combobox > calcite-combobox-item[text-label="
						+ FolderName + "]')";

				WebElement ele = cfunction.Webelement_JSPath(FOLDERITEMLIST11_3_JSPATH);
				if (ele == null) {
					cfunction.Webelement_JSClick(FEATURE_FOLDER_11_1_JSPATH);
				} else if (ele.getText().equalsIgnoreCase(FolderName)) {
					// cfunction.clickJS(ele);
					cfunction.ScrollToWebElement(FOLDERITEMLIST11_3_JSPATH);
					cfunction.clickusingActions(FOLDERITEMLIST11_3_JSPATH);

				}
				CommonFunction.logStatus("PASS", msg);

			} else if ((projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				final String FOLDERITEMLIST11_5_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelectorAll('calcite-combobox > calcite-combobox-item')";

				List<WebElement> list1 = cfunction.WebelementList_JSPath(FOLDERITEMLIST11_5_JSPATH);
				if (list1.size() > 0) {
					System.out.println(list1.size());

					for (int i = list1.size(); i > 0; i--) {
						cfunction.ScrollToWebElement(String.format(FOLDERITEM11_5_JSPATH, (i)));
						String listName = cfunction.Webelement_JSPath(String.format(FOLDERITEM11_5_JSPATH, (i)))
								.getText();

						if (listName.contains(FolderName)) {

							cfunction.clickusingActions(String.format(FOLDERITEM11_5_JSPATH, (i)));

							CommonFunction.logStatus("PASS", msg);
							break;
						}
					}
				}
			} else {

				FOLDER_NAME_XPATH = "//table[contains(@id,'dialog_ItemPropertiesDlg') and contains(@id,'folders_menu')]//td[text()='"
						+ FolderName + "']";
				cfunction.Commmon_Click("xpath", FOLDER_NAME_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}

		} catch (Exception e) {
			try {
				String FOLDERITEMLIST11_3_JSPATH = "document.querySelector('arcgis-item-properties-folder').shadowRoot.querySelector('calcite-combobox > calcite-combobox-item[text-label="
						+ FolderName + "]')";
				cfunction.clickusingActions(FOLDERITEMLIST11_3_JSPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}
		}
		cfunction.sync(2);

	}

	public void SaveLayer(String title, String tag, String summary) throws Exception {
		msg = "Enter Layer Title";
		try {
			if (projectVersion.startsWith("11") || projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0.0")
					|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.Webelement_JSInput(FEATURE_TITLE_11_BUTTON_JSPATH, title);
			} else
				cfunction.CommonTextBox_Input(FEATURE_TITLE_BUTTON_XPATH, title);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			try {
				cfunction.Webelement_JSActInput(FEATURE_TITLE_11_BUTTON_JSPATH, title);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				CommonFunction.logStatusWithException("FAIL", msg, e1);
				e.printStackTrace();
			}
		}

		cfunction.sync(2);

		msg = "Enter Layer tags";
		try {
			if (projectVersion.startsWith("11") || projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0.0")
					|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.Webelement_JSInput(FEATURE_TAG_11_JSPATH, tag);
			} else {
				cfunction.CommonTextBox_Input(FEATURE_TAG_XPATH, tag);
				cfunction.Commmon_Click("xpath", FEATURE_TAG_XPATH);
				/*
				 * WebElement element1 = driver.findElement(By.xpath(FEATURE_TAG_XPATH));
				 * element1.click();
				 */
			}
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg);
			e.printStackTrace();
		}

		cfunction.sync(2);

		// SelectFolder(); // Select the created folder................................

		msg = "Click on Save button";
		try {
			if (projectVersion.startsWith("11") || projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0.0")
					|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
			} else
				cfunction.CommmonJS_Click(SAVE_FEATURE_BUTTON_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		/*
		 * if (cfunction.elementexist(TITLE_LAYER_MANAGE_XPATH)) {
		 * cfunction.Commmon_Click("xpath", TILELAYER_MANAGE_CLOSE_BTN_XPATH); }
		 */

		// logging wait message
		msg = "Waiting for 'Creating Services'";
		try {
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		cfunction.sync(10);
		try {
			if (projectVersion.startsWith("12") || projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
				cfunction.waitForElementToBeClickable("(//calcite-button[contains(@href,'mapviewer')])[1]", 90);
			else if (projectVersion.startsWith("11") || projectVersion.startsWith("10")
					|| projectVersion.equalsIgnoreCase("Kubernetes11.5.0")) {
				cfunction.waitForElementToBeClickable("//div[@class='flex clearfix']//a[@data-action='newMapViewer']",
						90);

			} else
				cfunction.waitForElementToBeClickable(
						"//div[contains(@class,'geoprocessing')]//a[contains(@class,'mapViewer')]", 90);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void SaveTileLayer(String title, String tag, String summary) throws Exception {
		// removed arcgis-new-item-pages-item-properties >
		msg = "Enter Layer Title";
		try {
			if (projectVersion.startsWith("11") || projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0.0")
					|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.Webelement_JSInput(FEATURE_TITLE_11_BUTTON_JSPATH, title);
			} else
				cfunction.Webelement_JSInput(TILETITLE_10_9_1_JSPATH, title);

			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			try {
				cfunction.Webelement_JSActInput(FEATURE_TITLE_11_BUTTON_JSPATH, title);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				CommonFunction.logStatusWithException("FAIL", msg, e1);
				e.printStackTrace();
			}
		}
		cfunction.sync(2);

		// removed arcgis-new-item-pages-item-properties >
		msg = "Enter Layer tags";
		try {
			if (projectVersion.startsWith("11") || projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0.0")
					|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.Webelement_JSInput(FEATURE_TAG_11_JSPATH, tag);
			} else {
				cfunction.Webelement_JSInput(TILE_10_9_1_TAGS_JSPATH, tag);
				cfunction.Webelement_JSClick(TILE_10_9_1_TAGS_JSPATH);
			}
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {

			e.printStackTrace();
		}

		// SelectFolder();

		msg = "Click on Save button";
		try {
			if (projectVersion.startsWith("11") || projectVersion.equalsIgnoreCase("Kubernetes11.5.0")
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) || projectVersion.startsWith("12.0.0")
					|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.Commmon_Click("xpath", FEATURE_11_NEXT_XPATH);
			} else
				cfunction.Commmon_Click("xpath", NEXT_10_9_1_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Waiting for 'Creating Services'";
		try {
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

		cfunction.sync(10);
		try {
			if (projectVersion.startsWith("12") || (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")))
				cfunction.waitForElementToBeClickable("(//calcite-button[contains(@href,'mapviewer')])[1]", 90);
			else if (projectVersion.startsWith("11") || projectVersion.startsWith("10")) {
				cfunction.waitForElementToBeClickable("//div[@class='flex clearfix']//a[@data-action='newMapViewer']",
						90);

			} else
				cfunction.waitForElementToBeClickable(
						"//div[contains(@class,'geoprocessing')]//a[contains(@class,'mapViewer')]", 90);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickExperienceBuilderTemplate() throws Exception {

		cfunction.refreshpage();
		cfunction.waitforpagetoload();

		hp.navigateToContentPage();
		verifycontentpage();

		msg = "Click on Create App button";
		try {
			cfunction.Commmon_Click("xpath", CREATEAPPBUTTONIN_VS_11_3_0_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);

		msg = "Verify ExperinceBuilder options pop up and click on Experience Builder";
		try {
			cfunction.Commmon_Click("xpath", EXPERIENCE_BUILDER_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);

		// switch to new window
		try {
			cfunction.switchTowindow(1);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);
		try {
			if (cfunction.elementIsDisplayed(EXB_TEMPLATE_XPATH)) {
				CommonFunction.logStatus("PASS", "Experience Builder template page is displayed and clicked");
			} else
				CommonFunction.logStatus("FAIL", "Experience Builder template page is not displayed");
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		// verify if popup appears
		msg = "Verify  if Express mode poup appears";
		try {
			if (cfunction.elementIsDisplayed(EXPRESSMODE_POPUP_XPATH, 10)) {
				cfunction.Commmon_Click("xpath", EXPRESSMODE_POPUP_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		msg = "Select the Experience option from the menu";
		try {
			cfunction.Commmon_Click("xpath", EXPERIENCEMENU_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);
		msg = "Click on the Create New button";
		try {
			cfunction.Commmon_Click("xpath", CREATE_NEW_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);
		msg = "Click on Foldable template under Fullscren Fixed section";
		try {
			cfunction.Commmon_Click("xpath", FOLDABLE_TEMPLATE_XPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

	}

	public void verifyCreatedContentInFolder(String contentName) throws Exception {

		msg = "Navigate to content page";
		try {
			hp.navigateToContentPage();
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);
		}

		msg = "Click on folder link: " + FolderName;
		try {
			List<WebElement> list = cfunction.WebelementList_JSPath(FOLDERLIST11_3_JSPATH);
			for (int i = list.size(); i > 0; i--) {
				cfunction.ScrollToWebElement(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
				WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
				if (ele.getText().equalsIgnoreCase(FolderName)) {
					cfunction.clickusingActions(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
					CommonFunction.logStatus("PASS", msg);
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}
		msg = "Verify " + contentName + " is created under the folder: " + FolderName;
		try {
			cfunction.ScrollToElement("//h1[ text()='Content']");
			cfunction.sync(2);
			final String FOLDERFEATURE_JSPATH = "document.querySelector('div> arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row').shadowRoot.querySelector('div>a')";
			WebElement ele = cfunction.Webelement_JSPath(FOLDERFEATURE_JSPATH);

			if (ele.getText().equalsIgnoreCase(contentName)) {
				CommonFunction.logStatus("PASS", msg);
			} else {
				CommonFunction.logStatus("FAIL", msg);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

	}

	public void selectContentFromFolder(String contentName) throws Exception {

		msg = "Open created Experience Builder App from folder";
		try {
			List<WebElement> list1 = cfunction.WebelementList_JSPath(
					"document.querySelectorAll('arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row')");
			if (list1.size() > 0) {
				for (int i = 0; i < list1.size(); i++) {
					cfunction.ScrollToWebElement(String.format(CONTENTNAME_JSPATH, (i + 1)));
					String listName = cfunction.Webelement_JSPath(String.format(CONTENTNAME_JSPATH, (i + 1))).getText();
					if (listName.contains(contentName)) {
						cfunction.clickusingActions(String.format(CONTENTNAME_JSPATH, (i + 1)));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);
		msg = "Verify Overview Page is displayed with the App deatils";
		try {
			FEATURE_NAME_XPATH = "//h1[text()='" + contentName + "']";
			if (cfunction.elementIsDisplayed(FEATURE_NAME_XPATH)) {
				CommonFunction.logStatus("PASS", msg);
			} else {
				CommonFunction.logStatus("FAIL", msg);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(2);
		msg = "Verify the url has the app id";
		try {
			String currentUrl = driver.getCurrentUrl();

			Pattern pattern = Pattern.compile("[?&]id=([a-zA-Z0-9]+)");
			Matcher matcher = pattern.matcher(currentUrl);

			if (matcher.find()) {
				String itemId = matcher.group(1);
				CommonFunction.logStatus("PASS", msg);
			} else {
				CommonFunction.logStatus("FAIL", msg);

			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.closeAllOtherTabs();
		cfunction.switchToMainWindow();
		hp.navigateToContentPage();

	}

	public boolean createMapForSharing() throws Exception {
		boolean content = false;
		hp.navigateToContentPage();

		msg = "Click on folder link";
		try {
			List<WebElement> list = cfunction.WebelementList_JSPath(FOLDERLIST11_3_JSPATH);
			if ((projectVersion.equalsIgnoreCase("11.5.0")) || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				for (int i = list.size(); i > 0; i--) {
					cfunction.ScrollToWebElement(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.clickusingActions(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			} else if (projectVersion.equalsIgnoreCase("10.9.1") || projectVersion.equalsIgnoreCase("10.8.1")
					|| projectVersion.equalsIgnoreCase("11.1.0")) {

				List<WebElement> folderlist = cfunction.getListOfWebElements(FOLDERLIST10_9_1_XPATH);

				for (int i = folderlist.size(); i > 0; i--) {
					WebElement ele = driver.findElement(By.xpath(FOLDERLIST10_9_1_XPATH + "[" + i + "]"));
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.ScrollToWebElement(ele);
						ele.click();
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}

			} else {
				for (int i = list.size(); i > 0; i--) {
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAME11_3_JSPATH, (i)));
					// System.out.println(ele.getText());
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.ScrollToWebElement(String.format(FOLDERNAME11_3_JSPATH, (i)));
						cfunction.clickusingActions(String.format(FOLDERNAME11_3_JSPATH, (i)));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Verify the folder has the content to be shared";
		try {
			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				final String FOLDERFEATURE_JSPATH = "document.querySelectorAll('div> arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row')";
				List<WebElement> list1 = cfunction.WebelementList_JSPath(FOLDERFEATURE_JSPATH);
				if (list1.size() > 0) {
					content = false;
					CommonFunction.logStatus("PASS", "Content Available for sharing");

				} else {
					content = true;
					CommonFunction.logStatus("PASS", "Creating Web Map for sharing");
				}
			} else {
				List<WebElement> list2 = cfunction.getListOfWebElements(
						"//div[contains(@class,'content-table')]//div[@role='rowgroup']//div[contains(@class,'select-row')]");
				if (list2.size() > 1) {
					content = false;
					CommonFunction.logStatus("PASS", "Content Available for sharing");

				} else {
					content = true;
					CommonFunction.logStatus("PASS", "Creating Web Map for sharing");
				}
			}

		} catch (Exception e) {
			content = true;
			CommonFunction.logStatus("PASS", "Creating Web Map for sharing");

		}
		return content;
	}

	public void VerifyDashboardCreated(String DashboardName) throws Exception {
		cfunction.sync(1);
		msg = "Click on folder link";
		try {
			List<WebElement> list = cfunction.WebelementList_JSPath(FOLDERLIST11_3_JSPATH);
			if ((projectVersion.equalsIgnoreCase("11.5.0")) || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))
					|| projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) {
				for (int i = list.size(); i > 0; i--) {
					cfunction.ScrollToWebElement(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.clickusingActions(String.format(FOLDERNAMELIST11_5_JSPATH, (i)));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			} else if (projectVersion.equalsIgnoreCase("11.1.0") || projectVersion.equalsIgnoreCase("11.2.0")) {
				List<WebElement> folderlist = cfunction.getListOfWebElements(FOLDERLIST10_9_1_XPATH);

				for (int i = folderlist.size(); i > 0; i--) {
					WebElement ele = driver.findElement(By.xpath(FOLDERLIST10_9_1_XPATH + "[" + i + "]"));
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.ScrollToWebElement(ele);
						ele.click();
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}

			} else {
				for (int i = list.size(); i > 0; i--) {
					WebElement ele = cfunction.Webelement_JSPath(String.format(FOLDERNAME11_3_JSPATH, (i)));
					if (ele.getText().equalsIgnoreCase(FolderName)) {
						cfunction.ScrollToWebElement(String.format(FOLDERNAME11_3_JSPATH, (i)));
						cfunction.clickusingActions(String.format(FOLDERNAME11_3_JSPATH, (i)));
						CommonFunction.logStatus("PASS", msg);
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

		msg = "Verify Dashboard is created under the folder: " + FolderName;
		try {
			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0"))
					|| projectVersion.equalsIgnoreCase("Kubernetes12.1.0")
					|| (projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.ScrollToElement("//h1[ text()='Content']");
				cfunction.sync(2);
				final String FOLDERFEATURE_JSPATH = "document.querySelector('div> arcgis-drag-and-drop > div > arcgis-item-browser-content > arcgis-item-browser-table > arcgis-item-browser-table-row').shadowRoot.querySelector('div>a')";
				WebElement ele = cfunction.Webelement_JSPath(FOLDERFEATURE_JSPATH);

				if (ele.getText().equalsIgnoreCase(DashboardName)) {
					CommonFunction.logStatus("PASS", msg);
					msg = "Click on the created Dashboard item";
					try {
						cfunction.ScrollToWebElement(FOLDERFEATURE_JSPATH);
						cfunction.Webelement_JSClick(FOLDERFEATURE_JSPATH);
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						CommonFunction.logStatusWithException("FAIL", msg, e);
						e.printStackTrace();
					}
				} else {
					CommonFunction.logStatus("FAIL", msg);

				}

			} else {
				String CREATEDDAHSBOARD_CONTENTPAGE_XPATH = "//a[text()='" + DashboardName + "']";
				if (!(driver.findElements(By.xpath(CREATEDDAHSBOARD_CONTENTPAGE_XPATH)).isEmpty())) {
					CommonFunction.logStatus("PASS", msg);
					msg = "Click on the created Dashboard item";
					try {
						cfunction.ScrollToElement(CREATEDDAHSBOARD_CONTENTPAGE_XPATH);
						cfunction.CommmonJS_Click1(CREATEDDAHSBOARD_CONTENTPAGE_XPATH);
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e) {
						CommonFunction.logStatusWithException("FAIL", msg, e);
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		msg = "Verify the Dashboard overview page is displayed with Item ID in URL";
		try {
			String currentUrl = driver.getCurrentUrl();
			Pattern pattern = Pattern.compile("[?&]id=([a-zA-Z0-9]+)");
			Matcher matcher = pattern.matcher(currentUrl);
			if (matcher.find()) {
				String itemId = matcher.group(1);
				CommonFunction.logStatus("PASS", msg);
			} else {
				CommonFunction.logStatus("FAIL", msg);

			}
		} catch (Exception e1) {
			CommonFunction.logStatusWithException("FAIL", msg, e1);
			e1.printStackTrace();
		}

	}

}
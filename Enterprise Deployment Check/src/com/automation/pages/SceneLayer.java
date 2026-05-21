package com.automation.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;

public class SceneLayer extends TestBase {

	String msg = "";
	String FEATURE_NAME_XPATH;
	CommonFunction cfunction = new CommonFunction();
	ContentPage cp = new ContentPage();
	HomePage hp = new HomePage();

	final String FOLDERITEMLISTSCENCELAYER_12_0_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelectorAll('calcite-list > calcite-list-item')";
	final String FOLDERITEMSCENCELAYER_12_0_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelector('calcite-list > calcite-list-item:nth-child(%s)').shadowRoot.querySelector('div.content > div')";

	final String SCENEINPUT_11_3_JSPATH = "document.querySelector(\"div.wsv-dialog--save__sceneproperties div> arcgis-tags-picker\").shadowRoot.querySelector(\"calcite-label > calcite-combobox\").shadowRoot.querySelector(\"input\")";
	final String SCENEHEADER_NAME_XPATH = "//span[@id='pagetitle' and text()='" + SceneLayerName + "']";
	final String OPENSCENEVIEWER12_0_JSPATH="document.querySelector('div.item-pane.item-pane--active > div  div:nth-child(1) > div.transition-all.grid.gap-3 > div > calcite-button:nth-child(1) > span')";
	final String OPENSCENEVIEWERVERSION12_0_JSPATH="document.querySelector('div.item-pane.item-pane--active > div  div:nth-child(1) > div.transition-all.grid.gap-3 > div > calcite-button:nth-child(1) > span')";
	
	
	final String OPENSCENEVIEWER11_1_XPATH = "//a[contains(@class,'sceneViewer')]";
	final String OPENSCENEVIEWER12_0_XPATH = "//calcite-button[contains(@href,'./webscene')]//span[text()='Open in Scene Viewer']";
	final String SCENEVIEWER_LAYERNAME_XPATH = "//div[@data-collection-id='layers']//div[contains(@title,'Chateau')]";
	final String SCENEVIEWER_11_3_LAYERNAME_XPATH = "//div[@data-collection-id='layers']//div[contains(@class,'item__header')]";
	final String SCENEVIEWER_11_4_LAYERNAME_XPATH = "//calcite-tree-item//div[contains(@class,'item__header-content')]";
	final String MYSCENE_TITLE_XPATH = "//*[contains(@id,'webmap')]";
	final String SCENSAVE11_1_BUTTON_JSPATH = "document.querySelector(\"#wsv-designer-bar__save\").shadowRoot.querySelector(\"button\")";
	final String SCENESAVE11_0_BUTTON_JSPATH = "//*[contains(@id,'save')]";
	final String SCENETITLE11_1_INPUT_XPATH = "//div[contains(@class,'scenetitle')]//input";
	final String SCENETAG11_1_XPATH = "//div[contains(@class,'tags')]//input";
	final String SCENE11_1_SAVE_JSPATH = "document.querySelector(\"#modal > div > calcite-modal > calcite-button:nth-child(4)\").shadowRoot.querySelector(\"button\")";
	final String VIEW_ITEMSDETAILS_MSG_XPATH = "//a[text()='View item details']";
	final String SCENELAYER_NAME_XPATH = "//div[contains(@class,'operationalLayers')]//h5";
	final String SCENELAYER_NAME_12_0_0_XPATH = "(//calcite-list[@class='layer-list__container']//span[contains(@class,'medium underline')])[1]";
	final String SAVE_XPATH = "//calcite-button[@slot='secondary']";
	final String SAVE_12_0_XPATH = "//calcite-button[@data-testid='save-dialog-save-button']";
	final String FOLDERCHANGE_CANCEL_XPATH = "//div[contains(@class,'action-bar')]//button[contains(@class,'cancel')]";
	final String GROUNDLAYER_XPATH = "//div[contains(@class,'elevationLayers trailer')]//h5";
	final String GROUNDLAYER_NAME_12_0_0_XPATH = "(//calcite-list[@class='layer-list__container']//span[contains(@class,'medium underline')])[2]";
	final String MOVEFOLDER_XPATH = "//button[contains(@class,'folder')]";
	final String MOVEFOLDER_12_0_0_XPATH = "//div//h3[text()='Folder']/..//calcite-button";
	final String FOLDERLIST_XPATH = "//li[@class='folder-list-item']//span";
	final String FOLDERSERACH_JSPATH = "document.querySelector('arcgis-folder-picker').shadowRoot.querySelector('calcite-list').shadowRoot.querySelector('calcite-stack > calcite-filter').shadowRoot.querySelector(' calcite-input').shadowRoot.querySelector('div.element-wrapper > input')";
	final String SAVEBUTTON_XPATH = "//div[contains(@class,'overlay')]//button[text()='Save']";
	final String SAVEBTN_12_0_JSPATH = "document.querySelector(\"body > calcite-dialog:nth-child(21) > calcite-button:nth-child(4)\").shadowRoot.querySelector(\"div > button\")";
	final String CONTENT_BUTTONXPATH = "//a[contains(@href,'home/content') and contains(@id,'desktop')]";
	final String CREATED_FOLDER_XPATH = "//*[@class='folder-list-title']//following-sibling::ul//a[text()='"
			+ FolderName + "']";
	final String SCEBE_WARNING_CLOSEBUTTON_JSPATH = "document.querySelector(\"#webscene-map > div.esri-view-user-storage > div.esri-wsv-messages > calcite-alert.esri-wsv-messages__msg.esri-wsv-messages__msg--warning\").shadowRoot.querySelector(\"div > button\")";
	final String SCENE_WARNING_XPATH = "//calcite-alert[contains(@class,'warning')]";
	final String SCENE_LAYERHEADER_XPATH = "//*[contains(@id,'webmap-title')]//a";

	public void verifySceneLayerCreated() throws Exception {
		msg = "Verify Scene layer name";

		try {
			if (projectVersion.equalsIgnoreCase("12.0.0")||(projectVersion.equalsIgnoreCase("12.1.0"))) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OPENSCENEVIEWER12_0_XPATH)));
				CommonFunction.logStatus("PASS", msg);
			} else {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OPENSCENEVIEWER11_1_XPATH)));
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e1) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OPENSCENEVIEWER11_1_XPATH)));
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e1);
				e.printStackTrace();
			}
		}

		msg = "Click Open in Scene Viewer";

		try {
			cfunction.sync(40);
			if (projectVersion.equalsIgnoreCase("12.0.0")||(projectVersion.equalsIgnoreCase("12.1.0"))) {
				try {
					cfunction.ScrollToWebElement(OPENSCENEVIEWER12_0_XPATH);
					cfunction.hoverByAction(OPENSCENEVIEWER12_0_XPATH);
					// cfunction.Webelement_JSClick(OPENSCENEVIEWER12_0_JSPATH);
					cfunction.CommmonJS_Click1(OPENSCENEVIEWER12_0_XPATH);
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e) {
					try {
						cfunction.sync(10);
						cfunction.ScrollToWebElement(OPENSCENEVIEWER12_0_XPATH);
						//cfunction.hoverByAction(OPENSCENEVIEWER12_0_XPATH);
						cfunction.Webelement_JSClick(OPENSCENEVIEWER12_0_JSPATH);
						CommonFunction.logStatus("PASS", msg);
					} catch (Exception e1) {
						CommonFunction.logStatusWithException("FAIL", msg, e);
						e.printStackTrace();
					}
				}
			} else {
				cfunction.Commmon_Click("xpath", OPENSCENEVIEWER11_1_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}

		} catch (Exception e) {
			try {
				cfunction.sync(100);
				cfunction.Commmon_Click("xpath", OPENSCENEVIEWER11_1_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e1) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
				return;
			}
		}
		msg = "Verify the scene loads";

		try {
			String xpath = SCENEVIEWER_LAYERNAME_XPATH;
			if (projectVersion.equalsIgnoreCase("11.3.0")) {
				xpath = SCENEVIEWER_11_3_LAYERNAME_XPATH;
			} else if ((projectVersion.equalsIgnoreCase("11.4.0")) || (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0")) || (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))) {
				xpath = SCENEVIEWER_11_4_LAYERNAME_XPATH;
			}
			cfunction.waitforelement(xpath, 150);
			String title = cfunction.getElementAttribute(xpath, "title");

			if (title.contains("SceneLayer")) {
				CommonFunction.logStatus("PASS", msg);
			} else
				CommonFunction.logStatus("FAIL", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
			return;
		}

	}

	public void createScene() throws Exception {

		msg = "Verify My Scene Page";
		try {
			if (cfunction.elementIsDisplayed(MYSCENE_TITLE_XPATH))
				CommonFunction.logStatus("PASS", msg);
			else
				CommonFunction.logStatus("FAIL", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
			return;
		}

		cfunction.sync(15);
		msg = "Click on the Save button";
		try {
			cfunction.Commmon_Click("xpath", SCENESAVE11_0_BUTTON_JSPATH);
			CommonFunction.logStatus("PASS", msg);
			cfunction.sync(2);
		} catch (Exception e1) {
			try {
				cfunction.Commmon_Click("xpath", SCENESAVE11_0_BUTTON_JSPATH);
				CommonFunction.logStatus("PASS", msg);
				cfunction.sync(2);
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

		}

		msg = "Enter Scene Title";
		try {
			cfunction.CommonTextBox_Input(SCENETITLE11_1_INPUT_XPATH, SceneTitle);
			CommonFunction.logStatus("PASS", msg);
			cfunction.sync(2);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		msg = "Enter Scene Tag";
		try {
			if (projectVersion.equals("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0")) || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.Webelement_JSInput(SCENEINPUT_11_3_JSPATH, "TestTag");
				Actions act = new Actions(driver);
				act.sendKeys(Keys.ENTER);
				CommonFunction.logStatus("PASS", msg);
			} else {
				cfunction.CommonTextBox_Input(SCENETAG11_1_XPATH, "Test");
				CommonFunction.logStatus("PASS", msg);
				cfunction.sync(2);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		if (Browser.equalsIgnoreCase("Firefox")) {
			msg = "Click Save button to save the Scene";
			try {
				cfunction.Commmon_Click("xpath", SAVE_XPATH);
				CommonFunction.logStatus("PASS", msg);

			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			msg = "Verify 'Scene saved successfully to My Content' message is displayed";
			try {

				if (cfunction.elementexist(SCENE_WARNING_XPATH, 2)) {
					cfunction.Webelement_JSClick(SCEBE_WARNING_CLOSEBUTTON_JSPATH);
				}
				cfunction.Commmon_Click("xpath", SCENE_LAYERHEADER_XPATH);
				CommonFunction.logStatus("PASS", msg);

				cfunction.sync(10);

			} catch (Exception e1) {
				CommonFunction.logStatusWithException("FAIL", msg, e1);
				e1.printStackTrace();
			}
		}

		else {
			msg = "Click Save button to save the Scene";
			try {
				if (projectVersion.equalsIgnoreCase("12.0.0")||(projectVersion.equalsIgnoreCase("12.1.0"))) {
					cfunction.Commmon_Click("xpath", SAVE_12_0_XPATH);
					CommonFunction.logStatus("PASS", msg);
				} else {
					cfunction.Commmon_Click("xpath", SAVE_XPATH);
					CommonFunction.logStatus("PASS", msg);
				}
			} catch (Exception e) {
				CommonFunction.logStatusWithException("FAIL", msg, e);
				e.printStackTrace();
			}

			msg = "Verify 'Scene saved successfully to My Content' message is displayed and click it";
			try {

				if (cfunction.elementexist(SCENE_WARNING_XPATH, 2)) {
					cfunction.Webelement_JSClick(SCEBE_WARNING_CLOSEBUTTON_JSPATH);
				}
				if (projectVersion.equalsIgnoreCase("12.0.0")||(projectVersion.equalsIgnoreCase("12.1.0"))) {
					Actions act = new Actions(driver);
					act.moveToElement(driver.findElement(By.xpath("//h1[@id='webmap-title']//a"))).click().build()
							.perform();

					CommonFunction.logStatus("PASS", msg);
				} else {
					Actions act = new Actions(driver);
					act.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'msg__content')]//a"))).click()
							.build().perform();

					CommonFunction.logStatus("PASS", msg);
				}
				cfunction.sync(10);
			} catch (Exception e1) {
				CommonFunction.logStatusWithException("FAIL", msg, e1);
				e1.printStackTrace();
			}
		}

		msg = "Verify Scene is created";

		try {

			cfunction.switchTowindow(1);
			if (projectVersion.startsWith("11") || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))) {
				FEATURE_NAME_XPATH = "//header//h1//span[text()='" + SceneTitle + "']";
			} else if (projectVersion.startsWith("12")) {
				FEATURE_NAME_XPATH = "//div//h1[text()='" + SceneTitle + "']";
			} else {
				FEATURE_NAME_XPATH = "//header[@title='" + SceneTitle + "']";
			}

			cfunction.waitforelement(FEATURE_NAME_XPATH, 60);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		msg = "Verify Scene Layer name is " + SceneLayerName;
		try {
			if (projectVersion.equalsIgnoreCase("12.0.0")||(projectVersion.equalsIgnoreCase("12.1.0"))) {
				if (cfunction.getElementText(SCENELAYER_NAME_12_0_0_XPATH).startsWith(SceneLayerName))
					CommonFunction.logStatus("PASS", msg);
				else
					CommonFunction.logStatus("FAIL", msg);
				cfunction.sync(2);
			} else {
				if (cfunction.getElementText(SCENELAYER_NAME_XPATH).contains(SceneLayerName))
					CommonFunction.logStatus("PASS", msg);
				else
					CommonFunction.logStatus("FAIL", msg);
				cfunction.sync(2);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		msg = "Verify Ground Layer name is Terrain3D";
		try {
			if (projectVersion.equalsIgnoreCase("12.0.0")||(projectVersion.equalsIgnoreCase("12.1.0"))) {
				if (cfunction.getElementText(GROUNDLAYER_NAME_12_0_0_XPATH).contains("Terrain3D"))
					CommonFunction.logStatus("PASS", msg);
				else
					CommonFunction.logStatus("FAIL", msg);
				cfunction.sync(2);
			} else {

				if (cfunction.getElementText(GROUNDLAYER_XPATH).equalsIgnoreCase("Terrain3D"))
					CommonFunction.logStatus("PASS", msg);
				else
					CommonFunction.logStatus("PASS", msg);
				cfunction.sync(2);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		msg = "Click Open in Scene Viewer";

		try {
			cfunction.sync(50);
			if (projectVersion.equalsIgnoreCase("12.0.0")||(projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.Commmon_Click("xpath", OPENSCENEVIEWER12_0_XPATH);
				CommonFunction.logStatus("PASS", msg);
			} else {
				cfunction.Commmon_Click("xpath", OPENSCENEVIEWER11_1_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		cfunction.sync(20);
		msg = "Verify My Scene Page loads";
		try {

			if (cfunction.elementIsDisplayed(MYSCENE_TITLE_XPATH))
				CommonFunction.logStatus("PASS", msg);
			else
				CommonFunction.logStatus("FAIL", msg);
		} catch (Exception e) {
			CommonFunction.logStatusWithException("FAIL", msg, e);
			e.printStackTrace();
		}

		msg = "Click back button";
		try {
			driver.navigate().back();
			CommonFunction.logStatus("PASS", msg);
			cfunction.sync(10);
		} catch (Exception e) {

			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}

		msg = "Move the scene to Test Folder";
		try {
			if (projectVersion.equalsIgnoreCase("12.0.0")||(projectVersion.equalsIgnoreCase("12.1.0"))) {
				cfunction.Commmon_Click("xpath", MOVEFOLDER_12_0_0_XPATH);
				cfunction.sync(2);
				List<WebElement> list1 = cfunction.WebelementList_JSPath(FOLDERITEMLISTSCENCELAYER_12_0_JSPATH);
				if (list1.size() > 0) {
					System.out.println(list1.size());
					for (int i = 0; i < list1.size(); i++) {
						cfunction.ScrollToWebElement(String.format(FOLDERITEMSCENCELAYER_12_0_JSPATH, (i + 1)));
						String listName = cfunction
								.Webelement_JSPath(String.format(FOLDERITEMSCENCELAYER_12_0_JSPATH, (i + 1))).getText();

						if (listName.contains(FolderName)) {
							cfunction.clickusingActions(String.format(FOLDERITEMSCENCELAYER_12_0_JSPATH, (i + 1)));
							// SAVEBTN_12_0_JSPATH
							cfunction.clickusingActions(SAVEBTN_12_0_JSPATH);
							CommonFunction.logStatus("PASS", msg);
							break;
						}
					}
				}
			} else {
				cfunction.Commmon_Click("xpath", MOVEFOLDER_XPATH);
				cfunction.sync(2);
				List<WebElement> list = driver.findElements(By.xpath(FOLDERLIST_XPATH));

				for (WebElement ele : list) {

					if (ele.getText().equalsIgnoreCase(FolderName)) {
						ele.click();
						cfunction.Commmon_Click("xpath", SAVEBUTTON_XPATH);
						break;

					}
				}

			}
			cfunction.sync(2);
			if (cfunction.elementexist(SAVEBUTTON_XPATH)) {
				// System.out.println("Inside folderchange block when no folder found");
				cfunction.Commmon_Click("xpath", FOLDERCHANGE_CANCEL_XPATH);
				CommonFunction.logStatus("PASS", msg);
			}

		} catch (Exception e) {

			e.printStackTrace();
			CommonFunction.logStatus("FAIL", msg);
		}

		msg = "Navigate to content page";
		try {
			cfunction.sync(5);
			cfunction.Commmon_Click("xpath", CONTENT_BUTTONXPATH);
			CommonFunction.logStatus("PASS", msg);
		} catch (Exception e) {
			CommonFunction.logStatus("FAIL", msg);
			e.printStackTrace();
		}

		msg = "Click on folder link";
		try {
			if (cfunction.elementexist(CREATED_FOLDER_XPATH)) {
				cfunction.CommmonJS_Click(CREATED_FOLDER_XPATH);
			}
			cfunction.sync(2);
			CommonFunction.logStatus("PASS", msg);
			scenecreated = true;
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunction.logStatusWithException("FAIL", msg, e);

		}

	}

}

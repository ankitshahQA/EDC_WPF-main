package com.automation.library;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.imageio.ImageIO;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class CommonFunction extends TestBase {

	/*
	 * static Method m; static String testName = m.getName(); public static
	 * ExtentTest logger;
	 */
	/**********************************************************************************************
	 * <b>Method Signature Name:</b> getWebElement <br>
	 * <br>
	 * <b>Description:</b> Retrieves the element.
	 * 
	 * @param locator     The locator text
	 * @param locatorType The locator type (e.g. <i>id, name, css, xpath, class,
	 *                    linkText, partialLinkText, tagName</i>)
	 * @return The web element success, null on failure
	 *********************************************************************************************/
	public static WebElement getWebElement(String locatorType, String locator) {

		String type = locatorType.trim().toLowerCase();
		WebElement element = null;

		try {

			if (type.equals("id")) {
				element = (new WebDriverWait(driver, Duration.ofSeconds(25)))
						.until(ExpectedConditions.elementToBeClickable(By.id(locator)));
			} else if (type.equals("name")) {
				element = (new WebDriverWait(driver, Duration.ofSeconds(25)))
						.until(ExpectedConditions.elementToBeClickable(By.name(locator)));
			} else if (type.equals("css")) {
				element = (new WebDriverWait(driver, Duration.ofSeconds(25)))
						.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator)));
			} else if (type.equals("class")) {
				element = (new WebDriverWait(driver, Duration.ofSeconds(25)))
						.until(ExpectedConditions.elementToBeClickable(By.className(locator)));
			} else if (type.equals("xpath")) {
				element = (new WebDriverWait(driver, Duration.ofSeconds(25)))
						.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
			} else if (type.equals("tagname")) {
				element = (new WebDriverWait(driver, Duration.ofSeconds(25)))
						.until(ExpectedConditions.elementToBeClickable(By.tagName(locator)));
			} else if (type.equals("linktext")) {
				element = (new WebDriverWait(driver, Duration.ofSeconds(15)))
						.until(ExpectedConditions.elementToBeClickable(By.linkText(locator)));
			} else if (type.equals("partiallinktext")) {
				element = (new WebDriverWait(driver, Duration.ofSeconds(15)))
						.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locator)));
			} else {
				String msg = "isElementPresent(): Invalid locator type.  Supported types are id, name, css, xpath, class, linkText, partialLinkText, tagName";
				System.out.println(msg);
			}

		} catch (NoSuchElementException e) {
			System.out.println("getWebElement(): The WebElement was not found");
			return null;
		}

		return element;
	}

	public void JSclickOnListOfElementIDBasedOnIndex(String id, int index) {
		List<WebElement> list = driver.findElements(By.id(id));
		WebElement element = list.get(index);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void JSclickOnListOfElementBasedOnIndex(String xpath, int index) {
		List<WebElement> list = driver.findElements(By.xpath(xpath));
		WebElement element = list.get(index);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	// Button click function
	public void Commmon_Click(String xpath, String locator) {
         
		WebElement clickButton = getWebElement(xpath, locator);
		clickButton.click();

	}

	public static void getFolderName() {
		if (!flagcontent) {
			FolderName = driver.findElement(By.xpath("//h4[@class='folder-list-title']//following-sibling::ul/li[2]"))
					.getAttribute("data-title");
		}

	}
	// Button double click function

	public void CommmonDouble_Click(String XPATH) {
		WebElement doubleClick = (new WebDriverWait(driver, Duration.ofSeconds(50)))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));

		((JavascriptExecutor) driver).executeScript("var evt = document.createEvent('MouseEvents');"
				+ "evt.initMouseEvent('dblclick',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
				+ "arguments[0].dispatchEvent(evt);", doubleClick);
	}

	public void CommmonJS_Click(String XPATH) {
		WebElement doubleClick = (new WebDriverWait(driver, Duration.ofSeconds(60)))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", doubleClick);
	}

	public void CommmonJS_Click1(String XPATH) {
		WebElement element1 = driver.findElement(By.xpath(XPATH));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1);
	}

	public void waitForProgressBarToEnd() {
		String XPATH = "//div[@class='esriLoading' and contains(@style,'display:none')]";
		WebElement progressbar = (new WebDriverWait(driver, Duration.ofSeconds(15)))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(XPATH)));
		// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
		// progressbar);
		if (progressbar.isDisplayed()) {
			System.out.println("Done");
		}
	}

	public void waitForloadingToEnd() {
		String XPATH = "//div[@class='loader-text' and contains(text(),'Loading')]";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(XPATH)));

	}

	// Check box check function
	public void CommonCheckBox_Check(String XPATH) {
		WebElement checkBoxCheck = (new WebDriverWait(driver, Duration.ofSeconds(55)))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		checkBoxCheck.click();
	}
	
	public void waitForElementToBeClickable(String XPATH,int duration) {
		WebElement checkBoxCheck = (new WebDriverWait(driver, Duration.ofSeconds(duration)))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
	}

	// get Text of element
	public String getElementText(String XPATH) {
		WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		return element.getText();
	}

	// get Attribute of element
	public String getElementAttribute(String XPATH, String attribute) throws Exception {
		WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		return element.getAttribute(attribute);
	}

	// get Attribute of element
	public void setElementAttribute(String XPATH, String attribute, String value) throws Exception {
		WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(30)))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]); element, attribute,value");
		element.sendKeys(Keys.ENTER);
	}

	// Pass/Insert/Input value in textbox
	public void CommonTextBox_Input(String XPATH, String txtValue) {
		WebElement txtBox = (new WebDriverWait(driver, Duration.ofSeconds(25)))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		txtBox.clear();
		sync(2);
		txtBox.sendKeys(txtValue);
	}
	
	public void CommonTextBox_InputWithClear(String XPATH, String txtValue) {
		WebElement txtBox = (new WebDriverWait(driver, Duration.ofSeconds(25)))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		//code to clear text box using action class
		txtBox.sendKeys(Keys.CONTROL + "a");
		txtBox.sendKeys(Keys.DELETE);;
		sync(2);
		txtBox.sendKeys(txtValue);
	}

	public void CommonTextBox_InputJS(String XPATH, String value) throws Exception {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement ele = (WebElement) js.executeScript("return " + XPATH);
		ele.sendKeys(value);

	}

	public void CommonTextBox_Inputrobo() {

		Robot rb;
		try {
			rb = new Robot();
			rb.keyPress(KeyEvent.VK_1);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Press control keyboard key

		// txtBox.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
		// txtBox.sendKeys(Keys.DELETE);
		// txtBox.sendKeys(txtValue);
	}

	// Click on dropdown and select value from dropdown
	public void CommonDropDown_Select(String XPATH) {
		WebElement select_dropdown = (new WebDriverWait(driver, Duration.ofSeconds(55)))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		select_dropdown.click();
	}
	// Select data in dropdown by name
	// public void DropDown_SelectById(WebDriver driver, String XPATH, String drpId,
	// String drpOption) {
	// Select SelectByName = new Select(driver.findElement(By.id(drpId)));
	// SelectByName.selectByVisibleText(drpOption);
	// }

	public void DropDown_SelectByText(String Text) {

		List<WebElement> optionList = driver
				.findElements(By.xpath("//*[@id='syncGeoServiceName']/tbody/tr/td[2]/input"));

		for (int i = 0; i < optionList.size(); i++) {
			if (optionList.get(i).getText() == Text) {
				optionList.get(i).click();
			}
		}
	}

	public void DefDropDwn() {
		List<WebElement> allOptions = driver.findElements(By.xpath("//input[@id='dijit_form_FilteringSelect_9']"));

		for (WebElement option : allOptions) {
			if (option.getText().equals("*")) {
				option.click();
			}
		}
	}

	public void refreshpage() {
		driver.navigate().refresh();
	}

	public void clickusingActions(String jspath) {
		waitforwebelement(jspath);
		WebElement element = getWebelement_JSpath(jspath);
		Actions act = new Actions(driver);
		act.moveToElement(element).click().build().perform();
	}
	
	public void clickxpathusingActions(String xpath) {
		Actions act = new Actions(driver);
		WebElement ele=driver.findElement(By.xpath(xpath));
		act.moveToElement(ele).click().build().perform();
	}
	
	

	public List getWebElementList(String Xpath) {
		waitforelement(Xpath, 15);
		List<WebElement> element = driver.findElements(By.xpath(Xpath));
		return element;
	}

	public String getTextFromTd(String XPATH) {
		String returnValue = "";
		returnValue = driver.findElement(By.xpath(XPATH)).getText();
		return returnValue;
		// WebElement getTextValue = (new WebDriverWait(driver, 55))
		// .until(ExpectedConditions.findElement(By.xpath(XPATH)));
	}

	public String checkString(String compValue) {
		String[] notificationZoneName = new String[] { "Alpha", "Bravo", "Charlie", "Delta", "Echo" };
		String returnString = "";
		for (String compair : notificationZoneName) {
			if (compair == compValue) {
				returnString = "";
				returnString = compValue;
			}
		}
		return returnString;
	}

	public void sync(int time) {
		int waittime = time * 1000;
		try {
			Thread.sleep(waittime);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.out.println("Sync Failed");
		}
	}

	public void ScrollToElement(String xpath) {
		try {
			WebElement scrollElement = (new WebDriverWait(driver, Duration.ofSeconds(15)))
					.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrollElement);
			Thread.sleep(500);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ScrollToWebElement(WebElement webElement) {
		try {
			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
			Thread.sleep(500);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void logStatus(String status, String msg) {
		String msg1 = msg;
		msg = stepCount++ + " - " + msg;

		try {
			if (status.equals("PASS")) {
				msg = msg + " -  Passed";

				if (screenshotEnabled) {
					String screenshot = TestBase.getScreenshot(driver);
					childnode.pass(msg, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				} else {
					childnode.log(Status.PASS, MarkupHelper.createLabel(msg, ExtentColor.GREEN));
				}

				try {
					writeStatus("success", msg1 + " -  Passed");
				} catch (Exception e) {
					// e.printStackTrace();
				}
			} else if (status.equals("FAIL")) {
				VDFailResult++;
				//VDFailResult = VDFailResult + 1;
				msg = msg + " - Failed" + "<br />" + submitDefectGitHub();
				// if(stop) {
				// msg1 = msg1+ "- Tool Stopped by user";
				// }
				// if(screenshotEnabled) {
				String screenshot = getScreenshot(driver);

				childnode.fail(msg, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				/*
				 * }else { logger.log(Status.FAIL, MarkupHelper.createLabel(msg+" -  Failed",
				 * ExtentColor.RED)); }
				 */

				try {
					setTextRed(msg1 + " -  Failed");
				} catch (Exception e) {
					// e.printStackTrace();
				}
				
			} else if (status.equals("FAILWITHOUTSCREENSHOT")) {
				VDFailResult++;
				//VDFailResult = VDFailResult + 1;
				msg = msg + " - Failed" + "<br />" + submitDefectGitHub();
				childnode.log(Status.FAIL, MarkupHelper.createLabel(msg, ExtentColor.RED));
				try {
					setTextRed(msg1 + " -  Failed");
				} catch (Exception e) {
					// e.printStackTrace();
				}

			 

			} else if (status.equals("WARNING")) {
				childnode.log(Status.WARNING, MarkupHelper.createLabel(msg, ExtentColor.ORANGE));
				try {
					setText(msg1 +" ");
				} catch (Exception e) {
					// e.printStackTrace();
				}

			} else if (status.equals("SKIP")) {

				// Log SKIP under the current test's child node so it appears in the
				// correct test section of the report (not at the top under the parent).
				if (childnode != null) {
					childnode.log(Status.SKIP,
							MarkupHelper.createLabel(msg + " - Test Case Skipped", ExtentColor.ORANGE));
				} else {
					logger.log(Status.SKIP,
							MarkupHelper.createLabel(msg + " - Test Case Skipped", ExtentColor.ORANGE));
				}
				msg1 = msg1 + " Step skipped";

				try {
					setTextOrange(msg1 + " -  Skipped");
				} catch (Exception e) {
					// e.printStackTrace();
				}
			}

			else if (status.equals("INFO")) {
				if (screenshotEnabled) {
					String screenshot = TestBase.getScreenshot(driver);
					childnode.info(msg, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				} else {
					childnode.log(Status.INFO, MarkupHelper.createLabel(msg, ExtentColor.WHITE));
				}
				try {
					setText(msg1 );
				} catch (Exception e) {
					// e.printStackTrace();
				}
				// logger.log(Status.INFO, MarkupHelper.createLabel(msg, ExtentColor.WHITE));
				// } else if (status.equals("INFO")) {
				// logger.log(Status.INFO, MarkupHelper.createLabel(msg, ExtentColor.WHITE));
			}

		} catch (Exception e) {
			// e.printStackTrace();
		}
		extent.flush();
		

	}

	public static void log_global_failStatus() {

		try {
			logger = extent.createTest("Test Status");
			logger.log(Status.FAIL, MarkupHelper.createLabel(globalmsg, ExtentColor.RED));
			extent.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void lognodeStatus(String status, String msg) {

		try {
			if (status.equals("PASS")) {
				msg = msg + " -  Passed";

				logger.log(Status.PASS, MarkupHelper.createLabel(msg, ExtentColor.GREEN));

			} else if (status.equals("FAIL")) {

				logger.log(Status.FAIL, MarkupHelper.createLabel(msg + " -  Failed", ExtentColor.RED));

			} else if (status.equals("SKIP")) {
				logger.log(Status.SKIP, MarkupHelper.createLabel(msg + " - Test Case Skipped", ExtentColor.ORANGE));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		extent.flush();
	}

	public boolean verifyElementText(String xpath, String text) throws Exception {
		boolean verified = false;
		String elementText = getElementText(xpath);
		System.out.println("---" + elementText);

		// In case of INPUT getText() returns ""(empty String), try getting the
		// attribute value
		if (elementText.equals(""))
			elementText = getElementAttribute(xpath, "value");
		System.out.println("---" + elementText);
		// check if expected text is same as the actual
		if (elementText.equalsIgnoreCase(text))
			verified = true;

		return verified;
	}
	// finds if the test suite is runnable

	public static String findfilename(String text) {
		// Wait for page to load
		if ((Browser.equalsIgnoreCase("FireFox"))) {
			text = text + "_ff.png";
		}
		if ((Browser.equalsIgnoreCase("Chrome"))) {
			text = text + "_chrome.png";
		}
		if ((Browser.equalsIgnoreCase("Edge"))) {
			text = text + "_edge.png";
		}
		return text;
	}

	public void closeAllOtherTabs() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		for (int i = 1; i < tabs.size(); i++) {
			driver.switchTo().window(tabs.get(i));
			driver.close();
		}
		driver.switchTo().window(tabs.get(0));
	}

	public void selectValueFromDropdown(String geoeventdefinitiondropdownXpath, String value) {
		CommonDropDown_Select(geoeventdefinitiondropdownXpath);
		sync(2);
		String MENUITEM_XPATH = geoeventdefinitiondropdownXpath.replace("']", "_dropdown']")
				+ "//tr//td[@class='dijitReset dijitMenuItemLabel']";
		// String MENUITEM_XPATH = geoeventdefinitiondropdownXpath.replace("']",
		// "_dropdown']") + "//tr[contains(@id,'dijit_MenuItem_')]";

		List<WebElement> items = driver.findElements(By.xpath(MENUITEM_XPATH));
		for (WebElement item : items) {
			String menuItem = item.getText();
			// String menuItem = item.getAttribute("aria-label");
			if (menuItem.contains("  ")) {
				menuItem = menuItem.split("  ")[0];
			}
			System.out.println(menuItem + "  --  " + value);
			if (menuItem.equalsIgnoreCase(value)) {
				item.click();
				break;
			}
		}
	}

	public void selectValueFromMenueItem(String assigntodropdownmenuitemXpath, String value) {
		List<WebElement> items = driver.findElements(By.xpath(assigntodropdownmenuitemXpath));
		for (WebElement item : items) {
			String menuItem = item.getText();

			System.out.println(menuItem + "  --  " + value);
			if (menuItem.equalsIgnoreCase(value)) {
				item.click();
				System.out.println(menuItem + " selected");
				break;
			}
		}
	}

	public static boolean elementIsDisplayed(String xPath) {
		boolean iselementPresent = false;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		iselementPresent = driver.findElement(By.xpath(xPath)).isDisplayed();
		System.out.println("iselementPresent: " + iselementPresent);
		return iselementPresent;
	}

	public static boolean elementIsDisplayed(String xPath, int time) {
		boolean iselementPresent = false;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		iselementPresent = driver.findElement(By.xpath(xPath)).isDisplayed();
		System.out.println("iselementPresent: " + iselementPresent);
		return iselementPresent;
	}

	public boolean elementexist(String xPath) {
		boolean iselementPresent = false;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		List<WebElement> itemListString = driver.findElements(By.xpath(xPath));
		if (itemListString.size() > 0) {
			iselementPresent = true;
		}
		System.out.println("iselementPresent: " + iselementPresent);
		return iselementPresent;
	}

	public boolean elementexist(String xPath, int waittime) {
		boolean iselementPresent = false;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waittime));
		List<WebElement> itemListString = driver.findElements(By.xpath(xPath));
		if (itemListString.size() > 0) {
			iselementPresent = true;
		}
		System.out.println("iselementPresent: " + iselementPresent);
		return iselementPresent;
	}

	public void waitforelement(String xpath, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

	}

	public void switchtoqueryframe() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("previewConfigFrame");
	}

	public void switchtoqueryframe1() {
		// driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
	}

	public void switchToWidgetTemplateFrame() {
		WebElement frame = driver.findElement(By.xpath("//iframe[contains(@id,'ijit__WidgetsInTemplate')]"));
		driver.switchTo().frame(frame);
	}

	public void switchtodefaultContent() {
		driver.switchTo().defaultContent();
	}

	public void waitforelement(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

	}

	public void waitforinvisibilityofelement(String xpath) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));

	}

	public void waitforvisibilityofelement(String xpath, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

	}

	public void waitforinvisibilityofelement(String xpath, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));

		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));

	}

	public void confirmdelete11_2_0() {
		List<WebElement> elements = getListOfWebElements("//calcite-modal//calcite-button[@slot='primary']");
		for (int i = 0; i <= elements.size(); i++) {
			if (elements.get(i).isDisplayed()) {
				elements.get(i).click();
				break;
			}
		}
	}

	public void confirmdelete11_5_0() {
		List<WebElement> elements = getListOfWebElements("(//calcite-dialog[2]/calcite-button[@slot='footer-end'])[4]");
		for (int i = 0; i <= elements.size(); i++) {
			if (elements.get(i).isDisplayed()) {
				elements.get(i).click();
				break;
			}
		}
	}

	public void confirmdelete12_0_0() {
		List<WebElement> elements = getListOfWebElements("((//calcite-dialog)[7]//p//..//calcite-button)[2]");
		for (int i = 0; i <= elements.size(); i++) {
			if (elements.get(i).isDisplayed()) {
				elements.get(i).click();
				break;
			}
		}
	}

	public void confirmDialogdelete11_5_0() {
		List<WebElement> elements = getListOfWebElements("//calcite-dialog/calcite-button[text()='Delete member']");
		for (int i = 0; i <= elements.size(); i++) {
			if (elements.get(i).isDisplayed()) {
				elements.get(i).click();
				break;
			}
		}
	}

	public void waitforinvisibilityofelement(WebElement ele, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));

		wait.until(ExpectedConditions.invisibilityOf(ele));

	}

	public WebElement fluentwaitforelement(String xpath) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofSeconds(10));
		// .ignoring(NoSuchElementException.class);
		WebElement seleniumelement = wait.until(new Function<WebDriver, WebElement>() {

			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpath));
			}
		});
		return seleniumelement;
	}

	public static boolean checkSorting(String JOBLIST_XPATH) throws InterruptedException {

		System.out.println("checkSorting: Webelement");
		// Get All Weblement from List
		List<WebElement> itemListTags = driver.findElements(By.xpath(JOBLIST_XPATH));

		// Check Size
		System.out.println("itemListTags size: " + itemListTags.size());

		// Take an Array List of type String
		// List<String> itemListString= new ArrayList<String>();
		ArrayList<String> alist = new ArrayList<String>();

		// Add Text of all weblements to arraylist
		for (int i = 0; i < itemListTags.size(); i++) {
			alist.add(itemListTags.get(i).getText().toString());
		}
		// Confrom All elements are added
		System.out.println("All items added");

		// Make copy of unsoted arraylist
		List<String> presort = alist;

		// Sort the array list
		Collections.sort(alist);

		boolean sorted = false;
		// Alternate method to check id list is sorted
		// sorted = checkAscendingOrder(alist);

		// Check if Sorted and unsorted arraylist match
		if (alist.toString().equals(presort.toString())) {
			// if (sorted== true){
			sorted = true;
			// System.out.println("Products names are Sorted in ascending Order");
		} else {
			// System.out.println("Products names are not Sorted in ascending Order");
		}
		return sorted;
	}

	public static boolean checkAscendingOrder(ArrayList<String> texts) {
		System.out.println("In checkAscendingOrder: ");

		String previous = ""; // empty string

		for (String current : texts) {
			if (current.compareTo(previous) < 0) {
				return false;
			}
			previous = current;
		}
		return true;
	}

	public static void ScrollBottom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.scrollHeight)");
	}

	public static void ScrollTop() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(document.scrollHeight,0)");
	}

	public static boolean verifylowToHigh(String PRIORITYLIST_XPATH) throws InterruptedException

	{
		List<WebElement> priorityListTags = driver.findElements(By.xpath(PRIORITYLIST_XPATH));
		System.out.println(priorityListTags.size());
		ArrayList<String> alist = new ArrayList<String>();
		// for (int i = 0; i<priorityListTags.size(); i=i+1)
		{

			// Add Text of all weblements to arraylist
			for (int i = 0; i < priorityListTags.size(); i++) {
				alist.add(priorityListTags.get(i).getText().toString());
				System.out.println(priorityListTags.get(i).getText());
			}
			// Confrom All elements are added
			System.out.println("All items added");

			// Make copy of unsoted arraylist
			// List<String> presort = alist;

			// Sort the array list
			Collections.sort(alist);
		}
		return true;
	}

	public void clearAndPressEnterKey(String XPATH, String txtValue) {
		WebElement txtBox = (new WebDriverWait(driver, Duration.ofSeconds(60)))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
		txtBox.clear();
		sync(2);
		txtBox.sendKeys(Keys.ENTER);
	}

	public void pressEnterKey() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
	}

	public boolean verifyToolTip(String xpath, String tooltip) {
		try {
			WebElement element = CommonFunction.getWebElement("xpath", xpath);
			String title = element.getAttribute("title");
			if (title.equalsIgnoreCase(tooltip)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	public void hoverOnElement(WebElement elementHover) {
		String strJavaScript = "var element = arguments[0];"
				+ "var mouseEventObj = document.createEvent('MouseEvents');"
				+ "mouseEventObj.initEvent( 'mouseover', true, true );" + "element.dispatchEvent(mouseEventObj);";
		((JavascriptExecutor) driver).executeScript(strJavaScript, elementHover);
	}

	public void hoverByJS(WebElement elementHoverV) {
		/*
		 * String strJavaScript = "var element = arguments[0];" +
		 * "var mouseEventObj = document.createEvent('MouseEvents');" +
		 * "mouseEventObj.initEvent( 'mouseover', true, true );" +
		 * "element.dispatchEvent(mouseEventObj);";
		 */
		String strJavaScript = "var evObj = document.createEvent('MouseEvents');"
				+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
				+ "arguments[0].dispatchEvent(evObj);";
		((JavascriptExecutor) driver).executeScript(strJavaScript, elementHoverV);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementHoverV);
		// elementHoverV.click();

		/*
		 * String strJavaScript = "var evObj = document.createEvent('MouseEvents');" +
		 * "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
		 * + "arguments[0].onmouseover();"; ((JavascriptExecutor)
		 * driver).executeScript(strJavaScript, elementHoverV);
		 */

		/*
		 * String mouseOverScript =
		 * "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}"
		 * ; JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript(mouseOverScript, elementHoverV);
		 */
		// System.out.println("strJavaScript"+ strJavaScript);

		/*
		 * JavascriptExecutor jsHover = (JavascriptExecutor)driver;
		 * jsHover.executeScript("var evObj = document.createEvent('MouseEvents');" +
		 * "evObj.initMouseEvent('mouseover',true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
		 * + "arguments[0].dispatchEvent(evObj);", element);
		 * jsHover.executeScript("arguments[0].click();",element);
		 */

		/*
		 * try { String mouseOverScript =
		 * "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}"
		 * ; ((JavascriptExecutor) driver).executeScript(mouseOverScript,elementHover);
		 * Thread.sleep(1000);
		 * ((JavascriptExecutor)driver).executeScript("arguments[0].click();",
		 * elementHover);
		 * 
		 * 
		 * } catch (Exception e) { // TODO: handle exception }
		 */
	}

	public void hoverByAction(String createDefault_xpath) {
		WebElement hoverElem = CommonFunction.getWebElement("xpath", createDefault_xpath);
		Actions builder = new Actions(driver);
		builder.moveToElement(hoverElem).build().perform();

		/*
		 * Actions builder = new Actions(driver);
		 * builder.moveToElement(hoverElem).click(hoverElem).build().perform();
		 */
		// builder.moveToElement(hoverElem).perform();
		// By locator = By.id("clickElementID");
		// builder.click(hoverElem);

	}

	public void hoverByAction(WebElement hoverElem) {
		Actions builder = new Actions(driver);
		builder.moveToElement(hoverElem).build().perform();

		/*
		 * Actions builder = new Actions(driver);
		 * builder.moveToElement(hoverElem).click(hoverElem).build().perform();
		 */
		// builder.moveToElement(hoverElem).perform();
		// By locator = By.id("clickElementID");
		// builder.click(hoverElem);

	}

	public void PressEnterByAction() {

		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();

	}

	public static String getCSSValue(String xpath, String css) throws Exception {
		String value = "";
		WebElement element = getWebElement("xpath", xpath);
		value = element.getCssValue(css);
		return value;
	}

	public WebElement getElementByIndex(String xpath, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		List<WebElement> list = driver.findElements(By.xpath(xpath));
		WebElement element = list.get(index);
		wait.until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public static String rgbToHex(String colorString) {
		String hex = null;
		// check the rgb
		int rgbStartIndex = colorString.indexOf("rgb");
		int rgbEndIndex = colorString.indexOf(")") + 1;
		if (rgbStartIndex > 0) {
			colorString = colorString.substring(rgbStartIndex, rgbEndIndex);
		}
		System.out.println("=> Color is : " + colorString);
		hex = org.openqa.selenium.support.Color.fromString(colorString).asHex();
		System.out.println("=> The hex conversion is : " + hex);

		return hex;
	}

	public boolean verifyIfHighlighted(String xPath) throws Exception {
		String color = getCSSValue(xPath, "background-color");
		String hex = rgbToHex(color);
		System.out.println("Color ---> " + hex);
		if (hex.equalsIgnoreCase("#0079c1"))
			return true;
		return false;
	}

	public boolean verifyIfHighlighted(String xPath, String attribute) throws Exception {
		String color = getCSSValue(xPath, "background-color");
		if (attribute.equals("border"))
			color = getCSSValue(xPath, "border");
		String hex = rgbToHex(color);
		System.out.println("Color ---> " + hex);
		if (hex.equalsIgnoreCase("#0079c1"))
			return true;
		return false;
	}

	public String getUniqueName(String name) {
		Date datef = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy_MM_dd_HHmm");
		name = name + "_" + dateformat.format(datef);
		System.out.println("Service name  --- " + name);
		return name;
	}

	// get the Devtopia Reference from Excel for each test
	/*
	 * public static String getDevtopiaReference(String testName){ String
	 * devtopiaRef = "Devtopia Reference : No Reference Found"; try { String
	 * excelSheet ="Executable Suite";
	 * 
	 * for(int i=2; i <= xls.getRowCount(excelSheet) ;i++ ){ String testscenario =
	 * xls.getCellData(excelSheet, "Test Scenario", i);
	 * if(testName.equalsIgnoreCase(testscenario)){ int ref = (int)
	 * Double.parseDouble(xls.getCellData(excelSheet, "Devtopia", i)); devtopiaRef =
	 * "Devtopia Reference : <a href='https://devtopia.esri.com/ArcGISPro/ps-software-release/issues/"
	 * + ref+"' target='_blank'> #"+ ref + " </a>"; break; } } }catch(Exception e) {
	 * e.printStackTrace(); } return devtopiaRef; }
	 * 
	 * private static String getName(String testName) { String name = ""; try {
	 * String excelSheet ="Executable Suite"; for(int i=2; i <=
	 * xls.getRowCount(excelSheet) ;i++ ){ String testscenario =
	 * xls.getCellData(excelSheet, "Test Scenario", i);
	 * if(testName.equalsIgnoreCase(testscenario)){ name =
	 * xls.getCellData(excelSheet, "Name", i); } } }catch(Exception e) {
	 * e.printStackTrace(); } return name; }
	 */
	public static void setUpTest(boolean flagdevtopia) {
		try {
			String hostname = "COMPUTERNAME";
			hostname = System.getenv(hostname);
			testName = new Throwable().getStackTrace()[1].getMethodName();
			System.out.println("Test -->> " + testName);
			String name = testName;
			String devtopiaReference = null;
			/*
			 * if (flagdevtopia) { name = getName(testName); devtopiaReference =
			 * getDevtopiaReference(testName); System.out.println(name + " === "+
			 * devtopiaReference); }
			 */

			if (extent != null) {
				if (flagdevtopia)
					logger = extent.createTest(name, devtopiaReference);
				else
					logger = extent.createTest(name);
				// Mark that the Extent test node for the current TestNG method
				// has been created; reset childnode so subsequent step logs
				// don't accidentally land in a previous test's child node.
				childnode = null;
				extentNodeCreated = true;
			}

			else
				System.out.println("Got uuuuuuuuuuuuuuuuuuuuuuuuuuuuu");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void pressTAB() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).build().perform();
		sync(1);
		action.sendKeys(Keys.TAB).build().perform();
		sync(1);
		action.sendKeys(Keys.TAB).build().perform();
		sync(1);
		action.sendKeys(Keys.TAB).build().perform();
		sync(1);
		action.sendKeys(Keys.TAB).build().perform();
		sync(1);
		action.sendKeys(Keys.TAB).build().perform();
	}

	public String getCurrentDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String currentDate = simpleDateFormat.format(new Date());
		System.out.println("Current Date -- >" + currentDate);
		return currentDate;
	}

	public boolean verifyIfHighlighted1(String xPath, String attribute, String Bcolor) throws Exception {
		String color = getCSSValue(xPath, "background-color");
		if (attribute.equals("color"))
			color = getCSSValue(xPath, "border-bottom-color");
		String hex = rgbToHex(color);
		System.out.println("Color ---> " + hex);
		if (hex.equalsIgnoreCase(Bcolor))
			return true;
		return false;
	}

	public static void OpenURL(String url) {

		try {
			driver.get(url);
			waitforpagetoload();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				driver.get(url);
				waitforpagetoload();
			} catch (Exception e1) {
				e1.printStackTrace();
				CommonFunction.logStatus("FAIL", "Navigating to URL ");
			}
		}
	}

	/********************
	 * Log Status with actual and expected Screenshots & Submit Defect
	 * 
	 * @throws Exception
	 ******************************************/
	public static void logStatus(String status, String msg, String imgStored, int bugId) {

		msg = stepCount++ + " - " + msg;
		// String bugLink = "Devtopia Bug Reference : <a
		// href='https://github.com/arcgis/indoors-webapp/issues/" + bugId+"'
		// target='_blank'> #"+ bugId + " </a>";
		System.out.println("Entered in log");
		try {
			if (status.equals("PASS")) {
				msg = msg + " -  Passed";
				if (screenshotEnabled) {
					String screenshot = TestBase.getScreenshot(driver);
					logger.pass(msg + " -  Passed",
							MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
				} else {
					logger.log(Status.PASS, MarkupHelper.createLabel(msg, ExtentColor.GREEN));
				}
			} else if (status.equals("FAIL")) {

				String screenshot = TestBase.getScreenshot(driver);
				/*
				 * byte[] image = IOUtils.toByteArray(new FileInputStream(
				 * System.getProperty("user.dir") + "/test-output/ExtentReports/Latest/" +
				 * screenshot)); image = ImageUtil.compressImage(image); String finalImage =
				 * Base64.getEncoder().encodeToString(image);
				 */
				childnode.fail(msg, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

				try {
					setTextRed(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (status.equals("SKIP")) {
				logger.log(Status.SKIP, MarkupHelper.createLabel(msg + " - Test Case Skipped", ExtentColor.ORANGE));
			}
			extent.flush();
		} catch (Exception e) {
			e.printStackTrace();
			extent.flush();
		}
	}

	public static void observedImage(String image1, String image2) throws IOException {

		String path = System.getProperty("user.dir") + "/Images/ReportScreenshot";
		String path2 = System.getProperty("user.dir") + "/test-output/ExtentReports/Latest/Screenshot";

		// String screenshot = TestBase.getScreenshot(driver);
		// load source images
		BufferedImage image = ImageIO.read(new File(path, image1));
		BufferedImage overlay = ImageIO.read(new File(path2, image2));

		/// do some calculate first
		int offset = 50;
		int wid = image.getWidth() + overlay.getWidth() + offset;
		int height = Math.max(image.getHeight(), overlay.getHeight()) + offset;
		// create a new buffer and draw two image into the new image
		BufferedImage combined = new BufferedImage(wid, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = combined.createGraphics();
		Color oldColor = g2.getColor();
		// fill background
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, wid, height);
		// draw image
		g2.setColor(oldColor);
		g2.drawImage(image, null, 350, 0);
		g2.drawImage(overlay, null, image.getWidth() + offset, 100);
		g2.dispose();
		ImageIO.write(combined, "PNG", new File(path, "combined.png"));

	}

	public static void expectedImage(String image1, String image2) throws IOException {
		String path1 = System.getProperty("user.dir") + "/Images/ReportScreenshot";

		// load source images
		BufferedImage overlay = ImageIO.read(new File(path1, image2));
		BufferedImage image = ImageIO.read(new File(path1, image1));

		// do some calculate first
		int offset = 50;
		int wid = image.getWidth() + overlay.getWidth() + offset;
		int height = Math.max(image.getHeight(), overlay.getHeight()) + offset;
		// create a new buffer and draw two image into the new image
		BufferedImage combined1 = new BufferedImage(wid, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = combined1.createGraphics();

		g2.drawImage(image, null, 250, 10);
		g2.drawImage(overlay, null, image.getWidth() + offset, 100);
		g2.dispose();
		ImageIO.write(combined1, "PNG", new File(path1, "combined1.png"));
	}

	public static String finalImage(String image1, String image2) throws IOException {

		String path = System.getProperty("user.dir") + "/Images/ReportScreenshot";
		String savedScreenShotPath = System.getProperty("user.dir") + "/test-output/ExtentReports/Latest/Screenshot";
		// load source images
		BufferedImage image = ImageIO.read(new File(path, image1));
		BufferedImage overlay = ImageIO.read(new File(path, image2));

		// IndexColorModel cm = new IndexColorModel()
		// do some calculate first
		int offset = 100;
		int wid = image.getWidth() + overlay.getWidth() + offset;
		int height = Math.max(image.getHeight(), overlay.getHeight()) + offset;
		// create a new buffer and draw two image into the new image

		BufferedImage screenshot = new BufferedImage(wid, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = screenshot.createGraphics();

		Color oldColor = g2.getColor();
		// fill background
		g2.setPaint(Color.WHITE);
		g2.fillRect(0, 0, wid, height);
		// draw image
		g2.setColor(oldColor);
		g2.drawImage(image, null, image.getWidth() + offset, 0);
		g2.drawImage(overlay, null, image.getWidth() + offset, 0);
		g2.dispose();
		String FinalScreenShot = "FinalScreenShot_" + System.currentTimeMillis() + ".png";
		ImageIO.write(screenshot, "PNG", new File(savedScreenShotPath, FinalScreenShot));
		return FinalScreenShot;
	}

	public static String submitDefectGitHub() {
		String GitHubRef = "Submit Defect : No Reference Found";

		try {
			// JavascriptExecutor js=(JavascriptExecutor)driver;
			// String str1="Functionality test for US Courts is completed.Please see reports
			// " ;
			// js.executeScript("alert(arguments[0]);",str1);

			GitHubRef = "<a href='https://github.com/EsriPS/PS-Regression/issues/new ' target='_blank'> Submit Defect </a>";
			// GitHubRef = "<a
			// href='https://Github.esri.com/webgis/workflow-manager/issues/new'
			// target='_blank'> Submit Defect </a>";
			// GitHubRef = "<a href='https://Github.esri.com/webgis/issues/new'
			// target='_blank'> Submit Defect </a>";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return GitHubRef;
	}

	/********************
	 * Log Status with actual and expected Screenshots
	 ******************************************/
	private static String addBugId(int bugId) {
		String bug;
		try {
			bug = "Bug ID : <a href='https://devtopia.esri.com/WebGIS/workflow-manager/issues/" + bugId
					+ "' target='_blank'> WebGIS/workflow-manager#" + bugId + " </a>";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return bug;
	}

	public void switchToLoginWindow() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
	}

	public void switchToMainWindow() throws Exception {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
	}

	public void Sanity_finish_dialog() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('Sanity Testing over, Please see reports');");
		sync(10);
		driver.switchTo().alert().accept();
	}

	public String getWebelementText_JSpath(String JSPATH) {
		sync(2);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String value = (String) js.executeScript("return " + JSPATH + ".value");
		return value;
	}

	public void Webelement_JSClick(String JSPATH) {
		sync(2);
		
		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = (WebElement) js.executeScript("return " + JSPATH);
		*/
		WebElement element =waitforwebelement(JSPATH);
		element.click();

	}

	public void Webelement_JSClickbasedonText(String JSPATH, String text) {
		sync(2);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> elementList = (List<WebElement>) js.executeScript("return " + JSPATH);
		for (int i = 0; i < elementList.size(); i++) {
			if (text.equalsIgnoreCase(elementList.get(i).getText())) {
				elementList.get(i).click();
				break;
			}
		}
	}

	public void Webelement_JSClickbasedonText11_4(String ListJSPATH, String text) {
		sync(3);
		List<WebElement> list1 = WebelementList_JSPath(ListJSPATH);
		for (int i = 0; i < list1.size(); i++) {
			String PATH = "document.querySelector('arcgis-new-item-pages-tile-layer-from-existing').shadowRoot.querySelector('arcgis-new-item-feature-layer-browser').shadowRoot.querySelector('arcgis-item-browser > arcgis-item-browser-content > arcgis-item-browser-card:nth-child("
					+ (i + 1)
					+ ")').shadowRoot.querySelector('arcgis-item-card').shadowRoot.querySelector('section > div.item-browser-card__upper div.item-browser-card-flex > h3')";
			String listName = Webelement_JSPath(PATH).getText();
			if (text.equalsIgnoreCase(listName)) {
				list1.get(i).click();
				break;
			}
		}
	}

	public void Webelement_JSInput(String JSPATH, String inputText) {
		sync(2);
		waitforwebelement(JSPATH);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = (WebElement) js.executeScript("return " + JSPATH);
		element.clear();
		element.sendKeys(inputText);

	}

	public void Webelement_JSActInput(String JSPATH, String inputText) {
		sync(2);
		waitforwebelement(JSPATH);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = (WebElement) js.executeScript("return " + JSPATH);
		element.clear();
		Actions act = new Actions(driver);
		act.sendKeys(element, inputText).build().perform();

	}

	public WebElement Webelement_JSPath(String JSPATH) {
		waitforwebelement(JSPATH);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = (WebElement) js.executeScript("return " + JSPATH);
		return element;

	}

	public void ScrollToWebElement(String JSPATH) {
		try {
			waitforwebelement(JSPATH);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement scrollElement = (WebElement) js.executeScript("return " + JSPATH);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", scrollElement);
			Thread.sleep(500);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ScrollToJsElement(WebElement webelementjspath) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
				    "arguments[0].scrollIntoView({block: 'center'});",
				    webelementjspath
				);Thread.sleep(500);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public WebElement waitforwebelement(String JSPATH) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
		JavascriptExecutor js = (JavascriptExecutor) driver;

           return wait.until(d -> {
            Object obj = js.executeScript("return " + JSPATH);
            if (!(obj instanceof WebElement)) return null;

            WebElement el = (WebElement) obj;
            try {
                if (el.isDisplayed() && el.isEnabled()) {
                    // Scroll into view
                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", el);
                    return el;
                }
            } catch (StaleElementReferenceException | NoSuchElementException e) {
                return null;
            }
            return null;
        });
    

	}

	public void Webelement_JSInput2(String JSPATH, String inputText) {
		sync(2);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement elm = getWebelement_JSpath(JSPATH);
		Actions act = new Actions(driver);
		act.moveToElement(elm).sendKeys(inputText).build().perform();

	}

	public void selectlastCity(String xpath) {
		List<WebElement> list = driver.findElements(By.xpath(xpath));
		list.get(list.size() - 1).click();

	}

	public static void logStatusWithException(String status, String msg, Exception exp) throws Exception {
		String msg1 = msg;
		msg = stepCount++ + " - " + msg;
		try {

			if (status.equals("PASS")) {

				msg = msg + " -  Passed";

				if (screenshotEnabled) {

					String screenshot = TestBase.getScreenshot(driver);

					logger.pass(msg, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());

				} else {

					logger.log(Status.PASS, MarkupHelper.createLabel(msg, ExtentColor.GREEN));

				}
				try {
					setText(msg+ " - Passed");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			else if (status.equals("FAIL")) {
				VDFailResult++;
				//VDFailResult = VDFailResult + 1;
				msg = msg + " - Failed" + "<br />" + submitDefectGitHub() + "<br />  <details> \r\n"

						+ "<summary style='color: #e57373;font-weight: bold;'>Logs</summary>\r\n"

						+ "<p><pre>" + exp + "</pre></p></details>\r\n"

						+ "";

				// if(screenshotEnabled) {

				String screenshot = getScreenshot(driver);

				/*
				 * byte[] image = IOUtils.toByteArray(new FileInputStream(
				 * System.getProperty("user.dir") +
				 * "/test-output/ExtentReports/Latest/Screenshot")); image =
				 * ImageUtil.compressImage(image); String finalImage =
				 * Base64.getEncoder().encodeToString(image);
				 * 
				 * childnode.fail(msg,
				 * MediaEntityBuilder.createScreenCaptureFromBase64String(finalImage).build());
				 */

				if (screenshot != null && !screenshot.isEmpty()) {
					try {
						childnode.fail(msg, MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
					} catch (Exception screenshotEx) {
						childnode.fail(msg);
					}
				} else {
					childnode.fail(msg);
				}

				try {
					setTextRed(msg1 + " - Failed");
					setTextRed(exp.getLocalizedMessage());
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (status.equals("SKIP")) {

				logger.log(Status.SKIP, MarkupHelper.createLabel(msg + " - Test Case Skipped", ExtentColor.ORANGE));
				try {
					setTextOrange(msg1 + " - Skipped");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		extent.flush();

	}

	public void switchTowindow(int tab) {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		driver.switchTo().window(tabs.get(tab));
	}

	public List getListOfWebElements(String Xpath) {
		List<WebElement> eleList = driver.findElements(By.xpath(Xpath));
		return eleList;
	}

	public void selectCensusmapfromList(String xpath, String element) throws InterruptedException {

		// List of elements in the table
		List<WebElement> list = driver.findElements(By.xpath(element));
		int siz = list.size() - 1;

		for (int i = siz; i > 0; i--) {

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", list.get(siz));
			Thread.sleep(3000);
			// List of elemenys name
			List<WebElement> textlist = driver.findElements(By.xpath(xpath));
			for (int j = 0; j <= siz; j++) {

				System.out.print(textlist.get(j).getText() + "  ");
				if (textlist.get(j).getText().contains("Census")) {
					list.get(j).click();
					break;
				}
			}
			// Again taking the list of visible elements in the scrolling table
			List<WebElement> list2 = driver.findElements(By.xpath(element));
			if (list2.size() != siz + 1) {
				siz = list2.size() - 1;
				list = list2;
			} else
				break;
		}
	}

	public List<WebElement> WebelementList_JSPath(String JSPATH) {
		sync(2);

		List<WebElement> elelist = new ArrayList<>();
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Execute JavaScript to get a NodeList of elements
		Object nodeList = js.executeScript("return " + JSPATH);

		// Convert the NodeList to a List<WebElement>
		if (nodeList instanceof List<?>) {
			elelist = (List<WebElement>) nodeList;
			elelist.size();
		} else if (nodeList instanceof Iterable<?>) {
			for (Object element : (Iterable<?>) nodeList) {
				if (element instanceof WebElement) {
					elelist.add((WebElement) element);
				}
			}
		}
		return elelist;

	}

	public static void DeleteFile(String destinationFolderPath, String fileToBeDeleted) {

		// Specify the folder path
		String folderPath = destinationFolderPath;

		// Specify the file name to be deleted
		String fileNameToDelete = fileToBeDeleted;

		// Create a File object for the folder
		File folder = new File(folderPath);

		// Check if the folder exists
		if (folder.exists() && folder.isDirectory()) {
			// List all files in the folder
			File[] files = folder.listFiles();

			// Iterate through the files
			for (File file : files) {
				// Check if the file has the specified name
				if (file.getName().contains(fileNameToDelete)) {
					// Attempt to delete the file
					if (file.delete()) {
						System.out.println("File deleted successfully: " + file.getAbsolutePath());
					} else {
						System.out.println("Failed to delete the file: " + file.getAbsolutePath());
					}
					// Break out of the loop after deleting the file (optional)
					break;
				}
			}
		} else {
			System.out.println("Folder does not exist or is not a directory.");
		}
	}
	
	/**
	 * Simple log method — delegates to EDCMainFrame.writeStatus() so messages
	 * appear in the new ResultsLogPanel as plain text.
	 */
	public static void setText(String txt) {
		writeStatus("default", txt);
	}

}

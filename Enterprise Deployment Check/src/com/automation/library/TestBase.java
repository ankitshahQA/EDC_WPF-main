package com.automation.library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
//import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
//import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
//import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
//import org.sikuli.natives.SysUtil;
import org.testng.asserts.SoftAssert;

import com.automation.testcases.EDCMainFrame;
import com.aventstack.extentreports.AnalysisStrategy;
//import com.automation.powershell.Powershell;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

@SuppressWarnings("serial")
public class TestBase extends EDCMainFrame {
	// public static JFrame f1 ;
	// public JLabel l1;
	// public static DialogControl cd1;
	// public static RemoteWebDriver driver;
	// public JDialog d1 ;
	public boolean skipSuite;
	public static boolean DashboardPageFlag=false;
	public static boolean DashboardCreatedFlag=false;
	public static boolean dashboard =false;
	public static boolean ObjectStore=false;
	public static String VersionToArchive ="";
	public static String networkPath="not found";
	public static int isStepFailed;
	public static int stepCount=1;
	public static int VDFailResult=0;
	public static String DeploymentType="";
	public static String Appversion="";
	//public static int VDPassResult=0;
	public static String videoname="";
	public static boolean videocature=true;
	public static String recordingPath="not found";;
	public static FileInputStream config;
	public static String webserviceurl = "https://sampleserver6.arcgisonline.com/arcgis/rest/services/Census/MapServer";
	public static SoftAssert softAssert = new SoftAssert();
	public static ExtentReports extent;
	public static ExtentTest logger;
	public static ExtentTest childnode;
	/**
	 * Tracks whether an Extent test node has already been created for the
	 * currently executing TestNG @Test method. Reset to false in @BeforeMethod
	 * and set to true the first time CommonFunction.setUpTest() OR
	 * TestSuite.skipTest() creates the node. Prevents duplicate / merged nodes
	 * in the report when a test is skipped after partial execution.
	 */
	public static boolean extentNodeCreated = false;
	public static boolean logindone = false;
	public static boolean foldercreateddone=false;
	// public static Xls_Reader xls = null;
	public static WebElement element = null;
	public static boolean loggedIn = false;
	public static boolean session = true;
	public static String AddFromWebLayerTitle = "";
	public static String globalmsg = "";
	// public static boolean testreport=true;
	public static Date date = null;
	public SimpleDateFormat sdf = null;
	public String formattedDate = null;
	public Calendar cal = null;
	
	public static ExtentSparkReporter htmlReporter;
	public static ExtentSparkReporter htmlReporter_latest;
	// public static String extentReportFilePath =null;
	public static String extentReportFilePath_BrowserNameOnly = null;
	
	public static int counter = 1;
	public static String locale;
	// public static String msg="";
	public static String testName;
	public static boolean SAMLLogin=true;
	// public static String URL;
	// public static String adminURL;
	// public static String UserName;
	// public static String Password;
	// public static String projectName;
	public static String projectVersion = "Not found";

	
	public static boolean screenshotEnabled = false;
	public static String FeatureName;
	public static String TileName;
	public static String FolderName;
	public static String BaseFolderName;
	public static String SceneTag;
	public static String SceneLayerName;
	public static String SceneTitle;
	public static String MapName;
	public static String WebAppBuilderName;
	public static String ExperienceBuilderName;
	public static String StoryMapName;
	public static String DashboardName;
	public static String GroupName;
	public static String Testuser1;
	public static String Testuser2;
	public static String title;
	public static int Time_wait_1 = 10;
	public static int Time_wait_2 = 15;
	public static int Time_wait_3 = 20;
	public static boolean featurecreated = true;
	public static boolean tilecreated = true;
	public static boolean webappcreated = true;
	public static boolean experiencecreated = true;
	public static boolean mapcreated = true;
	public static boolean member1created = true;
	public static boolean member2created = true;
	public static boolean groupcreated = true;
	public static boolean FeatureLayerExist=false;
	public static boolean storymapPage=false;
	public static String testcaseName = "";
	//public static String[] adminURL = new String[] { "", "", "", "", "", "", "", "", "", "" };
	//public static String[] adminUsername = new String[] { "", "", "", "", "", "", "", "", "", "" };
	//public static String[] adminPassword = new String[] { "", "", "", "", "", "", "", "", "", "" };
	//public static String[] serverRole = new String[] { "", "", "", "", "", "", "", "", "", "" };

	// public static Logger APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
	// //devpinoyLogger

	/*
	 * public static void setLocale() { locale = xls.getCellData("Configuration",
	 * "Value", 2); if(locale.equalsIgnoreCase("default") ) { try { CommonFunction
	 * cfunction = new CommonFunction(); String headerXpath =
	 * "//div[@id='geoevent_discovery_dijit_PageHeader_0']"; String locale =
	 * cfunction.getElementAttribute(headerXpath, "lang"); if(!locale.isEmpty())
	 * TestBase.locale = locale; } catch (Exception e) { e.printStackTrace(); } }
	 * System.out.println("Locale set to : " + locale); }
	 */
	
	  

	public static void getappversion() {
		if(!flagKUB) {
		List<WebElement> list2 = driver.findElements(By.xpath("//head/link[contains(@href,'css')]"));
		if (list2.size() > 0) {
			String href1 = driver.findElement(By.xpath("(//head/link[contains(@href,'css')])[1]")).getAttribute("href");
			String[] lst2 = href1.split("js")[0].split("/");
			projectVersion = lst2[lst2.length - 1];
			// projectVersion=href1.replace(URL, "").split("/")[0];
			System.out.println("Project version set to : " + projectVersion);
		}

	}else {
		projectVersion="Kubernetes";
		System.out.println("Project version set to : " + projectVersion);
	}
	}
	public WebElement getWebelement_JSpath(String JSPATH) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = (WebElement) js.executeScript("return " + JSPATH);
		return element;
	}

	public static void setTestVariables() {
		Date datef = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		FeatureName = "AutomationFeatureLayer_" + dateformat.format(datef).replace("-", "").replace(" ", "");
		TileName = "AutomationTileLayer_" + dateformat.format(datef).replace("-", "").replace(" ", "");
		SceneLayerName = "AutomationSceneLayer" + dateformat.format(datef).replace("-", "").replace(" ", "");
		SceneTag = "AutomationSceneTag_" + dateformat.format(datef).replace("-", "").replace(" ", "");
		SceneTitle = "AutomationMyScene" + dateformat.format(datef).replace("-", "").replace(" ", "");
		MapName = "AutomationMAP_" + dateformat.format(datef).replace("-", "").replace(" ", "");
		WebAppBuilderName = "AutomationWebApp_" + dateformat.format(datef).replace("-", "").replace(" ", "");
		ExperienceBuilderName="AutomationEXBuilder_" + dateformat.format(datef).replace("-", "").replace(" ", "");
		StoryMapName="AutomationStoryMap_" + dateformat.format(datef).replace("-", "").replace(" ", "");
		GroupName = "AutomationGroup_" + dateformat.format(datef).replace("-", "").replace(" ", "");
		Testuser1 = "AutomationUser1" + dateformat.format(datef).replace("-", "").replace(" ", "");
		Testuser2 = "AutomationUser2" + dateformat.format(datef).replace("-", "").replace(" ", "");
		AddFromWebLayerTitle = "AutomationCensus_" + dateformat.format(datef).replace("-", "").replace(" ", "");
		DashboardName="AutomationDashboard_" + dateformat.format(datef).replace("-", "").replace(" ", "");
		if ((flagcontent)||(projectVersion.equals("11.3.0"))) {
			FolderName = "Folder_" + dateformat.format(datef).replace("-", "").replace(" ", "");
			BaseFolderName="Folder_";
		} else {
			FolderName = driver.findElement(By.xpath("//*[@class='folder-list-title']//following-sibling::ul/li[2]"))
					.getAttribute("data-title");

		}
		
	}
	
	public static void publishNetworkLogsAndVideoRecording() {
		String data=" <br/> <details> <summary style='color: #12a6f4;font-weight: bold;'>Video Recording Location(Open in VLC Media player) </summary><pre>" +recordingPath +"\\"+videoname+"</pre></details>" + "<br/>";
		//data=data +" <br/> <details> <summary style='color: #12a6f4;font-weight: bold;'>Network Logs Location </summary><pre>" +networkPath+"</pre></details>" + "<br/>";
		logger.log(Status.FAIL, MarkupHelper.createLabel(data, ExtentColor.WHITE));
		
		//logger.log(Status.FAIL, MarkupHelper.createLabel("SKIPED", ExtentColor.ORANGE));
		
	}

	public static String getServerManagerURL(String text) {
		String text1 = "";
		// text1=text.replace("portal","");
		// text1=text1.replace("/home","");
		// text1=text1+"server/manager";
		text1 = text.replace("/login", "");
		text1 = text1.replace("admin", "manager");
		return text1;
	}
	public static void setCounter() {
		try {
		counter=0;
			if(flagRunalltest) {
				counter=13;
			}else {
			if(flagloginadmin)
				counter=counter+1;
			if(flagloginmanager)
				counter=counter+1;
			if(flagloginportal)
				counter=counter+1;
			if(flagcontent)
				counter=counter+1;
			if(flagfeaturelayer)
				counter=counter+1;
			if(flagtilelayer)
				counter=counter+1;
			if(flagscenelayer)
				counter=counter+1;
			if(flagmap)
				counter=counter+1;
			if(flagwebappbuilder)
				counter=counter+1;
			if(flagServerRole)
				counter=counter+1;
			if(flagdatastore)
				counter=counter+1;
			if(flagorganization)
				counter=counter+1;
			if(flaggroup)
				counter=counter+1;
			
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@SuppressWarnings("deprecation")
	public static void setup(String platform, String browser) throws Exception {
		//TestBase.browser = browser;
		String browserPath = "C:\\Automation\\Git\\ps-software-release\\AllJar\\WebDrivers";
		// String downloadFilepath = System.getProperty("user.home") + "\\Downloads\\";
		String downloadFilepath = System.getProperty("user.dir") + "\\Downloads\\";
		// Load Excel Setup
		// Load Excel Setup
		// System.out.println("Load Excel");
		try {

			if (((browser.equalsIgnoreCase("Firefox") && (platform.equalsIgnoreCase("Desktop"))))) {
				try {
					Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					// wdm.setup();
					//WebDriverManager.firefoxdriver().setup();
					
					
					FirefoxProfile profile = new FirefoxProfile();
					profile.setPreference("webgl.force-enabled", true);
					
					
					FirefoxOptions options = new FirefoxOptions();
					options.addArguments("--ignore-gpu-blocklist");
					options.addArguments("--use-gl");
					options.addArguments("--enable-webgl");
					// options.setCapability("moz:useNonSpecCompliantPointerOrigin", true);
					// options.setCapability("--disable-user-media-security",true);
					//// options.addArguments("--ignore-gpu-blacklist");
					// options.addArguments("--use-gl");

					options.setAcceptInsecureCerts(true);
					
					
					options.setProfile(profile);
					//
					driver = new FirefoxDriver(options);
					driver.manage().window().maximize();
				} catch (Exception e) {
					e.printStackTrace();
					try {
						try {
							Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
							System.out.println("kill instance of driver");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						//Time.sleep(5);
						//WebDriverManager.firefoxdriver().setup();
						FirefoxOptions options = new FirefoxOptions();
						options.addArguments("--ignore-gpu-blocklist");
						options.addArguments("--use-gl");
						// options.setCapability("moz:useNonSpecCompliantPointerOrigin", true);
						options.setAcceptInsecureCerts(true);

						driver = new FirefoxDriver(options);
						driver.manage().window().maximize();
						System.out.println("Setting path to firefox done");
					} catch (Exception e1) {

						e1.printStackTrace();
						session = false;
						globalmsg = "Not able to create " + browser + " browser session.Error message- "
								+ e1.getMessage().toString().split("\n")[0];
					}
				}
		


				}
			// Check if parameter passed as 'chrome'
			else if ((browser.equalsIgnoreCase("Chrome") && (platform.equalsIgnoreCase("Desktop")))) {
				browser = "Chrome";
				try {
					Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
				} catch (Exception e) {
					e.printStackTrace();
				}

				// set path to chromedriver.exe
				System.out.println("Setting path to chrome");
				System.out.println(System.getProperty("user.dir"));

				try {
					// wdm.clearResolutionCache();
					// System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Webdriver\\chromedriver.exe");
					// wdm.setup();

					//WebDriverManager.chromedriver().setup();
					HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
					chromePrefs.put("profile.default_content_settings.popups", 0);
					chromePrefs.put("credentials_enable_service", false);
					chromePrefs.put("profile.password_manager_enabled", false);
					chromePrefs.put("profile.password_manager_leak_detection", false);
					// chromePrefs.put("safebrowsing.enabled", "false");
					ChromeOptions options = new ChromeOptions();
					options.setExperimentalOption("prefs", chromePrefs);

					options.addArguments("remote-allow-origins=*");
					options.addArguments("enable-automation");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-extensions");
					//options.addArguments("--disable-gpu");
					options.addArguments("--dns-prefetch-disable");
					options.addArguments("--ignore-gpu-blocklist");
					options.addArguments("--use-gl");

					options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
					options.setAcceptInsecureCerts(true);
					DesiredCapabilities cap = new DesiredCapabilities();
					cap.setCapability("credentials_enable_service", false);
					cap.setCapability("profile.password_manager_enabled", false);
					cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
					cap.setCapability(ChromeOptions.CAPABILITY, options);
					options.merge(cap);

					driver = new ChromeDriver(options);

					driver.manage().window().maximize();

					System.out.println("Setting path to chrome done");
				} catch (Exception e) {
					e.printStackTrace();

					try {
						try {
							Runtime.getRuntime().exec("taskkill /F /IM ChromeDriver.exe");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						System.out.println("kill instance of driver");
						//Time.sleep(5);
						try {
							File f = new File(System.getProperty("user.dir") + "\\Webdriver\\chromedriver.exe");
							if (f != null) {
								f.delete();
								System.out.println("delete driver.exe");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						

						//WebDriverManager.chromedriver().setup();
						ChromeOptions options = new ChromeOptions();
						HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
						chromePrefs.put("profile.default_content_settings.popups", 0);
						chromePrefs.put("safebrowsing.enabled", "false");
						options.setExperimentalOption("prefs", chromePrefs);

						options.addArguments("remote-allow-origins=*");
						/*
						 * options.addArguments("enable-automation");
						 * options.addArguments("--no-sandbox");
						 * options.addArguments("--disable-extensions");
						 * options.addArguments("--dns-prefetch-disable");
						 * options.addArguments("--disable-gpu");
						 */ options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
						options.addArguments("--ignore-gpu-blocklist");
						options.addArguments("--use-gl");

						options.setAcceptInsecureCerts(true);

						DesiredCapabilities cap = new DesiredCapabilities();
						cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
						cap.setCapability(ChromeOptions.CAPABILITY, options);
						options.merge(cap);

						driver = new ChromeDriver(options);

						driver.manage().window().maximize();
						System.out.println("Setting path to chrome done");
					} catch (Exception e1) {
						session = false;
						globalmsg = "Not able to create " + browser + " browser session.Error message- "
								+ e1.getMessage().toString().split("\n")[0];
						e1.printStackTrace();
					}
				}
			}
			// Check if parameter passed as 'Edge'
			else if ((browser.equalsIgnoreCase("Edge") && (platform.equalsIgnoreCase("Desktop")))) {

				// browserName = "";
				browser = "Edge";
				try {
					//WebDriverManager.edgedriver().setup();
					HashMap<String, Object> edgePrefs = new HashMap<String, Object>();
					edgePrefs.put("profile.default_content_settings.popups", 0);
					// edgePrefs.put("download.default_directory", downloadFilepath);
					edgePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
					edgePrefs.put("download.prompt_for_download", false);
					edgePrefs.put("safebrowsing.enabled", "false");
					EdgeOptions options = new EdgeOptions();
					options.addArguments("--ignore-gpu-blocklist");
					options.addArguments("--use-gl");
			        //options.addArguments("--disable-gpu");
			        options.addArguments("--no-sandbox");
			        options.addArguments("--disable-dev-shm-usage");
			        options.addArguments("--remote-debugging-port=9222"); // Set a custom port for the browser session
			        options.addArguments("remote-allow-origins=*");
			        System.setProperty("SE_MSEDGEDRIVER_MIRROR_URL", "https://msedgedriver.microsoft.com");
					edgePrefs.put("download.show_hub_popup_on_download_start", "false");

					driver = new EdgeDriver(options);
					driver.manage().window().maximize();
				} catch (Exception e) {
					e.printStackTrace();

					try {
						try {
							Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe");
							System.out.println("kill instance of driver");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						Thread.sleep(5);
						try {
							File f = new File(browserPath + "\\msedgedriver.exe");
							if (f != null) {
								f.delete();
								System.out.println("delete driver.exe");
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						Thread.sleep(5);
						// wdm.setup();
						EdgeOptions options = new EdgeOptions();

						// options.setCapability("download.default_directory", downloadFilepath);
						// HashMap<String, Object> edgePrefs = new HashMap<String, Object>();
						// edgePrefs.put("profile.default_content_settings.popups", 0);
						// edgePrefs.put("download.default_directory", downloadFilepath);
						// edgePrefs.put("profile.default_content_setting_values.automatic_downloads",
						// 1);
						// edgePrefs.put("safebrowsing.enabled", "false");
						// options.(, value);setExperimentalOption("prefs", edgePrefs);
						HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
						chromePrefs.put("profile.default_content_settings.popups", 0);
						chromePrefs.put("download.default_directory", downloadFilepath);
						chromePrefs.put("profile.default_content_setting_values.automatic_downloads", 1);
						chromePrefs.put("safebrowsing.enabled", "false");
						chromePrefs.put("show_hub_popup_on_download_start", false);
						// ChromeOptions options1 = new ChromeOptions();
						options.setExperimentalOption("prefs", chromePrefs);
						options.addArguments("--remote-allow-origins=*");
						options.addArguments("--ignore-gpu-blocklist");
						options.addArguments("--use-gl");

						driver = new EdgeDriver(options);
						driver.manage().window().maximize();
						System.out.println("Setting path to edge done");
					} catch (Exception e1) {
						session = false;
						globalmsg = "Not able to create " + browser + " browser session.Error message- "
								+ e1.getMessage().toString().split("\n")[0];

						e1.printStackTrace();
					}
				}

				// create Edge instance
				// DesiredCapabilities capabilities = DesiredCapabilities.edge();
				// EdgeOptions options = new EdgeOptions();
				// options.setPageLoadStrategy("eager");

				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
			}

			else {
				// If no browser passed throw exception
				throw new Exception("Browser is not correct");
			}
			System.out.println("driver set");
			System.out.println((driver).getSessionId());
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		} catch (Exception ex) {
			ex.printStackTrace();
			session = false;
			globalmsg = "Not able to create " + browser + " browser session.Error message- "
					+ ex.getMessage().toString().split("\n")[0];
		}
	}

	public static void extentReportappversionSetup() {
		try {
			extent.setSystemInfo("Application Version", projectVersion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void extentReportbrowserversionSetup() {
		try {
			Capabilities cap = driver.getCapabilities();
			String browserversion = cap.getBrowserVersion().toString();
			extent.setSystemInfo("Browser Version", browserversion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void extentReportSetup(String platform, String browser, String version, String projectName,
			String env, String URL) {
		try {
			// Extent Report constant values declaration
			String nameOS = "os.name";
			String versionOS = "os.version";
			// String architectureOS = "os.arch";
			String username = "user.name";
			String hostname = "COMPUTERNAME";
			

			username = System.getProperty(username);
			hostname = System.getenv(hostname);

			// Conditional Code for KIOSK
			String browsername = browser;
			if (platform.equalsIgnoreCase("KIOSK")) {
				browser = "KIOSK";
			}

			// Extent report Configurations
			Date datef = new Date();
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			
			// For Latest folder for Browser Name Only
			extentReportFilePath_BrowserNameOnly =reportPath + "\\" + "/test-output/ExtentReports/Latest/"
					+ browser + ".html";

			// Reverting to browser
			if (platform.equalsIgnoreCase("KIOSK")) {
				browser = browsername;
			}
			String url = "<a href=" + URL + " target='_blank'>" + URL + "</a>";
			htmlReporter = new ExtentSparkReporter(reportPath);
			htmlReporter_latest = new ExtentSparkReporter(extentReportFilePath_BrowserNameOnly);
			System.out.println("Latest Report - " + htmlReporter_latest);
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
			extent.attachReporter(htmlReporter_latest);
			extent.setSystemInfo("URL", url);
			// extent.setSystemInfo("Application Version", );
			extent.setSystemInfo("Host Name", hostname);
			extent.setSystemInfo("Environment", projectName + " Test Environment");
			extent.setSystemInfo("User Name", username);
			extent.setSystemInfo("Platform", platform);

			// extent.setSystemInfo("Browser Version", browserversion);
			extent.setSystemInfo("OS", nameOS);
			extent.setSystemInfo("OS Version", versionOS);
			extent.setSystemInfo("Browser", browser);
			extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
			// extent.setSystemInfo("Devtopia Ref", devtopiaRef);

			// extent.setSystemInfo("OS Architecture", architectureOS);

			// Extent Report config setting

			htmlReporter.config().setDocumentTitle(projectName + " Automation Test Execution Report");
			htmlReporter.config().setReportName(projectName + " Automation Test Execution Report");
			htmlReporter.config().thumbnailForBase64(true);
			htmlReporter.config().setTheme(Theme.STANDARD);
			// Extent Report config setting
			htmlReporter_latest.config().setDocumentTitle(projectName + " Automation Test Execution Report");
			htmlReporter_latest.config().setReportName(projectName + " Automation Test Execution Report");
			htmlReporter_latest.config().thumbnailForBase64(true);
			htmlReporter_latest.config().setTheme(Theme.STANDARD);
			

			try {
				// Company logo and Extent report customization

				byte[] image = IOUtils.toByteArray(
						new FileInputStream(System.getProperty("user.dir") + "\\src\\images\\EsriLogo.png"));
				System.out.println((System.getProperty("user.dir") + "\\images\\EsriLogo.png"));
				String finalImage = Base64.getEncoder().encodeToString(image);

				// htmlReporter_latest.config().setJs("document.getElementsByClassName('logo')[0].style.backgroundImage=\"url('data:image/png;base64,"+finalImage+"')\";");

				htmlReporter_latest.config().setJs(
						"document.getElementsByClassName('logo')[0].style.backgroundImage=\"url('data:image/png;base64,"
								+ finalImage + "')\";\r\n"

								+ "\r\n"

								+ "document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(2) > div:nth-child(3) > div > div.card-header > h6').innerHTML='Steps';\r\n"

								+ "\r\n"

								+ "let ele=document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(2) > div:nth-child(3) > div > div.card-footer > div:nth-child(1) > small');\r\n"

								+ "let txt=ele.innerText.replace('events','steps');\r\n"

								+ "ele.innerHTML=txt;\r\n"

								+ "ele=document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(2) > div:nth-child(3) > div > div.card-footer > div:nth-child(2)>small');\r\n"

								+ "txt=ele.innerText.replace('events','steps');\r\n"

								+ "ele.innerHTML=txt;"

								+ "let date1=document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(1) > div:nth-child(1) > div > div > h3').innerText;\r\n"

								+ "let date2=document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(1) > div:nth-child(2) > div > div > h3').innerText;\r\n"

								+ "console.log(date1);\r\n"

								+ "console.log(date2);\r\n"

								+ "let newdate1 = new Date(date1);\r\n"

								+ "let newdate2 = new Date(date2);\r\n"

								+ "date1 = new Date(newdate1.getFullYear(), newdate1.getMonth() + 1, newdate1.getDate(), newdate1.getHours(), newdate1.getMinutes(), newdate1.getSeconds());\r\n"

								+ "date2 = new Date(newdate2.getFullYear(), newdate2.getMonth() + 1, newdate2.getDate(), newdate2.getHours(), newdate2.getMinutes(), newdate2.getSeconds());\r\n"

								+ "\r\n"

								+ "let diffInMilliseconds = date2 - date1;\r\n"

								+ "let diffInSeconds = diffInMilliseconds / 1000;\r\n"

								+ "console.log(diffInSeconds);\r\n"

								+ "\r\n"

								+ "let totalSeconds = diffInSeconds;\r\n"

								+ "let hours = Math.floor(totalSeconds / 3600);\r\n"

								+ "let minutes = Math.floor((totalSeconds % 3600) / 60);\r\n"

								+ "let seconds = totalSeconds % 60;\r\n"

								+ "let totalDuration=hours + ' hours,' + minutes + ' minutes,' + seconds+ ' seconds';\r\n"

								+ "console.log(totalDuration);\r\n"

								+ "\r\n"

								+ "let txt1=document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(1) > div:nth-child(1)').innerHTML;\r\n"

								+ "let child1=document.createElement('charts');\r\n"

								+ "child1.innerHTML=txt1;\r\n"

								+ "let parent1=document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(3) > div > div > div.card-header');\r\n"

								+ "parent1.appendChild(child1);\r\n"

								+ "\r\n"

								+ "\r\n"

								+ "document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(3) > div > div > div.card-header > charts > div > div > p').innerHTML='Duration';\r\n"

								+ "document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(3) > div > div > div.card-header > charts > div > div >h3').innerHTML=totalDuration;");

				htmlReporter_latest.config().setCss(".header .vheader .nav-logo>a .logo{width: 200%}");

				htmlReporter.config().setJs(
						"document.getElementsByClassName('logo')[0].style.backgroundImage=\"url('data:image/png;base64,"
								+ finalImage + "')\";\r\n"

								+ "\r\n"

								+ "document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(2) > div:nth-child(3) > div > div.card-header > h6').innerHTML='Steps';\r\n"

								+ "\r\n"

								+ "let ele=document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(2) > div:nth-child(3) > div > div.card-footer > div:nth-child(1) > small');\r\n"

								+ "let txt=ele.innerText.replace('events','steps');\r\n"

								+ "ele.innerHTML=txt;\r\n"

								+ "ele=document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(2) > div:nth-child(3) > div > div.card-footer > div:nth-child(2)>small');\r\n"

								+ "txt=ele.innerText.replace('events','steps');\r\n"

								+ "ele.innerHTML=txt;"

								+ "let date1=document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(1) > div:nth-child(1) > div > div > h3').innerText;\r\n"

								+ "let date2=document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(1) > div:nth-child(2) > div > div > h3').innerText;\r\n"

								+ "console.log(date1);\r\n"

								+ "console.log(date2);\r\n"

								+ "let newdate1 = new Date(date1);\r\n"

								+ "let newdate2 = new Date(date2);\r\n"

								+ "date1 = new Date(newdate1.getFullYear(), newdate1.getMonth() + 1, newdate1.getDate(), newdate1.getHours(), newdate1.getMinutes(), newdate1.getSeconds());\r\n"

								+ "date2 = new Date(newdate2.getFullYear(), newdate2.getMonth() + 1, newdate2.getDate(), newdate2.getHours(), newdate2.getMinutes(), newdate2.getSeconds());\r\n"

								+ "\r\n"

								+ "let diffInMilliseconds = date2 - date1;\r\n"

								+ "let diffInSeconds = diffInMilliseconds / 1000;\r\n"

								+ "console.log(diffInSeconds);\r\n"

								+ "\r\n"

								+ "let totalSeconds = diffInSeconds;\r\n"

								+ "let hours = Math.floor(totalSeconds / 3600);\r\n"

								+ "let minutes = Math.floor((totalSeconds % 3600) / 60);\r\n"

								+ "let seconds = totalSeconds % 60;\r\n"

								+ "let totalDuration=hours + ' hours,' + minutes + ' minutes,' + seconds+ ' seconds';\r\n"

								+ "console.log(totalDuration);\r\n"

								+ "\r\n"

								+ "let txt1=document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(1) > div:nth-child(1)').innerHTML;\r\n"

								+ "let child1=document.createElement('charts');\r\n"

								+ "child1.innerHTML=txt1;\r\n"

								+ "let parent1=document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(3) > div > div > div.card-header');\r\n"

								+ "parent1.appendChild(child1);\r\n"

								+ "\r\n"

								+ "\r\n"

								+ "document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(3) > div > div > div.card-header > charts > div > div > p').innerHTML='Duration';\r\n"

								+ "document.querySelector('div.container-fluid.p-4.view.dashboard-view > div:nth-child(3) > div > div > div.card-header > charts > div > div >h3').innerHTML=totalDuration;");

				htmlReporter.config().setCss(".header .vheader .nav-logo>a .logo{width: 200%}");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
			session = false;
			globalmsg = "Not able to setup extent report session.";
		}
	}

	public static void extentReportSetup(String platform, String browser) {
		try {
			// Extent Report constant values declaration
			//String nameOS = "os.name";
		//	String versionOS = "os.version";
			// String architectureOS = "os.arch";
			String username = "user.name";
			String hostname = "COMPUTERNAME";
			
			username = System.getProperty(username);
			hostname = System.getenv(hostname);

			// Conditional Code for KIOSK
			String browsername = browser;
			if (platform.equalsIgnoreCase("KIOSK")) {
				browser = "KIOSK";
			}

			// Extent report Configurations
			Date datef = new Date();
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			// extentReportFilePath= System.getProperty("user.dir") +
			// "/test-output/ExtentReports/Archive/"+projectName+"Report"+ "_" + browser+"_"
			// +dateformat.format(datef)+ ".html";

			// For Latest folder for Browser Name Only
			extentReportFilePath_BrowserNameOnly = reportPath + "\\"  + browser + "_" + dateformat.format(datef)
					+ ".html";
			//extentReportFilePath = System.getProperty("user.dir") + "/Enterprise Installation Checklist Test Results";

			// Reverting to browser
			if (platform.equalsIgnoreCase("KIOSK")) {
				browser = browsername;
			}
			String url = "<a href=" + Portalurl + " target='_blank'>" + Portalurl + "</a>";
			// htmlReporter = new ExtentHtmlReporter(extentReportFilePath);
			htmlReporter_latest = new ExtentSparkReporter(extentReportFilePath_BrowserNameOnly);
			System.out.println("Latest Report - " + htmlReporter_latest);
			extent = new ExtentReports();
			// extent.attachReporter(htmlReporter);
			extent.attachReporter(htmlReporter_latest);
			extent.setSystemInfo("Project Name", projectName);
			extent.setSystemInfo("URL", url);
			// extent.setSystemInfo("Application Version",projectVersion );
			extent.setSystemInfo("Host Name", hostname);
			extent.setSystemInfo("User Name", username);
			extent.setSystemInfo("Platform", platform);
			extent.setSystemInfo("Browser", browser);
			//extent.setSystemInfo("OS", nameOS);
			//extent.setSystemInfo("OS Version", versionOS);
			extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
			// extent.setSystemInfo("OS Architecture", architectureOS);

			// Extent Report config setting
			/*
			 * htmlReporter.config().setDocumentTitle(projectName +
			 * " Automation Test Execution Report");
			 * htmlReporter.config().setReportName(projectName +
			 * " Automation Test Execution Report");
			 * htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			 * htmlReporter.config().setTheme(Theme.STANDARD);
			 */

			// Extent Report config setting
			htmlReporter_latest.config().setDocumentTitle(projectName + " Execution Report");
			htmlReporter_latest.config().setReportName(projectName + " Execution Report");
			htmlReporter_latest.config().setTheme(Theme.STANDARD);
			htmlReporter_latest.config().thumbnailForBase64(true);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session = false;
			globalmsg = "Not able to setup extent report session.";
		}
	}

	
	public void setupzoom() {
		;
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '1.5'");
	}

	/*
	 * public static String getScreenshot(WebDriver driver) { TakesScreenshot
	 * ts=(TakesScreenshot) driver;
	 * 
	 * File src=ts.getScreenshotAs(OutputType.FILE);
	 * 
	 * String
	 * path=System.getProperty("user.dir")+"/Screenshot/"+System.currentTimeMillis()
	 * +".png";
	 * 
	 * File destination=new File(path);
	 * 
	 * try { FileUtils.copyFile(src, destination); } catch (IOException e) {
	 * System.out.println("Capture Failed "+e.getMessage()); } return path; }
	 */

	/*
	 * public static String getScreenshot(WebDriver driver) { String path =
	 * "\\\\esri.com\\psdata\\PSProductsQA\\AutomationTestData\\ExtentReportImages\\Screenshot\\blank.png";
	 * String relativePath = "Screenshot/"; String localPath = ""; String
	 * localPathArchive = ""; try { TakesScreenshot ts = (TakesScreenshot) driver;
	 * File src = ts.getScreenshotAs(OutputType.FILE); String screenshot =
	 * System.currentTimeMillis() + ".png"; path =
	 * "\\\\esri.com\\psdata\\PSProductsQA\\AutomationTestData\\ExtentReportImages\\"
	 * + projectName + "\\Screenshot\\" + screenshot; if (submitdefect) { localPath
	 * = System.getProperty("user.dir") +
	 * "/test-output/ExtentReports/Latest/Screenshot/" + screenshot;
	 * localPathArchive = System.getProperty("user.dir") +
	 * "/test-output/ExtentReports/Archive/Screenshot/" + screenshot; File
	 * localDestinationlatest = new File(localPath); File localDestinationArchive =
	 * new File(localPathArchive); FileUtils.copyFile(src, localDestinationlatest);
	 * FileUtils.copyFile(src, localDestinationArchive); } else { localPath =
	 * System.getProperty("user.dir") +
	 * "/Enterprise Installation Checklist Test Results/Screenshot/" + screenshot;
	 * 
	 * File localDestination = new File(localPath); FileUtils.copyFile(src,
	 * localDestination); }
	 * 
	 * relativePath += screenshot; } catch (Exception e) { e.printStackTrace(); }
	 * return relativePath; }
	 */
	
	public static String getScreenshot(WebDriver driver) throws IOException {
		// Guard against a torn-down/terminated session. When the user stops the run
		// mid-execution the browser is killed and any TakesScreenshot call would
		// throw UnreachableBrowserException / NoSuchSessionException, which then
		// bubbles up into the UI Results Log as a scary stack trace. Swallow those
		// here and return an empty string; callers already handle empty screenshots.
		if (driver == null) {
			return "";
		}
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] orgImage = ts.getScreenshotAs(OutputType.BYTES);
			orgImage = ImageUtil.compressImage(orgImage);
			return Base64.getEncoder().encodeToString(orgImage);
		} catch (Throwable t) {
			// UnreachableBrowserException, NoSuchSessionException, WebDriverException,
			// or any other failure when the session is gone — don't propagate.
			return "";
		}
	}

	public static void copyfile(String source, String dest) {
		InputStream is = null;
		OutputStream os = null;
		try {

			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void waitforHomePagetoload() {
		// Wait for page to load
		((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");

		// wait for the wmx header on homepage to load
		String locator = "//div[@id='header']";

		@SuppressWarnings("unused")
		WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(600)))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		System.out.println("HomePage load complete");
	}

	public static void waitforpagetoload() {
		// Wait for page to load
		try {
			((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			System.out.println("Page load complete");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clickJS(WebElement element) {
		// WebElement element = driver.findElement(By.id("gbqfd"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void resetStepCount() {
		// reset the step count to 1
		stepCount = 1;
	}

	public static String getScreenshotFailReport(WebDriver driver) throws IOException {
		// 1
		String path = "\\\\esri.com\\psdata\\PSProductsQA\\AutomationTestData\\ExtentReportImages\\Screenshot\\blank.png";
		String relativePath = "";
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			// WebDriver augmentedDriver = new Augmenter().augment(driver);
			// File src = (File)((TakesScreenshot)
			// augmentedDriver).getScreenshotAs(OutputType.FILE);
			String screenshot = System.currentTimeMillis() + ".png";
			path = "\\\\esri.com\\psdata\\PSProductsQA\\AutomationTestData\\ExtentReportImages\\A4IoT\\Screenshot\\"
					+ screenshot;
			String localPath = System.getProperty("user.dir") + "/test-output/Screenshot/" + screenshot;
			File destination = new File(path);
			File localDestination = new File(localPath);
			// FileUtils.copyFile(src, destination);
			FileUtils.copyFile(src, localDestination);
			relativePath += screenshot;
		} catch (Exception e) {
			// e.printStackTrace();
			throw e;
		}
		return relativePath;
	}

public void skipTest(String message) {
		
		try {
			if (extent != null && !extentNodeCreated
					&& testcaseName != null && !testcaseName.isEmpty()) {
				logger = extent.createTest(testcaseName);
				childnode = null;
				extentNodeCreated = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			CommonFunction.logStatus("SKIP", message);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try {
			System.out.println("[SKIP] " + message);
		} catch (Exception ignored) {
		}
		throw new SkipException(message);
	}

	/*protected static void readconfig() {

		try {
			adminURL[0] = prop.getProperty("Serverurl1");
			URL = prop.getProperty("Portalurl");
			UserName = prop.getProperty("PortalUserName");
			Password = prop.getProperty("PortalPassword");
			adminUsername[0] = prop.getProperty("ServerAdminUsername1");
			adminPassword[0] = prop.getProperty("ServerAdminPassword1");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}*/

}

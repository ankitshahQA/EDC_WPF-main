package com.automation.testcases;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;
import com.automation.pages.GroupsPage;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.pages.MapPage;
import com.automation.pages.OrganizationPage;

public class Organization_TestScenario extends TestBase {

	CommonFunction cfunction = new CommonFunction();
	LoginPage lp = new LoginPage();
	HomePage hp = new HomePage();
	MapPage mp = new MapPage();
	GroupsPage gp = new GroupsPage();
	OrganizationPage op = new OrganizationPage();
	String msg = "";
	boolean find = true;
	
	public void organization_functionality	() throws Exception {
		cfunction.refreshpage();
		cfunction.waitforpagetoload();
		msg="Verify the organization Functionality";
		childnode = logger.createNode(msg);
		
		if (projectVersion.equalsIgnoreCase("10.6.1")) {
			organization_functionality_10_6_1();
		}

		else if (projectVersion.equalsIgnoreCase("10.7.1") || projectVersion.equalsIgnoreCase("10.8.0")
				|| projectVersion.equalsIgnoreCase("10.8.1") || projectVersion.equalsIgnoreCase("10.9.0")
				|| projectVersion.equalsIgnoreCase("10.9.1") || projectVersion.equalsIgnoreCase("11.2.0")
				|| projectVersion.equalsIgnoreCase("11.0.0") || projectVersion.equalsIgnoreCase("11.1.0")
				|| projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
				|| (projectVersion.equalsIgnoreCase("11.5.0")) || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))) {

			msg = "navigating to organization";
			try {
				hp.navigateToOrganizationPage();
				op.verifyorganizationpage();
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg);

			}
			msg = "Verify members tab";
			childnode = logger.createNode(msg);

			op.verifymembertab();

			msg = "Verify Status tab";
			childnode = logger.createNode(msg);

			op.verifystatus();

			cfunction.sync(2);
			driver.switchTo().defaultContent();
		}

		else if (projectVersion.equalsIgnoreCase("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0"))|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
			msg = "navigating to organization";
			if ((projectVersion.equalsIgnoreCase("12.0.0"))) {
				try {
					cfunction.sync(1);
					hp.navigateToOrganizationPage();
					cfunction.switchToWidgetTemplateFrame();
					op.verifyorganizationpage();
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e) {
					e.printStackTrace();
					CommonFunction.logStatus("FAIL", msg);

				}
			} else if ((projectVersion.equalsIgnoreCase("12.1.0"))|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				try {
					
					hp.navigateToOrganizationPage();
					cfunction.sync(5);
					cfunction.switchtoqueryframe1();
					op.verifyorganizationpage();
					CommonFunction.logStatus("PASS", msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}

			msg = "Verify members tab";
			childnode = logger.createNode(msg);
			op.verifymembertab_12_0_0();

			msg = "Verify Status tab";
			childnode = logger.createNode(msg);
			op.verifystatus();

			cfunction.sync(2);
			driver.switchTo().defaultContent();
			if (projectVersion.equalsIgnoreCase("12.1.0") || (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				cfunction.switchtoqueryframe1();
				hp.click_on_content_home_12_0_0();

			} else if (projectVersion.equalsIgnoreCase("12.0.0")) {
				cfunction.switchToWidgetTemplateFrame();
				hp.click_on_content_home_12_0_0();
			}
		}
	}

	public void organization_functionality_10_6_1() throws Exception {

		// Step message
		msg = "Navigate to organization";
		childnode = logger.createNode(msg);

		hp.navigateToOrganizationPage_10_6_1();
		op.verifyorganizationpage_10_6_1();
		msg = "Verify members tab";
		childnode = logger.createNode(msg);

		op.verifymembertab_10_6_1();

		msg = "Verify Status tab";
		childnode = logger.createNode(msg);

		op.verifystatus_10_6_1();

		cfunction.sync(2);
		driver.switchTo().defaultContent();

	}
	/*
	 * public void groups_functionality () throws InterruptedException {
	 * 
	 * //Step message
	 * 
	 * 
	 * msg = "navigating to organization"; try { hp.navigateToOrganizationPage();
	 * op.verifyorganizationpage();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); CommonFunction.logStatus("FAIL",
	 * msg);
	 * 
	 * }
	 * 
	 * cfunction.sync(2); msg = "Create Member 1"; try {
	 * cfunction.waitforpagetoload(); CommonFunction.logStatus("PASS", msg);
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); CommonFunction.logStatus("FAIL",
	 * msg);
	 * 
	 * } cfunction.sync(2); msg = "Verify BaseMap rendering";
	 * 
	 * try { mp.verifyBaseMapRendering(); CommonFunction.logStatus("PASS", msg);
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); CommonFunction.logStatus("FAIL",
	 * msg);
	 * 
	 * } cfunction.sync(2); msg = "Verify Search for layers added";
	 * 
	 * try { mp.SearchForLayers(); CommonFunction.logStatus("PASS", msg);
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); CommonFunction.logStatus("FAIL",
	 * msg);
	 * 
	 * } cfunction.sync(2); msg = "Verify Browse by living Atlas functionality";
	 * 
	 * try { mp.SearchForLivingAtlas(); CommonFunction.logStatus("PASS", msg);
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); CommonFunction.logStatus("FAIL",
	 * msg);
	 * 
	 * } cfunction.sync(2); msg = "Verify Legends and table on content panel";
	 * 
	 * try { mp.VerifyLegendTableDisplays(); CommonFunction.logStatus("PASS", msg);
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); CommonFunction.logStatus("FAIL",
	 * msg);
	 * 
	 * } cfunction.sync(2); msg = "Save Map";
	 * 
	 * try { mp.SaveMap(); CommonFunction.logStatus("PASS", msg);
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); CommonFunction.logStatus("FAIL",
	 * msg);
	 * 
	 * }
	 * 
	 * }
	 */
}

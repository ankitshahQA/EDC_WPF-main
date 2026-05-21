package com.automation.testcases;

import com.automation.library.CommonFunction;
import com.automation.library.TestBase;
import com.automation.pages.ContentPage;
import com.automation.pages.GroupsPage;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.pages.MapPage;
import com.automation.pages.OrganizationPage;

public class Groups_TestScenario extends TestBase {

	CommonFunction cfunction = new CommonFunction();
	LoginPage lp = new LoginPage();
	HomePage hp = new HomePage();
	MapPage mp = new MapPage();
	GroupsPage gp = new GroupsPage();
	OrganizationPage op = new OrganizationPage();
	ContentPage cp = new ContentPage();
	Map_Functionality_TestScenario maptest = new Map_Functionality_TestScenario();
	String msg = "";
	boolean find = true;

	public void groups_functionality() throws Exception {

		// Step message

		msg = "Navigate to organization";
		childnode = logger.createNode(msg);

		hp.navigateToOrganizationPage();
		op.verifyorganizationpage();

		/*
		 * msg = "Verify BaseMap Image";
		 * 
		 * try{ //mp.verifyBaseMapImage(); CommonFunction.logStatus("PASS", msg);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); CommonFunction.logStatus("FAIL",
		 * msg+e);
		 * 
		 * }
		 */
		cfunction.sync(2);
		msg = "Create Member 1";
		childnode = logger.createNode(msg);

		op.createmember1();
		cfunction.waitforpagetoload();

		cfunction.sync(2);
		msg = "Create Member 2";
		childnode = logger.createNode(msg);

		op.createmember2();

		cfunction.sync(3);

		op.verifymembers();

		cfunction.sync(2);
		msg = "Create Group";
		childnode = logger.createNode(msg);

		hp.navigateToGroupsPage();
		cfunction.waitforpagetoload();

		gp.verifygrouppage();
		cfunction.waitforpagetoload();

		gp.creategroup();
		cfunction.waitforpagetoload();
		cfunction.sync(3);
		msg = "Invite members";
		childnode = logger.createNode(msg);
		if (groupcreated) {
			gp.invitemembers();
			cfunction.waitforpagetoload();
		} else {
			msg = "Not able to Invite members as Group is not created";
			CommonFunction.logStatus("FAIL", msg);
		}
		cfunction.sync(5);
		
		msg="Verify Content for Sharing";
		childnode = logger.createNode(msg);
		if(cp.createMapForSharing()) {
			flagmap=true;
			maptest.map_functionality();
		}
		
		
		msg = "Share Content";
		childnode = logger.createNode(msg);
		if (groupcreated) {
			hp.navigateToContentPage();
			cp.verifycontentpage();

			
			gp.sharecontents();

			cfunction.sync(5);

			hp.navigateToGroupsPage();
			gp.verifygrouppage();

			cfunction.sync(3);

			gp.clickgroupcreated();

			gp.verifygroupcreated();

			cfunction.sync(3);

			gp.verifycontentsavailable();

			cfunction.sync(5);

		} else {
			msg = "Not able to share contents as Group is not created";
			CommonFunction.logStatus("FAIL", msg);
		}
		cfunction.sync(5);
		msg = "Delete Group";
		childnode = logger.createNode(msg);
		if (groupcreated) {
			hp.navigateToGroupsPage();
			gp.verifygrouppage();

			gp.deletegroup();

			cfunction.sync(2);

			gp.verifygroupdeleted();
		} else {
			msg = "Not able to Delete Group as Group is not created";
			CommonFunction.logStatus("FAIL", msg);
		}
		msg = "Delete Members";
		childnode = logger.createNode(msg);

		hp.navigateToOrganizationPage();
		op.verifyorganizationpage();

		cfunction.sync(2);

		op.deletemember();
		msg = "Navigate to content page";
		childnode = logger.createNode(msg);

		hp.navigateToContentPage();
		/*
		 * cfunction.sync(2); msg = "verify members deleted"; try {
		 * op.verifymembersdeleted(); } catch (Exception e) { e.printStackTrace();
		 * CommonFunction.logStatus("FAIL", msg+e);
		 * 
		 * }
		 */
	}

	public void groups_functionality_10_6_1() throws Exception {

		// Step message

		msg = "Navigate to organization";
		childnode = logger.createNode(msg);

		hp.navigateToOrganizationPage_10_6_1();
		op.verifyorganizationpage_10_6_1();

		/*
		 * msg = "Verify BaseMap Image";
		 * 
		 * try{ //mp.verifyBaseMapImage(); CommonFunction.logStatus("PASS", msg);
		 * 
		 * } catch (Exception e) { e.printStackTrace(); CommonFunction.logStatus("FAIL",
		 * msg+e);
		 * 
		 * }
		 */
		cfunction.sync(2);
		msg = "Create Member 1";
		childnode = logger.createNode(msg);

		op.createmember1_10_6_1();
		cfunction.waitforpagetoload();

		cfunction.sync(2);
		msg = "Create Member 2";
		childnode = logger.createNode(msg);

		op.createmember2_10_6_1();

		cfunction.sync(3);

		op.verifymembers_10_6_1();

		cfunction.sync(2);
		msg = "Create Group";
		childnode = logger.createNode(msg);

		hp.navigateToGroupsPage_10_6_1();
		cfunction.waitforpagetoload();

		gp.verifygrouppage();
		cfunction.waitforpagetoload();

		gp.creategroup();
		cfunction.waitforpagetoload();
		cfunction.sync(3);
		msg = "Invite members";
		childnode = logger.createNode(msg);
		if (groupcreated) {
			gp.invitemembers();
			cfunction.waitforpagetoload();
		} else {
			msg = "Not able to Invite Members as Group is not created";
			CommonFunction.logStatus("FAIL", msg);
		}
		cfunction.sync(5);
		
		msg="Verify Content for Sharing";
		childnode = logger.createNode(msg);
		if(cp.createMapForSharing()) {
			flagmap=true;
			maptest.map_functionality();
		}
		
		
		msg = "Share Content";
		childnode = logger.createNode(msg);
		if (groupcreated) {
			hp.navigateToContentPage_10_6_1();
			cp.verifycontentpage_10_6_1();


			gp.sharecontents();

			cfunction.sync(5);

			hp.navigateToGroupsPage_10_6_1();
			gp.verifygrouppage();

			cfunction.sync(3);

			gp.clickgroupcreated();

			gp.verifygroupcreated_10_6_1();

			cfunction.sync(3);

			gp.verifycontentsavailable();

			cfunction.sync(5);
		} else {
			msg = "Not able to Share Contents as Group is not created";
			CommonFunction.logStatus("FAIL", msg);
		}
		hp.navigateToGroupsPage_10_6_1();
		gp.verifygrouppage();

		cfunction.sync(5);
		msg = "Delete Group";
		childnode = logger.createNode(msg);
		if (groupcreated) {
			gp.deletegroup();

			cfunction.sync(2);

			gp.verifygroupdeleted();
		} else {
			msg = "Not able to Delete Group as Group is not created";
			CommonFunction.logStatus("FAIL", msg);
		}
		msg = "Delete Members";
		childnode = logger.createNode(msg);

		hp.navigateToOrganizationPage_10_6_1();
		op.verifyorganizationpage_10_6_1();

		cfunction.sync(2);

		op.deletemember_10_6_1();
		msg = "Navigate to content page";
		childnode = logger.createNode(msg);

		hp.navigateToContentPage_10_6_1();

		/*
		 * cfunction.sync(2); msg = "verify members deleted"; try {
		 * op.verifymembersdeleted(); } catch (Exception e) { e.printStackTrace();
		 * CommonFunction.logStatus("FAIL", msg+e);
		 * 
		 * }
		 */
	}

	public void groups_functionality_10_8_0() throws Exception {

		cfunction.refreshpage();
		cfunction.waitforpagetoload();
		
		
		
		if (projectVersion.equalsIgnoreCase("10.6.1")) {
			groups_functionality_10_6_1();
		} else if (projectVersion.equalsIgnoreCase("10.7.1")) {
			groups_functionality();
		}

		if (projectVersion.equalsIgnoreCase("10.8.0") || projectVersion.equalsIgnoreCase("10.8.1")
				|| projectVersion.equalsIgnoreCase("10.9.0") || projectVersion.equalsIgnoreCase("10.9.1")
				|| projectVersion.equalsIgnoreCase("11.2.0") || projectVersion.equalsIgnoreCase("11.0.0")
				|| projectVersion.equalsIgnoreCase("11.1.0") || projectVersion.equalsIgnoreCase("11.3.0")
				|| (projectVersion.equalsIgnoreCase("11.4.0")) || (projectVersion.equalsIgnoreCase("11.5.0"))
				|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0")) || (projectVersion.equalsIgnoreCase("12.0.0"))
				|| (projectVersion.equalsIgnoreCase("12.1.0")) || (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")) ) {

			msg = "Navigate to organization";
			childnode = logger.createNode(msg);
			if (projectVersion.equalsIgnoreCase("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0"))) {
				try {
					cfunction.sync(1);
					hp.navigateToOrganizationPage();
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				hp.navigateToOrganizationPage();
				//op.verifyorganizationpage();
			}

			try {
				cfunction.sync(5);
				if (projectVersion.equalsIgnoreCase("12.0.0"))
					cfunction.switchToWidgetTemplateFrame();
				else if (projectVersion.equalsIgnoreCase("12.1.0") || projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
					cfunction.switchtoqueryframe1();
			} catch (Exception e) {
				cfunction.sync(5);
				if (projectVersion.equalsIgnoreCase("12.0.0"))
					cfunction.switchToWidgetTemplateFrame();
				else if (projectVersion.equalsIgnoreCase("12.1.0"))
					cfunction.switchtoqueryframe1();
			}

			cfunction.sync(2);
			msg = "Create Member 1";
			childnode = logger.createNode(msg);
			if (projectVersion.equalsIgnoreCase("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				op.createmember1_Version12();
				CommonFunction.waitforpagetoload();
			} else {
				op.createmember1();
			}

			cfunction.sync(2);
			msg = "Create Member 2";
			childnode = logger.createNode(msg);
			if (projectVersion.equalsIgnoreCase("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0")) || (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				op.createmember2_Version12_0();
			} else {
				op.createmember2();
			}

			msg = "Verify the Created Members";
			childnode = logger.createNode(msg);
			if (projectVersion.equalsIgnoreCase("12.0.0") || (projectVersion.equalsIgnoreCase("12.1.0") 
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")))) {
				op.verifCreatedymembers12_0();
			} else {
				op.verifymembers();

			}

			cfunction.sync(2);
			msg = "Create group";
			childnode = logger.createNode(msg);
			hp.navigateToGroupsPage();
			gp.verifygrouppage();
			if (projectVersion.equalsIgnoreCase("11.2.0") || projectVersion.equalsIgnoreCase("11.3.0")
					|| (projectVersion.equalsIgnoreCase("11.4.0")) || (projectVersion.equalsIgnoreCase("11.5.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0")) || (projectVersion.equalsIgnoreCase("12.0.0"))
					|| (projectVersion.equalsIgnoreCase("12.1.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))
					|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0")))
				gp.creategroup_11_2();
			else
				gp.creategroup();
			cfunction.sync(5);
			msg = "Invite members";
			childnode = logger.createNode(msg);
			if (groupcreated) {
				if (projectVersion.equalsIgnoreCase("11.2.0") || projectVersion.equalsIgnoreCase("11.3.0")
						|| projectVersion.equalsIgnoreCase("11.4.0") || (projectVersion.equalsIgnoreCase("11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0")) ||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
						|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0")))
					gp.invitemembers11_2_0();
				else
					gp.invitemembers_10_8_0();
				cfunction.waitforpagetoload();

				cfunction.sync(5);
			} else {
				msg = "Not able to Invite Members as Group is not created";
				CommonFunction.logStatus("FAIL", msg);
			}
			
			msg="Verify Content for Sharing";
			childnode = logger.createNode(msg);
			if(cp.createMapForSharing()) {
				flagmap=true;
				maptest.map_functionality();
			}

				msg = "Share Content";
				childnode = logger.createNode(msg);
				if (groupcreated) {

					if (projectVersion.equalsIgnoreCase("10.8.0")) {
						gp.sharecontents();
					} else if (projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
							|| (projectVersion.equalsIgnoreCase("11.5.0"))
							|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
							|| (projectVersion.equalsIgnoreCase("12.0.0"))||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
							|| (projectVersion.equalsIgnoreCase("12.1.0"))) {						
						gp.sharecontents_11_3_0();
					} else {
						gp.sharecontents_10_8_1();
					}
				
				hp.navigateToGroupsPage();
				gp.verifygrouppage();

				cfunction.sync(3);

				if (projectVersion.equalsIgnoreCase("11.2.0") || projectVersion.equalsIgnoreCase("11.3.0")
						|| (projectVersion.equalsIgnoreCase("11.4.0")) || (projectVersion.equalsIgnoreCase("11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
						|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0")))
					gp.clickgroupcreated11_2_0();
				else
					gp.clickgroupcreated();

				cfunction.sync(3);

				gp.verifygroupcreated();

			} else {
				msg = "Not able to Share contents as Group is not created";
				CommonFunction.logStatus("FAIL", msg);
			}
			cfunction.sync(5);
			msg = "Delete Group";
			childnode = logger.createNode(msg);
			if (groupcreated) {
				hp.navigateToGroupsPage();
				gp.verifygrouppage();

				cfunction.sync(5);

				gp.deletegroup();

				cfunction.sync(2);

				if (projectVersion.equalsIgnoreCase("11.2.0") || (projectVersion.equalsIgnoreCase("11.3.0"))
						|| (projectVersion.equalsIgnoreCase("11.4.0")) || (projectVersion.equalsIgnoreCase("11.5.0"))
						|| (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
						|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0")))
					gp.verifygroupdeleted11_2_0();
				else
					gp.verifygroupdeleted();
			} else {
				msg = "Not able to Delete Group as Group is not created";
				CommonFunction.logStatus("FAIL", msg);
			}
			msg = "Delete members";
			childnode = logger.createNode(msg);
			try {			
				hp.navigateToOrganizationPage();
				CommonFunction.logStatus("PASS", msg);
			} catch (Exception e) {
				e.printStackTrace();
				CommonFunction.logStatus("FAIL", msg);
			}

			cfunction.sync(2);
			if (projectVersion.equalsIgnoreCase("11.1.0")||projectVersion.equalsIgnoreCase("11.3.0") || (projectVersion.equalsIgnoreCase("11.4.0"))
					|| (projectVersion.equalsIgnoreCase("11.5.0")) || (projectVersion.equalsIgnoreCase("Kubernetes11.5.0"))
					|| (projectVersion.equalsIgnoreCase("12.0.0")) || (projectVersion.equalsIgnoreCase("12.1.0"))
					||(projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))) {
				try {
					if (projectVersion.equalsIgnoreCase("12.0.0"))
						cfunction.switchToWidgetTemplateFrame();
					else if (projectVersion.equalsIgnoreCase("12.1.0")|| (projectVersion.equalsIgnoreCase("Kubernetes12.1.0"))
							)
						cfunction.switchtoqueryframe1();
			
				} catch (Exception e) {
					e.printStackTrace();
					CommonFunction.logStatus("FAIL", msg);
				}

				
				op.deletemember11_3();
			} else {
				op.deletemember();
			}

			msg = "Navigate to content page";
			childnode = logger.createNode(msg);

			hp.navigateToContentPage();

		}
	}

}

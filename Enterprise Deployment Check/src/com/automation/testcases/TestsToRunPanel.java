package com.automation.testcases;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.*;

/**
 * Left-side "Tests to Run" tab. Three sections:
 *   1. "Run All Tests" master checkbox
 *   2. Server tests   (Login Admin, Login Manager, Validate Data Stores)
 *   3. Portal tests   (Login, Feature/Tile/Scene layer, Map, Builders, Group, Org)
 *
 * Dependency rules:
 *   - "Run All" selects + locks every individual checkbox
 *   - Experience Builder and WebApp Builder are mutually exclusive
 *   - Selecting any portal sub-test forces "Login to Portal" on
 *   - Deselecting "Login to Portal" cascades to the sub-tests
 *   - Kubernetes mode disables "Validate Data Stores"
 *
 * EDCMainFrame copies the checkbox states into the static flag booleans
 * (flagloginportal, flagfeaturelayer, etc.) before launching TestNG.
 */
public class TestsToRunPanel extends JPanel {

	private static final Color BRAND_BLUE     = new Color(  0, 120, 212);
	private static final Color TEXT_PRIMARY   = new Color( 36,  36,  36);
	private static final Color TEXT_SECONDARY = new Color( 96,  96,  96);
	private static final Color BG_PAGE        = new Color(243, 244, 246);
	private static final Color BG_CARD        = Color.WHITE;
	private static final Color BG_GROUP       = new Color(239, 239, 239);
	private static final Color BORDER_COLOR   = new Color(218, 218, 218);

	private static final Font FONT_LABEL   = new Font("Segoe UI", Font.PLAIN, 15);
	private static final Font FONT_HEADING = new Font("Segoe UI", Font.BOLD,  16);//portal,sever
	private static final Font FONT_HINT    = new Font("Segoe UI", Font.PLAIN, 14);

	private JCheckBox runAllCheckbox;

	// Server tests
	private boolean   serverExpanded = true;
	private JCheckBox serverLoginAdmin;
	private JCheckBox serverLoginManager;
	private JCheckBox serverValidateDS;

	// Portal tests
	private boolean   portalExpanded = true;
	private JCheckBox portalLogin;
	private JCheckBox portalFeatureLayer;
	private JCheckBox portalTileLayer;
	private JCheckBox portalSceneLayer;
	private JCheckBox portalWebMap;
	private JCheckBox portalExpBuilder;
	private JCheckBox portalWebAppBuilder;
	private JCheckBox portalGroupMember;
	private JCheckBox portalOrgFunc;
	private JCheckBox portalValidateServerRole;
	private JCheckBox portalCreateDashboardTest;
	private JCheckBox portalStoryMap;

	// All individual test checkboxes (excludes "Run All") — used for bulk ops
	private final List<JCheckBox> allTests = new ArrayList<>();

	private boolean kubernetesMode = false;

	// Set true while a listener is changing other checkboxes, to avoid recursive cascades
	private boolean updatingDeps = false;

	public TestsToRunPanel() {
		setLayout(new BorderLayout());
		setBackground(BG_PAGE);

		JPanel content = new JPanel(new GridBagLayout());
		content.setBackground(BG_PAGE);
		content.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill    = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.gridx   = 0;
		gbc.insets  = new Insets(0, 0, 12, 0);

		gbc.gridy = 0; content.add(createRunAllSection(),       gbc);
		gbc.gridy = 1; content.add(createServerTestsSection(),  gbc);
		gbc.gridy = 2; gbc.insets = new Insets(0, 0, 0, 0);
		content.add(createPortalTestsSection(), gbc);

		// Push everything to the top
		gbc.gridy = 3; gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH;
		content.add(new JPanel() {{ setOpaque(false); }}, gbc);

		JScrollPane sp = new JScrollPane(content);
		sp.setBorder(null);
		sp.setBackground(BG_PAGE);
		sp.getViewport().setBackground(BG_PAGE);
		sp.getVerticalScrollBar().setUnitIncrement(16);
		add(sp, BorderLayout.CENTER);

		// Wire dependencies after every checkbox exists
		wireDependencyListeners();
	}

	// ── Sections ────────────────────────────────────────────────────────────

	private JPanel createRunAllSection() {
		JPanel card = createCard();
		card.setLayout(new BorderLayout());
		card.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(BORDER_COLOR, 1),
			BorderFactory.createEmptyBorder(14, 18, 14, 18)));

		JPanel inner = new JPanel();
		inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
		inner.setBackground(BG_CARD);

		runAllCheckbox = new JCheckBox("Run All Tests");
		runAllCheckbox.setFont(FONT_HEADING);
		runAllCheckbox.setForeground(TEXT_PRIMARY);
		runAllCheckbox.setBackground(BG_CARD);
		runAllCheckbox.setFocusPainted(false);
		runAllCheckbox.setSelected(true);

		JLabel hint = new JLabel("15 tests available");
		hint.setFont(FONT_HINT);
		hint.setForeground(TEXT_SECONDARY);
		hint.setBorder(BorderFactory.createEmptyBorder(0, 24, 0, 0));

		inner.add(runAllCheckbox);
		inner.add(hint);
		card.add(inner, BorderLayout.CENTER);
		return card;
	}

	private JPanel createServerTestsSection() {
		JPanel card   = createCollapsibleCard();
		card.setLayout(new BorderLayout());
		JPanel header = createSectionHeader("Server Tests", serverExpanded);

		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		body.setBackground(BG_CARD);
		body.setBorder(BorderFactory.createEmptyBorder(8, 18, 14, 18));

		JPanel group = new JPanel();
		group.setLayout(new BoxLayout(group, BoxLayout.Y_AXIS));
		group.setBackground(BG_GROUP);
		group.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(BORDER_COLOR, 1),
			BorderFactory.createEmptyBorder(10, 14, 10, 14)));

		serverLoginAdmin   = createTestCheckbox("Login to Server Admin");
		serverLoginManager = createTestCheckbox("Login to Server Manager");
		serverValidateDS   = createTestCheckbox("Validate Data Stores");

		group.add(serverLoginAdmin);   group.add(Box.createVerticalStrut(4));
		group.add(serverLoginManager); group.add(Box.createVerticalStrut(4));
		group.add(serverValidateDS);

		body.add(group);

		header.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				serverExpanded = !serverExpanded;
				body.setVisible(serverExpanded);
				updateArrow(header, serverExpanded);
				revalidateAll();
			}
		});

		card.add(header, BorderLayout.NORTH);
		card.add(body,   BorderLayout.CENTER);
		return card;
	}

	private JPanel createPortalTestsSection() {
		JPanel card   = createCollapsibleCard();
		card.setLayout(new BorderLayout());
		JPanel header = createSectionHeader("Portal Tests", portalExpanded);

		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		body.setBackground(BG_CARD);
		body.setBorder(BorderFactory.createEmptyBorder(8, 18, 14, 18));

		JPanel group = new JPanel();
		group.setLayout(new BoxLayout(group, BoxLayout.Y_AXIS));
		group.setBackground(BG_GROUP);
		group.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(BORDER_COLOR, 1),
			BorderFactory.createEmptyBorder(10, 14, 10, 14)));

		portalLogin         = createTestCheckbox("Login to Portal");
		portalFeatureLayer  = createTestCheckbox("Create Feature Layer");
		portalTileLayer     = createTestCheckbox("Create Tile Layer");
		portalSceneLayer    = createTestCheckbox("Check SceneLayer functionality (11.1 and above)");
		portalWebMap        = createTestCheckbox("Create Web Map");
		portalExpBuilder    = createTestCheckbox("Create Experience Builder App(12.0 and above versions)");
		portalWebAppBuilder = createTestCheckbox("Create Webapp Builder App (11.5 and below versions)");
		portalGroupMember   = createTestCheckbox("Create Group/Member and share content");
		portalOrgFunc              = createTestCheckbox("Check Organization functionality");
		portalValidateServerRole   = createTestCheckbox("Validate Server Role(11.3 and above versions)");
		portalCreateDashboardTest   = createTestCheckbox("Create Dashboard (11.1 and above versions)");
		portalStoryMap			  = createTestCheckbox("Create Story Map (11.1 and above versions)");
		
		group.add(portalLogin);                group.add(Box.createVerticalStrut(4));
		group.add(portalFeatureLayer);         group.add(Box.createVerticalStrut(4));
		group.add(portalTileLayer);            group.add(Box.createVerticalStrut(4));
		group.add(portalSceneLayer);           group.add(Box.createVerticalStrut(4));
		group.add(portalWebMap);               group.add(Box.createVerticalStrut(4));
		group.add(portalExpBuilder);           group.add(Box.createVerticalStrut(4));
		group.add(portalWebAppBuilder);        group.add(Box.createVerticalStrut(4));
		group.add(portalGroupMember);          group.add(Box.createVerticalStrut(4));
		group.add(portalOrgFunc);              group.add(Box.createVerticalStrut(4));
		group.add(portalValidateServerRole);	group.add(Box.createVerticalStrut(4));
		group.add(portalCreateDashboardTest);	group.add(Box.createVerticalStrut(4));
		group.add(portalStoryMap);	group.add(Box.createVerticalStrut(4));

		body.add(group);

		header.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				portalExpanded = !portalExpanded;
				body.setVisible(portalExpanded);
				updateArrow(header, portalExpanded);
				revalidateAll();
			}
		});

		card.add(header, BorderLayout.NORTH);
		card.add(body,   BorderLayout.CENTER);
		return card;
	}

	// ── Dependency rules ────────────────────────────────────────────────────

	private void wireDependencyListeners() {
		// Run All — toggles every individual checkbox
		runAllCheckbox.addItemListener(e -> {
			if (updatingDeps) return;
			updatingDeps = true;
			if (runAllCheckbox.isSelected()) {
				applyRunAll();
			} else {
				for (JCheckBox cb : allTests) { cb.setSelected(false); cb.setEnabled(true); }
				applyKubernetesRestrictions();
			}
			updatingDeps = false;
		});

		// Experience Builder ↔ WebApp Builder are mutually exclusive
		portalExpBuilder.addItemListener(e -> {
			if (updatingDeps) return;
			updatingDeps = true;
			if (portalExpBuilder.isSelected() && !runAllCheckbox.isSelected()) {
				portalWebAppBuilder.setSelected(false);
				portalWebAppBuilder.setEnabled(false);
				forceLoginPortal();
			} else if (!portalExpBuilder.isSelected()) {
				if (!runAllCheckbox.isSelected()) portalWebAppBuilder.setEnabled(true);
				refreshLoginPortalState();
			}
			updatingDeps = false;
		});

		portalWebAppBuilder.addItemListener(e -> {
			if (updatingDeps) return;
			updatingDeps = true;
			if (portalWebAppBuilder.isSelected() && !runAllCheckbox.isSelected()) {
				portalExpBuilder.setSelected(false);
				portalExpBuilder.setEnabled(false);
				forceLoginPortal();
			} else if (!portalWebAppBuilder.isSelected()) {
				if (!runAllCheckbox.isSelected())
					portalExpBuilder.setEnabled(true);
				refreshLoginPortalState();
			}
			updatingDeps = false;
		});

		// Any portal sub-test selected → force "Login to Portal" on
		JCheckBox[] portalSubs = {
			portalFeatureLayer, portalTileLayer, portalSceneLayer, portalWebMap,
			portalGroupMember, portalOrgFunc, portalValidateServerRole, portalCreateDashboardTest, portalStoryMap,
		};
		for (JCheckBox cb : portalSubs) {
			cb.addItemListener(e -> {
				if (updatingDeps) return;
				updatingDeps = true;
				if (cb.isSelected()) 
					forceLoginPortal();
				else                 
					refreshLoginPortalState();
				updatingDeps = false;
			});
		}

		// Deselecting "Login to Portal" cascades — clears every portal sub-test
		portalLogin.addItemListener(e -> {
			if (updatingDeps) return;
			updatingDeps = true;
			if (!portalLogin.isSelected()) {
				portalFeatureLayer.setSelected(false);
				portalTileLayer.setSelected(false);
				portalSceneLayer.setSelected(false);
				portalWebMap.setSelected(false);
				portalExpBuilder.setSelected(false);
				portalWebAppBuilder.setSelected(false);
				portalGroupMember.setSelected(false);
				portalOrgFunc.setSelected(false);
				portalValidateServerRole.setSelected(false);
				portalCreateDashboardTest.setSelected(false);
				portalStoryMap.setSelected(false);
				portalFeatureLayer.setEnabled(true);
				portalTileLayer.setEnabled(true);
				portalSceneLayer.setEnabled(true);
				portalWebMap.setEnabled(true);
				portalExpBuilder.setEnabled(true);
				portalWebAppBuilder.setEnabled(true);
				portalGroupMember.setEnabled(true);
				portalOrgFunc.setEnabled(true);
				portalValidateServerRole.setEnabled(true);
				portalCreateDashboardTest.setEnabled(true);
				portalStoryMap.setEnabled(true);
				applyKubernetesRestrictions();
			}
			syncRunAll();
			updatingDeps = false;
		});
	}

	private void applyRunAll() {
		for (JCheckBox cb : allTests) {
			cb.setSelected(true);
			cb.setEnabled(false);
		}
		// Kubernetes restrictions still apply
		if (kubernetesMode) {
			serverValidateDS.setSelected(false);
			serverValidateDS.setEnabled(false);
			portalValidateServerRole.setSelected(false);
			portalValidateServerRole.setEnabled(false);
		}
		portalExpBuilder.setEnabled(false);
		portalWebAppBuilder.setEnabled(false);
	}

	private void forceLoginPortal() {
		portalLogin.setSelected(true);
		portalLogin.setEnabled(false);
	}

	// Re-evaluate whether "Login to Portal" should remain locked-on
	private void refreshLoginPortalState() {
		if (runAllCheckbox.isSelected()) return;
		boolean anySubSelected =
			portalFeatureLayer.isSelected() || portalTileLayer.isSelected() ||
			portalSceneLayer.isSelected()   || portalWebMap.isSelected()    ||
			portalExpBuilder.isSelected()   || portalWebAppBuilder.isSelected() ||
			portalGroupMember.isSelected()  || portalOrgFunc.isSelected()   ||
			portalValidateServerRole.isSelected()|| portalCreateDashboardTest.isSelected() || portalStoryMap.isSelected();
		if (anySubSelected) {
			portalLogin.setSelected(true);
			portalLogin.setEnabled(false);
		} else {
			portalLogin.setEnabled(true);
		}
		syncRunAll();
	}

	// EDCMainFrame calls this whenever the K8s checkbox in InputParametersPanel changes
	public void setKubernetesMode(boolean k8s) {
		this.kubernetesMode = k8s;
		applyKubernetesRestrictions();
	}

	private void applyKubernetesRestrictions() {
		if (kubernetesMode) {
			serverValidateDS.setSelected(false);
			serverValidateDS.setEnabled(false);
			portalValidateServerRole.setSelected(false);
			portalValidateServerRole.setEnabled(false);
		} else {
			if (runAllCheckbox.isSelected()) {
				// Run All is on — re-select but keep disabled (Run All locks everything)
				serverValidateDS.setSelected(true);
				serverValidateDS.setEnabled(false);
				portalValidateServerRole.setSelected(true);
				portalValidateServerRole.setEnabled(false);
			} else {
				// Run All is off — re-enable so user can toggle freely
				serverValidateDS.setEnabled(true);
				portalValidateServerRole.setEnabled(true);
			}
		}
	}

	// ── Config load / save ──────────────────────────────────────────────────

	public void loadConfig(Properties props) {
		updatingDeps = true;   // suppress dependency cascades during bulk update

		boolean runAll = isYes(props, "RunAllTest");

		serverLoginAdmin.setSelected  (runAll || isYes(props, "LoginServer"));
		serverLoginManager.setSelected(runAll || isYes(props, "LoginManager"));
		serverValidateDS.setSelected  (runAll ? !kubernetesMode : isYes(props, "ValidateDataStores"));

		portalLogin.setSelected         (runAll || isYes(props, "LoginPortal"));
		portalFeatureLayer.setSelected  (runAll || isYes(props, "CreateFeatureLayer"));
		portalTileLayer.setSelected     (runAll || isYes(props, "CreateTileLayer"));
		portalSceneLayer.setSelected    (runAll || isYes(props, "CreateSceneLayer"));
		portalWebMap.setSelected        (runAll || isYes(props, "CreateMap"));
		portalExpBuilder.setSelected    (runAll || isYes(props, "CreateExperienceBuilderApp"));
		portalWebAppBuilder.setSelected (runAll || isYes(props, "CreateWebAppBuilder"));
		portalGroupMember.setSelected   (runAll || isYes(props, "CreateMember"));
		portalOrgFunc.setSelected       (runAll || isYes(props, "ValidateOrganization"));
		portalCreateDashboardTest.setSelected(runAll || isYes(props, "CreateDashboard"));
		portalStoryMap.setSelected(runAll || isYes(props, "CreateStoryMap"));
		portalValidateServerRole.setSelected(runAll ? !kubernetesMode : isYes(props, "ValidateServerRole"));

		runAllCheckbox.setSelected(runAll);

		updatingDeps = false;

		// Apply the dependency rules now that everything is set
		if (runAll) {
			applyRunAll();
		} else {
			refreshLoginPortalState();
			applyKubernetesRestrictions();
			// Enforce mutually-exclusive builders
			if (portalExpBuilder.isSelected()) {
				portalWebAppBuilder.setSelected(false);
				portalWebAppBuilder.setEnabled(false);
			} else if (portalWebAppBuilder.isSelected()) {
				portalExpBuilder.setSelected(false);
				portalExpBuilder.setEnabled(false);
			}
		}
		syncRunAll();
	}

	public void saveConfig(Properties props) {
		props.setProperty("RunAllTest",                 runAllCheckbox.isSelected()      ? "Yes" : "No");
		props.setProperty("LoginServer",                serverLoginAdmin.isSelected()    ? "Yes" : "No");
		props.setProperty("LoginManager",               serverLoginManager.isSelected()  ? "Yes" : "No");
		props.setProperty("ValidateDataStores",         serverValidateDS.isSelected()    ? "Yes" : "No");
		props.setProperty("LoginPortal",                portalLogin.isSelected()         ? "Yes" : "No");
		props.setProperty("CreateFeatureLayer",         portalFeatureLayer.isSelected()  ? "Yes" : "No");
		props.setProperty("CreateTileLayer",            portalTileLayer.isSelected()     ? "Yes" : "No");
		props.setProperty("CreateSceneLayer",           portalSceneLayer.isSelected()    ? "Yes" : "No");
		props.setProperty("CreateMap",                  portalWebMap.isSelected()        ? "Yes" : "No");
		props.setProperty("CreateExperienceBuilderApp", portalExpBuilder.isSelected()    ? "Yes" : "No");
		props.setProperty("CreateWebAppBuilder",        portalWebAppBuilder.isSelected() ? "Yes" : "No");
		props.setProperty("CreateMember",               portalGroupMember.isSelected()   ? "Yes" : "No");
		props.setProperty("ValidateOrganization",       portalOrgFunc.isSelected()       ? "Yes" : "No");
		props.setProperty("ValidateServerRole",          portalValidateServerRole.isSelected() ? "Yes" : "No");
		props.setProperty("CreateStoryMap",            portalStoryMap.isSelected() ? "Yes" : "No");
		props.setProperty("CreateDashboard",            portalCreateDashboardTest.isSelected() ? "Yes" : "No");}

	private static boolean isYes(Properties p, String key) {
		String v = p.getProperty(key, "No").trim();
		return v.equalsIgnoreCase("Yes") || v.equalsIgnoreCase("Y");
	}

	// ── Helpers ─────────────────────────────────────────────────────────────

	private JCheckBox createTestCheckbox(String text) {
		JCheckBox cb = new JCheckBox(text);
		cb.setFont(FONT_LABEL);
		cb.setForeground(TEXT_PRIMARY);
		cb.setBackground(BG_GROUP);
		cb.setFocusPainted(false);
		cb.setSelected(true);
		allTests.add(cb);
		return cb;
	}

	// Auto-check "Run All" when every individual test happens to be selected
	private void syncRunAll() {
		if (updatingDeps) return;
		boolean all = true;
		for (JCheckBox cb : allTests) { if (!cb.isSelected()) { all = false; break; } }
		updatingDeps = true;
		runAllCheckbox.setSelected(all);
		updatingDeps = false;
	}

	private JPanel createCard() {
		JPanel card = new JPanel();
		card.setBackground(BG_CARD);
		card.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
		card.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		card.setAlignmentX(Component.LEFT_ALIGNMENT);
		return card;
	}

	private JPanel createCollapsibleCard() {
		JPanel card = new JPanel(new BorderLayout()) {
			@Override
			public Dimension getMaximumSize() {
				return new Dimension(super.getMaximumSize().width, getPreferredSize().height);
			}
		};
		card.setBackground(BG_CARD);
		card.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 1));
		card.setAlignmentX(Component.LEFT_ALIGNMENT);
		return card;
	}

	private JPanel createSectionHeader(String title, boolean expanded) {
		JPanel hdr = new JPanel(new BorderLayout());
		hdr.setBackground(BG_CARD);
		hdr.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
		hdr.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JLabel t = new JLabel(title);
		t.setFont(FONT_HEADING);
		t.setForeground(TEXT_PRIMARY);

		JLabel arrow = new JLabel();
		arrow.setIcon(EDCMainFrame.arrow(20, TEXT_SECONDARY, expanded));
		arrow.setName("arrow");

		hdr.add(t,     BorderLayout.WEST);
		hdr.add(arrow, BorderLayout.EAST);
		return hdr;
	}

	private void updateArrow(JPanel hdr, boolean expanded) {
		for (Component c : hdr.getComponents()) {
			if (c instanceof JLabel && "arrow".equals(c.getName())) {
				((JLabel) c).setIcon(EDCMainFrame.arrow(20, TEXT_SECONDARY, expanded));
			}
		}
	}

	private void revalidateAll() {
		revalidate(); repaint();
		Container p = getParent();
		while (p != null) {
			p.revalidate();
			p.repaint(); 
			p = p.getParent();
			}
	}

	// Names of all currently-selected tests (used by EDCMainFrame.validateInputs)
	public List<String> getSelectedTests() {
		List<String> selected = new ArrayList<>();
		for (JCheckBox cb : allTests) {
			if (cb.isSelected()) 
				selected.add(cb.getText());
		}
		return selected;
	}

	public int getSelectedTestCount() {
		return getSelectedTests().size();
	}

	/**
	 * Enable or disable all test checkboxes (including Run All).
	 * Called by EDCMainFrame when tests start/stop.
	 */
	public void setTestsEnabled(boolean enabled) {
		runAllCheckbox.setEnabled(enabled);
		for (JCheckBox cb : allTests) {
			cb.setEnabled(enabled);
		}
		// Re-apply dependency rules when re-enabling
		if (enabled) {
			if (runAllCheckbox.isSelected()) {
				applyRunAll();
			} else {
				refreshLoginPortalState();
				applyKubernetesRestrictions();
				// Enforce mutually-exclusive builders
				if (portalExpBuilder.isSelected()) {
					portalWebAppBuilder.setEnabled(false);
				} else if (portalWebAppBuilder.isSelected()) {
					portalExpBuilder.setEnabled(false);
				}
			}
		}
	}
}

package com.automation.testcases;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.*;

/**
 * Left-side "Input Parameters" tab. Lets the user enter:
 *   - Portal URL, admin credentials, browser choice, Kubernetes flag
 *   - Up to 10 server blocks (URL, role, credentials, federated/standalone)
 *   - A few advanced toggles (verbose log, skip SSL, auto reports, timeout)
 *
 * loadConfig(props) / saveConfig(props) move values between this panel and
 * the Properties object. EDCMainFrame writes that object to config.properties.
 *
 * Fires a "kubernetes" PropertyChangeEvent whenever the user toggles the
 * Kubernetes checkbox so TestsToRunPanel can disable K8s-incompatible tests.
 */
public class InputParametersPanel extends JPanel {

	// Portal section
	private JTextField     portalUrlField;
	private JTextField     adminUsernameField;
	private JPasswordField adminPasswordField;
	private JCheckBox      kubernetesCheckbox;
	private JRadioButton   chromeRadio;
	private JRadioButton   edgeRadio;

	// Configuration (servers)
	private JPanel                    serversContainer;
	private final ArrayList<ServerBlock> serverBlocks = new ArrayList<>();
	private JButton                   addServerBtn;

	// Section expand/collapse state
	private boolean portalExpanded   = true;
	private boolean configExpanded   = true;
	private boolean advancedExpanded = false;

	// Advanced settings (currently UI-only — not persisted)
	private ToggleSwitch verboseLoggingToggle;
	private ToggleSwitch skipSSLToggle;
	private ToggleSwitch autoReportsToggle;
	private JSpinner     timeoutSpinner;

	// Colors / fonts shared by the section builders
	private static final Color BRAND_BLUE     = new Color(  0, 120, 212);
	private static final Color TEXT_PRIMARY   = new Color( 36,  36,  36);
	private static final Color TEXT_SECONDARY = new Color( 96,  96,  96);
	private static final Color BG_PAGE        = new Color(243, 244, 246);
	private static final Color BG_CARD        = Color.WHITE;
	private static final Color BG_GROUP       = new Color(239, 239, 239);
	private static final Color BORDER_COLOR   = new Color(218, 218, 218);
	private static final Color LABEL_COLOR    = new Color( 51,  51,  51);
	private static final Color DELETE_RED     = new Color(200,  50,  50);

	private static final Font FONT_LABEL   = new Font("Segoe UI", Font.PLAIN, 15);
	private static final Font FONT_INPUT   = new Font("Segoe UI", Font.PLAIN, 15);
	private static final Font FONT_SECTION = new Font("Segoe UI", Font.BOLD,  16);

	// Placeholder strings
	private static final String PH_PORTAL_URL  = "https://portal.example.com/arcgis";
	private static final String PH_ADMIN_USER  = "admin";
	private static final String PH_ADMIN_PASS  = "\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022";
	private static final String PH_SERVER_URL  = "https://server.example.com/arcgis";
	private static final String PH_SERVER_USER = "siteadmin";
	private static final String PH_SERVER_PASS = "\u2022\u2022\u2022\u2022\u2022\u2022\u2022\u2022";

	public InputParametersPanel() {
		setLayout(new BorderLayout());
		setBackground(BG_PAGE);

		// Three collapsible cards stacked top-to-bottom
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		content.setBackground(BG_PAGE);
		content.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

		content.add(createPortalSection());
		content.add(Box.createVerticalStrut(12));
		content.add(createConfigurationSection());
		content.add(Box.createVerticalStrut(12));
		content.add(createAdvancedSettingsSection());
		content.add(Box.createVerticalGlue());

		JScrollPane sp = new JScrollPane(content);
		sp.setBorder(null);
		sp.setBackground(BG_PAGE);
		sp.getViewport().setBackground(BG_PAGE);
		sp.getVerticalScrollBar().setUnitIncrement(16);
		add(sp, BorderLayout.CENTER);

		// Always start with one empty server block visible
		addServerBlock();
	}

	// ── Portal section ──────────────────────────────────────────────────────
	private JPanel createPortalSection() {
		JPanel card   = createCollapsibleCard();
		JPanel header = createSectionHeader("Portal", portalExpanded);

		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		body.setBackground(BG_CARD);

		// URL + credentials + Kubernetes
		JPanel portalGroup = createGroupPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill    = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.anchor  = GridBagConstraints.WEST;

		gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 10, 0);
		portalUrlField = new JTextField("");
		setPlaceholder(portalUrlField, PH_PORTAL_URL);
		portalGroup.add(createLabeledField("Portal URL *", portalUrlField), gbc);

		gbc.gridy = 1; gbc.gridwidth = 1; gbc.insets = new Insets(0, 0, 10, 8);
		adminUsernameField = new JTextField("");
		setPlaceholder(adminUsernameField, PH_ADMIN_USER);
		portalGroup.add(createLabeledField("Admin Username *", adminUsernameField), gbc);

		gbc.gridx = 1; gbc.insets = new Insets(0, 8, 10, 0);
		adminPasswordField = new JPasswordField("");
		adminPasswordField.setEchoChar('\u2022');
		setPlaceholder(adminPasswordField, PH_ADMIN_PASS);
		portalGroup.add(createPasswordField("Admin Password *", adminPasswordField), gbc);

		gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
		gbc.insets = new Insets(0, 0, 0, 0);
		kubernetesCheckbox = new JCheckBox("ArcGIS Enterprise for Kubernetes");
		kubernetesCheckbox.setFont(FONT_LABEL);
		kubernetesCheckbox.setForeground(TEXT_PRIMARY);
		kubernetesCheckbox.setBackground(BG_GROUP);
		kubernetesCheckbox.setFocusPainted(false);
		// Notify listeners (TestsToRunPanel) when K8s toggles
		kubernetesCheckbox.addItemListener(e ->
			firePropertyChange("kubernetes", !kubernetesCheckbox.isSelected(), kubernetesCheckbox.isSelected()));
		portalGroup.add(kubernetesCheckbox, gbc);

		body.add(wrapInCard(portalGroup, 14, 18, 8, 18));

		// Browser radio buttons
		JPanel browserGroup = createGroupPanel();
		GridBagConstraints bgbc = new GridBagConstraints();
		bgbc.anchor = GridBagConstraints.WEST;
		bgbc.gridx  = 0; bgbc.gridy = 0;

		JLabel browserLbl = new JLabel("Browser");
		browserLbl.setFont(FONT_LABEL);
		browserLbl.setForeground(LABEL_COLOR);
		bgbc.insets = new Insets(0, 0, 0, 16);
		browserGroup.add(browserLbl, bgbc);

		chromeRadio = new JRadioButton("Chrome", true);
		edgeRadio   = new JRadioButton("Edge");
		ButtonGroup bgrp = new ButtonGroup();
		bgrp.add(chromeRadio); bgrp.add(edgeRadio);
		styleRadio(chromeRadio); styleRadio(edgeRadio);
		chromeRadio.setBackground(BG_GROUP);
		edgeRadio.setBackground(BG_GROUP);

		JPanel rr = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 0));
		rr.setBackground(BG_GROUP);
		rr.add(chromeRadio); rr.add(edgeRadio);

		bgbc.gridx = 1;
		bgbc.weightx = 1.0;
		bgbc.insets = new Insets(0, 0, 0, 0);
		bgbc.fill = GridBagConstraints.HORIZONTAL;
		browserGroup.add(rr, bgbc);

		body.add(wrapInCard(browserGroup, 8, 18, 14, 18));

		header.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				portalExpanded = !portalExpanded;
				body.setVisible(portalExpanded);
				updateArrow(header, portalExpanded);
				revalidateUpToScroll();
			}
		});

		card.add(header, BorderLayout.NORTH);
		card.add(body,   BorderLayout.CENTER);
		return card;
	}

	// ── Configuration section (server blocks + Add button) ──────────────────
	private JPanel createConfigurationSection() {
		JPanel card   = createCollapsibleCard();
		JPanel header = createSectionHeader("Configuration", configExpanded);

		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		body.setBackground(BG_CARD);

		JPanel configGroup = new JPanel();
		configGroup.setLayout(new BoxLayout(configGroup, BoxLayout.Y_AXIS));
		configGroup.setBackground(BG_GROUP);
		configGroup.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(BORDER_COLOR, 1),
			BorderFactory.createEmptyBorder(12, 16, 4, 16)));
		configGroup.setAlignmentX(Component.LEFT_ALIGNMENT);

		serversContainer = new JPanel();
		serversContainer.setLayout(new BoxLayout(serversContainer, BoxLayout.Y_AXIS));
		serversContainer.setBackground(BG_GROUP);
		serversContainer.setAlignmentX(Component.LEFT_ALIGNMENT);
		configGroup.add(serversContainer);

		// Centered "+ Add Server" button
		JPanel addBtnBox = new JPanel(new GridBagLayout());
		addBtnBox.setBackground(BG_CARD);
		addBtnBox.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(BORDER_COLOR, 1),
			BorderFactory.createEmptyBorder(4, 20, 4, 20)));
		addBtnBox.setMaximumSize(new Dimension(180, 34));
		addBtnBox.setPreferredSize(new Dimension(180, 34));

		addServerBtn = new JButton("+  Add Server");
		addServerBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		addServerBtn.setForeground(Color.BLACK);
		addServerBtn.setBorderPainted(false);
		addServerBtn.setContentAreaFilled(false);
		addServerBtn.setFocusPainted(false);
		addServerBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addServerBtn.addActionListener(e -> addServerBlock());
		addBtnBox.add(addServerBtn);

		JPanel addRow = new JPanel();
		addRow.setLayout(new BoxLayout(addRow, BoxLayout.X_AXIS));
		addRow.setBackground(BG_GROUP);
		addRow.setAlignmentX(Component.LEFT_ALIGNMENT);
		addRow.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COLOR),
			BorderFactory.createEmptyBorder(10, 0, 6, 0)));
		addRow.add(Box.createHorizontalGlue());
		addRow.add(addBtnBox);
		addRow.add(Box.createHorizontalGlue());

		configGroup.add(addRow);
		body.add(wrapInCard(configGroup, 14, 18, 14, 18));

		header.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				configExpanded = !configExpanded;
				body.setVisible(configExpanded);
				updateArrow(header, configExpanded);
				revalidateUpToScroll();
			}
		});

		card.add(header, BorderLayout.NORTH);
		card.add(body,   BorderLayout.CENTER);
		return card;
	}

	// ── Advanced settings section (collapsed by default) ────────────────────
	private JPanel createAdvancedSettingsSection() {
		JPanel card   = createCollapsibleCard();
		JPanel header = createSectionHeader("Advanced Settings", advancedExpanded);

		JPanel body = new JPanel();
		body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
		body.setBackground(BG_CARD);

		JPanel settingsGroup = new JPanel(new GridBagLayout());
		settingsGroup.setBackground(BG_GROUP);
		settingsGroup.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(BORDER_COLOR, 1),
			BorderFactory.createEmptyBorder(8, 16, 12, 16)));
		settingsGroup.setAlignmentX(Component.LEFT_ALIGNMENT);

		GridBagConstraints gl = new GridBagConstraints();
		gl.anchor = GridBagConstraints.WEST; gl.weightx = 1.0;

		GridBagConstraints gr = new GridBagConstraints();
		gr.anchor = GridBagConstraints.EAST; gr.weightx = 0;

		gl.gridx = 0; gl.gridy = 0; gl.insets = new Insets(0, 0, 8, 0);
		settingsGroup.add(createSettingLabel("Enable verbose logging"), gl);
		gr.gridx = 1; gr.gridy = 0; gr.insets = new Insets(0, 0, 8, 0);
		verboseLoggingToggle = new ToggleSwitch(false);
		settingsGroup.add(verboseLoggingToggle, gr);

		gl.gridy = 1;
		settingsGroup.add(createSettingLabel("Skip SSL certificate validation"), gl);
		gr.gridy = 1;
		skipSSLToggle = new ToggleSwitch(false);
		settingsGroup.add(skipSSLToggle, gr);

		gl.gridy = 2;
		settingsGroup.add(createSettingLabel("Auto-generate reports"), gl);
		gr.gridy = 2;
		autoReportsToggle = new ToggleSwitch(true);
		settingsGroup.add(autoReportsToggle, gr);

		gl.gridy = 3; gl.gridwidth = 2; gl.insets = new Insets(4, 0, 4, 0);
		settingsGroup.add(createSettingLabel("Request Timeout (seconds)"), gl);

		GridBagConstraints spinGbc = new GridBagConstraints();
		spinGbc.gridx = 0; spinGbc.gridy = 4; spinGbc.gridwidth = 2;
		spinGbc.fill = GridBagConstraints.HORIZONTAL;
		spinGbc.weightx = 1.0;
		timeoutSpinner = new JSpinner(new SpinnerNumberModel(30, 1, 600, 5));
		timeoutSpinner.setFont(FONT_INPUT);
		timeoutSpinner.setPreferredSize(new Dimension(0, 32));
		JComponent editor = timeoutSpinner.getEditor();
		if (editor instanceof JSpinner.DefaultEditor) {
			JTextField tf = ((JSpinner.DefaultEditor) editor).getTextField();
			tf.setBackground(Color.WHITE);
			tf.setFont(FONT_INPUT);
		}
		settingsGroup.add(timeoutSpinner, spinGbc);

		body.add(wrapInCard(settingsGroup, 10, 18, 14, 18));
		body.setVisible(advancedExpanded);

		header.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				advancedExpanded = !advancedExpanded;
				body.setVisible(advancedExpanded);
				updateArrow(header, advancedExpanded);
				revalidateUpToScroll();
			}
		});

		card.add(header, BorderLayout.NORTH);
		card.add(body,   BorderLayout.CENTER);
		return card;
	}

	// Simple custom ON/OFF slider used in the Advanced section
	private static class ToggleSwitch extends JToggleButton {
		private static final Color ON_COLOR  = new Color(  0, 123, 255);
		private static final Color OFF_COLOR = new Color(189, 189, 189);
		private static final int W = 32, H = 16, KNOB = 12;

		ToggleSwitch(boolean on) {
			super("", on);
			setPreferredSize(new Dimension(W, H));
			setMinimumSize  (new Dimension(W, H));
			setMaximumSize  (new Dimension(W, H));
			setFocusPainted(false);
			setBorderPainted(false);
			setContentAreaFilled(false);
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(isSelected() ? ON_COLOR : OFF_COLOR);
			g2.fillRoundRect(0, 0, W, H, H, H);
			g2.setColor(Color.WHITE);
			int pad = (H - KNOB) / 2;
			int kx  = isSelected() ? W - KNOB - pad : pad;
			g2.fillOval(kx, pad, KNOB, KNOB);
			g2.dispose();
		}
	}

	// ── Server-block management ─────────────────────────────────────────────

	private void addServerBlock() {
		if (serverBlocks.size() >= 10) {
			JOptionPane.showMessageDialog(this, "Maximum of 10 servers allowed.",
				"Limit Reached", JOptionPane.WARNING_MESSAGE);
			return;
		}
		// Find first unused index — keeps numbering compact after deletes
		int newIdx = 1;
		while (true) {
			boolean used = false;
			for (ServerBlock sb : serverBlocks) { if (sb.index == newIdx) { used = true; break; } }
			if (!used) break;
			newIdx++;
		}
		ServerBlock sb = new ServerBlock(newIdx);
		serverBlocks.add(sb);
		serversContainer.add(sb.panel);
		if (serverBlocks.size() >= 10) addServerBtn.setEnabled(false);
		refreshServerHeaders();
		revalidate(); repaint();
	}

	private void removeServerBlock(ServerBlock sb) {
		sb.serverUrlField.setText("");
		sb.serverAdminUsername.setText("");
		sb.serverAdminPassword.setText("");
		sb.serverRoleCombo.setSelectedIndex(0);

		serverBlocks.remove(sb);
		serversContainer.remove(sb.panel);
		if (serverBlocks.size() < 10) 
			addServerBtn.setEnabled(true);
		refreshServerHeaders();
		serversContainer.revalidate();
		serversContainer.repaint();
		revalidate(); repaint();
	}

	// Re-number "Server N" headers and hide the first block's Remove button
	private void refreshServerHeaders() {
		for (int i = 0; i < serverBlocks.size(); i++) {
			ServerBlock s = serverBlocks.get(i);
			s.label.setText("SERVER " + (i + 1));
			s.removeBtn.setVisible(i > 0);
			if (i == 0) {
				s.panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
			} else {
				s.panel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COLOR),
					BorderFactory.createEmptyBorder(10, 0, 10, 0)));
			}
		}
	}

	// One server entry (URL, role, credentials, federation)
	private class ServerBlock {
		int               index;
		JPanel            panel;
		JLabel            label;
		JButton           removeBtn;
		JTextField        serverUrlField;
		JComboBox<String> serverRoleCombo;
		JTextField        serverAdminUsername;
		JPasswordField    serverAdminPassword;
		JRadioButton      federatedRadio;
		JRadioButton      nonFederatedRadio;

		ServerBlock(int idx) {
			this.index = idx;
			panel = new JPanel(new GridBagLayout());
			panel.setBackground(BG_GROUP);
			panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
			panel.setAlignmentX(Component.LEFT_ALIGNMENT);

			GridBagConstraints g = new GridBagConstraints();
			g.fill    = GridBagConstraints.HORIZONTAL;
			g.weightx = 1.0;
			g.anchor  = GridBagConstraints.WEST;

			// Header row: "Server N" + Remove button
			g.gridx = 0; g.gridy = 0; g.gridwidth = 2; g.insets = new Insets(0, 0, 8, 0);
			JPanel hRow = new JPanel(new BorderLayout());
			hRow.setBackground(BG_GROUP);
			label = new JLabel("SERVER " + idx);
			label.setFont(new Font("Segoe UI", Font.BOLD, 13));
			label.setForeground(new Color(140, 140, 140));
			hRow.add(label, BorderLayout.WEST);

			removeBtn = new JButton("Remove");
			removeBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
			removeBtn.setForeground(DELETE_RED);
			removeBtn.setPreferredSize(new Dimension(80, 28));
			removeBtn.setBorder(BorderFactory.createLineBorder(DELETE_RED, 1));
			removeBtn.setContentAreaFilled(false);
			removeBtn.setFocusPainted(false);
			removeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			removeBtn.setToolTipText("Remove this server");
			removeBtn.setVisible(false);
			removeBtn.addActionListener(e -> removeServerBlock(ServerBlock.this));
			hRow.add(removeBtn, BorderLayout.EAST);
			panel.add(hRow, g);

			g.gridy = 1; g.gridwidth = 2; g.insets = new Insets(0, 0, 8, 0);
			serverUrlField = new JTextField("");
			setPlaceholder(serverUrlField, PH_SERVER_URL);
			panel.add(createLabeledField("Server URL", serverUrlField), g);

			g.gridy = 2;
			serverRoleCombo = new JComboBox<>(new String[]{"Hosting Server", "Raster Analysis Server", "Notebook Server",
					"Workflow Manager Server" });
			styleCombo(serverRoleCombo);
			panel.add(createLabeledCombo("Server Role", serverRoleCombo), g);

			// Username / Password side-by-side
			g.gridy = 3; g.gridwidth = 1; g.insets = new Insets(0, 0, 8, 8);
			serverAdminUsername = new JTextField("");
			setPlaceholder(serverAdminUsername, PH_SERVER_USER);
			panel.add(createLabeledField("Admin Username", serverAdminUsername), g);

			g.gridx = 1; g.insets = new Insets(0, 8, 8, 0);
			serverAdminPassword = new JPasswordField("");
			serverAdminPassword.setEchoChar('\u2022');
			setPlaceholder(serverAdminPassword, PH_SERVER_PASS);
			panel.add(createPasswordField("Admin Password", serverAdminPassword), g);

			// Federated / Non-Federated (non-federated currently disabled — feature not supported)
			g.gridx = 0; g.gridy = 4; g.gridwidth = 2; g.insets = new Insets(0, 0, 0, 0);
			federatedRadio    = new JRadioButton("Federated", true);
			nonFederatedRadio = new JRadioButton("Non-Federated");
			ButtonGroup grp = new ButtonGroup();
			grp.add(federatedRadio); grp.add(nonFederatedRadio);
			styleRadio(federatedRadio); styleRadio(nonFederatedRadio);
			federatedRadio.setBackground(BG_GROUP);
			nonFederatedRadio.setBackground(BG_GROUP);
			nonFederatedRadio.setEnabled(false);

			JPanel radRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 16, 0));
			radRow.setBackground(BG_GROUP);
			radRow.add(federatedRadio); radRow.add(nonFederatedRadio);
			panel.add(radRow, g);
		}
	}

	// ── Layout helpers ──────────────────────────────────────────────────────

	// Card that shrinks to its preferred height when collapsed (for BoxLayout)
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

	private JPanel createGroupPanel() {
		JPanel p = new JPanel(new GridBagLayout());
		p.setBackground(BG_GROUP);
		p.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(BORDER_COLOR, 1),
			BorderFactory.createEmptyBorder(14, 16, 14, 16)));
		p.setAlignmentX(Component.LEFT_ALIGNMENT);
		return p;
	}

	private JPanel wrapInCard(JPanel inner, int top, int left, int bottom, int right) {
		JPanel w = new JPanel(new BorderLayout());
		w.setBackground(BG_CARD);
		w.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
		w.add(inner, BorderLayout.CENTER);
		w.setAlignmentX(Component.LEFT_ALIGNMENT);
		return w;
	}

	// Walk up to the JScrollPane and revalidate so collapsed sections free their space
	private void revalidateUpToScroll() {
		revalidate(); repaint();
		Container parent = getParent();
		while (parent != null) {
			parent.revalidate(); parent.repaint();
			parent = parent.getParent();
		}
	}

	private JPanel createSectionHeader(String title, boolean expanded) {
		JPanel hdr = new JPanel(new BorderLayout());
		hdr.setBackground(BG_CARD);
		hdr.setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
		hdr.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		JLabel t = new JLabel(title);
		t.setFont(FONT_SECTION);
		t.setForeground(TEXT_PRIMARY);

		JLabel arrow = new JLabel();
		arrow.setIcon(EDCMainFrame.arrow(20, TEXT_SECONDARY, expanded));
		arrow.setName("arrow");

		hdr.add(t, BorderLayout.WEST);
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

	private JLabel createSettingLabel(String text) {
		JLabel l = new JLabel(text);
		l.setFont(FONT_LABEL);
		l.setForeground(TEXT_PRIMARY);
		return l;
	}

	private JPanel createLabeledField(String labelText, JTextField field) {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(BG_GROUP);
		JLabel l = new JLabel(labelText);
		l.setFont(FONT_LABEL);
		l.setForeground(LABEL_COLOR);
		l.setAlignmentX(Component.LEFT_ALIGNMENT);
		p.add(l);
		p.add(Box.createVerticalStrut(4));
		styleTextField(field);
		field.setAlignmentX(Component.LEFT_ALIGNMENT);
		p.add(field);
		return p;
	}

	private JPanel createLabeledCombo(String labelText, JComboBox<String> combo) {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(BG_GROUP);
		JLabel l = new JLabel(labelText);
		l.setFont(FONT_LABEL);
		l.setForeground(LABEL_COLOR);
		l.setAlignmentX(Component.LEFT_ALIGNMENT);
		p.add(l);
		p.add(Box.createVerticalStrut(4));
		combo.setAlignmentX(Component.LEFT_ALIGNMENT);
		p.add(combo);
		return p;
	}

	// Password field with show/hide eye-icon toggle
	private JPanel createPasswordField(String labelText, JPasswordField field) {
		JPanel p = new JPanel();
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setBackground(BG_GROUP);
		JLabel l = new JLabel(labelText);
		l.setFont(FONT_LABEL);
		l.setForeground(LABEL_COLOR);
		l.setAlignmentX(Component.LEFT_ALIGNMENT);
		p.add(l);
		p.add(Box.createVerticalStrut(4));

		JPanel row = new JPanel(new BorderLayout());
		row.setBackground(BG_GROUP);
		row.setAlignmentX(Component.LEFT_ALIGNMENT);
		styleTextField(field);
		row.add(field, BorderLayout.CENTER);

		JButton eye = new JButton();
		eye.setIcon(EDCMainFrame.eye(16, TEXT_SECONDARY, false));
		eye.setPreferredSize(new Dimension(32, 32));
		eye.setBorderPainted(false);
		eye.setContentAreaFilled(false);
		eye.setFocusPainted(false);
		eye.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eye.setToolTipText("Show/Hide password");
		eye.addActionListener(e -> {
			if (field.getEchoChar() == '\u0000') {
				field.setEchoChar('\u2022');
				eye.setIcon(EDCMainFrame.eye(16, TEXT_SECONDARY, false));
			} else {
				field.setEchoChar('\u0000');
				eye.setIcon(EDCMainFrame.eye(16, TEXT_SECONDARY, true));
			}
		});
		row.add(eye, BorderLayout.EAST);
		p.add(row);
		return p;
	}

	private void styleTextField(JTextField field) {
		field.setFont(FONT_INPUT);
		field.setPreferredSize(new Dimension(0, 32));
		field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
		field.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(BORDER_COLOR, 1),
			BorderFactory.createEmptyBorder(4, 8, 4, 8)));
		field.setBackground(Color.WHITE);
	}

	private void styleCombo(JComboBox<String> combo) {
		combo.setFont(FONT_INPUT);
		combo.setPreferredSize(new Dimension(0, 32));
		combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
		combo.setBackground(Color.WHITE);
	}

	private void styleRadio(JRadioButton radio) {
		radio.setFont(FONT_LABEL);
		radio.setForeground(TEXT_PRIMARY);
		radio.setFocusPainted(false);
	}

	// ── Public getters used by EDCMainFrame ─────────────────────────────────

	public String  getPortalUrl()       { return getFieldValue(portalUrlField, PH_PORTAL_URL).trim(); }
	public String  getAdminUsername()   { return getFieldValue(adminUsernameField, PH_ADMIN_USER).trim(); }
	public String  getAdminPassword()   { return getFieldValue(adminPasswordField, PH_ADMIN_PASS); }
	public boolean isKubernetes()       { return kubernetesCheckbox.isSelected(); }
	public String  getSelectedBrowser() { return chromeRadio.isSelected() ? "Chrome" : "Edge"; }
	public int     getServerCount()     { return serverBlocks.size(); }

	// ── Config persistence ──────────────────────────────────────────────────

	public void loadConfig(Properties props) {
		loadFieldValue(portalUrlField, props.getProperty("PortalUrl", ""), PH_PORTAL_URL);
		loadFieldValue(adminUsernameField, props.getProperty("PortalUserName", ""), PH_ADMIN_USER);
		loadFieldValue(adminPasswordField, props.getProperty("PortalPassword", ""), PH_ADMIN_PASS);
		kubernetesCheckbox.setSelected(isYes(props, "Kubernetes"));

		String browser = props.getProperty("Browser", "Chrome");
		if ("Edge".equalsIgnoreCase(browser)) edgeRadio.setSelected(true);
		else                                   chromeRadio.setSelected(true);

		// Wipe extra blocks, then rebuild from saved server entries
		while (serverBlocks.size() > 1) {
			removeServerBlock(serverBlocks.get(serverBlocks.size() - 1));
		}

		java.util.List<Integer> validServers = new java.util.ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			String url = props.getProperty("ServerUrl" + i, "").trim();
			if (!url.isEmpty()) validServers.add(i);
		}

		if (validServers.isEmpty()) {
			// No servers in config — leave the single empty block visible
			if (!serverBlocks.isEmpty()) {
				ServerBlock sb = serverBlocks.get(0);
				loadFieldValue(sb.serverUrlField, "", PH_SERVER_URL);
				loadFieldValue(sb.serverAdminUsername, "", PH_SERVER_USER);
				loadFieldValue(sb.serverAdminPassword, "", PH_SERVER_PASS);
				sb.serverRoleCombo.setSelectedIndex(0);
				sb.federatedRadio.setSelected(true);
			}
		} else {
			loadServerFromProps(serverBlocks.get(0), props, validServers.get(0));
			for (int i = 1; i < validServers.size(); i++) {
				addServerBlock();
				loadServerFromProps(serverBlocks.get(serverBlocks.size() - 1), props, validServers.get(i));
			}
		}

		// Advanced settings aren't persisted yet — reset to defaults each load
		if (verboseLoggingToggle != null) verboseLoggingToggle.setSelected(false);
		if (skipSSLToggle != null)        skipSSLToggle.setSelected(false);
		if (autoReportsToggle != null)    autoReportsToggle.setSelected(true);
		if (timeoutSpinner != null)       timeoutSpinner.setValue(30);
	}

	private void loadServerFromProps(ServerBlock sb, Properties props, int num) {
		loadFieldValue(sb.serverUrlField, props.getProperty("ServerUrl" + num, ""), PH_SERVER_URL);
		loadFieldValue(sb.serverAdminUsername, props.getProperty("ServerAdminUsername" + num, ""), PH_SERVER_USER);
		loadFieldValue(sb.serverAdminPassword, props.getProperty("ServerAdminPassword" + num, ""), PH_SERVER_PASS);

		String role = props.getProperty("ServerRole" + num, "");
		if (role != null && !role.isEmpty()) {
			for (int j = 0; j < sb.serverRoleCombo.getItemCount(); j++) {
				if (sb.serverRoleCombo.getItemAt(j).equalsIgnoreCase(role)) {
					sb.serverRoleCombo.setSelectedIndex(j);
					break;
				}
			}
		}

		if (!isYes(props, "Federated" + num)) sb.nonFederatedRadio.setSelected(true);
		else                                   sb.federatedRadio.setSelected(true);
	}

	public void saveConfig(Properties props) {
		props.setProperty("PortalUrl",      getPortalUrl());
		props.setProperty("PortalUserName", getAdminUsername());
		props.setProperty("PortalPassword", getAdminPassword());
		props.setProperty("Kubernetes",     isKubernetes() ? "Yes" : "No");
		props.setProperty("Browser",        getSelectedBrowser());

		// Clear all 10 slots first, then write the active ones
		for (int i = 1; i <= 10; i++) {
			props.setProperty("ServerUrl" + i, "");
			props.setProperty("ServerAdminUsername" + i, "");
			props.setProperty("ServerAdminPassword" + i, "");
			props.setProperty("ServerRole" + i, "");
			props.setProperty("Federated" + i, "Yes");
		}
		for (int i = 0; i < serverBlocks.size() && i < 10; i++) {
			ServerBlock sb = serverBlocks.get(i);
			int n = i + 1;
			props.setProperty("ServerUrl" + n,           getFieldValue(sb.serverUrlField, PH_SERVER_URL).trim());
			props.setProperty("ServerAdminUsername" + n, getFieldValue(sb.serverAdminUsername, PH_SERVER_USER).trim());
			props.setProperty("ServerAdminPassword" + n, getFieldValue(sb.serverAdminPassword, PH_SERVER_PASS));
			props.setProperty("ServerRole" + n,          (String) sb.serverRoleCombo.getSelectedItem());
			props.setProperty("Federated" + n,           sb.federatedRadio.isSelected() ? "Yes" : "No");
		}
	}

	private static boolean isYes(Properties p, String key) {
		String v = p.getProperty(key, "No").trim();
		return v.equalsIgnoreCase("Yes") || v.equalsIgnoreCase("Y");
	}

	// ── Placeholder helpers ──────────────────────────────────────────────────

	private static final Color PLACEHOLDER_COLOR = new Color(180, 180, 180);
	private static final Color NORMAL_COLOR      = new Color(36, 36, 36);

	// Add placeholder to a regular text field
	private static void setPlaceholder(JTextField field, String placeholder) {
		field.setForeground(PLACEHOLDER_COLOR);
		field.setText(placeholder);
		field.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (field.getText().equals(placeholder) && field.getForeground().equals(PLACEHOLDER_COLOR)) {
					field.setText("");
					field.setForeground(NORMAL_COLOR);
				}
			}
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (field.getText().isEmpty()) {
					field.setForeground(PLACEHOLDER_COLOR);
					field.setText(placeholder);
				}
			}
		});
	}

	// Add placeholder to a password field
	private static void setPlaceholder(JPasswordField field, String placeholder) {
		char originalEcho = field.getEchoChar();
		field.setEchoChar((char) 0);
		field.setForeground(PLACEHOLDER_COLOR);
		field.setText(placeholder);
		field.addFocusListener(new java.awt.event.FocusAdapter() {
			@Override
			public void focusGained(java.awt.event.FocusEvent e) {
				if (String.valueOf(field.getPassword()).equals(placeholder) && field.getForeground().equals(PLACEHOLDER_COLOR)) {
					field.setText("");
					field.setForeground(NORMAL_COLOR);
					field.setEchoChar(originalEcho);
				}
			}
			@Override
			public void focusLost(java.awt.event.FocusEvent e) {
				if (field.getPassword().length == 0) {
					field.setEchoChar((char) 0);
					field.setForeground(PLACEHOLDER_COLOR);
					field.setText(placeholder);
				}
			}
		});
	}

	// Get value from a text field — returns "" if placeholder is still showing
	private static String getFieldValue(JTextField field, String placeholder) {
		if (field.getForeground().equals(PLACEHOLDER_COLOR) && field.getText().equals(placeholder)) {
			return "";
		}
		return field.getText();
	}

	// Get value from a password field — returns "" if placeholder is still showing
	private static String getFieldValue(JPasswordField field, String placeholder) {
		String val = String.valueOf(field.getPassword());
		if (field.getForeground().equals(PLACEHOLDER_COLOR) && val.equals(placeholder)) {
			return "";
		}
		return val;
	}

	// Load a saved value into a text field, or show placeholder if empty
	private static void loadFieldValue(JTextField field, String value, String placeholder) {
		if (value == null || value.isEmpty()) {
			field.setForeground(PLACEHOLDER_COLOR);
			field.setText(placeholder);
		} else {
			field.setForeground(NORMAL_COLOR);
			field.setText(value);
		}
	}

	// Load a saved value into a password field, or show placeholder if empty
	private static void loadFieldValue(JPasswordField field, String value, String placeholder) {
		if (value == null || value.isEmpty()) {
			field.setEchoChar((char) 0);
			field.setForeground(PLACEHOLDER_COLOR);
			field.setText(placeholder);
		} else {
			field.setEchoChar('\u2022');
			field.setForeground(NORMAL_COLOR);
			field.setText(value);
		}
	}
}
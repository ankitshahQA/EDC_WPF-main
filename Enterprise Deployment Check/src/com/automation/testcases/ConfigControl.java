package com.automation.testcases;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class ConfigControl extends JFrame {
	public static FileInputStream input = null;

	// public static Xls_Reader xls = null;
	private static JFrame frame;
	public static Properties prop;
	static JTabbedPane tabbedPane = new JTabbedPane();
	// static JScrollPane scroll = new JScrollPane();
	// public static final JPanel panel;
	public static String projectName = "Enterprise Installation Sanity Test";
	private static final String sheetName = "Configuration";
	static String frameTitle = "Enterprise Installation Checklist Test Tool";
	private JTextField portalurltext;
	private JLabel portalurllabel;
	private JLabel portalusernamelabel;

	private JLabel portalpasswordlabel;
	private JLabel managerusernamelabel;
	private JLabel managerurllabel;
	private JLabel managerpasswordlabel;
	private JLabel adminurllabel1;
	private JLabel adminurllabel2;
	private JLabel adminurllabel3;
	private JLabel adminurllabel4;
	private JLabel adminurllabel5;
	private JLabel adminurllabel6;
	private JLabel adminusernamelabel;
	private JLabel adminpasswordlabel;
	private JLabel federatedlabel;
	private JRadioButton federatedradio;
	private JRadioButton nonfederatedradio;
	private JCheckBox saml;
	private JTextField portalusernametext;
	private JPasswordField portalpasswordtext;
	private JTextField managerusernametext;
	private JTextField managerurltext;
	private JPasswordField managerpasswordtext;
	private JTextField adminurltext1;
	private JTextField adminurltext2;
	private JTextField adminurltext3;
	private JTextField adminurltext4;
	private JTextField adminurltext5;
	private JTextField adminurltext6;
	private JTextField adminusernametext;
	private JPasswordField adminpasswordtext;
	private JButton saveBtn;
	//private JButton addsite;
	private JLabel addsite;
	private JButton removesite;
	public static JScrollPane scroll;
	public static JPanel panel;
	public static String URL;
	public static String adminURL1;
	public static int no_of_site = 1;
	public static String managerURL;
	public static String UserName;
	public static String Password;
	public static boolean SAML = false;
	public static boolean federated = true;
	public static String username_admin;
	public static String password_admin;
	public static String username_manager;
	public static String password_manager;
	public static String browser;

	private JComboBox<String> browsercomboBox;
	private JLabel browserlabel;
	String Portalurl = "e.g. https://myportal.esri.com/portal/home/";
	String PortalUserName = "";
	String PortalPassword = "";
	String Serverurl1 = "";
	String Serverurl2 = "";
	String Serverurl3 = "";
	String Serverurl4 = "";
	String Serverurl5 = "";
	String Serverurl6 = "";
	String Servermanagerurl = "";
	String ServerAdminUsername = "";
	String ServerAdminPassword = "";
	String ServerManagerUsername = "";
	String ServerManagerPassword = "";
	String Browser = "";
	int x1 = 30;
	int y1 = 50;
	int h1 = 30;
	int w1 = 200;
	int x2 = 250;
	int y2 = 50;
	int h2 = 30;
	int w2 = 400;

	TestRunner runner1 = new TestRunner();

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		configSetup();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		// JFrame frame = new JFrame(frameTitle);

		// Create and set up the content pane.
		// FormControl newContentPane = new FormControl();
		frame = new ConfigControl();
		// newContentPane.setOpaque(true); //content panes must be opaque
		// newContentPane.setLayout(new SpringLayout());
		// frame.setContentPane(newContentPane);
		frame.setTitle(frameTitle);
		// frame.setLocation(100, 100);
		frame.setBounds(100, 100, 750, 600);
		frame.setMaximumSize(new Dimension(1300, 600));
		frame.setResizable(false);

		// frame.add(tabbedPane);

		// frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		// Container p = frame.getContentPane();
		// p.add(scroll, BorderLayout.CENTER);
		frame.getContentPane().add(scroll);
		// frame.setContentPane(newContentPane);
		// Display the window.
		// frame.pack();
		// frame.add(newContentPane, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Create the application.
	 */
	public ConfigControl() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// frame = new JFrame("Enter Test Parameters");

		panel = new JPanel();
		scroll = new JScrollPane(tabbedPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// scroll.setLayout(null);
		// frame.setLocation(100, 100);
		// frame.setBounds(200, 200, 900, 700);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setLayout(new SpringLayout());
		// panel = new JPanel();
		panel.setLayout(null);
		// scroll.setMaximumSize(new Dimension(1300, 600));
		// add(BorderLayout.CENTER, new JScrollPane(panel));
		panel.setBackground(Color.GRAY);
		// panel.setMaximumSize(new Dimension(800, 600));
		// GridLayout gr=new GridLayout(xls.getRowCount(sheetName), 2,10,10);
		// panel.setLayout(gr);

		try {
			if (input != null) {
				Portalurl = prop.getProperty("Portalurl");
				PortalUserName = prop.getProperty("PortalUserName");
				PortalPassword = prop.getProperty("PortalPassword");
				Serverurl1 = prop.getProperty("Serverurl1");
				Serverurl2 = prop.getProperty("Serverurl2");
				Serverurl3 = prop.getProperty("Serverurl3");
				Serverurl4 = prop.getProperty("Serverurl4");
				Serverurl5 = prop.getProperty("Serverurl5");
				Serverurl6 = prop.getProperty("Serverurl6");
				ServerAdminUsername = prop.getProperty("ServerAdminUsername");
				ServerAdminPassword = prop.getProperty("ServerAdminPassword");
				Servermanagerurl = prop.getProperty("Servermanagerurl");
				ServerManagerUsername = prop.getProperty("ServerManagerUsername");
				ServerManagerPassword = prop.getProperty("ServerManagerPassword");
				Browser = prop.getProperty("Browser");
				if (Browser == null) {
					Browser = "Firefox";
				}
			}
			// tabbedPane.add(scroll);
			tabbedPane.addTab("Enterprise Parameters", null, panel, "Enterprise Parameters");
			tabbedPane.setFont(new Font("Verdana", Font.BOLD, 12));
			tabbedPane.setBorder(BorderFactory.createLineBorder(Color.black, 5));

			// panel.add(scroll, BorderLayout.CENTER);
			// tabbedPane.addTab("Enterprise Parameters", null, scroll, "Enterprise
			// Parameters");
			// tabbedPane.setFont(new Font("Verdana", Font.BOLD, 12));
			// tabbedPane.setBorder(BorderFactory.createLineBorder(Color.black,5));
			JLabel header = new JLabel("Enter all required information in the form below...");// make label and assign
			header.setFont(new Font("Verdana", Font.BOLD, 14)); // text in 1 line
			header.setBounds(40, 10, 500, h1);
			panel.add(header);

			portalurllabel = new JLabel("Portal URL*", JLabel.RIGHT);// make label and assign text in 1 line
			portalurllabel.setBounds(x1, y1, w1, h1);
			portalurllabel.setFont(new Font("Verdana", Font.PLAIN, 14));
			panel.add(portalurllabel);
			portalurltext = new JTextField(Portalurl, 100);
			portalurltext.setBounds(x2, y2, w2, h2);
			portalurltext.setBackground(Color.LIGHT_GRAY);
			panel.add(portalurltext);
			y1 = y1 + 30;
			y2 = y1;
			portalusernamelabel = new JLabel("Portal Username*", JLabel.RIGHT);
			portalusernamelabel.setFont(new Font("Verdana", Font.PLAIN, 14));
			// make label and assign text in 1 line
			portalusernametext = new JTextField(PortalUserName, 100);
			portalusernametext.setBackground(Color.LIGHT_GRAY);
			portalpasswordlabel = new JLabel("Portal Password*", JLabel.RIGHT);
			portalpasswordlabel.setFont(new Font("Verdana", Font.PLAIN, 14));// make label and assign text in 1 line
			portalpasswordtext = new JPasswordField(PortalPassword);
			portalpasswordtext.setBackground(Color.LIGHT_GRAY);
			managerurllabel = new JLabel("Server Manager URL*", JLabel.RIGHT);
			managerurllabel.setFont(new Font("Verdana", Font.PLAIN, 14));
			managerurltext = new JTextField(Servermanagerurl, 100);
			managerurltext.setBackground(Color.LIGHT_GRAY);
			managerusernamelabel = new JLabel("Server Manager Username*", JLabel.RIGHT);
			managerusernamelabel.setFont(new Font("Verdana", Font.PLAIN, 14));
			managerusernametext = new JTextField(ServerManagerUsername, 100);
			managerusernametext.setBackground(Color.LIGHT_GRAY);
			managerpasswordlabel = new JLabel("Server Manager Password*", JLabel.RIGHT);
			managerpasswordlabel.setFont(new Font("Verdana", Font.PLAIN, 14));
			managerpasswordtext = new JPasswordField(ServerManagerPassword, 100);
			managerpasswordtext.setBackground(Color.LIGHT_GRAY);
			federatedlabel = new JLabel("Is the Enterprise*", JLabel.RIGHT);
			federatedlabel.setFont(new Font("Verdana", Font.PLAIN, 14));
			federatedradio = new JRadioButton("federated", true);
			federatedradio.setFont(new Font("Verdana", Font.PLAIN, 14));
			nonfederatedradio = new JRadioButton("non federated");
			nonfederatedradio.setFont(new Font("Verdana", Font.PLAIN, 14));
			nonfederatedradio.setBackground(Color.GRAY);
			federatedradio.setBackground(Color.GRAY);
			//addsite = new JButton();
			addsite=new JLabel();
			// <p style='text-align:left;'>
			addsite.setText("<HTML><U>Click to add additional site(max 6)</U></HTML>");
			//addsite.setBorderPainted(false);
			addsite.setOpaque(false);
			addsite.setBackground(Color.GRAY);
			addsite.setFont(new Font("Verdana", Font.PLAIN, 14));
			addsite.setHorizontalTextPosition(SwingConstants.LEFT);
			addsite.setHorizontalAlignment(SwingConstants.LEFT);
			// addsite.set
			adminurllabel1 = new JLabel("Server Site1 Admin URL*", JLabel.RIGHT);
			adminurllabel1.setFont(new Font("Verdana", Font.PLAIN, 14));
			adminurltext1 = new JTextField(Serverurl1, 100);
			adminurltext1.setBackground(Color.LIGHT_GRAY);
			adminurllabel2 = new JLabel("Server Site2 Admin URL*", JLabel.RIGHT);
			adminurllabel2.setFont(new Font("Verdana", Font.PLAIN, 14));
			adminurltext2 = new JTextField(Serverurl2, 100);
			adminurltext2.setBackground(Color.LIGHT_GRAY);
			adminurllabel3 = new JLabel("Server Site3 Admin URL*", JLabel.RIGHT);
			adminurllabel3.setFont(new Font("Verdana", Font.PLAIN, 14));
			adminurltext3 = new JTextField(Serverurl3, 100);
			adminurltext3.setBackground(Color.LIGHT_GRAY);
			adminurllabel4 = new JLabel("Server Site4 Admin URL*", JLabel.RIGHT);
			adminurllabel4.setFont(new Font("Verdana", Font.PLAIN, 14));
			adminurltext4 = new JTextField(Serverurl4, 100);
			adminurltext4.setBackground(Color.LIGHT_GRAY);
			adminurllabel5 = new JLabel("Server Site5 Admin URL*", JLabel.RIGHT);
			adminurllabel5.setFont(new Font("Verdana", Font.PLAIN, 14));
			adminurltext5 = new JTextField(Serverurl5, 100);
			adminurltext5.setBackground(Color.LIGHT_GRAY);
			adminurllabel6 = new JLabel("Server Site6 Admin URL*", JLabel.RIGHT);
			adminurllabel6.setFont(new Font("Verdana", Font.PLAIN, 14));
			adminurltext6 = new JTextField(Serverurl6, 100);
			adminurltext6.setBackground(Color.LIGHT_GRAY);
			adminusernamelabel = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
			adminusernamelabel.setFont(new Font("Verdana", Font.PLAIN, 14)); // line
			adminusernametext = new JTextField(ServerAdminUsername, 100);
			adminusernametext.setBackground(Color.LIGHT_GRAY);
			adminpasswordlabel = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
			adminpasswordlabel.setFont(new Font("Verdana", Font.PLAIN, 14)); // line
			adminpasswordtext = new JPasswordField(ServerAdminPassword, 100);
			adminpasswordtext.setBackground(Color.LIGHT_GRAY);
			browserlabel = new JLabel("which browser do you use?", JLabel.RIGHT);// make label and assign text in 1 line
			panel.add(browserlabel);
			browserlabel.setFont(new Font("Verdana", Font.PLAIN, 14));
			browsercomboBox = new JComboBox<String>();
			browsercomboBox.setBackground(Color.LIGHT_GRAY);
			browsercomboBox.addItem("Chrome");
			browsercomboBox.addItem("Firefox");
			browsercomboBox.addItem("Edge");
			browsercomboBox.setSelectedItem(Browser);

			// scroll = new JScrollPane(panel);
			saveBtn = new JButton("Save and Run Tool");
			saveBtn.setBackground(Color.blue);
			saveBtn.setFont(new Font("Verdana", Font.PLAIN, 14));
			saml = new JCheckBox("Check to login using SAML");
			saml.setBackground(Color.GRAY);
			saml.setFont(new Font("Verdana", Font.PLAIN, 14));
			saml.setBounds(x2, y2, w2, h1);
			panel.add(saml);
			panel.add(portalusernamelabel);
			panel.add(portalusernametext);
			panel.add(portalpasswordlabel);
			panel.add(portalpasswordtext);
			panel.add(federatedlabel);
			panel.add(federatedradio);
			panel.add(nonfederatedradio);
			panel.add(adminurllabel1);
			panel.add(adminurltext1);
			panel.add(adminurllabel2);
			panel.add(adminurltext2);
			panel.add(adminurllabel3);
			panel.add(adminurltext3);
			panel.add(adminurllabel4);
			panel.add(adminurltext4);
			panel.add(adminurllabel5);
			panel.add(adminurltext5);
			panel.add(adminurllabel6);
			panel.add(adminurltext6);
			panel.add(addsite);
			panel.add(adminusernamelabel);
			panel.add(adminusernametext);
			panel.add(adminpasswordlabel);
			panel.add(adminpasswordtext);
			panel.add(browsercomboBox);

			y1 = y1 + 40;
			y2 = y1;
			saml.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						y1 = saml.getY();
						y1 = y1 + 40;
						y2 = y1;
						panel.remove(portalusernamelabel);
						panel.remove(portalpasswordlabel);
						panel.remove(portalusernametext);
						panel.remove(portalpasswordtext);
						tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(), tabbedPane.getHeight() - 80));
						validate();
						federatedlabel.setBounds(x1, y1, w1, h1);
						federatedradio.setBounds(x2, y2, w2, h2);
						y1 = y1 + 25;
						y2 = y1;
						nonfederatedradio.setBounds(x2, y2, w2, h2);
						y1 = y1 + 40;
						y2 = y1;
						if (nonfederatedradio.isSelected()) {
							managerurllabel.setBounds(x1, y1, w1, h1);
							managerurltext.setBounds(x2, y2, w2, h2);
							y1 = y1 + 40;
							y2 = y1;
							managerusernamelabel.setBounds(x1, y1, w1, h1);
							managerusernametext.setBounds(x2, y2, w2, h2);
							y1 = y1 + 40;
							y2 = y1;
							managerpasswordlabel.setBounds(x1, y1, w1, h1);
							managerpasswordtext.setBounds(x2, y2, w2, h2);
							y1 = y1 + 40;
							y2 = y1;
							// tabbedPane.setPreferredSize(new Dimension(700,500));
						}
						if (federatedradio.isSelected()) {
							panel.remove(managerurllabel);
							panel.remove(managerurltext);
							panel.remove(managerusernamelabel);
							panel.remove(managerusernamelabel);
							panel.remove(managerpasswordlabel);
							panel.remove(managerpasswordtext);

							// tabbedPane.setPreferredSize(new Dimension(700,400));
						}

						adminurllabel1.setBounds(x1, y1, w1, h1);
						adminurltext1.setBounds(x2, y2, w2, h2);

						for (int i = 1; i < no_of_site; i++) {
							y1 = y1 + 40;
							y2 = y1;
							// tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(),
							// tabbedPane.getHeight()+40));

							if (i == 1) {
								adminurllabel2.setBounds(x1, y1, w1, h1);
								adminurltext2.setBounds(x2, y2, w2, h2);

							}
							if (i == 2) {
								adminurllabel3.setBounds(x1, y1, w1, h1);
								adminurltext3.setBounds(x2, y2, w2, h2);

							}
							if (i == 3) {
								adminurllabel4.setBounds(x1, y1, w1, h1);
								adminurltext4.setBounds(x2, y2, w2, h2);

							}
							if (i == 4) {
								adminurllabel5.setBounds(x1, y1, w1, h1);
								adminurltext5.setBounds(x2, y2, w2, h2);

							}
							if (i == 5) {
								adminurllabel6.setBounds(x1, y1, w1, h1);
								adminurltext6.setBounds(x2, y2, w2, h2);

							}

							// panel.revalidate();
						}
						if (no_of_site < 6) {
							y1 = y1 + 25;
							y2 = y1;
							//addsite.setBorderPainted(false);
							addsite.setOpaque(false);
							addsite.setBounds(x2, y2, w2 , h2);
						}
						y1 = y1 + 40;
						y2 = y1;
						adminusernamelabel.setBounds(x1, y1, w1, h1);
						adminusernametext.setBounds(x2, y2, w2, h2);
						y1 = y1 + 40;
						y2 = y1;
						adminpasswordlabel.setBounds(x1, y1, w1, h1);
						adminpasswordtext.setBounds(x2, y2, w2, h2);
						y1 = y1 + 40;
						y2 = y1;
						browserlabel.setBounds(x1, y1, w1, h1);
						browsercomboBox.setBounds(x2, y2, w2, h2);
						saveBtn.setBounds(x2, y1 + 60, 150, 30);

						validate();
						repaint();
						// textField2.setEnabled(false);
						// textField3.setEnabled(false);
					}

					else if (e.getStateChange() == ItemEvent.DESELECTED) {
						y1 = saml.getY();
						y1 = y1 + 40;
						y2 = y1;
						portalusernamelabel.setBounds(x1, y1, w1, h1);
						panel.add(portalusernamelabel);

						portalusernametext.setBounds(x2, y2, w2, h2);
						panel.add(portalusernametext);
						y1 = y1 + 40;
						y2 = y1;
						portalpasswordlabel.setBounds(x1, y1, w1, h1);
						panel.add(portalpasswordlabel);
						portalpasswordtext.setBounds(x2, y2, w2, h2);
						panel.add(portalpasswordtext);
						// tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(),
						// tabbedPane.getHeight()+40));
						// validate();
						y1 = y1 + 40;
						y2 = y1;
						federatedlabel.setBounds(x1, y1, w1, h1);
						federatedradio.setBounds(x2, y2, w2, h2);
						y1 = y1 + 25;
						y2 = y1;
						nonfederatedradio.setBounds(x2, y2, w2, h2);
						y1 = y1 + 40;
						y2 = y1;
						if (nonfederatedradio.isSelected()) {
							managerurllabel.setBounds(x1, y1, w1, h1);
							managerurltext.setBounds(x2, y2, w2, h2);
							y1 = y1 + 40;
							y2 = y1;
							managerusernamelabel.setBounds(x1, y1, w1, h1);
							managerusernametext.setBounds(x2, y2, w2, h2);
							y1 = y1 + 40;
							y2 = y1;
							managerpasswordlabel.setBounds(x1, y1, w1, h1);
							managerpasswordtext.setBounds(x2, y2, w2, h2);
							y1 = y1 + 40;
							y2 = y1;
							validate();
							// tabbedPane.setPreferredSize(new Dimension(700,650));
						}
						if (federatedradio.isSelected()) {
							panel.remove(managerurllabel);
							panel.remove(managerurltext);
							panel.remove(managerusernamelabel);
							panel.remove(managerusernamelabel);
							panel.remove(managerpasswordlabel);
							panel.remove(managerpasswordtext);
							// tabbedPane.setPreferredSize(new Dimension(700,400));
						}

						adminurllabel1.setBounds(x1, y1, w1, h1);
						adminurltext1.setBounds(x2, y2, w2, h2);
						for (int i = 1; i < no_of_site; i++) {
							y1 = y1 + 40;
							y2 = y1;
							tabbedPane.setPreferredSize(
									new Dimension(tabbedPane.getWidth(), tabbedPane.getHeight() + 40));
							if (i == 1) {
								adminurllabel2.setBounds(x1, y1, w1, h1);
								adminurltext2.setBounds(x2, y2, w2, h2);

							}
							if (i == 2) {
								adminurllabel3.setBounds(x1, y1, w1, h1);
								adminurltext3.setBounds(x2, y2, w2, h2);

							}
							if (i == 3) {
								adminurllabel4.setBounds(x1, y1, w1, h1);
								adminurltext4.setBounds(x2, y2, w2, h2);

							}
							if (i == 4) {
								adminurllabel5.setBounds(x1, y1, w1, h1);
								adminurltext5.setBounds(x2, y2, w2, h2);

							}
							if (i == 5) {
								adminurllabel6.setBounds(x1, y1, w1, h1);
								adminurltext6.setBounds(x2, y2, w2, h2);

							}

							// panel.revalidate();
						}
						if (no_of_site < 6) {
							y1 = y1 + 25;
							y2 = y1;
							//addsite.setBorderPainted(false);
							addsite.setOpaque(false);
							addsite.setBounds(x2, y2, w2, h2);
						}

						y1 = y1 + 40;
						y2 = y1;

						adminusernamelabel.setBounds(x1, y1, w1, h1);
						adminusernametext.setBounds(x2, y2, w2, h2);
						y1 = y1 + 40;
						y2 = y1;
						adminpasswordlabel.setBounds(x1, y1, w1, h1);
						adminpasswordtext.setBounds(x2, y2, w2, h2);
						y1 = y1 + 40;
						y2 = y1;
						browserlabel.setBounds(x1, y1, w1, h1);
						browsercomboBox.setBounds(x2, y2, w2, h2);
						saveBtn.setBounds(x2, y1 + 60, 150, 30);
						validate();
						repaint();
						// textField2.setEnabled(true);
						// textField3.setEnabled(true);
					}

				}
			});
			portalusernamelabel.setBounds(x1, y1, w1, h1);
			portalusernametext.setBounds(x2, y2, w2, h2);
			y1 = y1 + 40;
			y2 = y1;
			portalpasswordlabel.setBounds(x1, y1, w1, h1);
			portalpasswordtext.setBounds(x2, y2, w2, h2);

			y1 = y1 + 40;
			y2 = y1;
			// make label and assign text in 1 line
			federatedlabel.setBounds(x1, y1, w1, h1);
			federatedradio.setBounds(x2, y2, w2, h2);

			federatedradio.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						y1 = nonfederatedradio.getY();
						panel.remove(managerurllabel);
						panel.remove(managerurltext);
						panel.remove(managerusernamelabel);
						panel.remove(managerusernametext);
						panel.remove(managerpasswordlabel);
						panel.remove(managerpasswordtext);
						if(saml.isSelected()) {
						tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(), tabbedPane.getHeight() - 40));
						}
						else {
							tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(), tabbedPane.getHeight() - 120));
								
						}
						validate();
						y1 = y1 + 40;
						y2 = y1;
						adminurllabel1.setBounds(x1, y1, w1, h1);
						adminurltext1.setBounds(x2, y2, w2, h2);
						for (int i = 1; i < no_of_site; i++) {
							y1 = y1 + 40;
							y2 = y1;
							// tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(),
							// tabbedPane.getHeight()+40));
							if (i == 1) {
								adminurllabel2.setBounds(x1, y1, w1, h1);
								adminurltext2.setBounds(x2, y2, w2, h2);

							}
							if (i == 2) {
								adminurllabel3.setBounds(x1, y1, w1, h1);
								adminurltext3.setBounds(x2, y2, w2, h2);

							}
							if (i == 3) {
								adminurllabel4.setBounds(x1, y1, w1, h1);
								adminurltext4.setBounds(x2, y2, w2, h2);

							}
							if (i == 4) {
								adminurllabel5.setBounds(x1, y1, w1, h1);
								adminurltext5.setBounds(x2, y2, w2, h2);

							}
							if (i == 5) {
								adminurllabel6.setBounds(x1, y1, w1, h1);
								adminurltext6.setBounds(x2, y2, w2, h2);

							}

							// panel.revalidate();
						}
						if (no_of_site < 6) {
							y1 = y1 + 25;
							y2 = y1;
							//addsite.setBorderPainted(false);
							addsite.setOpaque(false);
							addsite.setBounds(x2, y2, w2, h2);
							// tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(),
							// tabbedPane.getHeight()+40));
							// panel.revalidate();
						}
						y1 = y1 + 40;
						y2 = y1;
						adminusernamelabel.setBounds(x1, y1, w1, h1);
						adminusernametext.setBounds(x2, y2, w2, h2);
						y1 = y1 + 40;
						y2 = y1;
						adminpasswordlabel.setBounds(x1, y1, w1, h1);
						adminpasswordtext.setBounds(x2, y2, w2, h2);
						y1 = y1 + 40;
						y2 = y1;
						browserlabel.setBounds(x1, y1, w1, h1);
						browsercomboBox.setBounds(x2, y2, w2, h2);
						saveBtn.setBounds(x2, y1 + 60, 150, 30);
						// tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(),
						// tabbedPane.getHeight()+40));
						/*
						 * if (saml.isSelected()) { tabbedPane.setPreferredSize(new Dimension(700,400));
						 * } else { tabbedPane.setPreferredSize(new Dimension(700,500)); }
						 * tabbedPane.setPreferredSize(new Dimension(700,400));
						 */
						panel.validate();
						panel.repaint();

						// textField2.setEnabled(false);
						// textField3.setEnabled(false);
					}

				}
			});
			y1 = y1 + 25;
			y2 = y1;

			nonfederatedradio.setBounds(x2, y2, w2, h2);

			ButtonGroup group = new ButtonGroup();
			group.add(federatedradio);
			group.add(nonfederatedradio);
			nonfederatedradio.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						y1 = nonfederatedradio.getY();

						y1 = y1 + 40;
						y2 = y1;
						// make label and assign text in 1 line
						managerurllabel.setBounds(x1, y1, w1, h1);
						panel.add(managerurllabel);

						managerurltext.setBounds(x2, y2, w2, h2);
						panel.add(managerurltext);
						y1 = y1 + 40;
						y2 = y1;
						
						// make label and assign text in 1 line
						managerusernamelabel.setBounds(x1, y1, w1, h1);
						panel.add(managerusernamelabel);

						managerusernametext.setBounds(x2, y2, w2, h2);
						panel.add(managerusernametext);
						y1 = y1 + 40;
						y2 = y1;
						
						// make label and assign text in 1 line
						managerpasswordlabel.setBounds(x1, y1, w1, h1);
						panel.add(managerpasswordlabel);

						managerpasswordtext.setBounds(x2, y2, w2, h2);
						panel.add(managerpasswordtext);
						y1 = y1 + 40;
						y2 = y1;
						if (saml.isSelected()) {
							tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(), tabbedPane.getHeight()+40));

							validate();
							}
							else {
								tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(), tabbedPane.getHeight() +120));

								validate();
							}
						adminurllabel1.setBounds(x1, y1, w1, h1);
						adminurltext1.setBounds(x2, y2, w2, h2);

						for (int i = 1; i < no_of_site; i++) {
							y1 = y1 + 40;
							y2 = y1;
							// tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(),
							// tabbedPane.getHeight()+40));
							if (i == 1) {
								adminurllabel2.setBounds(x1, y1, w1, h1);
								adminurltext2.setBounds(x2, y2, w2, h2);

							}
							if (i == 2) {
								adminurllabel3.setBounds(x1, y1, w1, h1);
								adminurltext3.setBounds(x2, y2, w2, h2);

							}
							if (i == 3) {
								adminurllabel4.setBounds(x1, y1, w1, h1);
								adminurltext4.setBounds(x2, y2, w2, h2);

							}
							if (i == 4) {
								adminurllabel5.setBounds(x1, y1, w1, h1);
								adminurltext5.setBounds(x2, y2, w2, h2);

							}
							if (i == 5) {
								adminurllabel6.setBounds(x1, y1, w1, h1);
								adminurltext6.setBounds(x2, y2, w2, h2);

							}

							// panel.validate();
						}
						if (no_of_site < 6) {
							y1 = y1 + 25;
							y2 = y1;
							//addsite.setBorderPainted(false);
							addsite.setOpaque(false);
							addsite.setBounds(x2, y2, w2, h2);
							// tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(),
							// tabbedPane.getHeight()+40));
							// validate();
						}
						y1 = y1 + 40;
						y2 = y1;
						adminusernamelabel.setBounds(x1, y1, w1, h1);
						adminusernametext.setBounds(x2, y2, w2, h2);
						y1 = y1 + 40;
						y2 = y1;
						// tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(),
						// tabbedPane.getHeight()+40));
						// panel.validate();
						adminpasswordlabel.setBounds(x1, y1, w1, h1);
						adminpasswordtext.setBounds(x2, y2, w2, h2);
						y1 = y1 + 40;
						y2 = y1;
						// tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(),
						// tabbedPane.getHeight()+40));
						// validate();
						browserlabel.setBounds(x1, y1, w1, h1);
						browsercomboBox.setBounds(x2, y2, w2, h2);
						saveBtn.setBounds(x2, y1 + 60, 150, 30);
						// tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(),
						// tabbedPane.getHeight()+100));

						// panel.add(scroll);

						/*
						 * if (saml.isSelected()) { tabbedPane.setPreferredSize(new Dimension(700,500));
						 * } else { tabbedPane.setPreferredSize(new Dimension(700,650)); }
						 */
						validate();
						repaint();
						// textField2.setEnabled(false);
						// textField3.setEnabled(false);
					}

				}
			});

			y1 = y1 + 40;
			y2 = y1;
			adminurllabel1.setBounds(x1, y1, w1, h1);
			adminurltext1.setBounds(x2, y2, w2, h2);

			// make label and assign text in 1 line

			for (int i = 1; i < no_of_site; i++) {
				y1 = y1 + 40;
				y2 = y1;
				tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(), tabbedPane.getHeight() + 40));

				if (i == 1) {
					adminurllabel2.setBounds(x1, y1, w1, h1);
					adminurltext2.setBounds(x2, y2, w2, h2);

				}
				if (i == 2) {
					adminurllabel3.setBounds(x1, y1, w1, h1);
					adminurltext3.setBounds(x2, y2, w2, h2);

				}
				if (i == 3) {
					adminurllabel4.setBounds(x1, y1, w1, h1);
					adminurltext4.setBounds(x2, y2, w2, h2);

				}
				if (i == 4) {
					adminurllabel5.setBounds(x1, y1, w1, h1);
					adminurltext5.setBounds(x2, y2, w2, h2);

				}
				if (i == 5) {
					adminurllabel6.setBounds(x1, y1, w1, h1);
					adminurltext6.setBounds(x2, y2, w2, h2);

				}

				panel.revalidate();
			}
			if (no_of_site < 6) {
				y1 = y1 + 25;
				y2 = y1;
				//addsite.setBorderPainted(false);
				addsite.setOpaque(false);
				addsite.setBounds(x2, y2, w2, h2);
			}
			y1 = y1 + 40;
			y2 = y1;
			adminusernamelabel.setBounds(x1, y1, w1, h1);

			adminusernametext.setBounds(x2, y2, w2, h2);
			
			y1 = y1 + 40;
			y2 = y1;
			adminpasswordlabel.setBounds(x1, y1, w1, h1);

			adminpasswordtext.setBounds(x2, y2, w2, h2);
			
			y1 = y1 + 40;
			y2 = y1;
			browserlabel.setBounds(x1, y1, w1, h1);
			browsercomboBox.setBounds(x2, y2, w2, h2);
			
			saveBtn.setBounds(x2, y1 + 60, 175, 30);
			saveBtn.setBorder(BorderFactory.createRaisedSoftBevelBorder());
			
			// saveBtn.setPreferredSize(new Dimension(20, 30));
			// saveBtn.setAlignmentY(CENTER_ALIGNMENT);
			// saveBtn.setAlignmentX(CENTER_ALIGNMENT);
			saveBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setvalues(panel);
					verifyvalues(panel);
					// saveToconfig(panel);
					// setvalues(panel);
					// frame.dispose();
					// runner1.callSanity(browser);
				}

			});
			addsite.addMouseListener(new MouseAdapter() {
			       
			//addsite.addActionListener(new ActionListener() {

				@Override 
				public void mouseClicked(MouseEvent e) {
				//public void actionPerformed(ActionEvent e) {
					
					no_of_site = no_of_site + 1;
					if (no_of_site == 2) {
						y1 = adminurllabel1.getY();
						y1 = y1 + 40;
						y2 = y1;
						adminurllabel2.setBounds(x1, y1, w1, h1);
						adminurltext2.setBounds(x2, y2, w2, h2);
						y1 = y1 + 40;
						y2 = y1;
						//addsite.setBorderPainted(false);
						addsite.setOpaque(false);
						addsite.setBounds(x2, y2, w2, h2);
						tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(), tabbedPane.getHeight() + 40));
						validate();
					}
					if (no_of_site == 3) {
						y1 = adminurllabel2.getY();
						y1 = y1 + 40;
						y2 = y1;
						adminurllabel3.setBounds(x1, y1, w1, h1);
						adminurltext3.setBounds(x2, y2, w2, h2);
						y1 = y1 + 40;
						y2 = y1;
						//addsite.setBorderPainted(false);
						addsite.setOpaque(false);
						addsite.setBounds(x2, y2, w2, h2);
						tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(), tabbedPane.getHeight() + 40));
						validate();
					}
					if (no_of_site == 4) {
						y1 = adminurllabel3.getY();
						y1 = y1 + 40;
						y2 = y1;
						adminurllabel4.setBounds(x1, y1, w1, h1);
						adminurltext4.setBounds(x2, y2, w2, h2);
						y1 = y1 + 40;
						y2 = y1;
						//addsite.setBorderPainted(false);
						addsite.setOpaque(false);
						addsite.setBounds(x2, y2, w2, h2);
						tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(), tabbedPane.getHeight() + 40));
						validate();

					}
					if (no_of_site == 5) {
						y1 = adminurllabel4.getY();
						y1 = y1 + 40;
						y2 = y1;
						adminurllabel5.setBounds(x1, y1, w1, h1);
						adminurltext5.setBounds(x2, y2, w2, h2);
						y1 = y1 + 40;
						y2 = y1;
						addsite.setBounds(x2 , y2, w2, h2);
						//addsite.setBorderPainted(false);
						addsite.setOpaque(false);
						tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(), tabbedPane.getHeight() + 40));
						validate();
					}
					if (no_of_site == 6) {
						panel.remove(addsite);
						y1 = adminurllabel5.getY();
						y1 = y1 + 40;
						y2 = y1;
						adminurllabel6.setBounds(x1, y1, w1, h1);
						adminurltext6.setBounds(x2, y2, w2, h2);
						
					}
					
					y1 = y1 + 40;
					y2 = y1;
					adminusernamelabel.setBounds(x1, y1, w1, h1);
					adminusernametext.setBounds(x2, y2, w2, h2);

					y1 = y1 + 40;
					y2 = y1;
					adminpasswordlabel.setBounds(x1, y1, w1, h1);

					adminpasswordtext.setBounds(x2, y2, w2, h2);

					y1 = y1 + 40;
					y2 = y1;
					browserlabel.setBounds(x1, y1, w1, h1);
					browsercomboBox.setBounds(x2, y2, w2, h2);
					
					saveBtn.setBounds(x2, y1 + 60, 175, 30);
					saveBtn.setBorder(BorderFactory.createRaisedSoftBevelBorder());
					//tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(), tabbedPane.getHeight() + 40));
					validate();
					repaint();

				}
			});
			panel.add(saveBtn);
			panel.revalidate();
			// add(scroll);
			// panel.add(scroll);
			// scroll.add(panel);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}
	static void adjustheight() {
		try {
			//if tabbedPane.geth
			tabbedPane.setPreferredSize(new Dimension(tabbedPane.getWidth(), tabbedPane.getHeight() + 40));
			tabbedPane.validate();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	static void configSetup() {
		try {
			prop = new Properties();
			input = new FileInputStream(System.getProperty("user.dir") + "\\Webdriver\\config.properties");
			if (input != null) {
				prop.load(input);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private void verifyvalues(JPanel panel) {
		if (SAML) {
			if (URL.isEmpty() || adminURL1.isEmpty() || managerURL.isEmpty() || username_admin.isEmpty()
					|| password_admin.isEmpty() || username_manager.isEmpty() || password_manager.isEmpty()) {
				String message = "Please fill all details to run sanity";
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
			} else {
				setvalues(panel);
				saveToconfig(panel);
				// setvalues(panel);
				frame.dispose();
				runner1.callSanity();
			}
		} else {
			if (URL.isEmpty() || UserName.isEmpty() || Password.isEmpty() || adminURL1.isEmpty()
					|| username_admin.isEmpty() || password_admin.isEmpty() || managerURL.isEmpty()
					|| username_manager.isEmpty() || password_manager.isEmpty()) {
				String message = "Please fill all details to run sanity";
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
			} else {
				setvalues(panel);
				saveToconfig(panel);
				// setvalues(panel);
				frame.dispose();
				runner1.callSanity();
			}
		}
	}

	private void setvalues(JPanel panel) {

		try {

			URL = portalurltext.getText().trim();
			if (saml.isSelected()) {
				SAML = true;
				UserName = "";
				Password = "";
			} else {
				SAML = false;
				UserName = portalusernametext.getText().trim();
				Password = new String(portalpasswordtext.getPassword());
			}
			adminURL1 = adminurltext1.getText().trim();
			username_admin = adminusernametext.getText().trim();
			password_admin = new String(adminpasswordtext.getPassword());
			managerURL = managerurltext.getText().trim();
			username_manager = managerusernametext.getText().trim();
			password_manager = new String(managerpasswordtext.getPassword());
			browser = browsercomboBox.getSelectedItem().toString();

			// frame.dispose();
			// frame.setVisible(false);
			// JOptionPane.showMessageDialog(null, "Your changes have been saved
			// Successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Not able to read test values");
		}
	}

	private void saveToconfig(JPanel panel) {

		try {

			OutputStream output = new FileOutputStream(
					System.getProperty("user.dir") + "\\Webdriver\\config.properties");

			prop.setProperty("Portalurl", URL);
			prop.setProperty("PortalUserName", UserName);
			prop.setProperty("PortalPassword", Password);
			prop.setProperty("Serverurl1", adminURL1);
			prop.setProperty("ServerAdminUsername", username_admin);
			prop.setProperty("ServerAdminPassword", password_admin);
			prop.setProperty("Servermanagerurl", managerURL);
			prop.setProperty("ServerManagerUsername", username_manager);
			prop.setProperty("ServerManagerPassword", password_manager);
			prop.setProperty("Browser", browsercomboBox.getSelectedItem().toString());
			prop.store(output, null);
			// frame.dispose();
			// frame.setVisible(false);input
			// JOptionPane.showMessageDialog(null, "Your changes have been saved
			// Successfully!");
		} catch (Exception e) {
			e.printStackTrace();
			// JOptionPane.showMessageDialog(null, "Your changes could not be saved.");
		}
	}

	// xPad, yPad
}

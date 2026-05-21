package com.automation.testcases;

//this is main class to create jar file
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

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
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import org.openqa.selenium.remote.RemoteWebDriver;

public class ConfigControl3 extends JFrame {
	public static FileInputStream input = null;
	public static JProgressBar pbar;

	static int min = 0;

	
	
	static int max = 100;
	// public static Xls_Reader xls = null;
	private static JFrame frame;
	public static DialogControl cd1;
	public static Properties prop;
	public static int no_of_site = 1;
	static JTabbedPane tabbedPane = new JTabbedPane();
	// public static final JPanel panel;
	public static String projectName = "Enterprise Deployment Check Test";
	private static final String sheetName = "Configuration";
	static String frameTitle = "Enterprise Deployment Check";
	private static JTextField portalurltext;
	private JLabel portalurllabel;
	public static RemoteWebDriver driver;
	private JLabel portalusernamelabel;
	private static JLabel addsite;
	private JLabel portalpasswordlabel;
	private JLabel managerusernamelabel;
	private JLabel managerurllabel;
	private JLabel managerpasswordlabel;
	private JLabel header;
	private static JLabel adminurllabel1;
	private static JLabel adminurllabel2;
	private static JLabel adminurllabel3;
	private static JLabel adminurllabel4;
	private static JLabel adminurllabel5;
	private static JLabel adminurllabel6;

	private JLabel federatedlabel;
	private static JLabel partition1, partition2;
	private static JRadioButton federatedradio1;
	private static JRadioButton nonfederatedradio1;
	private static JRadioButton federatedradio2;
	private static JRadioButton nonfederatedradio2;
	private static JRadioButton federatedradio3;
	private static JRadioButton nonfederatedradio3;
	private static JRadioButton federatedradio4;
	private static JRadioButton nonfederatedradio4;
	private static JRadioButton federatedradio5;
	private static JRadioButton nonfederatedradio5;
	private static JRadioButton federatedradio6;
	private static JRadioButton nonfederatedradio6;
	private static JCheckBox saml;

	public static String extentReportFilePath = null;

	public static JCheckBox loginportal, loginmanager, loginserver, groupfunctionality, webappbuilder, organization,
			scenelayer, feature, tile, map, content, hosting;
	private JTextField portalusernametext;
	private JPasswordField portalpasswordtext;
	private JTextField managerusernametext;
	private JTextField managerurltext;
	private JPasswordField managerpasswordtext;
	private static JTextField adminurltext1;
	private static JTextField adminurltext2;
	private static JTextField adminurltext3;
	private static JTextField adminurltext4;
	private static JTextField adminurltext5;
	private static JTextField adminurltext6;
	private static JLabel adminusernamelabel1;
	private static JLabel adminpasswordlabel1;
	private static JTextField adminusernametext1;
	private static JPasswordField adminpasswordtext1;
	private static JLabel adminusernamelabel2;
	private static JLabel adminpasswordlabel2;
	private static JTextField adminusernametext2;
	private static JPasswordField adminpasswordtext2;
	private static JLabel adminusernamelabel3;
	private static JLabel adminpasswordlabel3;
	private static JTextField adminusernametext3;
	private static JPasswordField adminpasswordtext3;
	private static JLabel adminusernamelabel4;
	private static JLabel adminpasswordlabel4;
	private static JTextField adminusernametext4;
	private static JPasswordField adminpasswordtext4;
	private static JLabel adminusernamelabel5;
	private static JLabel adminpasswordlabel5;
	private static JTextField adminusernametext5;
	private static JPasswordField adminpasswordtext5;
	private static JLabel adminusernamelabel6;
	private static JLabel adminpasswordlabel6;
	private static JTextField adminusernametext6;
	private static JPasswordField adminpasswordtext6;
	private static JButton saveBtn;

	private JButton clearBtn;
	public static String URL;

	// public static String username_admin;
	// public static String password_admin;
	// public static String managerURL;
	public static String UserName;
	public static String Password;
	public static boolean SAML = false;

	public static boolean stop = false;
	public static boolean submitdefect = true;

	// public static String username_manager;
	// public static String password_manager;
	public static String browser;
	static JScrollPane scroll = new JScrollPane();
	static SpringLayout layout = new SpringLayout();
	final static JPanel panel = new JPanel();
	// final static JPanel panel1 = new JPanel();
	// final static JPanel panel2 = new JPanel();
	// final static JPanel panel3 = new JPanel();
	// final static JPanel panel4 = new JPanel();
	// final static JPanel panel5 = new JPanel();
	// final static JPanel panel6 = new JPanel();
	// final static JPanel panel7 = new JPanel();
	// final static JPanel panel8 = new JPanel();
	private static JComboBox<String> browsercomboBox;
	private static JLabel browserlabel;
	private static JComboBox<String> serverrolecomboBox1;
	private static JLabel serverrolelabel1;
	private static JComboBox<String> serverrolecomboBox2;
	private static JLabel serverrolelabel2;
	private static JComboBox<String> serverrolecomboBox3;
	private static JLabel serverrolelabel3;
	private static JComboBox<String> serverrolecomboBox4;
	private static JLabel serverrolelabel4;
	private static JComboBox<String> serverrolecomboBox5;
	private static JLabel serverrolelabel5;
	private static JComboBox<String> serverrolecomboBox6;
	static JLabel serverrolelabel6;
	String Portalurl = "";
	String PortalUserName = "";
	String PortalPassword = "";
	private String[] serverRolevalues = new String[] { "Hosting", "Imagery", "Geo Analytics", "Raster Analysis",
			"Notebook", "Mission", "Workflow Manager", "Knowledge Srever" };
//variables to read config file and display on UI

	String[] Serverurl = new String[6];
	String[] Servermanagerurl = new String[6];
	String[] ServerAdminUsername = new String[6];
	String[] ServerAdminPassword = new String[6];
	String[] ServerManagerUsername = new String[6];;
	String[] ServerManagerPassword = new String[6];

//variables to read values from UI and store in config file	
	public static String[] adminURL = new String[] { "", "", "", "", "", "" };
	public static String[] adminUsername = new String[] { "", "", "", "", "", "" };
	public static String[] adminPassword = new String[] { "", "", "", "", "", "" };
	public static String[] managerURL = new String[] { "", "", "", "", "", "" };
	public static String[] managerUsername = new String[] { "", "", "", "", "", "" };
	public static String[] managerPassword = new String[] { "", "", "", "", "", "" };
	public static String[] serverRole = new String[] { "", "", "", "", "", "" };
	// public static boolean[] federated = new boolean[] { false, false, false,
	// false, false, false };
	public static boolean[] federated = new boolean[] { true, true, true, true, true, true };

	String Browser = "";

	static int WIDTH = 750;
	static int HEIGHT = 620;
	static int textlength = 250;
	static int labelstart = 50;
	static int textstart = 255;
	static int distance = 10;
	static int distance1 = 2;
	String Portalurltooltip = "e.g. https://myportal.esri.com/portal";
	String Serverurltooltip = "e.g. https://myportal.esri.com/server";
	String Managerurltooltip = "e.g. https://myportal.esri.com/server/manager";
	static int hl = 25;
	static int h2 = 17;
	static int wl = 200;
	public static boolean flagloginadmin = true;
	public static boolean flagloginmanager = true;
	public static boolean flagloginportal = true;
	public static boolean flagtilelayer = true;
	public static boolean flagfeaturelayer = true;
	public static boolean flaggroup = true;
	public static boolean flagorganization = true;
	public static boolean flagscenelayer = true;
	public static boolean scenecreated = false;
	// public static boolean exe = false;
	public static boolean flagmap = true;
	public static boolean flagwebappbuilder = true;
	public static boolean flagcontent = true;
	public static boolean flaghosting = true;
	private static JRadioButton runalltestradio;
	private static JRadioButton selecttestradio;

	/// private static JLabel selecttest;

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
		// frame = new ConfigControl3();
		frame = new ConfigControl3();
		((ConfigControl3) frame).initialize1();
		frame.setTitle(frameTitle);
		frame.setBounds(100, 100, WIDTH, HEIGHT);

		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setResizable(false);

		frame.setLocationRelativeTo(null);

		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		frame.pack();
		// frame.add(newContentPane, BorderLayout.CENTER);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Create the application.
	 */
	public ConfigControl3() {
		initialize();
	}

	private void initialize() {
		try {
			if (input != null) {
				readfromconfigdisplay();

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize1() {

		// exe = true;
		try {

			panel.setLayout(layout);

			panel.setBackground(Color.GRAY);

			// javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			// javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			// tabbedPane.setLayout(layout);
			UIManager.put("CheckBox.disabledText", Color.BLACK);
			UIManager.put("ToolTip.background", new ColorUIResource(255, 247, 200));
			UIManager.put("ToolTip.foreground", Color.BLUE);
			UIManager.put("ToolTip.font", new Font("Verdana", Font.BOLD, 13));
			scroll = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scroll.setPreferredSize(new Dimension(WIDTH, HEIGHT));
			// scroll.
			// tabbedPane is to add tab in the UI
			tabbedPane.addTab("Enterprise Parameters", null, scroll, "Enterprise Parameters");
			tabbedPane.setFont(new Font("Verdana", Font.BOLD, 15));
			tabbedPane.setBorder(BorderFactory.createLineBorder(Color.black, 5));

			header = new JLabel("Enter all required information in the form below...");
			header.setFont(new Font("Verdana", Font.BOLD, 14));

			panel.add(header);

			portalurllabel = new JLabel("Portal URL*", JLabel.RIGHT);// make label and assign text in 1 line
			layout.getConstraints(portalurllabel).setHeight(Spring.constant(hl));
			layout.getConstraints(portalurllabel).setWidth(Spring.constant(wl));
			portalurllabel.setFont(new Font("Verdana", Font.PLAIN, 14));

			portalurltext = new JTextField(Portalurl, textlength);

			portalurltext.setToolTipText(Portalurltooltip);
			portalurltext.setBackground(Color.LIGHT_GRAY);
			layout.getConstraints(portalurltext).setHeight(Spring.constant(hl));
			// portalurltext.addActionListener(this);
			portalusernamelabel = new JLabel("Admin Username*", JLabel.RIGHT);
			portalusernamelabel.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(portalusernamelabel).setHeight(Spring.constant(hl));
			layout.getConstraints(portalusernamelabel).setWidth(Spring.constant(wl));
			// make label and assign text in 1 line
			portalusernametext = new JTextField(PortalUserName, textlength);
			portalusernametext.setBackground(Color.LIGHT_GRAY);
			layout.getConstraints(portalusernametext).setHeight(Spring.constant(hl));
			portalpasswordlabel = new JLabel("Admin Password*", JLabel.RIGHT);
			portalpasswordlabel.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(portalpasswordlabel).setHeight(Spring.constant(hl));
			layout.getConstraints(portalpasswordlabel).setWidth(Spring.constant(wl));// make label and assign text in 1
			// line
			portalpasswordtext = new JPasswordField(PortalPassword, textlength);
			portalpasswordtext.setBackground(Color.LIGHT_GRAY);
			layout.getConstraints(portalpasswordtext).setHeight(Spring.constant(hl));
			// Manger section
			/*
			 * managerurllabel = new JLabel("Server Manager URL", JLabel.RIGHT);
			 * managerurllabel.setFont(new Font("Verdana", Font.PLAIN, 14));
			 * layout.getConstraints(managerurllabel).setHeight(Spring.constant(hl));
			 * layout.getConstraints(managerurllabel).setWidth(Spring.constant(wl));
			 * managerurltext = new JTextField(Servermanagerurl, textlength);
			 * managerurltext.setBackground(Color.LIGHT_GRAY);
			 * managerurltext.setToolTipText(Managerurltooltip);
			 * layout.getConstraints(managerurltext).setHeight(Spring.constant(hl));
			 * managerusernamelabel = new JLabel("Server Manager Username", JLabel.RIGHT);
			 * managerusernamelabel.setFont(new Font("Verdana", Font.PLAIN, 14));
			 * layout.getConstraints(managerusernamelabel).setHeight(Spring.constant(hl));
			 * layout.getConstraints(managerusernamelabel).setWidth(Spring.constant(wl));
			 * managerusernametext = new JTextField(ServerManagerUsername, textlength);
			 * managerusernametext.setBackground(Color.LIGHT_GRAY);
			 * layout.getConstraints(managerusernametext).setHeight(Spring.constant(hl));
			 * managerpasswordlabel = new JLabel("Server Manager Password", JLabel.RIGHT);
			 * managerpasswordlabel.setFont(new Font("Verdana", Font.PLAIN, 14));
			 * layout.getConstraints(managerpasswordlabel).setHeight(Spring.constant(hl));
			 * layout.getConstraints(managerpasswordlabel).setWidth(Spring.constant(wl));
			 * managerpasswordtext = new JPasswordField(ServerManagerPassword, textlength);
			 * managerpasswordtext.setBackground(Color.LIGHT_GRAY);
			 * layout.getConstraints(managerpasswordtext).setHeight(Spring.constant(hl));
			 */
			partition1 = new JLabel(
					"-----------------------------------------------------------------------------------------------------------------",
					JLabel.LEFT);
			partition1.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(partition1).setHeight(Spring.constant(hl));
			layout.getConstraints(partition1).setWidth(Spring.constant(WIDTH));
			partition2 = new JLabel(
					"-----------------------------------------------------------------------------------------------------------------",
					JLabel.LEFT);
			partition2.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(partition2).setHeight(Spring.constant(hl));
			layout.getConstraints(partition2).setWidth(Spring.constant(WIDTH));
			federatedlabel = new JLabel("Is the Enterprise*", JLabel.RIGHT);
			federatedlabel.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(federatedlabel).setHeight(Spring.constant(hl));
			layout.getConstraints(federatedlabel).setWidth(Spring.constant(wl));
			federatedradio1 = new JRadioButton("federated", true);
			federatedradio1.setFont(new Font("Verdana", Font.PLAIN, 14));
			nonfederatedradio1 = new JRadioButton("non federated");
			nonfederatedradio1.setFont(new Font("Verdana", Font.PLAIN, 14));
			nonfederatedradio1.setBackground(Color.GRAY);
			federatedradio1.setBackground(Color.GRAY);
			ButtonGroup group1 = new ButtonGroup();
			group1.add(federatedradio1);
			group1.add(nonfederatedradio1);
			federatedradio2 = new JRadioButton("federated", true);
			federatedradio2.setFont(new Font("Verdana", Font.PLAIN, 14));
			nonfederatedradio2 = new JRadioButton("non federated");
			nonfederatedradio2.setFont(new Font("Verdana", Font.PLAIN, 14));
			nonfederatedradio2.setBackground(Color.GRAY);
			federatedradio2.setBackground(Color.GRAY);
			ButtonGroup group2 = new ButtonGroup();
			group2.add(federatedradio2);
			group2.add(nonfederatedradio2);
			federatedradio3 = new JRadioButton("federated", true);
			federatedradio3.setFont(new Font("Verdana", Font.PLAIN, 14));
			nonfederatedradio3 = new JRadioButton("non federated");
			nonfederatedradio3.setFont(new Font("Verdana", Font.PLAIN, 14));
			nonfederatedradio3.setBackground(Color.GRAY);
			federatedradio3.setBackground(Color.GRAY);
			ButtonGroup group3 = new ButtonGroup();
			group3.add(federatedradio3);
			group3.add(nonfederatedradio3);
			federatedradio4 = new JRadioButton("federated", true);
			federatedradio4.setFont(new Font("Verdana", Font.PLAIN, 14));
			nonfederatedradio4 = new JRadioButton("non federated");
			nonfederatedradio4.setFont(new Font("Verdana", Font.PLAIN, 14));
			nonfederatedradio4.setBackground(Color.GRAY);
			federatedradio4.setBackground(Color.GRAY);
			ButtonGroup group4 = new ButtonGroup();
			group4.add(federatedradio4);
			group4.add(nonfederatedradio4);
			federatedradio5 = new JRadioButton("federated", true);
			federatedradio5.setFont(new Font("Verdana", Font.PLAIN, 14));
			nonfederatedradio5 = new JRadioButton("non federated");
			nonfederatedradio5.setFont(new Font("Verdana", Font.PLAIN, 14));
			nonfederatedradio5.setBackground(Color.GRAY);
			federatedradio5.setBackground(Color.GRAY);
			ButtonGroup group5 = new ButtonGroup();
			group5.add(federatedradio5);
			group5.add(nonfederatedradio5);
			federatedradio6 = new JRadioButton("federated", true);
			federatedradio6.setFont(new Font("Verdana", Font.PLAIN, 14));
			nonfederatedradio6 = new JRadioButton("non federated");
			nonfederatedradio6.setFont(new Font("Verdana", Font.PLAIN, 14));
			nonfederatedradio6.setBackground(Color.GRAY);
			federatedradio6.setBackground(Color.GRAY);
			ButtonGroup group6 = new ButtonGroup();
			group6.add(federatedradio6);
			group6.add(nonfederatedradio6);
			serverrolelabel1 = new JLabel("Server Role", JLabel.RIGHT);// make label and assign text in 1 line
			layout.getConstraints(serverrolelabel1).setHeight(Spring.constant(hl));
			layout.getConstraints(serverrolelabel1).setWidth(Spring.constant(wl));
			serverrolelabel1.setFont(new Font("Verdana", Font.PLAIN, 14));
			serverrolecomboBox1 = new JComboBox<String>(serverRolevalues);
			layout.getConstraints(serverrolecomboBox1).setWidth(Spring.constant(wl));
			serverrolecomboBox1.setBackground(Color.LIGHT_GRAY);
			serverrolecomboBox1.setSelectedIndex(0);
			// serverrolecomboBox1.addActionListener();
			// serverrolecomboBox1.addItem("Hosting");

			layout.getConstraints(serverrolecomboBox1).setHeight(Spring.constant(hl));
			serverrolelabel2 = new JLabel("Server Role", JLabel.RIGHT);// make label and assign text in 1 line
			layout.getConstraints(serverrolelabel2).setHeight(Spring.constant(hl));
			layout.getConstraints(serverrolelabel2).setWidth(Spring.constant(wl));
			serverrolelabel2.setFont(new Font("Verdana", Font.PLAIN, 14));
			serverrolecomboBox2 = new JComboBox<String>(serverRolevalues);
			layout.getConstraints(serverrolecomboBox2).setWidth(Spring.constant(wl));
			serverrolecomboBox2.setBackground(Color.LIGHT_GRAY);
			serverrolecomboBox2.setSelectedIndex(0);
			layout.getConstraints(serverrolecomboBox2).setHeight(Spring.constant(hl));
			serverrolelabel3 = new JLabel("Server Role", JLabel.RIGHT);// make label and assign text in 1 line
			layout.getConstraints(serverrolelabel3).setHeight(Spring.constant(hl));
			layout.getConstraints(serverrolelabel3).setWidth(Spring.constant(wl));
			serverrolelabel3.setFont(new Font("Verdana", Font.PLAIN, 14));
			serverrolecomboBox3 = new JComboBox<String>(serverRolevalues);
			layout.getConstraints(serverrolecomboBox3).setWidth(Spring.constant(wl));
			serverrolecomboBox3.setBackground(Color.LIGHT_GRAY);
			serverrolecomboBox3.setSelectedIndex(0);
			layout.getConstraints(serverrolecomboBox3).setHeight(Spring.constant(hl));
			serverrolelabel4 = new JLabel("Server Role", JLabel.RIGHT);// make label and assign text in 1 line
			layout.getConstraints(serverrolelabel4).setHeight(Spring.constant(hl));
			layout.getConstraints(serverrolelabel4).setWidth(Spring.constant(wl));
			serverrolelabel4.setFont(new Font("Verdana", Font.PLAIN, 14));
			serverrolecomboBox4 = new JComboBox<String>(serverRolevalues);
			layout.getConstraints(serverrolecomboBox4).setWidth(Spring.constant(wl));
			serverrolecomboBox4.setBackground(Color.LIGHT_GRAY);
			serverrolecomboBox4.setSelectedIndex(0);
			layout.getConstraints(serverrolecomboBox4).setHeight(Spring.constant(hl));
			serverrolelabel5 = new JLabel("Server Role", JLabel.RIGHT);// make label and assign text in 1 line
			layout.getConstraints(serverrolelabel5).setHeight(Spring.constant(hl));
			layout.getConstraints(serverrolelabel5).setWidth(Spring.constant(wl));
			serverrolelabel5.setFont(new Font("Verdana", Font.PLAIN, 14));
			serverrolecomboBox5 = new JComboBox<String>(serverRolevalues);
			layout.getConstraints(serverrolecomboBox5).setWidth(Spring.constant(wl));
			serverrolecomboBox5.setBackground(Color.LIGHT_GRAY);
			serverrolecomboBox5.setSelectedIndex(0);
			layout.getConstraints(serverrolecomboBox5).setHeight(Spring.constant(hl));
			serverrolelabel6 = new JLabel("Server Role", JLabel.RIGHT);// make label and assign text in 1 line
			layout.getConstraints(serverrolelabel6).setHeight(Spring.constant(hl));
			layout.getConstraints(serverrolelabel6).setWidth(Spring.constant(wl));
			serverrolelabel6.setFont(new Font("Verdana", Font.PLAIN, 14));
			serverrolecomboBox6 = new JComboBox<String>(serverRolevalues);
			layout.getConstraints(serverrolecomboBox6).setWidth(Spring.constant(wl));
			serverrolecomboBox6.setBackground(Color.LIGHT_GRAY);
			serverrolecomboBox6.setSelectedIndex(0);
			layout.getConstraints(serverrolecomboBox6).setHeight(Spring.constant(hl));
			addsite = new JLabel();
			// <p style='text-align:left;'>
			addsite.setText("<HTML><U>Click to add another Server(max 6)</U></HTML>");
			// addsite.setBorderPainted(false);
			addsite.setOpaque(false);
			addsite.setBackground(Color.GRAY);
			addsite.setFont(new Font("Verdana", Font.PLAIN, 12));
			addsite.setForeground(Color.BLUE);
			addsite.setHorizontalAlignment(SwingConstants.LEFT);
			layout.getConstraints(addsite).setHeight(Spring.constant(hl));
			// layout.getConstraints(addsite).setWidth(Spring.constant(wl));
			adminurllabel1 = new JLabel("Server URL", JLabel.RIGHT);
			adminurllabel1.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminurllabel1).setHeight(Spring.constant(hl));
			layout.getConstraints(adminurllabel1).setWidth(Spring.constant(wl));
			adminurltext1 = new JTextField(Serverurl[0], textlength);
			adminurltext1.setBackground(Color.LIGHT_GRAY);
			adminurltext1.setToolTipText(Serverurltooltip);
			layout.getConstraints(adminurltext1).setHeight(Spring.constant(25));
			adminurllabel2 = new JLabel("Server URL 2", JLabel.RIGHT);
			adminurllabel2.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminurllabel2).setHeight(Spring.constant(hl));
			layout.getConstraints(adminurllabel2).setWidth(Spring.constant(wl));
			adminurltext2 = new JTextField(Serverurl[1], textlength);
			adminurltext2.setBackground(Color.LIGHT_GRAY);
			adminurltext2.setToolTipText(Serverurltooltip);
			layout.getConstraints(adminurltext2).setHeight(Spring.constant(25));
			adminurllabel3 = new JLabel("Server URL 3", JLabel.RIGHT);
			adminurllabel3.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminurllabel3).setHeight(Spring.constant(hl));
			layout.getConstraints(adminurllabel3).setWidth(Spring.constant(wl));
			adminurltext3 = new JTextField(Serverurl[2], textlength);
			adminurltext3.setBackground(Color.LIGHT_GRAY);
			adminurltext3.setToolTipText(Serverurltooltip);
			layout.getConstraints(adminurltext3).setHeight(Spring.constant(25));
			adminurllabel4 = new JLabel("Server URL 4", JLabel.RIGHT);
			adminurllabel4.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminurllabel4).setHeight(Spring.constant(hl));
			layout.getConstraints(adminurllabel4).setWidth(Spring.constant(wl));
			adminurltext4 = new JTextField(Serverurl[3], textlength);
			adminurltext4.setBackground(Color.LIGHT_GRAY);
			adminurltext4.setToolTipText(Serverurltooltip);
			layout.getConstraints(adminurltext4).setHeight(Spring.constant(25));
			adminurllabel5 = new JLabel("Server URL 5", JLabel.RIGHT);
			adminurllabel5.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminurllabel5).setHeight(Spring.constant(hl));
			layout.getConstraints(adminurllabel5).setWidth(Spring.constant(wl));
			adminurltext5 = new JTextField(Serverurl[4], textlength);
			adminurltext5.setBackground(Color.LIGHT_GRAY);
			adminurltext5.setToolTipText(Serverurltooltip);
			layout.getConstraints(adminurltext5).setHeight(Spring.constant(25));
			adminurllabel6 = new JLabel("Server URL 6", JLabel.RIGHT);
			adminurllabel6.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminurllabel6).setHeight(Spring.constant(hl));
			layout.getConstraints(adminurllabel6).setWidth(Spring.constant(wl));
			adminurltext6 = new JTextField(Serverurl[5], textlength);
			adminurltext6.setBackground(Color.LIGHT_GRAY);
			adminurltext6.setToolTipText(Serverurltooltip);
			layout.getConstraints(adminurltext6).setHeight(Spring.constant(25));
			adminusernamelabel1 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
			adminusernamelabel1.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminusernamelabel1).setHeight(Spring.constant(hl));// line
			layout.getConstraints(adminusernamelabel1).setWidth(Spring.constant(wl));
			adminusernametext1 = new JTextField(ServerAdminUsername[0], textlength);
			adminusernametext1.setBackground(Color.LIGHT_GRAY);
			layout.getConstraints(adminusernametext1).setHeight(Spring.constant(25));
			adminpasswordlabel1 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
			adminpasswordlabel1.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminpasswordlabel1).setHeight(Spring.constant(hl));// line
			layout.getConstraints(adminpasswordlabel1).setWidth(Spring.constant(wl));
			adminpasswordtext1 = new JPasswordField(ServerAdminPassword[0], textlength);
			adminpasswordtext1.setBackground(Color.LIGHT_GRAY);
			layout.getConstraints(adminpasswordtext1).setHeight(Spring.constant(25));

			adminusernamelabel2 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
			adminusernamelabel2.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminusernamelabel2).setHeight(Spring.constant(hl));// line
			layout.getConstraints(adminusernamelabel2).setWidth(Spring.constant(wl));
			adminusernametext2 = new JTextField(ServerAdminUsername[1], textlength);
			adminusernametext2.setBackground(Color.LIGHT_GRAY);
			layout.getConstraints(adminusernametext2).setHeight(Spring.constant(25));
			adminpasswordlabel2 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
			adminpasswordlabel2.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminpasswordlabel2).setHeight(Spring.constant(hl));// line
			layout.getConstraints(adminpasswordlabel2).setWidth(Spring.constant(wl));
			adminpasswordtext2 = new JPasswordField(ServerAdminPassword[1], textlength);
			adminpasswordtext2.setBackground(Color.LIGHT_GRAY);
			layout.getConstraints(adminpasswordtext2).setHeight(Spring.constant(hl));

			adminusernamelabel3 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
			adminusernamelabel3.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminusernamelabel3).setHeight(Spring.constant(hl));// line
			layout.getConstraints(adminusernamelabel3).setWidth(Spring.constant(wl));
			adminusernametext3 = new JTextField(ServerAdminUsername[2], textlength);
			adminusernametext3.setBackground(Color.LIGHT_GRAY);
			layout.getConstraints(adminusernametext3).setHeight(Spring.constant(25));
			adminpasswordlabel3 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
			adminpasswordlabel3.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminpasswordlabel3).setHeight(Spring.constant(hl));// line
			layout.getConstraints(adminpasswordlabel3).setWidth(Spring.constant(wl));
			adminpasswordtext3 = new JPasswordField(ServerAdminPassword[2], textlength);
			adminpasswordtext3.setBackground(Color.LIGHT_GRAY);
			layout.getConstraints(adminpasswordtext3).setHeight(Spring.constant(25));

			adminusernamelabel4 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
			adminusernamelabel4.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminusernamelabel4).setHeight(Spring.constant(hl));// line
			layout.getConstraints(adminusernamelabel4).setWidth(Spring.constant(wl));
			adminusernametext4 = new JTextField(ServerAdminUsername[3], textlength);
			adminusernametext4.setBackground(Color.LIGHT_GRAY);
			layout.getConstraints(adminusernametext4).setHeight(Spring.constant(hl));
			adminpasswordlabel4 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
			adminpasswordlabel4.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminpasswordlabel4).setHeight(Spring.constant(hl));// line
			layout.getConstraints(adminpasswordlabel4).setWidth(Spring.constant(wl));
			adminpasswordtext4 = new JPasswordField(ServerAdminPassword[3], textlength);
			adminpasswordtext4.setBackground(Color.LIGHT_GRAY);
			layout.getConstraints(adminpasswordtext4).setHeight(Spring.constant(hl));

			adminusernamelabel5 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
			adminusernamelabel5.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminusernamelabel5).setHeight(Spring.constant(hl));// line
			layout.getConstraints(adminusernamelabel5).setWidth(Spring.constant(wl));
			adminusernametext5 = new JTextField(ServerAdminUsername[4], textlength);
			adminusernametext5.setBackground(Color.LIGHT_GRAY);
			layout.getConstraints(adminusernametext5).setHeight(Spring.constant(hl));
			adminpasswordlabel5 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
			adminpasswordlabel5.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminpasswordlabel5).setHeight(Spring.constant(hl));// line
			layout.getConstraints(adminpasswordlabel5).setWidth(Spring.constant(wl));
			adminpasswordtext5 = new JPasswordField(ServerAdminPassword[4], textlength);
			adminpasswordtext5.setBackground(Color.LIGHT_GRAY);
			layout.getConstraints(adminpasswordtext5).setHeight(Spring.constant(hl));

			adminusernamelabel6 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
			adminusernamelabel6.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminusernamelabel6).setHeight(Spring.constant(hl));// line
			layout.getConstraints(adminusernamelabel6).setWidth(Spring.constant(wl));
			adminusernametext6 = new JTextField(ServerAdminUsername[5], textlength);
			adminusernametext6.setBackground(Color.LIGHT_GRAY);
			layout.getConstraints(adminusernametext6).setHeight(Spring.constant(hl));
			adminpasswordlabel6 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
			adminpasswordlabel6.setFont(new Font("Verdana", Font.PLAIN, 14));
			layout.getConstraints(adminpasswordlabel6).setHeight(Spring.constant(hl));// line
			layout.getConstraints(adminpasswordlabel6).setWidth(Spring.constant(wl));
			adminpasswordtext6 = new JPasswordField(ServerAdminPassword[5], textlength);
			adminpasswordtext6.setBackground(Color.LIGHT_GRAY);
			layout.getConstraints(adminpasswordtext6).setHeight(Spring.constant(hl));
			browserlabel = new JLabel("Which browser do you use?", JLabel.RIGHT);// make label and assign text in 1 line

			layout.getConstraints(browserlabel).setHeight(Spring.constant(hl));
			layout.getConstraints(browserlabel).setWidth(Spring.constant(wl));
			browserlabel.setFont(new Font("Verdana", Font.PLAIN, 14));
			browsercomboBox = new JComboBox<String>();
			layout.getConstraints(browsercomboBox).setWidth(Spring.constant(wl));
			browsercomboBox.setBackground(Color.LIGHT_GRAY);
			browsercomboBox.addItem("Chrome");
			browsercomboBox.addItem("Firefox");
			browsercomboBox.addItem("Edge");
			layout.getConstraints(browsercomboBox).setHeight(Spring.constant(hl));

			/*
			 * selecttest = new JLabel("Select Tests to Run", JLabel.RIGHT);// make label
			 * and assign text in 1 line
			 * 
			 * layout.getConstraints(selecttest).setHeight(Spring.constant(hl));
			 * layout.getConstraints(selecttest).setWidth(Spring.constant(wl));
			 * selecttest.setFont(new Font("Verdana", Font.PLAIN, 14));
			 */
			saveBtn = new JButton("Save and Run Tool");
			saveBtn.setBackground(Color.CYAN);
			saveBtn.setFont(new Font("Verdana", Font.PLAIN, 16));
			clearBtn = new JButton("Clear");
			clearBtn.setBackground(Color.blue);
			clearBtn.setFont(new Font("Verdana", Font.PLAIN, 14));
			saml = new JCheckBox("Check to login using SAML");
			saml.setBackground(Color.GRAY);
			saml.setBorderPainted(false);
			saml.setFont(new Font("Verdana", Font.PLAIN, 14));
			runalltestradio = new JRadioButton("Run all tests", false);
			runalltestradio.setFont(new Font("Verdana", Font.PLAIN, 14));
			// (default tests already selected and disabled)
			selecttestradio = new JRadioButton("Select Tests to Run", true);
			selecttestradio.setFont(new Font("Verdana", Font.PLAIN, 14));

			selecttestradio.setBackground(Color.GRAY);
			runalltestradio.setBackground(Color.GRAY);
			ButtonGroup group = new ButtonGroup();
			group.add(runalltestradio);
			group.add(selecttestradio);

			loginportal = new JCheckBox("Login to Portal");
			loginportal.setBackground(Color.GRAY);
			loginportal.setBorderPainted(false);
			loginportal.setFont(new Font("Verdana", Font.PLAIN, 14));
			loginmanager = new JCheckBox("Login to Server Manager");
			loginmanager.setBackground(Color.GRAY);
			loginmanager.setBorderPainted(false);
			loginmanager.setFont(new Font("Verdana", Font.PLAIN, 14));
			loginserver = new JCheckBox("Login to server Admin");
			loginserver.setBackground(Color.GRAY);
			loginserver.setBorderPainted(false);
			loginserver.setFont(new Font("Verdana", Font.PLAIN, 14));
			groupfunctionality = new JCheckBox("Create Group/Member and share content");
			groupfunctionality.setBackground(Color.GRAY);
			groupfunctionality.setBorderPainted(false);
			groupfunctionality.setFont(new Font("Verdana", Font.PLAIN, 14));
			webappbuilder = new JCheckBox("Create Webapp Builder App");
			webappbuilder.setBackground(Color.GRAY);
			webappbuilder.setBorderPainted(false);
			webappbuilder.setFont(new Font("Verdana", Font.PLAIN, 14));
			organization = new JCheckBox("Check Organization functionality");
			organization.setBackground(Color.GRAY);
			organization.setBorderPainted(false);
			organization.setFont(new Font("Verdana", Font.PLAIN, 14));
			scenelayer = new JCheckBox("Check SceneLayer functionality(11.1, 11.2, 11.3 & 11.4 only)");
			scenelayer.setBackground(Color.GRAY);
			scenelayer.setBorderPainted(false);
			scenelayer.setFont(new Font("Verdana", Font.PLAIN, 14));
			feature = new JCheckBox("Create Feature Layer");
			feature.setBackground(Color.GRAY);
			feature.setBorderPainted(false);
			feature.setFont(new Font("Verdana", Font.PLAIN, 14));
			tile = new JCheckBox("Create Tile Layer");
			tile.setBackground(Color.GRAY);
			tile.setBorderPainted(false);
			tile.setFont(new Font("Verdana", Font.PLAIN, 14));
			map = new JCheckBox("Create WebMap");
			map.setBackground(Color.GRAY);
			map.setBorderPainted(false);
			map.setFont(new Font("Verdana", Font.PLAIN, 14));
			content = new JCheckBox("Create Folder");
			content.setBackground(Color.GRAY);
			content.setBorderPainted(false);
			content.setFont(new Font("Verdana", Font.PLAIN, 14));

			hosting = new JCheckBox("Validate Hosting Server");
			hosting.setBackground(Color.GRAY);
			hosting.setBorderPainted(false);
			hosting.setFont(new Font("Verdana", Font.PLAIN, 14));

			panel.add(portalurllabel);
			panel.add(portalurltext);
			panel.add(saml);

			panel.add(portalusernamelabel);
			panel.add(portalusernametext);
			panel.add(portalpasswordlabel);
			panel.add(portalpasswordtext);
			panel.add(partition1);
			panel.add(partition2);
			panel.add(federatedradio1);
			panel.add(nonfederatedradio1);
			/*
			 * panel.add(managerurllabel); panel.add(managerurltext);
			 * panel.add(managerusernamelabel); panel.add(managerusernametext);
			 * panel.add(managerpasswordlabel); panel.add(managerpasswordtext);
			 */
			panel.add(addsite);
			panel.add(adminurllabel1);
			panel.add(adminurltext1);
			panel.add(serverrolelabel1);
			panel.add(serverrolecomboBox1);
			panel.add(adminusernamelabel1);
			panel.add(adminusernametext1);
			panel.add(adminpasswordtext1);
			panel.add(adminpasswordlabel1);

			panel.add(browserlabel);
			panel.add(browsercomboBox);
			panel.add(runalltestradio);
			panel.add(selecttestradio);
			panel.add(loginportal);
			panel.add(loginmanager);
			panel.add(loginserver);
			panel.add(hosting);
			panel.add(groupfunctionality);
			panel.add(webappbuilder);
			panel.add(organization);
			panel.add(scenelayer);
			panel.add(feature);
			panel.add(tile);
			panel.add(map);
			panel.add(content);
			panel.add(browsercomboBox);

			panel.add(saveBtn);

			panel1();

			serversitedisplay();
			// panel 3

			panel3();
			layout.putConstraint(SpringLayout.WEST, saveBtn, labelstart + 220, SpringLayout.WEST, panel);

			if (((no_of_site == 1)) && (runalltestradio.isSelected())) {
				// SN if (((no_of_site == 1))){
				layout.putConstraint(SpringLayout.SOUTH, panel, 100, SpringLayout.SOUTH, saveBtn);
			} else {
				layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, saveBtn);
			}
			/*
			 * portalurltext.getDocument().addDocumentListener(new DocumentListener() {
			 * 
			 * @Override public void insertUpdate(DocumentEvent e) { // TODO Auto-generated
			 * method stub test1(); }
			 * 
			 * @Override public void removeUpdate(DocumentEvent e) { // TODO Auto-generated
			 * method stub test1(); }
			 * 
			 * @Override public void changedUpdate(DocumentEvent e) { // TODO Auto-generated
			 * method stub test1(); }});
			 */

			saml.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						SAML = true;
						panel.remove(portalusernamelabel);
						panel.remove(portalpasswordlabel);
						panel.remove(portalusernametext);
						panel.remove(portalpasswordtext);

						panel1();

						serversitedisplay();

						panel3();
						 if (((no_of_site == 1)) && (runalltestradio.isSelected())) {
					
							layout.putConstraint(SpringLayout.SOUTH, panel, 100, SpringLayout.SOUTH, saveBtn);
						} else {
							layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, saveBtn);
						}
						// layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH,
						// saveBtn);
						validate();
						repaint();

					}

					if (e.getStateChange() == ItemEvent.DESELECTED) {
						SAML = false;

						panel.setLayout(layout);

						panel.add(portalusernamelabel);
						panel.add(portalpasswordlabel);
						panel.add(portalusernametext);
						panel.add(portalpasswordtext);

						panel1();

						serversitedisplay();

						panel3();
						 if (((no_of_site == 1)) && (runalltestradio.isSelected())) {
							layout.putConstraint(SpringLayout.SOUTH, panel, 100, SpringLayout.SOUTH, saveBtn);
						} else {
							layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, saveBtn);
						}
						validate();
						repaint();

					}
				}
			});
			addsite.addMouseListener(new MouseAdapter() {

				// addsite.addActionListener(new ActionListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// public void actionPerformed(ActionEvent e) {

					no_of_site = no_of_site + 1;

					panel1();
					if (no_of_site == 2) {

						panel.add(adminurllabel2);
						panel.add(adminurltext2);
						panel.add(serverrolelabel2);
						panel.add(serverrolecomboBox2);
						panel.add(adminusernamelabel2);
						panel.add(adminusernametext2);
						panel.add(adminpasswordlabel2);
						panel.add(adminpasswordlabel2);
						panel.add(adminpasswordtext2);
						panel.add(adminpasswordtext2);
						panel.add(federatedradio2);
						panel.add(nonfederatedradio2);

					}
					if (no_of_site == 3) {

						panel.add(adminurllabel3);
						panel.add(adminurltext3);
						panel.add(serverrolelabel3);
						panel.add(serverrolecomboBox3);
						panel.add(adminusernamelabel3);
						panel.add(adminusernametext3);
						panel.add(adminpasswordlabel3);
						panel.add(adminpasswordlabel3);
						panel.add(adminpasswordtext3);
						panel.add(adminpasswordtext3);
						panel.add(federatedradio3);
						panel.add(nonfederatedradio3);

					}
					if (no_of_site == 4) {

						panel.add(adminurllabel4);
						panel.add(adminurltext4);
						panel.add(serverrolelabel4);
						panel.add(serverrolecomboBox4);
						panel.add(adminusernamelabel4);
						panel.add(adminusernametext4);
						panel.add(adminpasswordlabel4);
						panel.add(adminpasswordlabel4);
						panel.add(adminpasswordtext4);
						panel.add(adminpasswordtext4);
						panel.add(federatedradio4);
						panel.add(nonfederatedradio4);
					}
					if (no_of_site == 5) {

						panel.add(adminurllabel5);
						panel.add(adminurltext5);
						panel.add(serverrolelabel5);
						panel.add(serverrolecomboBox5);
						panel.add(adminusernamelabel5);
						panel.add(adminusernametext5);
						panel.add(adminpasswordlabel5);
						panel.add(adminpasswordlabel5);
						panel.add(adminpasswordtext5);
						panel.add(adminpasswordtext5);
						panel.add(federatedradio5);
						panel.add(nonfederatedradio5);

					}
					if (no_of_site == 6) {
						panel.remove(addsite);
						panel.add(adminurllabel6);
						panel.add(adminurltext6);
						panel.add(serverrolelabel6);
						panel.add(serverrolecomboBox6);
						panel.add(adminusernamelabel6);
						panel.add(adminusernametext6);
						panel.add(adminpasswordlabel6);
						panel.add(adminpasswordlabel6);
						panel.add(adminpasswordtext6);
						panel.add(adminpasswordtext6);
						panel.add(federatedradio6);
						panel.add(nonfederatedradio6);
					}

					serversitedisplay();

					panel3();
					
					if (((no_of_site == 1)) && (runalltestradio.isSelected())) {

						layout.putConstraint(SpringLayout.SOUTH, panel, 100, SpringLayout.SOUTH, saveBtn);
					} else {
						layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, saveBtn);
					}
					validate();
					repaint();
				}
			});

			saveBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					setvalues(panel);
					try {
						verifyvalues(panel);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			clearBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					portalurltext.setText("");
					if (!saml.isSelected()) {
						portalusernametext.setText("");
						portalpasswordtext.setText("");
					}
					if (nonfederatedradio1.isSelected()) {
						managerurltext.setText("");
						managerusernametext.setText("");
						managerpasswordtext.setText("");
					}
					for (int i = 0; i < no_of_site; i++) {
						if (i == 0) {
							adminurltext1.setText("");
						}
						if (i == 1) {
							adminurltext2.setText("");
						}
						if (i == 2) {
							adminurltext3.setText("");
						}
						if (i == 3) {
							adminurltext4.setText("");
						}
						if (i == 4) {
							adminurltext5.setText("");
						}
						if (i == 5) {
							adminurltext6.setText("");
						}
					}
					adminusernametext1.setText("");
					adminpasswordtext1.setText("");
				}
			});

			runalltestradio.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {

						panel1();

						serversitedisplay();

						panel.remove(loginportal);
						panel.remove(loginmanager);
						panel.remove(loginserver);
						panel.remove(groupfunctionality);
						panel.remove(webappbuilder);
						panel.remove(organization);
						panel.remove(scenelayer);
						panel.remove(feature);
						panel.remove(tile);
						panel.remove(map);
						panel.remove(content);
						panel.remove(hosting);
						panel3();
						alltestfunctionality();

						flagloginadmin = true;
						flagloginmanager = true;
						flagloginportal = true;
						flagtilelayer = true;
						flagfeaturelayer = true;
						flaggroup = true;
						flagorganization = true;
						flagscenelayer = true;
						flagmap = true;
						flagwebappbuilder = true;
						flagcontent = true;
						flaghosting = true;

						if ((no_of_site == 1) && (saml.isSelected())) {
							layout.putConstraint(SpringLayout.SOUTH, panel, 80, SpringLayout.SOUTH, saveBtn);
						} else {
							layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, saveBtn);
						}
						saveBtn.setEnabled(true);
						validate();
						repaint();

					}

				}
			});
			selecttestradio.addItemListener(new ItemListener() {

				@SuppressWarnings("deprecation")
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {

						panel1();

						serversitedisplay();

						panel.add(loginportal);
						panel.add(loginmanager);
						panel.add(loginserver);
						panel.add(groupfunctionality);
						panel.add(webappbuilder);
						panel.add(organization);
						panel.add(scenelayer);
						panel.add(feature);
						panel.add(tile);
						panel.add(map);
						panel.add(content);
						panel.add(hosting);
						panel3();
						selecttestfunctionality();
						layout.putConstraint(SpringLayout.WEST, saveBtn, labelstart + 220, SpringLayout.WEST, panel);

						layout.putConstraint(SpringLayout.SOUTH, panel, 20, SpringLayout.SOUTH, saveBtn);
						map.setSelected(true);
						groupfunctionality.setSelected(true);
						feature.setSelected(true);
						tile.setSelected(false);
						webappbuilder.setSelected(false);
						organization.setSelected(true);
						scenelayer.setSelected(true);
						loginportal.setSelected(true);
						loginserver.setSelected(true);
						loginmanager.setSelected(true);
						hosting.setSelected(true);
						flagloginadmin = true;
						flagloginmanager = true;
						flagloginportal = true;
						flagtilelayer = false;
						flagfeaturelayer = true;
						flaggroup = true;
						flagorganization = true;
						flagscenelayer = true;
						flagmap = true;
						flagwebappbuilder = false;
						flagcontent = true;
						flaghosting = true;
						saveBtn.setEnabled(true);
						saveBtn.setToolTipText("Please select at least one Test to run");
						validate();
						repaint();

					}

				}
			});

			federatedradio1.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						federated[0] = true;
					}

				}
			});
			nonfederatedradio1.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						federated[0] = false;
					}

				}
			});
			federatedradio2.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						federated[1] = true;
					}

				}
			});
			nonfederatedradio2.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						federated[1] = false;
					}

				}
			});
			federatedradio3.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						federated[2] = true;
					}

				}
			});
			nonfederatedradio3.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						federated[2] = false;
					}

				}
			});
			federatedradio4.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						federated[3] = true;
					}

				}
			});
			nonfederatedradio4.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						federated[3] = false;
					}

				}
			});
			federatedradio5.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						federated[4] = true;
					}

				}
			});
			nonfederatedradio5.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						federated[4] = false;
					}

				}
			});
			federatedradio6.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						federated[5] = true;
					}

				}
			});
			nonfederatedradio6.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						federated[5] = false;
					}

				}
			});

			map.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {

					if (e.getStateChange() == ItemEvent.SELECTED) {
						flagmap = true;
						flagloginportal = true;
						// flagfeaturelayer = true;
						loginportal.setSelected(true);
						loginportal.setEnabled(false);
						// feature.setSelected(true);
						saveBtn.setEnabled(true);
						// feature.setEnabled(false);
					}
					if (e.getStateChange() == ItemEvent.DESELECTED) {
						flagmap = false;

						if (tile.isSelected() || feature.isSelected() || groupfunctionality.isSelected()
								|| scenelayer.isSelected() || organization.isSelected() || webappbuilder.isSelected()
								|| content.isSelected() || hosting.isSelected()) {
							loginportal.setEnabled(false);
						} else {
							loginportal.setEnabled(true);
						}

						if (loginmanager.isSelected() || loginserver.isSelected() || loginportal.isSelected()
								|| map.isSelected() || tile.isSelected() || feature.isSelected()
								|| groupfunctionality.isSelected() || organization.isSelected()
								|| scenelayer.isSelected() || webappbuilder.isSelected() || content.isSelected()
								|| hosting.isSelected()) {
							saveBtn.setEnabled(true);
						} else {
							saveBtn.setEnabled(false);
						}
					}

				}
			});

			hosting.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {

					if (e.getStateChange() == ItemEvent.SELECTED) {
						flaghosting = true;
						flagloginportal = true;
						// flagfeaturelayer = true;
						loginportal.setSelected(true);
						loginportal.setEnabled(false);
						// feature.setSelected(true);
						saveBtn.setEnabled(true);
						// feature.setEnabled(false);
					}
					if (e.getStateChange() == ItemEvent.DESELECTED) {
						flaghosting = false;

						if (tile.isSelected() || feature.isSelected() || groupfunctionality.isSelected()
								|| scenelayer.isSelected() || organization.isSelected() || webappbuilder.isSelected()
								|| content.isSelected() || map.isSelected()) {
							loginportal.setEnabled(false);
						} else {
							loginportal.setEnabled(true);
						}

						if (loginmanager.isSelected() || loginserver.isSelected() || loginportal.isSelected()
								|| map.isSelected() || tile.isSelected() || feature.isSelected()
								|| groupfunctionality.isSelected() || organization.isSelected()
								|| scenelayer.isSelected() || webappbuilder.isSelected() || content.isSelected()
								|| hosting.isSelected()) {
							saveBtn.setEnabled(true);
						} else {
							saveBtn.setEnabled(false);
						}
					}

				}
			});

			scenelayer.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {

					if (e.getStateChange() == ItemEvent.SELECTED) {
						flagscenelayer = true;

						flagloginportal = true;
						// flagfeaturelayer = true;
						loginportal.setSelected(true);
						loginportal.setEnabled(false);
						// feature.setSelected(true);

						saveBtn.setEnabled(true);
						// feature.setEnabled(false);
					}
					if (e.getStateChange() == ItemEvent.DESELECTED) {
						flagscenelayer = false;
						/*
						 * if (tile.isSelected()) { feature.setSelected(true);
						 * feature.setEnabled(false); } else { feature.setSelected(false);
						 * feature.setEnabled(true); flagfeaturelayer = false; }
						 */
						if (tile.isSelected() || feature.isSelected() || groupfunctionality.isSelected()
								|| map.isSelected() || organization.isSelected() || webappbuilder.isSelected()
								|| content.isSelected() || hosting.isSelected()) {
							loginportal.setEnabled(false);
						} else {
							loginportal.setEnabled(true);
						}

						if (loginmanager.isSelected() || loginserver.isSelected() || loginportal.isSelected()
								|| map.isSelected() || tile.isSelected() || feature.isSelected()
								|| groupfunctionality.isSelected() || organization.isSelected()
								|| scenelayer.isSelected() || webappbuilder.isSelected() || content.isSelected()
								|| hosting.isSelected()) {
							saveBtn.setEnabled(true);
						} else {
							saveBtn.setEnabled(false);
						}
					}

				}
			});

			tile.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						flagtilelayer = true;
						flagloginportal = true;
						loginportal.setSelected(true);
						feature.setSelected(true);
						loginportal.setEnabled(false);
						feature.setEnabled(false);
						saveBtn.setEnabled(true);
					}
					if (e.getStateChange() == ItemEvent.DESELECTED) {
						flagtilelayer = false;
						if (map.isSelected()) {
							feature.setSelected(true);
							feature.setEnabled(false);
						} else {
							// flagfeaturelayer = false;
							feature.setSelected(true);
							feature.setEnabled(true);
						}

						if (feature.isSelected() || groupfunctionality.isSelected() || organization.isSelected()
								|| scenelayer.isSelected() || map.isSelected() || webappbuilder.isSelected()
								|| content.isSelected() || hosting.isSelected()) {
							loginportal.setEnabled(false);
						} else {
							loginportal.setEnabled(true);
						}
						if (loginmanager.isSelected() || loginserver.isSelected() || loginportal.isSelected()
								|| map.isSelected() || tile.isSelected() || feature.isSelected()
								|| groupfunctionality.isSelected() || organization.isSelected()
								|| scenelayer.isSelected() || webappbuilder.isSelected() || content.isSelected()
								|| hosting.isSelected()) {
							saveBtn.setEnabled(true);
						} else {
							saveBtn.setEnabled(false);
						}
					}

				}
			});
			feature.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						flagfeaturelayer = true;
						flagloginportal = true;
						loginportal.setSelected(true);
						loginportal.setEnabled(false);
						saveBtn.setEnabled(true);

					}
					if (e.getStateChange() == ItemEvent.DESELECTED) {
						flagfeaturelayer = false;
						if (tile.isSelected() || groupfunctionality.isSelected() || organization.isSelected()
								|| scenelayer.isSelected() || map.isSelected() || webappbuilder.isSelected()
								|| content.isSelected() || hosting.isSelected()) {
							loginportal.setEnabled(false);

						} else {
							loginportal.setEnabled(true);
						}
						if (loginmanager.isSelected() || loginserver.isSelected() || loginportal.isSelected()
								|| map.isSelected() || tile.isSelected() || feature.isSelected()
								|| groupfunctionality.isSelected() || organization.isSelected()
								|| webappbuilder.isSelected() || content.isSelected() || scenelayer.isSelected()
								|| hosting.isSelected()) {
							saveBtn.setEnabled(true);
						} else {
							saveBtn.setEnabled(false);
						}
					}

				}
			});
			webappbuilder.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						flagwebappbuilder = true;
						flagloginportal = true;
						loginportal.setSelected(true);
						loginportal.setEnabled(false);
						saveBtn.setEnabled(true);

					}
					if (e.getStateChange() == ItemEvent.DESELECTED) {
						flagwebappbuilder = false;
						if (tile.isSelected() || feature.isSelected() || groupfunctionality.isSelected()
								|| scenelayer.isSelected() || organization.isSelected() || map.isSelected()
								|| content.isSelected() || hosting.isSelected()) {
							loginportal.setEnabled(false);
						} else {
							loginportal.setEnabled(true);
						}
						if (loginmanager.isSelected() || loginserver.isSelected() || loginportal.isSelected()
								|| map.isSelected() || tile.isSelected() || feature.isSelected()
								|| groupfunctionality.isSelected() || organization.isSelected()
								|| webappbuilder.isSelected() || content.isSelected() || scenelayer.isSelected()
								|| hosting.isSelected()) {
							saveBtn.setEnabled(true);
						} else {
							saveBtn.setEnabled(false);
						}
					}

				}
			});
			content.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						flagcontent = true;
						flagloginportal = true;
						loginportal.setSelected(true);
						loginportal.setEnabled(false);
						saveBtn.setEnabled(true);
					}
					if (e.getStateChange() == ItemEvent.DESELECTED) {
						flagcontent = false;
						if (tile.isSelected() || feature.isSelected() || groupfunctionality.isSelected()
								|| scenelayer.isSelected() || organization.isSelected() || map.isSelected()
								|| webappbuilder.isSelected() || hosting.isSelected()) {
							loginportal.setEnabled(false);
						} else {
							loginportal.setEnabled(true);
						}
						if (loginmanager.isSelected() || loginserver.isSelected() || loginportal.isSelected()
								|| map.isSelected() || tile.isSelected() || feature.isSelected()
								|| groupfunctionality.isSelected() || organization.isSelected()
								|| webappbuilder.isSelected() || content.isSelected() || scenelayer.isSelected()
								|| hosting.isSelected()) {
							saveBtn.setEnabled(true);
						} else {
							saveBtn.setEnabled(false);
						}
					}

				}
			});
			loginserver.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						flagloginadmin = true;
						saveBtn.setEnabled(true);

					}
					if (e.getStateChange() == ItemEvent.DESELECTED) {
						flagloginadmin = false;
						if (loginmanager.isSelected() || loginserver.isSelected() || loginportal.isSelected()
								|| map.isSelected() || tile.isSelected() || feature.isSelected()
								|| groupfunctionality.isSelected() || organization.isSelected()
								|| webappbuilder.isSelected() || content.isSelected() || scenelayer.isSelected()
								|| hosting.isSelected()) {
							saveBtn.setEnabled(true);
						} else {
							saveBtn.setEnabled(false);
						}
					}

				}
			});
			organization.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						flagorganization = true;
						flagloginportal = true;
						loginportal.setSelected(true);
						loginportal.setEnabled(false);
						saveBtn.setEnabled(true);

					}
					if (e.getStateChange() == ItemEvent.DESELECTED) {
						flagorganization = false;
						if (tile.isSelected() || feature.isSelected() || groupfunctionality.isSelected()
								|| map.isSelected() || webappbuilder.isSelected() || content.isSelected()
								|| scenelayer.isSelected() || hosting.isSelected()) {
							loginportal.setEnabled(false);
						} else {
							loginportal.setEnabled(true);
						}
						if (loginmanager.isSelected() || loginserver.isSelected() || loginportal.isSelected()
								|| map.isSelected() || tile.isSelected() || feature.isSelected()
								|| groupfunctionality.isSelected() || organization.isSelected()
								|| webappbuilder.isSelected() || content.isSelected() || scenelayer.isSelected()
								|| hosting.isSelected()) {
							saveBtn.setEnabled(true);
						} else {
							saveBtn.setEnabled(false);
						}
					}

				}
			});
			loginmanager.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						flagloginmanager = true;
						saveBtn.setEnabled(true);

					}
					if (e.getStateChange() == ItemEvent.DESELECTED) {
						flagloginmanager = false;
						if (loginmanager.isSelected() || loginserver.isSelected() || loginportal.isSelected()
								|| map.isSelected() || tile.isSelected() || feature.isSelected()
								|| groupfunctionality.isSelected() || organization.isSelected()
								|| webappbuilder.isSelected() || content.isSelected() || scenelayer.isSelected()
								|| hosting.isSelected()) {
							saveBtn.setEnabled(true);
						} else {
							saveBtn.setEnabled(false);
						}
					}

				}
			});
			groupfunctionality.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						flaggroup = true;
						flagloginportal = true;
						// flagfeaturelayer=false;
						// feature.setSelected(true);
						saveBtn.setEnabled(true);
						// feature.setEnabled(false);
						loginportal.setSelected(true);
						loginportal.setEnabled(false);
					}
					if (e.getStateChange() == ItemEvent.DESELECTED) {
						flaggroup = false;
						if (tile.isSelected() || feature.isSelected() || organization.isSelected() || map.isSelected()
								|| webappbuilder.isSelected() || content.isSelected() || scenelayer.isSelected()
								|| hosting.isSelected()) {
							loginportal.setEnabled(false);

						} else {
							loginportal.setEnabled(true);

						}
						if (loginmanager.isSelected() || loginserver.isSelected() || loginportal.isSelected()
								|| map.isSelected() || tile.isSelected() || feature.isSelected()
								|| groupfunctionality.isSelected() || organization.isSelected()
								|| webappbuilder.isSelected() || content.isSelected() || scenelayer.isSelected()
								|| hosting.isSelected()) {
							saveBtn.setEnabled(true);
						} else {
							saveBtn.setEnabled(false);
						}
					}

				}
			});
			loginportal.addItemListener(new ItemListener() {

				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						flagloginportal = true;
						saveBtn.setEnabled(true);
					}
					if (e.getStateChange() == ItemEvent.DESELECTED) {
						flagloginportal = false;
						flagtilelayer = false;
						flagfeaturelayer = false;
						flaggroup = false;
						flagorganization = false;
						flagscenelayer = false;
						flagmap = false;
						flagwebappbuilder = false;
						flagcontent = false;
						flaghosting=false;
						tile.setSelected(false);
						feature.setSelected(false);
						groupfunctionality.setSelected(false);
						map.setSelected(false);
						webappbuilder.setSelected(false);
						content.setSelected(false);
						organization.setSelected(false);
						scenelayer.setSelected(false);
						hosting.setSelected(false);
						tile.setEnabled(true);
						feature.setEnabled(true);
						groupfunctionality.setEnabled(true);
						map.setEnabled(true);
						webappbuilder.setEnabled(true);
						content.setEnabled(true);
						organization.setEnabled(true);
						scenelayer.setEnabled(true);
						hosting.setEnabled(true);
						if (loginmanager.isSelected() || loginserver.isSelected() || loginportal.isSelected()
								|| map.isSelected() || tile.isSelected() || feature.isSelected()
								|| groupfunctionality.isSelected() || organization.isSelected()
								|| webappbuilder.isSelected() || content.isSelected() || scenelayer.isSelected()
								|| hosting.isSelected()) {
							saveBtn.setEnabled(true);
						} else {
							saveBtn.setEnabled(false);
						}
					}

				}
			});

		} catch (Exception e) {
			e.printStackTrace();

		}
		// panel.validate();
		// panel.repaint();

	}

	static void selecttestfunctionality() {
		try {

			// panel.add(content);
			layout.getConstraints(loginportal).setHeight(Spring.constant(h2));
			layout.getConstraints(loginmanager).setHeight(Spring.constant(h2));
			layout.getConstraints(loginserver).setHeight(Spring.constant(h2));
			layout.getConstraints(groupfunctionality).setHeight(Spring.constant(h2));
			layout.getConstraints(webappbuilder).setHeight(Spring.constant(h2));
			layout.getConstraints(organization).setHeight(Spring.constant(h2));
			layout.getConstraints(scenelayer).setHeight(Spring.constant(h2));
			layout.getConstraints(feature).setHeight(Spring.constant(h2));
			layout.getConstraints(tile).setHeight(Spring.constant(h2));
			layout.getConstraints(map).setHeight(Spring.constant(h2));
			layout.getConstraints(content).setHeight(Spring.constant(h2));
			layout.getConstraints(hosting).setHeight(Spring.constant(h2));
			layout.putConstraint(SpringLayout.NORTH, loginportal, distance, SpringLayout.SOUTH, selecttestradio);
			layout.putConstraint(SpringLayout.WEST, loginportal, textstart, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, loginmanager, distance, SpringLayout.SOUTH, loginportal);
			layout.putConstraint(SpringLayout.WEST, loginmanager, textstart, SpringLayout.WEST, panel);

			layout.putConstraint(SpringLayout.NORTH, loginserver, distance, SpringLayout.SOUTH, loginmanager);
			layout.putConstraint(SpringLayout.WEST, loginserver, textstart, SpringLayout.WEST, panel);

			layout.putConstraint(SpringLayout.NORTH, hosting, distance, SpringLayout.SOUTH, loginserver);
			layout.putConstraint(SpringLayout.WEST, hosting, textstart, SpringLayout.WEST, panel);

			layout.putConstraint(SpringLayout.NORTH, content, distance, SpringLayout.SOUTH, hosting);
			layout.putConstraint(SpringLayout.WEST, content, textstart, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, feature, distance, SpringLayout.SOUTH, content);
			layout.putConstraint(SpringLayout.WEST, feature, textstart, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, tile, distance, SpringLayout.SOUTH, feature);
			layout.putConstraint(SpringLayout.WEST, tile, textstart, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, webappbuilder, distance, SpringLayout.SOUTH, tile);
			layout.putConstraint(SpringLayout.WEST, webappbuilder, textstart, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, map, distance, SpringLayout.SOUTH, webappbuilder);
			layout.putConstraint(SpringLayout.WEST, map, textstart, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, groupfunctionality, distance, SpringLayout.SOUTH, map);
			layout.putConstraint(SpringLayout.WEST, groupfunctionality, textstart, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, organization, distance, SpringLayout.SOUTH, groupfunctionality);
			layout.putConstraint(SpringLayout.WEST, organization, textstart, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, scenelayer, distance, SpringLayout.SOUTH, organization);
			layout.putConstraint(SpringLayout.WEST, scenelayer, textstart, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.WEST, saveBtn, labelstart + 220, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, saveBtn, distance + 15, SpringLayout.SOUTH, scenelayer);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	static void alltestfunctionality() {
		try {

			layout.getConstraints(runalltestradio).setHeight(Spring.constant(hl));
			layout.getConstraints(selecttestradio).setHeight(Spring.constant(hl));
			layout.putConstraint(SpringLayout.WEST, saveBtn, labelstart + 220, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, saveBtn, distance + 15, SpringLayout.SOUTH, selecttestradio);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	static void serversitedisplay() {
		try {

			for (int i = 1; i <= no_of_site; i++) {
				if (i == 1) {
					layout.getConstraints(adminurllabel1).setHeight(Spring.constant(hl));
					layout.getConstraints(adminurllabel1).setWidth(Spring.constant(wl));
					layout.getConstraints(adminurltext1).setHeight(Spring.constant(hl));
					layout.getConstraints(adminurltext1).setWidth(Spring.constant(textlength));
					layout.getConstraints(serverrolelabel1).setHeight(Spring.constant(hl));
					layout.getConstraints(serverrolelabel1).setWidth(Spring.constant(wl));
					layout.getConstraints(adminusernamelabel1).setHeight(Spring.constant(hl));
					layout.getConstraints(adminusernamelabel1).setWidth(Spring.constant(wl));
					layout.getConstraints(adminusernametext1).setHeight(Spring.constant(hl));
					layout.getConstraints(adminusernametext1).setWidth(Spring.constant(textlength));
					layout.getConstraints(adminpasswordlabel1).setHeight(Spring.constant(hl));
					layout.getConstraints(adminpasswordlabel1).setWidth(Spring.constant(wl));
					layout.getConstraints(adminpasswordtext1).setHeight(Spring.constant(hl));
					layout.getConstraints(adminpasswordtext1).setWidth(Spring.constant(textlength));
					layout.putConstraint(SpringLayout.WEST, adminurllabel1, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminurllabel1, distance, SpringLayout.SOUTH, partition1);
					layout.putConstraint(SpringLayout.WEST, adminurltext1, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminurltext1, distance, SpringLayout.SOUTH, partition1);
					layout.putConstraint(SpringLayout.WEST, serverrolelabel1, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, serverrolelabel1, distance, SpringLayout.SOUTH,
							adminurllabel1);
					layout.putConstraint(SpringLayout.WEST, serverrolecomboBox1, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, serverrolecomboBox1, distance, SpringLayout.SOUTH,
							adminurllabel1);
					layout.putConstraint(SpringLayout.WEST, adminusernamelabel1, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminusernamelabel1, distance, SpringLayout.SOUTH,
							serverrolelabel1);
					layout.putConstraint(SpringLayout.WEST, adminusernametext1, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminusernametext1, distance, SpringLayout.SOUTH,
							serverrolelabel1);
					layout.putConstraint(SpringLayout.WEST, adminpasswordlabel1, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminpasswordlabel1, distance, SpringLayout.SOUTH,
							adminusernamelabel1);
					layout.putConstraint(SpringLayout.WEST, adminpasswordtext1, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminpasswordtext1, distance, SpringLayout.SOUTH,
							adminusernamelabel1);
					layout.putConstraint(SpringLayout.WEST, federatedradio1, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, federatedradio1, distance, SpringLayout.SOUTH,
							adminpasswordlabel1);
					layout.putConstraint(SpringLayout.WEST, nonfederatedradio1, textstart + 100, SpringLayout.WEST,
							panel);
					layout.putConstraint(SpringLayout.NORTH, nonfederatedradio1, distance, SpringLayout.SOUTH,
							adminpasswordlabel1);
					// layout.putConstraint(SpringLayout.NORTH, addsite, distance-10,
					// SpringLayout.SOUTH, federatedradio1);
					// layout.putConstraint(SpringLayout.WEST, addsite, labelstart+10,
					// SpringLayout.WEST, panel2);

				}
				if (i == 2) {
					layout.getConstraints(adminurllabel2).setHeight(Spring.constant(hl));
					layout.getConstraints(adminurllabel2).setWidth(Spring.constant(wl));
					layout.getConstraints(adminurltext2).setHeight(Spring.constant(hl));
					layout.getConstraints(adminurltext2).setWidth(Spring.constant(textlength));
					layout.getConstraints(serverrolelabel2).setHeight(Spring.constant(hl));
					layout.getConstraints(serverrolelabel2).setWidth(Spring.constant(wl));
					layout.getConstraints(adminusernamelabel2).setHeight(Spring.constant(hl));
					layout.getConstraints(adminusernamelabel2).setWidth(Spring.constant(wl));
					layout.getConstraints(adminusernametext2).setHeight(Spring.constant(hl));
					layout.getConstraints(adminusernametext2).setWidth(Spring.constant(textlength));
					layout.getConstraints(adminpasswordlabel2).setHeight(Spring.constant(hl));
					layout.getConstraints(adminpasswordlabel2).setWidth(Spring.constant(wl));
					layout.getConstraints(adminpasswordtext2).setHeight(Spring.constant(hl));
					layout.getConstraints(adminpasswordtext2).setWidth(Spring.constant(textlength));

					/// layout.putConstraint(SpringLayout.EAST, panel4, -1, SpringLayout.EAST,
					/// panel);

					// layout.putConstraint(SpringLayout.WEST, panel4, 1, SpringLayout.WEST, panel);

					// layout.putConstraint(SpringLayout.NORTH, panel4, 0, SpringLayout.SOUTH,
					// panel2);

					layout.putConstraint(SpringLayout.WEST, adminurllabel2, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminurllabel2, distance, SpringLayout.SOUTH,
							federatedradio1);
					layout.putConstraint(SpringLayout.WEST, adminurltext2, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminurltext2, distance, SpringLayout.SOUTH,
							federatedradio1);
					layout.putConstraint(SpringLayout.WEST, serverrolelabel2, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, serverrolelabel2, distance, SpringLayout.SOUTH,
							adminurllabel2);
					layout.putConstraint(SpringLayout.WEST, serverrolecomboBox2, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, serverrolecomboBox2, distance, SpringLayout.SOUTH,
							adminurllabel2);
					layout.putConstraint(SpringLayout.WEST, adminusernamelabel2, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminusernamelabel2, distance, SpringLayout.SOUTH,
							serverrolelabel2);
					layout.putConstraint(SpringLayout.WEST, adminusernametext2, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminusernametext2, distance, SpringLayout.SOUTH,
							serverrolelabel2);
					layout.putConstraint(SpringLayout.WEST, adminpasswordlabel2, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminpasswordlabel2, distance, SpringLayout.SOUTH,
							adminusernametext2);
					layout.putConstraint(SpringLayout.WEST, adminpasswordtext2, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminpasswordtext2, distance, SpringLayout.SOUTH,
							adminusernametext2);
					layout.putConstraint(SpringLayout.WEST, federatedradio2, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, federatedradio2, distance, SpringLayout.SOUTH,
							adminpasswordlabel2);
					layout.putConstraint(SpringLayout.WEST, nonfederatedradio2, textstart + 100, SpringLayout.WEST,
							panel);
					layout.putConstraint(SpringLayout.NORTH, nonfederatedradio2, distance, SpringLayout.SOUTH,
							adminpasswordlabel2);
					// panel.add(panel4);
				}
				if (i == 3) {
					layout.getConstraints(adminurllabel3).setHeight(Spring.constant(hl));
					layout.getConstraints(adminurllabel3).setWidth(Spring.constant(wl));
					layout.getConstraints(adminurltext3).setHeight(Spring.constant(hl));
					layout.getConstraints(adminurltext3).setWidth(Spring.constant(textlength));
					layout.getConstraints(serverrolelabel3).setHeight(Spring.constant(hl));
					layout.getConstraints(serverrolelabel3).setWidth(Spring.constant(wl));
					layout.getConstraints(adminusernamelabel3).setHeight(Spring.constant(hl));
					layout.getConstraints(adminusernamelabel3).setWidth(Spring.constant(wl));
					layout.getConstraints(adminusernametext3).setHeight(Spring.constant(hl));
					layout.getConstraints(adminusernametext3).setWidth(Spring.constant(textlength));
					layout.getConstraints(adminpasswordlabel3).setHeight(Spring.constant(hl));
					layout.getConstraints(adminpasswordlabel3).setWidth(Spring.constant(wl));
					layout.getConstraints(adminpasswordtext3).setHeight(Spring.constant(hl));
					layout.getConstraints(adminpasswordtext3).setWidth(Spring.constant(textlength));

					layout.putConstraint(SpringLayout.WEST, adminurllabel3, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminurllabel3, distance, SpringLayout.SOUTH,
							federatedradio2);
					layout.putConstraint(SpringLayout.WEST, adminurltext3, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminurltext3, distance, SpringLayout.SOUTH,
							federatedradio2);
					layout.putConstraint(SpringLayout.WEST, serverrolelabel3, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, serverrolelabel3, distance, SpringLayout.SOUTH,
							adminurllabel3);
					layout.putConstraint(SpringLayout.WEST, serverrolecomboBox3, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, serverrolecomboBox3, distance, SpringLayout.SOUTH,
							adminurllabel3);
					layout.putConstraint(SpringLayout.WEST, adminusernamelabel3, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminusernamelabel3, distance, SpringLayout.SOUTH,
							serverrolelabel3);
					layout.putConstraint(SpringLayout.WEST, adminusernametext3, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminusernametext3, distance, SpringLayout.SOUTH,
							serverrolelabel3);
					layout.putConstraint(SpringLayout.WEST, adminpasswordlabel3, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminpasswordlabel3, distance, SpringLayout.SOUTH,
							adminusernamelabel3);
					layout.putConstraint(SpringLayout.WEST, adminpasswordtext3, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminpasswordtext3, distance, SpringLayout.SOUTH,
							adminusernamelabel3);
					layout.putConstraint(SpringLayout.WEST, federatedradio3, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, federatedradio3, distance, SpringLayout.SOUTH,
							adminpasswordlabel3);
					layout.putConstraint(SpringLayout.WEST, nonfederatedradio3, textstart + 100, SpringLayout.WEST,
							panel);
					layout.putConstraint(SpringLayout.NORTH, nonfederatedradio3, distance, SpringLayout.SOUTH,
							adminpasswordlabel3);
					// panel.add(panel5);
				}
				if (i == 4) {
					layout.getConstraints(adminurllabel4).setHeight(Spring.constant(hl));
					layout.getConstraints(adminurllabel4).setWidth(Spring.constant(wl));
					layout.getConstraints(adminurltext4).setHeight(Spring.constant(hl));
					layout.getConstraints(adminurltext4).setWidth(Spring.constant(textlength));
					layout.getConstraints(serverrolelabel4).setHeight(Spring.constant(hl));
					layout.getConstraints(serverrolelabel4).setWidth(Spring.constant(wl));
					layout.getConstraints(adminusernamelabel4).setHeight(Spring.constant(hl));
					layout.getConstraints(adminusernamelabel4).setWidth(Spring.constant(wl));
					layout.getConstraints(adminusernametext4).setHeight(Spring.constant(hl));
					layout.getConstraints(adminusernametext4).setWidth(Spring.constant(textlength));
					layout.getConstraints(adminpasswordlabel4).setHeight(Spring.constant(hl));
					layout.getConstraints(adminpasswordlabel4).setWidth(Spring.constant(wl));
					layout.getConstraints(adminpasswordtext4).setHeight(Spring.constant(hl));
					layout.getConstraints(adminpasswordtext4).setWidth(Spring.constant(textlength));

					layout.putConstraint(SpringLayout.WEST, adminurllabel4, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminurllabel4, distance, SpringLayout.SOUTH,
							federatedradio3);
					layout.putConstraint(SpringLayout.WEST, adminurltext4, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminurltext4, distance, SpringLayout.SOUTH,
							federatedradio3);
					layout.putConstraint(SpringLayout.WEST, serverrolelabel4, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, serverrolelabel4, distance, SpringLayout.SOUTH,
							adminurllabel4);
					layout.putConstraint(SpringLayout.WEST, serverrolecomboBox4, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, serverrolecomboBox4, distance, SpringLayout.SOUTH,
							adminurllabel4);
					layout.putConstraint(SpringLayout.WEST, adminusernamelabel4, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminusernamelabel4, distance, SpringLayout.SOUTH,
							serverrolelabel4);
					layout.putConstraint(SpringLayout.WEST, adminusernametext4, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminusernametext4, distance, SpringLayout.SOUTH,
							serverrolelabel4);
					layout.putConstraint(SpringLayout.WEST, adminpasswordlabel4, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminpasswordlabel4, distance, SpringLayout.SOUTH,
							adminusernamelabel4);
					layout.putConstraint(SpringLayout.WEST, adminpasswordtext4, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminpasswordtext4, distance, SpringLayout.SOUTH,
							adminusernamelabel4);
					layout.putConstraint(SpringLayout.WEST, federatedradio4, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, federatedradio4, distance, SpringLayout.SOUTH,
							adminpasswordlabel4);
					layout.putConstraint(SpringLayout.WEST, nonfederatedradio4, textstart + 100, SpringLayout.WEST,
							panel);
					layout.putConstraint(SpringLayout.NORTH, nonfederatedradio4, distance, SpringLayout.SOUTH,
							adminpasswordlabel4);

				}
				if (i == 5) {

					layout.getConstraints(adminurllabel5).setHeight(Spring.constant(hl));
					layout.getConstraints(adminurllabel5).setWidth(Spring.constant(wl));
					layout.getConstraints(adminurltext5).setHeight(Spring.constant(hl));
					layout.getConstraints(adminurltext5).setWidth(Spring.constant(textlength));
					layout.getConstraints(serverrolelabel5).setHeight(Spring.constant(hl));
					layout.getConstraints(serverrolelabel5).setWidth(Spring.constant(wl));
					layout.getConstraints(adminusernamelabel5).setHeight(Spring.constant(hl));
					layout.getConstraints(adminusernamelabel5).setWidth(Spring.constant(wl));
					layout.getConstraints(adminusernametext5).setHeight(Spring.constant(hl));
					layout.getConstraints(adminusernametext5).setWidth(Spring.constant(textlength));
					layout.getConstraints(adminpasswordlabel5).setHeight(Spring.constant(hl));
					layout.getConstraints(adminpasswordlabel5).setWidth(Spring.constant(wl));
					layout.getConstraints(adminpasswordtext5).setHeight(Spring.constant(hl));
					layout.getConstraints(adminpasswordtext5).setWidth(Spring.constant(textlength));

					layout.putConstraint(SpringLayout.WEST, adminurllabel5, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminurllabel5, distance, SpringLayout.SOUTH,
							federatedradio4);
					layout.putConstraint(SpringLayout.WEST, adminurltext5, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminurltext5, distance, SpringLayout.SOUTH,
							federatedradio4);
					layout.putConstraint(SpringLayout.WEST, serverrolelabel5, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, serverrolelabel5, distance, SpringLayout.SOUTH,
							adminurllabel5);
					layout.putConstraint(SpringLayout.WEST, serverrolecomboBox5, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, serverrolecomboBox5, distance, SpringLayout.SOUTH,
							adminurllabel5);
					layout.putConstraint(SpringLayout.WEST, adminusernamelabel5, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminusernamelabel5, distance, SpringLayout.SOUTH,
							serverrolelabel5);
					layout.putConstraint(SpringLayout.WEST, adminusernametext5, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminusernametext5, distance, SpringLayout.SOUTH,
							serverrolelabel5);
					layout.putConstraint(SpringLayout.WEST, adminpasswordlabel5, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminpasswordlabel5, distance, SpringLayout.SOUTH,
							adminusernamelabel5);
					layout.putConstraint(SpringLayout.WEST, adminpasswordtext5, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminpasswordtext5, distance, SpringLayout.SOUTH,
							adminusernamelabel5);
					layout.putConstraint(SpringLayout.WEST, federatedradio5, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, federatedradio5, distance, SpringLayout.SOUTH,
							adminpasswordlabel5);
					layout.putConstraint(SpringLayout.WEST, nonfederatedradio5, textstart + 100, SpringLayout.WEST,
							panel);
					layout.putConstraint(SpringLayout.NORTH, nonfederatedradio5, distance, SpringLayout.SOUTH,
							adminpasswordlabel5);

				}
				if (i == 6) {
					layout.getConstraints(adminurllabel6).setHeight(Spring.constant(hl));
					layout.getConstraints(adminurllabel6).setWidth(Spring.constant(wl));
					layout.getConstraints(adminurltext6).setHeight(Spring.constant(hl));
					layout.getConstraints(adminurltext6).setWidth(Spring.constant(textlength));
					layout.getConstraints(serverrolelabel6).setHeight(Spring.constant(hl));
					layout.getConstraints(serverrolelabel6).setWidth(Spring.constant(wl));
					layout.getConstraints(adminusernamelabel6).setHeight(Spring.constant(hl));
					layout.getConstraints(adminusernamelabel6).setWidth(Spring.constant(wl));
					layout.getConstraints(adminusernametext6).setHeight(Spring.constant(hl));
					layout.getConstraints(adminusernametext6).setWidth(Spring.constant(textlength));
					layout.getConstraints(adminpasswordlabel6).setHeight(Spring.constant(hl));
					layout.getConstraints(adminpasswordlabel6).setWidth(Spring.constant(wl));
					layout.getConstraints(adminpasswordtext6).setHeight(Spring.constant(hl));
					layout.getConstraints(adminpasswordtext6).setWidth(Spring.constant(textlength));

					layout.putConstraint(SpringLayout.WEST, adminurllabel6, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminurllabel6, distance, SpringLayout.SOUTH,
							federatedradio5);
					layout.putConstraint(SpringLayout.WEST, adminurltext6, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminurltext6, distance, SpringLayout.SOUTH,
							federatedradio5);
					layout.putConstraint(SpringLayout.WEST, serverrolelabel6, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, serverrolelabel6, distance, SpringLayout.SOUTH,
							adminurllabel6);
					layout.putConstraint(SpringLayout.WEST, serverrolecomboBox6, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, serverrolecomboBox6, distance, SpringLayout.SOUTH,
							adminurllabel6);
					layout.putConstraint(SpringLayout.WEST, adminusernamelabel6, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminusernamelabel6, distance, SpringLayout.SOUTH,
							serverrolelabel6);
					layout.putConstraint(SpringLayout.WEST, adminusernametext6, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminusernametext6, distance, SpringLayout.SOUTH,
							serverrolelabel6);
					layout.putConstraint(SpringLayout.WEST, adminpasswordlabel6, labelstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminpasswordlabel6, distance, SpringLayout.SOUTH,
							adminusernamelabel6);
					layout.putConstraint(SpringLayout.WEST, adminpasswordtext6, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, adminpasswordtext6, distance, SpringLayout.SOUTH,
							adminusernamelabel6);
					layout.putConstraint(SpringLayout.WEST, federatedradio6, textstart, SpringLayout.WEST, panel);
					layout.putConstraint(SpringLayout.NORTH, federatedradio6, distance, SpringLayout.SOUTH,
							adminpasswordlabel6);
					layout.putConstraint(SpringLayout.WEST, nonfederatedradio6, textstart + 100, SpringLayout.WEST,
							panel);
					layout.putConstraint(SpringLayout.NORTH, nonfederatedradio6, distance, SpringLayout.SOUTH,
							adminpasswordlabel6);

				}
			}
			if (no_of_site == 1) {
				layout.putConstraint(SpringLayout.NORTH, addsite, distance - 15, SpringLayout.SOUTH, federatedradio1);
				layout.putConstraint(SpringLayout.WEST, addsite, labelstart + 10, SpringLayout.WEST, panel);
				layout.putConstraint(SpringLayout.NORTH, partition2, distance, SpringLayout.SOUTH, addsite);

			}

			if (no_of_site == 2) {

				layout.putConstraint(SpringLayout.NORTH, addsite, distance - 15, SpringLayout.SOUTH, federatedradio2);
				layout.putConstraint(SpringLayout.WEST, addsite, labelstart + 10, SpringLayout.WEST, panel);
				layout.putConstraint(SpringLayout.NORTH, partition2, distance, SpringLayout.SOUTH, addsite);

			}
			if (no_of_site == 3) {

				layout.putConstraint(SpringLayout.NORTH, addsite, distance - 15, SpringLayout.SOUTH, federatedradio3);
				layout.putConstraint(SpringLayout.WEST, addsite, labelstart + 10, SpringLayout.WEST, panel);
				layout.putConstraint(SpringLayout.NORTH, partition2, distance, SpringLayout.SOUTH, addsite);

			}
			if (no_of_site == 4) {
				layout.putConstraint(SpringLayout.NORTH, addsite, distance - 15, SpringLayout.SOUTH, federatedradio4);
				layout.putConstraint(SpringLayout.WEST, addsite, labelstart + 10, SpringLayout.WEST, panel);
				layout.putConstraint(SpringLayout.NORTH, partition2, distance, SpringLayout.SOUTH, addsite);

			}
			if (no_of_site == 5) {
				layout.putConstraint(SpringLayout.NORTH, addsite, distance, SpringLayout.SOUTH, federatedradio5);
				layout.putConstraint(SpringLayout.WEST, addsite, labelstart + 10, SpringLayout.WEST, panel);
				layout.putConstraint(SpringLayout.NORTH, partition2, distance, SpringLayout.SOUTH, addsite);

			}
			if (no_of_site == 6) {

				layout.putConstraint(SpringLayout.NORTH, partition2, distance, SpringLayout.SOUTH, federatedradio6);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void panel1() {
		layout.getConstraints(portalurllabel).setHeight(Spring.constant(hl));
		layout.getConstraints(portalurltext).setWidth(Spring.constant(textlength));
		layout.getConstraints(portalusernamelabel).setHeight(Spring.constant(hl));
		layout.getConstraints(portalusernamelabel).setWidth(Spring.constant(wl));
		layout.getConstraints(portalpasswordlabel).setHeight(Spring.constant(hl));
		layout.getConstraints(portalpasswordlabel).setWidth(Spring.constant(wl));
		layout.getConstraints(portalusernametext).setHeight(Spring.constant(hl));
		layout.getConstraints(portalpasswordtext).setHeight(Spring.constant(hl));
		layout.getConstraints(portalusernametext).setWidth(Spring.constant(textlength));
		layout.getConstraints(portalpasswordtext).setWidth(Spring.constant(textlength));
		layout.putConstraint(SpringLayout.WEST, header, 20, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, header, 20, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, portalurllabel, labelstart, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, portalurllabel, distance, SpringLayout.SOUTH, header);
		layout.putConstraint(SpringLayout.WEST, portalurltext, textstart, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, portalurltext, distance, SpringLayout.SOUTH, header);
		layout.putConstraint(SpringLayout.NORTH, saml, distance1, SpringLayout.SOUTH, portalurltext);
		layout.putConstraint(SpringLayout.WEST, saml, textstart, SpringLayout.WEST, panel);
		if (!SAML) {
			layout.putConstraint(SpringLayout.WEST, portalusernamelabel, labelstart, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, portalusernamelabel, distance, SpringLayout.SOUTH, saml);
			layout.putConstraint(SpringLayout.WEST, portalusernametext, textstart, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, portalusernametext, distance, SpringLayout.SOUTH, saml);
			layout.putConstraint(SpringLayout.WEST, portalpasswordlabel, labelstart, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, portalpasswordlabel, distance, SpringLayout.SOUTH,
					portalusernamelabel);
			layout.putConstraint(SpringLayout.WEST, portalpasswordtext, textstart, SpringLayout.WEST, panel);
			layout.putConstraint(SpringLayout.NORTH, portalpasswordtext, distance, SpringLayout.SOUTH,
					portalusernamelabel);
			layout.putConstraint(SpringLayout.NORTH, partition1, distance, SpringLayout.SOUTH, portalpasswordtext);
			layout.putConstraint(SpringLayout.WEST, partition1, 1, SpringLayout.WEST, panel);
		} else {
			layout.putConstraint(SpringLayout.NORTH, partition1, distance, SpringLayout.SOUTH, saml);
			layout.putConstraint(SpringLayout.WEST, partition1, 1, SpringLayout.WEST, panel);
		}
	}

	void panel3() {
		// adding code

		// panel3();
		map.setSelected(true);
		groupfunctionality.setSelected(true);
		feature.setSelected(true);
		tile.setSelected(false);
		content.setSelected(true);
		webappbuilder.setSelected(false);
		organization.setSelected(true);
		scenelayer.setSelected(true);
		hosting.setSelected(true);
		loginportal.setSelected(true);
		loginserver.setSelected(true);
		loginmanager.setSelected(true);
		flagloginadmin = true;
		flagloginmanager = true;
		flagloginportal = true;
		flagtilelayer = false;
		flagfeaturelayer = true;
		flaggroup = true;
		flaghosting = true;
		flagorganization = true;
		flagscenelayer = true;
		flagmap = true;
		flagwebappbuilder = false;
		flagcontent = true;
		saveBtn.setEnabled(true);
		saveBtn.setToolTipText("Please select at least one Test to run");
         

		layout.putConstraint(SpringLayout.NORTH, browsercomboBox, distance, SpringLayout.SOUTH, partition2);
		layout.putConstraint(SpringLayout.NORTH, browserlabel, distance, SpringLayout.SOUTH, partition2);
		layout.putConstraint(SpringLayout.WEST, browserlabel, labelstart, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, browsercomboBox, textstart, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, runalltestradio, distance, SpringLayout.SOUTH, browsercomboBox);
		layout.putConstraint(SpringLayout.WEST, runalltestradio, textstart, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.WEST, selecttestradio, textstart, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, selecttestradio, distance1, SpringLayout.SOUTH, runalltestradio);

		selecttestfunctionality();
		
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

	private void verifyvalues(JPanel panel) throws Exception {
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;
		String msg = "Please fill below details:";

		if (flagloginportal) {
			if (URL.isEmpty()) {
				flag1 = true;
				String message = "Please provide Portal URL";
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
				return;
				// msg=msg+"\\n Portal URL";
			}
			if (!SAML) {

				if (UserName.isEmpty() || Password.isEmpty()) {
					flag1 = true;
					String message = "Please provide test data for fields: Portal Username,Portal Password.";
					JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}

		if (flagloginadmin) {
			if (adminURL[0].isEmpty() || adminUsername[0].isEmpty() || adminPassword[0].isEmpty()) {
				flag1 = true;
				String message = "Please provide test data for field: Server URL,Server Admin Username and Server Admin Password";
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		if (flagloginmanager) {
			if (adminURL[0].isEmpty()) {
				flag1 = true;
				String message = "Please provide test data for fields: Admin URL";
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (federated[0]) {
				if (!SAML) {
					if (UserName.isEmpty() || Password.isEmpty()) {
						flag1 = true;
						String message = "Please provide test data for fields: Portal Admin Username and Portal Admin Password.";
						JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			} else {

				if (adminUsername[0].isEmpty() || adminPassword[0].isEmpty()) {
					flag1 = true;
					String message = "Please provide test data for fields: Server Admin Username , Server Admin Password";
					JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}

		if (!flag1) {

			setvalues(panel);
			saveToconfig(panel);

			frame.dispose();
			cd1 = new DialogControl();

			// showDialog();
			mainTask m_task = new mainTask();
			// Set up to listen for progress changes
			/*
			 * m_task.addPropertyChangeListener( new PropertyChangeListener() { public void
			 * propertyChange(PropertyChangeEvent evt) { if
			 * ("progress".equals(evt.getPropertyName())) {
			 * cd1.progressBar.setValue((Integer)evt.getNewValue()); } } } );
			 */
			// Start the task thread
			m_task.execute();
			// 22worker.execute();
			// runner1.callSanity(browser);
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
			for (int i = 1; i <= no_of_site; i++) {
				if (i == 1) {
					if (!adminurltext1.getText().equals("")) {

						adminURL[0] = adminurltext1.getText().trim();
						// managerURL[0] = adminurltext1.getText().trim() + "/manager";
						adminUsername[0] = adminusernametext1.getText().trim();
						adminPassword[0] = new String(adminpasswordtext1.getPassword()).trim();
						serverRole[0] = serverrolecomboBox1.getSelectedItem().toString();
						/*
						 * if (!federated[0]) { managerUsername[0] = adminUsername[0];
						 * managerPassword[0] = adminPassword[0];
						 * 
						 * } else { managerUsername[0] = UserName; managerPassword[0] = Password; }
						 */
					}
				}
				if (i == 2) {
					if (!adminurltext2.getText().equals("")) {

						adminURL[1] = adminurltext2.getText().trim();
						managerURL[1] = adminurltext2.getText().trim();
						adminUsername[1] = adminusernametext2.getText().trim();
						adminPassword[1] = new String(adminpasswordtext2.getPassword()).trim();
						serverRole[1] = serverrolecomboBox2.getSelectedItem().toString();
						/*
						 * if (!federated[1]) { managerUsername[1] = adminUsername[1];
						 * managerPassword[1] = adminPassword[1];
						 * 
						 * } else { managerUsername[1] = UserName; managerPassword[1] = Password; }
						 */
					}
				}
				if (i == 3) {
					if (!adminurltext3.getText().equals("")) {

						adminURL[2] = adminurltext3.getText().trim();
						managerURL[2] = adminurltext3.getText().trim();
						adminUsername[2] = adminusernametext3.getText().trim();
						adminPassword[2] = new String(adminpasswordtext3.getPassword()).trim();
						serverRole[2] = serverrolecomboBox2.getSelectedItem().toString();
						/*
						 * if (!federated[2]) { managerUsername[2] = adminUsername[2];
						 * managerPassword[2] = adminPassword[2];
						 * 
						 * } else { managerUsername[2] = UserName; managerPassword[2] = Password; }
						 */
					}
				}
				if (i == 4) {
					if (!adminurltext4.getText().equals("")) {

						adminURL[3] = adminurltext4.getText().trim();
						managerURL[3] = adminurltext4.getText().trim();
						adminUsername[3] = adminusernametext4.getText().trim();
						adminPassword[3] = new String(adminpasswordtext4.getPassword()).trim();
						serverRole[3] = serverrolecomboBox4.getSelectedItem().toString();
						/*
						 * if (!federated[3]) { managerUsername[3] = adminUsername[3];
						 * managerPassword[3] = adminPassword[3];
						 * 
						 * } else { managerUsername[3] = UserName; managerPassword[3] = Password; }
						 */
					}
				}
				if (i == 5) {
					if (!adminurltext5.getText().equals("")) {

						adminURL[4] = adminurltext5.getText().trim();
						managerURL[4] = adminurltext5.getText().trim();
						adminUsername[4] = adminusernametext5.getText().trim();
						adminPassword[4] = new String(adminpasswordtext5.getPassword()).trim();
						serverRole[4] = serverrolecomboBox5.getSelectedItem().toString();
						/*
						 * if (!federated[4]) { managerUsername[4] = adminUsername[4];
						 * managerPassword[4] = adminPassword[4];
						 * 
						 * } else { managerUsername[4] = UserName; managerPassword[4] = Password;
						 * 
						 * }
						 */
					}
				}
				if (i == 6) {
					if (!adminurltext6.getText().equals("")) {

						adminURL[5] = adminurltext6.getText().trim();
						managerURL[5] = adminurltext6.getText().trim();
						adminUsername[5] = adminusernametext6.getText().trim();
						adminPassword[5] = new String(adminpasswordtext6.getPassword()).trim();
						serverRole[5] = serverrolecomboBox6.getSelectedItem().toString();
						/*
						 * if (!federated[5]) { managerUsername[5] = adminUsername[5];
						 * managerPassword[5] = adminPassword[5];
						 * 
						 * } else { managerUsername[5] = UserName; managerPassword[5] = Password; }
						 */
					}
				}
			}

			browser = browsercomboBox.getSelectedItem().toString();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Not able to read test values");
		}
	}

	private void readfromconfigdisplay() {
		if (prop.getProperty("Portalurl") != null)
			Portalurl = prop.getProperty("Portalurl");
		if (prop.getProperty("PortalUserName") != null)
			PortalUserName = prop.getProperty("PortalUserName");
		if (prop.getProperty("PortalPassword") != null)
			PortalPassword = prop.getProperty("PortalPassword");

		if (prop.getProperty("Serverurl1") != null)
			Serverurl[0] = prop.getProperty("Serverurl1");

		if (prop.getProperty("Serverurl2") != null)
			Serverurl[1] = prop.getProperty("Serverurl2");

		if (prop.getProperty("Serverurl3") != null)
			Serverurl[2] = prop.getProperty("Serverurl3");

		if (prop.getProperty("Serverurl4") != null)
			Serverurl[3] = prop.getProperty("Serverurl4");

		if (prop.getProperty("Serverurl5") != null)
			Serverurl[4] = prop.getProperty("Serverurl5");

		if (prop.getProperty("Serverurl6") != null)
			Serverurl[5] = prop.getProperty("Serverurl6");
		if (prop.getProperty("ServerAdminUsername1") != null)
			ServerAdminUsername[0] = prop.getProperty("ServerAdminUsername1");
		if (prop.getProperty("ServerAdminPassword1") != null)
			ServerAdminPassword[0] = prop.getProperty("ServerAdminPassword1");
		if (prop.getProperty("ServerAdminUsername2") != null)
			ServerAdminUsername[1] = prop.getProperty("ServerAdminUsername2");
		if (prop.getProperty("ServerAdminPassword2") != null)
			ServerAdminPassword[1] = prop.getProperty("ServerAdminPassword2");
		if (prop.getProperty("ServerAdminUsername3") != null)
			ServerAdminUsername[2] = prop.getProperty("ServerAdminUsername3");
		if (prop.getProperty("ServerAdminPassword3") != null)
			ServerAdminPassword[2] = prop.getProperty("ServerAdminPassword3");
		if (prop.getProperty("ServerAdminUsername4") != null)
			ServerAdminUsername[3] = prop.getProperty("ServerAdminUsername4");
		if (prop.getProperty("ServerAdminPassword4") != null)
			ServerAdminPassword[3] = prop.getProperty("ServerAdminPassword4");
		if (prop.getProperty("ServerAdminUsername5") != null)
			ServerAdminUsername[4] = prop.getProperty("ServerAdminUsername5");
		if (prop.getProperty("ServerAdminPassword5") != null)
			ServerAdminPassword[4] = prop.getProperty("ServerAdminPassword5");
		if (prop.getProperty("ServerAdminUsername6") != null)
			ServerAdminUsername[5] = prop.getProperty("ServerAdminUsername6");
		if (prop.getProperty("ServerAdminPassword6") != null)
			ServerAdminPassword[5] = prop.getProperty("ServerAdminPassword6");

		Browser = prop.getProperty("Browser");
		if (Browser == null) {
			Browser = "Chrome";
		}

		/*
		 * serverRole[0] = prop.getProperty("ServerRole1"); if (serverRole[0] == null) {
		 * serverRole[0] = "Hosting"; } serverRole[1] = prop.getProperty("ServerRole2");
		 * if (serverRole[1] == null) { serverRole[1] = "Hosting"; } serverRole[2] =
		 * prop.getProperty("ServerRole3"); if (serverRole[2] == null) { serverRole[2] =
		 * "Hosting"; } serverRole[3] = prop.getProperty("ServerRole4"); if
		 * (serverRole[3] == null) { serverRole[3] = "Hosting"; } serverRole[4] =
		 * prop.getProperty("ServerRole5"); if (serverRole[4] == null) { serverRole[4] =
		 * "Hosting"; } serverRole[5] = prop.getProperty("ServerRole6"); if
		 * (serverRole[5] == null) { serverRole[5] = "Hosting"; }
		 * 
		 * serverRole[0] = prop.getProperty("ServerRole1"); if (serverRole[0] == null) {
		 * serverRole[0] = "Hosting"; } serverRole[1] = prop.getProperty("ServerRole2");
		 * if (serverRole[1] == null) { serverRole[1] = "Hosting"; } serverRole[2] =
		 * prop.getProperty("ServerRole3"); if (serverRole[2] == null) { serverRole[2] =
		 * "Hosting"; } serverRole[3] = prop.getProperty("ServerRole4"); if
		 * (serverRole[3] == null) { serverRole[3] = "Hosting"; } serverRole[4] =
		 * prop.getProperty("ServerRole5"); if (serverRole[4] == null) { serverRole[4] =
		 * "Hosting"; } serverRole[5] = prop.getProperty("ServerRole6"); if
		 * (serverRole[5] == null) { serverRole[5] = "Hosting"; }
		 */
	}

	private void saveToconfig(JPanel panel) {

		try {

			OutputStream output = new FileOutputStream(
					System.getProperty("user.dir") + "\\Webdriver\\config.properties");

			prop.setProperty("Portalurl", URL);
			prop.setProperty("PortalUserName", UserName);
			prop.setProperty("PortalPassword", Password);
			if (adminURL[0] != "") {
				prop.setProperty("Serverurl1", adminURL[0]);
			}
			if (adminURL[1] != "") {
				prop.setProperty("Serverurl2", adminURL[1]);
			}
			if (adminURL[2] != "") {
				prop.setProperty("Serverurl3", adminURL[2]);
			}
			if (adminURL[3] != "") {
				prop.setProperty("Serverurl4", adminURL[3]);
			}
			if (adminURL[4] != "") {
				prop.setProperty("Serverurl5", adminURL[4]);
			}
			if (adminURL[5] != "") {
				prop.setProperty("Serverurl6", adminURL[5]);
			}
			if (adminUsername[0] != "")
				prop.setProperty("ServerAdminUsername1", adminUsername[0]);
			if (adminUsername[1] != "")
				prop.setProperty("ServerAdminUsername2", adminUsername[1]);
			if (adminUsername[2] != "")
				prop.setProperty("ServerAdminUsername3", adminUsername[2]);
			if (adminUsername[3] != "")
				prop.setProperty("ServerAdminUsername4", adminUsername[3]);
			if (adminUsername[4] != "")
				prop.setProperty("ServerAdminUsername5", adminUsername[4]);
			if (adminUsername[5] != "")
				prop.setProperty("ServerAdminUsername6", adminUsername[5]);
			if (adminPassword[0] != "")
				prop.setProperty("ServerAdminPassword1", adminPassword[0]);
			if (adminPassword[1] != "")
				prop.setProperty("ServerAdminPassword2", adminPassword[1]);
			if (adminPassword[2] != "")
				prop.setProperty("ServerAdminPassword3", adminPassword[2]);
			if (adminPassword[3] != "")
				prop.setProperty("ServerAdminPassword4", adminPassword[3]);
			if (adminPassword[4] != "")
				prop.setProperty("ServerAdminPassword5", adminPassword[4]);
			if (adminPassword[5] != "")
				prop.setProperty("ServerAdminPassword6", adminPassword[5]);
			if (serverRole[0] != "")
				prop.setProperty("ServerRole1", serverRole[0]);
			if (serverRole[1] != "")
				prop.setProperty("ServerRole2", serverRole[1]);
			if (serverRole[2] != "")
				prop.setProperty("ServerRole3", serverRole[2]);
			if (serverRole[3] != "")
				prop.setProperty("ServerRole4", serverRole[3]);
			if (serverRole[4] != "")
				prop.setProperty("ServerRole5", serverRole[4]);
			if (serverRole[5] != "")
				prop.setProperty("ServerRole6", serverRole[5]);
			prop.setProperty("Browser", browsercomboBox.getSelectedItem().toString());
			prop.store(output, null);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private class mainTask extends SwingWorker<Integer, Integer> {
		/**
		 * Constructor.
		 * 
		 * @param bitCount   the number of bits for a prime
		 * @param primeCount the number of primes to generate
		 */

		/**
		 * This method runs in the background, in the worker thread.
		 * 
		 * @return
		 */
		public Integer doInBackground() {
			System.out.println("text");
			TestRunner.callSanity();
			return null;
		}

		/**
		 * Receives data chunks from the publish method asynchronously on the Event
		 * Dispatch Thread.
		 */
		protected void process(Integer n) {

			cd1.txtArea.append(n + "\n");

		}

		/**
		 * Automatically invoked after the doBackground() method is finished. This
		 * method runs in the context of the Event Dispatch Thread
		 */
		@Override
		protected void done() {
			try {
				get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		/**
		 * Returns the next prime number
		 */

	}

}

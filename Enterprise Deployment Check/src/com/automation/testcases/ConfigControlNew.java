package com.automation.testcases;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.JTextComponent;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ConfigControlNew extends JPanel {
	// public static String extentReportFilePath = System.getProperty("user.dir") +
	// "/axeReport";
	public static String projectName = "Enterprise Deployment Check Test";
	private static JTextField portalurltext;
	private static JLabel portalurllabel;
	public static Properties prop;
	private static JLabel portalusernamelabel;
	private static JLabel addsite;
	private static JLabel portalpasswordlabel;
	private static JLabel managerusernamelabel;
	private static JLabel managerurllabel;
	private static JLabel managerpasswordlabel;
	private static JLabel header1;
	private static JLabel header2;
	private static JLabel header3;
	private static JLabel header4;
	private static JLabel adminurllabel1;
	private static JLabel adminurllabel2;
	private static JLabel adminurllabel3;
	private static JLabel adminurllabel4;
	private static JLabel adminurllabel5;
	private static JLabel adminurllabel6;
	private static JLabel adminurllabel7;
	private static JLabel adminurllabel8;
	private static JLabel adminurllabel9;
	private static JLabel adminurllabel10;
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
	private static JRadioButton federatedradio7;
	private static JRadioButton nonfederatedradio7;
	private static JRadioButton federatedradio8;
	private static JRadioButton nonfederatedradio8;
	private static JRadioButton federatedradio9;
	private static JRadioButton nonfederatedradio9;
	private static JRadioButton federatedradio10;
	private static JRadioButton nonfederatedradio10;
	private static JCheckBox kubernetes;
	private static JLabel kuberneteslabel;
	public static FileInputStream input = null;
	// public static String extentReportFilePath = null;
	public static JScrollPane scrollpane1;
	public static JScrollPane scrollpane2;
	public static JScrollPane scrollpane3;
	public static JCheckBox runalltest, loginportal, loginmanager, loginserver, groupfunctionality, webappbuilder,
			organization, scenelayer, feature, tile, map, hosting, datastore,experiencebuilder;

	private static JTextField portalusernametext;
	private static JPasswordField portalpasswordtext;
	private static JTextField adminurltext1;
	private static JTextField adminurltext2;
	private static JTextField adminurltext3;
	private static JTextField adminurltext4;
	private static JTextField adminurltext5;
	private static JTextField adminurltext6;
	private static JTextField adminurltext7;
	private static JTextField adminurltext8;
	private static JTextField adminurltext9;
	private static JTextField adminurltext10;
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
	private static JLabel adminusernamelabel7;
	private static JLabel adminpasswordlabel7;
	private static JTextField adminusernametext7;
	private static JPasswordField adminpasswordtext7;
	private static JLabel adminusernamelabel8;
	private static JLabel adminpasswordlabel8;
	private static JTextField adminusernametext8;
	private static JPasswordField adminpasswordtext8;
	private static JLabel adminusernamelabel9;
	private static JLabel adminpasswordlabel9;
	private static JTextField adminusernametext9;
	private static JPasswordField adminpasswordtext9;
	private static JLabel adminusernamelabel10;
	private static JLabel adminpasswordlabel10;
	private static JTextField adminusernametext10;
	private static JPasswordField adminpasswordtext10;

	private JButton clearBtn;
	// public static String URL;

	// public static String UserName;
	// public static String Password;
	public static boolean flagKUB = false;
	public static boolean uirun = false;
	public static boolean stop = false;
	public static boolean submitdefect = true;
	public static boolean flagrun=true;
	// public static String browser;
	static JScrollPane scroll = new JScrollPane();
	static SpringLayout layout = new SpringLayout();
	// final static JPanel panel = new JPanel();

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
	private static JComboBox<String> serverrolecomboBox7;
	static JLabel serverrolelabel7;
	private static JComboBox<String> serverrolecomboBox8;
	static JLabel serverrolelabel8;
	private static JComboBox<String> serverrolecomboBox9;
	static JLabel serverrolelabel9;
	private static JComboBox<String> serverrolecomboBox10;
	static JLabel serverrolelabel10;
	protected static String Portalurl = "";
	protected static String PortalUserName = "";
	protected static String PortalPassword = "";
	private String[] serverRolevalues = new String[] { "Hosting Server", "Raster Analysis Server", "Notebook Server",
			"Workflow Manager Server" };
	// variables to read config file and display on UI

	public static String[] Serverurl = new String[] { "", "", "", "", "", "", "", "", "", "" };
	public static String[] ServerAdminUsername = new String[] { "", "", "", "", "", "", "", "", "", "" };
	public static String[] ServerAdminPassword = new String[] { "", "", "", "", "", "", "", "", "", "" };;
	public static String[] ServerAdminRole = new String[] { "Hosting Server", "Hosting Server", "Hosting Server",
			"Hosting Server", "Hosting Server", "Hosting Server", "Hosting Server", "Hosting Server", "Hosting Server",
			"Hosting Server" };
	public static Boolean[] ServerFederated = new Boolean[] { true, true, true, true, true, true, true, true, true,
			true };

	public static String Browser = "";
	public static int no_of_site = 1;
	public static int totalSite = 10;
	static int textlength = 200;
	static int labelstart = 50;
	static int textstart = 255;
	static int distance = 10;
	static int distance1 = 2;
	String Portalurltooltip = "e.g. https://myportal.esri.com/portal";
	String Serverurltooltip = "e.g. https://myportal.esri.com/server";
	String Managerurltooltip = "e.g. https://myportal.esri.com/server/manager";
	// static int h1 = 25;
	// static int h2 = 17;
	// static int w1 = 200;
	public static boolean  configread=false;
	public static boolean flagRunalltest = false;
	public static boolean flagloginadmin = false;
	public static boolean flagloginmanager = false;
	public static boolean flagloginportal = false;
	public static boolean flagtilelayer = false;
	public static boolean flagfeaturelayer = false;
	public static boolean flaggroup = false;
	public static boolean flagorganization = false;
	public static boolean flagscenelayer = false;
	public static boolean scenecreated = false;
	public static boolean flagdatastore = false;
	public static boolean flagexperiencebuilder=false;
	// public static boolean exe = false;
	public static boolean flagmap = false;
	public static boolean flagwebappbuilder = false;
	public static boolean flagcontent = false;
	public static boolean flagServerRole = false;
	private static JRadioButton runalltestradio;
	private static JRadioButton selecttestradio;
	/////////////////////////////////////////////////////////
	long startTime = System.currentTimeMillis();
	long nowTime = System.currentTimeMillis();
	long elapsedTime = 0;
	public static Timer timer = new Timer(1000, null);
	static String frameTitle = "Enterprise Deployment Check - BETA";
	// public static DialogControl cd1=new DialogControl();
	static JFrame frame = new JFrame(frameTitle);
	private static final long serialVersionUID = 1L;

	private boolean DEBUG = false;
	public static JTextPane txtPane;
	public static JProgressBar progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 500);
	public static JScrollPane jsp;
	public static Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	public static Border lineBorder1 = BorderFactory.createLineBorder(Color.GRAY, 0);
	public static Border lineBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
	public static Border border = BorderFactory.createLineBorder(Color.GRAY, 2);
	static JLabel reportlabel = new JLabel("<html><U><b>Reports</b></U></HTML>");
	// reportlabel.setBackground(Color.decode("#9896aa"));
	JLabel timedisplay = new JLabel("");
	// reportlabel.setForeground(Color.BLACK);
	// reportlabel.setFont(new Font("Verdana", Font.PLAIN, 15));
	// static JTable table3;
	// static DefaultTableModel dtm3;
	public static int h1 = 25;
	public static int w1 = 200;
	public static int w2 = 250;
	public static int progress = 0;
	static int noofrow = 0;
	static JLabel msglabel = new JLabel("Saving configuration...", JLabel.RIGHT);
	static JLabel close1 = new JLabel("<html><U>Remove</U></html>");
	static JLabel close2 = new JLabel("<html><U>Remove</U></html>");
	static JLabel close3 = new JLabel("<html><U>Remove</U></html>");
	static JLabel close4 = new JLabel("<html><U>Remove</U></html>");
	static JLabel close5 = new JLabel("<html><U>Remove</U></html>");
	static JLabel close6 = new JLabel("<html><U>Remove</U></html>");
	static JLabel close7 = new JLabel("<html><U>Remove</U></html>");
	static JLabel close8 = new JLabel("<html><U>Remove</U></html>");
	static JLabel close9 = new JLabel("<html><U>Remove</U></html>");
	static JButton saveBtn = new JButton("<html><span>Save</span></html>");
	static JLabel test = new JLabel();
	// static JButton saveBtn = new JButton("SAVE");
	public static PrintStream printwrite;
	static JButton run = new JButton("<html><span>Run</span></html>");
	static Icon ico = new ImageIcon(
			System.getProperty("user.dir") + File.separator + "Input" + File.separator + "eye_icon_open.png");
	static Icon ico1 = new ImageIcon(
			System.getProperty("user.dir") + File.separator + "Input" + File.separator + "eye_icon_closed.png");
	static JButton toggleButton1 = new JButton(ico);
	static JButton toggleButton2 = new JButton(ico);
	static JButton toggleButton3 = new JButton(ico);
	static JButton toggleButton4 = new JButton(ico);
	static JButton toggleButton5 = new JButton(ico);
	static JButton toggleButton6 = new JButton(ico);
	static JButton toggleButton7 = new JButton(ico);
	static JButton toggleButton8 = new JButton(ico);
	static JButton toggleButton9 = new JButton(ico);
	static JButton toggleButton10 = new JButton(ico);
	static JButton toggleButton11 = new JButton(ico);
	// static JButton toggleButton1 = new JButton("???"); ;

	static JButton stopscan = new JButton("<html><span>Stop Test</html>");

	static JTabbedPane tabs = new JTabbedPane();
	static JPanel mainpanel = new JPanel();
	static JPanel resultPanel = new JPanel();
	private JPanel reportPanel = new JPanel();
	private JPanel progressBarPanel = new JPanel();
	private JPanel statusPanel = new JPanel();
	static JPanel parameterPanel = new JPanel();
	static JPanel testPanel = new JPanel();
	static JPanel panel1 = new JPanel();
	static JPanel panel1_0 = new JPanel();
	static JPanel panel1_1 = new JPanel();
	static JPanel panel1_2 = new JPanel();
	static JPanel panel1_3 = new JPanel();
	static JPanel panel1_4 = new JPanel();
	static JPanel panel1_5 = new JPanel();
	static JPanel panel1_6 = new JPanel();
	static JPanel panel1_7 = new JPanel();
	static JPanel panel1_8 = new JPanel();
	static JPanel panel1_9 = new JPanel();
	static JPanel panel1_10 = new JPanel();
	static JPanel panel1_11 = new JPanel();
	static JPanel panel1_12 = new JPanel();

	static JPanel panel2 = new JPanel();
	static JPanel panel2_1 = new JPanel();
	static JPanel panel2_2 = new JPanel();
	static JPanel panel2_3 = new JPanel();
	static JPanel messagepanel = new JPanel();
	static JPanel buttonpanel = new JPanel();

	public static boolean flagcomplete = false;

	public static Date datef = new Date();
	// public static SimpleDateFormat dateformat = new
	// SimpleDateFormat("yyyy-MM-dd");
	public static String resultfolder = System.getProperty("user.dir") + "\\Enterprise Deployment Check Results\\";
	// + dateformat.format(datef);
	public static String reportPath = "";
	public static String screenshotPath1 = "";
	public static String logPath = "";
	public static String logName = "";
	public static String reportName = "";
	public static JDialog d = new JDialog(frame, "Saving Configuration");
	private static final String sheetName = "Configuration";
	// public static String dataLocation = System.getProperty("user.dir") +
	// "\\Datasource\\";
	public static ExtentSparkReporter htmlReporter;
	public static ExtentSparkReporter htmlReporter_latest;
	public static String extentReportFilePath_BrowserNameOnly = null;
	public static ExtentReports extentReports = null;
	public static RemoteWebDriver driver;
	public static ExtentTest logger, logger1;
	public static ExtentTest parent;
	public static boolean flagreport = false;

	public static JLabel internal = new JLabel();
	// final Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();

	//
	// public static FileReader reader1;
	// public static FileReader reader2;
	public static FileReader reader3;
	mainTask m_task = new mainTask();

	private static void rundisabled() {
		run.setEnabled(false);

	}

	private static void runenabled() {
		run.setEnabled(true);

	}

	public void Initialize() {

		try {

			readfromconfigdisplay();

		} catch (Exception e) {
			e.printStackTrace();
			// JOptionPane.showMessageDialog(frame, "Error loading data. Please check the
			// Test data sheet!");
		}
		portalurllabel = new JLabel("Portal URL*", JLabel.RIGHT);// make label and assign text in 1 line
		portalurltext = new JTextField(Portalurl);
		portalurltext.setToolTipText(Portalurltooltip);
		portalusernamelabel = new JLabel("Admin Username*", JLabel.RIGHT);
		portalusernametext = new JTextField(PortalUserName);
		portalpasswordlabel = new JLabel("Admin Password*", JLabel.RIGHT);
		portalpasswordtext = new JPasswordField(PortalPassword);
		portalpasswordtext.setEchoChar('*');
		kubernetes = new JCheckBox("ArcGIS Enterprise for Kubernetes");
		
		// toggleButton1 = new JButton("???"); // Use an emoji or custom icon
		// toggleButton1.setMinimumSize(new Dimension(40, 70));
		// toggleButton1.setPreferredSize(new Dimension(70, 40));
		// toggleButton1.setPreferredSize(new Dimension(40, 40));
		// Add action listener to toggle visibility
		toggleButton1.addActionListener(new ActionListener() {
			private boolean isPasswordVisible = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible) {
					portalpasswordtext.setEchoChar('*'); // Hide password
					toggleButton1.setIcon(ico);
					// toggleButton1.setText("???"); // Update icon
					// toggleButton1.setText("<html><span>&#128065;</span></html>");
				} else {
					portalpasswordtext.setEchoChar((char) 0); // Show password
					toggleButton1.setIcon(ico1);
					// toggleButton1.setText("??"); // Update icon
				}
				isPasswordVisible = !isPasswordVisible;
			}
		});
		toggleButton2.addActionListener(new ActionListener() {
			private boolean isPasswordVisible = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible) {
					adminpasswordtext1.setEchoChar('*'); // Hide password
					toggleButton2.setIcon(ico);
					// toggleButton1.setText("???"); // Update icon
					// toggleButton1.setText("<html><span>&#128065;</span></html>");
				} else {
					adminpasswordtext1.setEchoChar((char) 0); // Show password
					toggleButton2.setIcon(ico1);
					// toggleButton1.setText("??"); // Update icon
				}
				isPasswordVisible = !isPasswordVisible;
			}
		});
		toggleButton3.addActionListener(new ActionListener() {
			private boolean isPasswordVisible = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible) {
					adminpasswordtext2.setEchoChar('*'); // Hide password
					toggleButton3.setIcon(ico);
					// toggleButton1.setText("???"); // Update icon
					// toggleButton1.setText("<html><span>&#128065;</span></html>");
				} else {
					adminpasswordtext2.setEchoChar((char) 0); // Show password
					toggleButton3.setIcon(ico1);
					// toggleButton1.setText("??"); // Update icon
				}
				isPasswordVisible = !isPasswordVisible;
			}
		});
		toggleButton4.addActionListener(new ActionListener() {
			private boolean isPasswordVisible = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible) {
					adminpasswordtext3.setEchoChar('*'); // Hide password
					toggleButton4.setIcon(ico);
					// toggleButton1.setText("???"); // Update icon
					// toggleButton1.setText("<html><span>&#128065;</span></html>");
				} else {
					adminpasswordtext3.setEchoChar((char) 0); // Show password
					toggleButton4.setIcon(ico1);
					// toggleButton1.setText("??"); // Update icon
				}
				isPasswordVisible = !isPasswordVisible;
			}
		});
		toggleButton5.addActionListener(new ActionListener() {
			private boolean isPasswordVisible = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible) {
					adminpasswordtext4.setEchoChar('*'); // Hide password
					toggleButton5.setIcon(ico);
					// toggleButton1.setText("???"); // Update icon
					// toggleButton1.setText("<html><span>&#128065;</span></html>");
				} else {
					adminpasswordtext4.setEchoChar((char) 0); // Show password
					toggleButton5.setIcon(ico1);
					// toggleButton1.setText("??"); // Update icon
				}
				isPasswordVisible = !isPasswordVisible;
			}
		});
		toggleButton6.addActionListener(new ActionListener() {
			private boolean isPasswordVisible = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible) {
					adminpasswordtext5.setEchoChar('*'); // Hide password
					toggleButton6.setIcon(ico);
					// toggleButton1.setText("???"); // Update icon
					// toggleButton1.setText("<html><span>&#128065;</span></html>");
				} else {
					adminpasswordtext5.setEchoChar((char) 0); // Show password
					toggleButton6.setIcon(ico1);
					// toggleButton1.setText("??"); // Update icon
				}
				isPasswordVisible = !isPasswordVisible;
			}
		});
		toggleButton7.addActionListener(new ActionListener() {
			private boolean isPasswordVisible = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible) {
					adminpasswordtext6.setEchoChar('*'); // Hide password
					toggleButton7.setIcon(ico);
					// toggleButton1.setText("???"); // Update icon
					// toggleButton1.setText("<html><span>&#128065;</span></html>");
				} else {
					adminpasswordtext6.setEchoChar((char) 0); // Show password
					toggleButton7.setIcon(ico1);
					// toggleButton1.setText("??"); // Update icon
				}
				isPasswordVisible = !isPasswordVisible;
			}
		});
		toggleButton8.addActionListener(new ActionListener() {
			private boolean isPasswordVisible = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible) {
					adminpasswordtext7.setEchoChar('*'); // Hide password
					toggleButton8.setIcon(ico);
					// toggleButton1.setText("???"); // Update icon
					// toggleButton1.setText("<html><span>&#128065;</span></html>");
				} else {
					adminpasswordtext7.setEchoChar((char) 0); // Show password
					toggleButton8.setIcon(ico1);
					// toggleButton1.setText("??"); // Update icon
				}
				isPasswordVisible = !isPasswordVisible;
			}
		});
		toggleButton9.addActionListener(new ActionListener() {
			private boolean isPasswordVisible = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible) {
					adminpasswordtext8.setEchoChar('*'); // Hide password
					toggleButton9.setIcon(ico);
					// toggleButton1.setText("???"); // Update icon
					// toggleButton1.setText("<html><span>&#128065;</span></html>");
				} else {
					adminpasswordtext8.setEchoChar((char) 0); // Show password
					toggleButton9.setIcon(ico1);
					// toggleButton1.setText("??"); // Update icon
				}
				isPasswordVisible = !isPasswordVisible;
			}
		});
		toggleButton10.addActionListener(new ActionListener() {
			private boolean isPasswordVisible = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible) {
					adminpasswordtext9.setEchoChar('*'); // Hide password
					toggleButton10.setIcon(ico);
					// toggleButton1.setText("???"); // Update icon
					// toggleButton1.setText("<html><span>&#128065;</span></html>");
				} else {
					adminpasswordtext9.setEchoChar((char) 0); // Show password
					toggleButton10.setIcon(ico1);
					// toggleButton1.setText("??"); // Update icon
				}
				isPasswordVisible = !isPasswordVisible;
			}
		});
		toggleButton11.addActionListener(new ActionListener() {
			private boolean isPasswordVisible = false;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (isPasswordVisible) {
					adminpasswordtext10.setEchoChar('*'); // Hide password
					toggleButton11.setIcon(ico);
					// toggleButton1.setText("???"); // Update icon
					// toggleButton1.setText("<html><span>&#128065;</span></html>");
				} else {
					adminpasswordtext10.setEchoChar((char) 0); // Show password
					toggleButton11.setIcon(ico1);
					// toggleButton1.setText("??"); // Update icon
				}
				isPasswordVisible = !isPasswordVisible;
			}
		});
		federatedradio1 = new JRadioButton("federated", true);
		nonfederatedradio1 = new JRadioButton("non federated");
		ButtonGroup group1 = new ButtonGroup();
		group1.add(federatedradio1);
		group1.add(nonfederatedradio1);
		nonfederatedradio1.setEnabled(false);
		if (ServerFederated[0]) {
			federatedradio1.setSelected(true);
		} else {
			nonfederatedradio1.setSelected(true);
		}
		federatedradio2 = new JRadioButton("federated", true);
		nonfederatedradio2 = new JRadioButton("non federated");
		ButtonGroup group2 = new ButtonGroup();
		group2.add(federatedradio2);
		group2.add(nonfederatedradio2);
		nonfederatedradio2.setEnabled(false);
		if (ServerFederated[1]) {
			federatedradio2.setSelected(true);
		} else {
			nonfederatedradio2.setSelected(true);
		}
		federatedradio3 = new JRadioButton("federated", true);
		nonfederatedradio3 = new JRadioButton("non federated");
		ButtonGroup group3 = new ButtonGroup();
		group3.add(federatedradio3);
		group3.add(nonfederatedradio3);
		nonfederatedradio3.setEnabled(false);
		if (ServerFederated[2]) {
			federatedradio3.setSelected(true);
		} else {
			nonfederatedradio3.setSelected(true);
		}
		federatedradio4 = new JRadioButton("federated", true);
		nonfederatedradio4 = new JRadioButton("non federated");
		ButtonGroup group4 = new ButtonGroup();
		group4.add(federatedradio4);
		group4.add(nonfederatedradio4);
		nonfederatedradio4.setEnabled(false);
		if (ServerFederated[3]) {
			federatedradio4.setSelected(true);
		} else {
			nonfederatedradio4.setSelected(true);
		}
		federatedradio5 = new JRadioButton("federated", true);
		nonfederatedradio5 = new JRadioButton("non federated");
		ButtonGroup group5 = new ButtonGroup();
		group5.add(federatedradio5);
		group5.add(nonfederatedradio5);
		nonfederatedradio5.setEnabled(false);
		if (ServerFederated[4]) {
			federatedradio5.setSelected(true);
		} else {
			nonfederatedradio5.setSelected(true);
		}
		federatedradio6 = new JRadioButton("federated", true);
		nonfederatedradio6 = new JRadioButton("non federated");
		ButtonGroup group6 = new ButtonGroup();
		group6.add(federatedradio6);
		group6.add(nonfederatedradio6);
		nonfederatedradio6.setEnabled(false);
		if (ServerFederated[5]) {
			federatedradio6.setSelected(true);
		} else {
			nonfederatedradio6.setSelected(true);
		}
		federatedradio7 = new JRadioButton("federated", true);
		nonfederatedradio7 = new JRadioButton("non federated");
		ButtonGroup group7 = new ButtonGroup();
		group7.add(federatedradio7);
		group7.add(nonfederatedradio7);
		nonfederatedradio7.setEnabled(false);
		if (ServerFederated[6]) {
			federatedradio7.setSelected(true);
		} else {
			nonfederatedradio7.setSelected(true);
		}
		federatedradio8 = new JRadioButton("federated", true);
		nonfederatedradio8 = new JRadioButton("non federated");
		nonfederatedradio8.setEnabled(false);
		ButtonGroup group8 = new ButtonGroup();
		group8.add(federatedradio8);
		group8.add(nonfederatedradio8);
		if (ServerFederated[7]) {
			federatedradio8.setSelected(true);
		} else {
			nonfederatedradio8.setSelected(true);
		}
		federatedradio9 = new JRadioButton("federated", true);
		nonfederatedradio9 = new JRadioButton("non federated");
		nonfederatedradio9.setEnabled(false);
		ButtonGroup group9 = new ButtonGroup();
		group9.add(federatedradio9);
		group9.add(nonfederatedradio9);
		if (ServerFederated[8]) {
			federatedradio9.setSelected(true);
		} else {
			nonfederatedradio9.setSelected(true);
		}
		federatedradio10 = new JRadioButton("federated", true);
		nonfederatedradio10 = new JRadioButton("non federated");
		ButtonGroup group10 = new ButtonGroup();
		group10.add(federatedradio10);
		group10.add(nonfederatedradio10);
		nonfederatedradio10.setEnabled(false);
		if (ServerFederated[9]) {
			federatedradio10.setSelected(true);
		} else {
			nonfederatedradio10.setSelected(true);
		}

		serverrolelabel1 = new JLabel("Server Role", JLabel.RIGHT);
		serverrolecomboBox1 = new JComboBox<String>(serverRolevalues);
		// serverrolecomboBox1.setSelectedIndex(0);
		serverrolecomboBox1.setSelectedItem(ServerAdminRole[0]);
		serverrolelabel2 = new JLabel("Server Role", JLabel.RIGHT);// make label and assign text in 1 line
		serverrolecomboBox2 = new JComboBox<String>(serverRolevalues);
		// serverrolecomboBox2.setSelectedIndex(0);
		serverrolecomboBox2.setSelectedItem(ServerAdminRole[1]);
		serverrolelabel3 = new JLabel("Server Role", JLabel.RIGHT);// make label and assign text in 1 line
		serverrolecomboBox3 = new JComboBox<String>(serverRolevalues);
		// serverrolecomboBox3.setSelectedIndex(0);
		serverrolecomboBox3.setSelectedItem(ServerAdminRole[2]);
		serverrolelabel4 = new JLabel("Server Role", JLabel.RIGHT);// make label and assign text in 1 line
		serverrolecomboBox4 = new JComboBox<String>(serverRolevalues);
		// serverrolecomboBox4.setSelectedIndex(0);
		serverrolecomboBox4.setSelectedItem(ServerAdminRole[3]);
		serverrolelabel5 = new JLabel("Server Role", JLabel.RIGHT);// make label and assign text in 1 line
		serverrolecomboBox5 = new JComboBox<String>(serverRolevalues);
		// serverrolecomboBox5.setSelectedIndex(0);
		serverrolecomboBox5.setSelectedItem(ServerAdminRole[4]);
		serverrolelabel6 = new JLabel("Server Role", JLabel.RIGHT);// make label and assign text in 1 line
		serverrolecomboBox6 = new JComboBox<String>(serverRolevalues);
		// serverrolecomboBox6.setSelectedIndex(0);
		serverrolecomboBox6.setSelectedItem(ServerAdminRole[5]);
		serverrolelabel7 = new JLabel("Server Role", JLabel.RIGHT);// make label and assign text in 1 line
		serverrolecomboBox7 = new JComboBox<String>(serverRolevalues);
		// serverrolecomboBox7.setSelectedIndex(0);
		serverrolecomboBox7.setSelectedItem(ServerAdminRole[6]);
		serverrolelabel8 = new JLabel("Server Role", JLabel.RIGHT);// make label and assign text in 1 line
		serverrolecomboBox8 = new JComboBox<String>(serverRolevalues);
		// serverrolecomboBox8.setSelectedIndex(0);
		serverrolecomboBox8.setSelectedItem(ServerAdminRole[7]);
		serverrolelabel9 = new JLabel("Server Role", JLabel.RIGHT);// make label and assign text in 1 line
		serverrolecomboBox9 = new JComboBox<String>(serverRolevalues);
		// serverrolecomboBox9.setSelectedIndex(0);
		serverrolecomboBox9.setSelectedItem(ServerAdminRole[8]);
		serverrolelabel10 = new JLabel("Server Role", JLabel.RIGHT);// make label and assign text in 1 line
		serverrolecomboBox10 = new JComboBox<String>(serverRolevalues);
		// serverrolecomboBox10.setSelectedIndex(0);
		serverrolecomboBox10.setSelectedItem(ServerAdminRole[9]);
		addsite = new JLabel();
		addsite.setText("<HTML><U>Click to add another Server(max 10)</U></HTML>");
		adminurllabel1 = new JLabel("Server URL 1", JLabel.RIGHT);
		adminurltext1 = new JTextField(Serverurl[0]);
		adminurltext1.setToolTipText(Serverurltooltip);

		adminurllabel2 = new JLabel("Server URL 2", JLabel.RIGHT);
		adminurltext2 = new JTextField(Serverurl[1]);
		adminurltext2.setToolTipText(Serverurltooltip);

		adminurllabel3 = new JLabel("Server URL 3", JLabel.RIGHT);
		adminurltext3 = new JTextField(Serverurl[2]);
		adminurltext3.setToolTipText(Serverurltooltip);

		adminurllabel4 = new JLabel("Server URL 4", JLabel.RIGHT);
		adminurltext4 = new JTextField(Serverurl[3]);
		adminurltext4.setToolTipText(Serverurltooltip);

		adminurllabel5 = new JLabel("Server URL 5", JLabel.RIGHT);
		adminurltext5 = new JTextField(Serverurl[4]);
		adminurltext5.setToolTipText(Serverurltooltip);

		adminurllabel6 = new JLabel("Server URL 6", JLabel.RIGHT);
		adminurltext6 = new JTextField(Serverurl[5]);
		adminurltext6.setToolTipText(Serverurltooltip);

		adminurllabel7 = new JLabel("Server URL 7", JLabel.RIGHT);
		adminurltext7 = new JTextField(Serverurl[6]);
		adminurltext7.setToolTipText(Serverurltooltip);

		adminurllabel8 = new JLabel("Server URL 8", JLabel.RIGHT);
		adminurltext8 = new JTextField(Serverurl[7]);
		adminurltext8.setToolTipText(Serverurltooltip);

		adminurllabel9 = new JLabel("Server URL 9", JLabel.RIGHT);
		adminurltext9 = new JTextField(Serverurl[8]);
		adminurltext9.setToolTipText(Serverurltooltip);

		adminurllabel10 = new JLabel("Server URL 10", JLabel.RIGHT);
		adminurltext10 = new JTextField(Serverurl[9]);
		adminurltext10.setToolTipText(Serverurltooltip);

		adminusernamelabel1 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
		adminusernametext1 = new JTextField(ServerAdminUsername[0]);
		adminpasswordlabel1 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
		adminpasswordtext1 = new JPasswordField(ServerAdminPassword[0]);
		adminpasswordtext1.setEchoChar('*');
		adminusernamelabel2 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
		adminusernametext2 = new JTextField(ServerAdminUsername[1]);
		adminpasswordlabel2 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
		adminpasswordtext2 = new JPasswordField(ServerAdminPassword[1]);
		adminpasswordtext2.setEchoChar('*');
		adminusernamelabel3 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
		adminusernametext3 = new JTextField(ServerAdminUsername[2]);
		adminpasswordlabel3 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
		adminpasswordtext3 = new JPasswordField(ServerAdminPassword[2]);
		adminpasswordtext3.setEchoChar('*');
		adminusernamelabel4 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
		adminusernametext4 = new JTextField(ServerAdminUsername[3]);
		adminpasswordlabel4 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
		adminpasswordtext4 = new JPasswordField(ServerAdminPassword[3]);
		adminpasswordtext4.setEchoChar('*');
		adminusernamelabel5 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
		adminusernametext5 = new JTextField(ServerAdminUsername[4]);
		adminpasswordlabel5 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
		adminpasswordtext5 = new JPasswordField(ServerAdminPassword[4]);
		adminpasswordtext5.setEchoChar('*');
		adminusernamelabel6 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
		adminusernametext6 = new JTextField(ServerAdminUsername[5]);
		adminpasswordlabel6 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
		adminpasswordtext6 = new JPasswordField(ServerAdminPassword[5]);
		adminpasswordtext6.setEchoChar('*');
		adminusernamelabel7 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
		adminusernametext7 = new JTextField(ServerAdminUsername[6]);
		adminpasswordlabel7 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
		adminpasswordtext7 = new JPasswordField(ServerAdminPassword[6]);
		adminpasswordtext7.setEchoChar('*');
		adminusernamelabel8 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
		adminusernametext8 = new JTextField(ServerAdminUsername[7]);
		adminpasswordlabel8 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
		adminpasswordtext8 = new JPasswordField(ServerAdminPassword[7]);
		adminpasswordtext8.setEchoChar('*');
		adminusernamelabel9 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
		adminusernametext9 = new JTextField(ServerAdminUsername[8]);
		adminpasswordlabel9 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
		adminpasswordtext9 = new JPasswordField(ServerAdminPassword[8]);
		adminpasswordtext9.setEchoChar('*');
		adminusernamelabel10 = new JLabel("Server Admin Username*", JLabel.RIGHT);// make label and assign text in 1
		adminusernametext10 = new JTextField(ServerAdminUsername[9]);
		adminpasswordlabel10 = new JLabel("Server Admin Password*", JLabel.RIGHT);// make label and assign text in 1
		adminpasswordtext10 = new JPasswordField(ServerAdminPassword[9]);
		adminpasswordtext10.setEchoChar('*');
		browserlabel = new JLabel("Which browser do you use?", JLabel.RIGHT);// make label and assign text in 1 line

		browsercomboBox = new JComboBox<String>();

		browsercomboBox.addItem("Chrome");
		browsercomboBox.addItem("Edge");
		browsercomboBox.setSelectedItem(Browser);
		
		//kubernetes.setBorderPainted(false);
		
		// runalltestradio = new JRadioButton("Run all tests", false);

		// selecttestradio = new JRadioButton("Select Tests to Run", true);

		// ButtonGroup group = new ButtonGroup();
		//// group.add(runalltestradio);
		// group.add(selecttestradio);
		runalltest = new JCheckBox("Run All Test");

		loginportal = new JCheckBox("Login to Portal");
		loginmanager = new JCheckBox("Login to Server Manager");
		loginserver = new JCheckBox("Login to server Admin");
		groupfunctionality = new JCheckBox("Create Group/Member and share content");
		webappbuilder = new JCheckBox("Create Webapp Builder App");
		organization = new JCheckBox("Check Organization functionality");
		scenelayer = new JCheckBox("Check SceneLayer functionality(11.1 and above versions)");
		feature = new JCheckBox("Create Feature Layer");
		tile = new JCheckBox("Create Tile Layer");
		map = new JCheckBox("Create WebMap");
		experiencebuilder = new JCheckBox("Create Experience Builder App(12.0 and above versions)");
		// content = new JCheckBox("Create Folder");
		hosting = new JCheckBox("Validate Server Role(11.3 and above versions)");
		datastore = new JCheckBox("Validate Data Stores");
		kubernetes.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					flagKUB = true;
					flagdatastore=false;
					//flagServerRole=false;
					testRunSetting();

				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					flagKUB = false;
					
					testRunSetting();
					
				}

			}
		});
		
		testRunSetting();
		edittextfieldRundisabled(portalurltext);
		edittextfieldRundisabled(portalusernametext);
		edittextfieldRundisabled(portalpasswordtext);
		editCheckboxRundisabled(kubernetes);
		edittextfieldRundisabled(adminurltext1);
		edittextfieldRundisabled(adminusernametext1);
		edittextfieldRundisabled(adminpasswordtext1);
		edittextfieldRundisabled(adminurltext2);
		edittextfieldRundisabled(adminusernametext2);
		edittextfieldRundisabled(adminpasswordtext2);
		edittextfieldRundisabled(adminurltext3);
		edittextfieldRundisabled(adminusernametext3);
		edittextfieldRundisabled(adminpasswordtext3);
		edittextfieldRundisabled(adminurltext4);
		edittextfieldRundisabled(adminusernametext4);
		edittextfieldRundisabled(adminpasswordtext4);
		edittextfieldRundisabled(adminurltext5);
		edittextfieldRundisabled(adminusernametext5);
		edittextfieldRundisabled(adminpasswordtext5);
		edittextfieldRundisabled(adminurltext6);
		edittextfieldRundisabled(adminusernametext6);
		edittextfieldRundisabled(adminpasswordtext6);
		edittextfieldRundisabled(adminurltext7);
		edittextfieldRundisabled(adminusernametext7);
		edittextfieldRundisabled(adminpasswordtext7);
		edittextfieldRundisabled(adminurltext8);
		edittextfieldRundisabled(adminusernametext8);
		edittextfieldRundisabled(adminpasswordtext8);
		edittextfieldRundisabled(adminurltext9);
		edittextfieldRundisabled(adminusernametext9);
		edittextfieldRundisabled(adminpasswordtext9);
		edittextfieldRundisabled(adminurltext10);
		edittextfieldRundisabled(adminusernametext10);
		edittextfieldRundisabled(adminpasswordtext10);

		editcheckboxRundisabled(runalltest);
		editcheckboxRundisabled(loginportal);
		editcheckboxRundisabled(loginserver);
		editcheckboxRundisabled(loginmanager);
		// editcheckboxRundisabled(content);
		editcheckboxRundisabled(feature);
		editcheckboxRundisabled(tile);
		editcheckboxRundisabled(map);
		editcheckboxRundisabled(experiencebuilder);
		editcheckboxRundisabled(webappbuilder);
		editcheckboxRundisabled(scenelayer);
		editcheckboxRundisabled(organization);
		editcheckboxRundisabled(datastore);
		editcheckboxRundisabled(groupfunctionality);
		editcheckboxRundisabled(hosting);
		editcomboboxRundisabled(browsercomboBox);
		editcomboboxRundisabled(serverrolecomboBox1);
		editcomboboxRundisabled(serverrolecomboBox2);
		editcomboboxRundisabled(serverrolecomboBox3);
		editcomboboxRundisabled(serverrolecomboBox4);
		editcomboboxRundisabled(serverrolecomboBox5);
		editcomboboxRundisabled(serverrolecomboBox6);
		editcomboboxRundisabled(serverrolecomboBox7);
		editcomboboxRundisabled(serverrolecomboBox8);
		editcomboboxRundisabled(serverrolecomboBox9);
		editcomboboxRundisabled(serverrolecomboBox10);
		editbuttonRundisabled(federatedradio1);
		editbuttonRundisabled(nonfederatedradio1);
		editbuttonRundisabled(federatedradio2);
		editbuttonRundisabled(nonfederatedradio2);
		editbuttonRundisabled(federatedradio3);
		editbuttonRundisabled(nonfederatedradio3);
		editbuttonRundisabled(federatedradio4);
		editbuttonRundisabled(nonfederatedradio4);
		editbuttonRundisabled(federatedradio5);
		editbuttonRundisabled(nonfederatedradio5);
		editbuttonRundisabled(federatedradio6);
		editbuttonRundisabled(nonfederatedradio6);
		editbuttonRundisabled(federatedradio7);
		editbuttonRundisabled(nonfederatedradio7);
		editbuttonRundisabled(federatedradio8);
		editbuttonRundisabled(nonfederatedradio8);
		editbuttonRundisabled(federatedradio9);
		editbuttonRundisabled(nonfederatedradio9);
		editbuttonRundisabled(federatedradio10);
		editbuttonRundisabled(nonfederatedradio10);

		/*
		 * adminurltext1.edigetDocument().addDocumentListener(new DocumentListener() {
		 * 
		 * @Override public
		 * 
		 * void insertUpdate(DocumentEvent e) { rundisabled(); }
		 * 
		 * @Override public void removeUpdate(DocumentEvent e) { rundisabled(); }
		 * 
		 * @Override public void changedUpdate(DocumentEvent e) { rundisabled(); } });
		 */

		for (Component component : panel1.getComponents()) {
			if (component instanceof JCheckBox) {
				((AbstractButton) component).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						rundisabled();
					}
				});
			}
		}
		for (Component component : panel1.getComponents()) {
			if (component instanceof JRadioButton) {
				((AbstractButton) component).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						rundisabled();
					}
				});
			}
		}
		for (Component component : panel1.getComponents()) {
			if (component instanceof JComboBox) {
				((AbstractButton) component).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						rundisabled();
					}
				});
			}
		}

		mainpanel.setBackground(Color.decode("#d3d3d3"));

		messagepanel.setBackground(Color.decode("#d3d3d3"));
		stopscan.setEnabled(false);
		txtPane = new JTextPane();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		// panel1.setPreferredSize(new Dimension(1500, 600));
		//panel2.setPreferredSize(new Dimension(1500, 600));
		//panel2_1.setPreferredSize(new Dimension(1500, 50));
		//panel2_2.setPreferredSize(new Dimension(1500, 120));
		//panel2_3.setPreferredSize(new Dimension(1500, 400));
		//panel2_1.setMinimumSize(new Dimension(1000, 50));
		//panel2_2.setMinimumSize(new Dimension(1000, 120));
		//panel2_3.setMinimumSize(new Dimension(1000, 400));
		// Border lineBorder = BorderFactory.createLineBorder(Color.GRAY, 1);
		// Border lineBorder1 = BorderFactory.createLineBorder(Color.GRAY, 1);
		panel1_0.setBorder(new TitledBorder(loweredetched, "Browser Information"));
		// panel1_0.setBorder(lineBorder);
		panel1_0.setLayout(new BoxLayout(panel1_0, BoxLayout.Y_AXIS));
		panel1_1.setBorder(new TitledBorder(loweredetched, "Portal Information"));
		// panel1_1.setBorder(lineBorder);
		panel1_1.setLayout(new BoxLayout(panel1_1, BoxLayout.Y_AXIS));
		panel1_2.setBorder(new TitledBorder(loweredetched, "Server Information"));
		// panel1_2.setBorder(lineBorder);
		panel1_2.setLayout(new BoxLayout(panel1_2, BoxLayout.Y_AXIS));

		panel1_3.setLayout(new BoxLayout(panel1_3, BoxLayout.Y_AXIS));
		panel1_3.setBorder(loweredetched);

		panel1_4.setLayout(new BoxLayout(panel1_4, BoxLayout.Y_AXIS));
		panel1_4.setBorder(loweredetched);

		panel1_5.setLayout(new BoxLayout(panel1_5, BoxLayout.Y_AXIS));
		panel1_5.setBorder(loweredetched);

		panel1_6.setLayout(new BoxLayout(panel1_6, BoxLayout.Y_AXIS));
		panel1_6.setBorder(loweredetched);

		panel1_7.setLayout(new BoxLayout(panel1_7, BoxLayout.Y_AXIS));
		panel1_7.setBorder(loweredetched);

		panel1_8.setLayout(new BoxLayout(panel1_8, BoxLayout.Y_AXIS));
		panel1_8.setBorder(loweredetched);

		panel1_9.setLayout(new BoxLayout(panel1_9, BoxLayout.Y_AXIS));
		panel1_9.setBorder(loweredetched);

		panel1_10.setLayout(new BoxLayout(panel1_10, BoxLayout.Y_AXIS));
		panel1_10.setBorder(loweredetched);

		panel1_11.setLayout(new BoxLayout(panel1_11, BoxLayout.Y_AXIS));
		panel1_11.setBorder(loweredetched);
		panel1_12.setLayout(new BoxLayout(panel1_12, BoxLayout.Y_AXIS));
		// panel1_12.setBorder(lineBorder);

		// panel for test tab
		// panel2_1.setBorder(lineBorder);

		panel2_1.setLayout(new BoxLayout(panel2_1, BoxLayout.Y_AXIS));
		// panel2_2.setBorder(lineBorder);
		panel2_2.setLayout(new BoxLayout(panel2_2, BoxLayout.Y_AXIS));
		// panel2_3.setBorder(lineBorder);
		panel2_3.setLayout(new BoxLayout(panel2_3, BoxLayout.Y_AXIS));
		addSitetoPanel();
		initialState1();
		initialState2();
		initialState3();
		initialState4();
		initialState5();
		initialState6();
		initialState7();
		initialState8();
		initialState9();
		initialState10();
		panel1.add(Box.createRigidArea(new Dimension(0, 20)));
		panel1.add(panel1_0);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(panel1_1);
		panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		panel1.add(panel1_2);
		// panel1.add(createFormRowLink(addsite));
		if (no_of_site == 1) {
			// initialState2();
			panel1.add(panel1_12);
		}

		if (no_of_site == 2) {

			panel1.add(panel1_3);
			panel1.add(panel1_12);

		}
		if (no_of_site == 3) {

			panel1.add(panel1_3);
			panel1.add(panel1_4);
			panel1.add(panel1_12);
		}
		if (no_of_site == 4) {

			panel1.add(panel1_3);
			panel1.add(panel1_4);
			panel1.add(panel1_5);
			panel1.add(panel1_12);

		}
		if (no_of_site == 5) {

			panel1.add(panel1_3);
			panel1.add(panel1_4);
			panel1.add(panel1_5);
			panel1.add(panel1_6);
			panel1.add(panel1_12);
		}
		if (no_of_site == 6) {
			panel1.add(panel1_3);
			panel1.add(panel1_4);
			panel1.add(panel1_5);
			panel1.add(panel1_6);
			panel1.add(panel1_7);
			panel1.add(panel1_12);
		}
		if (no_of_site == 7) {
			panel1.add(panel1_3);
			panel1.add(panel1_4);
			panel1.add(panel1_5);
			panel1.add(panel1_6);
			panel1.add(panel1_7);
			panel1.add(panel1_8);
			panel1.add(panel1_12);
		}
		if (no_of_site == 8) {
			panel1.add(panel1_3);
			panel1.add(panel1_4);
			panel1.add(panel1_5);
			panel1.add(panel1_6);
			panel1.add(panel1_7);
			panel1.add(panel1_8);
			panel1.add(panel1_9);
			panel1.add(panel1_12);
		}
		if (no_of_site == 9) {
			panel1.add(panel1_3);
			panel1.add(panel1_4);
			panel1.add(panel1_5);
			panel1.add(panel1_6);
			panel1.add(panel1_7);
			panel1.add(panel1_8);
			panel1.add(panel1_9);
			panel1.add(panel1_10);
			panel1.add(createFormRowLink(addsite));
		}
		if (no_of_site == 10) {
			panel1.add(panel1_3);
			panel1.add(panel1_4);
			panel1.add(panel1_5);
			panel1.add(panel1_6);
			panel1.add(panel1_7);
			panel1.add(panel1_8);
			panel1.add(panel1_9);
			panel1.add(panel1_10);
			panel1.add(panel1_11);
			// panel1.add(createFormRowLink(addsite));

		}
		panel1.validate();
		panel1.repaint();
		// panel1.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		// panel1_2.add(createFormRowLink(addsite));
		addsite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				no_of_site = no_of_site + 1;
				// initialState1();
				// panel1.add(createFormRowLink(addsite));
				if (no_of_site == 2) {
					panel1.remove(panel1_12);
					panel1.add(panel1_3);
					panel1.add(panel1_12);
					panel1.validate();
					panel1.repaint();
					/*
					 * if((!adminurltext1.getText().isBlank())&&
					 * (!adminusernametext1.getText().isBlank())&&(!adminpasswordtext1.getText().
					 * isBlank())) { adminurltext1.setBackground(Color.LIGHT_GRAY);
					 * adminusernametext1.setBackground(Color.LIGHT_GRAY);
					 * adminpasswordtext1.setBackground(Color.LIGHT_GRAY); panel1.validate();
					 * panel1.repaint(); initialState3();
					 * 
					 * panel1_3.add(createFormRowLink(addsite));
					 * 
					 * // 20 pixels gap
					 * 
					 * } else { JOptionPane.showMessageDialog(frame,
					 * "Please provide data for Server URl1, Username, Password", "Dialog",
					 * JOptionPane.ERROR_MESSAGE); if(adminurltext1.getText().isBlank())
					 * adminurltext1.setBackground(Color.yellow);
					 * if(adminusernametext1.getText().isBlank())
					 * adminusernametext1.setBackground(Color.yellow);
					 * if(adminpasswordtext1.getText().isBlank())
					 * adminpasswordtext1.setBackground(Color.yellow); panel1.validate();
					 * panel1.repaint(); no_of_site = no_of_site - 1; }
					 */

				}
				if (no_of_site == 3) {
					panel1.remove(panel1_12);
					panel1.add(panel1_4);
					panel1.add(panel1_12);
					panel1.validate();
					panel1.repaint();
					/*
					 * if((!adminurltext2.getText().isBlank())&&
					 * (!adminusernametext2.getText().isBlank())&&(!adminpasswordtext2.getText().
					 * isBlank())) { adminurltext2.setBackground(Color.LIGHT_GRAY);
					 * adminusernametext2.setBackground(Color.LIGHT_GRAY);
					 * adminpasswordtext2.setBackground(Color.LIGHT_GRAY); panel1.validate();
					 * panel1.repaint(); initialState4();
					 * 
					 * panel1_4.add(createFormRowLink(addsite));
					 * 
					 * } else { JOptionPane.showMessageDialog(frame,
					 * "Please provide data for Server URl2, Username, Password", "Dialog",
					 * JOptionPane.ERROR_MESSAGE); if(adminurltext2.getText().isBlank())
					 * adminurltext2.setBackground(Color.yellow);
					 * if(adminusernametext2.getText().isBlank())
					 * adminusernametext2.setBackground(Color.yellow);
					 * if(adminpasswordtext2.getText().isBlank())
					 * adminpasswordtext2.setBackground(Color.yellow); panel1.validate();
					 * panel1.repaint(); no_of_site = no_of_site - 1; }
					 */

				}
				if (no_of_site == 4) {
					panel1.remove(panel1_12);
					panel1.add(panel1_5);
					panel1.add(panel1_12);
					panel1.validate();
					panel1.repaint();
					/*
					 * if((!adminurltext3.getText().isBlank())&&
					 * (!adminusernametext3.getText().isBlank())&&(!adminpasswordtext3.getText().
					 * isBlank())) { adminurltext3.setBackground(Color.LIGHT_GRAY);
					 * adminusernametext3.setBackground(Color.LIGHT_GRAY);
					 * adminpasswordtext3.setBackground(Color.LIGHT_GRAY); panel1.validate();
					 * panel1.repaint(); initialState5();
					 * 
					 * panel1_5.add(createFormRowLink(addsite));
					 * 
					 * }else { JOptionPane.showMessageDialog(frame,
					 * "Please provide data for Server URl3, Username, Password", "Dialog",
					 * JOptionPane.ERROR_MESSAGE); if(adminurltext3.getText().isBlank())
					 * adminurltext3.setBackground(Color.yellow);
					 * if(adminusernametext3.getText().isBlank())
					 * adminusernametext3.setBackground(Color.yellow);
					 * if(adminpasswordtext3.getText().isBlank())
					 * adminpasswordtext3.setBackground(Color.yellow); panel1.validate();
					 * panel1.repaint(); no_of_site = no_of_site - 1; }
					 */

				}
				if (no_of_site == 5) {
					panel1.remove(panel1_12);
					panel1.add(panel1_6);
					panel1.add(panel1_12);
					panel1.validate();
					panel1.repaint();
					/*
					 * if((!adminurltext4.getText().isBlank())&&
					 * (!adminusernametext4.getText().isBlank())&&(!adminpasswordtext4.getText().
					 * isBlank())) { adminurltext4.setBackground(Color.LIGHT_GRAY);
					 * adminusernametext4.setBackground(Color.LIGHT_GRAY);
					 * adminpasswordtext4.setBackground(Color.LIGHT_GRAY); panel1.validate();
					 * panel1.repaint(); initialState6();
					 * 
					 * panel1_6.add(createFormRowLink(addsite));
					 * 
					 * }else { JOptionPane.showMessageDialog(frame,
					 * "Please provide data for Server URl4, Username, Password", "Dialog",
					 * JOptionPane.ERROR_MESSAGE); if(adminurltext4.getText().isBlank())
					 * adminurltext4.setBackground(Color.yellow);
					 * if(adminusernametext4.getText().isBlank())
					 * adminusernametext4.setBackground(Color.yellow);
					 * if(adminpasswordtext4.getText().isBlank())
					 * adminpasswordtext4.setBackground(Color.yellow); panel1.validate();
					 * panel1.repaint(); no_of_site = no_of_site - 1; }
					 */

				}
				if (no_of_site == 6) {
					panel1.remove(panel1_12);
					panel1.add(panel1_7);
					panel1.add(panel1_12);
					panel1.validate();
					panel1.repaint();
					/*
					 * if((!adminurltext5.getText().isBlank())&&
					 * (!adminusernametext5.getText().isBlank())&&(!adminpasswordtext5.getText().
					 * isBlank())) { adminurltext5.setBackground(Color.LIGHT_GRAY);
					 * adminusernametext5.setBackground(Color.LIGHT_GRAY);
					 * adminpasswordtext5.setBackground(Color.LIGHT_GRAY); panel1.validate();
					 * panel1.repaint(); initialState7();
					 * 
					 * panel1_7.add(createFormRowLink(addsite));
					 * 
					 * }else { JOptionPane.showMessageDialog(frame,
					 * "Please provide data for Server URl5, Username, Password", "Dialog",
					 * JOptionPane.ERROR_MESSAGE); if(adminurltext5.getText().isBlank())
					 * adminurltext5.setBackground(Color.yellow);
					 * if(adminusernametext5.getText().isBlank())
					 * adminusernametext5.setBackground(Color.yellow);
					 * if(adminpasswordtext5.getText().isBlank())
					 * adminpasswordtext5.setBackground(Color.yellow); panel1.validate();
					 * panel1.repaint(); no_of_site = no_of_site - 1; }
					 */

				}

				if (no_of_site == 7) {
					panel1.remove(panel1_12);
					panel1.add(panel1_8);
					panel1.add(panel1_12);
					panel1.validate();
					panel1.repaint();

					/*
					 * if((!adminurltext6.getText().isBlank())&&
					 * (!adminusernametext6.getText().isBlank())&&(!adminpasswordtext6.getText().
					 * isBlank())) { adminurltext6.setBackground(Color.LIGHT_GRAY);
					 * adminusernametext6.setBackground(Color.LIGHT_GRAY);
					 * adminpasswordtext6.setBackground(Color.LIGHT_GRAY); panel1.validate();
					 * panel1.repaint(); initialState8();
					 * 
					 * panel1_8.add(createFormRowLink(addsite));
					 * 
					 * }else { JOptionPane.showMessageDialog(frame,
					 * "Please provide data for Server URl6, Username, Password", "Dialog",
					 * JOptionPane.ERROR_MESSAGE); if(adminurltext6.getText().isBlank())
					 * adminurltext6.setBackground(Color.yellow);
					 * if(adminusernametext6.getText().isBlank())
					 * adminusernametext6.setBackground(Color.yellow);
					 * if(adminpasswordtext6.getText().isBlank())
					 * adminpasswordtext6.setBackground(Color.yellow); panel1.validate();
					 * panel1.repaint(); no_of_site = no_of_site - 1; }
					 */
				}
				if (no_of_site == 8) {
					panel1.remove(panel1_12);
					panel1.add(panel1_9);
					panel1.add(panel1_12);
					panel1.validate();
					panel1.repaint();
					/*
					 * if((!adminurltext7.getText().isBlank())&&
					 * (!adminusernametext7.getText().isBlank())&&(!adminpasswordtext7.getText().
					 * isBlank())) { adminurltext7.setBackground(Color.LIGHT_GRAY);
					 * adminusernametext7.setBackground(Color.LIGHT_GRAY);
					 * adminpasswordtext7.setBackground(Color.LIGHT_GRAY); panel1.validate();
					 * panel1.repaint(); initialState9();
					 * 
					 * panel1_9.add(createFormRowLink(addsite));
					 * 
					 * }else { JOptionPane.showMessageDialog(frame,
					 * "Please provide data for Server URl7, Username, Password", "Dialog",
					 * JOptionPane.ERROR_MESSAGE); if(adminurltext7.getText().isBlank())
					 * adminurltext7.setBackground(Color.yellow);
					 * if(adminusernametext7.getText().isBlank())
					 * adminusernametext7.setBackground(Color.yellow);
					 * if(adminpasswordtext7.getText().isBlank())
					 * adminpasswordtext7.setBackground(Color.yellow); panel1.validate();
					 * panel1.repaint(); no_of_site = no_of_site - 1; }
					 */

				}
				if (no_of_site == 9) {
					panel1.remove(panel1_12);
					panel1.add(panel1_10);
					panel1.add(panel1_12);
					panel1.validate();
					panel1.repaint();
					/*
					 * if((!adminurltext8.getText().isBlank())&&
					 * (!adminusernametext8.getText().isBlank())&&(!adminpasswordtext8.getText().
					 * isBlank())) { adminurltext8.setBackground(Color.LIGHT_GRAY);
					 * adminusernametext8.setBackground(Color.LIGHT_GRAY);
					 * adminpasswordtext8.setBackground(Color.LIGHT_GRAY); panel1.validate();
					 * panel1.repaint(); initialState10();
					 * 
					 * panel1_10.add(createFormRowLink(addsite));
					 * 
					 * }else { JOptionPane.showMessageDialog(frame,
					 * "Please provide data for Server URl8, Username, Password", "Dialog",
					 * JOptionPane.ERROR_MESSAGE); if(adminurltext8.getText().isBlank())
					 * adminurltext8.setBackground(Color.yellow);
					 * if(adminusernametext8.getText().isBlank())
					 * adminusernametext8.setBackground(Color.yellow);
					 * if(adminpasswordtext8.getText().isBlank())
					 * adminpasswordtext8.setBackground(Color.yellow); panel1.validate();
					 * panel1.repaint(); no_of_site = no_of_site - 1; }
					 */

				}
				if (no_of_site == 10) {
					panel1.remove(panel1_12);
					panel1.add(panel1_11);
					// panel1.add(panel1_12);
					panel1.validate();
					panel1.repaint();
					/*
					 * if((!adminurltext9.getText().isBlank())&&
					 * (!adminusernametext9.getText().isBlank())&&(!adminpasswordtext9.getText().
					 * isBlank())) { adminurltext9.setBackground(Color.LIGHT_GRAY);
					 * adminusernametext9.setBackground(Color.LIGHT_GRAY);
					 * adminpasswordtext9.setBackground(Color.LIGHT_GRAY); panel1.validate();
					 * panel1.repaint(); initialState11();
					 * 
					 * 
					 * }else { JOptionPane.showMessageDialog(frame,
					 * "Please provide data for Server URl9, Username, Password", "Dialog",
					 * JOptionPane.ERROR_MESSAGE); if(adminurltext9.getText().isBlank())
					 * adminurltext9.setBackground(Color.yellow);
					 * if(adminusernametext9.getText().isBlank())
					 * adminusernametext9.setBackground(Color.yellow);
					 * if(adminpasswordtext9.getText().isBlank())
					 * adminpasswordtext9.setBackground(Color.yellow); panel1.validate();
					 * panel1.repaint(); no_of_site = no_of_site - 1; }
					 */

				}
				rundisabled();
				validate();
				repaint();
			}

		});
		close1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					closePanel(2);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panel1.revalidate();
				panel1.repaint();
				scrollpane1.revalidate();
				scrollpane1.repaint();

			}
		});
		close2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					closePanel(3);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panel1.revalidate();
				panel1.repaint();
				scrollpane1.revalidate();
				scrollpane1.repaint();

			}
		});
		close3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					closePanel(4);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panel1.revalidate();
				panel1.repaint();
				scrollpane1.revalidate();
				scrollpane1.repaint();
			}
		});
		close4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					closePanel(5);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panel1.revalidate();
				panel1.repaint();
				scrollpane1.revalidate();
				scrollpane1.repaint();
			}
		});
		close5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					closePanel(6);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panel1.revalidate();
				panel1.repaint();
				scrollpane1.revalidate();
				scrollpane1.repaint();

			}
		});
		close6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					closePanel(7);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panel1.revalidate();
				panel1.repaint();
				scrollpane1.revalidate();
				scrollpane1.repaint();
			}
		});
		close7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					closePanel(8);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panel1.revalidate();
				panel1.repaint();
				scrollpane1.revalidate();
				scrollpane1.repaint();

			}
		});
		close8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					closePanel(9);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panel1.revalidate();
				panel1.repaint();
				scrollpane1.revalidate();
				scrollpane1.repaint();
			}
		});
		close9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					closePanel(10);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panel1.revalidate();
				panel1.repaint();
				scrollpane1.revalidate();
				scrollpane1.repaint();

			}
		});
		federatedradio1.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[0] = true;
				}

			}
		});
		nonfederatedradio1.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[0] = false;
				}

			}
		});
		federatedradio2.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[1] = true;
				}

			}
		});
		nonfederatedradio2.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[1] = false;
				}

			}
		});
		federatedradio3.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[2] = true;
				}

			}
		});
		nonfederatedradio3.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[2] = false;
				}

			}
		});
		federatedradio4.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[3] = true;
				}

			}
		});
		nonfederatedradio4.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[3] = false;
				}

			}
		});
		federatedradio5.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[4] = true;
				}

			}
		});
		nonfederatedradio5.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[4] = false;
				}

			}
		});
		federatedradio6.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[5] = true;
				}

			}
		});
		nonfederatedradio6.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[5] = false;
				}

			}
		});
		federatedradio7.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[6] = true;
				}

			}
		});
		nonfederatedradio7.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[6] = false;
				}

			}
		});
		federatedradio8.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[7] = true;
				}

			}
		});
		nonfederatedradio8.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[7] = false;
				}

			}
		});
		federatedradio9.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[8] = true;
				}

			}
		});
		nonfederatedradio9.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[8] = false;
				}

			}
		});
		federatedradio10.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[9] = true;
				}

			}
		});
		nonfederatedradio10.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ServerFederated[9] = false;
				}

			}
		});
		runalltest.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					flagRunalltest=true;
					testRunSetting1();
				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					flagRunalltest=false;
					testRunSetting1();
					}			}
		});

		map.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					flagmap = true;
					flagloginportal = true;
					flagcontent = true;
					loginportal.setSelected(true);
					loginportal.setEnabled(false);
					// saveBtn.setEnabled(true);

				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					flagmap = false;

					if (tile.isSelected() || feature.isSelected() || groupfunctionality.isSelected()
							|| scenelayer.isSelected() || organization.isSelected() || webappbuilder.isSelected()
							|| hosting.isSelected()|| experiencebuilder.isSelected()) {
						loginportal.setEnabled(false);
					} else {
						loginportal.setEnabled(true);
					}
					if (tile.isSelected() || feature.isSelected() || scenelayer.isSelected()
							|| webappbuilder.isSelected() || experiencebuilder.isSelected()) {
						
						
						flagcontent = true;
					} else {
						flagcontent = false;
					}
					/*
					 * if (loginmanager.isSelected() || loginserver.isSelected() ||
					 * loginportal.isSelected() || map.isSelected() || tile.isSelected() ||
					 * feature.isSelected() || groupfunctionality.isSelected() ||
					 * organization.isSelected() || scenelayer.isSelected() ||
					 * webappbuilder.isSelected() || content.isSelected() || hosting.isSelected()) {
					 * saveBtn.setEnabled(true); } else { saveBtn.setEnabled(false); }
					 */
				}

			}
		});

		hosting.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					flagServerRole = true;
					flagloginadmin = true;
					// flagfeaturelayer = true;
					loginportal.setSelected(true);
					loginportal.setEnabled(false);
					// saveBtn.setEnabled(true);

				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					flagServerRole = false;
					if (tile.isSelected() || feature.isSelected() || groupfunctionality.isSelected() || map.isSelected()
							|| organization.isSelected() || webappbuilder.isSelected() || scenelayer.isSelected()|| experiencebuilder.isSelected()) {
						loginportal.setEnabled(false);
					} else {
						loginportal.setEnabled(true);
					}

				}

			}
		});

		scenelayer.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					flagscenelayer = true;
					flagcontent = true;
					flagloginportal = true;
					loginportal.setSelected(true);
					loginportal.setEnabled(false);

				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					flagscenelayer = false;
					/*
					 * if (tile.isSelected()) { feature.setSelected(true);
					 * feature.setEnabled(false); } else { feature.setSelected(false);
					 * feature.setEnabled(true); flagfeaturelayer = false; }
					 */
					if (tile.isSelected() || feature.isSelected() || groupfunctionality.isSelected() || map.isSelected()
							|| organization.isSelected() || webappbuilder.isSelected() || hosting.isSelected()|| experiencebuilder.isSelected()) {
						loginportal.setEnabled(false);
					} else {
						loginportal.setEnabled(true);
					}
					if (tile.isSelected() || feature.isSelected() || map.isSelected() || webappbuilder.isSelected()|| experiencebuilder.isSelected()) {
						flagcontent = true;
					} else {
						flagcontent = false;
					}
				}

			}
		});
		
		experiencebuilder.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					flagexperiencebuilder = true;
					flagloginportal = true;
					loginportal.setSelected(true);
					loginportal.setEnabled(false);
					
					if(!runalltest.isSelected()) {
					webappbuilder.setSelected(false);
					webappbuilder.setEnabled(false);
					// saveBtn.setEnabled(true);
					}
				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					flagexperiencebuilder = false;
					if (tile.isSelected() || feature.isSelected() || groupfunctionality.isSelected() || map.isSelected()
							|| organization.isSelected() || webappbuilder.isSelected() || scenelayer.isSelected()
							|| hosting.isSelected()) {
						loginportal.setEnabled(false);
						webappbuilder.setEnabled(true);
					} else {
						loginportal.setEnabled(true);
						webappbuilder.setEnabled(true);
					}

				}

			}
		});

		tile.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					flagtilelayer = true;
					flagloginportal = true;
					flagcontent = true;
					loginportal.setSelected(true);
					//feature.setSelected(true); As per the requirement, the test cases need to be independent 
					loginportal.setEnabled(false);
					//feature.setEnabled(false);As per the requirement, the test cases need to be independent
					// saveBtn.setEnabled(true);
				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					flagtilelayer = false;
					//feature.setEnabled(true); As per the requirement, the test cases need to be independent
					if (feature.isSelected() || groupfunctionality.isSelected() || organization.isSelected()
							|| scenelayer.isSelected() || map.isSelected() || webappbuilder.isSelected()|| experiencebuilder.isSelected()
							|| hosting.isSelected()) {
						loginportal.setEnabled(false);
					} else {
						loginportal.setEnabled(true);
					}

					/*if (groupfunctionality.isSelected()) {
						feature.setEnabled(false);
					} else {
						feature.setEnabled(true);
					}*/
					if (map.isSelected() || feature.isSelected() || scenelayer.isSelected()
							|| webappbuilder.isSelected()|| experiencebuilder.isSelected()) {
						flagcontent = true;
					} else {
						flagcontent = false;
					}

				}

			}
		});
		feature.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					flagfeaturelayer = true;
					flagloginportal = true;
					flagcontent = true;
					loginportal.setSelected(true);
					loginportal.setEnabled(false);
					// saveBtn.setEnabled(true);

				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					flagfeaturelayer = false;
					if (tile.isSelected() || groupfunctionality.isSelected() || organization.isSelected()
							|| scenelayer.isSelected() || map.isSelected() || webappbuilder.isSelected()
							|| hosting.isSelected()|| experiencebuilder.isSelected()) {
						loginportal.setEnabled(false);

					} else {
						loginportal.setEnabled(true);
					}
					if (tile.isSelected() || map.isSelected() || scenelayer.isSelected()
							|| webappbuilder.isSelected()|| experiencebuilder.isSelected()) {
						flagcontent = true;
					} else {
						flagcontent = false;
					}

				}

			}
		});
		webappbuilder.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					flagwebappbuilder = true;
					flagcontent = true;
					flagloginportal = true;
					loginportal.setSelected(true);
					loginportal.setEnabled(false);
					experiencebuilder.setSelected(false);
					experiencebuilder.setEnabled(false);

				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					flagwebappbuilder = false;
					if (tile.isSelected() || feature.isSelected() || groupfunctionality.isSelected()
							|| scenelayer.isSelected() || organization.isSelected() || map.isSelected()
							|| hosting.isSelected()|| experiencebuilder.isSelected()) {
						loginportal.setEnabled(false);
						experiencebuilder.setEnabled(true);
					} else {
						loginportal.setEnabled(true);
						experiencebuilder.setEnabled(true);
					}
					if (tile.isSelected() || feature.isSelected() || scenelayer.isSelected() || experiencebuilder.isSelected()|| map.isSelected()) {
						flagcontent = true;
					} else {
						flagcontent = false;
					}

				}

			}
		});

		loginserver.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					flagloginadmin = true;
					// saveBtn.setEnabled(true);

				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					flagloginadmin = false;

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
					// saveBtn.setEnabled(true);

				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					flagorganization = false;
					if (tile.isSelected() || feature.isSelected() || groupfunctionality.isSelected() || map.isSelected()
							|| experiencebuilder.isSelected()|| webappbuilder.isSelected() || scenelayer.isSelected() || hosting.isSelected()) {
						loginportal.setEnabled(false);
					} else {
						loginportal.setEnabled(true);
					}

				}

			}
		});
		loginmanager.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					flagloginmanager = true;
					// saveBtn.setEnabled(true);

				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					flagloginmanager = false;

				}

			}
		});
		datastore.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {
					flagdatastore = true;
					//flagloginmanager = true;
					// flagfeaturelayer = true;
					//loginmanager.setSelected(true);
					//loginmanager.setEnabled(false);
					// saveBtn.setEnabled(true);

				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					flagdatastore = false;
					loginmanager.setEnabled(true);

				}

			}
		});
		groupfunctionality.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					flaggroup = true;
					flagloginportal = true;
				//	flagfeaturelayer = true;
					flagcontent = true;
					loginportal.setSelected(true);
					loginportal.setEnabled(false);
				//	feature.setEnabled(false);
				//	feature.setSelected(true);
				}
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					flaggroup = false;
					if (tile.isSelected() || feature.isSelected() || organization.isSelected() || map.isSelected()
							|| experiencebuilder.isSelected()|| webappbuilder.isSelected() || scenelayer.isSelected() || hosting.isSelected()) {
						loginportal.setEnabled(false);

					} else {
						loginportal.setEnabled(true);

					}
					if (tile.isSelected()) {
						feature.setEnabled(true);//As per the requirement, the test cases need to be independent so false value is not set to feature layer when group functionality is deselected

					} else {
						feature.setEnabled(true);

					}

				}
				if (map.isSelected() || feature.isSelected() || scenelayer.isSelected() || webappbuilder.isSelected() || experiencebuilder.isSelected()) {
					flagcontent = true;
				} else {
					flagcontent = false;
				}

			}
		});
		loginportal.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					flagloginportal = true;
					// saveBtn.setEnabled(true);
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
					flagServerRole = false;
					flagexperiencebuilder = false;
					tile.setSelected(false);
					feature.setSelected(false);
					groupfunctionality.setSelected(false);
					map.setSelected(false);
					experiencebuilder.setSelected(false);
					webappbuilder.setSelected(false);
					// content.setSelected(false);
					organization.setSelected(false);
					scenelayer.setSelected(false);
					hosting.setSelected(false);
					tile.setEnabled(true);
					feature.setEnabled(true);
					groupfunctionality.setEnabled(true);
					map.setEnabled(true);
					experiencebuilder.setEnabled(true);
					webappbuilder.setEnabled(true);					
					// content.setEnabled(true);
					organization.setEnabled(true);
					scenelayer.setEnabled(true);
					if(flagKUB) {
					hosting.setEnabled(false);
					}
					else {
						hosting.setEnabled(true);
					}

				}

			}
		});

		scrollpane1 = new JScrollPane(panel1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane2 = new JScrollPane(panel2, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpane3 = new JScrollPane(txtPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		// scrollpane2.setPreferredSize(new Dimension(1600,800));

		reportlabel.setBackground(Color.decode("#9896aa"));
		reportlabel.setForeground(Color.BLACK);
		reportlabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		reportlabel.setEnabled(false);
		reportlabel.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent pre) {

				try {
					if (reportlabel.isEnabled()) {
						Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + reportPath);
						// Runtime.getRuntime().exec("explorer.exe
						// "+extentReportFilePath_BrowserNameOnly);
					}
				} catch (Exception e) {

					e.printStackTrace();

				}

			}

			public void mouseEntered(MouseEvent ent) {

				reportlabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			}

			public void mouseExited(MouseEvent exi) {

				reportlabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

			}

		});
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				nowTime = System.currentTimeMillis();
				elapsedTime = nowTime - startTime;
				// DurationFormatUtils.formatDuration(elapsedTime, "HH:mm:ss.S");
				timedisplay.setText(
						org.apache.commons.lang3.time.DurationFormatUtils.formatDuration(elapsedTime, "HH:mm:ss")
								+ " Time Elapsed");
			}
		};
		// Timer timer = new Timer(delay ,taskPerformer);
		timer.addActionListener(taskPerformer);
		// timer.setRepeats(false);

		timedisplay.setFont(new Font("Verdana", Font.PLAIN, 14));
		parameterPanel.add(scrollpane1);
		testPanel.add(scrollpane2);
		// resultPanel.add(cd1);
		// resultPanel.setLayout( new BorderLayout());

		resultPanel.setBackground(Color.decode("#d9dddc"));

		test.setPreferredSize(new Dimension(3, 7));

		progressBar.setStringPainted(true);

		progressBar.setValue(0);
		progressBar.setBorder(border);
		// progressBar.setPreferredSize(new Dimension(1000,35));
		reportPanel.setPreferredSize(new Dimension(1500, 30));
		// reportPanel.setMaximumSize(new Dimension(1550, 30));
		reportPanel.setLayout(new BorderLayout());
		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
		progressBarPanel.setPreferredSize(new Dimension(1500, 30));
		// progressBarPanel.setMaximumSize(new Dimension(1550, 30));
		progressBarPanel.setLayout(new BorderLayout());
		statusPanel.setPreferredSize(new Dimension(1500, 900));
		// scrollpane2.setPreferredSize(new Dimension(1000, 600));
		scrollpane1.setPreferredSize(new Dimension(1100, 600));
		txtPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtPane.setFont(new Font("courier new", Font.PLAIN, 14));
		DefaultCaret caret = (DefaultCaret) txtPane.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		txtPane.setEditable(false);

		// txtPane.setEnabled(true);
		txtPane.setContentType("text/html");
		reportPanel.add(reportlabel, BorderLayout.EAST);
		reportPanel.add(timedisplay, BorderLayout.WEST);

		progressBarPanel.add(progressBar);
		statusPanel.add(scrollpane3);

		resultPanel.add(reportPanel);

		resultPanel.add(progressBarPanel);
		resultPanel.add(statusPanel);

		tabs.addTab("Input Parameters", parameterPanel);
		tabs.addTab("Tests to Run", testPanel);
		tabs.addTab("Results Log", resultPanel);
		tabs.setFont(new Font("Verdana", Font.BOLD, 15));
		msglabel.setText("");
		// lb.setSize(100, 80);
		// internal.setSize(100, 80);
		msglabel.setOpaque(false);
		msglabel.setBackground(Color.decode("#d3d3d3"));
		msglabel.setForeground(Color.BLACK);
		msglabel.setFont(new Font("Verdana", Font.ITALIC, 14));
		msglabel.setEnabled(true);
		msglabel.setVisible(true);
		mainpanel.setLayout(new BoxLayout(mainpanel, BoxLayout.Y_AXIS));
		resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
		testPanel.setLayout(new BoxLayout(testPanel, BoxLayout.Y_AXIS));
		parameterPanel.setLayout(new BoxLayout(parameterPanel, BoxLayout.Y_AXIS));
		//parameterPanel.setPreferredSize(new Dimension(1000, 750));
		// statusPanel.setPreferredSize(new Dimension(500, 650));
		//testPanel.setPreferredSize(new Dimension(1000, 650));
		// resultPanel.setPreferredSize(new Dimension(900, 650));
		messagepanel.setLayout(new BorderLayout());
		messagepanel.setPreferredSize(new Dimension(1550, 50));
		buttonpanel.setPreferredSize(new Dimension(1550, 50));
		// messagepanel.setMaximumSize(new Dimension(1550, 50));
		// buttonpanel.setMaximumSize(new Dimension(1800, 50));
		buttonpanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0;
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		buttonpanel.add(saveBtn, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0;
		gbc.gridx = 5;
		gbc.insets = new Insets(10, 10, 10, 10);
		buttonpanel.add(run, gbc);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0;
		gbc.gridx = 6;
		gbc.insets = new Insets(10, 10, 10, 10);
		buttonpanel.add(stopscan, gbc);
		run.setEnabled(false);
		stopscan.setEnabled(false);
		messagepanel.add(msglabel, BorderLayout.WEST);
		// messagepanel.add(internal, BorderLayout.EAST);
		mainpanel.add(tabs);
		mainpanel.add(messagepanel);
		mainpanel.add(buttonpanel);
		stopscan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(frame,
						"Do you really want to stop Enterprise Deployment Check tool? ", null,
						JOptionPane.YES_NO_OPTION);

				if (result == JOptionPane.YES_OPTION) {
					stopscan();
					// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				} else if (result == JOptionPane.NO_OPTION)
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

			}
		});

		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// saveToExcel();
				try {

					mainTask1 m_task1 = new mainTask1();

					m_task1.execute();

				} catch (Exception e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Not able to save details. Please try again!");
				}
			}
		});
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				runsmoke();
				// System.out.println("Option 1 selected");
			}
		});

		internal.setText(
				"<html><span style='font-size:8px;color:black;font-family:verdana;'><strong>Esri internal use only   </span></html>");
		internal.setEnabled(true);
		internal.setVisible(true);

	}

	private static void closePanel(int cnt) throws Exception {
		// no_of_site=no_of_site-1;

		removehighlights();
		String[] adminURL1 = new String[] { "", "", "", "", "", "", "", "", "" };
		String[] adminUsername1 = new String[] { "", "", "", "", "", "", "", "", "" };
		String[] adminPassword1 = new String[] { "", "", "", "", "", "", "", "", "" };
		String[] serverRole1 = new String[] { "", "", "", "", "", "", "", "", "" };
		boolean[] federated = new boolean[] { true, true, true, true, true };
		adminURL1[0] = adminurltext2.getText();
		adminURL1[1] = adminurltext3.getText();
		adminURL1[2] = adminurltext4.getText();
		adminURL1[3] = adminurltext5.getText();
		adminURL1[4] = adminurltext6.getText();
		adminURL1[5] = adminurltext7.getText();
		adminURL1[6] = adminurltext8.getText();
		adminURL1[7] = adminurltext9.getText();
		adminURL1[8] = adminurltext10.getText();

		adminUsername1[0] = adminusernametext2.getText();
		adminUsername1[1] = adminusernametext3.getText();
		adminUsername1[2] = adminusernametext4.getText();
		adminUsername1[3] = adminusernametext5.getText();
		adminUsername1[4] = adminusernametext6.getText();
		adminUsername1[5] = adminusernametext7.getText();
		adminUsername1[6] = adminusernametext8.getText();
		adminUsername1[7] = adminusernametext9.getText();
		adminUsername1[8] = adminusernametext10.getText();

		adminPassword1[0] = adminpasswordtext2.getText();
		adminPassword1[1] = adminpasswordtext3.getText();
		adminPassword1[2] = adminpasswordtext4.getText();
		adminPassword1[3] = adminpasswordtext5.getText();
		adminPassword1[4] = adminpasswordtext6.getText();
		adminPassword1[5] = adminpasswordtext7.getText();
		adminPassword1[6] = adminpasswordtext8.getText();
		adminPassword1[7] = adminpasswordtext9.getText();
		adminPassword1[8] = adminpasswordtext10.getText();
		for (int i = cnt - 2; i < no_of_site - 2; i++) {
			adminURL1[i] = adminURL1[i + 1];
			adminUsername1[i] = adminUsername1[i + 1];
			adminPassword1[i] = adminPassword1[i + 1];
		}
		for (int i = no_of_site - 2; i < 9; i++) {
			adminURL1[i] = "";
			adminUsername1[i] = "";
			adminPassword1[i] = "";
		}
		adminurltext2.setText(adminURL1[0]);
		adminurltext3.setText(adminURL1[1]);
		adminurltext4.setText(adminURL1[2]);
		adminurltext5.setText(adminURL1[3]);
		adminurltext6.setText(adminURL1[4]);
		adminurltext7.setText(adminURL1[5]);
		adminurltext8.setText(adminURL1[6]);
		adminurltext9.setText(adminURL1[7]);
		adminurltext10.setText(adminURL1[8]);

		adminusernametext2.setText(adminUsername1[0]);
		adminusernametext3.setText(adminUsername1[1]);
		adminusernametext4.setText(adminUsername1[2]);
		adminusernametext5.setText(adminUsername1[3]);
		adminusernametext6.setText(adminUsername1[4]);
		adminusernametext7.setText(adminUsername1[5]);
		adminusernametext8.setText(adminUsername1[6]);
		adminusernametext9.setText(adminUsername1[7]);
		adminusernametext10.setText(adminUsername1[8]);

		adminpasswordtext2.setText(adminPassword1[0]);
		adminpasswordtext3.setText(adminPassword1[1]);
		adminpasswordtext4.setText(adminPassword1[2]);
		adminpasswordtext5.setText(adminPassword1[3]);
		adminpasswordtext6.setText(adminPassword1[4]);
		adminpasswordtext7.setText(adminPassword1[5]);
		adminpasswordtext8.setText(adminPassword1[6]);
		adminpasswordtext9.setText(adminPassword1[7]);
		adminpasswordtext10.setText(adminPassword1[8]);

		if (no_of_site == 2) {
			panel1.remove(panel1_3);
			panel1.validate();
			panel1.repaint();

		}
		if (no_of_site == 3) {
			panel1.remove(panel1_4);
			panel1.validate();
			panel1.repaint();
		}

		if (no_of_site == 4) {
			panel1.remove(panel1_5);
			panel1.validate();
			panel1.repaint();
		}
		if (no_of_site == 5) {
			panel1.remove(panel1_6);
			panel1.validate();
			panel1.repaint();
		}
		if (no_of_site == 6) {
			panel1.remove(panel1_7);
			panel1.validate();
			panel1.repaint();
		}
		if (no_of_site == 7) {
			panel1.remove(panel1_8);
			panel1.validate();
			panel1.repaint();
		}
		if (no_of_site == 8) {
			panel1.remove(panel1_9);
			panel1.validate();
			panel1.repaint();
		}
		if (no_of_site == 9) {
			panel1.remove(panel1_10);
			panel1.validate();
			panel1.repaint();
		}
		if (no_of_site == 10) {
			panel1.remove(panel1_11);
			panel1.add(panel1_12);
			panel1.validate();
			panel1.repaint();

		}
		no_of_site = no_of_site - 1;
		rundisabled();
	}

	private static void initialState1() {
		// header1 = new JLabel("Enter all required information in the form below...");
		// header1.setFont(new Font("Verdana", Font.BOLD, 14));
		// panel1.add(Box.createRigidArea(new Dimension(0, 10)));
		// panel1.add(createFormRowHeader(header1));

		panel1_0.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_0.add(createFormRowCombo(browserlabel, browsercomboBox));
		panel1_0.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_1.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_1.add(createFormRow(portalurllabel, portalurltext));
		panel1_1.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_1.add(createFormRow(portalusernamelabel, portalusernametext));
		panel1_1.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_1.add(createFormRow(portalpasswordlabel, portalpasswordtext, toggleButton1));
		panel1_1.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_1.add(createFormRow(kubernetes));
		panel1_1.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_2.add(Box.createRigidArea(new Dimension(0, 20))); // 20 pixels gap
		panel1_2.add(createFormRow(adminurllabel1, adminurltext1));
		panel1_2.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_2.add(createFormRowCombo(serverrolelabel1, serverrolecomboBox1));
		panel1_2.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_2.add(createFormRow(adminusernamelabel1, adminusernametext1));
		panel1_2.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_2.add(createFormRow(adminpasswordlabel1, adminpasswordtext1, toggleButton2));
		panel1_2.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_2.add(createFormRowRadioHorizontal(federatedradio1, nonfederatedradio1));
		panel1_2.add(Box.createRigidArea(new Dimension(0, 15))); // 20 pixels gap
		// panel1.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		// panel1.add(createFormRowLink(addsite));
		// panel1.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
	}

	private static void initialState2() {
		// header2 = new JLabel("Run all test");
		// header2.setFont(new Font("Verdana", Font.BOLD, 12));
		// header3 = new JLabel("Server Test");
		// header3.setFont(new Font("Verdana", Font.BOLD, 12));
		// header4 = new JLabel("Portal test");
		// header4.setFont(new Font("Verdana", Font.BOLD, 12));

		// panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2_1.setBorder(new TitledBorder(lineBorder1, "Run all test"));
		// panel2_1.add(createFormRowHeader(header2));
		panel2_1.add(Box.createRigidArea(new Dimension(0, 5))); // 20 pixels gap
		panel2_1.add(createFormRowCheckbox(runalltest));
		panel2_1.add(Box.createRigidArea(new Dimension(0, 5)));

		panel2_2.setBorder(new TitledBorder(lineBorder1, "Server Tests"));
		// panel2_1.add(createFormRowHeader(header3));
		panel2_2.add(Box.createRigidArea(new Dimension(0, 5))); // 20 pixels gap
		panel2_2.add(createFormRowCheckbox(loginserver));
		panel2_2.add(Box.createRigidArea(new Dimension(0, 5))); // 20 pixels gap
		panel2_2.add(createFormRowCheckbox(loginmanager));
		panel2_2.add(Box.createRigidArea(new Dimension(0, 5))); // 20 pixels gap
		panel2_2.add(createFormRowCheckbox(datastore));
		panel2_2.add(Box.createRigidArea(new Dimension(0, 5))); // 20 pixels gap

		panel2_3.setBorder(new TitledBorder(lineBorder1, "Portal Tests"));
		panel2_3.add(Box.createRigidArea(new Dimension(0, 5)));
		panel2_3.add(createFormRowCheckbox(loginportal));

		panel2_3.add(Box.createRigidArea(new Dimension(0, 5))); // 20 pixels gap
		panel2_3.add(createFormRowCheckbox(feature));
		panel2_3.add(Box.createRigidArea(new Dimension(0, 5))); // 20 pixels gap
		panel2_3.add(createFormRowCheckbox(tile));
		panel2_3.add(Box.createRigidArea(new Dimension(0, 5)));
		panel2_3.add(createFormRowCheckbox(scenelayer));// 20 pixels gap
		panel2_3.add(Box.createRigidArea(new Dimension(0, 5))); // 20 pixels gap
		panel2_3.add(createFormRowCheckbox(map));
		panel2_3.add(Box.createRigidArea(new Dimension(0, 5))); // 20 pixels gap
		panel2_3.add(createFormRowCheckbox(experiencebuilder));
		panel2_3.add(Box.createRigidArea(new Dimension(0, 5))); // 20 pixels gap
		panel2_3.add(createFormRowCheckbox(webappbuilder));			
		panel2_3.add(Box.createRigidArea(new Dimension(0, 5))); // 20 pixels gap
		panel2_3.add(createFormRowCheckbox(groupfunctionality));
		panel2_3.add(Box.createRigidArea(new Dimension(0, 5))); // 20 pixels gap
		panel2_3.add(createFormRowCheckbox(organization));
		panel2_3.add(Box.createRigidArea(new Dimension(0, 5))); // 20 pixels gap
		panel2_3.add(createFormRowCheckbox(hosting));
		panel2_3.add(Box.createRigidArea(new Dimension(0, 5))); // 20 pixels gap
		panel2_3.add(Box.createVerticalGlue()); // Allow panel to expand properly
		// panel2_3.add(createFormRowCheckbox(groupfunctionality));
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(panel2_1);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(panel2_2);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
		panel2.add(panel2_3);
		panel2.add(Box.createRigidArea(new Dimension(0, 10)));
	}

	private static void initialState3() {
		panel1_3.add(Box.createRigidArea(new Dimension(0, 20)));
		panel1_3.add(createFormRow(adminurllabel2, adminurltext2));
		panel1_3.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_3.add(createFormRowCombo(serverrolelabel2, serverrolecomboBox2));
		panel1_3.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_3.add(createFormRow(adminusernamelabel2, adminusernametext2));
		panel1_3.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_3.add(createFormRow(adminpasswordlabel2, adminpasswordtext2, toggleButton3));
		panel1_3.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_3.add(createFormRowRadioHorizontal(federatedradio2, nonfederatedradio2));
		panel1_3.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_3.add(createFormRowLink2(close1));
		// panel1_3.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		// panel1.add(panel1_3);

	}

	private static void initialState4() {
		panel1_4.add(Box.createRigidArea(new Dimension(0, 20)));
		panel1_4.add(createFormRow(adminurllabel3, adminurltext3));
		panel1_4.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_4.add(createFormRowCombo(serverrolelabel3, serverrolecomboBox3));
		panel1_4.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_4.add(createFormRow(adminusernamelabel3, adminusernametext3));
		panel1_4.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_4.add(createFormRow(adminpasswordlabel3, adminpasswordtext3, toggleButton4));
		panel1_4.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_4.add(createFormRowRadioHorizontal(federatedradio3, nonfederatedradio3));
		panel1_4.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_4.add(createFormRowLink2(close2));
		//
		// panel1.add(panel1_4);

	}

	private static void initialState5() {
		panel1_5.add(Box.createRigidArea(new Dimension(0, 20)));
		panel1_5.add(createFormRow(adminurllabel4, adminurltext4));
		panel1_5.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_5.add(createFormRowCombo(serverrolelabel4, serverrolecomboBox4));
		panel1_5.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_5.add(createFormRow(adminusernamelabel4, adminusernametext4));
		panel1_5.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_5.add(createFormRow(adminpasswordlabel4, adminpasswordtext4, toggleButton5));
		panel1_5.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_5.add(createFormRowRadioHorizontal(federatedradio4, nonfederatedradio4));
		panel1_5.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_5.add(createFormRowLink2(close3));
		// panel1.add(panel1_5);

	}

	private static void initialState6() {
		panel1_6.add(Box.createRigidArea(new Dimension(0, 20)));
		panel1_6.add(createFormRow(adminurllabel5, adminurltext5));
		panel1_6.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_6.add(createFormRowCombo(serverrolelabel5, serverrolecomboBox5));
		panel1_6.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_6.add(createFormRow(adminusernamelabel5, adminusernametext5));
		panel1_6.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_6.add(createFormRow(adminpasswordlabel5, adminpasswordtext5, toggleButton6));
		panel1_6.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_6.add(createFormRowRadioHorizontal(federatedradio5, nonfederatedradio5));
		panel1_6.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_6.add(createFormRowLink2(close4));
		// panel1.add(panel1_6);
	}

	private static void initialState7() {
		panel1_7.add(Box.createRigidArea(new Dimension(0, 20)));
		panel1_7.add(createFormRow(adminurllabel6, adminurltext6));
		panel1_7.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_7.add(createFormRowCombo(serverrolelabel6, serverrolecomboBox6));
		panel1_7.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_7.add(createFormRow(adminusernamelabel6, adminusernametext6));
		panel1_7.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_7.add(createFormRow(adminpasswordlabel6, adminpasswordtext6, toggleButton7));
		panel1_7.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_7.add(createFormRowRadioHorizontal(federatedradio6, nonfederatedradio6));
		panel1_7.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_7.add(createFormRowLink2(close5));
		// panel1.add(panel1_7);

	}

	private static void initialState8() {
		panel1_8.add(Box.createRigidArea(new Dimension(0, 20)));
		panel1_8.add(createFormRow(adminurllabel7, adminurltext7));
		panel1_8.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_8.add(createFormRowCombo(serverrolelabel7, serverrolecomboBox7));
		panel1_8.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_8.add(createFormRow(adminusernamelabel7, adminusernametext7));
		panel1_8.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_8.add(createFormRow(adminpasswordlabel7, adminpasswordtext7, toggleButton8));
		panel1_8.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_8.add(createFormRowRadioHorizontal(federatedradio7, nonfederatedradio7));
		panel1_8.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_8.add(createFormRowLink2(close6));
		// panel1.add(panel1_8);

	}

	private static void initialState9() {
		panel1_9.add(Box.createRigidArea(new Dimension(0, 20)));
		panel1_9.add(createFormRow(adminurllabel8, adminurltext8));
		panel1_9.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_9.add(createFormRowCombo(serverrolelabel8, serverrolecomboBox8));
		panel1_9.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_9.add(createFormRow(adminusernamelabel8, adminusernametext8));
		panel1_9.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_9.add(createFormRow(adminpasswordlabel8, adminpasswordtext8, toggleButton9));
		panel1_9.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_9.add(createFormRowRadioHorizontal(federatedradio8, nonfederatedradio8));
		panel1_9.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_9.add(createFormRowLink2(close7));
		// panel1.add(panel1_9);

	}

	private static void initialState10() {
		panel1_10.add(Box.createRigidArea(new Dimension(0, 20)));
		panel1_10.add(createFormRow(adminurllabel9, adminurltext9));
		panel1_10.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_10.add(createFormRowCombo(serverrolelabel9, serverrolecomboBox9));
		panel1_10.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_10.add(createFormRow(adminusernamelabel9, adminusernametext9));
		panel1_10.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_10.add(createFormRow(adminpasswordlabel9, adminpasswordtext9, toggleButton10));
		panel1_10.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_10.add(createFormRowRadioHorizontal(federatedradio9, nonfederatedradio9));
		panel1_10.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_10.add(createFormRowLink2(close8));
		// panel1.add(panel1_10);

	}

	private static void initialState11() {
		panel1_11.add(Box.createRigidArea(new Dimension(0, 20)));
		panel1_11.add(createFormRow(adminurllabel10, adminurltext10));
		panel1_11.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_11.add(createFormRowCombo(serverrolelabel10, serverrolecomboBox10));
		panel1_11.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_11.add(createFormRow(adminusernamelabel10, adminusernametext10));
		panel1_11.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_11.add(createFormRow(adminpasswordlabel10, adminpasswordtext10, toggleButton11));
		panel1_11.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_11.add(createFormRowRadioHorizontal(federatedradio10, nonfederatedradio10));
		panel1_11.add(Box.createRigidArea(new Dimension(0, 10))); // 20 pixels gap
		panel1_11.add(createFormRowLink2(close9));
		// panel1.add(panel1_11);

	}
	private static void testRunSetting1(){
		if(flagKUB) {
			kubernetes.setSelected(true);
		}else {
			kubernetes.setSelected(false);
		}
		if (flagRunalltest) {
			if (flagKUB) {
				flagServerRole=false;	
				flagdatastore=false;
				flagloginportal=true;
				flagloginmanager=true;
				flagloginadmin=true;
				flagfeaturelayer=true;
				flagtilelayer=true;
				flagmap=true;
				flaggroup=true;
				flagorganization=true;
				flagscenelayer=true;
				flagwebappbuilder=true;
				flagexperiencebuilder=true;
				runalltest.setSelected(true);
				loginportal.setSelected(true);
				loginmanager.setSelected(true);
				loginserver.setSelected(true);
				// content.setSelected(true);
				feature.setSelected(true);
				tile.setSelected(true);
				map.setSelected(true);
				webappbuilder.setSelected(true);
				experiencebuilder.setSelected(true);
				scenelayer.setSelected(true);
				hosting.setSelected(false);
				organization.setSelected(true);
				groupfunctionality.setSelected(true);
				datastore.setSelected(false);
				loginportal.setEnabled(false);
				loginserver.setEnabled(false);
				loginmanager.setEnabled(false);
				hosting.setEnabled(false);
				// content.setEnabled(false);
				feature.setEnabled(false);
				tile.setEnabled(false);
				experiencebuilder.setEnabled(false);
				webappbuilder.setEnabled(false);
				map.setEnabled(false);
				groupfunctionality.setEnabled(false);
				organization.setEnabled(false);
				scenelayer.setEnabled(false);
				datastore.setEnabled(false);
			} else {
				flagServerRole=true;	
				flagdatastore=true;
				flagloginportal=true;
				flagloginmanager=true;
				flagloginadmin=true;
				flagfeaturelayer=true;
				flagtilelayer=true;
				flagmap=true;
				flaggroup=true;
				flagorganization=true;
				flagscenelayer=true;
				flagwebappbuilder=true;
				flagexperiencebuilder=true;
				runalltest.setSelected(true);
				loginportal.setSelected(true);
				loginmanager.setSelected(true);
				loginserver.setSelected(true);
				// content.setSelected(true);
				feature.setSelected(true);
				tile.setSelected(true);
				map.setSelected(true);
				webappbuilder.setSelected(true);
				experiencebuilder.setSelected(true);
				scenelayer.setSelected(true);
				hosting.setSelected(true);
				organization.setSelected(true);
				groupfunctionality.setSelected(true);
				datastore.setSelected(true);
				loginportal.setEnabled(false);
				loginserver.setEnabled(false);
				loginmanager.setEnabled(false);
				hosting.setEnabled(false);
				// content.setEnabled(false);
				feature.setEnabled(false);
				tile.setEnabled(false);
				webappbuilder.setEnabled(false);
				experiencebuilder.setEnabled(false);
				map.setEnabled(false);
				groupfunctionality.setEnabled(false);
				organization.setEnabled(false);
				scenelayer.setEnabled(false);
				datastore.setEnabled(false);
			}
			
		}else {
			if (flagKUB) {
				flagServerRole=false;	
				flagdatastore=false;
				flagloginportal=false;
				flagloginmanager=false;
				flagloginadmin=false;
				flagfeaturelayer=false;
				flagtilelayer=false;
				flagmap=false;
				flaggroup=false;
				flagorganization=false;
				flagscenelayer=false;
				flagwebappbuilder=false;
				flagexperiencebuilder=false;
				runalltest.setSelected(false);
				loginportal.setSelected(false);
				loginmanager.setSelected(false);
				loginserver.setSelected(false);
				// content.setSelected(true);
				feature.setSelected(false);
				tile.setSelected(false);
				map.setSelected(false);
				webappbuilder.setSelected(false);
				scenelayer.setSelected(false);
				experiencebuilder.setSelected(false);
				hosting.setSelected(false);
				organization.setSelected(false);
				groupfunctionality.setSelected(false);
				datastore.setSelected(false);
				loginportal.setEnabled(true);
				loginserver.setEnabled(true);
				loginmanager.setEnabled(true);
				hosting.setEnabled(false);
				// content.setEnabled(false);
				feature.setEnabled(true);
				tile.setEnabled(true);
				webappbuilder.setEnabled(true);
				map.setEnabled(true);
				groupfunctionality.setEnabled(true);
				organization.setEnabled(true);
				scenelayer.setEnabled(true);
				experiencebuilder.setEnabled(true);
				datastore.setEnabled(false);
			}
			else {	
				flagServerRole=false;	
				flagdatastore=false;
				flagServerRole=false;	
				flagdatastore=false;
				flagloginportal=false;
				flagloginmanager=false;
				flagloginadmin=false;
				flagfeaturelayer=false;
				flagtilelayer=false;
				flagmap=false;
				flaggroup=false;
				flagorganization=false;
				flagscenelayer=false;
				flagwebappbuilder=false;
				flagexperiencebuilder=false;
				runalltest.setSelected(false);
				loginportal.setSelected(false);
				loginmanager.setSelected(false);
				loginserver.setSelected(false);
				// content.setSelected(true);
				feature.setSelected(false);
				tile.setSelected(false);
				map.setSelected(false);
				webappbuilder.setSelected(false);
				scenelayer.setSelected(false);
				experiencebuilder.setSelected(false);
				hosting.setSelected(false);
				organization.setSelected(false);
				groupfunctionality.setSelected(false);
				datastore.setSelected(false);
				loginportal.setEnabled(true);
				loginserver.setEnabled(true);
				loginmanager.setEnabled(true);
				hosting.setEnabled(true);
				// content.setEnabled(false);
				feature.setEnabled(true);
				tile.setEnabled(true);
				webappbuilder.setEnabled(true);
				map.setEnabled(true);
				groupfunctionality.setEnabled(true);
				organization.setEnabled(true);
				scenelayer.setEnabled(true);
				experiencebuilder.setEnabled(true);
				datastore.setEnabled(true);
		}
		}
	}
	private static void testRunSetting(){
		if(flagKUB) {
			kubernetes.setSelected(true);
		}else {
			kubernetes.setSelected(false);
		}
		if (flagRunalltest) {
			if (flagKUB) {
				flagServerRole=false;	
				flagdatastore=false;
				runalltest.setSelected(true);
				loginportal.setSelected(true);
				loginmanager.setSelected(true);
				loginserver.setSelected(true);
				// content.setSelected(true);
				feature.setSelected(true);
				tile.setSelected(true);
				map.setSelected(true);
				webappbuilder.setSelected(true);
				scenelayer.setSelected(true);
				experiencebuilder.setSelected(true);
				hosting.setSelected(false);
				organization.setSelected(true);
				groupfunctionality.setSelected(true);
				datastore.setSelected(false);
				loginportal.setEnabled(false);
				loginserver.setEnabled(false);
				loginmanager.setEnabled(false);
				hosting.setEnabled(false);
				// content.setEnabled(false);
				feature.setEnabled(false);
				tile.setEnabled(false);
				webappbuilder.setEnabled(false);
				map.setEnabled(false);
				groupfunctionality.setEnabled(false);
				organization.setEnabled(false);
				scenelayer.setEnabled(false);
				experiencebuilder.setEnabled(false);
				datastore.setEnabled(false);
			} else {
				runalltest.setSelected(true);
				loginportal.setSelected(true);
				loginmanager.setSelected(true);
				loginserver.setSelected(true);
				// content.setSelected(true);
				feature.setSelected(true);
				tile.setSelected(true);
				map.setSelected(true);
				webappbuilder.setSelected(true);
				scenelayer.setSelected(true);
				experiencebuilder.setSelected(true);
				hosting.setSelected(true);
				organization.setSelected(true);
				groupfunctionality.setSelected(true);
				datastore.setSelected(true);
				loginportal.setEnabled(false);
				loginserver.setEnabled(false);
				loginmanager.setEnabled(false);
				hosting.setEnabled(false);
				// content.setEnabled(false);
				feature.setEnabled(false);
				tile.setEnabled(false);
				webappbuilder.setEnabled(false);
				map.setEnabled(false);
				groupfunctionality.setEnabled(false);
				organization.setEnabled(false);
				scenelayer.setEnabled(false);
				experiencebuilder.setEnabled(false);
				datastore.setEnabled(false);
			}
			
		}else {
			if (flagKUB) {
				flagServerRole=false;	
				flagdatastore=false;
				datastore.setEnabled(false);
				datastore.setSelected(false);
				hosting.setEnabled(false);
				hosting.setSelected(false);
				if (flagloginportal) {
					loginportal.setSelected(true);
					loginportal.setEnabled(true);
				}
				if (flagloginadmin) {
					loginserver.setSelected(true);
					loginserver.setEnabled(true);
				}
				if (flagloginmanager) {
					loginmanager.setSelected(true);
					loginmanager.setEnabled(true);
				}
				/*
				 * if(flagcontent) { loginportal.setSelected(true);
				 * loginportal.setEnabled(false); content.setSelected(true); }
				 */
				if (flagfeaturelayer) {
					loginportal.setSelected(true);
					loginportal.setEnabled(false);
					feature.setEnabled(true);
					feature.setSelected(true);
				}
				if (flagtilelayer) {
					loginportal.setSelected(true);
					loginportal.setEnabled(false);
					//feature.setEnabled(false);
					//feature.setSelected(true);
					tile.setEnabled(true);
					tile.setSelected(true);
				}
				if (flagmap) {
					loginportal.setSelected(true);
					loginportal.setEnabled(false);
					map.setEnabled(true);
					map.setSelected(true);
				}
				if (flagwebappbuilder) {
					loginportal.setSelected(true);
					loginportal.setEnabled(false);
					webappbuilder.setEnabled(true);
					webappbuilder.setSelected(true);
					experiencebuilder.setEnabled(false);
					experiencebuilder.setSelected(false);
				}
				if (flagscenelayer) {
					loginportal.setSelected(true);
					loginportal.setEnabled(false);
					scenelayer.setEnabled(true);
					scenelayer.setSelected(true);
				}
				if (flagexperiencebuilder) {
					loginportal.setSelected(true);
					loginportal.setEnabled(false);
					experiencebuilder.setEnabled(true);
					experiencebuilder.setSelected(true);
					webappbuilder.setEnabled(false);
					webappbuilder.setSelected(false);
				}
				if (flagServerRole) {
					loginportal.setSelected(true);
					loginportal.setEnabled(false);
					hosting.setEnabled(true);
					hosting.setSelected(true);
				}
				
				if (flagorganization) {
					loginportal.setSelected(true);
					loginportal.setEnabled(false);
					organization.setEnabled(true);
					organization.setSelected(true);
				}
				if (flaggroup) {
					loginportal.setSelected(true);
					loginportal.setEnabled(false);
				//	feature.setEnabled(false);
				//	feature.setSelected(true);
					groupfunctionality.setEnabled(true);
					groupfunctionality.setSelected(true);
				}
				if (flagdatastore) {
					//loginmanager.setSelected(true);
					//loginmanager.setEnabled(false);
					datastore.setEnabled(true);
					datastore.setSelected(true);
				
			}
			}
			else {	
		if (flagloginportal) {
			loginportal.setSelected(true);
			loginportal.setEnabled(true);
		}
		if (flagloginadmin) {
			loginserver.setSelected(true);
			loginserver.setEnabled(true);
		}
		if (flagloginmanager) {
			loginmanager.setSelected(true);
			loginmanager.setEnabled(true);
		}
		/*
		 * if(flagcontent) { loginportal.setSelected(true);
		 * loginportal.setEnabled(false); content.setSelected(true); }
		 */
		if (flagfeaturelayer) {
			loginportal.setSelected(true);
			loginportal.setEnabled(false);
			feature.setEnabled(true);
			feature.setSelected(true);
		}
		if (flagtilelayer) {
			loginportal.setSelected(true);
			loginportal.setEnabled(false);
			//feature.setEnabled(false);
			//feature.setSelected(true);
			tile.setEnabled(true);
			tile.setSelected(true);
		}
		if (flagmap) {
			loginportal.setSelected(true);
			loginportal.setEnabled(false);
			map.setEnabled(true);
			map.setSelected(true);
		}
		if (flagwebappbuilder) {
			loginportal.setSelected(true);
			loginportal.setEnabled(false);
			webappbuilder.setEnabled(true);
			webappbuilder.setSelected(true);
			experiencebuilder.setEnabled(false);
			experiencebuilder.setSelected(false);
		}
		if (flagscenelayer) {
			loginportal.setSelected(true);
			loginportal.setEnabled(false);
			scenelayer.setEnabled(true);
			scenelayer.setSelected(true);
		}
		if (flagexperiencebuilder) {
			loginportal.setSelected(true);
			loginportal.setEnabled(false);
			experiencebuilder.setEnabled(true);
			experiencebuilder.setSelected(true);
			webappbuilder.setEnabled(false);
			webappbuilder.setSelected(false);
		}
		if (flagServerRole) {
			loginportal.setSelected(true);
			loginportal.setEnabled(false);
			hosting.setEnabled(true);
			hosting.setSelected(true);
		}
		else {
			hosting.setEnabled(true);
		}
		if (flagorganization) {
			loginportal.setSelected(true);
			loginportal.setEnabled(false);
			organization.setEnabled(true);
			organization.setSelected(true);
		}
		if (flaggroup) {
			loginportal.setSelected(true);
			loginportal.setEnabled(false);
		//	feature.setEnabled(false);As per requirement, group functionality should be independent of feature layer.
		//	feature.setSelected(true);
			groupfunctionality.setEnabled(true);
			groupfunctionality.setSelected(true);
		}
		if (flagdatastore) {
			//loginmanager.setSelected(true);
			//loginmanager.setEnabled(false);
			datastore.setEnabled(true);
			datastore.setSelected(true);
		}else {
			datastore.setEnabled(true);
		}
			}
		}
	}
	private static void edittextfieldRundisabled(JTextField text) {
		text.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				rundisabled();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				rundisabled();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				rundisabled();
			}
		});
	}

	private static void editcheckboxRundisabled(JCheckBox text) {
		text.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rundisabled();
			}
		});
	}

	private static void editcomboboxRundisabled(JComboBox text) {
		text.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rundisabled();
			}
		});
	}

	private static void editbuttonRundisabled(JRadioButton text) {
		text.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rundisabled();
			}
		});
	}
	private static void editCheckboxRundisabled(JCheckBox text) {
		text.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rundisabled();
			}
		});
	}

	private static void addSitetoPanel() {

		panel1_12.add(createFormRowLink(addsite));

	}

	private static JPanel createFormRow(JLabel label, JTextField textField) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
		rowPanel.setPreferredSize(new Dimension(500, 35));
		
		label.setMinimumSize(new Dimension(200, 30));
		label.setPreferredSize(new Dimension(200, 30));
		textField.setPreferredSize(new Dimension(150, 35));
		textField.setMinimumSize(new Dimension(150, 30));
		label.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField.setFont(new Font("Verdana", Font.PLAIN, 15));
		textField.setBackground(Color.LIGHT_GRAY);
		// JLabel label = new JLabel(labelText);
		// JTextField textField = new JTextField(15);
		// rowPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		rowPanel.add(label, BorderLayout.CENTER);
		rowPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add some space between label and text field
		rowPanel.add(textField, BorderLayout.CENTER);
		rowPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		return rowPanel;
	}
	private static JPanel createFormRow(JCheckBox textField) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new BorderLayout());
		//rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
		rowPanel.setPreferredSize(new Dimension(1000, 35));
		
		textField.setPreferredSize(new Dimension(500, 35));
		textField.setMinimumSize(new Dimension(500, 30));
		textField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textField.setFont(new Font("Verdana", Font.PLAIN, 14));
		rowPanel.setBorder(BorderFactory.createEmptyBorder(0, 205, 0, 0)); // 
		rowPanel.add(textField, BorderLayout.WEST);
		rowPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		return rowPanel;
		
		
	}
	private static JPanel createFormRow(JLabel label, JTextField textField, JButton jbtn) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
		rowPanel.setPreferredSize(new Dimension(500, 35));
		label.setMinimumSize(new Dimension(200, 30));
		label.setPreferredSize(new Dimension(200, 30));
		// jbtn.setMinimumSize(new Dimension(30, 70));
		// jbtn.setPreferredSize(new Dimension(30, 70));
		textField.setPreferredSize(new Dimension(150, 30));
		textField.setMinimumSize(new Dimension(150, 30));
		label.setAlignmentX(Component.RIGHT_ALIGNMENT);
		textField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField.setFont(new Font("Verdana", Font.PLAIN, 15));
		textField.setBackground(Color.LIGHT_GRAY);
		// jbtn.setBackground(Color.LIGHT_GRAY);
		jbtn.setBorder(null);
		jbtn.setOpaque(true);
		// JLabel label = new JLabel(labelText);
		// JTextField textField = new JTextField(15);
		// rowPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		rowPanel.add(label, BorderLayout.CENTER);
		rowPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add some space between label and text field
		rowPanel.add(textField, BorderLayout.CENTER);
		rowPanel.add(jbtn, BorderLayout.CENTER);
		rowPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		return rowPanel;
	}

	private static JPanel createFormRowHeader(JLabel label) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
		rowPanel.setPreferredSize(new Dimension(550, 35));
		rowPanel.add(label, BorderLayout.WEST);

		return rowPanel;
	}

	private static JPanel createFormRowRadioHorizontal(JRadioButton button1, JRadioButton button2) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
		rowPanel.setPreferredSize(new Dimension(500, 25));

		// rowPanel.add(Box.createRigidArea(new Dimension(300, 0)));
		button1.setFont(new Font("Verdana", Font.PLAIN, 14));
		button2.setFont(new Font("Verdana", Font.PLAIN, 14));
		// button1.setBackground(Color.GRAY);
		// button2.setBackground(Color.GRAY);
		rowPanel.add(button1, BorderLayout.CENTER);
		rowPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add some space between label and text field
		rowPanel.add(button2, BorderLayout.CENTER);
		// rowPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		return rowPanel;
	}

	private static JPanel createFormRowCheckbox(JCheckBox button1) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.Y_AXIS));
		rowPanel.setPreferredSize(new Dimension(1500, 30));
		button1.setMinimumSize(new Dimension(1500, 30));
		// button1.set
		button1.setFont(new Font("Verdana", Font.PLAIN, 14));
		button1.setBorderPainted(false);

		rowPanel.add(button1, BorderLayout.WEST);
		rowPanel.add(Box.createRigidArea(new Dimension(70, 0))); // Add some space between label and text field

		return rowPanel;
	}

	private static JPanel createFormRowLink1(JLabel label, JLabel label1) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new BorderLayout());
		// rowPanel.setPreferredSize(new Dimension(700, 25));
		rowPanel.setPreferredSize(new Dimension(900, 30));
		rowPanel.setMinimumSize(new Dimension(900, 30));
		// rowPanel.setMaximumSize(new Dimension(1500, 30));
		// rowPanel.add(Box.createRigidArea(new Dimension(500, 0)));
		label.setOpaque(false);
		// addsite.setBackground(Color.GRAY);
		label.setFont(new Font("Verdana", Font.PLAIN, 12));
		label.setForeground(Color.BLUE);
		label1.setOpaque(false);
		// addsite.setBackground(Color.GRAY);
		label1.setFont(new Font("Verdana", Font.PLAIN, 12));
		label1.setForeground(Color.BLUE);
		// label.setAlignmentX(Component.LEFT_ALIGNMENT);
		label1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		// label.setPreferredSize(new Dimension(800, 25));
		// label1.setPreferredSize(new Dimension(70, 25));

		rowPanel.add(label, BorderLayout.WEST);
		rowPanel.add(label1, BorderLayout.EAST);

		// rowPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		return rowPanel;
	}

	private static JPanel createFormRowLink(JLabel label) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new BorderLayout());
		rowPanel.setPreferredSize(new Dimension(700, 30));

		// rowPanel.add(Box.createRigidArea(new Dimension(500, 0)));
		label.setOpaque(false);
		// addsite.setBackground(Color.GRAY);
		label.setFont(new Font("Verdana", Font.PLAIN, 12));
		label.setForeground(Color.BLUE);

		rowPanel.add(label, BorderLayout.WEST);

		// rowPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		return rowPanel;
	}

	private static JPanel createFormRowLink2(JLabel label) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new BorderLayout());
		rowPanel.setPreferredSize(new Dimension(900, 30));
		// rowPanel.add(Box.createRigidArea(new Dimension(500, 0)));
		label.setOpaque(false);
		// addsite.setBackground(Color.GRAY);
		label.setFont(new Font("Verdana", Font.PLAIN, 12));
		label.setForeground(Color.BLUE);

		rowPanel.add(label, BorderLayout.EAST);

		return rowPanel;
	}

	private static JPanel createFormRowButton(JButton label) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
		rowPanel.setPreferredSize(new Dimension(30, 25));
		label.setPreferredSize(new Dimension(30, 25));
		// rowPanel.add(Box.createRigidArea(new Dimension(500, 0)));
		label.setOpaque(false);
		// addsite.setBackground(Color.GRAY);
		label.setFont(new Font("Verdana", Font.PLAIN, 12));
		label.setForeground(Color.BLUE);

		rowPanel.add(label, BorderLayout.EAST);
		// rowPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		return rowPanel;
	}

	private static JPanel createFormRowCombo(JLabel label, JComboBox textField) {
		JPanel rowPanel = new JPanel();
		rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
		rowPanel.setPreferredSize(new Dimension(500, 40));
		label.setMinimumSize(new Dimension(200, 25));
		label.setPreferredSize(new Dimension(205, 40));
		textField.setPreferredSize(new Dimension(200, 40));
		textField.setMinimumSize(new Dimension(200, 30));
		label.setAlignmentX(Component.RIGHT_ALIGNMENT);
		// textField.setAlignmentX(Component.RIGHT_ALIGNMENT);
		label.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField.setBackground(Color.LIGHT_GRAY);
		// JLabel label = new JLabel(labelText);
		// JTextField textField = new JTextField(15);
		// rowPanel.add(Box.createRigidArea(new Dimension(30, 0)));
		rowPanel.add(label, BorderLayout.CENTER);
		rowPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add some space between label and text field
		rowPanel.add(textField, BorderLayout.CENTER);
		rowPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		return rowPanel;
	}

	private static void removehighlights() throws Exception {
		adminurltext1.setBackground(Color.LIGHT_GRAY);
		adminusernametext1.setBackground(Color.LIGHT_GRAY);
		adminpasswordtext1.setBackground(Color.LIGHT_GRAY);
		adminurltext2.setBackground(Color.LIGHT_GRAY);
		adminusernametext2.setBackground(Color.LIGHT_GRAY);
		adminpasswordtext2.setBackground(Color.LIGHT_GRAY);
		adminurltext3.setBackground(Color.LIGHT_GRAY);
		adminusernametext3.setBackground(Color.LIGHT_GRAY);
		adminpasswordtext3.setBackground(Color.LIGHT_GRAY);
		adminurltext4.setBackground(Color.LIGHT_GRAY);
		adminusernametext4.setBackground(Color.LIGHT_GRAY);
		adminpasswordtext4.setBackground(Color.LIGHT_GRAY);
		adminurltext5.setBackground(Color.LIGHT_GRAY);
		adminusernametext5.setBackground(Color.LIGHT_GRAY);
		adminpasswordtext5.setBackground(Color.LIGHT_GRAY);
		adminurltext6.setBackground(Color.LIGHT_GRAY);
		adminusernametext6.setBackground(Color.LIGHT_GRAY);
		adminpasswordtext6.setBackground(Color.LIGHT_GRAY);
		adminurltext7.setBackground(Color.LIGHT_GRAY);
		adminusernametext7.setBackground(Color.LIGHT_GRAY);
		adminpasswordtext7.setBackground(Color.LIGHT_GRAY);
		adminurltext8.setBackground(Color.LIGHT_GRAY);
		adminusernametext8.setBackground(Color.LIGHT_GRAY);
		adminpasswordtext8.setBackground(Color.LIGHT_GRAY);
		adminurltext9.setBackground(Color.LIGHT_GRAY);
		adminusernametext9.setBackground(Color.LIGHT_GRAY);
		adminpasswordtext9.setBackground(Color.LIGHT_GRAY);
		adminurltext10.setBackground(Color.LIGHT_GRAY);
		adminusernametext10.setBackground(Color.LIGHT_GRAY);
		adminpasswordtext10.setBackground(Color.LIGHT_GRAY);
		portalurltext.setBackground(Color.LIGHT_GRAY);
		portalusernametext.setBackground(Color.LIGHT_GRAY);
		portalpasswordtext.setBackground(Color.LIGHT_GRAY);
		panel1.validate();
		panel1.repaint();
	}

	private void verifyvalues() throws Exception {
		boolean flag1 = true;

		String msg = "";
		removehighlights();

		if (!(flagRunalltest || flagloginportal || flagloginadmin || flagloginmanager || flagtilelayer || flagServerRole
				|| flagfeaturelayer || flaggroup || flagorganization || flagscenelayer || flagmap || flagwebappbuilder
				|| flagdatastore)) {
			flag1 = false;
			String message = "Please select atleast one test to run";
			JOptionPane.showMessageDialog(frame, message, "Dialog", JOptionPane.ERROR_MESSAGE);
			tabs.setSelectedIndex(1);
			return;

		}
		if (flagloginportal) {
			msg = "";
			msg = "Please provide data for fields:<br>";
			flag1 = true;
			if (portalurltext.getText().isEmpty()) {
				flag1 = false;
				msg = msg + "Portal URL<br>";
				portalurltext.setBackground(Color.yellow);
			}

			if (portalusernametext.getText().isEmpty()) {
				flag1 = false;
				msg = msg + ", Portal username<br>";
				portalusernametext.setBackground(Color.yellow);
			}
			if (portalpasswordtext.getText().isEmpty()) {
				flag1 = false;
				msg = msg + ", Portal password<br>";
				portalpasswordtext.setBackground(Color.yellow);
			}
			if (!flag1) {
				tabs.setSelectedIndex(0);
				panel1.validate();
				panel1.repaint();
				JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog", JOptionPane.ERROR_MESSAGE);

				return;
			}

		}
		if (flagloginadmin) {
			 flag1 = true;
			if ((adminurltext1.getText().isEmpty()) && (adminurltext2.getText().isEmpty())
					&& (adminurltext3.getText().isEmpty()) && (adminurltext4.getText().isEmpty())
					&& (adminurltext5.getText().isEmpty()) && (adminurltext6.getText().isEmpty())
					&& (adminurltext7.getText().isEmpty()) && (adminurltext8.getText().isEmpty())
					&& (adminurltext9.getText().isEmpty()) && (adminurltext10.getText().isEmpty())) {
				tabs.setSelectedIndex(0);
				flag1 = false;
				JOptionPane.showMessageDialog(frame, "<html>Please provide data for at least one server</html>",
						"Dialog", JOptionPane.ERROR_MESSAGE);
				return;
			}
			boolean flag2 = false,flag3= false,flag4= false,flag5= false,flag6= false,flag7= false,flag8= false,flag9= false,flag10= false,flag11= false;
			if (!(adminurltext1.getText().isEmpty())&&(serverrolecomboBox1.getSelectedItem().toString().toLowerCase().contains("hosting"))){
				flag2=true;}
			if (!(adminurltext2.getText().isEmpty())&&(serverrolecomboBox2.getSelectedItem().toString().toLowerCase().contains("hosting"))){
				flag3=true;
			}
			if (!(adminurltext3.getText().isEmpty())&&(serverrolecomboBox3.getSelectedItem().toString().toLowerCase().contains("hosting"))){
				flag4=true;
			}
			if (!(adminurltext4.getText().isEmpty())&&(serverrolecomboBox4.getSelectedItem().toString().toLowerCase().contains("hosting"))){
				flag5=true;
			}
			if (!(adminurltext5.getText().isEmpty())&&(serverrolecomboBox5.getSelectedItem().toString().toLowerCase().contains("hosting"))){
				flag6=true;
			}
			if (!(adminurltext6.getText().isEmpty())&&(serverrolecomboBox6.getSelectedItem().toString().toLowerCase().contains("hosting"))){
				flag7=true;
			}
			if (!(adminurltext7.getText().isEmpty())&&(serverrolecomboBox7.getSelectedItem().toString().toLowerCase().contains("hosting"))){
				flag8=true;
			}
			if (!(adminurltext8.getText().isEmpty())&&(serverrolecomboBox8.getSelectedItem().toString().toLowerCase().contains("hosting"))){
				flag9=true;
			}
			if (!(adminurltext9.getText().isEmpty())&&(serverrolecomboBox9.getSelectedItem().toString().toLowerCase().contains("hosting"))){
				flag10=true;
			}
			if (!(adminurltext10.getText().isEmpty())&&(serverrolecomboBox10.getSelectedItem().toString().toLowerCase().contains("hosting"))){
				flag11=true;
			}
			if(!(flag2||flag3||flag4||flag5||flag6||flag7||flag8||flag9||flag10||flag11)) {
				tabs.setSelectedIndex(0);
				flag1 = false;
				JOptionPane.showMessageDialog(frame, "<html>Please provide  role 'Hosting' for at least one server</html>",
						"Dialog", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// The XOR operator returns true if an odd number of operands are true, and
			// false if an even number of operands are true.
			flag1 = true;
			if ((adminurltext1.isShowing())) {
				// eneter in loop if all are empty or anyone is empty
				if(serverrolecomboBox1.getSelectedItem().toString().toLowerCase().contains("hosting")){
					//if anyone is empty or all is empty
				if (!(!adminurltext1.getText().isEmpty() && !adminusernametext1.getText().isEmpty()
						&& !adminpasswordtext1.getText().isEmpty())) {
					//if all are not empty
					if (!(adminurltext1.getText().isEmpty() && adminusernametext1.getText().isEmpty()
							&& adminpasswordtext1.getText().isEmpty())) {
						
						msg = "Please provide data for fields:<br>";
						if (adminurltext1.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server URL 1<br>";
							adminurltext1.setBackground(Color.yellow);
						}

						if (adminusernametext1.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Username 1<br>";
							adminusernametext1.setBackground(Color.yellow);
						}
						if (adminpasswordtext1.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Password 1<br>";
							adminpasswordtext1.setBackground(Color.yellow);
						}
					}
				}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			flag1 = true;
			if (adminurltext2.isShowing()) {
				if(serverrolecomboBox2.getSelectedItem().toString().toLowerCase().contains("hosting")){
				if (!(!adminurltext2.getText().isEmpty() && !adminusernametext2.getText().isEmpty()
						&& !adminpasswordtext2.getText().isEmpty())) {
					if (!(adminurltext2.getText().isEmpty() && adminusernametext2.getText().isEmpty()
							&& adminpasswordtext2.getText().isEmpty())) {
						msg = "";

						msg = "Please provide data for fields:<br>";
						if (adminurltext2.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server URL 2<br>";
							adminurltext2.setBackground(Color.yellow);
						}

						if (adminusernametext2.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Username 2<br>";
							adminusernametext2.setBackground(Color.yellow);
						}
						if (adminpasswordtext2.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Password 2<br>";
							adminpasswordtext2.setBackground(Color.yellow);
						}
					}
				}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			flag1 = true;
			if (adminurltext3.isShowing()) {
				if(serverrolecomboBox3.getSelectedItem().toString().toLowerCase().contains("hosting")){
				if (!(!adminurltext3.getText().isEmpty() && !adminusernametext3.getText().isEmpty()
						&& !adminpasswordtext3.getText().isEmpty())) {
					if (!(adminurltext3.getText().isEmpty() && adminusernametext3.getText().isEmpty()
							&& adminpasswordtext3.getText().isEmpty())) {
						msg = "";

						msg = "Please provide data for fields:<br>";

						if (adminurltext3.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server URL 3<br>";
							adminurltext3.setBackground(Color.yellow);
						}

						if (adminusernametext3.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Username 3<br>";
							adminusernametext3.setBackground(Color.yellow);
						}
						if (adminpasswordtext3.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Password 3<br>";
							adminpasswordtext3.setBackground(Color.yellow);
						}
					}
				}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			flag1 = true;
			if (adminurltext4.isShowing()) {
				if(serverrolecomboBox4.getSelectedItem().toString().toLowerCase().contains("hosting")){
				if (!(!adminurltext4.getText().isEmpty() && !adminusernametext4.getText().isEmpty()
						&& !adminpasswordtext4.getText().isEmpty())) {
					if (!(adminurltext4.getText().isEmpty() && adminusernametext4.getText().isEmpty()
							&& adminpasswordtext4.getText().isEmpty())) {
						msg = "";

						msg = "Please provide data for fields:<br>";

						if (adminurltext4.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server URL 4<br>";
							adminurltext4.setBackground(Color.yellow);
						}

						if (adminusernametext4.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Username 4<br>";
							adminusernametext4.setBackground(Color.yellow);
						}
						if (adminpasswordtext4.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Password 4<br>";
							adminpasswordtext4.setBackground(Color.yellow);
						}
					}
				}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);

					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);

					return;
				}
			}
			flag1 = true;
			if (adminurltext5.isShowing()) {
				if(serverrolecomboBox5.getSelectedItem().toString().toLowerCase().contains("hosting")){
				if (!(!adminurltext5.getText().isEmpty() && !adminusernametext5.getText().isEmpty()
						&& !adminpasswordtext5.getText().isEmpty())) {
					if (!(adminurltext5.getText().isEmpty() && adminusernametext5.getText().isEmpty()
							&& adminpasswordtext5.getText().isEmpty())) {
						msg = "";

						msg = "Please provide data for fields:<br>";

						if (adminurltext5.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server URL 5<br>";
							adminurltext5.setBackground(Color.yellow);
						}

						if (adminusernametext5.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Username 5<br>";
							adminusernametext5.setBackground(Color.yellow);
						}
						if (adminpasswordtext5.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Password 5<br>";
							adminpasswordtext5.setBackground(Color.yellow);
						}
					}
				}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			flag1 = true;
			if (adminurltext6.isShowing()) {
				if(serverrolecomboBox6.getSelectedItem().toString().toLowerCase().contains("hosting")){
				if (!(!adminurltext6.getText().isEmpty() && !adminusernametext6.getText().isEmpty()
						&& !adminpasswordtext6.getText().isEmpty())) {
					if (!(adminurltext6.getText().isEmpty() && adminusernametext6.getText().isEmpty()
							&& adminpasswordtext6.getText().isEmpty())) {
						msg = "";

						msg = "Please provide data for fields:<br>";

						if (adminurltext6.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server URL 6<br>";
							adminurltext6.setBackground(Color.yellow);
						}

						if (adminusernametext6.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Username 6<br>";
							adminusernametext6.setBackground(Color.yellow);
						}
						if (adminpasswordtext6.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Password 6<br>";
							adminpasswordtext6.setBackground(Color.yellow);
						}
					}
				}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			flag1 = true;
			if (adminurltext7.isShowing()) {
				if(serverrolecomboBox7.getSelectedItem().toString().toLowerCase().contains("hosting")){
				if (!(!adminurltext7.getText().isEmpty() && !adminusernametext7.getText().isEmpty()
						&& !adminpasswordtext7.getText().isEmpty())) {
					if (!(adminurltext7.getText().isEmpty() && adminusernametext7.getText().isEmpty()
							&& adminpasswordtext7.getText().isEmpty())) {
						msg = "";

						msg = "Please provide data for fields:<br>";

						if (adminurltext7.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server URL 7<br>";
							adminurltext7.setBackground(Color.yellow);
						}

						if (adminusernametext7.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Username 7<br>";
							adminusernametext7.setBackground(Color.yellow);
						}
						if (adminpasswordtext7.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Password 7<br>";
							adminpasswordtext7.setBackground(Color.yellow);
						}
					}
				}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			flag1 = true;
			if (adminurltext8.isShowing()) {
				if(serverrolecomboBox8.getSelectedItem().toString().toLowerCase().contains("hosting")){
				if (!(!adminurltext8.getText().isEmpty() && !adminusernametext8.getText().isEmpty()
						&& !adminpasswordtext8.getText().isEmpty())) {
					if (!(adminurltext8.getText().isEmpty() && adminusernametext8.getText().isEmpty()
							&& adminpasswordtext8.getText().isEmpty())) {
						msg = "";

						msg = "Please provide data for fields:<br>";

						if (adminurltext8.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server UR L8<br>";
							adminurltext8.setBackground(Color.yellow);
						}

						if (adminusernametext8.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Username 8<br>";
							adminusernametext8.setBackground(Color.yellow);
						}
						if (adminpasswordtext8.getText().isEmpty()) {
							flag1 = false;
							msg = msg + ", Server Admin Password 8<br>";
							adminpasswordtext8.setBackground(Color.yellow);
						}
					}
				}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			flag1 = true;
			if (adminurltext9.isShowing()) {
				if(serverrolecomboBox9.getSelectedItem().toString().toLowerCase().contains("hosting")){
				if (!(!adminurltext9.getText().isEmpty() && !adminusernametext9.getText().isEmpty()
						&& !adminpasswordtext9.getText().isEmpty())) {
					if (!(adminurltext9.getText().isEmpty() && adminusernametext9.getText().isEmpty()
							&& adminpasswordtext9.getText().isEmpty())) {
						msg = "";

						msg = "Please provide data for fields:<br>";

						if (adminurltext9.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server URL 9<br>";
							adminurltext9.setBackground(Color.yellow);
						}

						if (adminusernametext9.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Username 9<br>";
							adminusernametext9.setBackground(Color.yellow);
						}
						if (adminpasswordtext9.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Password 9<br>";
							adminpasswordtext9.setBackground(Color.yellow);
						}
					}
				}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

			flag1 = true;
			if (adminurltext10.isShowing()) {
				if(serverrolecomboBox10.getSelectedItem().toString().toLowerCase().contains("hosting")){
				if (!(!adminurltext10.getText().isEmpty() && !adminusernametext10.getText().isEmpty()
						&& !adminpasswordtext10.getText().isEmpty())) {
					if (!(adminurltext10.getText().isEmpty() && adminusernametext10.getText().isEmpty()
							&& adminpasswordtext10.getText().isEmpty())) {
						msg = "";

						msg = "Please provide data for fields:<br>";

						if (adminurltext10.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server URL 10<br>";
							adminurltext10.setBackground(Color.yellow);
						}

						if (adminusernametext10.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Username 10<br>";
							adminusernametext10.setBackground(Color.yellow);
						}
						if (adminpasswordtext10.getText().isEmpty()) {
							flag1 = false;
							msg = msg + " Server Admin Password 10<br>";
							adminpasswordtext10.setBackground(Color.yellow);
						}
					}
				}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

		}
		if (flagloginmanager) {
			if ((adminurltext1.getText().isEmpty()) && (adminurltext2.getText().isEmpty())
					&& (adminurltext3.getText().isEmpty()) && (adminurltext4.getText().isEmpty())
					&& (adminurltext5.getText().isEmpty()) && (adminurltext6.getText().isEmpty())
					&& (adminurltext7.getText().isEmpty()) && (adminurltext8.getText().isEmpty())
					&& (adminurltext9.getText().isEmpty()) && (adminurltext10.getText().isEmpty())) {
				tabs.setSelectedIndex(0);
				flag1 = false;
				JOptionPane.showMessageDialog(frame, "<html>Please provide data for at least one Server Url</html>",
						"Dialog", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (adminurltext1.isShowing()) {
				if (!(adminurltext1.getText().isEmpty())) {
					msg = "";
					msg = "Please provide data for fields:<br>";
					flag1 = true;
					if (federatedradio1.isSelected()) {
						if (portalusernametext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Username<br>";
							portalusernametext.setBackground(Color.yellow);
						}
						if (portalpasswordtext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Password<br>";
							portalpasswordtext.setBackground(Color.yellow);
						}

					} else {

						if (adminusernametext1.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Username 1<br>";
							adminusernametext1.setBackground(Color.yellow);
						}
						if (adminpasswordtext1.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Password 1<br>";
							adminpasswordtext1.setBackground(Color.yellow);
						}
					}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (adminurltext2.isShowing()) {

				if (!(adminurltext2.getText().isEmpty())) {
					msg = "";
					msg = "Please provide data for fields:<br>";
					flag1 = true;
					if (federatedradio2.isSelected()) {
						if (portalusernametext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal username<br>";
							portalusernametext.setBackground(Color.yellow);
						}
						if (portalpasswordtext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal password<br>";
							portalpasswordtext.setBackground(Color.yellow);
						}

					} else {

						if (adminusernametext2.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Username 2<br>";
							adminusernametext2.setBackground(Color.yellow);
						}
						if (adminpasswordtext2.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Password 2<br>";
							adminpasswordtext2.setBackground(Color.yellow);
						}
					}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (adminurltext3.isShowing()) {
				if (!(adminurltext3.getText().isEmpty())) {
					msg = "";
					flag1 = true;
					msg = "Please provide data for fields:<br>";
					if (federatedradio3.isSelected()) {
						if (portalusernametext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Username<br>";
							portalusernametext.setBackground(Color.yellow);
						}
						if (portalpasswordtext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Password<br>";
							portalpasswordtext.setBackground(Color.yellow);
						}

					} else {

						if (adminusernametext3.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Username 3<br>";
							adminusernametext3.setBackground(Color.yellow);
						}
						if (adminpasswordtext3.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Password 3<br>";
							adminpasswordtext3.setBackground(Color.yellow);
						}
					}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (adminurltext4.isShowing()) {
				if (!(adminurltext4.getText().isEmpty())) {
					msg = "";
					msg = "Please provide data for fields:<br>";
					flag1 = true;
					if (federatedradio4.isSelected()) {
						if (portalusernametext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Username<br>";
							portalusernametext.setBackground(Color.yellow);
						}
						if (portalpasswordtext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Password<br>";
							portalpasswordtext.setBackground(Color.yellow);
						}

					} else {

						if (adminusernametext4.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Username 4<br>";
							adminusernametext4.setBackground(Color.yellow);
						}
						if (adminpasswordtext4.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Password 4<br>";
							adminpasswordtext4.setBackground(Color.yellow);
						}
					}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (adminurltext5.isShowing()) {
				if (!(adminurltext5.getText().isEmpty())) {
					msg = "";
					msg = "Please provide data for fields:<br>";
					flag1 = true;
					if (federatedradio5.isSelected()) {
						if (portalusernametext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Username<br>";
							portalusernametext.setBackground(Color.yellow);
						}
						if (portalpasswordtext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Password<br>";
							portalpasswordtext.setBackground(Color.yellow);
						}

					} else {

						if (adminusernametext5.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Username 5<br>";
							adminusernametext5.setBackground(Color.yellow);
						}
						if (adminpasswordtext2.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Password 5<br>";
							adminpasswordtext5.setBackground(Color.yellow);
						}
					}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (adminurltext6.isShowing()) {
				if (!(adminurltext6.getText().isEmpty())) {
					msg = "";
					msg = "Please provide data for fields:<br>";
					flag1 = true;
					if (federatedradio6.isSelected()) {
						if (portalusernametext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Username<br>";
							portalusernametext.setBackground(Color.yellow);
						}
						if (portalpasswordtext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Password<br>";
							portalpasswordtext.setBackground(Color.yellow);
						}

					} else {

						if (adminusernametext6.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Username 6<br>";
							adminusernametext6.setBackground(Color.yellow);
						}
						if (adminpasswordtext6.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Password 6<br>";
							adminpasswordtext6.setBackground(Color.yellow);
						}
					}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (adminurltext7.isShowing()) {
				if (!(adminurltext7.getText().isEmpty())) {
					msg = "";
					flag1 = true;
					msg = "Please provide data for fields:<br>";
					if (federatedradio7.isSelected()) {
						if (portalusernametext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Username<br>";
							portalusernametext.setBackground(Color.yellow);
						}
						if (portalpasswordtext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Password<br>";
							portalpasswordtext.setBackground(Color.yellow);
						}

					} else {

						if (adminusernametext7.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Username 7<br>";
							adminusernametext7.setBackground(Color.yellow);
						}
						if (adminpasswordtext7.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Password 7<br>";
							adminpasswordtext7.setBackground(Color.yellow);
						}
					}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (adminurltext8.isShowing()) {
				if (!(adminurltext8.getText().isEmpty())) {
					msg = "";
					msg = "Please provide data for fields:<br>";
					flag1 = true;
					if (federatedradio8.isSelected()) {
						if (portalusernametext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Username<br>";
							portalusernametext.setBackground(Color.yellow);
						}
						if (portalpasswordtext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Password<br>";
							portalpasswordtext.setBackground(Color.yellow);
						}

					} else {

						if (adminusernametext8.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Username 8<br>";
							adminusernametext8.setBackground(Color.yellow);
						}
						if (adminpasswordtext8.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Password 8<br>";
							adminpasswordtext8.setBackground(Color.yellow);
						}
					}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (adminurltext9.isShowing()) {
				if (!(adminurltext9.getText().isEmpty())) {
					msg = "";
					flag1 = true;
					msg = "Please provide data for fields:<br>";
					if (federatedradio9.isSelected()) {
						if (portalusernametext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Username<br>";
							portalusernametext.setBackground(Color.yellow);
						}
						if (portalpasswordtext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Password<br>";
							portalpasswordtext.setBackground(Color.yellow);
						}

					} else {

						if (adminusernametext9.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Username 9<br>";
							adminusernametext9.setBackground(Color.yellow);
						}
						if (adminpasswordtext2.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Password 9<br>";
							adminpasswordtext9.setBackground(Color.yellow);
						}
					}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			if (adminurltext10.isShowing()) {
				if (!(adminurltext10.getText().isEmpty())) {
					msg = "";
					flag1 = true;
					msg = "Please provide data for fields:<br>";
					if (federatedradio10.isSelected()) {
						if (portalusernametext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Username<br>";
							portalusernametext.setBackground(Color.yellow);
						}
						if (portalpasswordtext.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Portal Password<br>";
							portalpasswordtext.setBackground(Color.yellow);
						}

					} else {

						if (adminusernametext10.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Username 10<br>";
							adminusernametext10.setBackground(Color.yellow);
						}
						if (adminpasswordtext10.getText().isEmpty()) {
							flag1 = false;
							msg = msg + "Server Admin Password 10<br>";
							adminpasswordtext10.setBackground(Color.yellow);
						}
					}
				}
				if (!flag1) {
					tabs.setSelectedIndex(0);
					panel1.validate();
					panel1.repaint();
					JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
		if (flagServerRole) {
			msg = "";
			flag1 = true;
			msg = "Please provide data for fields:<br>";
			if (portalurltext.getText().isEmpty()) {
				flag1 = false;
				msg = msg + "Portal URL<br>";
				portalurltext.setBackground(Color.yellow);
			}

			if (portalusernametext.getText().isEmpty()) {
				flag1 = false;
				msg = msg + " Portal Username<br>";
				portalusernametext.setBackground(Color.yellow);
			}
			if (portalpasswordtext.getText().isEmpty()) {
				flag1 = false;
				msg = msg + " Portal Password<br>";
				portalpasswordtext.setBackground(Color.yellow);
			}
			if (!flag1) {
				tabs.setSelectedIndex(0);
				panel1.validate();
				panel1.repaint();
				JOptionPane.showMessageDialog(frame, "<html>" + msg + "</html>", "Dialog", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if ((adminurltext1.getText().isEmpty()) && (adminurltext2.getText().isEmpty())
					&& (adminurltext3.getText().isEmpty()) && (adminurltext4.getText().isEmpty())
					&& (adminurltext5.getText().isEmpty()) && (adminurltext6.getText().isEmpty())
					&& (adminurltext7.getText().isEmpty()) && (adminurltext8.getText().isEmpty())
					&& (adminurltext9.getText().isEmpty()) && (adminurltext10.getText().isEmpty())) {
				tabs.setSelectedIndex(0);
				flag1 = false;
				JOptionPane.showMessageDialog(frame,
						"<html>Please provide data for at least one server URL and role</html>", "Dialog",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

		}

		try {
			if (flag1) {
				msglabel.setText("Saving Configuration...");
				savetoconfigdisplay();
				// lb.setText("Your changes have been saved.");
				JOptionPane.showMessageDialog(frame, "Your changes have been saved.");

				runenabled();

				panel1.repaint();
				panel2.repaint();
			} else {
				JOptionPane.showMessageDialog(frame, "Your changes could not be saved. Please try again.");
			}

		} catch (Exception e) {
			e.printStackTrace();

			JOptionPane.showMessageDialog(frame, "Your changes could not be saved. Please try again.");
		}
	}

	@SuppressWarnings("deprecation")
	private void savetoconfigdisplay() {
		Map<String, String> propertiesMap = new LinkedHashMap<>();
		// propertiesMap.put("database.url", "jdbc:mysql://localhost:3306/mydb");
		//// propertiesMap.put("database.username", "root");
		// propertiesMap.put("database.password", "password");
		// propertiesMap.put("app.version", "1.0.0");
		// propertiesMap.put("app.name", "MyApplication");
		propertiesMap.put("Browser", "");
		propertiesMap.put("PortalUrl", "");
		propertiesMap.put("PortalUserName", "");
		propertiesMap.put("PortalPassword", "");
		if (kubernetes.isSelected())
			propertiesMap.put("Kubernetes", "Yes");
		else
			propertiesMap.put("Kubertenes", "No");

		propertiesMap.put("ServerUrl1", "");
		propertiesMap.put("ServerAdminUsername1", "");
		propertiesMap.put("ServerAdminPassword1", "");
		propertiesMap.put("ServerRole1", (String) "");
		propertiesMap.put("Federated1", "Yes");
		propertiesMap.put("ServerUrl2", "");
		propertiesMap.put("ServerAdminUsername2", "");
		propertiesMap.put("ServerAdminPassword2", "");
		propertiesMap.put("ServerRole2", (String) "");
		propertiesMap.put("Federated2", "Yes");
		propertiesMap.put("ServerUrl3", "");
		propertiesMap.put("ServerAdminUsername3", "");
		propertiesMap.put("ServerAdminPassword3", "");
		propertiesMap.put("ServerRole3", (String) "");
		propertiesMap.put("Federated3", "Yes");
		propertiesMap.put("ServerUrl4", "");
		propertiesMap.put("ServerAdminUsername4", "");
		propertiesMap.put("ServerAdminPassword4", "");
		propertiesMap.put("ServerRole4", (String) "");
		propertiesMap.put("Federated4", "Yes");
		propertiesMap.put("ServerUrl5", "");
		propertiesMap.put("ServerAdminUsername5", "");
		propertiesMap.put("ServerAdminPassword5", "");
		propertiesMap.put("ServerRole5", (String) "");
		propertiesMap.put("Federated5", "Yes");
		propertiesMap.put("ServerUrl6", "");
		propertiesMap.put("ServerAdminUsername6", "");
		propertiesMap.put("ServerAdminPassword6", "");
		propertiesMap.put("ServerRole6", (String) "");
		propertiesMap.put("Federated6", "Yes");
		propertiesMap.put("ServerUrl7", "");
		propertiesMap.put("ServerAdminUsername7", "");
		propertiesMap.put("ServerAdminPassword7", "");
		propertiesMap.put("ServerRole7", (String) "");
		propertiesMap.put("Federated7", "Yes");
		propertiesMap.put("ServerUrl8", "");
		propertiesMap.put("ServerAdminUsername8", "");
		propertiesMap.put("ServerAdminPassword8", "");
		propertiesMap.put("ServerRole8", (String) "");
		propertiesMap.put("Federated8", "Yes");
		propertiesMap.put("ServerUrl9", "");
		propertiesMap.put("ServerAdminUsername9", "");
		propertiesMap.put("ServerAdminPassword9", "");
		propertiesMap.put("ServerRole9", (String) "");
		propertiesMap.put("Federated9", "Yes");
		propertiesMap.put("ServerUrl10", "");
		propertiesMap.put("ServerAdminUsername10", "");
		propertiesMap.put("ServerAdminPassword10", "");
		propertiesMap.put("ServerRole10", (String) "");
		propertiesMap.put("Federated10", "Yes");
		propertiesMap.put("RunAllTest", "No");

		propertiesMap.put("LoginServer", "No");
		propertiesMap.put("LoginManager", "No");
		propertiesMap.put("ValidateDataStores", "No");
		propertiesMap.put("LoginPortal", "No");
		propertiesMap.put("CreateFeatureLayer", "No");
		propertiesMap.put("CreateTileLayer", "No");
		propertiesMap.put("CreateSceneLayer", "No");
		propertiesMap.put("CreateMap", "No");
		propertiesMap.put("CreateWebAppBuilder", "No");
		propertiesMap.put("CreateExperienceBuilderApp", "No");
		propertiesMap.put("CreateMember", "No");
		propertiesMap.put("ValidateOrganization", "No");
		propertiesMap.put("ValidateServerRole", "No");
		// reading values from UI
		propertiesMap.put("Browser", (String) browsercomboBox.getSelectedItem());
		propertiesMap.put("PortalUrl", portalurltext.getText());
		propertiesMap.put("PortalUserName", portalusernametext.getText());
		propertiesMap.put("PortalPassword", portalpasswordtext.getText());
		propertiesMap.put("ServerUrl1", adminurltext1.getText());
		propertiesMap.put("ServerAdminUsername1", adminusernametext1.getText());
		propertiesMap.put("ServerAdminPassword1", adminpasswordtext1.getText());
		propertiesMap.put("ServerRole1", (String) serverrolecomboBox1.getSelectedItem());
		if (federatedradio1.isSelected())
			propertiesMap.put("Federated1", "Yes");
		else
			propertiesMap.put("Federated1", "No");

		propertiesMap.put("ServerUrl2", adminurltext2.getText());
		propertiesMap.put("ServerAdminUsername2", adminusernametext2.getText());
		propertiesMap.put("ServerAdminPassword2", adminpasswordtext2.getText());
		propertiesMap.put("ServerRole2", (String) serverrolecomboBox2.getSelectedItem());
		if (federatedradio2.isSelected())
			propertiesMap.put("Federated2", "Yes");
		else
			propertiesMap.put("Federated2", "No");

		propertiesMap.put("ServerUrl3", adminurltext3.getText());
		propertiesMap.put("ServerAdminUsername3", adminusernametext3.getText());
		propertiesMap.put("ServerAdminPassword3", adminpasswordtext3.getText());
		propertiesMap.put("ServerRole3", (String) serverrolecomboBox3.getSelectedItem());
		if (federatedradio3.isSelected())
			propertiesMap.put("Federated3", "Yes");
		else
			propertiesMap.put("Federated3", "No");

		propertiesMap.put("ServerUrl4", adminurltext4.getText());
		propertiesMap.put("ServerAdminUsername4", adminusernametext4.getText());
		propertiesMap.put("ServerAdminPassword4", adminpasswordtext4.getText());
		propertiesMap.put("ServerRole4", (String) serverrolecomboBox4.getSelectedItem());
		if (federatedradio4.isSelected())
			propertiesMap.put("Federated4", "Yes");
		else
			propertiesMap.put("Federated4", "No");

		propertiesMap.put("ServerUrl5", adminurltext5.getText());
		propertiesMap.put("ServerAdminUsername5", adminusernametext5.getText());
		propertiesMap.put("ServerAdminPassword5", adminpasswordtext5.getText());
		propertiesMap.put("ServerRole5", (String) serverrolecomboBox5.getSelectedItem());
		if (federatedradio5.isSelected())
			propertiesMap.put("Federated5", "Yes");
		else
			propertiesMap.put("Federated5", "No");

		propertiesMap.put("ServerUrl6", adminurltext6.getText());
		propertiesMap.put("ServerAdminUsername6", adminusernametext6.getText());
		propertiesMap.put("ServerAdminPassword6", adminpasswordtext6.getText());
		propertiesMap.put("ServerRole6", (String) serverrolecomboBox6.getSelectedItem());
		if (federatedradio6.isSelected())
			propertiesMap.put("Federated6", "Yes");
		else
			propertiesMap.put("Federated6", "No");

		propertiesMap.put("ServerUrl7", adminurltext7.getText());
		propertiesMap.put("ServerAdminUsername7", adminusernametext7.getText());
		propertiesMap.put("ServerAdminPassword7", adminpasswordtext7.getText());
		propertiesMap.put("ServerRole7", (String) serverrolecomboBox7.getSelectedItem());
		if (federatedradio7.isSelected())
			propertiesMap.put("Federated7", "Yes");
		else
			propertiesMap.put("Federated7", "No");

		propertiesMap.put("ServerUrl8", adminurltext8.getText());
		propertiesMap.put("ServerAdminUsername8", adminusernametext8.getText());
		propertiesMap.put("ServerAdminPassword8", adminpasswordtext8.getText());
		propertiesMap.put("ServerRole8", (String) serverrolecomboBox8.getSelectedItem());
		if (federatedradio8.isSelected())
			propertiesMap.put("Federated8", "Yes");
		else
			propertiesMap.put("Federated8", "No");

		propertiesMap.put("ServerUrl9", adminurltext9.getText());
		propertiesMap.put("ServerAdminUsername9", adminusernametext9.getText());
		propertiesMap.put("ServerAdminPassword9", adminpasswordtext9.getText());
		propertiesMap.put("ServerRole9", (String) serverrolecomboBox9.getSelectedItem());
		if (federatedradio9.isSelected())
			propertiesMap.put("Federated9", "Yes");
		else
			propertiesMap.put("Federated9", "No");

		propertiesMap.put("ServerUrl10", adminurltext10.getText());
		propertiesMap.put("ServerAdminUsername10", adminusernametext10.getText());
		propertiesMap.put("ServerAdminPassword10", adminpasswordtext10.getText());
		propertiesMap.put("ServerRole10", (String) serverrolecomboBox10.getSelectedItem());
		if (federatedradio10.isSelected())
			propertiesMap.put("Federated10", "Yes");
		else
			propertiesMap.put("Federated10", "No");

		if (runalltest.isSelected())
			propertiesMap.put("RunAllTest", "Yes");
		else
			propertiesMap.put("RunAllTest", "No");

		if (loginserver.isSelected())
			propertiesMap.put("LoginServer", "Yes");
		else
			propertiesMap.put("LoginServer", "No");

		if (loginmanager.isSelected())
			propertiesMap.put("LoginManager", "Yes");
		else
			propertiesMap.put("LoginManager", "No");

		if (datastore.isSelected())
			propertiesMap.put("ValidateDataStores", "Yes");
		else
			propertiesMap.put("ValidateDataStores", "No");

		if (loginportal.isSelected())
			propertiesMap.put("LoginPortal", "Yes");
		else
			propertiesMap.put("LoginPortal", "No");

		/*
		 * if(content.isSelected()) propertiesMap.put("CreateFolder","Yes"); else
		 * propertiesMap.put("CreateFolder","No");
		 * 
		 */

		if (feature.isSelected())
			propertiesMap.put("CreateFeatureLayer", "Yes");
		else
			propertiesMap.put("CreateFeatureLayer", "No");

		if (tile.isSelected())
			propertiesMap.put("CreateTileLayer", "Yes");
		else
			propertiesMap.put("CreateTileLayer", "No");

		if (scenelayer.isSelected())
			propertiesMap.put("CreateSceneLayer", "Yes");
		else
			propertiesMap.put("CreateSceneLayer", "No");

		if (map.isSelected())
			propertiesMap.put("CreateMap", "Yes");
		else
			propertiesMap.put("CreateMap", "No");

		if (webappbuilder.isSelected())
			propertiesMap.put("CreateWebAppBuilder", "Yes");
		else
			propertiesMap.put("CreateWebAppBuilder", "No");
		
		if (experiencebuilder.isSelected())
			propertiesMap.put("CreateExperienceBuilderApp", "Yes");
		else
			propertiesMap.put("CreateExperienceBuilderApp", "No");

		if (groupfunctionality.isSelected())
			propertiesMap.put("CreateMember", "Yes");
		else
			propertiesMap.put("CreateMember", "No");

		if (organization.isSelected())
			propertiesMap.put("ValidateOrganization", "Yes");
		else
			propertiesMap.put("ValidateOrganization", "No");
		if (hosting.isSelected())
			propertiesMap.put("ValidateServerRole", "Yes");
		else
			propertiesMap.put("ValidateServerRole", "No");

		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter(System.getProperty("user.dir") + "\\Input\\config.properties"))) {
			for (Map.Entry<String, String> entry : propertiesMap.entrySet()) {
				writer.write(entry.getKey() + " = " + entry.getValue());
				writer.newLine(); // Add a new line after each key-value pair }
			}
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		 * // Add properties in order Properties prop1 = new Properties(); // for
		 * (Map.Entry<String, String> entry : propertiesMap.entrySet()) { //
		 * prop1.setProperty(entry.getKey(), entry.getValue()); // }
		 * prop1.putAll(propertiesMap); try (FileOutputStream output = new
		 * FileOutputStream(System.getProperty("user.dir") +
		 * "\\Input\\config.properties")) { // Save properties to the file
		 * prop1.store(output, "Configuration Settings");
		 * System.out.println("Properties file saved successfully!"); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */

		// Create a Properties object

	}

	/*
	 * //intialize every parameter to blank in config
	 * prop.setProperty("Browser",""); prop.setProperty("PortalUrl","");
	 * prop.setProperty("PortalUserName",""); prop.setProperty("PortalPassword","");
	 * prop.setProperty("ServerUrl1", "");
	 * prop.setProperty("ServerAdminUsername1","");
	 * prop.setProperty("ServerAdminPassword1","");
	 * prop.setProperty("ServerRole1",(String) "");
	 * prop.setProperty("Federated1","Yes"); prop.setProperty("ServerUrl2", "");
	 * prop.setProperty("ServerAdminUsername2","");
	 * prop.setProperty("ServerAdminPassword2","");
	 * prop.setProperty("ServerRole2",(String) "");
	 * prop.setProperty("Federated2","Yes"); prop.setProperty("ServerUrl3", "");
	 * prop.setProperty("ServerAdminUsername3","");
	 * prop.setProperty("ServerAdminPassword3","");
	 * prop.setProperty("ServerRole3",(String) "");
	 * prop.setProperty("Federated3","Yes"); prop.setProperty("ServerUrl4", "");
	 * prop.setProperty("ServerAdminUsername4","");
	 * prop.setProperty("ServerAdminPassword4","");
	 * prop.setProperty("ServerRole4",(String) "");
	 * prop.setProperty("Federated4","Yes"); prop.setProperty("ServerUrl5", "");
	 * prop.setProperty("ServerAdminUsername5","");
	 * prop.setProperty("ServerAdminPassword5","");
	 * prop.setProperty("ServerRole5",(String) "");
	 * prop.setProperty("Federated5","Yes"); prop.setProperty("ServerUrl6", "");
	 * prop.setProperty("ServerAdminUsername6","");
	 * prop.setProperty("ServerAdminPassword6","");
	 * prop.setProperty("ServerRole6",(String) "");
	 * prop.setProperty("Federated6","Yes"); prop.setProperty("ServerUrl7", "");
	 * prop.setProperty("ServerAdminUsername7","");
	 * prop.setProperty("ServerAdminPassword7","");
	 * prop.setProperty("ServerRole7",(String) "");
	 * prop.setProperty("Federated7","Yes");
	 * 
	 * prop.setProperty("ServerUrl8", "");
	 * prop.setProperty("ServerAdminUsername8","");
	 * prop.setProperty("ServerAdminPassword8","");
	 * prop.setProperty("ServerRole8",(String) "");
	 * prop.setProperty("Federated8","Yes"); prop.setProperty("ServerUrl9", "");
	 * prop.setProperty("ServerAdminUsername9","");
	 * prop.setProperty("ServerAdminPassword9","");
	 * prop.setProperty("ServerRole9",(String) "");
	 * prop.setProperty("Federated9","Yes"); prop.setProperty("ServerUrl10", "");
	 * prop.setProperty("ServerAdminUsername10","");
	 * prop.setProperty("ServerAdminPassword10","");
	 * prop.setProperty("ServerRole10",(String) "");
	 * prop.setProperty("Federated10","Yes"); //reading values from UI
	 * prop.setProperty("Browser",(String) browsercomboBox.getSelectedItem());
	 * prop.setProperty("PortalUrl", portalurltext.getText());
	 * prop.setProperty("PortalUserName",portalusernametext.getText());
	 * prop.setProperty("PortalPassword",portalpasswordtext.getText());
	 * prop.setProperty("ServerUrl1", adminurltext1.getText());
	 * prop.setProperty("ServerAdminUsername1",adminusernametext1.getText());
	 * prop.setProperty("ServerAdminPassword1",adminpasswordtext1.getText());
	 * prop.setProperty("ServerRole1",(String)
	 * serverrolecomboBox1.getSelectedItem()); if(federatedradio1.isSelected())
	 * prop.setProperty("Federated1","Yes"); else
	 * prop.setProperty("Federated1","No");
	 * 
	 * prop.setProperty("ServerUrl2", adminurltext2.getText());
	 * prop.setProperty("ServerAdminUsername2",adminusernametext2.getText());
	 * prop.setProperty("ServerAdminPassword2",adminpasswordtext2.getText());
	 * prop.setProperty("ServerRole2",(String)
	 * serverrolecomboBox2.getSelectedItem()); if(federatedradio2.isSelected())
	 * prop.setProperty("Federated2","Yes"); else
	 * prop.setProperty("Federated2","No");
	 * 
	 * prop.setProperty("ServerUrl3", adminurltext3.getText());
	 * prop.setProperty("ServerAdminUsername3",adminusernametext3.getText());
	 * prop.setProperty("ServerAdminPassword3",adminpasswordtext3.getText());
	 * prop.setProperty("ServerRole3",(String)
	 * serverrolecomboBox3.getSelectedItem()); if(federatedradio3.isSelected())
	 * prop.setProperty("Federated3","Yes"); else
	 * prop.setProperty("Federated3","No");
	 * 
	 * prop.setProperty("ServerUrl4", adminurltext4.getText());
	 * prop.setProperty("ServerAdminUsername4",adminusernametext4.getText());
	 * prop.setProperty("ServerAdminPassword4",adminpasswordtext4.getText());
	 * prop.setProperty("ServerRole4",(String)
	 * serverrolecomboBox4.getSelectedItem()); if(federatedradio4.isSelected())
	 * prop.setProperty("Federated4","Yes"); else
	 * prop.setProperty("Federated4","No");
	 * 
	 * prop.setProperty("ServerUrl5", adminurltext5.getText());
	 * prop.setProperty("ServerAdminUsername5",adminusernametext5.getText());
	 * prop.setProperty("ServerAdminPassword5",adminpasswordtext5.getText());
	 * prop.setProperty("ServerRole5",(String)
	 * serverrolecomboBox5.getSelectedItem()); if(federatedradio5.isSelected())
	 * prop.setProperty("Federated5","Yes"); else
	 * prop.setProperty("Federated5","No");
	 * 
	 * prop.setProperty("ServerUrl6", adminurltext6.getText());
	 * prop.setProperty("ServerAdminUsername6",adminusernametext6.getText());
	 * prop.setProperty("ServerAdminPassword6",adminpasswordtext6.getText());
	 * prop.setProperty("ServerRole6",(String)
	 * serverrolecomboBox6.getSelectedItem()); if(federatedradio6.isSelected())
	 * prop.setProperty("Federated6","Yes"); else
	 * prop.setProperty("Federated6","No");
	 * 
	 * prop.setProperty("ServerUrl7", adminurltext7.getText());
	 * prop.setProperty("ServerAdminUsername7",adminusernametext7.getText());
	 * prop.setProperty("ServerAdminPassword7",adminpasswordtext7.getText());
	 * prop.setProperty("ServerRole7",(String)
	 * serverrolecomboBox7.getSelectedItem()); if(federatedradio7.isSelected())
	 * prop.setProperty("Federated7","Yes"); else
	 * prop.setProperty("Federated7","No");
	 * 
	 * prop.setProperty("ServerUrl8", adminurltext8.getText());
	 * prop.setProperty("ServerAdminUsername8",adminusernametext8.getText());
	 * prop.setProperty("ServerAdminPassword8",adminpasswordtext8.getText());
	 * prop.setProperty("ServerRole8",(String)
	 * serverrolecomboBox8.getSelectedItem()); if(federatedradio8.isSelected())
	 * prop.setProperty("Federated8","Yes"); else
	 * prop.setProperty("Federated8","No");
	 * 
	 * prop.setProperty("ServerUrl9", adminurltext9.getText());
	 * prop.setProperty("ServerAdminUsername9",adminusernametext9.getText());
	 * prop.setProperty("ServerAdminPassword9",adminpasswordtext9.getText());
	 * prop.setProperty("ServerRole9",(String)
	 * serverrolecomboBox9.getSelectedItem()); if(federatedradio9.isSelected())
	 * prop.setProperty("Federated9","Yes"); else
	 * prop.setProperty("Federated9","No");
	 * 
	 * prop.setProperty("ServerUrl10", adminurltext10.getText());
	 * prop.setProperty("ServerAdminUsername10",adminusernametext10.getText());
	 * prop.setProperty("ServerAdminPassword10",adminpasswordtext10.getText());
	 * prop.setProperty("ServerRole10",(String)
	 * serverrolecomboBox10.getSelectedItem()); if(federatedradio10.isSelected())
	 * prop.setProperty("Federated10","Yes"); else
	 * prop.setProperty("Federated10","No");
	 * 
	 * 
	 * 
	 * if(runalltest.isSelected()) prop.setProperty("RunAllTest","Yes"); else
	 * prop.setProperty("RunAllTest","No");
	 * 
	 * if(loginportal.isSelected()) prop.setProperty("LoginPortal","Yes"); else
	 * prop.setProperty("LoginPortal","No");
	 * 
	 * if(loginmanager.isSelected()) prop.setProperty("LoginManager","Yes"); else
	 * prop.setProperty("LoginManager","No");
	 * 
	 * if(loginserver.isSelected()) prop.setProperty("LoginServer","Yes"); else
	 * prop.setProperty("LoginServer","No");
	 * 
	 * if(hosting.isSelected()) prop.setProperty("ValidateServerRole","Yes"); else
	 * prop.setProperty("ValidateServerRole","No");
	 * 
	 * 
	 * if(datastore.isSelected()) prop.setProperty("ValidateDataStores","Yes"); else
	 * prop.setProperty("ValidateDataStores","No");
	 * 
	 * 
	 * 
	 * if(feature.isSelected()) prop.setProperty("CreateFeatureLayer","Yes"); else
	 * prop.setProperty("CreateFeatureLayer","No");
	 * 
	 * 
	 * 
	 * if(tile.isSelected()) prop.setProperty("CreateTileLayer","Yes"); else
	 * prop.setProperty("CreateTileLayer","No");
	 * 
	 * 
	 * 
	 * if(map.isSelected()) prop.setProperty("CreateMap","Yes"); else
	 * prop.setProperty("CreateMap","No");
	 * 
	 * 
	 * 
	 * if(webappbuilder.isSelected()) prop.setProperty("CreateWebAppBuilder","Yes");
	 * else prop.setProperty("CreateWebAppBuilder","No");
	 * 
	 * 
	 * 
	 * if(scenelayer.isSelected()) prop.setProperty("CreateSceneLayer","Yes"); else
	 * prop.setProperty("CreateSceneLayer","No");
	 * 
	 * if(groupfunctionality.isSelected()) prop.setProperty("CreateMember","Yes");
	 * else prop.setProperty("CreateMember","No");
	 * 
	 * 
	 * 
	 * if(organization.isSelected()) prop.setProperty("ValidateOrganization","Yes");
	 * else prop.setProperty("ValidateOrganization","No"); try (FileOutputStream
	 * output = new FileOutputStream(System.getProperty("user.dir") +
	 * "\\Input\\config.properties")) { // Save properties to the file
	 * prop.store(output, "Configuration Settings");
	 * System.out.println("Properties file saved successfully!"); } catch
	 * (IOException e) { e.printStackTrace(); }
	 * 
	 * }
	 */
	@SuppressWarnings("deprecation")
	private static void createConfig() {
		Map<String, String> propertiesMap = new LinkedHashMap<>();
		// propertiesMap.put("database.url", "jdbc:mysql://localhost:3306/mydb");
		//// propertiesMap.put("database.username", "root");
		// propertiesMap.put("database.password", "password");
		// propertiesMap.put("app.version", "1.0.0");
		// propertiesMap.put("app.name", "MyApplication");
		propertiesMap.put("Browser", "Chrome");
		propertiesMap.put("PortalUrl", "");
		propertiesMap.put("PortalUserName", "");
		propertiesMap.put("PortalPassword", "");
		propertiesMap.put("Kubernetes", "No");
		propertiesMap.put("ServerUrl1", "");
		propertiesMap.put("ServerAdminUsername1", "");
		propertiesMap.put("ServerAdminPassword1", "");
		propertiesMap.put("ServerRole1", (String) "");
		propertiesMap.put("Federated1", "Yes");
		propertiesMap.put("ServerUrl2", "");
		propertiesMap.put("ServerAdminUsername2", "");
		propertiesMap.put("ServerAdminPassword2", "");
		propertiesMap.put("ServerRole2", (String) "");
		propertiesMap.put("Federated2", "Yes");
		propertiesMap.put("ServerUrl3", "");
		propertiesMap.put("ServerAdminUsername3", "");
		propertiesMap.put("ServerAdminPassword3", "");
		propertiesMap.put("ServerRole3", (String) "");
		propertiesMap.put("Federated3", "Yes");
		propertiesMap.put("ServerUrl4", "");
		propertiesMap.put("ServerAdminUsername4", "");
		propertiesMap.put("ServerAdminPassword4", "");
		propertiesMap.put("ServerRole4", (String) "");
		propertiesMap.put("Federated4", "Yes");
		propertiesMap.put("ServerUrl5", "");
		propertiesMap.put("ServerAdminUsername5", "");
		propertiesMap.put("ServerAdminPassword5", "");
		propertiesMap.put("ServerRole5", (String) "");
		propertiesMap.put("Federated5", "Yes");
		propertiesMap.put("ServerUrl6", "");
		propertiesMap.put("ServerAdminUsername6", "");
		propertiesMap.put("ServerAdminPassword6", "");
		propertiesMap.put("ServerRole6", (String) "");
		propertiesMap.put("Federated6", "Yes");
		propertiesMap.put("ServerUrl7", "");
		propertiesMap.put("ServerAdminUsername7", "");
		propertiesMap.put("ServerAdminPassword7", "");
		propertiesMap.put("ServerRole7", (String) "");
		propertiesMap.put("Federated7", "Yes");
		propertiesMap.put("ServerUrl8", "");
		propertiesMap.put("ServerAdminUsername8", "");
		propertiesMap.put("ServerAdminPassword8", "");
		propertiesMap.put("ServerRole8", (String) "");
		propertiesMap.put("Federated8", "Yes");
		propertiesMap.put("ServerUrl9", "");
		propertiesMap.put("ServerAdminUsername9", "");
		propertiesMap.put("ServerAdminPassword9", "");
		propertiesMap.put("ServerRole9", (String) "");
		propertiesMap.put("Federated9", "Yes");
		propertiesMap.put("ServerUrl10", "");
		propertiesMap.put("ServerAdminUsername10", "");
		propertiesMap.put("ServerAdminPassword10", "");
		propertiesMap.put("ServerRole10", (String) "");
		propertiesMap.put("Federated10", "Yes");
		propertiesMap.put("RunAllTest", "No");
		propertiesMap.put("LoginServer", "No");
		propertiesMap.put("LoginManager", "No");
		propertiesMap.put("ValidateDataStores", "No");
		propertiesMap.put("LoginPortal", "No");
		propertiesMap.put("CreateFeatureLayer", "No");
		propertiesMap.put("CreateTileLayer", "No");
		propertiesMap.put("CreateSceneLayer", "No");
		propertiesMap.put("CreateMap", "No");
		propertiesMap.put("CreateWebAppBuilder", "No");
		propertiesMap.put("CreateExperienceBuilderApp", "No");
		propertiesMap.put("CreateMember", "No");
		propertiesMap.put("ValidateOrganization", "No");
		propertiesMap.put("ValidateServerRole", "No");
		try (BufferedWriter writer = new BufferedWriter(
				new FileWriter(System.getProperty("user.dir") + "\\Input\\config.properties"))) {
			for (Map.Entry<String, String> entry : propertiesMap.entrySet()) {
				writer.write(entry.getKey() + " = " + entry.getValue());
				writer.newLine(); // Add a new line after each key-value pair }
			}
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*
		 * Properties prop2 = new Properties(); for (Map.Entry<String, String> entry :
		 * propertiesMap.entrySet()) { prop2.setProperty(entry.getKey(),
		 * entry.getValue()); } // prop1.putAll(propertiesMap); try (FileOutputStream
		 * output1 = new FileOutputStream(System.getProperty("user.dir") +
		 * "\\Input\\config.properties")) { // Save properties to the file
		 * prop2.store(output1, "Configuration Settings");
		 * System.out.println("Properties file saved successfully!"); output1.flush();
		 * output1.close();
		 * 
		 * } catch (IOException e) { e.printStackTrace(); }
		 */

		// intialize every parameter to blank in config
		/*
		 * prop.setProperty("Browser","Chrome"); prop.setProperty("PortalUrl","");
		 * prop.setProperty("PortalUserName",""); prop.setProperty("PortalPassword","");
		 * prop.setProperty("ServerUrl1", "");
		 * prop.setProperty("ServerAdminUsername1","");
		 * prop.setProperty("ServerAdminPassword1","");
		 * prop.setProperty("ServerRole1",(String) "");
		 * prop.setProperty("Federated1","Yes"); prop.setProperty("ServerUrl2", "");
		 * prop.setProperty("ServerAdminUsername2","");
		 * prop.setProperty("ServerAdminPassword2","");
		 * prop.setProperty("ServerRole2",(String) "");
		 * prop.setProperty("Federated2","Yes"); prop.setProperty("ServerUrl3", "");
		 * prop.setProperty("ServerAdminUsername3","");
		 * prop.setProperty("ServerAdminPassword3","");
		 * prop.setProperty("ServerRole3",(String) "");
		 * prop.setProperty("Federated3","Yes"); prop.setProperty("ServerUrl4", "");
		 * prop.setProperty("ServerAdminUsername4","");
		 * prop.setProperty("ServerAdminPassword4","");
		 * prop.setProperty("ServerRole4",(String) "");
		 * prop.setProperty("Federated4","Yes"); prop.setProperty("ServerUrl5", "");
		 * prop.setProperty("ServerAdminUsername5","");
		 * prop.setProperty("ServerAdminPassword5","");
		 * prop.setProperty("ServerRole5",(String) "");
		 * prop.setProperty("Federated5","Yes"); prop.setProperty("ServerUrl6", "");
		 * prop.setProperty("ServerAdminUsername6","");
		 * prop.setProperty("ServerAdminPassword6","");
		 * prop.setProperty("ServerRole6",(String) "");
		 * prop.setProperty("Federated6","Yes"); prop.setProperty("ServerUrl7", "");
		 * prop.setProperty("ServerAdminUsername7","");
		 * prop.setProperty("ServerAdminPassword7","");
		 * prop.setProperty("ServerRole7",(String) "");
		 * prop.setProperty("Federated7","Yes");
		 * 
		 * prop.setProperty("ServerUrl8", "");
		 * prop.setProperty("ServerAdminUsername8","");
		 * prop.setProperty("ServerAdminPassword8","");
		 * prop.setProperty("ServerRole8",(String) "");
		 * prop.setProperty("Federated8","Yes"); prop.setProperty("ServerUrl9", "");
		 * prop.setProperty("ServerAdminUsername9","");
		 * prop.setProperty("ServerAdminPassword9","");
		 * prop.setProperty("ServerRole9",(String) "");
		 * prop.setProperty("Federated9","Yes"); prop.setProperty("ServerUrl10", "");
		 * prop.setProperty("ServerAdminUsername10","");
		 * prop.setProperty("ServerAdminPassword10","");
		 * prop.setProperty("ServerRole10",(String) "");
		 * prop.setProperty("Federated10","Yes"); prop.setProperty("RunAllTest","No");
		 * prop.setProperty("LoginPortal","No"); prop.setProperty("LoginManager","No");
		 * prop.setProperty("LoginServer","No");
		 * prop.setProperty("ValidateServerRole","No");
		 * prop.setProperty("ValidateDataStores","No");
		 * prop.setProperty("CreateFeatureLayer","No");
		 * prop.setProperty("CreateTileLayer","No"); prop.setProperty("CreateMap","No");
		 * prop.setProperty("CreateWebAppBuilder","No");
		 * prop.setProperty("CreateSceneLayer","No");
		 * prop.setProperty("CreateMember","No");
		 * prop.setProperty("ValidateOrganization","No"); try (FileOutputStream output =
		 * new FileOutputStream(System.getProperty("user.dir") +
		 * "\\Input\\config.properties")) { // Save properties to the file
		 * prop.store(output, "Configuration Settings");
		 * System.out.println("Properties file saved successfully!"); } catch
		 * (IOException e) { e.printStackTrace(); }
		 */

	}

	public static void readfromconfigdisplay() {
		if (prop.getProperty("PortalUrl") != null)
			Portalurl = prop.getProperty("PortalUrl").trim();
		if (prop.getProperty("PortalUserName") != null)
			PortalUserName = prop.getProperty("PortalUserName").trim();
		if (prop.getProperty("PortalPassword") != null)
			PortalPassword = prop.getProperty("PortalPassword").trim();
		if (prop.getProperty("Kubernetes") != null) {
			if (prop.getProperty("Kubernetes").equalsIgnoreCase("yes")
					|| prop.getProperty("Kubernetes").equalsIgnoreCase("y")) {
				flagKUB = true;
			} else {
				flagKUB = false;
			}
		} else {
			flagKUB = false;
		}
		if (prop.getProperty("ServerUrl1") != null)
			Serverurl[0] = prop.getProperty("ServerUrl1").trim();

		if (prop.getProperty("ServerUrl2") != null)
			Serverurl[1] = prop.getProperty("ServerUrl2").trim();

		if (prop.getProperty("ServerUrl3") != null)
			Serverurl[2] = prop.getProperty("ServerUrl3").trim();

		if (prop.getProperty("ServerUrl4") != null)
			Serverurl[3] = prop.getProperty("ServerUrl4").trim();

		if (prop.getProperty("ServerUrl5") != null)
			Serverurl[4] = prop.getProperty("ServerUrl5").trim();

		if (prop.getProperty("ServerUrl6") != null)
			Serverurl[5] = prop.getProperty("ServerUrl6").trim();

		if (prop.getProperty("ServerUrl7") != null)
			Serverurl[6] = prop.getProperty("ServerUrl7").trim();

		if (prop.getProperty("ServerUrl8") != null)
			Serverurl[7] = prop.getProperty("ServerUrl8").trim();

		if (prop.getProperty("ServerUrl9") != null)
			Serverurl[8] = prop.getProperty("ServerUrl9").trim();

		if (prop.getProperty("ServerUrl10") != null)
			Serverurl[9] = prop.getProperty("ServerUrl10".trim());

		if (prop.getProperty("ServerAdminUsername1") != null)
			ServerAdminUsername[0] = prop.getProperty("ServerAdminUsername1").trim();
		if (prop.getProperty("ServerAdminPassword1") != null)
			ServerAdminPassword[0] = prop.getProperty("ServerAdminPassword1");
		if (prop.getProperty("ServerAdminUsername2") != null)
			ServerAdminUsername[1] = prop.getProperty("ServerAdminUsername2").trim();
		if (prop.getProperty("ServerAdminPassword2") != null)
			ServerAdminPassword[1] = prop.getProperty("ServerAdminPassword2");
		if (prop.getProperty("ServerAdminUsername3") != null)
			ServerAdminUsername[2] = prop.getProperty("ServerAdminUsername3").trim();
		if (prop.getProperty("ServerAdminPassword3") != null)
			ServerAdminPassword[2] = prop.getProperty("ServerAdminPassword3");
		if (prop.getProperty("ServerAdminUsername4") != null)
			ServerAdminUsername[3] = prop.getProperty("ServerAdminUsername4").trim();
		if (prop.getProperty("ServerAdminPassword4") != null)
			ServerAdminPassword[3] = prop.getProperty("ServerAdminPassword4");
		if (prop.getProperty("ServerAdminUsername5") != null)
			ServerAdminUsername[4] = prop.getProperty("ServerAdminUsername5").trim();
		if (prop.getProperty("ServerAdminPassword5") != null)
			ServerAdminPassword[4] = prop.getProperty("ServerAdminPassword5");
		if (prop.getProperty("ServerAdminUsername6") != null)
			ServerAdminUsername[5] = prop.getProperty("ServerAdminUsername6").trim();
		if (prop.getProperty("ServerAdminPassword6") != null)
			ServerAdminPassword[5] = prop.getProperty("ServerAdminPassword6");
		if (prop.getProperty("ServerAdminUsername7") != null)
			ServerAdminUsername[6] = prop.getProperty("ServerAdminUsername7").trim();
		if (prop.getProperty("ServerAdminPassword7") != null)
			ServerAdminPassword[6] = prop.getProperty("ServerAdminPassword7");
		if (prop.getProperty("ServerAdminUsername8") != null)
			ServerAdminUsername[7] = prop.getProperty("ServerAdminUsername8").trim();
		if (prop.getProperty("ServerAdminPassword8") != null)
			ServerAdminPassword[7] = prop.getProperty("ServerAdminPassword8");
		if (prop.getProperty("ServerAdminUsername9") != null)
			ServerAdminUsername[8] = prop.getProperty("ServerAdminUsername9").trim();
		if (prop.getProperty("ServerAdminPassword9") != null)
			ServerAdminPassword[8] = prop.getProperty("ServerAdminPassword9");
		if (prop.getProperty("ServerAdminUsername10") != null)
			ServerAdminUsername[9] = prop.getProperty("ServerAdminUsername10").trim();
		if (prop.getProperty("ServerAdminPassword10") != null)
			ServerAdminPassword[9] = prop.getProperty("ServerAdminPassword10");
		for (int i = 1; i < 10; i++) {
			if ((!ServerAdminPassword[i].isBlank()) || (!ServerAdminUsername[i].isBlank())
					|| (!Serverurl[i].isBlank())) {
				no_of_site = i + 1;
			}
		}

		if (prop.getProperty("ServerRole1") != null)
			ServerAdminRole[0] = prop.getProperty("ServerRole1");

		if (prop.getProperty("ServerRole2") != null)
			ServerAdminRole[1] = prop.getProperty("ServerRole2");

		if (prop.getProperty("ServerRole3") != null)
			ServerAdminRole[2] = prop.getProperty("ServerRole3");

		if (prop.getProperty("ServerRole4") != null)
			ServerAdminRole[3] = prop.getProperty("ServerRole4");

		if (prop.getProperty("ServerRole5") != null)
			ServerAdminRole[4] = prop.getProperty("ServerRole5");

		if (prop.getProperty("ServerRole6") != null)
			ServerAdminRole[5] = prop.getProperty("ServerRole6");

		if (prop.getProperty("ServerRole7") != null)
			ServerAdminRole[6] = prop.getProperty("ServerRole7");

		if (prop.getProperty("ServerRole8") != null)
			ServerAdminRole[7] = prop.getProperty("ServerRole8");

		if (prop.getProperty("ServerRole9") != null)
			ServerAdminRole[8] = prop.getProperty("ServerRole9");

		if (prop.getProperty("ServerRole10") != null)
			ServerAdminRole[9] = prop.getProperty("ServerRole10");

		if (prop.getProperty("RunAllTest") != null) {
			if (prop.getProperty("RunAllTest").equalsIgnoreCase("yes")
					|| prop.getProperty("RunAllTest").equalsIgnoreCase("y")) {
				if(flagKUB) {
				flagRunalltest = true;
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
				flagexperiencebuilder = true;
				flagcontent = true;
				flagServerRole = false;
				flagdatastore = false;
				}
				else {
					flagRunalltest = true;
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
					flagexperiencebuilder = true;
					flagcontent = true;
					flagServerRole = true;
					flagdatastore = true;	
				}
			} else {
				flagRunalltest = false;
				flagloginadmin = false;
				flagloginmanager = false;
				flagloginportal = false;
				flagtilelayer = false;
				flagfeaturelayer = false;
				flaggroup = false;
				flagorganization = false;
				flagscenelayer = false;
				flagmap = false;
				flagwebappbuilder = false;
				flagexperiencebuilder = false;
				flagcontent = false;
				flagServerRole = false;
				flagdatastore = false;

			}
		} else {
			flagRunalltest = false;
			flagloginadmin = false;
			flagloginmanager = false;
			flagloginportal = false;
			flagtilelayer = false;
			flagfeaturelayer = false;
			flaggroup = false;
			flagorganization = false;
			flagscenelayer = false;
			flagmap = false;
			flagwebappbuilder = false;
			flagexperiencebuilder = false;
			flagcontent = false;
			flagServerRole = false;
			flagdatastore = false;

		}
		if (!flagRunalltest) {
			if (prop.getProperty("LoginPortal") != null) {
				if (prop.getProperty("LoginPortal").equalsIgnoreCase("yes")
						|| prop.getProperty("LoginPortal").equalsIgnoreCase("y")) {
					flagloginportal = true;
				} else {
					flagloginportal = false;
				}
			} else {
				flagloginportal = false;
			}
			if (prop.getProperty("LoginManager") != null) {
				if (prop.getProperty("LoginManager").equalsIgnoreCase("yes")
						|| prop.getProperty("LoginManager").equalsIgnoreCase("y")) {
					flagloginmanager = true;
				} else {
					flagloginmanager = false;
				}
			} else {
				flagloginmanager = false;
			}

			if (prop.getProperty("LoginServer") != null) {
				if (prop.getProperty("LoginServer").equalsIgnoreCase("yes")
						|| prop.getProperty("LoginServer").equalsIgnoreCase("y")) {
					flagloginadmin = true;
				} else {
					flagloginadmin = false;
				}
			} else {
				flagloginadmin = false;
			}
			if (prop.getProperty("ValidateServerRole") != null) {
				if (prop.getProperty("ValidateServerRole").equalsIgnoreCase("yes")
						|| prop.getProperty("ValidateServerRole").equalsIgnoreCase("y")) {
					if(flagKUB) {
					flagServerRole = false;
					
					}else {
						flagServerRole = true;
						flagloginportal = true;	
					}
				} else {
					flagServerRole = false;
				}
			} else {
				flagServerRole = false;
			}

			if (prop.getProperty("ValidateDataStores") != null) {
				if (prop.getProperty("ValidateDataStores").equalsIgnoreCase("yes")
						|| prop.getProperty("ValidateDataStores").equalsIgnoreCase("y")) {
					if(flagKUB) {
						flagdatastore = false;
						
						}else {
							flagdatastore = true;
							//flagloginmanager = true;	
						}
					
				} else {
					flagdatastore = false;
				}
			} else {
				flagdatastore = false;
			}
			if (prop.getProperty("CreateFeatureLayer") != null) {
				if (prop.getProperty("CreateFeatureLayer").equalsIgnoreCase("yes")
						|| prop.getProperty("CreateFeatureLayer").equalsIgnoreCase("y")) {
					flagfeaturelayer = true;
					flagcontent = true;
					flagloginportal = true;
				} else {
					flagfeaturelayer = false;
				}
			} else {
				flagfeaturelayer = false;
			}
			if (prop.getProperty("CreateTileLayer") != null) {
				if (prop.getProperty("CreateTileLayer").equalsIgnoreCase("yes")
						|| prop.getProperty("CreateTileLayer").equalsIgnoreCase("y")) {
					flagtilelayer = true;
					//flagfeaturelayer = true;
					flagcontent = true;
					flagloginportal = true;
				} else {
					flagtilelayer = false;
				}
			} else {
				flagtilelayer = false;
			}
			if (prop.getProperty("CreateMap") != null) {
				if (prop.getProperty("CreateMap").equalsIgnoreCase("yes")
						|| prop.getProperty("CreateMap").equalsIgnoreCase("y")) {
					flagmap = true;
					flagloginportal = true;
					flagcontent = true;
				} else {
					flagmap = false;
				}
			} else {
				flagmap = false;
			}
			if (prop.getProperty("CreateWebAppBuilder") != null) {
				if (prop.getProperty("CreateWebAppBuilder").equalsIgnoreCase("yes")
						|| prop.getProperty("CreateWebAppBuilder").equalsIgnoreCase("y")) {
					flagwebappbuilder = true;
					flagloginportal = true;
					flagcontent = true;
				} else {
					flagwebappbuilder = false;
				}
			} else {
				flagwebappbuilder = false;
			}
			if (prop.getProperty("CreateExperienceBuilderApp") != null) {
				if (prop.getProperty("CreateExperienceBuilderApp").equalsIgnoreCase("yes")
						|| prop.getProperty("CreateExperienceBuilderApp").equalsIgnoreCase("y")) {
					flagexperiencebuilder = true;
					flagloginportal = true;
					flagcontent = true;
					//flagwebappbuilder=false;
				} else {
					flagexperiencebuilder = false;
				}
			} else {
				flagexperiencebuilder = false;
			}
			if (prop.getProperty("CreateSceneLayer") != null) {
				if (prop.getProperty("CreateSceneLayer").equalsIgnoreCase("yes")
						|| prop.getProperty("CreateSceneLayer").equalsIgnoreCase("y")) {
					flagscenelayer = true;
					flagloginportal = true;
					flagcontent = true;
				} else {
					flagscenelayer = false;
				}
			} else {
				flagscenelayer = false;
			}
			if (prop.getProperty("CreateMember") != null) {
				if (prop.getProperty("CreateMember").equalsIgnoreCase("yes")
						|| prop.getProperty("CreateMember").equalsIgnoreCase("y")) {
					flaggroup = true;
					flagloginportal = true;
				//	flagfeaturelayer = true; As per requirement group should be independent of feature
					flagcontent = true;
				} else {
					flaggroup = false;
				}
			} else {
				flaggroup = false;
			}
			if (prop.getProperty("ValidateOrganization") != null) {
				if (prop.getProperty("ValidateOrganization").equalsIgnoreCase("yes")
						|| prop.getProperty("ValidateOrganization").equalsIgnoreCase("y")) {
					flagorganization = true;
					flagloginportal = true;
				} else {
					flagorganization = false;
				}
			} else {
				flagorganization = false;
			}
		}
		if (prop.getProperty("Federated1") != null) {
			if (prop.getProperty("Federated1").equalsIgnoreCase("yes")
					|| prop.getProperty("Federated1").equalsIgnoreCase("y")) {
				ServerFederated[0] = true;
			} else {
				ServerFederated[0] = false;
			}
		} else {
			ServerFederated[0] = true;
		}
		if (prop.getProperty("Federated2") != null) {
			if (prop.getProperty("Federated2").equalsIgnoreCase("yes")
					|| prop.getProperty("Federated2").equalsIgnoreCase("y")) {
				ServerFederated[1] = true;
			} else {
				ServerFederated[1] = false;
			}
		} else {
			ServerFederated[1] = true;
		}
		if (prop.getProperty("Federated3") != null) {
			if (prop.getProperty("Federated3").equalsIgnoreCase("yes")
					|| prop.getProperty("Federated3").equalsIgnoreCase("y")) {
				ServerFederated[2] = true;
			} else {
				ServerFederated[2] = false;
			}
		} else {
			ServerFederated[2] = true;
		}

		if (prop.getProperty("Federated4") != null) {
			if (prop.getProperty("Federated4").equalsIgnoreCase("yes")
					|| prop.getProperty("Federated4").equalsIgnoreCase("y")) {
				ServerFederated[3] = true;
			} else {
				ServerFederated[3] = false;
			}
		} else {
			ServerFederated[3] = true;
		}

		if (prop.getProperty("Federated5") != null) {
			if (prop.getProperty("Federated5").equalsIgnoreCase("yes")
					|| prop.getProperty("Federated5").equalsIgnoreCase("y")) {
				ServerFederated[4] = true;
			} else {
				ServerFederated[4] = false;
			}
		} else {
			ServerFederated[4] = true;
		}

		if (prop.getProperty("Federated6") != null) {
			if (prop.getProperty("Federated6").equalsIgnoreCase("yes")
					|| prop.getProperty("Federated6").equalsIgnoreCase("y")) {
				ServerFederated[5] = true;
			} else {
				ServerFederated[5] = false;
			}
		} else {
			ServerFederated[5] = true;
		}

		if (prop.getProperty("Federated7") != null) {
			if (prop.getProperty("Federated7").equalsIgnoreCase("yes")
					|| prop.getProperty("Federated7").equalsIgnoreCase("y")) {
				ServerFederated[6] = true;
			} else {
				ServerFederated[6] = false;
			}
		} else {
			ServerFederated[6] = true;
		}

		if (prop.getProperty("Federated8") != null) {
			if (prop.getProperty("Federated8").equalsIgnoreCase("yes")
					|| prop.getProperty("Federated8").equalsIgnoreCase("y")) {
				ServerFederated[7] = true;
			} else {
				ServerFederated[7] = false;
			}
		} else {
			ServerFederated[7] = true;
		}

		if (prop.getProperty("Federated9") != null) {
			if (prop.getProperty("Federated9").equalsIgnoreCase("yes")
					|| prop.getProperty("Federated9").equalsIgnoreCase("y")) {
				ServerFederated[8] = true;
			} else {
				ServerFederated[8] = false;
			}
		} else {
			ServerFederated[8] = true;
		}

		if (prop.getProperty("Federated10") != null) {
			if (prop.getProperty("Federated10").equalsIgnoreCase("yes")
					|| prop.getProperty("Federated10").equalsIgnoreCase("y")) {
				ServerFederated[9] = true;
			} else {
				ServerFederated[9] = false;
			}
		} else {
			ServerFederated[9] = true;
		}
		Browser = prop.getProperty("Browser");
		if (Browser == null || Browser.isBlank() || Browser.equalsIgnoreCase("Chrome")
				|| Browser.toLowerCase().contains("chrome")) {
			Browser = "Chrome";
		} else {
			Browser = "Edge";
		}

	}

	public static void configSetup() {
		try {
			prop = new Properties();
			input = new FileInputStream(System.getProperty("user.dir") + "\\Input\\config.properties");
			if (input != null) {
				prop.load(input);
			}
			input.close();
		} catch (Exception e) {
			// e.printStackTrace();
			createConfig();

		}
	}
//this is to verify values for command line arguments

	public static String verifyConfigvalues() throws Exception {
		boolean flag1 = true;
		String msg = "";
		if (!(flagRunalltest || flagloginportal || flagloginadmin || flagloginmanager || flagtilelayer || flagServerRole
				|| flagfeaturelayer || flaggroup || flagorganization || flagscenelayer || flagmap || flagwebappbuilder
				|| flagexperiencebuilder|| flagdatastore)) {
			flag1 = false;
			msg = "Please select atleast one test to run";

			return msg;

		}
		msg = "";
		if (flagloginportal) {
			if (!((!Portalurl.isEmpty()) && (!PortalUserName.isEmpty()) && (!PortalPassword.isEmpty()))) {
			msg = "Please provide data for fields:";
			flag1 = true;
			if (Portalurl.isEmpty()) {
				flag1 = false;
				msg = msg + "Portal URL";

			}

			if (PortalUserName.isEmpty()) {
				flag1 = false;
				msg = msg + " Portal username";

			}
			if (PortalPassword.isEmpty()) {
				flag1 = false;
				msg = msg + " Portal password";

			}
			}
			if (!flag1) {
				return msg;
			}
			
		}
		flag1 = true;
		msg = "";
		if (flagloginadmin) {
			
			// boolean flaga=false;
			if ((Serverurl[0].isEmpty()) && (Serverurl[1].isEmpty()) && (Serverurl[2].isEmpty())
					&& (Serverurl[3].isEmpty()) && (Serverurl[4].isEmpty()) && (Serverurl[5].isEmpty())
					&& (Serverurl[6].isEmpty()) && (Serverurl[7].isEmpty()) && (Serverurl[8].isEmpty())
					&& (Serverurl[9].isEmpty())) {
				msg = "Please provide data for at least one server url";
				flag1 = false;
				return msg;
			}
			boolean flag2 = false,flag3= false,flag4= false,flag5= false,flag6= false,flag7= false,flag8= false,flag9= false,flag10= false,flag11= false;
			if (!(Serverurl[0].isEmpty())&&(ServerAdminRole[0].toLowerCase().contains("hosting"))){
				flag2=true;}
			if (!(Serverurl[1].isEmpty())&&(ServerAdminRole[1].toLowerCase().contains("hosting"))){
				flag3=true;
			}
			if (!(Serverurl[2].isEmpty())&&(ServerAdminRole[2].toLowerCase().contains("hosting"))){
				flag4=true;
			}
			if (!(Serverurl[3].isEmpty())&&(ServerAdminRole[3].toLowerCase().contains("hosting"))){
				flag5=true;
			}
			if (!(Serverurl[4].isEmpty())&&(ServerAdminRole[4].toLowerCase().contains("hosting"))){
				flag6=true;
			}
			if (!(Serverurl[5].isEmpty())&&(ServerAdminRole[5].toLowerCase().contains("hosting"))){
				flag7=true;
			}
			if (!(Serverurl[6].isEmpty())&&(ServerAdminRole[6].toLowerCase().contains("hosting"))){
				flag8=true;
			}
			if (!(Serverurl[7].isEmpty())&&(ServerAdminRole[7].toLowerCase().contains("hosting"))){
				flag9=true;
			}
			if (!(Serverurl[8].isEmpty())&&(ServerAdminRole[8].toLowerCase().contains("hosting"))){
				flag10=true;
			}
			if (!(Serverurl[9].isEmpty())&&(ServerAdminRole[9].toLowerCase().contains("hosting"))){
				flag11=true;
			}
			if(!(flag2||flag3||flag4||flag5||flag6||flag7||flag8||flag9||flag10||flag11)) {
				msg="Please provide at least one server url with role 'Hosting Server'";
				return msg;	
			}
			
			// The XOR operator returns true if an odd number of operands are true, and
			// false if an even number of operands are true.
			flag1 = true;
			msg = "";
			if (!Serverurl[0].isEmpty()) {
				// eneter in loop if all are empty or anyone is empty
				if(ServerAdminRole[0].toLowerCase().contains("hosting")) {
				if (!(!ServerAdminUsername[0].isEmpty() &&!ServerAdminPassword[0].isEmpty())) {
					// enter in loop if anyone empty
					msg = "Please provide data for fields:";

					if (ServerAdminUsername[0].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 1";

					}
					if (ServerAdminPassword[0].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 1";

					}
				}
				}
				if (!flag1) {

					return msg;
				}
				
			}

			flag1 = true;
			msg = "";
			if (!Serverurl[1].isEmpty()) {
				// eneter in loop if all are empty or anyone is empty
				if(ServerAdminRole[1].toLowerCase().contains("hosting")) {
					if (!(!ServerAdminUsername[1].isEmpty() &&!ServerAdminPassword[1].isEmpty())) {
					// enter in loop if anyone empty
					msg = "Please provide data for fields:";

					if (ServerAdminUsername[1].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 2";

					}
					if (ServerAdminPassword[1].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 2";

					}
				}
				}
				if (!flag1) {

					return msg;
				}
				
			}
			flag1 = true;
			msg = "";
			if (!Serverurl[2].isEmpty()) {
				// eneter in loop if all are empty or anyone is empty
				if(ServerAdminRole[2].toLowerCase().contains("hosting")) {
					if (!(!ServerAdminUsername[2].isEmpty() &&!ServerAdminPassword[2].isEmpty())) {
					// enter in loop if anyone empty
					msg = "Please provide data for fields:";

					if (ServerAdminUsername[2].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 3";

					}
					if (ServerAdminPassword[2].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 3";

					}
				}
				}
				if (!flag1) {

					return msg;
				}
				
			}
			flag1 = true;
			msg = "";
			if (!Serverurl[3].isEmpty()) {
				// eneter in loop if all are empty or anyone is empty
				if(ServerAdminRole[3].toLowerCase().contains("hosting")) {
					if (!(!ServerAdminUsername[3].isEmpty() &&!ServerAdminPassword[3].isEmpty())) {
					// enter in loop if anyone empty
					msg = "Please provide data for fields:";

					if (ServerAdminUsername[3].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 4";

					}
					if (ServerAdminPassword[3].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 4";

					}
				}
				}
				if (!flag1) {

					return msg;
				}
				
			}
			flag1 = true;
			msg = "";
			if (!Serverurl[4].isEmpty()) {
				// eneter in loop if all are empty or anyone is empty
				if(ServerAdminRole[4].toLowerCase().contains("hosting")) {
					if (!(!ServerAdminUsername[4].isEmpty() &&!ServerAdminPassword[4].isEmpty())) {
					// enter in loop if anyone empty
					

					msg = "Please provide data for fields:";

					if (ServerAdminUsername[4].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 5";

					}
					if (ServerAdminPassword[4].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 5";

					}
				}
				}
				if (!flag1) {

					return msg;
				}
			}
			flag1 = true;
			msg = "";
			if (!Serverurl[5].isEmpty()) {
				// eneter in loop if all are empty or anyone is empty
				if(ServerAdminRole[5].toLowerCase().contains("hosting")) {
					if (!(!ServerAdminUsername[5].isEmpty() &&!ServerAdminPassword[5].isEmpty())) {
					// enter in loop if anyone empty
					

					msg = "Please provide data for fields:";

					if (ServerAdminUsername[5].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 6";

					}
					if (ServerAdminPassword[5].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 6";

					}
				}
				}
				if (!flag1) {

					return msg;
				}
			}
			flag1 = true;
			msg = "";
			if (!Serverurl[6].isEmpty()) {
				// eneter in loop if all are empty or anyone is empty
				if(ServerAdminRole[6].toLowerCase().contains("hosting")) {
					if (!(!ServerAdminUsername[6].isEmpty() &&!ServerAdminPassword[6].isEmpty())) {
					// enter in loop if anyone empty
				msg = "Please provide data for fields:";

					if (ServerAdminUsername[6].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 7";

					}
					if (ServerAdminPassword[6].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 7";

					}
				}
				}
				if (!flag1) {

					return msg;
				}
			}
			flag1 = true;
			msg = "";
			if (!Serverurl[7].isEmpty()) {
				// eneter in loop if all are empty or anyone is empty
				if(ServerAdminRole[7].toLowerCase().contains("hosting")) {
					if (!(!ServerAdminUsername[7].isEmpty() &&!ServerAdminPassword[7].isEmpty())) {
					// enter in loop if anyone empty
					msg = "Please provide data for fields:";

					if (ServerAdminUsername[7].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 8";

					}
					if (ServerAdminPassword[7].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 8";

					}
				}
				}
				if (!flag1) {

					return msg;
				}
			}
			flag1 = true;
			msg = "";
			if (!Serverurl[8].isEmpty()) {
				// eneter in loop if all are empty or anyone is empty
				if(ServerAdminRole[8].toLowerCase().contains("hosting")) {
					if (!(!ServerAdminUsername[8].isEmpty() &&!ServerAdminPassword[8].isEmpty())) {
					// enter in loop if anyone empty
					msg = "Please provide data for fields:";

					if (ServerAdminUsername[8].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 9";

					}
					if (ServerAdminPassword[8].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 9";

					}
				}
				}
				if (!flag1) {

					return msg;
				}
			}
			flag1 = true;
			msg = "";
			if (!Serverurl[9].isEmpty()) {
				// eneter in loop if all are empty or anyone is empty
				if(ServerAdminRole[9].toLowerCase().contains("hosting")) {
					if (!(!ServerAdminUsername[9].isEmpty() &&!ServerAdminPassword[9].isEmpty())) {
					// enter in loop if anyone empty
					msg = "Please provide data for fields:";

					if (ServerAdminUsername[9].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 10";

					}
					if (ServerAdminPassword[9].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 10";

					}
				}
				}
				if (!flag1) {

					return msg;
				}
			}
		}

		if (flagloginmanager) {
			msg="";
			
			//flag1 = true;
			if ((Serverurl[0].isEmpty()) && (Serverurl[1].isEmpty()) && (Serverurl[2].isEmpty())
					&& (Serverurl[3].isEmpty()) && (Serverurl[4].isEmpty()) && (Serverurl[5].isEmpty())
					&& (Serverurl[6].isEmpty()) && (Serverurl[7].isEmpty()) && (Serverurl[8].isEmpty())
					&& (Serverurl[9].isEmpty()) ){
				msg = "Please provide data for at least one server url";
				flag1 = false;
				return msg;
			}
			if (!Serverurl[0].isEmpty()) {

				msg = "";
				
				flag1 = true;
				if (ServerFederated[0]) {
					if (!(!PortalUserName.isEmpty() &&!PortalPassword.isEmpty())) {
					msg = "Please provide data for fields:";					
					if (PortalUserName.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Username";

					}
					if (PortalPassword.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Password";
					}
					}
				} else {
					if (!(!ServerAdminUsername[0].isEmpty()) && (!ServerAdminPassword[0].isEmpty())){
						msg = "Please provide data for fields:";
					
					if (ServerAdminUsername[0].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 1";
					}
					if (ServerAdminPassword[0].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 1";
					}
				}
				}
			}
			
			if (!flag1) {
				
				return msg;
			}
			
			if (!Serverurl[1].isEmpty()) {

				msg = "";
				
				flag1 = true;
				if (ServerFederated[1]) {
					if (!(!PortalUserName.isEmpty() &&!PortalPassword.isEmpty())) {
						msg = "Please provide data for fields:";
					
					if (PortalUserName.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Username";

					}
					if (PortalPassword.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Password";
					}
					}
				} else {
					if (!(!ServerAdminUsername[1].isEmpty()) && (!ServerAdminPassword[1].isEmpty())){
						msg = "Please provide data for fields:";
					
					if (ServerAdminUsername[1].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 2";
					}
					if (ServerAdminPassword[1].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 2";
					}
				}
				}
			}
			if (!flag1) {
				return msg;
			}
			if (!Serverurl[2].isEmpty()) {

				msg = "";
				
				flag1 = true;
				if (ServerFederated[2]) {
					if (!(!PortalUserName.isEmpty() &&!PortalPassword.isEmpty())) {
						msg = "Please provide data for fields:";
					
					if (PortalUserName.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Username";

					}
					if (PortalPassword.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Password";
					}
					}
				} else {
					if (!(!ServerAdminUsername[2].isEmpty()) && (!ServerAdminPassword[2].isEmpty())){
						msg = "Please provide data for fields:";
					
					if (ServerAdminUsername[2].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 3";
					}
					if (ServerAdminPassword[2].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 3";
					}
				}
				}
			}
			if (!flag1) {
				return msg;
			}
			if (!Serverurl[3].isEmpty()) {

				msg = "";
				
				flag1 = true;
				if (ServerFederated[3]) {
					if (!(!PortalUserName.isEmpty() &&!PortalPassword.isEmpty())) {
						msg = "Please provide data for fields:";
					
					if (PortalUserName.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Username";

					}
					if (PortalPassword.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Password";
					}
					}
				} else {
					if (!(!ServerAdminUsername[3].isEmpty()) && (!ServerAdminPassword[3].isEmpty())){
						msg = "Please provide data for fields:";
					
					if (ServerAdminUsername[3].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 4";
					}
					if (ServerAdminPassword[3].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 4";
					}
				}
				}
			}
			if (!flag1) {
				return msg;
			}
			if (!Serverurl[4].isEmpty()) {

				msg = "";
				
				flag1 = true;
				if (ServerFederated[4]) {
					if (!(!PortalUserName.isEmpty() &&!PortalPassword.isEmpty())) {
						msg = "Please provide data for fields:";
					
					if (PortalUserName.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Username";

					}
					if (PortalPassword.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Password";
					}
					}
				} else {
					if (!(!ServerAdminUsername[4].isEmpty()) && (!ServerAdminPassword[4].isEmpty())){
						msg = "Please provide data for fields:";
					
					if (ServerAdminUsername[4].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 5";
					}
					if (ServerAdminPassword[4].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 5";
					}
				}
				}
			}
			if (!flag1) {
				return msg;
			}
			if (!Serverurl[5].isEmpty()) {

				msg = "";
				
				flag1 = true;
				if (ServerFederated[5]) {
					if (!(!PortalUserName.isEmpty() &&!PortalPassword.isEmpty())) {
						msg = "Please provide data for fields:";
					
					if (PortalUserName.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Username";

					}
					if (PortalPassword.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Password";
					}
					}
				} else {
					if (!(!ServerAdminUsername[5].isEmpty()) && (!ServerAdminPassword[5].isEmpty())){
						msg = "Please provide data for fields:";
					
					if (ServerAdminUsername[5].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 6";
					}
					if (ServerAdminPassword[5].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 6";
					}
				}
				}
			}
			if (!flag1) {
				return msg;
			}
			if (!Serverurl[6].isEmpty()) {

				msg = "";
				
				flag1 = true;
				if (ServerFederated[6]) {
					if (!(!PortalUserName.isEmpty() &&!PortalPassword.isEmpty())) {
						msg = "Please provide data for fields:";
					
					if (PortalUserName.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Username";

					}
					if (PortalPassword.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Password";
					}
					}
				} else {
					if (!(!ServerAdminUsername[6].isEmpty()) && (!ServerAdminPassword[6].isEmpty())){
						msg = "Please provide data for fields:";
					
					if (ServerAdminUsername[6].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 7";
					}
					if (ServerAdminPassword[6].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 7";
					}
				}
				}
			}
			if (!flag1) {
				return msg;
			}
			if (!Serverurl[7].isEmpty()) {

				msg = "";
				
				flag1 = true;
				if (ServerFederated[7]) {
					if (!(!PortalUserName.isEmpty() &&!PortalPassword.isEmpty())) {
						msg = "Please provide data for fields:";
					
					if (PortalUserName.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Username";

					}
					if (PortalPassword.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Password";
					}
					}
				} else {
					if (!(!ServerAdminUsername[7].isEmpty()) && (!ServerAdminPassword[7].isEmpty())){
						msg = "Please provide data for fields:";
					
					if (ServerAdminUsername[7].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 8";
					}
					if (ServerAdminPassword[7].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 8";
					}
				}
				}
			}
			if (!flag1) {
				return msg;
			}
			if (!Serverurl[8].isEmpty()) {

				msg = "";
				
				flag1 = true;
				if (ServerFederated[8]) {
					if (!(!PortalUserName.isEmpty() &&!PortalPassword.isEmpty())) {
						msg = "Please provide data for fields:";
					
					if (PortalUserName.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Username";

					}
					if (PortalPassword.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Password";
					}
					}
				} else {
					if (!(!ServerAdminUsername[8].isEmpty()) && (!ServerAdminPassword[8].isEmpty())){
						msg = "Please provide data for fields:";
					
					if (ServerAdminUsername[8].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 9";
					}
					if (ServerAdminPassword[8].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 9";
					}
				}
				}
			}
			if (!flag1) {
				return msg;
			}
			if (!Serverurl[9].isEmpty()) {

				msg = "";
				
				flag1 = true;
				if (ServerFederated[9]) {
					if (!(!PortalUserName.isEmpty() &&!PortalPassword.isEmpty())) {
						msg = "Please provide data for fields:";
					
					if (PortalUserName.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Username";

					}
					if (PortalPassword.isEmpty()) {
						flag1 = false;
						msg = msg + " Portal Password";
					}
					}
				} else {
					if (!(!ServerAdminUsername[9].isEmpty()) && (!ServerAdminPassword[9].isEmpty())){
						msg = "Please provide data for fields:";
					
					if (ServerAdminUsername[9].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Username 10";
					}
					if (ServerAdminPassword[9].isEmpty()) {
						flag1 = false;
						msg = msg + " Server Admin Password 10";
					}
				}
				}
			}
			if (!flag1) {
				return msg;
			}
			if (flagServerRole) {
				
				msg = "";
				flag1 = true;
				if (!(!Portalurl.isEmpty())&&(!PortalUserName.isEmpty()) && (!PortalPassword.isEmpty())){
					msg = "Please provide data for fields:";
				
				if (Portalurl.isEmpty()) {
					flag1 = false;
					msg = msg + " Portal URL";
				}

				if (PortalUserName.isEmpty()) {
					flag1 = false;
					msg = msg + " Portal Username";

				}
				if (PortalPassword.isEmpty()) {
					flag1 = false;
					msg = msg + " Portal Password";

				}
				}
				if (!flag1) {
					return msg;
				}
				if ((Serverurl[0].isEmpty()) && (Serverurl[1].isEmpty()) && (Serverurl[2].isEmpty())
						&& (Serverurl[3].isEmpty()) && (Serverurl[4].isEmpty()) && (Serverurl[5].isEmpty())
						&& (Serverurl[6].isEmpty()) && (Serverurl[7].isEmpty()) && (Serverurl[8].isEmpty())
						&& (Serverurl[9].isEmpty())) {

					flag1 = false;
					msg = "Please provide data for at least one server URL and role";
					return msg;
				}
			}
		}
		return msg;
	}

	public static void setreport() {
		try {
			reportlabel.setEnabled(true);
			reportlabel.setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void close1() throws Exception {
		// timer.restart();
		// timer.stop();
		if (flagreport) {
			String txt = "<html><Strong>INFO:Click 'Reports' link to view reports. Path to reports: "
					+ reportPath.toString() + "</Strong></html>";
			try {
				HTMLDocument doc = (HTMLDocument) txtPane.getStyledDocument();
				HTMLEditorKit editorKit = (HTMLEditorKit) txtPane.getEditorKit();
				// String text = "<a href=\"abc\">hyperlink</a>";

				editorKit.insertHTML(doc, doc.getLength(), txt, 0, 0, null);
			} catch (BadLocationException ex) {
				// ex.printStackTrace();
			} catch (IOException ex) {
				// ex.printStackTrace();
			}
			// flagcomplete = true;

		}
		stopscan.setEnabled(false);
		saveBtn.setEnabled(true);
		runenabled();

		try {
			driver.close();
			driver.quit();
			// Exception Exception;
			throw new Exception();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// throw new Exception() ;
		}

		// Btn.setLabel("Close Tool");

	}

	public static void close() throws Exception {
		// timer.restart();
		timer.stop();
		if (flagreport) {
			String txt = "<html><Strong>INFO:Click 'Reports' link to view reports. Path to reports: "
					+ reportPath.toString() + "</Strong></html>";
			try {
				HTMLDocument doc = (HTMLDocument) txtPane.getStyledDocument();
				HTMLEditorKit editorKit = (HTMLEditorKit) txtPane.getEditorKit();
				// String text = "<a href=\"abc\">hyperlink</a>";

				editorKit.insertHTML(doc, doc.getLength(), txt, 0, 0, null);
			} catch (BadLocationException ex) {
				// ex.printStackTrace();
			} catch (IOException ex) {
				// ex.printStackTrace();
			}
			// flagcomplete = true;

		}
		stopscan.setEnabled(false);
		saveBtn.setEnabled(true);
		runenabled();

		try {
			driver.close();
			driver.quit();
			// Exception Exception;
			// throw new Exception() ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// throw new Exception() ;
		}

		// Btn.setLabel("Close Tool");

	}

	public static boolean InitializeFolder() {
		try {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			resultfolder = System.getProperty("user.dir") + "\\EnterpriseDeploymentCheckResults\\"
					+ dateformat.format(datef);
			reportPath = resultfolder + "\\Reports";
			logPath = resultfolder + "\\Logs";

			SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			logName = "EnterpriseDeploymentCheckLogs_" + dateformat1.format(datef) + ".txt";
			File folder = new File(resultfolder);
			if (!folder.exists()) {
				// List all files in the folder
				boolean flag1 = folder.mkdir();

			}
			File folder1 = new File(reportPath);
			if (!folder1.exists()) {
				// List all files in the folder
				boolean flag1 = folder1.mkdir();

			}
			// if(uirun) {
			File folder2 = new File(logPath);
			if (!folder2.exists()) {
				// List all files in the folder
				boolean flag1 = folder2.mkdir();

			}
			try {
				// out = new FileOutputStream(logPath, true);
				FileOutputStream f1 = FileUtils.openOutputStream(new File(logPath + "\\" + logName));
				printwrite = new PrintStream(f1);

			} catch (Exception e) {
				// e.printStackTrace();
			}
			// }
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
		return true;
	}

	public static void setText(String txt) {
		try {
			if (uirun) {
				// StyleConstants.setBackground(attributes, Color.RED);
				HTMLDocument doc = (HTMLDocument) txtPane.getStyledDocument();
				HTMLEditorKit editorKit = (HTMLEditorKit) txtPane.getEditorKit();
				txt = "<html><font color='Black'>" + txt + "</font></html>";
				if (!stop)
					editorKit.insertHTML(doc, doc.getLength(), txt, 0, 0, null);
			}
		} catch (BadLocationException ex) {
			// ex.printStackTrace();
		} catch (IOException ex) {
			// ex.printStackTrace();
		}
		try {
			printwrite.append(txt.replace(" </b>", "").replace("<html>", "").replace("</html>", "").replace("<b>", "")
					.replace("</font>", "").replace("<font color='Black'>", ""));
			printwrite.append("\n");
		} catch (Exception ex) {
			// ex.printStackTrace();
		}

	}

	public static void setTextRed(String txt) {
		try {
			if (uirun) {
				StringBuilder buildSomething = new StringBuilder();
				buildSomething.append(txt); // textPane.setText(buildSomething.toString());
				HTMLDocument doc = (HTMLDocument) txtPane.getStyledDocument();
				txt = "<html><font color='Red'>" + txt + "</font></html>";
				HTMLEditorKit editorKit = (HTMLEditorKit) txtPane.getEditorKit();
				MutableAttributeSet attributes = new SimpleAttributeSet(txtPane.getInputAttributes());
				StyleConstants.setForeground(attributes, Color.RED);
				if (!stop)
					editorKit.insertHTML(doc, doc.getLength(), txt, 0, 0, null);
				frame.repaint();
			}
			// doc.insertAfterEnd(doc.getCharacterElement(doc.getLength()),
			// buildSomething.toString());
		} catch (BadLocationException ex) {
			// ex.printStackTrace();
		} catch (IOException ex) {
			// ex.printStackTrace();
		}
		try {
			printwrite.append(txt.replace(" </b>", "").replace("<html>", "").replace("</html>", "").replace("<b>", "")
					.replace("</font>", "").replace("<font color='Red'>", ""));
			printwrite.append("\n");
		} catch (Exception ex) {
			// ex.printStackTrace();
		}

	}

	public static void setTextOrange(String txt) {
		try {
			if (uirun) {
				HTMLDocument doc = (HTMLDocument) txtPane.getStyledDocument();
				HTMLEditorKit editorKit = (HTMLEditorKit) txtPane.getEditorKit();
				txt = "<html><font color='Orange'>" + txt + "</font></html>";
				MutableAttributeSet attributes = new SimpleAttributeSet(txtPane.getInputAttributes());
				StyleConstants.setForeground(attributes, Color.ORANGE);
				if (!stop)
					editorKit.insertHTML(doc, doc.getLength(), txt, 0, 0, null);
			}
		} catch (BadLocationException ex) {
			// ex.printStackTrace();
		} catch (IOException ex) {
			// ex.printStackTrace();
		}
		try {
			printwrite.append(txt.replace(" </b>", "").replace("<html>", "").replace("</html>", "").replace("<b>", "")
					.replace("</font>", "").replace("<font color='Orange'>", ""));
			printwrite.append("\n");
		} catch (Exception ex) {
			// ex.printStackTrace();
		}

	}

	public static void setTextGreen(String txt) {
		try {
			if (uirun) {
				HTMLDocument doc = (HTMLDocument) txtPane.getStyledDocument();
				HTMLEditorKit editorKit = (HTMLEditorKit) txtPane.getEditorKit();
				txt = "<html><Strong><font color='Black'>" + txt + "</font></Strong></html>";
				// MutableAttributeSet attributes = new
				// SimpleAttributeSet(txtPane.getInputAttributes());
				// StyleConstants.setForeground(attributes, Color.GREEN);
				if (!stop)
					editorKit.insertHTML(doc, doc.getLength(), txt, 0, 0, null);
			}
		} catch (BadLocationException ex) {
			// ex.printStackTrace();
		} catch (IOException ex) {
			// ex.printStackTrace();
		}

		try {
			printwrite.append(txt.replace(" </b>", "").replace("<html>", "").replace("</html>", "").replace("<b>", "")
					.replace("</font>", "").replace("<font color='Black'>", ""));
			printwrite.append("\n");
		} catch (Exception ex) {
			// ex.printStackTrace();
		}

	}

	public static void setTextBlue(String txt) {
		try {
			if (uirun) {
				HTMLDocument doc = (HTMLDocument) txtPane.getStyledDocument();
				HTMLEditorKit editorKit = (HTMLEditorKit) txtPane.getEditorKit();
				MutableAttributeSet attributes = new SimpleAttributeSet(txtPane.getInputAttributes());
				txt = "<html><font color='Blue'>" + txt + "</font></html>";
				if (!stop)
					editorKit.insertHTML(doc, doc.getLength(), txt, 0, 0, null);
				frame.repaint();
			}
		} catch (BadLocationException ex) {
			// ex.printStackTrace();
		} catch (IOException ex) {
			// ex.printStackTrace();
		}
		try {
			printwrite.append(txt.replace("</b>", "").replace("<html>", "").replace("</html>", "").replace("<b>", "")
					.replace("<br>", "").replace("</font>", "").replace("<font color='Blue'>", ""));
			printwrite.append("\n");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static void setTextnew1ine() {
		try {
			if (uirun) {
				String txt = "\n";
				MutableAttributeSet attributes = new SimpleAttributeSet(txtPane.getInputAttributes());
				StyleConstants.setForeground(attributes, Color.BLACK);

				txtPane.getStyledDocument().insertString(txtPane.getDocument().getLength(), txt, attributes);
			}
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	public static void setTextPink(String txt) {
		try {
			if (uirun) {
				HTMLDocument doc = (HTMLDocument) txtPane.getStyledDocument();
				HTMLEditorKit editorKit = (HTMLEditorKit) txtPane.getEditorKit();

				if (!stop)
					editorKit.insertHTML(doc, doc.getLength(), txt, 0, 0, null);
			}
		} catch (BadLocationException ex) {
			// ex.printStackTrace();
		} catch (IOException ex) {
			// ex.printStackTrace();
		}
		try {
			printwrite.append(txt.replace(" </b>", "").replace("<html>", "").replace("</html>", "").replace("<b>", ""));
			printwrite.append("\n");
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
	}

	public static void setValue(int n) {
		try {
			progressBar.setValue(n * 5);
			progress = n;
			// progressBar.repaint();
			// txtArea.setText(n+"% Completed.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void stopscan() {
		stop = true;
		timer.stop();
		stopscan.setEnabled(false);
		rundisabled();
		// timer.set
		String txt = "<html><Strong>INFO:Tool will Stop after completing current running test,please wait for the 'Run' button to be enable again.</Strong></html>";

		MutableAttributeSet attributes = new SimpleAttributeSet(txtPane.getInputAttributes());
		StyleConstants.setForeground(attributes, Color.black);
		HTMLDocument doc = (HTMLDocument) txtPane.getStyledDocument();
		HTMLEditorKit editorKit = (HTMLEditorKit) txtPane.getEditorKit();
		// String text = "<a href=\"abc\">hyperlink</a>";

		try {
			editorKit.insertHTML(doc, doc.getLength(), txt, 0, 0, null);
		} catch (BadLocationException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		/*
		 * try { driver.close(); driver.quit(); } catch (Exception e) { // TODO
		 * Auto-generated catch block //e.printStackTrace(); }
		 */
		try {
			Thread.sleep(2000);
			// mainTask m_task = new mainTask();
			// m_task.cancel(true); // causes isCancelled to return true in doInBackground
			// m_task.stopTask();
			// m_task = null;
			// Thread.sleep(2000);

			// m_task.stopTask();

		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public class CustomTableCellRenderer1 extends DefaultTableCellRenderer {
		private String str2;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// Call the super implementation of getTableCellRendererComponent
			Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, 1);

			// Check if this is the cell you want to change the background color of
			// if(isSelected )
			if (column == 1) {

				if (!value.toString().isBlank()) {
					if (this.str2.toString().contains(value.toString())) {

						cellComponent.setBackground(Color.yellow);

					} else {
						cellComponent.setBackground(Color.white);
					}
				} else
					cellComponent.setBackground(Color.white);

			}
			return cellComponent;
		}
	}

	public class CustomTableCellRendererdefault extends DefaultTableCellRenderer {

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// Call the super implementation of getTableCellRendererComponent
			Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, 1);

			// Check if this is the cell you want to change the background color of
			// if(isSelected )
			// .table.sets
			int row1 = table.getRowCount();
			int col = table.getColumnCount();
			for (int i = 0; i < row1; i++)
				for (int j = 1; j < col; j++)
					table.getComponentAt(row1, col).setBackground(Color.white);
			// cellComponent.setBackground(Color.white);

			return cellComponent;
		}
	}

	public class CustomTableCellRendererun extends DefaultTableCellRenderer {
		private int row1;
		private int col1;

		public CustomTableCellRendererun(int row1, int col1) {
			// TODO Auto-generated constructor stub
			this.row1 = row1;
			this.col1 = col1;
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			// Call the super implementation of getTableCellRendererComponent
			Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row1,
					col1);

			// Check if this is the cell you want to change the background color of
			// if(isSelected )
			String text = table.getValueAt(row, 1).toString();
			String text1 = table.getValueAt(row, column).toString();
			if (!(text.toString().isBlank()) && (text1.toString().isBlank())) {

				cellComponent.setBackground(Color.yellow);

			} else {
				cellComponent.setBackground(Color.white);
			}

			return cellComponent;
		}
	}

	private void runsmoke() {
		try {
			SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			elapsedTime = 0;
			timer.restart();
			timer.start();
			tabs.setSelectedIndex(2);
			stopscan.setEnabled(true);
			saveBtn.setEnabled(false);
			rundisabled();
			// run.se
			setValue(0);
			stop = false;
			setTextnew1ine();
			setTextBlue(
					"******************************************************************************************************");
			setTextBlue("Test Run Started " + dateformat1.format(datef));
			setTextBlue(
					"******************************************************************************************************");

			(m_task = new mainTask()).execute();

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Not able to run application. Please try again.");
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	static JFrame createAndShowGUI() {
		// Create and set up the window.
		// frame=new jFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		 * String iconPath = "image/android.png"; Image icon; try { icon = new
		 * ImageIcon(getClass().getResource(iconPath)).getImage(); } catch (Exception e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); }
		 * frame.setIconImage(icon);
		 */
		// Create and set up the content pane.
		ConfigControlNew newContentPane = new ConfigControlNew();
		newContentPane.Initialize();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);
		// ImageIcon ico1 = new ImageIcon(dataLocation + "//" + "esri_image.png");

		// frame.setIconImage(ico1.getImage());
		frame.setLocation(50, 50);
		// frame.setBounds(100, 100, 1000, 400);
		frame.setPreferredSize(new Dimension(1150, 850));
		frame.setMinimumSize(new Dimension(1150, 850));
		frame.setMaximumSize(new Dimension(1700, 1000));
		frame.setLayout(new BorderLayout());
		// frame.setResizable(false);
		// frame.getContentPane().add(tabs, BorderLayout.CENTER);
		frame.getContentPane().add(mainpanel, BorderLayout.CENTER);
		// Display the window.
		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("Help");
		JMenu menuAbout = new JMenu("About");
		JMenuItem menuItemhelp = new JMenuItem("Help");
		JMenuItem menuItemabout = new JMenuItem("About");
		menuFile.add(menuItemhelp);
		menuAbout.add(menuItemabout);
		menuBar.add(menuFile);
		menuBar.add(menuAbout);
		// adds menu bar to the frame
		frame.setJMenuBar(menuBar);
		menuItemhelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JMenuItem menu = (JMenuItem) event.getSource();
				if (menu == menuItemhelp) {
					File file = new File(System.getProperty("user.dir") + File.separator + "Input" + File.separator
							+ "Enterprise Deployment Check - Help Doc.pdf");
					try {
						Desktop.getDesktop().open(file);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frame, "No help found");
					}
				}
			}
		});
		menuItemabout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JMenuItem menu = (JMenuItem) event.getSource();
				if (menu == menuItemabout) {
					String info = "<html><span  style='font-weight:normal;color:black;'><b>Enterprise Deployment Check</b><br>Version: 1.1 BETA <br>Run confidence tests against ARCGIS Enterprise<br>Contact Information: mnelson@esri.com<br>Copyright &#169;2026 Esri Inc. All rights reserved.<br></span></html>";
					JOptionPane.showMessageDialog(frame, info);

					// System.exit(0);
				}
			}
		});

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				String ObjButtons[] = { "Yes", "No" };
				int PromptResult = JOptionPane.showOptionDialog(frame,
						"Are you sure you want to close 'Enterprise Deployment Check' tool?", "",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
				if (PromptResult == JOptionPane.YES_OPTION) {
					try {
						// Accessibility_Functions.setting();
						driver.close();
						driver.quit();
					} catch (Exception e1) { // TODO
						// e1.printStackTrace();
					}
					System.exit(0);
				}
			}
		});

		frame.pack();
		frame.setVisible(true);
		return frame;
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			configSetup();
			uirun = true;
			InitializeFolder();
			// creating and showing this application's GUI.
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createAndShowGUI();

				}
			});
		} else {
			if (args[0].equalsIgnoreCase("true")) {
				//if (args.length == 0) {
				try {
					uirun = false;
					InitializeFolder();
					TestRunner trunner = new TestRunner();
					// trunner.callSanity(Browser);
					trunner.callSanity();

				} catch (Exception e) {
					// TODO Auto-generated catch block

				}
			}
		}
	}

	private class mainTask extends SwingWorker<String, String> {
		private volatile boolean running = true;

		public String doInBackground() {
			// System.out.println("text");
			if (running) {
				try {
					configread=false;
					TestRunner trunner = new TestRunner();
					// trunner.callSanity(Browser);
					trunner.callSanity();

				} catch (Exception e) {
					// TODO Auto-generated catch block

				}
			}
			//
			return null;
		}

		public void stopTask() {
			running = false;
		}

		@Override
		protected void done() {
			try {

				// run.setEnabled(true);
				// distortFrame();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private class mainTask1 extends SwingWorker<Integer, Integer> {

		public Integer doInBackground() throws Exception {
			verifyvalues();
			return null;
		}

		@Override
		protected void done() {
			try {
				msglabel.setText("        ");
				get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
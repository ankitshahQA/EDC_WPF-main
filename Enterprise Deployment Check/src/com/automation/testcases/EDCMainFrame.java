package com.automation.testcases;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Main class of the Enterprise Deployment Check application.
 * Holds the global configuration state used by the test classes,
 * loads/saves config.properties, and builds the Swing UI.
 *
 * Kept as a plain class (not a JFrame) so TestBase can extend it without
 * needing a display in headless test runs.
 */
public class EDCMainFrame {

	// ── Configuration state (read by TestSuite / TestRunner via static access) ──
	public static String     projectName    = "Enterprise Deployment Check Test";
	public static Properties prop;

	public static String   Portalurl      = "";
	public static String   PortalUserName = "";
	public static String   PortalPassword = "";
	public static String   Browser        = "Chrome";

	// Up to 10 server slots
	public static String[]  Serverurl           = new String[] {"","","","","","","","","",""};
	public static String[]  ServerAdminUsername = new String[] {"","","","","","","","","",""};
	public static String[]  ServerAdminPassword = new String[] {"","","","","","","","","",""};
	public static String[]  ServerAdminRole     = new String[] {
			"Hosting Server","Hosting Server","Hosting Server","Hosting Server","Hosting Server",
			"Hosting Server","Hosting Server","Hosting Server","Hosting Server","Hosting Server"};
	public static Boolean[] ServerFederated     = new Boolean[] {true,true,true,true,true,true,true,true,true,true};

	public static int no_of_site = 1;
	public static int totalSite  = 10;

	// Run-control flags. Each @Test in TestSuite checks the relevant flag.
	public static boolean configread            = false;
	public static boolean uirun                 = false;
	public static boolean stop                  = false;
	public static boolean flagrun               = true;
	public static boolean flagKUB               = false;
	public static boolean flagRunalltest        = false;
	public static boolean flagloginadmin        = false;
	public static boolean flagloginmanager      = false;
	public static boolean flagloginportal       = false;
	public static boolean flagtilelayer         = false;
	public static boolean flagfeaturelayer      = false;
	public static boolean flaggroup             = false;
	public static boolean flagorganization      = false;
	public static boolean flagscenelayer        = false;
	public static boolean flagdatastore         = false;
	public static boolean flagexperiencebuilder = false;
	public static boolean flagmap               = false;
	public static boolean flagwebappbuilder     = false;
	public static boolean flagcontent           = false;
	public static boolean flagServerRole        = false;
	public static boolean flagreport            = false;
	public static boolean flagcomplete          = false;
	public static boolean scenecreated          = false;
	public static boolean flagdashboard      = false;
	public static boolean flagstorymap      	= false;
	public static boolean submitdefect          = true;

	// Output paths (set by InitializeFolder)
	public static Date   datef           = new Date();
	public static String resultfolder    = System.getProperty("user.dir") + "\\Enterprise Deployment Check Results\\";
	public static String reportPath      = "";
	public static String logPath         = "";
	public static String logName         = "";
	public static PrintStream printwrite;

	public static RemoteWebDriver driver;
	public static JProgressBar    progressBar;

	private static ResultsLogPanel logPanel;

	public static void setLogPanel(ResultsLogPanel p) {
		logPanel = p;
		uirun = (p != null);
	}

	public static ResultsLogPanel getLogPanel() {
		return logPanel;
	}

	public static void setValue(int n) {
		if (progressBar != null) {
			SwingUtilities.invokeLater(() -> progressBar.setValue(n * 5));
		}
	}

	public static int getActiveServerCount() {
		int count = 0;
		for (int i = 0; i < 10; i++) {
			if (Serverurl[i] != null && !Serverurl[i].trim().isEmpty()) count++;
		}
		return Math.max(count, 1);
	}

	// Resolved at first use so CLI (`--config <path>`), system property
	// (`-Dedc.config=<path>`), or environment variable (`EDC_CONFIG`) can
	// override the default `./Input/config.properties`. CI/CD agents typically
	// stage a per-environment properties file outside the working directory
	// and point the engine at it.
	private static String CONFIG_PATH = null;

	public static String getConfigPath() {
		if (CONFIG_PATH != null && !CONFIG_PATH.isEmpty()) return CONFIG_PATH;
		String fromSys = System.getProperty("edc.config");
		if (fromSys != null && !fromSys.isEmpty()) return fromSys;
		String fromEnv = System.getenv("EDC_CONFIG");
		if (fromEnv != null && !fromEnv.isEmpty()) return fromEnv;
		return System.getProperty("user.dir") + "\\Input\\config.properties";
	}

	public static void setConfigPath(String path) { CONFIG_PATH = path; }

	// ── Config load / save ──────────────────────────────────────────────────

	public static void configSetup() {
		try {
			prop = new Properties();
			FileInputStream in = new FileInputStream(getConfigPath());
			prop.load(in);
			in.close();
		} catch (Exception e) {
			createConfig();
		}
	}

	public static void createConfig() {
		try {
			File cfg = new File(getConfigPath());
			File dir = cfg.getParentFile();
			if (dir != null && !dir.exists()) dir.mkdirs();
			if (!cfg.exists()) cfg.createNewFile();
			if (prop == null) prop = new Properties();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// Copy values from the Properties object into the static fields above.
	public static void readfromconfigdisplay() {
		if (prop == null) return;

		Portalurl      = trim(prop.getProperty("PortalUrl"),      Portalurl);
		PortalUserName = trim(prop.getProperty("PortalUserName"), PortalUserName);
		PortalPassword = trim(prop.getProperty("PortalPassword"), PortalPassword);
		flagKUB        = isYes(prop.getProperty("Kubernetes"));

		for (int i = 0; i < 10; i++) {
			int n = i + 1;
			Serverurl[i]           = trim(prop.getProperty("ServerUrl" + n),           Serverurl[i]);
			ServerAdminUsername[i] = trim(prop.getProperty("ServerAdminUsername" + n), ServerAdminUsername[i]);
			String pwd  = prop.getProperty("ServerAdminPassword" + n);
			if (pwd  != null) ServerAdminPassword[i] = pwd;
			String role = prop.getProperty("ServerRole" + n);
			if (role != null) ServerAdminRole[i] = role;
			ServerFederated[i] = isYesOr(prop.getProperty("Federated" + n), true);
		}

		// Highest used slot determines no_of_site
		for (int i = 1; i < 10; i++) {
			if (!ServerAdminPassword[i].isBlank() || !ServerAdminUsername[i].isBlank()
					|| !Serverurl[i].isBlank()) {
				no_of_site = i + 1;
			}
		}

		applyTestFlags();

		Browser = prop.getProperty("Browser");
		if (Browser == null || Browser.isBlank() || Browser.toLowerCase().contains("chrome")) {
			Browser = "Chrome";
		} else {
			Browser = "Edge";
		}
	}

	private static void applyTestFlags() {
		boolean runAll = isYes(prop.getProperty("RunAllTest"));

		if (runAll) {
			flagRunalltest = true;
			flagloginadmin = flagloginmanager = flagloginportal = true;
			flagtilelayer  = flagfeaturelayer = flaggroup = flagorganization = true;
			flagscenelayer = flagmap = flagwebappbuilder = flagexperiencebuilder = true;
			flagcontent    = true;
			flagdashboard=true;
			flagstorymap=true;
			// Kubernetes deployments don't expose data-store / server-role checks
			flagServerRole = !flagKUB;
			flagdatastore  = !flagKUB;
			return;
		}

		flagRunalltest        = false;
		flagloginportal       = isYes(prop.getProperty("LoginPortal"));
		flagloginmanager      = isYes(prop.getProperty("LoginManager"));
		flagloginadmin        = isYes(prop.getProperty("LoginServer"));
		flagServerRole        = !flagKUB && isYes(prop.getProperty("ValidateServerRole"));
		if (flagServerRole) flagloginportal = true;
		flagdatastore         = !flagKUB && isYes(prop.getProperty("ValidateDataStores"));
		flagfeaturelayer      = isYes(prop.getProperty("CreateFeatureLayer"));
		flagtilelayer         = isYes(prop.getProperty("CreateTileLayer"));
		flagmap               = isYes(prop.getProperty("CreateMap"));
		flagwebappbuilder     = isYes(prop.getProperty("CreateWebAppBuilder"));
		flagexperiencebuilder = isYes(prop.getProperty("CreateExperienceBuilderApp"));
		flagscenelayer        = isYes(prop.getProperty("CreateSceneLayer"));
		flaggroup             = isYes(prop.getProperty("CreateMember"));
		flagorganization      = isYes(prop.getProperty("ValidateOrganization"));
		flagdashboard	=isYes(prop.getProperty("CreateDashboard"));
		flagstorymap	=isYes(prop.getProperty("CreateStoryMap"));
		
		flagcontent = flagfeaturelayer || flagtilelayer || flagmap || flagwebappbuilder
				   || flagexperiencebuilder || flagscenelayer || flaggroup||flagdashboard||flagstorymap;

		// Any portal-side test requires login
		if (flagcontent || flagorganization) flagloginportal = true;
	}

	// Returns "" if config is valid, otherwise a user-facing error message.
	public static String verifyConfigvalues() throws Exception {
		if (!(flagRunalltest || flagloginportal || flagloginadmin || flagloginmanager
				|| flagtilelayer || flagServerRole || flagfeaturelayer || flaggroup
				|| flagorganization || flagscenelayer || flagmap || flagwebappbuilder
				|| flagexperiencebuilder || flagdatastore||flagdashboard||flagstorymap)) {
			return "Please select atleast one test to run";
		}

		if (flagloginportal) {
			String m = requirePortal();
			if (!m.isEmpty()) return m;
		}
		if (flagloginadmin) {
			String m = requireAdminServers();
			if (!m.isEmpty()) return m;
		}
		if (flagloginmanager) {
			String m = requireManagerServers();
			if (!m.isEmpty()) return m;
		}
		if (flagServerRole) {
			String m = requirePortal();
			if (!m.isEmpty()) return m;
			if (allServerUrlsEmpty()) {
				return "Please provide data for at least one server URL and role";
			}
		}
		return "";
	}

	private static String requirePortal() {
		if (!Portalurl.isEmpty() && !PortalUserName.isEmpty() && !PortalPassword.isEmpty()) return "";
		StringBuilder msg = new StringBuilder("Please provide data for fields:");
		if (Portalurl.isEmpty())      msg.append(" Portal URL");
		if (PortalUserName.isEmpty()) msg.append(" Portal username");
		if (PortalPassword.isEmpty()) msg.append(" Portal password");
		return msg.toString();
	}

	private static String requireAdminServers() {
		if (allServerUrlsEmpty()) return "Please provide data for at least one server url";

		boolean anyHosting = false;
		for (int i = 0; i < 10; i++) {
			if (!Serverurl[i].isEmpty() && ServerAdminRole[i].toLowerCase().contains("hosting")) {
				anyHosting = true;
				break;
			}
		}
		if (!anyHosting) return "Please provide at least one server url with role 'Hosting Server'";

		for (int i = 0; i < 10; i++) {
			if (Serverurl[i].isEmpty()) continue;
			if (!ServerAdminRole[i].toLowerCase().contains("hosting")) continue;
			if (ServerAdminUsername[i].isEmpty() || ServerAdminPassword[i].isEmpty()) {
				StringBuilder msg = new StringBuilder("Please provide data for fields:");
				if (ServerAdminUsername[i].isEmpty()) msg.append(" Server Admin Username ").append(i + 1);
				if (ServerAdminPassword[i].isEmpty()) msg.append(" Server Admin Password ").append(i + 1);
				return msg.toString();
			}
		}
		return "";
	}

	private static String requireManagerServers() {
		if (allServerUrlsEmpty()) return "Please provide data for at least one server url";

		for (int i = 0; i < 10; i++) {
			if (Serverurl[i].isEmpty()) continue;

			if (ServerFederated[i]) {
				// Federated server uses portal credentials
				if (PortalUserName.isEmpty() || PortalPassword.isEmpty()) {
					StringBuilder msg = new StringBuilder("Please provide data for fields:");
					if (PortalUserName.isEmpty()) msg.append(" Portal Username");
					if (PortalPassword.isEmpty()) msg.append(" Portal Password");
					return msg.toString();
				}
			} else {
				// Standalone server needs its own credentials
				if (ServerAdminUsername[i].isEmpty() || ServerAdminPassword[i].isEmpty()) {
					StringBuilder msg = new StringBuilder("Please provide data for fields:");
					if (ServerAdminUsername[i].isEmpty()) msg.append(" Server Admin Username ").append(i + 1);
					if (ServerAdminPassword[i].isEmpty()) msg.append(" Server Admin Password ").append(i + 1);
					return msg.toString();
				}
			}
		}
		return "";
	}

	private static boolean allServerUrlsEmpty() {
		for (int i = 0; i < 10; i++) if (!Serverurl[i].isEmpty()) return false;
		return true;
	}

	public static void saveConfig() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("Browser",        Browser == null ? "Chrome" : Browser);
		map.put("PortalUrl",      Portalurl);
		map.put("PortalUserName", PortalUserName);
		map.put("PortalPassword", PortalPassword);
		map.put("Kubernetes",     flagKUB ? "Yes" : "No");

		for (int i = 0; i < 10; i++) {
			int n = i + 1;
			map.put("ServerUrl"           + n, Serverurl[i]);
			map.put("ServerAdminUsername" + n, ServerAdminUsername[i]);
			map.put("ServerAdminPassword" + n, ServerAdminPassword[i]);
			map.put("ServerRole"          + n, ServerAdminRole[i]);
			map.put("Federated"           + n, ServerFederated[i] ? "Yes" : "No");
		}

		map.put("RunAllTest",                 flagRunalltest        ? "Yes" : "No");
		map.put("LoginServer",                flagloginadmin        ? "Yes" : "No");
		map.put("LoginManager",               flagloginmanager      ? "Yes" : "No");
		map.put("ValidateDataStores",         flagdatastore         ? "Yes" : "No");
		map.put("LoginPortal",                flagloginportal       ? "Yes" : "No");
		map.put("CreateFeatureLayer",         flagfeaturelayer      ? "Yes" : "No");
		map.put("CreateTileLayer",            flagtilelayer         ? "Yes" : "No");
		map.put("CreateSceneLayer",           flagscenelayer        ? "Yes" : "No");
		map.put("CreateMap",                  flagmap               ? "Yes" : "No");
		map.put("CreateWebAppBuilder",        flagwebappbuilder     ? "Yes" : "No");
		map.put("CreateExperienceBuilderApp", flagexperiencebuilder ? "Yes" : "No");
		map.put("CreateMember",               flaggroup             ? "Yes" : "No");
		map.put("ValidateOrganization",       flagorganization      ? "Yes" : "No");
		map.put("ValidateServerRole",         flagServerRole        ? "Yes" : "No");
		map.put("CreateDashboard",        flagdashboard     ? "Yes" : "No");
		map.put("CreateStoryMap",             flagstorymap          ? "Yes" : "No");

		File dir = new File(System.getProperty("user.dir") + "\\Input");
		if (!dir.exists()) dir.mkdirs();
		try (BufferedWriter w = new BufferedWriter(new FileWriter(CONFIG_PATH))) {
			for (Map.Entry<String, String> e : map.entrySet()) {
				w.write(e.getKey() + " = " + e.getValue());
				w.newLine();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	// Allows CI/CD pipelines to override the artifact root via
	// `--results <dir>`, `-Dedc.results.dir=<dir>`, or `EDC_RESULTS_DIR`.
	// Reports / Logs subfolders are still created under it.
	private static String RESULTS_ROOT_OVERRIDE = null;
	public static void setResultsRoot(String path) { RESULTS_ROOT_OVERRIDE = path; }
	public static String getResultsRoot() {
		if (RESULTS_ROOT_OVERRIDE != null && !RESULTS_ROOT_OVERRIDE.isEmpty()) return RESULTS_ROOT_OVERRIDE;
		String fromSys = System.getProperty("edc.results.dir");
		if (fromSys != null && !fromSys.isEmpty()) return fromSys;
		String fromEnv = System.getenv("EDC_RESULTS_DIR");
		if (fromEnv != null && !fromEnv.isEmpty()) return fromEnv;
		return System.getProperty("user.dir") + "\\EnterpriseDeploymentCheckResults";
	}

	// Create today's Reports/ and Logs/ folders and open the log file for writing.
	public static boolean InitializeFolder() {
		try {
			SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
			resultfolder = getResultsRoot() + "\\" + day.format(datef);
			reportPath   = resultfolder + "\\Reports";
			logPath      = resultfolder + "\\Logs";

			SimpleDateFormat ts = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			logName = "EnterpriseDeploymentCheckLogs_" + ts.format(datef) + ".txt";

			new File(resultfolder).mkdirs();
			new File(reportPath).mkdirs();
			new File(logPath).mkdirs();

			try {
				FileOutputStream fos = FileUtils.openOutputStream(new File(logPath + "\\" + logName));
				// autoFlush=true so the WPF log-file tailer sees every line as soon as it's
				// written. Without this the JVM buffers up to ~8 KB before flushing and the
				// Results Log pane appears to lag the actual run by several test cases.
				printwrite = new PrintStream(fos, true, java.nio.charset.StandardCharsets.UTF_8);
			} catch (Exception ignored) {}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// Kept for backward compatibility — old UI used this hook; new UI doesn't need it.
	public static void setreport() { }

	public static void close() {
		try {
			if (driver != null) {
				driver.quit();
				driver = null;
			}
		} catch (Exception ignored) { }
	}

	// Forced close — same as close(). Kept because TestSuite calls both.
	public static void close1() {
		close();
	}

	// e.g. https://server.com/arcgis/admin/login -> https://server.com/arcgis/manager
	public static String getServerManagerURL(String text) {
		return text.replace("/login", "").replace("admin", "manager");
	}

	// ── Status text helpers (called by TestSuite / TestScenario classes) ────

	public static void setText      (String txt) { writeStatus("default", txt); }
	public static void setTextRed   (String txt) { writeStatus("fail",    txt); }
	public static void setTextBlue  (String txt) { writeStatus("default", txt); }
	public static void setTextOrange(String txt) { writeStatus("warn",    txt); }
	public static void setTextPink  (String txt) { writeStatus("warn",    txt); }
	public static void setTextnew1ine()          { writeStatus("default", "");  }

	// setTextGreen is used both for test-case headings and plain log lines.
	// Lines containing "started" are real test-case headings.
	public static void setTextGreen(String txt) {
		if (txt != null && txt.toLowerCase().contains("started")) {
			writeStatus("info", txt);
		} else {
			writeStatus("pass", txt);
		}
	}

	protected static void writeStatus(String style, String txt) {
		String plain = stripHtml(txt);

		// Style prefix is the on-the-wire marker the WPF UI's LogStreamParser uses
		// to classify lines (PASS / FAIL / WARN). It's prepended to both stdout
		// (headless mode) and the archival log file so a line read by the file
		// tailer keeps the same classification it had on stdout.
		String prefix;
		switch (style == null ? "" : style.toLowerCase()) {
			case "fail":
			case "error":  prefix = "FAIL: "; break;
			case "pass":
			case "success": prefix = "PASS: "; break;
			case "skip":   prefix = "SKIP: "; break;
			case "warn":   prefix = "WARN: "; break;
			default:       prefix = "";       break;
		}

		if (uirun && logPanel != null && !stop) {
			// Test threads aren't on the EDT; route the UI call to it.
			SwingUtilities.invokeLater(() -> logPanel.appendLog(plain, style));
		} else {
			System.out.println(prefix + plain);
		}

		if (printwrite != null) {
			try {
				printwrite.println(prefix + plain);
				printwrite.flush();
			} catch (Exception ignored) {}
		}
	}

	private static String stripHtml(String s) {
		if (s == null) return "";
		return s.replaceAll("(?i)<[^>]*>", "").replace("&nbsp;", " ").trim();
	}

	private static String trim(String v, String def) { return v == null ? def : v.trim(); }
	private static boolean isYes(String v) { return isYesOr(v, false); }
	private static boolean isYesOr(String v, boolean def) {
		if (v == null) return def;
		String t = v.trim().toLowerCase();
		return t.equals("yes") || t.equals("y");
	}

	// ── Icon factories (used by buttons and section headers) ────────────────

	public static Icon play(int size, Color color) {
		return makeIcon(size, (g2, x, y, s) ->
			g2.fillPolygon(
				new int[]{x + 3, x + s - 2, x + 3},
				new int[]{y + 1, y + s / 2, y + s - 1}, 3),
			color);
	}

	public static Icon stop(int size, Color color) {
		return makeIcon(size, (g2, x, y, s) -> {
			int m = 2;
			g2.fillRect(x + m, y + m, s - 2 * m, s - 2 * m);
		}, color);
	}

	public static Icon clear(int size, Color color) {
		return makeIcon(size, (g2, x, y, s) -> {
			g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			int m = 3;
			g2.drawLine(x + m, y + m, x + s - m, y + s - m);
			g2.drawLine(x + s - m, y + m, x + m, y + s - m);
		}, color);
	}

	public static Icon document(int size, Color color) {
		return makeIcon(size, (g2, x, y, s) -> {
			g2.setStroke(new BasicStroke(1.5f));
			int m = 2;
			g2.drawRect(x + m, y + m, s - 2 * m, s - 2 * m);
			int lx1 = x + m + 3, lx2 = x + s - m - 3;
			g2.drawLine(lx1, y + s / 3,     lx2,     y + s / 3);
			g2.drawLine(lx1, y + s / 2,     lx2,     y + s / 2);
			g2.drawLine(lx1, y + s * 2 / 3, lx2 - 2, y + s * 2 / 3);
		}, color);
	}

	public static Icon arrow(int size, Color color, boolean expanded) {
		return makeIcon(size, (g2, x, y, s) -> {
			int cx = x + s / 2, cy = y + s / 2, d = s / 4;
			if (expanded) {
				g2.fillPolygon(new int[]{cx - d, cx, cx + d},
							   new int[]{cy - d / 2, cy + d / 2, cy - d / 2}, 3);
			} else {
				g2.fillPolygon(new int[]{cx - d / 2, cx + d / 2, cx - d / 2},
							   new int[]{cy - d, cy, cy + d}, 3);
			}
		}, color);
	}

	public static Icon eye(int size, Color color, boolean open) {
		return makeIcon(size, (g2, x, y, s) -> {
			g2.setStroke(new BasicStroke(1.5f));
			int cx = x + s / 2, cy = y + s / 2;
			int ew = s - 6, eh = s / 2 - 1;
			g2.drawOval(cx - ew / 2, cy - eh / 2, ew, eh);
			if (open) {
				int r = s / 6;
				g2.fillOval(cx - r, cy - r, r * 2, r * 2);
			} else {
				g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
				g2.drawLine(x + 3, y + s - 3, x + s - 3, y + 3);
			}
		}, color);
	}

	public static Icon skipped(int size, Color color) {
		return makeIcon(size, (g2, x, y, s) -> {
			int stroke = Math.max(2, s / 12);
			g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			int[] xs = { x + s / 2, x + s - 2, x + 2 };
			int[] ys = { y + 2, y + s - 2, y + s - 2 };
			g2.drawPolygon(xs, ys, 3);
			g2.drawLine(x + s / 2, y + s / 3, x + s / 2, y + (2 * s) / 3);
			g2.fillOval(x + s / 2 - 1, y + (2 * s) / 3 + 2, 3, 3);
		}, color);
	}

	public static Icon failed(int size, Color color) {
		return makeIcon(size, (g2, x, y, s) -> {
			int stroke = Math.max(2, s / 12);
			g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2.drawOval(x + stroke, y + stroke, s - 2 * stroke, s - 2 * stroke);
			g2.drawLine(x + s / 3, y + s / 3, x + (2 * s) / 3, y + (2 * s) / 3);
			g2.drawLine(x + (2 * s) / 3, y + s / 3, x + s / 3, y + (2 * s) / 3);
		}, color);
	}

	public static Icon passed(int size, Color color) {
	    return makeIcon(size, (g2, x, y, s) -> {
	        int stroke = Math.max(2, s / 10);
	        g2.setStroke(new BasicStroke(stroke, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

	        // Check mark coordinates (scaled nicely)
	        int x1 = x + s / 5;
	        int y1 = y + s / 2;

	        int x2 = x + (2 * s) / 5;
	        int y2 = y + (3 * s) / 4;

	        int x3 = x + (4 * s) / 5;
	        int y3 = y + s / 4;

	        g2.drawLine(x1, y1, x2, y2);
	        g2.drawLine(x2, y2, x3, y3);
	    }, color);
	}
	
	private interface IconPainter {
		void paint(Graphics2D g2, int x, int y, int size);
	}

	private static Icon makeIcon(int size, IconPainter painter, Color color) {
		return new Icon() {
			public void paintIcon(Component c, Graphics g, int x, int y) {
				Graphics2D g2 = (Graphics2D) g.create();
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setColor(color);
				painter.paint(g2, x, y, size);
				g2.dispose();
			}
			public int getIconWidth()  { return size; }
			public int getIconHeight() { return size; }
		};
	}

	// ── UI bootstrap ────────────────────────────────────────────────────────

	private static final Color BRAND_BLUE     = new Color(  0, 120, 212);
	private static final Color TEXT_PRIMARY   = new Color( 36,  36,  36);
	private static final Color TEXT_SECONDARY = new Color( 96,  96,  96);
	private static final Color BG_APP         = Color.WHITE;
	private static final Color BORDER_COLOR   = new Color(218, 218, 218);
	private static final Font  FONT_TITLE     = new Font("Segoe UI", Font.BOLD,  18);
	private static final Font  FONT_TAB       = FONT_TITLE; // Use the same font for tabs as section headers

	private JFrame               frame;
	private InputParametersPanel inputParametersPanel;
	private TestsToRunPanel      testsToRunPanel;
	private ResultsLogPanel      resultsLogPanel;
	private Properties           uiConfig;
	private volatile Thread      runThread;

	public void show() {
		frame = new JFrame("Enterprise Deployment Check");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setBackground(BG_APP);
		frame.setLayout(new BorderLayout());

		frame.add(buildHeader(), BorderLayout.NORTH);
		frame.add(buildSplit(),  BorderLayout.CENTER);

		resultsLogPanel.setOnRunTests  (this::onRunTests);
		resultsLogPanel.setOnStopTests (this::onStopTests);
		resultsLogPanel.setOnViewReport(this::onViewReport);
		setLogPanel(resultsLogPanel);

		uiConfig = loadPropertiesFromFile();
		inputParametersPanel.loadConfig(uiConfig);
		testsToRunPanel.setKubernetesMode(inputParametersPanel.isKubernetes());
		testsToRunPanel.loadConfig(uiConfig);

		// Sync K8s checkbox state into the tests panel as the user toggles it
		inputParametersPanel.addPropertyChangeListener("kubernetes",
				evt -> testsToRunPanel.setKubernetesMode(inputParametersPanel.isKubernetes()));

		// Save settings on close with confirmation
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int choice = JOptionPane.showConfirmDialog(frame,
						"Are you sure you want to exit Enterprise Deployment Check?",
						"Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (choice != JOptionPane.YES_OPTION) return;

				// Stop running tests and close browser if active
				if (runThread != null && runThread.isAlive()) {
					stop = true;
					closeBrowserSafely();
					flushReport();
					runThread.interrupt();
				}

				saveCurrentState();
				frame.dispose();
				System.exit(0);
			}
		});

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int w = Math.min(1400, (int) (screen.width  * 0.85));
		int h = Math.min( 850, (int) (screen.height * 0.85));
		frame.setPreferredSize(new Dimension(w, h));
		frame.setMinimumSize  (new Dimension(1000, 650));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void saveCurrentState() {
		inputParametersPanel.saveConfig(uiConfig);
		testsToRunPanel.saveConfig(uiConfig);
		savePropertiesToFile(uiConfig);
	}

	private JPanel buildHeader() {
		JPanel header = new JPanel(new BorderLayout());
		header.setBackground(BG_APP);
		header.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR),
				BorderFactory.createEmptyBorder(16, 24, 10, 24)));
		header.setMinimumSize(new Dimension(0, 56));

		JLabel title = new JLabel("Enterprise Deployment Check");
		title.setFont(FONT_TITLE);
		title.setForeground(TEXT_PRIMARY);

		JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 4));
		actions.setBackground(BG_APP);

		JButton aboutBtn = new JButton("About");
		aboutBtn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		aboutBtn.setForeground(TEXT_PRIMARY); // Set About button to black
		aboutBtn.setBorderPainted(false);
		aboutBtn.setContentAreaFilled(false);
		aboutBtn.setFocusPainted(false);
		aboutBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		aboutBtn.addActionListener(e -> {
			String info = "<html><span style='font-weight:normal;color:black;'>"
					+ "<b>Enterprise Deployment Check</b><br>"
					+ "Version: 2.0<br>"
					+ "Run confidence tests against ARCGIS Enterprise<br>"
					+ "Contact Information: mnelson@esri.com<br>"
					+ "Copyright \u00A92026 Esri Inc. All rights reserved.</span></html>";
			JOptionPane.showMessageDialog(frame, info);
		});

		JLabel helpBtn = new JLabel("Help");
		helpBtn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		helpBtn.setForeground(TEXT_PRIMARY);
		helpBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		helpBtn.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override public void mouseClicked(java.awt.event.MouseEvent e) {
				File file = new File(System.getProperty("user.dir") + File.separator + "Input" + File.separator
						+ "Enterprise Deployment Check - Help Doc.pdf");
				try {
					Desktop.getDesktop().open(file);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(frame, "No help found");
				}
			}
		});

		actions.add(helpBtn);
		actions.add(aboutBtn);

		header.add(title,   BorderLayout.WEST);
		header.add(actions, BorderLayout.EAST);
		return header;
	}

	private JSplitPane buildSplit() {
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		split.setBorder(null);
		split.setDividerSize(1);
		split.setResizeWeight(0.7);
		split.setBackground(BG_APP);

		split.setLeftComponent (buildLeftTabs());
		resultsLogPanel = new ResultsLogPanel();
		split.setRightComponent(resultsLogPanel);
		return split;
	}

	private JTabbedPane buildLeftTabs() {
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setFont(FONT_TAB); // FONT_TAB is now FONT_TITLE
		tabs.setBackground(BG_APP);
		tabs.setFocusable(false);
		tabs.setUI(createCleanTabUI());

		inputParametersPanel = new InputParametersPanel();
		testsToRunPanel      = new TestsToRunPanel();
		tabs.addTab("Input Parameters", inputParametersPanel);
		tabs.addTab("Tests to Run",     testsToRunPanel);
		return tabs;
	}

	// Custom tab look: blue underline on the selected tab, no focus dots,
	// fixed 48-px height so the left and right section headers line up.
	private BasicTabbedPaneUI createCleanTabUI() {
		return new BasicTabbedPaneUI() {
			@Override protected void installDefaults() {
				super.installDefaults();
				tabAreaInsets        = new Insets(0, 0, 0, 0);
				selectedTabPadInsets = new Insets(0, 0, 0, 0);
				tabInsets            = new Insets(20, 32, 20, 32);
				contentBorderInsets  = new Insets(1, 0, 0, 0);
			}
			@Override protected void paintFocusIndicator(Graphics g, int p, Rectangle[] r,
					int i, Rectangle ir, Rectangle tr, boolean sel) { }
			@Override protected void paintTabBorder(Graphics g, int p, int i,
					int x, int y, int w, int h, boolean sel) {
				if (sel) { g.setColor(BRAND_BLUE); g.fillRect(x, y + h - 3, w, 3); }
			}
			@Override protected void paintTabBackground(Graphics g, int p, int i,
					int x, int y, int w, int h, boolean sel) {
				g.setColor(BG_APP); g.fillRect(x, y, w, h);
			}
			@Override protected void paintContentBorder(Graphics g, int p, int selIdx) {
				g.setColor(BORDER_COLOR);
				Insets ins = tabPane.getInsets();
				int y = calculateTabAreaHeight(p, runCount, maxTabHeight) + ins.top;
				g.drawLine(ins.left, y, tabPane.getWidth() - ins.right - 1, y);
			}
			@Override protected void paintText(Graphics g, int p, Font f, FontMetrics m,
					int i, String title, Rectangle tr, boolean sel) {
				g.setFont(FONT_TAB); // FONT_TAB is now FONT_TITLE
				g.setColor(sel ? BRAND_BLUE : TEXT_SECONDARY); // Selected: blue, Unselected: grey
				g.drawString(title, tr.x, tr.y + m.getAscent());
			}
			@Override protected int calculateTabHeight(int p, int i, int fh) { return 48; }
			@Override protected int calculateTabWidth(int p, int i, FontMetrics m) {
				return m.stringWidth(tabPane.getTitleAt(i)) + tabInsets.left + tabInsets.right;
			}
		};
	}

	private Properties loadPropertiesFromFile() {
		Properties p = new Properties();
		File f = new File(CONFIG_PATH);
		if (f.exists()) {
			try (FileInputStream fis = new FileInputStream(f)) { p.load(fis); }
			catch (Exception e) { System.err.println("Failed to load config: " + e.getMessage()); }
		}
		return p;
	}

	private void savePropertiesToFile(Properties p) {
		File f = new File(CONFIG_PATH);
		if (f.getParentFile() != null) f.getParentFile().mkdirs();
		try (FileOutputStream fos = new FileOutputStream(f)) {
			p.store(fos, "EDC Configuration");
		} catch (Exception e) {
			System.err.println("Failed to save config: " + e.getMessage());
		}
	}

	// Quick UI-side validation. Deeper rules are checked by verifyConfigvalues().
	private String validateInputs() {
		java.util.List<String> selected = testsToRunPanel.getSelectedTests();
		if (selected.isEmpty()) return "Please select at least one test to run";

		boolean needsPortal = selected.stream().anyMatch(t ->
				t.contains("Portal") || t.contains("Feature") || t.contains("Tile")
				|| t.contains("Scene") || t.contains("Map") || t.contains("WebApp")
				|| t.contains("Experience") || t.contains("Group") || t.contains("Organization")
				|| t.contains("ServerRole"));

		if (needsPortal) {
			String url  = inputParametersPanel.getPortalUrl();
			String user = inputParametersPanel.getAdminUsername();
			String pass = inputParametersPanel.getAdminPassword();
			StringBuilder missing = new StringBuilder();
			if (url.isEmpty())  missing.append("Portal URL, ");
			if (user.isEmpty()) missing.append("Portal Username, ");
			if (pass.isEmpty()) missing.append("Portal Password, ");
			if (missing.length() > 0) {
				missing.setLength(missing.length() - 2);
				return "Please enter credentials: " + missing;
			}
		}
		return "";
	}

	// ── Run / Stop / View Report ────────────────────────────────────────────

	// Show validation error dialog at bottom-center of the Results Log panel
	private void showValidationPopup(String message) {
		// Also log the error in the Results Log panel (centered)
		resultsLogPanel.logError(message);

		// Create the same JOptionPane dialog
		JOptionPane pane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
		JDialog dialog = pane.createDialog(resultsLogPanel, "Validation Error");

		// Position at bottom-center of the results log panel
		Point loc = resultsLogPanel.getLocationOnScreen();
		int panelW = resultsLogPanel.getWidth();
		int panelH = resultsLogPanel.getHeight();
		int dlgW = dialog.getWidth();
		int dlgH = dialog.getHeight();
		int x = loc.x + (panelW - dlgW) / 2;       // center horizontally
		int y = loc.y + panelH - dlgH - 10;         // 10px above the bottom edge
		dialog.setLocation(x, y);
		dialog.setVisible(true);
	}

	private void onRunTests() {
		saveCurrentState();

		String uiErr = validateInputs();
		if (!uiErr.isEmpty()) {
			showValidationPopup(uiErr);
			return;
		}

		// Push the loaded Properties into the static fields used by tests
		prop = uiConfig;
		readfromconfigdisplay();
		InitializeFolder();
		configread = true;
		uirun      = true;
		stop       = false;

		String deepErr;
		try { deepErr = verifyConfigvalues(); } catch (Exception ex) { deepErr = ex.getMessage(); }
		if (deepErr != null && !deepErr.isEmpty()) {
			showValidationPopup(deepErr);
			return;
		}

		startExecution();
	}

	private void startExecution() {
		resultsLogPanel.resetExecutionState();
		EDCTestListener.resetAll();
		resultsLogPanel.setRunningState();
		testsToRunPanel.setTestsEnabled(false);

		int selectedCount = testsToRunPanel.getSelectedTestCount();
		resultsLogPanel.setTotalSteps(selectedCount);

		resultsLogPanel.log("Configuration saved. Starting Enterprise Deployment Check...");
		resultsLogPanel.log("Portal URL: " + Portalurl);
		resultsLogPanel.log("Browser: "    + Browser);
		resultsLogPanel.log("Kubernetes: " + (flagKUB ? "Yes" : "No"));
		resultsLogPanel.log("Servers configured: " + inputParametersPanel.getServerCount());
		resultsLogPanel.log("Tests selected: " + selectedCount);

		
		
		// Run TestNG on a background thread so the UI stays responsive
		runThread = new Thread(() -> {
			try {
				TestRunner.callSanity();
			} catch (Throwable t) {
				SwingUtilities.invokeLater(() -> resultsLogPanel.logError("Run failed: " + t.getMessage()));
			} finally {
				SwingUtilities.invokeLater(() -> {
					// removed automatic completion banner per request
					resultsLogPanel.updateStatusMessage("Test Run Completed " + currentTimestamp());
					resultsLogPanel.setIdleState();
					testsToRunPanel.setTestsEnabled(true);
				});
			}
		}, "EDC-TestRunner");
		runThread.start();
	}

	private void stopExecution() {
		resultsLogPanel.logWarn("Stop requested. Wait for current test to complete.");
		stop = true;
		resultsLogPanel.setStopEnabled(false);

		// Let the TestNG thread finish naturally — remaining @Test methods will skip
		// because they check the 'stop' flag, and @AfterSuite will still execute
		// (handling DeleteAllFolder + browser close).
		new Thread(() -> {
			Thread t = runThread;
			if (t != null && t.isAlive()) {
				try { t.join(120000); } catch (InterruptedException ignored) {} // Wait up to 2 min for AfterSuite
				if (t.isAlive()) {
					// If still alive after 2 min, force interrupt as last resort
					t.interrupt();
					closeBrowserSafely();
				}
			}
			flushReport();

			SwingUtilities.invokeLater(() -> {
				resultsLogPanel.updateStatusMessage("Test Stopped " + currentTimestamp());
				resultsLogPanel.setIdleState();
				testsToRunPanel.setTestsEnabled(true);
			});
		}, "EDC-StopHandler").start();
	}

	private static void closeBrowserSafely() {
		try {
			if (driver != null) {
				driver.quit();
				driver = null;
			}
		} catch (Exception ignored) { }
	}

	// Flush ExtentReports so the HTML is valid even after a stop
	private static void flushReport() {
		try {
			if (com.automation.library.TestBase.extent != null) {
				com.automation.library.TestBase.extent.flush();
			}
		} catch (Exception ignored) { }
	}

	private void onStopTests() {
		stopExecution();
	}

	private static String currentTimestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	private void onViewReport() {
		try {
			File reportDir = new File(reportPath);
			if (reportDir.exists() && Desktop.isDesktopSupported()) {
				Desktop.getDesktop().open(reportDir);
				return;
			}
		} catch (Exception ignored) {}
		JOptionPane.showMessageDialog(frame,
				"Latest reports are stored in:\n" +
				System.getProperty("user.dir") + File.separator + "EnterpriseDeploymentCheckResults",
				"View Report", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		// ── CLI parsing ──────────────────────────────────────────────────
		// Headless / CI-CD usage (Azure DevOps, Jenkins, GitHub Actions, …):
		//
		//   java -jar EDC.jar --headless [--config <path>]
		//                                [--results <dir>]
		//                                [--tests <comma-list>|all]
		//                                [--fail-on-skip]
		//
		// Legacy form `java -jar EDC.jar true` is still honoured (treated as
		// `--headless`).
		//
		// --tests accepts the same canonical keys used in config.properties:
		//   LoginServer, LoginManager, LoginPortal, ValidateDataStores,
		//   ValidateServerRole, ValidateOrganization, CreateFeatureLayer,
		//   CreateTileLayer, CreateMap, CreateWebAppBuilder,
		//   CreateExperienceBuilderApp, CreateSceneLayer, CreateMember,
		//   CreateDashboard, CreateStoryMap, or "all".
		// Passing --tests overrides whatever the config file says.
		//
		// Exit codes (consumed by the CI agent):
		//   0  all selected tests passed (skipped tests are OK by default)
		//   1  one or more tests failed
		//   2  --fail-on-skip was set and one or more tests were skipped
		//   3  config validation error (verifyConfigvalues returned non-empty)
		//   4  uncaught exception during run
		boolean headless = false;
		String  configOverride  = null;
		String  resultsOverride = null;
		String  testsOverride   = null;
		boolean failOnSkip      = false;
		if (args.length > 0 && args[0].equalsIgnoreCase("true")) headless = true;
		for (int i = 0; i < args.length; i++) {
			String a = args[i];
			if (a.equalsIgnoreCase("--headless") || a.equalsIgnoreCase("-h")) {
				headless = true;
			} else if ((a.equalsIgnoreCase("--config") || a.equalsIgnoreCase("-c")) && i + 1 < args.length) {
				configOverride = args[++i];
			} else if ((a.equalsIgnoreCase("--results") || a.equalsIgnoreCase("-r")) && i + 1 < args.length) {
				resultsOverride = args[++i];
			} else if ((a.equalsIgnoreCase("--tests") || a.equalsIgnoreCase("-t")) && i + 1 < args.length) {
				testsOverride = args[++i];
			} else if (a.equalsIgnoreCase("--fail-on-skip")) {
				failOnSkip = true;
			} else if (a.equalsIgnoreCase("--help") || a.equalsIgnoreCase("-?")) {
				printCliHelp();
				System.exit(0);
			}
		}

		if (headless) {
			// ── Headless / CLI mode — run tests without opening the UI ──
			System.out.println("Running in headless mode (no UI)...");
			if (configOverride  != null) setConfigPath(configOverride);
			if (resultsOverride != null) setResultsRoot(resultsOverride);
			System.out.println("Config:  " + getConfigPath());
			System.out.println("Results: " + getResultsRoot());

			// Register a JVM shutdown hook so that if the parent process (the
			// .NET WPF host, or CI agent abort) asks us to terminate, we still
			// delete every artifact created so far. Fires for System.exit() and
			// for normal JVM termination but NOT for TerminateProcess — the
			// parent must request shutdown cooperatively (write "STOP" to our
			// stdin — see the watcher thread below).
			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				try {
					System.out.println("Shutdown signal received \u2014 running test-data cleanup...");
					TestSuite.runShutdownCleanup();
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}, "edc-shutdown-cleanup"));

			// Cooperative stop: parent writes "STOP\n" to our stdin → set the
			// global `stop` flag → every remaining @Test method short-circuits
			// via skipTest(...) → TestNG records SKIPPED → @AfterSuite cleanup
			// runs naturally → JVM exits with the chosen status code.
			Thread stdinWatcher = new Thread(() -> {
				try (java.io.BufferedReader br = new java.io.BufferedReader(
						new java.io.InputStreamReader(System.in))) {
					String line;
					while ((line = br.readLine()) != null) {
						if (line.trim().equalsIgnoreCase("STOP")) {
							System.out.println("Received STOP from parent \u2014 remaining tests will be skipped.");
							stop = true;
							// Force-quit the WebDriver so any in-flight Selenium call
							// (find element, wait, click, …) errors out immediately
							// with a NoSuchSessionException instead of blocking until
							// the GracefulStopTimeout expires. Without this the
							// in-flight @Test can pin TestNG long enough that the
							// parent kills the JVM before @AfterSuite runs the
							// DeleteCreatedTestData cleanup. Best-effort only —
							// swallow every error so the watcher thread survives.
							try {
								org.openqa.selenium.remote.RemoteWebDriver d = driver;
								if (d != null) {
									try { d.quit(); } catch (Throwable ignored) {}
								}
							} catch (Throwable ignored) {}
						}
					}
				} catch (Throwable ignored) {}
			}, "edc-stdin-watcher");
			stdinWatcher.setDaemon(true);
			stdinWatcher.start();

			int exitCode = 0;
			try {
				configSetup();
				uirun = false;
				readfromconfigdisplay();

				// --tests <list>|all overrides the config-driven selection so
				// CI pipelines can re-use a single shared config.properties and
				// pick a subset per job/stage.
				if (testsOverride != null && !testsOverride.isEmpty()) {
					applyTestsOverride(testsOverride);
				}

				InitializeFolder();
				configread = true;
				stop = false;

				String err = verifyConfigvalues();
				if (err != null && !err.isEmpty()) {
					System.err.println("Config validation failed: " + err);
					System.exit(3);
				}
				TestRunner.callSanity();
				exitCode = computeHeadlessExitCode(failOnSkip);
			} catch (Throwable t) {
				System.err.println("Test execution failed: " + t.getMessage());
				t.printStackTrace();
				exitCode = 4;
			} finally {
				try { closeBrowserSafely(); } catch (Throwable ignored) {}
				try { flushReport();        } catch (Throwable ignored) {}
				System.out.println("Headless run completed. Exit code: " + exitCode);
			}
			System.exit(exitCode);
		} else {
			// ── GUI mode — open the Swing window ──
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception ignored) {}

			SwingUtilities.invokeLater(() -> {
				UIManager.put("TabbedPane.focus",            new Color(0, 0, 0, 0));
				UIManager.put("TabbedPane.selected",         Color.WHITE);
				UIManager.put("TabbedPane.background",       Color.WHITE);
				UIManager.put("TabbedPane.contentAreaColor", Color.WHITE);
				UIManager.put("TabbedPane.tabsOpaque",       Boolean.FALSE);
				UIManager.put("TabbedPane.font",             new Font("Segoe UI", Font.PLAIN, 13));

				new EDCMainFrame().show();
			});
		}
	}

	private static void printCliHelp() {
		System.out.println("Enterprise Deployment Check — CLI options");
		System.out.println("  --headless, -h                Run without UI (CI/CD mode)");
		System.out.println("  --config  <path>, -c <path>   Path to config.properties");
		System.out.println("  --results <dir>,  -r <dir>    Root folder for reports/logs");
		System.out.println("  --tests   <list>, -t <list>   Comma-separated test keys, or 'all'");
		System.out.println("  --fail-on-skip                Exit 2 if any test was skipped");
		System.out.println("  --help, -?                    Print this message");
		System.out.println();
		System.out.println("Test keys: LoginServer, LoginManager, LoginPortal, ValidateDataStores,");
		System.out.println("           ValidateServerRole, ValidateOrganization, CreateFeatureLayer,");
		System.out.println("           CreateTileLayer, CreateMap, CreateWebAppBuilder,");
		System.out.println("           CreateExperienceBuilderApp, CreateSceneLayer, CreateMember,");
		System.out.println("           CreateDashboard, CreateStoryMap");
		System.out.println();
		System.out.println("Exit codes: 0 pass | 1 failure | 2 skipped(+--fail-on-skip) | 3 config | 4 error");
	}

	// Apply --tests <comma-list>|all by toggling the static flag* fields that
	// applyTestFlags() would otherwise set from properties. Called AFTER
	// readfromconfigdisplay() so it cleanly overrides whatever the config said.
	private static void applyTestsOverride(String csv) {
		boolean all = csv.trim().equalsIgnoreCase("all");
		if (all) {
			flagRunalltest = true;
			flagloginadmin = flagloginmanager = flagloginportal = true;
			flagtilelayer  = flagfeaturelayer = flaggroup = flagorganization = true;
			flagscenelayer = flagmap = flagwebappbuilder = flagexperiencebuilder = true;
			flagcontent    = true;
			flagdashboard  = true;
			flagstorymap   = true;
			flagServerRole = !flagKUB;
			flagdatastore  = !flagKUB;
			System.out.println("Tests override: ALL");
			return;
		}
		// Zero everything first — explicit selection only.
		flagRunalltest = false;
		flagloginadmin = flagloginmanager = flagloginportal = false;
		flagtilelayer  = flagfeaturelayer = flaggroup = flagorganization = false;
		flagscenelayer = flagmap = flagwebappbuilder = flagexperiencebuilder = false;
		flagdatastore  = flagServerRole = flagdashboard = flagstorymap = false;
		flagcontent    = false;

		for (String raw : csv.split(",")) {
			String key = raw.trim();
			if (key.isEmpty()) continue;
			switch (key.toLowerCase()) {
				case "loginserver":                 flagloginadmin        = true; break;
				case "loginmanager":                flagloginmanager      = true; break;
				case "loginportal":                 flagloginportal       = true; break;
				case "validatedatastores":          flagdatastore         = !flagKUB; break;
				case "validateserverrole":          flagServerRole        = !flagKUB; flagloginportal = true; break;
				case "validateorganization":       flagorganization      = true; flagloginportal = true; break;
				case "createfeaturelayer":         flagfeaturelayer      = true; break;
				case "createtilelayer":            flagtilelayer         = true; break;
				case "createmap":                   flagmap               = true; break;
				case "createwebappbuilder":        flagwebappbuilder     = true; break;
				case "createexperiencebuilderapp": flagexperiencebuilder = true; break;
				case "createscenelayer":           flagscenelayer        = true; break;
				case "createmember":                flaggroup             = true; break;
				case "createdashboard":            flagdashboard         = true; break;
				case "createstorymap":             flagstorymap          = true; break;
				default:
					System.err.println("Unknown test key in --tests: '" + key + "' (ignored)");
			}
		}
		flagcontent = flagfeaturelayer || flagtilelayer || flagmap || flagwebappbuilder
				   || flagexperiencebuilder || flagscenelayer || flaggroup
				   || flagdashboard || flagstorymap;
		if (flagcontent || flagorganization) flagloginportal = true;
		System.out.println("Tests override: " + csv);
	}

	// Translate TestNG run status into a CI-friendly exit code.
	// TestNG.getStatus() returns a bit mask: 1 = HAS_FAILURE, 2 = HAS_SKIPPED.
	private static int computeHeadlessExitCode(boolean failOnSkip) {
		try {
			org.testng.TestNG tn = TestRunner.testng;
			if (tn == null) return 0;
			int status = tn.getStatus();
			boolean hasFailure = (status & 1) != 0;
			boolean hasSkipped = (status & 2) != 0;
			if (hasFailure) return 1;
			if (hasSkipped && failOnSkip) return 2;
			return 0;
		} catch (Throwable t) {
			t.printStackTrace();
			return 4;
		}
	}
}

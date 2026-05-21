package com.automation.testcases;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

/**
 * Right-side panel that shows test stopscan = new JButton("Stop Tests");
run = new JButton("Run Test");
saveBtn = new JButton("Save");
stopscan = new JButton("Stop Tests");
run = new JButton("Run Test");
saveBtn = new JButton("Save");
stopscan = new JButton("Stop Tests");
run = new JButton("Run Test");
saveBtn = new JButton("Save");

 * Test code calls EDCMainFrame.setTextXxx() which routes here via appendLog().
 */
public class ResultsLogPanel extends JPanel {

	// ── Palette / fonts ─────────────────────────────────────────────────────
	private static final Color TEXT_BLACKHEADING_COLOR   = new Color( 36,  36,  36);//All heading color -black
	private static final Color TEXT_SECONDARY_COLOR = new Color( 96,  96,  96);// -grey
	private static final Color BG_PAGE        = new Color(243, 244, 246);
	private static final Color BG_CARD        = Color.WHITE;
	private static final Color BRAND_BLUE     = new Color(  0, 120, 212);
	private static final Color DARK_BLUE      = new Color(  0,  90, 170);
	private static final Color LIGHT_BLUE     = new Color(179, 215, 243);
	private static final Color BORDER_COLOR   = new Color(218, 218, 218);
	private static final Color SUCCESS_GREEN  = new Color( 16, 124,  16);
	private static final Color FAIL_RED       = new Color(196,  43,  28);
	private static final Color STOP_RED       = new Color(215,  45,  45);
	private static final Color WARN_ORANGE    = new Color(225, 125,   0);

	private static final Font FONT_RESULT_TITLE    = new Font("Segoe UI", Font.BOLD,  16);//main title :Result Logs
	private static final Font FONT_BTN      = new Font("Segoe UI", Font.PLAIN, 13);//clear,run,run test label
	private static final Font FONT_TESTCASECOUNT     = new Font("Segoe UI", Font.BOLD,  14);
	private static final Font FONT_TESTCASECOUNT_LBL = new Font("Segoe UI", Font.PLAIN, 14);//passed,failed
	private static final Font FONT_HEADING  = new Font("Segoe UI", Font.BOLD,  15);//logs heading
	private static final Font FONT_STEP     = new Font("Segoe UI", Font.PLAIN, 15);//logs size
	private static final Font FONT_ICON     = resolveIconFont();

	private static final Dimension BTN_SIZE = new Dimension(110, 30);

	/**
	 * Section-heading height shared with EDCMainFrame so the "Results Log"
	 * title and the "Input Parameters / Tests to Run" tabs line up.
	 */
	public static final int SECTION_HEADER_H = 48;

	private static final String ICON_PASS = "\u2714"; // ✔
	private static final String ICON_FAIL = "\u2718"; // ✘
	private static final String ICON_PLAY = "\u25B6"; // ▶

	// Find a font that can render the Unicode tick — Segoe UI Symbol on Windows
	private static Font resolveIconFont() {
		String[] candidates = { "Segoe UI Symbol", "Segoe UI Emoji", "Dialog" };
		for (String name : candidates) {
			Font f = new Font(name, Font.PLAIN, 13);
			if (f.canDisplay('\u2714')) return f;
		}
		return new Font("Segoe UI", Font.PLAIN, 13);
	}

	// ── Widgets ─────────────────────────────────────────────────────────────
	private JPanel      logContainer;
	private JPanel      emptyStatePanel;
	private JPanel      errorStatePanel;   // centered error message card
	private JLabel      errorIconLabel;
	private JLabel      errorTextLabel;
	private JScrollPane logScrollPane;
	private JPanel      bodyCard;
	private boolean     hasLogContent;

	private JButton clearBtn;
	private JButton runBtn;
	private JButton reportBtn;

	private JPanel       summaryPanel;
	private JLabel       elapsedLabel;
	private JLabel       totalLabel, passedLabel, failedLabel, skippedLabel;
	private JProgressBar progressBar;
	private JLabel       progressText;

	private JPanel statusBar;
	private JLabel statusLabel;

	private boolean running;
	private long    startMs;
	private Timer   elapsedTimer;

	private int total, passed, failed, skipped;

	// Wired by EDCMainFrame
	private Runnable onRunTests;
	private Runnable onStopTests;
	private Runnable onViewReport;

	public ResultsLogPanel() {
		setLayout(new BorderLayout());
		setBackground(BG_CARD);

		summaryPanel = buildSummary();
		summaryPanel.setVisible(false);

		// Stack header and summary at the top
		JPanel topWrapper = new JPanel();
		topWrapper.setLayout(new BoxLayout(topWrapper, BoxLayout.Y_AXIS));
		topWrapper.setBackground(BG_CARD);
		topWrapper.add(buildHeader());
		topWrapper.add(summaryPanel);

		add(topWrapper,  BorderLayout.NORTH);
		add(buildBody(), BorderLayout.CENTER);

		statusBar = buildStatusBar();
		statusBar.setVisible(false);
		add(statusBar, BorderLayout.SOUTH);

		// 1-second tick to update the elapsed time label
		elapsedTimer = new Timer(1000, e -> updateElapsed());
		setIdleState();
	}

	// ── Layout construction ─────────────────────────────────────────────────

	private JPanel buildHeader() {
		// BorderLayout — title on the left, buttons pinned to the right
		JPanel header = new JPanel(new BorderLayout(8, 0));
		header.setBackground(BG_CARD);
		header.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR),
				BorderFactory.createEmptyBorder(8, 14, 8, 10)));

		JLabel title = new JLabel("Results Log");
		title.setFont(FONT_RESULT_TITLE);
		title.setForeground(TEXT_BLACKHEADING_COLOR);

		// GridBagLayout keeps the three buttons on one row (FlowLayout would wrap)
		JPanel btns = new JPanel(new GridBagLayout());
		btns.setOpaque(false);

		clearBtn  = makeButton("Clear",     EDCMainFrame.clear   (13, TEXT_BLACKHEADING_COLOR));
		runBtn    = makeButton("Run Tests", EDCMainFrame.play    (11, Color.WHITE));
		reportBtn = makeButton("Report",    EDCMainFrame.document(11, Color.WHITE));

		clearBtn .addActionListener(e -> clearLog());
		runBtn   .addActionListener(e -> onRunOrStopClicked());
		reportBtn.addActionListener(e -> { if (onViewReport != null) onViewReport.run(); });

		GridBagConstraints bgc = new GridBagConstraints();
		bgc.gridy = 0; bgc.anchor = GridBagConstraints.CENTER;
		bgc.insets = new Insets(0, 3, 0, 3);

		bgc.gridx = 0; btns.add(clearBtn,  bgc);
		bgc.gridx = 1; btns.add(runBtn,    bgc);
		bgc.gridx = 2; btns.add(reportBtn, bgc);

		header.add(title, BorderLayout.CENTER);
		header.add(btns,  BorderLayout.EAST);

		// Match the left-tab header height so the two title lines align
		header.setMinimumSize  (new Dimension(0, SECTION_HEADER_H));
		header.setPreferredSize(new Dimension(0, SECTION_HEADER_H));
		return header;
	}

	private JComponent buildBody() {
		// Empty state — shown until the first log row arrives
		emptyStatePanel = new JPanel();
		emptyStatePanel.setLayout(new BoxLayout(emptyStatePanel, BoxLayout.Y_AXIS));
		emptyStatePanel.setBackground(BG_PAGE);
		emptyStatePanel.setBorder(BorderFactory.createEmptyBorder(60, 24, 40, 24));

		JLabel l1 = centered("Click \"Run Tests\" to start the deployment check",
				new Font("Segoe UI", Font.PLAIN, 14), TEXT_BLACKHEADING_COLOR);
		JLabel l2 = centered("Configure input parameters and select tests before running",
				new Font("Segoe UI", Font.PLAIN, 11), TEXT_SECONDARY_COLOR);
		emptyStatePanel.add(Box.createVerticalGlue());
		emptyStatePanel.add(l1);
		emptyStatePanel.add(Box.createVerticalStrut(4));
		emptyStatePanel.add(l2);
		emptyStatePanel.add(Box.createVerticalGlue());

		// Log container — vertical stack of row panels (one per log line / heading)
		logContainer = new JPanel();
		logContainer.setLayout(new BoxLayout(logContainer, BoxLayout.Y_AXIS));
		logContainer.setBackground(BG_PAGE);
		logContainer.setBorder(BorderFactory.createEmptyBorder(10, 14, 10, 14));
		hasLogContent = false;

		// Wrapper keeps logContainer top-aligned inside the scroll viewport
		JPanel logWrapper = new JPanel(new BorderLayout());
		logWrapper.setBackground(BG_PAGE);
		logWrapper.add(logContainer, BorderLayout.NORTH);

		logScrollPane = new JScrollPane(logWrapper);
		logScrollPane.setBorder(null);
		logScrollPane.getViewport().setBackground(BG_PAGE);
		logScrollPane.getVerticalScrollBar().setUnitIncrement(16);

		// Error state — centered error message (shown for validation errors)
		errorStatePanel = new JPanel(new GridBagLayout()); // GridBagLayout auto-centers
		errorStatePanel.setBackground(BG_PAGE);
		errorIconLabel = new JLabel(ICON_FAIL);
		errorIconLabel.setFont(FONT_ICON);
		errorIconLabel.setForeground(FAIL_RED);
		errorTextLabel = new JLabel("");
		errorTextLabel.setFont(FONT_STEP);
		errorTextLabel.setForeground(FAIL_RED);

		JPanel errorRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 0));
		errorRow.setBackground(BG_PAGE);
		errorRow.add(errorIconLabel);
		errorRow.add(errorTextLabel);
		errorStatePanel.add(errorRow); // GridBagLayout centers this automatically

		// CardLayout swap between empty placeholder, log, and error
		bodyCard = new JPanel(new CardLayout());
		bodyCard.add(emptyStatePanel, "empty");
		bodyCard.add(logScrollPane,   "log");
		bodyCard.add(errorStatePanel, "error");
		return bodyCard;
	}

	private JPanel buildSummary() {
		JPanel summary = new JPanel(new GridBagLayout());
		summary.setBackground(BG_CARD);
		summary.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 0, 1, 0, BORDER_COLOR),
				BorderFactory.createEmptyBorder(8, 14, 8, 14)));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill   = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;
		gbc.gridx = 0;

		// Row 0 — "Time Elapsed: HH:MM:SS"
		gbc.gridy = 0; gbc.insets = new Insets(0, 0, 4, 0);
		JPanel elapsedRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
		elapsedRow.setOpaque(false);

		JLabel elapsedHeading = new JLabel("Time Elapsed:");
		elapsedHeading.setFont(new Font("Segoe UI", Font.BOLD, 15));
		elapsedHeading.setForeground(TEXT_BLACKHEADING_COLOR);

		elapsedLabel = new JLabel("00:00:00");
		elapsedLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		elapsedLabel.setForeground(BRAND_BLUE);

		elapsedRow.add(elapsedHeading);
		elapsedRow.add(elapsedLabel);
		summary.add(elapsedRow, gbc);

		// Row 1 — Total / Passed / Failed / Skipped counter cells
		gbc.gridy = 1; gbc.insets = new Insets(4, 0, 4, 0);
		totalLabel   = statValue(TEXT_BLACKHEADING_COLOR);
		passedLabel  = statValue(SUCCESS_GREEN);
		failedLabel  = statValue(FAIL_RED);
		skippedLabel = statValue(WARN_ORANGE);

		JPanel counters = new JPanel(new GridLayout(1, 4, 8, 0));
		counters.setOpaque(false);
		counters.setPreferredSize(new Dimension(300, 40));
		counters.add(makeCounterPanel("Total",   totalLabel));
		counters.add(makeCounterPanel("Passed",  passedLabel));
		counters.add(makeCounterPanel("Failed",  failedLabel));
		counters.add(makeCounterPanel("Skipped", skippedLabel));
		summary.add(counters, gbc);

		// Row 2 — Progress bar with "n%" label on the right
		gbc.gridy = 2; gbc.insets = new Insets(4, 0, 0, 0);
		JPanel progressRow = new JPanel(new BorderLayout(8, 0));
		progressRow.setOpaque(false);
		progressBar = new JProgressBar(0, 100);
		progressBar.setForeground(BRAND_BLUE);
		progressBar.setPreferredSize(new Dimension(200, 8));
		progressText = new JLabel("0%");
		progressText.setFont(new Font("Segoe UI", Font.BOLD, 11));
		progressText.setForeground(TEXT_BLACKHEADING_COLOR);
		progressRow.add(progressBar,  BorderLayout.CENTER);
		progressRow.add(progressText, BorderLayout.EAST);
		summary.add(progressRow, gbc);

		return summary;
	}

	private JPanel buildStatusBar() {
		JPanel bar = new JPanel(new BorderLayout());
		bar.setBackground(BG_CARD);
		bar.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(1, 0, 0, 0, BORDER_COLOR),
				BorderFactory.createEmptyBorder(6, 14, 6, 14)));

		statusLabel = new JLabel(" ");
		statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		statusLabel.setForeground(TEXT_BLACKHEADING_COLOR);
		bar.add(statusLabel, BorderLayout.WEST);
		return bar;
	}

	// ── Clear,Run TestSection,Progress bar,(live) management ────────────────────────────────────────────────────
    
	public void setIdleState() {
		running = false;
		stopElapsedTimer();

		boolean hasLog = hasLogContent;
		showCard(hasLog ? "log" : "empty");

		runBtn.setText("Run Tests");
		runBtn.setIcon(EDCMainFrame.play(11, Color.WHITE));
		styleFilled(runBtn, BRAND_BLUE, Color.WHITE);
		runBtn.setEnabled(true);

		boolean clearEnabled = hasLog || summaryPanel.isVisible();
		clearBtn.setEnabled(clearEnabled);
		clearBtn.setIcon(EDCMainFrame.clear(13, clearEnabled ? TEXT_BLACKHEADING_COLOR : BORDER_COLOR));
		styleOutlined(clearBtn, BG_CARD, clearEnabled ? TEXT_BLACKHEADING_COLOR : TEXT_SECONDARY_COLOR);

		reportBtn.setEnabled(hasLog);
		styleFilled(reportBtn, hasLog ? DARK_BLUE : LIGHT_BLUE, Color.WHITE);
	}

	public void setRunningState() {
		running = true;
		showCard("log");
		showTimeElapsed();
		showStatusBar();

		runBtn.setText("Stop Tests");
		runBtn.setIcon(EDCMainFrame.stop(11, Color.WHITE));
		styleFilled(runBtn, STOP_RED, Color.WHITE);
		runBtn.setEnabled(true);

		clearBtn.setEnabled(false);
		clearBtn.setIcon(EDCMainFrame.clear(13, BORDER_COLOR));
		styleOutlined(clearBtn, BG_CARD, TEXT_SECONDARY_COLOR);

		reportBtn.setEnabled(false);
		styleFilled(reportBtn, LIGHT_BLUE, Color.WHITE);
	}

	public void showTimeElapsed() {
		summaryPanel.setVisible(true);
		startMs = System.currentTimeMillis();
		startElapsedTimer();
	}

	public void hideTimeElapsed() {
		stopElapsedTimer();
		summaryPanel.setVisible(false);
	}

	public void showStatusBar() { statusBar.setVisible(true); }

	public void hideStatusBar() {
		statusLabel.setText(" ");
		statusBar.setVisible(false);
	}

	public void updateStatusMessage(String message) { 
		statusLabel.setText(message); 
		}

	// Wipe the panel for a fresh run
	public void resetExecutionState() {
		logContainer.removeAll();
		logContainer.revalidate();
		logContainer.repaint();
		hasLogContent = false;

		total = passed = failed = skipped = 0;
		elapsedLabel.setText("00:00:00");
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setString("0%");
		progressText.setText("0%");
		updateTestCounts();

		updateStatusMessage("Test Started " + currentTimestamp());
	}

	// ── Public API used by EDCMainFrame and EDCTestListener ─────────────────

	public void setOnRunTests  (Runnable cb) { this.onRunTests   = cb; }
	public void setOnStopTests (Runnable cb) { this.onStopTests  = cb; }
	public void setOnViewReport(Runnable cb) { this.onViewReport = cb; }

	public void setStopEnabled(boolean enabled) { runBtn.setEnabled(enabled); }

	public void setTotalSteps(int selectedTestCount) {
		total = selectedTestCount;
		updateTestCounts();
	}

	public void updateTestCounts() {
		totalLabel  .setText(String.valueOf(total));
		passedLabel .setText(String.valueOf(passed));
		failedLabel .setText(String.valueOf(failed));
		skippedLabel.setText(String.valueOf(skipped));
		updateProgressText();
	}

	public void incrementPassed () { passed++;  updateTestCounts(); }
	public void incrementFailed () { failed++;  updateTestCounts(); }
	public void incrementSkipped() { skipped++; updateTestCounts(); }

	// Used at end of run to overwrite incremental counts with TestNG's totals
	public void setFinalCounts(int passed, int failed, int skipped) {
		this.passed  = passed;
		this.failed  = failed;
		this.skipped = skipped;
		updateTestCounts();
	}

	// ── Log writing ─────────────────────────────────────────────────────────

	/**
	 * Append a log line. Style determines how it renders:
	 *   "info"       — bold heading with ▶ icon and separator
	 *   "success"    — ✔ row in green
	 *   "error"      — ✘ row in red
	 *   "warn"       — orange plain row
	 *   "completion" — green-bordered completion banner
	 *   "default"    — plain row
	 */
	public void appendLog(String message, String styleName) {
		if (message == null || message.trim().isEmpty()) return;
		String style = (styleName != null) ? styleName : "default";

		switch (style) {
			case "info":       addTestCaseHeading(message); break;
			case "success":    addTestStepRow(message, SUCCESS_GREEN, true);  break;
			case "error":      addTestStepRow(message, FAIL_RED,      false); break;
			case "warn":       addColoredPlainRow(message, WARN_ORANGE);      break;
			case "completion": addCompletionRow(message);                     break;
			default:           addPlainRow(message);                          break;
		}

		hasLogContent = true;
		showCard("log");

		// Auto-scroll to the latest entry
		SwingUtilities.invokeLater(() -> {
			JScrollBar vBar = logScrollPane.getVerticalScrollBar();
			vBar.setValue(vBar.getMaximum());
		});
	}

	// "▶  Login to Portal functionality started" + thin separator below
	private void addTestCaseHeading(String text) {
		if (logContainer.getComponentCount() > 0) {
			logContainer.add(Box.createVerticalStrut(18));
		}

		JPanel headingRow = new JPanel(new BorderLayout(4, 0));
		headingRow.setBackground(BG_PAGE);
		headingRow.setAlignmentX(Component.LEFT_ALIGNMENT);
		headingRow.setBorder(BorderFactory.createEmptyBorder(4, 0, 4, 8));

		JLabel arrow = new JLabel(ICON_PLAY + "  ");
		arrow.setFont(FONT_ICON);
		arrow.setForeground(BRAND_BLUE);
		arrow.setVerticalAlignment(SwingConstants.TOP);

		JTextArea headingArea = makeTextArea(text, FONT_HEADING, TEXT_BLACKHEADING_COLOR, BG_PAGE);
		headingRow.add(arrow,       BorderLayout.WEST);
		headingRow.add(headingArea, BorderLayout.CENTER);
		logContainer.add(headingRow);

		JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
		sep.setForeground(BORDER_COLOR);
		sep.setBackground(BG_PAGE);
		sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
		sep.setAlignmentX(Component.LEFT_ALIGNMENT);
		logContainer.add(sep);
		logContainer.add(Box.createVerticalStrut(4));

		logContainer.revalidate();
		logContainer.repaint();
	}

	// "✔ Wait for sign-in page to load" / "✘ Click on sign in"
	private void addTestStepRow(String text, Color statusColor, boolean passed) {
		JPanel stepRow = new JPanel(new BorderLayout(6, 0));
		stepRow.setBackground(BG_PAGE);
		stepRow.setBorder(BorderFactory.createEmptyBorder(2, 24, 2, 8));
		stepRow.setAlignmentX(Component.LEFT_ALIGNMENT);

		JLabel iconLabel = new JLabel();
		// Use graphical icons for pass/fail
		Icon stepIcon = passed ? EDCMainFrame.passed(18, statusColor) : EDCMainFrame.failed(18, statusColor);
		iconLabel.setIcon(stepIcon);
		iconLabel.setVerticalAlignment(SwingConstants.TOP);
		iconLabel.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 6));

		Color textColor = passed ? TEXT_BLACKHEADING_COLOR : FAIL_RED;
		JTextArea stepArea = makeTextArea(text, FONT_STEP, textColor, BG_PAGE);

		stepRow.add(iconLabel, BorderLayout.WEST);
		stepRow.add(stepArea,  BorderLayout.CENTER);

		logContainer.add(stepRow);
		logContainer.revalidate();
		logContainer.repaint();
	}

	private void addPlainRow(String text) {
		addColoredPlainRow(text, TEXT_BLACKHEADING_COLOR);
	}

	private void addColoredPlainRow(String text, Color color) {
		JPanel row = new JPanel(new BorderLayout());
		row.setBackground(BG_PAGE);
		row.setBorder(BorderFactory.createEmptyBorder(2, 24, 2, 8));
		row.setAlignmentX(Component.LEFT_ALIGNMENT);
		row.add(makeTextArea(text, FONT_STEP, color, BG_PAGE), BorderLayout.CENTER);

		logContainer.add(row);
		logContainer.revalidate();
		logContainer.repaint();
	}

	private void addCompletionRow(String text) {
		logContainer.add(Box.createVerticalStrut(12));

		Color bg = new Color(232, 245, 232);
		JPanel row = new JPanel(new BorderLayout());
		row.setBackground(bg);
		row.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(SUCCESS_GREEN, 1),
			BorderFactory.createEmptyBorder(6, 14, 6, 14)));
		row.setAlignmentX(Component.LEFT_ALIGNMENT);
		row.add(makeTextArea(text, new Font("Segoe UI", Font.PLAIN, 12), SUCCESS_GREEN, bg),
			BorderLayout.CENTER);

		logContainer.add(row);
		logContainer.revalidate();
		logContainer.repaint();
	}

	// Read-only word-wrapping JTextArea styled to look like a label
	private static JTextArea makeTextArea(String text, Font font, Color fg, Color bg) {
		JTextArea area = new JTextArea(text);
		area.setFont(font);
		area.setForeground(fg);
		area.setBackground(bg);
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setEditable(false);
		area.setFocusable(false);
		area.setOpaque(true);
		area.setBorder(BorderFactory.createEmptyBorder());
		area.setHighlighter(null);
		return area;
	}

	// Convenience wrappers
	public void log       (String m) { appendLog(m, "default"); }
	public void logInfo   (String m) { appendLog(m, "info");    }
	public void logSuccess(String m) { appendLog(m, "success"); }
	public void logError  (String m) {
		// Show error centered in the panel (not in the scrollable log)
		errorTextLabel.setText(m);
		showCard("error");
	}
	public void logWarn   (String m) { appendLog(m, "warn");    }

	// Clear button — drop logs, hide summary + status bar, return to placeholder
	public void clearLog() {
		resetExecutionState();
		hideTimeElapsed();
		hideStatusBar();
		showCard("empty");
		clearBtn.setEnabled(false);
		clearBtn.setIcon(EDCMainFrame.clear(13, BORDER_COLOR));
		styleOutlined(clearBtn, BG_CARD, TEXT_SECONDARY_COLOR);
		reportBtn.setEnabled(false);
		styleFilled(reportBtn, LIGHT_BLUE, Color.WHITE);
	}

	// ── Internals ───────────────────────────────────────────────────────────

	private void onRunOrStopClicked() {
		if (running) { if (onStopTests != null) onStopTests.run(); }
		else         { if (onRunTests  != null) onRunTests .run(); }
	}

	private void startElapsedTimer() { elapsedTimer.restart(); updateElapsed(); }
	private void stopElapsedTimer () { if (elapsedTimer.isRunning()) elapsedTimer.stop(); }

	private void updateElapsed() {
		long secs = (System.currentTimeMillis() - startMs) / 1000;
		long h = secs / 3600, m = (secs % 3600) / 60, s = secs % 60;
		elapsedLabel.setText(String.format("%02d:%02d:%02d", h, m, s));
	}

	private void updateProgressText() {
		int done = passed + failed + skipped;
		int pct  = (total > 0) ? Math.min(done * 100 / total, 100) : 0;
		progressBar.setValue(pct);
		progressBar.setStringPainted(true);
		progressBar.setString(pct + "%");
		progressText.setText(pct + "%");
	}

	private void showCard(String name) {
		if (bodyCard != null && bodyCard.getLayout() instanceof CardLayout) {
			((CardLayout) bodyCard.getLayout()).show(bodyCard, name);
		}
	}

	private static String currentTimestamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	// ── Small UI helpers ────────────────────────────────────────────────────

	private static JLabel centered(String text, Font f, Color fg) {
		JLabel l = new JLabel(text);
		l.setFont(f); l.setForeground(fg);
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		return l;
	}

	private static JLabel statValue(Color c) {
		JLabel l = new JLabel("0", SwingConstants.CENTER);
		l.setFont(FONT_TESTCASECOUNT);
		l.setForeground(c);
		return l;
	}

	//passed,failed,skipped counter panel
	private static JPanel makeCounterPanel(String name, JLabel value) {
		JPanel p = new JPanel(new BorderLayout());
		p.setOpaque(false);

		// Add colored icon next to the label for Passed/Failed/Skipped
		JLabel lbl = new JLabel(name, SwingConstants.CENTER);
		lbl.setFont(FONT_TESTCASECOUNT_LBL);
		lbl.setForeground(TEXT_BLACKHEADING_COLOR);

		switch (name) {
			case "Passed":
				lbl.setForeground(SUCCESS_GREEN);
				lbl.setIcon(EDCMainFrame.passed(16, SUCCESS_GREEN));
				break;
			case "Failed":
				lbl.setForeground(FAIL_RED);
				lbl.setIcon(EDCMainFrame.failed(16, FAIL_RED));
				break;
			case "Skipped":
				lbl.setForeground(WARN_ORANGE);
				lbl.setIcon(EDCMainFrame.skipped(16, WARN_ORANGE));
				break;
		}

		p.add(value, BorderLayout.CENTER);
		p.add(lbl,   BorderLayout.SOUTH);
		return p;
	}

	private JButton makeButton(String text, Icon icon) {
		JButton b = new JButton(text, icon);
		b.setFont(FONT_BTN);
		// Lock all three size hints so layout managers never resize the buttons
		b.setPreferredSize(BTN_SIZE);
		b.setMinimumSize  (BTN_SIZE);
		b.setMaximumSize  (BTN_SIZE);
		b.setIconTextGap(4);
		b.setFocusPainted(false);
		b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		b.setBorderPainted(false);
		b.setOpaque(true);
		b.setHorizontalAlignment(SwingConstants.CENTER);
		return b;
	}

	private static void styleFilled(JButton b, Color bg, Color fg) {
		b.setBackground(bg);
		b.setForeground(fg);
		b.setOpaque(true);
		b.setBorderPainted(false);
		b.setBorder(BorderFactory.createEmptyBorder(3, 6, 3, 6));
	}

	private static void styleOutlined(JButton b, Color bg, Color fg) {
		b.setBackground(bg);
		b.setForeground(fg);
		b.setOpaque(true);
		b.setBorderPainted(true);
		b.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(BORDER_COLOR, 1),
				BorderFactory.createEmptyBorder(3, 6, 3, 6)));
	}
}
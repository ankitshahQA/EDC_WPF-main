package com.automation.testcases;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*from w ww.j a  v  a2  s  .  c o  m*/
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class DialogControl extends ConfigControl3 {

	// private JDialog dialog = new JDialog();
	private JFrame dialog = new JFrame();
	private JLabel lb, lb1;
	protected Button Btn, Btn1;
	protected TextArea txtArea;
	protected JTextPane txtPane;
	protected JProgressBar progressBar;
	protected JScrollPane jsp;
	private JTabbedPane tabbedPane11 = new JTabbedPane();
	private JPanel panel2 = new JPanel();;
	private SpringLayout layout = new SpringLayout();
	// private static long startTime = 0;
	long startTime = System.currentTimeMillis();
	long nowTime = System.currentTimeMillis();
	long elapsedTime = 0;

	public DialogControl() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		Btn = new Button("Stop Test");
		Btn.setBackground(Color.cyan);
		Btn.setFont(new Font("Verdana", Font.PLAIN, 16));
		Btn1 = new Button("Close Tool");
		Btn1.setBackground(Color.cyan);
		Btn1.setFont(new Font("Verdana", Font.PLAIN, 16));
		lb = new JLabel();
		lb1 = new JLabel();
		int delay = 1000; // milliseconds

		// dialog.setForeground(Color.DARK_GRAY);
		// dialog.setBackground(Color.DARK_GRAY);
		javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");

		progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 500);
		panel2.setLayout(layout);
		panel2.setBackground(Color.GRAY);
		lb.setText("<HTML><U>Reports</U></HTML>");
		// addsite.setBorderPainted(false);
		lb.setOpaque(false);
		lb.setBackground(Color.GRAY);
		lb.setFont(new Font("Verdana", Font.PLAIN, 14));
		lb1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lb.setHorizontalAlignment(SwingConstants.LEFT);

		progressBar.setStringPainted(true);
		progressBar.setPreferredSize(new Dimension(200, 100));
		progressBar.setValue(0);
		txtArea = new TextArea();

		txtArea.setPreferredSize(new Dimension(300, 100));
		// txtArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtArea.setFont(new Font("courier new", Font.PLAIN, 14));
		// txtArea.setLineWrap(true);
		txtArea.setEditable(false);
		// txtArea.setText("INFO:Tool is running");
		txtArea.setText("INFO: Enterprise Installation Test started.........");
		txtPane = new JTextPane();
		txtPane.setPreferredSize(new Dimension(400, 200));
		txtPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtPane.setFont(new Font("courier new", Font.PLAIN, 14));
		DefaultCaret caret = (DefaultCaret) txtPane.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		txtPane.setEditable(false);

		txtPane.setText("Enterprise Installation Sanity started.........");
		jsp = new JScrollPane(txtPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// jsp.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		// jsp.setSize(120, 300);
		layout.getConstraints(lb).setHeight(Spring.constant(30));
		// layout.getConstraints(lb).setWidth(Spring.constant(200));
		layout.getConstraints(lb1).setHeight(Spring.constant(30));
		// layout.getConstraints(lb1).setWidth(Spring.constant(200));
		layout.getConstraints(progressBar).setHeight(Spring.constant(25));
		layout.getConstraints(progressBar).setWidth(Spring.constant(400));
		layout.getConstraints(Btn).setHeight(Spring.constant(30));
		layout.getConstraints(Btn).setWidth(Spring.constant(85));
		layout.getConstraints(Btn1).setHeight(Spring.constant(30));
		layout.getConstraints(Btn1).setWidth(Spring.constant(85));
		layout.getConstraints(txtArea).setHeight(Spring.constant(120));
		layout.getConstraints(txtArea).setWidth(Spring.constant(300));
		layout.getConstraints(txtPane).setHeight(Spring.constant(220));
		layout.getConstraints(txtPane).setWidth(Spring.constant(400));
		layout.getConstraints(jsp).setHeight(Spring.constant(220));
		layout.getConstraints(jsp).setWidth(Spring.constant(400));
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		layout.putConstraint(SpringLayout.WEST, lb, 355, SpringLayout.WEST, panel2);
		layout.putConstraint(SpringLayout.NORTH, lb, 5, SpringLayout.NORTH, panel2);
		layout.putConstraint(SpringLayout.WEST, lb1, 15, SpringLayout.WEST, panel2);
		layout.putConstraint(SpringLayout.NORTH, lb1, 5, SpringLayout.NORTH, panel2);
		layout.putConstraint(SpringLayout.WEST, txtArea, 20, SpringLayout.WEST, panel2);
		layout.putConstraint(SpringLayout.NORTH, txtArea, 5, SpringLayout.SOUTH, progressBar);
		layout.putConstraint(SpringLayout.WEST, jsp, 20, SpringLayout.WEST, panel2);
		layout.putConstraint(SpringLayout.NORTH, jsp, 5, SpringLayout.SOUTH, progressBar);
		layout.putConstraint(SpringLayout.WEST, progressBar, 20, SpringLayout.WEST, panel2);
		layout.putConstraint(SpringLayout.NORTH, progressBar, 10, SpringLayout.SOUTH, lb);
		layout.putConstraint(SpringLayout.WEST, Btn, 180, SpringLayout.WEST, panel2);
		layout.putConstraint(SpringLayout.NORTH, Btn, 10, SpringLayout.SOUTH, jsp);
		layout.putConstraint(SpringLayout.WEST, Btn1, 180, SpringLayout.WEST, panel2);
		layout.putConstraint(SpringLayout.NORTH, Btn1, 10, SpringLayout.SOUTH, jsp);
		// panel2.add(txtArea);
		panel2.add(jsp, BorderLayout.CENTER);
		panel2.add(progressBar);
		panel2.add(Btn);
		panel2.add(lb);
		panel2.add(lb1);
		lb.setEnabled(false);
		dialog.add(panel2);
		dialog.pack();
		dialog.setLocation(900, 350);
		dialog.setSize(450, 380);
		dialog.setTitle("Enterprise Installation Confidence Test Tool #1.11");
		dialog.setVisible(true);
		dialog.setResizable(false);
		Btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				stop = true;
				if (driver != null) {

					String txt = "\n" + "INFO: Closing Browser,Please wait....";
					MutableAttributeSet attributes = new SimpleAttributeSet(txtPane.getInputAttributes());
					StyleConstants.setForeground(attributes, Color.blue);
					try {
						txtPane.getStyledDocument().insertString(txtPane.getDocument().getLength(), txt, attributes);
					} catch (BadLocationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {

						driver.close();
						driver.quit();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				panel2.remove(Btn);
				panel2.add(Btn1);
				panel2.validate();
				panel2.repaint();
				// dialog.dispose();

			}
		});
		Btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				stop = true;
				String txt = "\n" + "INFO: Closing Tool,Please wait....";
				MutableAttributeSet attributes = new SimpleAttributeSet(txtPane.getInputAttributes());
				StyleConstants.setForeground(attributes, Color.blue);

				try {
					txtPane.getStyledDocument().insertString(txtPane.getDocument().getLength(), txt, attributes);
				} catch (BadLocationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/*
				 * try { driver.close(); driver.quit(); } catch (Exception e1) { // TODO
				 * Auto-generated catch block e1.printStackTrace(); }
				 */
				dialog.dispose();

			}
		});
		lb.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent pre) {

				try {

					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + extentReportFilePath);
					// Runtime.getRuntime().exec("explorer.exe
					// "+extentReportFilePath_BrowserNameOnly);
				}

				catch (Exception e) {

					e.printStackTrace();

				}

			}

			public void mouseEntered(MouseEvent ent) {

				lb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			}

			public void mouseExited(MouseEvent exi) {

				lb.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

			}

		});
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				nowTime = System.currentTimeMillis();
				elapsedTime = nowTime - startTime;
				// DurationFormatUtils.formatDuration(elapsedTime, "HH:mm:ss.S");
				lb1.setText(org.apache.commons.lang3.time.DurationFormatUtils.formatDuration(elapsedTime, "HH:mm:ss")
						+ " Time Elapsed");
			}
		};
		new Timer(delay, taskPerformer).start();
	}

	public void close() {
		String txt = "\n" + "INFO: Closing Browser,Please wait....";
		MutableAttributeSet attributes = new SimpleAttributeSet(txtPane.getInputAttributes());
		StyleConstants.setForeground(attributes, Color.BLUE);

		try {
			txtPane.getStyledDocument().insertString(txtPane.getDocument().getLength(), txt, attributes);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			driver.close();
			driver.quit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel2.remove(Btn);
		panel2.add(Btn1);
		panel2.validate();
		panel2.repaint();
		txt = "\n" + "INFO: Test Completed, Please see reports...";

		try {
			txtPane.getStyledDocument().insertString(txtPane.getDocument().getLength(), txt, attributes);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setButton() {

		panel2.add(Btn);
		panel2.validate();
		panel2.repaint();

	}

	public void setreport() {
		lb.setEnabled(true);
	}

	public void setText(String txt) {
		// txtArea.append("\n"+"INFO: "+txt);
		txt = "\n" + "INFO: " + txt;
		MutableAttributeSet attributes = new SimpleAttributeSet(txtPane.getInputAttributes());
		StyleConstants.setForeground(attributes, Color.BLACK);
		// StyleConstants.setBackground(attributes, Color.RED);

		try {
			txtPane.getStyledDocument().insertString(txtPane.getDocument().getLength(), txt, attributes);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setTextRed(String txt) {
		// txtArea.append("\n"+"INFO: "+txt);
		txt = "\n" + "INFO: " + txt;
		MutableAttributeSet attributes = new SimpleAttributeSet(txtPane.getInputAttributes());
		StyleConstants.setForeground(attributes, Color.RED);
		// StyleConstants.setBackground(attributes, Color.RED);

		try {
			txtPane.getStyledDocument().insertString(txtPane.getDocument().getLength(), txt, attributes);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setTextOrange(String txt) {
		// txtArea.append("\n"+"INFO: "+txt);
		txt = "\n" + "INFO: " + txt;
		MutableAttributeSet attributes = new SimpleAttributeSet(txtPane.getInputAttributes());
		StyleConstants.setForeground(attributes, Color.ORANGE);
		// StyleConstants.setBackground(attributes, Color.RED);

		try {
			txtPane.getStyledDocument().insertString(txtPane.getDocument().getLength(), txt, attributes);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setTextGreen(String txt) {
		// txtArea.append("\n"+"INFO: "+txt);
		txt = "\n" + "INFO: " + txt;
		MutableAttributeSet attributes = new SimpleAttributeSet(txtPane.getInputAttributes());
		StyleConstants.setForeground(attributes, Color.BLUE);
		// StyleConstants.setBackground(attributes, Color.RED);

		try {
			txtPane.getStyledDocument().insertString(txtPane.getDocument().getLength(), txt, attributes);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setValue(int n) {
		progressBar.setValue(n);
		// txtArea.setText(n+"% Completed.");

	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					cd1 = new DialogControl();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// cd1.setValue(50);
				// Graphics g = cd1.getGraphics();
				// cd1.doDrawing(g);
			}
		});
	}
}
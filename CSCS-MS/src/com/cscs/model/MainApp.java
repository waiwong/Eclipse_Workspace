package com.cscs.model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cscs.model.template.*;
import com.cscs.model.util.MyMethod;

public class MainApp extends JPanel {
	private static final long serialVersionUID = 1L;
	public static JTextArea msgArea;

	public MainApp() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"resources/applicationContext.xml");
		MyMethod myMethod = new MyMethod();
		setLayout(new BorderLayout());
		JTabbedPane tabbedPane = new JTabbedPane();
		JComponent panel1 = new DBOperation(context);
		tabbedPane.addTab("DB Operation", null, panel1, "Database Operation");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = new GenReport(context);
		tabbedPane.addTab("Gen Report", null, panel2, "Generate Report");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

		JComponent panel3 = new Comparator();
		tabbedPane.addTab("Data Comparator", null, panel3, "Excel Data Comparator");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		msgArea = new JTextArea(4, 35);
		msgArea.setBackground(new Color(215, 215, 225));
		msgArea.setEditable(false);
		msgArea.setFont(myMethod.setMyFont());
		JScrollPane msgPanel = new JScrollPane(msgArea);
		msgPanel.setBorder(new TitledBorder("System Message"));

		add(tabbedPane, BorderLayout.CENTER);
		add(msgPanel, BorderLayout.SOUTH);
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("CSCS - Manage System v1.1");
		int screenW = frame.getToolkit().getScreenSize().width, screenH = frame
				.getToolkit().getScreenSize().height;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MainApp(), BorderLayout.CENTER);
		frame.setSize(700, 650);
		frame.setLocation(screenW / 2 - frame.getWidth(),
				screenH / 2 - frame.getHeight() / 2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					createAndShowGUI();
				} catch (Exception e) {
				}
			}
		});
	}

}
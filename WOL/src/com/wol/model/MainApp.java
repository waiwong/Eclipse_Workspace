package com.wol.model;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.wol.model.template.*;

public class MainApp {

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("WOL System v1.2");
		int screenW = frame.getToolkit().getScreenSize().width, screenH = frame
				.getToolkit().getScreenSize().height;
		frame.add(new ConsolePanel(), BorderLayout.CENTER);
		frame.setPreferredSize(new Dimension(800, 640));
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
					UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
							"Calibri", Font.BOLD, 18)));
					createAndShowGUI();
				} catch (Exception e) {
				}
			}
		});
	}

}
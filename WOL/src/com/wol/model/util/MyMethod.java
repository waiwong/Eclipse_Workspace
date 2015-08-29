package com.wol.model.util;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyMethod {
	public Object[][] objArray;

	public void scrollToBottom(JTextArea textArea) {
		textArea.selectAll();
		int x = textArea.getSelectionEnd();
		textArea.select(x, x);
	}

	public Font setMyFont() {
		return new Font("Calibri", Font.BOLD, 18);
	}

	public void setMyLabel(String str, JPanel panel) {
		JLabel label = new JLabel(str + " : ", JLabel.RIGHT);
		label.setFont(setMyFont());
		panel.add(label);
	}

	public void setMyTextField(JTextField field, int count, JPanel panel) {
		field.setFont(setMyFont());
		field.setText(String.valueOf(count));
		field.setHorizontalAlignment(JTextField.RIGHT);
		panel.add(field);
	}

	public void setMyButton(JButton btn, JPanel panel) {
		btn.setPreferredSize(new Dimension(180, 28));
		btn.setFont(setMyFont());
		panel.add(btn);
	}

}
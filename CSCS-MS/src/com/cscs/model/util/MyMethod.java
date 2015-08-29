package com.cscs.model.util;

import static ch.lambdaj.Lambda.having;
import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.selectFirst;
import static org.hamcrest.Matchers.equalTo;

import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.cscs.model.MainApp;
import com.cscs.model.bo.Department;

public class MyMethod {

	public Font setMyFont() {
		return new Font("Calibri", Font.BOLD, 18);
	}

	public void setMyLabel(JPanel panel, String str, boolean isParagraph) {
		JLabel label = new JLabel(str + " :");
		label.setFont(setMyFont());
		if (isParagraph)
			panel.add(label, ParagraphLayout.NEW_PARAGRAPH);
		else
			panel.add(label);
	}

	public void setMyButton(JPanel panel, JButton btn) {
		btn.setPreferredSize(new Dimension(100, 28));
		btn.setFont(setMyFont());
		panel.add(btn);
	}

	public void setMyTextField(JPanel panel, JTextField textField, String str,
			boolean isRight) {
		textField.setFont(setMyFont());
		textField.setText(str);
		if (isRight)
			textField.setHorizontalAlignment(JTextField.RIGHT);
		panel.add(textField);
	}

	public void showMsg(String msg) {
		MainApp.msgArea.append(msg);
		MainApp.msgArea.selectAll();
		int x = MainApp.msgArea.getSelectionEnd();
		MainApp.msgArea.select(x, x);
	}

	public Department deptConfig(String deptName, List<Department> deptList) {
		Department department = null;
		if (deptName.contains("-")) {
			int lastIndex = deptName.lastIndexOf("-");
			deptName = deptName.substring(0, lastIndex);
		}
		for (Department dept : deptList) {
			String name = dept.getName(), alias = dept.getAlias();
			if (deptName.equals(name) || deptName.equals(alias))
				department = dept;
		}
		if (department == null)
			department = selectFirst(deptList,
					having(on(Department.class).getName(), equalTo("ITD")));
		return department;
	}

}
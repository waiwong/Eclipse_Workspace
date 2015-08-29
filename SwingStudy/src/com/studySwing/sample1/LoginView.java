package com.studySwing.sample1;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame mainFrm = new JFrame("Demo Study Application");
		mainFrm.setSize(300, 150);
		mainFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SetFrmCtrl(mainFrm);
		mainFrm.setVisible(true);
	}

	private static void SetFrmCtrl(JFrame frm) {
		frm.setLayout(null);

		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		frm.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		frm.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		frm.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		frm.add(passwordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(10, 80, 80, 25);
		frm.add(loginButton);

		JButton registerButton = new JButton("register");
		registerButton.setBounds(180, 80, 80, 25);
		frm.add(registerButton);

		ActionListener loginButtonListener = new BtnListener();
		loginButton.addActionListener(loginButtonListener);
	}
}

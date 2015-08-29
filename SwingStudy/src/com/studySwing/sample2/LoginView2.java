package com.studySwing.sample2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginView2 {
	private final static Logger logger = LogManager.getLogger("LoginView2");

	public static void main(String[] args) {
		JFrame mainFrm = new JFrame("Demo Study Application2");
		mainFrm.setSize(300, 300);
		mainFrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// // Use JFrame
		// SetFrmCtrl(mainFrm);

		// KeyboardExample keyboardEx = new KeyboardExample();
		// keyboardEx.setBounds(10, 110, 160, 50);
		// keyboardEx.setBackground(Color.RED);
		// mainFrm.add(keyboardEx);
		// System.out.println("keyboardEx.hasFocus():"
		// + String.valueOf(keyboardEx.hasFocus()));
		// if (!keyboardEx.hasFocus())
		// keyboardEx.setFocusable(true);
		// System.out.println("keyboardEx.hasFocus():"
		// + String.valueOf(keyboardEx.hasFocus()));

		// //Use JPanel
		// JPanel panel = new JPanel();
		// mainFrm.add(panel);
		// SetPanelCtrl(panel);
		// // Use KeyboardExample
		KeyboardExample keyboardEx = new KeyboardExample();
		mainFrm.add(keyboardEx);
		SetPanelCtrl(keyboardEx);
		// KeyListener listener = new MyKeyListener();
		// mainFrm.addKeyListener(listener);
		mainFrm.setVisible(true);

		logger.entry();
		logger.info("Hello, World!");
		logger.error("Hello, World!");
		logger.exit();
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

		ActionListener btnListener2 = new BtnListener2();
		loginButton.addActionListener(btnListener2);
		registerButton.addActionListener(btnListener2);
	}

	private static void SetPanelCtrl(KeyboardExample pnl) {
		pnl.setLayout(null);

		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		pnl.add(userLabel);

		JTextField userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		pnl.add(userText);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		pnl.add(passwordLabel);

		JPasswordField passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		pnl.add(passwordText);

		JButton loginButton = new JButton("login");
		loginButton.setBounds(10, 80, 80, 25);
		pnl.add(loginButton);

		JButton registerButton = new JButton("register");
		registerButton.setBounds(180, 80, 80, 25);
		pnl.add(registerButton);

		JButton calculatorBtn = new JButton("calculator");
		calculatorBtn.setBounds(10, 110, 80, 25);
		pnl.add(calculatorBtn);

		calculatorBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String n1 = JOptionPane.showInputDialog(null,
						"First number to add");
				while (!isNumber(n1)) {
					n1 = JOptionPane
							.showInputDialog(null,
									"Invalid first number. Please insert another number");
				}
				String n2 = JOptionPane.showInputDialog(null,
						"Second number to add");
				while (!isNumber(n2)) {
					n2 = JOptionPane
							.showInputDialog(null,
									"Invalid second number. Please insert another number");

				}

				int r = new Integer(n1) + new Integer(n2);
				JOptionPane.showMessageDialog(null,
						"The result of the addition of: " + n1 + " and: " + n2
						+ " is " + r);
			}
		});

		ActionListener btnListener2 = new BtnListener2();
		loginButton.addActionListener(btnListener2);
		registerButton.addActionListener(btnListener2);
	}

	private static boolean isNumber(String n) {
		try {
			Integer.parseInt(n);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}

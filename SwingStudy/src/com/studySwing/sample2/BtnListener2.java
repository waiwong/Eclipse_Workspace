package com.studySwing.sample2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class BtnListener2 implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		JButton source = (JButton) arg0.getSource();
		JOptionPane.showMessageDialog(source, source.getText()
				+ " button has been pressed");
	}

}

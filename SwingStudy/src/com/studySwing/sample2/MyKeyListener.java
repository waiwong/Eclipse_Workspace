package com.studySwing.sample2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author B1340 Listener
 */
public class MyKeyListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

		System.out.println("keyPressed="
				+ KeyEvent.getKeyText(arg0.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("keyReleased="
				+ KeyEvent.getKeyText(arg0.getKeyCode()));

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}

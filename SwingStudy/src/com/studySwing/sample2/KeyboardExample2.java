/**
 *
 */
package com.studySwing.sample2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

/**
 * @author B1340
 *
 */
@SuppressWarnings("serial")
public class KeyboardExample2 extends JPanel {
	@SuppressWarnings("javadoc")
	public KeyboardExample2() {
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
	}

	/**
	 * @author B1340 Listener
	 */
	public class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("keyPressed="
					+ KeyEvent.getKeyText(e.getKeyCode()));
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("keyReleased="
					+ KeyEvent.getKeyText(e.getKeyCode()));
		}
	}
}

package org.codapli.utn;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class JLabelBlinking extends JLabel {
	private static final long serialVersionUID = 1L;

	private static final int BLINKING_RATE = 1000; // in ms

	private boolean blinkingOn = true;
	private Timer timer;

	public JLabelBlinking(String text) {
		super(text);
		timer = new Timer(BLINKING_RATE, new TimerListener(this));

	}

	public void startBlink() {
		timer.setInitialDelay(0);
		timer.start();
	}

	public void stopBlink() {
		timer.stop();
	}

	public void setBlinking(boolean flag) {
		this.blinkingOn = flag;
	}

	public boolean getBlinking(boolean flag) {
		return this.blinkingOn;
	}

	private class TimerListener implements ActionListener {
		private JLabelBlinking bl;
		private Color bg;
		private Color fg;
		private boolean isForeground = true;

		public TimerListener(JLabelBlinking bl) {
			this.bl = bl;
			fg = bl.getForeground();
			bg = bl.getBackground();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (bl.blinkingOn) {
				if (isForeground) {
					bl.setForeground(fg);
				} else {
					bl.setForeground(bg);
				}
				isForeground = !isForeground;
			} else {
				if (isForeground) {
					bl.setForeground(fg);
					isForeground = false;
				}
			}
		}

	}

}

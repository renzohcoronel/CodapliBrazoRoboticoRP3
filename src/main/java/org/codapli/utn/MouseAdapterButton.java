package org.codapli.utn;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Timer;

public class MouseAdapterButton extends MouseAdapter {

	private Timer timer;

	public MouseAdapterButton(ActionListener action, Integer delta) {
		timer = new Timer(delta, action);
		timer.setRepeats(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		timer.restart();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		timer.stop();
	}
}

package pl.edu.pw.elka.mmarkiew.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import pl.edu.pw.elka.mmarkiew.controller.Controller;
import pl.edu.pw.elka.mmarkiew.controller.ViewEventQueue;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.ViewKeyPress;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.ViewEvent;

public class MovementListener implements KeyListener, ViewEvent {
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		try {
			ViewEventQueue.getInstance().put(new ViewKeyPress(e.getKeyCode(), true));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		try {
			ViewEventQueue.getInstance().put(new ViewKeyPress(e.getKeyCode(), false));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}

package pl.edu.pw.elka.mmarkiew.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.ViewKeyPress;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.ViewKeyPress.Keys;

/**
 * KeyListener adapter<br>
 * Sends proper events into queue
 * @author Acer
 *
 */
public class KeybordListener implements KeyListener {
	
	@Override
	public void keyPressed(KeyEvent e) {
		try {
			View.blockingQueue.put(new ViewKeyPress(chooseEvent(e), true));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		try {
			View.blockingQueue.put(new ViewKeyPress(chooseEvent(e), false));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	private Keys chooseEvent(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:	return Keys.UP;
			case KeyEvent.VK_DOWN:	return Keys.DOWN;
			case KeyEvent.VK_LEFT:	return Keys.LEFT;
			case KeyEvent.VK_RIGHT:	return Keys.RIGHT;
			case KeyEvent.VK_SPACE:	return Keys.PLANT;
			case KeyEvent.VK_1:		return Keys.TIMER_1;
			case KeyEvent.VK_2:		return Keys.TIMER_2;
			case KeyEvent.VK_3:		return Keys.TIMER_3;
			case KeyEvent.VK_N:		return Keys.NEW_GAME;
			case KeyEvent.VK_P:		return Keys.PAUSE;
			case KeyEvent.VK_B:		return Keys.BACKGROUND_MUSIC;
			case KeyEvent.VK_S:		return Keys.SOUND_EFFECTS;
			case KeyEvent.VK_ESCAPE:	return Keys.EXIT;
			default:				return Keys.UNDEFINED;
		}
	}
}

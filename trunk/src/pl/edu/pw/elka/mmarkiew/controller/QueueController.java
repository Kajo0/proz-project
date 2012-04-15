package pl.edu.pw.elka.mmarkiew.controller;

import java.awt.event.KeyEvent;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.GamePlayEvent;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.ViewEvent;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.ViewKeyPress;
import pl.edu.pw.elka.mmarkiew.model.Model;
import pl.edu.pw.elka.mmarkiew.model.SoundManager;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * To control events from view
 * @author Acer
 *
 */
public class QueueController implements Runnable {
	private final Model model;
	
	/**
	 * 
	 * @param model - Game model
	 */
	public QueueController(final Model model) {
		this.model = model;
	}

	/**
	 * Waiting for event pushed into BlockingQueue
	 * If there is sth, calls helper function 
	 */
	@Override
	public void run() {
		ViewEvent event = null;
		
		while (true) {
			try {
				if ((event = ViewEventQueue.getInstance().take()) != null) {
					checkInput(event);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Check what is that event, and invoke some actions<br>
	 * depended on event. Almost all events change player state.
	 * @param event
	 */
	private void checkInput(final ViewEvent event) {
		Player player = model.getPlayer();
		boolean xVelocity = (player.getXVelocity() != 0);
		boolean yVelocity = (player.getYVelocity() != 0);
		
		try {
			if (event instanceof ViewKeyPress) {
				int code = ((ViewKeyPress) event).getKeyCode();
				boolean pressed = ((ViewKeyPress) event).isPress();
				switch (code) {
					case KeyEvent.VK_UP: if (pressed && !yVelocity)
											player.setYVelocity(-player.getMaxVelocity());
										else if (!pressed && yVelocity)
											player.setYVelocity(0);
										break;
					case KeyEvent.VK_DOWN: if (pressed && !yVelocity)
												player.setYVelocity(player.getMaxVelocity());
											else if (!pressed && yVelocity)
												player.setYVelocity(0);
											break;
					case KeyEvent.VK_LEFT: if (pressed && !xVelocity)
												player.setXVelocity(-player.getMaxVelocity());
											else if (!pressed && xVelocity)
												player.setXVelocity(0);
											break;
					case KeyEvent.VK_RIGHT: if (pressed && !xVelocity)
												player.setXVelocity(player.getMaxVelocity());
											else if (!pressed && xVelocity)
												player.setXVelocity(0);
											break;
					case KeyEvent.VK_SPACE: if (pressed)
												model.plantBomb();
											break;
					case KeyEvent.VK_1:
					case KeyEvent.VK_2:
					case KeyEvent.VK_3:	if (pressed)
											player.setBombTimer((code - 48) * 1000);
										break;
					case KeyEvent.VK_P: if (pressed)
											model.switchPause();
										break;
					case KeyEvent.VK_B: if (pressed)
											SoundManager.switchBackgroundMusicEnable();
										break;
					case KeyEvent.VK_S: if (pressed)
											SoundManager.switchSoundEffectsEnable();
										break;
					case KeyEvent.VK_N: if (pressed && model.getStatistics().getTimer() == 0)
											model.newGame();
										break;
					case KeyEvent.VK_ESCAPE: System.exit(0);//TODO Wywalic escape sysexit
				}
			} else if (event instanceof GamePlayEvent) {
				
				switch (((GamePlayEvent) event).getCode()) {
					case NEW_GAME: model.newGame();
									break;
					case SWITCH_BACKGROUND_MUSIC: SoundManager.switchBackgroundMusicEnable();
									break;
					case SWITCH_SOUND_EFFECTS: SoundManager.switchSoundEffectsEnable();
									break;
				}
			}
		} catch (NullPointerException e) {
			//There was no player/model -> do nothing 
		}
	}
	
}

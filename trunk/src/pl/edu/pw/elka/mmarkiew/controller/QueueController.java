package pl.edu.pw.elka.mmarkiew.controller;

import java.awt.event.KeyEvent;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.ViewEvent;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.ViewKeyPress;
import pl.edu.pw.elka.mmarkiew.model.Model;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

public class QueueController implements Runnable {
	private Model model;
	
	public QueueController(Model model) {
		this.model = model;
	}
	
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

	private void checkInput(ViewEvent event) {
		Player player = model.getPlayer();
		
		// TODO zmienic na gameaction
		try {
			if (event instanceof ViewKeyPress) {
				int code = ((ViewKeyPress) event).getKeyCode();
				boolean pressed = ((ViewKeyPress) event).isPress();
				switch (code) {
					case KeyEvent.VK_UP: if (pressed)
											player.setYVelocity(-player.getMaxVelocity());
										else player.setYVelocity(0);
										break;
					case KeyEvent.VK_DOWN: if (pressed)
												player.setYVelocity(player.getMaxVelocity());
											else player.setYVelocity(0);
											break;
					case KeyEvent.VK_LEFT: if (pressed)
												player.setXVelocity(-player.getMaxVelocity());
											else player.setXVelocity(0);
											break;
					case KeyEvent.VK_RIGHT: if (pressed)
												player.setXVelocity(player.getMaxVelocity());
											else player.setXVelocity(0);
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
					case KeyEvent.VK_ESCAPE: System.exit(0);//TODO Wywalic escape sysexit
				}
			}
		} catch (NullPointerException e) {
			//TODO wywalic sysouta
			System.out.println("nie bylo playera przy key evencie");
		}
	}

}

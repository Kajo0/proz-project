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
				if (((ViewKeyPress) event).getKeyCode() == KeyEvent.VK_UP) {
					if (((ViewKeyPress) event).isPress())
						player.setYVelocity(-player.getMaxVelocity());
						else player.setYVelocity(0);
				}
				if (((ViewKeyPress) event).getKeyCode() == KeyEvent.VK_DOWN) {
					if (((ViewKeyPress) event).isPress())
						player.setYVelocity(player.getMaxVelocity());
					else player.setYVelocity(0);
				}
				if (((ViewKeyPress) event).getKeyCode() == KeyEvent.VK_LEFT) {
					if (((ViewKeyPress) event).isPress())
						player.setXVelocity(-player.getMaxVelocity());
					else player.setXVelocity(0);
				}
				if (((ViewKeyPress) event).getKeyCode() == KeyEvent.VK_RIGHT) {
					if (((ViewKeyPress) event).isPress())
						player.setXVelocity(player.getMaxVelocity());
					else player.setXVelocity(0);
				}
				if (((ViewKeyPress) event).getKeyCode() == KeyEvent.VK_SPACE) {
					if (((ViewKeyPress) event).isPress()) {
						model.plantBomb();
					}
				}
				if (((ViewKeyPress) event).getKeyCode() == KeyEvent.VK_1) {
					if (((ViewKeyPress) event).isPress()) {
						model.getPlayer().setBombTimer(1000);
					}
				}
				if (((ViewKeyPress) event).getKeyCode() == KeyEvent.VK_2) {
					if (((ViewKeyPress) event).isPress()) {
						model.getPlayer().setBombTimer(2000);
					}
				}
				if (((ViewKeyPress) event).getKeyCode() == KeyEvent.VK_3) {
					if (((ViewKeyPress) event).isPress()) {
						model.getPlayer().setBombTimer(3000);
					}
				}
			}
		} catch (NullPointerException e) {
			//TODO wywalic sysouta
			System.out.println("nie bylo playera przy key evencie");
		}
			
		if (((ViewKeyPress) event).getKeyCode() == KeyEvent.VK_P) {
			if (((ViewKeyPress) event).isPress())
				model.switchPause();
		}
		
		//TODO Wywalic escape sysexit
		if (((ViewKeyPress) event).getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

}

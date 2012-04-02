package pl.edu.pw.elka.mmarkiew.controller;

import pl.edu.pw.elka.mmarkiew.model.Model;
import pl.edu.pw.elka.mmarkiew.model.map.BlockElement;
import pl.edu.pw.elka.mmarkiew.model.map.BlockFactory;
import pl.edu.pw.elka.mmarkiew.model.map.GameBlocks;
import pl.edu.pw.elka.mmarkiew.view.View;

/**
 * @author Acer
 *
 */
public class Controller implements Runnable {
	private final Model model;
	private final View view;

	public Controller(final Model model, final View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public synchronized void run() {
//		while (true) {
//			try {
//				wait(2000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			
//			//view.drawGame(model.getMap(), model.getPlayer());
//		}
	}
}

package pl.edu.pw.elka.mmarkiew.controller;

import java.awt.Color;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.SwingUtilities;

import pl.edu.pw.elka.mmarkiew.model.ColorChange;
import pl.edu.pw.elka.mmarkiew.model.Model;
import pl.edu.pw.elka.mmarkiew.view.View;

public class Controller implements Runnable {
	public static BlockingQueue<ColorChange> queue;
	private Model model;
	private View view;
	
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		this.queue = new LinkedBlockingQueue<ColorChange>();
	}

	@Override
	public void run() {
		while (true) {
			ColorChange cc;
			try {
				if ((cc = queue.take()) != null) {
					sthInQueue(cc);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void sthInQueue(ColorChange cc) throws InterruptedException {
		System.out.println("cos jest");
		final Color col = model.calculate(cc);
		Thread.currentThread().sleep(50);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				view.changeSth(col);
			}
		});
	}
}

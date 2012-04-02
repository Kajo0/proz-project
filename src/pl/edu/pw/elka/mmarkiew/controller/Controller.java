package pl.edu.pw.elka.mmarkiew.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import pl.edu.pw.elka.mmarkiew.model.Model;
import pl.edu.pw.elka.mmarkiew.view.View;

public class Controller implements Runnable {
	public static final int GAME_X_SIZE;
	public static final int GAME_Y_SIZE;
	public static final int VIEW_WIDTH;
	public static final int VIEW_HEIGHT;
	
	static {
		GAME_X_SIZE = GAME_Y_SIZE = 600;
		VIEW_WIDTH = (int) (GAME_X_SIZE * 4/3);
		VIEW_HEIGHT = GAME_Y_SIZE;
	}
	
	
	private Model model;
	private View view;
	
	private Thread modelThread;
	private Thread viewThread;
	private Thread queueControl;
	
	
	public Controller() {
		this.model = new Model(GAME_X_SIZE, GAME_Y_SIZE);
		this.view = new View(VIEW_WIDTH, VIEW_HEIGHT);
		
		this.modelThread = new Thread(model);
		this.viewThread = new Thread(view);
		this.queueControl = new Thread(new QueueController(model));
		
		
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(modelThread);
		executor.execute(viewThread);
		executor.execute(queueControl);
		executor.shutdown();
		
		
	}
	
	@Override
	@SuppressWarnings("static-access")
	public void run() {
		while (true) {
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (model.getMap() != null)
				view.sendModel(model.getMap());
		}
	}

}

package pl.edu.pw.elka.mmarkiew.model;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import pl.edu.pw.elka.mmarkiew.controller.ViewEventQueue;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.ViewEvent;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.GameMap;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

public class Model implements Runnable {
	private int width;
	private int height;
	private long startTime;
	private GameMap map = null;
	private ResourceManager resource;
	
	public Model(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public synchronized void run() {
		init();
		gameLoop();
	}

	private void init() {
		resource = new ResourceManager();
		try {
			map = resource.loadMap("maps/1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void gameLoop() {
		long startTime = System.currentTimeMillis();
        long currTime = startTime;
        
		while (true) {
			long elapsedTime = System.currentTimeMillis() - currTime;
            currTime += elapsedTime;
			
            update(elapsedTime);
            
            try {
				wait(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void update(long elapsedTime) {
		map.getPlayer().update(elapsedTime);
	}

	public GameMap getMap() {
		if (map != null)
			return map.clone();
		return null;
	}

}

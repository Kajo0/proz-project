package pl.edu.pw.elka.mmarkiew.model;

import java.io.IOException;
import pl.edu.pw.elka.mmarkiew.model.entities.GameMap;

public class Model implements Runnable {
	private int width;
	private int height;
	private long startTime;
	private GameMap map = null;
	private ResourceManager resource;
	private CollisionDetector collisionDetector;
	
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
		this.resource = new ResourceManager();
		try {
			map = resource.loadMap("maps/1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.width = map.getWidth() * GameMap.BLLOCK_SIZE;
		this.height = map.getHeight() * GameMap.BLLOCK_SIZE;
		
		this.collisionDetector = new CollisionDetector(map);
	}

	private void gameLoop() {
		startTime = System.currentTimeMillis();
        long currTime = startTime;
        
		while (true) {
			long elapsedTime = System.currentTimeMillis() - currTime;
            currTime += elapsedTime;
			
            update(elapsedTime);
            
            try {
				wait(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void update(long elapsedTime) {
		map.getPlayer().update(elapsedTime);
		collisionDetector.checkCollision();
	}

	public GameMap getMap() {
		if (map != null)
			return map.clone();
		return null;
	}

}

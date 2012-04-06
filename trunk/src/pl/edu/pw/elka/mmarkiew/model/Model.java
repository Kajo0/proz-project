package pl.edu.pw.elka.mmarkiew.model;

import java.io.IOException;
import java.util.ArrayList;

import pl.edu.pw.elka.mmarkiew.model.entities.Bomb;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.GameMap;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

public class Model implements Runnable {
	private long startTime;
	private boolean paused;
	private GameMap map;
	private ResourceManager resource;
	private CollisionDetector collisionDetector;
	
	public Model() {
		this.startTime = -1;
		this.paused = false;
		this.map = null;
		this.resource = new ResourceManager();
		this.collisionDetector = null;
	}

	@Override
	public synchronized void run() {
		init();
		gameLoop();
	}

	//loads first map
	private void init() {
		try {
			this.map = resource.loadMap("maps/1.txt");
			this.startTime = 0;
			this.collisionDetector = new CollisionDetector(resource.getPlayer(), map);
		} catch (IOException e) {
			this.map = null;
			this.startTime = -1;
		}
	}

	private void gameLoop() {
		long currTime = this.startTime = System.currentTimeMillis();
		long elapsedTime;
		
		while (true) {
			elapsedTime = System.currentTimeMillis() - currTime;
            currTime += elapsedTime;
			
            if (!paused)
            	update(elapsedTime);
            
            try {
				wait(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void update(final long elapsedTime) {
		for (Entity e : map.getEntities())
			e.update(elapsedTime);
		
		if (collisionDetector != null)
			collisionDetector.detectCollision();
		
		//TODO zmienic na GAME OVER
		if (resource.getPlayer().getLifes() < 1)
			startTime = -1;
		
		calculateBombs();
	}

	public MapToDraw getMapToDraw() {
		if (map != null)
			return new MapToDraw(map.getBlockHolder(), map.getEntities(), map.getWidthBlocks(),
													map.getHeightBlocks(), isPaused(), isStarted());
		return new MapToDraw(true);
	}
	
	public boolean isStarted() {
		return (this.startTime < 0) ? false : true;
	}
	
	public boolean isPaused() {
		return this.paused;
	}
	
	public void switchPause() {
		this.paused = !this.paused;
	}

	public Player getPlayer() {
		if (resource != null)
			return resource.getPlayer();
		else return null;
	}

	public void plantBomb() {
		if (resource.getPlayer().canPlantBomb()) {
			resource.getPlayer().plantBomb();
			map.addBomb(resource.getPlayer().getX(), resource.getPlayer().getY(), System.currentTimeMillis());
		}
	}
	
	private void calculateBombs() {
		long currTime = System.currentTimeMillis();
		ArrayList<Bomb> toRemove = new ArrayList<Bomb>();
		
		for (Bomb b : map.getBombs()) {
			if (currTime - b.getPlantTime() >= b.getTimer()) {
				//TODO animation & sound exploding bomb
				toRemove.add(b);
				resource.getPlayer().bombExploded();
			}
		}
		
		for (Bomb b : toRemove) {
			map.removeBomb(b);
			resource.getPlayer().bombExploded();
		}
	}

}

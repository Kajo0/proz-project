package pl.edu.pw.elka.mmarkiew.model;

import java.io.IOException;
import java.util.LinkedList;
import pl.edu.pw.elka.mmarkiew.model.entities.BlockEntity;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

public class Model implements Runnable {
	private long startTime;
	private boolean paused;
	private GameMap map;
	private ResourceManager resource;
	private CollisionDetector collisionDetector;
	private BombCalculator bombCalculator;

	public Model() {
		this.startTime = -1;
		this.paused = false;
		this.map = null;
		this.resource = new ResourceManager();
		this.collisionDetector = null;
		this.bombCalculator = null;
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
			this.collisionDetector = new CollisionDetector(map);
			this.bombCalculator = new BombCalculator(map);
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
			
            if (!paused && startTime > 0)
            	update(elapsedTime);
            
            try {
				wait(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void update(final long elapsedTime) {
		LinkedList<Entity> entityToRemove = new LinkedList<Entity>();
		for (Entity e : map.getEntities()) {
			e.update(elapsedTime);
			if ((!e.isAlive() || e instanceof BlockEntity) && e.getDieTime() + e.getDyingTime() < System.currentTimeMillis())
				entityToRemove.add(e);
		}
		for (Entity e : entityToRemove)
			map.removeEnemy(e);
		
		if (collisionDetector != null)
			collisionDetector.detectCollision();
		
		//TODO zmienic na GAME OVER
		if (resource.getPlayer().getLifes() < 1)
			startTime = -1;
		
		bombCalculator.calculateBombs();
		
		//TODO WYWALIC TO BO TO TYLKO DO TESTU PRZEJSCIA MAPY
		if (map.getEnemies().isEmpty()) {
			nextMap();
		}
	}

	private void nextMap() {
		try {
			map = resource.loadNextMap();
			collisionDetector.setMap(map);
			bombCalculator.setMap(map);
		} catch (IOException e) {
			map = null;
			collisionDetector.setMap(map);
			bombCalculator.setMap(map);
			startTime = -2;
		}
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
		bombCalculator.plantBomb();
	}
	
}

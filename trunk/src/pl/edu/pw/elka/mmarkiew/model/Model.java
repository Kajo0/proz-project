package pl.edu.pw.elka.mmarkiew.model;

import java.io.IOException;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.GameMap;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

public class Model implements Runnable {
	private int width;
	private int height;
	private long startTime;
	private boolean paused;
	private GameMap map;
	private ResourceManager resource;
	
	public Model() {
		this.width = 0;
		this.height = 0;
		this.startTime = -1;
		this.paused = false;
		this.map = null;
		this.resource = new ResourceManager();
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
			this.width = map.getWidth();
			this.height = map.getHeight();
		} catch (IOException e) {
			this.map = null;
			this.startTime = -1;
			this.width = 0;
			this.height = 0;
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
				wait(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void update(final long elapsedTime) {
		for (Entity e : map.getEntities()) {
			e.update(elapsedTime);
			CollisionDetector.checkEntityBlockCollision(e, map.getBlockHolder());
		}
		
		//TODO zmienic zycie-- playera i ustawianie n a poczatku
		if (CollisionDetector.checkPlayerEntityCollision(resource.getPlayer(), map.getEnemies())) {
			resource.getPlayer().setX((float) map.getPlayerStartPosition().getX());
			resource.getPlayer().setY((float) map.getPlayerStartPosition().getY());
		}
		
		CollisionDetector.checkEnemiesCollision(map.getEnemies());
		//TODO dodac bonusy kolizje zbieranie
//		CollisionDetector.checkPlayerBonusCollision();
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

}
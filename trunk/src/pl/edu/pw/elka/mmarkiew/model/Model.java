package pl.edu.pw.elka.mmarkiew.model;

import java.io.IOException;
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
		this.resource = null;
	}

	@Override
	public synchronized void run() {
		init();
		gameLoop();
	}

	//loads first map
	private void init() {
		this.resource = new ResourceManager();
		
		try {
			this.map = resource.loadMap("maps/1.txt");
			this.startTime = 0;
		} catch (IOException e) {
			this.map = null;
			this.startTime = -1;
		}
		
		this.width = map.getWidth();
		this.height = map.getHeight();
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
				e.printStackTrace();//TODO INTERRUPT DZIALAJ OMG
			}
		}
	}
	
	private void update(final long elapsedTime) {
		this.map.getPlayer().update(elapsedTime);
		CollisionDetector.checkEntityBlockCollision(map.getPlayer(), map.getBlockTable());
	}

	public MapToDraw getMapToDraw() {
		if (map != null)
			return new MapToDraw(map.getBlockTable(), map.getEntities(), map.getWidthBlocks(),
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
		return map.getPlayer();
	}

}

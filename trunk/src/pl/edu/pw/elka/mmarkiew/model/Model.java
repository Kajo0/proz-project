package pl.edu.pw.elka.mmarkiew.model;

import java.io.IOException;

import pl.edu.pw.elka.mmarkiew.controller.crossobjects.MapToDraw;
import pl.edu.pw.elka.mmarkiew.controller.crossobjects.EntityToDraw;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.map.BlockElement;
import pl.edu.pw.elka.mmarkiew.view.View;


/**
 * 
 * @author Acer
 *
 */
public class Model implements Runnable {
	private GameMap map;
	private Player player;
	private boolean paused = false;
	private int numOfBlocks;
	private int width;
	private int height;
	private long startTime;
	
	/**
	 * Create game model with level 1
	 */
	public Model() {
		this.map = null;
		this.width = this.height = View.HEIGHT_DEFAULT;
		setMap("maps/1.txt");
		this.numOfBlocks = Math.max(map.getWidth(), map.getHeight());
		player = new Player();
	}
	
	/**
	 * Sets new map<br>
	 * If file doesn't exist set null map
	 * @param path path to map
	 */
	public void setMap(final String path) {
		try {
			map = new GameMap(path);
		} catch (IOException e) {
			map = null;
			System.out.println("Nie ma mapy: " + path);
		}
	}
	
	
	public MapToDraw getMap() {
		return new MapToDraw(map.getTerrain().clone(), numOfBlocks, map.getWidth(), map.getHeight());
	}
	
	public EntityToDraw getPlayer() {
		return new EntityToDraw(player.getAnim(), Math.round(player.getX()), Math.round(player.getY()));
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
        long currTime = startTime;
        
		while (true) {
			if (!paused) {
				
				long elapsedTime = System.currentTimeMillis() - currTime;
		        currTime += elapsedTime;
		        
		        update(elapsedTime);
			}
		}	
	}

	private void update(long elapsedTime) {
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		player.update(elapsedTime);
		if (player.getX() < 0 || player.getX() > width - player.getAnim().getWidth(null))
			player.setXVelocity(-player.getXVelocity());

		if (player.getY() < 0 || player.getY() > height - player.getAnim().getHeight(null))
			player.setYVelocity(-player.getYVelocity());
		
	}

	public int getNumOfBlocks() {
		return numOfBlocks;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}

package pl.edu.pw.elka.mmarkiew.model;

import java.io.IOException;

/**
 * 
 * @author Acer
 *
 */
public class Model implements Runnable {
	private GameMap map;
	private boolean paused = false;
	
	/**
	 * Create game model with level 1
	 */
	public Model() {
		this.map = null;
		setMap("maps/1.txt");
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
	
	
	public String[][] getMap() {
		return map.getTerrain();
	}

	@Override
	public void run() {
		while (true) {
			if (!paused) {
				update();
			}
		}	
	}

	private void update() {
		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

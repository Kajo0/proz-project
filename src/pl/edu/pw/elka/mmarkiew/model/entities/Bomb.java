package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import pl.edu.pw.elka.mmarkiew.model.GameMap;

public class Bomb extends Entity {
	private long plantTime;
	/** miliseconds */
	private long timer;
	
	public Bomb(float x, float y, long plantTime) {
		super();
		this.setX(GameMap.getTilePosition(x) * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2);
		this.setY(GameMap.getTilePosition(y) * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2);
		this.setWidth(15);
		this.setHeight(15);
		this.plantTime = plantTime;
		this.timer = 3000;
		
		BufferedImage img = new BufferedImage((int) this.getWidth(), (int) this.getHeight(),
																				BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, (int) this.getWidth(), (int) this.getHeight());
		
		this.setAnim(img);
	}

	public long getPlantTime() {
		return plantTime;
	}

	public long getTimer() {
		return timer;
	}
	
	public void collisionX() {
		this.setXVelocity(-this.getXVelocity());
	}
	
	public void collisionY() {
		this.setYVelocity(-this.getYVelocity());
	}

}

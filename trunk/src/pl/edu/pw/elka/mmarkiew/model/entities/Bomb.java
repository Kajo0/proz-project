package pl.edu.pw.elka.mmarkiew.model.entities;

import pl.edu.pw.elka.mmarkiew.model.GameMap;

public class Bomb extends Entity {
	private long plantTime;
	/** miliseconds */
	private long timer;
	
	public Bomb(Animation anim, float x, float y, long plantTime) {
		super(anim, anim);
		this.setX(GameMap.getTilePosition(x) * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2);
		this.setY(GameMap.getTilePosition(y) * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2);
		this.plantTime = plantTime;
		this.timer = 3000;
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

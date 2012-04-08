package pl.edu.pw.elka.mmarkiew.model.entities;

import pl.edu.pw.elka.mmarkiew.model.GameMap;

public class Bomb extends Entity {
	private long plantTime;
	/** miliseconds */
	private long timer;
	
	public Bomb(Animation anim, float x, float y, long plantTime) {
		super(anim, anim);
		this.setX(GameMap.getTileCenterFromPosition(x));
		this.setY(GameMap.getTileCenterFromPosition(y));
		this.plantTime = plantTime;
		this.timer = 2000;
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

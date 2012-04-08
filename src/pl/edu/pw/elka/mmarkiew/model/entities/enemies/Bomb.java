package pl.edu.pw.elka.mmarkiew.model.entities.enemies;

import pl.edu.pw.elka.mmarkiew.model.GameMap;
import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;

public class Bomb extends Entity {
	private long plantTime;
	/** miliseconds */
	private long timer;
	private int area;
	
	public Bomb(Animation anim, float x, float y, long plantTime, long timer, int area) {
		super(anim, anim);
		this.setX(GameMap.getTileCenterFromPosition(x));
		this.setY(GameMap.getTileCenterFromPosition(y));
		this.plantTime = plantTime;
		this.timer = timer;
		this.area = area;
	}

	public long getPlantTime() {
		return plantTime;
	}

	public long getTimer() {
		return timer;
	}
	
	public int getArea() {
		return area;
	}
	
	public void collisionX() {
		this.setXVelocity(-this.getXVelocity());
	}
	
	public void collisionY() {
		this.setYVelocity(-this.getYVelocity());
	}


}

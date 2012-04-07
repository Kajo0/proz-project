package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Image;

public abstract class Entity {
	private Animation anim;	// TODO zamienic na Animation
	private Animation dyingAnim;
	private float x;
	private float y;
	private float xVelocity;
	private float yVelocity;
	private float maxVelocity;
	private float width;
	private float height;
	private boolean alive;
	private long dieTime;
	private int dyingTime;
	
	public Entity(Animation anim, Animation dyingAnim) {
		this.anim = anim;
		this.dyingAnim = dyingAnim;
		this.x = 0;
		this.y = 0;
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.maxVelocity = 0;
		this.width = anim.getImage().getWidth(null);
		this.height = anim.getImage().getHeight(null);
		this.alive = true;
		this.dieTime = -1;
		this.dyingTime = 2000;
	}
	
	public void update(final long elapsedTime) {
		if (alive) {
			this.x += xVelocity * elapsedTime;
			this.y += yVelocity * elapsedTime;
			anim.update(elapsedTime);
		} else dyingAnim.update(elapsedTime);
	}
	
	public void collisionX() {
		this.xVelocity = 0f;
	}
	
	public void collisionY() {
		this.yVelocity = 0f;
	}

	public Image getAnim() {
		if (isAlive())
			return this.anim.getImage();
		else return this.dyingAnim.getImage();
	}

	public void setAnim(Animation anim) {
		this.anim = anim;
	}

	public void setDyingAnim(Animation anim) {
		this.dyingAnim = anim;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getXVelocity() {
		return xVelocity;
	}

	public void setXVelocity(float xVelocity) {
		this.xVelocity = xVelocity;
	}

	public float getYVelocity() {
		return yVelocity;
	}

	public void setYVelocity(float yVelocity) {
		this.yVelocity = yVelocity;
	}
	
	public float getMaxVelocity() {
		return maxVelocity;
	}
	
	public void setMaxVelocity(float maxVelocity) {
		this.maxVelocity = maxVelocity;
	}
	
	public float getWidth() {
		return width;
	}
	
	public void setWidth(float width) {
		this.width = width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public boolean isAlive() {
		return this.alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void setDead() {
		this.alive = false;
		this.dieTime = System.currentTimeMillis();
	}

	public long getDieTime() {
		return dieTime;
	}

	public void setDieTime(long dieTime) {
		this.dieTime = dieTime;
	}

	public int getDyingTime() {
		return this.dyingTime;
	}
	
	public void setDyingTime(int dyingTime) {
		this.dyingTime = dyingTime;
	}
}

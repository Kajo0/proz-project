package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Image;

public abstract class Entity {
	private Image anim;
	private float x;
	private float y;
	private float xVelocity;
	private float yVelocity;
	private float maxVelocity;
	
	public void update(long elapsedTime) {
		x += xVelocity * elapsedTime;
        y += yVelocity * elapsedTime;
	}
	
	public void collisionX() {
		xVelocity = 0;
	}
	
	public void collisionY() {
		yVelocity = 0;
	}

	public Image getAnim() {
		return anim;
	}

	public void setAnim(Image anim) {
		this.anim = anim;
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

}

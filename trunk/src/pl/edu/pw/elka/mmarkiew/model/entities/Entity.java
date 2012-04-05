package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Image;

public abstract class Entity {
	private Image anim;	// TODO zamienic na Animation
	private float x;
	private float y;
	private float xVelocity;
	private float yVelocity;
	private float maxVelocity;
	private float width;
	private float height;
	
	public Entity() {
		this.anim = null;
		this.x = 0;
		this.y = 0;
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.maxVelocity = 0;
		this.width = 0;
		this.height = 0;
	}
	
	public void update(final long elapsedTime) {
		this.x += xVelocity * elapsedTime;
		this.y += yVelocity * elapsedTime;
	}
	
	public void collisionX() {
		this.xVelocity = 0f;
	}
	
	public void collisionY() {
		this.yVelocity = 0f;
	}

	public Image getAnim() {
		return this.anim;
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

}
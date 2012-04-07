package pl.edu.pw.elka.mmarkiew.model.entities;

public abstract class Enemy extends Entity {
	
	public Enemy(Animation anim, Animation dyingAnim) {
		super(anim, dyingAnim);
	}
	
	public Enemy(Animation anim, Animation dyingAnim, float x, float y) {
		this(anim, dyingAnim);
		this.setX(x);
		this.setY(y);
	}

	public void collisionX() {
		this.setXVelocity(-this.getXVelocity());
	}
	
	public void collisionY() {
		this.setYVelocity(-this.getYVelocity());
	}
}

package pl.edu.pw.elka.mmarkiew.model.entities;

public class HeliumEnemy extends Entity implements Enemy {
	
	public HeliumEnemy(Animation anim, Animation dyingAnim) {
		super(anim, dyingAnim);
		this.setXVelocity(0.1f);
		this.setYVelocity(0.1f);
		this.setMaxVelocity(0.1f);
	}
	
	public HeliumEnemy(Animation anim, Animation dyingAnim, int x, int y) {
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

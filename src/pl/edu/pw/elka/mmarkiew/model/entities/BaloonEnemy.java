package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Image;

public class BaloonEnemy extends Entity implements Enemy {
	
	public BaloonEnemy(Image anim, Image dyingAnim) {
		super(anim, dyingAnim);
		this.setXVelocity(0.05f);
		this.setYVelocity(0.05f);
		this.setMaxVelocity(0.05f);
	}
	
	public BaloonEnemy(Image anim, Image dyingAnim, int x, int y) {
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

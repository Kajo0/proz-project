package pl.edu.pw.elka.mmarkiew.model.entities.enemies;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;

/**
 * Representing simply fast enemy
 * @author Acer
 *
 */
public class HeliumEnemy extends Enemy {
	
	public HeliumEnemy(Animation anim, Animation dyingAnim, float x, float y) {
		super(anim, dyingAnim, x, y);
		this.setXVelocity(0.1f);
		this.setYVelocity(0.1f);
		this.setMaxVelocity(0.1f);
	}
}
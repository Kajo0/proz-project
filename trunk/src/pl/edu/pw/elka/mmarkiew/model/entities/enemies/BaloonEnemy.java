package pl.edu.pw.elka.mmarkiew.model.entities.enemies;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;

/**
 * Representing simply slow enemy
 * @author Acer
 *
 */
public class BaloonEnemy extends Enemy {
	
	public BaloonEnemy(final Animation anim, final Animation dyingAnim, int width, int height, float x, float y) {
		super(anim, dyingAnim, width, height, x, y);
		this.setXVelocity(0.06f);
		this.setYVelocity(0.05f);
		this.setMaxVelocity(0.06f);
	}
}

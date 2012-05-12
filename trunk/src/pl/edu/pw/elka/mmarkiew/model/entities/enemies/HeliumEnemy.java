package pl.edu.pw.elka.mmarkiew.model.entities.enemies;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;

/**
 * Representing simply fast enemy
 * @author Acer
 *
 */
public class HeliumEnemy extends Enemy {
	
	public HeliumEnemy(final Animation anim, final Animation dyingAnim, int width, int height, float x, float y)
	{
		super(anim, dyingAnim, width, height, x, y);
		this.setXVelocity(0.1f);
		this.setYVelocity(0.11f);
		this.setMaxVelocity(0.11f);
	}
}
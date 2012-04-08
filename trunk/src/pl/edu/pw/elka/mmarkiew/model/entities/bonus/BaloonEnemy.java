package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;

public class BaloonEnemy extends Enemy {
	
	public BaloonEnemy(Animation anim, Animation dyingAnim, float x, float y) {
		super(anim, dyingAnim, x, y);
		this.setXVelocity(0.05f);
		this.setYVelocity(0.05f);
		this.setMaxVelocity(0.05f);
	}
}

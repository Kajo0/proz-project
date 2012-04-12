package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Bonus increments player's speed
 * @author Acer
 *
 */
public class SpeedBonus extends Bonus {

	public SpeedBonus(final Animation anim, final Animation dyingAnim, float x, float y) {
		super(anim, dyingAnim, x, y);
	}
	
	public void bonusideEntity(final Player e) {
		e.setMaxVelocity(e.getMaxVelocity() + 0.05f);
	}
}

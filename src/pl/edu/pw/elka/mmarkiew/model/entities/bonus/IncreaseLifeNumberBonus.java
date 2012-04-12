package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Bonus increments player's lifes
 * @author Acer
 *
 */
public class IncreaseLifeNumberBonus extends Bonus {

	public IncreaseLifeNumberBonus(final Animation anim, final Animation dyingAnim, float x, float y) {
		super(anim, dyingAnim, x, y);
	}
	
	public void bonusideEntity(final Player e) {
		e.setLifes(e.getLifes() + 1);
	}
}

package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Bonus increments player's speed
 * @author Acer
 *
 */
public class SpeedBonus extends Bonus {

	public SpeedBonus(final Animation anim, final Animation dyingAnim, int width, int height, float x, float y) {
		super(anim, dyingAnim, width, height, x, y);
	}
	
	public void bonusideEntity(final Player player) {
		super.bonusideEntity(player);
		player.setMaxVelocity(player.getMaxVelocity() + 0.05f);
	}
}

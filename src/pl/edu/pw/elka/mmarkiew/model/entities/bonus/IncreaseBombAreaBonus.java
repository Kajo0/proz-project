package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Bonus increment player's bomb explosion area 
 * @author Acer
 *
 */
public class IncreaseBombAreaBonus extends Bonus {

	public IncreaseBombAreaBonus(final Animation anim, final Animation dyingAnim, int width, int height, float x, float y) {
		super(anim, dyingAnim, width, height, x, y);
	}
	
	public void bonusideEntity(final Player player) {
		super.bonusideEntity(player);
		player.setBombArea(player.getBombArea() + 1);
	}
}

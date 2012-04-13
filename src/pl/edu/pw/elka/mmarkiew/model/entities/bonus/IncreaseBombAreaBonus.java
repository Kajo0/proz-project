package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Bonus increment player's bomb explosion area 
 * @author Acer
 *
 */
public class IncreaseBombAreaBonus extends Bonus {

	public IncreaseBombAreaBonus(final Animation anim, final Animation dyingAnim, float x, float y) {
		super(anim, dyingAnim, x, y);
	}
	
	public void bonusideEntity(final Player player) {
		super.bonusideEntity(player);
		player.setBombArea(player.getBombArea() + 1);
	}
}

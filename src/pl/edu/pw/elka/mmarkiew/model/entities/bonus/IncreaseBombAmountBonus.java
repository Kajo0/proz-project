package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Bonus which increment amount of player bombs possible to plant
 * @author Acer
 *
 */
public class IncreaseBombAmountBonus extends Bonus {

	public IncreaseBombAmountBonus(final Animation anim, final Animation dyingAnim, int width, int height, float x, float y) {
		super(anim, dyingAnim, width, height, x, y);
	}
	
	public void bonusideEntity(final Player player) {
		super.bonusideEntity(player);
		player.setPossibleBombs(player.getPossibleBombs() + 1);
	}
}

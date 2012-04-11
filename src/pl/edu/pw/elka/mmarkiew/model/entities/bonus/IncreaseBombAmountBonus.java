package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Bonus which increment amount of player bombs possible to plant
 * @author Acer
 *
 */
public class IncreaseBombAmountBonus extends Bonus {

	public IncreaseBombAmountBonus(Animation anim, Animation dyingAnim, float x, float y) {
		super(anim, dyingAnim, x, y);
	}
	
	public void bonusideEntity(Player e) {
		e.setPossibleBombs(e.getPossibleBombs() + 1);
	}
}

package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Bonus which increment amount of player bombs possible to plant
 * @author Acer
 *
 */
public class IncreaseBombAmountBonus extends Bonus {

	public IncreaseBombAmountBonus(final Animation anim, final Animation dyingAnim,
													final int width, final int height, final float x, final float y)
	{
		super(anim, dyingAnim, width, height, x, y);
	}
	
	public void bonusideEntity(final Player player)
	{
		player.setPossibleBombs(player.getPossibleBombs() + 1);
	}
}

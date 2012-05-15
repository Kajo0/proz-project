package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Bonus increments player's lifes
 * @author Acer
 *
 */
public class IncreaseLifeNumberBonus extends Bonus {

	public IncreaseLifeNumberBonus(final Animation anim, final Animation dyingAnim,
													final int width, final int height, final float x, final float y)
	{
		super(anim, dyingAnim, width, height, x, y);
	}
	
	public void bonusideEntity(final Player player)
	{
		player.setLifes(player.getLifes() + 1);
	}
}

package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Bonus increment player's bomb explosion area 
 * @author Acer
 *
 */
public class IncreaseBombAreaBonus extends Bonus {

	public IncreaseBombAreaBonus(final Animation anim, final Animation dyingAnim,
													final int width, final int height, final float x, final float y)
	{
		super(anim, dyingAnim, width, height, x, y);
	}
	
	public void bonusideEntity(final Player player)
	{
		player.setBombArea(player.getBombArea() + 1);
	}
}

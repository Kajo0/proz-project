package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Bonus which sets player ability to push bombs
 * @author Acer
 *
 */
public class BouncingBomb extends Bonus {

	public BouncingBomb(final Animation anim, final Animation dyingAnim, int width, int height, float x, float y)
	{
		super(anim, dyingAnim, width, height, x, y);
	}
	
	public void bonusideEntity(final Player player)
	{
		player.setBouncingBomb(true);
	}
}


package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Bonus which sets player ability to push bombs
 * @author Acer
 *
 */
public class BouncingBomb extends Bonus{

	public BouncingBomb(final Animation anim, final Animation dyingAnim, float x, float y) {
		super(anim, dyingAnim, x, y);
	}
	
	public void bonusideEntity(final Player e) {
		e.setBouncingBomb(true);
	}
}


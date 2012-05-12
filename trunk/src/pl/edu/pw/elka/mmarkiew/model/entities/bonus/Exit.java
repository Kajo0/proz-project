package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Bonus representing Exit doors from game<br>
 * Default dead, has no bonus
 * @author Acer
 *
 */
public class Exit extends Bonus {
	
	public Exit(final Animation anim, final Animation dyingAnim, int width, int height, float x, float y)
	{
		super(anim, dyingAnim, width, height, x, y);
		this.setAlive(false);
	}
	
	public void setDead() {}
	
	public void bonusideEntity(final Player e) {}
}

package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Class represents Bonus entites
 * @author Acer
 *
 */
public abstract class Bonus extends Entity {
	
	/**
	 * Creates Bonus entity<br>
	 * Calls super Entity constructor
	 * 
	 * @param anim - Animation when is alive
	 * @param dyingAnim - Animation when dying
	 * @param x - Position
	 * @param y - Position
	 */
	public Bonus(final Animation anim, final Animation dyingAnim,
													final int width, final int height, final float x, final float y)
	{
		super(anim, dyingAnim, width, height);
		this.setX(x);
		this.setY(y);
		this.setDyingTime(0);
	}
	
	/**
	 * Update player by giving him appropriate bonus
	 * 
	 * @param player - Player
	 */
	public void bonusideEntity(final Player player) {}
}
package pl.edu.pw.elka.mmarkiew.model.entities.enemies;

//import pl.edu.pw.elka.mmarkiew.model.GameMap;
import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;

/**
 * Class representing enemy entity
 * @author Kajo
 *
 */
public abstract class Enemy extends Entity {
	
	public Enemy(final Animation anim, final Animation dyingAnim, final int width, final int height)
	{
		super(anim, dyingAnim, width, height);
	}
	
	/**
	 * Creates entity and set it given position
	 * 
	 * @param anim - Animation when is alive
	 * @param dyingAnim - Animation when is dying
	 * @param width - Object width
	 * @param height - Object height
	 * @param x - Position
	 * @param y - Position
	 */
	public Enemy(final Animation anim, final Animation dyingAnim,
													final int width, final int height, final float x, final float y)
	{
		this(anim, dyingAnim, width, height);
		this.setX(x);
		this.setY(y);
	}

	/**
	 * When horizontal collision, turn it back
	 */
	public void collisionX()
	{
		this.setXVelocity(-this.getXVelocity());
	}

	/**
	 * When vertical collision, turn it back
	 */
	public void collisionY()
	{
		this.setYVelocity(-this.getYVelocity());
	}
}
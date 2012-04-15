package pl.edu.pw.elka.mmarkiew.model.entities.enemies;

//import pl.edu.pw.elka.mmarkiew.model.GameMap;
import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;

/**
 * Class representing enemy entity
 * @author Acer
 *
 */
public abstract class Enemy extends Entity {
	
	public Enemy(Animation anim, Animation dyingAnim) {
		super(anim, dyingAnim);
		/* If they should not to bounce a lot - uncomment that */
//		this.setWidth(GameMap.BLOCK_SIZE);
//		this.setHeight(GameMap.BLOCK_SIZE);
	}
	
	/**
	 * Creates entity and set it given position
	 * @param anim - Animation when is alive
	 * @param dyingAnim - Animation when is dying
	 * @param x - Position
	 * @param y - Position
	 */
	public Enemy(final Animation anim, final Animation dyingAnim, float x, float y) {
		this(anim, dyingAnim);
		this.setX(x);
		this.setY(y);
	}

	/**
	 * When horizontal collision, turn it back
	 */
	public void collisionX() {
		this.setXVelocity(-this.getXVelocity());
	}

	/**
	 * When vertical collision, turn it back
	 */
	public void collisionY() {
		this.setYVelocity(-this.getYVelocity());
	}
}

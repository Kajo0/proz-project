package pl.edu.pw.elka.mmarkiew.model.entities.enemies;

import pl.edu.pw.elka.mmarkiew.model.GameMap;
import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;

/**
 * Entity representing explosions
 * @author Acer
 *
 */
public class ExplosionEntity extends Entity implements Explosion {
	
	/**
	 * Creates explosion entity which kills both enemies and player if it collides with them<br>
	 * Position given in coordinates not tiles
	 * 
	 * @param anim - Dying animation
	 * @param x - Position
	 * @param y - Position
	 */
	public ExplosionEntity(final Animation anim, final int x, final int y)
	{
		super(anim, anim, GameMap.BLOCK_SIZE, GameMap.BLOCK_SIZE);
		this.setX(GameMap.getPositionCenterFromTile(x));
		this.setY(GameMap.getPositionCenterFromTile(y));
		this.setDyingTime(500);
		setDead();
	}

	/**
	 * Set it alive after dead to enable collisions
	 */
	public void setDead()
	{
		super.setDead();
		this.setAlive(true);
	}
}

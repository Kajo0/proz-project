package pl.edu.pw.elka.mmarkiew.model.entities.enemies;

import pl.edu.pw.elka.mmarkiew.model.GameMap;
import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;

/**
 * Entity representing brick after destruction by bomb explosion
 * @author Acer
 *
 */
public class DestroyingBrick extends Entity implements Explosion {
	
	/**
	 * Creates destroyed bricks entity and set it dying <br>
	 * Position given in coordinates not tiles
	 * @param anim - Dying animation
	 * @param x - Position
	 * @param y - Position
	 */
	public DestroyingBrick(final Animation anim, int x, int y) {
		super(anim, anim);
		this.setX(GameMap.getPositionCenterFromTile(x));
		this.setY(GameMap.getPositionCenterFromTile(y));
		this.setWidth(GameMap.BLOCK_SIZE);
		this.setHeight(GameMap.BLOCK_SIZE);
		this.setDyingTime(500);
		setDead();
	}
	
	/**
	 * Set it alive after dead to enable collisions
	 */
	public void setDead() {
		super.setDead();
		this.setAlive(true);
	}
}
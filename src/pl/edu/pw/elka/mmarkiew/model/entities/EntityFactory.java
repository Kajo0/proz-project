package pl.edu.pw.elka.mmarkiew.model.entities;

import pl.edu.pw.elka.mmarkiew.model.entities.bonus.BouncingBomb;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.Exit;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.IncreaseBombAmountBonus;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.IncreaseBombAreaBonus;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.IncreaseLifeNumberBonus;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.SpeedBonus;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.BaloonEnemy;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.Bomb;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.DestroyingBrick;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.ExplosionEntity;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.HeliumEnemy;

/**
 * Factory of entities
 * @author Acer
 *
 */
public abstract class EntityFactory {
	
	/**
	 * Creates given entity
	 * 
	 * @param entity - GameEntity object to create
	 * @return Created entity
	 */
	public static Entity createEntity(final GameEntities entity)
	{
		return createEntity(entity, 0, 0);
	}

	/**
	 * Overloaded function with integer position<br>
	 * Creates entity and sets it on (x, y) position
	 * 
	 * @param entity - GameEntity object to create
	 * @param x - Position
	 * @param y - Position
	 * @return Created entity
	 */
	public static Entity createEntity(final GameEntities entity, final int x, final int y)
	{
		return createEntity(entity, (float) x, (float) y);
	}
	
	/**
	 * Creates entity and sets it on (x, y) position
	 * 
	 * @param entity - GameEntity object to create
	 * @param x - Position
	 * @param y - Position
	 * @return Created entity
	 */
	public static Entity createEntity(final GameEntities entity, final float x, final float y)
	{
		final Animation anim = entity.getAnim().clone();
		final Animation dyingAnim = entity.getDyingAnim().clone();
		final int width = entity.getWidth();
		final int height = entity.getHeight();
		
		switch (entity)
		{
			case PLAYER:			return new Player(					anim, dyingAnim, width, height);
			case BALOON:			return new BaloonEnemy(				anim, dyingAnim, width, height, x, y);
			case HELIUM:			return new HeliumEnemy(				anim, dyingAnim, width, height, x, y);
			case EXIT:				return new Exit(					anim, dyingAnim, width, height, x, y);
			case SPEED:				return new SpeedBonus(				anim, dyingAnim, width, height, x, y);
			case AREA_INC:			return new IncreaseBombAreaBonus(	anim, dyingAnim, width, height, x, y);
			case BOMB_INC:			return new IncreaseBombAmountBonus(	anim, dyingAnim, width, height, x, y);
			case LIFE_INC:			return new IncreaseLifeNumberBonus(	anim, dyingAnim, width, height, x, y);
			case BOUNCING_BOMB:		return new BouncingBomb(		anim, dyingAnim, width, height, x, y);
			case EXPLOSION:			return new ExplosionEntity(			anim, (int) x, (int) y);
			case DESTROYING_BRICK:	return new DestroyingBrick(	anim, (int) x, (int) y);
			case UNDEFINED:			return null;
			default:				return null;
		}
	}
	
	/**
	 * Creates bomb entity with given parameters
	 * 
	 * @param x - Position
	 * @param y - Position
	 * @param plantTime - Time when bomb was planted
	 * @param timer - Time after which bomb explodes
	 * @param area - Explosion area
	 * @return Appropriate Bomb object
	 */
	public static Bomb createBombEntity(final float x, final float y,
															final long plantTime, final long timer, final int area)
	{
		return new Bomb(GameEntities.BOMB.getAnim().clone(),
							GameEntities.BOMB.getWidth(), GameEntities.BOMB.getHeight(), x, y, plantTime, timer, area);
	}
}

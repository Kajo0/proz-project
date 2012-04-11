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
	 * @param entity - GameEntity object to create
	 * @return Created entity
	 */
	public static Entity createEntity(final GameEntities entity) {
		return createEntity(entity, 0, 0);
	}

	/**
	 * Overloaded function with integer position
	 * Creates entity and sets it on (x, y) position
	 * @param entity - GameEntity object to create
	 * @param x - Position
	 * @param y - Position
	 * @return Created entity
	 */
	public static Entity createEntity(final GameEntities entity, int x, int y) {
		return createEntity(entity, (float) x, (float) y);
	}
	
	/**
	 * Creates entity and sets it on (x, y) position
	 * @param entity - GameEntity object to create
	 * @param x - Position
	 * @param y - Position
	 * @return Created entity
	 */
	public static Entity createEntity(final GameEntities entity, float x, float y) {
	
		switch (entity) {
			case PLAYER:	return new Player(entity.getAnim().clone(), entity.getDyingAnim());
			case BALOON:	return new BaloonEnemy(entity.getAnim().clone(), entity.getDyingAnim(), x, y);
			case HELIUM:	return new HeliumEnemy(entity.getAnim().clone(), entity.getDyingAnim(), x, y);
			case EXIT:		return new Exit(entity.getAnim().clone(), entity.getDyingAnim(), x, y);
			case SPEED:		return new SpeedBonus(entity.getAnim().clone(), entity.getAnim().clone(), x, y);
			case AREA_INC:	return new IncreaseBombAreaBonus(entity.getAnim().clone(), entity.getAnim().clone(), x, y);
			case BOMB_INC:	return new IncreaseBombAmountBonus(entity.getAnim().clone(), entity.getAnim().clone(), x, y);
			case LIFE_INC:	return new IncreaseLifeNumberBonus(entity.getAnim().clone(), entity.getAnim().clone(), x, y);
			case BOUNCING_BOMB:	return new BouncingBomb(entity.getAnim().clone(), entity.getAnim().clone(), x, y);
			case EXPLOSION:	return new ExplosionEntity(entity.getAnim().clone(), (int) x, (int) y);
			case DESTROYING_BRICK:	return new DestroyingBrick(entity.getAnim().clone(), (int) x, (int) y);
			case UNDEFINED: return null;
			default:	return null;
		}
	}
	
	/**
	 * Creates bomb entity with given parameters
	 * @param x - Position
	 * @param y - Position
	 * @param plantTime - Time when bomb was planted
	 * @param timer - Time after which bomb explodes
	 * @param area - Explosion area
	 * @return Appropriate Bomb object
	 */
	public static Bomb createBombEntity(float x, float y, long plantTime, long timer, int area) {
		return new Bomb(GameEntities.BOMB.getAnim().clone(), x, y, plantTime, timer, area);
	}
}

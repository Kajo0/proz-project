package pl.edu.pw.elka.mmarkiew.model.entities;

public abstract class EntityFactory {
	public static Entity createEntity(final GameEntities entity) {
		return createEntity(entity, 0, 0);
	}

	public static Entity createEntity(final GameEntities entity, int x, int y) {
		return createEntity(entity, (float) x, (float) y);
	}
	
	public static Entity createEntity(final GameEntities entity, final float x, final float y) {
	
		switch (entity) {
			case PLAYER:	return new Player(entity.getAnim().clone(), entity.getDyingAnim());
			case BALOON:	return new BaloonEnemy(entity.getAnim().clone(), entity.getDyingAnim(), x, y);
			case HELIUM:	return new HeliumEnemy(entity.getAnim().clone(), entity.getDyingAnim(), x, y);
			case EXIT:		return new Exit(entity.getAnim().clone(), entity.getDyingAnim(), x, y);
			case SPEED:		return new SpeedBonus(entity.getAnim().clone(), entity.getAnim().clone(), x, y);
			case AREA_INC:	return new IncreaseBombAreaBonus(entity.getAnim().clone(), entity.getAnim().clone(), x, y);
			case BOMB_INC:	return new IncreaseBombAmountBonus(entity.getAnim().clone(), entity.getAnim().clone(), x, y);
			case LIFE_INC:	return new IncreaseLifeNumberBonus(entity.getAnim().clone(), entity.getAnim().clone(), x, y);
			case EXPLOSION:	return new ExplosionEntity(entity.getAnim().clone(), (int) x, (int) y);
			case DESTROYING_BRICK:	return new DestroyingBrick(entity.getAnim().clone(), (int) x, (int) y);
			case UNDEFINED: return null;
			default:	return null;
		}
	}
	
	public static Bomb createBombEntity(final float x, final float y, final long plantTime, long timer, int area) {
		return new Bomb(GameEntities.BOMB.getAnim().clone(), x, y, plantTime, timer, area);
	}
}

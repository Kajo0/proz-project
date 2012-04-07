package pl.edu.pw.elka.mmarkiew.model.entities;

public abstract class EntityFactory {
	public static Entity createEntity(final GameEntities entity) {
		return createEntity(entity, 0, 0, 0);
	}

	public static Entity createEntity(final GameEntities entity, int x, int y) {
		return createEntity(entity, x, y, 0);
	}
	
	public static Entity createEntity(final GameEntities entity, final float x, final float y, final long plantTime) {
	
		switch (entity) {
			case PLAYER:	return new Player(entity.getAnim().clone(), entity.getDyingAnim());
			case BALOON:	return new BaloonEnemy(entity.getAnim().clone(), entity.getDyingAnim(), (int) x, (int) y);
			case HELIUM:	return new HeliumEnemy(entity.getAnim().clone(), entity.getDyingAnim(), (int) x, (int) y);
			case BOMB:		return new Bomb(entity.getAnim().clone(), x, y, plantTime);
			case EXIT:		return new Exit(entity.getAnim().clone(), entity.getDyingAnim(), x, y);
			case EXPLOSION:	return new ExplosionEntity(entity.getAnim().clone(), (int) x, (int) y);
			case DESTROYING_BRICK:	return new DestroyingBrick(entity.getAnim().clone(), (int) x, (int) y);
			case UNDEFINED: return null;
			default:	return null;
		}
	}
}

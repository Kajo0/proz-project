package pl.edu.pw.elka.mmarkiew.model.entities;

public abstract class EntityFactory {
	public static Entity createEntity(final GameEntities entity) {
		return createEntity(entity, 0, 0);
	}

	public static Entity createEntity(final GameEntities entity, int i, int j) {
		switch (entity) {
		case BALOON: return new BaloonEnemy(i, j);
		case UNDEFINED: return null;
		default:	return null;
	}
	}
}

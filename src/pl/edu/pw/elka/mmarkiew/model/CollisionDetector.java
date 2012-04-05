package pl.edu.pw.elka.mmarkiew.model;

import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.GameMap;
import pl.edu.pw.elka.mmarkiew.model.map.BlockElement;
import pl.edu.pw.elka.mmarkiew.model.map.BlockHolder;
import pl.edu.pw.elka.mmarkiew.model.map.EmptyBlock;

public class CollisionDetector {

	public static void checkEntityBlockCollision(final Entity entity, final BlockHolder blocks) {

		float xPlayerPosition = entity.getX();
		float yPlayerPosition = entity.getY();
		int xTilePlayerPosition = GameMap.getTilePosition(xPlayerPosition);
		int yTilePlayerPosition = GameMap.getTilePosition(yPlayerPosition);
		float dividedAnimWidth = entity.getHeight() / 2;
		float dividedAnimHeight = entity.getHeight() / 2;
		
		// gora dol prawo lewo
			if (! (blocks.getBlock(xTilePlayerPosition, yTilePlayerPosition-1) instanceof EmptyBlock) ) {
				if (yPlayerPosition - dividedAnimHeight < (yTilePlayerPosition) * GameMap.BLLOCK_SIZE) {
					entity.collisionY();
					entity.setY((yTilePlayerPosition) * GameMap.BLLOCK_SIZE + dividedAnimHeight);
				}
			}
			if (! (blocks.getBlock(xTilePlayerPosition, yTilePlayerPosition+1) instanceof EmptyBlock) ) {
				if (yPlayerPosition + dividedAnimHeight > (yTilePlayerPosition+1) * GameMap.BLLOCK_SIZE) {
					entity.collisionY();
					entity.setY((yTilePlayerPosition+1) * GameMap.BLLOCK_SIZE - dividedAnimHeight);
				}
			}
			if (! (blocks.getBlock(xTilePlayerPosition-1, yTilePlayerPosition) instanceof EmptyBlock) ) {
				if (xPlayerPosition - dividedAnimWidth < (xTilePlayerPosition) * GameMap.BLLOCK_SIZE) {
					entity.collisionX();
					entity.setX((xTilePlayerPosition) * GameMap.BLLOCK_SIZE + dividedAnimWidth);
				}
			}
			if (! (blocks.getBlock(xTilePlayerPosition+1, yTilePlayerPosition) instanceof EmptyBlock) ) {
				if (xPlayerPosition + dividedAnimWidth > (xTilePlayerPosition+1) * GameMap.BLLOCK_SIZE) {
					entity.collisionX();
					entity.setX((xTilePlayerPosition+1) * GameMap.BLLOCK_SIZE - dividedAnimWidth);
				}
			}
		
	}
}

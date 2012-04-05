package pl.edu.pw.elka.mmarkiew.model;

import java.util.Arrays;
import java.util.LinkedList;

import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.GameMap;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.map.BlockHolder;
import pl.edu.pw.elka.mmarkiew.model.map.EmptyBlock;

public class CollisionDetector {

	public static void checkEntityBlockCollision(final Entity entity, final BlockHolder blocks) {
		//TODO better colision do
		float xPlayerPosition = entity.getX();
		float yPlayerPosition = entity.getY();
		int xTilePlayerPosition = GameMap.getTilePosition(xPlayerPosition);
		int yTilePlayerPosition = GameMap.getTilePosition(yPlayerPosition);
		float dividedAnimWidth = entity.getHeight() / 2;
		float dividedAnimHeight = entity.getHeight() / 2;
		
		// gora dol prawo lewo
			if (! (blocks.getBlock(xTilePlayerPosition, yTilePlayerPosition-1) instanceof EmptyBlock) ) {
				if (yPlayerPosition - dividedAnimHeight < (yTilePlayerPosition) * GameMap.BLOCK_SIZE) {
					entity.collisionY();
					entity.setY((yTilePlayerPosition) * GameMap.BLOCK_SIZE + dividedAnimHeight);
				}
			}
			if (! (blocks.getBlock(xTilePlayerPosition, yTilePlayerPosition+1) instanceof EmptyBlock) ) {
				if (yPlayerPosition + dividedAnimHeight > (yTilePlayerPosition+1) * GameMap.BLOCK_SIZE) {
					entity.collisionY();
					entity.setY((yTilePlayerPosition+1) * GameMap.BLOCK_SIZE - dividedAnimHeight);
				}
			}
			if (! (blocks.getBlock(xTilePlayerPosition-1, yTilePlayerPosition) instanceof EmptyBlock) ) {
				if (xPlayerPosition - dividedAnimWidth < (xTilePlayerPosition) * GameMap.BLOCK_SIZE) {
					entity.collisionX();
					entity.setX((xTilePlayerPosition) * GameMap.BLOCK_SIZE + dividedAnimWidth);
				}
			}
			if (! (blocks.getBlock(xTilePlayerPosition+1, yTilePlayerPosition) instanceof EmptyBlock) ) {
				if (xPlayerPosition + dividedAnimWidth > (xTilePlayerPosition+1) * GameMap.BLOCK_SIZE) {
					entity.collisionX();
					entity.setX((xTilePlayerPosition+1) * GameMap.BLOCK_SIZE - dividedAnimWidth);
				}
			}

////		if (entity.getYVelocity() < 0) {
////			if (yPlayerPosition - dividedAnimHeight < yTilePlayerPosition * GameMap.BLOCK_SIZE) {
////				if (! (blocks.getBlock(xTilePlayerPosition, yTilePlayerPosition - 1) instanceof EmptyBlock) ) {
////					entity.collisionY();
////					entity.setY(yTilePlayerPosition * GameMap.BLOCK_SIZE + dividedAnimHeight);
////				}
////				if (! (blocks.getBlock(xTilePlayerPosition - 1, yTilePlayerPosition - 1) instanceof EmptyBlock) &&
////						entity.getX() - dividedAnimWidth < xTilePlayerPosition * GameMap.BLOCK_SIZE) {
////					entity.collisionY();
////					entity.setY(yTilePlayerPosition * GameMap.BLOCK_SIZE + dividedAnimHeight);
////				}
////				if (! (blocks.getBlock(xTilePlayerPosition + 1, yTilePlayerPosition - 1) instanceof EmptyBlock) &&
////						entity.getX() + dividedAnimWidth > (xTilePlayerPosition + 1) * GameMap.BLOCK_SIZE) {
////					entity.collisionY();
////					entity.setY(yTilePlayerPosition * GameMap.BLOCK_SIZE + dividedAnimHeight);
////				}
////			}
////		}
////		
////		if (entity.getYVelocity() > 0) {
////			if (yPlayerPosition + dividedAnimHeight > (yTilePlayerPosition + 1) * GameMap.BLOCK_SIZE) {
////				if (! (blocks.getBlock(xTilePlayerPosition, yTilePlayerPosition + 1) instanceof EmptyBlock) ) {
////					entity.collisionY();
////					entity.setY((yTilePlayerPosition + 1) * GameMap.BLOCK_SIZE - dividedAnimHeight);
////				}
////				if (! (blocks.getBlock(xTilePlayerPosition - 1, yTilePlayerPosition + 1) instanceof EmptyBlock) &&
////						entity.getX() - dividedAnimWidth < xTilePlayerPosition * GameMap.BLOCK_SIZE) {
////					entity.collisionY();
////					entity.setY((yTilePlayerPosition + 1) * GameMap.BLOCK_SIZE - dividedAnimHeight);
////				}
////				if (! (blocks.getBlock(xTilePlayerPosition + 1, yTilePlayerPosition + 1) instanceof EmptyBlock) &&
////						entity.getX() + dividedAnimWidth > (xTilePlayerPosition + 1) * GameMap.BLOCK_SIZE) {
////					entity.collisionY();
////					entity.setY((yTilePlayerPosition + 1) * GameMap.BLOCK_SIZE - dividedAnimHeight);
////				}
////			}
////		}
////		
////		if (entity.getXVelocity() < 0) {
////			if (xPlayerPosition - dividedAnimWidth < xTilePlayerPosition * GameMap.BLOCK_SIZE) {
////				if (! (blocks.getBlock(xTilePlayerPosition - 1, yTilePlayerPosition) instanceof EmptyBlock) ) {
////					entity.collisionX();
////					entity.setX(xTilePlayerPosition * GameMap.BLOCK_SIZE + dividedAnimWidth);
////				}
////				if (! (blocks.getBlock(xTilePlayerPosition - 1, yTilePlayerPosition - 1) instanceof EmptyBlock) &&
////						entity.getY() - dividedAnimHeight < yTilePlayerPosition * GameMap.BLOCK_SIZE) {
////					entity.collisionX();
////					entity.setX(xTilePlayerPosition * GameMap.BLOCK_SIZE + dividedAnimWidth);
////				}
////				if (! (blocks.getBlock(xTilePlayerPosition - 1, yTilePlayerPosition + 1) instanceof EmptyBlock) &&
////						entity.getY() + dividedAnimHeight > (yTilePlayerPosition + 1) * GameMap.BLOCK_SIZE) {
////					entity.collisionX();
////					entity.setX(xTilePlayerPosition * GameMap.BLOCK_SIZE + dividedAnimWidth);
////				}
////			}
////		}
////		
////		if (entity.getXVelocity() > 0) {
////			if (xPlayerPosition + dividedAnimWidth > (xTilePlayerPosition + 1) * GameMap.BLOCK_SIZE) {
////				if (! (blocks.getBlock(xTilePlayerPosition + 1, yTilePlayerPosition) instanceof EmptyBlock) ) {
////					entity.collisionX();
////					entity.setX((xTilePlayerPosition + 1) * GameMap.BLOCK_SIZE - dividedAnimWidth);
////				}
////				if (! (blocks.getBlock(xTilePlayerPosition + 1, yTilePlayerPosition - 1) instanceof EmptyBlock) &&
////						entity.getY() - dividedAnimHeight < yTilePlayerPosition * GameMap.BLOCK_SIZE) {
////					entity.collisionX();
////					entity.setX((xTilePlayerPosition + 1) * GameMap.BLOCK_SIZE - dividedAnimWidth);
////				}
////				if (! (blocks.getBlock(xTilePlayerPosition + 1, yTilePlayerPosition + 1) instanceof EmptyBlock) &&
////						entity.getY() + dividedAnimHeight > (yTilePlayerPosition + 1) * GameMap.BLOCK_SIZE) {
////					entity.collisionX();
////					entity.setX((xTilePlayerPosition + 1) * GameMap.BLOCK_SIZE - dividedAnimWidth);
////				}
////			}
////		}
	}

	public static boolean checkPlayerEntityCollision(Player player, LinkedList<Entity> linkedList) {
		for (Entity e : linkedList) {
			if (isEntitiesCollision(player, e)) {
				return true;
			}
		}
		return false;
	}


	public static void checkEnemiesCollision(LinkedList<Entity> enemies) {
		//TODO usprawnic kolizje
		Entity[] entities = enemies.toArray(new Entity[enemies.size()]);

		for (int i = 0; i < entities.length; i++) {
			for (int j = 0; j < entities.length; j++)
				if (isEntitiesCollision(entities[i], entities[j])) {
					entities[i].collisionX();
					entities[i].collisionY();
					entities[j].collisionX();
					entities[j].collisionY();
				}
			i++;
		}
		
	}
	
	public static void checkPlayerBonusCollision() {
		// TODO Auto-generated method stub
		
	}
	
	private static boolean isEntitiesCollision(Entity first, Entity second) {
		return (first.getX() + first.getWidth() / 2 - second.getX() + second.getWidth() / 2 > 0 &&
				first.getX() - first.getWidth() / 2 - second.getX() - second.getWidth() / 2 < 0 &&
				first.getY() + first.getHeight() / 2 - second.getY() + second.getHeight() / 2 > 0 &&
				first.getY() - first.getHeight() / 2 - second.getY() - second.getHeight() / 2 < 0);
	}

}

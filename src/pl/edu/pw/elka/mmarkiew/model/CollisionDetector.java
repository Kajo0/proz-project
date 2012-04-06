package pl.edu.pw.elka.mmarkiew.model;

import java.util.LinkedList;
import pl.edu.pw.elka.mmarkiew.model.entities.BlockEntity;
import pl.edu.pw.elka.mmarkiew.model.entities.Bomb;
import pl.edu.pw.elka.mmarkiew.model.entities.Enemy;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.map.BlockHolder;
import pl.edu.pw.elka.mmarkiew.model.map.EmptyBlock;

public class CollisionDetector {
	private GameMap map;

	public CollisionDetector(GameMap map) {
		this.map = map;
	}
	
	public void setMap(GameMap map) {
		this.map = map;
	}
	
	public void detectCollision() {
		for (Entity e : map.getEntities())
			checkEntityBlockCollision(e, map.getBlockHolder());

		
		checkPlayerEntityCollision(map.getEntities());
		
		checkEnemiesCollision(map.getEntities());
		
		//TODO dodac bonusy kolizje zbieranie
		checkPlayerBonusCollision();
		
	}

	public void checkEntityBlockCollision(final Entity entity, final BlockHolder blocks) {
		//TODO better colision do
		float xPlayerPosition = entity.getX();
		float yPlayerPosition = entity.getY();
		int xTilePlayerPosition = GameMap.getTilePosition(xPlayerPosition);
		int yTilePlayerPosition = GameMap.getTilePosition(yPlayerPosition);
		float dividedAnimWidth = entity.getHeight() / 2;
		float dividedAnimHeight = entity.getHeight() / 2;
		
		if (entity instanceof BlockEntity)
			return;
		
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
	}

	public void checkPlayerEntityCollision(LinkedList<Entity> linkedList) {
		Player player = map.getPlayer();
		for (Entity e : linkedList) {
			if (e.isAlive() && isEntitiesCollision(player, e)) {
				if (e instanceof Enemy || e instanceof BlockEntity) { //TODO i nie bonus bedzie trza dac
					player.setDead();
					player.setX((float) map.getPlayerStartPosition().getX());
					player.setY((float) map.getPlayerStartPosition().getY());
					map.removeExplosions();
					return;
				}
				if (e instanceof Bomb) {
					//TODO tak zeby blokowala bomba playera
				}
			}
		}
	}


	public void checkEnemiesCollision(LinkedList<Entity> enemies) {
		//TODO usprawnic kolizje
		Entity[] entities = enemies.toArray(new Entity[enemies.size()]);

		for (int i = 0; i < entities.length - 1; i++)
			for (int j = i + 1; j < entities.length; j++)
				if (!entities[i].isAlive() || !entities[j].isAlive() || entities[i] instanceof Player)
					continue;
				else if (isEntitiesCollision(entities[i], entities[j])) {
					if (entities[i] instanceof Enemy && entities[j] instanceof BlockEntity)
						entities[i].setDead();
					if (entities[j] instanceof Enemy && entities[j] instanceof BlockEntity)
						entities[j].setDead();
					entities[i].collisionX();
					entities[i].collisionY();
					entities[j].collisionX();
					entities[j].collisionY();
				}
		
	}
	
	public void checkPlayerBonusCollision() {
		// TODO dorobic z bonusem kolizje
		
	}
	
	public static boolean isEntitiesCollision(Entity first, Entity second) {
		if (first == second)
			return false;
		return (first.getX() + first.getWidth() / 2 - second.getX() + second.getWidth() / 2 > 0 &&
				first.getX() - first.getWidth() / 2 - second.getX() - second.getWidth() / 2 < 0 &&
				first.getY() + first.getHeight() / 2 - second.getY() + second.getHeight() / 2 > 0 &&
				first.getY() - first.getHeight() / 2 - second.getY() - second.getHeight() / 2 < 0);
	}

}

package pl.edu.pw.elka.mmarkiew.model;

import java.util.LinkedList;
import java.util.Random;

import pl.edu.pw.elka.mmarkiew.model.entities.Bomb;
import pl.edu.pw.elka.mmarkiew.model.entities.Enemy;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.GameMap;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.map.BlockFactory;
import pl.edu.pw.elka.mmarkiew.model.map.BlockHolder;
import pl.edu.pw.elka.mmarkiew.model.map.EmptyBlock;
import pl.edu.pw.elka.mmarkiew.model.map.GameBlock;

public class CollisionDetector {
	private Player player;
	private GameMap map;

	public CollisionDetector(Player player, GameMap map) {
		this.player = player;
		this.map = map;
	}
	
	public void detectCollision() {
		for (Entity e : map.getEntities())
			checkEntityBlockCollision(e, map.getBlockHolder());

		
		checkPlayerEntityCollision(player, map.getEntities());
		
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

	public void checkPlayerEntityCollision(Player player, LinkedList<Entity> linkedList) {
		for (Entity e : linkedList) {
			if (isEntitiesCollision(player, e)) {
				if (e instanceof Enemy) { //TODO i nie bonus bedzie trza dac
					player.setX((float) map.getPlayerStartPosition().getX());
					player.setY((float) map.getPlayerStartPosition().getY());
					player.setLifes(player.getLifes() - 1);
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
				if (!entities[i].isAlive() || entities[i] instanceof Player)
					continue;
				else if (isEntitiesCollision(entities[i], entities[j])) {
					entities[i].collisionX();
					entities[i].collisionY();
					entities[j].collisionX();
					entities[j].collisionY();
				}
		
	}
	
	public void checkPlayerBonusCollision() {
		// TODO dorobic z bonusem kolizje
		
	}
	
	private boolean isEntitiesCollision(Entity first, Entity second) {
		return (first.getX() + first.getWidth() / 2 - second.getX() + second.getWidth() / 2 > 0 &&
				first.getX() - first.getWidth() / 2 - second.getX() - second.getWidth() / 2 < 0 &&
				first.getY() + first.getHeight() / 2 - second.getY() + second.getHeight() / 2 > 0 &&
				first.getY() - first.getHeight() / 2 - second.getY() - second.getHeight() / 2 < 0);
	}

}

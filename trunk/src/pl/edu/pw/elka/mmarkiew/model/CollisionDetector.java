package pl.edu.pw.elka.mmarkiew.model;

import java.util.LinkedList;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.Bonus;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.Bomb;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.DestroyingBrick;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.Enemy;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.ExplosionEntity;
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
		checkPlayerBombCollision(map.getBombs());
		checkPlayerBonusCollision(map.getBonuses());
		
		checkEnemiesCollision(map.getEntities());
	}

	public void checkEntityBlockCollision(final Entity entity, final BlockHolder blocks) {
		//TODO better colision do
		float xPlayerPosition = entity.getX();
		float yPlayerPosition = entity.getY();
		int xTilePlayerPosition = GameMap.getTilePosition(xPlayerPosition);
		int yTilePlayerPosition = GameMap.getTilePosition(yPlayerPosition);
		float dividedAnimWidth = entity.getWidth() / 2;
		float dividedAnimHeight = entity.getHeight() / 2;
		
		if (entity instanceof ExplosionEntity)
			return;

		// gora dol prawo lewo
		
		int i1 = 0, i2 = 0, i3 = 0;
		float f1 = 0, f2 = 0;
		boolean comparison = false;
		for (int i = 0; i < 4; i++) {
			switch (i) {
				case 0: i1 = xTilePlayerPosition;	i2 = yTilePlayerPosition-1;	i3 = yTilePlayerPosition;
						f1 = yPlayerPosition;		f2 = - dividedAnimHeight;
						break;
				case 1: i1 = xTilePlayerPosition;	i2 = yTilePlayerPosition+1;	i3 = yTilePlayerPosition+1;
						f1 = yPlayerPosition;		f2 = dividedAnimHeight;
						break;
				case 2: i1 = xTilePlayerPosition-1;	i2 = yTilePlayerPosition;	i3 = xTilePlayerPosition;
						f1 = xPlayerPosition;		f2 = - dividedAnimWidth;
						break;
				case 3: i1 = xTilePlayerPosition+1;	i2 = yTilePlayerPosition;	i3 = xTilePlayerPosition+1;
						f1 = xPlayerPosition;		f2 = dividedAnimWidth;
						break;
			}
			
			if (! (blocks.getBlock(i1, i2) instanceof EmptyBlock) ) {
				if (i % 2 == 0)
					comparison = (f1 + f2 < i3 * GameMap.BLOCK_SIZE);
				else
					comparison = (f1 + f2 > i3 * GameMap.BLOCK_SIZE);
				
				if (comparison) {
					if ( i < 2 ) {
						entity.collisionY();
						entity.setY(i3 * GameMap.BLOCK_SIZE - f2);
					} else {
						entity.collisionX();
						entity.setX(i3 * GameMap.BLOCK_SIZE - f2);
					}
				}
			}
		}
	}
	
	public void checkPlayerEntityCollision(LinkedList<Entity> linkedList) {
		Player player = map.getPlayer();
		for (Entity e : linkedList) {
			if (e.isAlive() && isEntitiesCollision(player, e)) {
				if (e instanceof Enemy || e instanceof ExplosionEntity) {
					player.setDead();
					return;
				} else
				if (e instanceof DestroyingBrick) {
					//TODO tak zeby blokowala bomba playera
					checkPlayerStopCollision(player, e);
				}
			}
		}
	}

	private void checkPlayerBombCollision(LinkedList<Bomb> bombs) {
		Player player = map.getPlayer();
		int b = 0;
		for (Bomb e : bombs) {
			if (isEntitiesCollision(player, e)) {
				if (!player.isOnBomb())
					checkPlayerStopCollision(player, e);
			} else b++;
		}
		if (b == bombs.size())
			player.setOnBomb(false);
	}

	private void checkPlayerStopCollision(Player player, Entity entity) {
		float xPlayerPosition = player.getX();
		float yPlayerPosition = player.getY();
		float xEntityPosition = entity.getX();
		float yEntityPosition = entity.getY();
		float dividedPlayerWidth = player.getWidth() / 2;
		float dividedPlayerHeight = player.getHeight() / 2;
		float dividedEntityWidth = entity.getWidth() / 2;
		float dividedEntityHeight = entity.getHeight() / 2;
		
		boolean oneDirection = false;
		boolean horizontalCollision = false;
		
		if (player.getXVelocity() != 0 && player.getYVelocity() != 0) {
			float deltaX = Math.abs(xPlayerPosition - xEntityPosition);
			float deltaY = Math.abs(yPlayerPosition - yEntityPosition);
			
			if (deltaX > deltaY)
				horizontalCollision = false;
			else horizontalCollision = true;
			
		} else oneDirection = true;
		
		// gora dol lewo prawo
		if (player.getYVelocity() < 0 && (horizontalCollision || oneDirection)) {
			player.collisionY();
			player.setY(yEntityPosition + dividedEntityHeight + dividedPlayerHeight);
		}
		if (player.getYVelocity() > 0 && (horizontalCollision || oneDirection)) {
			player.collisionY();
			player.setY(yEntityPosition - dividedEntityHeight - dividedPlayerHeight);
		}
		if (player.getXVelocity() < 0 && (!horizontalCollision || oneDirection)) {
			player.collisionX();
			player.setX(xEntityPosition + dividedPlayerWidth + dividedEntityWidth);
		}
		if (player.getXVelocity() > 0 && (!horizontalCollision || oneDirection)) {
			player.collisionX();
			player.setX(xEntityPosition - dividedEntityWidth - dividedPlayerWidth);
		}
		
	}

	private void checkPlayerBonusCollision(LinkedList<Bonus> bonuses) {
		Player player = map.getPlayer();
		for (Bonus b : bonuses) {
			if (b.isAlive() && isEntitiesCollision(player, b)) {
				b.bonusideEntity(player);
				b.setDead();
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
					if (entities[i] instanceof Enemy && entities[j] instanceof ExplosionEntity)
						entities[i].setDead();
					if (entities[j] instanceof Enemy && entities[j] instanceof ExplosionEntity)
						entities[j].setDead();
					entities[i].collisionX();
					entities[i].collisionY();
					entities[j].collisionX();
					entities[j].collisionY();
				}
		
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

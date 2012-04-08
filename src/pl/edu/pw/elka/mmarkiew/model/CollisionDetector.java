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
//		if (! (blocks.getBlock(xTilePlayerPosition, yTilePlayerPosition-1) instanceof EmptyBlock) ) {
//			if (yPlayerPosition - dividedAnimHeight < (yTilePlayerPosition) * GameMap.BLOCK_SIZE) {
//				entity.collisionY();
//				entity.setY((yTilePlayerPosition) * GameMap.BLOCK_SIZE + dividedAnimHeight);
//			}
//		}
//		if (! (blocks.getBlock(xTilePlayerPosition, yTilePlayerPosition+1) instanceof EmptyBlock) ) {
//			if (yPlayerPosition + dividedAnimHeight > (yTilePlayerPosition+1) * GameMap.BLOCK_SIZE) {
//				entity.collisionY();
//				entity.setY((yTilePlayerPosition+1) * GameMap.BLOCK_SIZE - dividedAnimHeight);
//			}
//		}
//		if (! (blocks.getBlock(xTilePlayerPosition-1, yTilePlayerPosition) instanceof EmptyBlock) ) {
//			if (xPlayerPosition - dividedAnimWidth < (xTilePlayerPosition) * GameMap.BLOCK_SIZE) {
//				entity.collisionX();
//				entity.setX((xTilePlayerPosition) * GameMap.BLOCK_SIZE + dividedAnimWidth);
//			}
//		}
//		if (! (blocks.getBlock(xTilePlayerPosition+1, yTilePlayerPosition) instanceof EmptyBlock) ) {
//			if (xPlayerPosition + dividedAnimWidth > (xTilePlayerPosition+1) * GameMap.BLOCK_SIZE) {
//				entity.collisionX();
//				entity.setX((xTilePlayerPosition+1) * GameMap.BLOCK_SIZE - dividedAnimWidth);
//			}
//		}
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

	private void checkPlayerStopCollision(Player player, Entity e) {
		//TODO better colision do!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		float xPlayerPosition = player.getX();
		float yPlayerPosition = player.getY();
		int xTilePlayerPosition = GameMap.getTilePosition(xPlayerPosition);
		int yTilePlayerPosition = GameMap.getTilePosition(yPlayerPosition);
		float dividedAnimWidth = player.getWidth() / 2;
		float dividedAnimHeight = player.getHeight() / 2;
		
		boolean flag = false;
		boolean flag2 = false;
		
		if (player.getXVelocity() != 0 && player.getYVelocity() != 0) {
			float deltax, deltay;
			deltax = Math.abs(player.getX() - e.getX());
			deltay = Math.abs(player.getY() - e.getY());
			
			if (deltax > deltay)
				flag2 = false;
			else flag2 = true;
			
		} else flag = true;
		
		// dol gora lewo prawo
		if (player.getYVelocity() > 0 && (flag2 || flag)) {
			player.collisionY();
			player.setY(yPlayerPosition + e.getY() - e.getHeight() / 2 - player.getY() - player.getHeight() / 2);
		}
		if (player.getYVelocity() < 0 && (flag2 || flag)) {
			player.collisionY();
			player.setY(yPlayerPosition - (player.getY() - player.getHeight() / 2 - e.getY() - e.getHeight() / 2));
		}
		if (player.getXVelocity() > 0 && (!flag2 || flag)) {
			player.collisionX();
			player.setX(xPlayerPosition + e.getX() - e.getWidth() / 2 - player.getX() - player.getWidth() / 2);
		}
		if (player.getXVelocity() < 0 && (!flag2 || flag)) {
			player.collisionX();
			player.setX(xPlayerPosition - (player.getX() - player.getWidth() / 2 - e.getX() - e.getWidth() / 2));
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

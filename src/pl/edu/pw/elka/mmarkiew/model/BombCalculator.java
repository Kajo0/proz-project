package pl.edu.pw.elka.mmarkiew.model;

import java.util.ArrayList;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.EntityFactory;
import pl.edu.pw.elka.mmarkiew.model.entities.GameEntities;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.Bomb;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.DestroyingBrick;
import pl.edu.pw.elka.mmarkiew.model.map.BlockFactory;
import pl.edu.pw.elka.mmarkiew.model.map.BlockHolder;
import pl.edu.pw.elka.mmarkiew.model.map.BrickBlock;
import pl.edu.pw.elka.mmarkiew.model.map.EmptyBlock;
import pl.edu.pw.elka.mmarkiew.model.map.GameBlock;
import pl.edu.pw.elka.mmarkiew.model.map.StoneBlock;

/**
 * Do whatever needs to take care about bomb things in the game
 * @author Acer
 *
 */
public class BombCalculator {
	/** Actual map of game */
	private GameMap map;
	
	/**
	 * Takes care about bomb things
	 * @param map - Actual game map
	 */
	public BombCalculator(final GameMap map) {
		this.map = map;
	}

	/**
	 * Calculate bomb explosions and whatever with them<br><br>
	 * Removes destroyed bombs from map and explode those that should be exploaded
	 */
	public synchronized void calculateBombs() {
		if (map == null)
			return;
		
		long currTime = System.currentTimeMillis();
		ArrayList<Bomb> toRemove = new ArrayList<Bomb>();
		
		for (Bomb b : map.getBombs()) {
			if (currTime - b.getPlantTime() >= b.getTimer()) {
				//TODO add sound exploding bomb
				toRemove.add(b);
			}
		}
		
		for (Bomb b : toRemove) {
			map.removeBomb(b);
			explodeBomb(b);
			map.getPlayer().bombExploded();
		}
	}

	/**
	 * Exploding bomb, and creates 'Enemy' entities -> explosions
	 * @param b - Exploding bomb
	 */
	private void explodeBomb(Bomb b) {
		BlockHolder blocks = map.getBlockHolder();
		int xR = GameMap.getTilePosition(b.getX());
		int yR = GameMap.getTilePosition(b.getY());
		int x, y;

		/*
		 * Add explosion entity into place where was planted
		 * If Player is like a ghost and plan into non EmptyBlock it stops expanding
		 */
		map.addEnemy(EntityFactory.createEntity(GameEntities.EXPLOSION, xR, yR));
		if (blocks.getBlock(xR, yR) instanceof BrickBlock) {
			blocks.setBlock(BlockFactory.createElement(GameBlock.EMPTY), xR, yR);
			Entity blen = EntityFactory.createEntity(GameEntities.DESTROYING_BRICK, xR, yR);
			map.addEnemy(blen);
			return;
		}
		
		/*
		 * Makes cross of enemy explosion entities
		 * Stops on non EmptyBlock
		 */
		for (int i = 0; i < 4; i++) {
			x = xR;
			y = yR;
			
			destroyedBrickAnimation:
			for (int j = 1; j <= b.getArea(); j++) {
				
				/*
				 * Way to 'recurse'
				 */
				switch (i) {
					case 0: x = xR;		y = yR - j; break;
					case 1: x = xR;		y = yR + j; break;
					case 2: x = xR - j; y = yR;		break;
					case 3: x = xR + j; y = yR;		break;
				}
				
				if (blocks.getBlock(x, y) instanceof StoneBlock)
					break;
				else if (blocks.getBlock(x, y) instanceof BrickBlock) {
					
					Entity blen = EntityFactory.createEntity(GameEntities.DESTROYING_BRICK, x, y);
					blocks.setBlock(BlockFactory.createElement(GameBlock.EMPTY), x, y);
					map.addEnemy(blen);
					break;
					
				} else if (blocks.getBlock(x, y) instanceof EmptyBlock) {

					Entity blen = EntityFactory.createEntity(GameEntities.EXPLOSION, x, y);

					/*
					 * Stops on brick which is dying
					 */
					for (Entity e : map.getEntities())
						if (e instanceof DestroyingBrick)
							if (CollisionDetector.isEntitiesCollision(blen, e))
								break destroyedBrickAnimation;

					map.addEnemy(blen);
				}
			}
		}
	}

	/**
	 * Plants bomb with specific timer on place where player is standing
	 * @param timer - Time to explode
	 */
	public synchronized void plantBomb(long timer) {
		Player player = map.getPlayer();
		
		/*
		 * Check if player can plant any bomb at all
		 */
		if (player.canPlantBomb()) {
			
			/*
			 * If there is any bomb on this place
			 * One bomb, one block
			 */
			for (Bomb b : map.getBombs())
				if (player.getX() > GameMap.getTilePosition(b.getX()) * GameMap.BLOCK_SIZE &&
					player.getX() < (GameMap.getTilePosition(b.getX()) + 1) * GameMap.BLOCK_SIZE &&
					player.getY() > GameMap.getTilePosition(b.getY()) * GameMap.BLOCK_SIZE &&
					player.getY() < (GameMap.getTilePosition(b.getY()) + 1) * GameMap.BLOCK_SIZE) {
					return;
				}

			player.plantBomb();
			map.addBomb(player.getX(), player.getY(), System.currentTimeMillis(), timer,
																				player.getBombArea());
			player.setOnBomb(true);
		}
	}
	
	public synchronized void setMap(GameMap map) {
		this.map = map;
	}
}

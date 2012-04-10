package pl.edu.pw.elka.mmarkiew.model;

import java.util.ArrayList;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.EntityFactory;
import pl.edu.pw.elka.mmarkiew.model.entities.GameEntities;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.Bomb;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.DestroyingBrick;
import pl.edu.pw.elka.mmarkiew.model.map.BlockFactory;
import pl.edu.pw.elka.mmarkiew.model.map.BlockHolder;
import pl.edu.pw.elka.mmarkiew.model.map.BrickBlock;
import pl.edu.pw.elka.mmarkiew.model.map.EmptyBlock;
import pl.edu.pw.elka.mmarkiew.model.map.GameBlock;
import pl.edu.pw.elka.mmarkiew.model.map.StoneBlock;

public class BombCalculator {
	private GameMap map;
	
	public BombCalculator(GameMap map) {
		this.map = map;
	}
	
	public void setMap(GameMap map) {
		this.map = map;
	}

	public synchronized void calculateBombs() {
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

	private void explodeBomb(Bomb b) {
		BlockHolder blocks = map.getBlockHolder();
		int xR = GameMap.getTilePosition(b.getX());
		int yR = GameMap.getTilePosition(b.getY());
		int x, y;


		map.addEnemy(EntityFactory.createEntity(GameEntities.EXPLOSION, xR, yR));
		if (blocks.getBlock(xR, yR) instanceof BrickBlock) {
			blocks.setBlock(BlockFactory.createElement(GameBlock.EMPTY), xR, yR);
			Entity blen = EntityFactory.createEntity(GameEntities.DESTROYING_BRICK, xR, yR);
			map.addEnemy(blen);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			x = xR;
			y = yR;
			
			destroyedBrickAnimation:
			for (int j = 1; j <= b.getArea(); j++) {
				switch (i) {
					case 0: x = xR;		y = yR - j; break;
					case 1: x = xR;		y = yR + j; break;
					case 2: x = xR - j; y = yR;		break;
					case 3: x = xR + j; y = yR;		break;
				}
				if (blocks.getBlock(x, y) instanceof StoneBlock)
					break;
				else if (blocks.getBlock(x, y) instanceof BrickBlock) {
					blocks.setBlock(BlockFactory.createElement(GameBlock.EMPTY), x, y);
					Entity blen = EntityFactory.createEntity(GameEntities.DESTROYING_BRICK, x, y);
					map.addEnemy(blen);
					break;
				} else if (blocks.getBlock(x, y) instanceof EmptyBlock) {

					Entity blen = EntityFactory.createEntity(GameEntities.EXPLOSION, x, y);
					for (Entity e : map.getEntities()) {
						if (e instanceof DestroyingBrick) {
							if (CollisionDetector.isEntitiesCollision(blen, e))
								break destroyedBrickAnimation;
						}
					}
					map.addEnemy(blen);
				}
			}
		}
	}

	public void plantBomb(long timer) {
		if (map.getPlayer().canPlantBomb()) {
			for (Bomb b : map.getBombs())
				if (map.getPlayer().getX() > GameMap.getTilePosition(b.getX()) * GameMap.BLOCK_SIZE &&
					map.getPlayer().getX() < (GameMap.getTilePosition(b.getX()) + 1) * GameMap.BLOCK_SIZE &&
					map.getPlayer().getY() > GameMap.getTilePosition(b.getY()) * GameMap.BLOCK_SIZE &&
					map.getPlayer().getY() < (GameMap.getTilePosition(b.getY()) + 1) * GameMap.BLOCK_SIZE) {
					return;
				}

			map.getPlayer().plantBomb();
			map.addBomb(map.getPlayer().getX(), map.getPlayer().getY(), System.currentTimeMillis(), timer,
																				map.getPlayer().getBombArea());
			map.getPlayer().setOnBomb(true);
		}
	}
}

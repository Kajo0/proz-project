package pl.edu.pw.elka.mmarkiew.model;

import java.util.ArrayList;
import pl.edu.pw.elka.mmarkiew.model.entities.BlockEntity;
import pl.edu.pw.elka.mmarkiew.model.entities.Bomb;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
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

	public void calculateBombs() {
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
		
		for (int i = 0; i < 4; i++) {
			x = xR;
			y = yR;
			for (int j = 1; j <= map.getPlayer().getBombArea(); j++) {
				switch (i) {
					case 0: x = xR;		y = yR - j; break;
					case 1: x = xR;		y = yR + j; break;
					case 2: x = xR - j; y = yR;		break;
					case 3: x = xR + j; y = yR;		break;
				}
				if (blocks.getBlock(x, y) instanceof StoneBlock)
					break;
				else if (blocks.getBlock(x, y) instanceof BrickBlock) {
					blocks.setBlock(BlockFactory.createElement(GameBlock.SPACE), x, y);
					break;
				} else if (blocks.getBlock(x, y) instanceof EmptyBlock) {
					Entity blen = new BlockEntity(x, y);
					map.addEnemy(blen);
					for (Entity e : map.getEntities()) {
						if (CollisionDetector.isEntitiesCollision(e, blen)) {
							if (e instanceof Player) {
								e.setX((float) map.getPlayerStartPosition().getX());
								e.setY((float) map.getPlayerStartPosition().getY());
							}
							e.setDead();
						}
					}
				}

			}
		}
	}

	public void plantBomb() {
		if (map.getPlayer().canPlantBomb()) {
			map.getPlayer().plantBomb();
			map.addBomb(map.getPlayer().getX(), map.getPlayer().getY(), System.currentTimeMillis());
		}
	}
}

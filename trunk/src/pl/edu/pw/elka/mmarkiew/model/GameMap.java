package pl.edu.pw.elka.mmarkiew.model;

import java.awt.Point;
import java.util.LinkedList;
import pl.edu.pw.elka.mmarkiew.model.entities.Exit;
import pl.edu.pw.elka.mmarkiew.model.entities.ExplosionEntity;
import pl.edu.pw.elka.mmarkiew.model.entities.Bomb;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.EntityFactory;
import pl.edu.pw.elka.mmarkiew.model.entities.GameEntities;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.map.BlockElement;
import pl.edu.pw.elka.mmarkiew.model.map.BlockHolder;

public class GameMap {
	public final static int BLOCK_SIZE = 40; 
	private Player player;
	private LinkedList<Entity> enemies;
	public  BlockHolder map;
	private int widthBlocks;
	private int heightBlocks;
	private Point playerStartPosition;
	private LinkedList<Bomb> bombs;
	private LinkedList<Entity> bonuses;
	
	public GameMap(Player player, int widthBlocks, int heightBlocks) {
		this.player = player;
		this.widthBlocks = widthBlocks;
		this.heightBlocks = heightBlocks;
		this.map = new BlockHolder(widthBlocks, heightBlocks);
		this.enemies = new LinkedList<Entity>();
		this.playerStartPosition = new Point(0, 0);
		this.bombs = new LinkedList<Bomb>();
		this.bonuses = new LinkedList<Entity>();
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public LinkedList<Entity> getEnemies() {
		return enemies;
	}

	public void addEnemy(Entity enemy) {
		if (enemy != null)
			this.enemies.add(enemy);
	}
	
	public void removeEnemy(Entity enemy) {
		for (Entity e : enemies) {
			if (e == enemy) {
				enemies.remove(e);
				break;
			}
		}
	}

	public void setBlock(BlockElement block, int x, int y) {
		map.setBlock(block, x, y);
	}
	
	public int getWidth() {
		return widthBlocks * GameMap.BLOCK_SIZE;
	}
	
	public int getHeight() {
		return heightBlocks * GameMap.BLOCK_SIZE;
	}
	
	public int getWidthBlocks() {
		return widthBlocks;
	}
	
	public int getHeightBlocks() {
		return heightBlocks;
	}
	
	public static int getTilePosition(float xy) {
		return (int) (xy / GameMap.BLOCK_SIZE);
	}

	public BlockHolder getBlockHolder() {
		return map;
	}
	
	public LinkedList<Entity> getEntities() {
		LinkedList<Entity> l = new LinkedList<Entity>(enemies);
		l.addFirst(player);
		l.addAll(bombs);
		return l;
	}

	public void setPlayerStartPosition(float x, float y) {
		this.playerStartPosition.setLocation(x, y);
	}
	
	public Point getPlayerStartPosition() {
		return this.playerStartPosition;
	}

	public LinkedList<Bomb> getBombs() {
		return bombs;
	}

	public void addBomb(float x, float y, long plantTime) {
		this.bombs.add((Bomb) EntityFactory.createEntity(GameEntities.BOMB, x, y, plantTime));
	}
	
	public void removeBomb(Bomb bomb) {
		for (Bomb b : bombs)
			if (b.equals(bomb)) {
				bombs.remove(b);
				return;
			}
	}

	public void removeExplosions() {
		LinkedList<Entity> toRemove = new LinkedList<Entity>();
		for (Entity e : enemies)
			if (e instanceof ExplosionEntity)
				toRemove.add(e);
		for (Entity e : toRemove)
			removeEnemy(e);

		toRemove.clear();
		for (Entity e : bombs)
			if (e instanceof Bomb)
				toRemove.add(e);
		for (Entity e : toRemove)
			removeBomb((Bomb) e);
		
	}

	public LinkedList<Entity> getBonuses() {
		return this.bonuses;
	}
	
	public void addBonus(Entity bonus) {
		if (bonus != null) {
			if (!this.bonuses.isEmpty()) {
				if (bonus instanceof Exit) {
					if (this.bonuses.get(0) instanceof Exit) {
						this.bonuses.set(0, bonus);
					} else this.bonuses.addFirst(bonus);
					return;
				}
			}
			this.bonuses.add(bonus);
		}
	}
	
	public void removeBonus(Entity bonus) {
		for (Entity b : bonuses) {
			if (b == bonus) {
				bonuses.remove(b);
				break;
			}
		}
	}
}

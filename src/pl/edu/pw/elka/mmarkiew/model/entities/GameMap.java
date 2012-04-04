package pl.edu.pw.elka.mmarkiew.model.entities;

import java.util.LinkedList;
import pl.edu.pw.elka.mmarkiew.model.map.BlockElement;

public class GameMap {
	public final static int BLLOCK_SIZE = 40; 
	private Player player;
	private LinkedList<Entity> enemies;
	public  BlockElement[][] map;
	private int widthBlocks;
	private int heightBlocks;
	
	public GameMap(Player player, int widthBlocks, int heightBlocks) {
		this.player = player;
		this.widthBlocks = widthBlocks;
		this.heightBlocks = heightBlocks;
		this.map = new BlockElement[widthBlocks][heightBlocks];
		this.enemies = new LinkedList<Entity>();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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
		map[x][y] = block;
	}
	
	public int getWidth() {
		return widthBlocks * GameMap.BLLOCK_SIZE;
	}
	
	public int getHeight() {
		return heightBlocks * GameMap.BLLOCK_SIZE;
	}
	
	public int getWidthBlocks() {
		return widthBlocks;
	}
	
	public int getHeightBlocks() {
		return heightBlocks;
	}
	
	public static int getTilePosition(float xy) {
		return (int) (xy / GameMap.BLLOCK_SIZE);
	}

	public BlockElement[][] getBlockTable() {
		return map;
	}
	
	public LinkedList<Entity> getEntities() {
		LinkedList<Entity> l = new LinkedList<Entity>(enemies);
		l.addFirst(player);
		return l;
	}
}

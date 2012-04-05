package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Point;
import java.util.LinkedList;
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
	
	public GameMap(Player player, int widthBlocks, int heightBlocks) {
		this.player = player;
		this.widthBlocks = widthBlocks;
		this.heightBlocks = heightBlocks;
		this.map = new BlockHolder(widthBlocks, heightBlocks);
		this.enemies = new LinkedList<Entity>();
		this.playerStartPosition = new Point(0, 0);
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
		return l;
	}

	public void setPlayerStartPosition(float x, float y) {
		this.playerStartPosition.setLocation(x, y);
	}
	
	public Point getPlayerStartPosition() {
		return this.playerStartPosition;
	}
}

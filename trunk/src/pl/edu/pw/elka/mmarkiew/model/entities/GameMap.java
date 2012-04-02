package pl.edu.pw.elka.mmarkiew.model.entities;

import java.util.LinkedList;

import pl.edu.pw.elka.mmarkiew.model.map.BlockElement;

public class GameMap {
	// Jak TileMap czyli mapa i jej obiekty te¿ spritey
	public final static int BLLOCK_SIZE = 40; 
	private Entity player;
	private LinkedList<Entity> enemies;
	public  BlockElement[][] map;
	private int widthBlocks;
	private int heightBlocks;
	
	public GameMap(int width, int height) {
		this.widthBlocks = width;
		this.heightBlocks = height;
		this.map = new BlockElement[width][height];
		this.enemies = new LinkedList<Entity>();
	}

	public Entity getPlayer() {
		return player;
	}

	public void setPlayer(Entity player) {
		this.player = player;
	}
	
	public void setBlock(BlockElement block, int x, int y) {
		map[x][y] = block;
	}
	
	public BlockElement getBlock(int x, int y) {
		if (x < 0 || x > getWidth() || y < 0 || y > getHeight())
			return null;
		else return map[x][y];
	}
	
	public int getWidth() {
		return widthBlocks;
	}
	
	public int getHeight() {
		return heightBlocks;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public GameMap clone() {
		GameMap tmp = new GameMap(widthBlocks, heightBlocks);
		tmp.setPlayer(player);
		tmp.enemies = (LinkedList<Entity>) enemies.clone();
		tmp.map = map.clone();
		
		return tmp;
	}

}

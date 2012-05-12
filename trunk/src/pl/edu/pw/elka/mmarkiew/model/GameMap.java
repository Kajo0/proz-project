package pl.edu.pw.elka.mmarkiew.model;

import java.awt.Point;
import java.util.LinkedList;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.EntityFactory;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.Bonus;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.Exit;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.Bomb;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.ExplosionEntity;
import pl.edu.pw.elka.mmarkiew.model.map.BlockElement;
import pl.edu.pw.elka.mmarkiew.model.map.BlockHolder;

/**
 * Contain information about map of game
 * @author Acer
 *
 */
public class GameMap {
	/** Size of one block on map */
	public final static int BLOCK_SIZE = 40;
//	/** Size of preferred block size on map */
//	public final static int PREFFERED_BLOCK_SIZE = 40;
	private Player player;
	private Point playerStartPosition;
	public  BlockHolder map;
	private int widthInBlocks;
	private int heightInBlocks;
	private LinkedList<Entity> enemies;
	private LinkedList<Bomb> bombs;
	private LinkedList<Bonus> bonuses;
	private LinkedList<Exit> exits;
	
	/**
	 * Creates new map of game
	 * @param player - Playing player
	 * @param widthBlocks - Width in blocks
	 * @param heightBlocks - Height in blocks
	 */
	public GameMap(final Player player, int widthBlocks, int heightBlocks)
	{
		this.player = player;
		this.playerStartPosition = new Point(0, 0);
		this.map = new BlockHolder(widthBlocks, heightBlocks);
		this.widthInBlocks = widthBlocks;
		this.heightInBlocks = heightBlocks;
		this.enemies = new LinkedList<Entity>();
		this.bombs = new LinkedList<Bomb>();
		this.bonuses = new LinkedList<Bonus>();
		this.exits = new LinkedList<Exit>();
	}

	public synchronized void setBlock(final BlockElement block, int x, int y)
	{
		if (block != null)
		{
			map.setBlock(block, x, y);
		}
	}
	
	/**
	 * Add enemy into enemy list
	 * @param enemy - Enemy entity
	 */
	public synchronized void addEnemy(final Entity enemy)
	{
		if (enemy != null)
		{
			enemies.add(enemy);
		}
	}
	
	/**
	 * Removes enemy from map
	 * @param enemy - Enemy to remove
	 */
	public synchronized void removeEnemy(final Entity enemy)
	{
		for (Entity e : enemies)
		{
			if (e == enemy)
			{
				enemies.remove(e);
				break;
			}
		}
	}

	/**
	 * Creates and adds bomb into map
	 * @param x	- x position
	 * @param y - y position
	 * @param plantTime - Time when the bomb was planted
	 * @param timer - Bomb timer, when it'll explode
	 * @param area - Bomb area, how far it can reach
	 */
	public synchronized void addBomb(float x, float y, long plantTime, long timer, int area)
	{
		bombs.add(EntityFactory.createBombEntity(x, y, plantTime, timer, area));
	}
	
	/**
	 * Removes bomb from map
	 * @param bomb - Bomb to remove
	 */
	public synchronized void removeBomb(final Bomb bomb)
	{
		for (Bomb b : bombs)
		{
			if (b == bomb)
			{
				bombs.remove(b);
				return;
			}
		}
	}

	/**
	 * Removes bombs and explosions from map
	 */
	public synchronized void removeExplosions()
	{
		LinkedList<Entity> toRemove = new LinkedList<Entity>();
		
		// First remove explosions
		for (Entity e : enemies)
		{
			if (e instanceof ExplosionEntity)
			{
				toRemove.add(e);
			}
		}
				
		for (Entity e : toRemove)
		{
			removeEnemy(e);
		}
				
		toRemove.clear();
		
		// Next remove bombs
		for (Entity b : bombs)
		{
			if (b instanceof Bomb)
			{
				toRemove.add(b);
			}
		}
				
		for (Entity b : toRemove)
		{
			removeBomb((Bomb) b);
		}
	}

	/**
	 * Adds bonus into map<br>
	 * if it's exit add to exits 
	 * @param bonus - Bonus to add
	 */
	public synchronized void addBonus(final Bonus bonus)
	{
		if (bonus != null)
		{
			if (!(bonus instanceof Exit))
			{
				bonuses.add(bonus);
			}
			else
			{
				addExit((Exit) bonus);
			}
		}
	}
	
	/**
	 * Removes bonus or exit from map
	 * @param bonus - Bonus to remove
	 */
	public synchronized void removeBonus(final Bonus bonus)
	{
		if (!(bonus instanceof Exit))
		{
			for (Bonus b : bonuses)
			{
				if (b == bonus)
				{
					bonuses.remove(b);
					return;
				}
			}
		}
		else
		{
			for (Exit e : exits)
			{
				if (e == bonus)
				{
					exits.remove(e);
					return;
				}
			}
		}
	}
	
	/**
	 * Adds exit into map
	 * @param exit
	 */
	public synchronized void addExit(final Exit exit)
	{
		if (exit != null)
		{
			exits.add(exit);
		}
	}

	/**
	 * Gets alive enemy entities from map
	 * @return list containing both enemies and bombs
	 */
	public LinkedList<Entity> getEntities()
	{
		LinkedList<Entity> l = new LinkedList<Entity>(enemies);
		l.addAll(bombs);
		return l;
	}

	public Player getPlayer()
	{
		return player;
	}
	
	public synchronized void setPlayer(final Player player)
	{
		this.player = player;
	}

	public Point getPlayerStartPosition()
	{
		return playerStartPosition;
	}
	
	public void setPlayerStartPosition(float x, float y)
	{
		playerStartPosition.setLocation(x, y);
	}

	public int getWidth()
	{
		return widthInBlocks * GameMap.BLOCK_SIZE;
	}
	
	public int getHeight()
	{
		return heightInBlocks * GameMap.BLOCK_SIZE;
	}
	
	public int getWidthInBlocks()
	{
		return widthInBlocks;
	}
	
	public int getHeightInBlocks()
	{
		return heightInBlocks;
	}
	
	public BlockHolder getBlockHolder()
	{
		return map;
	}
	
	public LinkedList<Entity> getEnemies()
	{
		return enemies;
	}

	public LinkedList<Bomb> getBombs()
	{
		return bombs;
	}
	
	public LinkedList<Bonus> getBonuses()
	{
		return bonuses;
	}

	public LinkedList<Exit> getExits()
	{
		return exits;
	}

	/**
	 * Calculate on which tile is given position
	 * @param xy - x or y position
	 * @return tile number position
	 */
	public static int getTilePosition(float xy)
	{
		return (int) (xy / GameMap.BLOCK_SIZE);
	}
	
	/**
	 * Calculate center of tile from given position
	 * @param xy - x or y position
	 * @return center position on tile
	 */
	public static float getTileCenterFromPosition(float xy)
	{
		return ((int) (xy / GameMap.BLOCK_SIZE)) * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2;
	}
	
	/**
	 * Calculate center of tile from given tile number
	 * @param ij - i or j tile position
	 * @return center position on given tile
	 */
	public static float getPositionCenterFromTile(int ij)
	{
		return ij * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2;
	}
}

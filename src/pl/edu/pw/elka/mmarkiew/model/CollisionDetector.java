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

/**
 * Everything with wide concept of collisions
 * @author Kajo
 *
 */
public class CollisionDetector {
	/** Actual game map */
	private GameMap map;

	/**
	 * Take care about collisions
	 * 
	 * @param map
	 */
	public CollisionDetector(final GameMap map)
	{
		this.map = map;
	}
	
	/**
	 * Detect all of collisions on the actual map
	 */
	public void detectCollision()
	{
		// Check player collision with blocks
		checkEntityBlockCollision(map.getPlayer(), map.getBlockHolder());
		
		// Check enemies and bombs collisions with blocks
		for (Entity e : map.getEntities())
			checkEntityBlockCollision(e, map.getBlockHolder());
		
		/*
		 * Check player collisions with enemies, bombs, bonuses
		 * At first simply spawn protection 1 sec pause time included
		 */
		if (!map.getPlayer().isImmortal() &&
				System.currentTimeMillis() - map.getPlayer().getDieTime() > map.getPlayer().getDyingTime() + 1000)
		{
			checkPlayerEntityCollision(map.getEnemies());
		}
		
		// Check player's collisions with bombs and next with bonuses
		checkPlayerBombCollision(map.getBombs());
		checkPlayerBonusCollision(map.getBonuses());
		
		// Check enemies and bombs collisions
		checkEnemiesCollision(map.getEntities());
	}

	/**
	 * Checks if is collisions with map blocks and stop entities not to go further
	 * 
	 * @param entity - Entity to check collision
	 * @param blocks - Block to check collision with
	 */
	private void checkEntityBlockCollision(final Entity entity, final BlockHolder blocks)
	{
		float xPlayerPosition = entity.getX();
		float yPlayerPosition = entity.getY();
		final int xTilePlayerPosition = GameMap.getTilePosition(xPlayerPosition);
		final int yTilePlayerPosition = GameMap.getTilePosition(yPlayerPosition);
		final float dividedAnimWidth = entity.getWidth() / 2;
		final float dividedAnimHeight = entity.getHeight() / 2;
		
		// Explosion entities like destroyed block or explosion has no collisions with blocks 
		if (entity instanceof ExplosionEntity)
			return;

		/*
		 * Checks collisions in sequence: (from->to)
		 * down->up, up->down, right->left, left->right
		 * 
		 * Checked method by trial and mistakes
		 */
		int i1 = 0, i2 = 0, i3 = 0;
		float f1 = 0, f2 = 0;
		boolean comparison = false;
		
		for (int i = 0; i < 4; ++i)
		{
			switch (i)
			{
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
			
			if (! (blocks.getBlock(i1, i2) instanceof EmptyBlock) )
			{
				if (i % 2 == 0)
				{
					comparison = (f1 + f2 < i3 * GameMap.BLOCK_SIZE);
				}
				else
				{
					comparison = (f1 + f2 > i3 * GameMap.BLOCK_SIZE);
				}
				
				if (comparison)
				{
					if ( i < 2 )
					{
						entity.collisionY();
						entity.setY(i3 * GameMap.BLOCK_SIZE - f2);
					}
					else
					{
						entity.collisionX();
						entity.setX(i3 * GameMap.BLOCK_SIZE - f2);
					}
				}
			}
		}
		
		
		// helpers
		final float oldX = xPlayerPosition;
		final float oldY = yPlayerPosition;
		float newX = xPlayerPosition = entity.getX();
		float newY = yPlayerPosition = entity.getY();
		
		/*
		 * Checks Up-Left, Up-Right, Down-Left, Down-Right collisions
		 * It could be probably better but I don't know how in this case
		 * It's just packed version of separate made 4 different 'ifs() if()..'
		 * 
		 * It's checked when it's needed to check
		 */
		if (	yPlayerPosition - dividedAnimHeight < (yTilePlayerPosition)		* GameMap.BLOCK_SIZE	||
				yPlayerPosition + dividedAnimHeight > (yTilePlayerPosition + 1)	* GameMap.BLOCK_SIZE	||
				xPlayerPosition - dividedAnimWidth < (xTilePlayerPosition)		* GameMap.BLOCK_SIZE	||
				xPlayerPosition + dividedAnimWidth > (xTilePlayerPosition + 1)	* GameMap.BLOCK_SIZE)
		{
			
			// helpers
			int l1 = 0, l2 = 0, l3 = 0, l4 = 0, l5 = 0, l6 = 0, l7 = 0, l8 = 0, l9 = 0, l10 = 0, l11 = 0;
			float d1 = 0, d2 = 0, d3 = 0, d4 = 0, d5 = 0, d6 = 0, d7 = 0, d8 = 0;
			
			for (int i = 0; i < 4; ++i)
			{
				switch (i)
				{
					case 0: l1 = xTilePlayerPosition;	l2 = yTilePlayerPosition-1;	l3 = yTilePlayerPosition;
							l4 = xTilePlayerPosition-1;	l5 = yTilePlayerPosition-1;	l6 = xTilePlayerPosition;
							l7 = xTilePlayerPosition;	l8 = xTilePlayerPosition+1;	l9 = yTilePlayerPosition-1;
							l10= xTilePlayerPosition+1;	l11 = xTilePlayerPosition+1;
							
							d1 = yPlayerPosition;		d2 = - dividedAnimHeight;	d3 = xPlayerPosition;
							d4 = - dividedAnimWidth;	d5 = dividedAnimWidth;		d6 = xPlayerPosition;
							d7 = dividedAnimWidth;		d8 = - dividedAnimWidth;
							break;
							
					case 1: l1 = xTilePlayerPosition;	l2 = yTilePlayerPosition+1;	l3 = yTilePlayerPosition+1;
							l4 = xTilePlayerPosition-1;	l5 = yTilePlayerPosition+1;	l6 = xTilePlayerPosition;
							l7 = xTilePlayerPosition;	l8 = xTilePlayerPosition+1;	l9 = yTilePlayerPosition+1;
							l10= xTilePlayerPosition+1;	l11 = xTilePlayerPosition+1;
							
							d1 = yPlayerPosition;		d2 = dividedAnimHeight;		d3 = xPlayerPosition;
							d4 = - dividedAnimWidth;	d5 = dividedAnimWidth;		d6 = xPlayerPosition;
							d7 = dividedAnimWidth;		d8 = - dividedAnimWidth;
							break;
							
					case 2: l1 = xTilePlayerPosition-1;	l2 = yTilePlayerPosition;	l3 = xTilePlayerPosition;
							l4 = xTilePlayerPosition-1;	l5 = yTilePlayerPosition-1;	l6 = yTilePlayerPosition;
							l7 = yTilePlayerPosition;	l8 = xTilePlayerPosition-1;	l9 = yTilePlayerPosition+1;
							l10= yTilePlayerPosition+1;	l11 = yTilePlayerPosition+1;
							
							d1 = xPlayerPosition;		d2 = - dividedAnimWidth;	d3 = yPlayerPosition;
							d4 = - dividedAnimHeight;	d5 = dividedAnimHeight;		d6 = yPlayerPosition;
							d7 = dividedAnimHeight;		d8 = - dividedAnimHeight;
							break;
							
					case 3: l1 = xTilePlayerPosition+1;	l2 = yTilePlayerPosition;	l3 = xTilePlayerPosition+1;
							l4 = xTilePlayerPosition+1;	l5 = yTilePlayerPosition-1;	l6 = yTilePlayerPosition;
							l7 = yTilePlayerPosition;	l8 = xTilePlayerPosition+1;	l9 = yTilePlayerPosition+1;
							l10= yTilePlayerPosition+1;	l11 = yTilePlayerPosition+1;
							
							d1 = xPlayerPosition;		d2 = dividedAnimWidth;		d3 = yPlayerPosition;
							d4 = - dividedAnimHeight;	d5 = dividedAnimHeight;		d6 = yPlayerPosition;
							d7 = dividedAnimHeight;		d8 = - dividedAnimHeight;
							break;
				}
				
				if (i % 2 == 0)
				{
					comparison = (blocks.getBlock(l1, l2) instanceof EmptyBlock && d1 + d2 < l3 * GameMap.BLOCK_SIZE);
				}
				else
				{
					comparison = (blocks.getBlock(l1, l2) instanceof EmptyBlock && d1 + d2 > l3 * GameMap.BLOCK_SIZE);
				}
				
				if (comparison)
				{
					if (! (blocks.getBlock(l4, l5) instanceof EmptyBlock) && d3 + d4 < (l6) * GameMap.BLOCK_SIZE)
					{
						if (i < 2)
						{
							newX = l7 * GameMap.BLOCK_SIZE + d5;
						}
						else
						{
							newY = l7 * GameMap.BLOCK_SIZE + d5;
						}
					}
					else
					{
						if (! (blocks.getBlock(l8, l9) instanceof EmptyBlock) && d6 + d7 > (l10) * GameMap.BLOCK_SIZE)
						{
							if (i < 2)
							{
								newX = (l11) * GameMap.BLOCK_SIZE + d8;
							}
							else
							{
								newY = (l11) * GameMap.BLOCK_SIZE + d8;
							}
						}
					}
				}
			}
			
			// Check which collision is smaller and this collision apply can't do both at once
			float minX = Math.abs(xPlayerPosition - newX);
			float minY = Math.abs(yPlayerPosition - newY);
			
			if (minX < minY && (Math.abs(newX - oldX) > GameMap.BLOCK_SIZE / 20))
			{
				entity.collisionX();
				entity.setX(newX);
			}
			else if (minX > minY && (Math.abs(newY - oldY) > GameMap.BLOCK_SIZE / 20))
			{
				entity.collisionY();
				entity.setY(newY);
			}
		}
	}
	
	/**
	 * Checks player-entities collisions
	 * 
	 * @param entities - Entities with which is need to be checked collision
	 */
	private void checkPlayerEntityCollision(final LinkedList<Entity> entities)
	{
		final Player player = map.getPlayer();
		
		for (Entity e : entities)
		{
			// If entity is dead, do not do nothing
			if (e.isAlive() && isEntitiesCollision(player, e))
			{
				/*
				 * if there is collision with alive enemy or explosion
				 * set player dead and return, model take care about player
				 */
				if (e instanceof Enemy || e instanceof ExplosionEntity)
				{
					player.setDead();
					Model.sound.playSound(SoundManager.KILL);
					return;
				} 
					// If there is destroyed brick, just stop player
				else if (e instanceof DestroyingBrick)
				{
					checkPlayerStopCollision(player, e);
				}
			}
		}
	}

	/**
	 * Checks player - bombs collisions<br>
	 * If player is standing on any bomb, bombs should'nt block him
	 * 
	 * @param bombs - List of planted bombs
	 */
	private void checkPlayerBombCollision(final LinkedList<Bomb> bombs)
	{
		final Player player = map.getPlayer();
		int onAnyBombStanding = 0;
		
		for (Bomb b : bombs)
		{
			if (isEntitiesCollision(player, b))
			{
				if (!player.isOnBomb())
				{
					checkPlayerStopCollision(player, b);
					
					if (player.isBouncingBomb())
					{
						b.collisionX();
						b.collisionY();
					}
				}
			}
			else
			{
				++onAnyBombStanding;
			}
		}
		
		// If player isn't standing on any bomb, block him down with bombs collision
		if (onAnyBombStanding == bombs.size())
		{
			player.setOnBomb(false);
		}
	}

	/**
	 * Checks if player collide with object which can stop him<br><br>
	 * It depends on player trajectory to stop him correctly
	 * 
	 * @param player - Player
	 * @param entity - Collide entity potential stopping
	 */
	private void checkPlayerStopCollision(final Player player, final Entity entity)
	{
		final float xPlayerPosition = player.getX();
		final float yPlayerPosition = player.getY();
		final float xEntityPosition = entity.getX();
		final float yEntityPosition = entity.getY();
		final float dividedPlayerWidth = player.getWidth() / 2;
		final float dividedPlayerHeight = player.getHeight() / 2;
		final float dividedEntityWidth = entity.getWidth() / 2;
		final float dividedEntityHeight = entity.getHeight() / 2;
		
		// Is there one direction of player movement
		boolean oneDirection = false;
		// Is he moving horizontal or vertical 
		boolean horizontalCollision = false;
		
		final boolean isBombEntity = (entity instanceof Bomb);
		
		/*
		 * If there are 2 directions of player movement
		 * Check which collision check first
		 */
		if (player.getXVelocity() != 0 && player.getYVelocity() != 0)
		{
			final float deltaX = Math.abs(xPlayerPosition - xEntityPosition);
			final float deltaY = Math.abs(yPlayerPosition - yEntityPosition);
			
			if (deltaX > deltaY)
			{
				horizontalCollision = false;
			}
			else
			{
				horizontalCollision = true;
			}
		}
		else
		{
			oneDirection = true;
		}
		
		/*
		 * Checks collisions in sequence: (from->to)
		 * down->up, up->down, right->left, left->right
		 * 
		 * Calculated method
		 * 
		 * If player moving only one direction check 2  'ifs'
		 * otherwise thanks to previous calculations check smart
		 * 
		 * If player can moves bombs and there is a bomb stopping,
		 * bounce it away -> set it player speed
		 */
		
		if (player.getYVelocity() < 0 && (horizontalCollision || oneDirection))
		{
			player.collisionY();
			player.setY(yEntityPosition + dividedEntityHeight + dividedPlayerHeight);

			if (isBombEntity && player.isBouncingBomb())
			{
				if (entity.getYVelocity() == 0)
				{
					entity.setYVelocity(player.getMaxVelocity());
				}
			}
		}
		
		if (player.getYVelocity() > 0 && (horizontalCollision || oneDirection))
		{
			player.collisionY();
			player.setY(yEntityPosition - dividedEntityHeight - dividedPlayerHeight);
			
			if (isBombEntity && player.isBouncingBomb())
			{
				if (entity.getYVelocity() == 0)
				{
					entity.setYVelocity(-player.getMaxVelocity());
				}
			}
		}
		
		if (player.getXVelocity() < 0 && (!horizontalCollision || oneDirection))
		{
			player.collisionX();
			player.setX(xEntityPosition + dividedPlayerWidth + dividedEntityWidth);
			
			if (isBombEntity && player.isBouncingBomb())
			{
				if (entity.getXVelocity() == 0)
				{
					entity.setXVelocity(player.getMaxVelocity());
				}
			}
		}
		
		if (player.getXVelocity() > 0 && (!horizontalCollision || oneDirection))
		{
			player.collisionX();
			player.setX(xEntityPosition - dividedEntityWidth - dividedPlayerWidth);
			
			if (isBombEntity && player.isBouncingBomb())
			{
				if (entity.getXVelocity() == 0)
				{
					entity.setXVelocity(-player.getMaxVelocity());
					
				}
			}
		}
	}

	/**
	 * Checks player - bonus collisions<br>
	 * If there is such collision, add bonus to collide player
	 * 
	 * @param bonuses - list of active bonuses
	 */
	private void checkPlayerBonusCollision(final LinkedList<Bonus> bonuses)
	{
		final Player player = map.getPlayer();
		
		// If there is collision, add bonus to player and kill bonus
		for (Bonus b : bonuses)
		{
			if (b.isAlive() && isEntitiesCollision(player, b))
			{
				b.bonusideEntity(player);
				Model.sound.playSound(SoundManager.BONUS);
				b.setDead();
			}
		}
	}

	/**
	 * Checks collision between enemies and bombs
	 * 
	 * @param enemies - List of enemies
	 */
	private void checkEnemiesCollision(final LinkedList<Entity> enemies)
	{
		final Entity[] entities = enemies.toArray(new Entity[enemies.size()]);

		for (int i = 0; i < entities.length - 1; ++i)
		{
			for (int j = i + 1; j < entities.length; ++j)
			{
				// If though one is dead continue
				if (!entities[i].isAlive() || !entities[j].isAlive())
				{
					continue;
				}
				else if (isEntitiesCollision(entities[i], entities[j]))
				{
					// Explosions kill enemies or
					// Explode bomb by bomb explosion or
					if ( entities[i] instanceof ExplosionEntity &&
													(entities[j] instanceof Enemy || entities[j] instanceof Bomb) )
					{
						entities[j].setDead();
						continue;
					}
					
					if ( entities[j] instanceof ExplosionEntity &&
													(entities[i] instanceof Enemy || entities[i] instanceof Bomb) )
					{
						entities[i].setDead();
						continue;
					}
					
					// Other collisions between enemies
					entities[i].collisionX();
					entities[i].collisionY();
					entities[j].collisionX();
					entities[j].collisionY();
				}
			}
		}
	}
	
	/**
	 * Checks is there any collisions between entities
	 * 
	 * @param first - First entity
	 * @param second - Second entity
	 * @return true if collision, false otherwise<br>also false if first == second
	 */
	public static boolean isEntitiesCollision(final Entity first, final Entity second)
	{
		if (first == second)
		{
			return false;
		}
		
		return (first.getX() + first.getWidth() / 2 - second.getX() + second.getWidth() / 2 > 0		&&
				first.getX() - first.getWidth() / 2 - second.getX() - second.getWidth() / 2 < 0		&&
				first.getY() + first.getHeight() / 2 - second.getY() + second.getHeight() / 2 > 0	&&
				first.getY() - first.getHeight() / 2 - second.getY() - second.getHeight() / 2 < 0);
	}

	public void setMap(final GameMap map)
	{
		this.map = map;
	}
}

package pl.edu.pw.elka.mmarkiew.model;

import java.util.LinkedList;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.GameEntities;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.BouncingBomb;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.Exit;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.IncreaseBombAmountBonus;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.IncreaseBombAreaBonus;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.IncreaseLifeNumberBonus;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.SpeedBonus;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.BaloonEnemy;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.Bomb;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.DestroyingBrick;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.ExplosionEntity;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.HeliumEnemy;
import pl.edu.pw.elka.mmarkiew.model.map.BlockHolder;
import pl.edu.pw.elka.mmarkiew.model.map.BrickBlock;
import pl.edu.pw.elka.mmarkiew.model.map.GameBlock;
import pl.edu.pw.elka.mmarkiew.model.map.StoneBlock;

/**
 * Contains information about game map<br>
 * Mostly images
 * @author Kajo
 *
 */
public class MapToDraw {
	public final static int blockSize = GameMap.BLOCK_SIZE;
	
	private final int widthInBlocks;
	private final int heightInBlocks;
	private final boolean paused;
	private final boolean started;
	private final boolean win;
	private final boolean over;
	private final BlockInformation blocks;
	private final EntityInformation entities;
	private final EntityInformation bonuses;

	/**
	 * Creates object containing every needed information to paint actual map
	 * 
	 * @param blockHolder - BlockHolder map object
	 * @param entities - List contains player and enemy entities
	 * @param bonuses - List of bonuses
	 * @param widthBlocks - Width in blocks
	 * @param heightBlocks - Height in blocks
	 * @param paused - Is game paused
	 * @param running - Is game started
	 * @param win - Is Win End game
	 * @param over - Is Game Over
	 */
	public MapToDraw(final BlockHolder blockHolder, final LinkedList<Entity> entities,
							final LinkedList<Entity> bonuses, final int widthBlocks, final int heightBlocks,
									final boolean paused, final boolean running, final boolean win, final boolean over)
	{
		this.widthInBlocks = widthBlocks;
		this.heightInBlocks = heightBlocks;
		this.paused = paused;
		this.started = running;
		this.win = win;
		this.over = over;
		
		this.blocks = new BlockInformation(blockHolder, widthBlocks, heightBlocks);
		this.entities = new EntityInformation(entities);
		this.bonuses = new EntityInformation(bonuses);
	}

	public GameBlock getBlock(final int x, final int y)
	{
		return blocks.getBlock(x, y);
	}

	/**
	 * Returns block which is hiding bonuses
	 * 
	 * @return image enum of block hiding bonuses
	 */
	public GameBlock getHiderBlock()
	{
		return blocks.getHiderBlock();
	}
	
	public LinkedList<SimpleEntity> getEntities()
	{
		return entities.getEntities();
	}
	
	public LinkedList<SimpleEntity> getBonuses()
	{
		return bonuses.getEntities();
	}
	
	public int getWidthBlocks()
	{
		return widthInBlocks;
	}

	public int getHeightBlocks()
	{
		return heightInBlocks;
	}

	public boolean isPaused()
	{
		return paused;
	}

	public boolean isStarted()
	{
		return started;
	}
	
	public boolean isWin()
	{
		return win;
	}
	
	public boolean isOver()
	{
		return over;
	}
	
	/**
	 * Helper class to gather information about blocks
	 * @author Kajo
	 *
	 */
	private final class BlockInformation {
		final GameBlock[][] blocks;
		final GameBlock hiderBlock;
		
		/**
		 * Creates helper class
		 * 
		 * @param blocks - BlockHolder
		 * @param width - width in block tiles
		 * @param height - height in block tiles
		 */
		BlockInformation(final BlockHolder blocks, final int width, final int height)
		{
			this.blocks = new GameBlock[width][height];
			this.hiderBlock = GameBlock.BRICK;
			
			if (blocks != null)
			{
				fillBlocks(blocks);
			}
		}

		/**
		 * Fills image matrix and sets if there is specified EmptyBlock
		 * 
		 * @param blocks - BlockHolder
		 */
		private void fillBlocks(final BlockHolder blocks)
		{
			for (int i = 0; i < MapToDraw.this.widthInBlocks; ++i)
			{
				for (int j = 0; j < MapToDraw.this.heightInBlocks; ++j)
				{
					if (blocks.getBlock(i, j) instanceof StoneBlock)
					{
						this.blocks[i][j] = GameBlock.STONE;
					}
					else if (blocks.getBlock(i, j) instanceof BrickBlock)
					{
						this.blocks[i][j] = GameBlock.BRICK;
					}
					else
					{
						this.blocks[i][j] = GameBlock.EMPTY;
					}
				}
			}
		}
		
		/**
		 * 
		 * @param x - Position
		 * @param y - Position
		 * @return Enum of block, null if out of bounds
		 */
		public GameBlock getBlock(final int x, final int y)
		{
			if (x < 0 || x > MapToDraw.this.widthInBlocks - 1 || y < 0 || y > MapToDraw.this.heightInBlocks - 1)
			{
				return null;
			}
			else
			{
				return blocks[x][y];
			}
		}
		
		public GameBlock getHiderBlock()
		{
			return hiderBlock;
		}
	}
	
	/**
	 * Helper class to contain entity informations
	 * @author Kajo
	 *
	 */
	private final class EntityInformation
	{
		private final LinkedList<SimpleEntity> entities;
		
		/**
		 * Gather information about entities
		 * 
		 * @param entities - List of entities
		 */
		EntityInformation(final LinkedList<Entity> entities)
		{
			this.entities = new LinkedList<MapToDraw.SimpleEntity>();
			
			if (entities != null)
			{
				fillEntities(entities);
			}
		}

		/**
		 * Fills list of entities
		 * 
		 * @param entities - List of entities
		 */
		private void fillEntities(final LinkedList<Entity> entities)
		{
			GameEntities entity;
			
			for (Entity e : entities)
			{
				// It could be exported into entities but done in the exact same way
				if (e instanceof Player)						entity = GameEntities.PLAYER;
				else if (e instanceof Bomb)						entity = GameEntities.BOMB;
				else if (e instanceof ExplosionEntity)			entity = GameEntities.EXPLOSION;
				else if (e instanceof DestroyingBrick)			entity = GameEntities.DESTROYING_BRICK;
				else if (e instanceof BaloonEnemy)				entity = GameEntities.BALOON;
				else if (e instanceof HeliumEnemy)				entity = GameEntities.HELIUM;
				else if (e instanceof Exit)						entity = GameEntities.EXIT;
				else if (e instanceof SpeedBonus)				entity = GameEntities.SPEED;
				else if (e instanceof IncreaseBombAreaBonus)	entity = GameEntities.AREA_INC;
				else if (e instanceof IncreaseBombAmountBonus)	entity = GameEntities.BOMB_INC;
				else if (e instanceof IncreaseLifeNumberBonus)	entity = GameEntities.LIFE_INC;
				else if (e instanceof BouncingBomb)				entity = GameEntities.BOUNCING_BOMB;
				else entity = GameEntities.UNDEFINED;
				
				this.entities.add(new SimpleEntity(entity, e.getAnimFrame(), e.getX(), e.getY()));
			}
		}
		
		public LinkedList<SimpleEntity> getEntities()
		{
			return entities;
		}
	}
	
	/**
	 * Class represent SimpleEntity, which means that it contains only Image and coordinates
	 * @author Kajo
	 *
	 */
	public class SimpleEntity {
		private final GameEntities entity;
		private final int animFrame;
		private final float x;
		private final float y;
		
		/**
		 * 
		 * @param entity - Entity enum
		 * @param animFrame - Actual animation frame
		 * @param x - Position
		 * @param y - Position
		 */
		public SimpleEntity(final GameEntities entity, final int animFrame, final float x, final float y)
		{
			this.entity = entity;
			this.animFrame = animFrame;
			this.x = x;
			this.y = y;
		}
		
		public GameEntities getEntity()
		{
			return entity;
		}
		
		public int getAnimFrame()
		{
			return animFrame;
		}
		
		public float getX()
		{
			return x;
		}
		
		public float getY()
		{
			return y;
		}
	}
}

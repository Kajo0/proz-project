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
import pl.edu.pw.elka.mmarkiew.model.map.EmptyBlock;
import pl.edu.pw.elka.mmarkiew.model.map.GameBlock;

/**
 * Contains information about game map<br>
 * Mostly images
 * @author Acer
 *
 */
public class MapToDraw {
	public final static int blockSize = GameMap.BLOCK_SIZE;
	private int widthBlocks;
	private int heightBlocks;
	private boolean paused;
	private boolean started;
	private boolean win;
	private boolean over;
	private BlockInformation blocks;
	private EntityInformation entities;
	private EntityInformation bonuses;

	/**
	 * Creates object containing every needed information to paint acutal map
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
										final LinkedList<Entity> bonuses, int widthBlocks, int heightBlocks,
											boolean paused, boolean running, boolean win, boolean over) {
		this.widthBlocks = widthBlocks;
		this.heightBlocks = heightBlocks;
		this.paused = paused;
		this.started = running;
		this.win = win;
		this.over = over;
		
		this.blocks = new BlockInformation(blockHolder, widthBlocks, heightBlocks);
		this.entities = new EntityInformation(entities);
		this.bonuses = new EntityInformation(bonuses);
		
	}
	
	/**
	 * If there is no map just set default information
	 * @param started - Is game started
	 * @param win - Is Win End game
	 * @param over -  Is Game Over
	 */
	public MapToDraw(boolean started, boolean win, boolean over) {
		this(null, null, null, 0, 0, false, started, win, over);
	}

	public GameBlock getBlock(int x, int y) {
		return blocks.getBlock(x, y);
	}

	/**
	 * Check is on xBlock, yBlock tile position is EmptyBlock
	 * @param x - Position
	 * @param y - Position
	 * @return true if there is EmptyBlock, false otherwise
	 */
	public boolean isEmptyBlock(int x, int y) {
		return blocks.isEmptyBlock(x, y);
	}

	/**
	 * Returns block which is hiding bonuses
	 * @return image of block hiding bonuses
	 */
	public GameBlock getHiderBlock() {
		return blocks.getHiderBlock();
	}
	
	public LinkedList<SimpleEntity> getEntities() {
		return entities.getEntities();
	}
	
	public LinkedList<SimpleEntity> getBonuses() {
		return bonuses.getEntities();
	}
	
	public int getWidthBlocks() {
		return widthBlocks;
	}

	public int getHeightBlocks() {
		return heightBlocks;
	}

	public boolean isPaused() {
		return paused;
	}

	public boolean isStarted() {
		return started;
	}
	
	public boolean isWin() {
		return win;
	}
	
	public boolean isOver() {
		return over;
	}
	
	/**
	 * Helper class to gather information about blocks
	 * @author Acer
	 *
	 */
	private final class BlockInformation {
		GameBlock[][] blocks;
		boolean[][] isEmpty;
		GameBlock hiderBlock;
		
		/**
		 * Creates helper class
		 * @param blocks - BlockHolder
		 * @param width - width in block tiles
		 * @param height - height in block tiles
		 */
		BlockInformation(final BlockHolder blocks, int width, int height) {
			this.blocks = new GameBlock[width][height];
			this.isEmpty = new boolean[width][height];
			this.hiderBlock = GameBlock.BRICK;
			
			if (blocks != null)
				fillBlocks(blocks);
		}

		/**
		 * Fills image matrix and sets if there is specified EmptyBlock
		 * @param blocks - BlockHolder
		 */
		private void fillBlocks(final BlockHolder blocks) {
			
			for (int i = 0; i < MapToDraw.this.widthBlocks; ++i)
				for (int j = 0; j < MapToDraw.this.heightBlocks; ++j) {
					
					this.blocks[i][j] = blocks.getBlock(i, j).getBlock();
					
					if (blocks.getBlock(i, j) instanceof EmptyBlock)
						this.isEmpty[i][j] = true;
					else this.isEmpty[i][j] = false;
				}
		}
		
		/**
		 * 
		 * @param x - Position
		 * @param y - Position
		 * @return Image of block, null if out of bounds
		 */
		public GameBlock getBlock(int x, int y) {
			if (x < 0 || x > MapToDraw.this.widthBlocks - 1 ||
					y < 0 || y > MapToDraw.this.heightBlocks - 1)
				return null;
			else return blocks[x][y];
		}
		
		public boolean isEmptyBlock(int x, int y) {
			return isEmpty[x][y];
		}
		
		public GameBlock getHiderBlock() {
			return hiderBlock;
		}
	}
	
	/**
	 * Helper class to contain enitity informations
	 * @author Acer
	 *
	 */
	private final class EntityInformation {
		private LinkedList<SimpleEntity> entities;
		
		/**
		 * Gather information about entities
		 * @param entities - List of entities
		 */
		EntityInformation(final LinkedList<Entity> entities) {
			this.entities = new LinkedList<MapToDraw.SimpleEntity>();
			
			if (entities != null)
				fillEntities(entities);
		}

		/**
		 * Fills list of entities
		 * @param entities - List of entities
		 */
		private void fillEntities(final LinkedList<Entity> entities) {
			GameEntities entity;
			
			for (Entity e : entities) {
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
		
		public LinkedList<SimpleEntity> getEntities() {
			return entities;
		}
	}
	
	/**
	 * Class represent SimpleEntity, which means that it
	 * contains only Image and coordinates
	 * @author Acer
	 *
	 */
	public class SimpleEntity {
		private GameEntities entity;
		private int animFrame;
		private float x;
		private float y;
		
		/**
		 * Create SimpleEntity
		 * @param image - Image of entity
		 * @param x - Position
		 * @param y - Position
		 */
		public SimpleEntity(GameEntities entity, int animFrame, float x, float y) {
			this.entity = entity;
			this.animFrame = animFrame;
			this.x = x;
			this.y = y;
		}
		
		public GameEntities getEntity() {
			return entity;
		}
		
		public int getAnimFrame() {
			return animFrame;
		}
		
		public float getX() {
			return x;
		}
		
		public float getY() {
			return y;
		}
	}
}

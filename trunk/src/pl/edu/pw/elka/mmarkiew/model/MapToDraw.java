package pl.edu.pw.elka.mmarkiew.model;

import java.awt.Image;
import java.util.LinkedList;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
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

	public Image getBlockImage(int x, int y) {
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
	public Image getHiderBlock() {
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
		Image[][] blocks;
		boolean[][] isEmpty;
		Image hiderBlock;
		
		/**
		 * Creates helper class
		 * @param blocks - BlockHolder
		 * @param width - width in block tiles
		 * @param height - height in block tiles
		 */
		BlockInformation(final BlockHolder blocks, int width, int height) {
			this.blocks = new Image[width][height];
			this.isEmpty = new boolean[width][height];
			this.hiderBlock = GameBlock.BRICK.getImage();
			
			if (blocks != null)
				fillBlocks(blocks);
		}

		/**
		 * Fills image matrix and sets if there is specified EmptyBlock
		 * @param blocks - BlockHolder
		 */
		private void fillBlocks(final BlockHolder blocks) {
			
			for (int i = 0; i < MapToDraw.this.widthBlocks; i++)
				for (int j = 0; j < MapToDraw.this.heightBlocks; j++) {
					
					this.blocks[i][j] = blocks.getBlock(i, j).getImage();
					
					if (blocks.getBlock(i, j) instanceof EmptyBlock)
						this.isEmpty[i][j] = true;
					else this.isEmpty[i][j] = false;
				}
		}
		
		public Image getBlock(int x, int y) {
			if (x < 0 || x > MapToDraw.this.widthBlocks - 1 ||
					y < 0 || y > MapToDraw.this.heightBlocks - 1)
				return null;
			else return blocks[x][y];
		}
		
		public boolean isEmptyBlock(int x, int y) {
			return isEmpty[x][y];
		}
		
		public Image getHiderBlock() {
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
			for (Entity e : entities)
				this.entities.add(new SimpleEntity(e.getAnim(), e.getX(), e.getY()));
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
		private Image image;
		private float x;
		private float y;
		
		/**
		 * Create SimpleEntity
		 * @param image - Image of entity
		 * @param x - Position
		 * @param y - Position
		 */
		public SimpleEntity(final Image image, float x, float y) {
			this.image = image;
			this.x = x;
			this.y = y;
		}
		
		public Image getImage() {
			return image;
		}
		
		public float getX() {
			return x;
		}
		
		public float getY() {
			return y;
		}
	}
}

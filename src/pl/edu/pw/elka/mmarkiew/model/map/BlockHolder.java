package pl.edu.pw.elka.mmarkiew.model.map;

/**
 * Class for storing matrix of blocks forming game map
 * @author Acer
 *
 */
public class BlockHolder {
	/** in blocks */
	private int width;
	/** in blocks */
	private int height;
	/** Hold block as map */
	private BlockElement[][] blocks;
	
	/**
	 * Create new holder
	 * @param widthBlocks - Number of columns
	 * @param heightBlocks - Number of rows
	 */
	public BlockHolder(int widthBlocks, int heightBlocks)
	{
		this.width = widthBlocks;
		this.height = heightBlocks;
		this.blocks = new BlockElement[widthBlocks][heightBlocks];
	}
	
	/**
	 * Sets new block into matrix, or do nothing
	 * if there was out of bounds position
	 * @param block - BlockElement to put
	 * @param x - Tile position
	 * @param y - Tile position
	 */
	public void setBlock(BlockElement block, int x, int y)
	{
		if (x < 0 || x > width - 1 || y < 0 || y > height - 1 )
		{
			return;
		}
		else
		{
			blocks[x][y] = block;
		}
	}
	
	/**
	 * Return specified block from map matrix
	 * @param x - Tile position
	 * @param y - Tile position
	 * @return Appropriate block or null if out of bounds
	 */
	public BlockElement getBlock(int x, int y)
	{
		if (x < 0 || x > width - 1 || y < 0 || y > height - 1)
		{
			return null;
		}
		else
		{
			return blocks[x][y];
		}
	}
}
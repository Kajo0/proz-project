package pl.edu.pw.elka.mmarkiew.model.map;

/**
 * Factory of blocks
 * @author Acer
 *
 */
public abstract class BlockFactory {
	
	/**
	 * Creates appropriate block from GameBlock object
	 * If there was no block, create empty block
	 * @param block - Block to create
	 * @return Created block
	 */
	public static BlockElement createElement(final GameBlock block) {
		switch (block) {
			case STONE: return new StoneBlock(block.getImage());
			case BRICK: return new BrickBlock(block.getImage());
			case EMPTY: return new EmptyBlock(block.getImage());
			default:	return new EmptyBlock(block.getImage());
		}
	}
}

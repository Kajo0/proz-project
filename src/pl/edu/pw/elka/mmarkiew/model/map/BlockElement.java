package pl.edu.pw.elka.mmarkiew.model.map;

/**
 * Class represents game tile Block
 * @author Acer
 *
 */
public abstract class BlockElement {
	private GameBlock block;
	
	/**
	 * Creates block with given image
	 * @param image - image of block
	 */
	public BlockElement(final GameBlock block) {
		this.block = block;
	}
	
	public GameBlock getBlock() {
		return this.block;
	}
}

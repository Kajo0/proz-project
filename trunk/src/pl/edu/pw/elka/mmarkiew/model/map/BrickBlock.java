package pl.edu.pw.elka.mmarkiew.model.map;

/**
 * Class represents block which player can destroy<br>
 * and with which there are collisions
 * @author Acer
 *
 */
public class BrickBlock extends BlockElement {
	
	/**
	 * Creates block made of bricks, which can be destroyed
	 * @param block - Block enum
	 */
	public BrickBlock(final GameBlock block) {
		super(block);
	}
}

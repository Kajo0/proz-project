package pl.edu.pw.elka.mmarkiew.model.map;

/**
 * Class represents block which can't be destroyed<br>
 * and with which there are collisions
 * @author Acer
 *
 */
public class StoneBlock extends BlockElement {
	
	/**
	 * Creates stone block, which can't be destroyed
	 * @param image - Block enum
	 */
	public StoneBlock(final GameBlock block) {
		super(block);
	}
}

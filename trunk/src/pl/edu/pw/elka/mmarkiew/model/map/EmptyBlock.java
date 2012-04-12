package pl.edu.pw.elka.mmarkiew.model.map;

import java.awt.Image;

/**
 * Class represents block on which player can walk<br>
 * and with which there is no collision
 * @author Acer
 *
 */
public class EmptyBlock extends BlockElement{
	
	/**
	 * Creates empty block
	 * @param image - Block image
	 */
	public EmptyBlock(final Image image) {
		super(image);
	}
}

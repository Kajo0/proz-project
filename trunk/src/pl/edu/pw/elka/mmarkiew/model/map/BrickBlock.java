package pl.edu.pw.elka.mmarkiew.model.map;

import java.awt.Image;

/**
 * Class represents block which player can destroy<br>
 * and with which there are collisions
 * @author Acer
 *
 */
public class BrickBlock extends BlockElement {
	
	/**
	 * Creates block made of bricks, which can be destroyed
	 * @param image - Brick block image
	 */
	public BrickBlock(final Image image) {
		super(image);
	}
}

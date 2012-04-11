package pl.edu.pw.elka.mmarkiew.model.map;

import java.awt.Image;

/**
 * Class represents block which can't be destroyed<br>
 * and with which there are collisions
 * @author Acer
 *
 */
public class StoneBlock extends BlockElement {
	
	/**
	 * Creates stone block, which can't be destroyed
	 * @param image - Block image
	 */
	public StoneBlock(Image image) {
		super(image);
	}
}

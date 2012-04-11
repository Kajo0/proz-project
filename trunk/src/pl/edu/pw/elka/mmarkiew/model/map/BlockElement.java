package pl.edu.pw.elka.mmarkiew.model.map;

import java.awt.Image;

/**
 * Class represents game tile Block
 * @author Acer
 *
 */
public abstract class BlockElement {
	private Image image;
	
	/**
	 * Creates block with given image
	 * @param image - image of block
	 */
	public BlockElement(final Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		return this.image;
	}
}

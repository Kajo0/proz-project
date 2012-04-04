package pl.edu.pw.elka.mmarkiew.model.map;

import java.awt.Image;

public abstract class BlockElement {
	private Image image;
	
	public BlockElement(Image image) {
		this.image = image;
	}
	
	public Image getImage() {
		return this.image;
	}
}

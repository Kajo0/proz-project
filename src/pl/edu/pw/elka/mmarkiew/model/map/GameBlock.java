package pl.edu.pw.elka.mmarkiew.model.map;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import pl.edu.pw.elka.mmarkiew.model.GameMap;

/**
 * Enumeration representing available game blocks 
 * @author Acer
 *
 */
public enum GameBlock {
	STONE("#", "images/stoneBlock.png"),
	BRICK("@", "images/brickBlock.png"),
	EMPTY(" ", "images/emptyBlock.png");
	
	/** String representing block */
	private final String character;
	/** Block imgage */
	private final Image image;
	
	/**
	 * Creates and load image of block
	 * @param character - String representing block
	 * @param image - Path to block image
	 */
	private GameBlock(final String character, final String image) {
		this.character = character;
		
		/*
		 * If there is no image, create blank image
		 */
		Image img = null;
		if (new File(image).canExecute()) {
			ImageIcon icon = new ImageIcon(image);
			img = icon.getImage();
		} else {
			BufferedImage bufImg = new BufferedImage(GameMap.BLOCK_SIZE, GameMap.BLOCK_SIZE,
																				BufferedImage.TYPE_INT_ARGB);
			Graphics g = bufImg.getGraphics();
			g.fillRect(0, 0, GameMap.BLOCK_SIZE, GameMap.BLOCK_SIZE);
			g.dispose();
			
			img = bufImg;
		}
		
		this.image = img;
	}
	
	public String getCharacter() {
		return this.character;
	}
	
	public Image getImage() {
		return this.image;
	}

	public String toString() {
		return getCharacter();
	}
	
	/**
	 * Return GameBlock if such exists
	 * @param character - Entity representation string
	 * @return Apropriate GameBlock, EMPTY if didn't find
	 */
	public static GameBlock getEnumBlock(final String character) {
		for (GameBlock g : values()) {
			if (g.getCharacter().equals(character))
				return g;
		}
		return EMPTY;
	}
	
}
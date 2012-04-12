package pl.edu.pw.elka.mmarkiew.model.map;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import pl.edu.pw.elka.mmarkiew.model.GameMap;

/**
 * Enumeration representing available game blocks 
 * @author Acer
 *
 */
public enum GameBlock {
	STONE("#", "stoneBlock"),
	BRICK("@", "brickBlock"),
	EMPTY(" ", "emptyBlock");
	
	/** String representing block */
	private final String character;
	/** Block imgage */
	private final Image image;
	
	/**
	 * Creates and load image of block
	 * @param character - String representing block
	 * @param image - Path to block image
	 */
	private GameBlock(final String character, String image) {
		this.character = character;
		
		/*
		 * If there is no image, create blank image
		 */
		Image img = null;
		image = "/" + image;
		if (getClass().getResource(image + ".png") != null)
			img = new ImageIcon(getClass().getResource(image + ".png")).getImage();
		
		else {
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
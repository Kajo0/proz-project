package pl.edu.pw.elka.mmarkiew.model.map;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

import pl.edu.pw.elka.mmarkiew.model.GameMap;

public enum GameBlock {
	STONE("#", "images/stoneBlock.png"),
	BRICK("@", "images/brickBlock.png"),
	EMPTY(" ", "images/emptyBlock.png");
	
	private final String character;
	private final Image image;
	
	private GameBlock(final String character, final String image) {
		this.character = character;
		
		Image img = null;
		try {
			ImageIcon icon = new ImageIcon(image);
			img = icon.getImage();
		} catch (Exception e) {
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
	 * Return enum form of given character
	 * @param character enum string
	 * @return enum GameBlocks
	 */
	public static GameBlock getEnumBlock(final String character) {
		for (GameBlock g : values()) {
			if (g.getCharacter().equals(character))
				return g;
		}
		return EMPTY;
	}
	
}
package pl.edu.pw.elka.mmarkiew.model.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public enum GameBlock {
	STONE("#", "kamyczek"),
	BRICK("@", "murek"),
	SPACE(" ", "pusto");
	
	private final String character;
	private final Image image;
	
	private GameBlock(final String character, final String image) {
		//TODO zmienic na obrazki
		this.character = character;
		
		BufferedImage img = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		
		if (character.equals("#"))
			g.setColor(Color.BLACK);
		else if (character.equals("@"))
			g.setColor(Color.RED);
		else
			g.setColor(Color.WHITE);
		
		g.fillRect(0, 0, 40, 40);
		
		
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
		return SPACE;
	}
	
}
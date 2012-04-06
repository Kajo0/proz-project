package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public enum GameEntities {
	PLAYER("P", "", ""),
	BALOON("A", "", ""),
	HELIUM("B", "", ""),
	UNDEFINED("", "", "");
	
	private final String character;
	private final Image anim;
	private final Image dyingAnim;
	
	private GameEntities(final String character, final String anim, final String dyingAnim) {
		this.character = character;
		
		BufferedImage img = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		
		if (character.equals("P"))
			g.setColor(Color.GREEN);
		else if (character.equals("A"))
			g.setColor(Color.PINK);
		else if (character.equals("B"))
			g.setColor(Color.MAGENTA);
		else
			g.setColor(Color.WHITE);
		
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		g.dispose();
		
		this.anim = img;
		
		
		BufferedImage img2 = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
		Graphics g2 = img2.getGraphics();
		
		if (character.equals("P"))
			g2.setColor(Color.LIGHT_GRAY);
		else if (character.equals("A"))
			g2.setColor(Color.LIGHT_GRAY);
		else if (character.equals("B"))
			g2.setColor(Color.LIGHT_GRAY);
		else
			g2.setColor(Color.LIGHT_GRAY);
		
		g2.fillRect(0, 0, img2.getWidth(), img2.getHeight());
		g.dispose();
		
		this.dyingAnim = img2;
	}
	
	public String getCharacter() {
		return this.character;
	}

	public Image getAnim() {
		return this.anim;
	}

	public Image getDyingAnim() {
		return this.dyingAnim;
	}
	
	public String toString() {
		return getCharacter();
	}
	
	public static GameEntities getEnumEntity(final String character) {
		for (GameEntities g : values()) {
			if (g.getCharacter().equals(character))
				return g;
		}
		return UNDEFINED;
	}
	
}
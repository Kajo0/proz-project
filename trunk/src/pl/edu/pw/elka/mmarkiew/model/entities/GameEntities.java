package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import pl.edu.pw.elka.mmarkiew.model.GameMap;

public enum GameEntities {
	PLAYER("P", "images/player.png", ""),
	BALOON("A", "images/ballonEnemy.png", ""),
	HELIUM("B", "images/heliumEnemy.png", ""),
	BOMB("BOMB", "images/bomb.png", ""),
	EXPLOSION("EXPLOSION", "images/explosion.png", ""),
	DESTROYING_BRICK("DESTROYING_BRICK", "images/destroyingBrick.png", ""),
	UNDEFINED("", "images/undefined.png", "");
	
	private final String character;
	private final Image anim;
	private final Image dyingAnim;
	
	private GameEntities(final String character, final String anim, final String dyingAnim) {
		this.character = character;
		
		Image img = null;
		try {
			ImageIcon icon = new ImageIcon(anim);
			img = icon.getImage();
		} catch (Exception e) {
			BufferedImage bufImg = new BufferedImage(GameMap.BLOCK_SIZE, GameMap.BLOCK_SIZE,
																				BufferedImage.TYPE_INT_ARGB);
			Graphics g = bufImg.getGraphics();
			g.fillRect(10, 10, 20, 20);
			g.dispose();
			
			img = bufImg;
		}
		
		this.anim = img;
		this.dyingAnim = img;
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
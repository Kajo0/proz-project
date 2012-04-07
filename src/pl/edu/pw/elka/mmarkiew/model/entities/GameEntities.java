package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import pl.edu.pw.elka.mmarkiew.model.GameMap;

public enum GameEntities {
	PLAYER("P", "images/player.png", ""),
	BALOON("A", "images/ballonEnemy.png", ""),
	HELIUM("B", "images/heliumEnemy.png", ""),
	BOMB("BOMB", "images/bomb.png", ""),
	EXIT("X", "images/exitOpen.png", "images/exitClose.png"),
	EXPLOSION("EX PLOSION", "images/explosion.png", ""),
	DESTROYING_BRICK("DESTROYING_BRICK", "images/destroyingBrick.png", ""),
	UNDEFINED("", "images/undefined.png", "");
	
	private final String character;
	private final Animation anim;
	private final Animation dyingAnim;
	
	private GameEntities(final String character, final String anim, final String dyingAnim) {
		this.character = character;
		
		Animation a = new Animation();

		if (new File(anim).canExecute()) {
			a.addFrame(new ImageIcon(anim).getImage(), 500);
			
		} else {
			BufferedImage bufImg = new BufferedImage(GameMap.BLOCK_SIZE, GameMap.BLOCK_SIZE,
																				BufferedImage.TYPE_INT_ARGB);
			Graphics g = bufImg.getGraphics();
			g.fillRect(10, 10, 20, 20);
			g.dispose();
			
			a.addFrame(bufImg, 50);
		}
		
		this.anim = a;
		this.dyingAnim = a;
	}

	public String getCharacter() {
		return this.character;
	}

	public Animation getAnim() {
		return this.anim;
	}

	public Animation getDyingAnim() {
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
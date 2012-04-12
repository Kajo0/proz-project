package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import pl.edu.pw.elka.mmarkiew.model.GameMap;

/**
 * Enum defines posibble entities in game<br>
 * And contain them Animation to clone them
 * @author Acer
 *
 */
public enum GameEntities {
	PLAYER				("P",	"player",					"playerDying"),
	BALOON				("B",	"ballonEnemy",				"ballonEnemyDying"),
	HELIUM				("H",	"heliumEnemy",				"ballonEnemyDying"),
	BOMB				("BOM",	"bomb",						""),
	EXIT				("E",	"exitOpen",					"exitClose"),
	SPEED				("S",	"speedBonus",				""),
	AREA_INC			("A",	"increaseBombAreaBonus",	""),
	BOMB_INC			("N",	"increaseBombAmountBonus",	""),
	LIFE_INC			("L",	"increaseLifeNumberBonus",	""),
	BOUNCING_BOMB		("G",	"bouncingBombBonus",		""),
	EXPLOSION			("EXP",	"explosion",				""),
	DESTROYING_BRICK	("DES",	"destroyingBrick",			""),
	UNDEFINED			("",	"undefined",				"");
	
	private final String character;
	private final Animation anim;
	private final Animation dyingAnim;
	
	/**
	 * Generates game entity
	 * @param character - String represents entity
	 * @param anim - Entity animation
	 * @param dyingAnim - Entity animation when is in agony
	 */
	private GameEntities(final String character, final String anim, final String dyingAnim) {
		this.character = character;
		
		/*
		 * If there is no dead animation,
		 * both animations are the same
		 */
		this.anim = createAnimation(anim);
		if (dyingAnim.equals(""))
			this.dyingAnim = this.anim;
		else this.dyingAnim = createAnimation(dyingAnim);
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
	
	/**
	 * Return GameEntity if such exists
	 * @param character - Entity representation string
	 * @return Apropriate GameEntity, UNDEFINED if didn't find
	 */
	public static GameEntities getEnumEntity(final String character) {
		for (GameEntities g : values()) {
			if (g.getCharacter().equals(character))
				return g;
		}
		return UNDEFINED;
	}
	
	/**
	 * Helper function to load next frames of animation and made it as
	 * one complete animation.<br>
	 * First frame sufix: 0.png, next ${number}.png
	 * @param anim - Filename without number and extension
	 * @return Created Animation from frames<br>
	 * If there was no image, return Blank square animation
	 */
	private Animation createAnimation(String anim) {
		Animation a = new Animation();

		/*
		 * Loading next frames if exists
		 */
		
		anim = "/" + anim;
		if (getClass().getResource(anim + "0.png") != null) {
			a.addFrame(new ImageIcon(getClass().getResource(anim + "0.png")).getImage(), 250);

			int i = 1;
			while (getClass().getResource(anim + i + ".png") != null) {
				a.addFrame(new ImageIcon(getClass().getResource(anim + i + ".png")).getImage(), 250);
				++i;
			}
		} else {
			/*
			 * Create blank square image
			 */
			BufferedImage bufImg = new BufferedImage(GameMap.BLOCK_SIZE, GameMap.BLOCK_SIZE,
																				BufferedImage.TYPE_INT_ARGB);
			Graphics g = bufImg.getGraphics();
			g.fillRect(10, 10, 20, 20);
			g.dispose();
			
			a.addFrame(bufImg, 50);
		}
		
		return a;
	}
	
}
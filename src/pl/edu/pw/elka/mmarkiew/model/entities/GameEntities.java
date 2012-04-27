package pl.edu.pw.elka.mmarkiew.model.entities;

import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Enum defines posibble entities in game<br>
 * And contain them Animation to clone them
 * @author Acer
 *
 */
public enum GameEntities {
	PLAYER				("P",	"player",					"playerDying",		200),
	BOMB				("BOM",	"bomb",						"",					250),
	EXPLOSION			("EXP",	"explosion",				"",					125),
	DESTROYING_BRICK	("DES",	"destroyingBrick",			"",					250),
	UNDEFINED			("",	"undefined",				"",					0),
	BALOON				("B",	"ballonEnemy",				"ballonEnemyDying",	200),
	HELIUM				("H",	"heliumEnemy",				"ballonEnemyDying",	100),
	EXIT				("E",	"exitOpen",					"exitClose",		0),
	SPEED				("S",	"speedBonus",				"",					0),
	AREA_INC			("A",	"increaseBombAreaBonus",	"",					0),
	BOMB_INC			("N",	"increaseBombAmountBonus",	"",					0),
	LIFE_INC			("L",	"increaseLifeNumberBonus",	"",					0),
	BOUNCING_BOMB		("G",	"bouncingBombBonus",		"",					0);
	
	private final String character;
	private final Animation anim;
	private final Animation dyingAnim;
	private final long animInterval;
	private int width;
	private int height;
	private static Random rand = new Random();
	
	/**
	 * Generates game entity
	 * @param character - String represents entity
	 * @param anim - Entity animation
	 * @param dyingAnim - Entity animation when is in agony
	 * @param animStep - Interval between animations
	 */
	private GameEntities(final String character, final String anim, final String dyingAnim, long animStep) {
		this.character = character;
		this.animInterval = (animStep == 0) ? 251 : ++animStep;
		
		this.width = 1;
		this.height = 1;
		
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
	
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
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
			a.addFrame(0, animInterval);

			this.width = new ImageIcon(getClass().getResource(anim + "0.png")).getImage().getWidth(null);
			this.height = new ImageIcon(getClass().getResource(anim + "0.png")).getImage().getHeight(null);

			int i = 1;
			while (getClass().getResource(anim + i + ".png") != null) {
				a.addFrame(i, animInterval);
				++i;
			}
		} else
			a.addFrame(0, 0);
		
		return a;
	}
	
	/**
	 * Generates random bonus character
	 * @return Random string represents bonus
	 */
	public static String getRandomBonusCharacter() {
		return GameEntities.values()[rand.nextInt(6) + 7].toString();
	}
	
	/**
	 * Generates random enemy character
	 * @return Random string represents enemy
	 */
	public static String getRandomEnemyCharacter() {
		return GameEntities.values()[rand.nextInt(2) + 5].toString();
	}
	
}
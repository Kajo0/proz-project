package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import pl.edu.pw.elka.mmarkiew.model.GameMap;

public enum GameEntities {
	PLAYER				("P",	"player",			"playerDying"),
	BALOON				("A",	"ballonEnemy",		"ballonEnemyDying"),
	HELIUM				("B",	"heliumEnemy",		"ballonEnemyDying"),
	BOMB				("BOM",	"bomb",				"bomb"),
	EXIT				("X",	"exitOpen",			"exitClose"),
	SPEED				("S",	"speedBonus",		"speedBonus"),
	EXPLOSION			("EXP",	"explosion",		"explosion"),
	DESTROYING_BRICK	("DES",	"destroyingBrick",	"destroyingBrick"),
	UNDEFINED			("",	"undefined",		"undefined");
	
	private final String character;
	private final Animation anim;
	private final Animation dyingAnim;
	
	private GameEntities(final String character, final String anim, final String dyingAnim) {
		this.character = character;
		
		this.anim = createAnimation(anim);
		this.dyingAnim = createAnimation(dyingAnim);
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
	
	private Animation createAnimation(String anim) {
		Animation a = new Animation();

		File file = new File("images" + File.separator + anim + "0.png");
		if (file.canExecute()) {
			a.addFrame(new ImageIcon(file.getPath()).getImage(), 250);

			file = new File("images" + File.separator + anim + "1.png");
			for (int i = 1; file.canExecute(); i++, file = new File("images" + File.separator + anim + i + ".png"))
				a.addFrame(new ImageIcon(file.getPath()).getImage(), 250);
			
		} else {
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
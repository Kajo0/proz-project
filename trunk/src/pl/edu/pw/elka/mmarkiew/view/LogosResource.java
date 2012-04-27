package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * Enumeration containing images to draw on view panel
 * @author Acer
 *
 */
public enum LogosResource {
	LEVEL				("levelLabel"),
	LIFES				("player0"),
	BOMBS				("bombsLabel"),
	STEPS				("increaseBombAreaBonus0"),
	TIMER				("timerLabel"),
	INFO_BACKGROUND		("infoBackground"),
	BOUNCE				("bouncingBombBonus0"),
	BACKGROUND_MUSIC_ON	("musicOn"),
	BACKGROUND_MUSIC_OFF("musicOff"),
	SOUND_EFFECTS_ON	("effectsOn"),
	SOUND_EFFECTS_OFF	("effectsOff"),
	GAME_PAUSED			("gamePaused"),
	GAME_WIN			("gameWin"),
	GAME_OVER			("gameOver"),
	GAME_LOGO			("gameLogo"),
	GAME_BACKGROUND		("gameBackground");
	
	private Image image;
	
	/**
	 * Creates enum
	 * @param image - Part of path to image
	 */
	private LogosResource(String image) {
		
		/*
		 * If can't be executed draw blank image
		 */
		Image img = null;
		image = "/" + image;
		if (getClass().getResource(image + ".png") != null)
			img = new ImageIcon(getClass().getResource(image + ".png")).getImage();
		
		else {
			BufferedImage bufImg = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
			Graphics g = bufImg.getGraphics();
			
			g.fillRect(0, 0, 50, 50);
			g.dispose();
			
			img = bufImg;
		}
		
		this.image = img;
	}

	public Image getImage() {
		return image;
	}
}
package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;

/**
 * Enumeration containing images to draw on view panel
 * @author Acer
 *
 */
public enum LogosResource {
	LEVEL("images/levelLabel.png"),
	LIFES("images/player0.png"),
	BOMBS("images/bombsLabel.png"),
	STEPS("images/increaseBombAreaBonus0.png"),
	TIMER("images/timerLabel.png"),
	INFO_BACKGROUND("images/infoBackground.png");
	
	private Image image;
	
	/**
	 * Creates enum
	 * @param image - Path to image
	 */
	private LogosResource(final String image) {
		
		/*
		 * If can't be executed draw blank image
		 */
		Image img = null;
		if (new File(image).canExecute()) {
			ImageIcon icon = new ImageIcon(image);
			img = icon.getImage();
		} else {
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
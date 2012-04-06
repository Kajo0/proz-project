package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Entity {
	//TODO dodac pointsy i bonusy ustawione
	private int lifes;

	public Player() {
		super();
		this.setMaxVelocity(0.15f);
		this.setWidth(30);
		this.setHeight(30);
		this.setLifes(3);
		
		BufferedImage img = new BufferedImage((int) this.getWidth(), (int) this.getHeight(),
																	BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.GREEN);
//		g.fillOval(0, 0, (int) this.getWidth(), (int) this.getHeight());
		g.fillRect(0, 0, (int) this.getWidth(), (int) this.getHeight());
		
		this.setAnim(img);
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}
}

package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Entity {

	public Player() {
		this.setX(0);
		this.setY(0);
		this.setXVelocity(0);
		this.setYVelocity(0);
		this.setMaxVelocity(0.15f);
		
		BufferedImage img = new BufferedImage(30, 30, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.GREEN);
		g.fillOval(0, 0, 30, 30);
		
		this.setAnim(img);
	}
}

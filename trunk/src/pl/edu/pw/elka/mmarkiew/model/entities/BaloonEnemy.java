package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BaloonEnemy extends Entity {
	
	public BaloonEnemy() {
		super();
		this.setXVelocity(0.05f);
		this.setYVelocity(0.05f);
		this.setMaxVelocity(0.05f);
		this.setWidth(30);
		this.setHeight(30);
		
		BufferedImage img = new BufferedImage((int) this.getWidth(), (int) this.getHeight(),
																		BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.CYAN);
		g.fillOval(0, 0, (int) this.getWidth(), (int) this.getHeight());
		
		this.setAnim(img);
	}
	
	public BaloonEnemy(int x, int y) {
		this();
		this.setX(x);
		this.setY(y);
	}

	public void collisionX() {
		this.setXVelocity(-this.getXVelocity());
	}
	
	public void collisionY() {
		this.setYVelocity(-this.getYVelocity());
	}
}

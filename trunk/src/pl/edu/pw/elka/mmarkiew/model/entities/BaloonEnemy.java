package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class BaloonEnemy extends Entity {
	
	public BaloonEnemy() {
		super();
		this.setXVelocity(0.05f);
		this.setYVelocity(0.05f);
		this.setMaxVelocity(0.05f);
		this.setWidth(40);
		this.setHeight(40);
		
		BufferedImage img = new BufferedImage((int) this.getWidth(), (int) this.getHeight(),
																		BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(new Color(new Random().nextInt()));
		g.fillRect(0, 0, (int) this.getWidth(), (int) this.getHeight());
		
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

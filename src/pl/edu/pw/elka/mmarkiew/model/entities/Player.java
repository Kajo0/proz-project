package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Entity {
	//TODO dodac pointsy i bonusy ustawione
	private int lifes;
	private int possibleBombs;
	private int plantedBombs;

	public Player() {
		super();
		this.setMaxVelocity(0.15f);
		this.setWidth(30);
		this.setHeight(30);
		this.setLifes(3);
		this.possibleBombs = 5;
		this.plantedBombs = 0;
		
		BufferedImage img = new BufferedImage((int) this.getWidth(), (int) this.getHeight(),
																	BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, (int) this.getWidth(), (int) this.getHeight());
		
		this.setAnim(img);
	}
	
	public void plantBomb() {
		if (plantedBombs < possibleBombs)
			this.plantedBombs++;
	}
	
	public void bombExploded() {
		this.plantedBombs--;
	}
	
	public boolean canPlantBomb() {
		return (plantedBombs < possibleBombs);
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}
}

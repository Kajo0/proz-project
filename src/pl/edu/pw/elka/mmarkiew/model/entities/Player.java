package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Image;

public class Player extends Entity {
	//TODO dodac pointsy i bonusy ustawione
	private int lifes;
	private int possibleBombs;
	private int plantedBombs;
	private int bombArea;

	public Player(Image anim, Image dyingAnim) {
		super(anim, dyingAnim);
		this.setMaxVelocity(0.15f);
		this.setLifes(3);
		this.possibleBombs = 5;
		this.plantedBombs = 0;
		this.bombArea = 3;
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

	public int getBombArea() {
		return bombArea;
	}

	public void setBombArea(int bombArea) {
		this.bombArea = bombArea;
	}
	
	public void setDead() {
		this.lifes--;
		this.plantedBombs = 0;
	}
}

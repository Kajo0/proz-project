package pl.edu.pw.elka.mmarkiew.model.entities;

public class Player extends Entity {
	private int lifes;
	private int possibleBombs;
	private int plantedBombs;
	private int bombArea;
	private float defaultSpeed;
	private long bombTimer;
	private boolean bouncingBomb;
	private boolean onBomb;

	public Player(Animation anim, Animation dyingAnim) {
		super(anim, dyingAnim);
		this.setMaxVelocity(0.15f);
		this.setLifes(30);
		this.possibleBombs = 5;
		this.plantedBombs = 0;
		this.bombArea = 5;
		this.bombTimer = 3000;
		this.defaultSpeed = this.getMaxVelocity();
		this.bouncingBomb = true;
		this.onBomb = false;
		this.setDyingTime(2000);
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
	
	public int getPossibleBombs() {
		return this.possibleBombs;
	}
	
	public void setPossibleBombs(int amount) {
		this.possibleBombs = amount;
	}
	
	public boolean isBouncingBomb() {
		return this.bouncingBomb;
	}
	
	public void setBouncingBomb(boolean bouncing) {
		this.bouncingBomb = bouncing;
	}
	
	public long getBombTimer() {
		return this.bombTimer;
	}
	
	public void setBombTimer(long timer) {
		this.bombTimer = timer;
	}
	
	public boolean isOnBomb() {
		return onBomb;
	}
	
	public void setOnBomb(boolean onBomb) {
		this.onBomb = onBomb;
	}
	
	public void setDead() {
		super.setDead();
		this.lifes--;
		this.plantedBombs = 0;
	}

	public void reset() {
		this.bombArea = 1;
		this.possibleBombs = 1;
		this.plantedBombs = 0;
		this.bouncingBomb = false;
		this.setMaxVelocity(this.defaultSpeed);
	}
}

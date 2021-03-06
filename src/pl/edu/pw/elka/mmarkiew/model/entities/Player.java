package pl.edu.pw.elka.mmarkiew.model.entities;

/**
 * Represents special entity - Player entity
 * @author Kajo
 *
 */
public class Player extends Entity {
	private int lifes;
	/** How many bombs, player can plant */
	private int possibleBombs;
	private int plantedBombs;
	/** Actual bomb exploding area */
	private int bombArea;
	private float defaultSpeed;
	/** Time after which bomb explodes */
	private long bombTimer;
	/** If bomb can be pushed */
	private boolean bouncingBomb;
	/** Tells is player after bomb planting and still on it, to do not checking collisions with bombs */
	private boolean onBomb;
	/** If player is immortal */
	private boolean immoritality;
	
	public Player(final Animation anim, final Animation dyingAnim, final int width, final int height)
	{
		super(anim, dyingAnim, width, height);
		this.setMaxVelocity(0.15f);
		this.setLifes(3);
		this.possibleBombs = 1;
		this.plantedBombs = 0;
		this.bombArea = 1;
		this.bombTimer = 3000;
		this.defaultSpeed = this.getMaxVelocity();
		this.bouncingBomb = false;
		this.onBomb = false;
		this.immoritality = false;
		this.setDyingTime(2000);
	}
	
	/**
	 * Increment planted bomb counter
	 */
	public void plantBomb()
	{
		if (plantedBombs < possibleBombs)
		{
			++plantedBombs;
		}
	}
	
	/**
	 * Decrement planted bomb counter
	 */
	public void bombExploded()
	{
		if (plantedBombs > 0)
		{
			plantedBombs--;
		}
	}
	
	public boolean canPlantBomb()
	{
		return (plantedBombs < possibleBombs);
	}

	public int getLifes()
	{
		return lifes;
	}

	public void setLifes(final int lifes)
	{
		this.lifes = lifes;
	}

	public int getBombArea()
	{
		return bombArea;
	}

	public void setBombArea(final int bombArea)
	{
		this.bombArea = bombArea;
	}
	
	public int getPossibleBombs()
	{
		return this.possibleBombs;
	}
	
	public void setPossibleBombs(final int amount)
	{
		this.possibleBombs = amount;
	}
	
	public boolean isBouncingBomb()
	{
		return this.bouncingBomb;
	}
	
	public void setBouncingBomb(final boolean bouncing)
	{
		this.bouncingBomb = bouncing;
	}
	
	public long getBombTimer()
	{
		return this.bombTimer;
	}
	
	public void setBombTimer(final long timer)
	{
		this.bombTimer = timer;
	}
	
	public boolean isOnBomb()
	{
		return onBomb;
	}
	
	public void setOnBomb(final boolean onBomb)
	{
		this.onBomb = onBomb;
	}
	
	public boolean isImmortal()
	{
		return immoritality;
	}
	
	public void setImmortality(final boolean immortality)
	{
		this.immoritality = immortality;
	}
	
	/**
	 * Set player dead, decrement life counter and clear bomb planted counter
	 */
	public void setDead()
	{
		super.setDead();
		this.lifes--;
		this.plantedBombs = 0;
	}

	/**
	 * Reset player state into defaults without touching lifes counter
	 */
	public void reset()
	{
		this.bombArea = 1;
		this.possibleBombs = 1;
		this.plantedBombs = 0;
		this.bouncingBomb = false;
		this.immoritality = false;
		this.setMaxVelocity(this.defaultSpeed);
	}
}

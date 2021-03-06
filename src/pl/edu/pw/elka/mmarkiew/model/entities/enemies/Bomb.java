package pl.edu.pw.elka.mmarkiew.model.entities.enemies;

import pl.edu.pw.elka.mmarkiew.model.GameMap;
import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;

/**
 * Class representing Bomb entity
 * @author Kajo
 *
 */
public class Bomb extends Entity {
	private long plantTime;
	/** Time in milliseconds */
	private long timer;
	/** How far it can reach in tiles */
	private int area;
	
	/**
	 * Creates Bomb with given Animation on position
	 * 
	 * @param anim - Bomb Animation
	 * @param width - Object width
	 * @param height - Object height
	 * @param x - Position
	 * @param y - Position
	 * @param plantTime - Time when bomb was planted
	 * @param timer - Time after which bomb explodes
	 * @param area - Area which is reachable during explosion
	 */
	public Bomb(final Animation anim, final int width, final int height, final float x, final float y,
																final long plantTime, final long timer, final int area)
	{
		super(anim, anim, width, height);
		this.setX(GameMap.getTileCenterFromPosition(x));
		this.setY(GameMap.getTileCenterFromPosition(y));
		this.plantTime = plantTime;
		this.timer = timer;
		this.area = area;
	}

	public long getPlantTime()
	{
		return plantTime;
	}

	public long getTimer()
	{
		return timer;
	}
	
	public int getArea()
	{
		return area;
	}
	
	/**
	 * If there is some velocity, bounce it<br>
	 * If there is no velocity do nothing
	 */
	public void collisionX()
	{
		this.setXVelocity(-this.getXVelocity());
	}

	/**
	 * If there is some velocity, bounce it<br>
	 * If there is no velocity do nothing
	 */
	public void collisionY()
	{
		this.setYVelocity(-this.getYVelocity());
	}

	/**
	 * Sets bomb timer to 0, which means that it should explode as fast as can calculate it
	 */
	public void setDead()
	{
		super.setDead();
		this.timer = 0;
	}
}
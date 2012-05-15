package pl.edu.pw.elka.mmarkiew.model.entities;

import pl.edu.pw.elka.mmarkiew.model.GameMap;

/**
 * Abstract class represents entity
 * @author Kajo
 *
 */
public abstract class Entity {
	private Animation anim;
	private Animation dyingAnim;
	private float x;
	private float y;
	private float xVelocity;
	private float yVelocity;
	private float maxVelocity;
	private float width;
	private float height;
	private boolean alive;
	/** Time when entity dies */
	private long dieTime;
	/** Time after which entity is really dead */
	private int dyingTime;
	
	/**
	 * Creates entity with its animations in game
	 * 
	 * @param anim - Animation when is alive
	 * @param dyingAnim - Animation when is dead
	 * @param width - Object width
	 * @param height - Object height
	 */
	public Entity(final Animation anim, final Animation dyingAnim, final int width, final int height)
	{
		this.anim = anim;
		this.dyingAnim = dyingAnim;
		this.width = Math.min(width, GameMap.BLOCK_SIZE);
		this.height = Math.min(height, GameMap.BLOCK_SIZE);
		this.x = 0;
		this.y = 0;
		this.xVelocity = 0;
		this.yVelocity = 0;
		this.maxVelocity = 0;
		this.alive = true;
		this.dieTime = -1;
		this.dyingTime = 2000;
	}
	
	/**
	 * Update state of entity
	 * 
	 * @param elapsedTime
	 */
	public void update(final long elapsedTime)
	{
		if (alive)
		{
			this.x += xVelocity * elapsedTime;
			this.y += yVelocity * elapsedTime;
			anim.update(elapsedTime);
		}
		else
		{
			dyingAnim.update(elapsedTime);
		}
	}
	
	/**
	 * It happens when is horizontal collision
	 */
	public void collisionX()
	{
		this.xVelocity = 0f;
	}
	
	/**
	 * It happens when is vertical collision
	 */
	public void collisionY()
	{
		this.yVelocity = 0f;
	}

	/**
	 * Return actual animation animFrame
	 * 
	 * @return Actual animFrame, if dead -animFrame
	 */
	public int getAnimFrame()
	{
		if (isAlive())
		{
			return this.anim.getAnimFrame();
		}
		else
		{
			if (this.anim == this.dyingAnim)
			{
				return this.dyingAnim.getAnimFrame();
			}
			else
			{
				return -(this.dyingAnim.getFrameCount() - this.dyingAnim.getAnimFrame());
			}
		}
	}

	public void setAnim(final Animation anim)
	{
		this.anim = anim;
	}

	public void setDyingAnim(final Animation anim)
	{
		this.dyingAnim = anim;
	}

	public float getX()
	{
		return x;
	}

	public void setX(final float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(final float y)
	{
		this.y = y;
	}

	public float getXVelocity()
	{
		return xVelocity;
	}

	public void setXVelocity(final float xVelocity)
	{
		this.xVelocity = xVelocity;
	}

	public float getYVelocity()
	{
		return yVelocity;
	}

	public void setYVelocity(final float yVelocity)
	{
		this.yVelocity = yVelocity;
	}
	
	public float getMaxVelocity()
	{
		return maxVelocity;
	}
	
	public void setMaxVelocity(final float maxVelocity)
	{
		this.maxVelocity = maxVelocity;
	}
	
	public float getWidth()
	{
		return width;
	}
	
	public void setWidth(final float width)
	{
		this.width = width;
	}
	
	public float getHeight()
	{
		return height;
	}
	
	public void setHeight(final float height)
	{
		this.height = height;
	}
	
	public boolean isAlive()
	{
		return this.alive;
	}

	public void setAlive(final boolean alive)
	{
		this.alive = alive;
	}

	/**
	 * Sets time when entity die and sets it dead
	 */
	public void setDead()
	{
		this.alive = false;
		this.dieTime = System.currentTimeMillis();
	}

	public long getDieTime()
	{
		return dieTime;
	}

	public void setDieTime(final long dieTime)
	{
		this.dieTime = dieTime;
	}

	public int getDyingTime()
	{
		return this.dyingTime;
	}
	
	public void setDyingTime(final int dyingTime)
	{
		this.dyingTime = dyingTime;
	}
}

package pl.edu.pw.elka.mmarkiew.model;

import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Contains information about player to send it away
 * @author Kajo
 *
 */
public class ModelStatistics {
	/** Game elapsed time from the beginning */
	private final long timer;
	private final int level;
	private final int lifes;
	private final int bombs;
	private final int bombArea;
	/** Bomb explosion delay */
	private final long bombTimer;
	/** Can bomb bounces */
	private final boolean bouncingBomb;
	/** Background music on / off */
	private final boolean backgroundOn;
	/** Sound effects on / off */
	private final boolean soundsOn;

	/**
	 * Gather information about player
	 * 
	 * @param player - Player
	 * @param timer - Gameplay time
	 * @param level - Actual level
	 */
	public ModelStatistics(final Player player, final long timer, final int level)
	{
		this.timer = timer;
		this.level = level;
		this.lifes = player.getLifes();
		this.bombs = player.getPossibleBombs();
		this.bombArea = player.getBombArea();
		this.bombTimer = player.getBombTimer();
		this.bouncingBomb = player.isBouncingBomb();
		this.backgroundOn = Model.sound.isBackgroundOn();
		this.soundsOn = Model.sound.isSoundEffectOn();
	}

	public long getTimer()
	{
		return timer;
	}

	public int getLevel()
	{
		return level;
	}

	public int getLifes()
	{
		return lifes;
	}

	public int getBombs()
	{
		return bombs;
	}

	public int getBombArea()
	{
		return bombArea;
	}

	public long getBombTimer()
	{
		return bombTimer;
	}
	
	public boolean isBouncingBomb()
	{
		return bouncingBomb;
	}

	public boolean isBackgroundOn()
	{
		return backgroundOn;
	}

	public boolean isSoundEffectOn()
	{
		return soundsOn;
	}

}

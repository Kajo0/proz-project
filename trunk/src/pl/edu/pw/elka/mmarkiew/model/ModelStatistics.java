package pl.edu.pw.elka.mmarkiew.model;

import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Contains information about player to send it away
 * @author Acer
 *
 */
public class ModelStatistics {
	private long timer;
	private int level;
	private int lifes;
	private int bombs;
	private int bombArea;
	private long bombTimer;
	private boolean bouncingBomb;
	
	/**
	 * Creates null model => Everything is 0
	 */
	public ModelStatistics() {
		this.timer = 0;
		this.level = 0;
		this.lifes = 0;
		this.bombs = 0;
		this.bombArea = 0;
		this.bombTimer = 0;
		this.bouncingBomb = false;
	}

	/**
	 * Gather information about player
	 * @param player - Player
	 * @param timer - Gameplay time
	 * @param level - Actual level
	 */
	public ModelStatistics(final Player player, long timer, int level) {
		this.timer = timer;
		this.level = level;
		this.lifes = player.getLifes();
		this.bombs = player.getPossibleBombs();
		this.bombArea = player.getBombArea();
		this.bombTimer = player.getBombTimer();
		this.bouncingBomb = player.isBouncingBomb();
	}

	public long getTimer() {
		return timer;
	}

	public int getLevel() {
		return level;
	}

	public int getLifes() {
		return lifes;
	}

	public int getBombs() {
		return bombs;
	}

	public int getBombArea() {
		return bombArea;
	}

	public long getBombTimer() {
		return bombTimer;
	}
	
	public boolean isBouncingBomb() {
		return bouncingBomb;
	}

}

package pl.edu.pw.elka.mmarkiew.model;

import java.util.LinkedList;

import pl.edu.pw.elka.mmarkiew.model.entities.Bonus;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.map.BlockElement;
import pl.edu.pw.elka.mmarkiew.model.map.BlockHolder;

public class MapToDraw {
	private BlockHolder blocks;
	private LinkedList<Entity> entities;
	private LinkedList<Bonus> bonuses;
	private int widthBlocks;
	private int heightBlocks;
	private boolean paused;
	private boolean started;
	private boolean win;
	private boolean over;

	public MapToDraw(final BlockHolder blockHolder, final LinkedList<Entity> entities, final LinkedList<Bonus> linkedList,
											final int widthBlocks, final int heightBlocks,
											final boolean paused, final boolean running, boolean win, boolean over) {
		this.blocks = blockHolder;
		this.entities = entities;
		this.bonuses = linkedList;
		this.widthBlocks = widthBlocks;
		this.heightBlocks = heightBlocks;
		this.paused = paused;
		this.started = running;
		this.win = win;
		this.over = over;
	}
	
	public MapToDraw(boolean started, boolean win, boolean over) {
		this(null, null, null, 0, 0, false, started, win, over);
	}

	public BlockElement getBlock(int x, int y) {
		return blocks.getBlock(x, y);
	}

	public LinkedList<Entity> getEntities() {
		return entities;
	}
	
	public LinkedList<Bonus> getBonuses() {
		return bonuses;
	}
	
	public int getWidthBlocks() {
		return widthBlocks;
	}

	public int getHeightBlocks() {
		return heightBlocks;
	}

	public boolean isPaused() {
		return paused;
	}

	public boolean isStarted() {
		return started;
	}
	
	public boolean isWin() {
		return win;
	}
	
	public boolean isOver() {
		return over;
	}
}

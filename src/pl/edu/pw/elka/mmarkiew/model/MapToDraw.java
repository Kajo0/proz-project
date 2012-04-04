package pl.edu.pw.elka.mmarkiew.model;

import java.util.LinkedList;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.map.BlockElement;

public class MapToDraw {
	private BlockElement[][] blocks;
	private LinkedList<Entity> entities;
	private int widthBlocks;
	private int heightBlocks;
	private boolean paused;
	private boolean started;

	public MapToDraw(final BlockElement[][] blocks, final LinkedList<Entity> linkedList,
											final int widthBlocks, final int heightBlocks,
											final boolean paused, final boolean running) {
		this.blocks = blocks;
		this.entities = linkedList;
		this.widthBlocks = widthBlocks;
		this.heightBlocks = heightBlocks;
		this.paused = paused;
		this.started = running;
	}
	
	public MapToDraw(boolean started) {
		this.blocks = null;
		this.entities = null;
		this.widthBlocks = 0;
		this.heightBlocks = 0;
		this.paused = false;
		this.started = false;
	}

	public BlockElement getBlock(int x, int y) {
		if (x < 0 || x > widthBlocks || y < 0 || y > heightBlocks)
			return null;
		else return blocks[x][y];
	}

	public LinkedList<Entity> getEntities() {
		return entities;
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
}

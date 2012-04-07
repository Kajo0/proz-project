package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Image;
import pl.edu.pw.elka.mmarkiew.model.GameMap;

public class DestroyingBrick extends Entity implements Explosion {
	public DestroyingBrick(Image anim, int x, int y) {
		super(anim, anim);
		this.setX(x * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2);
		this.setY(y * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2);
		this.setWidth(GameMap.BLOCK_SIZE);
		this.setHeight(GameMap.BLOCK_SIZE);
		this.setDyingTime(500);
		setDead();
	}
	
	public void setDead() {
		super.setDead();
		this.setAlive(true);
	}
}

package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Image;
import pl.edu.pw.elka.mmarkiew.model.GameMap;

public class Exit extends Entity implements Bonus {
	public Exit(Image anim, Image dyingAnim, float x, float y) {
		super(anim, dyingAnim);
		this.setX(GameMap.getTilePosition(x) * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2);
		this.setY(GameMap.getTilePosition(y) * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2);
		this.setAlive(false);
	}
	
	public void setDead() {
		
	}
}

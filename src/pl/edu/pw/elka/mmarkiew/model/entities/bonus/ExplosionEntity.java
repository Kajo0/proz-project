package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.GameMap;
import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;

public class ExplosionEntity extends Entity implements Explosion {
	public ExplosionEntity(Animation anim, int x, int y) {
		super(anim, anim);
		this.setX(GameMap.getTileCenterFromTile(x));
		this.setY(GameMap.getTileCenterFromTile(y));
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

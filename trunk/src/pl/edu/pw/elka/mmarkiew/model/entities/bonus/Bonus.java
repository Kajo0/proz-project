package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

public abstract class Bonus extends Entity {
	public Bonus(Animation anim, Animation dyingAnim, float x, float y) {
		super(anim, dyingAnim);
		this.setX(x);
		this.setY(y);
		this.setDyingTime(0);
	}
	
	public abstract void bonusideEntity(Player e);
}

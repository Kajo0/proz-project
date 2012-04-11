package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Bonus representing Exit doors from game<br>
 * Default dead, has no bonus
 * @author Acer
 *
 */
public class Exit extends Bonus {
	public Exit(Animation anim, Animation dyingAnim, float x, float y) {
		super(anim, dyingAnim, x, y);
		this.setAlive(false);
	}
	
	public void setDead() {
	}
	
	public void bonusideEntity(Player e) {
	}
}

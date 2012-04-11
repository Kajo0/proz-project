package pl.edu.pw.elka.mmarkiew.model.entities.bonus;

import pl.edu.pw.elka.mmarkiew.model.entities.Animation;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

/**
 * Bonus increment player's bomb explosion area 
 * @author Acer
 *
 */
public class IncreaseBombAreaBonus extends Bonus {

	public IncreaseBombAreaBonus(Animation anim, Animation dyingAnim, float x, float y) {
		super(anim, dyingAnim, x, y);
	}
	
	public void bonusideEntity(Player e) {
		e.setBombArea(e.getBombArea() + 1);
	}
}

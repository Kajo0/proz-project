package pl.edu.pw.elka.mmarkiew.model.entities;

public class IncreaseLifeNumberBonus extends Bonus {

	public IncreaseLifeNumberBonus(Animation anim, Animation dyingAnim, float x, float y) {
		super(anim, dyingAnim, x, y);
	}
	
	public void bonusideEntity(Player e) {
		e.setLifes(e.getLifes() + 1);
	}
}

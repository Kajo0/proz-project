package pl.edu.pw.elka.mmarkiew.model.entities;

public class IncreaseBombAmountBonus extends Bonus {

	public IncreaseBombAmountBonus(Animation anim, Animation dyingAnim, float x, float y) {
		super(anim, dyingAnim, x, y);
	}
	
	public void bonusideEntity(Player e) {
		e.setPossibleBombs(e.getPossibleBombs() + 1);
	}
}

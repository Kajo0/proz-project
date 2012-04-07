package pl.edu.pw.elka.mmarkiew.model.entities;

public class SpeedBonus extends Bonus {

	public SpeedBonus(Animation anim, Animation dyingAnim, float x, float y) {
		super(anim, dyingAnim, x, y);
	}
	
	public void bonusideEntity(Entity e) {
		e.setMaxVelocity(e.getMaxVelocity() + 0.05f);
	}
}

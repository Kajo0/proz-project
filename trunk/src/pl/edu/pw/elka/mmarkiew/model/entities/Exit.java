package pl.edu.pw.elka.mmarkiew.model.entities;

public class Exit extends Bonus {
	public Exit(Animation anim, Animation dyingAnim, float x, float y) {
		super(anim, dyingAnim, x, y);
		this.setAlive(false);
	}
	
	public void setDead() {
	}
	
	public void bonusideEntity(Entity e) {
	}
}

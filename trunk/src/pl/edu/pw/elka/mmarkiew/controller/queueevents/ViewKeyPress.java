package pl.edu.pw.elka.mmarkiew.controller.queueevents;

/**
 * To mediate between view and controller via keyboard
 * @author Acer
 *
 */
public class ViewKeyPress extends QueueEvent {
	public static enum Keys{UP, DOWN, LEFT, RIGHT, TIMER_1, TIMER_2, TIMER_3, PLANT,
							PAUSE, NEW_GAME, BACKGROUND_MUSIC, SOUND_EFFECTS, EXIT, UNDEFINED};
							
	private Keys key;
	/** Pressed or released */
	private boolean press;
	
	public ViewKeyPress(Keys key, boolean press) {
		this.key = key;
		this.press = press;
	}

	public Keys getKey() {
		return key;
	}
	
	public boolean isPress() {
		return press;
	}
	
}

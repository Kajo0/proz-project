package pl.edu.pw.elka.mmarkiew.controller.queueevents;

/**
 * To mediate between view and controller via keyboard
 * @author Acer
 *
 */
public class ViewKeyPress extends QueueEvent {
	/**
	 * Represents action which can be done on game model
	 * @author Acer
	 *
	 */
	public static enum Keys	{	UP, DOWN, LEFT, RIGHT,
								TIMER_1, TIMER_2, TIMER_3,
								PLANT,
								PAUSE, NEW_GAME, EXIT,
								BACKGROUND_MUSIC, SOUND_EFFECTS,
								UNDEFINED	};
	
	private final Keys key;
	/** Pressed or released */
	private final boolean press;
	
	public ViewKeyPress(final Keys key, final boolean press)
	{
		this.key = key;
		this.press = press;
	}

	public Keys getKey()
	{
		return key;
	}
	
	public boolean isPress()
	{
		return press;
	}
	
}

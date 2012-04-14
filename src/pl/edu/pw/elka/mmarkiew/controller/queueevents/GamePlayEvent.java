package pl.edu.pw.elka.mmarkiew.controller.queueevents;

/**
 * To mediate between view and controller via clicks
 * @author Acer
 *
 */
public class GamePlayEvent implements ViewEvent {
	public static enum codes {
		NEW_GAME,
		SWITCH_BACKGROUND_MUSIC,
		SWITCH_SOUND_EFFECTS;
	}
	private codes code;
	
	public GamePlayEvent(codes code) {
		this.code = code;
	}
	
	public codes getCode() {
		return code;
	}

}

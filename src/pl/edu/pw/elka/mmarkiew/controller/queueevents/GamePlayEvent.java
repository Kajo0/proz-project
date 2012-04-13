package pl.edu.pw.elka.mmarkiew.controller.queueevents;

/**
 * To mediate between view and controller via clicks
 * @author Acer
 *
 */
public class GamePlayEvent implements ViewEvent {
	public static enum codes {
		NEW_GAME;
	}
	private codes code;
	
	public GamePlayEvent(codes code) {
		this.code = code;
	}
	
	public codes getCode() {
		return code;
	}

}

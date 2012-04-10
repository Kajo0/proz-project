package pl.edu.pw.elka.mmarkiew.controller.queueevents;

/**
 * To mediate between view and controller
 * @author Acer
 *
 */
public class ViewKeyPress implements ViewEvent {
	private int keyCode;
	/** Pressed or released */
	private boolean press;
	
	public ViewKeyPress(int keyCode, boolean press) {
		this.keyCode = keyCode;
		this.press = press;
	}

	public int getKeyCode() {
		return keyCode;
	}
	
	public boolean isPress() {
		return press;
	}
	
}

package pl.edu.pw.elka.mmarkiew.controller.queueevents;

public class ViewKeyPress implements ViewEvent {
	private int keyCode;
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

package pl.edu.pw.elka.mmarkiew.model;

public class ColorChange {
	public static enum colors {
		RED, GREEN, BLUE;
	}
	
	private int value;
	private colors color;
	
	public ColorChange(colors cc, int newValue) {
		this.value = newValue;
		this.color = cc;
	}
	
	public colors getColor() {
		return color;
	}
	public int getValue() {
		return value;
	}
}

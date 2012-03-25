package pl.edu.pw.elka.mmarkiew.model;

import java.awt.Color;

public class Model {
	private int red, green, blue;
	private String text;
	
	public Model() {
		this.red = 0;
		this.green = 0;
		this.blue = 0;
		this.text = "RGB(" + this.red + "," + this.green + "," + this.blue + ")";
	}
	
	public Color calculate(ColorChange cc) {
		switch (cc.getColor()) {
			case RED	: red = cc.getValue(); System.out.println("r"); break;
			case GREEN	: green = cc.getValue(); System.out.println("g"); break;
			case BLUE	: blue = cc.getValue(); System.out.println("b"); break;
		}
		return new Color(red, green, blue);
	}

}

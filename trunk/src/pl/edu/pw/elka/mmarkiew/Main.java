package pl.edu.pw.elka.mmarkiew;

import pl.edu.pw.elka.mmarkiew.controller.Controller;

public class Main {

	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		try {
		new Controller().run();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}

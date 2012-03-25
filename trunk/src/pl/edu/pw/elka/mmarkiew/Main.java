package pl.edu.pw.elka.mmarkiew;

import pl.edu.pw.elka.mmarkiew.controller.Controller;
import pl.edu.pw.elka.mmarkiew.model.Model;
import pl.edu.pw.elka.mmarkiew.view.View;

/**
 * @author Miko³aj Markiewicz
 * @version 0.0
 *
 */
public class Main {

	/**
	 * Main class, the begin of the program
	 */
	public Main() {
		View view = new View();
		Model model = new Model();
		Controller controller = new Controller(model, view);
		
		Thread th = new Thread(controller);
		th.start();
	}
	public static void main(String[] args) {
		Main main = new Main();
	}

}

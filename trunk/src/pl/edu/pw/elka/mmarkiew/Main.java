package pl.edu.pw.elka.mmarkiew;

import pl.edu.pw.elka.mmarkiew.controller.*;
import pl.edu.pw.elka.mmarkiew.exceptions.NoViewException;
import pl.edu.pw.elka.mmarkiew.model.*;
import pl.edu.pw.elka.mmarkiew.view.*;

/**
 * @author Miko³aj Markiewicz
 * @version 0.0
 *
 */
public class Main {

	/**
	 * Main class, begin of the program
	 */
	public Main() {
		try {
			View view = View.getInstance();
		} catch (NoViewException e) {
			e.printStackTrace();
			return;
		}
//		Model model = new Model();
//		Controller controller = new Controller(model, view);
//		
//		Thread th = new Thread(controller);
//		th.start();
	}
	public static void main(String[] args) {
		Main main = new Main();
	}

}

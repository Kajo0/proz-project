package pl.edu.pw.elka.mmarkiew;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

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
		View view = new View();
		Model model = new Model();
		Controller controller = new Controller(model, view);
		
		ExecutorService exec = Executors.newCachedThreadPool();
		exec.execute(model);
		exec.execute(view);
		exec.execute(controller);
		
		exec.shutdown();
	}
	public static void main(String[] args) {
		Main main = new Main();
	}

}

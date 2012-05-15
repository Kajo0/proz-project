package pl.edu.pw.elka.mmarkiew;

import pl.edu.pw.elka.mmarkiew.controller.Controller;

/**
 * Main class
 * @author Kajo
 *
 */
public class Main {

	public static void main(String[] args)
	{
		new Main();
	}
	
	public Main()
	{
		try
		{
			new Controller().start();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}

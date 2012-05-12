package pl.edu.pw.elka.mmarkiew.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.ViewKeyPress;

public class ClickingListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e)
	{
		try
		{
			View.blockingQueue.put(new ViewKeyPress(ViewKeyPress.Keys.valueOf(e.getActionCommand()), true));
		}
		catch (InterruptedException ex) {}
	}
}

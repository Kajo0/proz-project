package pl.edu.pw.elka.mmarkiew.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pl.edu.pw.elka.mmarkiew.controller.ViewEventQueue;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.GamePlayEvent;

public class ClickingListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			ViewEventQueue.getInstance().put(new GamePlayEvent(GamePlayEvent.codes.valueOf(e.getActionCommand())));
			
		} catch (InterruptedException ex) {}
		catch (IllegalArgumentException ex) {}
	}
}

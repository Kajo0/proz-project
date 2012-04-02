package pl.edu.pw.elka.mmarkiew.controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import pl.edu.pw.elka.mmarkiew.controller.queueevents.ViewEvent;

public class ViewEventQueue {
	private static BlockingQueue<ViewEvent> queue = null;
	
	public static final BlockingQueue<ViewEvent> getInstance() {
		if (queue == null)
			return (queue = new LinkedBlockingQueue<ViewEvent>());
		else
			return queue;
	}
	
	private ViewEventQueue() {}
	
}

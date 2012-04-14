package pl.edu.pw.elka.mmarkiew.controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.ViewEvent;

public abstract class ViewEventQueue {
	/** Static queue waiting for ViewEvent events */
	private final static BlockingQueue<ViewEvent> queue = new LinkedBlockingQueue<ViewEvent>();
	
	/**
	 * Static access to the queue
	 * @return queue
	 */
	public final static BlockingQueue<ViewEvent> getInstance() {
		return queue;
	}
	
	/**
	 * Blocked constructor.<br><br>
	 * Static access -> Singleton
	 */
	private ViewEventQueue() {}
	
}

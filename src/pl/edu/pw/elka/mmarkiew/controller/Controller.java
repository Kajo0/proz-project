package pl.edu.pw.elka.mmarkiew.controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.QueueEvent;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.TimerEvent;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.ViewKeyPress;
import pl.edu.pw.elka.mmarkiew.controller.queueevents.ViewKeyPress.Keys;
import pl.edu.pw.elka.mmarkiew.model.Model;
import pl.edu.pw.elka.mmarkiew.model.SoundManager;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.view.View;

/**
 * Game Controller
 * @author Kajo
 *
 */
public class Controller {
	/** Queue for events */
	private final BlockingQueue<QueueEvent> blockingQueue;
	/** Sound things */
	private final SoundManager sound;
	/** Counts timeouts and put event into queue */
	private final Timer timer;
	/** Game model */
	private final Model model;
	/** Game view */
	private final View view;
	
	public Controller()
	{
		this.blockingQueue = new LinkedBlockingQueue<QueueEvent>();
		this.sound = new SoundManager();
		this.timer = new Timer();
		this.model = new Model(sound);
		this.view = new View(blockingQueue);

		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(new Thread(timer));
		executor.shutdown();
	}
	
	/**
	 * Every 10 milliseconds gets map and statistics<br>
	 * which sends to view. View should draw it.
	 */
	public void start()
	{
		QueueEvent event = null;
		
		while (true)
		{
			try
			{
				event = blockingQueue.take();
				checkInput(event);
			}
			catch (InterruptedException e) {}
		}
	}
	
	/**
	 * Check what is that event, and invoke some actions<br>
	 * depended on event. Almost all events change player state.
	 * 
	 * @param event - Event from View or Timer event
	 */
	private void checkInput(final QueueEvent event)
	{
		final Player player = model.getPlayer();
		final boolean xVelocity = (player.getXVelocity() != 0);
		final boolean yVelocity = (player.getYVelocity() != 0);
		
		if (event instanceof ViewKeyPress)
		{
			final Keys code = ((ViewKeyPress) event).getKey();
			final boolean pressed = ((ViewKeyPress) event).isPress();
			
			switch (code)
			{				//set velocity only if it's needed to set it, and reset it the same
				case UP:	if (pressed && !yVelocity)
							{
								player.setYVelocity(-player.getMaxVelocity());
							}
							else if (!pressed && yVelocity)
							{
								player.setYVelocity(0);
							}
							break;
				case DOWN:	if (pressed && !yVelocity)
							{
								player.setYVelocity(player.getMaxVelocity());
							}
							else if (!pressed && yVelocity)
							{
								player.setYVelocity(0);
							}
							break;
				case LEFT:	if (pressed && !xVelocity)
							{
								player.setXVelocity(-player.getMaxVelocity());
							}
							else if (!pressed && xVelocity)
							{
								player.setXVelocity(0);
							}
							break;
				case RIGHT:	if (pressed && !xVelocity)
							{
								player.setXVelocity(player.getMaxVelocity());
							}
							else if (!pressed && xVelocity)
							{
								player.setXVelocity(0);
							}
							break;
				case PLANT:	if (pressed)
							{
								model.plantBomb();
							}
							break;
				case TIMER_1:
				case TIMER_2:
				case TIMER_3:	if (pressed)
								{
									// depends on the number of timer enum, sets appropriate number of seconds
									player.setBombTimer( (code.ordinal() - Keys.TIMER_1.ordinal() + 1) * 1000 );
								}
								break;
				case PAUSE:	if (pressed)
							{
								model.switchPause();
							}
							break;
				case BACKGROUND_MUSIC:	if (pressed)
										{
											sound.switchBackgroundMusicEnable();
										}
										break;
				case SOUND_EFFECTS:	if (pressed)
									{
										sound.switchSoundEffectsEnable();
									}
									break;
				case NEW_GAME:	if (pressed && !model.isStarted())
								{
									model.newGame();
								}
								break;
				case EXIT:	System.exit(0);
							break;
			}
		}
		else if (event instanceof TimerEvent)
		{
			// Calculate next step of game and send it to draw
			model.nextGameLoop();
			view.sendMapModel(model.getMapToDraw());
			view.sendStatistics(model.getStatistics());
		}
	}
	
	/**
	 * Timer class to put update calculation events into queue
	 * @author Kajo
	 *
	 */
	private class Timer implements Runnable {
		final static long TIMEOUT = 10;
		
		/**
		 * Puts update model events into queue
		 */
		@Override
		public void run()
		{
			while (true)
			{
				try
				{
					blockingQueue.put(new TimerEvent());
					Thread.sleep(TIMEOUT);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}

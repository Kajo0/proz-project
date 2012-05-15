package pl.edu.pw.elka.mmarkiew.model;

import java.util.LinkedList;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.Bonus;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.Exit;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.Explosion;

/**
 * Game play model
 * @author Kajo
 *
 */
public class Model {
	/**	Time when started or negative otherwise */
	private long startTime;
	private long lastUpdateTime;
	private long gamePlayTime;
	private boolean paused;
	private boolean win;
	private boolean over;
	private GameMap map;
	/** Loading map and holds player entity */
	private MapLoader mapLoader;
	private CollisionDetector collisionDetector;
	/** Does bomb calculations */
	private BombCalculator bombCalculator;
	static SoundManager sound;

	/**
	 * Creates model with defaults parameters
	 */
	public Model(final SoundManager sound)
	{
		this.startTime = -1;
		this.lastUpdateTime = -1;
		this.gamePlayTime = 0;
		this.paused = false;
		this.win = false;
		this.over = false;
		this.map = null;
		this.mapLoader = new MapLoader();
		this.collisionDetector = new CollisionDetector(map);
		this.bombCalculator = new BombCalculator(map);
		Model.sound = sound;
		
		init();
	}

	/**
	 * Sets new game parameters
	 */
	public void newGame()
	{
		mapLoader.reset();
		
		this.gamePlayTime = 0;
		this.paused = false;
		this.win = false;
		this.over = false;
		this.startTime = System.currentTimeMillis();
		
		init();
	}
	
	/**
	 * Load first map,
	 */
	private void init()
	{
		// Next map in initialization is first map
		this.lastUpdateTime = this.startTime;
		
		// Load first map, first level = 0
		nextMap();
	}

	/**
	 * Main game 'loop' - next step of game
	 */
	public void nextGameLoop()
	{
		final long elapsedTime;
		
		elapsedTime = System.currentTimeMillis() - lastUpdateTime;
		lastUpdateTime += elapsedTime;
		
		/*
		 * If there is no pause, game is started
		 * and player is alive, update game
		 * 
		 * elapsedTime it refers to my processor delays
		 * too big elapsedTime = no collision detected ->
		 * everyone is like a ghost
		 */
		if (!paused && isStarted() && getPlayer().isAlive() && elapsedTime < 50)
		{
			update(elapsedTime);
			gamePlayTime += elapsedTime;
		}
		
		// If player is dead show animation and delete all bombs and explosions
		if (!getPlayer().isAlive())
		{
			playerDyingAnim();
		}
	}

	/**
	 * 'Shows' simple player dying animation<br>
	 * sets default player position and<br>
	 * removes explosions
	 */
	private void playerDyingAnim()
	{
		// While player hasn't fallen under map or during his dying time do not do that
		if ( getPlayer().getY() > map.getHeight() ||
							getPlayer().getDieTime() + getPlayer().getDyingTime() < System.currentTimeMillis())
		{
			// Set player alive and set him correct and remove explosions
			getPlayer().setAlive(true);
			getPlayer().setX((float) map.getPlayerStartPosition().getX());
			getPlayer().setY((float) map.getPlayerStartPosition().getY());
			
			map.removeExplosions();
			
			// If he has no lifes, set game over
			if (getPlayer().getLifes() < 1)
			{
				startTime = -1;
				over = true;
				win = false;
			}
		} 
		else 
		{
			// Fall him down
			getPlayer().setY(getPlayer().getY() + 5);
			getPlayer().update(25);
		}
	}

	/**
	 * Help updater
	 * 
	 * @param elapsedTime - Time which had elapsed before last update
	 */
	private void update(final long elapsedTime)
	{
		// Update player
		getPlayer().update(elapsedTime);
		
		// Update and possibly remove dead enemies and explosions
		updateAndRemoveDeadEnemies(elapsedTime);
		
		// Remove eaten bonuses
		removeBonuses();
		
		// After update detect collisions and calculate bomb things
		collisionDetector.detectCollision();
		bombCalculator.calculateBombs();

		// Check if there is clear map first step to next map
        checkMapCleared();
	}
	
	/**
	 * Update enemies and explosions<br>
	 * Remove them if they are dead after their dying time
	 */
	private void updateAndRemoveDeadEnemies(final long elapsedTime)
	{
		final LinkedList<Entity> entityToRemove = new LinkedList<Entity>();
		
		for (Entity e : map.getEntities())
		{
			e.update(elapsedTime);
			
			if ((!e.isAlive() || e instanceof Explosion) &&
													e.getDieTime() + e.getDyingTime() < System.currentTimeMillis())
			{
				entityToRemove.add(e);
			}
		}

		for (Entity e : entityToRemove)
		{
			map.removeEnemy(e);
			
		}
	}

	/**
	 * Removes dead bonuses<br>
	 * Bonus dying time is 0 so just remove if not alive
	 */
	private void removeBonuses()
	{
		final LinkedList<Bonus> toRemove = new LinkedList<Bonus>();
		
		for (Bonus b : map.getBonuses())
		{
			if (!b.isAlive())
			{
				toRemove.add(b);
			}
		}
		
		for (Bonus b : toRemove)
		{
			map.removeBonus(b);
		}
	}

	/**
	 * Check if there is enemy cleared map<br>
	 * and possibly find player collision with exit
	 */
	private void checkMapCleared()
	{
		// If no enemies then check collision with exits or set next map
		if (map.getEnemies().isEmpty())
		{
			if (map.getExits().isEmpty())
			{
				nextMap();
			}
			else
			{
				// If there are still dead set them alive
				if (!map.getExits().get(0).isAlive())
				{
					for (Exit e : map.getExits())
					{
						e.setAlive(true);
					}
				}
				
				// Check collisions with exits
				for (Exit e : map.getExits())
				{
					if (CollisionDetector.isEntitiesCollision(getPlayer(), e))
					{
						nextMap();
					}
				}
			}
		}
	}

	/**
	 * Loads next map
	 */
	private void nextMap()
	{
		// Next map -> players bonuses reset :(
		getPlayer().reset();
		
		// Set next map. If there is no next map (level > maxLevel) set win :D
		try
		{
			Model.sound.playSound(SoundManager.EXIT);
//			Thread.sleep(50);
			
			map = mapLoader.loadNextMap();
			collisionDetector.setMap(map);
			bombCalculator.setMap(map);
			System.gc();
		}
		catch (WinGameException e)
		{
			// Player win game, stop it
			collisionDetector.setMap(map);
			bombCalculator.setMap(map);
			startTime = -1;
			win = true;
			over = false;
		}
//		catch (InterruptedException e)
//		{
//			e.printStackTrace();
//		}
	}

	/**
	 * Generate new MapToDraw object dependent on game map<br>
	 * Player = last entity on the list
	 * 
	 * @return Map ready to paint
	 */
	public MapToDraw getMapToDraw()
	{
		final LinkedList<Entity> entities = new LinkedList<Entity>();

		// Create list of entities in proper order
		entities.addAll(map.getEnemies());
		entities.addAll(map.getBombs());
		entities.add(getPlayer());
		
		// Create proper ordered list of bonuses
		final LinkedList<Entity> bonuses = new LinkedList<Entity>(map.getBonuses());
		bonuses.addAll(map.getExits());
		
		return new MapToDraw(map.getBlockHolder(), entities, bonuses,
								map.getWidthInBlocks(), map.getHeightInBlocks(), paused, isStarted(), win, over);
	}

	/**
	 * Generate new ModelStatistics object dependent on game play situation
	 * 
	 * @return Player statistics
	 */
	public ModelStatistics getStatistics()
	{
		return new ModelStatistics(getPlayer(), gamePlayTime, mapLoader.getLevel());
	}

	/**
	 * Plants bomb if it's possible, calls inside method
	 */
	public void plantBomb() 
	{
		if (getPlayer().isAlive() && !paused && isStarted())
		{
			bombCalculator.plantBomb(getPlayer().getBombTimer());
		}
	}
	
	/**
	 * (Un)set game paused
	 */
	public void switchPause()
	{
		if (isStarted())
		{
			this.paused = !this.paused;
		}
	}

	/**
	 * 
	 * @return Current player if exists, null otherwise
	 */
	public Player getPlayer()
	{
		return mapLoader.getPlayer();
	}
	
	/**
	 * Has game been started?
	 * 
	 * @return true if game is running, false otherwise
	 */
	public boolean isStarted()
	{
		return startTime > 0;
	}
}

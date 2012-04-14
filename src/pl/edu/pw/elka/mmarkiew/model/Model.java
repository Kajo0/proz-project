package pl.edu.pw.elka.mmarkiew.model;

import java.util.LinkedList;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.Bonus;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.Exit;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.Explosion;

/**
 * Gameplay model
 * @author Acer
 *
 */
public class Model implements Runnable {
	private long startTime;
	private long gamePlayTime;
	private boolean paused;
	private boolean win;
	private boolean over;
	private GameMap map;
	private ResourceManager resource;
	private CollisionDetector collisionDetector;
	private BombCalculator bombCalculator;

	/**
	 * Creates model with defaults parameters
	 */
	public Model() {
		this.startTime = -1;
		this.gamePlayTime = 0;
		this.paused = false;
		this.win = false;
		this.over = false;
		this.map = null;
		this.resource = new ResourceManager();
		this.collisionDetector = new CollisionDetector(map);
		this.bombCalculator = new BombCalculator(map);
	}

	/**
	 * Invoke initialization and starts game loop
	 */
	@Override
	public void run() {
//		newGame();
		gameLoop();
	}

	/**
	 * Sets new game parameters
	 */
	public void newGame() {
		resource.reset();
		
		this.gamePlayTime = 0;
		this.paused = false;
		this.win = false;
		this.over = false;
		
		init();
	}
	
	/**
	 * Load first map,
	 */
	private void init() {
		/* Next map in initialization is first map */
		this.startTime = System.currentTimeMillis();
		
		/* Load first map, first level = 0 */
		nextMap();
	}

	/**
	 * Main game loop
	 */
	private synchronized void gameLoop() {
		long currentTime = this.startTime;
		long elapsedTime;
		
		while (true) {

			elapsedTime = System.currentTimeMillis() - currentTime;
            currentTime += elapsedTime;
			
            /*
             * If there is no pause, game is started
             * and player is alive, update game
             */
            if (!paused && startTime > 0 && getPlayer().isAlive()) {
            	update(elapsedTime);
            	gamePlayTime += elapsedTime;
            }
            
            /*
             * If player is dead show animation
             * and delete all bombs and explosions
             */
            if (!getPlayer().isAlive())
            	playerDyingAnim();
            
            /*
             * Little delay
             */
            try {
				wait(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 'Shows' simple player dying animation<br>
	 * sets default player position and<br>
	 * removes explosions
	 */
	private void playerDyingAnim() {
		
		/*
		 * While player hasn't fallen under map or during his dying time do not do that
		 */
		if ( getPlayer().getY() > map.getHeight() ||
				getPlayer().getDieTime() + getPlayer().getDyingTime() < System.currentTimeMillis()) {
			
			/*
			 * Set player alive and set him correct
			 * Remove explosions
			 */
			getPlayer().setAlive(true);
			getPlayer().setX((float) map.getPlayerStartPosition().getX());
			getPlayer().setY((float) map.getPlayerStartPosition().getY());
			
			map.removeExplosions();

			/*
			 * If he has no lifes, set game over
			 */
			if (getPlayer().getLifes() < 1) {
				startTime = -2;
				over = true;
				win = false;
			}
		} else {
			/*
			 * Fall him down
			 */
			getPlayer().setY(getPlayer().getY() + 5);
			getPlayer().update(25);
		}
	}

	/**
	 * Helper updater
	 * @param elapsedTime - Time which had elapsed before last update
	 */
	private synchronized void update(final long elapsedTime) {
		/*
		 * Update player
		 */
		getPlayer().update(elapsedTime);
		
		/*
		 * Update and possibly remove dead enemies and explosions
		 */
		updateAndRemoveDeadEnemies(elapsedTime);
		
		/*
		 * Remove eaten bonuses
		 */
		removeBonuses();
		
		/*
		 * After update detect collisions
		 * and calculate bomb things
		 */
		collisionDetector.detectCollision();
		bombCalculator.calculateBombs();
        
		/*
		 * Check if there is clear map
		 * first step to next map
		 */
        checkMapCleared();
	}
	
	/**
	 * Update enemies and explosions<br>
	 * Remove them if they are dead after their dying time
	 */
	private void updateAndRemoveDeadEnemies(long elapsedTime) {
		LinkedList<Entity> entityToRemove = new LinkedList<Entity>();
		
		for (Entity e : map.getEntities()) {
			e.update(elapsedTime);
			
			if ((!e.isAlive() || e instanceof Explosion) && e.getDieTime() + e.getDyingTime() < System.currentTimeMillis())
				entityToRemove.add(e);
		}
		
		for (Entity e : entityToRemove)
			map.removeEnemy(e);
	}

	/**
	 * Removes dead bonuses<br>
	 * Bonus dying time is 0 so just remove if not alive
	 */
	private void removeBonuses() {
		LinkedList<Bonus> toRemove = new LinkedList<Bonus>();
		
		for (Bonus b : map.getBonuses()) {
			if (!b.isAlive())
				toRemove.add(b);
		}
		
		for (Bonus b : toRemove)
			map.removeBonus(b);
	}

	/**
	 * Check if there is enemy cleared map<br>
	 * and possibly find player collision with exit
	 */
	private void checkMapCleared() {
		
		if (map.getEnemies().isEmpty()) {
			if (map.getExits().isEmpty())
				nextMap();
			else {
				/*
				 * If there are still dead set them alive
				 */
				if (!map.getExits().get(0).isAlive())
					for (Exit e : map.getExits())
						e.setAlive(true);
				
				/*
				 * Check collisions with exits
				 */
				for (Exit e : map.getExits())
					if (CollisionDetector.isEntitiesCollision(getPlayer(), e))
						nextMap();
			}
		}
	}

	/**
	 * Loads next map
	 */
	@SuppressWarnings("static-access")
	private synchronized void nextMap() {
		/*
		 * Next map -> players bonuses reset :(
		 */
		getPlayer().reset();
		
		/*
		 * Set next map
		 * If there is no next map (level > maxLevel) set win :D
		 */
		//TODO zmienic przejscie pomiedzy mapami
		try {
			SoundManager.playExit();
			Thread.currentThread().sleep(100);
			
			map = resource.loadNextMap();
			collisionDetector.setMap(map);
			bombCalculator.setMap(map);
			System.gc();
			
		} catch (WinGameException e) {
			/* Player win game, stop it */
			map = null;
			collisionDetector.setMap(map);
			bombCalculator.setMap(map);
			startTime = -1;
			win = true;
			over = false;

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generate new MapToDraw object dependent on game map
	 * @return Map ready to paint
	 */
	public synchronized MapToDraw getMapToDraw() {
		if (map != null) {
			LinkedList<Entity> entities = new LinkedList<Entity>();

			entities.addAll(map.getEnemies());
			entities.addAll(map.getBombs());
			entities.add(getPlayer());
			
			LinkedList<Entity> bonuses = new LinkedList<Entity>(map.getBonuses());
			bonuses.addAll(map.getExits());
			
			return new MapToDraw(map.getBlockHolder(), entities, bonuses, map.getWidthBlocks(), map.getHeightBlocks(),
																				paused, (startTime > 0), win, over);
		}
		return new MapToDraw(false, win, over);
	}

	/**
	 * Generate new ModelStatistics object dependent on game play situation
	 * @return Player statistics
	 */
	public synchronized ModelStatistics getStatistics() {
		if (getPlayer() != null)
			return new ModelStatistics(getPlayer(), gamePlayTime, resource.getLevel());
		else return new ModelStatistics();
	}

	public synchronized void plantBomb() {
		if (getPlayer().isAlive())
			bombCalculator.plantBomb(getPlayer().getBombTimer());
	}
	
	/**
	 * (Un)set game paused
	 */
	public synchronized void switchPause() {
		this.paused = !this.paused;
	}

	/**
	 * 
	 * @return Current player if exists, null otherwise
	 */
	public Player getPlayer() {
		if (resource != null)
			return resource.getPlayer();
		else return null;
	}
}

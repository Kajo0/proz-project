package pl.edu.pw.elka.mmarkiew.model;

import java.io.IOException;
import java.util.LinkedList;

import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.Bonus;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.Exit;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.Explosion;

public class Model implements Runnable {
	private long startTime;
	private long gamePlayTime;
	private boolean paused;
	private GameMap map;
	private ResourceManager resource;
	private CollisionDetector collisionDetector;
	private BombCalculator bombCalculator;
	private boolean win;
	private boolean over;

	public Model() {
		this.startTime = -1;
		this.gamePlayTime = 0;
		this.paused = false;
		this.map = null;
		this.resource = new ResourceManager();
		this.collisionDetector = null;
		this.bombCalculator = null;
		this.win = false;
		this.over = false;
	}

	@Override
	public synchronized void run() {
		init();
		gameLoop();
	}

	//loads first map
	private void init() {
		try {
			this.map = resource.loadNextMap();
			this.startTime = 0;
			this.collisionDetector = new CollisionDetector(map);
			this.bombCalculator = new BombCalculator(map);
		} catch (IOException e) {
			this.map = null;
			this.startTime = -1;
		}
	}

	private void gameLoop() {
		long currTime = this.startTime = System.currentTimeMillis();
		long elapsedTime;
		
		while (true) {
			elapsedTime = System.currentTimeMillis() - currTime;
            currTime += elapsedTime;
			
            if (!paused && startTime > 0 && getPlayer().isAlive()) {
            	update(elapsedTime);
            	gamePlayTime += elapsedTime;
            }
            
            if (!getPlayer().isAlive())
            	playerDyingAnim();
            
            try {
				wait(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	private void playerDyingAnim() {
		if ( getPlayer().getY() > map.getHeight() ||
				getPlayer().getDieTime() + getPlayer().getDyingTime() < System.currentTimeMillis()) {
			getPlayer().setAlive(true);
			getPlayer().setX((float) map.getPlayerStartPosition().getX());
			getPlayer().setY((float) map.getPlayerStartPosition().getY());
			map.removeExplosions();

			if (getPlayer().getLifes() < 1) {
				startTime = -2;
				over = true;
				win = false;
			}
		} else {
			getPlayer().setY(getPlayer().getY() + 5);
			getPlayer().update(25);
		}
	}

	private void update(final long elapsedTime) {
		LinkedList<Entity> entityToRemove = new LinkedList<Entity>();
		for (Entity e : map.getEntities()) {
			e.update(elapsedTime);
			if ((!e.isAlive() || e instanceof Explosion) && e.getDieTime() + e.getDyingTime() < System.currentTimeMillis())
				entityToRemove.add(e);
		}
		for (Entity e : entityToRemove)
			map.removeEnemy(e);
				
		removeBonuses();
		
		if (collisionDetector != null)
			collisionDetector.detectCollision();

		bombCalculator.calculateBombs();
        
        checkMapCleared();
	}
	
	private void removeBonuses() {
		LinkedList<Bonus> bonusToRemove = new LinkedList<Bonus>();
		for (Bonus b : map.getBonuses()) {
			if ( !b.isAlive() && !(b instanceof Exit) )
				bonusToRemove.add(b);
		}
		for (Bonus b : bonusToRemove)
			map.removeBonus(b);
	}

	private void checkMapCleared() {
		if (map.getEnemies().isEmpty()) {
			if (map.getBonuses().isEmpty())
				nextMap();
			else if (map.getBonuses().get(0) instanceof Exit) {
				map.getBonuses().get(0).setAlive(true);
				if (CollisionDetector.isEntitiesCollision(getPlayer(), map.getBonuses().get(0)))
					nextMap();
			}
		}
	}

	@SuppressWarnings("static-access")
	private void nextMap() {
		getPlayer().reset();
		try {//TODO zmienic przejscie pomiedzy mapami
			Thread.currentThread().sleep(100);
			map = resource.loadNextMap();
			collisionDetector.setMap(map);
			bombCalculator.setMap(map);
		} catch (IOException e) {
			map = null;
			collisionDetector.setMap(map);
			bombCalculator.setMap(map);
			startTime = -2;
			win = true;
			over = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized MapToDraw getMapToDraw() {
		//TODO tu musze skopiowac - deep copy wszystkiego
		if (map != null)
			return new MapToDraw(map.getBlockHolder(), map.getEntities(), map.getBonuses(), map.getWidthBlocks(),
													map.getHeightBlocks(), paused, (startTime > 0), win, over);
		return new MapToDraw(false, win, over);
	}
	
	public synchronized ModelStatistics getStatistics() {
		if (getPlayer() != null)
			return new ModelStatistics(getPlayer(), gamePlayTime, resource.getLevel());
		else return new ModelStatistics();
	}
	
	public void switchPause() {
		this.paused = !this.paused;
	}

	public Player getPlayer() {
		if (resource != null)
			return resource.getPlayer();
		else return null;
	}

	public void plantBomb() {
		bombCalculator.plantBomb(getPlayer().getBombTimer());
	}
	
}

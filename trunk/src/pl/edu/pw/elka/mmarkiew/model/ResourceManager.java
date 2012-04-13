package pl.edu.pw.elka.mmarkiew.model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.EntityFactory;
import pl.edu.pw.elka.mmarkiew.model.entities.GameEntities;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.entities.bonus.Bonus;
import pl.edu.pw.elka.mmarkiew.model.entities.enemies.Enemy;
import pl.edu.pw.elka.mmarkiew.model.map.BlockFactory;
import pl.edu.pw.elka.mmarkiew.model.map.GameBlock;

/**
 * Responsible for loading map
 * @author Acer
 *
 */
public class ResourceManager {
	private Player playerEntity;
	private int level;
	
	/**
	 * Creates player and set him on 0 level
	 */
	public ResourceManager() {
		this.level = 0;
		this.playerEntity = (Player) EntityFactory.createEntity(GameEntities.PLAYER);
	}

	/**
	 * Load next map from file ${++level}.txt
	 * @return
	 * @throws IOException
	 */
	public synchronized GameMap loadNextMap() throws IOException {
		++level;
		try {
			return loadMap();
		} catch (NullPointerException e) {
			if (level > 1)
				return generateMap();
			throw new IOException();
		}
	}
	
	/**
	 * Load Map from file
	 * @param path - Path to map
	 * @return Map of game
	 * @throws IOException
	 */
	private GameMap loadMap() throws IOException {
		ArrayList<String> listOfLines = new ArrayList<String>();

//		BufferedReader buffer = new BufferedReader(new FileReader(path));
		BufferedReader buffer = new BufferedReader(new FileReader(getClass()
																.getResource("/" + level + ".txt").getPath()));
		
		int width = 0;
		int height = 0;
		
		String line;
		while( (line = buffer.readLine()) != null) {
			listOfLines.add(line);
			width = Math.max(width, line.length());
		}	
		buffer.close();
		
		height = listOfLines.size();
		
		return analyzeMap(listOfLines, width, height);
	}

	/**
	 * Analyzes .txt file with map
	 * @param listOfLines - List of map lines
	 * @param width - Maximum length of line
	 * @param height - Number of lines
	 * @return game of map
	 */
	private GameMap analyzeMap(final ArrayList<String> listOfLines, int width, int height) {
		Iterator<String> it = listOfLines.iterator();
		String line;
		GameMap tempMap = new GameMap(this.playerEntity, width, height);
		int j = 0;

		while (it.hasNext()) {
			line = it.next();
			
			for (int i = 0; i < width; ++i) {
				if (i < line.length()) {
					chooseBlockEntityToCreate(tempMap, line.charAt(i), i, j);
				} else {
					tempMap.setBlock(BlockFactory.createElement(GameBlock.EMPTY), i, j);
				}
			}
			++j;
		}
		
		/*
		 * Sets player Velocity to 0
		 */
		playerEntity.setXVelocity(0);
		playerEntity.setYVelocity(0);
		/*
		 * If there was no specific of player Position
		 * sets it on (1,1) tile
		 */
		if (tempMap.getPlayerStartPosition().equals(new Point(0, 0)))
			tempMap.setPlayerStartPosition(GameMap.getPositionCenterFromTile(1), GameMap.getPositionCenterFromTile(1));
		playerEntity.setX((float) tempMap.getPlayerStartPosition().getX());
		playerEntity.setY((float) tempMap.getPlayerStartPosition().getY());
		
		return tempMap;
	}
	
	/**
	 * Choosing appropriate entity or/and block to create
	 * @param tempMap - Actual game of map
	 * @param charAt - Character to analyze
	 * @param i - Tile x position
	 * @param j - Tile y position
	 */
	private void chooseBlockEntityToCreate(final GameMap tempMap, char charAt, int i, int j) {

		/*
		 * Create appropriate block on this position
		 */
		tempMap.setBlock(BlockFactory.createElement( GameBlock.getEnumBlock( "" + charAt) ), i, j);
		
		/*
		 * Sets player position if it is Player character
		 */
		if ( GameEntities.getEnumEntity("" + charAt) == GameEntities.PLAYER)
			tempMap.setPlayerStartPosition(GameMap.getPositionCenterFromTile(i), GameMap.getPositionCenterFromTile(j));
		else
		/*
		 * Create appropriate entity and possibly hide it behind destructive block
		 */
		if ( GameEntities.getEnumEntity("" + charAt) != GameEntities.UNDEFINED) {
			Entity e = EntityFactory.createEntity( GameEntities.getEnumEntity("" + charAt), 
							(int) GameMap.getPositionCenterFromTile(i), (int) GameMap.getPositionCenterFromTile(j));
			/*
			 * If it's enemy add it into map
			 * else if it's bonus add it into map and hide it
			 */
			if (e instanceof Enemy)
				tempMap.addEnemy(e);
			else if (e instanceof Bonus) {
				tempMap.addBonus((Bonus) e);
				tempMap.setBlock(BlockFactory.createElement(GameBlock.BRICK), i, j);
			}
		}
	}
	
	/**
	 * Generates random map
	 * @return Created random map
	 * @throws IOException
	 */
	private GameMap generateMap() throws IOException {
		//TODO map generation
		try {
			int dimension = 10 + level / 2;
			dimension += (dimension % 2 == 0) ? 1 : 0;
			
			/*
			 * How often bonuses are generate
			 */
			int bonusSpawn = 5;
			/*
			 * How often enemies are generate
			 */
			int enemySpawn = 50 / level + 1;
			
			ArrayList<String> lines = new ArrayList<String>();
			StringBuilder line = new StringBuilder();
			
			Random rand = new Random();
			
			/*
			 * Generating map
			 */
			for (int i = 0; i < dimension; ++i) {
				line.setLength(0);
				
				for (int j = 0; j < dimension; ++j) {
					/*
					 * Borders & inside blocks
					 */
					if (i % (dimension - 1) == 0 || j % (dimension - 1) == 0 || i % 2 == 0 && j % 2 == 0)
						line.append(GameBlock.STONE.getCharacter());
					else {
						/*
						 * Left player some spawn space
						 */
						if (i == 1 && j <= 3 || j == 1 && i <= 3)
							line.append(GameBlock.EMPTY);
						else
							/*
							 * Rand bonus, enemy or brick
							 */
							if (rand.nextInt(bonusSpawn) == 0)
								line.append(GameEntities.getRandomBonusCharacter());
							else if (rand.nextInt(enemySpawn) == 0)
								line.append(GameEntities.getRandomEnemyCharacter());
							else
								line.append(GameBlock.values()[rand.nextInt(2) + 1].getCharacter());
					}
				}
				
				lines.add(line.toString());
			}

			return analyzeMap(lines, dimension, dimension);
//			throw new NullPointerException();
		} catch (NullPointerException e) {
			--level;
			throw new IOException();
		}
	}

	Player getPlayer() {
		return playerEntity;
	}

	int getLevel() {
		return level;
	}
	
}


package pl.edu.pw.elka.mmarkiew.model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import pl.edu.pw.elka.mmarkiew.model.entities.EntityFactory;
import pl.edu.pw.elka.mmarkiew.model.entities.GameEntities;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.map.BlockFactory;
import pl.edu.pw.elka.mmarkiew.model.map.GameBlock;

public class ResourceManager {
	private Player playerEntity;
	private int level;
	
	public ResourceManager() {
		this.level = 0;
		this.playerEntity = new Player();
	}
	
	public GameMap loadMap(String path) throws IOException {
		ArrayList<String> listOfLines = new ArrayList<String>();
		
		BufferedReader buffer = new BufferedReader(new FileReader(path));
		
		int width = 0;
		int height = 0;
		
		String line;
		while( (line = buffer.readLine()) != null) {
			listOfLines.add(line);
			width = Math.max(width, line.length());
		}	
		buffer.close();
		
		height = listOfLines.size();
		
		return AnalizeMap(listOfLines, width, height);
	}

	/**
	 * Invoke by loadMap to generate proper terrain map
	 * @param listOfLines
	 * @param width
	 * @param height
	 */
	private GameMap AnalizeMap(final ArrayList<String> listOfLines, final int width, final int height) {
		Iterator<String> it = listOfLines.iterator();
		String line;
		GameMap tempMap = new GameMap(this.playerEntity, width, height);
		int j = 0;

		while (it.hasNext()) {
			line = it.next();
			
			for (int i = 0; i < width; i++) {
				if (i < line.length()) {
					tempMap.setBlock(BlockFactory.createElement( GameBlock.getEnumBlock( "" + line.charAt(i)) ), i, j);
					if ( GameEntities.getEnumEntity("" + line.charAt(i)) == GameEntities.PLAYER)
						tempMap.setPlayerStartPosition(i * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2,
														j * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2);
					else if ( GameEntities.getEnumEntity("" + line.charAt(i)) != GameEntities.UNDEFINED)
						tempMap.addEnemy(EntityFactory.createEntity( GameEntities.getEnumEntity("" + line.charAt(i)), 
												i * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2,
												j * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2));
				} else {
					tempMap.setBlock(BlockFactory.createElement(GameBlock.SPACE), i, j);
				}
			}
			j++;
		}
		
		playerEntity.setXVelocity(0);
		playerEntity.setYVelocity(0);
		if (tempMap.getPlayerStartPosition().equals(new Point(0, 0)))
			tempMap.setPlayerStartPosition(GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2,
													GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2);
		playerEntity.setX((float) tempMap.getPlayerStartPosition().getX());
		playerEntity.setY((float) tempMap.getPlayerStartPosition().getY());
		
		return tempMap;
	}
	
	public Player getPlayer() {
		return playerEntity;
	}
	
}


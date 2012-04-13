package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pl.edu.pw.elka.mmarkiew.controller.Controller;
import pl.edu.pw.elka.mmarkiew.model.GameMap;
import pl.edu.pw.elka.mmarkiew.model.MapToDraw;
import pl.edu.pw.elka.mmarkiew.model.MapToDraw.SimpleEntity;

/**
 * Class responsible for painting images on game canv
 * @author Acer
 *
 */
public class MapPainter {
	private MapToDraw map;
	private Canvas panel;
	private int blockSize;
	/**
	 * To make big map shown it is needed to
	 * move drawing images on canvas
	 * Those are horozontal & vertical moves
	 */
	private int dx;
	private int dy;
	
	/**
	 * Creates new painter
	 * @param gamePanel - Canvas to draw on
	 * @param map - Map to paint
	 */
	public MapPainter(Canvas gamePanel, MapToDraw map) {
		this.panel = gamePanel;
		this.map = map;
		this.blockSize = GameMap.BLOCK_SIZE;
		this.dx = 0;
		this.dy = 0;
	}
	
	/**
	 * Calls appropriate paint functions to show it on canv
	 */
	public void paint() {
		
		Graphics g = panel.getBufferStrategy().getDrawGraphics();
		g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
		
		prepareDeltasMovingMap();
		
		/*
		 * If pauses show paused string
		 * If Game Over show sth else,
		 * If Game Win show sth else...
		 */
		if (map.isPaused())
			paintPauseMap(g);
		else if (map.isOver())
			paintGameOver(g);
		else if (map.isWin())
			paintWinLogo(g);
		else if (!map.isStarted())
			paintLogo(g);
		else {
			paintMap(g);
			paintBonuses(g);
			paintEntities(g);
		}
		
		g.dispose();
		panel.getBufferStrategy().show();
		
	}

	/**
	 * Calculates how much and where map should be
	 * moved to show player
	 */
	private void prepareDeltasMovingMap() {
		dx = 0;
		dy = 0;
		
		/*
		 * If there is any map => game is playing calculate deltas
		 * -> If there is too wide map move it to see player
		 */
		if (map.isStarted()) {
			
			float playerX = map.getEntities().getLast().getX();
			float playerY = map.getEntities().getLast().getY();
			
			if (map.getWidthBlocks() > panel.getWidth() / blockSize) {
				if (playerX > panel.getWidth() / 2) {
					
					dx = (int) (panel.getWidth() / 2 - playerX);
					
					/*
					 * If player reach right edge
					 */
					if (panel.getWidth() - dx > map.getWidthBlocks() * blockSize)
						dx = - (map.getWidthBlocks() * blockSize - panel.getWidth());
				}
			}
			
			if (map.getHeightBlocks() > panel.getHeight() / blockSize) {
				if (playerY > panel.getHeight() / 2) {
					
					dy = (int) (panel.getHeight() / 2 - playerY);

					/*
					 * If player reach bottom edge
					 */
					if (panel.getHeight() - dy > map.getHeightBlocks() * blockSize)
						dy = - (map.getHeightBlocks() * blockSize - panel.getHeight());
				}
			}
		}
	}

	/**
	 * Paints pause pane
	 * @param g - Canv graphics to draw on it
	 */
	private void paintPauseMap(Graphics g) {
		BufferedImage img = new BufferedImage(Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE,
																			BufferedImage.TYPE_INT_RGB);
		img.getGraphics().clearRect(0, 0, Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE / 2);
		img.getGraphics().drawString("PAUSE", Controller.GAME_X_SIZE / 2, Controller.GAME_Y_SIZE / 2);
		g.drawImage(img, 0, 0, panel);
		g.dispose();
	}
	

	/**
	 * Paints Logo pane
	 * @param g - Canv graphics to draw on it
	 */
	private void paintLogo(Graphics g) {
		BufferedImage img = new BufferedImage(Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE,
																			BufferedImage.TYPE_INT_RGB);
		img.getGraphics().clearRect(0, 0, Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE / 2);
		img.getGraphics().drawString("BOMBERMAN LOGO", Controller.GAME_X_SIZE / 2, Controller.GAME_Y_SIZE / 2);
		g.drawImage(img, 0, 0, panel);
		g.dispose();
	}
	
	/**
	 * Paints game over pane
	 * @param g - Canv graphics to draw on it
	 */
	private void paintGameOver(Graphics g) {
		BufferedImage img = new BufferedImage(Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE,
																			BufferedImage.TYPE_INT_RGB);
		img.getGraphics().clearRect(0, 0, Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE / 2);
		img.getGraphics().drawString("GAME OVER !", Controller.GAME_X_SIZE / 2, Controller.GAME_Y_SIZE / 2);
		g.drawImage(img, 0, 0, panel);
		g.dispose();
	}

	/**
	 * Paints win logo pane
	 * @param g - Canv graphics to draw on it
	 */
	private void paintWinLogo(Graphics g) {
		BufferedImage img = new BufferedImage(Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE,
																			BufferedImage.TYPE_INT_RGB);
		img.getGraphics().clearRect(0, 0, Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE / 2);
		img.getGraphics().drawString("WIN !!!!!!", Controller.GAME_X_SIZE / 2, Controller.GAME_Y_SIZE / 2);
		g.drawImage(img, 0, 0, panel);
		g.dispose();
	}

	/**
	 * Paints blocks on map
	 * @param g - Canv graphics to draw on it
	 */
	private void paintMap(Graphics g) {
		for (int j = 0; j < map.getHeightBlocks(); ++j) {
			for (int i = 0; i < map.getWidthBlocks(); ++i) {
				g.drawImage(map.getBlockImage(i, j), i * blockSize + dx, j * blockSize + dy, panel);
			}
		}
	}

	/**
	 * Paints entities on map
	 * @param g - Canv graphics to draw on it
	 */
	private void paintEntities(Graphics g) {
		for (SimpleEntity e : map.getEntities())
				g.drawImage(e.getImage(), ((int) e.getX()) - e.getImage().getWidth(panel) / 2 + dx,
											((int) e.getY()) - e.getImage().getHeight(panel) / 2 + dy, panel);
	}

	/**
	 * Paints bonuses on map and possibly hide them
	 * under hider block
	 * @param g - Canv graphics to draw on it
	 */
	private void paintBonuses(Graphics g) {
		for (SimpleEntity b : map.getBonuses()) {
			g.drawImage(b.getImage(), ((int) b.getX()) - b.getImage().getWidth(null) / 2 + dx,
										((int) b.getY()) - b.getImage().getHeight(null) / 2 + dy, panel);
			
			if (!map.isEmptyBlock((int) (b.getX() / blockSize), (int) (b.getY() / blockSize)))
				g.drawImage(map.getHiderBlock(),
								(int) b.getX() - map.getHiderBlock().getWidth(panel) / 2 + dx,
								(int) b.getY() - map.getHiderBlock().getHeight(panel) / 2 + dy,
								panel);
		}
	}
	
	public boolean isMap() {
		return this.map != null;
	}
	
	public void setMap(MapToDraw map) {
		this.map = map;
	}

}

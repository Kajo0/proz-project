package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
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
		this.blockSize = MapToDraw.blockSize;
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
		if (map.isOver() || map.isWin() || !map.isStarted())
			paintLogos(g);
		else {
			paintMap(g);
			paintBonuses(g);
			paintEntities(g);
			
			if (map.isPaused())
				paintLogos(g);
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
	 * Paints depends on game state: Pause/Win/Over/Logo - logos
	 * @param g - Canv graphics to draw on it
	 */
	private void paintLogos(Graphics g) {
		Image img;
		
		/* Draw background */
		if (!map.isPaused()) {
			int w = LogosResource.GAME_BACKGROUND.getImage().getWidth(null);
			int h = LogosResource.GAME_BACKGROUND.getImage().getHeight(null);
			
			for (int i = 0; i < panel.getWidth(); i += w)
				for (int j = 0; j < panel.getHeight(); j += h)
					g.drawImage(LogosResource.GAME_BACKGROUND.getImage(), i, j, panel);
		}
		
		/* Select appropriate image */
		if (map.isPaused() && map.isStarted() && !map.isWin() && !map.isOver())
			img = LogosResource.GAME_PAUSED.getImage();
		else if (map.isOver())
			img = LogosResource.GAME_OVER.getImage();
		else if (map.isWin())
			img = LogosResource.GAME_WIN.getImage();
		else
			img = LogosResource.GAME_LOGO.getImage();
		
		/* Draw appropriate image */
		g.drawImage(img, panel.getWidth() / 2 - img.getWidth(panel) / 2,
							panel.getHeight() / 2 - img.getHeight(panel) / 2, panel);
	}

	/**
	 * Paints blocks on map
	 * @param g - Canv graphics to draw on it
	 */
	private void paintMap(Graphics g) {
		for (int j = 0; j < map.getHeightBlocks(); ++j) {
			for (int i = 0; i < map.getWidthBlocks(); ++i) {
				g.drawImage(BlocksResource.getBlockImage(map.getBlock(i, j)), i * blockSize + dx, j * blockSize + dy, panel);
			}
		}
	}

	/**
	 * Paints entities on map
	 * @param g - Canv graphics to draw on it
	 */
	private void paintEntities(Graphics g) {
		Image img = null;
		
		for (SimpleEntity e : map.getEntities()) {
			img = EntitiesResource.getEntityImage(e.getEntity(), e.getAnimFrame());
			
			g.drawImage(img, ((int) e.getX()) - img.getWidth(panel) / 2 + dx,
								((int) e.getY()) - img.getHeight(panel) / 2 + dy, panel);
		}
	}

	/**
	 * Paints bonuses on map and possibly hide them
	 * under hider block
	 * @param g - Canv graphics to draw on it
	 */
	private void paintBonuses(Graphics g) {
		
		Image img = null;
		
		for (SimpleEntity b : map.getBonuses()) {
			img = EntitiesResource.getEntityImage(b.getEntity(), b.getAnimFrame());
			
			g.drawImage(img, ((int) b.getX()) - img.getWidth(panel) / 2 + dx,
								((int) b.getY()) - img.getHeight(panel) / 2 + dy, panel);
			
			
			if (!map.isEmptyBlock((int) (b.getX() / blockSize), (int) (b.getY() / blockSize))) {
				img = BlocksResource.getBlockImage(map.getHiderBlock());
			
				g.drawImage(img, (int) b.getX() - img.getWidth(panel) / 2 + dx,
									(int) b.getY() - img.getHeight(panel) / 2 + dy, panel);
			}
		}
	}
	
	public boolean isMap() {
		return this.map != null;
	}
	
	public void setMap(MapToDraw map) {
		this.map = map;
	}

}

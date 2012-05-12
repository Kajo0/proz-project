package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import pl.edu.pw.elka.mmarkiew.model.MapToDraw;
import pl.edu.pw.elka.mmarkiew.model.MapToDraw.SimpleEntity;
import pl.edu.pw.elka.mmarkiew.model.map.GameBlock;

/**
 * Class responsible for painting images on game canv
 * @author Acer
 *
 */
public class MapPainter {
	private MapToDraw map;
	private Canvas canv;
	private int blockSize;
	/**
	 * To make big map shown it is needed to
	 * move drawing images on canvas
	 * Those are horizontal & vertical moves
	 */
	private int dx;
	private int dy;
	
	/**
	 * Creates new painter
	 * @param gamePanel - Canvas to draw on
	 * @param map - Map to paint
	 */
	public MapPainter(final Canvas gamePanel, final MapToDraw map)
	{
		this.canv = gamePanel;
		this.map = map;
		this.blockSize = MapToDraw.blockSize;
		this.dx = 0;
		this.dy = 0;
	}
	
	/**
	 * Calls appropriate paint functions to show it on canv
	 */
	public void paint()
	{
		Graphics g = canv.getBufferStrategy().getDrawGraphics();
		g.clearRect(0, 0, canv.getWidth(), canv.getHeight());
		
		prepareDeltasMovingMap();
		
		/*
		 * If pauses show paused string
		 * If Game Over show sth else,
		 * If Game Win show sth else...
		 */
		if (map.isOver() || map.isWin() || !map.isStarted())
		{
			paintLogos(g);
		}
		else
		{
			paintMap(g);
			paintBonuses(g);
			paintEntities(g);
			
			if (map.isPaused())
			{
				paintLogos(g);
				
			}
		}
		
		g.dispose();
		canv.getBufferStrategy().show();
	}

	/**
	 * Calculates how much and where map should be moved to show player
	 */
	private void prepareDeltasMovingMap()
	{
		dx = 0;
		dy = 0;
		
		// If there is any map => game is playing calculate deltas -> If there is too wide map move it to see player
		if (map.isStarted())
		{
			float playerX = map.getEntities().getLast().getX();
			float playerY = map.getEntities().getLast().getY();
			
			if (map.getWidthBlocks() > canv.getWidth() / blockSize)
			{
				if (playerX > canv.getWidth() / 2)
				{
					dx = (int) (canv.getWidth() / 2 - playerX);
					
					// If player reach right edge
					if (canv.getWidth() - dx > map.getWidthBlocks() * blockSize)
					{
						dx = - (map.getWidthBlocks() * blockSize - canv.getWidth());
					}
				}
			}
			
			if (map.getHeightBlocks() > canv.getHeight() / blockSize)
			{
				if (playerY > canv.getHeight() / 2)
				{
					dy = (int) (canv.getHeight() / 2 - playerY);

					// If player reach bottom edge
					if (canv.getHeight() - dy > map.getHeightBlocks() * blockSize)
					{
						dy = - (map.getHeightBlocks() * blockSize - canv.getHeight());
					}
				}
			}
		}
	}

	/**
	 * Paints depends on game state: Pause/Win/Over/Logo - logos
	 * @param g - Canv graphics to draw on it
	 */
	private void paintLogos(final Graphics g)
	{
		Image img;
		
		// Draw background
		if (!map.isPaused())
		{
			int w = LogosResource.GAME_BACKGROUND.getImage().getWidth(null);
			int h = LogosResource.GAME_BACKGROUND.getImage().getHeight(null);
			
			for (int i = 0; i < canv.getWidth(); i += w)
			{
				for (int j = 0; j < canv.getHeight(); j += h)
				{
					g.drawImage(LogosResource.GAME_BACKGROUND.getImage(), i, j, canv);
				}
			}
		}
		
		// Select appropriate image
		if (map.isPaused() && map.isStarted() && !map.isWin() && !map.isOver())
		{
			img = LogosResource.GAME_PAUSED.getImage();
		}
		else if (map.isOver())
		{
			img = LogosResource.GAME_OVER.getImage();
		}
		else if (map.isWin())
		{
			img = LogosResource.GAME_WIN.getImage();
		}
		else
		{
			img = LogosResource.GAME_LOGO.getImage();
		}
		
		// Draw appropriate image
		g.drawImage(img, canv.getWidth() / 2 - img.getWidth(canv) / 2,
																canv.getHeight() / 2 - img.getHeight(canv) / 2, canv);
	}

	/**
	 * Paints blocks on map
	 * @param g - Canv graphics to draw on it
	 */
	private void paintMap(final Graphics g)
	{
		for (int j = 0; j < map.getHeightBlocks(); ++j)
		{
			for (int i = 0; i < map.getWidthBlocks(); ++i)
			{
				g.drawImage(BlocksResource.getBlockImage(map.getBlock(i, j)),
																		i * blockSize + dx, j * blockSize + dy, canv);
			}
		}
	}

	/**
	 * Paints entities on map
	 * @param g - Canv graphics to draw on it
	 */
	private void paintEntities(final Graphics g)
	{
		Image img = null;
		
		for (SimpleEntity e : map.getEntities())
		{
			img = EntitiesResource.getEntityImage(e.getEntity(), e.getAnimFrame());
			
			g.drawImage(img, ((int) e.getX()) - img.getWidth(canv) / 2 + dx,
																((int) e.getY()) - img.getHeight(canv) / 2 + dy, canv);
		}
	}

	/**
	 * Paints bonuses on map and possibly hide them under hider block
	 * @param g - Canv graphics to draw on it
	 */
	private void paintBonuses(final Graphics g)
	{
		Image img = null;
		
		for (SimpleEntity b : map.getBonuses())
		{
			img = EntitiesResource.getEntityImage(b.getEntity(), b.getAnimFrame());

			// If there is hidden bonus, don't draw it, just hiding it block
			if (map.getBlock((int) (b.getX() / blockSize), (int) (b.getY() / blockSize)) != GameBlock.EMPTY )
			{
				img = BlocksResource.getBlockImage(map.getHiderBlock());
			
				g.drawImage(img, (int) b.getX() - img.getWidth(canv) / 2 + dx,
																(int) b.getY() - img.getHeight(canv) / 2 + dy, canv);
			}
			else
			{
				g.drawImage(img, ((int) b.getX()) - img.getWidth(canv) / 2 + dx,
																((int) b.getY()) - img.getHeight(canv) / 2 + dy, canv);
			}
		}
	}
	
	public void setMap(final MapToDraw map)
	{
		this.map = map;
	}
}
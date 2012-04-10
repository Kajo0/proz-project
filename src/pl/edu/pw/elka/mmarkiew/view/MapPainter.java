package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import pl.edu.pw.elka.mmarkiew.controller.Controller;
import pl.edu.pw.elka.mmarkiew.model.GameMap;
import pl.edu.pw.elka.mmarkiew.model.MapToDraw;
import pl.edu.pw.elka.mmarkiew.model.MapToDraw.SimpleEntity;

public class MapPainter implements Runnable {
	private MapToDraw map;
	private Canvas panel;
	private int blockSize;
	
	public MapPainter(Canvas gamePanel, MapToDraw map) {
		this.panel = gamePanel;
		this.map = map;
		this.blockSize = GameMap.BLOCK_SIZE;
	}
	
	@Override
	public void run() {
		Graphics g = panel.getBufferStrategy().getDrawGraphics();
		
		g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
		
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

	private void paintPauseMap(Graphics g) {
		BufferedImage img = new BufferedImage(Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE,
																			BufferedImage.TYPE_INT_RGB);
		img.getGraphics().clearRect(0, 0, Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE / 2);
		img.getGraphics().drawString("PAUSE", Controller.GAME_X_SIZE / 2, Controller.GAME_Y_SIZE / 2);
		g.drawImage(img, 0, 0, panel);
		g.dispose();
	}
	
	private void paintLogo(Graphics g) {
		BufferedImage img = new BufferedImage(Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE,
																			BufferedImage.TYPE_INT_RGB);
		img.getGraphics().clearRect(0, 0, Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE / 2);
		img.getGraphics().drawString("BOMBERMAN LOGO", Controller.GAME_X_SIZE / 2, Controller.GAME_Y_SIZE / 2);
		g.drawImage(img, 0, 0, panel);
		g.dispose();
	}
	

	private void paintGameOver(Graphics g) {
		BufferedImage img = new BufferedImage(Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE,
																			BufferedImage.TYPE_INT_RGB);
		img.getGraphics().clearRect(0, 0, Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE / 2);
		img.getGraphics().drawString("GAME OVER !", Controller.GAME_X_SIZE / 2, Controller.GAME_Y_SIZE / 2);
		g.drawImage(img, 0, 0, panel);
		g.dispose();
	}

	private void paintWinLogo(Graphics g) {
		BufferedImage img = new BufferedImage(Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE,
																			BufferedImage.TYPE_INT_RGB);
		img.getGraphics().clearRect(0, 0, Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE / 2);
		img.getGraphics().drawString("WIN !!!!!!", Controller.GAME_X_SIZE / 2, Controller.GAME_Y_SIZE / 2);
		g.drawImage(img, 0, 0, panel);
		g.dispose();
	}

	private void paintMap(Graphics g) {
		for (int j = 0; j < map.getHeightBlocks(); j++) {
			for (int i = 0; i < map.getWidthBlocks(); i++) {
				g.drawImage(map.getBlockImage(i, j), i * blockSize, j * blockSize, panel);
			}
		}
	}

	private void paintEntities(Graphics g) {
		for (SimpleEntity e : map.getEntities())
				g.drawImage(e.getImage(), ((int) e.getX()) - e.getImage().getWidth(panel) / 2,
									((int) e.getY()) - e.getImage().getHeight(panel) / 2, panel);
	}
	
	private void paintBonuses(Graphics g) {
		for (SimpleEntity b : map.getBonuses()) {
			g.drawImage(b.getImage(), ((int) b.getX()) - b.getImage().getWidth(null) / 2,
								((int) b.getY()) - b.getImage().getHeight(null) / 2, panel);
			
			if (!map.isEmptyBlock((int) (b.getX() / blockSize), (int) (b.getY() / blockSize)))
				g.drawImage(map.getHiderBlock(),
								(int) b.getX() - map.getHiderBlock().getWidth(panel) / 2,
								(int) b.getY() - map.getHiderBlock().getHeight(panel) / 2,
								panel);
		}
	}

}

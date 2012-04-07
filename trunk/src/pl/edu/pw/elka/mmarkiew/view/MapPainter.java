package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import pl.edu.pw.elka.mmarkiew.controller.Controller;
import pl.edu.pw.elka.mmarkiew.model.MapToDraw;
import pl.edu.pw.elka.mmarkiew.model.entities.Entity;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.map.BlockElement;
import pl.edu.pw.elka.mmarkiew.model.map.EmptyBlock;

public class MapPainter implements Runnable {
	private MapToDraw map;
	private Canvas panel;
	private int blockSize;
	
	public MapPainter(Canvas gamePanel, MapToDraw map) {
		this.panel = gamePanel;
		this.map = map;
		this.blockSize = 40;//TODO dac ze jak wiecej blokow to sklaujemy itp..
	}
	
	@Override
	public void run() {
		Graphics g = panel.getBufferStrategy().getDrawGraphics();
		
		g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
		
		if (map.isPaused())
			paintPauseMap(g);
		else if (!map.isStarted())
			paintPlainMap(g);
		else {
			paintMap(g);
			paintBonuses(g);
			paintPalyer(g);
			paintEnemies(g);
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
	
	private void paintPlainMap(Graphics g) {
		BufferedImage img = new BufferedImage(Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE,
				BufferedImage.TYPE_INT_RGB);
		img.getGraphics().clearRect(0, 0, Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE / 2);
		img.getGraphics().drawString("BOMBERMAN LOGO", Controller.GAME_X_SIZE / 2, Controller.GAME_Y_SIZE / 2);
		g.drawImage(img, 0, 0, panel);
		g.dispose();
	}

	private void paintMap(Graphics g) {
		for (int j = 0; j < map.getHeightBlocks(); j++) {
			for (int i = 0; i < map.getWidthBlocks(); i++) {
				g.drawImage(map.getBlock(i, j).getImage(), i*40, j*40, null);
			}
		}
	}

	private void paintPalyer(Graphics g) {
		Player player = (Player) map.getEntities().get(0);
		Image image = player.getAnim();
		
		g.drawImage(image, ((int) player.getX()) - image.getWidth(null) / 2,
							((int) player.getY()) - image.getHeight(null) / 2, panel);
	}
	
	private void paintEnemies(Graphics g) {
		Image image = null;
		for (Entity e : map.getEntities())
			if ( !(e instanceof Player) ) {
				image = e.getAnim();
				g.drawImage(image, ((int) e.getX()) - image.getWidth(null) / 2,
									((int) e.getY()) - image.getHeight(null) / 2, panel);
			}
	}
	
	private void paintBonuses(Graphics g) {
		Image image = null;
		for (Entity b : map.getBonuses()) {
			image = b.getAnim();
			g.drawImage(image, ((int) b.getX()) - image.getWidth(null) / 2,
								((int) b.getY()) - image.getHeight(null) / 2, panel);
			
			BlockElement block = map.getBlock((int) (b.getX() / blockSize), (int) (b.getY() / blockSize));
			image = block.getImage();
			if (! (block instanceof EmptyBlock) ) {
				g.drawImage(block.getImage(),
							(int) b.getX() - image.getWidth(null) / 2,
							(int) b.getY() - image.getWidth(null) / 2,
							panel);
			}
		}
	}

}

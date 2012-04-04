package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import pl.edu.pw.elka.mmarkiew.controller.Controller;
import pl.edu.pw.elka.mmarkiew.model.MapToDraw;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

public class MapPainter implements Runnable {
	private MapToDraw map;
	private Canvas panel;
	
	public MapPainter(Canvas gamePanel, MapToDraw map) {
		this.panel = gamePanel;
		this.map = map;
	}
	
	@Override
	public void run() {
		Graphics g = panel.getBufferStrategy().getDrawGraphics();
		
		if (map.isPaused())
			paintPauseMap(g);
		else if (!map.isStarted())
			paintPlainMap(g);
		else {
			paintMap(g);
			paintPalyer(g);
			paintEnemies(g);
			paintBonuses(g);
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
		//TODO rysowanie wrogow
	}
	
	private void paintBonuses(Graphics g) {
		//TODO rysowanie bonusow
	}

}

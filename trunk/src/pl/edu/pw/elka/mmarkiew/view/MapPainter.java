package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import pl.edu.pw.elka.mmarkiew.model.entities.GameMap;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

public class MapPainter implements Runnable {
	private GameMap map;
	private Canvas panel;
	
	public MapPainter(Canvas gamePanel, GameMap map) {
		this.panel = gamePanel;
		this.map = map;
	}
	
	@Override
	public void run() {
		Graphics g = panel.getBufferStrategy().getDrawGraphics();
		
		paintMap(g);
		paintPalyer(g);
		paintEnemies(g);
		paintBonuses(g);
		
		g.dispose();
		panel.getBufferStrategy().show();
		
	}

	private void paintMap(Graphics g) {
		for (int j = 0; j < map.getHeight(); j++) {
			for (int i = 0; i < map.getWidth(); i++) {
				g.drawImage(map.getBlock(i, j).getImage(), i*40, j*40, null);
			}
		}
	}

	private void paintPalyer(Graphics g) {
		Image image = map.getPlayer().getAnim();
		Player player = (Player) map.getPlayer();
		
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

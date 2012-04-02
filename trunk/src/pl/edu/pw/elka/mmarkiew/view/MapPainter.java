package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import pl.edu.pw.elka.mmarkiew.model.entities.GameMap;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;

public class MapPainter implements Runnable {
	private GameMap map;
	private JPanel panel;
	
	public MapPainter(JPanel gamePanel, GameMap map) {
		this.panel = gamePanel;
		this.map = map;
	}
	
	@Override
	public void run() {
//		Graphics g = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB).getGraphics();
//		g.clearRect(0, 0, panel.getWidth(), panel.getHeight());
		
		Graphics g = panel.getGraphics();
		
		paintMap(g);
		paintPalyer(g);
		paintEnemies(g);
		paintBonuses(g);
		
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

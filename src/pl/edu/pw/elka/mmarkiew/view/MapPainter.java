package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import pl.edu.pw.elka.mmarkiew.model.entities.GameMap;
import pl.edu.pw.elka.mmarkiew.model.map.BlockElement;
import pl.edu.pw.elka.mmarkiew.model.map.GameBlock;

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
		
	}

	private void paintMap(Graphics g) {
		for (int j = 0; j < map.getHeight(); j++) {
			for (int i = 0; i < map.getWidth(); i++) {
				g.drawImage(map.getBlock(i, j).getImage(), i*40, j*40, null);
			}
		}
	}

	private void paintPalyer(Graphics g) {
		g.drawImage(map.getPlayer().getAnim(), (int) map.getPlayer().getX(), (int) map.getPlayer().getY(), panel);
	}

}

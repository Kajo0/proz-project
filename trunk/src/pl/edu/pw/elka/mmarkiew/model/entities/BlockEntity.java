package pl.edu.pw.elka.mmarkiew.model.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import pl.edu.pw.elka.mmarkiew.model.GameMap;

public class BlockEntity extends Entity {
	public BlockEntity(int x, int y) {
		super();
		this.setX(x * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2);
		this.setY(y * GameMap.BLOCK_SIZE + GameMap.BLOCK_SIZE / 2);
		this.setWidth(GameMap.BLOCK_SIZE);
		this.setHeight(GameMap.BLOCK_SIZE);
		
		BufferedImage img = new BufferedImage((int) this.getWidth(), (int) this.getHeight(),
																	BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(Color.YELLOW);
		g.fillRect(15, 15, 10, 10);
		
		this.setAnim(img);
	}
}

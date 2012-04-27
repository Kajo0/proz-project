package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import pl.edu.pw.elka.mmarkiew.model.MapToDraw;
import pl.edu.pw.elka.mmarkiew.model.map.GameBlock;

/**
 * Enumeration containing images of game blocks to draw on view panel
 * @author Acer
 *
 */
public enum BlocksResource {
	STONE(GameBlock.STONE, "stoneBlock"),
	BRICK(GameBlock.BRICK, "brickBlock"),
	EMPTY(GameBlock.EMPTY, "emptyBlock");
	
	/** Block representing block */
	private final GameBlock block;
	/** Block image */
	private final Image image;
	
	/**
	 * Creates and load image of block
	 * @param block - Enum representing block
	 * @param image - Part of path to block image
	 */
	private BlocksResource(GameBlock block, String image) {
		this.block = block;
		
		/*
		 * If there is no image, create blank image
		 */
		Image img = null;
		image = "/" + image;
		if (getClass().getResource(image + ".png") != null)
			img = new ImageIcon(getClass().getResource(image + ".png")).getImage();
		
		else {
			BufferedImage bufImg = new BufferedImage(MapToDraw.blockSize, MapToDraw.blockSize,
																				BufferedImage.TYPE_INT_ARGB);
			Graphics g = bufImg.getGraphics();
			g.fillRect(0, 0, MapToDraw.blockSize, MapToDraw.blockSize);
			g.dispose();
			
			img = bufImg;
		}
		
		this.image = img;
	}
	
	/**
	 * Gets image of given block
	 * @param block - Block to get image of
	 * @return Image representing given block or<br>
	 * 			empty block if undefined
	 */
	public static Image getBlockImage(GameBlock block) {
		for (BlocksResource g : values()) {
			if (g.block.equals(block))
				return g.image;
		}
		return EMPTY.image;
	}

}

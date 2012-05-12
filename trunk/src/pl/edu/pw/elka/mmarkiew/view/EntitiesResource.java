package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import pl.edu.pw.elka.mmarkiew.model.MapToDraw;
import pl.edu.pw.elka.mmarkiew.model.entities.GameEntities;

/**
 * Enumeration containing images of game entities to draw on view panel
 * @author Acer
 *
 */
public enum EntitiesResource {
	PLAYER				(GameEntities.PLAYER,			"player",					"playerDying"),
	BOMB				(GameEntities.BOMB,				"bomb",						""),
	EXPLOSION			(GameEntities.EXPLOSION,		"explosion",				""),
	DESTROYING_BRICK	(GameEntities.DESTROYING_BRICK,	"destroyingBrick",			""),
	UNDEFINED			(GameEntities.UNDEFINED,		"undefined",				""),
	BALOON				(GameEntities.BALOON,			"ballonEnemy",				"ballonEnemyDying"),
	HELIUM				(GameEntities.HELIUM,			"heliumEnemy",				"ballonEnemyDying"),
	EXIT				(GameEntities.EXIT,				"exitOpen",					"exitClose"),
	SPEED				(GameEntities.SPEED,			"speedBonus",				""),
	AREA_INC			(GameEntities.AREA_INC,			"increaseBombAreaBonus",	""),
	BOMB_INC			(GameEntities.BOMB_INC,			"increaseBombAmountBonus",	""),
	LIFE_INC			(GameEntities.LIFE_INC,			"increaseLifeNumberBonus",	""),
	BOUNCING_BOMB		(GameEntities.BOUNCING_BOMB,	"bouncingBombBonus",		"");

	
	final GameEntities entity;
	final LinkedList<Image> images;
	
	/**
	 * Creates and load images of entity
	 * @param entity - Enum representing entity
	 * @param anim - Significant part of path to alive entity image
	 * @param dyingAnim - Part of path to dead entity image
	 */
	private EntitiesResource(GameEntities entity, String anim, String dyingAnim)
	{
		this.entity = entity;
		this.images = new LinkedList<Image>();
		
		fillImages(anim);
		
		// If there is no dead images don't add them
		if (!dyingAnim.equals(""))
		{
			fillImages(dyingAnim);
		}
	}
	
	/**
	 * Fills list by images of entity
	 * @param anim - Part of path to entity image
	 */
	private void fillImages(String anim)
	{
		anim = "/" + anim;
		if (getClass().getResource(anim + "0.png") != null)
		{
			this.images.add(new ImageIcon(getClass().getResource(anim + "0.png")).getImage());

			int i = 1;
			while (getClass().getResource(anim + i + ".png") != null)
			{
				this.images.add(new ImageIcon(getClass().getResource(anim + i + ".png")).getImage());
				++i;
			}
		}
		else
		{
			// Create blank square image
			BufferedImage bufImg = new BufferedImage(MapToDraw.blockSize, MapToDraw.blockSize,
																						BufferedImage.TYPE_INT_ARGB);
			Graphics g = bufImg.getGraphics();
			g.fillRect(10, 10, 20, 20);
			g.dispose();
			
			this.images.add(bufImg);
		}
	}

	/**
	 * Gets image of given entity frame
	 * @param entity - Entity to get image of
	 * @param frame - Actual animation frame<br>
	 * 				if < 0 -> get from end -> dying
	 * @return Image representing given entity or<br>
	 * 			undefined 1x1px image instead
	 */
	public static Image getEntityImage(GameEntities entity, int frame)
	{
		for (EntitiesResource g : values())
		{
			if (g.entity.equals(entity))
			{
				try
				{
					if (frame < 0)
					{
						frame = g.images.size() + frame;
					}
					
					return g.images.get(frame);
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					// There is always one element
					return g.images.get(0);
				}
			}
		}
		
		return UNDEFINED.images.get(0);
	}
}
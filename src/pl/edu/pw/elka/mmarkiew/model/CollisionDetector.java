package pl.edu.pw.elka.mmarkiew.model;

import pl.edu.pw.elka.mmarkiew.model.entities.GameMap;
import pl.edu.pw.elka.mmarkiew.model.entities.Player;
import pl.edu.pw.elka.mmarkiew.model.map.EmptyBlock;

public class CollisionDetector {
	private GameMap map;
	private Player player;
	
	public CollisionDetector(GameMap map) {
		this.map = map;
		this.player = (Player) map.getPlayer();
	}
	
	public void checkCollision() {
		checkPlayerCollisionWithBlocks();
	}

	private void checkPlayerCollisionWithBlocks() {
		int xTilePlayerPosition = map.getTilePosition(player.getX());
		int yTilePlayerPosition = map.getTilePosition(player.getY());
		float dividedAnimWidth = player.getAnim().getHeight(null) / 2;
		float dividedAnimHeight = player.getAnim().getHeight(null) / 2;
		float xPlayerPosition = player.getX();
		float yPlayerPosition = player.getY();
		
		// gora dol prawo lewo
		if (! (map.getBlock(xTilePlayerPosition, yTilePlayerPosition-1) instanceof EmptyBlock) ) {
			if (yPlayerPosition - dividedAnimHeight < (yTilePlayerPosition) * GameMap.BLLOCK_SIZE) {
				player.collisionY();
				player.setY((yTilePlayerPosition) * GameMap.BLLOCK_SIZE + dividedAnimHeight);
			}
		}
		if (! (map.getBlock(xTilePlayerPosition, yTilePlayerPosition+1) instanceof EmptyBlock) ) {
			if (yPlayerPosition + dividedAnimHeight > (yTilePlayerPosition+1) * GameMap.BLLOCK_SIZE) {
				player.collisionY();
				player.setY((yTilePlayerPosition+1) * GameMap.BLLOCK_SIZE - dividedAnimHeight);
			}
		}
		if (! (map.getBlock(xTilePlayerPosition-1, yTilePlayerPosition) instanceof EmptyBlock) ) {
			if (xPlayerPosition - dividedAnimWidth < (xTilePlayerPosition) * GameMap.BLLOCK_SIZE) {
				player.collisionY();
				player.setX((xTilePlayerPosition) * GameMap.BLLOCK_SIZE + dividedAnimWidth);
			}
		}
		if (! (map.getBlock(xTilePlayerPosition+1, yTilePlayerPosition) instanceof EmptyBlock) ) {
			if (xPlayerPosition + dividedAnimWidth > (xTilePlayerPosition+1) * GameMap.BLLOCK_SIZE) {
				player.collisionX();
				player.setX((xTilePlayerPosition+1) * GameMap.BLLOCK_SIZE - dividedAnimWidth);
			}
		}
		
	}
}

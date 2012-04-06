package pl.edu.pw.elka.mmarkiew.model.map;

public class BlockFactory {
	
	public static BlockElement createElement(final GameBlock block) {
		switch (block) {
			case STONE: return new StoneBlock(block.getImage());
			case BRICK: return new BrickBlock(block.getImage());
			case EMPTY: return new EmptyBlock(block.getImage());
			default:	return new EmptyBlock(block.getImage());
		}
	}
}

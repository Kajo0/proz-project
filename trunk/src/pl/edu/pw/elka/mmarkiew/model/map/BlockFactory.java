package pl.edu.pw.elka.mmarkiew.model.map;

public class BlockFactory {
	
//	public static BlockElement createElement(final GameBlock block) {
//		return createElement(block, 0, 0);
//	}
	
	public static BlockElement createElement(final GameBlock block) {
		switch (block) {
			case STONE: return new StoneBlock(block.getImage());
			case BRICK: return new BrickBlock(block.getImage());
			case SPACE: return new EmptyBlock(block.getImage());
			default:	return new EmptyBlock(block.getImage());
		}
	}
}

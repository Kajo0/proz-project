package pl.edu.pw.elka.mmarkiew.model.map;

public class BlockHolder {
	private int width;
	private int height;
	private BlockElement[][] blocks;
	
	public BlockHolder(int widthBlocks, int heightBlocks) {
		this.width = widthBlocks;
		this.height = heightBlocks;
		this.blocks = new BlockElement[widthBlocks][heightBlocks];
	}
	
	public void setBlock(BlockElement block, int x, int y) {
		if (x < 0 || x > width || y < 0 || y > height)
			return;
		else blocks[x][y] = block;
	}
	
	public BlockElement getBlock(int x, int y) {
		if (x < 0 || x > width || y < 0 || y > height)
			return null;
		else return blocks[x][y];
	}

}

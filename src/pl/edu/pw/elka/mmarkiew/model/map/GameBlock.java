package pl.edu.pw.elka.mmarkiew.model.map;

/**
 * Enumeration representing available game blocks 
 * @author Acer
 *
 */
public enum GameBlock {
	STONE("#"),
	BRICK("@"),
	EMPTY(" ");
	
	/** String representing block */
	private final String character;
	
	/**
	 * Creates and load image of block
	 * @param character - String representing block
	 * @param image - Path to block image
	 */
	private GameBlock(final String character)
	{
		this.character = character;
	}
	
	public String getCharacter()
	{
		return character;
	}
	
	public String toString()
	{
		return getCharacter();
	}
	
	/**
	 * Return GameBlock if such exists
	 * @param character - Entity representation string
	 * @return Appropriate GameBlock, EMPTY if didn't find
	 */
	public static GameBlock getEnumBlock(final String character)
	{
		for (GameBlock g : values())
		{
			if (g.getCharacter().equals(character))
			{
				return g;
			}
		}
		return EMPTY;
	}
}
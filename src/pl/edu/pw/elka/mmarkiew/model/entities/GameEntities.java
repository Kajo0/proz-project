package pl.edu.pw.elka.mmarkiew.model.entities;

public enum GameEntities {
	BALOON("A"),
	UNDEFINED("");
	
	private final String character;
	
	private GameEntities(final String character) {
		this.character = character;
	}
	
	public String getCharacter() {
		return this.character;
	}

	public String toString() {
		return getCharacter();
	}
	
	public static GameEntities getEnumEntity(final String character) {
		for (GameEntities g : values()) {
			if (g.getCharacter().equals(character))
				return g;
		}
		return UNDEFINED;
	}
	
}
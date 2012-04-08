package pl.edu.pw.elka.mmarkiew.model;

import pl.edu.pw.elka.mmarkiew.model.entities.Player;

public class ModelStatistics {
	
	public ModelStatistics() {
		
	}

	public ModelStatistics(Player player) {
	}

	public long getTimer() {
		return System.currentTimeMillis();
	}

}

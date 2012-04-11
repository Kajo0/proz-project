package pl.edu.pw.elka.mmarkiew.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import pl.edu.pw.elka.mmarkiew.controller.Controller;

/**
 * Right panel of game view frame
 * @author Acer
 *
 */
@SuppressWarnings("serial")
public class RightPanel extends JPanel {
	private JTabbedPane tabs;
	/** Game infromation panel - statistics */
	private GameInfoPanel gameInfo;
	/** Game option panel - options */
	private GameOptionsPanel gameOptions;
	
	/**
	 * Creates right panel
	 */
	public RightPanel() {
		super();
		
		setBounds(Controller.GAME_X_SIZE, 0, Controller.VIEW_WIDTH - Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE);
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout());
		
		tabs = new JTabbedPane();
		gameInfo = new GameInfoPanel();
		gameOptions = new GameOptionsPanel();

		init();
	}
	
	/**
	 * Initialization of right panel
	 */
	private void init() {
		tabs.setFocusable(false);
		tabs.addTab("Game", gameInfo);
		tabs.addTab("Options", gameOptions);

		add(tabs, BorderLayout.CENTER);
	}
	
	public GameInfoPanel getGameInfo() {
		return gameInfo;
	}
	
	public GameOptionsPanel getGameOptions() {
		return gameOptions;
	}
}

package pl.edu.pw.elka.mmarkiew.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Right panel of game view frame
 * @author Acer
 *
 */
@SuppressWarnings("serial")
public class RightPanel extends JPanel {
	/** Panel Tabs */
	private final JTabbedPane tabs;
	/** Game information panel - statistics */
	private final GameInfoPanel gameInfo;
	/** Game option panel - options */
	private final GameOptionsPanel gameOptions;
	
	/**
	 * Creates right panel
	 */
	public RightPanel()
	{
		super();
		
		setBounds(View.GAME_X_SIZE, 0, View.VIEW_WIDTH - View.GAME_X_SIZE, View.GAME_Y_SIZE);
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
	private void init()
	{
		tabs.setFocusable(false);
		tabs.addTab("Game", gameInfo);
		tabs.addTab("Keys", gameOptions);

		add(tabs, BorderLayout.CENTER);
	}
	
	public GameInfoPanel getGameInfo()
	{
		return gameInfo;
	}
	
	public GameOptionsPanel getGameOptions()
	{
		return gameOptions;
	}
}
package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import pl.edu.pw.elka.mmarkiew.controller.Controller;
import pl.edu.pw.elka.mmarkiew.model.MapToDraw;
import pl.edu.pw.elka.mmarkiew.model.ModelStatistics;
import pl.edu.pw.elka.mmarkiew.model.SoundManager;

/**
 * Game view
 * @author Acer
 *
 */
@SuppressWarnings("serial")
public class View extends JFrame implements Runnable {
	private int width;
	private int height;
	private Canvas gamePanel;
	private RightPanel rightPanel;
	private MapPainter mapPainter;

	/**
	 * Creates new View
	 * @param width - Frame width
	 * @param height - Frame height
	 */
	public View(int width, int height) {
		super("Bomberman version 0.1");

		this.width = width;
		this.height = height;
		
		this.gamePanel = new Canvas();
		this.rightPanel = new RightPanel();
		
		this.mapPainter = new MapPainter(gamePanel, null);
		
		init();
	}
	
	/**
	 * Initialize frame
	 */
	private void init() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		} catch (Exception e) {}
		
		setBounds( (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2,
					(Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2,
					width, height);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setAlwaysOnTop(true);
		setLayout(null);
		setVisible(true);
		
		gamePanel.setBounds(0, 0, Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE);
		gamePanel.setBackground(Color.LIGHT_GRAY);

		add(gamePanel);
		/*
		 * Set it double buffered by buffer strategy
		 */
		gamePanel.createBufferStrategy(2);
		
		add(rightPanel);
		/*
		 * Add focus into game
		 */
		gamePanel.requestFocus();
	}

	/**
	 * Resize window to show whole panes by adding insets<br>
	 * Add request listener
	 */
	@Override
	public void run() {
		Insets insets = Window.getWindows()[0].getInsets();
		this.setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
		
		gamePanel.addKeyListener(new MovementListener());
	}

	/**
	 * Invoke swing painter method to paint map
	 * @param map - Informations necessary to paint map
	 */
	public void sendMapModel(final MapToDraw map) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				mapPainter.setMap(map);
				mapPainter.paint();
				
				/*
				 * Enabling new game button after end game
				 */
				if (map.isOver() || !map.isStarted())
					rightPanel.getGameInfo().setNewGameButtonEnable(true);
				else
					rightPanel.getGameInfo().setNewGameButtonEnable(false);
			}
		});
	}

	/**
	 * Invoke update of player statistics
	 * @param statistics - Informations about player
	 */
	public void sendStatistics(final ModelStatistics statistics) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				updateStatistics(statistics);
			}
		});
	}

	/**
	 * Sets new text into labels
	 * @param statistics - Player statistics
	 */
	private void updateStatistics(ModelStatistics statistics) {
		rightPanel.getGameInfo().setTimerLabel(statistics.getTimer() / 1000);
		rightPanel.getGameInfo().setLevelLabel(statistics.getLevel());
		rightPanel.getGameInfo().setLifeLabel(statistics.getLifes());
		rightPanel.getGameInfo().setBombLabel(statistics.getBombs());
		rightPanel.getGameInfo().setBombAreaLabel(statistics.getBombArea());
		rightPanel.getGameInfo().setBombTimerLabel(statistics.getBombTimer());
		rightPanel.getGameInfo().setBouncingBombLabel(statistics.isBouncingBomb());

		rightPanel.getGameInfo().setBackgroundMusicButtonIcon(SoundManager.isBackgroundOn());
		rightPanel.getGameInfo().setSoundEffectsButtonIcon(SoundManager.isSoundEffectOn());
	}

}

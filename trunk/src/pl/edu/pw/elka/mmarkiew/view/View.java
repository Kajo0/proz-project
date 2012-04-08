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

@SuppressWarnings("serial")
public class View extends JFrame implements Runnable {
	private int width;
	private int height;
	private Canvas gamePanel;
	private RightPanel rightPanel;

	public View(int width, int height) {
		super("Bomberman version 0.0");

		this.width = width;
		this.height = height;
		
		this.gamePanel = new Canvas();
		this.rightPanel = new RightPanel();
		
		init();
	}
	
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
		gamePanel.createBufferStrategy(2);
		
		add(rightPanel);
		gamePanel.requestFocus();
		
		
		revalidate();
	}
	
	@Override
	public void run() {
		// TODO View run = init graph
		Insets insets = Window.getWindows()[0].getInsets();
		this.setSize(width + insets.left + insets.right, height + insets.top + insets.bottom);
		
		gamePanel.addKeyListener(new MovementListener());
	}

	public void sendMapModel(MapToDraw map) {
		SwingUtilities.invokeLater(new MapPainter(gamePanel, map));
	}

	public void sendStatistics(final ModelStatistics statistics) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				updateStatistics(statistics);
			}
		});
	}

	protected void updateStatistics(ModelStatistics statistics) {
		rightPanel.getGameInfo().setTimerLabel(statistics.getTimer() / 1000);
		rightPanel.getGameInfo().setLevelLabel(statistics.getLevel());
		rightPanel.getGameInfo().setLifeLabel(statistics.getLifes());
		rightPanel.getGameInfo().setBombLabel(statistics.getBombs());
		rightPanel.getGameInfo().setBombAreaLabel(statistics.getBombArea());
		rightPanel.getGameInfo().setBombTimer(statistics.getBombTimer());
	}

}

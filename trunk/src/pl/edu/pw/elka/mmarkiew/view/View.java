package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import pl.edu.pw.elka.mmarkiew.controller.Controller;
import pl.edu.pw.elka.mmarkiew.model.entities.GameMap;

@SuppressWarnings("serial")
public class View extends JFrame implements Runnable {
	private int width;
	private int height;
	private Canvas gamePanel;

	public View(int width, int height) {
		super("Bomberman version 0.0");

		this.width = width;
		this.height = height;
		
		this.gamePanel = new Canvas();
		
		init();
	}
	
	private void init() {
		setBounds( (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2,
					(Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2,
					width, height + 28);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setAlwaysOnTop(true);
		setLayout(null);
		setVisible(true);
		
		gamePanel.setBounds(0, 0, Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE);
		gamePanel.setBackground(Color.LIGHT_GRAY);
		
		add(gamePanel);
		
		gamePanel.createBufferStrategy(2);
	}
	
	@Override
	public void run() {
		// TODO View run = init graph
		
		this.addKeyListener(new MovementListener());
	}

	public void sendModel(GameMap map) {
		SwingUtilities.invokeLater(new MapPainter(gamePanel, map));
	}

}

package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

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

	public void sendOverlay(final String txt) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				BufferedImage img = new BufferedImage(Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE,
																					BufferedImage.TYPE_INT_RGB);
				img.getGraphics().clearRect(0, 0, Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE / 2);
				img.getGraphics().drawString(txt, Controller.GAME_X_SIZE / 2, Controller.GAME_Y_SIZE / 2);
				
				
				Graphics g = gamePanel.getBufferStrategy().getDrawGraphics();
				
				g.drawImage(img, 0, 0, gamePanel);
				
				g.dispose();
				gamePanel.getBufferStrategy().show();
			}
		});
	}

}

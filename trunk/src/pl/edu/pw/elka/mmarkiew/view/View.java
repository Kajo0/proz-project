package pl.edu.pw.elka.mmarkiew.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import pl.edu.pw.elka.mmarkiew.controller.crossobjects.EntityToDraw;
import pl.edu.pw.elka.mmarkiew.controller.crossobjects.MapToDraw;
import pl.edu.pw.elka.mmarkiew.exceptions.NoViewException;
import pl.edu.pw.elka.mmarkiew.model.map.BlockElement;
import pl.edu.pw.elka.mmarkiew.model.map.EmptyBlock;

/**
 * @author Acer
 *
 */
public class View extends JFrame implements Runnable {
	public static final int WIDTH_DEFAULT;
	public static final int HEIGHT_DEFAULT;
	public static final Point GAME_CORNER;
	public static final Point OPTION_CORNER;
	private GamePane gamePanel;
	private OptionPane optionPanel;
	
	static {
		WIDTH_DEFAULT = 800;
		HEIGHT_DEFAULT = 600;
		GAME_CORNER = new Point(0, 0);
		OPTION_CORNER = new Point(HEIGHT_DEFAULT + 5, 0);
	}
	
	public View() {
		super("Bomberman version 0.0");
		
		init();
	}
	
	/**
	 * Initialize view frame and components
	 */
	private void init() {
		
			// Main frame initialization
		setBounds( (Toolkit.getDefaultToolkit().getScreenSize().width - WIDTH_DEFAULT) / 2,
					(Toolkit.getDefaultToolkit().getScreenSize().height - HEIGHT_DEFAULT) / 2,
					WIDTH_DEFAULT, HEIGHT_DEFAULT + 28);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setAlwaysOnTop(true);
		setLayout(null);
		setVisible(true);
		
			// Both panels adding
		gamePanel = new GamePane(GAME_CORNER, HEIGHT_DEFAULT, HEIGHT_DEFAULT);
		optionPanel = new OptionPane(OPTION_CORNER, WIDTH_DEFAULT - HEIGHT_DEFAULT - 5, HEIGHT_DEFAULT);
		
		add(gamePanel);
		add(optionPanel);

	}

	@Override
	public void run() {
	}
	
	
	public void drawGame(final MapToDraw map, final EntityToDraw player) {
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				gamePanel.drawGame(map, player);
//			}
//		});
	}

}

package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import pl.edu.pw.elka.mmarkiew.controller.queueevents.ViewKeyPress.Keys;

/**
 * Game panel with informations about game
 * @author Acer
 *
 */
@SuppressWarnings("serial")
public class GameInfoPanel extends JPanel {
	private final JLabel timerLabel;
	private final JLabel levelLabel;
	private final JLabel lifeLabel;
	private final JLabel bombLabel;
	private final JLabel bombAreaLabel;
	private final JLabel bombTimerLabel;
	private final JLabel bouncingBombLabel;
	private final JButton newGameButton;
	private final JButton backgroundMusicToogleButton;
	private final JButton soundEffectsToogleButton;
	
	/**
	 * Creates game info panel and components on it
	 */
	public GameInfoPanel()
	{
		super();
		setLayout(null);
		
		timerLabel = new JLabel();
		levelLabel = new JLabel();
		lifeLabel = new JLabel();
		bombLabel = new JLabel();
		bombAreaLabel = new JLabel();
		bombTimerLabel = new JLabel();
		bouncingBombLabel = new JLabel();
		
		newGameButton = new JButton();
		
		backgroundMusicToogleButton = new JButton();
		soundEffectsToogleButton = new JButton();
		
		init();
	}

	/**
	 * Initialize game info panel components
	 */
	private void init()
	{
		// Game things
		final ArrayList<JLabel> labels = new ArrayList<JLabel>(Arrays.asList(timerLabel, levelLabel, lifeLabel,
														bombLabel, bombAreaLabel, bombTimerLabel, bouncingBombLabel));
		int i = 0;
		for (JLabel l : labels)
		{
			l.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
			l.setHorizontalAlignment(JLabel.LEFT);
			l.setBounds(80, 10 + i * 50, 120, 50);
			++i;
		}
		
		timerLabel.setBounds(0, 10, 200, 50);
		timerLabel.setHorizontalAlignment(JLabel.CENTER);
		
		// New game button
		newGameButton.setBounds(25, 500, 150, 50);
		newGameButton.setText("New Game");
		newGameButton.setFocusable(false);
		newGameButton.setActionCommand(Keys.NEW_GAME.toString());
		
		final ClickingListener actionListener = new ClickingListener();
		newGameButton.addActionListener(actionListener);
		
		// Sound buttons
		backgroundMusicToogleButton.setBounds(50, 440, 40, 40);
		backgroundMusicToogleButton.setFocusable(false);
		backgroundMusicToogleButton.setActionCommand(Keys.BACKGROUND_MUSIC.toString());
		
		soundEffectsToogleButton.setBounds(110, 440, 40, 40);
		soundEffectsToogleButton.setFocusable(false);
		soundEffectsToogleButton.setActionCommand(Keys.SOUND_EFFECTS.toString());

		backgroundMusicToogleButton.addActionListener(actionListener);
		soundEffectsToogleButton.addActionListener(actionListener);
		
		// Adding
		add(timerLabel);
		add(levelLabel);
		add(lifeLabel);
		add(bombLabel);
		add(bombAreaLabel);
		add(bombTimerLabel);
		add(bouncingBombLabel);
		
		add(newGameButton);
		
		add(backgroundMusicToogleButton);
		add(soundEffectsToogleButton);
	}
	
	/**
	 * Paints components of game info panel 
	 */
	public void paint(final Graphics g)
	{
		super.paint(g);
		
		g.drawImage(LogosResource.INFO_BACKGROUND.getImage(), 0, 0, null);
		
		final ArrayList<LogosResource> logos = new ArrayList<LogosResource>(Arrays.asList(LogosResource.LEVEL,
														LogosResource.LIFES, LogosResource.BOMBS, LogosResource.STEPS,
														LogosResource.TIMER, LogosResource.BOUNCE));
		int i = 1;
		for (LogosResource l : logos)
		{
			g.drawImage(l.getImage(), 40 - l.getImage().getWidth(this) / 2,
																35 + i * 50 - l.getImage().getHeight(this) / 2, this);
			++i;
		}
		
		g.dispose();
	}
	
	public void setTimerLabel(final long timerLabel)
	{
		this.timerLabel.setText("Timer: " + timerLabel + " s");
	}
	
	public void setLevelLabel(final int levelLabel)
	{
		this.levelLabel.setText("<html><body>" + levelLabel + " <span style=\"font-size: 15px;\">level");
	}

	public void setLifeLabel(final int lifeLabel)
	{
		this.lifeLabel.setText("<html><body>" + lifeLabel + " <span style=\"font-size: 15px;\">life" +
																					((lifeLabel != 1) ? "s" : ""));
	}

	public void setBombLabel(final int bombLabel)
	{
		this.bombLabel.setText("<html><body>" + bombLabel + " <span style=\"font-size: 15px;\">bomb" +
																					((bombLabel != 1) ? "s" : ""));
	}

	public void setBombAreaLabel(final int bombAreaLabel)
	{
		this.bombAreaLabel.setText("<html><body>" + bombAreaLabel + " <span style=\"font-size: 15px;\">step" +
																				((bombAreaLabel != 1) ? "s" : ""));
	}

	public void setBombTimerLabel(final long bombTimerLabel)
	{
		this.bombTimerLabel.setText("<html><body>" + (float) bombTimerLabel / 1000 +
																			" <span style=\"font-size: 15px;\">sec.");
	}
	
	public void setBouncingBombLabel(final boolean bouncingBombLabel)
	{
		this.bouncingBombLabel.setText("<html><body><span style=\"font-size: 15px;\">Can" +
												(bouncingBombLabel ? "" : "'t") + " push bombs!");
	}
	
	public void setNewGameButtonEnable(final boolean enable)
	{
		newGameButton.setEnabled(enable);
	}
	
	public void setBackgroundMusicButtonIcon(final boolean onOff)
	{
		backgroundMusicToogleButton.setIcon( onOff ? new ImageIcon(LogosResource.BACKGROUND_MUSIC_ON.getImage()) :
														new ImageIcon(LogosResource.BACKGROUND_MUSIC_OFF.getImage()));
	}
	
	public void setSoundEffectsButtonIcon(final boolean onOff)
	{
		soundEffectsToogleButton.setIcon( onOff ? new ImageIcon(LogosResource.SOUND_EFFECTS_ON.getImage()) :
														new ImageIcon(LogosResource.SOUND_EFFECTS_OFF.getImage()));
	}
}

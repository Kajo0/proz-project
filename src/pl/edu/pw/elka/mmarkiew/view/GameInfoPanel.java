package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameInfoPanel extends JPanel {
	private JLabel timerLabel;
	private JLabel levelLabel;
	private JLabel lifeLabel;
	private JLabel bombLabel;
	private JLabel bombAreaLabel;
	private JLabel bombTimer;
	
	public GameInfoPanel() {
		super();
		setLayout(null);
		
		timerLabel = new JLabel();
		levelLabel = new JLabel();
		lifeLabel = new JLabel();
		bombLabel = new JLabel();
		bombAreaLabel = new JLabel();
		bombTimer = new JLabel();
		
		init();
	}

	private void init() {
		ArrayList<JLabel> labels = new ArrayList<JLabel>(Arrays.asList(timerLabel, levelLabel, lifeLabel, bombLabel,
																		bombAreaLabel, bombTimer));
		int i = 0;
		for (JLabel l : labels) {
			l.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
			l.setHorizontalAlignment(JLabel.LEFT);
			l.setBounds(80, 10 + i * 50, 120, 50);
			i++;
		}
		timerLabel.setBounds(0, 10, 200, 50);
		timerLabel.setHorizontalAlignment(JLabel.CENTER);
		
		add(timerLabel);
		add(levelLabel);
		add(lifeLabel);
		add(bombLabel);
		add(bombAreaLabel);
		add(bombTimer);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		ArrayList<LogosResource> logos = new ArrayList<LogosResource>(Arrays.asList(LogosResource.LEVEL,
							LogosResource.LIFES, LogosResource.BOMBS, LogosResource.STEPS, LogosResource.TIMER));
		int i = 1;
		for (LogosResource l : logos) {
			g.drawImage(l.getImage(), 40 - l.getImage().getWidth(this) / 2,
										35 + i * 50 - l.getImage().getHeight(this) / 2, this);
			i++;
		}
		
		g.dispose();
	}
	
	public void setTimerLabel(long timerLabel) {
		this.timerLabel.setText("Timer: " + timerLabel + " s");
	}
	
	public void setLevelLabel(int levelLabel) {
		this.levelLabel.setText("<html><body>" + levelLabel + " <span style=\"font-size: 15px;\">level");
	}

	public void setLifeLabel(int lifeLabel) {
		this.lifeLabel.setText("<html><body>" + lifeLabel + " <span style=\"font-size: 15px;\">life" +
																					((lifeLabel != 1) ? "s" : ""));
	}

	public void setBombLabel(int bombLabel) {
		this.bombLabel.setText("<html><body>" + bombLabel + " <span style=\"font-size: 15px;\">bomb" +
																					((bombLabel != 1) ? "s" : ""));
	}

	public void setBombAreaLabel(int bombAreaLabel) {
		this.bombAreaLabel.setText("<html><body>" + bombAreaLabel + " <span style=\"font-size: 15px;\">step" +
																				((bombAreaLabel != 1) ? "s" : ""));
	}

	public void setBombTimer(long bombTimer) {
		this.bombTimer.setText("<html><body>" + (float) bombTimer / 1000 + " <span style=\"font-size: 15px;\">sec.");
	}
}

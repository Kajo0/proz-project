package pl.edu.pw.elka.mmarkiew.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Options panel of game
 * @author Acer
 *
 */
@SuppressWarnings("serial")
public class GameOptionsPanel extends JPanel {
	private final JLabel optionLabel;
	private final JButton[] keyButtons;
	
	/**
	 * Creates game options panel and swing components on it
	 */
	public GameOptionsPanel()
	{
		super();
		
		optionLabel = new JLabel();
		keyButtons = new JButton[10];
		for (int i = 0; i < 10; ++i)
		{
			keyButtons[i] = new JButton();
		}
		
		init();
	}

	/**
	 * Option panel initialization
	 */
	private void init()
	{
		setLayout(null);
		
		optionLabel.setText("<html><body><center>Keyboard set.<br><small><sub>(Over to tooltip)");
		optionLabel.setBounds(50, 10, 100, 40);
		keyButtons[0].setText("P/N");
		keyButtons[0].setToolTipText("Switch pause/Start new game");
		
		keyButtons[1].setText("\u2191");	// UP arrow
		keyButtons[1].setToolTipText("Go up");
		
		keyButtons[2].setText("B/S");
		keyButtons[2].setToolTipText("Switch music/effects on/off");
		
		keyButtons[3].setText("\u2190");	// LEFT arrow
		keyButtons[3].setToolTipText("Go left");
		
		keyButtons[4].setText("\u2193");	// DOWN arrow
		keyButtons[4].setToolTipText("Go down");
		
		keyButtons[5].setText("\u2192");	// RIGHT arrow
		keyButtons[5].setToolTipText("Go right");
		
		keyButtons[6].setText("1");
		keyButtons[6].setToolTipText("Set bomb timer at 1 second");
		
		keyButtons[7].setText("2");
		keyButtons[7].setToolTipText("Set bomb timer at 2 seconds");
		
		keyButtons[8].setText("3");
		keyButtons[8].setToolTipText("Set bomb timer at 3 seconds");
		
		keyButtons[9].setText("SPACE");
		keyButtons[9].setToolTipText("Plant bomb");
		
		int k = 0;
		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 3; ++j)
			{
				keyButtons[k].setBounds(j % 3 * 60 + (j + 1) * 4, i % 3 * 60 + 60 + (i + 1) * 4, 60, 60);
				keyButtons[k].setFocusable(false);
				++k;
			}
		}
		
		keyButtons[9].setBounds(4, 256, 188, 60);
		keyButtons[9].setFocusable(false);
		
		add(optionLabel);
		for (int i = 0; i < 10; ++i)
		{
			add(keyButtons[i]);
		}
	}
}
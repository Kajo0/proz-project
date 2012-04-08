package pl.edu.pw.elka.mmarkiew.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameOptionsPanel extends JPanel {
	private JLabel optionLabel;
	private JButton[] keyButtons;
	
	public GameOptionsPanel() {
		super();
		
		optionLabel = new JLabel();
		keyButtons = new JButton[10];
		for (int i = 0; i < 10; i++)
			keyButtons[i] = new JButton();
		
		init();
	}

	private void init() {
		setLayout(null);
		
		optionLabel.setText("<html><body><center>Options Menu:<br><small><sub>(Click to change)");
		optionLabel.setBounds(50, 10, 100, 40);
		keyButtons[0].setText("P");
		keyButtons[1].setText("\u2191");
		keyButtons[2].setText("F");
		keyButtons[3].setText("\u2190");
		keyButtons[4].setText("\u2193");
		keyButtons[5].setText("\u2192");
		keyButtons[6].setText("1");
		keyButtons[7].setText("2");
		keyButtons[8].setText("3");
		keyButtons[9].setText("SPACE");
		
		int k = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				keyButtons[k].setBounds(j % 3 * 60 + (j + 1) * 4, i % 3 * 60 + 60 + (i + 1) * 4, 60, 60);
				keyButtons[k].setFocusable(false);
				k++;
			}
		}
		keyButtons[9].setBounds(4, 256, 188, 60);
		keyButtons[9].setFocusable(false);
		
		add(optionLabel);
		for (int i = 0; i < 10; i++)
			add(keyButtons[i]);
	}
}

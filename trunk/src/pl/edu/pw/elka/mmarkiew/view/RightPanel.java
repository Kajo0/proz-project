package pl.edu.pw.elka.mmarkiew.view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import pl.edu.pw.elka.mmarkiew.controller.Controller;

@SuppressWarnings("serial")
public class RightPanel extends JPanel {
	private JTabbedPane tabs;
	private JPanel gameInfo;
	private JPanel gameOptions;
	private JLabel label;
	
	public RightPanel() {
		setBounds(Controller.GAME_X_SIZE, 0, Controller.VIEW_WIDTH - Controller.GAME_X_SIZE, Controller.GAME_Y_SIZE);
		setBackground(Color.LIGHT_GRAY);
		setLayout(new BorderLayout());
		
		tabs = new JTabbedPane();
		gameInfo = new JPanel();
		gameOptions = new JPanel();
		
		label = new JLabel();
		
		init();
	}
	
	private void init() {

		tabs.setFocusable(false);
		tabs.addTab("Game", gameInfo);
		tabs.addTab("Options", gameOptions);
		
		label.setText("hehe");
		
		
		gameOptions.add(label);
		add(tabs, BorderLayout.CENTER);
	}

	public JLabel getLabel() {
		return label;
	}
}

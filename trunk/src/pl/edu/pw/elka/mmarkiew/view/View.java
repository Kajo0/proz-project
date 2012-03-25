package pl.edu.pw.elka.mmarkiew.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pl.edu.pw.elka.mmarkiew.controller.Controller;
import pl.edu.pw.elka.mmarkiew.model.ColorChange;

public class View {
	
	private JFrame defaultFrame;
	private JSlider redSlider, greenSlider, blueSlider;
	private JPanel panel;
	private JLabel label;
	
	
	public View() {
		initView();
		initListeners();
	}
	
	private void initView() {
		defaultFrame = new JFrame("Sample");
		
		defaultFrame.setBounds(500, 300, 200, 200);
		defaultFrame.setVisible(true);
		defaultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		defaultFrame.setAlwaysOnTop(true);
		defaultFrame.setResizable(false);

		redSlider = new JSlider(0, 255, 0);
		greenSlider = new JSlider(0, 255, 0);
		blueSlider = new JSlider(0, 255, 0);
		
		panel = new JPanel();
		label = new JLabel();
		
		panel.setBorder(new TitledBorder("Title"));
		panel.add(label);
		
		defaultFrame.setLayout(new GridLayout(4, 1));
		defaultFrame.add(redSlider);
		defaultFrame.add(greenSlider);
		defaultFrame.add(blueSlider);
		defaultFrame.add(panel);
		
	}
	
	private void initListeners() {
		redSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					Controller.queue.put(new ColorChange(ColorChange.colors.RED, redSlider.getValue()));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		greenSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					Controller.queue.put(new ColorChange(ColorChange.colors.GREEN, greenSlider.getValue()));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		blueSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					Controller.queue.put(new ColorChange(ColorChange.colors.BLUE, blueSlider.getValue()));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void changeSth(Color cc) {
		panel.setBackground(cc);
	}
}

package solarsystem;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SolarWindow extends JFrame {
	private JPanel drawPanel;
	private JPanel settingsPanel;
	
	private JLabel stateLabel;
	private Font   standardFont;
	
	public SolarWindow() {
		super("Solar system");
		this.setMinimumSize(new Dimension(750, 500));
		this.setBounds(100, 100, 750, 500);
		
		BorderLayout mainWindowLayout = new BorderLayout();		
		this.setLayout(mainWindowLayout);
		
		drawPanel = new JPanel(new GridLayout(1, 1));
		drawPanel.setBackground(Color.black);
				
		settingsPanel = new JPanel(new GridLayout());
		settingsPanel.setBackground(Color.gray);
		settingsPanel.setPreferredSize(new Dimension(250, 600));
		
		standardFont = new Font("Bahnschrift Light", 0, 12);
		stateLabel = new JLabel();
		stateLabel.setText("Current state:");
		stateLabel.setForeground(Color.white);
		stateLabel.setFont(standardFont);
		stateLabel.setBorder(new EmptyBorder(0, 5, 0, 0));

		JPanel statePanel = new JPanel(new BorderLayout());
			
		statePanel.setPreferredSize(new Dimension(300, 20));
		statePanel.setBackground(new Color(255, 51, 0));
		statePanel.add(stateLabel);
		
		SolarCanvas canvas = new SolarCanvas(250, Color.ORANGE, 70);
		canvas.setPreferredSize(new Dimension(100, 100));
		drawPanel.add(canvas);
		
		this.add(drawPanel, BorderLayout.CENTER);
		this.add(settingsPanel, BorderLayout.EAST);
		this.add(statePanel, BorderLayout.SOUTH);
	}
	
	public void setStateInformation(String newState) {
		stateLabel.setText(newState);
	}
	
}

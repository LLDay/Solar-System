package solarsystem;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import solarsystem.drawpanel.DrawPanel;
import solarsystem.propertypanel.SpaceObjectProperty;

public class SolarWindow extends JFrame {
	private JPanel drawPanel;
	private JPanel propertyPanel;

	private static JLabel stateLabel = new JLabel();

	public SolarWindow() {
		super("Solar system");

		this.setSize(new Dimension(750, 500));
		this.setMinimumSize(new Dimension(750, 500));
		this.setBounds(100, 100, 750, 500);

		BorderLayout mainWindowLayout = new BorderLayout();
		this.setLayout(mainWindowLayout);

		drawPanel = new DrawPanel();
		propertyPanel = new SpaceObjectProperty(DrawPanel.sun);
		
		stateLabel.setText("Current state:");
		stateLabel.setForeground(Color.white);
		stateLabel.setFont(MainProperties.mainFont);
		stateLabel.setBorder(new EmptyBorder(0, 5, 0, 0));

		JPanel statePanel = new JPanel(new BorderLayout());

		statePanel.setPreferredSize(new Dimension(300, 20));
		statePanel.setBackground(new Color(255, 51, 0));
		statePanel.add(stateLabel);

		this.add(drawPanel, BorderLayout.CENTER);
		this.add(propertyPanel, BorderLayout.EAST);
		this.add(statePanel, BorderLayout.SOUTH);
	}

	public static void setStateInformation(String newState) {
		stateLabel.setText(newState);
	}

}

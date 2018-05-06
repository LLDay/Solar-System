package solarsystem;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class SolarWindow extends JFrame {
	private JPanel drawPanel;
	private JPanel settingsPanel;

	private JLabel stateLabel;

	public SolarWindow() {
		super("Solar system");

		this.setSize(new Dimension(750, 500));
		this.setMinimumSize(new Dimension(750, 500));
		this.setBounds(100, 100, 750, 500);

		BorderLayout mainWindowLayout = new BorderLayout();
		this.setLayout(mainWindowLayout);

		drawPanel = new DrawPanel();
		drawPanel.setLayout(new GridLayout(1, 1));
		drawPanel.setBackground(Color.black);

		settingsPanel = new JPanel(new GridLayout());
		settingsPanel.setBackground(MainProperties.settingsPanelColor);
		settingsPanel.setPreferredSize(new Dimension(250, 600));
		settingsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white),
				"Properties", TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION, MainProperties.mainFont, Color.white));

		stateLabel = new JLabel();
		stateLabel.setText("Current state:");
		stateLabel.setForeground(Color.white);
		stateLabel.setFont(MainProperties.mainFont);
		stateLabel.setBorder(new EmptyBorder(0, 5, 0, 0));

		JPanel statePanel = new JPanel(new BorderLayout());

		statePanel.setPreferredSize(new Dimension(300, 20));
		statePanel.setBackground(new Color(255, 51, 0));
		statePanel.add(stateLabel);

		this.add(drawPanel, BorderLayout.CENTER);
		this.add(settingsPanel, BorderLayout.EAST);
		this.add(statePanel, BorderLayout.SOUTH);
	}

	public void setStateInformation(String newState) {
		stateLabel.setText(newState);
	}

}

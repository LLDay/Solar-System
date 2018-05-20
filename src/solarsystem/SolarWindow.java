package solarsystem;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import solarsystem.drawpanel.DrawPanel;
import solarsystem.propertypanel.SpaceProperty;
import solarsystem.spaceobject.SpaceObject;

public class SolarWindow extends JFrame {
	private static JPanel drawPanel;
	private static SpaceProperty spaceProperty;
	private static JLabel stateLabel = new JLabel();

	public SolarWindow() {
		super("Solar system");

		this.setSize(new Dimension(750, 500));
		this.setMinimumSize(new Dimension(750, 500));
		this.setBounds(100, 100, 750, 500);

		BorderLayout mainWindowLayout = new BorderLayout();
		this.setLayout(mainWindowLayout);

		drawPanel = new DrawPanel();

		stateLabel.setForeground(Color.white);
		stateLabel.setFont(MainProperties.mainFont);
		stateLabel.setBorder(new EmptyBorder(0, 5, 0, 0));

		JPanel statePanel = new JPanel(new BorderLayout());

		statePanel.setPreferredSize(new Dimension(300, 20));
		statePanel.setBackground(new Color(255, 51, 0));
		statePanel.add(stateLabel);

		spaceProperty = new SpaceProperty();

		this.add(drawPanel, BorderLayout.CENTER);
		this.add(spaceProperty, BorderLayout.EAST);
		this.add(statePanel, BorderLayout.SOUTH);
	}

	public static void setStateInformation(final String newState) {
		stateLabel.setText(newState);
	}

	public static void setProperty(final SpaceObject object) {
		if (spaceProperty != null) { //on starting
			spaceProperty.addObjectProperty(object);
			spaceProperty.repaint();
		}
	}

	public static void addSpaceObject(final SpaceObject spaceObject) {
		drawPanel.add(spaceObject);
	}

	public static int getCountSpaceObject() {
		return drawPanel.getComponentCount();
	}

	public static final SpaceObject getSpaceObject(int index) {
		return (SpaceObject) drawPanel.getComponent(index);
	}
}

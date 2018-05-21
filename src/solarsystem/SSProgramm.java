package solarsystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import solarsystem.drawpanel.DrawPanel;
import solarsystem.propertypanel.SpaceProperty;
import solarsystem.spaceobject.SpaceObject;

public class SSProgramm extends JFrame {
	private static JPanel drawPanel;
	private static SpaceProperty spaceProperty;
	private static JLabel stateLabel = new JLabel();
	
	public static Color spaceColor = Color.BLACK;
	public static Color defaultSunColor = Color.ORANGE;
	public static Color settingsPanelColor = new Color(37, 77, 116);
	
	public static boolean isGradient = true;
	public static double  brightness = 30000;
	
	public static Font mainFont = new Font("Bahnschrift Light", 0, 12);
	public static int  maxFPS   = 60;
	
	public static double timeSpeed = 1.0;

	public static void main(String[] args) {
		SSProgramm solar = new SSProgramm();
		solar.setVisible(true);
	}
	
	public SSProgramm() {
		super("Solar system");

		this.setSize(new Dimension(750, 500));
		this.setMinimumSize(new Dimension(750, 500));
		this.setBounds(100, 100, 750, 500);

		BorderLayout mainWindowLayout = new BorderLayout();
		this.setLayout(mainWindowLayout);

		drawPanel = new DrawPanel();

		stateLabel.setForeground(Color.white);
		stateLabel.setFont(mainFont);
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

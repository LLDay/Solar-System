package solarsystem.propertypanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import solarsystem.property.ColorButton;
import solarsystem.property.NameSettingsPanel;
import solarsystem.property.PhaseSettingsPanel;
import solarsystem.property.RadiusSettingsPanel;
import solarsystem.property.SpeedSettingsPanel;
import solarsystem.spaceobject.Planet;
import solarsystem.spaceobject.SpaceObjectChangeListener;
import solarsystem.spaceobject.SpaceObjectMoveListener;

public class PlanetProperty extends JPanel {
	PlanetInfoPanel info;
	PlanetSettingsPanel settings;
	
	public PlanetProperty(final Planet spaceObject) {
		super.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Planet", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));

		super.setLayout(new BorderLayout(5, 5));

		info = new PlanetInfoPanel(spaceObject);
		settings = new PlanetSettingsPanel(spaceObject);

		super.add(info, BorderLayout.NORTH);
		super.add(settings, BorderLayout.SOUTH);
	}
	
	private class PlanetInfoPanel extends SmartGridBagLayoutJPanel implements SpaceObjectChangeListener, SpaceObjectMoveListener {
		private final Planet object;
		
		private JLabel spaceObjectName = new JLabel();
		private JLabel spaceObjectSize = new JLabel();
		
		private JLabel spaceObjectPosX = new JLabel();
		private JLabel spaceObjectPosY = new JLabel();
		
		private JLabel planetSpeed = new JLabel();
		
		public PlanetInfoPanel(final Planet spaceObject) {
			object = spaceObject;	
			object.addStateListener(this);
			object.addMoveListener(this);
			
			JPanel positionPanel = new JPanel();
			positionPanel.setBorder(new TitledBorder(null, "Positon", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			positionPanel.setLayout(new GridLayout(0, 1, 5, 5));
			
			positionPanel.add(spaceObjectPosX);
			positionPanel.add(spaceObjectPosY);
			
			super.add(spaceObjectName);
			super.add(spaceObjectSize);
			
			super.add(positionPanel);
			super.add(planetSpeed);
			
			updateState();
		}

		@Override
		public void updateState() {
			spaceObjectName.setText("Name: " + object.getName());
			spaceObjectSize.setText("Radius: " + object.getRadius() + " km");
			planetSpeed.setText("Speed: " + String.format("%.2f", object.getAngleSpeed()) + "rad / c");		
		}

		@Override
		public void updateMoveInfo() {
			spaceObjectPosX.setText("x: " + (int)object.getPosition().getX());
			spaceObjectPosY.setText("y: " + (int)object.getPosition().getY());
		}
	}

	private class PlanetSettingsPanel extends SmartGridBagLayoutJPanel {
		public PlanetSettingsPanel(final Planet spaceObject) {

			JPanel sizeSettingsPanel = new RadiusSettingsPanel(spaceObject);
			JPanel speedSettingsPanel = new SpeedSettingsPanel(spaceObject);
			JPanel phaseSettingsPanel = new PhaseSettingsPanel(spaceObject);
			JPanel nameSettingsPanel = new NameSettingsPanel(spaceObject);
			
			JPanel buttonPanel = new JPanel();
			JButton colorButton = new ColorButton(spaceObject);
			
			buttonPanel.setLayout(new BorderLayout());
			buttonPanel.add(colorButton, BorderLayout.WEST);

			super.add(sizeSettingsPanel);
			super.add(speedSettingsPanel);
			super.add(phaseSettingsPanel);
			super.add(nameSettingsPanel);
			super.add(buttonPanel);
		}
	}
}

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
import solarsystem.property.SizeSettingsPanel;
import solarsystem.property.SpeedSettingsPanel;
import solarsystem.spaceobject.Planet;
import solarsystem.spaceobject.SpaceObjectChangeListener;

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
	
	private class PlanetInfoPanel extends SmartGridBagLayoutJPanel implements SpaceObjectChangeListener {
		private final Planet object;
		
		private JLabel spaceObjectName = new JLabel();
		private JLabel spaceObjectSize = new JLabel();
		
		private JLabel spaceObjectPosX = new JLabel();
		private JLabel spaceObjectPosY = new JLabel();
		
		public PlanetInfoPanel(final Planet spaceObject) {
			object = spaceObject;	
			object.addSOListener(this);
			
			JPanel positionPanel = new JPanel();
			positionPanel.setBorder(new TitledBorder(null, "Positon", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			positionPanel.setLayout(new GridLayout(0, 1, 5, 5));
			
			positionPanel.add(spaceObjectPosX);
			positionPanel.add(spaceObjectPosY);
			
			super.addColumn(spaceObjectName);
			super.addColumn(spaceObjectSize);
			super.addColumn(positionPanel);
			
			spaceObjectSize.setHorizontalAlignment(SwingConstants.LEFT);
			updateState();
		}

		@Override
		public void updateState() {
			spaceObjectName.setText("Name: " + object.getName());
			spaceObjectSize.setText("Radius: " + object.getRadius() + " km");
			spaceObjectPosX.setText("x: " + String.format("%.2f", object.getPosition().getX()));
			spaceObjectPosY.setText("y: " + String.format("%.2f", object.getPosition().getY()));
		}
	}

	private class PlanetSettingsPanel extends SmartGridBagLayoutJPanel {
		public PlanetSettingsPanel(final Planet spaceObject) {

			JPanel sizeSettingsPanel = new SizeSettingsPanel(spaceObject);
			JPanel speedSettingsPanel = new SpeedSettingsPanel(spaceObject);
			JPanel phaseSettingsPanel = new PhaseSettingsPanel(spaceObject);
			JPanel nameSettingsPanel = new NameSettingsPanel(spaceObject);
			
			JPanel buttonPanel = new JPanel();
			JButton colorButton = new ColorButton(spaceObject);
			
			buttonPanel.setLayout(new BorderLayout());
			buttonPanel.add(colorButton, BorderLayout.WEST);

			super.addColumn(sizeSettingsPanel);
			super.addColumn(speedSettingsPanel);
			super.addColumn(phaseSettingsPanel);
			super.addColumn(nameSettingsPanel);
			super.addColumn(buttonPanel);
		}
	}
}

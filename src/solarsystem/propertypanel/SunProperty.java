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
import solarsystem.property.RadiusSettingsPanel;
import solarsystem.spaceobject.SpaceObject;
import solarsystem.spaceobject.SpaceObjectChangeListener;
import solarsystem.spaceobject.Sun;

public class SunProperty extends JPanel{
	private SunInfoPanel info;
	private SunSettingsPanel settings;

	public SunProperty(final Sun sun) {
		super.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Star", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));

		super.setLayout(new BorderLayout(5, 5));

		info = new SunInfoPanel(sun);
		settings = new SunSettingsPanel(sun);

		super.add(info, BorderLayout.NORTH);
		super.add(settings, BorderLayout.SOUTH);
	}

	private class SunSettingsPanel extends SmartGridBagLayoutJPanel {
		final SpaceObject object;

		public SunSettingsPanel(final SpaceObject spaceObject) {
			object = spaceObject;

			JPanel sizeSettingsPanel = new RadiusSettingsPanel(object);
			JPanel nameSettingsPanel = new NameSettingsPanel(object);
			JPanel buttonPanel = new JPanel();
			JButton colorButton = new ColorButton(object);
			
			buttonPanel.setLayout(new BorderLayout());
			buttonPanel.add(colorButton, BorderLayout.WEST);
			
			super.add(sizeSettingsPanel);
			super.add(nameSettingsPanel);
			super.add(buttonPanel);
		}
	}

	private class SunInfoPanel extends JPanel implements SpaceObjectChangeListener {
		private final SpaceObject object;

		private JLabel spaceObjectName = new JLabel();
		private JLabel spaceObjectSize = new JLabel();

		public SunInfoPanel(final SpaceObject spaceObject) {
			object = spaceObject;
			object.addSOListener(this);
			
			super.setLayout(new BorderLayout());
			super.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			JPanel staticInfoPanel = new JPanel();
			super.add(staticInfoPanel);
			staticInfoPanel.setLayout(new GridLayout(0, 1, 5, 5));

			staticInfoPanel.add(spaceObjectName);
			spaceObjectName.setHorizontalAlignment(SwingConstants.LEFT);

			staticInfoPanel.add(spaceObjectSize);
			spaceObjectSize.setHorizontalAlignment(SwingConstants.LEFT);
			updateState();
		}

		@Override
		public void updateState() {
			spaceObjectName.setText("Name: " + object.getName());
			spaceObjectSize.setText("Radius: " + object.getRadius() + " km");
		}
	}

}

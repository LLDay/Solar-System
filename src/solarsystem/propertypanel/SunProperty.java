package solarsystem.propertypanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import solarsystem.property.ColorButton;
import solarsystem.property.NameSettingsPanel;
import solarsystem.property.SizeSettingsPanel;
import solarsystem.spaceobject.SpaceObject;
import solarsystem.spaceobject.Sun;

public class SunProperty extends JPanel {
	private SunInfoPanel info;
	private SunSettingsPanel settings;

	public SunProperty(Sun sun) {
		super.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sun", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));

		super.setLayout(new BorderLayout(5, 5));

		info = new SunInfoPanel(sun);
		settings = new SunSettingsPanel(sun);

		super.add(info, BorderLayout.NORTH);
		super.add(settings, BorderLayout.SOUTH);
	}

	private class SunSettingsPanel extends JPanel {
		final SpaceObject object;

		public SunSettingsPanel(final SpaceObject spaceObject) {
			object = spaceObject;

			GridBagLayout gbl_panel_2 = new GridBagLayout();
			gbl_panel_2.columnWidths = new int[] { 0, 0 };
			gbl_panel_2.rowHeights = new int[] { 0, 0, 0, 0 };
			gbl_panel_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
			gbl_panel_2.rowWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
			super.setLayout(gbl_panel_2);

			JPanel sizeSettingsPanel = new SizeSettingsPanel(object);
			GridBagConstraints gbc_panel_3 = new GridBagConstraints();
			gbc_panel_3.insets = new Insets(0, 0, 5, 0);
			gbc_panel_3.fill = GridBagConstraints.BOTH;
			gbc_panel_3.gridx = 0;
			gbc_panel_3.gridy = 0;
			super.add(sizeSettingsPanel, gbc_panel_3);

			JPanel nameSettingsPanel = new NameSettingsPanel(object);
			GridBagConstraints gbc_panel_4 = new GridBagConstraints();
			gbc_panel_4.insets = new Insets(0, 0, 5, 0);
			gbc_panel_4.fill = GridBagConstraints.BOTH;
			gbc_panel_4.gridx = 0;
			gbc_panel_4.gridy = 1;
			super.add(nameSettingsPanel, gbc_panel_4);

			JPanel buttonPanel = new JPanel();
			JButton colorButton = new ColorButton(object);
			
			buttonPanel.setLayout(new BorderLayout());
			buttonPanel.add(colorButton, BorderLayout.WEST);
			
			GridBagConstraints gbc_panel_5 = new GridBagConstraints();
			gbc_panel_5.fill = GridBagConstraints.BOTH;
			gbc_panel_5.gridx = 0;
			gbc_panel_5.gridy = 2;
			super.add(buttonPanel, gbc_panel_5);
		}
	}

	private class SunInfoPanel extends JPanel {
		private final SpaceObject object;

		private JLabel spaceObjectName = new JLabel();
		private JLabel spaceObjectSize = new JLabel();

		public SunInfoPanel(final SpaceObject spaceObject) {
			object = spaceObject;

			super.setLayout(new BorderLayout());
			super.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			JPanel staticInfoPanel = new JPanel();
			super.add(staticInfoPanel);
			staticInfoPanel.setLayout(new GridLayout(0, 1, 5, 5));

			staticInfoPanel.add(spaceObjectName);
			spaceObjectName.setHorizontalAlignment(SwingConstants.LEFT);

			staticInfoPanel.add(spaceObjectSize);
			spaceObjectSize.setHorizontalAlignment(SwingConstants.LEFT);
			update();
		}

		public void update() {
			spaceObjectName.setText("Name: " + object.getName());
			spaceObjectSize.setText("Radius: " + object.getRadius() + " km");
		}
	}

}

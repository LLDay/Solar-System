package solarsystem.propertypanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import solarsystem.MainProperties;
import solarsystem.drawpanel.SpaceObject;

import java.awt.*;
import java.util.List;

public class PropertyPanel extends JPanel {
	private List<Property> properties; 

	private GridBagLayout layout = new GridBagLayout();
	
	public PropertyPanel() {
		layout.columnWidths = new int[]{250, 0};
		layout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		layout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		layout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		super.setLayout(layout);
		super.setBackground(MainProperties.settingsPanelColor);
		super.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white),
				"Properties", TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION, MainProperties.mainFont, Color.white));
	
		this.addProperty(new Property.NameProperty());
		this.addProperty(new Property.SizeProperty());
	}
	
	public void addProperty(Property property) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 10, 0);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = super.getComponentCount();
		super.add(property, gbc);
	}
	
	public void selectSpaceObejct(SpaceObject object) {
		
	}
}

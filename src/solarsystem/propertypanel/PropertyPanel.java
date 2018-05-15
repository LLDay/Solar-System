package solarsystem.propertypanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import solarsystem.MainProperties;
import solarsystem.drawpanel.SpaceObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;
import java.util.List;

public class PropertyPanel extends JPanel {
	private List<Property> properties; 
	private BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);

	
	public PropertyPanel() {
		super.setLayout(layout);
		super.setBackground(MainProperties.settingsPanelColor);
		super.setPreferredSize(new Dimension(250, 600));
		super.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.white),
				"Properties", TitledBorder.DEFAULT_POSITION, TitledBorder.DEFAULT_POSITION, MainProperties.mainFont, Color.white));
	
		super.add(new Property.CheckBoxProperty("Is gradient"));
		super.add(new Property.TextFieldProperty("Size: "));
	}
	
	
	public void selectSpaceObejct(SpaceObject object) {
		
	}
}

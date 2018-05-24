package solarsystem.propertypanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import solarsystem.property.GradientSettingsPanel;
import solarsystem.property.SpaceObjectListComboBox;
import solarsystem.property.TimeSettingsPanel;
import solarsystem.spaceobject.SpaceObject;

public class SpaceProperty extends JPanel {
	private JPanel objectSettings;
	
	public SpaceProperty() {
		super.setLayout(new BorderLayout(5, 5));
		super.setPreferredSize(new Dimension(300, 1000));
		
		JPanel objectPropertyPanel = new JPanel();
		objectPropertyPanel.setBorder(new TitledBorder(null, "Object", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		super.add(objectPropertyPanel);
		objectPropertyPanel.setLayout(new BorderLayout(0, 0));
		
		JComboBox<SpaceObject> objectComboBox = new SpaceObjectListComboBox();
		objectPropertyPanel.add(objectComboBox, BorderLayout.NORTH);
		
		objectSettings = new JPanel();
		objectSettings.setLayout(new GridLayout(1, 1));
		objectPropertyPanel.add(objectSettings, BorderLayout.CENTER);
		
		JPanel commonPropertyPanel = new SmartGridBagLayoutJPanel();
		super.add(commonPropertyPanel, BorderLayout.SOUTH);
		
		JPanel timePanel = new TimeSettingsPanel();
		JPanel commonSettingsPanel = new JPanel();
		JPanel gradientPanel = new GradientSettingsPanel();
		JButton defaulPositionButton = new JButton("Set default position");
		
		commonPropertyPanel.add(timePanel);		
		commonPropertyPanel.add(commonSettingsPanel);
		commonSettingsPanel.add(gradientPanel);
		commonSettingsPanel.add(defaulPositionButton);

	}

	public void addObjectProperty(final SpaceObject object) {
		objectSettings.removeAll();
		objectSettings.add(object.getProperty());
	}
}

package solarsystem.propertypanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import solarsystem.property.SpaceObjectListComboBox;
import solarsystem.property.TimeSettingsPanel;
import solarsystem.spaceobject.SpaceObject;

public class SpaceProperty extends JPanel {
	private JPanel objectSettings;
	
	public SpaceProperty() {
		super.setLayout(new BorderLayout(5, 5));
		
		JPanel objectPropertyPanel = new JPanel();
		objectPropertyPanel.setBorder(new TitledBorder(null, "Object", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		super.add(objectPropertyPanel);
		objectPropertyPanel.setLayout(new BorderLayout(0, 0));
		
		SpaceObjectListComboBox objectComboBox = new SpaceObjectListComboBox();
		objectPropertyPanel.add(objectComboBox, BorderLayout.NORTH);
		
		objectSettings = new JPanel();
		objectSettings.setLayout(new GridLayout(1, 1));
		objectPropertyPanel.add(objectSettings, BorderLayout.CENTER);
		
		JPanel commonPropertyPanel = new JPanel();
		super.add(commonPropertyPanel, BorderLayout.SOUTH);
		commonPropertyPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel timePanel = new TimeSettingsPanel();
		commonPropertyPanel.add(timePanel);
		
		JPanel commonSettingsPanel = new JPanel();
		commonPropertyPanel.add(commonSettingsPanel, BorderLayout.SOUTH);
		
		JCheckBox gradientCheckBox = new JCheckBox("Use gradient");
		commonSettingsPanel.add(gradientCheckBox);
		
		JButton defaulPositionButton = new JButton("Set default position");
		commonSettingsPanel.add(defaulPositionButton);
	}

	public void addObjectProperty(final SpaceObject object) {
		objectSettings.removeAll();
		objectSettings.add(object.getProperty());
	}
}

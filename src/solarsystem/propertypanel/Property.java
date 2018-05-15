package solarsystem.propertypanel;

import solarsystem.MainProperties;


import java.awt.*;
import javax.swing.*;


public class Property extends Container {
	public Property() {
		super.setBackground(MainProperties.settingsPanelColor);
		BorderLayout layout = new BorderLayout();
		layout.setHgap(10);
		layout.setVgap(10);
		super.setLayout(layout);
	}

	private Property(String propertyName) {
		this();
		JLabel lbl = new JLabel(propertyName);
		super.add(lbl, BorderLayout.WEST);
	}
	
	public static class NameProperty extends Property {
		NameProperty() {
			super("Name");
			JTextField txtField = new JTextField();
			super.add(txtField, BorderLayout.CENTER);
		}
	}
	
	public static class SizeProperty extends Property {
		SizeProperty() {
			super("Size");
			JSlider slider = new JSlider();
			slider.setBackground(MainProperties.settingsPanelColor);
			
			JLabel lbl = new JLabel("value");
			super.add(slider, BorderLayout.CENTER);
			super.add(lbl, BorderLayout.EAST);
		}
	}
}

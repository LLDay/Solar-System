package solarsystem.propertypanel;

import solarsystem.MainProperties;

import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Container;
import java.awt.*;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.Spring;
import javax.swing.SpringLayout;

public class Property extends Container {
	private Property() {
		super.setBackground(MainProperties.settingsPanelColor);
		super.setLayout(new GridLayout());
	}
	
	private Property(String info) {
		this();
		super.add(new Label(info));
	}
	
	private Property(Component component) {
		this();
		super.add(component);
	}
	
	public Property(String propertyName, Component component) {
		this();
		super.add(new Label(propertyName));
		super.add(component);
	}

	public static class InfoProperty extends Property {
		public InfoProperty(String propertyName, String info) {
			super(propertyName + " " +info);
		}
		
		public InfoProperty(String info) {
			super(info);
		}
	}
	
	public static class TextFieldProperty extends Property {
		public TextFieldProperty(String propertyName) {
			super(propertyName, new TextField());
		}
	}
	
	public static class CheckBoxProperty extends Property {
		public CheckBoxProperty(String info) {
			super(new Checkbox(info));
		}
	}
}

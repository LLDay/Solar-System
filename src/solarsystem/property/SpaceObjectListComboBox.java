package solarsystem.property;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import solarsystem.MainProperties;
import solarsystem.SolarWindow;
import solarsystem.spaceobject.SpaceObject;

public class SpaceObjectListComboBox extends JComboBox<SpaceObject> {
	public SpaceObjectListComboBox() {
		super.setFont(MainProperties.mainFont);
		super.addItemListener(new SelectItem());
		update();
	}
	
	public void update() {
		final int count = SolarWindow.getCountSpaceObject();
		for (int i = 0; i < count; i++) {
			super.addItem(SolarWindow.getSpaceObject(i));
		}
	}
	
	private class SelectItem implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			SolarWindow.setProperty((SpaceObject)e.getItem());			
		}
		
	}
}

package solarsystem.property;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import solarsystem.SSProgramm;
import solarsystem.spaceobject.SpaceObject;

public class SpaceObjectListComboBox extends JComboBox<SpaceObject> {
	public SpaceObjectListComboBox() {
		super.setFont(SSProgramm.mainFont);
		super.addItemListener(new SelectItem());
		update();
	}
	
	public void update() {
		final int count = SSProgramm.getCountSpaceObject();
		for (int i = 0; i < count; i++) {
			super.addItem(SSProgramm.getSpaceObject(i));
		}
	}
	
	private class SelectItem implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			SSProgramm.setProperty((SpaceObject)e.getItem());			
		}
		
	}
}

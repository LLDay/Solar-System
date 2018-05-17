package solarsystem.propertypanel;

import javax.swing.JPanel;

import solarsystem.drawpanel.SpaceObject;

public class SpaceObjectProperty extends JPanel {
	final SpaceObject object;
	
	public SpaceObjectProperty(final SpaceObject spaceObject) {
		object = spaceObject;
	}
	
	public final SpaceObject getObject() {
		return object;
	}
	
	public void update() {
	}
}

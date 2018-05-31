package solarsystem.property;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;

import solarsystem.spaceobject.SpaceObject;
import solarsystem.spaceobject.SpaceObjectChangeListener;

public class ColorButton extends JButton implements SpaceObjectChangeListener{
	final SpaceObject object;

	public ColorButton(final SpaceObject spaceObject) {
		object = spaceObject;
		spaceObject.addStateListener(this);

		super.setBackground(spaceObject.getColor());
		super.setText("Color");
		super.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Color newColor = JColorChooser.showDialog(null, "Choose a color", object.getColor());
				if (newColor != null) {
					object.setColor(newColor);
					setBackground(newColor);
				}
			}
		});
	}

	@Override
	public void updateState() {
		super.setBackground(object.getColor());		
	}
}

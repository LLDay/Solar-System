package solarsystem.property;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;

import solarsystem.spaceobject.SpaceObject;

public class ColorButton extends JButton {
	final SpaceObject object;

	public ColorButton(final SpaceObject spaceObject) {
		object = spaceObject;

		super.setBackground(spaceObject.getColor());
		super.setText("Color");
		super.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Color newColor = JColorChooser.showDialog(null, "Choose a color", object.getColor());
				object.setColor(newColor);
				setBackground(newColor);
			}
		});
	}
}

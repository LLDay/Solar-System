package solarsystem.propertypanel;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import solarsystem.drawpanel.DrawPanel;

import java.awt.*;

public class PropertyPanel extends JPanel {
	private JSlider slider = new JSlider();
	private JTextField sizeTextField = new JTextField();
	
	public PropertyPanel() {
		super.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Space", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		super.setLayout(new BorderLayout(0, 0));
		
		SpaceObjectProperty property = new SpaceObjectProperty(DrawPanel.sun);

		super.add(property.getInfo(), BorderLayout.NORTH);
		super.add(property.getSettings());
	}
}

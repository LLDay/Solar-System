package solarsystem.property;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import solarsystem.spaceobject.SpaceObject;

public class SizeSettingsPanel extends JPanel {
	private final SpaceObject object;
	
	private JSlider slider;
	double k = 0.5;
	
	public SizeSettingsPanel(final SpaceObject spaceObject) {
		object = spaceObject;
	
		slider = new JSlider();
		slider.setValue((int) (object.getRadius() / k));
		slider.setPreferredSize(new Dimension(100, 25));
		slider.addChangeListener(new SliderChangeListener());
		slider.setMaximum(10000);
		slider.setMinimum(1);

		super.setLayout(new BorderLayout(5, 5));
		super.add(new JLabel("Size: "), BorderLayout.WEST);
		super.add(slider, BorderLayout.CENTER);
	}

	private class SliderChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			object.setRadius(slider.getValue() * k);
			object.setGradientRadius(object.getRadius() * 1.9);
		}
	}
}

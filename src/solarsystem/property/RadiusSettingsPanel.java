package solarsystem.property;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import solarsystem.spaceobject.SpaceObject;
import solarsystem.spaceobject.SpaceObjectChangeListener;

public class RadiusSettingsPanel extends JPanel implements SpaceObjectChangeListener {
	private final SpaceObject object;
	
	private JSlider slider;
	private double k = 0.5;
	
	public RadiusSettingsPanel(final SpaceObject spaceObject) {
		object = spaceObject;
		spaceObject.addStateListener(this);
	
		slider = new JSlider();
		slider.setValue((int) (object.getRadius() * k));
		slider.setMaximum(10000);
		slider.setMinimum(100);
		slider.addChangeListener(new SliderChangeListener());
		
		super.setLayout(new BorderLayout(5, 5));
		super.add(new JLabel("Radius: "), BorderLayout.WEST);
		super.add(slider, BorderLayout.CENTER);
	}

	private class SliderChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			object.setRadius(slider.getValue() / k);
		}
	}

	@Override
	public void updateState() {
		slider.setValue((int) (object.getRadius() * k));
		
	}
}

package solarsystem.property;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import solarsystem.spaceobject.Planet;

public class SpeedSettingsPanel extends JPanel {
	private final Planet object;

	private JSlider slider;
	private double k = 0.005;

	public SpeedSettingsPanel(final Planet spaceObject) {
		object = spaceObject;
		
		slider = new JSlider();
		slider.setValue((int)(object.getAngleSpeed() / k));
		slider.addChangeListener(new SliderChangeListener());
		slider.setMaximum(3600);
		slider.setMinimum(-3600);
		
		super.setLayout(new BorderLayout(5, 5));
		super.add(new JLabel("Speed: "), BorderLayout.WEST);
		super.add(slider, BorderLayout.CENTER);
	}

	private class SliderChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			object.setAngleSpeed(slider.getValue() * k);
		}
	}
}

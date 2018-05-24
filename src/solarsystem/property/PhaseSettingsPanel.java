package solarsystem.property;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;

import solarsystem.spaceobject.Planet;
import solarsystem.spaceobject.SpaceObjectChangeListener;

public class PhaseSettingsPanel extends JPanel implements SpaceObjectChangeListener {
	private final Planet object;
	private JSlider slider;

	private double lastSpeed;
	private double k = 0.1;

	public PhaseSettingsPanel(final Planet spaceObject) {
		object = spaceObject;
		object.addSOListener(this);

		slider = new JSlider();
		slider.setValue((int) (object.getPhase() / Math.PI * 180 / k));
		slider.addChangeListener(new SliderChangeListener());
		slider.addMouseListener(new FocusListener());
		slider.setMaximum(3599);
		slider.setMinimum(0);
		slider.setSnapToTicks(true);

		super.setLayout(new BorderLayout(5, 5));
		super.add(new JLabel("Phase: "), BorderLayout.WEST);
		super.add(slider, BorderLayout.CENTER);
	}

	private class SliderChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			object.setPhase(slider.getValue() * k * Math.PI / 180);
		}
	}

	@Override
	public void updateState() {
		double currPhase = object.getPhase();

		if (currPhase < 0.0)
			currPhase = 2 * Math.PI - currPhase;

		slider.setValue((int) (currPhase / Math.PI * 180 / k));
	}

	private class FocusListener extends MouseAdapter implements MouseInputListener {
		@Override
		public void mouseEntered(MouseEvent e) {
			double tmp = object.getAngleSpeed();
			object.setAngleSpeed(0.0);
			lastSpeed = tmp;
		}

		@Override
		public void mouseExited(MouseEvent e) {
			object.setAngleSpeed(lastSpeed);
		}
	}
}

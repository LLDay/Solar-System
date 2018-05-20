package solarsystem.property;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputListener;

import solarsystem.spaceobject.Planet;

public class PhaseSettingsPanel extends JPanel {
	private final Planet object;
	private JSlider slider;

	private double lastSpeed;
	private double k = 0.1;

	public PhaseSettingsPanel(final Planet spaceObject) {
		object = spaceObject;

		slider = new JSlider();
		slider.setValue((int) (object.getPhase() / Math.PI * 180 / k));
		slider.addChangeListener(new SliderChangeListener());
		slider.addMouseListener(new FocusListener());
		slider.setMaximum(3599);
		slider.setMinimum(0);

		super.setLayout(new BorderLayout(5, 5));
		super.add(new JLabel("Phase: "), BorderLayout.WEST);
		super.add(slider, BorderLayout.CENTER);
	}

	public void update() {
		slider.setValue((int) (object.getPhase() / Math.PI * 180 / k));
	}

	private class SliderChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			object.setPhase(slider.getValue() * k * Math.PI / 180);
		}
	}

	private class FocusListener extends MouseAdapter implements MouseInputListener {

		@Override
		public void mousePressed(MouseEvent e) {
			lastSpeed = object.getAngleSpeed();
			object.setAngleSpeed(0.0);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			object.setAngleSpeed(lastSpeed);
		}

	}
}

package solarsystem.property;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import solarsystem.SSProgramm;
import solarsystem.spaceobject.Planet;

public class AngleSpeedPlanetPanel extends JPanel {
	private final Planet object;

	private JSlider slider;
	double k = 0.1;

	public AngleSpeedPlanetPanel(final Planet planet) {
		object = planet;

		slider = new JSlider();
		slider.setValue((int) (object.getAngleSpeed() / k));
		slider.setPreferredSize(new Dimension(100, 25));
		slider.addChangeListener(new SliderChangeListener());
		slider.setMaximum(1000);
		slider.setMinimum(-1000);

		JLabel speedLbl = new JLabel("Speed: ");
		speedLbl.setFont(SSProgramm.mainFont);
		
		super.setLayout(new BorderLayout(5, 5));
		super.add(speedLbl, BorderLayout.WEST);
		super.add(slider, BorderLayout.CENTER);
	}

	private class SliderChangeListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			object.setAngleSpeed(slider.getValue() * k);
		}
	}
}

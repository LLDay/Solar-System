package solarsystem.property;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import solarsystem.SSProgramm;

public class GradientSettingsPanel extends JPanel {
	private JCheckBox checkbox;
	private JSlider slider;
	
	public GradientSettingsPanel() {
		super.setLayout(new BorderLayout(5, 5));
		checkbox = new JCheckBox("Use gradient", SSProgramm.isGradient);
		checkbox.addActionListener(new CheckBoxListener());
		
		JLabel lbl = new JLabel("Brightness: ");
		slider = new JSlider();
		slider.setMinimum(0);
		slider.setValue((int) Math.sqrt(SSProgramm.brightness));
		slider.setMaximum(100);
		slider.addChangeListener(new SliderListener());
		
		JPanel brightnessPanel = new JPanel();
		brightnessPanel.setLayout(new BorderLayout(5, 5));
		brightnessPanel.add(lbl, BorderLayout.WEST);
		brightnessPanel.add(slider, BorderLayout.EAST);
		
		super.add(checkbox, BorderLayout.NORTH);
		super.add(brightnessPanel, BorderLayout.SOUTH);		
	}
	
	private class SliderListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			SSProgramm.brightness = Math.pow(1.5, slider.getValue());		
		}	
	}

	private class CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!checkbox.isSelected()) {
				SSProgramm.isGradient = false;
				slider.setEnabled(false);
			}
			else {
				SSProgramm.isGradient = true;
				slider.setEnabled(true);
			}
		}		
	}
}

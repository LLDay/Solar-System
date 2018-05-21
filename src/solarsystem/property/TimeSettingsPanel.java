package solarsystem.property;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import solarsystem.SSProgramm;

public class TimeSettingsPanel extends JPanel {
	JLabel  timeLbl;
	JSlider slider;
	JButton stopButton;
	
	int lastValue;
	boolean isStop;
	
	double k = 25.0;
	
	public TimeSettingsPanel() {
		timeLbl = new JLabel("Time: 1.0x");
		timeLbl.setFont(SSProgramm.mainFont);
		
		slider = new JSlider();
		slider.setValue((int)k);
		slider.setMinimum(-500);
		slider.setMaximum(500);
		slider.addChangeListener(new SliderListener());
		
		stopButton = new JButton("Stop");
		stopButton.addMouseListener(new ButtonClick());
		stopButton.setFont(SSProgramm.mainFont);
		
		lastValue = (int)k;
		isStop = false;
		
		super.add(timeLbl);
		super.add(slider);
		super.add(stopButton);
	}
	
	private class SliderListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			lastValue = slider.getValue();
			SSProgramm.timeSpeed = lastValue / k;
			timeLbl.setText("Time: " + SSProgramm.timeSpeed + "x");
		}
	}
	
	private class ButtonClick extends MouseAdapter implements MouseListener {
		@Override 
		public void mouseReleased(MouseEvent e) {
			isStop = !isStop;
			
			if (!isStop) {
				if (lastValue == 0.0) 
					slider.setValue((int) k);
				
				else {
					slider.setValue((int)(lastValue * k));
					stopButton.setText("Stop");
				}
				slider.setEnabled(true);
			}
			
			else {
				stopButton.setText("Start");
				int tmpValue = lastValue;
				slider.setValue(0); //reset
				lastValue = tmpValue;
				slider.setEnabled(false);
			}
		}
	}
}

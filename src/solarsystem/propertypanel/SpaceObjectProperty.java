package solarsystem.propertypanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import solarsystem.drawpanel.SpaceObject;

public class SpaceObjectProperty {
	private Info info;
	private Settings settings;
	
	SpaceObjectProperty(final SpaceObject spaceObject) {
		info = new Info(spaceObject);
		settings = new Settings(spaceObject);
	}
	
	public final JPanel getInfo() {
		return info; 
	}
	
	public final JPanel getSettings() {
		return settings;
	}
	
	protected class Info extends JPanel {
		private final SpaceObject object;

		JLabel spaceObjectName = new JLabel();
		JLabel spaceObjectSize = new JLabel();
		JLabel spaceObjectX = new JLabel();
		JLabel spaceObjectY = new JLabel();
		
		public Info(final SpaceObject spaceObject) {
			object = spaceObject;
			super.setLayout(new BorderLayout());
			super.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			JPanel staticInfoPanel = new JPanel();
			super.add(staticInfoPanel);
			staticInfoPanel.setLayout(new GridLayout(0, 1, 5, 5));

			staticInfoPanel.add(spaceObjectName);
			spaceObjectName.setHorizontalAlignment(SwingConstants.LEFT);

			staticInfoPanel.add(spaceObjectSize);
			spaceObjectSize.setHorizontalAlignment(SwingConstants.LEFT);

			JPanel positionPanel = new JPanel();
			positionPanel.setBorder(new TitledBorder(null, "Position", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			super.add(positionPanel, BorderLayout.SOUTH);
			positionPanel.setLayout(new GridLayout(0, 1, 5, 5));

			positionPanel.add(spaceObjectX);
			positionPanel.add(spaceObjectY);
			
			update();
		}
		
		public void update() {
			spaceObjectName.setText("Name: " + object.getName());
			spaceObjectSize.setText("Radius: " + object.getRadius() + " km");
			spaceObjectX.setText("x: " + object.getPosition().getX());
			spaceObjectY.setText("y: " + object.getPosition().getY());
		}
	}

	protected class Settings extends JPanel {
		private final SpaceObject object;
		
		JTextField nameTextField = new JTextField();
		JSlider slider = new JSlider();
		
		public Settings(final SpaceObject spaceObject) {
			object = spaceObject;
			
			JPanel settingsPanelFlow = new JPanel();
			FlowLayout flowLayout = (FlowLayout) settingsPanelFlow.getLayout();
			flowLayout.setVgap(0);
			flowLayout.setHgap(0);
			super.add(settingsPanelFlow);

			JPanel settingsPanel = new JPanel();
			settingsPanel.setBorder(new TitledBorder(null, "Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			settingsPanelFlow.add(settingsPanel);
			settingsPanel.setLayout(new GridLayout(3, 0, 5, 5));

			JPanel sizePanel = new JPanel();
			settingsPanel.add(sizePanel);
			sizePanel.setLayout(new BorderLayout(5, 5));

			JLabel lblSize = new JLabel("Size");
			sizePanel.add(lblSize, BorderLayout.WEST);

			slider.setPreferredSize(new Dimension(100, 26));
			slider.addChangeListener(new SliderChangeListener());
			slider.setMaximum(1000);
			slider.setMinimum(1);
			sizePanel.add(slider, BorderLayout.CENTER);

			JTextField sizeTextField = new JTextField();
			sizePanel.add(sizeTextField, BorderLayout.EAST);
			sizeTextField.setColumns(5);

			JPanel namePanel = new JPanel();
			settingsPanel.add(namePanel);
			namePanel.setLayout(new BorderLayout(5, 5));

			JLabel lblName = new JLabel("Name");
			namePanel.add(lblName, BorderLayout.CENTER);
			
			namePanel.add(nameTextField, BorderLayout.EAST);
			nameTextField.setColumns(17);
			nameTextField.addKeyListener(new NameKeyListener());

			JPanel buttonsPanel = new JPanel();
			settingsPanel.add(buttonsPanel);
			buttonsPanel.setLayout(new BorderLayout(0, 0));

			JButton btnColor = new JButton("Color");
			buttonsPanel.add(btnColor, BorderLayout.WEST);

			JButton btnDelete = new JButton("Delete");
			buttonsPanel.add(btnDelete, BorderLayout.EAST);
		}
		
		private class NameKeyListener extends KeyAdapter implements KeyListener{
			@Override
			public void keyReleased(KeyEvent e) {
				object.setName(nameTextField.getText());
				info.update();
			}
		}
		
		private class SliderChangeListener implements ChangeListener {
			@Override
			public void stateChanged(ChangeEvent e) {
				object.setRadius(slider.getValue() * 5);
				object.setGradientRadius(object.getRadius() * 1.9);
			}
		}
	}

}

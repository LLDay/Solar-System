package solarsystem.propertypanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

import solarsystem.drawpanel.SpaceObject;

public class SpaceObjectProperty extends JPanel {
	private Info info;
	private Settings settings;

	public SpaceObjectProperty(final SpaceObject spaceObject) {
		super.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "SpaceObject",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		super.setLayout(new BorderLayout(0, 0));

		info = new Info(spaceObject);
		settings = new Settings(spaceObject);

		super.add(info, BorderLayout.NORTH);
		super.add(settings);
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
			positionPanel
					.setBorder(new TitledBorder(null, "Position", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		JSlider slider;

		public Settings(final SpaceObject spaceObject) {
			object = spaceObject;

			JPanel settingsPanelFlow = new JPanel();
			FlowLayout flowLayout = (FlowLayout) settingsPanelFlow.getLayout();
			flowLayout.setVgap(0);
			flowLayout.setHgap(0);
			super.add(settingsPanelFlow);

			JPanel settingsPanel = new JPanel();
			settingsPanel
					.setBorder(new TitledBorder(null, "Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			settingsPanelFlow.add(settingsPanel);
			settingsPanel.setLayout(new GridLayout(3, 0, 5, 5));

			JPanel sizePanel = new SliderSize();
			settingsPanel.add(sizePanel);

			JPanel namePanel = new JPanel();
			settingsPanel.add(namePanel);
			namePanel.setLayout(new BorderLayout(5, 5));
			namePanel.setMaximumSize(new Dimension(100, 25));

			JLabel lblName = new JLabel("Name");
			namePanel.add(lblName, BorderLayout.CENTER);

			namePanel.add(nameTextField, BorderLayout.EAST);
			nameTextField.setColumns(17);
			nameTextField.setDocument(new TextFieldLimit(25));
			nameTextField.addKeyListener(new NameKeyListener());

			JPanel buttonsPanel = new JPanel();
			settingsPanel.add(buttonsPanel);
			buttonsPanel.setLayout(new BorderLayout(0, 0));

			JButton btnColor = new JButton("Color");
			buttonsPanel.add(btnColor, BorderLayout.WEST);

			JButton btnDelete = new JButton("Delete");
			buttonsPanel.add(btnDelete, BorderLayout.EAST);
		}

		private class NameKeyListener extends KeyAdapter implements KeyListener {
			@Override
			public void keyReleased(KeyEvent e) {
				object.setName(nameTextField.getText().trim());
				info.update();
			}
		}

		private class SliderSize extends JPanel {
			JSlider slider;
			double k = 0.5;

			public SliderSize() {
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
					info.update();
				}
			}
		}
	}

}

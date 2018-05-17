package solarsystem.propertypanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import solarsystem.drawpanel.Sun;

public class SunProperty extends SpaceObjectProperty {
	private Info info;
	private Settings settings;

	public SunProperty(Sun sun) {
		super(sun);
		super.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sun", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));

		super.setLayout(new BorderLayout(5, 5));
		
		info = new Info();
		settings = new Settings();

		super.add(info, BorderLayout.NORTH);
		super.add(settings, BorderLayout.SOUTH);
	}
	
	@Override
	public void update() {
		info.update();
	}

	private class Info extends JPanel {
		private JLabel spaceObjectName = new JLabel();
		private JLabel spaceObjectSize = new JLabel();

		public Info() {
			super.setLayout(new BorderLayout());
			super.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));

			JPanel staticInfoPanel = new JPanel();
			super.add(staticInfoPanel);
			staticInfoPanel.setLayout(new GridLayout(0, 1, 5, 5));

			staticInfoPanel.add(spaceObjectName);
			spaceObjectName.setHorizontalAlignment(SwingConstants.LEFT);

			staticInfoPanel.add(spaceObjectSize);
			spaceObjectSize.setHorizontalAlignment(SwingConstants.LEFT);
			this.update();
		}

		public void update() {
			spaceObjectName.setText("Name: " + getObject().getName());
			spaceObjectSize.setText("Radius: " + getObject().getRadius() + " km");
		}
	}

	private class Settings extends JPanel {
		JTextField nameTextField = new JTextField();
		JSlider slider;

		public Settings() {
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
			btnColor.setBackground(object.getColor());
			
			btnColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					Color newColor = JColorChooser.showDialog(null, "Choose a color", object.getColor());
					object.setColor(newColor);
					btnColor.setBackground(newColor);
				}
			});
			
			buttonsPanel.add(btnColor, BorderLayout.WEST);
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

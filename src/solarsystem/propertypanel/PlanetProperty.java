package solarsystem.propertypanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import solarsystem.property.TextFieldLimit;
import solarsystem.spaceobject.Planet;
import solarsystem.spaceobject.SpaceObject;

public class PlanetProperty extends JPanel {
//	Info info;
//	Settings settings;
//	
//	public PlanetProperty(Planet planet) {
//		super.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Sun", TitledBorder.LEADING,
//				TitledBorder.TOP, null, new Color(0, 0, 0)));
//		
//		info = new Info();
//		settings = new Settings();
//		
//		
//		super.add(info, BorderLayout.NORTH);
//		super.add(settings, BorderLayout.SOUTH);
//	}
//	
//	private class Info extends JPanel {
//		JLabel spaceObjectName = new JLabel();
//		JLabel spaceObjectSize = new JLabel();
//
//		public Info() {
//			super.setLayout(new BorderLayout());
//			super.setBorder(new TitledBorder(null, "Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//
//			JPanel staticInfoPanel = new JPanel();
//			super.add(staticInfoPanel);
//			staticInfoPanel.setLayout(new GridLayout(0, 1, 5, 5));
//
//			staticInfoPanel.add(spaceObjectName);
//			spaceObjectName.setHorizontalAlignment(SwingConstants.LEFT);
//
//			staticInfoPanel.add(spaceObjectSize);
//			spaceObjectSize.setHorizontalAlignment(SwingConstants.LEFT);
//
//			JPanel positionPanel = new JPanel();
//			positionPanel
//					.setBorder(new TitledBorder(null, "Position", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//			super.add(positionPanel, BorderLayout.SOUTH);
//			positionPanel.setLayout(new GridLayout(0, 1, 5, 5));
//
//			update();
//		}
//
//		public void update() {
//			spaceObjectName.setText("Name: "   +  getObject().getName());
//			spaceObjectSize.setText("Radius: " + getObject().getRadius() + " km");
//		}
//	}
//
//	private class Settings extends JPanel {
//		JTextField nameTextField = new JTextField();
//		JSlider slider;
//
//		public Settings() {
//			JPanel settingsPanelFlow = new JPanel();
//			FlowLayout flowLayout = (FlowLayout) settingsPanelFlow.getLayout();
//			flowLayout.setVgap(0);
//			flowLayout.setHgap(0);
//			super.add(settingsPanelFlow);
//
//			JPanel settingsPanel = new JPanel();
//			settingsPanel
//					.setBorder(new TitledBorder(null, "Settings", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//			settingsPanelFlow.add(settingsPanel);
//			settingsPanel.setLayout(new GridLayout(3, 0, 5, 5));
//
//			JPanel sizePanel = new SliderSize();
//			settingsPanel.add(sizePanel);
//
//			JPanel namePanel = new JPanel();
//			settingsPanel.add(namePanel);
//			namePanel.setLayout(new BorderLayout(5, 5));
//			namePanel.setMaximumSize(new Dimension(100, 25));
//
//			JLabel lblName = new JLabel("Name");
//			namePanel.add(lblName, BorderLayout.CENTER);
//
//			namePanel.add(nameTextField, BorderLayout.EAST);
//			nameTextField.setColumns(17);
//			nameTextField.setDocument(new TextFieldLimit(25));
//			nameTextField.addKeyListener(new NameKeyListener());
//
//			JPanel buttonsPanel = new JPanel();
//			settingsPanel.add(buttonsPanel);
//			buttonsPanel.setLayout(new BorderLayout(0, 0));
//
//			JButton btnColor = new JButton("Color");
//			buttonsPanel.add(btnColor, BorderLayout.WEST);
//
//			JButton btnDelete = new JButton("Delete");
//			buttonsPanel.add(btnDelete, BorderLayout.EAST);
//		}
//
//		private class NameKeyListener extends KeyAdapter implements KeyListener {
//			@Override
//			public void keyReleased(KeyEvent e) {
//				getObject().setName(nameTextField.getText().trim());
//				info.update();
//			}
//		}
//
//		private class SliderSize extends JPanel {
//			JSlider slider;
//			double k = 0.5;
//
//			public SliderSize() {
//				slider = new JSlider();
//				slider.setValue((int) (getObject().getRadius() / k));
//				slider.setPreferredSize(new Dimension(100, 25));
//				slider.addChangeListener(new SliderChangeListener());
//				slider.setMaximum(5000);
//				slider.setMinimum(1);
//
//				super.setLayout(new BorderLayout(5, 5));
//				super.add(new JLabel("Size: "), BorderLayout.WEST);
//				super.add(slider, BorderLayout.CENTER);
//			}
//			
//			private class SliderChangeListener implements ChangeListener {
//				@Override
//				public void stateChanged(ChangeEvent e) {
//					getObject().setRadius(slider.getValue() * k);
//					getObject().setGradientRadius(getObject().getRadius() * 1.9);
//					info.update();
//				}
//			}
//		}
//	}

	private class PlanetInfoPanel extends JPanel {
		private final SpaceObject object;
		
		private JLabel spaceObjectName = new JLabel();
		private JLabel spaceObjectSize = new JLabel();
		
		private JLabel spaceObjectPosX = new JLabel();
		private JLabel spaceObjectPosY = new JLabel();
		
		public PlanetInfoPanel(final SpaceObject spaceObject) {
			object = spaceObject;
			
			GridBagLayout layout = new GridBagLayout();
			layout.columnWidths = new int[]{0, 0};
			layout.rowHeights = new int[]{0, 0, 0, 0};
			layout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			layout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
			super.setLayout(layout);
			
			GridBagConstraints gbc_spaceObjectName = new GridBagConstraints();
			gbc_spaceObjectName.insets = new Insets(0, 0, 5, 0);
			gbc_spaceObjectName.gridx = 0;
			gbc_spaceObjectName.gridy = 0;
			super.add(spaceObjectName, gbc_spaceObjectName);
			
			GridBagConstraints gbc_spaceObjectSize = new GridBagConstraints();
			gbc_spaceObjectSize.insets = new Insets(0, 0, 5, 0);
			gbc_spaceObjectSize.gridx = 0;
			gbc_spaceObjectSize.gridy = 1;
			super.add(spaceObjectSize, gbc_spaceObjectSize);
			
			JPanel positionPanel = new JPanel();
			positionPanel.setBorder(new TitledBorder(null, "Positon", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_positionPanel = new GridBagConstraints();
			gbc_positionPanel.fill = GridBagConstraints.BOTH;
			gbc_positionPanel.gridx = 0;
			gbc_positionPanel.gridy = 2;
			super.add(positionPanel, gbc_positionPanel);
			positionPanel.setLayout(new GridLayout(0, 1, 5, 5));
			
			positionPanel.add(spaceObjectPosX);
			positionPanel.add(spaceObjectPosY);
			
			spaceObjectSize.setHorizontalAlignment(SwingConstants.LEFT);
			update();
		}

		public void update() {
			spaceObjectName.setText("Name: " + object.getName());
			spaceObjectSize.setText("Radius: " + object.getRadius() + " km");
			spaceObjectPosX.setText("x: " + object.getPosition().getX());
			spaceObjectPosY.setText("y: " + object.getPosition().getY());
		}
	}

}

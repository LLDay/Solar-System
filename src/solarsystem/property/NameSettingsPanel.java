package solarsystem.property;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import solarsystem.spaceobject.SpaceObject;
import solarsystem.spaceobject.SpaceObjectChangeListener;

public class NameSettingsPanel extends JPanel implements SpaceObjectChangeListener{
	private JTextField nameTextField;
	private final SpaceObject object;
	
	public NameSettingsPanel(final SpaceObject spaceObject) {
		object = spaceObject;
		spaceObject.addStateListener(this);
		
		super.setLayout(new BorderLayout(5, 5));
		
		JLabel lblName = new JLabel("Name: " + object.getName());
		
		nameTextField = new JTextField();
		nameTextField.setDocument(new TextFieldLimit(25));
		nameTextField.addKeyListener(new NameKeyListener());
		nameTextField.setText(object.getName());
		
		super.add(lblName, BorderLayout.WEST);
		super.add(nameTextField, BorderLayout.CENTER);
	}
	
	private class NameKeyListener extends KeyAdapter implements KeyListener {
		@Override
		public void keyReleased(KeyEvent e) {
			object.setName(nameTextField.getText().trim());
		}
	}

	@Override
	public void updateState() {
		nameTextField.setText(object.getName());
	}
}

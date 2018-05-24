package solarsystem.propertypanel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class SmartGridBagLayoutJPanel extends JPanel {
	private GridBagLayout layout;
	private int index = 0;
	
	public SmartGridBagLayoutJPanel() {
		layout = new GridBagLayout();

		layout.columnWidths = new int[]{0};
		layout.rowHeights = new int[] {0};
		layout.columnWeights = new double[]{Double.MIN_VALUE};
		layout.rowWeights = new double[]{Double.MIN_VALUE};
		super.setLayout(layout);
	}
	
	@Override
	public Component add(Component column) {
		GridBagConstraints c = new GridBagConstraints();
//		gbc_spaceObjectName.anchor = GridBagConstraints.WEST;
//		gbc_spaceObjectName.insets = new Insets(0, 0, 5, 0);
//		gbc_spaceObjectName.gridwidth = GridBagConstraints.REMAINDER; 
//		gbc_spaceObjectName.gridx = 0;
//		gbc_spaceObjectName.gridy = GridBagConstraints.RELATIVE;
//		gbc_spaceObjectName.fill = GridBagConstraints.BOTH;
		
		c.anchor = GridBagConstraints.SOUTHWEST; 
		c.fill   = GridBagConstraints.HORIZONTAL;  
		c.gridheight = 1;
		c.gridwidth  = GridBagConstraints.REMAINDER; 
		c.gridx = 0; 
		c.gridy = GridBagConstraints.RELATIVE; 
		c.insets = new Insets(0, 0, 5, 0);
		c.ipadx = 0;
		c.ipady = 0;
		c.weightx = 0.0;
		c.weighty = 0.0;
		
		super.add(column, c);
		index++;
		return this;
	}
}

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
		layout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		layout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		super.setLayout(layout);
	}
	
	public void addColumn(Component column) {
		GridBagConstraints gbc_spaceObjectName = new GridBagConstraints();
		gbc_spaceObjectName.insets = new Insets(0, 0, 5, 0);
		gbc_spaceObjectName.gridx = 0;
		gbc_spaceObjectName.gridy = index;
		gbc_spaceObjectName.fill = GridBagConstraints.BOTH;
		super.add(column, gbc_spaceObjectName);
		index++;
	}
}

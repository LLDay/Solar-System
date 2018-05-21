package solarsystem.spaceobject;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class SpaceObjectListenerJComponent extends JComponent {
	private List<SpaceObjectChangeListener> listeners;
	
	public SpaceObjectListenerJComponent() {
		listeners = new ArrayList<>();
	}
	
	public void updateState() {
		for (SpaceObjectChangeListener listener : listeners)
			listener.updateState();
	}
	
	public void addSOListener(SpaceObjectChangeListener listener) {
		listeners.add(listener);
	}
}

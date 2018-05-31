package solarsystem.spaceobject;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

public class SpaceObjectPublisherJComponent extends JComponent {
	private List<SpaceObjectChangeListener> stateListeners;
	private List<SpaceObjectMoveListener> moveListeners;

	public SpaceObjectPublisherJComponent() {
		stateListeners = new ArrayList<>();
		moveListeners = new ArrayList<>();
	}

	public void updateState() {
		for (SpaceObjectChangeListener listener : stateListeners)
			listener.updateState();
	}

	public void updateMoveInfo() {
		for (SpaceObjectMoveListener listener : moveListeners)
			listener.updateMoveInfo();
	}

	public void addStateListener(SpaceObjectChangeListener listener) {
		stateListeners.add(listener);
	}

	public void addMoveListener(SpaceObjectMoveListener listener) {
		moveListeners.add(listener);
	}
}

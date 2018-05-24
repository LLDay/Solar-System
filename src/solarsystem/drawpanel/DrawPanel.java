package solarsystem.drawpanel;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import solarsystem.SSProgramm;
import solarsystem.spaceobject.Planet;
import solarsystem.spaceobject.SpaceObject;
import solarsystem.spaceobject.Sun;

public class DrawPanel extends JPanel implements Runnable {
	private Camera camera;
	private Sun sun;
	
	public DrawPanel() {
		super.setLayout(new BorderLayout());
		super.setBackground(SSProgramm.spaceColor);
		
		camera = new Camera(this);
		sun = new Sun(200);

		Planet earth = new Planet("Earth", 5000, 8000, Math.PI / 4);
		earth.setRadius(100);
		
		Planet mars = new Planet("Mars", 10000, 8000, Math.PI / 2);
		mars.setAngleSpeed(Math.PI / 8);
		mars.setRadius(80);

		addMouseWheelListener(camera);
		addMouseMotionListener(camera);
		addMouseListener(camera);

		this.add(sun);
		this.add(earth);
		this.add(mars);

		this.addMouseListener(new MouseReleasedProperty());
		
		Thread animation = new Thread(this);
		animation.start();
	}

	public void setCameraPosition(final Point2D position) {
		camera.setPosition(position);
	}

	public final SpaceObject getObjectByMousePos() {
		Point2D mousePos = camera.getMouseSpacePosition();
		if (mousePos == null)
			return null;
			
		double posX = mousePos.getX();
		double posY = mousePos.getY();

		for (int i = getComponentCount() - 1; i >= 0; i--) {
			SpaceObject tmpObject = (SpaceObject) this.getComponent(i);

			double objX = tmpObject.getPosition().getX();
			double objY = tmpObject.getPosition().getY();
			double objRadius = tmpObject.getRadius();

			double deltaXSquad = (posX - objX) * (posX - objX);
			double detlaYSquad = (posY - objY) * (posY - objY);

			if (Math.sqrt(deltaXSquad + detlaYSquad) <= objRadius)
				return tmpObject;
		}

		return null;
	}

	public void changeStateInfo() {
		SpaceObject mouseFocus = getObjectByMousePos();
		
		if (mouseFocus != null && mouseFocus.getName() != null) {
			if (mouseFocus.getName().isEmpty())
				SSProgramm.setStateInformation("Unnamed planet");
			else SSProgramm.setStateInformation(mouseFocus.getName());
			return;
		}
		
		Point2D mousePos = camera.getMouseSpacePosition();
		if (mousePos == null)
			return;

		SSProgramm.setStateInformation(String.format("%.3f", mousePos.getX()) + " : "
				+ String.format("%.3f", mousePos.getY()));
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000 / SSProgramm.maxFPS);
			} catch (InterruptedException e) {}

			repaint();
			camera.update();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;

		AffineTransform at = new AffineTransform();
		at.translate(camera.getPosition().getX(), camera.getPosition().getY());
		at.scale(camera.getZoom(), camera.getZoom());
		graphics.setTransform(at);

		changeStateInfo();

		for (int i = 0; i < this.getComponentCount(); i++)
			this.getComponent(i).paint(g);
	}

	class MouseReleasedProperty extends MouseAdapter implements MouseInputListener {
		@Override
		public void mouseReleased(MouseEvent e) {
			if (!SwingUtilities.isLeftMouseButton(e))
				return;
			
			SpaceObject mouseFocus = getObjectByMousePos();
			
			if (mouseFocus != null)
				SSProgramm.setProperty(mouseFocus);
		}
	}
	
}

package solarsystem.drawpanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import solarsystem.MainProperties;
import solarsystem.SolarWindow;
import solarsystem.propertypanel.SpaceProperty;
import solarsystem.spaceobject.Planet;
import solarsystem.spaceobject.SpaceObject;
import solarsystem.spaceobject.Sun;

public class DrawPanel extends JPanel implements Runnable {
	private Camera camera;
	private Sun sun;
	

	public DrawPanel() {
		super.setLayout(new BorderLayout());
		super.setBackground(MainProperties.spaceColor);
		
		camera = new Camera(200, 200);
		sun = new Sun(200);

		Planet earth = new Planet("Earth", 8000, 8000, Math.PI / 4);
		earth.setRadius(100);
		
		Planet mars = new Planet("Mars", 10000, 8000, Math.PI / 2);
		mars.setAngleSpeed(2.0);
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
		double posX = camera.getMouseSpacePosition().getX();
		double posY = camera.getMouseSpacePosition().getY();

		for (int i = 0; i < this.getComponentCount(); i++) {
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
			if (mouseFocus.getName() == "")
				SolarWindow.setStateInformation("Unnamed planet");
			else SolarWindow.setStateInformation(mouseFocus.getName());
			return;
		}

		SolarWindow.setStateInformation(String.format("%.3f", camera.getMouseSpacePosition().getX()) + " : "
				+ String.format("%.3f", camera.getMouseSpacePosition().getY()));
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000 / MainProperties.maxFPS);
			} catch (InterruptedException e) {}

			repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;

		camera.setAreaSize(this.getSize());

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
			SpaceObject mouseFocus = getObjectByMousePos();
			
			if (mouseFocus != null)
				SolarWindow.setProperty(mouseFocus);
		}
	}
	
}

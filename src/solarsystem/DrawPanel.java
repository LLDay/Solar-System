package solarsystem;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.JPanel;

public class DrawPanel extends JPanel implements Runnable {
	private Camera camera;
	private Sun sun;

	public DrawPanel() {
		camera = new Camera(200, 200);
		sun = new Sun(200);
		
		Planet earth = new Planet(sun);
		earth.setPosiiton(500, 500);
		earth.setRadius(100);
		earth.setName("Earth");

		addMouseWheelListener(camera);
		addMouseMotionListener(camera);
		addMouseListener(camera);

		this.add(sun);
		this.add(earth);
		
		Thread animation = new Thread(this);
		animation.start();

		this.setBackground(MainProperties.spaceColor);
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
			SolarWindow.setStateInformation(mouseFocus.getName());
			return;
		}
		
		SolarWindow.setStateInformation(String.format("%.3f", camera.getMouseSpacePosition().getX()) + " : " + 
				String.format("%.3f", camera.getMouseSpacePosition().getY()));
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000 / MainProperties.maxFPS);
			} catch (InterruptedException e) {}
			repaint(100);
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;

		camera.setCanvasSize(this.getSize());

		AffineTransform at = new AffineTransform();
		at.translate(camera.getPosition().getX(), camera.getPosition().getY());
		at.scale(camera.getZoom(), camera.getZoom());
		graphics.setTransform(at);
		
		Point2D mouseSpacePosition = camera.getMouseSpacePosition();
		
		changeStateInfo();
		
		for (int i = 0; i < this.getComponentCount(); i++) 
			this.getComponent(i).paint(g);
	}
}

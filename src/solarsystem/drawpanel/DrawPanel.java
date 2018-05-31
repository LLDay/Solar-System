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
		sun = new Sun(20000);
		
		addMouseWheelListener(camera);
		addMouseMotionListener(camera);
		addMouseListener(camera);
		addMouseListener(new MouseReleasedProperty());

		Planet mercury = new Planet();
		mercury.setName("Mercury");
		mercury.setRadius(480);
		mercury.setAngleSpeed(0.4787);
		mercury.setAngle(0);
		mercury.setSemiMajor(38709);
		mercury.setSemiMinor(mercury.getSemiMajor());
		
		Planet venus = new Planet();
		venus.setName("Venus");
		venus.setRadius(1210);
		venus.setAngleSpeed(0.3502);
		venus.setAngle(3.08923278);
		venus.setSemiMajor(72332);
		venus.setSemiMinor(venus.getSemiMajor());
		
		Planet earth = new Planet();
		earth.setName("Earth");
		earth.setRadius(1270);
		earth.setAngleSpeed(0.2979);
		earth.setAngle(0.40142573);
		earth.setSemiMajor(100000);
		earth.setSemiMinor(earth.getSemiMajor());
	
		Planet mars = new Planet();
		mars.setName("Mars");
		mars.setRadius(678);
		mars.setAngleSpeed(0.2413);
		mars.setAngle(0.43964844);
		mars.setSemiMajor(152367);
		mars.setSemiMinor(mars.getSemiMajor());
		
		Planet jupiter = new Planet();
		jupiter.setName("Jupiter");
		jupiter.setRadius(14280);
		jupiter.setAngleSpeed(0.1309);
		jupiter.setAngle(0.05462881);
		jupiter.setSemiMajor(520260);
		jupiter.setSemiMinor(jupiter.getSemiMajor());
		
		Planet saturn = new Planet();
		saturn.setName("Saturn");
		saturn.setRadius(12066);
		saturn.setAngleSpeed(0.0966);
		saturn.setAngle(0.43633231);
		saturn.setSemiMajor(955490);
		saturn.setSemiMinor(saturn.getSemiMajor());
		
		Planet uranus = new Planet();
		uranus.setName("Uranus");
		uranus.setRadius(5111);
		uranus.setAngleSpeed(0.0680);
		uranus.setAngle(1.6929694);
		uranus.setSemiMajor(1921844);
		uranus.setSemiMinor(uranus.getSemiMajor());
		
		Planet neptune = new Planet();
		neptune.setName("Neptune");
		neptune.setRadius(4952);
		neptune.setAngleSpeed(0.0544);
		neptune.setAngle(0.48869219);
		neptune.setSemiMajor(3011038);
		neptune.setSemiMinor(neptune.getSemiMajor());
		
		Planet pluto = new Planet();
		pluto.setName("Pluto");
		pluto.setRadius(230);
		pluto.setAngleSpeed(0.0474);
		pluto.setAngle(2.12930169);
		pluto.setSemiMajor(3951817);
		pluto.setSemiMinor(pluto.getSemiMajor());

		add(sun);
		add(mercury);
		add(venus);
		add(earth);
		add(mars);
		add(jupiter);
		add(saturn);
		add(uranus);
		add(neptune);
		add(pluto);
		
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

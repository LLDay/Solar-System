package solarsystem;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.JPanel;

public class DrawPanel extends JPanel implements Runnable {
	private Camera camera;
	private Sun sun;

	private int fps = 60;

	public DrawPanel() {
		camera = new Camera(200, 200);
		sun = new Sun(200);

		addMouseWheelListener(camera);
		addMouseMotionListener(camera);
		addMouseListener(camera);

		this.add(sun);
		Thread animation = new Thread(this);
		animation.start();

		this.setBackground(MainProperties.spaceColor);
	}

	public void setCameraPosition(final Point2D position) {
		camera.setPosition(position);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000 / fps);
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

		sun.paint(g);
	}
}

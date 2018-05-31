package solarsystem.drawpanel;

import java.awt.event.*;
import java.awt.geom.Point2D;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Camera extends MouseAdapter implements MouseWheelListener, MouseListener {
	private double zoom;
	private double posX;
	private double posY;

	private double speed = 3;

	private int mouseX;
	private int mouseY;
	private TimeTakt takt;
	private boolean isMoved;
	private final JPanel parent;

	public Camera(final JPanel parentPanel) {
		parent = parentPanel;
		posX = 200;
		posY = 200;
		zoom = 0.01;
		takt = new TimeTakt();
	}

	public void setZoom(double newZoom) {
		if (newZoom < 0.0)
			throw new IllegalArgumentException("Wrong zoom");

		zoom = newZoom;
	}

	public double getZoom() {
		return zoom;
	}

	public void setPosition(final Point2D position) {
		if (position == null)
			throw new NullPointerException("Position is null");

		posX = position.getX();
		posY = position.getY();
	}

	public void setPosition(double positionX, double positionY) {
		posX = positionX;
		posY = positionY;
	}

	public Point2D getPosition() {
		return new Point2D.Double(posX, posY);
	}

	public void setSpeed(double cameraSpeed) {
		speed = cameraSpeed;
	}

	public double getSpeed() {
		return speed;
	}

	public Point2D getMouseSpacePosition() {
		double mousePosX = (mouseX - posX) / zoom;
		double mousePosY = (posY - mouseY) / zoom;

		return new Point2D.Double(mousePosX, mousePosY);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		double zoomFactor = -0.1 * e.getPreciseWheelRotation() * zoom;
		zoom += zoomFactor;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (SwingUtilities.isRightMouseButton(e)) {
			isMoved = true;
			takt.delta();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		isMoved = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void update() {
		if (isMoved) {
			double taktTime = takt.delta();
			posX += taktTime * speed * (parent.getWidth() / 2 - mouseX);
			posY += taktTime * speed * (parent.getHeight() / 2 - mouseY);
		}
	}
}
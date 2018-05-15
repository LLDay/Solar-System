package solarsystem.drawpanel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;
import solarsystem.MainProperties;

public class Camera extends MouseAdapter implements MouseWheelListener, MouseListener {
	private double zoom;
	private double posX;
	private double posY;
	private Dimension canvSize;

	private double speed = 3;

	private int mouseX;
	private int mouseY;
	private Timer timer;
	private TimeTakt takt;
	

	Camera() {
		zoom = 1.0;
		takt = new TimeTakt();
	}

	Camera(final Dimension startPos) {
		this();

		if (startPos == null)
			throw new NullPointerException("Start position is null");

		posX = startPos.getWidth();
		posY = startPos.getHeight();
	}

	Camera(double x, double y) {
		this();

		posX = x;
		posY = y;
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

	public void setAreaSize(final Dimension size) {
		if (size == null)
			throw new NullPointerException("Area size is null");

		canvSize = size;
	}

	public void setAreaSize(int width, int height) {
		if (width <= 0 || height <= 0)
			throw new IllegalArgumentException("Wrong area size");

		canvSize = new Dimension(width, height);
	}

	public void setSpeed(double cameraSpeed) {
		speed = cameraSpeed;
	}

	public double getSpeed() {
		return speed;
	}

	public void centralPosition(final Container parent) {
		if (parent == null)
			throw new NullPointerException("Parent container is null");

		posX = parent.getWidth() / 2;
		posY = parent.getHeight() / 2;
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
		if (canvSize == null)
			return;

		if (!SwingUtilities.isRightMouseButton(e))
			return;

		if (timer == null) {
			timer = new Timer();
			takt.delta();
		}

		timer.schedule(new TimerTask() {
			public void run() {
				double taktTime = takt.delta();
				posX += taktTime * speed * (canvSize.getWidth() / 2 - mouseX);
				posY += taktTime * speed * (canvSize.getHeight() / 2 - mouseY);
			}
		}, 0, 1000 / MainProperties.maxFPS);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
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
}

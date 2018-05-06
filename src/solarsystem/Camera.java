package solarsystem;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Camera extends MouseAdapter implements MouseWheelListener, MouseListener {
	private double zoom;
	private double posX;
	private double posY;
	private Dimension canvSize;

	private double speed = 0.05;

	private int mouseX;
	private int mouseY;
	private Timer timer;

	Camera() {
		zoom = 1.0;
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

	public void setPosX(double position) {
		posX = position;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosY(double position) {
		posY = position;
	}

	public double getPosY() {
		return posY;
	}

	public void setCanvasSize(final Dimension size) {
		if (size == null)
			throw new NullPointerException("Canvas size is null");

		canvSize = size;
	}

	public void setCenvasSize(int width, int height) {
		if (width <= 0 || height <= 0)
			throw new IllegalArgumentException("Wrong canvas size");

		canvSize = new Dimension(width, height);
	}

	public final Dimension getCanvasSize() {
		return canvSize;
	}

	public void centralPosition(final Container parent) {
		if (parent == null)
			throw new NullPointerException("Parent container is null");

		posX = parent.getWidth() / 2;
		posY = parent.getHeight() / 2;
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

		if (timer == null)
			timer = new java.util.Timer();

		timer.schedule(new TimerTask() {
			public void run() {
				posX += speed * (canvSize.getWidth() / 2 - mouseX);
				posY += speed * (canvSize.getHeight() / 2 - mouseY);
			}
		}, 0, 50);
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
}

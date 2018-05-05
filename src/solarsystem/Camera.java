package solarsystem;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

public class Camera extends MouseAdapter
implements MouseWheelListener, MouseListener  {
	private double zoom;
	private double posX;
	private double posY;
	private Dimension canvSize;
	
	private double speed = 0.05;
	
	private int mouseX;
	private int mouseY;
	private Timer timer;
	
	Camera() {
		this(1.0);
	}
	
	Camera(double prefZoom) {
		zoom = prefZoom; 
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
		System.out.println(mouseX);
		timer.schedule(new TimerTask() {
            public void run() {
            	posX += speed * (canvSize.getWidth() / 2 - mouseX);
        		posY += speed * (canvSize.getHeight() / 2 - mouseY);
            }
        }, 0, 50);
	}

	
	@Override
	public void mouseReleased(MouseEvent e)
    {
        if(timer != null) {
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


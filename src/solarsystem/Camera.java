package solarsystem;

import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

public class Camera extends AffineTransform implements MouseWheelListener, MouseListener {
	private double zoom;
	private double posX;
	private double posY;
	private Timer timer;
	
	Camera() {
		this(1.0);
	}
	
	Camera(double prefZoom) {
		timer = new Timer();
		zoom = prefZoom; 
	}
	
	public final double getZoom() {
		return zoom;
	}
	
	public void setZoom(double newZoom) {
		if (newZoom < 0.0)
			throw new IllegalArgumentException("Wrong zoom");
		
		zoom = newZoom;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		double zoomFactor = -0.01 * e.getPreciseWheelRotation() * zoom;
        zoom = zoom + zoomFactor;
        
        
        this.setToScale(zoom, zoom);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {System.out.println("Klick");
	this.setToTranslation(posX++, posY);}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if(timer == null)
        {
			timer = new Timer();
        }
        timer.schedule(new TimerTask()
        {
            public void run()
            {
                System.out.println("Action");
            }
        }, 300, 500);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(timer != null)
        {
			timer.cancel();
			timer = null;
        }	
	}
}

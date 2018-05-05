package solarsystem;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Timer;
import java.util.TimerTask;

public class SolarCanvas extends Canvas {
	private Camera camera;
	private Sun sun;
	
	private int fps = 30;

	
	
	public SolarCanvas(int solarRad, Color solarColor, int gradientRadius) {
		camera = new Camera();
		sun = new Sun(200);		
		
		
		addMouseWheelListener(camera);
		addMouseMotionListener(camera);
		addMouseListener(camera);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
            public void run() {
            	repaint();
            }
        }, 100, 1000 / fps);
	
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		
		camera.setCanvasSize(this.getSize());
		
		AffineTransform at = new AffineTransform();
        at.translate(camera.getPosX(), camera.getPosY());
        at.scale(camera.getZoom(), camera.getZoom());

		graphics.setTransform(at);
		graphics.setPaint(sun.getPaint());
	    graphics.fill(sun.getShape());
	}
	

}

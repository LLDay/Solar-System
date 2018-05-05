package solarsystem;

import java.awt.*;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.*;

public class SolarCanvas extends Canvas {
	private Camera camera;
	private Sun sun;
	
	
	public SolarCanvas(int solarRad, Color solarColor, int gradientRadius) {
		camera = new Camera();
		
		sun = new Sun(200);		
		
		addMouseWheelListener(camera);
		addMouseWheelListener(e -> this.repaint());
		addMouseListener(camera);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		
	
		graphics.setTransform(camera);
		
		graphics.setPaint(sun.getPaint());
	    graphics.fill(sun.getShape());
	  
	}
	

}

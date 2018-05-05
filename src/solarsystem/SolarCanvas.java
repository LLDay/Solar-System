package solarsystem;

import java.awt.*;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.geom.*;

public class SolarCanvas extends Canvas {
	private Graphics2D graphics;
	private int solarRadius;
	private int gradRadius;
	private AffineTransform solarTransform;
	private Point position;
	private Sun sun;
	
	public SolarCanvas(int solarRad, Color solarColor, int gradientRadius) {
		solarRadius = solarRad;
		gradRadius = gradientRadius;
		solarTransform = new AffineTransform();
		position = new Point(0, 0);
		sun = new Sun(200);		
		sun.hasGradient(false);
		sun.setGradientRadius(250);
	}
	
	@Override
	public void paint(Graphics g) {
		graphics = (Graphics2D) g;
		solarTransform.setToTranslation(this.getWidth() / 2 + position.x, this.getHeight() / 2 + position.y);
		
		graphics.setTransform(solarTransform);
		graphics.setPaint(sun.getPaint());
	    graphics.fill(sun.getShape());
	    graphics.translate(-500, -500);
	}
	
	
}

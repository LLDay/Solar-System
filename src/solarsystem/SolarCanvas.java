package solarsystem;

import java.awt.*;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.geom.*;

public class SolarCanvas extends Canvas {
	private Graphics2D graphics;
	private int solarRadius;
	private int gradRadius;
	private Paint solarPaint;
	private AffineTransform solarTransform;
	private Point position;
	
	public SolarCanvas(int solarRad, Color solarColor, int gradientRadius) {
		solarRadius = solarRad;
		gradRadius = gradientRadius;
		solarTransform = new AffineTransform();
		position = new Point(0, 0);
	
		
		Point2D center = new Point2D.Double(0, 0);
		float[] dist = {0.1f, 1.0f};
		Color[] colors = {solarColor, Color.black};
		
		solarPaint = new RadialGradientPaint(center, gradRadius, center, dist, colors, CycleMethod.NO_CYCLE);
	}
	
	@Override
	public void paint(Graphics g) {
		graphics = (Graphics2D) g;
		solarTransform.setToTranslation(this.getWidth() / 2 + position.x, this.getHeight() / 2 + position.y);
		
		graphics.setTransform(solarTransform);
		graphics.setPaint(solarPaint);
	    graphics.fill(new Ellipse2D.Double(-solarRadius / 4, -solarRadius / 4, solarRadius / 2, solarRadius / 2));
	    graphics.translate(-500, -500);
	}
	
	
}

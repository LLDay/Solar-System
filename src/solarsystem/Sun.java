package solarsystem;

import java.awt.*;
import java.awt.geom.Point2D;


public class Sun extends SpaceObject {
	private static Point2D center = new Point2D.Float(0, 0);
	
	public Sun() {
		super(100.0f, center, Color.ORANGE);
	}
	
	public Sun(float radius) {
		this();
		super.setRadius(radius);
	}
	
	public Sun(float radius, Color centerColor) {
		super(radius, center, centerColor);
	}
	
	public Sun(float sunRadius, final Color topGradientColor, final Color botGradientColor,  float gradientRadius) {
		super(sunRadius, center, topGradientColor, botGradientColor, center, gradientRadius);
	}
}

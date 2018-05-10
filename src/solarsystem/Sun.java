package solarsystem;

import java.awt.*;
import java.awt.geom.Point2D;

public class Sun extends SpaceObject {
	private static Point2D center = new Point2D.Float(0, 0);

	public Sun() {
		super.setColor(Color.ORANGE);
		super.setName("The Sun");
	}

	public Sun(double radius) {
		this();
		super.setRadius(radius);
		super.setGradientRadius(radius * 1.9f);
	}

	public Sun(double radius, Color sunColor) {
		this(radius);
		super.setColor(sunColor);
	}

	public Sun(float sunRadius, final Color sunColor, float gradientRadius) {
		this(sunRadius, sunColor);
		super.setGradientRadius(gradientRadius);
	}
}

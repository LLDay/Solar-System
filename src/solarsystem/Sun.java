package solarsystem;

import java.awt.*;
import java.awt.geom.Point2D;

public class Sun extends SpaceObject {
	private static Point2D center = new Point2D.Float(0, 0);

	public Sun() {
		super(100.0f, center, MainProperties.defaultSunColor);
		super.setName("The Sun");
	}

	public Sun(float radius) {
		this();
		super.setRadius(radius);
		super.setGradientRadius(radius * 1.9f);
	}

	public Sun(float radius, Color centerColor) {
		super(radius, center, centerColor);
		super.setName("The Sun");
	}

	public Sun(float sunRadius, final Color sunColor, float gradientRadius, boolean hasGradient) {
		super(sunRadius, center, sunColor, center, gradientRadius, hasGradient);
		super.setName("The Sun");
	}
}

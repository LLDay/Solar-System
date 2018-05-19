package solarsystem.spaceobject;

import java.awt.*;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import solarsystem.propertypanel.SunProperty;

public class Sun extends SpaceObject {
	private static Point2D center = new Point2D.Double(0, 0);
	private SunProperty property;
	
	public Sun() {
		super.setColor(Color.ORANGE);
		super.setName("The Sun");
		property = new SunProperty(this);
	}

	public Sun(double radius) {
		this();
		super.setRadius(radius);
		super.setGradientRadius(radius * 1.9);
	}

	public Sun(double radius, Color sunColor) {
		this(radius);
		super.setColor(sunColor);
	}

	public Sun(double sunRadius, final Color sunColor, double gradientRadius) {
		this(sunRadius, sunColor);
		super.setGradientRadius(gradientRadius);
	}
	
	@Override
	public final JPanel getProperty() {
		return property;
	}
}

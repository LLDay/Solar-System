package solarsystem.drawpanel;

import java.awt.geom.*;

import solarsystem.MainProperties;

import java.awt.*;
import java.awt.MultipleGradientPaint.CycleMethod;

public class SpaceObject extends Component {
	private double radius;
	private Point2D position;
	private String name;

	private Color color;
	private Point2D gradCenter;
	private double gradRadius;

	public SpaceObject() {
		radius = 100;
		position = new Point2D.Double(0, 0);
		name = "";
		
		color = Color.WHITE;
		gradCenter = new Point2D.Double(0, 0);
		gradRadius = 100;
		name = "";
	}

	public void setColor(final Color shapeColor) {
		if (shapeColor == null)
			throw new NullPointerException("New color of shape is null");

		color = shapeColor;
	}

	public final Color getColor() {
		return color;
	}

	public void setRadius(double newRadius) {
		if (newRadius <= 0)
			throw new IllegalArgumentException("Radius cannot be negative or zero");

		radius = newRadius;
	}

	public double getRadius() {
		return radius;
	}

	public void setName(final String newName) {
		if (newName == null)
			throw new NullPointerException("Name of space object is null");

		name = newName;
	}

	public final String getName() {
		return name;
	}

	public void setPosition(final Point2D newPosition) {
		if (newPosition == null)
			throw new NullPointerException("New position of shape is null");

		position = newPosition;
	}

	public void setPosiiton(float x, float y) {
		position = new Point2D.Float(x, y);
	}

	public final Point2D getPosition() {
		return position;
	}

	public void setGradientCenter(final Point2D center) {
		if (center == null)
			throw new NullPointerException("New gradient center point is null");

		gradCenter = center;
	}

	public void setGradientCenter(double x, double y) {
		gradCenter = new Point2D.Double(x, y);
	}

	public final Point2D getGradientCenter() {
		return gradCenter;
	}

	public void setGradientRadius(double newGradRadius) {
		if (newGradRadius <= 0)
			throw new IllegalArgumentException("Gradient radius cannot be negative or zero");
		
		gradRadius = newGradRadius;
	}

	public double getGradientRadius() {
		return gradRadius;
	}

	private Paint getPainter() {
		if (!MainProperties.isGradient)
			return color;

		final float[] dist = { 0.0f, 1.0f };
		final Color[] colors = { color, MainProperties.spaceColor };

		return new RadialGradientPaint(gradCenter, (float) gradRadius, gradCenter, dist, colors,
				CycleMethod.NO_CYCLE);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;	
		
		Ellipse2D ellipse = new Ellipse2D.Double(position.getX() - radius, -position.getY() - radius, radius * 2,
				radius * 2);

		graphics.setPaint(getPainter());
		graphics.fill(ellipse);
	}
}

package solarsystem;

import java.awt.geom.*;
import java.awt.*;
import java.awt.MultipleGradientPaint.CycleMethod;

public class SpaceObject extends Component {
	private float radius;
	private Point2D position;

	private boolean hasGrad;
	private Color color;
	private Point2D gradCenter;
	private float gradRadius;

	public SpaceObject() {
		radius = 0.0f;
		gradRadius = 0.0f;
		hasGrad = true;
	}

	public SpaceObject(float shapeRadius, final Point2D shapePosition, final Color shapeColor) {
		this(shapeRadius, shapePosition, shapeColor, shapePosition, shapeRadius, true);
	}

	public SpaceObject(float shapeRadius, final Point2D shapePosition, final Color shapeColor,
			final Point2D gradientCenter, float gradientRadius, boolean hasGradient) {
		radius = shapeRadius;
		position = shapePosition;
		color = shapeColor;
		gradRadius = gradientRadius;
		gradCenter = gradientCenter;
		hasGrad = true;
	}

	public void setColor(final Color shapeColor) {
		if (shapeColor == null)
			throw new NullPointerException("New color of shape is null");

		color = shapeColor;
	}

	public final Color getColor() {
		if (color == null)
			throw new NullPointerException("Color is null");

		return color;
	}

	public void setRadius(float newRadius) {
		radius = newRadius;
	}

	public float getRadius() {
		return radius;
	}

	public void hasGradient(boolean isGrad) {
		hasGrad = isGrad;
	}

	public boolean isGradient() {
		return hasGrad;
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
		if (position == null)
			throw new NullPointerException("Position is null");

		return position;
	}

	public void setGradientCenter(final Point2D center) {
		if (center == null)
			throw new NullPointerException("New gradient center point is null");

		gradCenter = center;
	}

	public void setGradientCenter(float x, float y) {
		gradCenter = new Point2D.Float(x, y);
	}

	public final Point2D getGradientCenter() {
		if (gradCenter == null)
			throw new NullPointerException("Gradient center point is null");

		return gradCenter;
	}

	public void setGradientRadius(float newGradRadius) {
		gradRadius = newGradRadius;
	}

	public float getGradientRadius() {
		return gradRadius;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		Paint painter;

		if (!hasGrad)
			painter = color;

		else {
			final Point2D center = new Point2D.Double(0, 0);
			final float[] dist = { 0.0f, 1.0f };
			final Color[] colors = { color, MainProperties.spaceColor };

			painter = new RadialGradientPaint(center, gradRadius, center, dist, colors, CycleMethod.NO_CYCLE);
		}

		Ellipse2D ellipse = new Ellipse2D.Double(-radius / 2, -radius / 2, radius, radius);

		graphics.setPaint(painter);
		graphics.fill(ellipse);
	}
}

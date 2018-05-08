package solarsystem;

import java.awt.geom.*;
import java.awt.*;
import java.awt.MultipleGradientPaint.CycleMethod;

public class SpaceObject extends Component {
	private float radius;
	private Point2D position;
	private String name;
	
	private boolean hasGrad;
	private Color color;
	private Point2D gradCenter;
	private float gradRadius;
	
	public SpaceObject() {
		radius = 0.0f;
		gradRadius = 0.0f;
		hasGrad = true;
		name = "";
	}

	public SpaceObject(float shapeRadius, final Point2D shapePosition, final Color shapeColor) {
		this(shapeRadius, shapePosition, shapeColor, shapePosition, shapeRadius, true);
	}

	public SpaceObject(float shapeRadius, final Point2D shapePosition, final Color shapeColor,
			final Point2D gradientCenter, float gradientRadius, boolean hasGradient) {
		
		if(shapeRadius < 0)
			throw new IllegalArgumentException("Radius cannot be negative");
		
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
		return color;
	}

	public void setRadius(float newRadius) {
		if (newRadius <= 0)
			throw new IllegalArgumentException("Radius cannot be negative");
		
		radius = newRadius;
	}

	public float getRadius() {
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

		if (color == null) 
			throw new NullPointerException("Color is not defined");
		
		if (!hasGrad)
			painter = color;

		else {
			if (gradCenter == null)
				throw new NullPointerException("Center of gradient is not defined");
			
			final float[] dist = { 0.0f, 1.0f };
			final Color[] colors = { color, MainProperties.spaceColor };

			painter = new RadialGradientPaint(gradCenter, gradRadius, gradCenter, dist, colors, CycleMethod.NO_CYCLE);
		}

		Ellipse2D ellipse = new Ellipse2D.Double(-radius, -radius, radius * 2, radius * 2);

		graphics.setPaint(painter);
		graphics.fill(ellipse);
	}
}

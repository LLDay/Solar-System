package solarsystem;

import java.awt.geom.*;
import java.awt.*;
import java.awt.MultipleGradientPaint.CycleMethod;

public class SpaceObject {
	private float  radius;
	private Point2D position;
	
	private Color   colorTop;
	private Color	colorBot;
	private Point2D gradCenter;
	private float	gradRadius;
	
	public SpaceObject() {
		radius = 0.0f;
		gradRadius = 0.0f;
	}
	
	public SpaceObject(float shapeRadius, final Point2D shapePosition, final Color topGradientColor) {
		this(shapeRadius, shapePosition, topGradientColor, Color.BLACK, shapePosition, shapeRadius);
	}
	
	public SpaceObject(float shapeRadius, final Point2D shapePosition, final Color topGradientColor,
			final Color botGradientColor, final Point2D gradientCenter, float gradientRadius) {
		radius     = shapeRadius;
		position   = shapePosition;
		colorTop   = topGradientColor;
		colorBot   = botGradientColor;
		gradRadius = gradientRadius;
		gradCenter = gradientCenter;
	}
	
	
	public void setTopColor(final Color color) {
		if (color == null)
			throw new NullPointerException("New color of gradient center is null");
		
		colorTop = color;
	}
	
	public final Color getTopColor() {
		if (colorTop == null) 
			throw new NullPointerException("Color is null");
		
		return colorTop;
	}
	
	public void setBottomColor(final Color color) {
		if (color == null)
			throw new NullPointerException("New color of gradient bottom is null");
		
		colorBot = color;
	}
	
	public final Color getBottomColor() {
		if (colorTop == null) 
			throw new NullPointerException("Color is null");
		
		return colorBot;
	}
	
	public void setRadius(float newRadius) {
		radius = newRadius;
	}
	
	public float getRadius() {
		return radius;
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
	
	
	public Paint getPaint() {
		final Point2D center = new Point2D.Double(0, 0);
		final float[] dist = {0.1f, 1.0f};
		final Color[] colors = {colorTop, colorBot};
		
		return new RadialGradientPaint(center, gradRadius, center, dist, colors, CycleMethod.NO_CYCLE);
	}
	
	public Shape getShape() {
		return new Ellipse2D.Double(-radius / 2, -radius / 2, radius, radius);
	}
}

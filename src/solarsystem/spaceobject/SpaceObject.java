package solarsystem.spaceobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JPanel;

public abstract class SpaceObject extends SpaceObjectPublisherJComponent {
	private double radius;
	private Point2D position;
	private String name;
	private Color color;
	
	protected static final Point2D CENTER = new Point2D.Double(0, 0);

	public SpaceObject() {
		radius = 100;
		position = new Point2D.Double(0, 0);
		color = Color.WHITE;
		name = "";
	}

	public void setColor(final Color shapeColor) {
		if (shapeColor == null)
			throw new NullPointerException("New color of shape is null");

		color = shapeColor;
		super.updateState();
	}

	public final Color getColor() {
		return color;
	}

	public void setRadius(double newRadius) {
		if (newRadius <= 0)
			throw new IllegalArgumentException("Radius cannot be negative or zero");

		radius = newRadius;
		super.updateState();
	}

	public double getRadius() {
		return radius;
	}

	public void setName(final String newName) {
		if (newName == null)
			throw new NullPointerException("Name of space object is null");

		name = newName;
		super.updateState();
	}

	public final String getName() {
		return name;
	}

	public void setPosition(final Point2D newPosition) {
		if (newPosition == null)
			throw new NullPointerException("New position of shape is null");

		position = newPosition;
		super.updateState();
	}

	public void setPosition(double x, double y) {
		position = new Point2D.Double(x, y);
		super.updateMoveInfo();
	}

	public final Point2D getPosition() {
		return position;
	}

	protected abstract Paint getPainter();

	public double distance(final SpaceObject other) {
		double deltaX = this.position.getX() - other.position.getX();
		double deltaY = this.position.getY() - other.position.getY();
		
		return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
	}
	
	public JPanel getProperty() {
		return null;
	}
	
	@Override
	public final String toString() {
		String trimName = name.trim();
		if (trimName == "")
			return "Unnamed object";
		
		return trimName;			
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

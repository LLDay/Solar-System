package solarsystem.spaceobject;

import java.awt.Color;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Paint;
import java.awt.RadialGradientPaint;

import javax.swing.JPanel;

import solarsystem.SSProgramm;
import solarsystem.propertypanel.SunProperty;

public class Sun extends SpaceObject {
	private double gradientCoeff;
	
	public Sun() {
		gradientCoeff = 1.9;
		super.setColor(Color.ORANGE);
		super.setName("The Sun");
	}

	public Sun(double radius) {
		this();
		super.setRadius(radius);
	}

	public Sun(double radius, Color sunColor) {
		this(radius);
		super.setColor(sunColor);
	}
	
	public void setGradientCoefficient(double coefficient) { 
		if (coefficient <= 0.0)
			throw new IllegalArgumentException("Coefficient must be a positive");
		gradientCoeff = coefficient;
	}
	
	public double getGradientCoefficient() {
		return gradientCoeff;
	}
	
	@Override
	public final JPanel getProperty() {
		return new SunProperty(this);
	}

	@Override
	protected Paint getPainter() {
		if (!SSProgramm.isGradient)
			return super.getColor();

		final float[] dist = { 0.0f, 1.0f };
		final Color[] colors = { super.getColor(), SSProgramm.spaceColor };

		return new RadialGradientPaint(CENTER, (float) (super.getRadius() * gradientCoeff), CENTER, dist, colors, CycleMethod.NO_CYCLE);
	}
}

package solarsystem.spaceobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.Random;

import javax.swing.JPanel;

import solarsystem.MainProperties;
import solarsystem.drawpanel.TimeTakt;
import solarsystem.propertypanel.PlanetProperty;

public class Planet extends SpaceObject {
	private double angleSpeed = 45.0 / 180 * Math.PI;
	private TimeTakt takt;
	private PlanetProperty property;
	
	private double a, b, e, c, angle, phase;
	
	static Random rand = new Random(System.currentTimeMillis());
	
	public Planet(final String name, double a, double b, double angle) {
		super.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
		super.setName(name);
		
		this.a = Math.max(a, b);
		this.b = Math.min(a, b);
		this.angle = angle;
		c = Math.sqrt(Math.abs(a * a - b * b));
		e = c / this.a;
		phase = 0.0;
		
		property = new PlanetProperty(this);
		takt = new TimeTakt();
	}

	private void move() {
		double deltaTime = takt.delta();
		phase += MainProperties.timeSpeed * angleSpeed * deltaTime;
		phase %= 2 * Math.PI;
		
		double p = b * b / a;
		double ro = p / (1 - e * Math.cos(phase));
		
		double x = -ro * Math.cos(phase + angle) + 2 * c * Math.cos(angle);
		double y = -ro * Math.sin(phase + angle) + 2 * c * Math.sin(angle);
		
		setPosition(x, y);
	}

	public void setAngleSpeed(double speed) {
		this.angleSpeed = speed;
	}
	
	public double getAngleSpeed() {
		return angleSpeed;
	}
	
	public void setPhase(double phase) {
		this.phase = phase;
	}
	
	public double getPhase() {
		return phase;
	}
	
	public void reverse() {
		angleSpeed = -angleSpeed;
	}
	
	@Override
 	public void paint(Graphics g) {
		move();
		super.paint(g);
	}
	
	@Override
	public final JPanel getProperty() {
		return property;
	}

	@Override
	protected Paint getPainter() {
		if (!MainProperties.isGradient)
			return super.getColor();

		final float[] dist = { 0.0f, 1.0f };
		final Color[] colors = { super.getColor(), MainProperties.spaceColor };
		
		return new RadialGradientPaint(CENTER, (float) MainProperties.brightness, CENTER, dist, colors,	CycleMethod.NO_CYCLE);
	}
}
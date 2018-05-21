package solarsystem.spaceobject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.util.Random;

import javax.swing.JPanel;

import solarsystem.SSProgramm;
import solarsystem.drawpanel.TimeTakt;
import solarsystem.propertypanel.PlanetProperty;

public class Planet extends SpaceObject {
	private double angleSpeed = 45.0 / 180 * Math.PI;
	private TimeTakt takt;
	
	private double semiMajor; 
	private double semiMinor;
	private double eccentricity;
	private double foci;
	private double angle;
	private double phase;
	
	static Random rand = new Random(System.currentTimeMillis());
	
	public Planet() {
		super.setName("");
		super.setRadius(50);
		super.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
		semiMajor = 0.0;
		semiMinor = 0.0;
		angle = 0.0;
		phase = 0.0;
		takt = new TimeTakt();
	}
	
	public Planet(final String name, double a, double b, double angle) {
		this();
		this.semiMajor = Math.max(a, b);
		this.semiMinor = Math.min(a, b);
		this.angle = angle;
		foci = Math.sqrt(Math.abs(a * a - b * b));
		eccentricity = foci / this.semiMajor;
	}

	private void move() {
		double deltaTime = takt.delta();
		phase += SSProgramm.timeSpeed * angleSpeed * deltaTime;
		phase %= 2 * Math.PI;
		
		double p = semiMinor * semiMinor / semiMajor;
		double ro = p / (1 - eccentricity * Math.cos(phase));
		
		double x = -ro * Math.cos(phase + angle) + 2 * foci * Math.cos(angle);
		double y = -ro * Math.sin(phase + angle) + 2 * foci * Math.sin(angle);
		
		setPosition(x, y);
	}

	public void setSemiMajor(double semiMajor) {
		this.semiMajor = semiMajor;
		foci = Math.sqrt(Math.abs(semiMajor * semiMajor - semiMinor * semiMinor));
		eccentricity = foci / this.semiMajor;
	}
	
	public double getSemiMajor() {
		return semiMajor;
	}
	
	public void setSemiMinor(double semiMinor) {
		this.semiMinor = semiMinor;
		foci = Math.sqrt(Math.abs(semiMajor * semiMajor - semiMinor * semiMinor));
		eccentricity = foci / this.semiMajor;
	}
	
	public double getSemiMinor() {
		return semiMinor;
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
		return new PlanetProperty(this);
	}

	@Override
	protected Paint getPainter() {
		if (!SSProgramm.isGradient)
			return super.getColor();

		final float[] dist = { 0.0f, 1.0f };
		final Color[] colors = { super.getColor(), SSProgramm.spaceColor };
		
		return new RadialGradientPaint(CENTER, (float) SSProgramm.brightness, CENTER, dist, colors,	CycleMethod.NO_CYCLE);
	}
}
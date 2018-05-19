package solarsystem.spaceobject;

import java.awt.*;
import java.util.Random;

import javax.swing.JPanel;

import solarsystem.MainProperties;
import solarsystem.drawpanel.TimeTakt;
import solarsystem.propertypanel.PlanetProperty;

public class Planet extends SpaceObject {
	private double angleSpeed = 1.0;
	private TimeTakt takt;
	private PlanetProperty property;
	
	private double a, b, e, c, angle, phasa;
	
	static Random rand = new Random(System.currentTimeMillis());
	
	public Planet(String name, double a, double b, double angle) {
		super.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
		super.setGradientCenter(new Point.Double(0, 0));
		super.setGradientRadius(MainProperties.brightness);
		super.setName(name);
		
		this.a = Math.max(a, b);
		this.b = Math.min(a, b);
		this.angle = angle;
		c = Math.sqrt(Math.abs(a * a - b * b));
		e = c / this.a;
		phasa = 0.0;
		
		takt = new TimeTakt();
	}

	private void move() {
		double deltaTime = takt.delta();
		phasa += MainProperties.timeSpeed * angleSpeed * deltaTime;
		
		double p = b * b / a;
		double ro = p / (1 - e * Math.cos(phasa));
		
		double x = ro * Math.cos(phasa + angle) - 2 * c * Math.cos(angle);
		double y = ro * Math.sin(phasa + angle) - 2 * c * Math.sin(angle);
		
		setPosition(x, y);
	}
	
	private void update() {
		move();
	}
	
	public void setAngleSpeed(double speed) {
		this.angleSpeed = speed;
	}
	
	public double getAngleSpeed() {
		return angleSpeed;
	}
	
	@Override
	public void paint(Graphics g) {
		update();
		super.paint(g);
	}
	
	@Override
	public final JPanel getProperty() {
		return property;
	}
}
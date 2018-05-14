package solarsystem.drawpanel;

import java.awt.*;
import java.util.Random;

import solarsystem.MainProperties;

public class Planet extends SpaceObject {
	private double speed = 1.5;
	private TimeTakt takt;
	
	private double a, b;
	
	static Random rand = new Random(System.currentTimeMillis());
	
	public Planet(String name, double a, double b) {
		super.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
		super.setGradientCenter(new Point.Double(0, 0));
		super.setGradientRadius(MainProperties.brightness);
		super.setName(name);
		
		this.a = a;
		this.b = b;
		
		takt = new TimeTakt();
	}

	private void move(double x, double y) {
		double deltaTime = takt.delta();
		setPosition(getPosition().getX() + deltaTime * x, 
				getPosition().getY() + deltaTime * y);
	}
	
	private void move() {
		double deltaTime = takt.time();
		
		//double ang = 45 * Math.PI / 180;
		double ang = 0.0;
		
		double x = a * Math.cos(speed * deltaTime + ang);
		double y = b * Math.sin(speed * deltaTime + ang);
		
		setPosition(x, y);
	}
	
	private void update() {
		move();
	}
	
	@Override
	public void paint(Graphics g) {
		update();
		super.paint(g);
	}
}

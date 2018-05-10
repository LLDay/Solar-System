package solarsystem.drawpanel;

import java.awt.*;
import java.util.Random;

import solarsystem.MainProperties;

public class Planet extends SpaceObject {
	private float speedX;
	private float speedY;
	private TimeTakt takt;
	
	private SpaceObject objRotation;
	
	Random rand = new Random(System.currentTimeMillis());
	
	public Planet(final SpaceObject focusRotation, String name) {
		super.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
		super.setGradientCenter(focusRotation.getPosition());
		super.setGradientRadius(MainProperties.brightness);
		super.setName(name);
		
		takt = new TimeTakt();
		objRotation = focusRotation;
	}

	private void move(double x, double y) {
		double deltaTime = takt.delta();
		setPosition(getPosition().getX() + deltaTime * x, 
				getPosition().getY() + deltaTime * y);
	}
	
	private void update() {
		move(100.0, 0);
	}
	
	@Override
	public void paint(Graphics g) {
		update();
		super.paint(g);
	}
}

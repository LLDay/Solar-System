package solarsystem;

import java.awt.*;
import java.util.Random;

public class Planet extends SpaceObject {
	private float speedX;
	private float speedY;
	
	private SpaceObject objRotation;
	
	Random rand = new Random(System.currentTimeMillis());
	
	
	public Planet(final SpaceObject focusRotation) {
		super.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
		super.setGradientCenter(focusRotation.getPosition());
		super.setGradientRadius(850);
		objRotation = focusRotation;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
}

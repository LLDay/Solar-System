package solarsystem;

import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

public class Planet extends SpaceObject {
	private float speedX;
	private float speedY;
	
	public Planet(final SpaceObject focusRotation) {
		Random rand = new Random(255);
		super.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
	}
}

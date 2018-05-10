package solarsystem.drawpanel;

public class TimeTakt {
	private long lastTime;
	private static double NANO_IN_SEC = 1000000000.0;
	
	TimeTakt() {
		lastTime = System.nanoTime();
	}
	
	public double delta() {
		long currTime = System.nanoTime();
		double delta = (currTime - lastTime) / NANO_IN_SEC;
		lastTime = currTime;
	
		return delta;
	}
}

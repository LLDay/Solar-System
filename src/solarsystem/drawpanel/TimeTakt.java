package solarsystem.drawpanel;

public class TimeTakt {
	private long firstTime;
	private long lastTime;
	private static double NANO_IN_SEC = Math.pow(10, 9);
	
	public TimeTakt() {
		firstTime = System.nanoTime();
		lastTime = firstTime;
	}
	
	public double delta() {
		long currTime = System.nanoTime();
		double delta = (currTime - lastTime) / NANO_IN_SEC;
		lastTime = currTime;
		return delta;
	}

	public double absolute() {
		long currTime = System.nanoTime();
		return (currTime - firstTime) / NANO_IN_SEC;
	}
}

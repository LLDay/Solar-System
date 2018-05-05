package solarsystem;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class DrawPanel extends JPanel implements Runnable {
	private Camera camera;
	private Sun sun;
	
	private int fps = 60;

	
	public DrawPanel() {
		camera = new Camera();
		sun = new Sun(200);		
		
		addMouseWheelListener(camera);
		addMouseMotionListener(camera);
		addMouseListener(camera);
		
		Thread animation = new Thread(this);
		animation.start();
	
		this.setBackground(Color.BLACK);	
	}
	
	@Override
	public void run() {
		for(;;){
            try {
                Thread.sleep(1000 / fps);
            } 
            catch (InterruptedException e) {}
            repaint();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		
		camera.setCanvasSize(this.getSize());
		
		AffineTransform at = new AffineTransform();
        at.translate(camera.getPosX(), camera.getPosY());
        at.scale(camera.getZoom(), camera.getZoom());

		graphics.setTransform(at);
		sun.paint(graphics);
		g.dispose();
	}

}

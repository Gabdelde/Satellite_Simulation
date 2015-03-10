package com.gonz.upv.simulator.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JPanel;

import com.gonz.upv.simulator.sprites.Orbit;
import com.gonz.upv.simulator.sprites.PlanetEarth;
import com.gonz.upv.simulator.sprites.Satellite;

public class SimulationArea extends JPanel implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	// Game loop stuff
	private Thread animator;
	private final int FPS = 60;
	private final int TARGET_TIME = 1000 / FPS;	
	private boolean running;
	private boolean paused;
	
	// Graphics
	private Graphics2D dbg;
	public Image dbImage;
	
	// Sprites
	private PlanetEarth earth;
	private Orbit initialOrbit;
	private Satellite satellite;
	
	/** Creates a new JPanel where simulation is going to take place. */
	public SimulationArea() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.black);	
		dbImage = null;
		running = true;
		paused = true;		
		earth = new PlanetEarth(WIDTH/2, HEIGHT/2);
		initialOrbit = new Orbit(WIDTH/2, HEIGHT/2, 350, 360, 0);
		satellite = new Satellite(0,0,initialOrbit);
	}
	
	/** Wait for the JPanel to be added to the JFrame/JApplet before starting. */
	public void addNotify() {
		super.addNotify();
		init();
	}
	
	/** Initialize animator and starts update/render/paint loop. */
	private void init() {
		// Initialize celestial bodies
		earth.init();
		
		// Initialize and start the thread
		if(animator == null || !running) {
			animator = new Thread(this);
			animator.start();
		}
	}	
	
	@Override
	public void run() {		
		long elapsed, wait;		
		while(running) {			
			elapsed = System.nanoTime();			
			if(!paused) {
				update();
				render();
				paint();
			}			
			elapsed = System.nanoTime() - elapsed;
			wait = TARGET_TIME - elapsed / 1000000;			
			if(wait < 0) 
				wait = TARGET_TIME;			
			try {
				Thread.sleep(wait);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}		
	}
	
	public void update() {
		initialOrbit.update();
		earth.update();
		satellite.update();
	}
	
	public void render() 
	{
		if(dbImage == null) {	// Create the buffer
			dbImage = createVolatileImage(800, 600);
			if(dbImage == null){
				System.err.println("dbImage is null");
				return ;
			}
			else 
				dbg = (Graphics2D) dbImage.getGraphics();
		}		
		dbg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		dbg.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		dbg.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);		
		// Clear the background
		dbg.setColor(Color.black);
		dbg.fillRect(0, 0, WIDTH, HEIGHT);		
		// Draw orbits
		initialOrbit.render(dbg);
		// Draw the planet
		earth.render(dbg);
		// Draw the satellite
		satellite.render(dbg);		
		dbg.setColor(Color.WHITE);
		dbg.drawRect(1, 1, WIDTH-2, HEIGHT-2);
		
	}
	
	public void paint() {
		// Actively render the buffer image to the screen
		Graphics2D g;
		try  {			
			// Get the panel's graphic context
			g = (Graphics2D) this.getGraphics();					
			if (g != null && dbImage != null)
				g.drawImage(dbImage, 0, 0, null);			
			// Sync the display on some systems
			Toolkit.getDefaultToolkit().sync();					
			g.dispose();			
		} catch (Exception e) {
			System.err.println("Graphics context error: " + e);
			e.printStackTrace();
		}
	}
		
	public void pause() {	paused = true;	}
	public void resume() {	paused = false;	}
	public void stop() {	running = false;	}
	
}

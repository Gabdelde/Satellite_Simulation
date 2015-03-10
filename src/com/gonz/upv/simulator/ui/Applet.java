package com.gonz.upv.simulator.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JApplet;

public class Applet extends JApplet {

	private static final long serialVersionUID = 1L;
	public static final Dimension DIM = new Dimension(800, 600);
	
	private SimulationArea simArea;
	
	@Override
	public void init() {
		this.resize(DIM);
		this.simArea = new SimulationArea();
		this.add(simArea);
		this.simArea.resume();
		
		super.init();
	}
	
	@Override
	public void start() {
		simArea.addNotify();
		simArea.resume();
		
		super.start();
	}	
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		super.paint(g);
	}
 	
	@Override
	public void stop() {
		simArea.pause();
		
		super.stop();
	}
	
	@Override
	public void destroy() {
		simArea.stop();
		
		super.destroy();
	}

}

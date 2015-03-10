package com.gonz.upv.simulator.sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Satellite extends Sprite {

	private final int RADIUS = 15;
	private double speed;
	private double actualDegree;
	private Orbit orbit;
	
	public Satellite(int x, int y, Orbit init) {
		super(x, y);
		this.width = RADIUS;
		this.height = RADIUS;
		this.orbit = init;
		this.speed = 0.5;
		this.actualDegree = 360;
	}
	
	private void calculePosition() {
		actualDegree -= speed;
		if(actualDegree <= 0)
			actualDegree += 360;
		double rads = Math.toRadians(actualDegree);
		
		this.x = (int) (orbit.ellipse.getX() + (orbit.ellipse.getWidth()/2 * Math.cos(rads))); 
		this.y = (int) (orbit.ellipse.getY() + (orbit.ellipse.getHeight()/2 * Math.sin(rads))); 				
	}
	
	public void setOrbit(Orbit o) {
		this.orbit = o;
	}
	
	@Override
	public void update() {
		calculePosition();
	}

	@Override
	public void render(Graphics2D g) {
		AffineTransform oldTrans = g.getTransform();
		g.setColor(Color.GRAY);
		g.rotate(orbit.ellipse.getRotationRads(), 
				orbit.ellipse.getRotationPoint().getX(), 
				orbit.ellipse.getRotationPoint().getY());		
		g.fillOval(x-width/2, y-height/2, width, height);
		g.setTransform(oldTrans);
	}

}

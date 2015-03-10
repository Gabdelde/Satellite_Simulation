package com.gonz.upv.simulator.sprites;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Satellite extends Sprite {

	private final int RADIUS = 25;
	private double speed;
	private double actualDegree;
	private Orbit orbit;
	
	public Satellite(int x, int y, Orbit init) {
		super(x, y);
		this.width = RADIUS;
		this.height = RADIUS;
		this.orbit = init;
		this.speed = 1.0;
		this.actualDegree = 360;
	}
	
	private void calculePosition() {
		actualDegree -= speed;
		if(actualDegree <= 0)
			actualDegree += 360;
		double rads = Math.toRadians(actualDegree);
		Point2D orbitCenter = orbit.getCenter();
		
		this.x = (int) (orbitCenter.getX() + (orbit.getWidth()/2 * Math.cos(rads))); 
		this.y = (int) (orbitCenter.getY() + (orbit.getHeight()/2 * Math.sin(rads))); 				
	}
	
	@Override
	public void update() {
		calculePosition();
	}

	@Override
	public void render(Graphics2D g) {
		AffineTransform oldTrans = g.getTransform();
		g.setColor(Color.GRAY);
		g.rotate(orbit.getRotation(), orbit.getCenter().getX(), orbit.getCenter().getY());		
		g.fillOval(x-width/2, y-height/2, width, height);
		g.setTransform(oldTrans);
	}

}

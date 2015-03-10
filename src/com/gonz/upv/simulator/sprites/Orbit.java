package com.gonz.upv.simulator.sprites;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Orbit extends Sprite {
	
	private static BasicStroke stroke = new BasicStroke(2);
	
	private double rotation;
	private Ellipse2D ellipse;
	
	private Color color;
	
	public Orbit(int xx, int yy, int ww, int hh, double ang) {
		super(xx, yy, ww, hh);
		this.rotation = Math.toRadians(ang);		
		this.ellipse = new Ellipse2D.Double(x, y, width, height);
		AffineTransform.getRotateInstance(rotation)
			.createTransformedShape(ellipse);
		
		this.color = Color.white;
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics2D g) {
		AffineTransform oldTrans = g.getTransform();
		BasicStroke oldStk = (BasicStroke) g.getStroke();
		g.rotate(rotation, x + width / 2, y + height / 2);
		g.setColor(color);
		g.setStroke(stroke);
		g.draw(ellipse);
		
		g.setStroke(oldStk);
		g.setTransform(oldTrans);
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public Point2D getCenter() {
		double xx = x + width/2;
		double yy = y + height/2;
		Point2D p = new Point2D.Double(xx ,yy);
		return p;
	}
	
	public double getRotation() {
		return rotation;
	}

	
	
}

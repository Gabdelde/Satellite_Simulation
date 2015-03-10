package com.gonz.upv.simulator.sprites;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import com.gonz.geom.Ellipse;

public class Orbit extends Sprite {
	
	public Ellipse ellipse;	

	public Orbit(int xx, int yy, int ww, int hh, double ang) {
		super(xx, yy, ww, hh);
		ellipse = new Ellipse(xx, yy, ww, hh, ang);
		moveEllipse();
	}
	
	public void moveEllipse() {
		Point2D focus1 = ellipse.getLeftFocus();
		ellipse.setX(ellipse.getX() + Math.abs((ellipse.getX() - focus1.getX())));
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics2D g) {
		ellipse.render(g);
	}

}

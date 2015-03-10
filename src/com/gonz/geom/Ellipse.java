package com.gonz.geom;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Ellipse {

	// Coords
	private double x, y;
	
	private double width, height;
	
	// private Point2D focus1, focus2;
	
	private double rotation;
	private Point2D rotationPoint;
	
	private Color color;
	private static BasicStroke stroke = new BasicStroke(2);
	private Ellipse2D.Double drawing;
	
	public Ellipse(double x, double y) {
		this.x = x;
		this.y = y;
		this.width = 0.0;
		this.height = 0.0;
		this.rotation = 0.0;
		
		this.rotationPoint = new Point2D.Double(x, y);
		this.color = Color.WHITE;
		recalculateDrawing();
	}
	
	public Ellipse(double x, double y, double rotation) {
		this(x,y);
		this.rotation = Math.toRadians(rotation);
		recalculateDrawing();
	}
	
	public Ellipse(double x, double y, double w, double h) {
		this(x, y);
		this.width = w;
		this.height = h;
		recalculateDrawing();
	}
	
	public Ellipse(double x, double y, double w, double h, double rotation) {
		this(x, y, rotation);
		this.width = w;
		this.height = h;		
		recalculateDrawing();
	}
	
	
	// GETTERS AND SETTERS
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getRotation() {
		return Math.toDegrees(rotation);
	}
	
	public Point2D getRotationPoint() {
		return rotationPoint;
	}
		
	public Point2D getCenter() {
		return new Point2D.Double(x, y);
	}
	
	public Point2D getDrawingPoint() {
		return new Point2D.Double(x-width/2, y-height/2);
	}
	
	public void setX(double xx) {
		this.x = xx;
	}

	public void setY(double yy) {
		this.y = yy;
	}
	
	public void setWidth(double w) {
		this.width = w;
	}
	
	public void setHeight(double h) {
		this.height = h;
	}
	
	public void setRotation(double degrees) {
		this.rotation = Math.toRadians(degrees);
	}
		
	// OTHER METHODS
	
	public void recalculateDrawing() {
		Point2D upperleftPoint = getDrawingPoint();
		drawing = new Ellipse2D.Double(upperleftPoint.getX(), upperleftPoint.getY(), width, height); 
	}
	
	public void render(Graphics2D g) {
		AffineTransform oldTrans = g.getTransform();
		BasicStroke oldStk = (BasicStroke) g.getStroke();
		g.rotate(rotation, rotationPoint.getX(), rotationPoint.getY());
		g.setColor(color);
		g.setStroke(stroke);
		g.draw(drawing);		
		g.setStroke(oldStk);
		g.setTransform(oldTrans);
	}
	
	
	
	
}

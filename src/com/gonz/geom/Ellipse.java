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
		
		this.color = Color.WHITE;
		update();
	}
	
	public Ellipse(double x, double y, double rotation) {
		this(x,y);
		this.rotation = Math.toRadians(rotation);
		update();		
	}
	
	public Ellipse(double x, double y, double w, double h) {
		this(x, y);
		this.width = w;
		this.height = h;
		update();
	}
	
	public Ellipse(double x, double y, double w, double h, double rotation) {
		this(x, y, rotation);
		this.width = w;
		this.height = h;		
		update();
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
	
	public Point2D getLeftFocus() {
		double a = width/2.0, b = height/2.0;
		double c = Math.sqrt(Math.abs(a*a - b*b));
		return new Point2D.Double(x-c, y);
	}
	
	public Point2D getRightFocus() {
		double a = width/2.0, b = height/2.0;
		double c = Math.sqrt(Math.abs(a*a - b*b));
		return new Point2D.Double(x+c, y);
	}
	
	public double getRotation() {
		return Math.toDegrees(rotation);
	}
	
	public double getRotationRads() {
		return rotation;
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
		update();
	}

	public void setY(double yy) {
		this.y = yy;
		update();
	}
	
	public void setWidth(double w) {
		this.width = w;
		update();
	}
	
	public void setHeight(double h) {
		this.height = h;
		update();
	}
	
	public void setRotation(double degrees) {
		this.rotation = Math.toRadians(degrees);
		update();
	}
		
	// OTHER METHODS
	
	public void update() {
		calculateRotationPoint();
		recalculateDrawing();			
		// showDebugInfo();
	}
	
	public void calculateRotationPoint() {
		this.rotationPoint = getLeftFocus();
	}
	
	public void recalculateDrawing() {
		Point2D upperleftPoint = getDrawingPoint();
		drawing = new Ellipse2D.Double(upperleftPoint.getX(), upperleftPoint.getY(), width, height); 
	}
	
	public void showDebugInfo() {
		System.out.println("x: "+x);
		System.out.println("y: "+y);
		System.out.println("width: "+width);
		System.out.println("height: "+height);
		System.out.println("rotation: "+rotation);
		System.out.println("rotation point: "+rotationPoint.toString());
	}
	
	public void render(Graphics2D g) {
		AffineTransform oldTrans = g.getTransform();
		BasicStroke oldStk = (BasicStroke) g.getStroke();
		g.rotate(rotation, rotationPoint.getX(), rotationPoint.getY());
		g.setColor(color);
		g.setStroke(stroke);		
		g.draw(drawing);		
		g.setColor(Color.yellow);
		g.drawLine((int)x, (int)(y-height/2), (int)x, (int)(y+height/2));
		g.drawLine((int)(x-width/2), (int)y, (int)(x+width/2), (int)y);
		g.fillOval((int)getLeftFocus().getX()-5, (int)getLeftFocus().getY()-5, 10, 10);
		g.fillOval((int)getRightFocus().getX()-5, (int)getRightFocus().getY()-5, 10, 10);		
		g.setStroke(oldStk);
		g.setTransform(oldTrans);
	}
	
	
	
	
}

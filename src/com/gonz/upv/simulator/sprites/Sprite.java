package com.gonz.upv.simulator.sprites;

import java.awt.Graphics2D;

public abstract class Sprite {
	
	protected int x, y, width, height;
	
	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 0;
		this.height = 0;
	}
	
	public Sprite(int x, int y, int w, int h) {
		this.width = w;
		this.height = h;
		this.x = x - w/2;
		this.y = y - h/2;
	}
	
	public abstract void update();	
	public abstract void render(Graphics2D g);
		
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setX(int xx) {
		this.x = xx;
	}
	
	public void setY(int yy) {
		this.y = yy;
	}
	
}

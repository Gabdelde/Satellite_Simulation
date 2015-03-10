package com.gonz.upv.simulator.sprites;

import java.awt.Color;
import java.awt.Graphics2D;

public class PlanetEarth extends Sprite {

	// private BufferedImage texture;
	private final int RADIUS = 100;
	
	public PlanetEarth(int x, int y) {
		super(x, y);
		width = RADIUS;
		height = RADIUS;
		this.x = x-(width/2);
		this.y = y-(height/2);		
	}
	
	public void init() {
		//	loadTexture();
		
	}
	
	public void update() {
		
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillOval(x, y, width, height);
	}
	
	/*
	private void loadTexture() {		
		try{					
			texture = ImageIO.read(this.getClass().getResourceAsStream("/earth.jpg"));			
		}catch(IOException e){			
			System.err.println("[ENTITY]\t\tError loading earth planet image");
		}
	}
	*/
	
}

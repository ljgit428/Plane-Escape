package com.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class Shell extends GameObject{
	double degree;
	
	public Shell() {
		x = 100;
		y = 100;
		width = 10;
		height = 10;
		speed = 10;
		
		degree = Math.random()*Math.PI*2;
		// TODO Auto-generated constructor stub
	}

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		x += speed*Math.cos(degree);
		y -= speed*Math.sin(degree);
		g.fillOval((int)x, (int)y, width, height);
		
		if (x<0||x>Constant.GAME_WIDTH-width) {
			degree = Math.PI-degree;
		}
		
		if (y<30||y>Constant.GAME_HEIGHT-height) {
			degree = -degree;
		}
	}
	
}

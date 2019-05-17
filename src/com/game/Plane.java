package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{
	boolean left,up,right,down;
	boolean live = true;
	public void drawSelf(Graphics g) {
		
		if(live&&x>=5&&x<=Constant.GAME_WIDTH-Constant.PLANE_WIDTH&&
				y>=30&&y<=Constant.GAME_HEIGHT-Constant.PLANE_HEIGHT) {
			g.drawImage(img, (int)x,(int)y,50,50,null);

			if(left) {
				x-=speed;
			}
			if(right) {
				x+=speed;
			}
			if(up) {
				y-=speed;
			}
			if(down) {
				y+=speed;
			}
		} else if (!live) {
			return;
		} else {
			g.drawImage(img, (int)x,(int)y,50,50,null);
			
			if(x<5) {
				x++;
			}
			if(y<30) {
				y++;
			}
			if(x>Constant.GAME_WIDTH-Constant.PLANE_WIDTH) {
				x--;
			}
			if(y>Constant.GAME_HEIGHT-Constant.PLANE_HEIGHT) {
				y--;
			}
		}

	}

	public Plane(Image img, double x, double y) {
		super(img,x,y);
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = 10;
		this.width = Constant.PLANE_WIDTH;
		this.height = Constant.PLANE_HEIGHT;
	}

	public void addDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:
			right= true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		}
	}

	public void removeDirection(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		}
	}
}

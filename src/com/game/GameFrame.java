package com.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;

public class GameFrame extends Frame {

	Image planeImg = GameUtil.getImage("images/plane.png");
	Image bg = GameUtil.getImage("images/background.png");
	int planeX = 300;
	int planeY = 600;
	Plane plane = new Plane(planeImg,planeX,planeY);
	Shell shell = new Shell();
	Shell[] shells = new Shell[10];
	Explode explode;
	Date startTime = new Date();
	Date endTime;
	int period;

	public void paint(Graphics g) {
		Color c = g.getColor();
				
		g.drawImage(bg,0,0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT,null);
		plane.drawSelf(g);

		for(int i = 0; i<shells.length;i++) {
			shells[i].draw(g);

			boolean boom = shells[i].getRect().intersects(plane.getRect());

			if(boom) {
				plane.live = false;
				if(explode == null) {
					explode = new Explode(plane.x,plane.y);
					
					endTime = new Date();
					period = (int)(endTime.getTime()-startTime.getTime())/1000;
				}
				
				explode.draw(g);
			}
			
			if(!plane.live) {
				g.setColor(Color.red);
				Font f = new Font("Calibri",Font.BOLD, 30);
				g.setFont(f);
				g.drawString("Survival Time: "+period+"sec",(int)plane.x,(int)plane.y);
			}
		}
		
		g.setColor(c);
	}

	class PaintThread extends Thread{

		public void run() {
			while(true) {
				repaint();

				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			plane.removeDirection(e);
		}

	}

	public void launch() {
		this.setTitle("PlaneFight");
		this.setVisible(true);
		this.setSize(700,700);
		this.setLocation(500,0);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		new PaintThread().start();
		addKeyListener(new KeyMonitor());

		for(int i = 0; i<shells.length;i++) {
			shells[i] = new Shell();
		}
	}

	public static void main(String[] args) {
		GameFrame f = new GameFrame();
		f.launch();
	}

	private Image offScreenImage = null;

	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		}

		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0,0,null);
	}
}

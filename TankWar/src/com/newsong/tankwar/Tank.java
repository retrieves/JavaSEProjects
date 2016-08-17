package com.newsong.tankwar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
@SuppressWarnings("all")
public class Tank  {
	private int x = 300;
	private int y = 300;
	
	private Dir dir = Dir.STOP;
	public static final int rectW = 8;
	public static final int rectH = 40;
	public static final int rectMedW = 15;
	public static final int rectMedH = 30;
	public static final int r = 10;
	public static final int step  = 5;
	
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.cyan);
		
		if(dir == Dir.U||dir == Dir.D||dir==Dir.STOP){
			g.fill3DRect(x-rectW,y-rectH,rectW,rectH,false);
			g.fill3DRect(x+20, y, rectW, rectH,false);
			g.fill3DRect(x+5, y+5,rectMedW, rectMedH,false);
			g.setColor(Color.red);
			g.fillOval(x+10, y+15,r	,r);
		}else if(dir == Dir.L){
			g.fill3DRect(x-rectH,y,rectH,rectW,false);
			g.fill3DRect(x-rectH, y-20, rectH, rectW,false);
			g.fill3DRect(x-rectH+5, y-15,rectMedH, rectMedW,false);
			g.setColor(Color.red);
			g.fillOval(x+13, y-10,r	,r);
		}else{
			g.fill3DRect(x,y,rectH,rectW,false);
			g.fill3DRect(x, y-20, rectH, rectW,false);
			g.fill3DRect(x+5, y-15,rectMedH, rectMedW,false);
			g.setColor(Color.red);
			g.fillOval(x+10, y-20,r	,r);
		}
		
		switch(dir){
			case STOP:
			case U:g.drawLine(x+13, y, x+13, y+20);break;
			case D:g.drawLine(x+13, y+40, x+13, y+20);break;
			case L:g.drawLine(x-5, y-8, x+15, y-8);break;
		}
		g.setColor(c);
		move();
	}
	
	public void move(){
		switch(dir){
			case U:y-=step;break;
			case D:y+=step;break;
			case L:x-=step;break;
			case R:x+=step;break;
		}
		if(x < 5 )
			x = 5;
		if( x >= TankClient.w)
			x = TankClient.w;
		if(y < 0 )
			y = 0;
		if(y > TankClient.h)
			y = TankClient.h;
	}
	
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
			case KeyEvent.VK_UP: dir = Dir.U;
							    	break;
			case KeyEvent.VK_DOWN:dir = Dir.D;
									break;
			case KeyEvent.VK_LEFT:dir = Dir.L;
									break;
			case KeyEvent.VK_RIGHT:dir = Dir.R;
									break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		dir = Dir.STOP;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}
}

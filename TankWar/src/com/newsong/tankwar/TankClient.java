package com.newsong.tankwar;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

@SuppressWarnings("all")
public class TankClient extends JFrame {
	public static final int w = 800;
	public static final int h = 600;
	Tank t = new Tank();
	
	public TankClient() {
		this.addKeyListener(new Monitor());
		this.setResizable(false);
		this.setTitle("Ì¹¿Ë´óÕ½");
		this.setBounds(300,300,600,480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		new Thread(new PaintThread()).start();
	}
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 600, 480);
		t.draw(g);
		g.setColor(c);
	}
	
	
	class Monitor extends KeyAdapter{

		
		public void keyPressed(KeyEvent e) {
			t.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			t.keyReleased(e);
		}

	}
	
	class PaintThread implements Runnable{
		boolean isRunning = true;
		public void run() {
			while(isRunning){
				try{
					Thread.sleep(50);
					repaint();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
		
	}
	public static void main(String[] args) {
		TankClient tc = new TankClient();
	}
}

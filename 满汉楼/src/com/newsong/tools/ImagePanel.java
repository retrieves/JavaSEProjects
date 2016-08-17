package com.newsong.tools;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("all")
public class ImagePanel extends JPanel{
	Image img ;
	int w;
	int h;
	int x;
	int y;
	
	public ImagePanel(String filename,int x,int y, int w,int h) {
		try {
			img = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.w = w;
		this.h = h;
	}
	
	protected  void paintComponent(Graphics g){  
		Color c = g.getColor();
		//可以实现清屏
		super.paintComponent(g);
		g.drawImage(img, x, y,w,h, this);
		g.setColor(c);
	}
}

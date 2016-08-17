package com.newsong.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Demo06 {
	public static void main(String[] args) {
		PaintFrame frame = new PaintFrame();
	}
}
@SuppressWarnings("all")
class PaintFrame extends JFrame{
	
	public PaintFrame() {
		this.setTitle("≤‚ ‘œ‘ æÕº∆¨");
		this.setBounds(400, 500, 400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	public void paint(Graphics g) {
		Color c = g.getColor();
		Image img =Toolkit.getDefaultToolkit().getImage(Demo06.class.getClassLoader().getResource("images/1.png"));
		g.drawImage(img , 0, 0, 400, 300, this);
		/*g.setColor(Color.red);
		g.fillRect(0, 0, 400, 300);
		g.setColor(Color.BLACK);
		g.setFont(new Font("¡• È",Font.BOLD,30));
		g.drawString("hehhhh", 100, 100);*/
		g.setColor(c);
	}
}

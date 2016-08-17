package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 专门用来绘制背景的JFrame
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("all")
class PaintFrame extends JPanel {

	private Image img;
	private int x;
	private int y;
	private int width;
	private int height;

	public PaintFrame(String imgName, int x, int y, int width, int height) {
		try {
			img = ImageIO.read(new File(imgName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

	}

	// 在JPanel上绘制图片
	protected void paintComponent(Graphics g) {
		Color c = g.getColor();
		g.drawImage(img, x, y, width, height, this);
		g.setColor(c);
	}
}
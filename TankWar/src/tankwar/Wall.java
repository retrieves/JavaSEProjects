package tankwar;
import java.awt.*;
/**
 * 墙的类，用来表示墙的属性和方法
 * @author New Song
 *
 */

public class Wall {
	int x;
	int y;
	/**
	 * 将长宽不设为常量的原因是需要多个不同大小的墙
	 */
	int  w ;
	int  h ;
	TankClient tc = null;
	
	public Wall(int x,int y ,int w,int h,TankClient tc){
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.tc =tc;
	}

	/**
	 * 把墙自己画出来
	 * @param g
	 */
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.gray);
		g.fillRect(x, y, w, h);
		g.setColor(c);
	}
	
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}
}

package tankwar;
import java.awt.*;
/**
 * ǽ���࣬������ʾǽ�����Ժͷ���
 * @author New Song
 *
 */

public class Wall {
	int x;
	int y;
	/**
	 * ��������Ϊ������ԭ������Ҫ�����ͬ��С��ǽ
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
	 * ��ǽ�Լ�������
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

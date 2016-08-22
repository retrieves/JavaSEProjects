package tankwar;
import java.awt.*;
/**
 * 爆炸类，用于模拟爆炸效果
 * @author Administrator
 *
 */
public class Explode {
	private int x;
	private int y;
	/**
	 * 该数组的作用是每次重绘是画一个不同半径的圆，连起来就是一个小圆膨胀到大圆，后缩回小圆的模拟的爆炸
	 */
	private int [] diaMeter = {4,7,12,18,26,32,49,30,14,5};
	/**
	 * 用一个变量来标示爆炸进行到了哪一步
	 */
	private int step = 0;
	private boolean live = true;
	private TankClient tc = null; //拥有TankClient的引用是为了从容器中去除当前对象（爆炸）
	public Explode (int x,int y,TankClient tc){
		this.x= x;
		this.y = y;
		this.tc =tc;
	}
	/**
	 * 把爆炸给画出来
	 * 先判断是否死亡，如死亡那么从容器中去除
	 * 如果已经达到一次爆炸循环的最后一次，那么步数清零，重新开始
	 * @param g
	 */
	public void draw(Graphics g){
		if(!live){
			tc.explodes.remove(this); //死掉之后从容器中取出，参照子弹类的做法，用来节省内存空间
			return ;
		}
		if(step == diaMeter.length){
			live = false;
			step = 0;
			return;
		}
		Color c = g.getColor();
		g.setColor(Color.black);
		g.fillOval(x, y, diaMeter[step],  diaMeter[step]);
		step++;
		g.setColor(c);
	}
}

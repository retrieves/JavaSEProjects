package tankwar;
import java.awt.*;

/**
 * 血块类，用来表示血块的属性和方法
 * @author New Song
 *
 */
public class Blood {
	private int x ;
	private int y;
	private boolean live = true;
	/**
	 * 血块会固定地刷新在某几个固定的点处
	 * 用二维数组来表示一个点的集合
	 */
	private int [][] position = {
			{150,400},{200,200},{300,300},{400,400},{500,500},{700,550}
	};
	/**
	 * 用于表示一次循环的进度，当这些点都刷新过后重新从头刷新
	 */
	int step = 0;
	/**
	 * 用来控制每次血块刷新存在的时间和每两次血块刷新之间的时间间隔
	 * 记录屏幕刷新次数
	 * 时间的间隔都是用屏幕刷新次数来表示
	 */
	int count = 0;
	/**
	 * 血块的固定的大小
	 */
	public static final int w = 15;
	public static final int h = 15;
	TankClient tc = null;
	/**
	 * 构造方法是使血块出现在第一个点的位置
	 */
	public Blood(){
		x = position[0][0];
		y = position[0][0];
		
	}
	/**
	 * 把血块自己画出来
	 * 如果死亡，那么屏幕刷新300次之后就设置为复活，重新进入循环，开始刷新
	 * 每次刷新的间隔是100次刷新，每次刷新进入下一个点的位置
	 * @param g
	 */
	public void draw(Graphics g){
		if(!live ) {
			count++;
			if(count >= 300){
				live  = true;
				move();
				count = 0;
			}			
			return;
		}
		
		if(count < 100){
			Color c = g.getColor();
			g.setColor(Color.red);
			g.fillRect(x,y, w, h);		
			g.setColor(c);
		}
		else{
			move();
			count = 0;
		}			
		count++;
	}
	/**
	 * 设置每次刷新后进入下一个点的位置
	 */
	public void move(){
		step ++;
		if(step == position.length )
			step = 0;	
		x = position[step][0];
		y = position[step][1];
	}
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	public Rectangle getRect(){
		return new Rectangle(position[step][0], position[step][1],w,h);
	}

}

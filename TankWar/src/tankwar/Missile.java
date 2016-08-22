package tankwar;
import java.awt.*;
import java.util.*;
/**
 * 子弹类，表示子弹的属性和方法
 * @author New Song
 *
 */

public class Missile {

	private int x;
	private int y;
	private Dir dir = Dir.STOP;
	private boolean live = true;
	/**
	 * 区别子弹是敌方发射的还是我方发射的，因为要判断子弹击中是不同阵营的才有效
	 */
	private boolean good = true;
	private Color cMissile = null;
	public static final int w = 12;
	public static final int h = 12;
	public  static final int XSPEED = 10;
	public  static final int YSPEED = 10;
	TankClient tc = null;
	
	
	public Missile(int x, int y, Dir dir,boolean good) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.good = good;
		/**
		 * 根据敌我双方来设置不同的子弹颜色
		 */
		if(good)
			cMissile = Color.pink;
		else
			cMissile = Color.gray;
	}
	
	public Missile(int x,int y,Dir dir ,boolean good,TankClient tc){
		this(x,y,dir,good);
		this.tc =tc;
	}
	/**
	 * 重绘子弹，用一个小圆来表示
	 * 注意要先判断子弹是否活着（飞出窗口即视为死亡，不需要再画出来了）
	 * 死掉之后就从容器中去除，节约内存空间,并不再重绘
	 * @param g
	 */
	public void draw(Graphics g){
		if(!live){  //每次重绘之前先判断子弹是否已经死亡，如果死亡（可能是击中坦克，也可能是飞出窗口），
					//首先将其从容器中移除，然后跳过其重绘的过程
			tc.missiles.remove(this);
			return ;
		}
		Color c = g.getColor();		
		g.setColor(cMissile);
		g.fillOval(x, y, w,h);
		g.setColor(c);	
		move();
	}
	/**
	 * 子弹会根据其方向飞行
	 * 每次移动要判断是否飞出窗口，如果飞出那么视为死亡
	 */
	public void move(){
			switch(dir){
				case U: 	
					y-=YSPEED;
					break;
				case D: 	
					y+=YSPEED;
					break;
				case L: 	
					x-=XSPEED;
					break;
				case R: 	
					x+=XSPEED;
					break;
				case LU: 	
					y-=YSPEED;
					x-=XSPEED;
					break;
				case RU: 	
					y-=YSPEED;
					x+=XSPEED;
					break;
				case LD: 	
					x-=XSPEED;
					y+=YSPEED;
					break;
				case RD: 	
					x+=XSPEED;
					y+=YSPEED;
					break;
				case STOP :break;
			}
			
			if(x < 0 ||y < 0 || x > TankClient.w || y > TankClient.h ){
				live = false;
			}
				
	}
	
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}
	/**
	 * 判断一个子弹与一辆坦克是否碰撞
	 * 前提是子弹和坦克都活着，并且只有属于不同阵营才有效
	 * 同时击中敌方和我方后果不同，敌方死亡，我方减血20，如果为0则也死亡
	 * 每次击中后要模拟一次爆炸，爆炸的位置根据碰撞时子弹的位置来确定			
	 * @param t 坦克
	 */
	public void hitTank(Tank t){ //判断一个特定的子弹是否击中坦克，不能在Tank类加入该方法，因为要判断每一发子弹和坦克的位置关系，否则取出子弹的位置会很麻烦。
		
		if( t.isLive() &&this.live && this.getRect().intersects(t.getRect())  && this.good != t.isGood() ){ //从面向对象思维而言，是子弹击中坦克；所有的判断是否重合的方法都需要参数，调用的是移动的对象，被调用的参数等待被碰撞的。
			if(t.isGood()){																				  //要求坦克和子弹都是活着的，否则就不必去判断是否相撞
				t.setHp(t.getHp() -20);
				if(t.getHp() <= 0 )
					t.setLive(false);
			}
			else
				t.setLive(false);//如果不判断坦克是否死亡，那么其他子弹飞到已经死亡的坦克的位置上仍然会消失					
			this.live = false;										//如果子弹和坦克不是同一方的才可以打死
			tc.explodes.add(new Explode(x,y, tc));			
		}	 		
	}
	/**
	 * 判断一颗子弹与坦克容器是否相撞，主要是我方坦克发射的子弹与所有的敌方坦克遍历判断
	 * 这样写的好处时在paint方法中就不需要写循环的嵌套了，直接调用该方法即可，比较清晰
	 * @param enemyTanks 坦克数组
	 */
	public void hitTanks(ArrayList<Tank> enemyTanks){ //这样的话就不必在TankClient中使用嵌套循环了，直接调用该方法即可
		for(int i = 0; i < enemyTanks.size() ;i++){
			hitTank(enemyTanks.get(i));
		}
	}
	/**
	 * 子弹与墙的碰撞检测，如果子弹撞到墙，那么子弹消失
	 * @param wall
	 */
	public void hitWall(Wall wall){
		if(this.live && this.getRect().intersects(wall.getRect())){
			this.live  = false;			
		}
	}
}


package tankwar;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * 坦克类，用于表示坦克的属性和方法
 * @author New Song
 *
 */

public class Tank {
	
	private int x ; 
	private int y ;
	private int hp = 100;
	/**
	 * 用于区分我方和地方
	 */
	private boolean good = true;	
	private boolean live = true;
	/**
	 * 用于保存上一次移动的位置X，方便在撞墙之后能够返回上一次的位置
	 */
	private int oldX ;
	/**
	 * 用于保存上一次移动的位置Y，方便在撞墙之后能够返回上一次的位置
	 */
	private int oldY ;
	/**
	 * 坦克的长度 静态变量
	 */
	public static final int w = 30;
	/**
	 * 坦克的宽度 静态变量
	 */
	public static final int h = 30;
	/**
	 * 坦克东西向移动速度，也就是每次重绘时沿着坦克方向的位移
	 */
	public static final int XSPEED = 5;
	/**
	 * 坦克南北向移动速度，也就是每次重绘时沿着坦克方向的位移
	 */
	public static final int YSPEED = 5;
	/**
	 * 随机数生成器，用于完成各种各样的随机事件
	 * 设置为静态，方便其他类进行调用
	 */
	public static final Random r = new Random();//随机数生成器
	/**
	 * 用于敌方坦克在重绘随机个次数后改变其方向，显示其智能
	 */
	int step = r.nextInt(12)+3;//随机移动3~14步
	/**
	 * 表示坦克的颜色，根据我方还是地方会有不同的颜色
	 */
	Color cTank = null;//Color.cyan;
	/**
	 * 表示四个方向，相互组合之后可以用来模拟8个不同的方向
	 */
	private boolean bR = false;
	private boolean bL = false;
	private boolean bU = false;
	private boolean bD = false;
	/**
	 * 用于表示坦克的方向(移动方向)
	 */
	private Dir dir = Dir.STOP;
	/**
	 * 用于表示坦克的炮筒方向
	 * 子弹射出时的方向与炮筒方向一致
	 * 对于我方而言是有用的，因为如果坦克的方向为STOP，那么子弹的方向也是STOP
	 * 为了避免这种情况，只会选取非STOP的坦克移动方向作为炮筒方向
	 * 对于敌方而言是无用的，因为敌方坦克的方向不会为STOP
	 */
	private Dir barrelDir = Dir.U;
	/**
	 * 为了保持一致，每个构成TankClient主类的各个类都拥有一个TankClient的引用
	 * 对于Tank类而言，可以用来从enemyTanks中删除敌方坦克，向missiles添加子弹以及传入子弹的构造方法等
	 */
	TankClient tc = null;
	BloodBar bb = new BloodBar();

	/**
	 * 重载的构造方法，可能根据不同的需求调用不同的构造方法
	 * 不需要将每个参数都单独写一遍，其他的构造方法对于相同的参数而言使用this方法即可
	 * @param x
	 * @param y
	 * @param good
	 */
	
	public Tank(int x,int y,boolean good){ //重载的构造方法，可以适用于不同的情况，有的时候需要传另外一个参数，有的时候则不需要，重载可以满足不同需要。
		this.x = x;
		this.y = y;
		this.good = good;
		this.oldX = x;
		this.oldY = y;
		if(good)
			cTank = Color.cyan;
		else
			cTank = Color.green;
	}	
	
	public Tank(int x,int y,boolean good,TankClient tc){
		this(x,y,good);
		this.tc =tc ;
	}
	
	public Tank(int x,int y,boolean good,Dir dir,TankClient tc){
		this(x,y,good);
		this.dir = dir;
		this.tc =tc ;
	}
	

	/**
	 * 对于每个组件而言，绘图时使用的是draw而非paint方法
	 * 先判断坦克是否已经死亡，死亡不再画并且从enemyTanks中取出
	 * 判断敌我方，如果是我方，那么画出血条（调用BloodBar类的draw方法，组件嵌套组件就是draw的嵌套调用）
	 * 除了画出坦克外，还有炮筒，根据炮筒方向不同，其形状也是不同的
	 * 每次重绘坦克都调用move方法，保证坦克的持续移动
	 * @param g
	 */
	public void draw(Graphics g){
		if(!live) {
			if(!good)
				tc.enemyTanks.remove(this);
			return ;
		}
		if(good)
			bb.draw(g);
		Color c = g.getColor();		
		g.setColor(cTank);
		g.fillOval(x, y, w, h);
		g.setColor(Color.red);
		switch(barrelDir){
			case U: 	
				g.drawLine(x+w/2, y, x+w/2, y+h/2);
				break;
			case D: 	
				g.drawLine(x+w/2, y+h/2, x+w/2, y+h);
				break;
			case L: 	
				g.drawLine(x, y+h/2, x+w/2, y+h/2);
				break;
			case R: 	
				g.drawLine(x+w/2, y+h/2, x+w, y+h/2);
				break;
			case LU: 	
				g.drawLine(x, y, x+w/2, y+h/2);
				break;
			case RU: 	
				g.drawLine(x+w/2, y+h/2, x+w, y);
				break;
			case LD: 	
				g.drawLine(x, y+h, x+w/2, y+h/2);
				break;
			case RD: 	
				g.drawLine(x+w/2, y+h/2, x+w, y+h);
				break;
			default:
				break;
		}		
		g.setColor(c);		
		move();
	}
	/**
	 * 先将非STOP的移动方向赋给炮筒方向，无论敌我方
	 * 为了保存上次的位置，要先保存再改变位置
	 * 为了不超出窗口界限，当位置超出时重新设为窗口边缘
	 * 同时完成敌方坦克的随机方向和移动的随机步数
	 * 为了避免敌方坦克的子弹过于密集，设置随机数满足一个条件后才发射子弹 
	 */
	public void move(){
		if(dir != Dir.STOP)
			barrelDir = dir;
		oldX = x; //先记录上一次位置，再移动
		oldY = y;
/*		if(good){
			switch(dir){
				case U: 	
					y-=2*YSPEED;
					break;
				case D: 	
					y+=2*YSPEED;
					break;
				case L: 	
					x-=2*XSPEED;
					break;
				case R: 	
					x+=2*XSPEED;
					break;
				case LU: 	
					y-=2*YSPEED;
					x-=2*XSPEED;
					break;
				case RU: 	
					y-=2*YSPEED;
					x+=2*XSPEED;
					break;
				case LD: 	
					x-=2*XSPEED;
					y+=2*YSPEED;
					break;
				case RD: 	
					x+=2*XSPEED;
					y+=2*YSPEED;
					break;
				case STOP :break;
			}
		}else{
*/
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
//		}
		
		if(x < 0) x = 0; //控制位置
		if(y < 30) y = 30;
		if(x + Tank.w > TankClient.w) x = TankClient.w - Tank.w;
		if(y + Tank.h > TankClient.h) y = TankClient.h - Tank.h;
		
		if(!good){ //实现方向和移动步数的随机
			if(step == 0){
				dir = randomDir();
				if(r.nextInt(40) > 20)
					fire();
				step = r.nextInt(12)+3;
				
			}
			step --;
			
		}
	}
	/**
	 * 该静态方法用于实现敌方坦克方向的随机，将Dir枚举常量放到数组中，随机一个下标值来随机方向
	 * @return 返回随机的方向
	 */
	public static Dir  randomDir() {
		Dir []dirs = Dir.values();
		int randomIndex = r.nextInt(dirs.length);	
		Dir dir = dirs[randomIndex];
		return dir;
	}
	/**
	 * 每次键盘事件发生之后都要重新设置坦克移动方向
	 * 根据4种方向的组合来模拟8个方向
	 */
	public void locateDirection(){
		

		if(bU && !bD && ! bL && !bR) dir = Dir.U;
		else if(!bU && bD && ! bL && !bR) dir = Dir.D;
		else if(!bU && !bD &&  bL && !bR) dir = Dir.L;
		else if(!bU && !bD && ! bL && bR) dir = Dir.R;
		else if(bU && !bD &&  bL && !bR) dir =  Dir.LU;
		else if(bU && !bD && ! bL && bR) dir =  Dir.RU;
		else if(!bU && bD &&  bL && !bR) dir = Dir.LD;
		else if(!bU && bD &&  ! bL && bR) dir = Dir.RD;
		else if(!bU && !bD && ! bL && !bR) dir = Dir.STOP;
	}



	/**
	 * 监听了上下左右和Z键
	 * Z键用于完成一个我方坦克瞬移的功能，比较牛逼
	 * @param e 键盘事件
	 */
	public void keyPressed(KeyEvent e) { //键盘的按键在按下的瞬间会有时差(跟键长有关系),不可能是你同时按下ABCD时,
										 //键盘传出的值就是ABCD 有可能是ACBD ,ADBC等等所以只要判断你要的几个键是否被按下就行		
		switch(e.getKeyCode()){			//从面向对象而言，是坦克打子弹，而不是子弹打子弹
		case KeyEvent.VK_UP: 	
			bU= true;
			break;
		case KeyEvent.VK_DOWN: 	
			bD= true;
			break;
		case KeyEvent.VK_LEFT: 	
			bL= true;
			break;
		case KeyEvent.VK_RIGHT: 
			bR= true;
			break;
		case KeyEvent.VK_Z:
			TelePort();
			break;
		}
		locateDirection();

	}	
	

	/**
	 * 方向应该在键松开后置为false，否则就会保持之前的方向
	 * 监听X，用于发射子弹，放在松开按键触发是为了限制我方子弹在按住X时过于密集的问题，因此按一次X就只会发射一发子弹
	 * 监听C，用于发射超级炮弹
	 * @param e 键盘事件
	 */
	public void keyReleased(KeyEvent e){
		
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP: 	
			bU= false;
			break;
		case KeyEvent.VK_DOWN: 	
			bD= false;
			break;
		case KeyEvent.VK_LEFT: 	
			bL= false;
			break;
		case KeyEvent.VK_RIGHT: 
			bR= false;
			break;
		case KeyEvent.VK_X:
			fire();
			break;
		case KeyEvent.VK_C:
			superFire();
			break;
		
		}
		locateDirection();
		
	}
	/**
	 * 用于发射炮弹，每次按X时触发
	 * 如果坦克已死亡则不再发射子弹
	 * 发射炮弹就是new 一发发的子弹然后装入容器中
	 * 注意炮弹的坐标要求从坦克的正中发射出去
	 */
	public void fire(){ //如果在子弹类中new 对象 加到数组中很不方便，一是需要单独写接听方法，另一个是需要获得Tank类的成员变量
		if(!live ) return ;
		int xMissile = x + Tank.w/2- Missile.w/2;//确定子弹的位置
		int yMissile = y + Tank.h/2- Missile.h/2;
		
		Missile m = new Missile(xMissile,yMissile,barrelDir,this.good,this.tc);//而在Tank类引用其成员变量很方便
		tc.missiles.add(m);												 //将Tank中拥有的tc作为Missile的参数传递过去
	}
	/**
	 * 重载的fire方法，用于更方便地实现superFire()方法
	 * @param dire 炮弹的方向
	 */
	public void fire(Dir dire){ //重载fire方法
		if(!live ) return ;
		int xMissile = x + Tank.w/2- Missile.w/2;//确定子弹的位置
		int yMissile = y + Tank.h/2- Missile.h/2;
		
		Missile m = new Missile(xMissile,yMissile,dire,this.good,this.tc);//而在Tank类引用其成员变量很方便
		tc.missiles.add(m);	
	}
	/**
	 * 用于发射超级炮弹，向四周8个方向发射，调用8次重载的fire方法(面向对象的方法重用)
	 */
	public void superFire(){ //，调用fire的第二个重载方法，不必将相同代码再写一遍。向8个方向发射炮弹
		Dir [] dirs = Dir.values();
		for(int i = 0; i< 8;i++){
			fire(dirs[i]);										 
		}
	}
	/**
	 * 瞬移功能
	 */
	private void TelePort() {
		x = r.nextInt(800);
		y = r.nextInt(600);
	}
	/**
	 * 坦克和墙的碰撞检测，如果坦克碰到墙，那么坦克不再移动，不可穿墙
	 * 所有的碰撞方法主调的是移动的事物，参数是等待被碰撞的事物
	 * 并且都要先判断这两个事物是否都活着
	 * 都适用矩形的相交来判断是否碰撞
	 * 
	 * @param wall 墙
	 */
	public void collidesWithWall(Wall wall){
		if(live && this.getRect().intersects(wall.getRect()) ){
			stay();		//	this.dir = Dir.STOP; 如果STOP的话，那么下次随机移动时会发现仍然与墙相撞，会再次设置为STOP
		}
	}
	/**
	 * 敌我方坦克不可重合
	 * @param t 坦克
	 */
	public void collidesWithTank(Tank t){
		if(live && t.isLive() && this.getRect().intersects(t.getRect())){
			stay();
		//	t.stay();
		}
	}
	/**
	 * 我方坦克和敌方所有坦克/敌方的一个坦克和地方所有坦克的碰撞检测
	 * 注意要判断是否是同一辆坦克
	 * 重用collidesWithTank方法
	 * @param tanks 坦克的容器
	 */
	public void collidesWithTanks(ArrayList <Tank>tanks){
		for(int i = 0 ;i<tanks.size() ;i++){
			if(!this.equals(tanks.get(i)))
				collidesWithTank(tanks.get(i));
		}
	}
	/**
	 * 当碰撞后坦克就回到上一次移动的位置
	 */
	public void stay(){
		x = oldX;
		y = oldY;
	}
	/**
	 * 我方坦克和血块的碰撞检测
	 * @param blood 血块
	 */
	public void eatBlood(Blood blood){
		if(live && blood.isLive() && this.getRect().intersects(blood.getRect())){
			hp = 100;
			blood.setLive(false);
		}
	}
	/**
	 * 因为血条是属于坦克的，所以设置为坦克的内部类
	 * 只需要一个把自己画出来的方法即可，参数使用坦克的位置参数
	 * 用两个矩形来模拟血条，一个边框，一个实心，实心的长度根据hp的多少而变化
	 * @author New Song
	 *
	 */
	class BloodBar {

		public void draw(Graphics g){
			if(!live) return ;
			Color c = g.getColor();
			g.setColor(Color.red);
			g.drawRect(x, y-10, w, 10);			
			g.fillRect(x, y-10, w*hp/100, 10);
			g.setColor(c);
		}		
	}
	
	public Rectangle getRect(){
		return new Rectangle(x,y,w,h);
	}
	
	public boolean isGood() {
		return good;
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	
	public Dir getDir() {
		return dir;
	}
	
	public void setDir(Dir dir){
		this.dir = dir;
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
}

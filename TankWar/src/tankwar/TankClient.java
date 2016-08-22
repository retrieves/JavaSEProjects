package tankwar;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.util.*;
/**
 * 坦克客户端主界面类
 * @author New Song
 *
 */

public class TankClient extends Frame{
	

	Tank myTank = new Tank(Tank.r.nextInt(800),Tank.r.nextInt(600),true,this);
	Wall wall1  = new Wall(100,100,15,400,this);
	Wall wall2  = new Wall(400,100,350,15,this);
	Blood blood = new Blood();
	public ArrayList <Tank>enemyTanks = new ArrayList<Tank>(); 	
	public ArrayList<Missile> missiles = new ArrayList<Missile>();
	public ArrayList<Explode> explodes = new  ArrayList<Explode>();
	/**
	 * 界面的宽度
	 */
	public static final int w = 800;//常量定义为static final
	/**
	 * 界面的高度
	 */
	public static final int h = 600;
	/**
	 * 用于实现双缓冲机制，是一个Image对象
	 */
	Image OffScreenImage = null;
	/**
	 * 坦克客户端的构造方法，包括窗口标题、窗口大小、窗口的监听、大小改变、添加键盘监视器、第一次创建敌方坦克等
	 */
	public TankClient(){
		super("TankWar F1:开始 F2:重新开始 X:攻击 C:超级导弹 Z:瞬移 Welcome to TankWar!");
		this.setBounds(400,300,w,h);
		this.addWindowListener(new WindowAdapter(){		
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(0);
			}			
		});
		this.setResizable(false);
		this.addKeyListener(new KeyMonitor());
		this.setVisible(true);		
		createEnemyTanks(10);
	}
	/**
	 * 用于画出所有的组件和窗口
	 * 画出界面背景，子弹，墙，我方坦克，敌方坦克（数目不足时）、爆炸 、血条以及 子弹数目和敌方数目
	 * 注意包含了所有的碰撞检测
	 */
	public void paint(Graphics g){
		
		Color c = g.getColor();
		g.setColor(Color.yellow);
		g.fillRect(0, 0,w, h);
		
		if(enemyTanks.size() < 2)
			createEnemyTanks(10);
		
		for(int i = 0;i <missiles.size();i++){	//画出子弹		
			Missile m = missiles.get(i);
			m.hitTanks(enemyTanks);
			m.hitTank(myTank); //将自己从无敌状态改为被击中即死的状态
			m.hitWall(wall1);
			m.hitWall(wall2);
			m.draw(g);			
		}
		blood.draw(g);
		wall1.draw(g);	
		wall2.draw(g);
		myTank.draw(g);	//自己的东西自己画，应该在Tank了中定义draw方法将坦克本身画出来
		myTank.collidesWithWall(wall1);
		myTank.collidesWithWall(wall2);
		myTank.collidesWithTanks(enemyTanks);
		myTank.eatBlood(blood);
		
		for(int i = 0;i < enemyTanks.size() ;i++){//画出敌方坦克
			
			Tank enemy = enemyTanks.get(i);
/*			if(!myTank.isLive()) 
				enemy.setDir(Dir.STOP);
			else if(enemy.getX() >= myTank.getX() &&enemy.getY() >= myTank.getY()) 
				enemy.setDir(Dir.LU);
			else if(enemy.getX() >= myTank.getX() &&enemy.getY() <= myTank.getY()) 
				enemy.setDir(Dir.LD);
			else if(enemy.getX() <= myTank.getX() &&enemy.getY() >= myTank.getY()) 
				enemy.setDir(Dir.RU);
			else if(enemy.getX() <= myTank.getX() &&enemy.getY() <= myTank.getY()) 
				enemy.setDir(Dir.RD);
*/
			enemy.draw(g);
			enemy.collidesWithWall(wall1);
			enemy.collidesWithWall(wall2);
			enemy.collidesWithTank(myTank);
			enemy.collidesWithTanks(enemyTanks);
		}
		
		for(int i = 0; i < explodes.size(); i++ ){//画出爆炸
			explodes.get(i).draw(g);
		}
		
		g.setColor(Color.red);
		g.drawString("Bullet:"+missiles.size(), 40, 40);
		g.drawString("Enemys:"+enemyTanks.size(), 40, 60);
		
		g.setColor(c);

	}
	/**
	 * 用于实现双缓冲技术，避免屏幕闪烁
	 */
	public void update(Graphics g){//双缓冲技术，避免屏幕闪烁
		if(OffScreenImage == null)
			OffScreenImage = this.createImage(w, h);
		Graphics gOff = OffScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(OffScreenImage, 0, 0, null);
	}
	/**
	 * 所有的游戏界面的重绘都由一个单独的内部的线程类来完成，包括一个死循环，每隔一段时间就调用repaint方法
	 * @author New song 
	 *
	 */
	private class PaintThread implements Runnable{

		public void run() {
			while(true){
				repaint();
				try{
					Thread.sleep(50);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}		  
	}
	/**
	 * 监视器使用内部类来完成，main方法所在类来拥有此内部类，其他的类可以重写监听的方法，然后添加到主类中
	 * 除了上下左右由Tank类的监听方法实现外，主类提供F1开始游戏，F2重新开始游戏的监听
	 * @author New song
	 *
	 */
	class KeyMonitor extends KeyAdapter{
		
		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
			if(e.getKeyCode() == KeyEvent.VK_F1) //F1 启动线程，开始刷新
				gameStart();
			else if(e.getKeyCode() == KeyEvent.VK_F2)
				rePlay();
		}
		
		public void keyReleased(KeyEvent e){
			myTank.keyReleased(e);
		}
			
	}
	/**
	 * 用于启动线程，开始界面的重绘，开始后界面由静态转为动态
	 */
	public  void gameStart() {
		new Thread(new PaintThread()).start();
	}
	/**
	 * 用于重新开始游戏，设置我方坦克血量回满，live为true
	 */
	public  void rePlay() {
		myTank.setHp(100);
		myTank.setLive(true);
		
	}
	/**
	 * 创建坦克的一般方法，放在paint方法中，每次重绘如果敌方坦克数量不足则创建坦克
	 * 敌方坦克的位置都是随机的，缺点是可能与墙的位置重叠，造成卡在墙里的后果
	 * 敌方坦克的初始方向指向我方坦克，有一定的智能
	 * @param sum 重新刷新的坦克数量
	 */
	public void createEnemyTanks(int sum) {
		for(int i = 1; i<= sum; i++){
			enemyTanks.add(new Tank((int)(Math.random() * 800),(int)(Math.random() * 600),false,myTank.getDir(),this));
		}
	} 
	/**
	 * 用于启动整个游戏
	 * @param args
	 */
	public static void main(String []args){
		new TankClient();				
	}
}

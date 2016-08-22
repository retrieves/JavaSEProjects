package tankwar;
import java.awt.*;
import java.awt.event.*;
import java.awt.Image;
import java.util.*;
/**
 * ̹�˿ͻ�����������
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
	 * ����Ŀ��
	 */
	public static final int w = 800;//��������Ϊstatic final
	/**
	 * ����ĸ߶�
	 */
	public static final int h = 600;
	/**
	 * ����ʵ��˫������ƣ���һ��Image����
	 */
	Image OffScreenImage = null;
	/**
	 * ̹�˿ͻ��˵Ĺ��췽�����������ڱ��⡢���ڴ�С�����ڵļ�������С�ı䡢��Ӽ��̼���������һ�δ����з�̹�˵�
	 */
	public TankClient(){
		super("TankWar F1:��ʼ F2:���¿�ʼ X:���� C:�������� Z:˲�� Welcome to TankWar!");
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
	 * ���ڻ������е�����ʹ���
	 * �������汳�����ӵ���ǽ���ҷ�̹�ˣ��з�̹�ˣ���Ŀ����ʱ������ը ��Ѫ���Լ� �ӵ���Ŀ�͵з���Ŀ
	 * ע����������е���ײ���
	 */
	public void paint(Graphics g){
		
		Color c = g.getColor();
		g.setColor(Color.yellow);
		g.fillRect(0, 0,w, h);
		
		if(enemyTanks.size() < 2)
			createEnemyTanks(10);
		
		for(int i = 0;i <missiles.size();i++){	//�����ӵ�		
			Missile m = missiles.get(i);
			m.hitTanks(enemyTanks);
			m.hitTank(myTank); //���Լ����޵�״̬��Ϊ�����м�����״̬
			m.hitWall(wall1);
			m.hitWall(wall2);
			m.draw(g);			
		}
		blood.draw(g);
		wall1.draw(g);	
		wall2.draw(g);
		myTank.draw(g);	//�Լ��Ķ����Լ�����Ӧ����Tank���ж���draw������̹�˱�������
		myTank.collidesWithWall(wall1);
		myTank.collidesWithWall(wall2);
		myTank.collidesWithTanks(enemyTanks);
		myTank.eatBlood(blood);
		
		for(int i = 0;i < enemyTanks.size() ;i++){//�����з�̹��
			
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
		
		for(int i = 0; i < explodes.size(); i++ ){//������ը
			explodes.get(i).draw(g);
		}
		
		g.setColor(Color.red);
		g.drawString("Bullet:"+missiles.size(), 40, 40);
		g.drawString("Enemys:"+enemyTanks.size(), 40, 60);
		
		g.setColor(c);

	}
	/**
	 * ����ʵ��˫���弼����������Ļ��˸
	 */
	public void update(Graphics g){//˫���弼����������Ļ��˸
		if(OffScreenImage == null)
			OffScreenImage = this.createImage(w, h);
		Graphics gOff = OffScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(OffScreenImage, 0, 0, null);
	}
	/**
	 * ���е���Ϸ������ػ涼��һ���������ڲ����߳�������ɣ�����һ����ѭ����ÿ��һ��ʱ��͵���repaint����
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
	 * ������ʹ���ڲ�������ɣ�main������������ӵ�д��ڲ��࣬�������������д�����ķ�����Ȼ����ӵ�������
	 * ��������������Tank��ļ�������ʵ���⣬�����ṩF1��ʼ��Ϸ��F2���¿�ʼ��Ϸ�ļ���
	 * @author New song
	 *
	 */
	class KeyMonitor extends KeyAdapter{
		
		public void keyPressed(KeyEvent e) {
			myTank.keyPressed(e);
			if(e.getKeyCode() == KeyEvent.VK_F1) //F1 �����̣߳���ʼˢ��
				gameStart();
			else if(e.getKeyCode() == KeyEvent.VK_F2)
				rePlay();
		}
		
		public void keyReleased(KeyEvent e){
			myTank.keyReleased(e);
		}
			
	}
	/**
	 * ���������̣߳���ʼ������ػ棬��ʼ������ɾ�̬תΪ��̬
	 */
	public  void gameStart() {
		new Thread(new PaintThread()).start();
	}
	/**
	 * �������¿�ʼ��Ϸ�������ҷ�̹��Ѫ��������liveΪtrue
	 */
	public  void rePlay() {
		myTank.setHp(100);
		myTank.setLive(true);
		
	}
	/**
	 * ����̹�˵�һ�㷽��������paint�����У�ÿ���ػ�����з�̹�����������򴴽�̹��
	 * �з�̹�˵�λ�ö�������ģ�ȱ���ǿ�����ǽ��λ���ص�����ɿ���ǽ��ĺ��
	 * �з�̹�˵ĳ�ʼ����ָ���ҷ�̹�ˣ���һ��������
	 * @param sum ����ˢ�µ�̹������
	 */
	public void createEnemyTanks(int sum) {
		for(int i = 1; i<= sum; i++){
			enemyTanks.add(new Tank((int)(Math.random() * 800),(int)(Math.random() * 600),false,myTank.getDir(),this));
		}
	} 
	/**
	 * ��������������Ϸ
	 * @param args
	 */
	public static void main(String []args){
		new TankClient();				
	}
}

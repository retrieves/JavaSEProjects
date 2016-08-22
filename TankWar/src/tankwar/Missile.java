package tankwar;
import java.awt.*;
import java.util.*;
/**
 * �ӵ��࣬��ʾ�ӵ������Ժͷ���
 * @author New Song
 *
 */

public class Missile {

	private int x;
	private int y;
	private Dir dir = Dir.STOP;
	private boolean live = true;
	/**
	 * �����ӵ��ǵз�����Ļ����ҷ�����ģ���ΪҪ�ж��ӵ������ǲ�ͬ��Ӫ�Ĳ���Ч
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
		 * ���ݵ���˫�������ò�ͬ���ӵ���ɫ
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
	 * �ػ��ӵ�����һ��СԲ����ʾ
	 * ע��Ҫ���ж��ӵ��Ƿ���ţ��ɳ����ڼ���Ϊ����������Ҫ�ٻ������ˣ�
	 * ����֮��ʹ�������ȥ������Լ�ڴ�ռ�,�������ػ�
	 * @param g
	 */
	public void draw(Graphics g){
		if(!live){  //ÿ���ػ�֮ǰ���ж��ӵ��Ƿ��Ѿ���������������������ǻ���̹�ˣ�Ҳ�����Ƿɳ����ڣ���
					//���Ƚ�����������Ƴ���Ȼ���������ػ�Ĺ���
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
	 * �ӵ�������䷽�����
	 * ÿ���ƶ�Ҫ�ж��Ƿ�ɳ����ڣ�����ɳ���ô��Ϊ����
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
	 * �ж�һ���ӵ���һ��̹���Ƿ���ײ
	 * ǰ�����ӵ���̹�˶����ţ�����ֻ�����ڲ�ͬ��Ӫ����Ч
	 * ͬʱ���ез����ҷ������ͬ���з��������ҷ���Ѫ20�����Ϊ0��Ҳ����
	 * ÿ�λ��к�Ҫģ��һ�α�ը����ը��λ�ø�����ײʱ�ӵ���λ����ȷ��			
	 * @param t ̹��
	 */
	public void hitTank(Tank t){ //�ж�һ���ض����ӵ��Ƿ����̹�ˣ�������Tank�����÷�������ΪҪ�ж�ÿһ���ӵ���̹�˵�λ�ù�ϵ������ȡ���ӵ���λ�û���鷳��
		
		if( t.isLive() &&this.live && this.getRect().intersects(t.getRect())  && this.good != t.isGood() ){ //���������˼ά���ԣ����ӵ�����̹�ˣ����е��ж��Ƿ��غϵķ�������Ҫ���������õ����ƶ��Ķ��󣬱����õĲ����ȴ�����ײ�ġ�
			if(t.isGood()){																				  //Ҫ��̹�˺��ӵ����ǻ��ŵģ�����Ͳ���ȥ�ж��Ƿ���ײ
				t.setHp(t.getHp() -20);
				if(t.getHp() <= 0 )
					t.setLive(false);
			}
			else
				t.setLive(false);//������ж�̹���Ƿ���������ô�����ӵ��ɵ��Ѿ�������̹�˵�λ������Ȼ����ʧ					
			this.live = false;										//����ӵ���̹�˲���ͬһ���Ĳſ��Դ���
			tc.explodes.add(new Explode(x,y, tc));			
		}	 		
	}
	/**
	 * �ж�һ���ӵ���̹�������Ƿ���ײ����Ҫ���ҷ�̹�˷�����ӵ������еĵз�̹�˱����ж�
	 * ����д�ĺô�ʱ��paint�����оͲ���Ҫдѭ����Ƕ���ˣ�ֱ�ӵ��ø÷������ɣ��Ƚ�����
	 * @param enemyTanks ̹������
	 */
	public void hitTanks(ArrayList<Tank> enemyTanks){ //�����Ļ��Ͳ�����TankClient��ʹ��Ƕ��ѭ���ˣ�ֱ�ӵ��ø÷�������
		for(int i = 0; i < enemyTanks.size() ;i++){
			hitTank(enemyTanks.get(i));
		}
	}
	/**
	 * �ӵ���ǽ����ײ��⣬����ӵ�ײ��ǽ����ô�ӵ���ʧ
	 * @param wall
	 */
	public void hitWall(Wall wall){
		if(this.live && this.getRect().intersects(wall.getRect())){
			this.live  = false;			
		}
	}
}


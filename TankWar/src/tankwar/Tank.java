package tankwar;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * ̹���࣬���ڱ�ʾ̹�˵����Ժͷ���
 * @author New Song
 *
 */

public class Tank {
	
	private int x ; 
	private int y ;
	private int hp = 100;
	/**
	 * ���������ҷ��͵ط�
	 */
	private boolean good = true;	
	private boolean live = true;
	/**
	 * ���ڱ�����һ���ƶ���λ��X��������ײǽ֮���ܹ�������һ�ε�λ��
	 */
	private int oldX ;
	/**
	 * ���ڱ�����һ���ƶ���λ��Y��������ײǽ֮���ܹ�������һ�ε�λ��
	 */
	private int oldY ;
	/**
	 * ̹�˵ĳ��� ��̬����
	 */
	public static final int w = 30;
	/**
	 * ̹�˵Ŀ�� ��̬����
	 */
	public static final int h = 30;
	/**
	 * ̹�˶������ƶ��ٶȣ�Ҳ����ÿ���ػ�ʱ����̹�˷����λ��
	 */
	public static final int XSPEED = 5;
	/**
	 * ̹���ϱ����ƶ��ٶȣ�Ҳ����ÿ���ػ�ʱ����̹�˷����λ��
	 */
	public static final int YSPEED = 5;
	/**
	 * �������������������ɸ��ָ���������¼�
	 * ����Ϊ��̬��������������е���
	 */
	public static final Random r = new Random();//�����������
	/**
	 * ���ڵз�̹�����ػ������������ı��䷽����ʾ������
	 */
	int step = r.nextInt(12)+3;//����ƶ�3~14��
	/**
	 * ��ʾ̹�˵���ɫ�������ҷ����ǵط����в�ͬ����ɫ
	 */
	Color cTank = null;//Color.cyan;
	/**
	 * ��ʾ�ĸ������໥���֮���������ģ��8����ͬ�ķ���
	 */
	private boolean bR = false;
	private boolean bL = false;
	private boolean bU = false;
	private boolean bD = false;
	/**
	 * ���ڱ�ʾ̹�˵ķ���(�ƶ�����)
	 */
	private Dir dir = Dir.STOP;
	/**
	 * ���ڱ�ʾ̹�˵���Ͳ����
	 * �ӵ����ʱ�ķ�������Ͳ����һ��
	 * �����ҷ����������õģ���Ϊ���̹�˵ķ���ΪSTOP����ô�ӵ��ķ���Ҳ��STOP
	 * Ϊ�˱������������ֻ��ѡȡ��STOP��̹���ƶ�������Ϊ��Ͳ����
	 * ���ڵз����������õģ���Ϊ�з�̹�˵ķ��򲻻�ΪSTOP
	 */
	private Dir barrelDir = Dir.U;
	/**
	 * Ϊ�˱���һ�£�ÿ������TankClient����ĸ����඼ӵ��һ��TankClient������
	 * ����Tank����ԣ�����������enemyTanks��ɾ���з�̹�ˣ���missiles����ӵ��Լ������ӵ��Ĺ��췽����
	 */
	TankClient tc = null;
	BloodBar bb = new BloodBar();

	/**
	 * ���صĹ��췽�������ܸ��ݲ�ͬ��������ò�ͬ�Ĺ��췽��
	 * ����Ҫ��ÿ������������дһ�飬�����Ĺ��췽��������ͬ�Ĳ�������ʹ��this��������
	 * @param x
	 * @param y
	 * @param good
	 */
	
	public Tank(int x,int y,boolean good){ //���صĹ��췽�������������ڲ�ͬ��������е�ʱ����Ҫ������һ���������е�ʱ������Ҫ�����ؿ������㲻ͬ��Ҫ��
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
	 * ����ÿ��������ԣ���ͼʱʹ�õ���draw����paint����
	 * ���ж�̹���Ƿ��Ѿ��������������ٻ����Ҵ�enemyTanks��ȡ��
	 * �жϵ��ҷ���������ҷ�����ô����Ѫ��������BloodBar���draw���������Ƕ���������draw��Ƕ�׵��ã�
	 * ���˻���̹���⣬������Ͳ��������Ͳ����ͬ������״Ҳ�ǲ�ͬ��
	 * ÿ���ػ�̹�˶�����move��������֤̹�˵ĳ����ƶ�
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
	 * �Ƚ���STOP���ƶ����򸳸���Ͳ�������۵��ҷ�
	 * Ϊ�˱����ϴε�λ�ã�Ҫ�ȱ����ٸı�λ��
	 * Ϊ�˲��������ڽ��ޣ���λ�ó���ʱ������Ϊ���ڱ�Ե
	 * ͬʱ��ɵз�̹�˵����������ƶ����������
	 * Ϊ�˱���з�̹�˵��ӵ������ܼ����������������һ��������ŷ����ӵ� 
	 */
	public void move(){
		if(dir != Dir.STOP)
			barrelDir = dir;
		oldX = x; //�ȼ�¼��һ��λ�ã����ƶ�
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
		
		if(x < 0) x = 0; //����λ��
		if(y < 30) y = 30;
		if(x + Tank.w > TankClient.w) x = TankClient.w - Tank.w;
		if(y + Tank.h > TankClient.h) y = TankClient.h - Tank.h;
		
		if(!good){ //ʵ�ַ�����ƶ����������
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
	 * �þ�̬��������ʵ�ֵз�̹�˷�����������Dirö�ٳ����ŵ������У����һ���±�ֵ���������
	 * @return ��������ķ���
	 */
	public static Dir  randomDir() {
		Dir []dirs = Dir.values();
		int randomIndex = r.nextInt(dirs.length);	
		Dir dir = dirs[randomIndex];
		return dir;
	}
	/**
	 * ÿ�μ����¼�����֮��Ҫ��������̹���ƶ�����
	 * ����4�ַ���������ģ��8������
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
	 * �������������Һ�Z��
	 * Z���������һ���ҷ�̹��˲�ƵĹ��ܣ��Ƚ�ţ��
	 * @param e �����¼�
	 */
	public void keyPressed(KeyEvent e) { //���̵İ����ڰ��µ�˲�����ʱ��(�������й�ϵ),����������ͬʱ����ABCDʱ,
										 //���̴�����ֵ����ABCD �п�����ACBD ,ADBC�ȵ�����ֻҪ�ж���Ҫ�ļ������Ƿ񱻰��¾���		
		switch(e.getKeyCode()){			//�����������ԣ���̹�˴��ӵ����������ӵ����ӵ�
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
	 * ����Ӧ���ڼ��ɿ�����Ϊfalse������ͻᱣ��֮ǰ�ķ���
	 * ����X�����ڷ����ӵ��������ɿ�����������Ϊ�������ҷ��ӵ��ڰ�סXʱ�����ܼ������⣬��˰�һ��X��ֻ�ᷢ��һ���ӵ�
	 * ����C�����ڷ��䳬���ڵ�
	 * @param e �����¼�
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
	 * ���ڷ����ڵ���ÿ�ΰ�Xʱ����
	 * ���̹�����������ٷ����ӵ�
	 * �����ڵ�����new һ�������ӵ�Ȼ��װ��������
	 * ע���ڵ�������Ҫ���̹�˵����з����ȥ
	 */
	public void fire(){ //������ӵ�����new ���� �ӵ������кܲ����㣬һ����Ҫ����д������������һ������Ҫ���Tank��ĳ�Ա����
		if(!live ) return ;
		int xMissile = x + Tank.w/2- Missile.w/2;//ȷ���ӵ���λ��
		int yMissile = y + Tank.h/2- Missile.h/2;
		
		Missile m = new Missile(xMissile,yMissile,barrelDir,this.good,this.tc);//����Tank���������Ա�����ܷ���
		tc.missiles.add(m);												 //��Tank��ӵ�е�tc��ΪMissile�Ĳ������ݹ�ȥ
	}
	/**
	 * ���ص�fire���������ڸ������ʵ��superFire()����
	 * @param dire �ڵ��ķ���
	 */
	public void fire(Dir dire){ //����fire����
		if(!live ) return ;
		int xMissile = x + Tank.w/2- Missile.w/2;//ȷ���ӵ���λ��
		int yMissile = y + Tank.h/2- Missile.h/2;
		
		Missile m = new Missile(xMissile,yMissile,dire,this.good,this.tc);//����Tank���������Ա�����ܷ���
		tc.missiles.add(m);	
	}
	/**
	 * ���ڷ��䳬���ڵ���������8�������䣬����8�����ص�fire����(�������ķ�������)
	 */
	public void superFire(){ //������fire�ĵڶ������ط��������ؽ���ͬ������дһ�顣��8���������ڵ�
		Dir [] dirs = Dir.values();
		for(int i = 0; i< 8;i++){
			fire(dirs[i]);										 
		}
	}
	/**
	 * ˲�ƹ���
	 */
	private void TelePort() {
		x = r.nextInt(800);
		y = r.nextInt(600);
	}
	/**
	 * ̹�˺�ǽ����ײ��⣬���̹������ǽ����ô̹�˲����ƶ������ɴ�ǽ
	 * ���е���ײ�������������ƶ�����������ǵȴ�����ײ������
	 * ���Ҷ�Ҫ���ж������������Ƿ񶼻���
	 * �����þ��ε��ཻ���ж��Ƿ���ײ
	 * 
	 * @param wall ǽ
	 */
	public void collidesWithWall(Wall wall){
		if(live && this.getRect().intersects(wall.getRect()) ){
			stay();		//	this.dir = Dir.STOP; ���STOP�Ļ�����ô�´�����ƶ�ʱ�ᷢ����Ȼ��ǽ��ײ�����ٴ�����ΪSTOP
		}
	}
	/**
	 * ���ҷ�̹�˲����غ�
	 * @param t ̹��
	 */
	public void collidesWithTank(Tank t){
		if(live && t.isLive() && this.getRect().intersects(t.getRect())){
			stay();
		//	t.stay();
		}
	}
	/**
	 * �ҷ�̹�˺͵з�����̹��/�з���һ��̹�˺͵ط�����̹�˵���ײ���
	 * ע��Ҫ�ж��Ƿ���ͬһ��̹��
	 * ����collidesWithTank����
	 * @param tanks ̹�˵�����
	 */
	public void collidesWithTanks(ArrayList <Tank>tanks){
		for(int i = 0 ;i<tanks.size() ;i++){
			if(!this.equals(tanks.get(i)))
				collidesWithTank(tanks.get(i));
		}
	}
	/**
	 * ����ײ��̹�˾ͻص���һ���ƶ���λ��
	 */
	public void stay(){
		x = oldX;
		y = oldY;
	}
	/**
	 * �ҷ�̹�˺�Ѫ�����ײ���
	 * @param blood Ѫ��
	 */
	public void eatBlood(Blood blood){
		if(live && blood.isLive() && this.getRect().intersects(blood.getRect())){
			hp = 100;
			blood.setLive(false);
		}
	}
	/**
	 * ��ΪѪ��������̹�˵ģ���������Ϊ̹�˵��ڲ���
	 * ֻ��Ҫһ�����Լ��������ķ������ɣ�����ʹ��̹�˵�λ�ò���
	 * ������������ģ��Ѫ����һ���߿�һ��ʵ�ģ�ʵ�ĵĳ��ȸ���hp�Ķ��ٶ��仯
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

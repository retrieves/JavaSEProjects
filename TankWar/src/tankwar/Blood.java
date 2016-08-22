package tankwar;
import java.awt.*;

/**
 * Ѫ���࣬������ʾѪ������Ժͷ���
 * @author New Song
 *
 */
public class Blood {
	private int x ;
	private int y;
	private boolean live = true;
	/**
	 * Ѫ���̶���ˢ����ĳ�����̶��ĵ㴦
	 * �ö�ά��������ʾһ����ļ���
	 */
	private int [][] position = {
			{150,400},{200,200},{300,300},{400,400},{500,500},{700,550}
	};
	/**
	 * ���ڱ�ʾһ��ѭ���Ľ��ȣ�����Щ�㶼ˢ�¹������´�ͷˢ��
	 */
	int step = 0;
	/**
	 * ��������ÿ��Ѫ��ˢ�´��ڵ�ʱ���ÿ����Ѫ��ˢ��֮���ʱ����
	 * ��¼��Ļˢ�´���
	 * ʱ��ļ����������Ļˢ�´�������ʾ
	 */
	int count = 0;
	/**
	 * Ѫ��Ĺ̶��Ĵ�С
	 */
	public static final int w = 15;
	public static final int h = 15;
	TankClient tc = null;
	/**
	 * ���췽����ʹѪ������ڵ�һ�����λ��
	 */
	public Blood(){
		x = position[0][0];
		y = position[0][0];
		
	}
	/**
	 * ��Ѫ���Լ�������
	 * �����������ô��Ļˢ��300��֮�������Ϊ������½���ѭ������ʼˢ��
	 * ÿ��ˢ�µļ����100��ˢ�£�ÿ��ˢ�½�����һ�����λ��
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
	 * ����ÿ��ˢ�º������һ�����λ��
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

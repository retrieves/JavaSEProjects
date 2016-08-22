package tankwar;
import java.awt.*;
/**
 * ��ը�࣬����ģ�ⱬըЧ��
 * @author Administrator
 *
 */
public class Explode {
	private int x;
	private int y;
	/**
	 * �������������ÿ���ػ��ǻ�һ����ͬ�뾶��Բ������������һ��СԲ���͵���Բ��������СԲ��ģ��ı�ը
	 */
	private int [] diaMeter = {4,7,12,18,26,32,49,30,14,5};
	/**
	 * ��һ����������ʾ��ը���е�����һ��
	 */
	private int step = 0;
	private boolean live = true;
	private TankClient tc = null; //ӵ��TankClient��������Ϊ�˴�������ȥ����ǰ���󣨱�ը��
	public Explode (int x,int y,TankClient tc){
		this.x= x;
		this.y = y;
		this.tc =tc;
	}
	/**
	 * �ѱ�ը��������
	 * ���ж��Ƿ���������������ô��������ȥ��
	 * ����Ѿ��ﵽһ�α�ըѭ�������һ�Σ���ô�������㣬���¿�ʼ
	 * @param g
	 */
	public void draw(Graphics g){
		if(!live){
			tc.explodes.remove(this); //����֮���������ȡ���������ӵ����������������ʡ�ڴ�ռ�
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

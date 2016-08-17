package com.newsong.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;

@SuppressWarnings("all")
public class Index extends JWindow implements Runnable {

	JLabel jl ;
	JProgressBar jpb;
	
	public Index() {
		this.setLayout(new BorderLayout());
		jl = new JLabel(new ImageIcon("image/index/index.gif")); 
		jpb = new JProgressBar();
		jpb.setBackground(Color.cyan);
		jpb.setStringPainted(true);
		jpb.setBorderPainted(false);
		this.add(jl,BorderLayout.NORTH);
		this.add(jpb,BorderLayout.SOUTH); 
		//��֤����λ�ô�����Ļ������
		this.setSize(400, 308 );
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	//ע��ÿ��������һ�ν������ϵ���
	//ͼƬ�ı仯��ʹ����GIF��ͼ��ʹ��ͼƬ��ʾһ��֮�������ǡ�õ�100%
	public void run() {
		int [] num = {0,1,5,10,17,30,38,45,56,65,71,78,86,94,99,100};
		for(int i = 0 ; i< num.length;i++){
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			} 
			jpb.setValue(num[i]);
		}
		try{
			Thread.sleep(1000);
			this.dispose();
			new UserLogin(true);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Thread(new Index()).start();
	}
	
}
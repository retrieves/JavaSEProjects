package com.newsong.gui;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("all")
public class Demo01 {
	public static void main(String[] args) {
		MyFrame myFrame = new MyFrame();
	} 
}

@SuppressWarnings("all")
class MyFrame extends JFrame {
	JPanel jpNorth ;
	JPanel jpCenter;
	JLabel jlRoot;
	JLabel jlAttraction;
	JComboBox jcb;
	JList jlist;
	JScrollPane jsp ;
	Object[] provinces = {"����","����","����","����","����","�㶫","����","����","����","�ӱ�","������","����","����","����","����","����","����","����","����","���ɹ�","�ຣ","�Ϻ�","ɽ��","ɽ��","�Ĵ�","����","���","����","�½�","����","�㽭","̨��","���","����"};			
	
	
	public MyFrame() {
		jpNorth = new JPanel();
		jpNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
		jlRoot = new JLabel("���ļ�����");
		jcb = new JComboBox(provinces);
		jpNorth.add(jlRoot);
		jpNorth.add(jcb);
		
		jpCenter = new JPanel();
		jpCenter.setLayout(new FlowLayout(FlowLayout.CENTER));
		jlAttraction = new JLabel("��ϲ��ȥ���εĵ����� ");
		jlist = new JList(provinces);
		jlist.setVisibleRowCount(5);
		jsp = new JScrollPane(jlist);
		jsp.setWheelScrollingEnabled(true);
		jpCenter.add(jlAttraction);
		jpCenter.add(jsp);
		
		this.setLayout(new GridLayout(3,1));
		this.add(jpNorth,BorderLayout.NORTH);
		this.add(jpCenter,BorderLayout.CENTER);
		this.setTitle("�û�����");
		this.setBounds(500, 600, 400, 400);
 		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

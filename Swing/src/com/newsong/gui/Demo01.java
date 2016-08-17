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
	Object[] provinces = {"安徽","北京","重庆","福建","甘肃","广东","广西","贵州","海南","河北","黑龙江","河南","湖北","湖南","江苏","江西","吉林","辽宁","宁夏","内蒙古","青海","上海","山西","山东","四川","陕西","天津","西藏","新疆","云南","浙江","台湾","香港","澳门"};			
	
	
	public MyFrame() {
		jpNorth = new JPanel();
		jpNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
		jlRoot = new JLabel("您的籍贯是");
		jcb = new JComboBox(provinces);
		jpNorth.add(jlRoot);
		jpNorth.add(jcb);
		
		jpCenter = new JPanel();
		jpCenter.setLayout(new FlowLayout(FlowLayout.CENTER));
		jlAttraction = new JLabel("您喜欢去旅游的地区是 ");
		jlist = new JList(provinces);
		jlist.setVisibleRowCount(5);
		jsp = new JScrollPane(jlist);
		jsp.setWheelScrollingEnabled(true);
		jpCenter.add(jlAttraction);
		jpCenter.add(jsp);
		
		this.setLayout(new GridLayout(3,1));
		this.add(jpNorth,BorderLayout.NORTH);
		this.add(jpCenter,BorderLayout.CENTER);
		this.setTitle("用户调查");
		this.setBounds(500, 600, 400, 400);
 		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

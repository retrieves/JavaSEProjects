package com.newsong.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Demo03 {
	public static void main(String[] args) {
		QQFrame qq = new QQFrame();
		
	}
}
@SuppressWarnings("all")
class QQFrame extends JFrame{
	//北部
	JLabel jlImg ;
	
	URL urlIcon = Demo03.class.getClassLoader().getResource("images/2.png");
	URL urlNorth = Demo03.class.getClassLoader().getResource("images/3.png");
	//中部第一选项卡
	JTabbedPane jtp;
	JPanel jpCenterQQ;
	JLabel jlAccount;
	JLabel jlPWD;
	JTextField jtfAccount;
	JPasswordField jpfPWD;
	JCheckBox jcbHide;
	JCheckBox jcbMemorize;
	JButton jbClear;
	JButton jbForget;
	JButton jbApply;
	//中部第二选项卡
	JPanel jpCenterEmail;
	//中部第三选项卡
	JPanel jpCenterPhone;
	
	//南部
	JPanel jpSouth;
	JButton jbReg;
	JButton jbCancel;
	JButton jbHelp;
	
	public QQFrame() {
		Icon icon=  new ImageIcon(urlNorth);
		jlImg = new JLabel(icon);
		//北部完成
		jtp = new JTabbedPane();
		jpCenterQQ = new JPanel();
		jlAccount = new JLabel("QQ账号");
		jlPWD = new JLabel("QQ密码");
		jtfAccount = new JTextField(20);
		jpfPWD = new JPasswordField(20);
		jcbHide = new JCheckBox("隐身登录");
		jcbMemorize = new JCheckBox("记住密码");
		jbClear = new JButton("清除账号");
		jbForget = new JButton("忘记密码");
		jbApply = new JButton("申请密码保护");
		jpCenterQQ.setLayout(new GridLayout(3,3));
		jpCenterQQ.add(jlAccount);jpCenterQQ.add(jtfAccount);jpCenterQQ.add(jbClear);jpCenterQQ.add(jlPWD);
		jpCenterQQ.add(jpfPWD);jpCenterQQ.add(jbForget);jpCenterQQ.add(jcbHide);jpCenterQQ.add(jcbMemorize);
		jpCenterQQ.add(jbApply);
		jtp.add("QQ号码", jpCenterQQ);
		//第一个选项卡完成
		
		jpCenterEmail = new JPanel();
		jtp.add("电子邮箱", jpCenterEmail);
		//第二个选项卡完成
		
		jpCenterPhone = new JPanel();
		jtp.add("手机号码", jpCenterPhone);
		//第三个选项卡完成

		//中部完成
		jpSouth = new JPanel();
		jpSouth.setLayout(new FlowLayout());
		jbReg = new JButton("登录");
		jbCancel = new JButton("取消");
		jbHelp = new JButton("帮助");
		jpSouth.add(jbReg);jpSouth.add(jbCancel);jpSouth.add(jbHelp);
		//南部完成
		this.setLayout(new BorderLayout());
		this.add(jlImg,BorderLayout.NORTH);
		this.add(jtp,BorderLayout.CENTER);
		this.add(jpSouth,BorderLayout.SOUTH);
		this.setTitle("QQ");
		this.setBounds(300,300,400,320);
		Image img = Toolkit.getDefaultToolkit().getImage(urlIcon);
		this.setIconImage(img);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//顶部完成
	}
}

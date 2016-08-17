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
	//����
	JLabel jlImg ;
	
	URL urlIcon = Demo03.class.getClassLoader().getResource("images/2.png");
	URL urlNorth = Demo03.class.getClassLoader().getResource("images/3.png");
	//�в���һѡ�
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
	//�в��ڶ�ѡ�
	JPanel jpCenterEmail;
	//�в�����ѡ�
	JPanel jpCenterPhone;
	
	//�ϲ�
	JPanel jpSouth;
	JButton jbReg;
	JButton jbCancel;
	JButton jbHelp;
	
	public QQFrame() {
		Icon icon=  new ImageIcon(urlNorth);
		jlImg = new JLabel(icon);
		//�������
		jtp = new JTabbedPane();
		jpCenterQQ = new JPanel();
		jlAccount = new JLabel("QQ�˺�");
		jlPWD = new JLabel("QQ����");
		jtfAccount = new JTextField(20);
		jpfPWD = new JPasswordField(20);
		jcbHide = new JCheckBox("�����¼");
		jcbMemorize = new JCheckBox("��ס����");
		jbClear = new JButton("����˺�");
		jbForget = new JButton("��������");
		jbApply = new JButton("�������뱣��");
		jpCenterQQ.setLayout(new GridLayout(3,3));
		jpCenterQQ.add(jlAccount);jpCenterQQ.add(jtfAccount);jpCenterQQ.add(jbClear);jpCenterQQ.add(jlPWD);
		jpCenterQQ.add(jpfPWD);jpCenterQQ.add(jbForget);jpCenterQQ.add(jcbHide);jpCenterQQ.add(jcbMemorize);
		jpCenterQQ.add(jbApply);
		jtp.add("QQ����", jpCenterQQ);
		//��һ��ѡ����
		
		jpCenterEmail = new JPanel();
		jtp.add("��������", jpCenterEmail);
		//�ڶ���ѡ����
		
		jpCenterPhone = new JPanel();
		jtp.add("�ֻ�����", jpCenterPhone);
		//������ѡ����

		//�в����
		jpSouth = new JPanel();
		jpSouth.setLayout(new FlowLayout());
		jbReg = new JButton("��¼");
		jbCancel = new JButton("ȡ��");
		jbHelp = new JButton("����");
		jpSouth.add(jbReg);jpSouth.add(jbCancel);jpSouth.add(jbHelp);
		//�ϲ����
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
		//�������
	}
}

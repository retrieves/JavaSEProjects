package com.newsong.gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Demo02 {
	public static void main(String[] args) {
		MyFrame1 frame = new MyFrame1();
		
	}
}
@SuppressWarnings("all")
class MyFrame1 extends JFrame{
	JTextArea jta ;
	JPanel jp;
	JComboBox jcb;
	JTextField jtf;
	JButton jb;
	JScrollPane jsp ;
	Object[] friends ={"�˹�","����"};
	Image img = Toolkit.getDefaultToolkit().getImage(Demo02.class.getClassLoader().getResource("images/2.png"));
	public MyFrame1() {
	//	jsp = new JScrollPane();
	//���ʹ�ù���������ô���޷�����TextArea���ı�
	
		jta = new JTextArea(30,5);
		jta.setEditable(false);
	//	jsp.add(jta);
		jp = new JPanel();
		jcb = new JComboBox(friends);
		jtf = new JTextField(15);
		jb = new JButton("����"); 	
		jb.addActionListener(new ButtonMonitor());
		jp.setLayout(new FlowLayout());
		jp.add(jcb);
		jp.add(jtf);
		jp.add(jb);
		this.setLayout(new BorderLayout());
	//	this.add(jsp,BorderLayout.CENTER);
		this.add(jta,BorderLayout.CENTER);
		this.add(jp,BorderLayout.SOUTH);
		this.setTitle("QQ����");
		this.setIconImage(img);
		this.setBounds(200,200,400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	class ButtonMonitor implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			String line = jtf.getText().trim();
			jta.setText(jta.getText()+line+'\n');
			jtf.setText("");
		}
		
	}
}

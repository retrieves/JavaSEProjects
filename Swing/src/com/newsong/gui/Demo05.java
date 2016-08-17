package com.newsong.gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class Demo05 {
	public static void main(String[] args) {
		NotePad notePad = new NotePad();
	}
}

@SuppressWarnings("all")
class NotePad extends JFrame{
	JMenuBar jmb;
	JMenu file,edit,format,view,help;
	JMenu newMenu;
	JMenuItem open,save,saveAs,setting,print,exit;
	JMenuItem newFile,newProject;
	JTextArea jta;
	JScrollPane jsp ;
	JToolBar jtb;
	JButton jbNewFile ,jbOpen,jbSave,jbCopy,jbShear,jbPrint;
	URL [] urls ;
	public NotePad() {
		urls = new URL[8];
		for(int i=1;i<9;i++){
			ClassLoader classLoader = Demo05.class.getClassLoader();
			urls[i-1] = classLoader.getResource("images/"+i+".png");
		}
		jmb = new JMenuBar();
		file = new JMenu("�ļ�(F)");
		file.setMnemonic(KeyEvent.VK_F);//���ÿ�ݼ�
		edit = new JMenu("�༭(E)");
		edit.setMnemonic(KeyEvent.VK_E);
		format = new JMenu("��ʽ(O)");
		format.setMnemonic(KeyEvent.VK_O);
		view = new JMenu("�鿴(V)");
		view.setMnemonic(KeyEvent.VK_V);
		help = new JMenu("����(H)");
		help.setMnemonic(KeyEvent.VK_H);
		jmb.add(file);jmb.add(edit);jmb.add(format);jmb.add(view);jmb.add(help);
		newMenu = new JMenu("�½�");
		open = new JMenuItem("��	��",new ImageIcon(urls[6]));
		save = new JMenuItem("����");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		saveAs = new JMenuItem("���Ϊ");
		file.addSeparator();//��ӷָ���
		setting = new JMenuItem("ҳ������");
		print = new JMenuItem("��ӡ");
		file.addSeparator();
		exit = new JMenuItem("�˳�");
		file.add(newMenu);file.add(open);file.add(save);file.add(saveAs);file.add(setting);file.add(print);file.add(exit);
		
		
		newFile = new JMenuItem("�ļ�");
		newProject = new JMenuItem("����");
		newMenu.add(newFile);
		newMenu.add(newProject);
		
		jtb = new JToolBar();
		jbNewFile = new JButton(new ImageIcon(urls[0]));
		jbNewFile.setToolTipText("�½�");
		jbOpen = new JButton(new ImageIcon(urls[1]));
		jbOpen.setToolTipText("��");
		jbSave = new JButton(new ImageIcon(urls[2]));
		jbCopy = new JButton(new ImageIcon(urls[3]));
		jbShear = new JButton(new ImageIcon(urls[4]));
		jbPrint = new JButton(new ImageIcon(urls[5]));
		jtb.add(jbNewFile);jtb.add(jbOpen);jtb.add(jbSave);jtb.add(jbCopy);jtb.add(jbShear);jtb.add(jbPrint);
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//
		this.setTitle("���±�");
		this.setBounds(300,300,500,400);
		this.setJMenuBar(jmb);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(urls[7]));
		this.add(jtb,BorderLayout.NORTH);
		this.add(jsp,BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

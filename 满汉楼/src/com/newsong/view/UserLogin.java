package com.newsong.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.newsong.JavaBean.User;
import com.newsong.model.UserModel;
import com.newsong.tools.MyFont;
import com.newsong.view.receipt.ReceiptFrame;


public class UserLogin extends JDialog implements ActionListener{
	JLabel jlUserName ;
	JLabel jlPWD;
	JTextField jtfUserName;
	JPasswordField jpfPWD;
	JButton jbConfirm;
	JButton jbCancel;
	BackGround backGround ;
	boolean isInit ;
	String name;
	String password;
	String job;
	
	public UserLogin(boolean isInit) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		this.isInit = isInit;
		jlUserName = new JLabel("请输入用户名:");
		jlUserName.setBounds(45, 200,150,40);
		jlUserName.setFont(com.newsong.tools.MyFont.f2);
		jlPWD = new JLabel("请输入密码:");
		jlPWD.setBounds(45, 225,150,40);
		jlPWD.setFont(MyFont.f2);
		
		jtfUserName = new JTextField(10);
		jtfUserName.setBounds(180, 200,150,30);
		jtfUserName.setBorder(BorderFactory.createLoweredBevelBorder());
		jpfPWD = new JPasswordField(10);
		jpfPWD.setBounds(180,230,150,30);
		jpfPWD.setBorder(BorderFactory.createLoweredBevelBorder());
		
		jbConfirm = new JButton("确认");
		jbConfirm.setBounds(100,290,70,35);
		jbConfirm.setActionCommand("Confirm");
		jbConfirm.addActionListener(this);
		jbCancel = new JButton("取消");
		jbCancel.setBounds(200, 290,70,35);
		jbCancel.setActionCommand("Cancel");
		jbCancel.addActionListener(this);
		
		backGround = new BackGround();
		backGround.setBounds(0, 0,360,360);
		this.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-200,Toolkit.getDefaultToolkit().getScreenSize().height/2-200,380,400);
		this.setLayout(null);
		this.setTitle("用户登录");
		this.setResizable(false);
		this.add(jlUserName);
		this.add(jlPWD);
		this.add(jtfUserName); 
		this.add(jpfPWD);
		this.add(jbConfirm);
		this.add(jbCancel);
		this.add(backGround);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	//设置图片背景的一种方法
	class BackGround extends JPanel{
		
		Image img;
		public BackGround() {
			try {
				img = ImageIO.read(new File("image/login.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		protected  void paintComponent(Graphics g){  
			Color c = g.getColor();
			g.drawImage(img, 0, 0,360,360, this);
			g.setColor(c);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Confirm")){
			String username = jtfUserName.getText().trim();
			String password = new String(jpfPWD.getPassword());
			if(username.equals("")|| password.equals("")){
				showMsg("empty");
			} else{
				User user = new UserModel().findUser(username, password);
				if(user == null){
					showMsg("wrong");
					return;
				}
				this.name  = user.getName();
				this.job = user.getJob();
				if(job.equals("经理")||job.equals("主管")){
					
					showMsg("欢迎您,"+name+job);
					if(isInit){
						new MainFrame(name,job);
					}
					this.dispose();
				}else if (job.equals("收银员")){
					showMsg("欢迎您,"+name+job);
					if(isInit){
						new ReceiptFrame(null,name,job);
					}
					this.dispose();
				}else{
					showMsg("NoPermission");
				}
			}
		} else if(e.getActionCommand().equals("Cancel")){
			this.dispose();
		}
	}
	
	public void showMsg(String msg){
		if(msg.startsWith("欢迎您")){
			JOptionPane.showMessageDialog(this, msg);
			return;
		}
		switch(msg){
			case "empty":JOptionPane.showMessageDialog(this, "用户名或密码不可为空");
						 break;
			case "wrong":JOptionPane.showMessageDialog(this, "用户名或密码错误");
						 break;
			case "NoPermission":JOptionPane.showMessageDialog(this, "抱歉，你没有进入本系统的权限");
		}
	}
	public String getUserNameJob(){
		if(name == null || job == null)
			return null;
		return name+","+job;
	}
	
	/*public static void main(String[] args) {
		new UserLogin(true);
	}*/
}

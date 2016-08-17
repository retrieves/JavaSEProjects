package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import rmi.RemoteHelper;

/**
 * 登录界面
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("all")
public class LoginDialog extends JDialog implements ActionListener, KeyListener {
	private JLabel jlUserName;
	private JLabel jlPWD;
	private JTextField jtfUserName;
	private JPasswordField jpfPWD;
	private JButton jbConfirm;
	private JButton jbCancel;
	private PaintFrame backGround;
	private boolean isInit ;
	private String username;
	
	public LoginDialog(boolean isInit) {
		this.isInit = isInit;
		jlUserName = new JLabel("UserName:");
		jlUserName.setBounds(45, 200, 150, 40);
		jlUserName.setFont(MyFont.f3);
		jlPWD = new JLabel("Password:");
		jlPWD.setBounds(45, 225, 150, 40);
		jlPWD.setFont(MyFont.f3);

		jtfUserName = new JTextField(10);
		jtfUserName.setBounds(180, 200, 150, 30);
		jtfUserName.setBorder(BorderFactory.createLoweredBevelBorder());
		jpfPWD = new JPasswordField(10);
		jpfPWD.setBounds(180, 230, 150, 30);
		jpfPWD.setBorder(BorderFactory.createLoweredBevelBorder());
		jpfPWD.addKeyListener(this);
		
		jbConfirm = new JButton("Login");
		jbConfirm.setFont(MyFont.f6);
		jbConfirm.setBounds(100, 290, 80, 35);
		jbConfirm.setActionCommand("Confirm");
		jbConfirm.addActionListener(this);
		jbCancel = new JButton("Cancel");
		jbCancel.setFont(MyFont.f6);
		jbCancel.setBounds(200, 290, 80, 35);
		jbCancel.setActionCommand("Cancel");
		jbCancel.addActionListener(this);
		
		backGround = new PaintFrame("image/1.png",0,0,380,190);
		backGround.setBounds(0,0,380,190);
		this.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 200,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200, 400, 370);
		this.setLayout(null);
		this.setTitle("Login");
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


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Confirm")) {
			String username = jtfUserName.getText().trim();
			String password = new String(jpfPWD.getPassword()).trim();
			if (username.equals("") || password.equals("")) {
				showMsg("empty");
			} else {
				try {
					if (RemoteHelper.getInstance().getUserService().login(username, password)) {
						showMsg("Welcome ! " +username);
						if(isInit){
							new MainFrame(username);
							this.dispose();
						}
						else{
							this.username = username;
							this.dispose();
						}
					} else {
						showMsg("wrong");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
					showMsg("wrong");
				}
			}
		} else if (e.getActionCommand().equals("Cancel")) {
			this.dispose();
		}
	}
	
	public String getUserName(){
		return username;
	}
	
	public void showMsg(String msg) {
		if (msg.startsWith("Welcome")) {
			JOptionPane.showMessageDialog(this, msg);
			return;
		}
		switch (msg) {
		case "empty":
			JOptionPane.showMessageDialog(this, "User name or password can not be empty");
			break;
		case "wrong":
			JOptionPane.showMessageDialog(this, " Incorrect username or password");
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	//将键盘的回车键视为确认按钮
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			//命令按钮按下
			jbConfirm.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

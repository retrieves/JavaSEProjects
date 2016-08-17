package com.newsong.view;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.newsong.JavaBean.Employee;
import com.newsong.JavaBean.User;
import com.newsong.model.EmployeeModel;
import com.newsong.model.UserModel;

@SuppressWarnings("all")
public class AddLoginDialog extends JDialog implements ActionListener {
	UserModel um;
	EmployeeModel em;
	JLabel jlUserName;
	JLabel jlPWD;
	JTextField jtfUserName;
	JPasswordField jtfPWD;
	JButton jbConfirm;
	JButton jbCancel;

	public AddLoginDialog(JFrame registerPanel, String title, boolean model) {
		super(registerPanel, title, model);
		this.setLocationRelativeTo(null);
		this.setSize( 300, 220);
		this.setLayout(null);
		um = new UserModel();
		em = new EmployeeModel();
		jlUserName = new JLabel("工号");
		jlUserName.setBounds(40, 30, 200,30);
		jtfUserName = new JTextField(15);
		jtfUserName.setBounds(120,30,80,30);
		jlPWD = new JLabel("密码");
		jlPWD.setBounds(40, 80, 200, 30);
		jtfPWD = new JPasswordField(15);
		jtfPWD.setBounds(120, 80, 80, 30);
		jbConfirm = new JButton("确认");
		jbConfirm.setActionCommand("confirm");
		jbConfirm.setBounds(80, 120, 70, 30);
		jbConfirm.addActionListener(this);
		jbCancel = new JButton("取消");
		jbCancel.setActionCommand("cancel");
		jbCancel.setBounds(150, 120, 70, 30);
		jbCancel.addActionListener(this);
		this.add(jlUserName);
		this.add(jtfUserName);
		this.add(jlPWD);
		this.add(jtfPWD);
		this.add(jbConfirm);
		this.add(jbCancel);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "confirm":
			String username = jtfUserName.getText().trim();
			String password = new String(jtfPWD.getPassword()).trim();
			if (username.equals("")  || password.equals("") )  {
				showMsg("null");
				break;
			}
			if(username.length()!= 6 ){
				showMsg("forMatError");
				break;
			}
			if (um.getUser(username) != null) {
				showMsg("haveExisted");
				break;
			}
			Employee employee = null;
			if( (employee = em.findEmployeeId(username)) == null){
				showMsg("NoEmployee");
				break;
			}
			if (employee.getJob().equals("主管") || employee.getJob().equals("经理")) {
				um.addUser(new User(username, password));
			} else {
				showMsg("NoPermission");
			}
			break;
		case "cancel":
			this.dispose();
			break;
		}
	}

	public void showMsg(String msg) {
		switch (msg) {
		case "null":
			JOptionPane.showMessageDialog(this, "用户名或密码不可为空");
			break;
		case "haveExisted":
			JOptionPane.showMessageDialog(this, "该用户已存在");
			break;
		case "NoPermission":
			JOptionPane.showMessageDialog(this, "抱歉您无此权限");
			break;
		case "NoEmployee":
			JOptionPane.showMessageDialog(this, "没有此员工");
			break;
		case "forMatError":
			JOptionPane.showMessageDialog(this, "用户名必须为身份证后6位");
			break;
		}
		
	}

}

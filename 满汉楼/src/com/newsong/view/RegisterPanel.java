package com.newsong.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.newsong.JavaBean.User;
import com.newsong.model.UserModel;

@SuppressWarnings("all")
public class RegisterPanel extends JPanel implements ActionListener {
	JPanel jpNorth;
	JLabel jlPrompt;
	JTextField jtf;
	JButton jbSearch;
	JButton jbReturn;

	JPanel jpSouth;
	JPanel jpSouthEast;
	JLabel jlCount;
	JButton jbRefresh;
	JButton jbAdd;
	JButton jbUpdate;
	JTable jt;
	JScrollPane jsp;
	UserModel um;
	AddLoginDialog addDialog;
	JFrame superFrame;

	public RegisterPanel(JFrame frame) {
		this.superFrame = frame;
		um = new UserModel();
		um.findAllUsers();
		this.setLayout(new BorderLayout());
		jpNorth = new JPanel();
		jpNorth.setLayout(new FlowLayout());
		jlPrompt = new JLabel("������Ա����");
		jtf = new JTextField(10);
		jbSearch = new JButton("����");
		jbSearch.addActionListener(this);
		jbSearch.setActionCommand("search");
		jbReturn = new JButton("����");
		jbReturn.addActionListener(this);
		jbReturn.setActionCommand("return");
		jpNorth.add(jlPrompt);
		jpNorth.add(jtf);
		jpNorth.add(jbSearch);
		jpNorth.add(jbReturn);
		this.add(jpNorth, BorderLayout.NORTH);

		jt = new JTable(um);
		jt.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(jt.getSelectedRow() !=-1 ){
					jbUpdate.setEnabled(true);
				}
			}
		});
		jsp = new JScrollPane(jt);
		this.add(jsp, BorderLayout.CENTER);

		jpSouth = new JPanel();
		jpSouth.setLayout(new BorderLayout());
		jpSouthEast = new JPanel();
		jpSouthEast.setLayout(new FlowLayout());

		jlCount = new JLabel("��ǰ���ݿ⹲�� " + um.getCount()+" ����¼");
		jbRefresh = new JButton("ˢ��");
		jbRefresh.addActionListener(this);
		jbRefresh.setActionCommand("refresh");
		jbAdd = new JButton("���ӵ�¼�˺�");
		jbAdd.addActionListener(this);
		jbAdd.setActionCommand("add");
		jbUpdate = new JButton("�޸�����");
		jbUpdate.addActionListener(this);
		jbUpdate.setActionCommand("update");
		jbUpdate.setEnabled(false);
		
		jpSouth.add(jlCount, BorderLayout.WEST);
		jpSouthEast.add(jbRefresh);
		jpSouthEast.add(jbAdd);
		jpSouthEast.add(jbUpdate);
		jpSouth.add(jpSouthEast, BorderLayout.EAST);
		this.add(jpSouth, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {

		case "search":
			String username = jtf.getText().trim();
			if (username.equals("")) {
				System.out.println(username);
				showMsg("NoUserName");
			} else {
				um = new UserModel();
				um.getUser(username);
				jt.setModel(um);
			}
			break;
		case "return":
		case "refresh":
			um = new UserModel();
			um.findAllUsers();
			jt.setModel(um);
			break;
		case "add":
			addDialog = new AddLoginDialog(superFrame, "��ӵ�¼�û�", true);
			um = new UserModel();
			um.findAllUsers();
			jt.setModel(um);
			jlCount.setText("��ǰ���ݿ⹲�� " + um.getCount()+" ����¼");
			break;
		case "update":
			int rowNum = jt.getSelectedRow();
			String workId = (String)jt.getValueAt(rowNum, 1);
			String password = JOptionPane.showInputDialog(this,"�������޸ĺ������");
			if(password.equals("")){
				showMsg("NoPassword");
				jbUpdate.doClick();
			}else{
				um.updateUser(new User(workId,password));
				um = new UserModel();
				um.findAllUsers();
				jt.setModel(um);
			}
			break;
		}
		setUnable();
	}

	public void showMsg(String msg) {
		switch (msg) {
		case "NotChoose":
			JOptionPane.showMessageDialog(this, "��δѡ��Ա��");
			break;
		case "NoUserName":
			JOptionPane.showMessageDialog(this, "�����빤��");
			break;
		case "NoPassword":
			JOptionPane.showMessageDialog(this, "�������޸ĺ������");
		}
		setUnable();
	}
	public void setUnable(){
		jbUpdate.setEnabled(false);
	}
}

package com.newsong.view.receipt;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.newsong.JavaBean.Customer;
import com.newsong.JavaBean.Employee;
import com.newsong.model.CustomerModel;
import com.newsong.model.EmployeeModel;
import com.newsong.view.AddEmpDialog;
import com.newsong.view.DetailEmpDialog;
import com.newsong.view.UpdEmpDialog;
@SuppressWarnings("all")
public class ServicePanel extends JPanel implements ActionListener {

	CustomerModel cm;
	JPanel jpNorth;
	JLabel jlItem;
	JComboBox jcb;
	Object[] choices = { "姓名", "卡号"};
	JTextField jtf;
	JButton jbSearch;
	JButton jbReturn;
	JButton jbReturnToMain;
	
	JTable jt;
	JScrollPane jsp;
	
	JPanel jpSouth;
	JPanel jpSouthEast;
	JLabel jlCount;
	JButton jbRefresh;
	JButton jbAdd;
	JButton jbUpdate;
	JButton jbDelete;
	AddCusDialog addDialog;
	UpdCusDialog updDialog;
	
	public ServicePanel() {
		cm = new CustomerModel();
		this.setLayout(new BorderLayout());
		jpNorth = new JPanel();
		jpNorth.setLayout(new FlowLayout());
		jlItem = new JLabel("选择查询项目");
		jcb = new JComboBox<>(choices);
		jtf = new JTextField(10);
		jbSearch = new JButton("查询");
		jbSearch.setActionCommand("search");
		jbSearch.addActionListener(this);
		jbReturn = new JButton("返回");
		jbReturn.setActionCommand("return");
		jbReturn.addActionListener(this);
		jbReturnToMain = new JButton("返回到收款界面");
		jbReturnToMain.setActionCommand("returnToMain");
		jbReturnToMain.addActionListener(this);
		
		
		jpNorth.add(jlItem);
		jpNorth.add(jcb);
		jpNorth.add(jtf);
		jpNorth.add(jbSearch);
		jpNorth.add(jbReturn);
		this.add(jpNorth, BorderLayout.NORTH);

		cm.findAllCustomers();
		jt = new JTable(cm);
		jt.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(jt.getSelectedRow());
				if(jt.getSelectedRow() !=-1 ){
					jbUpdate.setEnabled(true);
					jbDelete.setEnabled(true);
				}
			}
		});
		
		jt.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jsp = new JScrollPane(jt);
		this.add(jsp, BorderLayout.CENTER);

		jpSouth = new JPanel();
		jpSouth.setLayout(new BorderLayout());
		jpSouthEast = new JPanel();
		jpSouthEast.setLayout(new FlowLayout());

		jlCount = new JLabel("当前数据库共有 " + cm.getCount() + " 条记录");
		jbRefresh = new JButton("刷新");
		jbRefresh.setActionCommand("refresh");
		jbRefresh.addActionListener(this);
		
		jbAdd = new JButton("添加客户");
		jbAdd.setActionCommand("add");
		jbAdd.addActionListener(this);
		
		jbUpdate = new JButton("修改客户信息");
		jbUpdate.setActionCommand("update");
		jbUpdate.addActionListener(this);
		jbUpdate.setEnabled(false);
		
		jbDelete = new JButton("删除客户");
		jbDelete.setActionCommand("delete");
		jbDelete.addActionListener(this);
		jbDelete.setEnabled(false);
		
		jpSouthEast.add(jbRefresh);
		jpSouthEast.add(jbAdd);
		jpSouthEast.add(jbUpdate);
		jpSouthEast.add(jbDelete);
		jpSouth.add(jlCount, BorderLayout.WEST);
		jpSouth.add(jpSouthEast, BorderLayout.EAST);
		this.add(jpSouth, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "search":
			String criteria = jtf.getText().trim();
			if (criteria.equals("")) {
				showMsg("NoCriteria");
				break;
			}
			cm = new CustomerModel();
			switch ((String) jcb.getSelectedItem()) {
			case "姓名":
				cm.findCustomerName(criteria);
				break;
			case "卡号":
				cm.findCustomerId(criteria);
				break;
			}
			jt.setModel(cm);
			break;
		case "return":
		case "refresh":
			cm = new CustomerModel();
			cm.findAllCustomers();
			jt.setModel(cm);
			break;
		case "add":
			addDialog = new AddCusDialog();
			addDialog.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					cm = new CustomerModel();
					cm.findAllCustomers();
					jt.setModel(cm);
					jlCount.setText("当前数据库共有 " + cm.getCount() + " 条记录");
				}
			});
			break;
		case "update":
			cm = new CustomerModel();
			Customer cus = cm.findCustomerId((String)jt.getValueAt(jt.getSelectedRow(),0));
			UpdCusDialog updDialog = new UpdCusDialog(cus);
			updDialog.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosed(WindowEvent e) {
					cm = new CustomerModel();
					cm.findAllCustomers();
					jt.setModel(cm);
				}
			});
			break;
		case "delete":
			int row = jt.getSelectedRow();
			cm.deleteCustomer((String)jt.getValueAt(row, 0));
			cm = new CustomerModel();
			cm.findAllCustomers();
			jt.setModel(cm);
			jlCount.setText("当前数据库共有 " + cm.getCount() + " 条记录");
			break;
		}
		setUnable();
	}

	public void showMsg(String msg) {
		switch (msg) {
		case "NotChoose":
			JOptionPane.showMessageDialog(this, "尚未选中客户");
			break;
		case "NoCriteria":
			JOptionPane.showMessageDialog(this, "请输入查询条件");
			break;
		}
		setUnable();
	}
	
	public void setUnable(){
		jbUpdate.setEnabled(false);
		jbDelete.setEnabled(false);
	}
}

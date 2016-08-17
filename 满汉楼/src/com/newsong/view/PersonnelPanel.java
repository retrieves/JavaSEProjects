package com.newsong.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.newsong.JavaBean.Employee;
import com.newsong.model.EmployeeModel;

@SuppressWarnings("all")
public class PersonnelPanel extends JPanel implements ActionListener {
	EmployeeModel em;
	JPanel jpNorth;
	JLabel jlItem;
	JComboBox jcb;
	Object[] choices = { "����", "����", "ְλ" };
	JTextField jtf;
	JButton jbSearch;
	JButton jbReturn;
	
	JTable jt;
	JScrollPane jsp;
	
	JPanel jpSouth;
	JPanel jpSouthEast;
	JLabel jlCount;
	JButton jbRefresh;
	JButton jbDetail;
	JButton jbAdd;
	JButton jbUpdate;
	JButton jbDelete;
	AddEmpDialog addDialog;
	UpdEmpDialog updDialog;
	
	public PersonnelPanel() {
		em = new EmployeeModel();
		this.setLayout(new BorderLayout());
		jpNorth = new JPanel();
		jpNorth.setLayout(new FlowLayout());
		jlItem = new JLabel("ѡ���ѯ��Ŀ");
		jcb = new JComboBox<>(choices);
		jtf = new JTextField(10);
		jbSearch = new JButton("��ѯ");
		jbSearch.setActionCommand("search");
		jbSearch.addActionListener(this);
		jbReturn = new JButton("����");
		jbReturn.setActionCommand("return");
		jbReturn.addActionListener(this);
		
		jpNorth.add(jlItem);
		jpNorth.add(jcb);
		jpNorth.add(jtf);
		jpNorth.add(jbSearch);
		jpNorth.add(jbReturn);
		this.add(jpNorth, BorderLayout.NORTH);

		em.findAllEmployees();
		jt = new JTable(em);
		jt.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(jt.getSelectedRow() !=-1 ){
					jbUpdate.setEnabled(true);
					jbDelete.setEnabled(true);
					jbDetail.setEnabled(true);
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

		jlCount = new JLabel("��ǰ���ݿ⹲�� " + em.getCount() + " ����¼");
		jbRefresh = new JButton("ˢ��");
		jbRefresh.setActionCommand("refresh");
		jbRefresh.addActionListener(this);
		
		jbDetail = new JButton("��ϸ��Ϣ");
		jbDetail.setActionCommand("detail");
		jbDetail.addActionListener(this);
		jbDetail.setEnabled(false);
		jbAdd = new JButton("���Ա��");
		jbAdd.setActionCommand("add");
		jbAdd.addActionListener(this);
		
		jbUpdate = new JButton("�޸�Ա����Ϣ");
		jbUpdate.setActionCommand("update");
		jbUpdate.addActionListener(this);
		jbUpdate.setEnabled(false);
		
		jbDelete = new JButton("ɾ��Ա��");
		jbDelete.setActionCommand("delete");
		jbDelete.addActionListener(this);
		jbDelete.setEnabled(false);
		
		jpSouthEast.add(jbRefresh);
		jpSouthEast.add(jbDetail);
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
			em = new EmployeeModel();
			switch ((String) jcb.getSelectedItem()) {
			case "����":
				em.findEmployeeName(criteria);
				break;
			case "����":
				em.findEmployeeId(criteria);
				break;
			case "ְλ":
				em.findEmployeeJob(criteria);
				break;
			}
			jt.setModel(em);
			break;
		case "return":
		case "refresh":
			em = new EmployeeModel();
			em.findAllEmployees();
			jt.setModel(em);
			break;
		case "detail":
			Employee emp = em.findEmployeeId((String)jt.getValueAt(jt.getSelectedRow(),1));
			new DetailEmpDialog(emp);
			break;
		case "add":
			addDialog = new AddEmpDialog();
			addDialog.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					em = new EmployeeModel();
					em.findAllEmployees();
					jt.setModel(em);
					jlCount.setText("��ǰ���ݿ⹲�� " + em.getCount() + " ����¼");
				}
			});
			break;
		case "update":
			em = new EmployeeModel();
			Employee emp2 = em.findEmployeeId((String)jt.getValueAt(jt.getSelectedRow(),1));
			UpdEmpDialog updDialog = new UpdEmpDialog(emp2);
			updDialog.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					em = new EmployeeModel();
					em.findAllEmployees();
					jt.setModel(em);
				}
			});
			break;
		case "delete":
			int row = jt.getSelectedRow();
			em.deleteEmployee((String)jt.getValueAt(row, 1));
			em = new EmployeeModel();
			em.findAllEmployees();
			jt.setModel(em);
			jlCount.setText("��ǰ���ݿ⹲�� " + em.getCount() + " ����¼");
			break;
		}
		setUnable();
	}

	public void showMsg(String msg) {
		switch (msg) {
		case "NotChoose":
			JOptionPane.showMessageDialog(this, "��δѡ��Ա��");
			break;
		case "NoCriteria":
			JOptionPane.showMessageDialog(this, "�������ѯ����");
			break;
		}
		setUnable();
	}
	public void setUnable(){
		jbUpdate.setEnabled(false);
		jbDelete.setEnabled(false);
		jbDetail.setEnabled(false);
	}

}

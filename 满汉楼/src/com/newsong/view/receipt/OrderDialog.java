package com.newsong.view.receipt;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.newsong.JavaBean.Order;
import com.newsong.JavaBean.OrderItem;
import com.newsong.model.OrderItemModel;
import com.newsong.model.OrderModel;
@SuppressWarnings("all")
public class OrderDialog extends JDialog implements ActionListener {
	int deskId;
	OrderModel om;
	OrderItemModel oim;
	JTable jt;
	JScrollPane jsp;
	JPanel jpSouth;
	JLabel jlSum;
	JButton jbgetSum;
	JButton jbConfirm;
	JButton jbCancel;
	boolean isSucc;
	
	public OrderDialog(int deskId) {
		this.deskId = deskId;
		this.setLayout(new BorderLayout());
		this.setTitle("点菜");
		this.setSize(640,480);
		this.setLocationRelativeTo(null);
		init();
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	
	public void init() {
		om = new OrderModel(deskId);
		om.findAllFoods();
		oim = new OrderItemModel(deskId);
		jt = new JTable(om);
		jt.getColumn("选择").setCellEditor(jt.getDefaultEditor(Boolean.class));
		jt.getColumn("选择").setCellRenderer(jt.getDefaultRenderer(Boolean.class));
		jt.getColumn("数量").setCellEditor(new DefaultCellEditor(new JTextField()));
		jt.getColumn("数量").setCellRenderer(jt.getDefaultRenderer(String.class));
		
		jt.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jlSum = new JLabel("0.0");
		jt.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					int col = jt.columnAtPoint(e.getPoint());
					int row = jt.rowAtPoint(e.getPoint());
					if(col == 4) {
						if(((Boolean)jt.getValueAt(row, col)).booleanValue()) {
							jt.setValueAt(false, row, col);
							jt.setValueAt(""+0, row, col+1);
						}else {
							jt.setValueAt(true, row, col);
							jt.setValueAt(""+1, row, col+1);
						}
					}
				}
			}
		});
		
		jsp = new JScrollPane(jt);
		this.add(jsp,BorderLayout.CENTER);
		jpSouth = new JPanel();
		jpSouth.setLayout(new FlowLayout());
		jbgetSum = new JButton("计算总额:");
		jbgetSum.setActionCommand("getSum");
		jbgetSum.addActionListener(this);
		
		jbConfirm = new JButton("确定");
		jbConfirm.setActionCommand("confirm");
		jbConfirm.addActionListener(this);
		
		jbCancel = new JButton("取消");
		jbCancel.setActionCommand("cancel");
		jbCancel.addActionListener(this);

		jpSouth.add(jbgetSum);
		jpSouth.add(jlSum);
		jpSouth.add(jbConfirm);
		jpSouth.add(jbCancel);
		this.add(jpSouth, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "getSum":
			jlSum.setText(""+sum());
			break;
		case "confirm":
			if(sum() < 0.1) {
				showMsg("NoChoose");
			}
			int foodId = 0;
			int servings = 0;
			Timestamp  time = new Timestamp(System.currentTimeMillis());
			Order order = new Order(deskId,time,sum());
			om.addOrder(order);
			int orderId = order.getId();
			for(int i = 0 ; i< jt.getRowCount();i++) {
				if(((Boolean)jt.getValueAt(i, 4)).booleanValue() ) {
					foodId = Integer.parseInt( (String)jt.getValueAt(i, 0));
					servings =  Integer.parseInt( (String)jt.getValueAt(i, 5));
					OrderItem orderItem = new OrderItem(orderId,foodId,servings);
					oim.addOrderItem(orderItem);
				}
			}
			showMsg("Success");
			this.dispose();
			isSucc = true;
			break;
		case "cancel":
			this.dispose();
		}
	}
	
	private void showMsg(String msg) {
		switch(msg) {
		case "NoChoose":
			JOptionPane.showMessageDialog(this, "您尚未选择");
			break;
		case "Success":
			JOptionPane.showMessageDialog(this, "点菜成功");
			break;
		}
	}

	public double sum() {
		double sum = 0;
		int servings = 0;
		for(int i = 0; i < jt.getRowCount();i++) {
			if(((Boolean)jt.getValueAt(i, 4)).booleanValue() ) {
				servings =  Integer.parseInt( (String)jt.getValueAt(i, 5));
				sum+= Double.parseDouble((String) jt.getValueAt(i, 2)) * servings;
			}
		}
		return sum;
	}	
	
	public boolean isSucc() {
		return isSucc;
	}
	
	/*public static void main(String[] args) {
		new OrderDialog(3);
	}*/
}


package com.newsong.view.stock;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.newsong.JavaBean.EnterStock;
import com.newsong.JavaBean.EnterStockItem;
import com.newsong.model.EmployeeModel;
import com.newsong.model.EnterStockItemModel;
import com.newsong.model.EnterStockModel;
import com.newsong.model.StockItemModel;
@SuppressWarnings("all")
public class EnterStockDialog extends JDialog implements ActionListener {

	StockItemModel sim;
	EnterStockModel esm;
	EnterStockItemModel esim;
	EmployeeModel em;
	
	
	JPanel jpNorth;
	JLabel jlEmp;
	JComboBox<String> jcb ;
	JTable jt;
	JScrollPane jsp;
	JPanel jpSouth;
	JButton jbConfirm;
	JButton jbCancel;
	boolean isSucc;
	
	Map<String,Integer> map ;
	String [] emp ;
	public EnterStockDialog() {
		this.setLayout(new BorderLayout());
		this.setTitle("入库");
		this.setSize(640,480);
		this.setLocationRelativeTo(null);
		init();
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	private void init() {
		esim = new EnterStockItemModel();
		em = new EmployeeModel();
		this.map = em.findAllEmployeesToMap();
		emp = map.keySet().toArray(new String[0]);
		jpNorth = new JPanel();
		jpNorth.setLayout(new FlowLayout());
		jlEmp = new JLabel("请选择您的姓名");
		jcb = new JComboBox<>(emp);
		jpNorth.add(jlEmp);
		jpNorth.add(jcb);
		this.add(jpNorth, BorderLayout.NORTH);
		
		esm = new EnterStockModel();
		sim = new StockItemModel();
		sim.findAll();
		jt = new JTable(sim);
		jt.getColumn("出入库").setCellEditor(jt.getDefaultEditor(Boolean.class));
		jt.getColumn("出入库").setCellRenderer(jt.getDefaultRenderer(Boolean.class));
		jt.getColumn("数量").setCellEditor(new DefaultCellEditor(new JTextField()));
		jt.getColumn("数量").setCellRenderer(jt.getDefaultRenderer(String.class));
		
		jt.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jt.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 1) {
					int col = jt.columnAtPoint(e.getPoint());
					int row = jt.rowAtPoint(e.getPoint());
					if(col == 3) {
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
		
		jbConfirm = new JButton("确定");
		jbConfirm.setActionCommand("confirm");
		jbConfirm.addActionListener(this);
		
		jbCancel = new JButton("取消");
		jbCancel.setActionCommand("cancel");
		jbCancel.addActionListener(this);
		
		jpSouth.add(jbConfirm);
		jpSouth.add(jbCancel);
		this.add(jpSouth, BorderLayout.SOUTH);
	}


	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "confirm":
			int id = map.get((String) jcb.getSelectedItem());
			EnterStock enterStock = new  EnterStock(new Date(),id );
			esm.addEnterStock(enterStock);
			String enterStockNo = enterStock.getNo();
			int stockId = 0;
			int stockQty = 0;
			for(int i = 0 ; i< jt.getRowCount();i++) {
				if(((Boolean)jt.getValueAt(i, 3)).booleanValue() ) {
					stockId = Integer.parseInt( (String)jt.getValueAt(i, 0));
					stockQty =  Integer.parseInt( (String)jt.getValueAt(i, 4));
					EnterStockItem esi = new EnterStockItem(enterStockNo,stockId,stockQty);
					esim.addEnterStockItem(esi);
				}
			}
			JOptionPane.showMessageDialog(this, "入库成功");
			this.dispose();
			isSucc = true;
			break;
		case "cancel":
			this.dispose();
		}
	}
	
	public boolean isSucc() {
		return isSucc;
	}
}

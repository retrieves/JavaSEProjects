package com.newsong.view.stock;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.newsong.JavaBean.Stock;
import com.newsong.JavaBean.Supplier;
import com.newsong.model.EnterStockModel;
import com.newsong.model.LeaveStockModel;
import com.newsong.model.StockModel;
import com.newsong.model.SupplierModel;
import com.newsong.view.MainFrame;

public class StockPanel extends JPanel implements ActionListener {
	JTabbedPane jtp;

	JPanel jpMaterial;
	JScrollPane jspMaterial;
	private JTable jtMaterial;
	JPanel jpMatSouth;
	JButton jbAddMat;
	JButton jbUpdMat;
	JButton jbRefresh;
	StockModel sm;
	EnterStockModel esm;
	LeaveStockModel lsm;
	SupplierModel slm;

	JPanel jpEnterStock;
	JScrollPane jspEnterStock;
	private JTable jtEnterStock;
	JPanel jpEnterSouth;
	JButton jbEnter;
	private JButton jbEnterDetail;

	JPanel jpLeaveStock;
	JScrollPane jspLeaveStock;
	private JTable jtLeaveStock;
	JPanel jpLeaveSouth;
	JButton jbLeave;
	private JButton jbLeaveDetail;

	private JPanel jpSupplier;
	private JScrollPane jspSupplier;
	private JTable jtSupplier;
	private JPanel jpSupplierSouth;
	private JButton jbAddSupplier;
	private JButton jbUpdSupplier;
	private JButton jbDelSupplier;
	private JButton jbRefreshSup;

	public StockPanel(MainFrame mainFrame) {

		jtp = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(jtp, GroupLayout.PREFERRED_SIZE, 1720, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(jtp,
				GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE));
		sm = new StockModel();
		sm.findAll();
		
		jpMaterial = new JPanel();
		jtp.addTab("\u5E93\u5B58\u7EDF\u8BA1", null, jpMaterial, null);
		jpMaterial.setLayout(new BorderLayout(0, 0));

		jspMaterial = new JScrollPane();
		jpMaterial.add(jspMaterial, BorderLayout.CENTER);

		jtMaterial = new JTable(sm);
		jspMaterial.setViewportView(jtMaterial);
		jtMaterial.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtMaterial.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (jtMaterial.getSelectedRow() != -1) {
					jbUpdMat.setEnabled(true);
				}
			}
		});
		jpMatSouth = new JPanel();
		jpMaterial.add(jpMatSouth, BorderLayout.SOUTH);

		jbAddMat = new JButton("\u6DFB\u52A0\u539F\u6599");
		jbAddMat.setActionCommand("matAdd");
		jbAddMat.addActionListener(this);
		jpMatSouth.add(jbAddMat);

		jbUpdMat = new JButton("\u4FEE\u6539\u539F\u6599\u4FE1\u606F");
		jbUpdMat.setActionCommand("matUpd");
		jbUpdMat.addActionListener(this);
		jpMatSouth.add(jbUpdMat);

		jbRefresh = new JButton("\u5237\u65B0\u5E93\u5B58");
		jbRefresh.setActionCommand("matRefresh");
		jbRefresh.addActionListener(this);
		jpMatSouth.add(jbRefresh);

		// ---------------------------------------------------------------------------------------
		jpEnterStock = new JPanel();
		jtp.addTab("\u5165\u5E93\u6E05\u5355", null, jpEnterStock, null);
		jpEnterStock.setLayout(new BorderLayout(0, 0));
		esm = new EnterStockModel();
		esm.findAll();
		jspEnterStock = new JScrollPane();
		jpEnterStock.add(jspEnterStock, BorderLayout.CENTER);

		jtEnterStock = new JTable(esm);
		jspEnterStock.setViewportView(jtEnterStock);
		jtEnterStock.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtEnterStock.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (jtEnterStock.getSelectedRow() != -1) {
					jbEnterDetail.setEnabled(true);
				}
			}
		});

		jpEnterSouth = new JPanel();
		jpEnterStock.add(jpEnterSouth, BorderLayout.SOUTH);
		jpEnterSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		jbEnter = new JButton("\u5165    \u5E93");
		jpEnterSouth.add(jbEnter);
		jbEnter.setActionCommand("enter");
		jbEnter.addActionListener(this);
		jbEnterDetail = new JButton("\u5165\u5E93\u8BE6\u7EC6\u4FE1\u606F");
		jbEnterDetail.setActionCommand("enterDetail");
		jbEnterDetail.addActionListener(this);
		jbEnterDetail.setEnabled(false);
		jpEnterSouth.add(jbEnterDetail);

		// --------------------------------------------------------------------------------------------------------
		jpLeaveStock = new JPanel();
		jtp.addTab("\u51FA\u5E93\u6E05\u5355", null, jpLeaveStock, null);
		jpLeaveStock.setLayout(new BorderLayout(0, 0));

		jspLeaveStock = new JScrollPane();
		jpLeaveStock.add(jspLeaveStock);
		lsm = new LeaveStockModel();
		lsm.findAll();
		jtLeaveStock = new JTable(lsm);
		jspLeaveStock.setViewportView(jtLeaveStock);
		jtLeaveStock.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtLeaveStock.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (jtLeaveStock.getSelectedRow() != -1) {
					jbLeaveDetail.setEnabled(true);
				}
			}
		});
		jpLeaveSouth = new JPanel();
		jpLeaveStock.add(jpLeaveSouth, BorderLayout.SOUTH);
		jpLeaveSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		jbLeave = new JButton("\u51FA    \u5E93");
		jbLeave.setActionCommand("leave");
		jbLeave.addActionListener(this);
		jpLeaveSouth.add(jbLeave);
		
		jbLeaveDetail = new JButton("\u51FA\u5E93\u8BE6\u7EC6\u4FE1\u606F");
		jbLeaveDetail.setEnabled(false);
		jbLeaveDetail.setActionCommand("leaveDetail");
		jbLeaveDetail.addActionListener(this);
		jpLeaveSouth.add(jbLeaveDetail);

		// ----------------------------------------------------------------------------------------------------
		jpSupplier = new JPanel();
		jtp.addTab("\u4F9B\u5E94\u5546\u7BA1\u7406", null, jpSupplier, null);
		jpSupplier.setLayout(new BorderLayout(0, 0));

		jspSupplier = new JScrollPane();
		jpSupplier.add(jspSupplier, BorderLayout.CENTER);
		slm = new SupplierModel();
		slm.findAll();
		jtSupplier = new JTable(slm);
		jspSupplier.setViewportView(jtSupplier);
		jtSupplier.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtSupplier.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (jtSupplier.getSelectedRow() != -1) {
					jbUpdSupplier.setEnabled(true);
					jbDelSupplier.setEnabled(true);
				}
			}
		});
		
		jpSupplierSouth = new JPanel();
		jpSupplier.add(jpSupplierSouth, BorderLayout.SOUTH);

		jbAddSupplier = new JButton("\u6DFB\u52A0\u4F9B\u5E94\u5546");
		jbAddSupplier.setActionCommand("supAdd");
		jbAddSupplier.addActionListener(this);
		jpSupplierSouth.add(jbAddSupplier);
		
		jbUpdSupplier = new JButton("\u4FEE\u6539\u4F9B\u5E94\u5546\u4FE1\u606F");
		jbUpdSupplier.setActionCommand("supUpd");
		jbUpdSupplier.addActionListener(this);
		jbUpdSupplier.setEnabled(false);
		jpSupplierSouth.add(jbUpdSupplier);

		jbDelSupplier = new JButton("\u5220\u9664\u4F9B\u5E94\u5546");
		jbDelSupplier.setActionCommand("supDel");
		jbDelSupplier.addActionListener(this);
		jbDelSupplier.setEnabled(false);
		jpSupplierSouth.add(jbDelSupplier);
		
		jbRefreshSup = new JButton("\u5237  \u65B0");
		jbRefreshSup.setActionCommand("supRefresh");
		jbRefreshSup.addActionListener(this);
		
		jpSupplierSouth.add(jbRefreshSup);
		setLayout(groupLayout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.startsWith("mat")) {
			switch (cmd) {
			case "matAdd":
				AddMaterialDialog addDialog = new AddMaterialDialog();
				addDialog.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						sm = new StockModel();
						sm.findAll();
						jtMaterial.setModel(sm);
					}
				});
				break;
			case "matUpd":
				Stock stock = sm.find(Integer.parseInt((String) jtMaterial.getValueAt(jtMaterial.getSelectedRow(), 0)));
				UpdMaterialDialog updDialog = new UpdMaterialDialog(stock);
				updDialog.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						sm = new StockModel();
						sm.findAll();
						jtMaterial.setModel(sm);
					}
				});
				break;
			case "matDel":
				sm.deleteStock(Integer.parseInt((String) jtMaterial.getValueAt(jtMaterial.getSelectedRow(), 0)));
				sm = new StockModel();
				sm.findAll();
				jtMaterial.setModel(sm);
				break;
			case "matRefresh":
				sm = new StockModel();
				sm.findAll();
				jtMaterial.setModel(sm);
				break;
			}
			this.jbUpdMat.setEnabled(false);
		} else if (cmd.startsWith("sup")) {
			switch(cmd) {
			case "supAdd":
				AddSupDialog addDialog = new AddSupDialog();
				addDialog.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						if(addDialog.isSucc()) {
							refreshSup();
						}
					}
				});
				break;
			case "supUpd":
				Supplier sup = slm.find(Integer.parseInt((String)jtSupplier.getValueAt(jtSupplier.getSelectedRow(),0)));
				UpdSupDialog updDialog = new UpdSupDialog(sup);
				updDialog.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						if(updDialog.isSucc()) {
							refreshSup();
						}
					}
				});
				break;
			case "supDel":
				slm.deleteSupplier(Integer.parseInt((String)jtSupplier.getValueAt(jtSupplier.getSelectedRow(),0)));
				refreshSup();
				break;
			case "supRefresh":
				refreshSup();
				break;
			}
			setSupplierUnable();
			
		} else if (cmd.startsWith("enter")) {
			switch (cmd) {
			case "enter":
				EnterStockDialog enterDialog = new EnterStockDialog();
				enterDialog.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						if (enterDialog.isSucc()) {
							esm = new EnterStockModel();
							esm.findAll();
							jtEnterStock.setModel(esm);
						}
					}
				});
				break;
			case "enterDetail":
				new EnterStockDetailDialog((String) jtEnterStock.getValueAt(jtEnterStock.getSelectedRow(), 0));
				break;
			}
			jbEnterDetail.setEnabled(false);

		} else if (cmd.startsWith("leave")) {
			switch (cmd) {
			case "leave":
				LeaveStockDialog leaveDialog = new LeaveStockDialog();
				leaveDialog.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						if (leaveDialog.isSucc()) {
							lsm = new LeaveStockModel();
							lsm.findAll();
							jtLeaveStock.setModel(lsm);
						}
					}
				});
				break;
			case "leaveDetail":
				new LeaveStockDetailDialog((String) jtLeaveStock.getValueAt(jtLeaveStock.getSelectedRow(), 0));
				break;
			}
			 jbLeaveDetail.setEnabled(false);
		}
	}
	public void setSupplierUnable() {
		jbUpdSupplier.setEnabled(false);
		jbDelSupplier.setEnabled(false);
	}
	
	public void refreshSup() {
		slm = new SupplierModel();
		slm.findAll();
		jtSupplier.setModel(slm);
	}
}

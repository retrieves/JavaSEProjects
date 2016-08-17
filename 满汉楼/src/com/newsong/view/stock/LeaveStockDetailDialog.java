package com.newsong.view.stock;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.newsong.model.EnterStockItemModel;
import com.newsong.model.LeaveStockItemModel;

@SuppressWarnings("all")
public class LeaveStockDetailDialog extends JDialog {
	JTable jt;
	JScrollPane jsp;
	JPanel jpSouth;
	JButton jbConfirm;
	LeaveStockItemModel lsim;
	
	public LeaveStockDetailDialog(String leaveStockId) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setTitle("�����굥");
		this.setLocationRelativeTo(null);
		this.setSize(640,480);
		lsim = new LeaveStockItemModel(leaveStockId);
		lsim.findAllLeaveStockItems();
		jt = new JTable(lsim);
		jsp = new JScrollPane(jt);
		this.add(jsp, BorderLayout.CENTER);
		jpSouth = new JPanel();
		jbConfirm = new JButton("ȷ  ��");
		jpSouth.add(jbConfirm);
		this.add(jpSouth, BorderLayout.SOUTH);
		jbConfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		this.setVisible(true);
	}
	
	
}

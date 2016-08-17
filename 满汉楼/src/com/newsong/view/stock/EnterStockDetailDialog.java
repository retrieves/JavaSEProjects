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

@SuppressWarnings("all")
public class EnterStockDetailDialog extends JDialog {
	JTable jt;
	JScrollPane jsp;
	JPanel jpSouth;
	JButton jbConfirm;
	EnterStockItemModel esim;
	
	public EnterStockDetailDialog(String enterStockId) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setTitle("入库详单");
		this.setLocationRelativeTo(null);
		this.setSize(640,480);
		esim = new EnterStockItemModel(enterStockId);
		esim.findAllEnterStockItems();
		jt = new JTable(esim);
		jsp = new JScrollPane(jt);
		this.add(jsp, BorderLayout.CENTER);
		jpSouth = new JPanel();
		jbConfirm = new JButton("确  定");
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

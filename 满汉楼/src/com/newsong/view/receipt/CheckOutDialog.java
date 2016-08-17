package com.newsong.view.receipt;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.newsong.JavaBean.Order;
import com.newsong.model.CustomerModel;
import com.newsong.model.OrderItemModel;
import com.newsong.model.OrderModel;

@SuppressWarnings("all")
public class CheckOutDialog extends JDialog implements ActionListener {

	private JPanel buttonPane;
	private JTable jt;
	private JTextField jtfCardId;

	JLabel jlCardId;
	JLabel jlSum;
	JLabel jlSumData;
	JLabel jlNote;
	JButton jbConfirm;
	JButton jbCancel;
	OrderItemModel oim;
	CustomerModel cm;
	OrderModel om;
	private JScrollPane scrollPane;
	Order order ;
	boolean isSucc;
	private JButton jbValidate;
	private boolean canDiscount;
	
	public CheckOutDialog(int deskId) {
		om = new OrderModel(deskId);
		this.order = om.findOrder();
		oim = new OrderItemModel(order.getId());
		oim.findOrderItems();
		cm = new CustomerModel();
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("\u7ED3\u8D26");
		setBounds(100, 100, 536, 476);
		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		}
		jlCardId = new JLabel("\u8D35\u5BBE\u5361\u53F7:");

		jtfCardId = new JTextField();
		jtfCardId.setColumns(10);
		
		
		jlSum = new JLabel("\u6D88\u8D39\u603B\u989D:");
		
		jlSumData = new JLabel(""+order.getOrderAmount());
		
		jlNote = new JLabel("(\u6CA1\u6709\u53EF\u4E0D\u586B)");

		jbConfirm = new JButton("\u7ED3\u8D26");
		jbConfirm.setActionCommand("confirm");
		jbConfirm.addActionListener(this);

		jbCancel = new JButton("\u53D6\u6D88");
		jbCancel.setActionCommand("cancel");
		jbCancel.addActionListener(this);

		scrollPane = new JScrollPane();
		
		jbValidate = new JButton("\u9A8C\u8BC1\u5361\u53F7");
		jbValidate.setActionCommand("validate");
		jbValidate.addActionListener(this);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, 518, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(jlNote)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(jlCardId)
								.addComponent(jlSum))
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(jlSumData, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(jtfCardId, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(jbValidate)))))
					.addContainerGap(74, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(151)
					.addComponent(jbConfirm, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(jbCancel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(183, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlCardId)
						.addComponent(jtfCardId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jbValidate))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jlNote)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlSum)
						.addComponent(jlSumData))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbConfirm)
						.addComponent(jbCancel))
					.addGap(85)
					.addComponent(buttonPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		jt = new JTable(oim);
		jt.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(jt);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setWheelScrollingEnabled(true);
		getContentPane().setLayout(groupLayout);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "confirm":
			if(canDiscount == false &&  !jtfCardId.getText().trim().equals("")) {
				int respone = JOptionPane.showConfirmDialog(this, "您尚未验证会员,是否要结账","是否要结账",JOptionPane.YES_NO_OPTION);
				if(respone == JOptionPane.NO_OPTION) {
					jbValidate.doClick();
					break;
				}
			}
			if(canDiscount)
				om.discount();
			this.isSucc = true;
			showMsg("Success");
			this.dispose();
			break;
		case "cancel":
			this.dispose();
			break;
		case "validate":
			String cardId = jtfCardId.getText().trim();
			if (!cardId.equals("")) {
				if(cm.findCustomerId(cardId) != null) {
					double discount = order.getOrderAmount()*0.2;
					jlSumData.setText(""+order.getOrderAmount()*0.8);
					showMsg("会员验证成功,您节省了"+discount+"元");
					canDiscount = true;
				}else {
					canDiscount = false;
					showMsg("validateFail");
				}
			}
			break;
		}
	}

	public void showMsg(String msg) {
		if(msg.startsWith("会员")) {
			JOptionPane.showMessageDialog(this, msg);
			return;
		}
		switch (msg) {
		case "Success":
			JOptionPane.showMessageDialog(this, "结账成功");
			break;
		case "validateFail":
			JOptionPane.showMessageDialog(this, "会员验证失败,请重新输入卡号");
			break;
		}
	}
}

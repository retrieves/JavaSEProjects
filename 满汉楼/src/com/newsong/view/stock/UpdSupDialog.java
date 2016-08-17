package com.newsong.view.stock;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.newsong.JavaBean.Supplier;
import com.newsong.model.SupplierModel;

public class UpdSupDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField jtfName;
	private JTextField jtfAddr;
	private JTextField jtfPhone;
	private JTextField jtfEmail;
	JLabel jlName;
	JLabel jbAddr;
	JLabel jlPhone;
	JLabel jbEmail;
	JLabel jlNote;
	JTextArea jtaNote;
	JButton jbConfirm;
	JButton jbCancel;
	private boolean isSucc;
	SupplierModel sm;
	Supplier sup;
	
	public UpdSupDialog(Supplier sup) {
		sm = new SupplierModel();
		this.sup = sup;
		setTitle("\u4FEE\u6539\u4F9B\u5E94\u5546\u4FE1\u606F");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 530, 383);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		jlName = new JLabel("\u540D\u79F0:");		
		jtfName = new JTextField();
		jtfName.setColumns(10);		
		jtfName.setText(sup.getName());
		jbAddr = new JLabel("\u5730\u5740:");		
		jtfAddr = new JTextField();
		jtfAddr.setColumns(10);	
		jtfAddr.setText(sup.getAddr());
		jlPhone = new JLabel("\u8054\u7CFB\u65B9\u5F0F:");	
		jtfPhone = new JTextField();
		jtfPhone.setColumns(10);
		jtfPhone.setText(sup.getPhone());
		jbEmail = new JLabel("\u90AE\u7BB1:");		
		jtfEmail = new JTextField();
		jtfEmail.setColumns(10);		
		jtfEmail.setText(sup.getEmail());
		jlNote = new JLabel("\u5907\u6CE8:");		
		jtaNote = new JTextArea();
		jtaNote.setText(sup.getNote());
		jbConfirm = new JButton("\u786E\u5B9A");
		jbConfirm.setActionCommand("confirm");
		jbConfirm.addActionListener(this);
		jbCancel = new JButton("\u53D6\u6D88");
		jbCancel.setActionCommand("cancel");
		jbCancel.addActionListener(this);
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(34)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(jlName)
												.addComponent(jbEmail)
												.addComponent(jbAddr))
											.addGap(36))
										.addComponent(jlNote))
									.addGap(6))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(jlPhone)
									.addPreferredGap(ComponentPlacement.UNRELATED)))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(jtaNote, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
								.addComponent(jtfName, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
								.addComponent(jtfAddr, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(jtfEmail, Alignment.LEADING)
									.addComponent(jtfPhone, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(155)
							.addComponent(jbConfirm, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(jbCancel, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlName)
						.addComponent(jtfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbAddr)
						.addComponent(jtfAddr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlPhone)
						.addComponent(jtfPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(jbEmail)
						.addComponent(jtfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(jlNote)
						.addComponent(jtaNote, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbCancel)
						.addComponent(jbConfirm)))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "confirm":
			String name = jtfName.getText().trim();
			String addr = jtfAddr.getText().trim();
			String phone = jtfPhone.getText().trim();
			String email= jtfEmail.getText().trim();
			String note = jtaNote.getText().trim();
			if(name.equals("")||addr.equals("")||email.equals("")||phone.equals("")) {
				showMsg("Empty");
				break;
			}
			Supplier sup = new Supplier(this.sup.getId(),name,addr,phone,email,note);
			sm.updateSupplier(sup);
			showMsg("Success");
			isSucc = true;
			this.dispose();
			break;
		case "cancel":
			this.dispose();
			break;
		}
	}
	
	public void showMsg(String msg){
		switch(msg){
		case "Empty":
			JOptionPane.showMessageDialog(this, "不可为空");
			break;
		case "Success":
			JOptionPane.showMessageDialog(this, "修改成功");
			break;
		}
	}
	public boolean isSucc() {
		return isSucc;
	}
}

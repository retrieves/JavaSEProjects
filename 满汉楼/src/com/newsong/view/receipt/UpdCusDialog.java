package com.newsong.view.receipt;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.newsong.JavaBean.Customer;
import com.newsong.model.CustomerModel;
@SuppressWarnings("all")
public class UpdCusDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField jtfName;
	private JTextField jtfHome;
	private JTextField jtfPhone;
	private ButtonGroup bgSex;
	JRadioButton jrbM;
	JRadioButton jrbF;
	JButton jbUpdate;
	JButton jbCancel;
	CustomerModel cm;
	Customer cus;

	public UpdCusDialog(Customer cus) {
		this.cus = cus;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("\u4FEE\u6539\u5BA2\u6237\u4FE1\u606F");
		setResizable(false);
		setSize(629, 423);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		init();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public void init() {
		cm = new CustomerModel();
		JLabel jlName = new JLabel("\u59D3\u540D\uFF1A");

		jtfName = new JTextField();
		jtfName.setColumns(10);
		jtfName.setText(cus.getName());
		JLabel jlSex = new JLabel("\u6027\u522B\uFF1A");

		jrbM = new JRadioButton("\u7537");
		jrbF = new JRadioButton("\u5973");
		bgSex = new ButtonGroup();
		bgSex.add(jrbF);
		bgSex.add(jrbM);
		if (cus.getSex().equals("男")) {
			jrbM.setSelected(true);
		} else {
			jrbF.setSelected(true);
		}

		JLabel jlHome = new JLabel("\u4F4F\u5740\uFF1A");

		jtfHome = new JTextField();
		jtfHome.setColumns(10);
		jtfHome.setText(cus.getHome());
		JLabel jlPhone = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");

		jtfPhone = new JTextField();
		jtfPhone.setColumns(10);
		jtfPhone.setText(cus.getPhone());
		JTextArea textArea = new JTextArea();

		JTextArea textArea_1 = new JTextArea();

		JTextArea textArea_2 = new JTextArea();

		JLabel jlCardId = new JLabel("\u8D35\u5BBE\u5361\u53F7\uFF1A");
		JLabel jlCardIdData = new JLabel(cus.getCardId());
		
		jbUpdate = new JButton("\u4FEE\u6539");
		jbUpdate.addActionListener(this);
		jbUpdate.setActionCommand("update");

		jbCancel = new JButton("\u53D6\u6D88");
		jbCancel.addActionListener(this);
		jbCancel.setActionCommand("cancel");
		
		
		JLabel label_14 = new JLabel("");
		JButton jbCancel_1 = new JButton("\u53D6\u6D88");
		
		JButton jbCancel_2 = new JButton("\u53D6\u6D88");
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addGap(60)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(jlPhone)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jtfPhone, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(jlName)
										.addComponent(jlSex))
									.addGap(36)
									.addComponent(jtfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(215)
									.addComponent(label_14))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(jlHome)
									.addGap(36)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGap(457)
											.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(475)
											.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGap(589)
											.addComponent(jbCancel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPanel.createSequentialGroup()
													.addComponent(jrbM)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(jrbF))
												.addComponent(jtfHome, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)))))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jlCardId)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jlCardIdData, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))))
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addGap(180)
							.addComponent(jbUpdate, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(jbCancel_2, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
					.addGap(29)
					.addComponent(jbCancel_1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlName)
						.addComponent(jtfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_14))
					.addGap(28)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlSex)
						.addComponent(jrbM)
						.addComponent(jrbF))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlHome)
						.addComponent(jtfHome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlPhone)
						.addComponent(jtfPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(89)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jbUpdate, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(jbCancel_1, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(jbCancel_2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addGap(105)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(33)
									.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGap(18)
							.addComponent(jbCancel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlCardId)
								.addComponent(jlCardIdData)))))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "update":
			String name = jtfName.getText().trim();
			String sex = jrbM.isSelected() ? "男" : "女";
			String home = jtfHome.getText().trim();
			String phone = jtfPhone.getText().trim();
			if (!phone.matches("[0-9]{6,}")) {
				showMsg("WrongLen");
				break;
			}
			if (name.equals("") || sex.equals("") || home.equals("") || phone.equals("")) {
				showMsg("Empty");
				break;
			}
			Customer customer = new Customer(cus.getCardId(), name, sex, home, phone, cus.getRegTime());
			cm.updateCustomer(customer);
			showMsg("Success");
			this.dispose();
			break;
		case "cancel":
			this.dispose();
			break;
		}
	}

	public void showMsg(String msg) {
		switch (msg) {
		case "Empty":
			JOptionPane.showMessageDialog(this, "不可为空");
			break;
		case "Success":
			JOptionPane.showMessageDialog(this, "修改成功");
			break;
		case "WrongLen":
			JOptionPane.showMessageDialog(this, "联系方式格式错误,请输入6位及以上数字");
			break;
		}
	}

}
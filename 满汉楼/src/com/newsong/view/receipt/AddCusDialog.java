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
public class AddCusDialog extends JDialog  implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField jtfName;
	private JTextField jtfHome;
	private JTextField jtfPhone;
	private ButtonGroup bgSex;
	JRadioButton jrbM;
	JRadioButton jrbF;
	JButton jbConfirm;
	JButton jbCancel;
	CustomerModel cm;


	public AddCusDialog() {

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("\u65B0\u5BA2\u6237\u767B\u8BB0");
		setResizable(false);
		setSize( 629, 423);
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

		JLabel jlSex = new JLabel("\u6027\u522B\uFF1A");

		jrbM = new JRadioButton("\u7537");

		jrbF = new JRadioButton("\u5973");
		bgSex = new ButtonGroup();
		bgSex.add(jrbF);
		bgSex.add(jrbM);
		JLabel jlHome = new JLabel("\u4F4F\u5740\uFF1A");

		jtfHome = new JTextField();
		jtfHome.setColumns(10);

		JLabel jlPhone = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");

		jtfPhone = new JTextField();
		jtfPhone.setColumns(10);

		JTextArea textArea = new JTextArea();

		JTextArea textArea_1 = new JTextArea();

		JTextArea textArea_2 = new JTextArea();

		jbConfirm = new JButton("\u6DFB\u52A0");
		jbConfirm.addActionListener(this);
		jbConfirm.setActionCommand("confirm");
		JLabel label_14 = new JLabel("");
		
		jbCancel = new JButton("\u53D6\u6D88");
		jbCancel.setActionCommand("cancel");
		jbCancel.addActionListener(this);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
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
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPanel.createSequentialGroup()
													.addComponent(jrbM)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(jrbF))
												.addComponent(jtfHome, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)))))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(180)
							.addComponent(jbConfirm, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(jbCancel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
					.addGap(89)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbConfirm, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(jbCancel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
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
					.addGap(53))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "confirm":
			String name = jtfName.getText().trim();
			String sex = jrbM.isSelected() ? "男":"女";
			String home = jtfHome.getText().trim();
			String phone = jtfPhone.getText().trim();
			if(!phone.matches("[0-9]{6,}")){
				showMsg("WrongLen");
				break;
			}  
			Date regDate = new Date();
			if(name.equals("")||sex.equals("")||home.equals("")||phone.equals("")){
				showMsg("Empty");
				break;
			}
			String cardId = generateCardId(regDate,phone);
			Customer cus = new Customer(cardId,name,sex,home,phone,regDate);
			cm.addCustomer(cus);
			showMsg("添加成功，您的贵宾卡号为"+cardId);
			this.dispose();
			break;
		case "cancel":
			this.dispose();
			break;
		}
	}
	
	public void showMsg(String msg){
		if(msg.startsWith("添加")) {
			JOptionPane.showMessageDialog(this,msg);
			return;
		}
		switch(msg){
		case "Empty":
			JOptionPane.showMessageDialog(this, "不可为空");
			break;

		case "WrongLen":
			JOptionPane.showMessageDialog(this, "联系方式格式错误,请输入6位及以上数字");
			break;
		}
	}
	
	//根据时间和手机号来生成一个12位卡号
	public  String generateCardId(Date date,String phone) {
		
		String left = (""+date.getTime()).substring(7);
		String right = phone.substring(phone.length()-6,phone.length());
		return left+right;
	}

}

package com.newsong.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.newsong.JavaBean.Food;
import com.newsong.model.FoodModel;

@SuppressWarnings("all")
public class AddFoodDialog extends JDialog implements ActionListener {
	FoodModel fm;
	JLabel jlName;
	JTextField jtfName;
	JLabel jlPrice;
	JTextField jtfPrice;
	JButton jbConfirm;
	JButton jbCancel;

	public AddFoodDialog(JFrame superFrame, String title, boolean model) {
		super(superFrame, title, model);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		fm = new FoodModel();
		jlName = new JLabel("菜品名称:");
		jtfName = new JTextField(20);
		jlPrice = new JLabel("菜品价格:");
		jtfPrice = new JTextField(20);

		jbConfirm = new JButton("确定");
		jbConfirm.setActionCommand("confirm");
		jbConfirm.addActionListener(this);

		jbCancel = new JButton("取消");
		jbCancel.setActionCommand("cancel");
		jbCancel.addActionListener(this);

		this.add(jlName);
		this.add(jtfName);
		this.add(jlPrice);
		this.add(jtfPrice);
		this.add(jbConfirm);
		this.add(jbCancel);

		this.setSize(280, 150);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "confirm":
			String name = jtfName.getText().trim();
			String price = jtfPrice.getText().trim();
			if (name.equals("") || price.equals("") ) {
				showMsg("Empty");
				break;
			}
			double p = 0;
			try{
				p = Double.parseDouble(price);
			}catch(NumberFormatException e1){
				e1.printStackTrace();
				showMsg("FormatWrong");
				break;
			}
			Food food = new Food(name, p);
			fm.addFood(food);
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
			JOptionPane.showMessageDialog(this, "添加成功");
			break;
		case "FormatWrong":
			JOptionPane.showMessageDialog(this, "价格必须为数字");
			break;
		}
	}
}

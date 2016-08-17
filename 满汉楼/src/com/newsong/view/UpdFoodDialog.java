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
public class UpdFoodDialog extends JDialog implements ActionListener {
	FoodModel fm;
	JLabel jlName;
	JTextField jtfName;
	JLabel jlPrice;
	JTextField jtfPrice;
	JButton jbConfirm;
	JButton jbCancel;
	Food food ;
	
	public UpdFoodDialog(JFrame superFrame, String title, boolean model,Food food ) {
		super(superFrame, title, model);
		this.food = food;
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		fm = new FoodModel();
		jlName = new JLabel("��Ʒ����:");
		jtfName = new JTextField(20);
		jtfName.setText(food.getName());
		jlPrice = new JLabel("��Ʒ�۸�:");
		jtfPrice = new JTextField(20);
		jtfPrice.setText(""+food.getPrice());
		
		jbConfirm = new JButton("ȷ��");
		jbConfirm.setActionCommand("confirm");
		jbConfirm.addActionListener(this);

		jbCancel = new JButton("ȡ��");
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
			Food food = new Food(this.food.getId(),name, p);
			fm.updateFood(food);
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
			JOptionPane.showMessageDialog(this, "����Ϊ��");
			break;
		case "Success":
			JOptionPane.showMessageDialog(this, "�޸ĳɹ�");
			break;
		case "FormatWrong":
			JOptionPane.showMessageDialog(this, "�۸����Ϊ����");
			break;
		}
	}
}
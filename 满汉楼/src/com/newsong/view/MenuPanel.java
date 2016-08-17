package com.newsong.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.newsong.JavaBean.Food;
import com.newsong.model.EmployeeModel;
import com.newsong.model.FoodModel;
@SuppressWarnings("all")
public class MenuPanel extends JPanel implements ActionListener  {
	JFrame superFrame;
	FoodModel fm;
	JPanel jpNorth;
	JLabel jlItem;
	JComboBox jcb;
	Object[] choices = { "编号", "菜名" };
	JTextField jtf;
	JButton jbSearch;
	JButton jbReturn;
	
	JTable jt;
	JScrollPane jsp;
	
	JPanel jpSouth;
	JPanel jpSouthEast;
	JLabel jlCount;
	JButton jbRefresh;
	JButton jbAdd;
	JButton jbUpdate;
	JButton jbDelete;
	AddFoodDialog addDialog;
	UpdFoodDialog updDiaglog;
	
	public MenuPanel(JFrame superFrame) {
		fm = new FoodModel();
		this.superFrame = superFrame;
		this.setLayout(new BorderLayout());
		jpNorth = new JPanel();
		jpNorth.setLayout(new FlowLayout());
		jlItem = new JLabel("选择查询项目");
		jcb = new JComboBox<>(choices);
		jtf = new JTextField(10);
		jbSearch = new JButton("查询");
		jbSearch.setActionCommand("search");
		jbSearch.addActionListener(this);
		jbReturn = new JButton("返回");
		jbReturn.setActionCommand("return");
		jbReturn.addActionListener(this);
		
		jpNorth.add(jlItem);
		jpNorth.add(jcb);
		jpNorth.add(jtf);
		jpNorth.add(jbSearch);
		jpNorth.add(jbReturn);
		this.add(jpNorth, BorderLayout.NORTH);
		
		fm.findAllFoods();
		jt = new JTable(fm);
		jt.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(jt.getSelectedRow());
				if(jt.getSelectedRow() !=-1 ){
					jbUpdate.setEnabled(true);
					jbDelete.setEnabled(true);
				}
			}
		});
		
		jt.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jsp = new JScrollPane(jt);
		this.add(jsp, BorderLayout.CENTER);
		jpSouth = new JPanel();
		jpSouth.setLayout(new BorderLayout());
		jpSouthEast = new JPanel();
		jpSouthEast.setLayout(new FlowLayout());

		jlCount = new JLabel("当前数据库共有 " + fm.getCount() + " 条记录");
		jbRefresh = new JButton("刷新");
		jbRefresh.setActionCommand("refresh");
		jbRefresh.addActionListener(this);
		
		jbAdd = new JButton("添加菜品");
		jbAdd.setActionCommand("add");
		jbAdd.addActionListener(this);
		
		jbUpdate = new JButton("修改菜品信息");
		jbUpdate.setActionCommand("update");
		jbUpdate.addActionListener(this);
		jbUpdate.setEnabled(false);
		
		jbDelete = new JButton("删除菜品");
		jbDelete.setActionCommand("delete");
		jbDelete.addActionListener(this);
		jbDelete.setEnabled(false);
		
		jpSouthEast.add(jbRefresh);
		jpSouthEast.add(jbAdd);
		jpSouthEast.add(jbUpdate);
		jpSouthEast.add(jbDelete);
		jpSouth.add(jlCount, BorderLayout.WEST);
		jpSouth.add(jpSouthEast, BorderLayout.EAST);
		this.add(jpSouth, BorderLayout.SOUTH);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "search":
			String criteria = jtf.getText().trim();
			if (criteria.equals("")) {
				showMsg("NoCriteria");
				break;
			}
			fm = new FoodModel();
			switch ((String) jcb.getSelectedItem()) {
			case "编号":
				fm.findFoodId(criteria);
				break;
			case "菜名":
				fm.findFoodName(criteria);
				break;
			}
			jt.setModel(fm);
			break;
		case "return":
		case "refresh":
			fm = new FoodModel();
			fm.findAllFoods();
			jt.setModel(fm);
			break;
		case "add":
			addDialog = new AddFoodDialog(superFrame,"添加菜品",true);
			addDialog.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					fm = new FoodModel();
					fm.findAllFoods();
					jt.setModel(fm);
					jlCount.setText("当前数据库共有 " + fm.getCount() + " 条记录");
				}
			});
			break;
		case "update":
			fm = new FoodModel();
			Food food = fm.findFoodId((String)jt.getValueAt(jt.getSelectedRow(),0));
			new UpdFoodDialog(superFrame,"修改菜品信息",true,food);
			fm = new FoodModel();
			fm.findAllFoods();
			jt.setModel(fm);
			break;
		case "delete":
			fm.deleteFood((String)jt.getValueAt(jt.getSelectedRow(), 0));
			fm = new FoodModel();
			fm.findAllFoods();
			jt.setModel(fm);
			jlCount.setText("当前数据库共有 " + fm.getCount() + " 条记录");
			break;
		}
		setUnable();
	}

	public void showMsg(String msg) {
		switch (msg) {
		case "NotChoose":
			JOptionPane.showMessageDialog(this, "尚未选中员工");
			break;
		case "NoCriteria":
			JOptionPane.showMessageDialog(this, "请输入查询条件");
			break;
		}
		setUnable();
	}
	public void setUnable(){
		jbUpdate.setEnabled(false);
		jbDelete.setEnabled(false);
	}
}
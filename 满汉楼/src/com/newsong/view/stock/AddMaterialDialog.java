package com.newsong.view.stock;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.newsong.JavaBean.Stock;
import com.newsong.model.StockModel;
import com.newsong.model.SupplierModel;
@SuppressWarnings("all")
public class AddMaterialDialog extends JDialog implements ActionListener {
	
	private final JPanel contentPanel = new JPanel();
	private JTextField jtfName;
	private JTextField jtfUnit;
	JLabel jlName ;
	JLabel jlUnit;
	JLabel jlSupplier;
	JComboBox jcbSupplier;
	JButton jbConfirm;
	JButton jbCancel;
	StockModel sm ;
	SupplierModel slm;
	Object [] supplier ;
	Map<String,Integer> map;
	
	public AddMaterialDialog() {
		sm = new StockModel();
		
		slm = new SupplierModel();
		map = slm.findAllToMap();
		supplier = new Object[map.size()];
		supplier = map.keySet().toArray(new String[0]);
		
		setTitle("\u6DFB\u52A0\u539F\u6599");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 370, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		jlName = new JLabel("\u539F\u6599\u540D\u79F0:");
		
		jtfName = new JTextField();
		jtfName.setColumns(10);
		
		JLabel jlUnit = new JLabel("\u539F\u6599\u5355\u4F4D:");
		
		jtfUnit = new JTextField();
		jtfUnit.setColumns(10);
		
		jlSupplier = new JLabel("\u4F9B\u5E94\u5546:");
		
		jcbSupplier = new JComboBox(supplier);
		
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
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(41)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(jlName)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(jtfName, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(jlUnit)
										.addComponent(jlSupplier))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(jcbSupplier, 0, 114, Short.MAX_VALUE)
										.addComponent(jtfUnit, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(jbConfirm)
							.addGap(18)
							.addComponent(jbCancel)))
					.addContainerGap(105, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlName)
						.addComponent(jtfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlUnit)
						.addComponent(jtfUnit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlSupplier)
						.addComponent(jcbSupplier, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbConfirm)
						.addComponent(jbCancel))
					.addContainerGap())
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
			String unit = jtfUnit.getText().trim();
			String supName = (String)jcbSupplier.getSelectedItem();
			if(name.equals("")||unit.equals("")||supName.equals("")) {
				showMsg("Empty");
				break;
			}
			int supId = map.get(supName);
			Stock stock =new Stock(name, unit, supId);
			sm.addStock(stock);
			showMsg("Success");
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
			JOptionPane.showMessageDialog(this, "添加成功");
			break;
		}
	}
}

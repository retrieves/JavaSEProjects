package com.newsong.view.receipt;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.newsong.JavaBean.BookItem;
import com.newsong.model.BookItemDAOImpl;
import com.newsong.model.DeskDAOImpl;

@SuppressWarnings("all")
public class SubscribeDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField jtfNum;
	private JTextField jtfPhone;
	JLabel jlTime ;
	JComboBox jcbMonth;
	JLabel jlMonth ;
	JComboBox jcbDay;
	JLabel jlDay;
	JComboBox jcbStart;
	JLabel jlNum;
	JLabel jlPhone;
	JButton jbConfirm;
	JButton jbCancel;
	JLabel jlDesk;
	JLabel jlDeskId;
	private Object[] month = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
	private Object[] day = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private Object[] time = {"9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00"};
	private JLabel jlName;
	private JTextField jtfName;
	int deskId;
	BookItemDAOImpl bookDAOImpl;
	DeskDAOImpl  deskDAOImpl;
	boolean isSucc;
	
	
	
	public SubscribeDialog(int deskId) {
		bookDAOImpl = new BookItemDAOImpl();
		deskDAOImpl = new DeskDAOImpl();
		this.deskId = deskId;
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		setTitle("\u9884\u5B9A\u4F4D\u7F6E");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 414, 349);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		jlTime = new JLabel("\u9884\u5B9A\u65F6\u95F4:");
		
		jcbMonth = new JComboBox(month);
		
		jlMonth = new JLabel("\u6708");
		
		jcbDay = new JComboBox(day);
		
		jlDay = new JLabel("\u65E5");
		
		jcbStart = new JComboBox(time);
		
		jlNum = new JLabel("\u9884\u5B9A\u4EBA\u6570:");
		
		jtfNum = new JTextField();
		jtfNum.setColumns(10);
		
		jlPhone = new JLabel("\u8054\u7CFB\u7535\u8BDD:");
		
		jtfPhone = new JTextField();
		jtfPhone.setColumns(10);
		
		jbConfirm = new JButton("\u786E\u5B9A");
		jbConfirm.setActionCommand("confirm");
		jbConfirm.addActionListener(this);
		jbCancel = new JButton("\u53D6\u6D88");
		jbCancel.setActionCommand("cancel");
		jbCancel.addActionListener(this);
		
		jlDesk = new JLabel("\u9884\u5B9A\u684C\u53F7:");
		jlDeskId = new JLabel(""+deskId);
		
		jlName = new JLabel("\u9884\u5B9A\u4EBA\u59D3\u540D:");
		
		jtfName = new JTextField();
		jtfName.setColumns(10);
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jlTime)
							.addGap(18)
							.addComponent(jcbMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jlMonth)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jcbDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jlDay)
							.addGap(18)
							.addComponent(jcbStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(jlNum)
									.addGap(18)
									.addComponent(jtfNum))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGap(74)
											.addComponent(jbConfirm))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(jlDesk)
											.addGap(18)
											.addComponent(jlDeskId))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(jlPhone)
											.addGap(18)
											.addComponent(jtfPhone))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(jlName)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(jtfName, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)))))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(jbCancel)
							.addGap(104))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlMonth)
						.addComponent(jcbDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jlDay)
						.addComponent(jlTime)
						.addComponent(jcbMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jcbStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlNum)
						.addComponent(jtfNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlName)
						.addComponent(jtfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlPhone)
						.addComponent(jtfPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlDesk)
						.addComponent(jlDeskId))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
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
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "confirm":
			Timestamp mealTime = null;
			int cusNum  = 0;
			try {
				mealTime = Timestamp.valueOf(Calendar.
						getInstance().get(Calendar.YEAR)+"-"+jcbMonth.getSelectedItem()+"-"+jcbDay.getSelectedItem()+" "+jcbStart.getSelectedItem()+":00");
				cusNum = Integer.parseInt(jtfNum.getText().trim());
			} catch(NumberFormatException e2) {
//				e2.printStackTrace();
				showMsg("NumFormat");
				break;
			}
			Timestamp bookTime = new Timestamp(System.currentTimeMillis());
			String cusName = jtfName.getText().trim();
			String bookPhone = jtfPhone.getText().trim();
			if(cusNum <= 0 ) {
				showMsg("NumFormat");
				break;
			}
			if(mealTime == null||cusName.equals("")||bookPhone.equals("")) {
				showMsg("Empty");
				break;
			}
			if(deskDAOImpl.checkDesk(deskId).equals("在用餐")) {
				if(mealTime.equals(new Timestamp(System.currentTimeMillis()))) {
					showMsg("HaveUsed");
					break;
				}
			}
			if(deskDAOImpl.checkDesk(deskId).equals("已预订")) {
				List<BookItem> bookList =  bookDAOImpl.findBookStatus(deskId);
				for (BookItem bookItem : bookList) {
					if(bookItem.getMealTime().equals(mealTime)) {
						showMsg("HaveBooked");
						return;
					}
				}
			}
			BookItem book = new BookItem(cusName, cusNum,bookPhone, this.deskId, bookTime, mealTime);
			bookDAOImpl.addBookItem(book);
			showMsg("Success");
			this.isSucc = true;
			this.dispose();
			break;
		case "cancel":
			this.dispose();
		}
	}
	
	public void showMsg(String msg) {
		switch(msg) {
		case "NumFormat":
			JOptionPane.showMessageDialog(this, "人数格式错误");
			break;
		case "Empty":
			JOptionPane.showMessageDialog(this, "信息不可为空");
			break;
		case "Success":
			JOptionPane.showMessageDialog(this, "预订成功");
			break;
		case "HaveBooked":
			JOptionPane.showMessageDialog(this, "此桌已被预订");
			break;
		case "HaveUsed":
			JOptionPane.showMessageDialog(this, "此桌正在使用");
			break;
		}
	}
	
	public boolean isSucc() {
		return isSucc;
	}
}

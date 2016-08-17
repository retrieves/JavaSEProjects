package com.newsong.view.receipt;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Calendar;
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
public class UnSubscribeDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField jtfNum;
	private JTextField jtfPhone;
	JLabel jlTime;
	JLabel jlMonth;
	JLabel jlDay;
	JLabel jlNum;
	JLabel jlPhone;
	JButton jbConfirm;
	JButton jbCancel;
	JButton jbSearch;
	JLabel jlDesk;
	JLabel jlDeskId;
	private JLabel jlName;
	private JTextField jtfName;
	int deskId;
	BookItemDAOImpl bookDAOImpl;
	DeskDAOImpl deskDAOImpl;
	boolean isSucc;
	BookItem book;
	JComboBox jcbMonth;
	JComboBox jcbDay;
	JComboBox jcbStart;
	private Object[] month = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
	private Object[] day = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private Object[] time = { "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00",
			"19:00", "20:00", "21:00" };

	public UnSubscribeDialog(int deskId) {
		this.deskId = deskId;
		bookDAOImpl = new BookItemDAOImpl();
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		setTitle("\u9000\u8BA2\u4F4D\u7F6E");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 482, 349);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		jlTime = new JLabel("\u8BF7\u8F93\u5165\u9884\u5B9A\u65F6\u95F4:");

		jlMonth = new JLabel("\u6708");

		jlDay = new JLabel("\u65E5");

		jlNum = new JLabel("\u9884\u5B9A\u4EBA\u6570:");

		jtfNum = new JTextField();
		jtfNum.setEditable(false);
		jtfNum.setColumns(10);

		jlPhone = new JLabel("\u8054\u7CFB\u7535\u8BDD:");

		jtfPhone = new JTextField();
		jtfPhone.setColumns(10);
		jtfPhone.setEditable(false);

		jbConfirm = new JButton("\u786E\u5B9A");
		jbConfirm.setActionCommand("confirm");
		jbConfirm.addActionListener(this);
		jbCancel = new JButton("\u53D6\u6D88");
		jbCancel.setActionCommand("cancel");
		jbCancel.addActionListener(this);

		jlDesk = new JLabel("\u9000\u8BA2\u684C\u53F7:");
		jlDeskId = new JLabel("" + deskId);

		jlName = new JLabel("\u9884\u5B9A\u4EBA\u59D3\u540D:");

		jtfName = new JTextField();
		jtfName.setColumns(10);
		jtfName.setEditable(false);
		jcbMonth = new JComboBox(month);

		jcbDay = new JComboBox(day);

		jcbStart = new JComboBox(time);

		jbSearch = new JButton("\u67E5\u8BE2");
		jbSearch.setActionCommand("search");
		jbSearch.addActionListener(this);

		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPanel.createSequentialGroup().addGap(28).addGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPanel.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jlTime).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jcbMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(jlMonth)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jcbDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(jlDay)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(jcbStart, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(jbSearch).addGap(19))
						.addGroup(gl_contentPanel.createSequentialGroup().addGroup(gl_contentPanel
								.createParallelGroup(Alignment.TRAILING).addComponent(jbConfirm)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(gl_contentPanel.createSequentialGroup().addComponent(jlNum).addGap(18)
												.addComponent(jtfNum))
										.addGroup(gl_contentPanel.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED).addGroup(gl_contentPanel
														.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_contentPanel.createSequentialGroup()
																.addComponent(jlDesk).addGap(18).addComponent(jlDeskId))
														.addGroup(gl_contentPanel.createSequentialGroup()
																.addComponent(jlPhone).addGap(18).addComponent(
																		jtfPhone))
														.addGroup(gl_contentPanel.createSequentialGroup()
																.addComponent(jlName)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(jtfName, GroupLayout.PREFERRED_SIZE, 95,
																		GroupLayout.PREFERRED_SIZE))))))
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jbCancel).addGap(132)))));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(33)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(jlMonth)
								.addComponent(jlTime)
								.addComponent(jcbMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jcbDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jlDay)
								.addComponent(jcbStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jbSearch))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(jlNum)
								.addComponent(jtfNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(jlName)
								.addComponent(jtfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(20)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(jlPhone)
								.addComponent(jtfPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(jlDesk)
								.addComponent(jlDeskId))
						.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE).addGroup(gl_contentPanel
								.createParallelGroup(Alignment.BASELINE).addComponent(jbCancel).addComponent(jbConfirm))
						.addContainerGap()));
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
		switch (e.getActionCommand()) {
		case "search":
			Timestamp mealTime = null;
			try {	
				mealTime = Timestamp
						.valueOf(Calendar.getInstance().get(Calendar.YEAR) + "-" + jcbMonth.getSelectedItem() + "-"
								+ jcbDay.getSelectedItem() + " " + jcbStart.getSelectedItem() + ":00");
				System.out.println(mealTime);
			} catch (NumberFormatException e2) {
				e2.printStackTrace();
				showMsg("NumFormat");
				break;
			}
			book = bookDAOImpl.findBookItem(deskId, mealTime);
			if (book == null) {
				showMsg("NotFound");
				break;
			}
			jtfName.setText(book.getCusName());
			jtfNum.setText("" + book.getCusNum());
			jtfPhone.setText(book.getCusPhone());
			break;
		case "confirm":
			if (book != null) {
				bookDAOImpl.deleteBookItem(book.getId());
				showMsg("Success");
				this.isSucc = true;
				this.dispose();
			}
		case "cancel":
			this.dispose();
		}
	}

	public void showMsg(String msg) {
		switch (msg) {
		case "NumFormat":
			JOptionPane.showMessageDialog(this, "日期格式错误");
			break;
		case "Success":
			JOptionPane.showMessageDialog(this, "退订成功");
			break;
		case "NotFound":
			JOptionPane.showMessageDialog(this, "没有找到该时间的预订记录");
			break;
		}
	}

	public boolean isSucc() {
		return isSucc;
	}

	public static void main(String[] args) {
		UnSubscribeDialog dd = new UnSubscribeDialog(3);

	}
}

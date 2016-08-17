package com.newsong.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import com.newsong.JavaBean.Employee;

@SuppressWarnings("all")
public class DetailEmpDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField jtfName;
	private JTextField jtfId;
	private JTextField jtfHome;
	private JTextField jtfPhone;
	private JTextField jtfCellPhone;
	private JTextField jtfEmail;
	private JLabel jlMarriedData;
	private JLabel jlSexData;
	
	JFileChooser jfc;
	JTextArea jtaNote;
	JButton jbConfirm;
	JLabel jlPic;
	String picPath  = "";
	Employee emp;
	public DetailEmpDialog(Employee emp) {
		this.emp = emp;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("\u65B0\u5458\u5DE5\u767B\u8BB0");
		setResizable(false);
		setSize( 701, 744);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		init();
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public void init() {
		JLabel jlName = new JLabel("\u59D3\u540D\uFF1A");

		jtfName = new JTextField();
		jtfName.setEditable(false);
		jtfName.setColumns(10);
		jtfName.setText(emp.getName());
		JLabel jlSex = new JLabel("\u6027\u522B\uFF1A");
		
		JLabel jlYearData = new JLabel();
		
		JLabel jlMonthData = new JLabel();
		
		JLabel jlDayData = new JLabel();
		
		JLabel jlDegreeData = new JLabel();
		
		JLabel jlJobData = new JLabel();	
		jlJobData.setText(emp.getJob());
		JLabel jlBirthdate = new JLabel("\u51FA\u751F\u65E5\u671F\uFF1A");
		System.out.println(emp.getBirthday());
		Calendar birthdate = Calendar.getInstance();
		birthdate.setTimeInMillis(emp.getBirthday().getTime());
		JLabel jlYear = new JLabel("\u5E74");
		jlYearData.setText(""+birthdate.get(Calendar.YEAR));
		JLabel jlMonth = new JLabel("\u6708");
		jlMonthData.setText(""+birthdate.get(Calendar.MONTH));
		JLabel jlDay = new JLabel("\u65E5");
		jlDayData.setText(""+birthdate.get(Calendar.DAY_OF_MONTH));
		
		JLabel jlId = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");

		jtfId = new JTextField();
		jtfId.setText(emp.getId());
		jtfId.setColumns(10);
		jtfId.setEditable(false);
		
		JLabel jlDegree = new JLabel("\u5B66\u5386\uFF1A");
		jlDegreeData.setText(emp.getDegree());
		
		JLabel jlMarried = new JLabel("\u5A5A\u5426\uFF1A");
		
		jlMarriedData = new JLabel(emp.getMarriageStatus());
		JLabel jlHome = new JLabel("\u4F4F\u5740\uFF1A");

		jtfHome = new JTextField();
		jtfHome.setColumns(10);
		jtfHome.setEditable(false);
		jtfHome.setText(emp.getHome());
		JLabel jlPhone = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");

		jtfPhone = new JTextField();
		jtfPhone.setColumns(10);
		jtfPhone.setEditable(false);
		jtfPhone.setText(emp.getPhoneNum());
		JLabel jlCellPhone = new JLabel("\u624B\u673A\uFF1A");

		jtfCellPhone = new JTextField();
		jtfCellPhone.setColumns(10);
		jtfCellPhone.setEditable(false);
		jtfCellPhone.setText(emp.getCellPhoneNum());
		JLabel jlEmail = new JLabel("\u90AE\u7BB1\uFF1A");

		jtfEmail = new JTextField();
		jtfEmail.setEditable(false);
		jtfEmail.setColumns(10);
		jtfEmail.setText(emp.getEmail());
		
		JLabel jlJob = new JLabel("\u804C\u4F4D\uFF1A");
		JLabel jlNote = new JLabel("\u5907\u6CE8\uFF1A");
		
		JTextArea textArea = new JTextArea();

		JTextArea textArea_1 = new JTextArea();

		JTextArea textArea_2 = new JTextArea();

		jtaNote = new JTextArea();
		jtaNote.setEditable(false);
		jtaNote.setBackground(Color.LIGHT_GRAY);
		jtaNote.setText(emp.getNote());
		jlSexData = new JLabel(emp.getSex());
		jbConfirm = new JButton("\u786E\u5B9A");
		jbConfirm.addActionListener(this);
		jbConfirm.setActionCommand("confirm");
		JLabel label_14 = new JLabel();

		jlPic = new JLabel();
		jlPic.setIcon(new ImageIcon(new ImageIcon(emp.getImg()).getImage().getScaledInstance(155, 205, Image.SCALE_DEFAULT)));
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(60)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(jlName)
										.addComponent(jlSex))
									.addGap(36)
									.addComponent(jtfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(215)
									.addComponent(label_14))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(jlBirthdate)
										.addComponent(jlPhone)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(jlId))
										.addComponent(jlDegree)
										.addComponent(jlMarried)
										.addComponent(jlHome)
										.addComponent(jlEmail)
										.addComponent(jlJob)
										.addComponent(jlNote))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(jlJobData)
										.addComponent(jlMarriedData)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(jtaNote, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(475)
											.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(jtfEmail, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
										.addComponent(jtfHome, GroupLayout.PREFERRED_SIZE, 535, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(jtfPhone, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
											.addGap(130)
											.addComponent(jlCellPhone)
											.addGap(18)
											.addComponent(jtfCellPhone, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(jlDegreeData)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
													.addComponent(jtfId, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
													.addGroup(gl_contentPanel.createSequentialGroup()
														.addGap(1)
														.addComponent(jlYearData)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(jlYear)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(jlMonthData, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(jlMonth, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(jlDayData, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(jlDay))
													.addComponent(jlSexData)))
											.addGap(139)
											.addComponent(jlPic, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(275)
							.addComponent(jbConfirm, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlName)
								.addComponent(jtfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_14))
							.addGap(32)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlSex)
								.addComponent(jlSexData))
							.addGap(28)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlBirthdate)
								.addComponent(jlYearData)
								.addComponent(jlYear)
								.addComponent(jlMonthData)
								.addComponent(jlDayData)
								.addComponent(jlDay)
								.addComponent(jlMonth))
							.addGap(36)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlId)
								.addComponent(jtfId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(24)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlDegree)
								.addComponent(jlDegreeData))
							.addGap(26)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlMarried)
								.addComponent(jlMarriedData)))
						.addComponent(jlPic, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(jlHome)
						.addComponent(jtfHome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlPhone)
						.addComponent(jlCellPhone)
						.addComponent(jtfCellPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jtfPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlEmail)
						.addComponent(jtfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlJob)
						.addComponent(jlJobData))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(33)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(38)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
									.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(jtaNote, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
								.addComponent(jlNote))))
					.addGap(18)
					.addComponent(jbConfirm, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}
	
}

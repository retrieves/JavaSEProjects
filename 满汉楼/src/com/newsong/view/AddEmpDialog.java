package com.newsong.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.newsong.JavaBean.Employee;
import com.newsong.model.EmployeeModel;

@SuppressWarnings("all")
public class AddEmpDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField jtfName;
	private JTextField jtfId;
	private JTextField jtfHome;
	private JTextField jtfPhone;
	private JTextField jtfCellPhone;
	private JTextField jtfEmail;
	private ButtonGroup bgSex;
	private ButtonGroup bgMarried;
	JFileChooser jfc;
	JRadioButton jrbM;
	JRadioButton jrbF;
	JComboBox jcbYear;
	JComboBox jcbMonth;
	JComboBox jcbDay;
	JComboBox jcbDegree;
	JRadioButton jrbUnMarried;
	JRadioButton jrbMarried;
	JComboBox jcbJob;
	JTextArea jtaNote;
	JButton jbConfirm;
	JButton jbCancel;
	JLabel jlPic;
	JButton jbAddPic;
	String picPath  = "";
	EmployeeModel em;
	private Object[] job = { "主管", "经理", "服务员", "收银员", "厨师", "帮厨" };
	private Object[] year = { "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000",
			"2001", "2002" };
	private Object[] month = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
	private Object[] day = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	private Object[] degree = {"高中","大专","本科","硕士"};

	/**
	 * Create the dialog.
	 */
	public AddEmpDialog() {

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
		em = new EmployeeModel();
		JLabel jlName = new JLabel("\u59D3\u540D\uFF1A");

		jtfName = new JTextField();
		jtfName.setColumns(10);

		JLabel jlSex = new JLabel("\u6027\u522B\uFF1A");

		jrbM = new JRadioButton("\u7537");

		jrbF = new JRadioButton("\u5973");
		bgSex = new ButtonGroup();
		bgSex.add(jrbF);
		bgSex.add(jrbM);
		JLabel jlBirthdate = new JLabel("\u51FA\u751F\u65E5\u671F\uFF1A");

		jcbYear = new JComboBox(year);

		JLabel jlYear = new JLabel("\u5E74");

		jcbMonth = new JComboBox(month);

		JLabel jlMonth = new JLabel("\u6708");

		jcbDay = new JComboBox(day);

		JLabel jlDay = new JLabel("\u65E5");

		JLabel jlId = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");

		jtfId = new JTextField();
		jtfId.setColumns(10);

		JLabel jlDegree = new JLabel("\u5B66\u5386\uFF1A");

		jcbDegree = new JComboBox(degree);

		JLabel jlMarried = new JLabel("\u5A5A\u5426\uFF1A");

		jrbUnMarried = new JRadioButton("\u672A\u5A5A");

		jrbMarried = new JRadioButton("\u5DF2\u5A5A");
		bgMarried = new ButtonGroup();
		bgMarried.add(jrbMarried);
		bgMarried.add(jrbUnMarried);
		JLabel jlHome = new JLabel("\u4F4F\u5740\uFF1A");

		jtfHome = new JTextField();
		jtfHome.setColumns(10);

		JLabel jlPhone = new JLabel("\u8054\u7CFB\u7535\u8BDD\uFF1A");

		jtfPhone = new JTextField();
		jtfPhone.setColumns(10);

		JLabel jlCellPhone = new JLabel("\u624B\u673A\uFF1A");

		jtfCellPhone = new JTextField();
		jtfCellPhone.setColumns(10);

		JLabel jlEmail = new JLabel("\u90AE\u7BB1\uFF1A");

		jtfEmail = new JTextField();
		jtfEmail.setColumns(10);

		JLabel jlJob = new JLabel("\u804C\u4F4D\uFF1A");

		jcbJob = new JComboBox(job);
		JLabel jlNote = new JLabel("\u5907\u6CE8\uFF1A");

		JTextArea textArea = new JTextArea();

		JTextArea textArea_1 = new JTextArea();

		JTextArea textArea_2 = new JTextArea();

		jtaNote = new JTextArea();
		jtaNote.setBackground(Color.LIGHT_GRAY);

		jbConfirm = new JButton("\u6DFB\u52A0");
		jbConfirm.addActionListener(this);
		jbConfirm.setActionCommand("confirm");
		
		jbCancel = new JButton("\u53D6\u6D88");
		jbCancel.addActionListener(this);
		jbCancel.setActionCommand("cancel");
		JLabel label_14 = new JLabel("");

		jlPic = new JLabel(new ImageIcon("image/openPic.png"));
		jlPic.setSize(155, 205);
		jbAddPic = new JButton("\u6DFB\u52A0\u7167\u7247");
		jbAddPic.addActionListener(this);
		jbAddPic.setActionCommand("addPic");
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(60)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(jlName)
								.addComponent(jlSex))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(36)
									.addComponent(jtfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(28)
									.addComponent(jrbM)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(jrbF)))
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
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(jrbUnMarried)
										.addComponent(jtfPhone, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGap(130)
											.addComponent(jlCellPhone)
											.addGap(18)
											.addComponent(jtfCellPhone, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGap(18)
											.addComponent(jrbMarried))))
								.addComponent(jcbJob, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(26)
									.addComponent(jbConfirm, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
									.addGap(99)
									.addComponent(jbCancel, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(jtfId, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(jcbYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(jlYear)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(jcbMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(jlMonth)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(jcbDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(jlDay))
										.addComponent(jcbDegree, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
									.addGap(123)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(jbAddPic, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jlPic, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(32)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlName)
								.addComponent(jtfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_14))
							.addGap(32)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlSex)
								.addComponent(jrbF)
								.addComponent(jrbM))
							.addGap(28)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlBirthdate)
								.addComponent(jcbYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(jlYear)
								.addComponent(jcbMonth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(jlMonth)
								.addComponent(jcbDay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(jlDay))
							.addGap(36)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlId)
								.addComponent(jtfId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(24)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlDegree)
								.addComponent(jcbDegree, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlMarried)
								.addComponent(jrbUnMarried)
								.addComponent(jrbMarried)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(26)
							.addComponent(jlPic, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jbAddPic)))
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
						.addComponent(jcbJob, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(33)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
									.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(jtaNote, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
								.addComponent(jlNote))))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(jbCancel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(jbConfirm, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
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
		switch (e.getActionCommand()) {
		case "confirm":
			String name = jtfName.getText().trim();
			String sex = jrbM.isSelected() ? "男":"女";
			Date birthdate = null;
			try {
				birthdate = new SimpleDateFormat("yyyy-MM-dd").parse(jcbYear.getSelectedItem()+"-"+jcbMonth.getSelectedItem()+"-"+jcbDay.getSelectedItem());
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			String id = jtfId.getText().trim();
			if(id.length() != 18){
				showMsg("WrongLen");
				break;
			}
			String workId = id.substring(12);
			String degree = (String) jcbDegree.getSelectedItem();
			String isMarried = jrbMarried.isSelected()?"已婚":"未婚";
			String home = jtfHome.getText().trim();
			String phone = jtfPhone.getText().trim();
			String cellPhone = jtfCellPhone.getText().trim();
			String email = jtfEmail.getText().trim();
			String job = (String)jcbJob.getSelectedItem();
			String note = jtaNote.getText().trim();
			String path = this.picPath;
			if(name.equals("")||sex.equals("")||id.equals("")||degree.equals("")||home.equals("")||isMarried.equals("")||phone.equals("")||cellPhone.equals("")||email.equals("")||job.equals("")){
				showMsg("Empty");
				break;
			}
			if(em.findEmployeeId(workId) != null){
				showMsg("haveExisted");
				break;
			}
			Employee emp = new Employee(name,id.substring(12),home,sex,birthdate,new Date(System.currentTimeMillis()), degree,id,path,job,isMarried,phone,cellPhone,email,note);
			em.addEmployee(emp);
			showMsg("Success");
			this.dispose();
			break;
		case "cancel":
			this.dispose();
			break;
		case "addPic":
			jfc = new JFileChooser();
			FileNameExtensionFilter  filter = new FileNameExtensionFilter("图片","jpg","jpeg","gif","png");
			jfc.setFileFilter(filter);
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.showOpenDialog(this);	
			if(jfc.getSelectedFile() != null){
				this.picPath= jfc.getSelectedFile().getAbsolutePath();
				Image img = new ImageIcon(picPath).getImage().getScaledInstance(155,205, Image.SCALE_DEFAULT);
				jlPic.setIcon(new ImageIcon(img));
			}
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
		case "haveExisted":
			JOptionPane.showMessageDialog(this, "该员工已存在");
			break;
		case "WrongLen":
			JOptionPane.showMessageDialog(this, "身份证号尚未填写或格式错误");
			break;
		}
	}

}

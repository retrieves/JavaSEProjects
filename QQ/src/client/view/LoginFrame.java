package client.view;
import client.common.Message;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


@SuppressWarnings("all")
/**
 * ��¼����
 * @author newsong
 *
 */
public class LoginFrame extends JFrame implements ActionListener {
	//����
	JLabel jlImg ;
	
	//�в���һѡ�
	JTabbedPane jtp;
	JPanel jpCenterQQ;
	JLabel jlAccount;
	JLabel jlPWD;
	JTextField jtfAccount;
	JPasswordField jpfPWD;
	JCheckBox jcbHide;
	JCheckBox jcbMemorize;
	JButton jbReg;
	JButton jbForget;
	JButton jbApply;
	//�в��ڶ�ѡ�
	JPanel jpCenterEmail;
	//�в�����ѡ�
	JPanel jpCenterPhone;
	
	//�ϲ�
	JPanel jpSouth;
	JButton jbLogin;
	JButton jbCancel;
	JButton jbHelp;
	
	public LoginFrame() {
		
		jlImg = new JLabel(new ImageIcon("image/head.jpg"));
		//�������
		jtp = new JTabbedPane();
		jpCenterQQ = new JPanel();
		jlAccount = new JLabel("QQ�˺�");
		jlPWD = new JLabel("QQ����");
		jtfAccount = new JTextField(20);
		jpfPWD = new JPasswordField(20);
		jcbHide = new JCheckBox("�����¼");
		jcbMemorize = new JCheckBox("��ס����");
		jbReg = new JButton(new ImageIcon("image/reg.jpg"));
		jbForget = new JButton(new ImageIcon("image/lost.jpg"));
		jbApply = new JButton("�������뱣��");
		jpCenterQQ.setLayout(new GridLayout(3,3));
		jpCenterQQ.add(jlAccount);jpCenterQQ.add(jtfAccount);jpCenterQQ.add(jbReg);jpCenterQQ.add(jlPWD);
		jpCenterQQ.add(jpfPWD);jpCenterQQ.add(jbForget);jpCenterQQ.add(jcbHide);jpCenterQQ.add(jcbMemorize);
		jpCenterQQ.add(jbApply);
		jtp.add("QQ����", jpCenterQQ);
		//��һ��ѡ����
		
		jpCenterEmail = new JPanel();
		jtp.add("��������", jpCenterEmail);
		//�ڶ���ѡ����
		
		jpCenterPhone = new JPanel();
		jtp.add("�ֻ�����", jpCenterPhone);
		//������ѡ����

		//�в����
		jpSouth = new JPanel();
		jpSouth.setLayout(new FlowLayout());
		jbLogin = new JButton(new ImageIcon("image/login.gif"));
		jbLogin.setActionCommand("login");
		jbLogin.addActionListener(this);
		
		jbCancel = new JButton(new ImageIcon("image/cancel.gif"));
		jbCancel.setActionCommand("cancel");
		jbCancel.addActionListener(this);
		
		jbHelp = new JButton("����");
		jpSouth.add(jbLogin);jpSouth.add(jbCancel);jpSouth.add(jbHelp);
		
		//�ϲ����
		this.setLayout(new BorderLayout());
		this.add(jlImg,BorderLayout.NORTH);
		this.add(jtp,BorderLayout.CENTER);
		this.add(jpSouth,BorderLayout.SOUTH);
		this.setTitle("QQ");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setSize(400,320);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("image/smallIcon.png"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		//�������
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "login":
			String username = jtfAccount.getText().trim();
			String password = new String(jpfPWD.getPassword());
			if(username.equals("")||password.equals("")) {
				showMsg("Empty");
				break;
			}
			Message message = new Message(username,password); 
			if(message.validate()) {
				new MainFrame(message);
				this.dispose();
			}else {
				showMsg("Error");
			}
			break;
		case "cancel":
			this.dispose();
			break;
		}
	}
	
	public void showMsg(String msg) {
		switch(msg) {
		case "Empty":
			JOptionPane.showMessageDialog(this, "�û��������벻��Ϊ��");
			break;
		case "Error":
			JOptionPane.showMessageDialog(this, "�û������������");
			break;
		}
	}
	
	
	public static void main(String[] args) {
		new LoginFrame();
	}
}

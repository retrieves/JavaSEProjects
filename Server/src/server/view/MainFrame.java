package server.view;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import server.common.ServMessage;
@SuppressWarnings("all")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTable jtDept;
	private JTextField jtfId;
	private JTextField jtfName;
	private JTable jtUser;
	
	
	public static void main(String[] args) {
		new MainFrame();
	}


	public MainFrame() {
		setTitle("QQ\u670D\u52A1\u5668");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize( 659, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 638, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 494, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JPanel jpLive = new JPanel();
		tabbedPane.addTab("\u5728\u7EBF\u7528\u6237\u7BA1\u7406", null, jpLive, null);
		
		JPanel jpLiveServer = new JPanel();
		jpLiveServer.setBorder(new TitledBorder(null, "\u670D\u52A1\u5668", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel jpLiveUser = new JPanel();
		jpLiveUser.setBorder(new TitledBorder(null, "\u5728\u7EBF\u7528\u6237", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel jpLiveBulletin = new JPanel();
		jpLiveBulletin.setBorder(new TitledBorder(null, "\u7CFB\u7EDF\u516C\u544A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_jpLive = new GroupLayout(jpLive);
		gl_jpLive.setHorizontalGroup(
			gl_jpLive.createParallelGroup(Alignment.LEADING)
				.addComponent(jpLiveServer, GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
				.addGroup(gl_jpLive.createSequentialGroup()
					.addComponent(jpLiveUser, GroupLayout.PREFERRED_SIZE, 630, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_jpLive.createSequentialGroup()
					.addComponent(jpLiveBulletin, GroupLayout.PREFERRED_SIZE, 634, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_jpLive.setVerticalGroup(
			gl_jpLive.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpLive.createSequentialGroup()
					.addComponent(jpLiveServer, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jpLiveUser, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jpLiveBulletin, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
		);
		
		JTextArea jtaBulletin = new JTextArea();
		
		JComboBox jcbSelectDept = new JComboBox();
		
		JButton jbSend = new JButton("\u53D1\u9001");
		GroupLayout gl_jpLiveBulletin = new GroupLayout(jpLiveBulletin);
		gl_jpLiveBulletin.setHorizontalGroup(
			gl_jpLiveBulletin.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_jpLiveBulletin.createSequentialGroup()
					.addGap(7)
					.addComponent(jtaBulletin, GroupLayout.PREFERRED_SIZE, 486, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_jpLiveBulletin.createParallelGroup(Alignment.TRAILING)
						.addComponent(jbSend, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
						.addComponent(jcbSelectDept, 0, 101, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_jpLiveBulletin.setVerticalGroup(
			gl_jpLiveBulletin.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpLiveBulletin.createSequentialGroup()
					.addGroup(gl_jpLiveBulletin.createParallelGroup(Alignment.BASELINE)
						.addComponent(jcbSelectDept, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jtaBulletin, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addComponent(jbSend)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jpLiveBulletin.setLayout(gl_jpLiveBulletin);
		
		JLabel jlServerIcon = new JLabel("Icon");
		
		JButton jbServer = new JButton("\u6253\u5F00\u670D\u52A1\u5668");
		
		JButton jbOffline = new JButton("\u5F3A\u5236\u4E0B\u7EBF");
		GroupLayout gl_jpLiveServer = new GroupLayout(jpLiveServer);
		gl_jpLiveServer.setHorizontalGroup(
			gl_jpLiveServer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpLiveServer.createSequentialGroup()
					.addGap(199)
					.addComponent(jlServerIcon)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbServer)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jbOffline, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(158, Short.MAX_VALUE))
		);
		gl_jpLiveServer.setVerticalGroup(
			gl_jpLiveServer.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_jpLiveServer.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_jpLiveServer.createParallelGroup(Alignment.BASELINE)
						.addComponent(jlServerIcon)
						.addComponent(jbServer)
						.addComponent(jbOffline))
					.addContainerGap(87, Short.MAX_VALUE))
		);
		jpLiveServer.setLayout(gl_jpLiveServer);
		jpLive.setLayout(gl_jpLive);
		
		JPanel jpDept = new JPanel();
		tabbedPane.addTab("\u90E8\u95E8\u7BA1\u7406", null, jpDept, null);
		jpDept.setLayout(new BorderLayout(0, 0));
		
		jtDept = new JTable();
		jpDept.add(jtDept, BorderLayout.CENTER);
		
		JPanel jpDeptSouth = new JPanel();
		jpDept.add(jpDeptSouth, BorderLayout.SOUTH);
		
		JButton jbDeptAdd = new JButton("\u6DFB\u52A0");
		jpDeptSouth.add(jbDeptAdd);
		
		JButton jbDeptUpd = new JButton("\u4FEE\u6539");
		jpDeptSouth.add(jbDeptUpd);
		
		JButton jbDeptDel = new JButton("\u5220\u9664");
		jpDeptSouth.add(jbDeptDel);
		
		JPanel jpUser = new JPanel();
		tabbedPane.addTab("\u7528\u6237\u7BA1\u7406", null, jpUser, null);
		jpUser.setLayout(new BorderLayout(0, 0));
		
		JPanel jpUserNorth = new JPanel();
		jpUser.add(jpUserNorth, BorderLayout.NORTH);
		
		JLabel jlId = new JLabel("\u7F16\u53F7");
		jpUserNorth.add(jlId);
		
		jtfId = new JTextField();
		jpUserNorth.add(jtfId);
		jtfId.setColumns(10);
		
		JLabel jlName = new JLabel("\u59D3\u540D");
		jpUserNorth.add(jlName);
		
		jtfName = new JTextField();
		jpUserNorth.add(jtfName);
		jtfName.setColumns(10);
		
		JLabel jlDept = new JLabel("\u90E8\u95E8");
		jpUserNorth.add(jlDept);
		
		JComboBox jcbDept = new JComboBox();
		jpUserNorth.add(jcbDept);
		
		JButton jbSearch = new JButton("\u67E5\u8BE2");
		jpUserNorth.add(jbSearch);
		
		JButton jbReturn = new JButton("\u8FD4\u56DE");
		jpUserNorth.add(jbReturn);
		
		jtUser = new JTable();
		jpUser.add(jtUser, BorderLayout.CENTER);
		
		JPanel jpUserSouth = new JPanel();
		jpUser.add(jpUserSouth, BorderLayout.SOUTH);
		
		JButton jbUserAdd = new JButton("\u6DFB\u52A0");
		jpUserSouth.add(jbUserAdd);
		
		JButton jbUserUpd = new JButton("\u4FEE\u6539");
		jpUserSouth.add(jbUserUpd);
		
		JButton jbUserDel = new JButton("\u5220\u9664");
		jpUserSouth.add(jbUserDel);
		
		JPanel jpLog = new JPanel();
		tabbedPane.addTab("\u65E5\u5FD7\u7BA1\u7406", null, jpLog, null);
		
		JSplitPane jsp = new JSplitPane();
		jsp.setOneTouchExpandable(true);
		GroupLayout gl_jpLog = new GroupLayout(jpLog);
		gl_jpLog.setHorizontalGroup(
			gl_jpLog.createParallelGroup(Alignment.LEADING)
				.addComponent(jsp, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
		);
		gl_jpLog.setVerticalGroup(
			gl_jpLog.createParallelGroup(Alignment.LEADING)
				.addComponent(jsp, GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)
		);
		
		JPanel jpHistoryLog = new JPanel();
		jsp.setRightComponent(jpHistoryLog);
		
		JPanel jpCurrentLog = new JPanel();
		jsp.setLeftComponent(jpCurrentLog);
		jsp.setDividerLocation(1000);
		jpLog.setLayout(gl_jpLog);
		contentPane.setLayout(gl_contentPane);
		ServMessage connect = new ServMessage();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}

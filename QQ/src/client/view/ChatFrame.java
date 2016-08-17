package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import client.common.Message;
@SuppressWarnings("all")
/**
 * ¡ƒÃÏΩÁ√Ê
 * @author newsong
 *
 */
public class ChatFrame extends JFrame implements ActionListener {
	JLabel jlFriendIcon;
	JTextArea jtaChatRecord;
	JTextArea jtaMsg;
	JLabel jlFont;
	JLabel jlEmoji;
	JLabel jlFile;
	JButton jbHistory;
	JButton jbClose;
	JButton jbSend;
	JPanel jpSendFile;
	private JPanel contentPane;
	Message message;

		
	public ChatFrame(Message message) {
		this.message = message;
		setTitle("\u60A8\u6B63\u5728\u4E0E...\u804A\u5929\u4E2D");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize( 635, 518);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		jlFriendIcon = new JLabel("\u597D\u53CB...");
		jtaChatRecord = new JTextArea();
		jtaMsg = new JTextArea();
		jlFont = new JLabel("\u5B57\u4F53");
		jlEmoji = new JLabel("\u8868\u60C5");
		jlFile = new JLabel("\u6587\u4EF6");
		jbHistory = new JButton("\u804A\u5929\u8BB0\u5F55");
		jbClose = new JButton("\u5173\u95ED");
		jbSend = new JButton("\u53D1\u9001");
		jbSend.setActionCommand("send");
		jbSend.addActionListener(this);
		
		jpSendFile = new JPanel();
		jpSendFile.setBorder(new TitledBorder(null, "\u4F20\u9001\u6587\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(jtaChatRecord, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jpSendFile, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
						.addComponent(jtaMsg, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(jlFont)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jlEmoji)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jlFile))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(jbHistory)
							.addGap(138)
							.addComponent(jbClose)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(jbSend))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addComponent(jlFriendIcon, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(jlFriendIcon, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(jpSendFile, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(jtaChatRecord, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(jlFont)
								.addComponent(jlEmoji)
								.addComponent(jlFile))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(jtaMsg, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(jbHistory)
								.addComponent(jbClose)
								.addComponent(jbSend))))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		this.setVisible(true);
	}

	public void setText(String msg) {
		jtaChatRecord.append(msg+"\n");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "send":
			String msg = jtaMsg.getText().trim();
			if(!msg.equals("")) {
				message.send(msg);
			}
			break;
			
		}
	}
}

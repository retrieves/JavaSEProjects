package codesummer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
@SuppressWarnings("all")
public class MainFrame extends JFrame implements ActionListener{
	
	JLabel jlPrompt;
	JFileChooser jfc;
	JTextField jlPath;
	JButton jbChooser;
	JLabel jlSum;
	JTextField jtf;
	JButton jbConfirm;
	JButton jbExit;
	File chosenFile;
	JLabel bg;
	public MainFrame() {
		bg = new JLabel(new ImageIcon("2.jpg"));
		bg.setBounds(0, 0, 400, 400);
		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("1.jpg"));
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		this.setTitle("����Գ���������");
		this.setBounds(Toolkit.getDefaultToolkit().getScreenSize().width/2-200, Toolkit.getDefaultToolkit().getScreenSize().height/2-200, 400,400);
		this.setLayout(null);
		jlPrompt = new JLabel("��ѡ��Դ����ĸ��ļ��л�ĳһԴ�ļ�");
		jlPrompt.setBounds(50, 50,300, 50);
		jlPath = new JTextField();
		jlPath.setBounds(50, 110, 200, 30);
		jlPath.setEditable(false);
		jbChooser = new JButton("��");
		jbChooser.setBounds(250,110,80,30);
		jbChooser.setActionCommand("choose");
		jbChooser.addActionListener(this);
		jlSum = new JLabel("������Ϊ");
		jlSum.setBounds(100, 200, 100, 30);
		jtf = new JTextField(10);
		jtf.setBounds(200, 200, 80,30);
		jtf.setEditable(false);
		jbConfirm = new JButton("ȷ��");
		jbConfirm.setBounds(100,300,80,40);
		jbConfirm.setActionCommand("confirm");
		jbConfirm.addActionListener(this);
		jbExit = new  JButton("�˳�");
		jbExit.setBounds(200, 300, 80, 40);
		jbExit.setActionCommand("cancel");
		jbExit.addActionListener(this);
		this.add(jlPrompt);
		this.add(jlPath);
		this.add(jbChooser);
		this.add(jlSum);
		this.add(jtf);
		this.add(jbConfirm);
		this.add(jbExit);
		this.add(bg);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "choose":
			jfc = new JFileChooser();
			jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			jfc.setDialogTitle("��ѡ���ļ�");
			jfc.showOpenDialog(this);
			this.chosenFile  = jfc.getSelectedFile();
			jlPath.setText(chosenFile.getAbsolutePath());
			jtf.setText("");
			break;
		case "confirm":
			if(jlPath.getText().equals("")){
				break;
			}
			if(chosenFile.isFile()){
				if(chosenFile.getName().matches(".*\\.java$")){
					jtf.setText(""+Summer.parse(chosenFile));
				}else{
					JOptionPane.showMessageDialog(this, "�ļ���ʽ����");
				}
			}else{
				jtf.setText(""+Summer.parseFolder(chosenFile));
			}
			break;
		case "cancel":
			JOptionPane.showMessageDialog(this, "Bye-bye");
			System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}
	
}

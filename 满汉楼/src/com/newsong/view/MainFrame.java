package com.newsong.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.newsong.tools.ImagePanel;
import com.newsong.tools.MyFont;
import com.newsong.view.receipt.ReceiptFrame;
import com.newsong.view.stock.StockPanel;

@SuppressWarnings("all")
public class MainFrame extends JFrame implements ActionListener, MouseListener {
	private String username;
	private String job;
	int w = Toolkit.getDefaultToolkit().getScreenSize().width;
	int h = Toolkit.getDefaultToolkit().getScreenSize().height;

	Image titleIcon;
	// ���山�����
	JMenuBar jmb;
	JMenu system, personnel, menu, table, stock, help;
	JMenuItem systemSwitchWindow, systemSwitchUser, systemReg, systemCalendar, systemExit;
	JMenuItem personnelReg;
	JMenuItem menuService;
	JMenuItem tableShow;
	JMenuItem costControl;
	JMenuItem helpFlash, helpText, helpAbout;
	JPanel jpNorth;
	JToolBar jbt;
	JButton jbSwitchWindow, jbSwitchUser, jbReg, jbPersonnel, jbTable, jbMenu, jbCost, jbAbout, jbHelp, jbExit;
	JLabel jlCurrentUser;

	// �����в����
	JSplitPane jsp; // �����в����
	JPanel FunctionPanel;
	ImagePanel jpBackGround;
	JLabel jlIcon, jlPersonnel, jlReg, jlMenu, jlTable, jlStock, jlSetting, jlHelp;
	JLabel jlEnter;
	JPanel CenterPanel;

	JPanel ButtonPanel;
	CardLayout buttonLayout;
	JLabel jlLeft;
	JLabel jlRight;

	JPanel TablePanel;
	CardLayout tableLayout;
	ImagePanel tableBackGround;
	PersonnelPanel personnelPanel;
	RegisterPanel regPanel;
	StockPanel stockPanel;
	MenuPanel menuPanel;
	SettingPanel settingPanel;
	GraphPanel chartPanel;
	HelpPanel helpPanel;

	// �����ϲ����
	ImagePanel jpSouth;
	JPanel jpSouthEast;
	JLabel jlTime;
	Timer timer;

	public void initMenu() {
		// �����˵���
		jmb = new JMenuBar();
		// ϵͳ�˵�
		system = new JMenu("ϵͳ����");
		systemSwitchWindow = new JMenuItem("�л����տ����", new ImageIcon("image/toolBar_image/jb1.jpg"));
		systemSwitchWindow.setActionCommand("switchWindows");
		systemSwitchWindow.addActionListener(this);
		systemSwitchUser = new JMenuItem("�л��û�", new ImageIcon("image/toolBar_image/jb2.jpg"));
		systemSwitchUser.setActionCommand("switchUser");
		systemSwitchUser.addActionListener(this);
		systemReg = new JMenuItem("��¼����", new ImageIcon("image/toolBar_image/jb3.jpg"));
		systemReg.setActionCommand("reg");
		systemReg.addActionListener(this);
		systemCalendar = new JMenuItem("������", new ImageIcon("image/toolBar_image/jb11.jpg"));
		systemCalendar.setActionCommand("calendar");
		systemCalendar.addActionListener(this);
		systemExit = new JMenuItem("�˳�", new ImageIcon("image/toolBar_image/jb10.jpg"));
		systemExit.setActionCommand("exit");
		systemExit.addActionListener(this);

		system.add(systemSwitchWindow);
		system.add(systemSwitchUser);
		system.add(systemReg);
		system.add(systemCalendar);
		system.add(systemExit);

		// ���²˵�
		personnel = new JMenu("���¹���");
		personnelReg = new JMenuItem("���µǼ�", new ImageIcon("image/jm1_icon4.jpg"));
		personnelReg.setActionCommand("personnel");
		personnelReg.addActionListener(this);
		personnel.add(personnelReg);
		// ���ײ˵�
		menu = new JMenu("�˵�����");
		menuService = new JMenuItem("���׼��۸�¼��", new ImageIcon("image/toolBar_image/jb6.jpg"));
		menuService.setActionCommand("menu");
		menuService.addActionListener(this);
		menu.add(menuService);
		// ����˵�
		table = new JMenu("����ͳ��");
		tableShow = new JMenuItem("����ͳ��", new ImageIcon("image/toolBar_image/jb5.jpg"));
		tableShow.setActionCommand("table");
		tableShow.addActionListener(this);
		table.add(tableShow);
		// �ɱ��˵�
		stock = new JMenu("�ɱ����ⷿ");
		costControl = new JMenuItem("�ɱ�����", new ImageIcon("image/toolBar_image/jb7.jpg"));
		costControl.setActionCommand("stock");
		costControl.addActionListener(this);
		stock.add(costControl);
		// �����˵�
		help = new JMenu("����");
		helpFlash = new JMenuItem("��������", new ImageIcon("image/toolBar_image/jb9.jpg"));
		helpFlash.setActionCommand("helpFlash");
		helpFlash.addActionListener(this);
		helpText = new JMenuItem("�ı�����", new ImageIcon("image/toolBar_image/jb12.jpg"));
		helpText.setActionCommand("helpText");
		helpText.addActionListener(this);
		helpAbout = new JMenuItem("����", new ImageIcon("image/toolBar_image/jb8.jpg"));
		helpAbout.setActionCommand("about");
		helpAbout.addActionListener(this);

		help.add(helpFlash);
		help.add(helpText);
		help.add(helpAbout);

		// ���JMenu
		jmb.add(system);
		jmb.add(personnel);
		jmb.add(menu);
		jmb.add(table);
		jmb.add(stock);
		jmb.add(help);
		this.setJMenuBar(jmb);
	}

	public void initTool() {
		jpNorth = new JPanel();
		jpNorth.setLayout(new BorderLayout());
		// ���ù�����
		jbt = new JToolBar();
		jbSwitchWindow = new JButton(new ImageIcon("image/toolBar_image/jb1.jpg"));
		jbSwitchWindow.setToolTipText("�л����տ����");
		jbSwitchWindow.setActionCommand("switchWindows");
		jbSwitchWindow.addActionListener(this);

		jbSwitchUser = new JButton(new ImageIcon("image/toolBar_image/jb2.jpg"));
		jbSwitchUser.setToolTipText("�л��û�");
		jbSwitchUser.setActionCommand("switchUser");
		jbSwitchUser.addActionListener(this);

		jbReg = new JButton(new ImageIcon("image/toolBar_image/jb3.jpg"));
		jbReg.setToolTipText("��¼����");
		jbReg.setActionCommand("reg");
		jbReg.addActionListener(this);

		jbPersonnel = new JButton(new ImageIcon("image/toolBar_image/jb4.jpg"));
		jbPersonnel.setToolTipText("���¹���");
		jbPersonnel.setActionCommand("personnel");
		jbPersonnel.addActionListener(this);

		jbTable = new JButton(new ImageIcon("image/toolBar_image/jb5.jpg"));
		jbTable.setToolTipText("����ͳ��");
		jbTable.setActionCommand("table");
		jbTable.addActionListener(this);

		jbMenu = new JButton(new ImageIcon("image/toolBar_image/jb6.jpg"));
		jbMenu.setToolTipText("�˵�����");
		jbMenu.setActionCommand("menu");
		jbMenu.addActionListener(this);

		jbCost = new JButton(new ImageIcon("image/toolBar_image/jb7.jpg"));
		jbCost.setToolTipText("�ɱ����ⷿ");
		jbCost.setActionCommand("stock");
		jbCost.addActionListener(this);

		jbAbout = new JButton(new ImageIcon("image/toolBar_image/jb8.jpg"));
		jbAbout.setToolTipText("����");
		jbAbout.setActionCommand("about");
		jbAbout.addActionListener(this);

		jbHelp = new JButton(new ImageIcon("image/toolBar_image/jb9.jpg"));
		jbHelp.setToolTipText("����");
		jbHelp.setActionCommand("help");
		jbHelp.addActionListener(this);

		jbExit = new JButton(new ImageIcon("image/toolBar_image/jb10.jpg"));
		jbExit.setToolTipText("�˳�");
		jbExit.setActionCommand("exit");
		jbExit.addActionListener(this);

		jbt.add(jbSwitchWindow);
		jbt.add(jbSwitchUser);
		jbt.add(jbReg);
		jbt.add(jbPersonnel);
		jbt.add(jbTable);
		jbt.add(jbMenu);
		jbt.add(jbCost);
		jbt.add(jbAbout);
		jbt.add(jbHelp);
		jbt.add(jbExit);
		jbt.setFloatable(false);
		jlCurrentUser = new JLabel("��ǰ�û�: " + username + "(" + job + ")          ");
		jpNorth.add(jbt, BorderLayout.WEST);
		jpNorth.add(jlCurrentUser, BorderLayout.EAST);
		this.add(jpNorth, BorderLayout.NORTH);
	}

	public void initLeft() {

		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		// ��������FunctionPanel������ѡ��
		FunctionPanel = new JPanel();
		jpBackGround = new ImagePanel("image/window.jpg", 0, 0, w, h - 150);
		jpBackGround.setLayout(new GridLayout(9, 1));
		FunctionPanel.setLayout(new BorderLayout());
		jlIcon = new JLabel(new ImageIcon("image/p1_mhl.jpg"));

		jlEnter = new JLabel("�� �� ϵ ͳ", new ImageIcon("image/jm1_icon6.jpg"), SwingConstants.CENTER);
		jlEnter.setFont(MyFont.f3);
		jlEnter.setEnabled(false);
		jlEnter.addMouseListener(this);
		jlEnter.setCursor(cursor);

		jlPersonnel = new JLabel("�� �� �� ��", new ImageIcon("image/center_image/label_2.jpg"), SwingConstants.CENTER);
		jlPersonnel.setFont(MyFont.f3);
		jlPersonnel.setEnabled(false);
		jlPersonnel.addMouseListener(this);
		jlPersonnel.setCursor(cursor);

		jlReg = new JLabel("�� ¼ �� ��", new ImageIcon("image/center_image/label_3.jpg"), SwingConstants.CENTER);
		jlReg.setFont(MyFont.f3);
		jlReg.setEnabled(false);
		jlReg.addMouseListener(this);
		jlReg.setCursor(cursor);

		jlMenu = new JLabel("�� Ʒ �� ��", new ImageIcon("image/center_image/label_4.jpg"), SwingConstants.CENTER);
		jlMenu.setFont(MyFont.f3);
		jlMenu.setEnabled(false);
		jlMenu.addMouseListener(this);
		jlMenu.setCursor(cursor);

		jlTable = new JLabel("�� �� ͳ ��", new ImageIcon("image/center_image/label_5.jpg"), SwingConstants.CENTER);
		jlTable.setFont(MyFont.f3);
		jlTable.setEnabled(false);
		jlTable.addMouseListener(this);
		jlTable.setCursor(cursor);

		jlStock = new JLabel("�� �� �� �� ��", new ImageIcon("image/center_image/label_6.jpg"), SwingConstants.CENTER);
		jlStock.setFont(MyFont.f3);
		jlStock.setEnabled(false);
		jlStock.addMouseListener(this);
		jlStock.setCursor(cursor);

		jlSetting = new JLabel("ϵ ͳ �� ��", new ImageIcon("image/center_image/label_7.jpg"), SwingConstants.CENTER);
		jlSetting.setFont(MyFont.f3);
		jlSetting.setEnabled(false);
		jlSetting.addMouseListener(this);
		jlSetting.setCursor(cursor);

		jlHelp = new JLabel("�� �� �� ��", new ImageIcon("image/center_image/label_8.jpg"), SwingConstants.CENTER);
		jlHelp.setFont(MyFont.f3);
		jlHelp.setEnabled(false);
		jlHelp.addMouseListener(this);
		jlHelp.setCursor(cursor);

		// ����������뵽jpBackGround���������ϣ�������һϵ�е�JLabel��������GridLayout
		jpBackGround.add(jlIcon);
		jpBackGround.add(jlEnter);
		jpBackGround.add(jlPersonnel);
		jpBackGround.add(jlReg);
		jpBackGround.add(jlMenu);
		jpBackGround.add(jlTable);
		jpBackGround.add(jlStock);
		jpBackGround.add(jlSetting);
		jpBackGround.add(jlHelp);
		// ��jpBackGround�ŵ�jpCenter�ϣ�jpCenter��ֻ����һ��JPanel��������BorderLayout
		FunctionPanel.add(jpBackGround);

	}

	public void initRight() {
		CenterPanel = new JPanel();
		CenterPanel.setLayout(new BorderLayout());
		ButtonPanel = new JPanel();
		buttonLayout = new CardLayout();
		ButtonPanel.setLayout(buttonLayout);
		TablePanel = new JPanel();
		tableLayout = new CardLayout();
		TablePanel.setLayout(tableLayout);

		CenterPanel.add(ButtonPanel, BorderLayout.WEST); // �в�Panel��ButtonPanel�������
		CenterPanel.add(TablePanel, BorderLayout.CENTER);// �в�Panel��TablePanel�����м䣬ռ���˳�������ȫ���ռ�

		// �����в���ButtonPanel
		jlLeft = new JLabel(new ImageIcon("image/center_image/jp2_left.jpg"));
		jlLeft.addMouseListener(this);
		jlRight = new JLabel(new ImageIcon("image/center_image/jp2_right.jpg"));
		jlRight.addMouseListener(this);
		ButtonPanel.add(jlLeft, "Left");
		ButtonPanel.add(jlRight, "Right");

		// �����в���TablePanel
		tableBackGround = new ImagePanel("image/jp3_bg.jpg", 180, 0, w-200, h - 150);
		TablePanel.add(tableBackGround, "background");
		personnelPanel = new PersonnelPanel();
		regPanel = new RegisterPanel(this);
		stockPanel = new StockPanel(this);
		menuPanel = new MenuPanel(this);
		settingPanel = new SettingPanel(this);
		chartPanel = new GraphPanel();
		helpPanel = new HelpPanel(this);

		TablePanel.add(personnelPanel, "PersonnelPanel");
		TablePanel.add(regPanel, "RegisterPanel");
		TablePanel.add(stockPanel, "StockPanel");
		TablePanel.add(menuPanel, "MenuPanel");
		TablePanel.add(chartPanel, "ChartPanel");
		TablePanel.add(settingPanel, "SettingPanel");
		TablePanel.add(helpPanel, "HelpPanel");
		// ��FunctionPanel��CenterPanel���뵽�в��Ĵ�Panel��
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, FunctionPanel, CenterPanel);
		jsp.setDividerLocation(w);
		jsp.setDividerSize(0);
		this.add(jsp, BorderLayout.CENTER);

	}

	public void initSouth() {

		jpSouth = new ImagePanel("image/time_bg.jpg", 0, 0, w, h);
		jpSouth.setLayout(new BorderLayout());
		jpSouthEast = new JPanel();
		jlTime = new JLabel();
		jpSouthEast.add(jlTime);
		timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jlTime.setText(
						"��ǰʱ�䣺" + new SimpleDateFormat("yyyy��MM��dd�� kkʱmm��ss��").format(System.currentTimeMillis()));
			}
		});
		timer.start();
		jpSouth.add(jpSouthEast, BorderLayout.EAST);
		this.add(jpSouth, BorderLayout.SOUTH);
	}

	public MainFrame(String username, String job) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		// ��ʾ����Сͼ��
		titleIcon = Toolkit.getDefaultToolkit().getImage("image/title.jpg");
		this.setIconImage(titleIcon);
		this.setLayout(new BorderLayout());
		this.username = username;
		this.job = job;
		initMenu();
		initTool();
		initLeft();
		initRight();
		initSouth();
		// ��������
		this.setTitle("��������ϵͳ");
		this.setBounds(0, 0, w, h - 50);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "switchWindows":
			new ReceiptFrame(this,this.username,this.job);
			this.setVisible(false);
			break;
		case "switchUser":
			UserLogin login = new UserLogin(false);
			login.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					if (login.getUserNameJob() != null) {
						String[] info = login.getUserNameJob().split(",");
						username = info[0];
						job = info[1];
						System.out.println(username + "   " + job);
						jlCurrentUser.setText("��ǰ�û�: " + username + "(" + job + ")          ");
					}
				}
			});
			break;
		case "reg":
			tableLayout.show(TablePanel, "RegisterPanel");
			break;
		case "calendar":
			System.out.println("��������");
			break;
		case "exit":
			JOptionPane.showMessageDialog(this, "ллʹ��");
			System.exit(0);
		case "personnel":
			tableLayout.show(TablePanel, "PersonnelPanel");
			break;
		case "menu":
			tableLayout.show(TablePanel, "MenuPanel");
			break;
		case "table":
			System.out.println("table");
			tableLayout.show(TablePanel, "ChartPanel");
			break;
		case "stock":
			tableLayout.show(TablePanel, "StockPanel");
			break;
		case "help":
			tableLayout.show(TablePanel, "HelpPanel");
			break;
		case "helpFlash":
			System.out.println("helpFlash");
			break;
		case "helpText":
			System.out.println("helpText");
			break;
		case "about":
			System.out.println("about");
			break;
		}

	}

	public static void main(String[] args) {
		MainFrame w = new MainFrame("�տ���", "����");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		jsp.setDividerLocation(180);
		if (e.getSource() == jlEnter) {
			tableLayout.show(TablePanel, "background");
		} else if (e.getSource() == jlPersonnel) {
			tableLayout.show(TablePanel, "PersonnelPanel");
		} else if (e.getSource() == jlReg) {
			tableLayout.show(TablePanel, "RegisterPanel");
		} else if (e.getSource() == jlStock) {
			tableLayout.show(TablePanel, "StockPanel");
		} else if (e.getSource() == jlMenu) {
			tableLayout.show(TablePanel, "MenuPanel");
		} else if (e.getSource() == jlTable) {
			tableLayout.show(TablePanel, "ChartPanel");
		} else if (e.getSource() == jlSetting) {
			tableLayout.show(TablePanel, "SettingPanel");
		} else if (e.getSource() == jlHelp) {
			tableLayout.show(TablePanel, "HelpPanel");
		} else if (e.getSource() == jlLeft) {
			buttonLayout.show(ButtonPanel, "Right");
			jsp.setDividerLocation(0);
		} else if (e.getSource() == jlRight) {
			buttonLayout.show(ButtonPanel, "Left");
			jsp.setDividerLocation(180);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

		if (e.getSource() == jlPersonnel) {
			jlPersonnel.setEnabled(true);
		} else if (e.getSource() == jlReg) {
			jlReg.setEnabled(true);
		} else if (e.getSource() == jlStock) {
			jlStock.setEnabled(true);
		} else if (e.getSource() == jlMenu) {
			jlMenu.setEnabled(true);
		} else if (e.getSource() == jlTable) {
			jlTable.setEnabled(true);
		} else if (e.getSource() == jlSetting) {
			jlSetting.setEnabled(true);
		} else if (e.getSource() == jlHelp) {
			jlHelp.setEnabled(true);
		} else if(e.getSource() == jlEnter){
			jlEnter.setEnabled(true);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {

		if (e.getSource() == jlPersonnel) {
			jlPersonnel.setEnabled(false);
		} else if (e.getSource() == jlReg) {
			jlReg.setEnabled(false);
		} else if (e.getSource() == jlStock) {
			jlStock.setEnabled(false);
		} else if (e.getSource() == jlMenu) {
			jlMenu.setEnabled(false);
		} else if (e.getSource() == jlTable) {
			jlTable.setEnabled(false);
		} else if (e.getSource() == jlSetting) {
			jlSetting.setEnabled(false);
		} else if (e.getSource() == jlHelp) {
			jlHelp.setEnabled(false);
		} else if(e.getSource() == jlEnter){
			jlEnter.setEnabled(false);
		}
	}

}

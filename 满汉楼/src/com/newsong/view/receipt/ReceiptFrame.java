package com.newsong.view.receipt;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.BorderFactory;
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
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

import com.newsong.JavaBean.Desk;
import com.newsong.model.BookItemDAOImpl;
import com.newsong.model.DeskDAOImpl;
import com.newsong.tools.ImagePanel;
import com.newsong.tools.MyFont;
import com.newsong.view.UserLogin;
@SuppressWarnings("all")
public class ReceiptFrame extends JFrame implements ActionListener,MouseListener {
	int w;
	int h;
	String username;
	String job;
	JFrame mainFrame;
	JMenuBar jmb;
	JMenu jmSystem,jmService,jmHelp;
	JMenuItem	sysSwitchUser,sysSwitchMain,sysSwitchReceipt,sysExit;
	JMenuItem service;
	JMenuItem help;
	JToolBar jtb;
	JButton jbSwitchMain,jbSwitchReceipt,jbSwitchUser,jbService,jbHelp,jbExit;
	
	//定义中部组件
	JLabel[] jlDesk;
	JSplitPane jsp;
	JPanel jpWest;
	CardLayout cardLayout ;
	ImagePanel jpMain;
	ServicePanel jpService;
	ImagePanel jpEast;
	JLabel jlLogo;
	JLabel jlUser;
	JLabel jlJob;	
	JButton jbSubscribe,jbUnSubscribe,jbOrder,jbCheckOut;
	
	// 定义南部组件
	ImagePanel jpSouth;
	JPanel jpSouthEast;
	JLabel jlTime;
	Timer timer;
	JLabel lastDesk ;
	DeskDAOImpl deskDAOImpl;
	BookItemDAOImpl bookDAOImpl;
	
	public void initMenu(){
		jmb = new JMenuBar();
		jmSystem = new JMenu("系统");
		sysSwitchUser = new JMenuItem("切换用户");
		sysSwitchUser.setActionCommand("switchUser");
		sysSwitchUser.addActionListener(this);
		
		sysSwitchMain = new JMenuItem("切换到主界面");
		sysSwitchMain.setActionCommand("switchMain");
		sysSwitchMain.addActionListener(this);
		
		sysSwitchReceipt = new JMenuItem("切换到收款界面");
		sysSwitchReceipt.setActionCommand("switchReceipt");
		sysSwitchReceipt.addActionListener(this);
		
		sysExit = new JMenuItem("退出");
		sysExit.setActionCommand("exit");
		sysExit.addActionListener(this);
		
		jmSystem.add(sysSwitchMain);
		jmSystem.add(sysSwitchReceipt);
		jmSystem.add(sysSwitchUser);
		jmSystem.addSeparator();
		jmSystem.add(sysExit);
		jmb.add(jmSystem);
		
		jmService = new JMenu("服务");
		service = new JMenuItem("客户服务");
		service.setActionCommand("service");
		service.addActionListener(this);
		
		jmService.add(service);
		jmb.add(jmService);
		
		jmHelp = new JMenu("帮助");
		help = new JMenuItem("文本帮助");
		help.setActionCommand("help");
		help.addActionListener(this);
		
		jmHelp.add(help);
		jmb.add(jmHelp);
		this.setJMenuBar(jmb);
	}
	
	public void initTool(){
		jtb = new JToolBar();
		
		jbSwitchMain = new JButton(new ImageIcon("image/toolBar_image/jb1.jpg"));
		jbSwitchMain.setToolTipText("切换到主界面");
		jbSwitchMain.setActionCommand("switchMain");
		jbSwitchMain.addActionListener(this);
		
		jbSwitchReceipt = new JButton(new ImageIcon("image/toolBar_image/jb8.jpg"));
		jbSwitchReceipt.setToolTipText("切换到收款界面");
		jbSwitchReceipt.setActionCommand("switchReceipt");
		jbSwitchReceipt.addActionListener(this);
		
		jbSwitchUser = new JButton(new ImageIcon("image/toolBar_image/jb2.jpg"));
		jbSwitchUser.setToolTipText("切换用户");
		jbSwitchUser.setActionCommand("switchUser");
		jbSwitchUser.addActionListener(this);
		
		jbService = new JButton(new ImageIcon("image/toolBar_image/jb13.jpg"));
		jbService.setToolTipText("客户服务");
		jbService.setActionCommand("service");
		jbService.addActionListener(this);
		
		jbHelp = new JButton( new ImageIcon("image/toolBar_image/jb9.jpg"));
		jbHelp.setToolTipText("帮助");
		jbHelp.setActionCommand("help");
		jbHelp.addActionListener(this);
		
		jbExit = new JButton( new ImageIcon("image/toolBar_image/jb10.jpg"));
		jbExit.setToolTipText("退出");
		jbExit.setActionCommand("exit");
		jbExit.addActionListener(this);
		jtb.add(jbSwitchMain);jtb.add(jbSwitchReceipt);jtb.add(jbSwitchUser);jtb.add(jbService);jtb.add(jbHelp);jtb.add(jbExit);
		jtb.setFloatable(false);
		this.add(jtb,BorderLayout.NORTH);
	}
	
	
	public void initSouth(){
		
		jpSouth = new ImagePanel("image/time_bg.jpg", 0, 0, w, h);
		jpSouth.setLayout(new BorderLayout());
		jpSouthEast = new JPanel();
		jlTime = new JLabel();
		jpSouthEast.add(jlTime);
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jlTime.setText(
						"当前时间：" + new SimpleDateFormat("yyyy年MM月dd日 kk时mm分ss秒").format(System.currentTimeMillis()));
			}
		});
		timer.start();
		jpSouth.add(jpSouthEast, BorderLayout.EAST);
		this.add(jpSouth, BorderLayout.SOUTH);
	}
	
	public void initCenter(){
		//初始化左侧
		jpWest = new JPanel();
		cardLayout = new CardLayout();
		jpWest.setLayout(cardLayout);
		
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		jpMain = new ImagePanel("image/orderindex.jpg", 0, 0, (int)(w*0.8), h-170);
		jpMain.setLayout(null);
		jpService = new ServicePanel();
		jlDesk = new JLabel[20];
		int x = 400;
		int y = 290;
		
		//桌子有三种颜色,红色表示正在用餐,黄色表示已预订,蓝色表示未预定
		List<Desk> desks= this.deskDAOImpl.findAllDesks();
		for(int i = 1 ; i<= jlDesk.length; i++){
			
			jlDesk[i-1] = new JLabel();
			jlDesk[i-1].setBounds(x,y , 100, 50);
			jlDesk[i-1].addMouseListener(this);
			jlDesk[i-1].setCursor(cursor);
			jlDesk[i-1].setName(""+i);
			if(desks.get(i-1).getStatus().equals("在用餐")) {
				jlDesk[i-1].setIcon(new ImageIcon("image/desk/red/"+i+"桌面组合.png"));
			}else if(desks.get(i-1).getStatus().equals("已预订")) {
				jlDesk[i-1].setIcon(new ImageIcon("image/desk/yellow/"+i+"桌面组合.png"));
			}else {
				jlDesk[i-1].setIcon(new ImageIcon("image/desk/blue/"+i+"桌面组合.png"));
			}
			jpMain.add(jlDesk[i-1]);
			x+= 200;
			if(i % 5 == 0){
				x = 400;
				y+=150;
			}
		}
		jpWest.add(jpMain, "main");
		jpWest.add(jpService, "service");
		
		cardLayout.show(jpWest, "main");
		
		//初始化右侧
		jpEast = new ImagePanel("image/manage.jpg",(int)(w*0.8),0,(int)(w*0.2), h-170);
		jpEast.setLayout(null);
		
		jlLogo = new JLabel(new ImageIcon("image/center_image/label_1.gif"));
		jlLogo.setBounds(100, 100, 150, 65);
		jlUser = new JLabel("当前用户:"+this.username);
		jlUser.setBounds(100, 200, 200, 100);
		jlUser.setFont(MyFont.f3);
		jlJob = new JLabel("职位:"+this.job);
		jlJob.setBounds(100, 300, 200, 100);
		jlJob.setFont(MyFont.f3);
		
		jbSubscribe = new JButton("预订");
		jbSubscribe.setBounds(100, 700, 80, 40);
		jbSubscribe.setActionCommand("subscribe");
		jbSubscribe.addActionListener(this);
		jbSubscribe.setFont(MyFont.f3);
		
		jbUnSubscribe = new JButton("退订");
		jbUnSubscribe.setBounds(200, 700, 80, 40);
		jbUnSubscribe.setActionCommand("unSubscribe");
		jbUnSubscribe.addActionListener(this);
		jbUnSubscribe.setFont(MyFont.f3);
		
		jbOrder = new JButton("点菜");
		jbOrder.setBounds(100,800,80,40);
		jbOrder.setActionCommand("order");
		jbOrder.addActionListener(this);
		jbOrder.setFont(MyFont.f3);
		
		jbCheckOut = new JButton("结账");
		jbCheckOut.setBounds(200, 800, 80, 40);
		jbCheckOut.setActionCommand("checkout");
		jbCheckOut.addActionListener(this);
		jbCheckOut.setFont(MyFont.f3);
		
		jpEast.add(jlLogo);
		jpEast.add(jlUser);
		jpEast.add(jlJob);
		jpEast.add(jbSubscribe);
		jpEast.add(jbUnSubscribe);
		jpEast.add(jbOrder);
		jpEast.add(jbCheckOut);
		
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,jpWest,jpEast);
		jsp.setDividerSize(0);
		jsp.setDividerLocation((int)(w*0.8));
		this.add(jsp,BorderLayout.CENTER);
		lastDesk = jlLogo;
	}
	
	public ReceiptFrame(JFrame mainFrame,String username, String job) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		deskDAOImpl = new DeskDAOImpl();
		bookDAOImpl = new BookItemDAOImpl();
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("image/ReceptIcon.jpg"));
		this.w = Toolkit.getDefaultToolkit().getScreenSize().width;
		this.h = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.username = username;
		this.job = job;
		this.mainFrame = mainFrame;
		this.setLayout(new BorderLayout());
		this.setBounds(0,0,w,h-50);
		this.setTitle("餐饮管理系统--收款界面");
		initMenu();
		initTool();
		initCenter();
		initSouth();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "switchMain":
			if(job.equals("收银员")) {
				showMsg("NoPermission");
				break;
			}
			mainFrame.setVisible(true);
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
						jlUser.setText("当前用户:" +username);
						jlJob.setText("职位:"+job);
					}
				}
			});
			break;
		case "switchReceipt":
			cardLayout.show(jpWest, "main");
			break;
		case "exit":
			this.dispose();
			break;
		case "service":
			if(this.job.equals("收银员")) {
				showMsg("NoPermission");
				break;
			}
			cardLayout.show(jpWest, "service");
			
			break;
		case "help":
			System.out.println("help");
			break;
		case "subscribe":
			if(lastDesk != jlLogo) {
				int deskId = Integer.parseInt(lastDesk.getName());
				boolean previoutEmpty = isEmpty(deskId);
				SubscribeDialog subDialog = new SubscribeDialog(deskId);
				subDialog.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						if(subDialog.isSucc && previoutEmpty  ) {
							lastDesk.setIcon(new ImageIcon("image/desk/yellow/"+deskId+"桌面组合.png"));
							deskDAOImpl.updateDeskStatus(new Desk(deskId, "已预订"));
						}
					}
				});
			}
			break;
		case "unSubscribe":
			if(lastDesk != jlLogo) {
				int deskId = Integer.parseInt(lastDesk.getName());
				if(deskDAOImpl.checkDesk(deskId).equals("空")) {
					showMsg("NoRecord");
				}else {
					UnSubscribeDialog unSubDialog = new UnSubscribeDialog(deskId);
					unSubDialog.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							if(unSubDialog.isSucc && isEmpty(deskId)  ) {
								lastDesk.setIcon(new ImageIcon("image/desk/blue/"+deskId+"桌面组合.png"));
								deskDAOImpl.updateDeskStatus(new Desk(deskId, "空"));
							}
						}
					});
				}
			}
			break;
		case "order":
			if(lastDesk != jlLogo) {
				int deskId = Integer.parseInt(lastDesk.getName());
				if(isOccupied(deskId)) {
					showMsg("HaveUsed");
				}else {
					OrderDialog orderDialog = new OrderDialog(deskId);
					orderDialog.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							if(orderDialog.isSucc  ) {
								lastDesk.setIcon(new ImageIcon("image/desk/red/"+deskId+"桌面组合.png"));
								deskDAOImpl.updateDeskStatus(new Desk(deskId, "在用餐"));
							}
						}
					});
				}
			}
			break;
		case "checkout":
			if(lastDesk != jlLogo) {
				int deskId = Integer.parseInt(lastDesk.getName());
				if(!isOccupied(deskId)) {
					showMsg("NotOccupied");
				}else {
					CheckOutDialog checkOutDialog = new CheckOutDialog(deskId);
					checkOutDialog.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							if(checkOutDialog.isSucc) {
								if(isEmpty(deskId)) {
									lastDesk.setIcon(new ImageIcon("image/desk/blue/"+deskId+"桌面组合.png"));
									deskDAOImpl.updateDeskStatus(new Desk(deskId, "空"));
								}else {
									lastDesk.setIcon(new ImageIcon("image/desk/yellow/"+deskId+"桌面组合.png"));
									deskDAOImpl.updateDeskStatus(new Desk(deskId, "已预订"));
								}
							}
						}
					});
				}
			}
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 1 ; i<= jlDesk.length; i++){
			if(e.getSource() == jlDesk[i-1]) {
				System.out.println(jlDesk[i-1].getName());
				jlDesk[i-1].setBorder(new BevelBorder(BevelBorder.LOWERED));
				lastDesk.setBorder(BorderFactory.createEmptyBorder());
				lastDesk = jlDesk[i-1];
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void showMsg(String msg) {
		switch(msg) {
		case "NoPermission":
			JOptionPane.showMessageDialog(this, "抱歉,您无此权限");
			break;
		case "NoRecord":
			JOptionPane.showMessageDialog(this, "此桌没有预订记录");
			break;
		case "HaveUsed":
			JOptionPane.showMessageDialog(this, "此桌正在用餐");
			break;
		case"NotOccupied":
			JOptionPane.showMessageDialog(this, "此桌并未在用餐");
			break;
		}
	}
	
	public boolean isOccupied(int deskId) {
		return deskDAOImpl.checkDesk(deskId).equals("在用餐");
	}
	
	public boolean isEmpty(int deskId) {
		return bookDAOImpl.findBookStatus(deskId).size() == 0;
	}
	
	/*public static void main(String[] args) {
		new ReceiptFrame(null, "temp", "temp");
	}	*/
}

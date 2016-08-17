package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import command.CommandManager;
import command.CutCommand;
import command.PasteCommand;
import command.TypeCommand;
import rmi.RemoteHelper;

/**
 * 主窗口 需要自己去实现和完善
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("all")
public class MainFrame extends JFrame implements ActionListener {
	//声明菜单栏及条目
	private JMenuBar jmb;
	private JMenu file, edit, run, user;
	private JMenuItem fileNew, fileOpen, fileSave, fileExit;
	private JMenuItem editCut, editCopy, editPaste, editSelectAll, editRedo, editUndo;
	private JMenuItem execute;
	private JMenuItem login;
	private JMenuItem logout;

	private JPanel jpWest;
	private JList list;
	private JPopupMenu jpmVersion;
	private JMenuItem open;
	private Vector fileList;
	//声明南部组件
	private JPanel jpSouth;
	private JTextArea jtaInput;
	private JTextArea jlOutput;
	//声明中部组件
	private JScrollPane jspCenter;
	private JTextArea ta;
	private JLabel welcome;
	private JPopupMenu jpmText;
	private JMenuItem copy;
	private JMenuItem cut;
	private JMenuItem paste;
	private JMenuItem undo;
	private JMenuItem redo;
	private JMenuItem selectAll;
	private Clipboard clipboard;
	//声明状态栏组件
	private String currentFileName;
	private String currentUserName;

	private JLabel jlUserName;
	private JLabel jlFileName;
	private JLabel jlTime;
	private Timer timer;
	
	private boolean isEdited;
	//声明undo/redo的manager
	private CommandManager commandManager;
	private int lastEditLength;
	
	public void initMenu(){
		// 定义菜单栏组件
		jmb = new JMenuBar();
		file = new JMenu("File");
		file.setBorder(BorderFactory.createRaisedBevelBorder());
		file.setFont(MyFont.f4);
		edit = new JMenu("Edit");
		edit.setBorder(BorderFactory.createRaisedBevelBorder());
		edit.setFont(MyFont.f4);
		run = new JMenu("Run");
		run.setBorder(BorderFactory.createRaisedBevelBorder());
		run.setFont(MyFont.f4);
		user = new JMenu("System");
		user.setBorder(BorderFactory.createRaisedBevelBorder());
		user.setFont(MyFont.f4);

		// 定义条目,设置快捷键，设置事件监听
		fileNew = new JMenuItem("New");
		fileNew.setActionCommand("newFile");
		fileNew.addActionListener(this);

		fileOpen = new JMenuItem("Open", new ImageIcon("image/folder.png"));
		fileOpen.setActionCommand("openFile");
		fileOpen.addActionListener(this);
		fileSave = new JMenuItem("Save", new ImageIcon("image/save.png"));
		fileSave.setActionCommand("saveFile");
		fileSave.addActionListener(this);
		fileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

		fileExit = new JMenuItem("Exit", new ImageIcon("image/exit.png"));
		fileExit.setActionCommand("exit");
		fileExit.addActionListener(this);
		file.add(fileNew);
		file.add(fileOpen);
		file.add(fileSave);
		file.addSeparator();
		file.add(fileExit);
		jmb.add(file);

		editCut = new JMenuItem("Cut");
		editCut.setActionCommand("cut");
		editCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		editCut.addActionListener(this);
		editCopy = new JMenuItem("Copy");
		editCopy.setActionCommand("copy");
		editCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		editCopy.addActionListener(this);
		
		editPaste = new JMenuItem("Paste");
		editPaste.setActionCommand("paste");
		editPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
		editPaste.addActionListener(this);
		editSelectAll = new JMenuItem("SelectAll");
		editSelectAll.setActionCommand("selectAll");
		editSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		editSelectAll.addActionListener(this);

		editUndo = new JMenuItem("Undo");
		editUndo.setActionCommand("undo");
		editUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
		editUndo.addActionListener(this);
		editRedo = new JMenuItem("Redo");
		editRedo.setActionCommand("redo");
		editRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK));
		editRedo.addActionListener(this);
		
		edit.add(editCut);
		edit.add(editCopy);
		edit.add(editPaste);
		edit.add(editSelectAll);
		edit.add(editUndo);
		edit.add(editRedo);
		
		//代码区的不同情况决定了哪些命令可用，哪些不可以
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ta.getText().equals("")) {
					editCut.setEnabled(false);
					editCopy.setEnabled(false);
				} else {
					editCut.setEnabled(true);
					editCopy.setEnabled(true);
				}
				if (clipboard.getContents(this) == null) {
					editPaste.setEnabled(false);
				} else {
					editPaste.setEnabled(true);
				}
			}
		});

		jmb.add(edit);
		
		execute = new JMenuItem("Execute", new ImageIcon("image/star.png"));
		execute.setActionCommand("execute");
		execute.addActionListener(this);
		run.add(execute);
		jmb.add(run);

		login = new JMenuItem("Sign in", new ImageIcon("image/user.png"));
		login.setActionCommand("login");
		login.addActionListener(this);
		logout = new JMenuItem("Sign out");
		logout.setActionCommand("logout");
		logout.addActionListener(this);
		user.add(login);
		user.add(logout);
		jmb.add(user);
		this.setJMenuBar(jmb);
	}
	
	public void initNorth(){

		// 定义中部的代码区
		// 启动时如果没有newFile，那么显示欢迎画面

		// 设置两个组件的位置，大小一致，就可以实现一个组件对另外一个组件的覆盖
		// 说明：先加入的组件，会显示在最上面，后加入的组件，会在底层。
		
		jlUserName = new JLabel("User:" + this.currentUserName);
		jlUserName.setFont(MyFont.f5);
		jlUserName.setBounds(10, 0, 200, 30);
		jlFileName = new JLabel();
		jlFileName.setBounds(250, 0, 400, 30);
		jlFileName.setHorizontalAlignment(SwingConstants.LEFT);
		jlFileName.setFont(MyFont.f2);
		jlTime = new JLabel();
		jlTime.setBounds(600, 0, 250, 30);
		jlTime.setFont(MyFont.f5);
		// 每秒更新一次时间
		timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jlTime.setText(
						"Time:" + new SimpleDateFormat("yyyy-MM-dd- kk:mm:ss").format(System.currentTimeMillis()));
			}
		});
		timer.start();
	}
	
	public void initRightClick(){
		// 定义代码区
		// 定义代码区的右键菜单
		jpmText = new JPopupMenu();
		cut = new JMenuItem("Cut");
		cut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editCut.doClick();
			}
		});
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		copy = new JMenuItem("Copy");
		copy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editCopy.doClick();
			}
		});
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		paste = new JMenuItem("Paste");
		paste.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editPaste.doClick();
			}
		});
		paste.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK)));
		selectAll = new JMenuItem("SelectAll");
		selectAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editSelectAll.doClick();
			}
		});
		selectAll.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK)));
		undo = new JMenuItem("Undo");
		undo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editUndo.doClick();
			}
		});
		undo.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK)));
		
		redo = new JMenuItem("Redo");
		redo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				editRedo.doClick();
			}
		});
		redo.setAccelerator((KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_DOWN_MASK)));
		
		jpmText.add(cut);
		jpmText.add(copy);
		jpmText.add(paste);
		jpmText.add(selectAll);
		jpmText.add(undo);
		jpmText.add(redo);
		
		jpmText.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					if (ta.getText().equals("")) {
						cut.setEnabled(false);
						copy.setEnabled(false);
					} else {
						cut.setEnabled(true);
						cut.setEnabled(true);
					}
					if (clipboard.getContents(this) == null) {
						paste.setEnabled(false);
					} else {
						paste.setEnabled(true);
					}
				}
			}
		});
	}
	
	public void initCodeSegment(){
		//代码区
				welcome = new JLabel(new ImageIcon("image/3.jpg"));
				welcome.setBounds(200, 30, 644, 400);
				ta = new JTextArea();
				ta.setLineWrap(true);
				ta.setWrapStyleWord(true);
				ta.setBounds(200, 30, 644, 400);
				ta.setMargin(new Insets(10, 10, 10, 10));
				ta.setBackground(Color.LIGHT_GRAY);
				ta.addMouseListener(new MouseAdapter() {

					@Override
					public void mousePressed(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON3) {
							jpmText.show(e.getComponent(), e.getX(), e.getY());
						}
					}

				});
				//KeyPressed是键被按下，KeyReleased是键被弹起，这两个都是更底层一些的事件。
				//KeyTypede是指有字符被输入，比如按住shift，再按A键，如果当时Caps Lock不亮，就产生一个输入大写A的事件。
				ta.addKeyListener(new KeyAdapter() {

					@Override
					public void keyPressed(KeyEvent e) {
						isEdited = true;
						commandManager.executeCommand(new TypeCommand(ta,lastEditLength));
					}
				});
				
				jspCenter = new JScrollPane(ta);
				jspCenter.setBounds(200, 30, 644, 400);
				jspCenter.setWheelScrollingEnabled(true);
				jspCenter.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				this.add(welcome);
				this.add(jlUserName);
				this.add(jlFileName);
				this.add(jlTime);
				this.add(jspCenter);
	}
	
	public void initSouth(){
		// 定义南部的输入输出区
		jpSouth = new JPanel();
		jpSouth.setLayout(new GridLayout(1, 2));
		jpSouth.setBounds(200, 430, 650, 200);
		jtaInput = new JTextArea();
		jtaInput.setBackground(new Color(175, 238, 238));
		jtaInput.setLineWrap(true);
		jtaInput.setWrapStyleWord(true);
		jlOutput = new JTextArea();
		jlOutput.setBackground(Color.LIGHT_GRAY);
		jlOutput.setEditable(false);
		jpSouth.add(jtaInput);
		jpSouth.add(jlOutput);
		this.add(jpSouth);
	}
	
	public void initWest(){

		// 定义西侧的版本树
		jpWest = new JPanel();
		jpWest.setBounds(0, 26, 200, 600);
		fileList = new Vector();
		list = new JList();
		refreshFileList();
		list.setFixedCellWidth(200);
		list.setFont(MyFont.f2);
		list.setSelectionBackground(Color.yellow);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3 && list.getSelectedIndex() != -1) {
					jpmVersion.show(e.getComponent(), e.getX(), e.getY());
				}
			}

		});
		jpWest.add(list, BorderLayout.CENTER);

		// 实现右键菜单
		jpmVersion = new JPopupMenu();
		open = new JMenuItem("open");
		open.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				fileOpen.doClick();
			}
		});
		jpmVersion.add(open);
		this.add(jpWest);
	}
	
	public MainFrame(String username) {
		// 获取系统的剪切板
		this.clipboard =  Toolkit.getDefaultToolkit().getSystemClipboard();
		//定义CommandManager
		commandManager = new CommandManager();
		
		
		//定义显示风格为Windows风格
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		this.currentUserName = username;
		this.setTitle("BF CodeRunner");
		this.setLayout(null);
		initMenu();
		initNorth();
		initRightClick();
		initCodeSegment();
		initSouth();
		initWest();
		

		// 定义JFrame的属性
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("image/2.png"));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				fileExit.doClick();
			}
			
		});
		this.setSize(800, 600);
		this.setLocation(400, 200);
		this.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();
		if (isLogged() == false && (cmd.equals("newFile") || cmd.equals("openFile") || cmd.equals("saveFile")
				|| cmd.equals("redo") || cmd.equals("undo") || cmd.equals("execute"))) {
			showMsg("NotLogged");
			return;
		}
		if (isLogged() == false && cmd.equals("logout")) {
			showMsg("HaveLoggedOut");
			return;
		}
		try {
			switch (cmd) {
			case "newFile":
				String fileName = JOptionPane.showInputDialog(this, "FileName:");
				if (fileName != null) {
					this.currentFileName = fileName;
					refreshFileName();
					ta.setText("");
					isEdited = false;
					lastEditLength = 0;
					welcome.setVisible(false);
				}
				break;
			case "openFile":
				int index = list.getSelectedIndex();
				if (index >= 0) {
					welcome.setVisible(false);
					String selectedFileName = (String) fileList.get(index);
					ta.setText(
							RemoteHelper.getInstance().getIOService().readFile(this.currentUserName, selectedFileName));
					this.currentFileName = selectedFileName;
					isEdited = false;
					lastEditLength = ta.getText().length();
					refreshFileName();
				}
				break;
			case "saveFile":
				if (!ta.getText().equals("") && isEdited) {
					if (this.currentFileName.matches(".*\\d{14}")) {
						this.currentFileName = currentFileName.substring(0, currentFileName.length() - 14);
					}
					this.currentFileName += new SimpleDateFormat("yyyyMMddkkMMss").format(System.currentTimeMillis());
					RemoteHelper.getInstance().getIOService().writeFile(ta.getText(), currentUserName, currentFileName);
					isEdited = false;
					lastEditLength = ta.getText().length();
					refreshFileList();
				}
				break;
			case "exit":
				if (isEdited) {
					int choose = JOptionPane.showConfirmDialog(this,
							"You havn't save current file, Do you want to save ? ", "Not yet save",
							JOptionPane.YES_NO_OPTION);
					if (choose == JOptionPane.YES_OPTION){
						fileSave.doClick();
					}
				}
				this.dispose();
				break;
			case "cut":
				System.out.println("CUT");
				this.cut();
				break;
			case "copy":
				System.out.println("COPY");
				this.copy();
				break;
			case "paste":
				System.out.println("PASTE");
				this.paste();
				break;
			case "selectAll":
				System.out.println("SelectAll");
				this.selectAll();
				break;
			case "undo":
				System.out.println("undo");
				commandManager.undo();
				break;
			case "redo":
				System.out.println("redo");
				commandManager.redo();
				break;
			case "execute":
				if (!ta.getText().equals("")) {
					jlOutput.setText("");
					if (isEdited) {
						fileSave.doClick();
					}
					jlOutput.setText(RemoteHelper.getInstance().getIOService().execCode(currentUserName,
							currentFileName, jtaInput.getText()));
				}
				break;
			case "login":
				LoginDialog dialog = new LoginDialog(false);
				dialog.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosed(WindowEvent e) {
						String username = dialog.getUserName();
						if (username != null) {
							setCurrentUserName(username);
							refreshUserName();
						}
					}
				});
				break;
			case "logout":
				if (RemoteHelper.getInstance().getUserService().logout(currentUserName)) {
					showMsg("logout");
					this.currentUserName = "Not logged in";
					refreshUserName();
				} else {
					showMsg("logoutfail");
				}
				break;
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
			showMsg("error");
			System.exit(ERROR);
		}
	}

	public void showMsg(String msg) {
		switch (msg) {
		case "logout":
			JOptionPane.showMessageDialog(this, "GoodBye~ " + this.currentUserName);
			break;
		case "error":
			JOptionPane.showMessageDialog(this, "System Error!", "Error", JOptionPane.ERROR_MESSAGE,
					new ImageIcon("4.png"));
			break;
		case "NotLogged":
			JOptionPane.showMessageDialog(this, "Not yet logged in", "Please sign in", JOptionPane.ERROR_MESSAGE,
					new ImageIcon("4.png"));
			break;
		case "HaveLoggedOut":
			JOptionPane.showMessageDialog(this, "You have logged out ");
			break;
		case "logoutfail":
			JOptionPane.showMessageDialog(this, "Logged out fail, Please try agagin " + this.currentUserName);
			break;
		}
	}

	public void refreshFileList() {
		this.fileList.clear();
		String fileListStr = "";
		try {
			fileListStr = RemoteHelper.getInstance().getIOService().readFileList(this.currentUserName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		String[] mainFiles = fileListStr.split(";");
		for (int i = 0; i < mainFiles.length; i++) {
			String[] items = mainFiles[i].split(",");
			for (int j = 0; j < items.length; j++) {
				this.fileList.add(items[j]);
			}
		}
		list.setListData(fileList);
	}

	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}

	public boolean isLogged() {
		return !this.currentUserName.equals("Not logged in");
	}

	public void refreshFileName() {
		jlFileName.setText("FileName: " + currentFileName);
	}

	public void refreshUserName() {
		jlUserName.setText("User: " + currentUserName);
	}

	public void cut() {
		copy();
		int start = ta.getSelectionStart();
		int end = ta.getSelectionEnd();
		//加入undo/redo机制
		String temp = ta.getSelectedText();
		ta.replaceRange("", start, end);
//System.out.println("被截下的子串"+temp);
		this.commandManager.executeCommand(new CutCommand(ta,start,end,temp));
	}

	public void copy() {
		String content = ta.getSelectedText();
		StringSelection ss = new StringSelection(content);
		clipboard.setContents(ss, null);
	}

	public void paste() {

		Transferable transf = clipboard.getContents(null);
		String content = "";
		try {
			if (transf != null && transf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
				content = (String) transf.getTransferData(DataFlavor.stringFlavor);
				// 如果选中了文本，那么将选中的文本替换为剪切板中的内容
				if (ta.getSelectedText() != null) {
					int start = ta.getSelectionStart();
					int end = ta.getSelectionEnd();
					ta.replaceRange(content, start, end);
					this.commandManager.executeCommand(new PasteCommand(ta,content,start));
				} else {
					int pos = ta.getCaretPosition();
					ta.insert(content, pos);
					this.commandManager.executeCommand(new PasteCommand(ta,content,pos));
				}
				
			}
		} catch (UnsupportedFlavorException | IOException e) {
			e.printStackTrace();
		}

	}

	public void selectAll() {
		ta.selectAll();
	}
}

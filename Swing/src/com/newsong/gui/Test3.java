package com.newsong.gui;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.Container;
import javax.swing.table.AbstractTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.DefaultCellEditor;
import javax.swing.table.TableColumn;

@SuppressWarnings("all")
public class Test3 extends JFrame {

	private JTable table = null;
	private JScrollPane jsp = null;
	private JComboBox cob = null;
	private JCheckBox ckb = null;
	private JTextField txt = null;

	public Test3() {
		this.setTitle("JTable��ListSelectionModeld�ļ����¼�");
		this.setSize(400, 360);

		// ��ȡ������������
		Container container = this.getContentPane();
		MyAbstractTableModel myModel = new MyAbstractTableModel();

		// JTable
		table = new JTable(myModel);
		// ��ñ��ı������
		TableColumn tc1 = table.getColumnModel().getColumn(2);
		TableColumn tc2 = table.getColumnModel().getColumn(4);
		TableColumn tc3 = table.getColumnModel().getColumn(5);

		// ʵ����JCheckBox
		ckb = new JCheckBox();
		tc1.setCellEditor(new DefaultCellEditor(ckb));

		// ʵ����JComboBox
		cob = new JComboBox();
		cob.addItem("HN");
		cob.addItem("HB");
		cob.addItem("BJ");
		tc2.setCellEditor(new DefaultCellEditor(cob));

		// ʵ����JTextField
		txt = new JTextField("");
		txt.setSize(100, 26);
		tc3.setCellEditor(new DefaultCellEditor(txt));

		// JScrollPane
		jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		container.add(jsp);

		// ����Զ���ĳ�����ģ��

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		new Test3();
	}
}

class MyAbstractTableModel extends AbstractTableModel {
	
	// �����ͷ����
	String[] head = { "ID", "NAME", "SEX(boy)", "AGE", "ADDRESS", "DEMO" };
	// ������������
	// Class[]
	// typeArray={Object.class,Object.class,Boolean.class,int.class,Object.class,Object.class};

	// ��������������
	Object[] data1 = { "200913420125", "SUMMER", new Boolean(true), new Integer(20), "1", "NULL" };
	Object[] data2 = { "200913420124", "WULEI", new Boolean(true), new Integer(20), "2", "NULL" };
	Object[] data3 = { "200913420125", "BOOK", new Boolean(false), new Integer(20), "3", "NULL" };
	Object[] data4 = { "200913420125", "CUP", new Boolean(true), new Integer(20), "4", "NULL" };
	Object[] data5 = { "200913420125", "MOUSE", new Boolean(true), new Integer(20), "5", "NULL" };
	// ������ÿһ�е���������

	Class[] typeArray = { Object.class, Object.class, Boolean.class, Integer.class, JComboBox.class, Object.class };

	Object[][] data = { data1, data2, data3, data4, data5 };

	// ��ñ�������
	public int getColumnCount() {
		return head.length;
	}

	// ��ñ�������
	public int getRowCount() {
		return data.length;
	}

	// ��ñ���������
	@Override
	public String getColumnName(int column) {
		return head[column];
	}

	// ��ñ��ĵ�Ԫ�������
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	// ʹ�����пɱ༭��
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	// �滻��Ԫ���ֵ
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = aValue;
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	// ʵ���������boolean�Զ�ת��JCheckbox
	/*
	 * ��Ҫ�Լ���celleditor��ô�鷳�ɡ�jtable�Զ�֧��Jcheckbox��
	 * ֻҪ����tablemodel��getColumnClass����һ��boolean��class�� jtable���Զ���һ��Jcheckbox���㣬
	 * ���value��true����falseֱ�Ӷ�table���Ǹ�cell��ֵ�Ϳ���
	 */
	public Class getColumnClass(int columnIndex) {
		return typeArray[columnIndex];// ����ÿһ�е���������
	}
}

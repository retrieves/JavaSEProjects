package com.newsong.model;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.newsong.DAO.EnterStockDAO;
import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.JavaBean.EnterStock;

@SuppressWarnings("all")
/**
 * ��ʾ���Ĵ�����Ϣ
 * @author Administrator
 *
 */
public class EnterStockModel  extends AbstractTableModel implements EnterStockDAO  {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��");
	private Vector rowData;
	private Vector<String> columnNames;
	private Vector<String> line;
	
	public EnterStockModel() {
		columnNames = new Vector<String>();
		rowData = new Vector<>();
		columnNames.add("��ⵥ��");
		columnNames.add("���ʱ��");
		columnNames.add("��⸺����");
	}
	
	private Vector addLine(EnterStock enterStock) {
		line = new Vector<String>();
		line.add(enterStock.getNo());
		line.add(sdf.format(enterStock.getEnterTime()));
		line.add(enterStock.getEmpName());
		return line;
	}
	
	@Override
	public void findAll() {
		String sql = "select enter_no as no ,enter_time as enterTime , emp_info.emp_name as empName "
				+ "from enter_stock join emp_info on enter_stock.enter_emp = emp_info.emp_no ";
		List<EnterStock> list = JDBCTemplateHelper.findAll(sql, EnterStock.class);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				rowData.add(addLine(list.get(i)));
			}
		}
	}

	@Override
	public void addEnterStock(EnterStock enterStock) {
		String sq = "select enter_no as no from enter_stock order by enter_time desc limit 1";
		String enterNo = JDBCTemplateHelper.find(sq, EnterStock.class).getNo();
System.out.println(enterNo);
		int no = Integer.parseInt(enterNo.substring(2))+1;
		enterNo = enterNo.substring(0, 2)+no;
		enterStock.setNo(enterNo);
System.out.println(enterNo);
		String sql = "insert into enter_stock(enter_no,enter_time,enter_emp) values(:no,:enterTime,:empId)";
		JDBCTemplateHelper.add(sql,enterStock);
	}
	
	/**
	 * �õ���ͷ
	 */
	public String getColumnName(int col) {
		return columnNames.get(col);
	}

	/**
	 * �õ�����
	 */
	public int getColumnCount() {
		return columnNames.size();
	}

	/**
	 * �õ�����
	 */
	public int getRowCount() {
		return rowData.size();
	}

	/**
	 * �õ�ĳ��ĳ�е�����
	 */
	public Object getValueAt(int row, int col) {
		return ((Vector) rowData.get(row)).get(col);
	}


	
}

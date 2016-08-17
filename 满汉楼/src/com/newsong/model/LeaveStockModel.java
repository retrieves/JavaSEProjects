package com.newsong.model;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.newsong.DAO.EnterStockDAO;
import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.DAO.LeaveStockDAO;
import com.newsong.JavaBean.EnterStock;
import com.newsong.JavaBean.LeaveStock;

@SuppressWarnings("all")
/**
 * 显示出库的粗略信息
 * @author Administrator
 *
 */
public class LeaveStockModel  extends AbstractTableModel implements LeaveStockDAO  {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
	private Vector rowData;
	private Vector<String> columnNames;
	private Vector<String> line;
	
	public LeaveStockModel() {
		columnNames = new Vector<String>();
		rowData = new Vector<>();
		columnNames.add("出库单号");
		columnNames.add("出库时间");
		columnNames.add("出库负责人");
	}
	
	private Vector addLine(LeaveStock leaveStock) {
		line = new Vector<String>();
		line.add(leaveStock.getNo());
		line.add(sdf.format(leaveStock.getLeaveTime()));
		line.add(leaveStock.getEmpName());
		return line;
	}
	
	@Override
	public void findAll() {
		String sql = "select leave_no as no ,leave_time as leaveTime , emp_info.emp_name as empName "
				+ "from leave_stock join emp_info on leave_stock.leave_emp = emp_info.emp_no ";
		List<LeaveStock> list = JDBCTemplateHelper.findAll(sql, LeaveStock.class);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				rowData.add(addLine(list.get(i)));
			}
		}
	}
	
	@Override
	public void addLeaveStock(LeaveStock leaveStock) {
		String sq = "select leave_no as no from leave_stock order by leave_time desc limit 1";
		String leaveNo = JDBCTemplateHelper.find(sq, LeaveStock.class).getNo();
		int no = Integer.parseInt(leaveNo.substring(2))+1;
		leaveNo = leaveNo.substring(0, 2)+no;
		leaveStock.setNo(leaveNo);
		String sql = "insert into leave_stock(leave_no,leave_time,leave_emp ) values(:no,:leaveTime,:empId)";
		JDBCTemplateHelper.add(sql,leaveStock);
	}

	/**
	 * 得到表头
	 */
	public String getColumnName(int col) {
		return columnNames.get(col);
	}

	/**
	 * 得到列数
	 */
	public int getColumnCount() {
		return columnNames.size();
	}

	/**
	 * 得到行数
	 */
	public int getRowCount() {
		return rowData.size();
	}

	/**
	 * 得到某行某列的数据
	 */
	public Object getValueAt(int row, int col) {
		return ((Vector) rowData.get(row)).get(col);
	}

	

	

	
}

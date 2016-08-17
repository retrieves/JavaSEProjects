package com.newsong.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.DAO.LeaveStockItemDAO;
import com.newsong.JavaBean.LeaveStockItem;
import com.newsong.JavaBean.Stock;
@SuppressWarnings("all")
public class LeaveStockItemModel extends AbstractTableModel implements LeaveStockItemDAO {
	private Vector rowData;
	private Vector<String> columnNames;
	private Vector line;
	String leaveStockId ;
	
	public LeaveStockItemModel(String leaveStockId) {
		this();
		this.leaveStockId = leaveStockId;
	}
	
	public LeaveStockItemModel() {
		columnNames = new Vector<String>();
		rowData = new Vector<>();
		columnNames.add("编号");
		columnNames.add("名称");
		columnNames.add("数量");
	}
	
	private Vector addLine(Stock stock) {
		line = new Vector<String>();
		line.add(stock.getId());
		line.add(stock.getName());
		line.add(stock.getQuantity());
		return line;
	}
	
	
	@Override
	public void addLeaveStockItem(LeaveStockItem lsi) {
		String sql = "insert into leave_stock_detail(leave_no,mat_id,mat_qty) values(:leaveStockNo,:matId,:matQty)";
		JDBCTemplateHelper.add(sql, lsi);
		
	}

	@Override
	public void findAllLeaveStockItems() {
		String sql = "select leave_stock_detail.mat_id as id , mat_name as name,mat_qty as quantity   from leave_stock_detail "
				+ "join material on leave_stock_detail.mat_id = material.mat_id where leave_no = ?";
		List<Stock> list = JDBCTemplateHelper.findAll(sql, Stock.class,leaveStockId);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				rowData.add(addLine(list.get(i)));
			}
		}
	}
	
	
	/**
	 * 得到某列的列名
	 */

	@Override
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

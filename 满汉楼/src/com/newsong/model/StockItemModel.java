package com.newsong.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.newsong.DAO.EnterStockItemDAO;
import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.JavaBean.EnterStockItem;
import com.newsong.JavaBean.Stock;
@SuppressWarnings("all")
/**
 * 显示选择出入库的原料及出入库数量
 * @author Administrator
 *
 */
public class StockItemModel extends AbstractTableModel  {
	
	private Vector rowData;
	private Vector<String> columnNames;
	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] { String.class, String.class, String.class,
			 Boolean.class, String.class };
	private Vector line;
	StockModel sm;
	EnterStockItemModel esim;
	
	public StockItemModel() {
		columnNames = new Vector<String>();
		rowData = new Vector<>();
		columnNames.add("编号");
		columnNames.add("名称");
		columnNames.add("单位");
		columnNames.add("出入库");
		columnNames.add("数量");
	}
	
	private Vector addLine(Stock stock) {
		line = new Vector<String>();
		line.add(""+stock.getId());
		line.add(stock.getName());
		line.add(stock.getUnit());
		line.add(new Boolean(false));
		line.add(""+0);
		return line;
	}
	
	public void findAll() {
		String sql = "select material.mat_id as id,mat_name as name,mat_unit as unit	from material ";
		List<Stock> list = JDBCTemplateHelper.findAll(sql, Stock.class);
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

	public void setValueAt(Object aValue, int row, int col) {
		((Vector) rowData.get(row)).set(col, aValue);
		fireTableCellUpdated(row, col);
	};

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return (columnIndex == 3|| columnIndex == 4);
		
	}

	@Override
	public Class<?> getColumnClass(int col) {
		return COLUMN_TYPES[col];
	}

	

	
}

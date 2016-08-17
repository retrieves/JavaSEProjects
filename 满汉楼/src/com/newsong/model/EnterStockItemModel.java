package com.newsong.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.newsong.DAO.EnterStockItemDAO;
import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.JavaBean.EnterStockItem;
import com.newsong.JavaBean.Stock;

/**
 * 显示一次入库的原料的详细信息
 * @author Administrator
 *
 */
@SuppressWarnings("all")
public class EnterStockItemModel extends AbstractTableModel implements EnterStockItemDAO {
	
	
	private Vector rowData;
	private Vector<String> columnNames;
	private Vector line;
	String enterStockId ;
	
	public EnterStockItemModel(String enterStockId) {
		this();
		this.enterStockId = enterStockId;
	}
	
	public EnterStockItemModel() {
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
	//暂时使用Stock来保存编号、名称和数量，实际上Stock并不是用来表示这些变量的JavaBean
	@Override
	public void findAllEnterStockItems() {
		String sql = "select enter_stock_detail.mat_id as id , mat_name as name,mat_qty as quantity   from enter_stock_detail "
				+ "join material on enter_stock_detail.mat_id = material.mat_id where enter_no = ?";
		List<Stock> list = JDBCTemplateHelper.findAll(sql, Stock.class,enterStockId);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				rowData.add(addLine(list.get(i)));
			}
		}
	}
	
	@Override
	public void addEnterStockItem(EnterStockItem esi) {
		String sql = "insert into enter_stock_detail(enter_no,mat_id,mat_qty) values(:enterStockNo,:matId,:matQty)";
		JDBCTemplateHelper.add(sql, esi);
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

package com.newsong.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.DAO.StockDAO;
import com.newsong.JavaBean.Stock;
import com.newsong.JavaBean.User;

@SuppressWarnings("all")
public class StockModel extends AbstractTableModel implements StockDAO  {
	private Vector rowData;
	private Vector<String> columnNames;
	private Vector<String> line;
	
	public StockModel() {
		columnNames = new Vector<String>();
		rowData = new Vector<>();
		columnNames.add("���");
		columnNames.add("����");
		columnNames.add("��λ");
		columnNames.add("��Ӧ��");
		columnNames.add("���");
	}
	
	private Vector addLine(Stock stock) {
		line = new Vector<String>();
		line.add(""+stock.getId());
		line.add(stock.getName());
		line.add(stock.getUnit());
		line.add(stock.getSupName());
		line.add(""+stock.getQuantity());
		return line;
	}
	
	
	@Override
	public void findAll() {
		String sql = "select material.mat_id as id,mat_name as name, mat_unit as unit, supplier.sup_name as supName ,quantity "
				+"from material join stock on stock.mat_id = material.mat_id join supplier on supplier.sup_id = material.mat_sup";
		List<Stock> list = JDBCTemplateHelper.findAll(sql, Stock.class);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				rowData.add(addLine(list.get(i)));
			}
		}
	}
	
	public Stock find(int id) {
		String sql  = "select material.mat_id as id,mat_name as name, mat_unit as unit, supplier.sup_name as supName ,quantity "
				+"from material  join stock on stock.mat_id = material.mat_id join supplier on supplier.sup_id = material.mat_sup where material.mat_id = ?";
		Stock stock = JDBCTemplateHelper.find(sql, Stock.class, id);
		return stock;
	}
	
	@Override
	public void addStock(Stock stock) {
		String sql = "insert into material(mat_name,mat_unit,mat_sup) values(:name,:unit,:supId)";
		JDBCTemplateHelper.addConcludesId(sql, stock);
		String sql2 = "insert into stock(mat_id,quantity) values(:id,:quantity) ";
		JDBCTemplateHelper.add(sql2,stock);
	}

	@Override
	public void updStock(Stock stock) {
		String sql = "update material set mat_name =:name,mat_unit=:unit,mat_sup =:supId where mat_id = :id";
		JDBCTemplateHelper.update(sql, stock);
	}

	@Override
	public void deleteStock(int id) {
		String sql = "delete from material where mat_id = ?";
		JDBCTemplateHelper.delete(sql, id);
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
		System.out.println(((Vector) rowData.get(row)).get(col));
		return ((Vector) rowData.get(row)).get(col);
	}


	
}

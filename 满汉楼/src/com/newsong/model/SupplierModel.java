package com.newsong.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.DAO.SupplierDAO;
import com.newsong.JavaBean.Supplier;
@SuppressWarnings("all")
public class SupplierModel  extends AbstractTableModel implements SupplierDAO  {
	private Vector rowData;
	private Vector<String> columnNames;
	private Vector<String> line;
	
	
	public SupplierModel() {
		columnNames = new Vector<String>();
		rowData = new Vector<>();
		columnNames.add("编号");
		columnNames.add("名称");
		columnNames.add("地址");
		columnNames.add("联系方式");
		columnNames.add("邮箱");
	}
	
	private Vector addLine(Supplier sup) {
		line = new Vector<String>();
		line.add(""+sup.getId());
		line.add(sup.getName());
		line.add(sup.getAddr());
		line.add(sup.getPhone());
		line.add(sup.getEmail());
		return line;
	}
	
	@Override
	public List<Supplier> findAll() {
		String sql = "select sup_id as id ,sup_name as name,sup_addr as addr,sup_phone as phone ,sup_email as email,sup_note as note from supplier";
		List<Supplier> list = JDBCTemplateHelper.findAll(sql, Supplier.class);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				rowData.add(addLine(list.get(i)));
			}
		}
		return list;
	}

	public Map<String,Integer> findAllToMap(){
		String sql = "select sup_id as id ,sup_name as name from supplier";
		List<Supplier> list = JDBCTemplateHelper.findAll(sql, Supplier.class);
		Map<String, Integer> map = new HashMap<>();
		Supplier sup = null;
		for (int i = 0; i < list.size(); i++) {
			sup = list.get(i);
			map.put(sup.getName(),sup.getId());
		}
		return map;
	}
	
	@Override
	public Supplier find(int id) {
		String sql = "select sup_id as id ,sup_name as name,sup_addr as addr,sup_phone as phone , sup_email as email , sup_note as note from supplier where sup_id = ?";
		Supplier sup = JDBCTemplateHelper.find(sql, Supplier.class, id);
		return sup;
	}
	
	@Override
	public void addSupplier(Supplier sup) {
		String sql = "insert into supplier(sup_name,sup_addr,sup_phone,sup_email,sup_note) values(:name,:addr,:phone,:email,:note)";
		JDBCTemplateHelper.addConcludesId(sql, sup);
	}

	@Override
	public void updateSupplier(Supplier sup) {
		String sql = "update supplier set sup_name =:name,sup_phone=:phone,sup_email=:email,sup_note =:note where sup_id=:id";
		JDBCTemplateHelper.update(sql, sup);
	}

	@Override
	public void deleteSupplier(int id) {
		String sql = "delete from supplier where sup_id =?";
		JDBCTemplateHelper.delete(sql, id);
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
		System.out.println(((Vector) rowData.get(row)).get(col));
		return ((Vector) rowData.get(row)).get(col);
	}

	


	
	

}

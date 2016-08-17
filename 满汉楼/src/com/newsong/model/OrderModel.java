package com.newsong.model;

import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.DAO.JDBCUtils;
import com.newsong.DAO.OrderDAO;
import com.newsong.JavaBean.Customer;
import com.newsong.JavaBean.Food;
import com.newsong.JavaBean.Order;

@SuppressWarnings("all")

public class OrderModel extends AbstractTableModel implements OrderDAO {
	private Vector rowData;
	private Vector<String> columnNames;
	private static final Class<?>[] COLUMN_TYPES = new Class<?>[] { String.class, String.class, String.class,
			String.class, Boolean.class, String.class };
	private Vector line;
	int deskId;
	
	public OrderModel(int deskId) {
		this.deskId = deskId;
		columnNames = new Vector<String>();
		rowData = new Vector<>();
		columnNames.add("编号");
		columnNames.add("菜名");
		columnNames.add("价格");
		columnNames.add("桌号");
		columnNames.add("选择");
		columnNames.add("数量");
	}

	private Vector addLine(Food food) {
		line = new Vector<String>();
		line.add("" + food.getId());
		line.add(food.getName());
		line.add("" + food.getPrice());
		line.add(deskId);
		line.add(new Boolean(false));
		line.add("" + 0);
		return line;
	}

	@Override
	public void addOrder(Order order) {
		String sql = "insert into order_info(desk_id,order_time,order_amount ) values(:deskId,:orderTime,:orderAmount)";
		JDBCTemplateHelper.addConcludesId(sql, order);
	}
	
	@Override
	public Order findOrder() {
		String sql = "select order_id as Id , desk_id as deskId , order_time as orderTime,order_amount as orderAmount "
				+ "from  order_info where order_id = (select max(order_id) from order_info where desk_id = ? ) "; 
		Order order = null;
		order  = JDBCTemplateHelper.find(sql, Order.class, deskId);
		return order;
	}
	
	public void discount() {
		Order order = findOrder();
		order.setOrderAmount(order.getOrderAmount()*0.8);
		String sql = "update order_info set order_amount = :orderAmount where order_id = :Id";
		JDBCTemplateHelper.update(sql, order);
	}
	
	public void findAllFoods() {
		String sql = "select food_id as id,food_name as name, food_price as price from food ";
		List<Food> list = JDBCTemplateHelper.findAll(sql, Food.class);
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
		return (columnIndex == 4|| columnIndex == 5);
		
	}

	@Override
	public Class<?> getColumnClass(int col) {
		return COLUMN_TYPES[col];
	}

	
	
}

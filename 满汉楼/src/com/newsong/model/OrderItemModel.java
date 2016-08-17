package com.newsong.model;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.DAO.JDBCUtils;
import com.newsong.DAO.OrderItemDAO;
import com.newsong.JavaBean.Food;
import com.newsong.JavaBean.OrderItem;
import com.newsong.JavaBean.User;


@SuppressWarnings("all")
public class OrderItemModel extends AbstractTableModel implements OrderItemDAO {
	private Vector rowData;
	private Vector<String> columnNames;
	private Vector line;
	
	int orderId;
	
	
	public OrderItemModel(int orderId) {
		this.orderId = orderId;
		columnNames = new Vector<String>();
		rowData = new Vector<>();
		columnNames.add("���");
		columnNames.add("����");
		columnNames.add("�۸�");
		columnNames.add("����");
	}

	private Vector addLine(OrderItem orderItem) {
		line = new Vector<String>();
		line.add(orderItem.getFoodId());
		line.add(orderItem.getFoodName());
		line.add(orderItem.getFoodPrice());
		line.add(orderItem.getFoodServings());
		return line;
	}
	
	@Override
	public void addOrderItem(OrderItem orderItem) {
		String sql = "insert into order_item(order_id,food_id,food_servings) values(:Id,:foodId,:foodServings)";
		JDBCTemplateHelper.add(sql, orderItem);
	}
	
	@Override
	public void findOrderItems() {
		String sql = "select order_id as Id , food.food_id as foodId,food_servings as foodServings , food_name as foodName,food_price as foodPrice"
				+ " from order_item join food on food.food_id = order_item.food_id where order_id = ? ";
		List<OrderItem> list = JDBCTemplateHelper.findAll(sql, OrderItem.class,orderId);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				rowData.add(addLine(list.get(i)));
			}
		}
	}


	@Override
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

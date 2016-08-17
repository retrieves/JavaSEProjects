package com.newsong.model;

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

import com.newsong.DAO.FoodDAO;
import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.DAO.JDBCUtils;
import com.newsong.JavaBean.Employee;
import com.newsong.JavaBean.Food;
import com.newsong.JavaBean.User;
@SuppressWarnings("all")
public class FoodModel extends AbstractTableModel implements FoodDAO {
	private Vector rowData;
	private Vector<String> columnNames;
	private Vector<String> line;
	private int count = 0;

	public FoodModel() {
		columnNames = new Vector<String>();
		rowData = new Vector<>();
		columnNames.add("编号");
		columnNames.add("菜名");
		columnNames.add("价格");
	}


	private Vector addLine(Food food) {
		line = new Vector<String>();
		line.add(""+food.getId());
		line.add(food.getName());
		line.add(""+food.getPrice());
		return line;
	}

	@Override
	public void addFood(Food food) {
		String sql = "insert into food(food_name,food_price) values(:name,:price)";
		JDBCTemplateHelper.addConcludesId(sql, food);
	}

	@Override
	public Food findFoodId(String id) {
		String sql = "select food_id as id,food_name as name, food_price as price from food where food_id = ?";
		Food food = JDBCTemplateHelper.find(sql, Food.class, id);
		if(food!= null)
			rowData.add(addLine(food));
		return food;
	}

	@Override
	public Food findFoodName(String name) {
		String sql = "select food_id as id,food_name as name, food_price as price from food where food_name = ?";
		Food food = JDBCTemplateHelper.find(sql, Food.class, name);
		if(food!= null)
			rowData.add(addLine(food));
		return food;
	}

	@Override
	public void updateFood(Food food) {
		String sql = "update food set food_id = :id ,food_name =:name,food_price = :price where food_id = :id";
		JDBCTemplateHelper.update(sql, food);
	}

	@Override
	public void findAllFoods() {
		String sql ="select food_id as id,food_name as name, food_price as price from food ";
		List<Food> list = JDBCTemplateHelper.findAll(sql, Food.class);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				rowData.add(addLine(list.get(i)));
			}
			this.count = list.size();
		}
	}

	@Override
	public void deleteFood(String id) {
		String sql = "delete from food where food_id = ?";
		JDBCTemplateHelper.delete(sql, id);
	}
	
	@Override
	public int getCount() {
		return this.count;
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


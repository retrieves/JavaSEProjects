package com.newsong.model;

import java.text.SimpleDateFormat;
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

import com.newsong.DAO.CustomerDAO;
import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.DAO.JDBCUtils;
import com.newsong.JavaBean.BookItem;
import com.newsong.JavaBean.Customer;
import com.newsong.JavaBean.Employee;
import com.newsong.JavaBean.Food;

@SuppressWarnings("all")
public class CustomerModel extends AbstractTableModel implements CustomerDAO {
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
	private Vector rowData;
	private Vector<String> columnNames;
	private Vector<String> line;
	private int count = 0;

	public CustomerModel() {
		columnNames = new Vector<String>();
		rowData = new Vector<>();
		columnNames.add("贵宾卡号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("住址");
		columnNames.add("联系方式");
		columnNames.add("注册时间");
	}

	private Vector addLine(Customer customer) {
		line = new Vector<String>();
		line.add("" + customer.getCardId());
		line.add(customer.getName());
		line.add(customer.getSex());
		line.add(customer.getHome());
		line.add(customer.getPhone());
		line.add(sdf.format(customer.getRegTime()));
		return line;
	}

	@Override
	public void addCustomer(Customer customer) {
		String sql = "insert into customer(cus_no,cus_name,cus_sex,cus_home,cus_phone,cus_reg_time) values(:cardId,:name,:sex,:home,:phone,:regTime)";
		JDBCTemplateHelper.addConcludesId(sql, customer);
	}

	@Override
	public Customer findCustomerId(String cardId) {
		String sql = "select cus_id as id ,cus_no as cardId,cus_name as name,cus_sex as sex,cus_home as home,cus_phone as phone ,cus_reg_time as regTime from customer where cus_no = ?";
		Customer customer = null;
		customer = JDBCTemplateHelper.find(sql, Customer.class, cardId);
		if (customer != null)
			rowData.add(addLine(customer));
		return customer;
	}

	@Override
	public Customer findCustomerName(String name) {
		String sql = "select cus_id as id ,cus_no as cardId,cus_name as name,cus_sex as sex,cus_home as home,cus_phone as phone ,cus_reg_time as regTime from customer where cus_name = ?";
		Customer customer = null;
		customer = JDBCTemplateHelper.find(sql, Customer.class, name);
		if (customer != null)
			rowData.add(addLine(customer));
		return customer;
	}

	@Override
	public void updateCustomer(Customer customer) {
		String sql = "update customer set cus_name = :name,cus_sex=:sex,cus_home =:home,cus_phone =:phone ,cus_reg_time =:regTime where cus_no =:cardId";
		JDBCTemplateHelper.update(sql, customer);
	}

	@Override
	public void findAllCustomers() {
		String sql = "select cus_id as id ,cus_no as cardId,cus_name as name,cus_sex as sex,cus_home as home,cus_phone as phone ,cus_reg_time as regTime from customer ";
		List<Customer> list = JDBCTemplateHelper.findAll(sql, Customer.class);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				rowData.add(addLine(list.get(i)));
			}
			this.count = list.size();
		}
	}

	@Override
	public void deleteCustomer(String cardId) {
		String sql = "delete from customer where cus_no = ?";
		JDBCTemplateHelper.delete(sql, cardId);
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

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

import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.DAO.JDBCUtils;
import com.newsong.DAO.UserDAO;
import com.newsong.JavaBean.Food;
import com.newsong.JavaBean.User;

@SuppressWarnings("all")
public class UserModel extends AbstractTableModel implements UserDAO {
	private Vector rowData;
	private Vector<String> columnNames;
	private Vector<String> line;
	private int count = 0;
	
	public UserModel() {
		columnNames = new Vector<String>();
		rowData = new Vector<>();
		columnNames.add("姓名");
		columnNames.add("工号");
		columnNames.add("职务");
		columnNames.add("密码");
	}
	private Vector addLine(User user) {
		line = new Vector<String>();
		line.add(user.getName());
		line.add(user.getWorkId());
		line.add(user.getJob());
		line.add("已注册");
		return line;
	}
	
	@Override
	public void addUser(User user) {
		String sql = "insert into login(login_id,login_pwd) values(:workId,:password)";
		JDBCTemplateHelper.add(sql, user);
	}

	@Override
	public User findUser(String username, String password) {
		String sql = "select login_id as workId,login_pwd as pwd, emp_name as name, emp_job as job  from login join emp_info on emp_work_num = login_id where login_id = ? and login_pwd = ?";
		User user = JDBCTemplateHelper.find(sql, User.class, username,password);
		return user;
	}

	// 返回多条记录
	public void findAllUsers() {
		String sql = "select login_id as workId,login_pwd as pwd, emp_name as name, emp_job as job  from login join emp_info on emp_work_num = login_id ";
		List<User> list = JDBCTemplateHelper.findAll(sql, User.class);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				rowData.add(addLine(list.get(i)));
			}
			this.count = list.size();
		}
	}


	@Override
	public void updateUser(User user) {
		String sql = "update login set login_id = :workId ,login_pwd =:pwd where login_id = :workId";
		JDBCTemplateHelper.update(sql, user);

	}

	@Override
	public User getUser(String workId) {
		String sql = "select login_id as workId,login_pwd as pwd, emp_name as name, emp_job as job  from login join emp_info on emp_work_num = login_id where login_id = ? ";
		User user = JDBCTemplateHelper.find(sql, User.class, workId);
		if(user!= null)
			rowData.add(addLine(user));
		return user;
	}
	
	public int getCount(){
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

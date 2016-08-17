package com.newsong.model;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.newsong.DAO.DeskDAO;
import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.DAO.JDBCUtils;
import com.newsong.JavaBean.Desk;
import com.newsong.JavaBean.Employee;
import com.newsong.JavaBean.Food;

public class DeskDAOImpl implements DeskDAO {
	static NamedParameterJdbcTemplate jdbcHelper = new NamedParameterJdbcTemplate(JDBCUtils.getDataSource());

	@Override
	public String checkDesk(int deskId) {
		String sql  = "select desk_id as id ,desk_status as status from desk_status where desk_id = ?";
		Desk desk = jdbcHelper.getJdbcOperations().queryForObject(sql, BeanPropertyRowMapper.newInstance(Desk.class),""+deskId);
		return desk.getStatus();
	}
	
	
	@Override
	public List<Desk> findAllDesks() {
		String sql  = "select desk_id as id ,desk_status as status from desk_status";
		List<Desk> list = JDBCTemplateHelper.findAll(sql, Desk.class);
		return list;
	}
	
	public void updateDeskStatus(Desk desk) {
		String sql = "update desk_status set desk_status = :status where desk_id  = :id";
		JDBCTemplateHelper.update(sql, desk);
	}
}

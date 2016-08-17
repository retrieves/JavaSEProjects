package com.newsong.DAO;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


public class JDBCTemplateHelper {
	static NamedParameterJdbcTemplate jdbcHelper = new NamedParameterJdbcTemplate(JDBCUtils.getDataSource());
	
	
	public static <T> void update(String sql, T t) {
		jdbcHelper.update(sql, new BeanPropertySqlParameterSource(t));
	}
	
	public static <T> void delete(String sql,T t) {
		jdbcHelper.getJdbcOperations().update(sql, t);
	}
	
	public static <T> List<T> findAll(String sql,Class<T> clazz){
		List<T> list = null;
		try {
			list = jdbcHelper.getJdbcOperations().query(sql, BeanPropertyRowMapper.newInstance(clazz));
		
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	
	public static <T> List<T> findAll(String sql,Class<T> clazz , Object ...constraints ){
		List<T> list = null;
		try {
			list = jdbcHelper.getJdbcOperations().query(sql, BeanPropertyRowMapper.newInstance(clazz),constraints );
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			list = null;
		}
		return list;
	}
	
	public static <T> T find(String sql ,Class<T> clazz, Object... constraints ) {
		T t = null;
		try {
			t = jdbcHelper.getJdbcOperations().queryForObject(sql,BeanPropertyRowMapper.newInstance(clazz),constraints);
		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		return t;
	}
	
	
	
	public static <T extends MyTemplate> void addConcludesId(String sql,T t ) {
		SqlParameterSource ps = new BeanPropertySqlParameterSource(t);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcHelper.update(sql,ps,keyHolder);
		int id = keyHolder.getKey().intValue();
		t.setId(id);
	}
	
	public static <T> void add(String sql,T t) {
		jdbcHelper.update(sql,new BeanPropertySqlParameterSource(t));
	}
	
}

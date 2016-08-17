package com.newsong.model;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.newsong.DAO.BookItemDAO;
import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.DAO.JDBCUtils;
import com.newsong.JavaBean.BookItem;
import com.newsong.JavaBean.Desk;
import com.newsong.JavaBean.Employee;
import com.newsong.JavaBean.Food;

public class BookItemDAOImpl implements BookItemDAO {

	@Override
	public void addBookItem(BookItem book) {
		String sql = "insert into book_info(cus_name,cus_num,cus_phone,desk_id,book_time,meal_time) values (:cusName,:cusNum,:cusPhone,:deskId,:bookTime,:mealTime)";
		JDBCTemplateHelper.addConcludesId(sql, book);
	}

	@Override
	public void deleteBookItem(int bookId) {
		String sql = "delete from book_info where book_id = ? ";
		JDBCTemplateHelper.delete(sql, bookId);
	}

	@Override
	public List<BookItem> findBookStatus(int deskId) {
		String sql = "select book_id as id ,cus_name as cusName , cus_num as cusNum,cus_phone as cusPhone, desk_id as deskId,book_time as bookTime, meal_time as mealTime from book_info where desk_id = ?";
		List<BookItem> list = JDBCTemplateHelper.findAll(sql, BookItem.class,deskId);
		return list;
	}

	@Override
	public BookItem findBookItem(int deskId,Timestamp time) {
		String sql = "select book_id as id ,cus_name as cusName , cus_num as cusNum,cus_phone as cusPhone,desk_id as deskId,book_time as bookTime, meal_time as mealTime from book_info where desk_id = ? and meal_time = ?";
		BookItem book = JDBCTemplateHelper.find(sql, BookItem.class, deskId,time);
		return book;
	}

}

package com.newsong.DAO;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class JDBCUtils {
	private static Properties prop ;
	private static DataSource dataSource ;
	static {
		try {
			prop = new Properties();
			prop.load(JDBCUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
			dataSource = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("≥ı ºªØ ß∞‹");
		}
	}
	public static DataSource getDataSource(){
		return dataSource;
	}
}

package com.newsong.DAO;

import java.util.List;

import com.newsong.JavaBean.SalesItem;

public interface SalesItemDAO {
	List<SalesItem> findMonth(int year,int month);
	List<SalesItem> findYear(int year);
	List<SalesItem> findYears(int year);
	SalesItem findValue(List<SalesItem> list, boolean isMax);
	
}

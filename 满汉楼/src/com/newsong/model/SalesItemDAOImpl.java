package com.newsong.model;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.DAO.SalesItemDAO;
import com.newsong.JavaBean.SalesItem;
@SuppressWarnings("all")
public class SalesItemDAOImpl implements SalesItemDAO {

	
	@Override
	public List<SalesItem> findMonth(int year,int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month-2);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date start = c.getTime();
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, 0);
		Date end = c.getTime();
		String sql = "select day(sales_day) as id ,sales_amount as amount from sales where sales_day >= ? and sales_day < ? order by sales_day asc ";
		List<SalesItem> list = JDBCTemplateHelper.findAll(sql, SalesItem.class, start,end);
		return list;
	}

	@Override
	public List<SalesItem> findYear(int year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date start = c.getTime();
		c.set(Calendar.YEAR, year+1);
		Date end = c.getTime();
		String sql = "select month(sales_day) as id  , sum(sales_amount)  as amount from sales where sales_day >= ? "
				+ "and sales_day < ?  group by  month(sales_day) order by month(sales_day) asc  ";
		List<SalesItem> list = JDBCTemplateHelper.findAll(sql, SalesItem.class, start,end);
		return list;
	}

	@Override
	public List<SalesItem> findYears(int year) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date start = c.getTime();
		String sql = "select year(sales_day) as id , sum(sales_amount) as amount from sales where sales_day >= ?"
				+ "group by  year(sales_day) order by year(sales_day) asc ";
		List<SalesItem> list = JDBCTemplateHelper.findAll(sql, SalesItem.class,start);
		return list;
	}

	@Override
	public SalesItem findValue(List<SalesItem> list,boolean isMax) {
		SalesItem saleItem = null;
		SalesItem valueItem = list.get(0);
		for (int i = 0; i < list.size(); i++) {
			saleItem = list.get(i);
			if(isMax && list.get(i).getAmount() > valueItem.getAmount()) {
				valueItem =  list.get(i);
			}else if(!isMax && list.get(i).getAmount() < valueItem.getAmount()){
				valueItem =  list.get(i);
			}
		}
		return valueItem;
	}
	
}

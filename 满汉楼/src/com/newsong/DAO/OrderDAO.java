package com.newsong.DAO;

import com.newsong.JavaBean.Order;

public interface OrderDAO {
	void addOrder(Order order);
	Order findOrder();
	void discount();
}

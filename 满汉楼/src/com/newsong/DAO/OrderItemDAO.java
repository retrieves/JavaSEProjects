package com.newsong.DAO;

import java.util.List;

import com.newsong.JavaBean.OrderItem;

public interface OrderItemDAO {
	void addOrderItem(OrderItem orderItem);
	void findOrderItems();
}

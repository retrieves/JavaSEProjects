package com.newsong.DAO;

import com.newsong.JavaBean.EnterStockItem;
import com.newsong.JavaBean.LeaveStockItem;

public interface LeaveStockItemDAO {
	void addLeaveStockItem(LeaveStockItem lsi);
	void findAllLeaveStockItems();
}

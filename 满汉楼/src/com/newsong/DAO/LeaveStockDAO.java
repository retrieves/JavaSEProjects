package com.newsong.DAO;

import java.util.List;

import com.newsong.JavaBean.EnterStock;
import com.newsong.JavaBean.LeaveStock;

public interface LeaveStockDAO {
	void findAll();
	void addLeaveStock(LeaveStock leaveStock);
}

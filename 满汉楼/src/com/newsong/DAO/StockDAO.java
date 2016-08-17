package com.newsong.DAO;

import java.util.List;

import com.newsong.JavaBean.Stock;

public interface StockDAO {
	void findAll();
	void addStock(Stock stock);
	void updStock(Stock stock);
	void deleteStock(int id);
	Stock find(int id);
}

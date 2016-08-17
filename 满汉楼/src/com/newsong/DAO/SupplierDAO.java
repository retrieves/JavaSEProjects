package com.newsong.DAO;

import java.util.List;
import java.util.Map;

import com.newsong.JavaBean.Supplier;

public interface SupplierDAO {
	List<Supplier> findAll();
	Map<String, Integer> findAllToMap();
	void addSupplier(Supplier sup);
	void updateSupplier(Supplier sup);
	void deleteSupplier(int id);
	Supplier find(int id);
}

package com.newsong.DAO;

import com.newsong.JavaBean.Customer;
import com.newsong.JavaBean.Employee;

public interface CustomerDAO {
	void addCustomer(Customer customer);
	Customer findCustomerId(String cardId);
	Customer findCustomerName(String name);
	void updateCustomer(Customer customer);
	void findAllCustomers();
	void deleteCustomer(String cardId);
	int getCount();
}

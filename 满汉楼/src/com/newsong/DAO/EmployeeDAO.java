package com.newsong.DAO;

import java.util.Map;

import com.newsong.JavaBean.Employee;

public interface EmployeeDAO {
	void addEmployee(Employee employee);
	Employee findEmployeeId(String workNum);
	void findEmployeeJob(String job);
	Employee findEmployeeName(String name);
	void updateEmployee(Employee employee);
	void findAllEmployees();
	void deleteEmployee(String workNum);
	int getCount();
	Map<String,Integer> findAllEmployeesToMap();
}

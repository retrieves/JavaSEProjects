package com.newsong.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.newsong.DAO.EmployeeDAO;
import com.newsong.DAO.JDBCTemplateHelper;
import com.newsong.JavaBean.Employee;

@SuppressWarnings("all")
public class EmployeeModel extends AbstractTableModel implements EmployeeDAO {
	private Vector rowData;
	private Vector<String> columnNames;
	private Vector<String> line;
	private int count = 0;

	public EmployeeModel() {
		columnNames = new Vector<String>();
		rowData = new Vector<>();
		columnNames.add("姓名");
		columnNames.add("工号");
		columnNames.add("性别");
		columnNames.add("职务");
	}
	
	private Vector addLine(Employee employee) {
		line = new Vector<String>();
		line.add(employee.getName());
		line.add(employee.getWorkNum());
		line.add(employee.getSex());
		line.add(employee.getJob());
		return line;
	}


	@Override
	public void addEmployee(Employee employee) {
		String sql = "insert into emp_info(emp_name,emp_work_num,emp_home,emp_sex,emp_birthdate,"
		+ "emp_reg_time,emp_degree,emp_id,emp_img,emp_job,emp_marrige,emp_phone,emp_cellphone,"
		+ "emp_email,emp_note) values(:name, :workNum, :home,:sex,:birthday,:registerDate,"
		+ ":degree,:id,:img,:job,:marriageStatus, :phoneNum,"
		+":cellPhoneNum, :email,:note)";
		JDBCTemplateHelper.add(sql, employee);
	}

	@Override
	public void findEmployeeJob(String job) {
		String sql = "select emp_name as name,emp_work_num as workNum ,emp_sex as sex,emp_job as job  "
		+ "from emp_info where emp_job = ?";
		List<Employee> list = JDBCTemplateHelper.findAll(sql, Employee.class);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				rowData.add(addLine(list.get(i)));
			}
			this.count = list.size();
		}
	}

	// 返回多条记录
	public void findAllEmployees() {
		this.count = 0;
		String sql =  "select emp_name as name,emp_work_num as workNum ,emp_sex as sex,emp_birthdate as birthday,"
				+ "emp_reg_time as registerDate,emp_degree as degree ,emp_id as id  ,emp_img as img ,emp_job as job ,emp_marrige as marriageStatus,"
				+ "emp_phone as phoneNum ,emp_cellphone as cellPhoneNum ,emp_email as email ,emp_note as note from emp_info ";
		List<Employee> list = JDBCTemplateHelper.findAll(sql, Employee.class);
		if(list != null) {
			for (int i = 0; i < list.size(); i++) {
				rowData.add(addLine(list.get(i)));
			}
			this.count = list.size();
		}
	}

	
	@Override
	public void deleteEmployee(String workNum) {
		String sql = "delete from emp_info where emp_work_num = ?";
		JDBCTemplateHelper.delete(sql, workNum);
	}

	@Override
	public void updateEmployee(Employee employee) {
		String sql = "update emp_info set  emp_name =:name,emp_work_num =:workNum ,emp_home =:home,emp_sex =:sex,emp_birthdate =:birthday,"
		+ "emp_reg_time =:registerDate,emp_degree =:degree ,emp_id =:id  ,emp_img =:img ,emp_job =:job ,emp_marrige =:marriageStatus,"
		+ "emp_phone =:phoneNum ,emp_cellphone =:cellPhoneNum ,emp_email =:email ,emp_note =:note  where emp_work_num = :workNum ";
		JDBCTemplateHelper.update(sql, employee);
	}

	@Override
	public Employee findEmployeeId(String workNum) {
		String sql = "select emp_name as name,emp_work_num as workNum ,emp_home as home,emp_sex as sex,emp_birthdate as birthday,"
				+ "emp_reg_time as registerDate,emp_degree as degree ,emp_id as id  ,emp_img as img ,emp_job as job ,emp_marrige as marriageStatus,"
				+ "emp_phone as phoneNum ,emp_cellphone as cellPhoneNum ,emp_email as email ,emp_note as note "
				+ "from emp_info  where emp_work_num  = ?";
		Employee employee = JDBCTemplateHelper.find(sql, Employee.class,workNum);
		if(employee!= null)
			rowData.add(addLine(employee));
		return employee;
	}

	public Employee findEmployeeName(String name) {
		String sql =  "select emp_name as name,emp_work_num as workNum ,emp_home as home,emp_sex as sex,emp_birthdate as birthday,"
				+ "emp_reg_time as registerDate,emp_degree as degree ,emp_id as id  ,emp_img as img ,emp_job as job ,emp_marrige as marriageStatus,"
				+ "emp_phone as phoneNum ,emp_cellphone as cellPhoneNum ,emp_email as email ,emp_note as note "
				+ "from emp_info  where emp_name = ?";
		Employee employee = JDBCTemplateHelper.find(sql, Employee.class,name);
		if(employee!= null)
			rowData.add(addLine(employee));
		return employee;
	}
	@Override
	public Map<String, Integer> findAllEmployeesToMap() {
		String sql  ="select emp_name as name,emp_no as identity from emp_info";
		List<Employee> list = JDBCTemplateHelper.findAll(sql, Employee.class);
		Map<String,Integer> map = new HashMap<>();
		Employee emp = null;
		for (int i = 0; i < list.size(); i++) {
			emp = list.get(i);
			map.put(emp.getName(), emp.getIdentity());
		}
		return map;
	}

	@Override
	public int getCount() {
		return this.count;
	}

	/**
	 * 得到表头
	 */
	public String getColumnName(int col) {
		return columnNames.get(col);
	}

	/**
	 * 得到列数
	 */
	public int getColumnCount() {
		return columnNames.size();
	}

	/**
	 * 得到行数
	 */
	public int getRowCount() {
		return rowData.size();
	}

	/**
	 * 得到某行某列的数据
	 */
	public Object getValueAt(int row, int col) {
		return ((Vector) rowData.get(row)).get(col);
	}


}

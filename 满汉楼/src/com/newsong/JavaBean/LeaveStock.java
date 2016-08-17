package com.newsong.JavaBean;

import java.util.Date;

public class LeaveStock {
	private String no;
	private Date leaveTime;
	private int empId;
	private String empName;
	
	
	


	public LeaveStock() {
		// TODO Auto-generated constructor stub
	}

	
	
	public LeaveStock(String no, Date leaveTime, String empName) {
		super();
		this.no = no;
		this.leaveTime = leaveTime;
		this.empName = empName;
	}

	public LeaveStock(Date date, int empId) {
		this.leaveTime = date;
		this.empId = empId;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}



	public void setEmpName(String empName) {
		this.empName = empName;
	}

	
}

package com.newsong.JavaBean;

import java.util.Date;

public class EnterStock {
	private String no;
	private Date enterTime;
	private int empId;
	private String  empName;
	
	



	public EnterStock() {
		// TODO Auto-generated constructor stub
	}

	
	
	public EnterStock(String no, Date enterTime, String empName) {
		super();
		this.no = no;
		this.enterTime = enterTime;
		this.empName = empName;
	}



	public EnterStock(Date date, int empId) {
		this.enterTime = date;
		this.empId = empId;
	}


	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Date getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(Date enterTime) {
		this.enterTime = enterTime;
	}

	public int  getEmpId() {
		return empId;
	}
	public String getEmpName() {
		return empName;
	}



	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}
	
	
	
}

package com.newsong.JavaBean;

import java.util.Date;
@SuppressWarnings("all")
public class Employee {
	private int identity;
	private String name;
	private String workNum;
	private String home;
	private String sex;
	

	private Date birthday;
	private Date registerDate;
	private String degree;
	private String id;//…Ì∑›÷§∫≈
	private String img;
	private String job;
	private String marriageStatus;
	private String phoneNum;
	private String cellPhoneNum;
	private String email;
	private String note;
	
	public Employee() {
	}
	
	public Employee( String name, String workNum,String sex, String job) {
		super();
		this.name = name;
		this.workNum = workNum;
		this.sex = sex;
		this.job = job;
	}
	
	
	public Employee(String name, String workNum, String home, String sex, Date birthday, Date registerDate,
			String degree, String id, String img, String job, String marriageStatus, String phoneNum,
			String cellPhoneNum, String email, String note) {
		super();
		this.name = name;
		this.workNum = workNum;
		this.home = home;
		this.sex = sex;
		this.birthday = birthday;
		this.registerDate = registerDate;
		this.degree = degree;
		this.id = id;
		this.img = img;
		this.job = job;
		this.marriageStatus = marriageStatus;
		this.phoneNum = phoneNum;
		this.cellPhoneNum = cellPhoneNum;
		this.email = email;
		this.note = note;
	}

	public String getWorkNum() {
		return workNum;
	}

	public void setWorkNum(String workNum) {
		this.workNum = workNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getDegree() {
		return degree;
	}
	
	public int getIdentity() {
		return identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getMarriageStatus() {
		return marriageStatus;
	}

	public void setMarriageStatus(String marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getCellPhoneNum() {
		return cellPhoneNum;
	}

	public void setCellPhoneNum(String cellPhoneNum) {
		this.cellPhoneNum = cellPhoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}

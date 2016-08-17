package com.newsong.JavaBean;

import java.util.Date;

import com.newsong.DAO.MyTemplate;

public class Customer implements MyTemplate{
	int id;
	String cardId;
	String name;
	String sex;
	String home;
	String phone;
	Date regTime;
	
	public Customer() {
	}

	public Customer(String cardId, String name, String sex, String home, String phone, Date regTime) {
		super();
		this.cardId = cardId;
		this.name = name;
		this.sex = sex;
		this.home = home;
		this.phone = phone;
		this.regTime = regTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	
	
}	

package com.newsong.JavaBean;

import java.sql.Timestamp;
import java.util.Date;

import com.newsong.DAO.MyTemplate;

public class BookItem implements MyTemplate{
	int id;
	String cusName;
	int cusNum;
	String cusPhone;
	
	int deskId;
	Timestamp bookTime;
	Timestamp mealTime;
	public BookItem() {
	}
	public BookItem(String cusName, int cusNum,String cusPhone, int deskId, Timestamp bookTime, Timestamp mealTime) {
		super();
		this.cusName = cusName;
		this.cusNum = cusNum;
		this.cusPhone = cusPhone;
		this.deskId = deskId;
		this.bookTime = bookTime;
		this.mealTime = mealTime;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCusName() {
		return cusName;
	}
	public String getCusPhone() {
		return cusPhone;
	}
	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public int getCusNum() {
		return cusNum;
	}
	public void setCusNum(int cusNum) {
		this.cusNum = cusNum;
	}
	public int getDeskId() {
		return deskId;
	}
	public void setDeskId(int deskId) {
		this.deskId = deskId;
	}
	public Timestamp getBookTime() {
		return bookTime;
	}
	public void setBookTime(Timestamp bookTime) {
		this.bookTime = bookTime;
	}
	public Timestamp getMealTime() {
		return mealTime;
	}
	public void setMealTime(Timestamp mealTime) {
		this.mealTime = mealTime;
	}
	
	
}

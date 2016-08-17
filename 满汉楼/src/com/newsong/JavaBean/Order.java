package com.newsong.JavaBean;

import java.sql.Timestamp;

import com.newsong.DAO.MyTemplate;

public class Order implements MyTemplate{
	int Id;
	int deskId;
	Timestamp orderTime ;
	double orderAmount;
	
	

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(int deskId, Timestamp orderTime,double orderAmount) {
		this.deskId = deskId;
		this.orderTime = orderTime;
		this.orderAmount = orderAmount;
	}

	public int getId() {
		return Id;
	}

	public void setId(int orderId) {
		this.Id = orderId;
	}

	public int getDeskId() {
		return deskId;
	}

	public void setDeskId(int deskId) {
		this.deskId = deskId;
	}

	public Timestamp getOrderTime() {
		return orderTime;
	}
	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	
	
	
}

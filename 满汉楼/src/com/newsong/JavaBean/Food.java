package com.newsong.JavaBean;

import com.newsong.DAO.MyTemplate;

@SuppressWarnings("all")

public class Food implements MyTemplate{
	private int id;
	private String name;
	private double price;
	public Food() {
	}
	
	public Food(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public Food(int id,String name, double price) {
		this(name,price);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}

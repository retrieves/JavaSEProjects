package com.newsong.JavaBean;

import com.newsong.DAO.MyTemplate;

@SuppressWarnings("all")

public class Stock implements MyTemplate{
	private int id;
	private String name;
	private String unit;
	private int supId;
	private String supName;
	private int quantity;
	
	public Stock() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Stock(String name, String unit,int supId) {
		this.name = name;
		this.unit = unit;
		this.supId = supId;
	}
	
	public Stock(int id,String name, String unit,int supId) {
		this(name,unit,supId);
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
	public int getSupId() {
		return supId;
	}


	public void setSupId(int supId) {
		this.supId = supId;
	}
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSupName() {
		return supName;
	}


	public void setSupName(String supName) {
		this.supName = supName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}

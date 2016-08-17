package com.newsong.JavaBean;

public class OrderItem {
	int orderId;
	int foodId;
	int foodServings;
	String foodName;
	double foodPrice;
	
	public OrderItem() {
		// TODO Auto-generated constructor stub
	}

	public OrderItem(int orderId, int foodId, int foodServings) {
		super();
		this.orderId = orderId;
		this.foodId = foodId;
		this.foodServings = foodServings;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getFoodServings() {
		return foodServings;
	}

	public void setFoodServings(int foodServings) {
		this.foodServings = foodServings;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}
	
	
}

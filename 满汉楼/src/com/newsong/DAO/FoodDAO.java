package com.newsong.DAO;

import com.newsong.JavaBean.Food;

public interface FoodDAO {
	void addFood(Food Food);
	Food findFoodId(String id);
	Food findFoodName(String name);
	void updateFood(Food Food);
	void findAllFoods();
	void deleteFood(String id);
	int getCount();
}

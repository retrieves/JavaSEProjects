package com.newsong.DAO;

import com.newsong.JavaBean.User;

public interface UserDAO {
	void addUser(User user);
	User findUser(String username,String password);
	void updateUser(User user);
	void findAllUsers();
	User getUser(String username);
	int getCount();
}

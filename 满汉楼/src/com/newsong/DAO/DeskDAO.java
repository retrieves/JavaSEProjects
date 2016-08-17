package com.newsong.DAO;

import java.util.List;

import com.newsong.JavaBean.Desk;

public interface DeskDAO {
	List<Desk> findAllDesks();
	String checkDesk(int deskId);
}

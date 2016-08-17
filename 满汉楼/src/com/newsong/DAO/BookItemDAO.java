package com.newsong.DAO;

import java.sql.Timestamp;
import java.util.List;

import com.newsong.JavaBean.BookItem;

public interface BookItemDAO {
	void addBookItem(BookItem book);
	void deleteBookItem(int bookId);
	List<BookItem> findBookStatus(int deskId);
	BookItem findBookItem(int deskId, Timestamp time);
}

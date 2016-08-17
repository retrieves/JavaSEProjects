package com.newsong.JavaBean;

import com.newsong.DAO.MyTemplate;

public class Supplier implements MyTemplate{
	private int id;
	private String name;
	private String addr;
	private String phone;
	private String email;
	private String note;
	
	public Supplier() {
		// TODO Auto-generated constructor stub
	}

	public Supplier(String name, String addr, String phone, String email, String note) {
		super();
		this.name = name;
		this.addr = addr;
		this.phone = phone;
		this.email = email;
		this.note = note;
	}
	public Supplier(int id,String name, String addr, String phone, String email, String note) {
		this(name,addr,phone,email,note);
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	
}

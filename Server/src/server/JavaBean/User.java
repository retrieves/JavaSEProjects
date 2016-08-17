package server.JavaBean;

import java.util.Date;

@SuppressWarnings("all")
public class User {
	private int username;
	private String password;
	private String sex;
	private String email;
	private String phone;
	private Date regDate;
	private Date birthday;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int username,String password) {
		this.username = username;
		this.password = password;
	}
	
	public int getUserName() {
		return username;
	}

	public void setUserName(int id) {
		this.username = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
	
}

package com.newsong.JavaBean;
@SuppressWarnings("all")

public class User {
	
	private String workId;
	private String pwd;
	private String name;
	private String job;
	
	

	public User() {
	}
	
	public User(String workId, String pwd) {
		this.workId = workId;
		this.pwd = pwd;
	}
	
	public User(String workId, String pwd,String name,String job) {
		this(workId,pwd);
		this.name = name;
		this.job = job;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}

	public String getWorkId(){
		return this.workId;
	}
	
	public void setWorkId(String workId){
		this.workId = workId;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}

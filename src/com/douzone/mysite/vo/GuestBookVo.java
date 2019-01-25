package com.douzone.mysite.vo;

public class GuestBookVo 
{
	private long no;
	private String name;
	private String password;
	private String message;
	private String regDate;

	public GuestBookVo(String name, String password, String message) {

		this.name = name;
		this.password = password;
		this.message = message;
	}

	public GuestBookVo() {
	}
	
	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}
	
	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}

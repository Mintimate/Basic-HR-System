package com.Mintimate.model;

public class User {
	public int id;
	public String name;
	public String pwd;
	public int limit;
	public String pin;


	public User(int id, String name, String pwd, int limit, String pin) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.limit = limit;
		this.pin = pin;
	}


	public User() {
	}

	public User(String name, String pwd) {
		this.name = name;
		this.pwd = pwd;
	}
	
	public User(String name, String pwd, int limit) {
		this.name = name;
		this.pwd = pwd;
		this.limit = limit;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pwd=" + pwd + ", limit=" + limit + ", pin=" + pin + "]";
	}


}

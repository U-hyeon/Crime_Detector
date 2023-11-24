package com.example.demo.model;

public class UserProfile_test {
	private String user_number;
	private String user_name;
	private int user_age;
	private char user_gender;
	private String user_spec;
	
	public UserProfile_test(String user_number, String user_name, int user_age, char user_gender, String user_spec) {
		super();
		this.user_number = user_number;
		this.user_name = user_name;
		this.user_age = user_age;
		this.user_gender = user_gender;
		this.user_spec = user_spec;
	}
	public String getUser_number() {
		return user_number;
	}
	public void setUser_number(String user_number) {
		this.user_number = user_number;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getUser_age() {
		return user_age;
	}
	public void setUser_age(int user_age) {
		this.user_age = user_age;
	}
	public char getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(char user_gender) {
		this.user_gender = user_gender;
	}
	public String getUser_spec() {
		return user_spec;
	}
	public void setUser_spec(String user_spec) {
		this.user_spec = user_spec;
	}
}

package com.example.demo.model;

public class ReportSpec {
	// report_code, user_name, user_gender, user_age, report_time, user_number, user_spec, crime, manager_name, execute_time
	private String report_code;
	private String user_name;
	private char user_gender;
	private int age;
	private String report_time;
	private String user_number;
	private String user_spec;
	private String crime;
	private String manager_name;
	private String execute_time;
	
	public ReportSpec(String report_code, String user_name, char user_gender, int age, String report_time,
			String user_number, String user_spec, String crime, String manager_name, String execute_time) {
		super();
		this.report_code = report_code;
		this.user_name = user_name;
		this.user_gender = user_gender;
		this.age = age;
		this.report_time = report_time;
		this.user_number = user_number;
		this.user_spec = user_spec;
		this.crime = crime;
		this.manager_name = manager_name;
		this.execute_time = execute_time;
	}
	public String getReport_code() {
		return report_code;
	}
	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public char getUser_gender() {
		return user_gender;
	}
	public void setUser_gender(char user_gender) {
		this.user_gender = user_gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getReport_time() {
		return report_time;
	}
	public void setReport_time(String report_time) {
		this.report_time = report_time;
	}
	public String getUser_number() {
		return user_number;
	}
	public void setUser_number(String user_number) {
		this.user_number = user_number;
	}
	public String getUser_spec() {
		return user_spec;
	}
	public void setUser_spec(String user_spec) {
		this.user_spec = user_spec;
	}
	public String getCrime() {
		return crime;
	}
	public void setCrime(String crime) {
		this.crime = crime;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getExecute_time() {
		return execute_time;
	}
	public void setExecute_time(String execute_time) {
		this.execute_time = execute_time;
	}
}

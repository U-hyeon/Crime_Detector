package com.example.demo.model;

//google map marker에 사용될 정보
public class ReportMonitorObjCoord {
	private String report_code;
	private String report_time;
	private String latitude;
	private String longitude;
	private char gender;
	private int age;
	private String name;
	private String crime;
	private char reporter_type;
	private String  memo;
	
	public ReportMonitorObjCoord(String report_code, String report_time, String latitude, String longitude, char gender,
			int age, String name, String crime, char reporter_type, String memo) {
		super();
		this.report_code = report_code;
		this.report_time = report_time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.gender = gender;
		this.age = age;
		this.name = name;
		this.crime = crime;
		this.reporter_type = reporter_type;
		this.memo = memo;
	}
	public String getReport_code() {
		return report_code;
	}
	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}
	public String getReport_time() {
		return report_time;
	}
	public void setReport_time(String report_time) {
		this.report_time = report_time;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCrime() {
		return crime;
	}
	public void setCrime(String crime) {
		this.crime = crime;
	}
	public char getReporter_type() {
		return reporter_type;
	}
	public void setReporter_type(char reporter_type) {
		this.reporter_type = reporter_type;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}

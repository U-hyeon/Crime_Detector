package com.example.demo.model;

//report에 대한 reverse-geocoding 텍스트주소 포함
public class ReportLocationString {
	private String report_code;
	private String report_time;
	private String crime;
	private String location;
	public ReportLocationString(String report_code, String report_time, String crime, String location) {
		super();
		this.report_code = report_code;
		this.report_time = report_time;
		this.crime = crime;
		this.location = location;
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
	public String getCrime() {
		return crime;
	}
	public void setCrime(String crime) {
		this.crime = crime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
}

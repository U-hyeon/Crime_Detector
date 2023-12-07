package com.example.demo.model;

// report에 대한 위도/경도 정보를 포함
public class ReportLocation {
	private String report_code;
	private String report_time;
	private String crime;
	private String latitude;
	private String longitude;
	public ReportLocation(String report_code, String report_time, String crime, String latitude, String longitude) {
		super();
		this.report_code = report_code;
		this.report_time = report_time;
		this.crime = crime;
		this.latitude = latitude;
		this.longitude = longitude;
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
}

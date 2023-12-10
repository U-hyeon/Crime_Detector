package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mapper.UserProfileMapper;
import com.example.demo.model.ReportLocation;
import com.example.demo.model.ReportLocationString;
import com.example.demo.model.ReportMonitorObj;
import com.example.demo.model.ReportMonitorObjCoord;
import com.example.demo.model.ReportSpec;
import com.example.demo.model.UserProfile;
import com.example.demo.services.geocoding;

@RestController
public class UserProfileController_test {
	
	private UserProfileMapper mapper;
	
	public UserProfileController_test(UserProfileMapper mapper) {
		this.mapper = mapper;
	}
	
	@GetMapping("/user/all")
	public List<UserProfile> getUserProfileList() {
		return mapper.getUserProfileList();
	}
	
	@GetMapping("/user/{number}")
	public UserProfile getUserProfile_test(@PathVariable("number") String number) {
		return mapper.getUserProfile(number);
	}

	@PostMapping("user/{number}")
	public void postUserProfile(@PathVariable("number") String number, @RequestParam("name") String name, @RequestParam("age") int age, @RequestParam("gender") char gender, @RequestParam("spec") String spec) {
		mapper.updateUserProfile(number, name, age, gender, spec);
	}
	
	@PutMapping("user/{number}")
	public void putUserProfile(@PathVariable("number") String number, @RequestParam("name") String name, @RequestParam("age") int age, @RequestParam("gender") char gender, @RequestParam("spec") String spec) {
		mapper.insertUserProfile(number, name, age, gender, spec);
	}
	
	@DeleteMapping("/user/{number}")
	public void deleteUserProfile(@PathVariable("number") String number) {
		mapper.deleteUserProfile(number);
	}
	

	//report_code, user_number, location, report_time, crime, manager_name, execute_time, reporter_type, memo
	@PutMapping("/report/{report_code}")
	public void putReport(@PathVariable("report_code") String report_code, @RequestParam("user_number") String user_number,
						  @RequestParam("location") String location, @RequestParam("report_time") String report_time,
						  @RequestParam("crime") String crime, @RequestParam("manager_name") String manager_name,
						  @RequestParam("execute_time") String execute_time, @RequestParam("reporter_type") char reporter_type,
						  @RequestParam("memo") String memo){
		mapper.insertReport(report_code, user_number, location, report_time, crime, manager_name, execute_time, reporter_type, memo);
	}
	
	
	@GetMapping("/report_location/all")
	public List<ReportLocation> getReportLocationList() {
		return mapper.getAllReportLocation();
	}
	
	@GetMapping("/report_location/warm")
	public List<ReportLocation> getReportLocationListNotDone() {
		return mapper.getReportLocationListNotDone();
	}
	
	@GetMapping("/report_location/all_to_string")
	public List<ReportLocationString> getAllStringLocation() {
		List<ReportLocationString> locations = new ArrayList<>();
		List<ReportLocation> buffer = getReportLocationList();

		for (ReportLocation location : buffer) {
		    ReportLocationString newLocation = new ReportLocationString(
		    		location.getReport_code(),
		    		location.getReport_time(),
		    		location.getCrime(),
		    		geocoding.reverseGeocode(location.getLatitude(), location.getLongitude()) 
		    		);
		    locations.add(newLocation);
		}
		return locations;
	}
	
	@GetMapping("/monitor") // 좌상단 처리되지 않은 신고리스트
	public List<ReportMonitorObj> getAllReportOnGoing() {
		List<ReportMonitorObj> reports = new ArrayList<>();
		List<ReportMonitorObjCoord> buffer = mapper.getAllReportOnGoing();

		for (ReportMonitorObjCoord report : buffer) {
			//report_time, location, gender, age, name, crime, reporter_type
			ReportMonitorObj newReport = new ReportMonitorObj(
					report.getReport_code(),
					report.getReport_time(), 
					geocoding.reverseGeocode(report.getLatitude(), report.getLongitude()),
					report.getGender(),
					report.getAge(),
					report.getName(),
					report.getCrime(),
					report.getReporter_type()
					);
		    reports.add(newReport);
		}
		return reports;
	}
	
	@GetMapping("/report-spec/{report_code}") // 좌하단 신고 상세정보
	public ReportSpec getReportSpec(@PathVariable("report_code") String report_code) {
		return mapper.getReportSpec(report_code);
	}
	
	// user_number, crime, manager_name, execute_time <= report_code
	@PostMapping("update-report/{report_code}")
	public void postReportSpec(
			@PathVariable("report_code") String report_code
			, @RequestParam("user_number") String user_number
			, @RequestParam("memo") String memo
			, @RequestParam("crime") String crime
			, @RequestParam("manager_name") String manager_name
			, @RequestParam("execute_time") String execute_time
			) {
		mapper.updateReportSpec(report_code, user_number, memo, crime, manager_name, execute_time);
	}
	
	@GetMapping("/count-events/{latitude}/{longitude}/{areaDistance}/{periodDay}/{presentTime}")
	public int getHistoryOfArea(
						@RequestParam("latitude") String latitude,
						@RequestParam("longitude") String longitude,
						@RequestParam("areaDistance") String areaDistance,
						@RequestParam("periodDay") String periodDay,
						@RequestParam("presentTime") String presentTime
				) {
		return Integer.valueOf(mapper.getCountReportsOnArea(latitude, longitude, areaDistance, periodDay, presentTime));
	}
}

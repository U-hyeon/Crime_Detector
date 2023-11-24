package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.ReportLocation;
import com.example.demo.model.ReportMonitorObjCoord;
import com.example.demo.model.ReportSpec;
import com.example.demo.model.UserProfile_test;

@Mapper
public interface UserProfileMapper {
	@Select("SELECT * FROM CRIME_DETECTOR.USERS WHERE user_number=#{number}")
	UserProfile_test getUserProfile(@Param("number") String number);

	@Select("SELECT * FROM CRIME_DETECTOR.USERS")
	List<UserProfile_test> getUserProfileList();
	
	@Insert("INSERT INTO CRIME_DETECTOR.USERS VALUES(#{number}, #{name}, #{age}, #{gender}, #{spec})")
	int insertUserProfile(@Param("number") String number, @Param("name") String name, @Param("age") int age, @Param("gender") char gender, @Param("spec") String spec);
	
	@Update("UPDATE CRIME_DETECTOR.USERS SET user_name=#{name}, user_age=#{age}, user_gender=#{gender}, user_spec=#{spec} WHERE user_number=#{number}")
	int updateUserProfile(@Param("number") String number, @Param("name") String name, @Param("age") int age, @Param("gender") char gender, @Param("spec") String spec);

	@Delete("DELETE FROM CRIME_DETECTOR.USERS WHERE user_number=#{number}")
	int deleteUserProfile(@Param("number") String number);
	
	@Select("SELECT USERS.user_name as name, substring_index(REPORTS.location, ', ', 1) as latitude, substring_index(REPORTS.location, ', ', -1) as longitude FROM REPORTS INNER JOIN USERS ON REPORTS.user_number = USERS.user_number")
	List<ReportLocation> getAllReportLocation();
	
	// report_code, report_time, crime, location
	@Select("SELECT report_code, report_time, crime, substring_index(location, ', ', 1) as latitude, substring_index(location, ', ', -1) as longitude FROM REPORTS WHERE REPORTS.execute_time = ''")
	List<ReportLocation> getReportLocationListNotDone();
	
	// report_code, report_time, location, gender, age, name, crime, reporter_type
	@Select("SELECT report_code, report_time, substring_index(location, ', ', 1) as latitude, substring_index(location, ', ', -1) as longitude, user_gender, user_age, user_name, crime, reporter_type FROM REPORTS INNER JOIN USERS ON REPORTS.user_number = USERS.user_number WHERE REPORTS.execute_time = '' ORDER BY report_time DESC")
	List<ReportMonitorObjCoord> getAllReportOnGoing();
	
	// report_code, name_name, user_gender, user_age, report_time, user_number, user_spec, crime, manager_name, execute_time
	@Select("SELECT report_code, user_name, user_gender, user_age, report_time, REPORTS.user_number, user_spec, crime, manager_name, execute_time FROM REPORTS INNER JOIN USERS ON REPORTS.user_number = USERS.user_number WHERE report_code=#{report_code}")
	ReportSpec getReportSpec(@Param("report_code") String report_code);
	
	// user_number, crime, manager_name, execute_time <= report_code
	@Update("UPDATE REPORTS SET user_number=#{user_number}, crime=#{crime}, manager_name=#{manager_name}, execute_time=#{execute_time} WHERE report_code=#{report_code}")
	int updateReportSpec(@Param("report_code") String report_code
			, @Param("user_number") String user_number
			, @Param("crime") String crime
			, @Param("manager_name") String manager_name
			, @Param("execute_time") String execute_time
			);
}

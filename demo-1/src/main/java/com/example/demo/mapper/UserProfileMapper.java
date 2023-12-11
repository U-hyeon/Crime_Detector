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
import com.example.demo.model.UserProfile;

@Mapper
public interface UserProfileMapper {
	@Select("SELECT * FROM CRIME_DETECTOR.USERS WHERE user_number=#{number}")
	UserProfile getUserProfile(@Param("number") String number);

	@Select("SELECT * FROM CRIME_DETECTOR.USERS")
	List<UserProfile> getUserProfileList();
	
	@Insert("INSERT INTO CRIME_DETECTOR.USERS VALUES(#{number}, #{name}, #{age}, #{gender}, #{spec})")
	int insertUserProfile(@Param("number") String number, @Param("name") String name, @Param("age") int age, @Param("gender") char gender, @Param("spec") String spec);

	//report_code, user_number, location, report_time, crime, manager_name, execute_time, reporter_type, memo
	@Insert("INSERT INTO CRIME_DETECTOR.REPORTS " +
			"VALUES(" +
			" #{report_code}, #{user_number}, #{location}" +
			", #{report_time}, #{crime}, ''" +
			", '', 'P', (SELECT user_spec from USERS WHERE user_number=#{user_number}) " +
			")"
	)
	int insertReport(
			@Param("report_code") String report_code, @Param("user_number") String user_number, @Param("location") String location
			, @Param("report_time") String report_time, @Param("crime") String crime
			);
	
	@Update("UPDATE CRIME_DETECTOR.REPORTS SET crime=#{crime} WHERE report_code=#{report_code}")
	int updateReportCrime(@Param("report_code") String report_code, @Param("crime") String crime);
	
	@Update("UPDATE CRIME_DETECTOR.REPORTS SET reporter_type=#{reporter_type} WHERE report_code=#{report_code}")
	int updateReporterType(@Param("report_code") String report_code, @Param("reporter_type") String reporter_type);

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
	@Select("SELECT report_code, report_time, substring_index(location, ', ', 1) as latitude, substring_index(location, ', ', -1) as longitude, user_gender, user_age, user_name, crime, reporter_type, memo FROM REPORTS INNER JOIN USERS ON REPORTS.user_number = USERS.user_number WHERE REPORTS.execute_time = '' OR REPORTS.execute_time = 'null' ORDER BY report_time DESC")
	List<ReportMonitorObjCoord> getAllReportOnGoing();
	
	// report_code, name_name, user_gender, user_age, report_time, user_number, user_spec, crime, manager_name, execute_time
	@Select("SELECT report_code, user_name, user_gender, user_age, report_time, REPORTS.user_number, memo, crime, manager_name, execute_time FROM REPORTS INNER JOIN USERS ON REPORTS.user_number = USERS.user_number WHERE report_code=#{report_code}")
	ReportSpec getReportSpec(@Param("report_code") String report_code);
	
	@Select("SELECT COUNT(*) FROM CRIME_DETECTOR.REPORTS "
			+ "WHERE ABS( CONVERT(float, substring_index(REPORTS.location, ', ', 1)) - CONVERT(float, #{latitude}) ) <= CONVERT(float, #{areaDistance}) "
			+ "AND ABS( CONVERT(float, substring_index(REPORTS.location, ', ', -1)) - CONVERT(float, #{longitude}) ) <= CONVERT(float, #{areaDistance}) "
			+ "AND TIMESTAMPDIFF(DAY, STR_TO_DATE(REPORTS.report_time, '%Y%m%d_%H%i%s'), STR_TO_DATE(#{presentTime}, '%Y%m%d_%H%i%s')) <= #{periodDay} ")
	int getCountReportsOnArea(@Param("latitude") String latitude
			, @Param("longitude") String longitude
			, @Param("areaDistance") String areaDistance
			, @Param("periodDay") String periodDay
			, @Param("presentTime") String presentTime
			);
	
	@Select("SELECT COUNT(*) FROM CRIME_DETECTOR.REPORTS "
			+ "WHERE ABS( CONVERT(float, substring_index(REPORTS.location, ', ', 1)) - CONVERT(float, #{latitude}) ) <= CONVERT(float, #{areaDistance}) "
			+ "AND ABS( CONVERT(float, substring_index(REPORTS.location, ', ', -1)) - CONVERT(float, #{longitude}) ) <= CONVERT(float, #{areaDistance}) "
			+ "AND REPORTS.execute_time = '' OR REPORTS.execute_time = 'null'")
	int getCountReportsOnAreaOnGoing(@Param("latitude") String latitude
			, @Param("longitude") String longitude
			, @Param("areaDistance") String areaDistance
			);
	
	// user_number, crime, manager_name, execute_time, memo <= report_code
	@Update("UPDATE REPORTS SET user_number=#{user_number}, crime=#{crime}, manager_name=#{manager_name}, execute_time=#{execute_time}, memo=#{memo} WHERE report_code=#{report_code}")
	int updateReportSpec(@Param("report_code") String report_code
			, @Param("user_number") String user_number
			, @Param("memo") String memo
			, @Param("crime") String crime
			, @Param("manager_name") String manager_name
			, @Param("execute_time") String execute_time
			);
}

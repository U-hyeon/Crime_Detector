package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

@Service
public class geocoding {
	private static String apiKey = "AIzaSyD3emR18UvttRCebTeZ6xkC9G9Jd4HrUoM";

    public static String reverseGeocode(String lat_s, String long_s) {
    	double latitude, longitude;
    	latitude = Double.parseDouble(lat_s);
    	longitude = Double.parseDouble(long_s);
    	
        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
        LatLng location = new LatLng(latitude, longitude); // LatLng 객체 생성

        try {
            GeocodingResult[] results = GeocodingApi
            		.reverseGeocode(context, location)
            		.language("ko")
            		.await();
            
            if (results.length > 0) {
                return results[0].formattedAddress;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "주소를 찾을 수 없습니다.";
    }
}
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import com.example.demo.services.CorsConfig;

@SpringBootApplication
@Import(CorsConfig.class)
public class Demo1Application {

//	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Demo1Application.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}

}

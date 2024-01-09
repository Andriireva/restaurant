package com.example.javapractice.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


// @SpringBootApplication - annotaion is entry to point to Spring Boot Application
@SpringBootApplication
@EnableJpaAuditing // it is required to enable auditing so spring annotation like @CreatedBy will work
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

}

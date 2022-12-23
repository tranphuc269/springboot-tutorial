package com.bookstore.authservice;

import com.bookstore.common.application.response.dto.BaseResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class AuthServiceApplication {

	@GetMapping("/")
	public BaseResponse<String> testConnection(){
		return  BaseResponse.ofSucceeded("Auth Service working");
	}

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}

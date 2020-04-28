package com.project.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AuthMain {

	public static void main(String[] args) {
		SpringApplication.run(AuthMain.class, args);
	}

}

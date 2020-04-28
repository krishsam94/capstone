package com.project.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EventInfoApp {
    public static void main(String[] args) {
        SpringApplication.run(EventInfoApp.class, args);
    }
}

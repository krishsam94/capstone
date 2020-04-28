package com.project.report.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.report.model.UserProfile;
import com.project.report.service.EmailService;
import com.project.report.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/get")
    public Flux<UserProfile> getAll() {
        return userService.getAll();
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/download")
    public Mono<ResponseEntity> generate() throws IOException {
        return userService.getList().map(userList -> {
            ByteArrayInputStream in = null;
            try {
                in = userService.convertToExcel(userList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=UserEvents.xlsx");

            return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                    .headers(headers).body(new InputStreamResource(in));
        }).cast(ResponseEntity.class);

    }

    @GetMapping("/ping")
    public String ping() {
        return "Pinged at " + System.currentTimeMillis();
    }

    @SuppressWarnings("rawtypes")
    @GetMapping("/sendmail")
    public Mono<ResponseEntity> sendMail(@RequestParam String email) {
        return userService.getList().map(userList -> {
            try {
                userService.convertToExcel(userList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            emailService.sendMailWithAttachment(email, "Capestone", "Kindly find the report");
            return ResponseEntity.status(HttpStatus.OK).body("Mail sent successfully");
        }).cast(ResponseEntity.class);
    }

}

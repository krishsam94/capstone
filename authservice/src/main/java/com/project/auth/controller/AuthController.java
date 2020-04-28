package com.project.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.auth.model.User;
import com.project.auth.service.AuthService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/get")
    public Flux<User> getitems() {
        return authService.getAllItems();
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/login")
    public Mono<ResponseEntity> login(@RequestBody User user) {
        Mono<ResponseEntity> mono = authService.findByIdAndPassword(user.getUserId(), user.getPassword());

        return mono;
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/register")
    public Mono<ResponseEntity> register(@RequestBody User user) {
        Mono<ResponseEntity> mono = authService.createUser(user);

        return mono;
    }

    @GetMapping("/ping")
    public String ping() {
        return "Pinged at " + System.currentTimeMillis();
    }

}

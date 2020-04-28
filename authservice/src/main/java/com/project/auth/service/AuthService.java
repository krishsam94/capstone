package com.project.auth.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.auth.exception.UserAlreadyExistsException;
import com.project.auth.exception.UserNotFoundException;
import com.project.auth.model.User;
import com.project.auth.repo.AuthRepo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AuthService {
    @Autowired
    AuthRepo authRepo;
    @Value("${spring.jwt.key}")
    private String jwtKey;

    public Flux<User> getAllItems() {
        return authRepo.findAll();
    }

    @SuppressWarnings("rawtypes")
    public Mono<ResponseEntity> findByIdAndPassword(String adminId, String password) {
        Mono<ResponseEntity> mono = authRepo.findByUserIdAndPassword(adminId, password)
                .map(admin -> ResponseEntity.status(HttpStatus.OK).body(this.generateToken(admin)))
                .cast(ResponseEntity.class).switchIfEmpty(Mono.error(new UserNotFoundException("Not Found")));

        return mono;
    }

    @SuppressWarnings("rawtypes")
    public Mono<ResponseEntity> createUser(User admin) {
        Mono<ResponseEntity> mono = authRepo.insert(admin).log()
                .map(user -> ResponseEntity.status(HttpStatus.OK).body("User created Successfully"))
                .cast(ResponseEntity.class).switchIfEmpty(Mono.error(new UserAlreadyExistsException("Already Exists")));

        return mono;
    }

    public Map<String, String> generateToken(User admin) {
        String jwtToken = Jwts.builder().setSubject(admin.getUserId()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS256, jwtKey).compact();
        Map<String, String> map = new HashMap<>();

        map.put("adminId", admin.getUserId());
        map.put("token", jwtToken);

        return map;
    }

}

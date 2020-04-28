package com.project.auth.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.project.auth.model.User;

import reactor.core.publisher.Mono;

public interface AuthRepo extends ReactiveMongoRepository<User, String> {
    public Mono<User> findByUserIdAndPassword(String adminId, String password);
}

package com.project.event.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.project.event.model.UserProfile;

public interface UserRepo extends ReactiveMongoRepository<UserProfile, String> {

}

package com.project.report.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.project.report.model.UserProfile;

public interface UserRepo extends ReactiveMongoRepository<UserProfile, String> {

}

package com.project.event.repo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.project.event.model.EventInfo;

public interface EventInfoRepo extends ReactiveMongoRepository<EventInfo, String> {

}

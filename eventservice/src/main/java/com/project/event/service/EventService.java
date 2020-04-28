package com.project.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.event.exception.EventAlreadyExistsException;
import com.project.event.exception.EventNotFoundException;
import com.project.event.model.EventInfo;
import com.project.event.repo.EventInfoRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EventService {

    @Autowired
    EventInfoRepo eventInfoRepo;

    public Flux<EventInfo> getAll() {
        return eventInfoRepo.findAll();
    }

    public Mono<EventInfo> getById(String id) {
        return eventInfoRepo.findById(id).switchIfEmpty(Mono.error(new EventNotFoundException("Not Found")));
    }

    public Mono<EventInfo> update(Mono<EventInfo> mono) {
        return mono.flatMap(eventInfoRepo::save).switchIfEmpty(Mono.error(new EventNotFoundException("Not Found")));
    }

    public Mono<EventInfo> addEvent(EventInfo eventInfo) {
        return eventInfoRepo.insert(eventInfo)
                .switchIfEmpty(Mono.error(new EventAlreadyExistsException("Already Exists")));
    }

    public Mono<Void> deleteEvent(String id) {
        return eventInfoRepo.deleteById(id);
    }
}

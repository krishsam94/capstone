package com.project.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.event.model.EventInfo;
import com.project.event.service.EventService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class EventInfoController {
    @Autowired
    private EventService eventService;

    @GetMapping("/event/get")
    public Flux<EventInfo> getAll() {
        return eventService.getAll();
    }

    @GetMapping("/ping")
    public String ping() {
        return "Pinged at " + System.currentTimeMillis();
    }
    
    @PostMapping("/event/add")
    public Mono<EventInfo> add(@RequestBody EventInfo eventInfo) {
        return eventService.addEvent(eventInfo);
    }
    
    @DeleteMapping("/event/delete/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return eventService.deleteEvent(id);
    }
    
    @PutMapping("/event/update")
    public Mono<EventInfo> update(@RequestBody EventInfo eventInfo) {
        Mono<EventInfo> mono = Mono.just(eventInfo);
        return eventService.update(mono);
    }
}

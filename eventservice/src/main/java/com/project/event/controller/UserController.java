package com.project.event.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.project.event.model.UserProfile;
import com.project.event.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public Flux<UserProfile> getAll() {
        return userService.getAll();
    }

    @PostMapping("/add")
    public Mono<UserProfile> add(@RequestBody UserProfile userProfile) {
        return userService.addUser(userProfile);
    }

    @GetMapping("/ping")
    public String ping() {
        return "Pinged at " + System.currentTimeMillis();
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/update")
    public Mono<UserProfile> update(@RequestBody UserProfile userProfile) {
        Mono<UserProfile> mono = Mono.just(userProfile);
        return userService.update(mono);
    }

    @PostMapping("/addevent/{userId}")
    public Mono<UserProfile> addEvent(@RequestBody EventInfo eventInfo, @PathVariable String userId) {
        Mono<UserProfile> mono = userService.getById(userId);

        Mono<UserProfile> resMono = mono.filter(user -> {
            if (user.getEventlist() != null) {
                List<EventInfo> list = user.getEventlist().stream()
                        .filter(event -> event.getId().equals(eventInfo.getId())).collect(Collectors.toList());
                if (!list.isEmpty())
                    return true;
            }
            return false;
        }).switchIfEmpty(userService.addEvents(eventInfo, mono));

        return userService.update(resMono);
    }

}

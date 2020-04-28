package com.project.event.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.event.exception.UserAlreadyExistsException;
import com.project.event.exception.UserNotFoundException;
import com.project.event.model.EventInfo;
import com.project.event.model.UserProfile;
import com.project.event.repo.UserRepo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    @Autowired
    EventService eventService;

    public Flux<UserProfile> getAll() {
        return userRepo.findAll();
    }

    public Mono<UserProfile> getById(String id) {
        return userRepo.findById(id).switchIfEmpty(Mono.error(new UserNotFoundException("Not Found")));
    }

    public Mono<UserProfile> update(Mono<UserProfile> mono) {
        return mono.flatMap(userRepo::save).switchIfEmpty(Mono.error(new UserNotFoundException("Not Found")));
    }

    public Mono<UserProfile> addUser(UserProfile userProfile) {
        return userRepo.insert(userProfile).switchIfEmpty(Mono.error(new UserAlreadyExistsException("Already Exists")));
    }

    public Mono<Void> deleteUser(String id) {
        return userRepo.deleteById(id);
    }

    public Mono<UserProfile> addEvents(EventInfo eventInfo, Mono<UserProfile> mono) {
        return mono.map(user -> {
            eventInfo.setTotaltravelhours(eventInfo.getTotaltravelhours() + user.getTravelhours());
            eventInfo.setTotalvolunteerhours(eventInfo.getTotalvolunteerhours() + user.getVolunteerhours());
            eventInfo.setTotalvolunteers(eventInfo.getTotalvolunteers() + 1);

            return eventService.update(Mono.just(eventInfo)).subscribe();
        }).then(mono.map(user -> {
            UserProfile resUser = user;
            List<EventInfo> eventlist = user.getEventlist();
            if (eventlist == null) {
                eventlist = new ArrayList<>();
                eventlist.add(eventInfo);
            } else
                eventlist.add(eventInfo);
            resUser.setEventlist(eventlist);
            return resUser;
        }));
    }
}

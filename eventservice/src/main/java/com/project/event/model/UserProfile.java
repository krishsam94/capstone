package com.project.event.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("userprofile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    
    @Id
    private String id;
    private String username;
    private int volunteerhours;
    private int travelhours;
    private int livesimpacted;
    private String businessunit;
    private String status;
    private List<EventInfo> eventlist;
}

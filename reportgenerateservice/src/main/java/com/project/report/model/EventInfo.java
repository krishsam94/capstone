package com.project.report.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventInfo {

    @Id
    private String id;
    private String month;
    private String baselocation;
    private String beneficiaryname;
    private String venueaddress;
    private String councilname;
    private String project;
    private String category;
    private String eventname;
    private String eventdescription;
    private String eventdate;
    private int totalvolunteers;
    private int totalvolunteerhours;
    private int totaltravelhours;
    private int livesimpacted;
    private String activitytype;
    private String status;
    private String poc_id;
    private String poc_name;
    private String poc_contact;
}

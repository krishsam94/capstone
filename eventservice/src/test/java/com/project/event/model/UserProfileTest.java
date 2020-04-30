package com.project.event.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;


class UserProfileTest {

    private UserProfile userProfile;

    @BeforeEach
    public void setUp() throws Exception {
        userProfile = new UserProfile();
        
        userProfile.setId("100001");
        userProfile.setBusinessunit("TestProject");
        userProfile.setEventlist(null);
        userProfile.setLivesimpacted(12);
        userProfile.setStatus("Active");
        userProfile.setTravelhours(52);
        userProfile.setUsername("User1");
        userProfile.setVolunteerhours(12);
        
    }

    @AfterEach
    public void tearDown() throws Exception {
        
        
    }

    @Test
    public void Beantest() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(UserProfile.class);


    }

}

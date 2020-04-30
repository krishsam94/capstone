package com.project.event.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

class EventInfoTest {

    private EventInfo eventInfo;

    @BeforeEach
    public void setUp() throws Exception {


        eventInfo = new EventInfo();

        eventInfo.setId("EVNT123");
        eventInfo.setActivitytype("Training");
        eventInfo.setBaselocation("Coimbatore");
        eventInfo.setBeneficiaryname("Test");
        eventInfo.setCategory("Training");
        eventInfo.setCouncilname("BEst counicl");
        eventInfo.setEventdate("Jun 2019");
        eventInfo.setLivesimpacted(123);
        eventInfo.setMonth("June");
        eventInfo.setProject("Test");
        eventInfo.setPoc_contact("1234");
        eventInfo.setTotalvolunteerhours(85);
        eventInfo.setStatus("Active");
    }

    @AfterEach
    public void tearDown() throws Exception {

    }

    @Test
    public void Beantest() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(EventInfo.class);

    }
}

/**
 * 
 */
package com.project.auth.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

/**
 * @author Krishna
 *
 */
class UserTest {

    private User user;

    @BeforeEach
    public void setUp() throws Exception {


        user = new User();

        user.setUserId("123123");
        user.setPassword("test");
        user.setCpassword("test");
        user.setRole("PMO");
    }

    @AfterEach
    public void tearDown() throws Exception {

    }

    @Test
    public void Beantest() {
        BeanTester beanTester = new BeanTester();
        beanTester.testBean(User.class);

    }

}

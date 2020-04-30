package com.project.auth.repo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.project.auth.model.User;

import reactor.core.publisher.Mono;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@DataJpaTest
class AuthRepoTest {

    @Autowired
    private AuthRepo authRepo;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUserId("100");
        user.setPassword("123456");
        user.setCpassword("123456");
    }

    @AfterEach
    public void tearDown() throws Exception {
        authRepo.deleteAll();
    }

    @Test
    public void testRegisterUserSuccess() {
        authRepo.save(user);

        Mono<User> fetchUser = authRepo.findById(user.getUserId());
        fetchUser.subscribe(curuser -> {
            assertThat(user.getUserId(), is(curuser.getUserId()));
        });
    }

    @Test
    public void testLoginUserSuccess() {
        authRepo.save(user);

        Mono<User> fetchUser = authRepo.findById(user.getUserId());
        fetchUser.subscribe(curuser -> {
            assertThat(user.getUserId(), is(curuser.getUserId()));
        });
    }
}

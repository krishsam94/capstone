package com.project.report.repo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.project.report.model.UserProfile;
import com.project.report.repo.UserRepo;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class UserRepoTest {
    @Autowired
    private UserRepo userRepo;

    private UserProfile userProfile;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
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
        userRepo.deleteAll();
    }

    @Test
    public void testRegisterUserSuccess() {
        userRepo.save(userProfile);

        Mono<UserProfile> fetchUser = userRepo.findById(userProfile.getId());
        fetchUser.subscribe(curuser -> {
            assertThat(userProfile.getId(), is(curuser.getId()));
        });
    }

}

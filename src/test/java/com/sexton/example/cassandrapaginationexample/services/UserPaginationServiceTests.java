package com.sexton.example.cassandrapaginationexample.services;

import com.sexton.example.cassandrapaginationexample.dto.CassandraPage;
import com.sexton.example.cassandrapaginationexample.exceptions.UserNotFoundException;
import com.sexton.example.cassandrapaginationexample.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPaginationServiceTests {
    @Autowired
    private UserPaginationService userPaginationService;

    @Test
    public void userPaginationServiceShouldNotBeNull() {
        assertNotNull(userPaginationService);
    }

    @Test
    public void shouldReturnPageOfUsersWithLengthTen() {
        final Integer expectedCount = 10;
        final CassandraPage<User> userPage = userPaginationService.getPageOfUsers(expectedCount);

        assertEquals(expectedCount, userPage.getCount());
        assertEquals(expectedCount.intValue(), userPage.getContent().size());

        assertTrue(userPage.getHasNext());

        assertNotNull(userPage.getPagingState());
        assertNotNull(userPage.getContent());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenInvalidUUIDIsGiven() throws UserNotFoundException {
        userPaginationService.getUserById("123");
    }

    @Test(expected = UserNotFoundException.class)
    public void shouldThrowUserNotFoundExceptionWhenValidUUIDIsGivenButDoesNotExist() throws UserNotFoundException {
        userPaginationService.getUserById("331986b7-594f-11e9-a4bf-bfa8f52b114c");
    }
}

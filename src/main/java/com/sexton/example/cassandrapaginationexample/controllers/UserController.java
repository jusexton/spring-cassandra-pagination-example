package com.sexton.example.cassandrapaginationexample.controllers;

import com.sexton.example.cassandrapaginationexample.dto.CassandraPage;
import com.sexton.example.cassandrapaginationexample.dto.GetUserByIdDTO;
import com.sexton.example.cassandrapaginationexample.dto.GetUserPageDTO;
import com.sexton.example.cassandrapaginationexample.exceptions.UserNotFoundException;
import com.sexton.example.cassandrapaginationexample.models.User;
import com.sexton.example.cassandrapaginationexample.services.UserPaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserPaginationService userPaginationService;

    @Autowired
    public UserController(final UserPaginationService userPaginationService) {
        this.userPaginationService = userPaginationService;
    }

    /**
     * Attempts to find a user by id. If found the user properties are returned.
     *
     * @param dto The bean tha represents incoming data passed by the url.
     * @return The found user
     * @throws UserNotFoundException Thrown when a user id was valid but did not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(final @Valid GetUserByIdDTO dto) throws UserNotFoundException {
        final User user = userPaginationService.getUserById(dto.getId());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Responds with a page of users based on the given limit and paging state.
     *
     * @param dto The bean that represents incoming data passed by the url.
     * @return The page of users
     */
    @GetMapping
    public ResponseEntity<CassandraPage<User>> getPageOfUsers(final @Valid GetUserPageDTO dto) {
        final CassandraPage<User> userPage = userPaginationService.getPageOfUsers(dto.getLimit(), dto.getPagingState());
        return new ResponseEntity<>(userPage, HttpStatus.OK);
    }
}

package com.sexton.cassandra.pagination.example.controllers;

import com.sexton.cassandra.pagination.example.dto.Paginated;
import com.sexton.cassandra.pagination.example.exceptions.ResourceNotFoundException;
import com.sexton.cassandra.pagination.example.models.User;
import com.sexton.cassandra.pagination.example.services.UserPaginationService;
import com.sexton.cassandra.pagination.example.dto.CassandraPage;
import com.sexton.cassandra.pagination.example.validation.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {
    private final UserPaginationService userPaginationService;

    /**
     * Attempts to find a user by id. If found the user properties are returned.
     *
     * @param id The id that should be used to look up the user
     * @return The found user
     * @throws ResourceNotFoundException Thrown when a user id was valid but did not exist.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable @UUID final String id) {
        return userPaginationService.getUserById(id);
    }

    /**
     * Responds with a page of users based on the given limit and paging state.
     *
     * @param paginated Paginated data accepted via query parameters
     * @return The page of users
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CassandraPage<User> getPageOfUsers(final @Valid Paginated paginated) {
        return userPaginationService.getPageOfUsers(paginated);
    }
}

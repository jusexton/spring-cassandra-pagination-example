package com.sexton.cassandra.pagination.example.exceptions;

import java.util.UUID;

/**
 * Thrown when a user could not be found.
 */
public class UserNotFoundException extends Exception {
    private String id;

    public UserNotFoundException(final String id) {
        super(String.format("The following user with id: %s could not be found.", id));
        this.id = id;
    }

    public UserNotFoundException(final UUID id) {
        this(id.toString());
    }

    public String getInvalidId() {
        return this.id;
    }
}

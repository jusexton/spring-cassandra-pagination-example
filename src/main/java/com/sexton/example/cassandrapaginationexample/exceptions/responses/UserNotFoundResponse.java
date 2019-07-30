package com.sexton.example.cassandrapaginationexample.exceptions.responses;

/**
 * Represents a response when a user could not be found.
 */
public class UserNotFoundResponse {
    private static final String DEFAULT_MESSAGE = "User with id: '%s' could not be found. Make sure you used the correct id.";

    private String invalidId;
    private String message;

    public UserNotFoundResponse(final String id) {
        this(id, String.format(DEFAULT_MESSAGE, id));
    }

    public UserNotFoundResponse(final String id, final String message) {
        this.invalidId = id;
        this.message = message;
    }

    public String getInvalidId() {
        return this.invalidId;
    }

    public String getMessage() {
        return this.message;
    }
}

package com.sexton.cassandra.pagination.example.services;

import com.sexton.cassandra.pagination.example.exceptions.UserNotFoundException;
import com.sexton.cassandra.pagination.example.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenInvalidUUIDIsGiven(@Mock UserRepository userRepository) {
        // No need to stub mock. Just need it to satisfy the dependency

        final UserService userService = new UserService(userRepository);

        assertThrows(IllegalArgumentException.class, () -> userService.getUserById("123"));
    }

    @Test
    public void shouldThrowUserNotFoundExceptionWhenRepositoryResultIsEmpty(@Mock UserRepository userRepository) {
        when(userRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        final UserService userService = new UserService(userRepository);

        assertThrows(UserNotFoundException.class, () -> userService.getUserById("331986ae-594f-11e9-a4bf-bfa8f52b114d"));
    }
}

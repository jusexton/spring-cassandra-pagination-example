package com.sexton.cassandra.pagination.example.services;

import com.sexton.cassandra.pagination.example.exceptions.ResourceNotFoundException;
import com.sexton.cassandra.pagination.example.models.User;
import com.sexton.cassandra.pagination.example.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    protected final UserRepository userRepository;

    public User getUserById(final String id) {
        return userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ResourceNotFoundException(id, User.class));
    }
}

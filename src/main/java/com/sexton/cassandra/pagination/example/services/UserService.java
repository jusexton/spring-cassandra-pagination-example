package com.sexton.cassandra.pagination.example.services;

import com.sexton.cassandra.pagination.example.exceptions.UserNotFoundException;
import com.sexton.cassandra.pagination.example.models.User;
import com.sexton.cassandra.pagination.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    protected final UserRepository userRepository;

    @Autowired
    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(final String id) throws UserNotFoundException {
        return userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}

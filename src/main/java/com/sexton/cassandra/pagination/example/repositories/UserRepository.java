package com.sexton.cassandra.pagination.example.repositories;

import com.sexton.cassandra.pagination.example.models.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<User, UUID> { }

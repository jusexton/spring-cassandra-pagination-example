package com.sexton.example.cassandrapaginationexample.repositories;

import com.sexton.example.cassandrapaginationexample.models.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<User, UUID> { }

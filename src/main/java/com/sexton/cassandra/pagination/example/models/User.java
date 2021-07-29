package com.sexton.cassandra.pagination.example.models;

import com.datastax.driver.core.utils.UUIDs;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Table("users")
public class User {
    @PrimaryKey
    private UUID id;
    private String firstName;
    private String lastName;

    public User(final String firstName, final String lastName) {
        this.id = UUIDs.timeBased();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("id: %s | firstname: %s | lastname: %s", id, firstName, lastName);
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }
}

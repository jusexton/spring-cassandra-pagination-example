package com.sexton.cassandra.pagination.example.dto;

import com.sexton.cassandra.pagination.example.validation.UUID;

public class GetUserByIdDTO {
    @UUID
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

package com.sexton.example.cassandrapaginationexample.dto;

import com.sexton.example.cassandrapaginationexample.validation.UUID;

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

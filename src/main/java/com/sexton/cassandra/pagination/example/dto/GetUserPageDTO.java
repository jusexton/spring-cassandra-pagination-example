package com.sexton.cassandra.pagination.example.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class GetUserPageDTO {
    public static final Integer DEFAULT_LIMIT = 10;

    @Min(1)
    @Max(100)
    private Integer limit = DEFAULT_LIMIT;
    private String pagingState;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getPagingState() {
        return pagingState;
    }

    public void setPagingState(String pagingState) {
        this.pagingState = pagingState;
    }
}

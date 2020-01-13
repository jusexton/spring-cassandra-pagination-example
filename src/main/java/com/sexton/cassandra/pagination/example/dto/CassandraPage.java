package com.sexton.cassandra.pagination.example.dto;

import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Objects;

public class CassandraPage<T> {
    private final Integer count;
    private final List<T> content;
    private final String pagingState;
    private final Boolean hasNext;

    public CassandraPage(final Slice<T> slice) {
        this.content = slice.getContent();
        this.count = content.size();
        this.hasNext = slice.hasNext();

        if (this.hasNext) {
            CassandraPageRequest pageRequest = (CassandraPageRequest) slice.nextPageable();
            this.pagingState = Objects.requireNonNull(pageRequest.getPagingState()).toString();
        } else {
            this.pagingState = null;
        }
    }

    public List<T> getContent() {
        return content;
    }

    public String getPagingState() {
        return pagingState;
    }

    public Integer getCount() {
        return count;
    }

    public Boolean getHasNext() {
        return hasNext;
    }
}

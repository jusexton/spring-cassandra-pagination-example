package com.sexton.cassandra.pagination.example.dto;

import lombok.Data;
import lombok.val;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
public class CassandraPage<T> {
    private final Integer count;
    private final List<T> content;
    private final String pagingState;
    private final Boolean hasNext;

    public CassandraPage(final Slice<T> slice) {
        this.content = slice.getContent();
        this.count = content.size();
        this.hasNext = slice.hasNext();
        this.pagingState = getPagingState(slice);
    }

    @Nullable
    private static <T> String getPagingState(final Slice<T> slice) {
        if (slice.hasNext()) {
            val pageRequest = (CassandraPageRequest) slice.nextPageable();
            return Objects.requireNonNull(pageRequest.getPagingState()).toString();
        } else {
            return null;
        }
    }

    public Optional<String> getPagingState() {
        return Optional.ofNullable(pagingState);
    }
}

package com.sexton.cassandra.pagination.example.services;

import com.datastax.driver.core.PagingState;
import com.sexton.cassandra.pagination.example.dto.CassandraPage;
import com.sexton.cassandra.pagination.example.dto.Paginated;
import com.sexton.cassandra.pagination.example.models.User;
import com.sexton.cassandra.pagination.example.repositories.UserRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class UserPaginationService extends UserService {
    @Autowired
    public UserPaginationService(final UserRepository userRepository) {
        super(userRepository);
    }

    public CassandraPage<User> getPageOfUsers(final Paginated paginated) {
        return getPageOfUsers(paginated.getLimit(), paginated.getPagingState().orElse(null));
    }

    public CassandraPage<User> getPageOfUsers(final Integer limit) {
        return getPageOfUsers(limit, null);
    }

    public CassandraPage<User> getPageOfUsers(final Integer limit, final String pagingState) {
        val pageRequest = createCassandraPageRequest(limit, pagingState);
        return getPageOfUsers(pageRequest);
    }

    public CassandraPage<User> getPageOfUsers(final CassandraPageRequest cassandraPageRequest) {
        val userSlice = userRepository.findAll(cassandraPageRequest);
        return new CassandraPage<>(userSlice);
    }

    private CassandraPageRequest createCassandraPageRequest(final Integer limit, @Nullable final String pagingState) {
        val pageRequest = PageRequest.of(0, limit);
        val pageState = pagingState != null ? PagingState.fromString(pagingState) : null;
        return CassandraPageRequest.of(pageRequest, pageState);
    }
}

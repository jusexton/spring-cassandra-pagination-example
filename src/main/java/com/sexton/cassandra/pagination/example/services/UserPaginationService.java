package com.sexton.cassandra.pagination.example.services;

import com.sexton.cassandra.pagination.example.models.User;
import com.sexton.cassandra.pagination.example.dto.CassandraPage;
import com.sexton.cassandra.pagination.example.repositories.UserRepository;
import com.datastax.driver.core.PagingState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class UserPaginationService extends UserService {
    @Autowired
    public UserPaginationService(final UserRepository userRepository) {
        super(userRepository);
    }

    public CassandraPage<User> getPageOfUsers(final Integer limit) {
        return getPageOfUsers(limit, null);
    }

    public CassandraPage<User> getPageOfUsers(final Integer limit, final String pagingState) {
        final CassandraPageRequest cassandraPageRequest = createCassandraPageRequest(limit, pagingState);
        return getPageOfUsers(cassandraPageRequest);
    }

    public CassandraPage<User> getPageOfUsers(final CassandraPageRequest cassandraPageRequest) {
        final Slice<User> userSlice = userRepository.findAll(cassandraPageRequest);
        return new CassandraPage<>(userSlice);
    }

    private CassandraPageRequest createCassandraPageRequest(final Integer limit, final String pagingState) {
        final PageRequest pageRequest = PageRequest.of(0, limit);
        final PagingState pageState = pagingState != null ? PagingState.fromString(pagingState) : null;
        return CassandraPageRequest.of(pageRequest, pageState);
    }
}

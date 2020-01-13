# Spring Cassandra Pagination Example with Spring Boot

Small CRUD application demonstration how to paginate results using Apache Cassandra with Spring Boot.

## How it Works
Simply put, Cassandra uses paging state to determine where to start reading results from. If no paging state is given,
cassandra will read the result set starting from the beginning.

The main key to this functionality in Spring Boot is creating the CassandraPageRequest.
Note the PageRequest object simply limits the number of results and does not determine where the results will be read from.
```
private CassandraPageRequest createCassandraPageRequest(final Integer limit, final String pagingState) {
    final PageRequest pageRequest = PageRequest.of(0, limit);
    final PagingState pageState = pagingState != null ? PagingState.fromString(pagingState) : null;
    return CassandraPageRequest.of(pageRequest, pageState);
}
```

Now simply use your CassandraPageRequest like it was any other page request.
```
public CassandraPage<User> getPageOfUsers(final CassandraPageRequest cassandraPageRequest) {
    final Slice<User> userSlice = userRepository.findAll(cassandraPageRequest);
    return new CassandraPage<>(userSlice);
}
```

Paging state is acquired from page requests to cassandra. It can be extracted by using the following method.
```
CassandraPageRequest pageRequest = (CassandraPageRequest) slice.nextPageable();
this.pagingState = Objects.requireNonNull(pageRequest.getPagingState()).toString();
```



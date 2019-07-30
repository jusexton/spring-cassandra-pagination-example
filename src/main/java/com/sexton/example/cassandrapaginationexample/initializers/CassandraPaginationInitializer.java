package com.sexton.example.cassandrapaginationexample.initializers;

import com.sexton.example.cassandrapaginationexample.models.User;
import com.sexton.example.cassandrapaginationexample.repositories.UserRepository;
import com.sexton.example.cassandrapaginationexample.util.UserSupplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Responsible for making sure the Cassandra database is always populated with USER_MAX users at all times.
 */
@Component
public class CassandraPaginationInitializer implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(CassandraPaginationInitializer.class);

    private static final String FIRST_NAMES_PATH = "names/first_names.txt";
    private static final String LAST_NAMES_PATH = "names/last_names.txt";

    private static final Long USER_MAX = 1_000L;

    private final UserRepository userRepository;

    @Autowired
    public CassandraPaginationInitializer(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        final Long count = userRepository.count();
        if (count.equals(USER_MAX)) {
            return;
        }

        // Easiest to just wipe the slate clean if there are not exactly USER_MAX users in database.
        userRepository.deleteAll();

        // Load the names that will be used to create the users
        final List<String> firstNames = loadNames(FIRST_NAMES_PATH);
        final List<String> lastNames = loadNames(LAST_NAMES_PATH);

        final UserSupplier supplier = new UserSupplier(firstNames, lastNames);
        final List<User> users = Stream.generate(supplier).limit(USER_MAX).collect(Collectors.toList());
        userRepository.saveAll(users);

        logCreatedUsers(users);
    }

    private void logCreatedUsers(List<User> users) {
        LOGGER.info("Users Created | Count {}", users.size());
        users.stream().map(User::toString).forEach(LOGGER::info);
    }

    private List<String> loadNames(final String path) throws IOException {
        final Path resourcePath = Paths.get(new ClassPathResource(path).getURI());
        return Files.readAllLines(resourcePath);
    }
}

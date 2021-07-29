package com.sexton.cassandra.pagination.example.initializers;

import com.sexton.cassandra.pagination.example.models.User;
import com.sexton.cassandra.pagination.example.repositories.UserRepository;
import com.sexton.cassandra.pagination.example.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Responsible for making sure the Cassandra database is always populated with USER_MAX users at all times.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CassandraPaginationInitializer implements CommandLineRunner {
    private static final String FIRST_NAMES_PATH = "names/first_names.txt";
    private static final String LAST_NAMES_PATH = "names/last_names.txt";

    private static final long USER_MAX = 1_000L;

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        val count = userRepository.count();
        if (count == USER_MAX) {
            return;
        }

        // Easiest to just wipe the slate clean if there are not exactly USER_MAX users in database.
        userRepository.deleteAll();

        val firstNames = loadNames(FIRST_NAMES_PATH);
        val lastNames = loadNames(LAST_NAMES_PATH);

        val supplier = new RandomUserSupplier(firstNames, lastNames);
        val users = Stream.generate(supplier).limit(USER_MAX).collect(Collectors.toList());
        userRepository.saveAll(users);

        logCreatedUsers(users);
    }

    private void logCreatedUsers(List<User> users) {
        log.info("Users Created | Count {}", users.size());
        users.stream().map(User::toString).forEach(log::info);
    }

    private List<String> loadNames(final String path) throws IOException {
        final Path resourcePath = Paths.get(new ClassPathResource(path).getURI());
        return Files.readAllLines(resourcePath);
    }

    @RequiredArgsConstructor
    private static class RandomUserSupplier implements Supplier<User> {
        private final List<String> firstNames;
        private final List<String> lastNames;

        @Override
        public User get() {
            val firstName = getRandomName(firstNames);
            val lastName = getRandomName(lastNames);

            return new User(firstName, lastName);
        }

        private String getRandomName(final List<String> names) {
            return names.get(RandomUtil.nextInt(names.size() - 1));
        }
    }
}

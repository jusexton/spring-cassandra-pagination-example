package com.sexton.cassandra.pagination.example.util;

import java.util.Random;

public final class RandomUtil {
    private static final Random RANDOM = new Random();

    public static Integer nextInt(final Integer max) {
        return RandomUtil.nextInt(0, max);
    }

    /**
     * @param min The minimum number that can be achieved (inclusive)
     * @param max The maximum number that can be achieved (inclusive)
     * @return The random number between the given range.
     */
    public static Integer nextInt(final Integer min, final Integer max) {
        if (min > max) {
            throw new IllegalArgumentException("Minimum can not be larger than maximum.");
        }

        return RANDOM.nextInt((max - min) + 1) + min;
    }

    private RandomUtil() {
        // Private constructor in order to stop initialization of class.
    }
}

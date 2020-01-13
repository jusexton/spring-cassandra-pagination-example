package com.sexton.cassandra.pagination.example.util;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomUtilTest {
    @RepeatedTest(100)
    public void shouldReturnValuesAllBetweenGivenRange() {
        final int min = 0;
        final int max = 10;
        final int result = RandomUtil.nextInt(min, max);

        assertTrue(result >= min);
        assertTrue(result <= max);
    }

    @RepeatedTest(100)
    public void shouldReturnThePassedNumberWhenBothMinimumAndMaximumAreEqual() {
        final int min = 10;
        final int max = 10;
        final int result = RandomUtil.nextInt(min, max);

        assertEquals(min, result);
        assertEquals(max, result);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenMinimumIsGreaterThanMaximum() {
        assertThrows(IllegalArgumentException.class, () -> RandomUtil.nextInt(10, 5));
    }
}

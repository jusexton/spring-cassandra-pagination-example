package com.sexton.example.cassandrapaginationexample.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class RandomUtilTests {
    @Test
    @Repeat(value = 1000)
    public void shouldReturnValuesAllBetweenGivenRange() {
        final Integer min = 0;
        final Integer max = 10;
        final Integer result = RandomUtil.nextInt(min, max);

        assertTrue(result >= min);
        assertTrue(result <= max);
    }

    @Test
    @Repeat(value = 100)
    public void shouldReturnThePassedNumberWhenBothMinimumAndMaximumAreEqual() {
        final Integer min = 10;
        final Integer max = 10;
        final Integer result = RandomUtil.nextInt(min, max);

        assertEquals(min, result);
        // Redundant, but whatever
        assertEquals(max, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenMinimumIsGreaterThanMaximum() {
        RandomUtil.nextInt(10, 5);
    }
}

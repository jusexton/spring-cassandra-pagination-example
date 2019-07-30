package com.sexton.example.cassandrapaginationexample.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class StringUtilTests {
    // TODO: Make parameterized test and pass multiple valid UUIDs
    @Test
    public void shouldReturnTrueWhenGivenValidUUIDString() {
        final Boolean result = StringUtil.isValidUUID("331986ae-594f-11e9-a4bf-bfa8f52b114d");
        assertTrue(result);
    }

    // TODO: Make parameterized test and pass multiple invalid UUIDs
    @Test
    public void shouldReturnFalseWhenGivenInvalidUUIDString() {
        final Boolean result = StringUtil.isValidUUID("123");
        assertFalse(result);
    }
}

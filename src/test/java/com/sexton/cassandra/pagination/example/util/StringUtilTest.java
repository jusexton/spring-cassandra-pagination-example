package com.sexton.cassandra.pagination.example.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringUtilTest {
    @ParameterizedTest
    @ValueSource(strings = {
            "331986ae-594f-11e9-a4bf-bfa8f52b114d",
    })
    public void shouldReturnTrueWhenGivenValidUUIDString(final String validUUID) {
        final boolean result = StringUtil.isValidUUID(validUUID);
        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "123",
            "33198676Re-594f-11e9-a4bf-bfa8f52b114d",
            "331986ae-594f-11e10Z-a4bf-bfa8f52b114d"
    })
    public void shouldReturnFalseWhenGivenInvalidUUIDString(final String invalidUUID) {
        final boolean result = StringUtil.isValidUUID(invalidUUID);
        assertFalse(result);
    }
}

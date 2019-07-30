package com.sexton.example.cassandrapaginationexample.util;

import java.util.regex.Pattern;

public final class StringUtil {
    // Represents a UUID string pattern.
    private static final Pattern VALID_UUID = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    /**
     * @param str The string to check UUID format.
     * @return Whether the given string complies with a UUID format or not.
     */
    public static boolean isValidUUID(final String str) {
        if (str.isEmpty()) {
            return false;
        }

        return VALID_UUID.matcher(str).matches();
    }

    private StringUtil() {
        // Private constructor in order to stop initialization of class.
    }
}

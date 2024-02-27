package com.carbon.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class StringUtilTest {

    private String string;

    @BeforeEach
    void initialize() {
        string = "A - Lara - 1 - 1 - S - AADADAGGA";
    }

    @Test
    void assertStringSanitizedReturnsArray() {
        String[] sArray = StringUtil.sanitize(string);
        assertArrayEquals(new String[]{"A", "Lara", "1", "1", "S", "AADADAGGA"}, sArray);
    }
}

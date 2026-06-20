package com.wiloon.playground;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class SamDemoTest {

    @Test
    void customSamImplementationsReturnSameLength() {
        String sample = "hello SAM";
        int expected = sample.length();

        assertEquals(expected, SamDemo.lengthOf(SamDemo.anonymousStringLength(), sample));
        assertEquals(expected, SamDemo.lengthOf(SamDemo.lambdaStringLength(), sample));
        assertEquals(expected, SamDemo.lengthOf(SamDemo.methodRefStringLength(), sample));
    }

    @Test
    void predicateSamDetectsBlankStrings() {
        assertFalse(SamDemo.isNonBlank("  "));
        assertTrue(SamDemo.isNonBlank("x"));
    }

    @Test
    void comparatorSamSortsByLength() {
        List<String> sorted = SamDemo.sortedByLength(List.of("banana", "fig", "apple"));
        assertEquals(List.of("fig", "apple", "banana"), sorted);
    }
}

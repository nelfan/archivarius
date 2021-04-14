package com.example.tables;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    @DisplayName("single word")
    void normalizeName1() {
        String name = "long";
        String result = Utility.normalizeName(name);
        String expected = "LONG";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("double word")
    void normalizeName2() {
        String name = "longWord";
        String result = Utility.normalizeName(name);
        String expected = "LONG_WORD";
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("triple word")
    void normalizeName3() {
        String name = "superLongWord";
        String result = Utility.normalizeName(name);
        String expected = "SUPER_LONG_WORD";
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("two consecutive capital chars")
    void normalizeName4() {
        String name = "superLongUSA";
        String result = Utility.normalizeName(name);
        String expected = "SUPER_LONG_USA";
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("group of digits at end")
    void normalizeName5() {
        String name = "superLong001";
        String result = Utility.normalizeName(name);
        String expected = "SUPER_LONG_001";
        assertEquals(expected, result);
    }
    @Test
    @DisplayName("group of digits in the middle")
    void normalizeName6() {
        String name = "super001Long";
        String result = Utility.normalizeName(name);
        String expected = "SUPER_001_LONG";
        assertEquals(expected, result);
    }
}
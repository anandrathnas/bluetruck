package com.rathnas.bluetruck.config;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BlueTruckConfigPropertiesTest {
    @Test
    void testDefaultValues() {
        BlueTruckConfigProperties properties = new BlueTruckConfigProperties();
        assertArrayEquals(new String[]{"dev", "default", "local", "localhost"}, properties.getProfiles());
        assertEquals(200, properties.getFrequency());
        assertEquals(200, properties.getDuration());
    }
}

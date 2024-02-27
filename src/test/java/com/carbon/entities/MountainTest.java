package com.carbon.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MountainTest {

    @Test
    void assertMountainX() {
        Mountain m = new Mountain(1, 3);
        assertEquals(1, m.getX());
    }

    @Test
    void assertMountainY() {
        Mountain m = new Mountain(1, 3);
        assertEquals(3, m.getY());
    }
}

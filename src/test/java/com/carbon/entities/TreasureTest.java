package com.carbon.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreasureTest {

    private Treasure treasure;

    @BeforeEach
    void initialize() {
        treasure = new Treasure(1, 3, 1);
    }

    @Test
    void assertTreasureX() {
        assertEquals(1, treasure.getX());
    }

    @Test
    void assertTreasureY() {
        assertEquals(3, treasure.getY());
    }

    @Test
    void assertTreasureQty() {
        assertEquals(1, treasure.getQuantity());
    }

    @Test
    void assertTreasureDecreaseQty() {
        treasure.decreaseQty();
        assertEquals(0, treasure.getQuantity());
    }
}

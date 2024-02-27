package com.carbon.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AdventurerTest {

    private Adventurer adventurer;

    @BeforeEach
    void initialize() {
        String name = "Lara";
        char orientation = 'S';
        String movement = "AADADAGGA";

        adventurer = new Adventurer(name, 1, 1, orientation, movement);
    }

    @Test
    void assetThatMovesAreCharArray() {
        assertArrayEquals(new char[]{'A', 'A', 'D', 'A', 'D', 'A', 'G', 'G', 'A'}, adventurer.getMoves());
    }

    @Test
    void assertMovesSize() {
        assertEquals(9, adventurer.getMovesSize());
    }

    @Test
    void assertForwardWithUnknowOrientation() {
        adventurer.setOrientation('M');
        UnsupportedOperationException ex
                = assertThrows(UnsupportedOperationException.class, () -> {
            adventurer.forward();
        });

        assertEquals("Unknown orientation", ex.getMessage());
    }

    @Test
    void assertForwardWithNorthOrientation() {
        adventurer.setOrientation('N');
        adventurer.forward();
        assertEquals(0, adventurer.getY());
    }

    @Test
    void assertForwardWithSouthOrientation() {
        adventurer.forward();
        assertEquals(2, adventurer.getY());
    }

    @Test
    void assertForwardWithEastOrientation() {
        adventurer.setOrientation('E');
        adventurer.forward();
        assertEquals(2, adventurer.getX());
    }

    @Test
    void assertForwardWithWestOrientation() {
        adventurer.setOrientation('O');
        adventurer.forward();
        assertEquals(0, adventurer.getX());
    }

    @Test
    void assertRotateLeftWithNorthOrientation() {
        adventurer.setOrientation('N');
        adventurer.rotate('G');
        assertEquals('O', adventurer.getOrientation());
    }

    @Test
    void assertRotateLeftWithSouthOrientation() {
        adventurer.rotate('G');
        assertEquals('E', adventurer.getOrientation());
    }

    @Test
    void assertRotateLeftWithEastOrientation() {
        adventurer.setOrientation('E');
        adventurer.rotate('G');
        assertEquals('N', adventurer.getOrientation());
    }

    @Test
    void assertRotateLeftWithWestOrientation() {
        adventurer.setOrientation('O');
        adventurer.rotate('G');
        assertEquals('S', adventurer.getOrientation());
    }

    @Test
    void assertRotateRightWithNorthOrientation() {
        adventurer.setOrientation('N');
        adventurer.rotate('D');
        assertEquals('E', adventurer.getOrientation());
    }

    @Test
    void assertRotateRightWithSouthOrientation() {
        adventurer.rotate('D');
        assertEquals('O', adventurer.getOrientation());
    }

    @Test
    void assertRotateRightWithEastOrientation() {
        adventurer.setOrientation('E');
        adventurer.rotate('D');
        assertEquals('S', adventurer.getOrientation());
    }

    @Test
    void assertRotateRightWithWestOrientation() {
        adventurer.setOrientation('O');
        adventurer.rotate('D');
        assertEquals('N', adventurer.getOrientation());
    }

    @Test
    void assertIncrementTreasureQty() {
        adventurer.incrementTreasureQty();
        assertEquals(1, adventurer.getTreasureQuantity());
    }
}

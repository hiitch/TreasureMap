package com.carbon.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.carbon.design.AbstractCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class BoardTest {

    private Board board;

    @BeforeEach
    void initialize() {
        int width  = 5;
        int height = 5;
        List<Mountain> mountains = new ArrayList<>(
                Arrays.asList(
                        new Mountain(0,0),
                        new Mountain(1, 4),
                        new Mountain(4,2)
                ));

        List<Treasure> treasures = new ArrayList<>(
                Arrays.asList(
                        new Treasure(1,2, 3),
                        new Treasure(3, 4, 1),
                        new Treasure(3,2, 2)
                ));

        board = new Board(width, height, mountains, treasures);
    }

    @Test
    void assertBoardWidth() {
        assertEquals(5, board.getWidth());
    }

    @Test
    void assertBoardHeight() {
        assertEquals(5, board.getHeight());
    }

    @Test
    void assertBoardIsCreated() {
        assertNotNull(board.getBoard());
    }

    @Test
    void assertCaseIsMountain() {
        AbstractCase m = board.getContent(0,0);
        assertInstanceOf(Mountain.class, m);
    }

    @Test
    void assertCaseIsTreasure() {
        AbstractCase t = board.getContent(1,2);
        assertInstanceOf(Treasure.class, t);
    }

    @Test
    void assertTreasureQty() {
        Treasure t = (Treasure) board.getContent(1, 2);
        assertEquals(3, t.getQuantity());
    }

    @Test
    void assertTreasureQtyDecreased() {
        Treasure t = (Treasure) board.getContent(1, 2);
        t.decreaseQty();
        assertEquals(2, t.getQuantity());
    }
}

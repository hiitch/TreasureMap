package com.carbon.engine;

import com.carbon.entities.Adventurer;
import com.carbon.entities.Board;
import com.carbon.entities.Mountain;
import com.carbon.entities.Treasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class GameEngineImplTest {

    private GameEngine gameEngine;
    private Adventurer adventurer;

    @BeforeEach
    void initialize() {
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

        gameEngine = new GameEngineImpl(new Board(5, 5, mountains, treasures),
                Arrays.asList(new Adventurer("Lara", 1, 1, 'S', "AADADAGGA"),
                        new Adventurer("Max", 1, 3, 'S', "D")
                ));

        adventurer = gameEngine.getAdventurers().get(0);
    }

    @Test
    void assertAdventurerMoveForward() {
        gameEngine.move(adventurer, 'A');
        assertEquals(1, adventurer.getX());
        assertEquals(2, adventurer.getY());
    }

    @Test
    void assertAdventurerMoveAndIsMountain() {
        adventurer.setY(3);
        gameEngine.move(adventurer, 'A');
        assertEquals(1, adventurer.getX());
        assertEquals(3, adventurer.getY());
        assertEquals(0, adventurer.getTreasureQuantity());
    }

    @Test
    void assertAdventurerMoveAndIsTreasure() {
        gameEngine.move(adventurer, 'A');
        Treasure treasure = (Treasure) gameEngine.getBoard().getContent(adventurer.getX(), adventurer.getY());
        assertEquals(1, adventurer.getX());
        assertEquals(2, adventurer.getY());
        assertEquals(2, treasure.getQuantity());
        assertEquals(1, adventurer.getTreasureQuantity());
    }

    @Test
    void assertAdventurerMoveAndIsAdventurer() {
        adventurer.setY(2);
        assertEquals(1, adventurer.getX());
        assertEquals(2, adventurer.getY());
        assertEquals(0, adventurer.getTreasureQuantity());
    }

    @Test
    void assertAdventurerMoveUnknowAction() {
        adventurer.setOrientation('M');
        UnsupportedOperationException ex
                = assertThrows(UnsupportedOperationException.class, () -> {
            gameEngine.moveForward(adventurer);
        });

        assertEquals("Unknown orientation", ex.getMessage());
    }

    @Test
    void assertRotateLeftWithNorthOrientation() {
        adventurer.setOrientation('N');
        gameEngine.move(adventurer, 'G');
        assertEquals('O', adventurer.getOrientation());
    }

    @Test
    void assertRotateLeftWithSouthOrientation() {
        gameEngine.move(adventurer, 'G');
        assertEquals('E', adventurer.getOrientation());
    }

    @Test
    void assertRotateLeftWithEastOrientation() {
        adventurer.setOrientation('E');
        gameEngine.move(adventurer, 'G');
        assertEquals('N', adventurer.getOrientation());
    }

    @Test
    void assertRotateLeftWithWestOrientation() {
        adventurer.setOrientation('O');
        gameEngine.move(adventurer, 'G');
        assertEquals('S', adventurer.getOrientation());
    }

    @Test
    void assertRotateRightWithNorthOrientation() {
        adventurer.setOrientation('N');
        gameEngine.move(adventurer, 'D');
        assertEquals('E', adventurer.getOrientation());
    }

    @Test
    void assertRotateRightWithSouthOrientation() {
        gameEngine.move(adventurer, 'D');
        assertEquals('O', adventurer.getOrientation());
    }

    @Test
    void assertRotateRightWithEastOrientation() {
        adventurer.setOrientation('E');
        gameEngine.move(adventurer, 'D');
        assertEquals('S', adventurer.getOrientation());
    }

    @Test
    void assertRotateRightWithWestOrientation() {
        adventurer.setOrientation('O');
        gameEngine.move(adventurer, 'D');
        assertEquals('N', adventurer.getOrientation());
    }

    @Test
    void assertGameStartedWithEmptyMove() {
        adventurer.setMovement("");
        gameEngine = mock(GameEngineImpl.class);
        doNothing().when(gameEngine).move(adventurer, 'A');
        verify(gameEngine, times(0)).move(adventurer, 'A');
    }
}

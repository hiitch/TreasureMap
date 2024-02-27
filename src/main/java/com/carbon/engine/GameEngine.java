package com.carbon.engine;

import com.carbon.entities.Adventurer;
import com.carbon.entities.Board;

import java.util.List;

public interface GameEngine {

    Board getBoard();
    List<Adventurer> getAdventurers();
    void play();
    void move(Adventurer adventurer, char action);
    void moveForward(Adventurer adventurer);

}

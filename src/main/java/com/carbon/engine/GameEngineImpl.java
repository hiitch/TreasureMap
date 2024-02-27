package com.carbon.engine;

import com.carbon.design.AbstractCase;
import com.carbon.entities.Adventurer;
import com.carbon.entities.Mountain;
import com.carbon.entities.Board;
import com.carbon.entities.Treasure;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class GameEngineImpl implements GameEngine {

    private List<Adventurer> adventurers;
    private Board board;

    public GameEngineImpl(Board board, List<Adventurer> adventurers)  {
        this.board = board;
        this.adventurers = adventurers;
    }

    @Override
    public void play() {
        Optional<Integer> maxMoves = getAdventurersMaxMoves();
        if (!maxMoves.isPresent()) {
            return;
        }

        int idx = 0;
        int maxMoveSize = maxMoves.get();

        while (idx < maxMoveSize) {
            for (Adventurer a : adventurers) {
                if (a.getMovesSize() <= maxMoveSize) {
                    char action = a.getMoves()[idx];
                    move(a, action);
                }
            }
            idx++;
        }
    }

    private Optional<Integer> getAdventurersMaxMoves() {
        return adventurers.stream()
                .filter(a -> a.getMovesSize() != 0)
                .max(Comparator.comparingInt(Adventurer::getMovesSize))
                .map(Adventurer::getMovesSize);
    }

    @Override
    public void move(Adventurer adventurer, char action) {
        if (action == 'A') {
            moveForward(adventurer);
        } else {
            adventurer.rotate(action);
        }
    }

    @Override
    public void moveForward(Adventurer adventurer) {
        int newX = adventurer.getX();
        int newY = adventurer.getY();

        switch(adventurer.getOrientation()) {
            case 'N':
                newY--;
                break;
            case 'S':
                newY++;
                break;
            case 'E':
                newX++;
                break;
            case 'O':
                newX--;
                break;
            default: throw new UnsupportedOperationException("Unknown orientation");
        }
        doCaseAction(adventurer, newX, newY);
    }

    private void doCaseAction(Adventurer adventurer, int newX, int newY) {
        AbstractCase content = board.getContent(newX, newY);
        if (!(content instanceof Mountain) && !isAdventureCase(newX, newY)) {
            if (content instanceof Treasure treasure) {
                treasure.decreaseQty();
                adventurer.incrementTreasureQty();
            }
            adventurer.setX(newX);
            adventurer.setY(newY);
        }
    }

    private boolean isAdventureCase(int newX, int newY) {
        Optional<Adventurer> adventurer = this.adventurers.stream()
                .filter(a -> a.getX() == newX && a.getY() == newY)
                .findFirst();

        return adventurer.isPresent();
    }

    @Override
    public Board getBoard() {
        return this.board;
    }

    @Override
    public List<Adventurer> getAdventurers() {
        return this.adventurers;
    }

}

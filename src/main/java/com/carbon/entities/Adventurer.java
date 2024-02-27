package com.carbon.entities;

import com.carbon.design.AbstractCase;
import com.carbon.engine.GameEngine;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Adventurer extends AbstractCase {

    private String name;
    private char orientation;
    private String movement;
    private int treasureQuantity;

    public Adventurer(String name, int x, int y, char orientation, String movement) {
        super(x, y);
        this.name = name;
        this.orientation = orientation;
        this.movement = movement;
        this.treasureQuantity = 0;
    }

    public char[] getMoves() {
        return this.getMovement().toCharArray();
    }

    public int getMovesSize() {
        return this.getMoves().length;
    }

    public void forward() {
        switch(this.orientation) {
            case 'N':
                this.y--;
                break;
            case 'S':
                this.y++;
                break;
            case 'E':
                this.x++;
                break;
            case 'O':
                this.x--;
                break;
            default: throw new UnsupportedOperationException("Unknown orientation");
        }
    }

    public void rotate(char direction) {
        if (direction == 'D') {
            rotateRight();
        } else if (direction == 'G') {
            rotateLeft();
        }
    }

    private void rotateRight() {
        switch (this.orientation) {
            case 'N':
                this.orientation = 'E';
                break;
            case 'S':
                this.orientation = 'O';
                break;
            case 'E':
                this.orientation = 'S';
                break;
            case 'O':
                this.orientation = 'N';
                break;
            default: throw new UnsupportedOperationException();
        }
    }

    private void rotateLeft() {
        switch (this.orientation) {
            case 'N':
                this.orientation = 'O';
                break;
            case 'S':
                this.orientation = 'E';
                break;
            case 'E':
                this.orientation = 'N';
                break;
            case 'O':
                this.orientation = 'S';
                break;
            default: throw new UnsupportedOperationException();
        }
    }

    public void incrementTreasureQty() {
        this.treasureQuantity++;
    }
}

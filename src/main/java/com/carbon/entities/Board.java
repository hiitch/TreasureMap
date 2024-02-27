package com.carbon.entities;

import com.carbon.design.AbstractCase;
import lombok.Getter;

import java.util.List;

@Getter
public class Board {

    private int width;
    private int height;
    private AbstractCase[][] board;

    public Board(int width, int height, List<Mountain> mountains, List<Treasure> treasures) {
        this.width = width;
        this.height = height;
        this.board = new AbstractCase[width][height];

        mountains.stream().forEach(m -> {
            this.board[m.getX()][m.getY()] = m;
        });

        treasures.stream().forEach(t -> {
            this.board[t.getX()][t.getY()] = t;
        });
    }

    public AbstractCase getContent(int x, int y) {
        return board[x][y];
    }
}

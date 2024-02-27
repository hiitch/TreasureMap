package com.carbon.design;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractCase {

    protected int x;
    protected int y;

    public AbstractCase(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

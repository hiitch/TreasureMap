package com.carbon.entities;

import com.carbon.design.AbstractCase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Treasure extends AbstractCase {

    private int quantity;

    public Treasure(int x, int y, int quantity) {
        super(x, y);
        this.quantity = quantity;
    }

    public void decreaseQty() {
        if (quantity > 0) {
            this.quantity--;
        }
    }

}
